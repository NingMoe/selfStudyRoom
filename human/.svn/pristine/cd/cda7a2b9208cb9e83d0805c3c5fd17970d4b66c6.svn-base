package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.entity.JzbUpClass;
import com.human.utils.PageView;

public interface JzbUpClassService {
    /**
     * 分页查询升班期课程规则
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,JzbUpClass cf);
    
    /**
     * 保存升班期课程规则
     * @param pageView
     * @param cf
     * @return
     */
    Map<String, Object> add(JzbUpClass cf);
    
    /**
     * 通过主键查询升班期课程规则
     * @param cf
     * @return
     */
    JzbUpClass selectById(long id);
        
    
    /**
     * 删除升班期课程规则数据
     * @param pageView
     * @param cf
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 通过年级、科目、班级类型查询升班期课程规则
     * @param map
     */
    List<JzbUpClass> selectUpClassRuleByParams(JzbGradeSubjectDto record);
}
