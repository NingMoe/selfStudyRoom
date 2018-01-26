package com.human.manager.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.dao.HrOrganizationDao;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.OrgResponse;
import com.human.manager.service.HrOrganizationService;
import com.human.utils.HttpClientUtil;
import com.human.utils.Md5Tool;
import com.human.utils.PageView;

@Service
public class HrOrganizationServiceImpl implements HrOrganizationService{
    @Value("${ad.orgurl}")
    private String orgurl; 
   
    @Value("${ad.appid}")
    private String appid; 
    
    @Value("${ad.appkey}")
    private String appkey;
    
    @Autowired
    private HrOrganizationDao orgDao;
    
    @Autowired
    private HrCompanyDao companyDao;
    
    
    @Override
    public PageView findAllOrg(PageView pageView, HrOrganization hrOrganization) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", hrOrganization);
        List<HrOrganization> list = orgDao.selectByCondition(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<HrOrganization> findOrgByCondition(HrOrganization hrOrganization) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("t", hrOrganization);
        List<HrOrganization> list = orgDao.selectByCondition(map);
        return list;
    }
    
    
    
    @Override
    @Transactional
    public void refreshOrgsByCompanyId(String companyId) {
        orgDao.deleteByCompanyId(companyId);
        List<HrOrganization> orgs = getOrgsByCompanyIdFromAd(companyId);
        orgDao.insertOrgList(orgs);
        orgDao.updateHrExtend(companyId);
        HrCompany record = new HrCompany();
        record.setCompanyId(companyId);
        record.setOrgUpdateTime(new Timestamp(System.currentTimeMillis()));
        companyDao.updateByPrimaryKeySelective(record);
        orgDao.updateHrExtend(companyId);
        orgDao.updateOrgStatus();
    }
    
    @Override
    public List<HrOrganization> getOrgsByCompanyIdFromAd(String companyId){
        List<HrOrganization> result = new ArrayList<HrOrganization>();
        Map<String,Object> params = new HashMap<String,Object>();
        String sign = Md5Tool.get32md5((appid+""+appkey+""+companyId).toLowerCase());
        params.put("appid",appid);
        params.put("sign", sign);
        params.put("Comid",companyId);
        try {
            OrgResponse res = JSON.parseObject(HttpClientUtil.httpPostRequest(orgurl, null, params), OrgResponse.class);
            if("1".equals(res.getStatus())){
                result = (List<HrOrganization>) res.getData();
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
