package com.human.continuation.dao;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.XdfClassInfo;
import com.human.continuation.entity.TeacherContinuationTeachername;

public interface TeacherContinuationTeachernameDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherContinuationTeachername record);

    int insertSelective(TeacherContinuationTeachername record);

    TeacherContinuationTeachername selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherContinuationTeachername record);

    int updateByPrimaryKey(TeacherContinuationTeachername record);
    
    /**
     * 分页获取管理的班级
     * @param map
     * @return
     */
    public List<TeacherContinuationTeachername> query(Map<String,Object> map);
    
    /**
     * 分页获取班级教师
     * @param map
     * @return
     */
    public List<TeacherContinuationTeachername> classesquery(Map<String, Object> map);

    /**
     * 通过班号获取班级名称
     * @param teacherContinuationTeachername
     * @return
     */
    public TeacherContinuationTeachername getclassname(TeacherContinuationTeachername teacherContinuationTeachername);

    /**
     * 通过email前缀获取教师名称
     * @param teacherContinuationTeachername
     * @return
     */
    public TeacherContinuationTeachername getteachername(TeacherContinuationTeachername teacherContinuationTeachername);

    
    /**
     * 通过邮箱前缀，获取教师当前班级结课时间在今天以后的班级信息
     * @return list
     */
    List<XdfClassInfo> selectTeacherStartClassesOnByEmrilAddr(Map<String, Object> mapparam);

}