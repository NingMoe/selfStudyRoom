package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.jzbTest.entity.JzbSchool;
@Repository
public interface JzbSchoolDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(JzbSchool record);

    JzbSchool selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbSchool record);
    
    /*
     * 分页查询
     * @param map
     * @return
     */
    List<JzbSchool> query(Map<Object, Object> map);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    List<JzbSchool> selectByAreaAndGrade(@Param("areaId") Integer areaId,@Param("gradeId") Integer gradeId);

    
}