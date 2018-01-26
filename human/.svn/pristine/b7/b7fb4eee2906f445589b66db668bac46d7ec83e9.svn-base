package com.human.continuedClass.service;

import java.util.Map;

import com.human.continuedClass.entity.ContinuedClassRule;
import com.human.utils.PageView;

public interface ContinuedClassRuleService {
    /**
     * 分页查询续班规则
     */
    PageView query(PageView pageView,ContinuedClassRule ccr);
    
    /**
     * 保存续班规则
     * @param ccr
     * @return
     */
    Map<String, Object> add(ContinuedClassRule ccr);
    
    /**
     * 根据主键查询续班规则
     * @param id
     * @return
     */
    ContinuedClassRule selectByPrimaryKey(Long id);
    
    /**
     * 编辑续班规则
     * @param ccr
     * @return
     */
    Map<String, Object> edit(ContinuedClassRule ccr);
    
    /**
     * 删除续班规则
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
}
