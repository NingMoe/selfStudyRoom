package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsTeacherInfo;

public interface IeltsTeacherInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsTeacherInfo record);

    int insertSelective(IeltsTeacherInfo record);

    IeltsTeacherInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsTeacherInfo record);

    int updateByPrimaryKey(IeltsTeacherInfo record);

    /**
     * 分页获取教师信息
     * @param map
     * @return
     */
    public List<IeltsTeacherInfo> queryteacher(Map<Object, Object> map);

    /**
     * 通过学员id获取代课教师信息
     * @param id
     * @return
     */
    public List<IeltsTeacherInfo> selectByStudentId(Integer student_id);

    /**
     * 分页获取教师积分信息
     * @param map
     * @return
     */
    public List<IeltsTeacherInfo> query(Map<Object, Object> map);

    /**
     * 通过email_addr邮箱前缀后去教师信息
     * @param teachermail
     * @return
     */
    public IeltsTeacherInfo seleByEmailAddr(String emal_addr);

    /**
     * 通过teacher_id获取教师信息
     * @param id
     * @return
     */
    public IeltsTeacherInfo selectByTeacherId(Map<String, Object> mapparam);

    /**
     * 获取教师人数
     * @return
     */
    public Integer selectTeacherCount();

    /**
     * 查询教师功底积分
     * @param map
     * @return
     */
    public List<IeltsTeacherInfo> selectselectallteacherintegral(Map<Object, Object> map);

    /**
     * 获取所有教师信息
     * @return
     */
    public List<IeltsTeacherInfo> selectAllTeacherInfo();
}