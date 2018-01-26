package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.ClassMatch;


@Repository
public interface ClassInformationDao {
   
    int insertSelective(ClassInformation record);
    
    /*
     * 批量插入原班数据
     * @param list
     */
    void insertOrginalClassByBatch(List<ClassInformation> list);
    
    /*
     * 批量插入续班数据
     * @param list
     */
    void insertContinuedClassByBatch(List<ClassInformation> list);

    ClassInformation selectByPrimaryKey(ClassInformation record);

    int updateByPrimaryKeySelective(ClassInformation record);
    
    List<ClassInformation> query(Map<Object, Object> map);

    int deleteByIds(Map<String, Object> map);
    
    int deleteAll(ClassInformation record);
    
    /*
     * 导出选择的班级数据
     */
    List<Map<String,Object>> exportSelect(Map<String,Object> map);
    
    /*
     * 查询全部班号
     */
    List<Map<String,Object>> selectAllClassCodes(ClassInformation record);
    
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
     * 通过班号查询班级详情信息
     * @param cm
     * @return
     */
    ClassInformation selectByClassCode(ClassMatch cm);
    
    /*
     * 导出升班数据
     * @return
     */
    List<Map<String,Object>> exportUpClassByRuleId(Long ruleId);
}