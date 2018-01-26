package com.human.continuedClass.service;

import java.util.List;
import java.util.Map;
import com.human.continuedClass.entity.SchoolArea;
import com.human.utils.PageView;

public interface SchoolAreaService {
    /**
     * 分页查询校区信息
     */
    PageView query(PageView pageView,SchoolArea ccr);
    
    /**
     * 保存校区信息
     * @param ccr
     * @return
     */
    Map<String, Object> add(SchoolArea ccr);
    
    /**
     * 根据主键查询校区信息
     * @param id
     * @return
     */
    SchoolArea selectByPrimaryKey(Long id);
    
    /**
     * 编辑校区信息
     * @param ccr
     * @return
     */
    Map<String, Object> edit(SchoolArea ccr);
    
    /**
     * 删除校区信息
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 查询校区信息
     * @param sa
     * @return
     */
    List<SchoolArea> getSchoolArea(SchoolArea sa);
    
    /**
     * 根据校区名称查询校区
     * @param name
     * @return
     */
    SchoolArea selectByName(String name);
}
