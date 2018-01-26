package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.jzbTest.dao.JzbPaperConfigDetailDao;
import com.human.jzbTest.dao.JzbPaperMonthConfigDao;
import com.human.jzbTest.dao.JzbPaperMonthLevelDao;
import com.human.jzbTest.dao.jzbKnowledgePointDao;
import com.human.jzbTest.entity.CacheQuestion;
import com.human.jzbTest.entity.JzbPaperConfigDetail;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbPaperMonthLevel;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.utils.TimeUtil;

@Service
public class JzbPaperMonthConfigServiceImpl implements JzbPaperMonthConfigService{
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
    
    @Autowired
    private JzbQuestionService questionService;
    
    @Resource 
    private jzbKnowledgePointDao jzbDao;
    
    @Override
    public JzbPaperMonthConfig selectByPrimaryKey(Integer id) {
        return jzb.selectByPrimaryKey(id);
    }
   
    @Override
    public boolean insert(JzbPaperMonthConfig jzbpapermonthconfig,boolean flag) {
        int i=jzb.insert(jzbpapermonthconfig);
        if(i==1){
            flag=false;
        }
        return flag;
    }

    @Override
    public List<JzbPaperMonthConfig> selectByMainId(String id) {
        return jzb.selectByMainId(id);
    }

    @Override
    public JzbPaperMonthConfig selectByMonthAndId(String month, int mainConfigId) {
        Map<String, Object> map=new HashMap<>();
        map.put("month", month);
        map.put("id", mainConfigId);
        return jzb.selectByMonthAndId(map);
    }

    @Override
    public int delete(JzbPaperMonthConfig jzbpapermonthconfig) {
        // TODO Auto-generated method stub
        return jzb.delete(jzbpapermonthconfig);
    }
    
    @Override
    public JzbPaperMonthConfig getPaperMonthConfig(JzbPaperMonthConfig jpmcf) {
        return jzb.getPaperMonthConfig(jpmcf);
    }

    @Override
    public Map<String, Object> selectExamName(JzbPaperMonthConfig jzbpapermonthconfig) {
        // TODO Auto-generated method stub
        return jzb.selectExamName(jzbpapermonthconfig);
    }
    
    @Override
    public Map<String, Object> checkMonthConfigStatus(Integer mainConfigId, Integer monthConfigId) {
        Map<String,Object> result = new HashMap<String,Object>();
        List<JzbPaperConfigDetail> detailConfigs = detailDao.selectByMonth(monthConfigId);
        JzbPaperMonthConfig monthConfig = monConfigDao.selectByPrimaryKey(monthConfigId);
        String preCode = questionService.getQuestionPreCode(mainConfigId);
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
            jzbKnowledgePoint point = jzbDao.selectByPrimaryKey(zsdId);
            String qType ="1";
            if(point.getTitleNum()>1){
                qType ="2";
            }
            List<CacheQuestion> questions = questionService.getCacheQuestion(preCode,qType);
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

    @Override
    public int updateStatus(int monthConfigId) {
        Map<String, Object> map=new HashMap<>();
        map.put("monthConfigId", monthConfigId);
        map.put("status",2);
        // TODO Auto-generated method stub
        return jzb.updateStatus(map);
    }

    @Override
    public int insertMonthLevel(JzbPaperMonthLevel le) {
        // TODO Auto-generated method stub
        return leDao.insertSelective(le);
    }

    @Override
    public List<JzbPaperMonthLevel> getMonthLevel( int monthId) {
        // TODO Auto-generated method stub
        return moDao.getMonthLevel(monthId);
    }

    @Override
    public int deleteMonthLevel(Integer id) {
        // TODO Auto-generated method stub
        return leDao.deleteMonthLevel(id);
    }
    @Override
    public List<JzbPaperMonthLevel> getMonthUseLevel( int monthId) {
        // TODO Auto-generated method stub
        return moDao.getMonthUseLevel(monthId);
    }

    @Override
    public boolean updateByprimary(JzbPaperMonthConfig jzbpapermonthconfig, boolean flag) {
        int i=jzb.updateByPrimaryKeyS(jzbpapermonthconfig);
        if(i==1){
            flag=false;
        }
        return flag;
    }

    @Override
    public int deleteMonthLevelByMonthId(JzbPaperMonthConfig jzbpapermonthconfig) {
        // TODO Auto-generated method stub
        return leDao.deleteMonthLevelByMonthId(jzbpapermonthconfig.getId());
    }
}
