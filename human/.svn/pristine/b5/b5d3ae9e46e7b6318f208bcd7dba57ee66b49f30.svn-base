package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.utils.PageView;

public interface JzbGradeSubjectClassService {
    
    /*
     * 分页查询推荐课程规则
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,JzbGradeSubjectClass cf);
    
    /*
     * 保存推荐课程规则
     * @param pageView
     * @param cf
     * @return
     */
    Map<String, Object> add(JzbGradeSubjectClass cf);
    
    /*
     * 通过主键查询推荐课程规则
     * @param cf
     * @return
     */
    JzbGradeSubjectClass selectById(long id);
    
    
    /*
     * 编辑推荐课程规则数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(JzbGradeSubjectClass cf);
    
    /*
     * 删除推荐课程规则数据
     * @param pageView
     * @param cf
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /*
     * 全部删除推荐课程规则数据
     * @param pageView
     * @param cf
     * @return
     */
    Map<String, Object> deleteAll(Long gradeSubjectId);
    
    /*
     * 通过年级、科目、班级类型查询推荐课程规则
     * @param map
     */
    List<JzbGradeSubjectClass> selectClassRuleByParams(JzbGradeSubjectDto record);
}
