package com.ls.spt.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.ls.spt.basic.entity.School;



@Repository
public interface SchoolDao {

    int insertSelective(School record);

    School selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(School record);

    /*
     * 分页查询
     * @param map
     * @return
     */
    List<School> query(Map<Object, Object> map);
    
    /*
     * 批量删除
     * @param map
     */
    int deleteByIds(Map<String, Object> map);
    
    /*
     * 批量插入公立学校数据
     * @param list
     */
    void insertSchoolByBatch(List<School> list);
    
    /*
     * 根据条件查询
     */
    List<School>getSchoolByParam(School cf);
}