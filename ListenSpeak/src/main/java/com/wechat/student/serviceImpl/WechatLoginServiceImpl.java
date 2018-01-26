package com.wechat.student.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ls.spt.manager.dao.MessageRecordDao;
import com.ls.spt.manager.entity.MessageRecord;
import com.ls.spt.manager.entity.ShortMessage;
import com.ls.spt.student.dao.StudentUserDao;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.Md5Tool;
import com.wechat.student.service.WechatLoginService;
import com.wechat.studentunionid.dao.StudentUnionidDao;
import com.wechat.studentunionid.entity.StudentUnionid;
import com.wechat.utils.interceptor.BindingConstants;
import com.wechat.utils.weixinapi.webpage.WxWebUtil;
import com.wechat.utils.weixinapi.webpage.entity.WechatOpenid;
import com.wechat.utils.weixinapi.webpage.entity.WechatUserinfo;;

@Service
public class WechatLoginServiceImpl implements WechatLoginService {
    
    private final Logger log = LogManager.getLogger(WechatLoginServiceImpl.class);

    @Resource
    private StudentUnionidDao studentUnionidDao;
    
    @Resource
    private StudentUserDao studentUserDao;
    
    @Resource
    private MessageRecordDao messageRecordDao;
    
    @Value("${humanServer}")
    private String human_url;
    
    /**
     * 微信跳转
     * @param request
     * @param response
     * @return
     */
    public String getwxview(HttpServletRequest request, HttpServletResponse response) {
        String resulturl = "wechat/public/404";
        String requestUrl = request.getParameter("requestUrl");
        //跳转url
        String redirect_url = human_url + "/wechat/binding/student/view.html?requestUrl="+requestUrl;
        
        WxWebUtil wau = new WxWebUtil();
        resulturl = wau.getCodeUrl(redirect_url, false);
        return resulturl;
    }

