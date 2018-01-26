package com.human.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.HrOrganization;

@Repository
public interface HrOrganizationDao {
    int deleteByPrimaryKey(String id);

    int insert(HrOrganization record);
    
    int updateHrExtend(String company);
    
    int insertSelective(HrOrganization record);

    HrOrganization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HrOrganization record);

    int updateByPrimaryKey(HrOrganization record);
    
    int deleteByCompanyId(String companyId);
    
    int insertOrgList(List<HrOrganization> orgs);
    
    List<HrOrganization> selectByCondition(Map<Object,Object> map);
    
    //将没有人的部门状态改为无效
    int updateOrgStatus();
    
}