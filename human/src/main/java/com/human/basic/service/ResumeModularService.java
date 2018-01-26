package com.human.basic.service;
import java.util.List;
import java.util.Map;
import com.human.basic.entity.ResumeModular;
import com.human.utils.PageView;

public interface ResumeModularService {
    /**
     * 分页查询
     * @param pageView
     * @param rm
     * @return
     */
    PageView query(PageView pageView,ResumeModular rm);
    
    /**
     * 新增
     * @param rm
     * @return
     */
    Map<String, Object> add(ResumeModular rm);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ResumeModular selectByPrimaryKey(Long id);
    
    /**
     * 编辑
     * @param rm
     * @return
     */
    Map<String, Object> edit(ResumeModular rm);
    
    /**
     * 删除
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 通过条件查询
     * @param rm
     * @return
     */
    List<ResumeModular> findResumeModularByCondition(String website);
}
