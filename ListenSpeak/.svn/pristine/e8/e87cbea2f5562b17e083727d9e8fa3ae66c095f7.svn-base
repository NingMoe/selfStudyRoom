package com.ls.spt.utils.XdfRequestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *新东方-网上报名系统调用其接口-生成签名
 * @author niuchong
 * @date 2015-05-06 9:40
 */
public class XdfGeneralRequestSignature {
	//引用日志
	final static Logger log = LoggerFactory.getLogger(XdfGeneralRequestSignature.class);
	
	//双方约定的值
	 private static String appId_value = "jfienfodxplxsutjhvdh298kinvbxop0";
	
	 /**
	  * 返回新东方快速报班系统-约定的签名
	  * @param map
	  * @return
	  */
	public static String generalRequestSignature(Map<String, Object> map){
		String signature = "";
		if (map == null){
			return signature;
		}
		try {
			List<String> keys = new ArrayList<String>(map.keySet());
	        Collections.sort(keys);
	        String prestr = "";
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i).trim();
	            String value = (String) map.get(key);
	            //if (value != null){//值为空，不拼接
	            	value = value.trim();
	            	 if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	 	                prestr = prestr + key + "=" + value;
	 	            } else {
	 	                prestr = prestr + key + "=" + value + "&";
	 	            }
	            //}  
	        }//循环结束
	        signature = PasswordUtil.getMD5(prestr+ PasswordUtil.getMD5(appId_value));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("生成快速报班系统需要的签名错误："  + e);
		}
		return signature;
	}
}
