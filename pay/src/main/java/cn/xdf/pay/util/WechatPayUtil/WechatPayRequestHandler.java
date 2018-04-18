package cn.xdf.pay.util.WechatPayUtil;


import java.io.IOException;
import java.io.InputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import cn.xdf.pay.util.CommonUtil;
import cn.xdf.pay.util.MD5Util;
import cn.xdf.pay.util.Sha1Util;
import cn.xdf.pay.util.XMLUtil;



/**
 * 微信支付请求适配器处理类
 * @author liuwei63
 */
@SuppressWarnings({ "deprecation", "unused" })
public class WechatPayRequestHandler extends RequestHandler {

	private static Logger logger = LoggerFactory.getLogger(WechatPayRequestHandler.class);
	
	public WechatPayRequestHandler(HttpServletRequest request,HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * 创建签名SHA1
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
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
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String createMd5Sign() {				
		StringBuffer sb = new StringBuffer();
		Set es = super.getAllParameters().entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());		
		String enc = CommonUtil.getCharacterEncoding(this.request, this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toUpperCase();				
		//debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
		return sign;
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
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"+ requestUrl);
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setReqContent(requestUrl);
		String resContent = "";
		this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
		if (httpClient.callHttpPost(requestUrl, params)) {
			resContent = httpClient.getResContent();
			if (!"".equals(resContent)){
				backXml=resContent;
			}else{
				logger.info("------获取微信返回XML值错误------");
			}
			this.setDebugInfo(this.getDebugInfo() + "\r\n" + "resContent:"+ resContent);
		}
		return backXml;
	}	
	
	/**
	 * 拼接微信接口请求XML参数公用方法
	 * @return
	 * @throws JSONException
	 */	
	@SuppressWarnings("rawtypes")
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
	 * 调用微信申请退款接口发获取返回Map
	 * @param path
	 * @return
	 */	
	@SuppressWarnings("rawtypes")
	public Map sendRefundAndGetMap(String path,String mchId){
		Map retMap = new HashMap();
		InputStream stream=null;		
		CloseableHttpClient httpclient =null;
		CloseableHttpResponse responseEntry=null;
		try{
			String params=getParamsXml();
			String requestUrl = super.getGateUrl();	
			//指定读取证书格式为PKCS12
			KeyStore keyStore= KeyStore.getInstance("PKCS12");
			//读取本机存放的PKCS12证书文件
			String filePath="classpath:/static/"+path;
			Resource resource=new DefaultResourceLoader().getResource(filePath);
			stream=resource.getInputStream();
			//指定PKCS12的密码(商户ID)
			keyStore.load(stream, mchId.toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();   
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	        HttpPost httppost = new HttpPost(requestUrl);
			StringEntity se = new StringEntity(params);              
            httppost.setEntity(se);  
            logger.info("executing request" + httppost.getRequestLine());
            responseEntry = httpclient.execute(httppost);
            HttpEntity entity = responseEntry.getEntity();   
            if (entity != null){
            	logger.info("Response content length:"+ entity.getContentLength());
                retMap=XMLUtil.parseInputStreamToMap(entity.getContent());
            } 
		}catch(Exception e){
			e.printStackTrace();
			logger.error("调用微信申请退款接口异常", e.getMessage());			
		}finally{
			try {
				if(stream!=null){
					stream.close();
				}
				if(responseEntry!=null){
					responseEntry.close();
				}
				if(httpclient!=null){
					httpclient.close();
				}				
			}catch (IOException e) {				
				e.printStackTrace();
				logger.error("关闭流异常", e.getMessage());
			}
		}
		return retMap;
	}
	
	
	
	
	
	
}
