package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherTkt;

public interface IeltsTeacherTktDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherTkt record);

    int insertSelective(IeltsTeacherTkt record);

    IeltsTeacherTkt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsTeacherTkt record);

    int updateByPrimaryKey(IeltsTeacherTkt record);

    /**
     * 通过教师id获取所有tkt成绩
     * @param id
     * @return
     */
    public List<IeltsTeacherTkt> selectByTeacherId(Integer teacher_id);

    /**
     * 分页获取教师信息
     * @param map
     * @return
     */
    public List<IeltsTeacherTkt> query(Map<Object, Object> map);

    /**
     * 查询TKT高分教师人数占比
     * @param mapparam
     * @return
     */
    Integer selectieltsteachersource(Map<String, Object> mapparam);
}