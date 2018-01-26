package com.human.basic.service;
import java.util.Map;
import com.human.basic.entity.ResumeKeyword;
import com.human.utils.PageView;

public interface ResumeKeywordService {
    /**
     * 分页查询
     * @param pageView
     * @param rm
     * @return
     */
    PageView query(PageView pageView,ResumeKeyword rk);
    
    /**
     * 新增
     * @param rm
     * @return
     */
    Map<String, Object> add(ResumeKeyword rk);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ResumeKeyword selectByPrimaryKey(Long id);
    
    /**
     * 编辑
     * @param rm
     * @return
     */
    Map<String, Object> edit(ResumeKeyword rk);
    
    /**
     * 删除
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
}
