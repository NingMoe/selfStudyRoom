package com.wechat.utils.weixinapi.webpage;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ls.spt.utils.HttpClientUtil;
import com.wechat.utils.weixinapi.webpage.entity.WechatOpenid;
import com.wechat.utils.weixinapi.webpage.entity.WechatUserinfo;

@Component
public class WxWebUtil {
    
    @Value("${wechat.appId}")
    private String appId;
    
    @Value("${wechat.appSecret}")
    private String appSecret;
    
    @Value("${redis.wx_access_token}")
    private String wx_access_token;
    
    @Value("${redis.wx_jsapi_ticket}")
    private String wx_jsapi_ticket;
    
    private final Logger log = LogManager.getLogger(WxWebUtil.class);
    
    /**
     * 第一步：获取code
     * @param redirect_url
     * @param is_silence 是否静默授权 ， true : 是 、false : 否 ，默认true 使用静默授权
     * @return
     */
    public String getCodeUrl(String redirect_url, Boolean is_silence){
        String result = redirect_url;
        try {
            redirect_url = java.net.URLEncoder.encode(redirect_url,"UTF-8");
            String scope = "snsapi_base";
            if(is_silence != null && !is_silence){
                scope = "snsapi_userinfo";
            }
            result="redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + 
                    "&redirect_uri="+redirect_url+"&response_type=code&scope="+scope+"&state=1";
            log.info("重定向地址:"+result);
        }catch (UnsupportedEncodingException e) {
            log.error("对url:"+redirect_url+"编码异常");
            e.printStackTrace();
        }
        return result;
    } 
    
    /**
     * 第二步：通过CODE向微信开放平台请求网页授权access_token
     * @param code
     * @return
     */
    public WechatOpenid getAccessToken(String code){
        WechatOpenid wco = null;
        //请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String resultJson = "";
        try {
            resultJson = HttpClientUtil.httpGetRequest(url,null,params);
            if(StringUtils.isNotEmpty(resultJson)){
                wco = new WechatOpenid();
                JSONObject jo = new JSONObject(resultJson);
                if(jo.has("access_token")){
                    wco.setAccess_token(jo.getString("access_token"));
                }
                if(jo.has("expires_in")){
                    wco.setExpires_in(jo.getInt("expires_in"));
                }
                if(jo.has("refresh_token")){
                    wco.setRefresh_token(jo.getString("refresh_token"));
                }
                if(jo.has("openid")){
                    wco.setOpenid(jo.getString("openid"));
                }
                if(jo.has("scope")){
                    wco.setScope(jo.getString("scope"));
                }
                if(jo.has("errcode")){
                    wco.setErrcode(jo.getInt("errcode"));
                }
                if(jo.has("errmsg")){
                    wco.setErrmsg(jo.getString("errmsg"));
                }
            }
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return wco;
    }
    
    /**
     * 第三步：获取网页授权access_token超时，使用refresh_token重新获取
     * @param freshToken
     * @return
     */
    public WechatOpenid refreshAccessToken(String refresh_token){
        WechatOpenid wco = null;
        //请求access_token的链接
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        Map<String,Object> params = new HashMap<String,Object>();  
        params.put("appid", appId);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refresh_token);
        String resultJson;
        try {
            resultJson = HttpClientUtil.httpGetRequest(url,null,params);
            if(StringUtils.isNotEmpty(resultJson)){
                wco = new WechatOpenid();
                JSONObject jo = new JSONObject(resultJson);
                if(jo.has("access_token")){
                    wco.setAccess_token(jo.getString("access_token"));
                }
                if(jo.has("expires_in")){
                    wco.setExpires_in(jo.getInt("expires_in"));
                }
                if(jo.has("refresh_token")){
                    wco.setRefresh_token(jo.getString("refresh_token"));
                }
                if(jo.has("openid")){
                    wco.setOpenid(jo.getString("openid"));
                }
                if(jo.has("scope")){
                    wco.setScope(jo.getString("scope"));
                }
                if(jo.has("errcode")){
                    wco.setErrcode(jo.getInt("errcode"));
                }
                if(jo.has("errmsg")){
                    wco.setErrmsg(jo.getString("errmsg"));
                }
            }
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return wco;
    }
    
    /**
     * 第四步：获取用户信息
     * @param access_token 网页授权的access_token,通过code获取
     * @param openid
     * @return
     */
    public WechatUserinfo getUnionID(String access_token, String openid){
        WechatUserinfo wu = new WechatUserinfo();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        Map<String,Object> params = new HashMap<String,Object>();  
        params.put("access_token", access_token);
        params.put("openid", openid);
        String resultJson;
        try {
            resultJson = HttpClientUtil.httpGetRequest(url,null,params);
            if(StringUtils.isNotEmpty(resultJson)){
                wu = new WechatUserinfo();
                JSONObject jo = new JSONObject(resultJson);
                if(jo.has("openid")){
                    wu.setOpenid(jo.getString("openid"));
                }
                if(jo.has("nickname")){
                    wu.setNickname(jo.getString("nickname"));
                }
                if(jo.has("sex")){
                    wu.setSex(jo.getString("sex"));
                }
                if(jo.has("province")){
                    wu.setProvince(jo.getString("province"));
                }
                if(jo.has("city")){
                    wu.setCity(jo.getString("city"));
                }
                if(jo.has("country")){
                    wu.setCountry(jo.getString("country"));
                }
                if(jo.has("headimgurl")){
                    wu.setHeadimgurl(jo.getString("headimgurl"));
                }
                if(jo.has("privilege")){
                    JSONArray ja = jo.getJSONArray("privilege");
                    if(ja != null && ja.length() > 0){
                        List<String> privilege = new ArrayList<String>();
                        for(int i = 0; i < ja.length(); i++){
                            privilege.add(ja.getString(i));
                        }
                        wu.setPrivilege(privilege);
                    }
                }
                if(jo.has("unionid")){
                    wu.setUnionid(jo.getString("unionid"));
                }
                if(jo.has("errcode")){
                    wu.setErrcode(jo.getInt("errcode"));
                }
                if(jo.has("errmsg")){
                    wu.setErrmsg(jo.getString("errmsg"));
                }
            }
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return wu;
    }

}
