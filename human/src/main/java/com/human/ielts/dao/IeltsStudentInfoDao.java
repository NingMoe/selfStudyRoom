package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsStudentInfo;

public interface IeltsStudentInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsStudentInfo record);

    int insertSelective(IeltsStudentInfo record);

    IeltsStudentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsStudentInfo record);

    int updateByPrimaryKey(IeltsStudentInfo record);

    /**
     * 分页获取学生信息
     * @param map
     * @return
     */
    public List<IeltsStudentInfo> query(Map<Object, Object> map);

    /**
     * 删除id本身的基础信息
     * @param id
     * @return
     */
    public int deleteByStudentId(Integer id);

    /**
     * 通过姓名手机号获取学生基础信息
     * @param student_name
     * @param student_phone
     * @return
     */
    public IeltsStudentInfo selectByStudentNameAndStudentPhone(Map<String, Object> map);

    /**
     * 获取学员总数
     * @return
     */
    public Integer selectStudentCount();

    /**
     * 分页获取教师的学生信息
     * @param map
     * @return
     */
    List<IeltsStudentInfo> queryTeacherStudent(Map<Object, Object> map);

    Integer selectTeacherStudentCount(Map<String, Object> mapparam);
}