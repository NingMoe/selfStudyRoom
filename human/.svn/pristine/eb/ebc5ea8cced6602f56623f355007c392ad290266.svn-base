package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherComplaint;
import com.human.ielts.entity.IeltsTeacherOperate;

public interface IeltsTeacherComplaintDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherComplaint record);

    int insertSelective(IeltsTeacherComplaint record);

    IeltsTeacherComplaint selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherComplaint record);

    int updateByPrimaryKey(IeltsTeacherComplaint record);

    /**
     * 分页获取教学分享
     * @param pageView
     * @param ieltsTeacherComplaint
     * @return
     */
    public List<IeltsTeacherOperate> query(Map<Object, Object> map);

    /**
     * 获取教学服务
     * @param mapparam1
     * @return
     */
    public List<IeltsTeacherComplaint> selectByTeacherId(Map<String, Object> mapparam);
}