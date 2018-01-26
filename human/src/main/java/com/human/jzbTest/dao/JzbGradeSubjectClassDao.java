package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.entity.JzbGradeSubjectDto;

@Repository
public interface JzbGradeSubjectClassDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(JzbGradeSubjectClass record);

    JzbGradeSubjectClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbGradeSubjectClass record);

    List<JzbGradeSubjectClass> query(Map<Object, Object> map);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 全部删除
     * @param map
     */
    int deleteAll(Long gradeSubjectId);
    
    /*
     * 通过年级、科目、班级类型查询推荐课程规则
     * @param map
     */
    List<JzbGradeSubjectClass> selectClassRuleByParams(JzbGradeSubjectDto record);

}