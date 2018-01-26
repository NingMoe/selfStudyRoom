package com.human.continuation.service;

import java.util.Map;

import com.human.continuation.entity.TeacherContinuationTeachername;
import com.human.utils.PageView;

public interface TeacherContinuationTeachernameService {

    /**
     * 分页获取管理的班级
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    public PageView query(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername);
    
    /**
     * 分页获取班级教师
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    public PageView classesquery(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 新增班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 修改班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> update(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 查询班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> select(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 删除班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 获取班级名称
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> getclassname(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 获取教师名称
     * @param teacherContinuationTeachername
     * @return
     */
    public Map<String, Object> getteachername(TeacherContinuationTeachername teacherContinuationTeachername);

}
