package com.ls.spt.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * 获取json工具类
 * @author hfxdfzx 2016-09-06
 *
 */
public class JsonUtil {
	
	
	/***
	 * 通过url和参数获取JSON数据
	 * @param url 接口对应的url
	 * @param namelist 传参集合
	 * @return 返回获取的JSONObject
	 */
	public JSONObject getJson(String url,List<NameValuePair> namelist){
		JSONObject json = null;
		//开始
		HttpClient htc = new DefaultHttpClient();
		HttpGet get = new HttpGet(url+URLEncodedUtils.format(namelist, HTTP.UTF_8));
//		HttpPost post = new HttpPost(url+URLEncodedUtils.format(namelist, HTTP.UTF_8));
		try {
			HttpResponse res = htc.execute(get);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				json = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), HTTP.UTF_8)));
				System.out.println("获取json成功！:"+json);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(1);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(2);
			return null;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(3);
			return null;
		}finally{
			htc.getConnectionManager().shutdown();
		}
		return json;
	}
	
	/**
	 * 获取jsonArray
	 * @param url 请求url
	 * @param namelist 请求参数
	 * @return
	 */
	public JSONArray getJsonArray(String url,List<NameValuePair> namelist){
		JSONArray jsonArray =null;
		//开始
		HttpClient htc = new DefaultHttpClient();
		HttpGet get = new HttpGet(url+URLEncodedUtils.format(namelist, HTTP.UTF_8));
		
		try {
			HttpResponse res = htc.execute(get);
			if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = res.getEntity();
				jsonArray = new JSONArray(new JSONTokener(new InputStreamReader(entity.getContent(), HTTP.UTF_8)));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(1);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(2);
			return null;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(3);
			return null;
		}finally{
			htc.getConnectionManager().shutdown();
		}
		return jsonArray;
	}

}
