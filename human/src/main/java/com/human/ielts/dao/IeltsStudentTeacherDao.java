package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsStudentTeacher;

public interface IeltsStudentTeacherDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsStudentTeacher record);

    int insertSelective(IeltsStudentTeacher record);

    IeltsStudentTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsStudentTeacher record);

    int updateByPrimaryKey(IeltsStudentTeacher record);

    /**
     * 删除id关联的教师
     * @param id
     * @return
     */
    public int deleteByStudentId(Integer id);

    /**
     * 通过教师id删除学生关联
     * @param id
     * @return
     */
    public int deleteByTeacherEmailaddr(String email_addr);

    /**
     * 成绩回收率
     * @param mapparam
     * @return
     */
    public List<IeltsStudentTeacher> selectByTeacherId(Map<String, Object> mapparam);
}