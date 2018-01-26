package com.human.continuation.service;

import java.util.Map;

import com.human.continuation.entity.TeacherContinuationClass;
import com.human.utils.PageView;

public interface TeacherContinuationClassService {

    /**
     * 分页查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    public PageView query(PageView page, TeacherContinuationClass teacherContinuationClass);

    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationClass teacherContinuationClass);

    /**
     * 新增班级中所有学员的人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> insertclass(TeacherContinuationClass teacherContinuationClass);

    /**
     * 修改人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> update(TeacherContinuationClass teacherContinuationClass);

    /**
     * 查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> select(TeacherContinuationClass teacherContinuationClass);

    /**
     * 删除人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationClass teacherContinuationClass);

    /**
     * 往接口中插入人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> updateIsadd(TeacherContinuationClass teacherContinuationClass);

    /**
     * 删除接口中人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> updateIsnotadd(TeacherContinuationClass teacherContinuationClass);
    
    /**
     * 通过姓名手机号获取学员号
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> getStduentCodes(TeacherContinuationClass teacherContinuationClass);

}
