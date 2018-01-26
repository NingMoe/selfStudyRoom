package com.human.utils.XdfRequestUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import net.sf.json.JSONObject;
/**
 * 调用新东方网上报名系统接口-请求操作
 * @author niuchong
 * @date 2015-05-07 9:30
 */
public class XdfPostDataOperate {

	//引入日志
	final static Logger log = LoggerFactory.getLogger(XdfPostDataOperate.class);
	
	/**
	 * 根据新东方网上报名系统接口-返回相应的数据
	 * @param map
	 * @return
	 */
	/*public static String sendPostDataToWechatUser(Map<String, Object> map,String sendUrl){
		//JSONObject jsonObject = null;
		String sb = "";
		//传入参数判断
		if (map == null || map.isEmpty()){
			return sb;
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
		//从属性文件中获取微信token的Url
		//String sendUrl = XdfUrlPropertyUtil.getPropertyeString("GetClassList");
		//拼凑微信接收的最终地址
		//sendUrl = sendUrl;
		//声明待发起get请求
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStreamWriter out = null;
		try {
			URL url = new URL(sendUrl);
			conn = (HttpURLConnection) url.openConnection();//打开连接
			//设置是否向httpUrlConnection输出,设为真
			//conn.setDoOutput(true);
			//设定请求的方法为"GET"，默认是GET
			conn.setRequestMethod("POST");
			//设置是否从httpUrlConnection读入，设置为true; 
			conn.setDoInput(true);
			//设置输出为true
			conn.setDoOutput(true);
			//Post 请求不能使用缓存
			conn.setUseCaches(false);
			//设置连接超时时间(单位：毫秒),超时设置，防止 网络异常的情况下，可能会导致程序僵死而不继续往下执行
			conn.setConnectTimeout(30000);
			//设置读取超时时间(单位：毫秒)
			conn.setReadTimeout(30000);
			//设置发送数据的格式
			conn.setRequestProperty("Content-Type", "application/json;encoding=UTF-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Lenth", String.valueOf(prestr.getBytes().length));
			//设置接收数据的格式
			conn.setRequestProperty("Accept", "application/json");
			//连接，从上述配置必须要在connect之前完成
			conn.connect();
			out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			JSONObject jsonObject2 = JSONObject.fromObject(map);
			//out.write(prestr);
			out.write(jsonObject2.toString());
			//刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
			out.flush();
			//关闭流对象。此时，不能再向对象输出流写入任何数据
			out.close();
			//获取响应的状态码
			int code = conn.getResponseCode();
			if(code != 200){
				log.error("网上报名系统接口服务器连接不上");
			}else {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				String s = "";
				while((s = br.readLine()) != null){
					sb = sb + s;
				}
			}
			//if(!sb.equals("")){
				//jsonObject = JSONObject.fromObject(sb);
			//}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("send data to wechat user is failure cause:" +e);
		}finally{
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				conn.disconnect();
			}
			
		}
		return sb;
		
	}*/
	
	
	public static String sendPostDataToWechatUser(Map<String, Object> map,String sendUrl){
		//JSONObject jsonObject = null;
		String sb = "";
		//传入参数判断
		if (map == null || map.isEmpty()){
			return sb;
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
        DefaultHttpClient httpClient = null;
		try {
			httpClient = new DefaultHttpClient();
			//创建httppost，目标地址
			HttpPost httpPost = new HttpPost(sendUrl);
			//发送方法
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			//设置参数
			HttpParams params = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 30000);
			HttpConnectionParams.setSoTimeout(params, 30000);
			//设置默认请求一次
			DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(0, false);
			httpClient.setHttpRequestRetryHandler(dhr);
			//post参数设置
			StringEntity s = new StringEntity(prestr, "UTF-8");
			httpPost.setEntity(s);
			//log.info("请求：" + System.currentTimeMillis());
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null){
					sb = EntityUtils.toString(entity, "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("调用快速报班接口获取响应发生错误：" + e);
			}finally{
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("调用快速报班接口发生错误" +e);
		}finally{
			try {
				if (httpClient != null){
					httpClient.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				log.error("调用快速报班接口关闭httpClient发生错误：" + e2);
			}
		}
		return sb;
		
	}
	
}
