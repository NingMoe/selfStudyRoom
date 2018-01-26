package com.human.mail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.mail.entity.AcceptMail;
@Repository
public interface AcceptMailDao {
    int deleteByPrimaryKey(Long id);

    int insert(AcceptMail record);

    int insertSelective(AcceptMail record);

    AcceptMail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AcceptMail record);

    int updateByPrimaryKey(AcceptMail record);
    
    /**
     * 根据邮件自带的唯一标示messageId查询邮件
     */
    int queryByMessageId(String messageId);
    
    /*
     * 查询所有解析失败的邮件
     */
    List<AcceptMail> queryFailAnalysis();
    
    
    /*
     * 分页查询所有解析的邮件
     */
    List<AcceptMail> query(Map<Object, Object> map);
    
}