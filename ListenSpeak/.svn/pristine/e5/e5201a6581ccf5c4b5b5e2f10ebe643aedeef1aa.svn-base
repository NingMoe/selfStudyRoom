package com.ls.spt.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ls.spt.manager.dao.MessageRecordDao;
import com.ls.spt.manager.dao.TeacherUserDao;
import com.ls.spt.manager.entity.MessageRecord;
import com.ls.spt.manager.entity.ShortMessage;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.service.MessageRecordService;
import com.ls.spt.utils.Md5Tool;


@Service
public class MessageRecordServiceImpl implements MessageRecordService {
        
    @Resource
    private TeacherUserDao tuDao;
    
    @Resource
    private MessageRecordDao messageRecordDao;

    
    @Override
    public Map<String, Object> sendRegisterMsg(String telNumber) {
        return null;
    }

    
    
    @Override
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

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> sendChangePasswordMsg(String telNumber) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            TeacherUser tu=new TeacherUser();
            tu.setPhone(telNumber);          
            tu.setStatus("1");
            TeacherUser user = tuDao.queryUser(tu);
            if(user == null){
                map.put("flag", false);
                map.put("message", "该手机号对应的教师用户不存在!");
                return map;
            }       
            Random rd = new Random();      
            MessageRecord mr = new MessageRecord();
            ShortMessage msg = new ShortMessage();
            msg.setMemo("changePassword");        
            String code = "";
            for(int i = 0; i < 6; i++){
                code += rd.nextInt(9);
            }
            String text = "密码找回：（"+code+"）SPT口语水平测试系统验证码";
            map.putAll(msg.sendMessage(telNumber, text));
            mr.setToUser(telNumber);
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
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("message", "短信发送失败，请重新发送!");
        }      
        return map;
    }



    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> changePasswordForMsg(TeacherUser tu, String msg) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            MessageRecord mr = getMsg(tu.getPhone());
            if(mr == null){
                map.put("flag", false);
                map.put("message", "验证码输入错误，请重新输入！");
                return map;
            }
            if(msg.equals(mr.getCode())){
                TeacherUser user=new TeacherUser();
                user.setPhone(tu.getPhone());          
                user.setStatus("1");
                user = tuDao.queryUser(user);
                if(user != null){
                    user.setPassword(Md5Tool.getMd5(tu.getPassword()));
                    Integer i = tuDao.updateByPrimaryKeySelective(user);
                    if(i == 1){
                        map.put("flag", true);
                        map.put("message", "修改成功!");
                    }else{
                        map.put("flag", false);
                        map.put("message", "修改失败!");
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "该手机号对应的教师用户不存在!");
                }  
            }else{
                map.put("flag", false);
                map.put("message", "验证码输入错误，请重新输入!");
            }                        
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "通过验证码修改密码错误!");
        }              
        return map;
    }
    
}
