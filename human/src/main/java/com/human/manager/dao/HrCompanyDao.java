package com.human.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.MessageWish;

@Repository
public interface HrCompanyDao {
    
    int deleteByPrimaryKey(String companyId);

    int insert(HrCompany record);

    int insertSelective(HrCompany record);
    
    List<HrCompany> selectAll();
    
    List<HrCompany> selectCompanyPage(Map<Object,Object> map);

    HrCompany selectByPrimaryKey(String companyId);
    
    HrCompany selectByCityId(Integer cityId);

    int updateByPrimaryKeySelective(HrCompany record);

    int updateByPrimaryKey(HrCompany record);
    
    List<MessageWish> selectWishEmployee();
    
    /*
     * 通过登录账号查询登录人所在学校的schoolId
     */
    HrCompany selectByEmailAddr(String email);
    
}