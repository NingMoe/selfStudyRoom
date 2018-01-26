package com.human.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.HrUser;
@Repository
public interface HrUserDao {
    int deleteByPrimaryKey(String emplId);

    int insert(HrUser record);

    int insertSelective(HrUser record);

    HrUser selectByPrimaryKey(HrUser hrUser);

    int updateByPrimaryKeySelective(HrUser record);

    int updateByPrimaryKey(HrUser record);
    
    int deleteByCompanyId(String companyId);
    
    int insertUserList(List<HrUser> list);
    
    List<HrUser> findAll();
    
    List<HrUser> findByCompany(String company);
    
}