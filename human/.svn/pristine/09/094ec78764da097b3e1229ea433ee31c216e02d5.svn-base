package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.SendCardMail;
@Repository
public interface SendCardMailDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SendCardMail record);

    SendCardMail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendCardMail record);
    
    SendCardMail selectByRuleId(Long ruleId);
    
    List<SendCardMail> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);
    
    int deleteByRuleId(Long ruleId);

}