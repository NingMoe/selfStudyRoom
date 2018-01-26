package com.human.continuedClass.service;

import java.util.List;
import java.util.Map;

import com.human.continuedClass.entity.SchoolAreaMatch;
import com.human.utils.PageView;

public interface SchoolAreaMatchService {
    /**
     * 分页查询校区匹配信息
     */
    PageView query(PageView pageView,SchoolAreaMatch ccr);
    
    /**
     * 保存校区匹配信息
     * @param ccr
     * @return
     */
    Map<String, Object> add(SchoolAreaMatch ccr);
    
    /**
     * 根据主键查询校区匹配信息
     * @param id
     * @return
     */
    SchoolAreaMatch selectByPrimaryKey(Long id);
    
    /**
     * 编辑校区匹配信息
     * @param ccr
     * @return
     */
    Map<String, Object> edit(SchoolAreaMatch ccr);
    
    /**
     * 删除校区匹配信息
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds,long ruleId);
    
    /**
     * 通过校区ID查询其相邻校区
     * @param schoolAreaId
     * @return
     */
    List<SchoolAreaMatch> selectBySmId(SchoolAreaMatch ccr);
}
