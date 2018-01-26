package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsClassType;

public interface IeltsClassTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsClassType record);

    int insertSelective(IeltsClassType record);

    IeltsClassType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsClassType record);

    int updateByPrimaryKey(IeltsClassType record);

    /**
     * 初始化获取班级类型
     * @return
     */
    public List<IeltsClassType> selectAllClassType();

    /**
     * 分页获取班级类型
     * @param pageView
     * @param ieltsClassType
     * @return
     */
    public List<IeltsClassType> query(Map<Object, Object> map);

    /**
     * 通过学员id获取班级类型
     * @param student_id
     * @return
     */
    public List<IeltsClassType> selectByStudentId(Integer student_id);

    public IeltsClassType selectByClassName(Map<String, Object> mapparams);
}