package com.ls.spt.lstClassTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClassTest.dao.LstStuTestDao;
import com.ls.spt.lstClassTest.dao.LstTestStudentAnswerDao;
import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.lstClassTest.service.LstTestStudentAnswerService;
import com.ls.spt.question.dao.LstQuestionDao;
import com.ls.spt.question.entity.LstQuestion;

@Service
public class LstTestStudentAnswerServiceImpl implements LstTestStudentAnswerService {
    @Resource 
    LstTestStudentAnswerDao ltsaDao;
    @Autowired
    private LstQuestionDao questionDao;
    @Autowired 
    private LstStuTestDao lstDao; 
    
    
    @Override
    public List<LstTestStudentAnswer> getStudentTestAnswerInfo(Integer id) {
        // TODO Auto-generated method stub
        return ltsaDao.getStudentTestAnswerInfo(id);
    }

    @Override
    public int insert(LstTestStudentAnswer aList) {
        // TODO Auto-generated method stub
        return ltsaDao.insertSelective(aList);
    }

    @Override
    public PageView query(PageView pageView, LstTestStudentAnswer ltsa) {
        Map<String, Object> map=new HashMap<>();
        map.put("paging", pageView);
        map.put("t",ltsa);
        List<LstStudentTest> list=lstDao.query(map);
        pageView.setData(list);
        return pageView;
    }

    @Override
    public LstTestStudentAnswer getGradeInfo(LstTestStudentAnswer lsta) {
        
        return ltsaDao.getGradeInfo(lsta);
    }

    @Override
    public int insertGrade(LstTestStudentAnswer answer) {
        // TODO Auto-generated method stub
        return ltsaDao.insertGradeSelective(answer);
    }

    @Override
    public int updateStudentTestStatus(int studentId, int testId) {
        Map<String, Object> map=new HashMap<>();
        map.put("studentId", studentId);
        map.put("testId", testId);
        // TODO Auto-generated method stub
        return ltsaDao.updateStudentTestStatus(map);
    }

    @Override
    public List<LstTestStudentAnswer> getDescTopFive(LstTestStudentAnswer lsta) {
        return ltsaDao.getDescTopFive(lsta);
    }

    @Override
    public List<LstTestStudentAnswer> getDescBackwardFive(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getDescBackwardFive(lsta);
    }

    @Override
    public Map<String, Object> getScoreList(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getScoreList(lsta);
    }

    @Override
    public List<LstQuestion> getTestQuestion(int paperId) {
        // TODO Auto-generated method stub
        return questionDao.getTestQuestion(paperId);
    }

    @Override
    public LstQuestion getSimpleTestQuestion(LstTestStudentAnswer ltsa, String code) {
        LstQuestion q = questionDao.selectSimpleQuestionByCode(code);
        ltsa.setQuestionId(q.getId());
        LstTestStudentAnswer a = ltsaDao.selectByCondition(ltsa);
        q.setTestAnswer(a);
        return q;
    }

    @Override
    public LstQuestion getMultiStudentQuestion(LstTestStudentAnswer ltsa, String code) {
        List<LstQuestion> questions = questionDao.selectMultiQuestionByCode(code);
        String topic = questions.get(0).getTopic();
        Integer topicTime = questions.get(0).getTopicTime();
        String zdmessage = questions.get(0).getZdmessage();
        LstQuestion question = new LstQuestion();
        for(LstQuestion q:questions){
            ltsa.setQuestionId(q.getId());
            LstTestStudentAnswer a = ltsaDao.selectByCondition(ltsa);
            q.setTestAnswer(a);
        }
        question.setTopic(topic);
        question.setTopicTime(topicTime);
        question.setQuestionList(questions);
        question.setZdmessage(zdmessage);
        return question;
    }

    @Override
    public void pgMultiTest(List<LstTestStudentAnswer> list) {
        
       if(list!=null ||list.size()>0){
           for (LstTestStudentAnswer Answer : list) {
               ltsaDao.updateByPrimaryKeySelective(Answer);
               LstTestStudentAnswer lt=ltsaDao.getScore(Answer);
               lt.setTestId(Answer.getTestId());
               lt.setClassCode(Answer.getClassCode());
               lt.setStudentId(Answer.getStudentId());
               lt.setFlag(Answer.getFlag());
               ltsaDao.updateStudentTestTeacherScore(lt);
           }   
//           ltsaDao.updateStudentTestTeacherScore(list.get(0));
       }
        
    }

    @Override
    public void pgSimpleTest(LstTestStudentAnswer list) {
        if(list!=null){
            ltsaDao.updateByPrimaryKeySelective(list);
        }
        //计算test 所需要的得分率
        LstTestStudentAnswer lt=ltsaDao.getScore(list);
        lt.setTestId(list.getTestId());
        lt.setClassCode(list.getClassCode());
        lt.setStudentId(list.getStudentId());
        lt.setFlag(list.getFlag());
        ltsaDao.updateStudentTestTeacherScore(lt);
    }

    @Override
    public List<Map<String, Object>> getclassAvgScore(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getclassAvgScore(lsta);
    }

    @Override
    public List<Map<String, Object>> getTotalAvgScore(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getTotalAvgScore(lsta);
    }

    @Override
    public LstTestStudentAnswer selectPrimarykey(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.selectByPrimaryKey(lsta.getId());
    }

    @Override
    public LstTestStudentAnswer getOverallScore(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getOverallScore(lsta);
    }

    @Override
    public List<Map<String, Object>> getStuAvgScore(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.getStuAvgScore(lsta);
    }

    @Override
    public List<Map<String, Object>> selectAllQuesForStu(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return ltsaDao.selectAllQuesForStu(lsta);
    }
    
}
