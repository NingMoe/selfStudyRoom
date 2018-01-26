package com.human.basic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.dao.HrEthnicDao;
import com.human.basic.dao.HrNationalityDao;
import com.human.basic.entity.HrEthnic;
import com.human.basic.entity.HrNationality;
import com.human.basic.service.HrNationalityService;

@Service
public class HrNationalityServiceImpl implements HrNationalityService {

    @Autowired
    private HrNationalityDao nationalityDao;
    
    @Autowired
    private HrEthnicDao ethnicDao;
    
    @Override
    public List<HrNationality> getAllNationality() {
        // TODO Auto-generated method stub
        return nationalityDao.getAll();
    }

    @Override
    public List<HrEthnic> getAllEthnic() {
        // TODO Auto-generated method stub
        return ethnicDao.getAll();
    }

}
