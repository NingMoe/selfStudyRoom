package com.ls.spt.manager.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.service.MessageRecordService;
import com.ls.spt.manager.service.TeacherUserService;
import com.ls.spt.utils.Common;

@Controller
@RequestMapping(value="/s/")
public class IndexController {
		
    private final  Logger logger = LogManager.getLogger(IndexController.class);
    
    
    @Resource
    private TeacherUserService teacherUserService;
	
    
	@Resource
    private MessageRecordService messageRecordService;
		
	
	
   /**
     * 忘记密码
     * @param request
     * @return
     */
    @RequestMapping("changePassWrod")
    public ModelAndView changePassWrod(HttpServletRequest request) {
        return new ModelAndView("/framework/changePassWord");
    }   
    
    /**
     * 发送忘记密码的短信验证码
     * @param telNumber 手机号
     * @return 发送修改密码验证码的结果
     */
    @ResponseBody
    @RequestMapping(value="sendChangePasswordMsg")
    public Map<String,Object> sendChangePasswordMsg(String telNumber){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            if(Common.isEmpty(telNumber)){
                map.put("flag", false);
                map.put("message", "手机号不能为空!");
                return map;
            }
            map=messageRecordService.sendChangePasswordMsg(telNumber);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("发送短信验证码错误!",e.getMessage());
            map.put("flag", false);
            map.put("message", "发送短信验证码错误!");
        }        
        return map;
    }
    
    
    /**
     * 通过验证码修改密码
     * @param telNumber 手机号
     * @param newpassword 新的密码
     * @param msg 验证码
     * @return 修改结果
     */
    @ResponseBody
    @RequestMapping(value="changPasswordForMsg")
    public Map<String,Object> changePasswordForMsg(TeacherUser tu,String msg){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map=messageRecordService.changePasswordForMsg(tu,msg);  
        }catch(Exception e){
            e.printStackTrace();
            logger.error("通过验证码修改密码错误!",e.getMessage());
            map.put("flag", false);
            map.put("message", "通过验证码修改密码错误!");
        }              
        return map;
    }
	

}
