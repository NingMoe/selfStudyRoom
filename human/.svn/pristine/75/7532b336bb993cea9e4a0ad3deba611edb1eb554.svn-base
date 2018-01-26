package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;

import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.ClassStudents;

public interface ClassStudentsDao {
    
    int insertSelective(ClassStudents record);

    ClassStudents selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassStudents record);
    
    ClassStudents select(ClassStudents record);
    
    /*
     * 批量插入
     * @param list
     */
    void insertByBatch(List<ClassStudents> list);
        
    /*
     * 批量更新
     * @param list
     */
    void updateBatch(List<ClassStudents> list);
    
    /*
     * 匹配同教师
     * @param ruleId
     * @return
     */
    List<Map<String,Object>> exportSelectByTeacher(Long ruleId);
    
    /*
     * 匹配同科目
     * @return
     */
    List<Map<String,Object>> exportSelectBySubject(Long ruleId); 
    
    /*
     * 匹配临近校区
     * @return
     */
    List<Map<String,Object>> exportSelectBySchoolArea(Long ruleId);
    
    /*
     * 分页查询匹配数据
     */
    List<ClassMatch> query(Map<Object, Object> map);

    int deleteByRuleId(Long ruleId);
    
    List<Map<String,Object>> queryList(Long ruleId);
    
    List<String> getStudentCode(Long ruleId);
    
    List<ClassStudents>getAllClass(ClassStudents record);
    
    void updateStudentByIds(Map<String, Object> map);
}