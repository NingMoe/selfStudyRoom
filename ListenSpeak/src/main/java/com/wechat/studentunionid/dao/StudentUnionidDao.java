package com.wechat.studentunionid.dao;

import org.springframework.stereotype.Repository;

import com.wechat.studentunionid.entity.StudentUnionid;

@Repository
public interface StudentUnionidDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentUnionid record);

    int insertSelective(StudentUnionid record);

    StudentUnionid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentUnionid record);

    int updateByPrimaryKey(StudentUnionid record);

    /**
     * 通过unionid查询学员
     * @param studentUnionid
     * @return
     */
    public StudentUnionid selectByUnionid(StudentUnionid studentUnionid);

    /**
     * 解除绑定
     * @param wechatUserinfo
     * @return
     */
    public int delectByUnionid(StudentUnionid studentUnionid);
}