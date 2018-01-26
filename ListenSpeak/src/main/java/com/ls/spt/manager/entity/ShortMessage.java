package com.ls.spt.manager.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.alibaba.fastjson.JSON;
import com.ls.spt.utils.msg.BaseDictionary;
import com.ls.spt.utils.msg.MsgConfig;
import com.ls.spt.utils.msg.Result;
import com.ls.spt.utils.msg.SmsMd5;
import com.ls.spt.utils.msg.WebUtilX;


public class ShortMessage {
	
	private String schoolId;
	
	private String dept;
	
	private String memo;
	
	public ShortMessage(){
		schoolId = "1009";
		dept= "信息管理部";
		memo = "Register";
	}
	
	/**
	 * @return 学校ID
	 */
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return 部门
	 */
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return 模块
	 */
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Map<String,Object> sendMessage(String phone,String txt){
		Map<String,Object> map = new HashMap<String, Object>();
		//当前时间格式 yyyy-MM-dd HH:mm:ss，注意一位数前面补零，例如：2014-01-08 08:09:59
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime = new java.util.Date();//得到当前系统时间
		String time = formatter.format(currentTime); //将日期时间格式化
		String msg = txt+time;
		String apiUrl = MsgConfig.ApiRootUrl + "apis/ApiV5.ashx";
		String method = "SendSmsV6"; //方法名称，固定值
		String appId = MsgConfig.appId; //本应用 通行证appId，写在配置文件中，测试环境appId：101
		System.out.println(MsgConfig.appKey+"--------------------");
		String appKey = MsgConfig.appKey.substring(0,5) +"@#$_v";  //本应用 通行证appKey，写在配置文件中，进行混淆处理，测试环境（未混淆前）：test_api_AppKey	
		String state =  UUID.randomUUID().toString();			
		//验签
		String signText = (method + appId + phone + schoolId + state + time + appKey).toLowerCase();
		String sign = SmsMd5.md5(signText); //签名           			
		BaseDictionary<String, String> dic = new BaseDictionary<String, String>();
		dic.Add("method", method);
		dic.Add("appid", appId);
		dic.Add("mobile", phone);
		dic.Add("msg", msg);
		dic.Add("schoolId", schoolId);
		dic.Add("dept", dept);
		dic.Add("memo", memo);
		dic.Add("state", state);
		dic.Add("time", time);
		dic.Add("sign", sign);		
		String josnresult = null; 
		try {
			josnresult = WebUtilX.DoPost(apiUrl, dic);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println("还回的JSON结果："+josnresult+"<br>");		
		//解析返回值
	    Result result=  JSON.parseObject(josnresult,Result.class);
	    map.put("status", result.Status);
	    map.put("data", result.Data);
	    map.put("Message", result.Message);	    
	    return map;
	}

}
