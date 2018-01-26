package com.human.binding.dao;

import java.util.List;

import com.human.binding.entity.WechatTeacherBinding;
import com.human.front.entity.WxTeacherModule;

public interface WechatTeacherBindingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatTeacherBinding record);

    int insertSelective(WechatTeacherBinding record);

    WechatTeacherBinding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatTeacherBinding record);

    int updateByPrimaryKey(WechatTeacherBinding record);

    public WechatTeacherBinding selectByOpenid(String openid);
    
    public WechatTeacherBinding selectUserByOpenid(String openid);

    public int deleteByOpenid(String openid);
    
}