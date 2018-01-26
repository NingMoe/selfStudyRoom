package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.jzbTest.entity.JzbGrade;

@Repository
public interface JzbGradeDao {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(JzbGrade record);

    JzbGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JzbGrade record);

    /*
     * 分页查询
     * @param map
     * @return
     */
    List<JzbGrade> query(Map<Object, Object> map);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 根据部门查询年级
     * @param map
     */
    List<JzbGrade> selectByDeptId(Long deptId);
    
    /*
     * 根据学校查询年级
     * @param map
     */
    List<JzbGrade> selectByCompanyId(String companyId);
    
    /*
     * 根据学校及当前月份获取有配置试卷的年级
     * @param map
     */
    List<JzbGrade> selectValidGrade(@Param("companyId")String companyId,@Param("month")String month);
}