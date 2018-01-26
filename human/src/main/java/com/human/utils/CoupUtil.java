package com.human.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.human.coupon.entity.StudentCoup;
import com.human.coupon.entity.StudentRes;

@Component
public class CoupUtil {
    private final Lock lock = new ReentrantLock(); 
    
    private final  Logger logger = LogManager.getLogger(CoupUtil.class);
    @Value("${coupon.grant_type}")
    private String grant_type;
    
    @Value("${coupon.client_id}")
    private String client_id;
    
    @Value("${coupon.client_secret}")
    private String client_secret;
    
    @Value("${coupon.username}")
    private String username;
    
    @Value("${coupon.password}")
    private String password;
    
    @Value("${coupon.tokenUrl}")
    private String tokenUrl;
    
    @Value("${coupon.couponUrl}")
    private String coupUrl;
    
    @Value("${coupon.student_url}")
    private String student_url;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    
    @SuppressWarnings("rawtypes")
    public String getToken(){
        logger.info("调用获取TOKEN接口START-----");
        lock.lock();  
        if(redisTemplateUtil.isExist(Constants.COUPTOKEN)){
            return (String) redisTemplateUtil.get(Constants.COUPTOKEN);
        }
        String access_token = "";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("grant_type",grant_type);
        params.put("client_id",client_id);
        params.put("client_secret",client_secret);
        params.put("username",username);
        params.put("password",password);
        try {
            String res = HttpClientUtil.httpPostRequest(tokenUrl,null, params);
            Map map = (Map) JSON.parse(res);
            access_token = (String) map.get("access_token");
            redisTemplateUtil.set(Constants.COUPTOKEN, access_token, 29*60L);  
            logger.info("调用获取TOKEN接口END-----");
            return access_token;
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }finally{
            lock.unlock();
        }
    }
    
    public Map<String,Object> getCoup(String studentCode,String phone,String schoolId){
        logger.info("调用获取优惠信息接口START-----schoolId:"+schoolId);
        Map<String,Object> result = new HashMap<String,Object>();
        if(StringUtils.isEmpty(studentCode) && StringUtils.isEmpty(phone)){
            result.put("flag", false);
            result.put("message", "学生编号、电话不能全为空!");
            return result;
        }
        Map<String,Object> params = new HashMap<String,Object>();
        String token = "";
        try{
            if(redisTemplateUtil.isExist(Constants.COUPTOKEN)){
                token = (String) redisTemplateUtil.get(Constants.COUPTOKEN);
            }else{
                token = getToken();
            }
            params.put("SchoolID",schoolId);
            params.put("StudentCode",studentCode);
            params.put("NewStudentMobile",phone);
            params.put("PageSize",0);
            params.put("PageIndex",0);
            String res = HttpClientUtil.httpPostRequest1(coupUrl,null,params,token);
            logger.info(res);
            StudentCoup sc = JSON.parseObject(res,StudentCoup.class);
            result.put("flag", true);
            result.put("Data",sc);
        }catch(Exception e){
            logger.error("获取学生优惠券信息失败");
            result.put("flag", false);
            result.put("message", "获取学生优惠券信息失败！");
        }
        
        logger.info("调用获取优惠信息接口END-----");
        return result;
    }
    
    public Map<String,Object> getStudentCode(String openId,String schoolid){
        logger.info("根据OPEID获取学生信息开始");
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            String res = HttpClientUtil.httpGetRequest(student_url+"?openid="+openId+"&schoolid="+schoolid, null);
            StudentRes sr = JSON.parseObject(res, StudentRes.class);
            result.put("flag", true);
            result.put("Data",sr);
        }
        catch (Exception e) {
            logger.error("获取学生优惠券信息失败");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "根据OPENID获取学生信息失败！");
        }
        logger.info("根据OPEID获取学生信息结束");
        return result;
    }
    
    public Map<String,Object> unBindOpenId(String openId,String schoolid){
        logger.info("解绑OPEID开始");
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("openid", openId);
            params.put("schoolid", schoolid);
            String jsonObj = JSON.toJSONString(params);
            String res = HttpClientUtil.httpPutRequest(student_url,jsonObj);
            if(StringUtils.isNotEmpty(res)){
                result.put("flag", true);
                result.put("Data",res);
            }
        }
        catch (Exception e) {
            logger.error("解绑OPEID失败");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "解绑OPEID失败");
        }
        logger.info("解绑OPEID结束");
        return result;
    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String access_token = "";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("grant_type","password");
        params.put("client_id","MicroServiceApp");
        params.put("client_secret","58476952659365838447");
        params.put("username","MicroServiceApiUser");
        params.put("password","de3@G22");
        String res = HttpClientUtil.httpPostRequest("http://preferential.staff.xdf.cn/",null, params);
        System.out.println(res);
        Map map = (Map) JSON.parse(res);
        access_token = (String) map.get("access_token");
        System.out.println(access_token);
        
        
        //icAEWjYenhCBaUEfBVwQRx-kVFAXVYbRMB_Kzzy9oY4lFamG2SiInE0tQuS-U4MNCqwTvw-2ha4E__uxDAQ7keM4tOKO7rQxpR-qQ_Mpre_fA4qusYSOeezczW92ahrDWvBoL5BuvF7ZXefnxj1V_yM3kPnMKCZsl2hNPzfkatzH6sPK3dcxzYkBbHXdiI4jYQxxSeVsqerS5JW-2Eya2qQsV26ek-hgMd4-_BcmIq2sl4sCz6kJslq1dbWfFHq-hpzz5O-KN6WejALP33OGJXRlXttuy9Au1M8ab6jd1ce496xpKRHs7bJWeCZb7f3utD__aCuWjk1jEGBVm3zMIQ
        
        /*Map<String,Object> params = new HashMap<String,Object>();
        params.put("SchoolID",25);
        String res = HttpClientUtil.httpPostRequest1("http://xuban.t.staff.xdf.cn/Api/LimitWP/GetWindowPeriod",null,params,"icAEWjYenhCBaUEfBVwQRx-kVFAXVYbRMB_Kzzy9oY4lFamG2SiInE0tQuS-U4MNCqwTvw-2ha4E__uxDAQ7keM4tOKO7rQxpR-qQ_Mpre_fA4qusYSOeezczW92ahrDWvBoL5BuvF7ZXefnxj1V_yM3kPnMKCZsl2hNPzfkatzH6sPK3dcxzYkBbHXdiI4jYQxxSeVsqerS5JW-2Eya2qQsV26ek-hgMd4-_BcmIq2sl4sCz6kJslq1dbWfFHq-hpzz5O-KN6WejALP33OGJXRlXttuy9Au1M8ab6jd1ce496xpKRHs7bJWeCZb7f3utD__aCuWjk1jEGBVm3zMIQ");
        System.out.println(res);*/
    }
}
