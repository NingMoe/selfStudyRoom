package com.ls.spt.basic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ls.spt.basic.dao.SmsTempDao;
import com.ls.spt.basic.entity.SmsDictionary;
import com.ls.spt.basic.entity.SmsRecord;
import com.ls.spt.basic.entity.SmsResult;
import com.ls.spt.basic.service.SmsTempService;
import com.ls.spt.utils.msg.SmsMd5;
import com.ls.spt.utils.msg.WebUtilX;


@Service
public class SmsTempServiceImpl implements SmsTempService {

    private final Logger logger = LogManager.getLogger(SmsTempServiceImpl.class);
            
    @Resource
    private  SmsTempDao  smsDao;
    
    @Value("${sms.apiUrl}")
    private String apiUrl;
    
    @Value("${sms.method}")
    private String method;
    
    @Value("${sms.appId}")
    private String appId;
    
    @Value("${sms.appKey}")
    private String appKey;
    
    
    @Override
    public Integer sendMessage(SmsRecord smsRecord ) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String time = formatter.format(currentTime);
        String msg = smsRecord.getSendComment() + time;
        logger.info("==========发送短信开始=============");
        logger.info("电话：" + smsRecord.getSendTel());
        logger.info("内容：" + smsRecord.getSendComment());
        /*SmsRecord smsRecord = new SmsRecord();
        smsRecord.setSendComment(sms.getMessage());
        smsRecord.setSendTel(sms.getPhone());
        smsRecord.setType(Integer.valueOf(sms.getType()));
        smsRecord.setCompany(sms.getCompanyId());
        smsRecord.setDeptId(sms.getDeptId());
        smsRecord.setSendUser(sms.getSendUser());
        smsRecord.setSendName(sms.getSendName());
        smsRecord.setSeekerId(seekerId);
        smsRecord.setResumeId(resumeId);*/
        String state = UUID.randomUUID().toString();
        String signText = (method + appId + smsRecord.getSendTel() + smsRecord.getCompany() + state + time + appKey).toLowerCase();
        String sign =SmsMd5.md5(signText); // 签名

        SmsDictionary<String, String> dic = new SmsDictionary<String, String>();
        dic.Add("method", method);
        dic.Add("appid", appId);
        dic.Add("mobile", smsRecord.getSendTel());
        dic.Add("msg", msg);
        dic.Add("schoolId", smsRecord.getCompany());
        dic.Add("dept", smsRecord.getDeptId());
        dic.Add("memo", smsRecord.getMemo());
        dic.Add("state", state);
        dic.Add("time", time);
        dic.Add("sign", sign);

        String josnresult = null;
        try {
            josnresult = WebUtilX.DoPost(apiUrl, dic);
            SmsResult result = JSON.parseObject(josnresult, SmsResult.class);
            smsRecord.setState(result.getStatus());
            smsRecord.setStateDesc(result.getMessage());
        }
        catch (Exception e) {
            logger.error("===短信发送异常====", e);
            smsRecord.setState(99);
            smsRecord.setStateDesc(e.getMessage());
        }
        logger.info("发送返回码：" + smsRecord.getState());
        logger.info("发送返回内容：" + smsRecord.getStateDesc());
        try {
            smsDao.insertSmsRecord(smsRecord);
        }
        catch (Exception e) {
            logger.error("===保存短信发送记录失败====", e);
        }
        return smsRecord.getState();
    }
    
    @Override
    @Async
    public void sendMessage(List<SmsRecord> srList) {
        if (srList == null || srList.size() == 0) {
            logger.info("待发送短信为空，取消发送");
            return;
        } 
        for (SmsRecord sr : srList) {
             sendMessage(sr);
        }
    }

}
