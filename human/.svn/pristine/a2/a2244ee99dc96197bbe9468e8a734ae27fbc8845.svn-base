package com.human.activity.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.human.activity.dao.ActivityInfoDao;
import com.human.activity.dao.BuyerInfoDao;
import com.human.activity.dao.ProductDao;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.BuyerInfoDto;
import com.human.activity.entity.FrontOrderInfoParams;
import com.human.activity.service.BuyerInfoService;
import com.human.basic.dao.SmsTempDao;
import com.human.utils.PageView;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;

@Service
public class BuyerInfoServiceImpl implements BuyerInfoService {

    @Resource
    private ActivityInfoDao aIDao;
    
    @Resource  
    private ProductDao  productDao ;
    
    @Resource  
    private BuyerInfoDao  buyerInfoDao ;
    
    @Resource
    private SmsTempDao smsTempDao;

    @Override
    public PageView queryPayDetailsByPage(PageView pageView, BuyerInfoDto info) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", info);
        List<BuyerInfoDto> list = buyerInfoDao.query(map);
        if(CollectionUtils.isNotEmpty(list)){
            //调用报班接口获取学员号
            for(BuyerInfoDto buyerInfoDto:list){
                System.out.println("姓名:"+buyerInfoDto.getName()+",手机号:"+buyerInfoDto.getTelephone());
                String studentCode=getStudentCodeByPhone(buyerInfoDto.getName(),buyerInfoDto.getTelephone());
                buyerInfoDto.setStudentCode(studentCode);
            }
        }   
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public BuyerInfo selectByOrderNo(String orderNo) {       
        return buyerInfoDao.selectByOrderNo(orderNo);
    }

    @Override
    public Map<String, Object> checkLoginInfo(FrontOrderInfoParams fops, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String telephone=fops.getTelephone();     
            String name =fops.getName();
            String code=fops.getCode();          
            //验证手机验证码是否正确
            String code1=getMsg(telephone);
            if(!code1.equals(code)){
                map.put("flag", false);
                map.put("message", "验证码输入错误!");
                return map;
            } 
            //验证是否存在此用户
            BuyerInfo  buyerInfo=new BuyerInfo();
            buyerInfo.setName(name);
            buyerInfo.setTelephone(telephone);
            int i=buyerInfoDao.selectByNameAndPhone(buyerInfo);
            if(i==0){
                map.put("flag", false);
                map.put("message", "您还未购买任何优惠卷!");
                return map;
            }
            map.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "校验登录信息失败,请稍后重试!");            
        }
        return map;
    }
    
    
    /**
     * 获取手机验证码
     * @param phone
     * @return
     */
    public String getMsg(String phone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE, -30);
        Date dt1=rightNow.getTime();
        String time = sdf.format(dt1);
        System.out.println(time);         
        String code="";
        String sendComment = smsTempDao.getMsg(phone,time);
        if(StringUtils.isNotEmpty(sendComment)){
            int start=sendComment.indexOf("(");
            int end=sendComment.indexOf(")");
            code=sendComment.substring(start+1, end);
        }
        return code;
    }

    @Override
    public List<BuyerInfoDto> selectMyCard(BuyerInfo record) {       
        return buyerInfoDao.selectMyCard(record);
    }
    
    
    /**
     * 通过姓名手机号调用网报接口获取学员号
     * @param studentName
     * @param mobile
     * @param schoolId
     * @return
     */
    public  String getStudentCodeByPhone(String studentName, String mobile){
        String studentCode="";
        String method = "GetStudentCode";//请求方法
        String sendUrl = "http://bm.xdf.cn/Apiext/Student/GetStuInfoByName";//请求Url
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d hh:mm:ss");       
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("method", method);
        map.put("requestTime", sdf.format(new Date()));
        map.put("schoolId", "25");
        map.put("studentName", studentName);
        map.put("mobile", mobile);
        map.put("appId", "wechatApp");
        String signature = XdfGeneralRequestSignature.generalRequestSignature(map);
        if (signature.equals("")){
            return studentCode;
        }        
        map.put("signMethod", "MD5");
        map.put("signature", signature);
        try {
            String result = XdfPostDataOperate.sendPostDataToWechatUser(map,sendUrl);
            if(StringUtils.isNotEmpty(result)){
                System.out.println("购买活动通过姓名手机号获取学员号result=="+result);
                JSONObject jo = new JSONObject(result);
                if(jo != null && jo.has("State") && jo.getInt("State") == 1 && jo.has("Data")){
                    JSONArray ja = jo.getJSONArray("Data");
                    if(ja != null && ja.length() > 0){                    
                        for(int i =0; i < ja.length(); i++){
                            JSONObject job = ja.getJSONObject(i);
                            if(job.has("StudentCode")){
                                String student_code = job.getString("StudentCode");
                                studentCode += student_code+"," ;
                            }
                        }
                    }
                }
                if(StringUtils.isNotEmpty(studentCode)){
                    studentCode=studentCode.substring(0,studentCode.length()-1);                
                } 
            }            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return studentCode;
    }
}