    /**
     * 跳转接收
     * @param request
     * @param response
     * @return
     */
    public String getview(HttpServletRequest request, HttpServletResponse response) {
        String resulturl = "wechat/public/404";
        String code =  request.getParameter("code");
        String requestUrl =  request.getParameter("requestUrl");
        if(StringUtils.isEmpty(code)){
            log.error("获取code失败。");
            return resulturl;
        }
        log.info("code:"+code+",requestUrl:"+requestUrl);
        WxWebUtil wau = new WxWebUtil();
        try {
            WechatOpenid wechatOpenid  = wau.getAccessToken(code);
            
            if(wechatOpenid == null || wechatOpenid.getErrcode() != null || StringUtils.isEmpty(wechatOpenid.getAccess_token()) || StringUtils.isEmpty(wechatOpenid.getOpenid())){
                log.error("获取微信网页授权access_token失败："+wechatOpenid.toString());
                return resulturl;
            }
            
            WechatUserinfo wechatUserinfo = wau.getUnionID(wechatOpenid.getAccess_token(), wechatOpenid.getOpenid());
            if(wechatUserinfo == null || wechatUserinfo.getErrcode() != null || wechatUserinfo.getUnionid() == null){
                log.error("调用微信接口获取用户信息失败:"+wechatUserinfo.getErrcode()+"-"+wechatUserinfo.getErrmsg());
                return resulturl;
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(BindingConstants.BINDING_USERINFO, wechatUserinfo);
            StudentUser studentUser = studentUserDao.selectByUnionid(wechatUserinfo.getUnionid());
            
            httpSession.setAttribute(BindingConstants.BINDING_REURL, requestUrl);
            if(studentUser == null){
                log.info("用户未绑定，跳转到绑定页");
                resulturl = "/wechat/student/binding";
                return resulturl;
            }
            
            if(StringUtils.isEmpty(studentUser.getPhone())){
                log.info("用户绑定人为空，解除该绑定");
                StudentUnionid studentUnionid = new StudentUnionid();
                studentUnionid.setUnionid(wechatUserinfo.getUnionid());
                studentUnionidDao.delectByUnionid(studentUnionid);
                resulturl = "/wechat/student/binding";
                return resulturl;
            }
            studentUser.setPassword(null);
            if(StringUtils.isEmpty(studentUser.getName())){
                log.info("用户绑定人姓名为空，跳转到填写姓名页");
                httpSession.setAttribute(BindingConstants.BINDING_STUDENT_USER, studentUser);
                resulturl = "/wechat/student/addname";
                return resulturl;
            }
            
            //已经绑定，跳转原链接
            resulturl = "/wechat/student/studentindex";
            if(StringUtils.isNotEmpty(requestUrl)){
                log.info("跳转到原链接："+requestUrl);
                httpSession.removeAttribute(BindingConstants.BINDING_REURL);
                resulturl = requestUrl;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resulturl;
    }
    
    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    public Map<String, Object> isRegister(String phone){
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "手机号为空");
            return map;
        }
        
        // 验证用户账号手机格式
        if(!Common.isMobile(phone)){
            map.put("flag", false);
            map.put("message", "手机号格式不正确！");
            return map;
        }
        
        try {
            StudentUser studentUser = new StudentUser();
            studentUser.setPhone(phone);
            studentUser.setStatus("1");
            StudentUser studentUser1 = studentUserDao.queryUser(studentUser);
            if(studentUser1 == null){
                map.put("flag", true);
                map.put("register", false);
                map.put("message", "未注册");
            }else{
                map.put("flag", true);
                map.put("register", true);
                map.put("message", "已注册");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "手机号验证异常："+e);
        }
        
        return map;
    }

    /**
     * 获取登录短信验证码
     * @param phone
     * @return
     */
    public Map<String, Object> getLoginShortMsg(String phone) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "手机号为空");
            return map;
        }
        
        // 验证用户账号手机格式
        if(!Common.isMobile(phone)){
            map.put("flag", false);
            map.put("message", "手机号格式不正确！");
            return map;
        }
        
        try {
            MessageRecord mr = new MessageRecord();
            ShortMessage msg = new ShortMessage();
            msg.setMemo("changePassword");        
            String code = "";
            Random rd = new Random();
            for(int i = 0; i < 6; i++){
                code += rd.nextInt(9);
            }
            String text = "微信绑定：（"+code+"）SPT口语水平测试系统验证码";
            map.putAll(msg.sendMessage(phone, text));
            mr.setToUser(phone);
            mr.setCode(code);
            mr.setSendComment(text);
            mr.setSendTime(new Date());
            mr.setType("1");        
            if((Integer)map.get("status") != 1){
                mr.setState(String.valueOf((Integer)map.get("status")));
                mr.setStateDesc("短信发送失败");
                map.put("flag",false);
                map.put("message", "短信发送失败，请重新发送,错误码："+map.get("status"));
            }else{
                mr.setState("1");
                mr.setStateDesc("短信发送成功");
                map.put("flag",true);
                map.put("message", "短信已经成功发送。");
            }
            messageRecordDao.insertSelective(mr);  
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "短信验证码发送异常："+e);
        }
        
        return map;
    }

    /**
     * 绑定验证
     * @param phone
     * @param msg
     * @param unionid
     * @return
     */
    public Map<String, Object> studentLogin(String phone, String short_msg, String password, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        HttpSession httpSession = request.getSession();
        WechatUserinfo wechatUserinfo = (WechatUserinfo) httpSession.getAttribute(BindingConstants.BINDING_USERINFO);
        
        if(wechatUserinfo == null){
            map.put("flag", false);
            map.put("message", "微信信息为空");
            return map;
        }
        
        if(StringUtils.isEmpty(wechatUserinfo.getUnionid())){
            map.put("flag", false);
            map.put("message", "微信信息为空");
            return map;
        }
        
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "手机号为空");
            return map;
        }
        
        // 验证用户账号手机格式
        if(!Common.isMobile(phone)){
            map.put("flag", false);
            map.put("message", "手机号格式不正确！");
            return map;
        }
        
        if(StringUtils.isEmpty(short_msg)){
            map.put("flag", false);
            map.put("message", "验证码为空");
            return map;
        }
        
        try {
            
            MessageRecord mr = getMsg(phone);
            if(mr == null || !short_msg.equals(mr.getCode())){
                map.put("flag", false);
                map.put("message", "验证码输入错误，请重新输入！");
                return map;
            }
            
            //验证是否绑定
            StudentUnionid studentUnionid = new StudentUnionid();
            studentUnionid.setUnionid(wechatUserinfo.getUnionid());
            StudentUnionid studentUnionid1 = studentUnionidDao.selectByUnionid(studentUnionid);
            
            //验证是否注册
            if(studentUnionid1 == null){ 
                Integer student_id = null;
                StudentUser studentUser = new StudentUser();
                studentUser.setPhone(phone);
                studentUser.setStatus("1");
                StudentUser studentUser1 = studentUserDao.queryUser(studentUser);
                //未注册
                if(studentUser1 == null){
                    if(StringUtils.isEmpty(password)){
                        map.put("flag", false);
                        map.put("message", "密码为空");
                        return map;
                    }
                    studentUser.setPassword(Md5Tool.getMd5(password));
                    studentUser.setCreateTime(new Date());
                    studentUserDao.insertSelective(studentUser);
                    student_id = studentUser.getId();
                }else{
                    student_id = studentUser1.getId();
                }
                studentUnionid.setStudent_id(student_id);
                studentUnionidDao.insertSelective(studentUnionid);
            }else{
                map.put("flag", false);
                map.put("message", "已经绑定，请勿重复绑定");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "绑定异常："+e);
        }
        return map;
    }
    
    /**
     * 完善姓名
     * @param name
     * @param request
     * @return
     */
    public Map<String, Object> addName(String name, String sex, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        HttpSession httpSession = request.getSession();
        WechatUserinfo wechatUserinfo = (WechatUserinfo) httpSession.getAttribute(BindingConstants.BINDING_USERINFO);
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(BindingConstants.BINDING_STUDENT_USER);
        
        if(wechatUserinfo == null){
            map.put("flag", false);
            map.put("message", "微信信息为空");
            return map;
        }
        
        if(StringUtils.isEmpty(wechatUserinfo.getUnionid())){
            map.put("flag", false);
            map.put("message", "微信信息为空");
            return map;
        }
        
        if(studentUser == null){
            map.put("flag", false);
            map.put("message", "学生信息为空");
            return map;
        }
        
        if(StringUtils.isEmpty(studentUser.getPhone())){
            map.put("flag", false);
            map.put("message", "未绑定");
            return map;
        }
        
        try {
            studentUser.setName(name);
            studentUser.setSex(sex);
            studentUserDao.updateByPrimaryKeySelective(studentUser);
            map.put("flag", true);
            map.put("message", "绑定成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "未绑定");
        }
        return map;
    }
    
    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public MessageRecord getMsg(String phone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE, -30);
        Date dt1=rightNow.getTime();
        String time = sdf.format(dt1);
        System.out.println(time);
        MessageRecord mr = messageRecordDao.getMsg(phone,time);
        return mr;
    }

}
