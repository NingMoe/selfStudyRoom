package com.ls.spt.lstClass.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstClass.entity.LstClass;

public interface LstClassDao {
	int deleteByPrimaryKey(Long id);

    int insert(LstClass record);

    int insertSelective(LstClass record);

    LstClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LstClass record);

    int updateByPrimaryKey(LstClass record);

    List<LstClass> query(Map<Object, Object> map);
    
    Map<String, Object> selectByPrimaryKeyMap(Long id);

    int deleteByIds(Map<String, Object> paraMap);

    String selectCount(String classCode);

    /**
     * 通过学生信息和状态获取学生的班级
     * @param mapparam
     * @return
     */
    public List<LstClass> selectByStudentUser(Map<String, Object> mapparam);

    /**
     * 通过学生信息和状态获取学生班级数量
     * @param mapparam
     * @return
     */
    public Integer selectClassCountByStudentUser(Map<String, Object> mapparam);

    /**
     * 通过申请码和手机号获取班级信息
     * @param mapparam
     * @return
     */
    public LstClass selectByInvitationcodeAndPhone(Map<String, Object> mapparam);



    List<LstClass> selectPrimaryKeyInfo(long userId);

    int goUse(Integer id);
}