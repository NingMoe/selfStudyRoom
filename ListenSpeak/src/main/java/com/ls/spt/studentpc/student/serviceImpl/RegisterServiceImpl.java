package com.ls.spt.studentpc.student.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ls.spt.basic.dao.SmsTempDao;
import com.ls.spt.basic.entity.SmsRecord;
import com.ls.spt.basic.service.SmsTempService;
import com.ls.spt.manager.dao.MessageRecordDao;
import com.ls.spt.manager.dao.VAllUserDao;
import com.ls.spt.manager.entity.MessageRecord;
import com.ls.spt.manager.entity.VAllUser;
import com.ls.spt.student.dao.StudentUserDao;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.service.RegisterService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.Md5Tool;
import com.ls.spt.utils.StudentPcConstants;
import com.wechat.studentunionid.dao.StudentUnionidDao;
import com.wechat.studentunionid.entity.StudentUnionid;

@Service
public class RegisterServiceImpl implements RegisterService {
    
    @Resource
    private VAllUserDao vAllUserDao;
    
    @Resource
    private  SmsTempDao  smsDao;
    
    @Resource
    private StudentUserDao studentUserDao;
    
    @Resource
    private StudentUnionidDao studentUnionidDao;
    
    @Resource
    private SmsTempService smsTempService;

    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    public Map<String, Object> isRegister(HttpServletRequest request,String phone, String short_msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String unionid = (String) session.getAttribute(StudentPcConstants.STUDENT_UNIONID);
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "手机号为空");
            return map;
        }
        
        if(StringUtils.isEmpty(short_msg)){
            map.put("flag", false);
            map.put("message", "短信为空");
            return map;
        }
        
        if(StringUtils.isEmpty(unionid)){
            map.put("flag", false);
            map.put("message", "微信授权超时");
            return map;
        }
        
        // 验证用户账号手机格式
        if(!Common.isMobile(phone)){
            map.put("flag", false);
            map.put("message", "手机号格式不正确！");
            return map;
        }
        
        try {
            SmsRecord mr = getMsg(phone);
            if(mr == null || !short_msg.equals(mr.getCode())){
                map.put("flag", false);
                map.put("message", "验证码输入错误，请重新输入！");
                return map;
            }
            
            VAllUser vAllUser = new VAllUser();
            vAllUser.setPhone(phone);
            VAllUser vAllUser1 = vAllUserDao.queryUser(vAllUser);
            if(vAllUser1 == null){
                map.put("flag", true);
                map.put("is_binding", false);
                map.put("message", "该手机号未注册");
            }else if(vAllUser1 != null && "1".equals(vAllUser1.getUser_type())){
                //已注册教师端，不绑定
                map.put("flag", false);
                map.put("message", "该手机号已注册为教师端账号");
            }else if(vAllUser1 != null && "2".equals(vAllUser1.getUser_type())){
                //已注册学生端，绑定
                //验证是否绑定过
                StudentUnionid su = new StudentUnionid();
                su.setUnionid(unionid);
                StudentUnionid sud = studentUnionidDao.selectByUnionid(su);
                if(sud != null){
                    map.put("flag", false);
                    map.put("message", "该微信号已经绑定过，请先登录解绑");
                    return map;
                }
                
                Map<String, Object> mapparam = new HashMap<String, Object>();
                mapparam.put("phone", phone);
                StudentUser studentUser = studentUserDao.selectByPhone(mapparam);
                if(studentUser == null){
                    map.put("flag", false);
                    map.put("is_binding", false);
                    map.put("message", "该手机号未注册");
                    return map;
                }
                StudentUnionid studentUnionid = new StudentUnionid();
                studentUnionid.setUnionid(unionid);
                studentUnionid.setStudent_id(studentUser.getId());
                studentUnionidDao.insertSelective(studentUnionid);
                studentUser.setPassword("");
                session.setAttribute(StudentPcConstants.STUDENT_USER, studentUser);
                map.put("flag", true);
                map.put("is_binding", true);
                map.put("message", "绑定成功");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "手机号验证异常："+e);
        }
        
        return map;
    }

    /**
     * 发送注册短信验证码
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
            VAllUser vAllUser = new VAllUser();
            vAllUser.setPhone(phone);
            VAllUser vAllUser1 = vAllUserDao.queryUser(vAllUser);
            if(vAllUser1 != null && "1".equals(vAllUser1.getUser_type())){
                map.put("flag", false);
                map.put("message", "该手机号为教师端账号，无法注册绑定。");
                return map;
            }
            
            SmsRecord msg = new SmsRecord();
            String code = "";
            Random rd = new Random();
            for(int i = 0; i < 6; i++){
                code += rd.nextInt(9);
            }
            msg.setMemo("register");
            msg.setCode(code);
            String text = "用户注册：（"+code+"）SPT口语水平测试系统验证码";
            msg.setSendComment(text);
            msg.setSendTel(phone);
            msg.setSendTime(new Timestamp(System.currentTimeMillis()));
            msg.setSmsType("1");   
            msg.setCompany("25");
            Integer i = smsTempService.sendMessage(msg);
            if(i == 1){
                map.put("flag", true);
                map.put("message", "短信验证码发送成功。");
            }else{
                map.put("flag", false);
                map.put("message", "短信发送失败："+i);
            }
            
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
     * @param short_msg
     * @param password
     * @param request
     * @return
     */
    public Map<String, Object> studentLogin(String phone, String password, String short_msg, String name, String sex, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String unionid = (String) session.getAttribute(StudentPcConstants.STUDENT_UNIONID);
        if(StringUtils.isEmpty(phone)){
            map.put("flag", false);
            map.put("message", "手机号为空");
            return map;
        }
        
        if(StringUtils.isEmpty(password)){
            map.put("flag", false);
            map.put("message", "密码为空");
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
            SmsRecord mr = getMsg(phone);
            if(mr == null || !short_msg.equals(mr.getCode())){
                map.put("flag", false);
                map.put("message", "验证码输入错误，请重新输入！");
                return map;
            }
            
            VAllUser vAllUser = new VAllUser();
            vAllUser.setPhone(phone);
            VAllUser vAllUser1 = vAllUserDao.queryUser(vAllUser);
            if(vAllUser1 != null){
                map.put("flag", false);
                map.put("message", "该手机号已注册");
                return map;
            }
            
            StudentUser studentUser = new StudentUser();
            studentUser.setPhone(phone);
            studentUser.setPassword(Md5Tool.getMd5(password));
            studentUser.setStatus("1");
            studentUser.setSex(sex);
            studentUser.setName(name);
            studentUser.setCreateTime(new Date());
            studentUserDao.insertSelective(studentUser);
            
            
            StudentUnionid studentUnionid = new StudentUnionid();
            studentUnionid.setUnionid(unionid);
            studentUnionid.setStudent_id(studentUser.getId());
            studentUnionidDao.insertSelective(studentUnionid);
            studentUser.setPassword("");
            session.setAttribute(StudentPcConstants.STUDENT_USER, studentUser);
            
            map.put("flag", true);
            map.put("message", "绑定成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "绑定异常："+e);
        }
        return map;
    }
    
    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public SmsRecord getMsg(String phone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE, -30);
        Date dt1=rightNow.getTime();
        String time = sdf.format(dt1);
        System.out.println(time);
        return smsDao.getMsg(phone,time);
    }
    
}
