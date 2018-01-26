package com.ls.spt.StudentClass.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.StudentClass.entity.StudentClass;

public interface StudentClassDao {
    int deleteByPrimaryKey(Long id);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    StudentClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);

    List<Map<String, Object>> query(Map<String, Object> map);

    int deleteByIds(Map<String, Object> paraMap);

    /**
     * 通过申请码和手机号获取申请状态
     * @param mapparams
     * @return
     */
    public StudentClass selectByClasscodeAndStudentid(Map<String, Object> mapparams);

}