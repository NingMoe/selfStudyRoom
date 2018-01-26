package com.ls.spt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ls.spt.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.ls.spt.utils.XdfRequestUtil.XdfPostDataOperate;


public class XdfInterface {
    
    
    //网报通过姓名手机号获取学员号
    public static String getstudentcodebyphone(String studentName, String mobile, String schoolId){
        String method = "GetStudentCode";//请求方法
        String sendUrl = "http://bm.xdf.cn/Apiext/Student/GetStuInfoByName";//从属性文件中获取Url
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d hh:mm:ss");
        String result = "";//接口返回字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("method", method);
        map.put("requestTime", sdf.format(new Date()));
        map.put("schoolId", schoolId);
        map.put("studentName", studentName);
        map.put("mobile", mobile);
        map.put("appId", "wechatApp");
        String signature = XdfGeneralRequestSignature.generalRequestSignature(map);
        if (signature.equals("")){
            return result;
        }
        
        map.put("signMethod", "MD5");
        map.put("signature", signature);
        try {
            result = XdfPostDataOperate.sendPostDataToWechatUser(map,sendUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
