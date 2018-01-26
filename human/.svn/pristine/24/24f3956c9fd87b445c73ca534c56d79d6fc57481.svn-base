package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.jzbTest.dao.JzbPaperConfigDetailDao;
import com.human.jzbTest.dao.JzbPaperMonthConfigDao;
import com.human.jzbTest.dao.JzbPaperMonthLevelDao;
import com.human.jzbTest.dao.JzbPaperQuestionDao;
import com.human.jzbTest.dao.JzbQuestionAnswerDao;
import com.human.jzbTest.dao.JzbQuestionDao;
import com.human.jzbTest.dao.JzbQuestionPaperDao;
import com.human.jzbTest.dao.JzbStudentDao;
import com.human.jzbTest.dao.jzbKnowledgePointDao;
import com.human.jzbTest.dao.jzbPaperMainConfigDao;
import com.human.jzbTest.entity.CacheQuestion;
import com.human.jzbTest.entity.JzbCacheAnswer;
import com.human.jzbTest.entity.JzbPaperConfigDetail;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.JzbQuestionKnowledge;
import com.human.jzbTest.entity.JzbQuestionPaper;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.TimeUtil;

@Service
public class JzbQuestionServiceImpl implements JzbQuestionService{
    
    @Autowired
    private JzbQuestionDao qDao;
    
    @Autowired
    private jzbKnowledgePointDao pointDao;
    
    @Autowired 
    jzbPaperMainConfigDao jzbDao;
    
    @Autowired 
    JzbQuestionPaperDao paperDao;
    
    @Autowired 
    JzbPaperQuestionDao paperQuestionDao;
    
    @Autowired 
    JzbQuestionAnswerDao answerDao;
    
    @Autowired
    private SmsTempService smsTempService;
    
    @Autowired 
    JzbPaperMonthConfigDao jzbPaperMonthConfigDao;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    @Autowired
    private JzbStudentDao jzbStudentDao;
    
    @Resource
    JzbPaperMonthConfigDao jzb;
    
    @Resource
    JzbPaperMonthLevelDao leDao;
    
    @Resource 
    JzbPaperMonthLevelDao moDao;
    @Resource 
    private JzbPaperConfigDetailDao detailDao;
    
    @Resource
    JzbPaperMonthConfigDao monConfigDao;
    
    @Resource 
    private jzbKnowledgePointDao knowledgeDao;
    
    
    @Override
    public PageView selectQuestionPage(PageView pageView,JzbQuestion question) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", question);
        List<JzbQuestion> list = qDao.selectQuestionPage(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<Integer> selectForQuestion(String month,String subject,String classType,String grade,String deptId) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("month", month);
        map.put("subject", subject);
        map.put("classType", classType);
        map.put("grade", grade);
        return qDao.selectForQuestion(map);
    }
    
