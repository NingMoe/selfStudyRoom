package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherArticle;

public interface IeltsTeacherArticleDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByTeacherId(Integer teacher_id);

    int insert(IeltsTeacherArticle record);

    int insertSelective(IeltsTeacherArticle record);

    IeltsTeacherArticle selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherArticle record);

    int updateByPrimaryKey(IeltsTeacherArticle record);

    public List<IeltsTeacherArticle> query(Map<Object, Object> map);

    /**
     * 获取所有教研文章
     * @param mapparam
     * @return
     */
    public List<IeltsTeacherArticle> selectByTeacherId(Map<Object, Object> mapparam);
}