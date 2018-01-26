package com.human.front.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.front.service.FrontService;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;


@Service
public class FrontServiceImpl implements FrontService{
    
    @Autowired
    private RecruitAcceptanceDao raDao;
    
    @Autowired
    private ResumeBaseDao rbDao;
    
    @Autowired
    private SmsTempService smsTempService;
    
    @Override
    public Map<String,Object> sendBindMsg(String telephone) {
        Map<String,Object> result = new HashMap<String,Object>();
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setSendTel(telephone);
        smsRecord.setCompany("25");
        String code = "";
        Random rd = new Random();
        for(int i = 0; i < 6; i++){
            code += rd.nextInt(9);
        }
        smsRecord.setSendComment("绑定：("+code+")SMARTWORK人力资源系统验证码");
        Integer sendResult = smsTempService.sendMessage(smsRecord);
        if(sendResult.intValue()!=1){
            result.put("flag", false);
            result.put("message", "验证码发送失败");
            return result;
        }
        result.put("flag", true);
        result.put("code",code);
        result.put("message", "验证码已发送");
        return result;
    }
    
    @Override
    public ResumeSeeker getSeekerBaseByNameAndPhone(String name, String phone) {
        return raDao.checkResumeByNameAndPhone(name, phone);
    }
    
    @Override
    public void bindOpenId(ResumeSeeker seeker) {
        ResumeSeeker rs = raDao.checkResumeByNameAndPhone(seeker.getName(), seeker.getPhone());
        if(rs!=null){
            seeker.setId(rs.getId());
            raDao.updateByPrimaryKeySelective(seeker);
        }else{
            raDao.insert(seeker);
        }
    }
    
    @Override
    public boolean isPositionHasTd(String openId, Integer positionId) {
        Integer cnt = rbDao.isPositionHasTd(openId, positionId);
        if(cnt==null || cnt.intValue()==0){
            return false;
        }
        return true;
    }
}
