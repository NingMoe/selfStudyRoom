package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherFeedback;

public interface IeltsTeacherFeedbackDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherFeedback record);

    int insertSelective(IeltsTeacherFeedback record);

    IeltsTeacherFeedback selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherFeedback record);

    int updateByPrimaryKey(IeltsTeacherFeedback record);
    
    public List<IeltsTeacherFeedback> selectByTeacherId(Map<String, Object> map);

    /**
     * 分页获取教学反馈
     * @param pageView
     * @param ieltsTeacherFeedback
     * @return
     */
    public List<IeltsTeacherFeedback> query(Map<Object, Object> map);
}