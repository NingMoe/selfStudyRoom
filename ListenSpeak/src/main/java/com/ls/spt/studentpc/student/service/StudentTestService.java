package com.ls.spt.studentpc.student.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ls.spt.studentpc.student.entity.KsQuestion;
import com.ls.spt.studenttest.entity.LstStudentTest;

public interface StudentTestService {
    
    /**
     * 考试报告
     * @param request
     * @param response
     * @param test_id
     * @param class_code
     * @return
     */
    public Map<String, Object> getStudentTestInfo(HttpServletRequest request, HttpServletResponse response, Integer test_id, String class_code);

    /**
     * 获取学生所有考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllTest(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize);

    /**
     * 获取学生已完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndTest(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize);

    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndTest(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 获取学生考试题目列表
     */
    List<KsQuestion> getTestKsQuestion(Integer testId);
    
    /**
     * 提交学生答案
     */
    Map<String,Object> setStudentAnswer(KsQuestion question);
    
    /**
     * 更新学生考试表
     * @param test
     * @return
     */
    int updateStudentTest(LstStudentTest test);
}
