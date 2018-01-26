package com.human.basic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.human.basic.dao.SmsTempDao;
import com.human.basic.entity.SmsDictionary;
import com.human.basic.entity.SmsParam;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsResult;
import com.human.basic.entity.SmsTem;
import com.human.basic.service.SmsTempService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.msg.SmsMd5;
import com.human.utils.msg.WebUtilX;


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
    public PageView queryTem(PageView pageView, SmsTem st,String deptIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(null!=deptIds&&deptIds.length()>0){
            st.setDeptIds(deptIds.split(","));
        }
        map.put("paging", pageView);
        map.put("t", st);
        List<SmsTem> list = smsDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<SmsParam> queryParam(SmsParam sp) {
        return smsDao.queryParam(sp);
    }

    @Override
    public int add(SmsTem st) {
        return smsDao.add(st);
    }

    @Override
    public SmsTem queryById(Long id) {
        return smsDao.queryById(id);
    }

    @Override
    public int edit(SmsTem st) {
        return smsDao.edit(st);
    }

    @Override
    @Transactional
    public Map<String,Object> delTemp(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
        String[] userIds=deleteIds.split(",");
        Map<String,Object> paraMap=new HashMap<String,Object>();
        paraMap.put("ids", userIds);
        paraMap.put("state", 1);
        paraMap.put("updateUser", Common.getMyUser().getUserid());
        smsDao.delTemp(paraMap);
        map.put("flag", true);
        map.put("message", "操作成功");
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", "操作失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
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

    @Override
    public List<SmsTem> queryTemAll(SmsTem st) {
        List<SmsTem> list = smsDao.queryAll(st);
        return list;
    }

    @Override
    public PageView queryMsgRecord(PageView pageView, SmsRecord sr, String deptIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(null!=deptIds&&deptIds.length()>0){
            sr.setDeptIds(deptIds.split(","));
        }
        map.put("paging", pageView);
        map.put("t", sr);
        List<SmsRecord> list = smsDao.queryMsgRecord(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<SmsRecord> queryMsgRecordNoPage(SmsRecord sr) {
        List<SmsRecord> list = smsDao.queryMsgRecordNoPage(sr);
        return list;
    }

    @Override
    public Map<String, Object> sendCode(String telNumber,Long resumeSeekerId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", "修改手机号短信验证码发送成功!"); 
        Random rd = new Random();
        String code = "";
        for(int i = 0; i < 6; i++){
            code += rd.nextInt(9);
        }
        String message = "验证码:("+code+"),SMARTWORK系统修改手机号短信验证码";
        SmsRecord s = new SmsRecord();
        s.setCompany("128");
        s.setDeptId("1181300000");
        s.setSendTel(telNumber);
        s.setMemo("verificationCode");
        s.setSendComment(message);
        s.setSeekerId(resumeSeekerId);
        s.setSmsType("1");
        try{
            Integer state=sendMessage(s);
            if(state!=1){
                map.put("flag", false);
                map.put("msg", "修改手机号短信验证码发送失败!");  
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "修改手机号短信验证码发送失败!");
        }
        return map;
    }

}
