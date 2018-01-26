package com.human.continuation.service;

import java.util.List;
import java.util.Map;

import com.human.continuation.entity.SchoolSbq;
import com.human.continuation.entity.TeacherContinuationConfig;
import com.human.utils.PageView;

public interface TeacherContinuationConfigService {

    /**
     * 分页获取配置信息
     * @param pageView
     * @param teacherContinuationConfig
     * @return
     */
    public PageView query(PageView pageView, TeacherContinuationConfig teacherContinuationConfig);

    /**
     * 新增配置信息
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationConfig teacherContinuationConfig);

    /**
     * 修改配置信息
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> update(TeacherContinuationConfig teacherContinuationConfig);

    /**
     * 查询关系配置
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> select(TeacherContinuationConfig teacherContinuationConfig);

    /**
     * 删除关系配置
     * @param teacherContinuationConfig
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationConfig teacherContinuationConfig);

    /**
     * 获取数据词典中管理部门
     * @return
     */
    public Map<String, Object> selectManagerDept();
    
    /**
     * 获取数据词典中财年
     * @return
     */
    public Map<String, Object> selectFiscalYear();

    public List<SchoolSbq> getSbqList();

}
