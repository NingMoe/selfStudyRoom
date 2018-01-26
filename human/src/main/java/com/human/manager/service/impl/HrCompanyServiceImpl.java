package com.human.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.dao.AreaInfoDao;
import com.human.basic.entity.AreaInfo;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.MessageWish;
import com.human.manager.service.HrCompanyService;
import com.human.utils.PageView;

@Service
public class HrCompanyServiceImpl implements HrCompanyService{
    
    @Autowired
    private HrCompanyDao hrCompanyDao;
    
    @Autowired
    private AreaInfoDao areaInfoDao;
    
    @Override
    public int insertSelective(HrCompany record) {
        return hrCompanyDao.insertSelective(record);
    }
    
    
    @Override
    public HrCompany selectByPrimaryKey(String companyId) {
        return hrCompanyDao.selectByPrimaryKey(companyId);
    }
    
    @Override
    public int updateByPrimaryKeySelective(HrCompany record) {
        return hrCompanyDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(HrCompany record) {
        return hrCompanyDao.updateByPrimaryKey(record);
    }
    
    @Override
    public PageView findAllCompany(PageView pageView, HrCompany hrCompany) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", hrCompany);
        List<HrCompany> list = hrCompanyDao.selectCompanyPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<HrCompany> findAll() {
        return hrCompanyDao.selectAll();
    }
    
    @Override
    public List<AreaInfo> findAllSchoolArea() {
        return areaInfoDao.getAllSchoolArea();
    }
    
    @Override
    public HrCompany selectByCityId(Integer cityId) {
        return hrCompanyDao.selectByCityId(cityId);
    }
    
    @Override
    public List<MessageWish> selectWishEmployee() {
        return hrCompanyDao.selectWishEmployee();
    }


    @Override
    public HrCompany selectByEmailAddr(String email) {
        return hrCompanyDao.selectByEmailAddr(email);
    }
}
