package com.human.manager.service;

import java.util.List;

import com.human.basic.entity.AreaInfo;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.MessageWish;
import com.human.utils.PageView;

public interface HrCompanyService {

    int insertSelective(HrCompany record);

    HrCompany selectByPrimaryKey(String companyId);
    
    HrCompany selectByCityId(Integer cityCode);

    int updateByPrimaryKeySelective(HrCompany record);
    
    int updateByPrimaryKey(HrCompany record);
    
    PageView findAllCompany(PageView pageView,HrCompany hrCompany);
    
    List<HrCompany> findAll();
    
    List<AreaInfo> findAllSchoolArea();
    
    List<MessageWish> selectWishEmployee();
    
    /*
     * 通过登录账号查询登录人所在学校的schoolId
     */
    HrCompany selectByEmailAddr(String email);
    
}
