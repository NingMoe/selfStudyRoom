package com.human.continuation.dao;

import java.util.List;
import java.util.Map;

import com.human.continuation.entity.TeacherContinuationClass;

public interface TeacherContinuationClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherContinuationClass record);

    int insertSelective(TeacherContinuationClass record);

    TeacherContinuationClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherContinuationClass record);

    int updateByPrimaryKey(TeacherContinuationClass record);

    /**
     * 分页获取人班关系信息
     * @param map
     * @return
     */
    public List<TeacherContinuationClass> query(Map<Object, Object> map);

    /**
     * 通过学员号和email_addr获取班级信息
     * @param mapparam
     * @return
     */
    public List<TeacherContinuationClass> selectClassesinfoByStudentCodeAndEmailaddr(Map<String, Object> mapparam);

    /**
     * 获取教师班级下已配置和未配置人数
     * @param mapparamx
     * @return
     */
    public Integer selectClassConfigNumInfoByClassCode(Map<String, Object> mapparamx);

    /**
     * 
     * @param mapparam
     * @return
     */
    public List<TeacherContinuationClass> selectClassesInfoByStudentcodeAndClasscode(Map<String, Object> mapparam);
}