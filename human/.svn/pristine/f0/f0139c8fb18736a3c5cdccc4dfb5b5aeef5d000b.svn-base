package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherAttendance;

public interface IeltsTeacherAttendanceDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherAttendance record);

    int insertSelective(IeltsTeacherAttendance record);

    IeltsTeacherAttendance selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherAttendance record);

    int updateByPrimaryKey(IeltsTeacherAttendance record);

    /**
     * 分页获取出勤信息
     * @param map
     * @return
     */
    public List<IeltsTeacherAttendance> query(Map<Object, Object> map);

    /**
     * 获取教师出勤信息
     * @param mapparam
     * @return
     */
    public List<IeltsTeacherAttendance> selectByTeacherId(Map<Object, Object> mapparam);
}