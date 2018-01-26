package com.human.continuation.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.continuation.entity.TeacherContinuationClass;

public interface TeacherContinuationUserService {
    
    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> addclasspeople(HttpServletRequest request, TeacherContinuationClass teacherContinuationClass);

    /**
     * 获取教师代课班级信息
     * @return
     */
    public Map<String, Object> getTeacherClasses(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取教师要续班级
     * @return
     */
    public Map<String, Object> getTeacherStudentClasses(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取教师可续班级
     * @return
     */
    public Map<String, Object> getTeacherNotBeginClasses(HttpServletRequest request, HttpServletResponse response);

    /**
     * 添加人班关系
     * @param studentclasses
     * @return
     */
    public Map<String, Object> addStduentClasses(HttpServletRequest request, HttpServletResponse response, String student_code, String student_name, String studentclasses);

    /**
     * 添加班班关系
     * @param classesclasses
     * @return
     */
    public Map<String, Object> addClassesClasses(HttpServletRequest request, HttpServletResponse response, String oldclasses, String newclasses);

    /**
     * 通过班号获取班级信息
     * @param class_code
     * @return
     */
    public Map<String, Object> getClassinfoByClasscode(String class_code);

    /**
     * 通过班号获取学员以及所报班级
     * @param class_code
     * @return
     */
    public Map<String, Object> getStudentinfoByClasscode(HttpServletRequest request,String class_code, String student_name);

    /**
     * 删除人班关系
     * @param request
     * @param response
     * @param student_code
     * @param studentclasses
     * @return
     */
    public Map<String, Object> removestduentclasses(HttpServletRequest request, HttpServletResponse response, String student_code, String studentclasses);

}