    @Override
    @Transactional
    public void addQuestion(JzbQuestion question,Map<String,String> imgMap) {
        String qInfo = question.getQuestionInfos();
        qInfo = qInfo.replace("@#@", "\"");
        String tkCode = getTkCode(question);
        question.setqCode(tkCode);
        String qType = question.getqType();
        /**
         * 单选题
         */
        if("1".equals(qType)){
            //插入题目
            qDao.insert(question);
            Integer questionId= question.getId();
            //设置答案
            List<JzbQuestionAnswer> answers = JSON.parseArray(qInfo, JzbQuestionAnswer.class);
            if(!imgMap.isEmpty()){
                for(JzbQuestionAnswer ja:answers){
                    if(imgMap.containsKey(ja.getXh())){
                        ja.setaImg(imgMap.get(ja.getXh()));
                    }
                }
            }
            for(JzbQuestionAnswer j:answers){
                j.setQuestionId(questionId);
            }
            qDao.insertQuestionAnswers(answers);
            //插入知识点
            setSimpleKnowledge(question);
           
        }else{
            addMultiQuestion(question);
        }
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,qType);
        refreshAnswerCache(codePre);
    }
    
    @Override
    @Transactional
    public void addTkQuestion(JzbQuestion question) {
        String tkCode = getTkCode(question);
        question.setqCode(tkCode);
        String qType = question.getqType();
        /**
         * 单选题
         */
        if("1".equals(qType)){
            qDao.insert(question);
            setSimpleKnowledge(question);
        }else{
            String qInfo = question.getQuestionInfos();
            qInfo = qInfo.replace("@#@", "\"");
            List<JzbQuestionKnowledge> knowledges = new ArrayList<JzbQuestionKnowledge>();
            List<JzbQuestion> questions = JSON.parseArray(qInfo, JzbQuestion.class);
            for(JzbQuestion q:questions){
                question.setqContent(q.getqContent());
                question.setTkNum(q.getTkNum());
                question.setTkAnswer(q.getTkAnswer());
                qDao.insert(question);
                JzbQuestionKnowledge k = new JzbQuestionKnowledge();
                k.setQuestionId(question.getId());
                knowledges.add(k);
            }
            List<JzbQuestionKnowledge> tmpknowledges = new ArrayList<JzbQuestionKnowledge>();;
            for(JzbQuestionKnowledge j:knowledges){
                String knowledge = question.getqKnowledge(); 
                JzbQuestionKnowledge k1 = new JzbQuestionKnowledge();
                k1.setKnowledgeId(Integer.valueOf(knowledge));
                k1.setQuestionId(j.getQuestionId());
                tmpknowledges.add(k1);
            }
            qDao.insertQuestionKnowledges(tmpknowledges);
        }
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,qType);
        refreshAnswerCache(codePre);
    }
    
    
    @Override
    @Transactional
    public void editSimpleQuestion(JzbQuestion question, Map<String, String> imgMap) {
        String tkCode= question.getqCode();
        String qInfo = question.getQuestionInfos();
        qInfo = qInfo.replace("@#@", "\"");
        if(isCodeNeedChange(question)){
            tkCode = getTkCode(question);
            question.setqCode(tkCode);
        }
        qDao.updateByPrimaryKeySelective(question);
        Integer questionId= question.getId();
        List<JzbQuestionAnswer> answers = JSON.parseArray(qInfo, JzbQuestionAnswer.class);
        if(!imgMap.isEmpty()){
            for(JzbQuestionAnswer ja:answers){
                if(imgMap.containsKey(ja.getXh())){
                    ja.setaImg(imgMap.get(ja.getXh()));
                }
            }
        }
       
        for(JzbQuestionAnswer j:answers){
            j.setQuestionId(questionId);
            qDao.updateAnswerByQuestionIdAndXh(j);
        }
        
        
        
        List<JzbQuestionKnowledge> knowledges = new ArrayList<JzbQuestionKnowledge>();
        if("2".equals(question.getZsdType())){
            jzbKnowledgePoint p = new jzbKnowledgePoint();
            p.setClassType(question.getqClasstype());
            p.setGrade(question.getqGrade());
            p.setDept(question.getqDept());
            p.setCompany(Common.getMyUser().getCompanyId());
            p.setTitleNum(1);
            pointDao.insert(p);
            JzbQuestionKnowledge k = new JzbQuestionKnowledge();
            k.setKnowledgeId(p.getId());
            k.setQuestionId(questionId);
            knowledges.add(k);
        }else{
            String knowledge = question.getqKnowledge();
            JzbQuestionKnowledge k1 = new JzbQuestionKnowledge();
            k1.setKnowledgeId(Integer.valueOf(knowledge));
            k1.setQuestionId(questionId);
            knowledges.add(k1);
        }    
        qDao.deleteQuestionKnowledges(questionId);
        qDao.insertQuestionKnowledges(knowledges);
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,"1");
        refreshAnswerCache(codePre);
    }
    
    @Override
    public void editTkSimpleQuestion(JzbQuestion question) {
        String tkCode= question.getqCode();
        if(isCodeNeedChange(question)){
            tkCode = getTkCode(question);
            question.setqCode(tkCode);
        }
        qDao.updateByPrimaryKeySelective(question);
        qDao.deleteQuestionKnowledges(question.getId());
        setSimpleKnowledge(question);
    }
    
    
    @Override
    @Transactional
    public void editMultiQuestion(JzbQuestion question) {
        String qInfo = question.getQuestionInfos();
        qInfo = qInfo.replace("@#@", "\"");
        List<JzbQuestion> questions = JSON.parseArray(qInfo, JzbQuestion.class);
        for(JzbQuestion q:questions){
            q.setqMainDesc(question.getqMainDesc());
            q.setqDifficulty(question.getqDifficulty());
            q.setqMonth(question.getqMonth());
            q.setqRemark(question.getqRemark());
            q.setqScore(question.getqScore());
            qDao.updateByPrimaryKeySelective(q);
            List<JzbQuestionAnswer> answers = q.getAnswers();
            for(JzbQuestionAnswer a:answers){
                a.setQuestionId(q.getId());
                qDao.updateAnswerByQuestionIdAndXh(a);
            }
        }
        String tkCode = question.getqCode();
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,"2");
        refreshAnswerCache(codePre);
    }
    
    @Override
    @Transactional
    public void editTkMultiQuestion(JzbQuestion question) {
        String qInfo = question.getQuestionInfos();
        qInfo = qInfo.replace("@#@", "\"");
        List<JzbQuestion> questions = JSON.parseArray(qInfo, JzbQuestion.class);
        for(JzbQuestion q:questions){
            q.setqMainDesc(question.getqMainDesc());
            q.setqDifficulty(question.getqDifficulty());
            q.setqMonth(question.getqMonth());
            q.setqRemark(question.getqRemark());
            q.setqScore(question.getqScore());
            q.setTkAnswer(question.getTkAnswer());
            qDao.updateByPrimaryKeySelective(q);
        }
        String tkCode = question.getqCode();
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,"2");
        refreshAnswerCache(codePre);
    }
    
    @Override
    public JzbQuestion getSimpleQusetion(String id) {
        JzbQuestion question = qDao.selectQuestionById(id);
        List<JzbQuestionAnswer> answers = qDao.getQuesAnswerList(Integer.valueOf(id));
        question.setAnswers(answers);
        return question;
    }
    
    @Override
    public void editStatus(JzbQuestion question) {
        String tkCode = question.getqCode();
        String ids = question.getIds();
        String qType = "1";
        if(ids.indexOf(",")>0){
            qType = "2";
            String[] idArr = ids.split(",");
            for(String id:idArr){
                JzbQuestion q = new JzbQuestion();
                q.setId(Integer.valueOf(id));
                q.setqState(question.getqState());
                qDao.updateStatus(q);
            }
        }else{
            question.setId(Integer.valueOf(ids));
            qDao.updateStatus(question);
        }
        String codePre = tkCode.substring(0,tkCode.length()-4);
        refreshQuestionCache(codePre,qType);
        refreshAnswerCache(codePre);
    }
    
    @Override
    public void delQuestion(JzbQuestion question) {
        String tkCode = question.getqCode();
        String codePre = tkCode.substring(0,tkCode.length()-4);
        
        /**
         * 刷新试卷配置的状态
         */
        refreshMonthConfigStatus(codePre,tkCode);
        
        String ids = question.getIds();
        if(ids.indexOf(",")>0){
            String[] idArr = ids.split(",");
            for(String id:idArr){
                Integer cuid = Integer.valueOf(id);
                qDao.deleteByPrimaryKey(cuid);
                qDao.deleteQuestionAnswers(cuid);
                qDao.deleteQuestionKnowledges(cuid);
            }
        }else{
            Integer cuid = Integer.valueOf(ids);
            qDao.deleteByPrimaryKey(cuid);
            qDao.deleteQuestionAnswers(cuid);
            qDao.deleteQuestionKnowledges(cuid);
        }
        refreshQuestionCache(codePre,question.getqType());
        refreshAnswerCache(codePre);
    }
    
    
    @Override
    public JzbQuestion getMultipleQusetion(String id) {
        JzbQuestion question = qDao.selectMultiQuestionById(id);
        List<JzbQuestion> questions = new ArrayList<JzbQuestion>();
        String[] idArr = id.split(",");
        for(String s:idArr){
            if(StringUtils.isNotEmpty(s)){
                JzbQuestion srcQuest = qDao.selectByPrimaryKey(Integer.valueOf(s));
                JzbQuestion q = new JzbQuestion();
                q.setId(Integer.valueOf(s));
                q.setqContent(srcQuest.getqContent());
                if(srcQuest.getIsTk().intValue()==1){
                    List<JzbQuestionAnswer> answers = qDao.getQuesAnswerList(Integer.valueOf(s));
                    q.setAnswers(answers);
                }else{
                    q.setTkAnswer(srcQuest.getTkAnswer());
                }
                questions.add(q);
            }
        }
        question.setQuestions(questions);
        return question;
    }
    
    private void addMultiQuestion(JzbQuestion question){
        String qInfo = question.getQuestionInfos();
        qInfo = qInfo.replace("@#@", "\"");
        List<JzbQuestionKnowledge> knowledges = new ArrayList<JzbQuestionKnowledge>();
        List<JzbQuestion> questions = JSON.parseArray(qInfo, JzbQuestion.class);
        for(JzbQuestion q:questions){
            question.setqContent(q.getqContent());
            qDao.insert(question);
            
            JzbQuestionKnowledge k = new JzbQuestionKnowledge();
            k.setQuestionId(question.getId());
            knowledges.add(k);
            List<JzbQuestionAnswer> answers = q.getAnswers();
            for(JzbQuestionAnswer a:answers){
                a.setQuestionId(question.getId());
            }
            qDao.insertQuestionAnswers(answers);
        }
        List<JzbQuestionKnowledge> tmpknowledges = new ArrayList<JzbQuestionKnowledge>();;
        for(JzbQuestionKnowledge j:knowledges){
            String knowledge = question.getqKnowledge(); 
            JzbQuestionKnowledge k1 = new JzbQuestionKnowledge();
            k1.setKnowledgeId(Integer.valueOf(knowledge));
            k1.setQuestionId(j.getQuestionId());
            tmpknowledges.add(k1);
        }
        qDao.insertQuestionKnowledges(tmpknowledges);
    }
    
    @Override
    public List<AreaInfo> getAreaByGrade(Integer gradeId) {
        return qDao.getAreaByGrade(gradeId);
    }
    
    @Override
    public String getQuestionPreCode(Integer mainConfigId) {
        jzbPaperMainConfig mainConfig = jzbDao.selectByPrimaryKey(mainConfigId);
        String kmCode = autoGenericCode(mainConfig.getSubject(),3);
        String gradeCode = autoGenericCode(mainConfig.getGrade(),3);
        String classTypeCode = autoGenericCode(mainConfig.getClassType(),3);
        return "TK"+kmCode+gradeCode+classTypeCode;
    }
    
    private String getTkCode(JzbQuestion question){
        String codePre = "TK";
        String kmCode = autoGenericCode(question.getqSubject(),3);
        String gradeCode = autoGenericCode(question.getqGrade(),3);
        String classTypeCode = autoGenericCode(question.getqClasstype(),3);
        codePre = codePre+kmCode+gradeCode+classTypeCode;
        String currentCode = qDao.getMaxCodeByCodePre(codePre);
        if(StringUtils.isEmpty(currentCode)){
            currentCode = codePre +"0001";
        }
        String xh = currentCode.substring(currentCode.length()-4);
        String currentXh = (Integer.valueOf(1+""+xh)+1)+"";
        currentXh = currentXh.substring(currentXh.length()-4);
        return codePre+""+currentXh;
    }
    
    private boolean isCodeNeedChange(JzbQuestion question){
        String currentCode = question.getqCode();
        String codePre = "TK";
        String kmCode = autoGenericCode(question.getqSubject(),3);
        String gradeCode = autoGenericCode(question.getqGrade(),3);
        String classTypeCode = autoGenericCode(question.getqClasstype(),3);
        codePre = codePre+kmCode+gradeCode+classTypeCode;
        String currentCodePre = currentCode.substring(0, currentCode.length()-4);
        return !codePre.equals(currentCodePre);
    }
    
    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    private String autoGenericCode(String code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", Integer.parseInt(code));
        return result;
    }
    
    
    @Override
    public List<CacheQuestion> getCacheQuestion(String preCode,String qType) {
        if(redisTemplateUtil.isExist(preCode+qType)){
            return (List<CacheQuestion>) redisTemplateUtil.get(preCode+qType);
        }else{
            return setCacheQuestion(preCode,qType);
        }
    }
    
    @Override
    public List<JzbCacheAnswer> getCacheAnswer(String preCode) {
        if(redisTemplateUtil.isExist("answer-"+preCode)){
            return (List<JzbCacheAnswer>) redisTemplateUtil.get("answer-"+preCode);
        }else{
            return setCacheAnswer(preCode);
        }
    }
    
    @Override
    public int insertPaper(JzbQuestionPaper paper) {
        return paperDao.insertSelective(paper);
    }
    
    @Override
    public int updatePaper(JzbQuestionPaper paper) {
        return paperDao.updateByPrimaryKeySelective(paper);
    }
    
    @Override
    public JzbQuestionPaper selectLastPaperByOpenId(String openId) {
        return paperDao.selectLastPaperByOpenId(openId);
    }
    
    @Override
    public int insertPaperQuestions(List<JzbPaperQuestion> questions) {
        return paperQuestionDao.insertPaperQuestions(questions);
    }
    
    @Override
    public int setAnswerId(Integer paperId) {
        return paperQuestionDao.setAnswerId(paperId);
    }
    
    @Override
    public JzbQuestion getPageQuestion(JzbPaperQuestion pq) {
        List<Integer> questionIds = paperQuestionDao.getPageQuestionIds(pq);
        if(questionIds.size()==1){
            Integer qId = questionIds.get(0).intValue();
            JzbQuestion q= qDao.selectByPrimaryKey(qId);
            if(q.getIsTk().intValue()==1){
                q.setAnswers(qDao.getQuesAnswerList(qId));
            }
            return q;
        }else{
            JzbQuestion question = new JzbQuestion();
            List<JzbQuestion> questions = new ArrayList<JzbQuestion>();
            for(Integer id:questionIds){
                JzbQuestion srcQuest = qDao.selectByPrimaryKey(id);
                JzbQuestion q = new JzbQuestion();
                q.setId(id);
                q.setqCode(srcQuest.getqCode());
                q.setqContent(srcQuest.getqContent());
                q.setqMainDesc(srcQuest.getqMainDesc());
                q.setIsTk(srcQuest.getIsTk());
                if(srcQuest.getIsTk().intValue()==1){
                    List<JzbQuestionAnswer> answers = qDao.getQuesAnswerList(id);
                    q.setAnswers(answers);
                }else{
                    q.setTkAnswer(srcQuest.getTkAnswer());
                }
                questions.add(q);
            }
            question.setQuestions(questions);
            question.setqMainDesc(questions.get(0).getqMainDesc());
            question.setqType("2");
            question.setqCode(questions.get(0).getqCode());
            return question;
        }
        
    }
    
    @Override
    public Integer getExistDayTimesByOpenId(JzbQuestionPaper paper){
        return paperDao.getExistDayTimesByOpenId(paper);
    }
    
    @Override
    public Integer getExistMonthTotalTimesByOpenId(JzbQuestionPaper paper){
        return paperDao.getExistMonthTotalTimesByOpenId(paper);
    }
    @Override
    public Integer getExistTotalTimesByOpenId(JzbQuestionPaper paper){
        return paperDao.getExistTotalTimesByOpenId(paper);
    }
    
    @Override
    public List<JzbQuestionPaper> selectByOpenId(String openId) {
        return paperDao.selectByOpenId(openId);
    }
    
    @Override
    public JzbQuestionPaper selectPaperByPaperId(Integer paperId) {
        return paperDao.selectByPrimaryKey(paperId);
    }
    
    @Override
    public Map<String, Object> sendBindMsg(String telephone) {
        Map<String,Object> result = new HashMap<String,Object>();
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setSendTel(telephone);
        smsRecord.setCompany("25");
        smsRecord.setSmsType("1");
        String code = "";
        Random rd = new Random();
        for(int i = 0; i < 6; i++){
            code += rd.nextInt(9);
        }
        smsRecord.setSendComment("验证：("+code+")SMARTWORK尖子班测试验证码");
        Integer sendResult = smsTempService.sendMessage(smsRecord);
        if(sendResult.intValue()!=1){
            result.put("flag", false);
            result.put("message", "验证码发送失败");
            return result;
        }
        result.put("flag", true);
        result.put("code",code);
        result.put("message", "验证码已发送");
        return result;
    }
    
    private List<CacheQuestion> setCacheQuestion(String preCode,String qType){
        List<CacheQuestion> list = null;
        if("1".equals(qType)){
            list = qDao.selectSimpleQuestionsByPrecode(preCode);
        }else{
            list = qDao.selectMultiQuestionsByPrecode(preCode);
        }
        redisTemplateUtil.setList(preCode+qType, list, 300*60L);
        return list;
    }
    
    private List<JzbCacheAnswer> setCacheAnswer(String preCode){
        List<JzbCacheAnswer> answers = qDao.selectCacheAnswerByPrecode(preCode);
        redisTemplateUtil.setList("answer-"+preCode,answers, 300*60L);
        return answers;
    }
    
    private void refreshAnswerCache(String preCode){
        if(redisTemplateUtil.isExist("answer-"+preCode)){
            redisTemplateUtil.del("answer-"+preCode);
        }
        setCacheAnswer(preCode);
    }
    
    private void refreshQuestionCache(String preCode,String qType){
        if(redisTemplateUtil.isExist(preCode+qType)){
            redisTemplateUtil.del(preCode+qType);
        }
        setCacheQuestion(preCode,qType);
    }
    
    @Override
    public JzbQuestionAnswer getAnswerById(Integer answerId) {
        return answerDao.selectByPrimaryKey(answerId);
    }
    
    @Override
    public int delImg(Integer answerId) {
        return answerDao.delImg(answerId);
    }
    
    @Override
    public List<DicData> getQuestionClassType() {
        return qDao.getQuestionClassType();
    }
    
    @Override
    public List<DicData> getQuestionSubjectByGrade(String grade) {
        return qDao.getQuestionSubjectByGrade(grade);
    }
    
    @Override
    public void insertBmRecord(JzbStudentBmRecord bmRecord) {
        jzbStudentDao.insertBmRecord(bmRecord);
    }
    
    
    @Override
    @Async
    public void refreshMonthConfigStatus(String preCode, String qCode) {
        JzbQuestion question = qDao.selectQuestionByCode(qCode);
        String knowledge = question.getqKnowledge();
        List<JzbPaperMonthConfig> list= jzbPaperMonthConfigDao.selectByPreCodeAndTkMonth(preCode, knowledge);
        String tkMonth = question.getqMonth();
        List<JzbPaperMonthConfig> result = new ArrayList<JzbPaperMonthConfig>();
        for(JzbPaperMonthConfig c : list){
            if(isMonthPipei(c.getMonth(),tkMonth)){
                result.add(c);
            }
        }
        for(JzbPaperMonthConfig pc:result){
            checkMonthConfigStatus(pc.getMainConfigId(), pc.getId());
        }
    }
    
    private boolean isMonthPipei(String month,String tkMonth){
        String[] monthArr = month.split(",");
        String[] tkMonthArr = tkMonth.split(",");
        for(String a:monthArr){
            for(String b:tkMonthArr){
                if(a.equals(b)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Map<String, Object> checkMonthConfigStatus(Integer mainConfigId, Integer monthConfigId) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<JzbPaperConfigDetail> detailConfigs = detailDao.selectByMonth(monthConfigId);
        JzbPaperMonthConfig monthConfig = monConfigDao.selectByPrimaryKey(monthConfigId);
        String preCode = getQuestionPreCode(mainConfigId);
        String tkMonth =monthConfig.getTkMonth();
        String month = TimeUtil.getCurrentMonth();
        if("10".equals(month)){
            month ="A";
        }
        if("11".equals(month)){
            month ="B";
        }
        if("12".equals(month)){
            month ="C";
        }
        
        List<JzbPaperQuestion> paperQuestions = new ArrayList<JzbPaperQuestion>();
        boolean isOk = true;
        for(JzbPaperConfigDetail detail:detailConfigs){
            detail.setMonth(month);
            Integer zsdId = detail.getKnowledgeId();
            jzbKnowledgePoint point = knowledgeDao.selectByPrimaryKey(zsdId);
            String qType ="1";
            if(point.getTitleNum()>1){
                qType ="2";
            }
            List<CacheQuestion> questions = getCacheQuestion(preCode,qType);
            int num = point.getTitleNum();
            List<JzbPaperQuestion> needQs = getQuestions(questions,detail,paperQuestions,num,tkMonth);
            if(needQs==null){
                isOk = false;
                result.put("flag", false); 
                result.put("message", "对应知识点的题目不够，请重新配置");
                break;
            }
        }
        /**
         * 更新状态
         */
        JzbPaperMonthConfig config = new JzbPaperMonthConfig();
        config.setId(monthConfigId);
        if(isOk){
            config.setStatus(2);
        }else{
            config.setStatus(1);
        }
        monConfigDao.updateByPrimaryKeySelective(config);
        result.put("flag", true); 
        return result;
    }
    
    private List<JzbPaperQuestion> getQuestions(List<CacheQuestion> questions,JzbPaperConfigDetail detail,
            List<JzbPaperQuestion> paperQuestions,int num,String tkMonth){
        List<JzbPaperQuestion> result = new ArrayList<JzbPaperQuestion>();
        for(CacheQuestion q:questions){
            if(q.getDifficulty().intValue()==detail.getDifficulty().intValue()
                    && q.getKnowledge().equals(detail.getKnowledgeId()+"")
                    && isMonthPipei(q.getMonths(),tkMonth) 
                    && !isQusetionExist(paperQuestions,q.getqCode())
                    ){
                if(result.size()>0 && isQusetionExist(result,q.getqCode())){
                    continue;
                }
                JzbPaperQuestion pq = new JzbPaperQuestion();
                pq.setqCode(q.getqCode());
                if(num==1){
                    pq.setQuestionId(q.getQuestionId());
                }else{
                    pq.setIds(q.getQuestionIds());
                }
                result.add(pq);
                
            }
        }
        
        Integer needNum = detail.getNum()/num;
        if(result.size()<needNum){
            return null;
        }
        
        Collections.shuffle(result);
        return result.subList(0, needNum);
    }
    

    private boolean isQusetionExist(List<JzbPaperQuestion> paperQuestions,String qCode){
        for(JzbPaperQuestion q:paperQuestions){
            if(q.getqCode().equals(qCode)){
                return true;
            }
        }
        return false;
    }
    
    private void setSimpleKnowledge(JzbQuestion question){
        Integer questionId= question.getId();
        List<JzbQuestionKnowledge> knowledges = new ArrayList<JzbQuestionKnowledge>();
        if("1".equals(question.getqType())){
            if("2".equals(question.getZsdType())){
                jzbKnowledgePoint p = new jzbKnowledgePoint();
                p.setClassType(question.getqClasstype());
                p.setGrade(question.getqGrade());
                p.setDept(question.getqDept());
                p.setCompany(Common.getMyUser().getCompanyId());
                p.setTitleNum(1);
                p.setSubject(question.getqSubject());
                p.setKnowledge(question.getqKnowledge());
                pointDao.insert(p);
                JzbQuestionKnowledge k = new JzbQuestionKnowledge();
                k.setKnowledgeId(p.getId());
                k.setQuestionId(questionId);
                knowledges.add(k);
            }else{
                String knowledge = question.getqKnowledge();
                JzbQuestionKnowledge k1 = new JzbQuestionKnowledge();
                k1.setKnowledgeId(Integer.valueOf(knowledge));
                k1.setQuestionId(questionId);
                knowledges.add(k1);
            }
            qDao.insertQuestionKnowledges(knowledges);
        }
    }

    @Override
    public List<JzbQuestion> getquestionArr(String questionId,JzbQuestion jq) {
        List<JzbQuestion> q=null;
        if(questionId!=null){
           String[] ids=questionId.split(",");
           Map<String, Object> map=new HashMap<>();
           map.put("ids", ids);
           map.put("jq", jq);
          q=  qDao.getquestionArr(map);
        }
        return q;
    }

    @Override
    public JzbQuestion selectPrimaryKey(String id) {
        // TODO Auto-generated method stub
        JzbQuestion q=qDao.selectPrimaryKey(Integer.valueOf(id));
        String tkAnswer = q.getTkAnswer();
        List<String> answer = new ArrayList<String>();
        if (tkAnswer != null && tkAnswer.indexOf("@@@") > -1) {
            String[] answerArr = tkAnswer.split("@@@");
            for (String t : answerArr) {
                if (StringUtils.isNotEmpty(t)) {
                    answer.add(t);
                }
            }
        }
        else {
            answer.add(tkAnswer);
        }
        q.setTkAnswerList(answer);
        q.setAnswers(qDao.getQuesAnswerList(Integer.valueOf(id)));
        return q;
    }
}
