package com.human.dingding.service.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.dingding.bean.AttendaceDetail;
import com.human.dingding.bean.AttendanceReq;
import com.human.dingding.bean.AttendanceResp;
import com.human.dingding.bean.CheckInOut;
import com.human.dingding.bean.DeptInfo;
import com.human.dingding.bean.DeptListResponse;
import com.human.dingding.bean.Emp;
import com.human.dingding.bean.EmpListRepsponse;
import com.human.dingding.bean.EmpRepsonse;
import com.human.dingding.bean.GroupInfo;
import com.human.dingding.bean.LocalDeptInfo;
import com.human.dingding.bean.LocalEmp;
import com.human.dingding.dao.DingDingDao;
import com.human.dingding.service.DingDingService;
import com.human.dingding.utils.DingDingUtils;
import com.human.dingding.utils.OApiException;
import com.human.pulgin.jdbc.DataSourceContextHolder;
import com.human.utils.execl.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DingDingServiceImpl implements DingDingService {

    private final Logger logger = LogManager.getLogger(DingDingServiceImpl.class);
    
    @Resource
    private DingDingUtils dingdingUtil;
    
    @Resource
    private DingDingDao dingdingDao;
    
    @Override
    public void dingdingSync() {
       String accToken="";
        try {
            logger.info("+++++++++++++++++开始获取accToken++++++++++++++++++");
            accToken=dingdingUtil.getAccToken();
        }
        catch (OApiException e1) {
            logger.error("+++++++++++++++++获取accToken异常,处理结束++++++++++++++++++\n",e1);
            return;
        }
        
        logger.info("++++++++++++++++++处理考勤数据+++++++++++++++++");
      //  String dayStr=getYesterDay();
        List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
        
        Map<String,String> map1=new HashMap<String,String>();
        String startDay="2018-01-05 00:00:00";
        String endDay ="2018-01-11 23:59:59";
        map1.put("startDay", startDay);
        map1.put("endDay", endDay);
        Map<String,String> map2=new HashMap<String,String>();
        startDay="2017-12-03 00:00:00";
        endDay ="2017-12-09 23:59:59";
        map2.put("startDay", startDay);
        map2.put("endDay", endDay);
        Map<String,String> map3=new HashMap<String,String>();
        startDay="2017-12-10 00:00:00";
        endDay ="2017-12-16 23:59:59";
        map3.put("startDay", startDay);
        map3.put("endDay", endDay);
        Map<String,String> map4=new HashMap<String,String>();
        startDay="2017-12-17 00:00:00";
        endDay ="2017-12-23 23:59:59";
        map4.put("startDay", startDay);
        map4.put("endDay", endDay);
        
        Map<String,String> map5=new HashMap<String,String>();
        startDay="2017-12-24 00:00:00";
        endDay ="2017-12-26 23:59:59";
        map5.put("startDay", startDay);
        map5.put("endDay", endDay);
        mapList.add(map1);
        /*mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);*/
        
       
        List<LocalEmp> empList=dingdingDao.queryEmpList();
        logger.info("++++++++++++++++++切换到考勤数据库+++++++++++++++++");
        
        DataSourceContextHolder. setDbType("sqlServerSource");
        logger.info("++++++++++++++++++切换成功+++++++++++++++++");       

     for(LocalEmp emp:empList){
         //目前只处理信息管理部数据,查询前一天的考勤数据
         if(emp.getOrgId().equals("1181300000")){
             for(Map<String,String> m:mapList) {
             AttendanceReq ar=new AttendanceReq();
             ar.setUserId(emp.getUserid());
             ar.setWorkDateFrom(m.get("startDay"));
             ar.setWorkDateTo(m.get("endDay"));
             AttendanceResp resp=dingdingUtil.getAttendanceList(accToken,ar);
             if(resp!=null&&resp.getRecordresult()!=null&&resp.getRecordresult().size()>0){
                 Integer userId=dingdingDao.queryKQuserId(emp.getUserid());
                 if(userId==null){
                     logger.warn(emp.getName()+"同步钉钉考勤异常,无法找到考勤对应的用户ID");
                     continue;
                 }
                 for (AttendaceDetail ad : resp.getRecordresult()) {
                     if (!ad.getTimeResult().equals("NotSigned")) {
                         try {
                             CheckInOut ci = new CheckInOut();
                             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                             String str = sdf.format(new Date(ad.getUserCheckTime()));
                             ci.setChecktime(str);
                             ci.setChecktype("I");
                             ci.setUserid(userId);
                             ci.setVerifycode(1);
                             dingdingDao.insertKQ(ci);
                         }
                         catch (Exception e) {
                             logger.error(emp.getName() + "同步钉钉考勤异常", e);
                         }
                     }
                 }
             }
         }
         
      }
        }
      
        //切换回来
        logger.info("++++++++++++++++++处理结束，切换到smart work数据源+++++++++++++++++");
        DataSourceContextHolder. setDbType("userDataSource");
        logger.info("+++++++++++++++++切换到smart work数据源成功+++++++++++++++++");
    }

    public String getYesterDay() {
        Date date = new Date();
       // 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);// 把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
}
