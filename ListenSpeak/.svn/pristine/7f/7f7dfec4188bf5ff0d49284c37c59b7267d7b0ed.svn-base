package com.ls.spt.utils.wxutils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class WxUtil {
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
    public static Map<String,String> getAccessToken(String code,String appid,String secret ){
        //请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        String rsultJson = sendGet(url);
        Map<String,String> map = parseData(rsultJson);
        return map;
    }
    
    //获取access_token超时，使用refresh_token重新获取
    public static Map<String,String> refreshAccessToken(String freshToken,String appid){
        //请求access_token的链接
        String url_access = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        StringBuffer params_access=new StringBuffer()
                .append("appid=").append(appid)
                .append("&grant_type=refresh_token")
                .append("&refresh_token=").append(freshToken);
        String url=url_access+"?"+params_access;
        String resultJson=sendGet(url);
        Map<String,String> map = parseData(resultJson);//将返回的json数据转换为Map结构存储
        return map;
    }
    
    
    //拉取用户信息，通过access_token和openId
    public static Map<String,String> getUserInfo(String accToken,String openId){
        //请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accToken+"&openid="+openId+"&lang=zh_CN";
        String resultJson=sendGet(url);
        Map<String,String> map = parseData(resultJson);//将返回的json数据转换为Map结构存储
        return map;
    }
    
    //JSON串解析成MAP
    public static Map<String,String> parseData(String data){
        Map<String, String> params = JSONObject.parseObject(data, 
                new TypeReference<Map<String, String>>(){});
        return params;
    }
    
    
    public static String sendGet(String url){
        String result = "";
        BufferedReader in = null;
        try{
            String urlNameString = url;
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
    
}
