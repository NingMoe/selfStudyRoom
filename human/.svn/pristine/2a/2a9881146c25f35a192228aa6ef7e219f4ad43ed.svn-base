package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.entity.JzbUpClass;

@Repository
public interface JzbUpClassDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(JzbUpClass record);

    JzbUpClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbUpClass record);

    List<JzbUpClass> query(Map<Object, Object> map);
    
    /**
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    /**
     * 通过年级、科目、班级类型查询升班期课程规则
     * @param map
     */
    List<JzbUpClass> selectUpClassRuleByParams(JzbGradeSubjectDto record);
}