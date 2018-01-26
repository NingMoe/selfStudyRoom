package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.jzbTest.entity.SchoolSbq;
import com.human.jzbTest.entity.SchoolXbgx;
import com.human.jzbTest.service.XbChkqService;
import com.human.utils.HttpClientUtil;
import com.human.utils.RedisTemplateUtil;

@Service
public class XbChkqServiceImpl implements XbChkqService{
	
	private final  Logger logger = LogManager.getLogger(XbChkqServiceImpl.class);
		
	@Resource
	private RedisTemplateUtil redisTemplateUtil;
	
	private final Lock lock = new ReentrantLock(); 
	
	@Value("${rb.client_id}") 
	private String client_id;
	
	@Value("${rb.client_secret}") 
	private String client_secret;
	
	@Value("${rb.username}") 
	private String username;
	
	@Value("${rb.password}") 
	private String password;
	
	@Value("${rb.tokenUrl}") 
	private String tokenUrl;
	
	@Value("${rb.queryUrl}") 
	private String queryUrl;
	
	@Value("${rb.addUrl}") 
	private String addUrl;
	
	@Value("${rb.delUrl}") 
	private String delUrl;
	
	
	@SuppressWarnings("rawtypes")
    @Override
	public String getToken() {
		    logger.info("调用获取TOKEN接口START-----");
	        if(redisTemplateUtil.isExist("SBTOKEN")){
	            return (String) redisTemplateUtil.get("SBTOKEN");
	        }
	        String access_token = "";
	        Map<String,Object> params = new HashMap<String,Object>();
	        params.put("grant_type","password");
	        params.put("client_id",client_id);
	        params.put("client_secret",client_secret);
	        params.put("username",username);
	        params.put("password",password);
	        try {
	            lock.lock();
	            String res = HttpClientUtil.httpPostRequest(tokenUrl,null, params);
	            Map map = (Map) JSON.parse(res);
	            access_token = map.get("access_token").toString();
	            redisTemplateUtil.set("SBTOKEN", access_token, Long.valueOf(29*60));
	            logger.info("调用获取TOKEN接口END-----");
	            return access_token;
	        }catch(Exception e){
	            e.printStackTrace();
	            return "";
	        }finally {
	            lock.unlock();
            }        
	}
	
	@SuppressWarnings("rawtypes")
    @Override
	public List<SchoolSbq> getSbqList(String schoolId) {
		String token = getToken();
		if(StringUtils.isEmpty(token)){
			return null;
		}
		String res ="";
		try {
			res = HttpClientUtil.httpPostJson(queryUrl,schoolId,token);
			if(StringUtils.isNotEmpty(res)){
			    Map map = (Map) JSON.parse(res);
	            String State = map.get("State").toString();
	            if("1".equals(State)){
	                String sbqs = map.get("Data").toString();
	                List<SchoolSbq> list = JSON.parseArray(sbqs, SchoolSbq.class);
	                return list;
	            } 
			}			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
    @Override
	public Map<String, Object> addXbgx(List<SchoolXbgx> xbgxs) {
		Map<String,Object> result = new HashMap<String,Object>();
		String token = getToken();
		if(StringUtils.isEmpty(token)){
			result.put("flag", false);
			result.put("message", "获取TOKEN失败");
			return result;
		}
		try {
		    for(SchoolXbgx sx:xbgxs){
		        List<SchoolXbgx> tmp = new ArrayList<SchoolXbgx>();
		        tmp.add(sx);
		        String json = JSON.toJSONString(tmp);
		        String res = HttpClientUtil.httpPostJson(addUrl,json, token);
		        if(StringUtils.isNotEmpty(res)){
	                Map map = (Map) JSON.parse(res);
	                String success = map.get("success").toString();
	                if(!"true".equals(success)){
	                    result.put("flag", false);
	                    result.put("message", "插入失败");
	                    return result;
	                } 
	            }           
		    }
		    result.put("flag", true);
	        result.put("message", "插入成功");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("message", "插入失败");
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
    @Override
	public Map<String, Object> delXbgx(List<SchoolXbgx> xbgxs) {
		Map<String,Object> result = new HashMap<String,Object>();
		String token = getToken();
		if(StringUtils.isEmpty(token)){
			result.put("flag", false);
			result.put("message", "获取TOKEN失败");
			return result;
		}
		try {
			String json = JSON.toJSONString(xbgxs);
			String res = HttpClientUtil.httpPostJson(delUrl,json, token);
			if(StringUtils.isNotEmpty(res)){
			    Map map = (Map) JSON.parse(res);
	            String success = map.get("success").toString();
	            if("true".equals(success)){
	                result.put("flag", true);
	                result.put("message", "删除成功");
	            }else{
	                result.put("flag", false);
	                result.put("message", "删除失败");
	            }  
			}			
		}catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("message", "删除失败");
		}
		return result;
	}
	
	
}
