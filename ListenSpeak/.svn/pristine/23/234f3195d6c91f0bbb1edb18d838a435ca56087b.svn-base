package com.wechat.utils.weixinapi.jsapi;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ls.spt.utils.HttpClientUtil;
import com.ls.spt.utils.RedisTemplateUtil;

@Component
public class WxJsapiUtil {
    
    @Value("${wechat.appId}")
    private String appId;
    
    @Value("${wechat.appSecret}")
    private String appSecret;
    
    @Value("${redis.wx_access_token}")
    private String wx_access_token;
    
    @Value("${redis.wx_jsapi_ticket}")
    private String wx_jsapi_ticket;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    private final Logger log = LogManager.getLogger(WxJsapiUtil.class);
    
    /**
     * 获取微信基础授权access_token(非网页授权)
     * @return
     */
    public String getToken(){
        String access_token = "";
        Long expires_in = 7200L;
        if(redisTemplateUtil.isExist(wx_access_token)){
            log.info("redis获取token");
            access_token =  redisTemplateUtil.getObject(wx_access_token, String.class);
        }else{
            log.info("redis的token失效");
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("grant_type", "client_credential");
            params.put("appid", appId);
            params.put("secret", appSecret);
            String result = "";
            try {
                result = HttpClientUtil.httpGetRequest(url,null,params);
                System.out.println("获取微信access_token接口返回："+result);
                if(StringUtils.isEmpty(result)){
                    return access_token;
                }
                
                JSONObject jo = new JSONObject(result);
                if(jo.has("access_token")){
                    access_token = jo.getString("access_token");
                    expires_in = (long) jo.getInt("expires_in");
                    redisTemplateUtil.set(wx_access_token, access_token, expires_in - 300L);
                }
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }
            
        }
        log.info("获取token结果为："+access_token);
        return access_token;
    }
    
    /**
     * 微信js-api获取ticket
     * @return
     */
    public String getJsapiTicket(){
        String jsapi_ticket = "";
        Long expires_in = 7200L;
        
        if(redisTemplateUtil.isExist(wx_jsapi_ticket)){
            log.info("redis获取jsapi_ticket");
            jsapi_ticket =  redisTemplateUtil.getObject(wx_jsapi_ticket, String.class);
        }else{
            log.info("redis的jsapi_ticket失效");
            String access_token = getToken();
            if(StringUtils.isEmpty(access_token)){
                return jsapi_ticket;
            }
            
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
            Map<String,Object> params = new HashMap<String,Object>();  
            params.put("access_token", access_token);
            params.put("type", "jsapi");
            String result = "";
            try {
                result = HttpClientUtil.httpGetRequest(url,null,params);
                System.out.println("获取微信jsapi_ticket接口返回："+result);
                if(StringUtils.isEmpty(result)){
                    return jsapi_ticket;
                }
                
                JSONObject jo = new JSONObject(result);
                if(jo.has("errmsg") && "ok".equals(jo.getString("errmsg"))){
                    jsapi_ticket = jo.getString("ticket");
                    expires_in = (long) jo.getInt("expires_in");
                    redisTemplateUtil.set(wx_jsapi_ticket, jsapi_ticket, expires_in - 300L);
                }
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        log.info("获取jsapi_ticket结果为："+jsapi_ticket);
        return jsapi_ticket;
    }
    
    
    //获取验签
    public Map<String, Object> sign(String jsapi_ticket, String url) {
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            log.info("获取验签："+signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
