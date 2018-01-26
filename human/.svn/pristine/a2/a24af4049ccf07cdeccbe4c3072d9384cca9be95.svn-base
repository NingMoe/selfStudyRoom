package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.dao.JzbQuestionDao;
import com.human.jzbTest.dao.jzbKnowledgePointDao;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class jzbKnowledgePointServiceImpl implements jzbKnowledgePointService {
    
    @Resource jzbKnowledgePointDao jzbDao;
    
    @Resource JzbQuestionDao dDao;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private JzbDeptService deptService;
    @Autowired
    private JzbGradeService gradeService;
    
    @Autowired
    private JzbPaperMonthConfigService jzbpaperMonthconfigService;
    
    @Override
    public PageView query(PageView pageView,jzbKnowledgePoint jzb) {
        Map< String , Object> map =new HashMap<>();
        map.put("paging", pageView);
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        jzb.setDept(String.valueOf(deptId));
        map.put("t", jzb);
        List<jzbKnowledgePoint> list = jzbDao.query(map);
        List<jzbKnowledgePoint> list2=new ArrayList<jzbKnowledgePoint>();
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        for (jzbKnowledgePoint jzbKnowledgePoint : list) {
            String subject=jzbKnowledgePoint.getSubject();
            String classType=jzbKnowledgePoint.getClassType();
            String grade=jzbKnowledgePoint.getGrade();
            for (DicData dicData : subjects) {
                if(subject.equals(dicData.getDataValue())){
                    jzbKnowledgePoint.setSubject(dicData.getName());
                }
            }
            for (DicData dicData : classTypes) {
                if(classType.equals(dicData.getDataValue())){
                    jzbKnowledgePoint.setClassType(dicData.getName());
                }
            }
            for (JzbGrade jzbGrade : grades) {
                if(grade.equals(String.valueOf(jzbGrade.getId()))){
                    jzbKnowledgePoint.setGrade(String.valueOf(jzbGrade.getGradeName()));
                }
            }
            jzbKnowledgePoint.setDept(deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
            list2.add(jzbKnowledgePoint);
        }
        pageView.setRecords(list2);
        return pageView;
    }

    @Override
    public int delete(int id) {
        // TODO Auto-generated method stub
        return jzbDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(jzbKnowledgePoint jzb) {
        // TODO Auto-generated method stub
        return jzbDao.insertSelective(jzb);
    }

    @Override
    public jzbKnowledgePoint selectByPrimaryKey(int id) {
        // TODO Auto-generated method stub
        return jzbDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(jzbKnowledgePoint jzb) {
        // TODO Auto-generated method stub
        return jzbDao.updateByPrimaryKey(jzb);
    }

    @Override
    public Map<String, Object> dele(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            jzbDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public List<jzbKnowledgePoint> selectKnowledge(int quesId) {
        // TODO Auto-generated method stub
        return jzbDao.selectKnowledge(quesId);
    }
    
    @Override
    public List<jzbKnowledgePoint> queryByCondition(jzbKnowledgePoint point) {
        // TODO Auto-generated method stub
        return jzbDao.queryByCondition(point);
    }

    @Override
    public List<jzbKnowledgePoint> selectCount(String questionId) {
        String[] questionIds=questionId.split(",");
        Map<String,Object> map=new HashMap<>();
        map.put("questionIds", questionIds);
        return jzbDao.selectCount(map);
    }

    @Override
    public List<Map<String, Object>> queryByKnow(int knowledgeId ,String questionId,String month,String mainConfigId) {
        Map<String, Object> map=new HashMap<>();
        String[] questionIds=questionId.split(",");
        JzbPaperMonthConfig  jpmcf=new JzbPaperMonthConfig();
        map.put("knowledgeId", knowledgeId);
        map.put("questionIds", questionIds);
        jpmcf.setMainConfigId(Integer.valueOf(mainConfigId));
        jpmcf.setMonth(month);
        jzbpaperMonthconfigService.getPaperMonthConfig(jpmcf);
        map.put("monthConfigId", (jzbpaperMonthconfigService.getPaperMonthConfig(jpmcf)).getId());
        List<Map<String, Object>> List= jzbDao.queryByKnow(map);
        for (Map<String, Object> map2 : List) {
          jzbKnowledgePoint jzb=  jzbDao.selectByPrimaryKey(knowledgeId);
          map2.put("titleNum", jzb.getTitleNum());
        }
        return List;
    }
    @Override
    public List<Map<String, Object>> queryByKnowledge(int knowledgeId ,String questionId) {
        Map<String, Object> map=new HashMap<>();
        map.put("knowledgeId", knowledgeId);
        String[] questionIds=questionId.split(",");
        map.put("questionIds", questionIds);
        List<Map<String, Object>> List= jzbDao.queryByKnowledge(map);
        for (Map<String, Object> map2 : List) {
          jzbKnowledgePoint jzb=  jzbDao.selectByPrimaryKey(knowledgeId);
          map2.put("titleNum", jzb.getTitleNum());
        }
        return List;
    }

    @Override
    public Integer selectMaxConfigNum(int knowledgeId) {
        Map<String, Object> map=new HashMap<>();
        map.put("knowledgeId", knowledgeId);
        // TODO Auto-generated method stub
        return jzbDao.selectMaxConfigNum(map);
    }

    @Override
    public List<jzbKnowledgePoint> getKnowledgeNameAndTitleNum(JzbQuestion jq) {
        // TODO Auto-generated method stub
        return jzbDao.getKnowledgeNameAndTitleNum(jq);
    }

    @Override
    public List<JzbQuestion> getQuestions(JzbQuestion jq) {
        // TODO Auto-generated method stub
        return jzbDao.getQuestions(jq);
    }

    @Override
    public List<JzbQuestion> getQuestionByCode(JzbQuestion jq) {
        // TODO Auto-generated method stub
        return jzbDao.getQuestionByCode(jq);
    }

    @Override
    public List<JzbQuestionAnswer> getQuesAnswerList(int id) {
        // TODO Auto-generated method stub
        return dDao.getQuesAnswerList(id);
    }

    @Override
    public int getTitleNum(JzbQuestion jq) {
        // TODO Auto-generated method stub
        return jzbDao.getTitleNum(jq);
    }

    @Override
    public List<JzbQuestion> getQuestionsForPhone(JzbQuestion jq) {
        // TODO Auto-generated method stub
        return jzbDao.getQuestionsForPhone(jq);
    }

    @Override
    public String getIds(String qCode) {
        // TODO Auto-generated method stub
        return jzbDao.getIds(qCode);
    }

}
