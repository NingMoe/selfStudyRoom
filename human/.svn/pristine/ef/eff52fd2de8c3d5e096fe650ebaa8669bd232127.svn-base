package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;
import com.human.jzbTest.entity.JzbGradeSubject;
import com.human.utils.PageView;

public interface JzbGradeSubjectService {
    /*
     * 分页查询年级科目数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,JzbGradeSubject cf);
    
    /*
     * 新增年级科目数据
     * @param cf
     * @return
     */
    Map<String, Object> add(JzbGradeSubject cf);
    
    /*
     * 通过主键查询年级科目数据
     * @param cf
     * @return
     */
    JzbGradeSubject selectById(long id);
    
    /*
     * 编辑年级科目数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(JzbGradeSubject cf);
    
    /*
     * 删除年级科目数据
     * @param cf
     * @return
     */
    Map<String, Object> delete (String deleteIds);
    
    /*
     * 查询某年级下的科目
     * @param cf
     * @return
     */
    List<JzbGradeSubject> selectByGradeId (Long gradeId);
}
