package com.human.manager.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.dao.HrUserDao;
import com.human.manager.dao.HrUserExtendDao;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrUser;
import com.human.manager.entity.UserResponse;
import com.human.manager.service.HrUserService;
import com.human.utils.HttpClientUtil;
import com.human.utils.Md5Tool;


@Service
public class HrUserServiceImpl implements HrUserService{
    @Value("${ad.userurl}")
    private String userurl; 
    
    @Value("${ad.usermethod}")
    private String usermethod; 
   
    @Value("${ad.appid}")
    private String appid; 
    
    @Value("${ad.appkey}")
    private String appkey;
    
    @Autowired
    private HrUserDao userDao;
    
    @Autowired
    private HrUserExtendDao extendDao;
    
    @Autowired
    private HrCompanyDao companyDao;
    
    @Override
    public List<HrUser> getUsersByCompanyIdFromAd(String companyId) {
        List<HrUser> result = new ArrayList<HrUser>();
        Map<String,Object> params = new HashMap<String,Object>();
        String sign = Md5Tool.get32md5((usermethod+""+appid+""+companyId+""+appkey).toLowerCase());
        params.put("method",usermethod);
        params.put("appid",appid);
        params.put("id",companyId);
        params.put("all", "0");
        params.put("allfileds", "1");
        params.put("sign", sign);
        try {
            UserResponse res = JSON.parseObject(HttpClientUtil.httpPostRequest(userurl, null, params), UserResponse.class);
            if("1".equals(res.getStatus())){
                result = (List<HrUser>) res.getData();
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public void refreshUsersByCompanyId(String companyId) {
        userDao.deleteByCompanyId(companyId);
        List<HrUser> users = getUsersByCompanyIdFromAd(companyId);
        userDao.insertUserList(users);
        HrCompany record = new HrCompany();
        record.setCompanyId(companyId);
        record.setUserUpdateTime(new Timestamp(System.currentTimeMillis()));
        companyDao.updateByPrimaryKeySelective(record);
        extendDao.updateHrExtend(companyId);
    }
}
