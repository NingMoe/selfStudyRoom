package com.human.continuedClass.dao;

import com.human.continuedClass.entity.RuleBackPhoto;

public interface RuleBackPhotoDao {
    
    int insertSelective(RuleBackPhoto record);

    RuleBackPhoto selectByRuleId(Long ruleId);

    int updateByPrimaryKeySelective(RuleBackPhoto record);
    
    int deleteByRuleId(Long ruleId);
 
}