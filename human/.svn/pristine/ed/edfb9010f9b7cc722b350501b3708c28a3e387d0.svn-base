package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherOperate;

public interface IeltsTeacherOperateDao {
    int deleteByPrimaryKey(Integer id);

    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherOperate record);

    int insertSelective(IeltsTeacherOperate record);

    IeltsTeacherOperate selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherOperate record);

    int updateByPrimaryKey(IeltsTeacherOperate record);

    /**
     * 分页获取教学分享
     * @param pageView
     * @param ieltsTeacherOperate
     * @return
     */
    public List<IeltsTeacherOperate> query(Map<Object, Object> map);

    /**
     * 
     * @param mapparam1
     * @return
     */
    public List<IeltsTeacherOperate> selectByTeacherId(Map<String, Object> mapparam1);
}