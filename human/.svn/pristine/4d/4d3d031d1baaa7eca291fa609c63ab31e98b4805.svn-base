package com.human.utils.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: XdfBusinessInterfaceGeneralRequestSignature
* @version:V1.0 
* @company:isoftstone
* @Description: 新东方业务接口验签方法
* @author: lyy 
* @date 2017年7月28日 上午10:40:25
*
 */
@Component
public class XdfBusinessInterfaceGeneralRequestSignature {
    
    @Value("${signatureKey}")
    private  String signatureKey;
	//引用日志
	final static Logger log = LoggerFactory.getLogger(XdfBusinessInterfaceGeneralRequestSignature.class);
	 /**
	  * 返回Api系统-约定的签名
	  * @param map
	  * @return
	  */
	private   String generalRequestSignature(Map<String, Object> map){
		String signature = "";
		String Key = "";
		if (map == null){
			return signature;
		}
		try {
			
			if (Key.equals("")){
				Key = signatureKey;
			}
			
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
	        log.info("验签参数：" + prestr);
	        log.info("最终参数：" + prestr + PasswordUtil.getMD5(Key));
	        System.out.println("最终参数：" + prestr + PasswordUtil.getMD5(Key));
	        signature = PasswordUtil.getMD5(prestr+PasswordUtil.getMD5(Key));
	        log.info("标签：" + signature);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("生成Api系统需要的签名错误："  + e);
		}
		return signature;
	}
	
	 /**
     * 
     * @Title: 通过学员号获取上课记录
     * @Description: 微信项目老带新接口for hefei 
     * @author: lyy
     * @date:   2017年9月12日 下午4:14:31   
     * @company:isoftstone  
     * @param: @param request
     * @param: @return      
     * @return: String      
     * @throws
     */
    public  List<WechatLdxStudentClass> getByStuCodex(String studentCodeList, String schoolId, String startDate, String endDate)throws Exception {
        log.info("网报通过学员号获取上课记录");
        List<WechatLdxStudentClass> list = new ArrayList<WechatLdxStudentClass>();
        try {
            for(String studentCode: studentCodeList.split(",")){
                //空值判断
                if(StringUtils.isEmpty(studentCode) || StringUtils.isEmpty(schoolId) || StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)){
                    log.error("参数为空");
                    return null;
                }
                
                String sendUrl = "http://apibm.staff.xdf.cn/api/NisOrderApi/GetByStuCode";//GetByStuCode
                //生成验签
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("schoolId", schoolId);
                map.put("studentCode", studentCode);
                map.put("startDate", startDate);
                map.put("endDate", endDate);
                String signature = generalRequestSignature(map);
                if (StringUtils.isBlank(signature)){
                    log.error("生成验签为空");
                    return null;
                }
                
                String url = sendUrl + "?schoolId=" + schoolId + "&studentCode=" + studentCode + "&startDate=" + startDate +  "&endDate=" + endDate +"&signature=" + signature;
                String result = httpGet(url);//数据请求
                JSONArray jsonArray = new JSONObject(result).getJSONArray("Data");
                if(jsonArray != null && jsonArray.length() > 0){
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jo = jsonArray.getJSONObject(i);
                        if(jo != null){
                            WechatLdxStudentClass c = new WechatLdxStudentClass();
                            c.setClassCode(getJSONObjectString(jo,"ClassCode"));
                            c.setClassName(getJSONObjectString(jo,"ClassName"));
                            String BeginDate =getJSONObjectString(jo,"BeginDate"); 
                            String EndDate = getJSONObjectString(jo,"EndDate");
                            c.setBeginDate(StringUtils.isEmpty(BeginDate) ? null : BeginDate.substring(0, 10));
                            c.setEndDate(StringUtils.isEmpty(EndDate) ? null : EndDate.substring(0, 10));
                            c.setSectBegin(getJSONObjectString(jo,"SectBegin"));
                            
                            c.setFee(getJSONObjectInteger(jo,"Fee"));
                            c.setOutType(getJSONObjectInteger(jo,"OutType"));
                            c.setManagerDptName(getJSONObjectString(jo,"ManagerDptName"));
                            c.setDptName(getJSONObjectString(jo,"DptName"));
                            c.setProjectName(getJSONObjectString(jo,"ProjectName"));
                            c.setCardCode(getJSONObjectString(jo,"CardCode"));
                            c.setAreaAddress(getJSONObjectString(jo,"AreaAddress"));
                            c.setPrintAddress(getJSONObjectString(jo,"PrintAddress"));
                            c.setChannel(getJSONObjectInteger(jo,"Channel"));
                            c.setStudentName(getJSONObjectString(jo,"StudentName"));
                            list.add(c);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("网报学员号获取上课记录接口时出错，原因是：" + e);
        }
        return list;
    }
    
    
    //获取jsonobject中jsonname的返回值
    public static String getJSONObjectString(JSONObject jo, String jsonname){
        String s = "";
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getString(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    //获取jsonobject中jsonname的返回值
    public static Integer getJSONObjectInteger(JSONObject jo, String jsonname){
        Integer s = null;
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getInt(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    /**
     * 
    * @Title: getClassWindowsConfiginfo
    * @Author: lyy
    * @Description: httpClient请求方法
    * @param @param url
    * @param @return
    * @param @throws Exception    设定文件
    * @return String    返回类型
    * @throws
     */ 
    public String httpGet(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        System.out.println("-------url----------" + url);
        HttpResponse response = httpClient.execute(request);
        String content = "";
        HttpEntity entity = response.getEntity();
        content = EntityUtils.toString(entity);
        return content;
    }

	
}
