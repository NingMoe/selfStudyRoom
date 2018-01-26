package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherSource;

public interface IeltsTeacherSourceDao {
    int insert(IeltsTeacherSource record);

    int insertSelective(IeltsTeacherSource record);
    
    /**
     * 通过id删除成绩
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id);
    
    /**
     * 通过教师id删除成绩
     * @param teacher_id
     * @return
     */
    public int deleteByTeacherId(Integer teacher_id); 
    
    /**
     * 更新
     * @param ieltsTeacherSource
     * @return
     */
    public int updateByPrimaryKeySelective(IeltsTeacherSource ieltsTeacherSource);
    
    /**
     * 分页获取信息
     * @param map
     * @return
     */
    public List<IeltsTeacherSource> query(Map<Object, Object> map);

    /**
     * 查询雅思高分人数
     * @param mapparam
     * @return
     */
    public Integer selectieltsteachersource(Map<String, Object> mapparam);



    
}