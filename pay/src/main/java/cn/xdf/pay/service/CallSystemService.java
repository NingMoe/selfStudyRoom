package cn.xdf.pay.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xdf.pay.annotation.ReadDataSource;
import cn.xdf.pay.constant.CommonStatus;
import cn.xdf.pay.dao.CallSystemDao;
import cn.xdf.pay.domain.CallSystem;
import cn.xdf.pay.util.SpringContextUtil;

@Service
public class CallSystemService {
	
	private static Logger logger = LoggerFactory.getLogger(CallSystemService.class);
	
	@Autowired
	private CallSystemDao callSystemDao;
	
	private CallSystemService getService(){		
		return SpringContextUtil.getBean(this.getClass());
	}
	
	/**
	 * 查询调用系统
	 * @param callSystem
	 * @return
	 */
	@ReadDataSource
	public CallSystem selectByCallSystem(CallSystem callSystem){
		logger.info("------查询调用系统------");
		return this.callSystemDao.selectByCallSystem(callSystem);
	}
	
	
	/**
	 * 校验调用系统权限
	 * @param appId
	 * @param appKey
	 * @return
	 */
	public Map<String,Object> checkCallSystem(String appId,String appKey){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(appId)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "调用系统appId参数必传！");
                return map;
			}
			if(StringUtils.isEmpty(appKey)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "调用系统appKey参数必传！");
                return map;
			}
			CallSystem callSystem=new CallSystem();
			callSystem.setAppId(appId);
			callSystem.setAppKey(appKey);
			callSystem=getService().selectByCallSystem(callSystem);
			if(callSystem==null){
				map.put("flag", false);
				map.put("code", CommonStatus.UNAUTHORIZED);
				map.put("message", "调用系统不存在！");
                return map;
			}
			map.put("flag", true);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("--------校验调用系统权限异常----------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;
	}
}
