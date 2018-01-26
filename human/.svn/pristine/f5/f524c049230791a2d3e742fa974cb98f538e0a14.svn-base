package com.human.utils.wxutils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.human.utils.HttpClientUtil;
import com.human.utils.Md5Tool;

public class WxUtil {
    private static final String appid = "wx762fd407faa3be61";
    
    private static final String secret = "34ea4a0fa63044e49d973058c9a5e694";
    
    //获得随机数
    public static final String generateRandomString(int length){
        String allChar = "";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for(int i=0;i<allChar.length();i++){
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }
    
    //通过CODE向微信开放平台请求access_token
    public static Map<String,Object> getAccessToken(String code){
        //请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String params = "appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        
        String rsultJson = sendGet(url,params);
        Map<String,Object> map = parseData(rsultJson);
        
        return map;
    }
    
    //获取access_token超时，使用refresh_token重新获取
    public static Map<String,Object> refreshAccessToken(String freshToken){
        //请求access_token的链接
        String url_access = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        StringBuffer params_access=new StringBuffer()
                .append("appid=").append(appid)
                .append("&grant_type=refresh_token")
                .append("&refresh_token=").append(freshToken);
        String resultJson=sendGet(url_access,params_access.toString());
        Map<String,Object> map = parseData(resultJson);//将返回的json数据转换为Map结构存储
        return map;
    }
    
    //JSON串解析成MAP
    public static Map<String,Object> parseData(String data){
        Map<String, Object> params = JSONObject.parseObject(data, 
                new TypeReference<Map<String, Object>>(){});
        return params;
    }
    
    
    public static String sendGet(String url,String param){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url+"?"+param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            //建立实际的连接
            connection.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }catch(Exception e){
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        getAccessToken("051ga4TK1XUwH70mEHSK1ZARSK1ga4TS");
    }
}
