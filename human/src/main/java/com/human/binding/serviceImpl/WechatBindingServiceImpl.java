package com.human.binding.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.human.binding.dao.WechatTeacherBindingDao;
import com.human.binding.entity.WechatTeacherBinding;
import com.human.binding.service.WechatBindingService;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.utils.BindingConstants;
import com.human.utils.JsonUtil;
import com.human.utils.Md5Tool;

/**
 * 通用绑定
 * @author hfxdf-zx
 *
 */
@Service
public class WechatBindingServiceImpl implements WechatBindingService {
    
    private final static  Logger log = LogManager.getLogger(WechatBindingServiceImpl.class);
    
    @Value("${humanServer}")
    private String human_url;
    
    @Resource
    private UserService userService;
    
    @Resource
    private WechatTeacherBindingDao wechatTeacherBindingDao;

    /**
     * 获取微信跳转页面链接
     * @param request
     * @param response
     * @return
     */
    public String wxview(HttpServletRequest request, HttpServletResponse response) {
        log.info("进入微信跳转页面");
        String error500_uri = "/binding/500";
        String redirect_uri = "";
        String requestUrl = request.getParameter("requestUrl");
        log.info(requestUrl);
        try {
            redirect_uri = human_url + "wechat/binding/view.html?requestUrl="+requestUrl;
            redirect_uri = java.net.URLEncoder.encode(redirect_uri,"UTF-8");
        }catch (UnsupportedEncodingException e) {
            log.error("对url:"+redirect_uri+"编码异常");
            e.printStackTrace();
            return error500_uri;
        }
        String result="redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx762fd407faa3be61" + 
                "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=1";
        log.info("重定向地址:"+result);
        return result;
    }

    /**
     * 根据接收微信参数决定跳转页面
     * @param request
     * @param response
     * @return
     */
    public String view(HttpServletRequest request, HttpServletResponse response) {
        log.info("进入获取openid方法");
        String error404_uri = "/binding/404";
        String error500_uri = "/binding/500";
        String redirect_uri = "";
        String code =  request.getParameter("code");
        String requestUrl =  request.getParameter("requestUrl");
        log.info("code:"+code+",requestUrl:"+requestUrl);
        if(StringUtils.isEmpty(code)){
            log.error("获取code失败。");
            return error404_uri;
        }
        if(StringUtils.isEmpty(requestUrl)){
            log.info("直接进入绑定页面，绑定后重定向教师中心。");
            requestUrl = "wechat/binding/toTeacherCenter.html";
        }
        log.info("code:"+code);
        String getTeacherUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";// 获取班级信息接口地址
        JsonUtil jsonUtil = new JsonUtil();
        List<NameValuePair> namelist = new ArrayList<NameValuePair>();
        namelist.add(new BasicNameValuePair("appid", "wx762fd407faa3be61"));
        namelist.add(new BasicNameValuePair("secret", "34ea4a0fa63044e49d973058c9a5e694"));
        namelist.add(new BasicNameValuePair("code", code));
        namelist.add(new BasicNameValuePair("grant_type", "authorization_code"));
        JSONObject jsonObject = jsonUtil.getJson(getTeacherUrl, namelist);
        String openid = WechatBindingServiceImpl.getJSONObjectString(jsonObject, "openid");
        if(StringUtils.isEmpty(openid)){
            log.error("获取openid失败");
            return error500_uri;
        }else{
            log.info("获取openid成功：" + openid);
            try {
                WechatTeacherBinding wechatTeacherBinding = wechatTeacherBindingDao.selectByOpenid(openid);
                HttpSession session = request.getSession();
                session.setAttribute(BindingConstants.BINDING_OPENID, openid);
                session.setAttribute(BindingConstants.BINDING_RE_URL, requestUrl);
                if(wechatTeacherBinding == null){
                    log.info("用户未绑定跳转绑定页面");
                    redirect_uri = "/binding/teacherbinding";
                }else{
                    session.setAttribute(BindingConstants.BINDING_EMAIL_ADDR, wechatTeacherBinding.getEmail_addr());
                    Users u=new Users();
                    u.setLoginName(wechatTeacherBinding.getEmail_addr());
                    u.setStatus(0);
                    u.setHrStatus("A");
                    // 验证用户账号与密码是否正确
                    List<Users> users = userService.queryUser(u);
                    if (users != null && users.size() >=0) {
                        session.setAttribute(BindingConstants.BINDING_USERS, users.get(0));
                    }
                    if('/' == requestUrl.charAt(0)){
                        requestUrl = requestUrl.substring(1);
                    }
                    redirect_uri = "redirect:"+human_url+requestUrl;
                    log.info("用户已经绑定，重定向到下一个页面："+redirect_uri);
                }
            }catch (Exception e) {
                log.error("通过openid获取绑定信息异常。");
                e.printStackTrace();
                return error500_uri;
                
            }
        }
        log.info("获取openid成功，openid:");
        return redirect_uri;
    }

