package com.human.basic.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.basic.entity.College;
@Repository
public interface CollegeDao {
    /*
     * 
     */
    int deleteByPrimaryKey(Long id);
   /*
    * 新增大学
    */
    int insertSelective(College college);
    /*
     * 根据主键查询大学
     */
    College selectByPrimaryKey(Long id);
    /*
     * 更新大学
     */
    int updateByPrimaryKeySelective(College college);
    /*
     * 分页查询大学
     */
    List<College> query(Map<Object, Object> map);
    
    /**
     * 删除大学
     * @param paraMap
     * @return
     */
    int updateByIds(Map<String, Object> paraMap);
    
    /*
     * 根据学校名称查询大学
     */
    College selectByName(String name);

}