package com.human.utils.msg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.SmsDictionary;
import com.human.basic.entity.SmsResult;
import com.human.utils.msg.SmsMd5;
import com.human.utils.msg.WebUtilX;

/**
 * 短信手动补发类。均为真实短信发送，请勿随意运行
 * @author XiaZy
 *
 */
public class SendMsgUtil {
    
    private final static  Logger logger = LogManager.getLogger(SendMsgUtil.class);
    
    public static void main(String[] args) {
        String apiUrl="http://smsapi.xdf.cn/api/apis/ApiV5.ashx";
        String method="SendSmsV6";
        String appId="90166";
         String appKey="u2-sp@#$_v";
        logger.info("==========发送短信开始=============");
        String message = "您好，由于刚才系统问题导致结算错误，系统将自动退班，请收到短信后重新报班！给您带来的不便敬请谅解!";
        String s1="18100501808";
        logger.info("==========发送短信开始=============");
        String[] telList=s1.split(",");
        logger.info("需要发送短信数量{0}",telList.length);
        for(String tel:telList) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentTime = new Date();
            String time = formatter.format(currentTime);
            String msg = message + time;
            logger.info("接收号码:{0}",tel);
            logger.info("接收信息:{0}",message);
            String state = UUID.randomUUID().toString();
            String signText = (method + appId + tel +"128" + state + time + appKey).toLowerCase();
            String sign =SmsMd5.md5(signText); // 签名
            SmsDictionary<String, String> dic = new SmsDictionary<String, String>();
            dic.Add("method", method);
            dic.Add("appid", appId);
            dic.Add("mobile", tel);
            dic.Add("msg", msg);
            dic.Add("schoolId", "128");
            dic.Add("dept", "1181300000");
            dic.Add("memo", "jinji");
            dic.Add("state", state);
            dic.Add("time", time);
            dic.Add("sign", sign);
            String josnresult = null;
            try {
                josnresult = WebUtilX.DoPost(apiUrl, dic);
                SmsResult result = JSON.parseObject(josnresult, SmsResult.class);
                logger.info("发送状态:{0},发送结果{1}",result.getStatus(),result.getMessage());
            }
            catch (Exception e) {
                logger.error("===短信发送异常====", e);
            }
        }
    }
}
