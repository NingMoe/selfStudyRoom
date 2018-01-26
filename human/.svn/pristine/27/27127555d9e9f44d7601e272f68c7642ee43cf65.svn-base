package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;
import com.human.jzbTest.entity.JzbGrade;
import com.human.utils.PageView;

public interface JzbGradeService {
    /*
     * 分页查询年级数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,JzbGrade cf);
    
    /*
     * 新增年级数据
     * @param cf
     * @return
     */
    Map<String, Object> add(JzbGrade cf);
    
    /*
     * 通过主键查询年级数据
     * @param cf
     * @return
     */
    JzbGrade selectById(long id);
    
    /*
     * 编辑年级数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(JzbGrade cf);
    
    /*
     * 删除年级数据
     * @param cf
     * @return
     */
    Map<String, Object> delete (String deleteIds);
    
    /*
     * 根据部门查询年级
     * @param map
     */
    List<JzbGrade> selectByDeptId(Long deptId);
    
    /*
     * 根据学校查询年级
     * @param map
     */
    List<JzbGrade> selectByCompanyId(String companyId);
    
    /**
     * 根据学校ID及配置的试卷
     */
    List<JzbGrade> selectKyByCompanyId(String companyId);
}
