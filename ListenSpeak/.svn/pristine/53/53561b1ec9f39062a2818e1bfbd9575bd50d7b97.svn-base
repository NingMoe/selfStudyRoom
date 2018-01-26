package com.ls.spt.studentzuoye.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.studentzuoye.entity.StudentGrowthTrajectory;

public interface LstStudentZuoyeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstStudentZuoye record);

    int insertSelective(LstStudentZuoye record);

    LstStudentZuoye selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstStudentZuoye record);

    int updateByPrimaryKey(LstStudentZuoye record);

    /**
     * 获取学生未完成作业
     * @param mapparam
     * @return
     */
    public List<LstStudentZuoye> selectStudentNotEndZuoye(Map<String, Object> mapparam);
    
    /**
     * 获取已经结束的作业数量
     * @param mapparam
     * @return
     */
    public Integer selectStudentEndZuoyeCount(Map<String, Object> mapparam);
    
    /**
     * 获取已经结束的作业
     * @param mapparam
     * @return
     */
    public List<LstStudentZuoye> selectStudentEndZuoye(Map<String, Object> mapparam);

    /**
     * 获取作业基础信息
     * @param mapparams
     * @return
     */
    public LstStudentZuoye selectByParams(Map<String, Object> mapparams);

    /**
     * 超过班级内人数百分比
     * @param mapparams
     * @return
     */
    public Double getTranscendCount(Map<String, Object> mapparams);

    /**
     * 获取学生成长轨迹
     * @param mapparam
     * @return
     */
    public List<StudentGrowthTrajectory> selectGrowthTrajectory(Map<String, Object> mapparam);
    
    /**
     * 获取作业基础信息
     * @param mapparams
     * @return
     */
    public LstStudentZuoye selectZuoyeinfo(Map<String, Object> mapparam);

    /**
     * 更新student_zuoye
     * @param mapparam
     * @return
     */
    public int updateByStudentidZuoyeidAndClassCode(Map<String, Object> mapparam);

}