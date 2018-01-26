package com.human.basic.service;

import java.util.Map;
import com.human.basic.entity.College;
import com.human.utils.PageView;

public interface CollegeService {
    /**
     * 分页查询大学
     */
    PageView query(PageView pageView, College college);
    
    /**
     * 新增大学
     */
    int insertSelective(College college);
    
    /**
     * 根据主键查询大学
     */
    College selectByPrimaryKey(Long id);
    
    /**
     * 编辑大学
     */
    int updateByPrimaryKeySelective(College college);
    
    /**
     * 删除大学
     * @param deleteIds
     * @return
     */
    Map<String, Object> updateStatus(String deleteIds);
    
    /**
     * 同步大学
     */
    void refreshCollege();
    

    
}
