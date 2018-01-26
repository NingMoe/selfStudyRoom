package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsEnrollInfo;

public interface IeltsEnrollInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsEnrollInfo record);

    int insertSelective(IeltsEnrollInfo record);

    IeltsEnrollInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsEnrollInfo record);

    int updateByPrimaryKey(IeltsEnrollInfo record);

    /**
     * 删除id关联的考试信息
     * @param id
     * @return
     */
    public int deleteByStudentId(Integer id);

    /**
     * 获取学生分数信息
     * @param pageView
     * @param ieltsEnrollInfo
     * @return
     */
    public List<IeltsEnrollInfo> query(Map<Object, Object> map);

    /**
     * 获取有报名日期的人数
     * @param mapparam
     * @return
     */
    public Integer selectEnrollCount(Map<String, Object> mapparam);

    /**
     * 获取有成绩的人
     * @param mapparam
     * @return
     */
    public Integer selectStudentCount(Map<String, Object> mapparam);

    /**
     * 获取听力平均分
     * @param mapparam
     * @return
     */
    public Double selectStudentListenAvg(Map<String, Object> mapparam);

    /**
     * 获取阅读平均分
     * @param mapparam
     * @return
     */
    public Double selectStudentReadAvg(Map<String, Object> mapparam);

    /**
     * 获取写作平均分
     * @param mapparam
     * @return
     */
    public Double selectStudentWriteAvg(Map<String, Object> mapparam);

    /**
     * 获取口语平均分
     * @param mapparam
     * @return
     */
    public Double selectStudentTalkAvg(Map<String, Object> mapparam);

    /**
     * 总成绩平均分
     * @param mapparam
     * @return
     */
    public Double selectStudentTotalAvg(Map<String, Object> mapparam);

    /**
     * 获取所有学员最高分
     * @param mapparam
     * @return
     */
    public List<Double> selectStudentMaxSourceList(Map<String, Object> mapparam);

    /**
     * 获取雅思提分学员数
     * @param mapparam
     * @return
     */
    public Integer selectEnrollIncreaseCount(Map<String, Object> mapparam);

    /**
     * 获取计划内学员数
     * @param mapparam
     * @return
     */
    public Integer selectStudentEnrollPlanCount(Map<String, Object> mapparam);

    /**
     * 获取计划内提分学员数
     * @param mapparam
     * @return
     */
    public Integer selectEnrollIncreasePlanCount(Map<String, Object> mapparam);

    public Integer selectTeacherStudentCount(Map<String, Object> mapparam);

    Integer selectTeacherEnrollCount(Map<String, Object> mapparam);

    Double selectTeacherStudentListenAvg(Map<String, Object> mapparam);

    Double selectTeacherStudentReadAvg(Map<String, Object> mapparam);

    Double selectTeacherStudentWriteAvg(Map<String, Object> mapparam);

    Double selectTeacherStudentTalkAvg(Map<String, Object> mapparam);

    Double selectTeacherStudentTotalAvg(Map<String, Object> mapparam);

    List<Double> selectTeacherStudentMaxSourceList(Map<String, Object> mapparam);

    Integer selectTeacherEnrollIncreaseCount(Map<String, Object> mapparam);

    Integer selectTeacherStudentEnrollPlanCount(Map<String, Object> mapparam);

    Integer selectTeacherEnrollIncreasePlanCount(Map<String, Object> mapparam);

    Integer selectGradeCount(Map<String, Object> mapparam);

    Integer selectIncreaseGradeCount(Map<String, Object> mapparam);
}