package com.ls.spt.studentpc.student.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ls.spt.lstBasePaper.dao.LstPaperQuestionDao;
import com.ls.spt.question.dao.LstQuestionDao;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.entity.KsQuestion;
import com.ls.spt.studentpc.student.service.StudentTestService;
import com.ls.spt.studenttest.dao.LstStudentTestDao;
import com.ls.spt.studenttest.dao.LstTestStudentAnswerDao;
import com.ls.spt.studenttest.entity.LstPaperQuestion;
import com.ls.spt.studenttest.entity.LstStudentTest;
import com.ls.spt.utils.StudentPcConstants;

@Service
public class StudentTestServiceImpl implements StudentTestService {

    @Resource
    private LstStudentTestDao lstStudentTestDao;
    
    @Resource
    private LstPaperQuestionDao lstPaperQuestionDao;
   
    @Resource
    private LstTestStudentAnswerDao studentAnswerDao;
    
    @Resource
    LstQuestionDao questionDao;
    
    /**
     * 考试报告
     * @param request
     * @param response
     * @param test_id
     * @param class_code
     * @return
     */
    public Map<String, Object> getStudentTestInfo(HttpServletRequest request, HttpServletResponse response, Integer test_id, String class_code) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "请重新登录");
            return map;
        }
        
        if(test_id == null){
            map.put("flag", false);
            map.put("message", "考试ID为空");
            return map;
        }
        
        if(StringUtils.isEmpty(class_code)){
            map.put("flag", false);
            map.put("message", "班号为空");
            return map;
        }
        
        try {
            Map<String, Object> mapparams = new HashMap<String, Object>();
            mapparams.put("student_id", studentUser.getId());
            mapparams.put("test_id", test_id);
            mapparams.put("class_code", class_code);
            LstStudentTest lstStudentTest = lstStudentTestDao.selectByParams(mapparams);//考试基础信息
            LstStudentTest lstStudentTest1 = lstStudentTestDao.selectClassByParams(mapparams);//班级考试平均
            Integer count = lstStudentTestDao.selectTranscendCount(mapparams);//超过学生百分比
            Integer classCount = lstStudentTestDao.selectClassCountThanStudent(mapparams);//班级比该学生分数高的人数
            List<LstPaperQuestion> list = lstPaperQuestionDao.selectStudentQuestion(mapparams);//获取考试题目详情
            map.put("flag", true);
            map.put("message", "获取考试成绩概况成功");
            map.put("student_test", lstStudentTest);
            map.put("class_test", lstStudentTest1);
            map.put("transcend_count", count);
            map.put("than_count", classCount);
            map.put("qlist", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "异常："+e);
        }
        
        return map;
    }
    
    /**
     * 获取学生所有考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllTest(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 获取学生已完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndTest(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "please login again");
            return map;
        }
        
        if(pageNow == null || pageNow <= 0){
            map.put("flag", false);
            map.put("message", "pageNow must greater than zero");
            return map;
        }
        
        if(pageSize == null || pageSize <= 0){
            map.put("flag", false);
            map.put("message", "pageSize must greater than zero");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            
            Integer count = lstStudentTestDao.selectStudentEndTestCount(mapparam);
            count = count == null ? 0 : count;
            map.put("count", count);
            if(count > 0){
                mapparam.put("pageStart", (pageNow - 1) * pageSize);
                mapparam.put("pageSize", pageSize);
                List<LstStudentTest> list = lstStudentTestDao.selectStudentEndTest(mapparam);
                map.put("flag", true);
                map.put("message", "ok");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "count is zero");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取已结束考试异常："+e);
        }
        
        return map;
    }

    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndTest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "please login again");
            return map;
        }
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("student_id", studentUser.getId());
            List<LstStudentTest> list = lstStudentTestDao.selectStudentNotEndTest(mapparam);
            map.put("flag", true);
            map.put("message", "ok");
            map.put("list", list);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取未完成考试异常："+e);
        }
        return map;
    }
    
    @Override
    public List<KsQuestion> getTestKsQuestion(Integer testId) {
        return questionDao.getTestKsQuestion(testId);
    }
    
    @Override
    public Map<String,Object> setStudentAnswer(KsQuestion question) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            studentAnswerDao.updateByKsQuestion(question);
            if("1".equals(question.getIsEnd())){
                LstStudentTest test = new LstStudentTest();
                test.setId(question.getStudentTestId());
                test.setStudent_id(question.getStudentId());
                test.setClass_code(question.getClassCode());
                test.setTest_id(question.getTestId());
                LstStudentTest studentScore = lstStudentTestDao.getStudentCountedScore(test);
                test.setAccuracy_sogou(studentScore.getAccuracy_sogou());
                test.setFluency_sogou(studentScore.getFluency_sogou());
                test.setIntegrity_sogou(studentScore.getIntegrity_sogou());
                test.setOverall_sogou(studentScore.getOverall_sogou());
                test.setSubmission_time(new Date());
                test.setStatus("2");
                test.setIsEnd("1");
                lstStudentTestDao.updateByPrimaryKeySelective(test);
            }else if("1".equals(question.getIsQuestionEnd())){
                LstStudentTest test = new LstStudentTest();
                test.setId(question.getStudentTestId());
                test.setIsQuestionEnd("1");
                lstStudentTestDao.updateByPrimaryKeySelective(test);
            }
            result.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
        }
        return result;
        
    }
    
    @Override
    public int updateStudentTest(LstStudentTest test) {
        test.setOpen_time(new Date());
        return lstStudentTestDao.updateByPrimaryKeySelective(test);
    }
}

