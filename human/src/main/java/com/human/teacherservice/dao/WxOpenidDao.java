package com.human.teacherservice.dao;

import com.human.teacherservice.entity.WxOpenid;

public interface WxOpenidDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WxOpenid record);

    int insertSelective(WxOpenid record);

    WxOpenid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxOpenid record);

    int updateByPrimaryKey(WxOpenid record);
    
    /**
     * 通过openid获取用户
     * @param openid
     * @return
     */
    public WxOpenid selectByOpenid(String openid);
}