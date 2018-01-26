package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherMatchclass;

public interface IeltsTeacherMatchclassDao {
    int insert(IeltsTeacherMatchclass record);

    int insertSelective(IeltsTeacherMatchclass record);
    
    int updateByPrimaryKeySelective(IeltsTeacherMatchclass ieltsTeacherMatchclass);

    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    /**
     * 获取赛课信息
     * @param mapparam
     * @return
     */
    public List<IeltsTeacherMatchclass> selectByTeacherId(Map<String, Object> mapparam);

    /**
     * 分页获取赛课信息
     * @param pageView
     * @param ieltsTeacherMatchclass
     * @return
     */
    public List<IeltsTeacherMatchclass> query(Map<Object, Object> map);
}