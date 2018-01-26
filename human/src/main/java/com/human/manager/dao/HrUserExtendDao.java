package com.human.manager.dao;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.HrUserExtend;

@Repository
public interface HrUserExtendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(HrUserExtend record);
    
    int insertSelective(HrUserExtend record);

    HrUserExtend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrUserExtend record);

    int updateByPrimaryKey(HrUserExtend record);
    
    int updateHrExtend(String company);
    
    /**
     * 更新员工手机号
     * @param empSupser
     * @return
     */
    int updateUserPhone(HrUserExtend userExt);
}