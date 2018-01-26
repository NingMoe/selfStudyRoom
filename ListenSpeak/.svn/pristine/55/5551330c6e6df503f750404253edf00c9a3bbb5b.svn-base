package com.ls.spt.studenttest.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.studenttest.entity.LstStudentTest;

public interface LstStudentTestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstStudentTest record);

    int insertSelective(LstStudentTest record);

    LstStudentTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstStudentTest record);

    int updateByPrimaryKey(LstStudentTest record);

    /**
     * 获取学生未完成考试
     * @param mapparam
     * @return
     */
    public List<LstStudentTest> selectStudentNotEndTest(Map<String, Object> mapparam);

    /**
     * 获取未完成考试数量
     * @param mapparam
     * @return
     */
    public Integer selectStudentNotEndTestCount(Map<String, Object> mapparam);
    
    /**
     * 获取学生已完成考试
     * @param mapparam
     * @return
     */
    public List<LstStudentTest> selectStudentEndTest(Map<String, Object> mapparam);

    /**
     * 获取已完成考试数量
     * @param mapparam
     * @return
     */
    public Integer selectStudentEndTestCount(Map<String, Object> mapparam);

    /**
     * 获取考试基础信息
     * @param mapparams
     * @return
     */
    public LstStudentTest selectByParams(Map<String, Object> mapparams);
    

    /**
     * 获取班级考试平均分
     * @param mapparams
     * @return
     */
    public LstStudentTest selectClassByParams(Map<String, Object> mapparams);

    /**
     * 超过学生百分比
     * @param mapparams
     * @return
     */
    public Integer selectTranscendCount(Map<String, Object> mapparams);
    
    /**
     * 比该学生总分高的人数
     * @param mapparams
     * @return
     */
    public Integer selectClassCountThanStudent(Map<String, Object> mapparams);
    
    /**
     * 获取加权后的平均分
     */
    LstStudentTest getStudentCountedScore(LstStudentTest test);
    
}