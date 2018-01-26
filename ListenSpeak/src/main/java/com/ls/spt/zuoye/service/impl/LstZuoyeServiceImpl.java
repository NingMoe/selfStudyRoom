package com.ls.spt.zuoye.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClass.dao.LstClassDao;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.question.dao.LstQuestionDao;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.utils.Common;
import com.ls.spt.zuoye.dao.LstStudentZuoyeDao;
import com.ls.spt.zuoye.dao.LstZuoyeDao;
import com.ls.spt.zuoye.dao.LstZuoyeStudentAnswerDao;
import com.ls.spt.zuoye.entity.LstClassZuoyeDto;
import com.ls.spt.zuoye.entity.LstZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeClass;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;
import com.ls.spt.zuoye.service.LstZuoyeService;

@Service
public class LstZuoyeServiceImpl implements LstZuoyeService{
    @Autowired
    private LstZuoyeDao zyDao;
    
    @Autowired
    private LstQuestionDao questionDao;
    
    @Autowired
    LstClassDao lcDao;
    
    @Autowired
    LstStudentZuoyeDao studentZuoyeDao;
    
    @Autowired
    LstZuoyeStudentAnswerDao answerDao;
    
    @Override
    public PageView query(PageView pageView, LstZuoye zuoye) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Integer userId =  Common.getMyUser().getUserid().intValue();
        zuoye.setCreateUser(userId);
        map.put("paging", pageView);
        map.put("t", zuoye);
        List<LstZuoye> list = zyDao.query(map);
        pageView.setData(list);
        return pageView;
    }
    
    @Override
    public LstZuoye selectZuoyeById(Integer id) {
        return zyDao.selectZuoyeById(id);
    }
    
    @Override
    @Transactional
    public void insertLstZuoye(LstZuoye zuoye) {
        zuoye.setStatus(1);
        zuoye.setCreateTime(new Date());
        zyDao.insert(zuoye);
        
        String classStr = zuoye.getClassStr();
        Integer zuoyeId = zuoye.getId();
        List<LstZuoyeClass> list = new ArrayList<LstZuoyeClass>();
        if(classStr.indexOf(",")>-1){
            String[] classaArr = classStr.split(",");
            for(String classCode:classaArr){
                LstZuoyeClass c = new LstZuoyeClass();
                c.setClassCode(classCode);
                c.setZuoyeId(zuoyeId);
                list.add(c);
            }
        }else{
            LstZuoyeClass c = new LstZuoyeClass();
            c.setClassCode(classStr);
            c.setZuoyeId(zuoyeId);
            list.add(c);
        }
        zyDao.insertZuoyeClassList(list);
    }
    
    @Override
    public void deleteZuoye(Integer id) {
        zyDao.delZuoyeQuestionList(id);
        zyDao.delZuoyeClassList(id);
        zyDao.deleteByPrimaryKey(id);
    }
    
    @Override
    @Transactional
    public void editLstZuoye(LstZuoye zuoye) {
        Integer zuoyeId = zuoye.getId();
        zyDao.updateByPrimaryKeySelective(zuoye);
        zyDao.delZuoyeClassList(zuoyeId);
        String classStr = zuoye.getClassStr();
        
        List<LstZuoyeClass> list = new ArrayList<LstZuoyeClass>();
        if(classStr.indexOf(",")>-1){
            String[] classaArr = classStr.split(",");
            for(String classCode:classaArr){
                LstZuoyeClass c = new LstZuoyeClass();
                c.setClassCode(classCode);
                c.setZuoyeId(zuoyeId);
                list.add(c);
            }
        }else{
            LstZuoyeClass c = new LstZuoyeClass();
            c.setClassCode(classStr);
            c.setZuoyeId(zuoyeId);
            list.add(c);
        }
        zyDao.insertZuoyeClassList(list);
    }
    
    @Override
    public int updateByPrimaryKey(LstZuoye zuoye) {
        return zyDao.updateByPrimaryKey(zuoye);
    }
    
    @Override
    public int updateByPrimaryKeySelective(LstZuoye zuoye) {
        return zyDao.updateByPrimaryKeySelective(zuoye);
    }
    
    @Override
    public List<LstClass> getClassList(Integer teacher) {
        LstClass c = new LstClass();
        c.setStatus("1");
        c.setTeacher(teacher);
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("t", c);
        return lcDao.query(map);
    }
    
    @Override
    public List<LstQuestion> getZuoyeQuestionList(Integer zuoyeId) {
        return questionDao.getZuoyeQuestionList(zuoyeId);
    }
    
    @Override
    public List<LstQuestion> getZuoyeQuestion(Integer zuoyeId) {
        return questionDao.getZuoyeQuestion(zuoyeId);
    }
    
    @Override
    public void delQuestion(LstQuestion question) {
        String ids = question.getIds();
        String[] idArr = ids.split(",");
        for(String idStr : idArr){
            question.setId(Integer.valueOf(idStr));
            zyDao.delZuoyeQuestion(question);
        }
    }
    
    @Override
    public int updateQuestionXh(LstQuestion question) {
        return zyDao.updateQuestionXh(question);
    }
    
    @Override
    public void addZuoyeQuestion(LstQuestion question) {
        Integer xh = zyDao.getZuoyeMaxXh(question.getZuoyeId());
        if(xh==null){
            xh = 1;
        }else{
            xh = xh +1;
        }
        question.setXh(xh+"");
        String ids = question.getIds();
        String[] idArr = ids.split(",");
        for(String idStr : idArr){
            question.setId(Integer.valueOf(idStr));
            zyDao.addZuoyeQuestion(question);
        }
    }
    
    @Override
    @Transactional
    public void releaseZuoye(Integer id) {
        LstZuoye lzy = new LstZuoye();
        lzy.setId(id);
        lzy.setStatus(2); 
        lzy.setReleaseTime(new Date());
        zyDao.initStudentZuoye(id);
        zyDao.initStudentZuoyeQuestion(id);
        zyDao.updateByPrimaryKeySelective(lzy);
    }
    
    @Override
    public List<LstClassZuoyeDto> getNoCompleteZuoye() {
        Integer teacher = Common.getMyUser().getUserid().intValue();
        return zyDao.getNoCompleteZuoye(teacher);
    }
    
    @Override
    public PageView getCompleteZuoyePage(PageView pageView) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Integer teacher =  Common.getMyUser().getUserid().intValue();
        map.put("paging", pageView);
        map.put("t", teacher);
        List<LstClassZuoyeDto> list = zyDao.getCompleteZuoye(map);
        for (LstClassZuoyeDto l : list) {
            if(l.getOverallDfl()!=""&&l.getOverallDfl()!=null){
                l.setOverallDfl(l.getOverallDfl()+"%");
            }
        }
        pageView.setData(list);
        return pageView;
    }
    
    @Override
    public PageView getStudentZuoyePage(PageView pageView, LstStudentZuoye studentZuoye) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", studentZuoye);
        List<LstStudentZuoye> list = studentZuoyeDao.getStudentZuoyePage(map);
        pageView.setData(list);
        return pageView;
    }
    
    
    @Override
    public LstQuestion getMultiStudentQuestion(LstZuoyeStudentAnswer answer,String code) {
        List<LstQuestion> questions = questionDao.selectMultiQuestionByCode(code);
        String topic = questions.get(0).getTopic();
        Integer topicTime = questions.get(0).getTopicTime();
        String zdmessage = questions.get(0).getZdmessage();
        LstQuestion question = new LstQuestion();
        for(LstQuestion q:questions){
            answer.setQuestion_id(q.getId());
            LstZuoyeStudentAnswer a = answerDao.selectByCondition(answer);
            q.setAnswer(a);
        }
        question.setTopic(topic);
        question.setTopicTime(topicTime);
        question.setQuestionList(questions);
        question.setZdmessage(zdmessage);
        return question;
    }
    
    @Override
    public LstQuestion getSimpleStudentQuestion(LstZuoyeStudentAnswer answer,String code) {
        LstQuestion q = questionDao.selectSimpleQuestionByCode(code);
        answer.setQuestion_id(q.getId());
        LstZuoyeStudentAnswer a = answerDao.selectByCondition(answer);
        q.setAnswer(a);
        return q;
    }
    
    @Override
    @Transactional
    public void pgSimpleZuoye(LstZuoyeStudentAnswer answer) {
        if(answer!=null){
            answerDao.updateByPrimaryKeySelective(answer);
            if("1".equals(answer.getIsEnd())){
                studentZuoyeDao.updateStudentZuoyeTeacherScore(answer);
                
            }
        }
    }
    
    @Override
    @Transactional
    public void pgMultiZuoye(List<LstZuoyeStudentAnswer> answers,String isEnd) {
        if(answers!=null && answers.size()>0){
            for(LstZuoyeStudentAnswer a:answers){
                answerDao.updateByPrimaryKeySelective(a);
            }
            if("1".equals(isEnd)){
                studentZuoyeDao.updateStudentZuoyeTeacherScore(answers.get(0));
            }
            
        }
    }

    @Override
    public int getTjNum(LstStudentZuoye lz,String zs) {
        Map<String, Object> map=new HashMap<>();
        map.put("t", lz);
        map.put("zs", zs);
        // TODO Auto-generated method stub
        return studentZuoyeDao.getTjNum(map);
    }
}
