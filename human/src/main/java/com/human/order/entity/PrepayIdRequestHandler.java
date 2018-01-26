package com.human.order.entity;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import com.human.order.utils.ConstantUtil;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.MD5Util;
import com.human.order.utils.Sha1Util;
import com.human.order.utils.TenpayHttpClient;
import com.human.order.utils.TenpayUtil;
import com.human.order.utils.XMLUtil;



public class PrepayIdRequestHandler extends RequestHandler {

	public PrepayIdRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建签名SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign() {
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		String appsign = Sha1Util.getSha1(params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "sha1 sb:" + params);
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "app sign:" + appsign);
		return appsign;
	}
	
	
	/**
	 * 创建签名MD5
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createMd5Sign() {				
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());
		
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toUpperCase();				
		//debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
		return sign;

	}

	// 判断access_token是否失效
	public String sendAccessToken() {
		String accesstoken = "";
		StringBuffer sb = new StringBuffer("{");
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"appkey".equals(k)) {
				sb.append("\"" + k + "\":\"" + v + "\",");
			}
		}
		String params = sb.substring(0, sb.lastIndexOf(","));
		params += "}";
		String requestUrl = super.getGateUrl();
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (2 == resContent.indexOf(ConstantUtil.ERRORCODE)) {
				accesstoken = resContent.substring(11, 16);//获取对应的errcode的值
			}
		}
		return accesstoken;
	}
	/**
	 * 调用微信接口发送请求获取返回XML通用方法
	 * @return
	 * @throws JSONException
	 */
	public String sendAndGetXml() throws JSONException {
		String backXml = "";
		String params=getParamsXml();
		String requestUrl = super.getGateUrl();
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
				+ requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (!"".equals(resContent)){
				backXml=resContent;
			}else{
				System.out.println("获取微信返回XML值错误！！！");
			}
			this.setDebugInfo(this.getDebugInfo() + "\r\n" + "resContent:"
					+ resContent);
		}
		return backXml;
	}	
	
	/**
	 * 拼接微信接口请求XML参数公用方法
	 * @return
	 * @throws JSONException
	 */
	
	public String getParamsXml() throws JSONException {
		String params="<xml>"+"\r\n";
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				sb.append("<" + k + ">" + v + "</" + k + ">" + "\r\n");
			}
		}		
		params += sb.toString();
		params += "</xml>";
		return params;
	}	
	
	/**
	 * 调用微信接口发送退款请求获取返回XML方法
	 * @return
	 * @throws JSONException
	 */
	@SuppressWarnings("deprecation")
	public Map sendRefundAndGetXml(){
		 Map retMap = new HashMap(); 
		 try{
			String params=getParamsXml();
			String requestUrl = super.getGateUrl();	
			//指定读取证书格式为PKCS12
			KeyStore keyStore= KeyStore.getInstance("PKCS12");
			//读取本机存放的PKCS12证书文件
			String fileTmp = request.getSession().getServletContext()
                    .getRealPath("/static/temp/apiclient_cert.p12");
			System.out.println("PKCS12证书文件==="+fileTmp);
			FileInputStream instream = new FileInputStream(new File(fileTmp));						
			try {  
				//指定PKCS12的密码(商户ID)
				keyStore.load(instream, ConstantUtil2.PARTNER.toCharArray());	 
            }finally {  
                instream.close();  
            }  
		   SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,  ConstantUtil2.PARTNER.toCharArray()).build();   
           SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                    sslcontext, new String[] { "TLSv1" }, null,  
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
           CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();			
			try {
				HttpPost httppost = new HttpPost(requestUrl);
				StringEntity se = new StringEntity(params);              
	            httppost.setEntity(se);  
	            System.out.println("executing request" + httppost.getRequestLine());  
	            CloseableHttpResponse responseEntry = httpclient.execute(httppost);
	            try{
	            	HttpEntity entity = responseEntry.getEntity();              
	                System.out.println("调用微信退款接口返回状态------------"+responseEntry.getStatusLine());  
	                if (entity != null){
	                  System.out.println("Response content length:"+ entity.getContentLength());
	                  retMap=XMLUtil.parseInputStreamToList2(entity.getContent());
	                }
	                EntityUtils.consume(entity);
	            }finally{
	            	responseEntry.close();
				}
			}finally{
				httpclient.close();
			}			            
		}catch (Exception e) {
			e.printStackTrace();
			retMap.put("status","error");  
			retMap.put("msg",e.getMessage()); 
		}
		return retMap;
	}	
	
	
	
	
	
	
	
}
