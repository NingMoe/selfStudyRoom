package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherShare;

public interface IeltsTeacherShareDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherShare record);

    int insertSelective(IeltsTeacherShare record);

    IeltsTeacherShare selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherShare record);

    int updateByPrimaryKey(IeltsTeacherShare record);

    public List<IeltsTeacherShare> query(Map<Object, Object> map);

    /**
     * 获取所有教研分享
     * @param mapparam
     * @return
     */
    public List<IeltsTeacherShare> selectByTeacherId(Map<Object, Object> mapparam);
}