    /**
     * 通过账号密码绑定
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> insertbindinginfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String email_addr = request.getParameter("email_addr");
        String password = request.getParameter("password");
        String openid = request.getParameter("openid");
        if(StringUtils.isEmpty(email_addr)){
            map.put("flag", false);
            map.put("message", "邮箱前缀不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(password)){
            map.put("flag", false);
            map.put("message", "密码不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(openid)){
            map.put("flag", false);
            map.put("message", "openid不能为空");
            return map;
        }
        
        try {
            Users u=new Users();
            u.setLoginName(email_addr);
            u.setStatus(0);
            u.setHrStatus("A");
            u.setUserPassword(Md5Tool.getMd5(password));
            // 验证用户账号与密码是否正确
            List<Users> users = userService.queryUser(u);
            if (users.size() ==0) {
                map.put("flag", false);
                map.put("message", "用户名或密码不正确！");
                return map;
            }
            
            WechatTeacherBinding wechatTeacherBinding = wechatTeacherBindingDao.selectByOpenid(openid);
            if(wechatTeacherBinding == null){
                WechatTeacherBinding wechatTeacherBinding1 = new WechatTeacherBinding();
                wechatTeacherBinding1.setEmail_addr(email_addr);
                wechatTeacherBinding1.setOpenid(openid);
                wechatTeacherBindingDao.insertSelective(wechatTeacherBinding1);
            }else{
                WechatTeacherBinding wechatTeacherBinding1 = new WechatTeacherBinding();
                wechatTeacherBinding1.setId(wechatTeacherBinding.getId());
                wechatTeacherBinding1.setEmail_addr(email_addr);
                wechatTeacherBinding1.setOpenid(openid);
                wechatTeacherBindingDao.updateByPrimaryKeySelective(wechatTeacherBinding1);
            }
            HttpSession session = request.getSession();
            session.setAttribute(BindingConstants.BINDING_EMAIL_ADDR, email_addr);
            session.setAttribute(BindingConstants.BINDING_USERS, users.get(0));
            map.put("flag", true);
            map.put("message", "绑定成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "绑定异常："+e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解绑操作
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> deletebindinginfo(HttpServletRequest request, HttpServletResponse response) {
        log.info("解绑操作开始");
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String openid = (String) session.getAttribute(BindingConstants.BINDING_OPENID);
        if(StringUtils.isEmpty(openid)){
            map.put("flag", false);
            map.put("message", "session获取openid失败");
            return map;
        }
        log.info("参数openid:"+openid);
        try {
            wechatTeacherBindingDao.deleteByOpenid(openid);
            session.removeAttribute(BindingConstants.BINDING_USERS);
            session.removeAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            session.removeAttribute(BindingConstants.BINDING_OPENID);
            session.removeAttribute(BindingConstants.BINDING_RE_URL);
            map.put("flag", true);
            map.put("message", "解绑成功");
            log.info("解绑成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "解绑异常："+e);
            e.printStackTrace();
            log.error("解绑异常："+e);
        }
        log.info("解绑操作结束");
        return map;
    }

    //获取jsonobject中jsonname的返回值
    public static String getJSONObjectString(JSONObject jo, String jsonname){
        String s = "";
        if(jo == null){
            log.error("返回json为空");
            return s;
        }
        
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getString(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    @Override
    public WechatTeacherBinding selectUserByOpenid(String openid) {
        return wechatTeacherBindingDao.selectUserByOpenid(openid);
    }
}
