package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbGradeSubject;

@Repository
public interface JzbGradeSubjectDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(JzbGradeSubject record);

    JzbGradeSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbGradeSubject record);
    
    /*
     * 分页查询
     * @param map
     * @return
     */
    List<JzbGradeSubject> query(Map<Object, Object> map);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);

    /*
     * 查询某年级下的科目
     */
    List<JzbGradeSubject> selectByGradeId (Long gradeId);
}