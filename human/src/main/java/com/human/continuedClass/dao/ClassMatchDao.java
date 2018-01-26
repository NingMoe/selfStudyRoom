package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.ClassMatch;
@Repository
public interface ClassMatchDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(ClassMatch record);

    ClassMatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassMatch record);
    
    int deleteByRuleId(Long ruleId);

    List<ClassMatch> selectCMBySchoolArea(Long ruleId);
    
    /*
     * 批量插入
     * @param list
     */
    void insertByBatch(List<ClassMatch> list);
    
    /*
     * 导出所有的学员-班级数据
     * @param ruleId
     * @return
     */
    List<Map<String,Object>> exportAllStudentsToClass(Long ruleId);
        
    /*
     * 查询学员原班报班总数
     */
    long selectCountOfoClass(ClassMatch record);
    
    /*
     * 查询学员续班总数
     */
    long  selectCountOfcClass(ClassMatch record);
        
    /*
     * 分页查询学员-班级匹配明细数据
     */
    List<ClassMatch> query(Map<Object, Object> map);
    
    /*
     * 删除学员-班级数据
     */
    int deleteByIds(Map<String, Object> map);
    
    long selectByRuleId(Long ruleId);
    
    /**
     * 查询校区
     * @param ruleId
     * @return
     */
    List<ClassMatch>querySchoolArea(Long ruleId);
    
    /**
     * 按学员查询校区
     * @param ruleId
     * @return
     */
    List<ClassMatch>querySchoolAreaByStudents(Map<String, Object> map);
    
    /**
     * 查询年级
     * @param record
     * @return
     */
    List<ClassMatch> queryGrade(ClassMatch record);
    
    /**
     * 按学员查询年级
     * @param record
     * @return
     */
    List<ClassMatch> queryGradeByStudents(Map<String, Object> map);
    
    /**
     * 查询科目
     * @param record
     * @return
     */
    List<ClassMatch> querySubject(ClassMatch record);
    
    /**
     * 按学员查询科目
     * @param record
     * @return
     */
    List<ClassMatch> querySubjectByStudents(Map<String, Object> map);
    
    /**
     * 查询班号
     * @param record
     * @return
     */
    List<ClassMatch>queryClassCode(ClassMatch record);
    
    /**
     * 按学员查询班号
     * @param record
     * @return
     */
    List<ClassMatch>queryClassCodeByStudents(Map<String, Object> map);
    
    
    /**
     * 查询学员
     * @param record
     * @return
     */
    List<ClassMatch> queryStudents(ClassMatch record);
    
    /**
     * 按学员查询学员
     * @param record
     * @return
     */
    List<ClassMatch> queryStudentsByStudents(Map<String, Object> map);
    
    /**
     * 查询学员某个原班的续班明细
     * @param record
     * @return
     */
    List<ClassMatch> queryCourseDetails(ClassMatch record);
    
    /**
     * 查询学员除了某个原班外其它原班的续班明细
     * @param record
     * @return
     */
    List<ClassMatch> queryCourseDetails2(ClassMatch record);
    
    /**
     * 查询学员
     * @param record
     * @return
     */
    List<ClassMatch> queryStudentsByRuleId(ClassMatch record);
    
    /**
     * 查询学员的续班数据
     * @param record
     * @return
     */
    List<ClassMatch> queryClassMatch(ClassMatch record);
    
    
    /**
     * 查询学员推荐班级
     * @param record
     * @return
     */
    List<ClassMatch> queryClassMatchByRecommend(ClassMatch record);
    
}