package com.human.jobs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.human.jzbTest.dao.JzbQuestionPaperDao;
import com.human.jzbTest.dao.JzbStudentDao;
import com.human.jzbTest.entity.JzbQuestionPaper;
import com.human.jzbTest.entity.JzbStudentDto;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;


@Component("checkJzbPaperXdfJob")
public class CheckJzbPaperXdfJob {
	
    private final Logger logger = LogManager.getLogger(CheckJzbPaperXdfJob.class);
    
	@Resource
	private JzbQuestionPaperDao paperDao;
    
	@Resource
	private JzbStudentDao jzbStudentDao;
    
    public void checkJzbPaperXdfJob(JobExecutionContext context) {  
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -7);
            Date d = c.getTime();
            List<JzbStudentDto> list = jzbStudentDao.getRecentPaper(d);
            for(JzbStudentDto stu:list){
                String realBmCodes = getClassCode(stu.getName(),stu.getPhone(),"25",d,new Date()); 
                if(StringUtils.isNotEmpty(realBmCodes)){
                    JzbQuestionPaper paper = new JzbQuestionPaper();
                    paper.setId(stu.getId());
                    paper.setRealBmCodes(realBmCodes);
                    paperDao.updateByPrimaryKeySelective(paper);
                }
            }
            
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("更新尖子班学员实报班级定时任务异常---",e.getMessage());
		}
    }  
    
    private String getClassCode(String name,String phone,String messageId,Date startDate,Date endDate){
        String classCode="";
        String method = "GetOrderList";//请求方法
        String sendUrl = "http://bm.xdf.cn/Apiext/QueryOrder/GetOrderList";//请求Url
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d hh:mm:ss");       
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("method", method);
        map.put("requestTime", sdf.format(new Date()));
        map.put("appId", "wechatApp");
        map.put("schoolId",messageId);
        map.put("name",name);
        map.put("classCode", "");
        map.put("phone",phone);
        String signature = XdfGeneralRequestSignature.generalRequestSignature(map);
        if (signature.equals("")){
            return classCode;
        }        
        map.put("signMethod", "MD5");
        map.put("signature", signature);
        try {
            String result = XdfPostDataOperate.sendPostDataToWechatUser(map,sendUrl);
            if(StringUtils.isNotEmpty(result)){
                JSONObject jo = new JSONObject(result);
                if(jo != null && jo.has("State") && jo.getInt("State") == 1 && jo.has("Data")){
                    JSONArray ja = jo.getJSONArray("Data");
                    if(ja != null && ja.length() > 0){                    
                        for(int i =0; i < ja.length(); i++){
                            JSONObject job = ja.getJSONObject(i);
                            if(job.has("dtInDate")){
                                String dtInDate = job.getString("dtInDate");
                                String enrollTime=dtInDate.substring(dtInDate.indexOf("(")+1, dtInDate.indexOf(")"));
                                //报名时间
                                Date date = new Date(Long.valueOf(enrollTime));
                                System.out.println("报名时间为===="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));                                
                                //判断报名时间是否为签到结束时间到一周以内
                                boolean bHasPayed=job.getBoolean("bHasPayed"); 
                                System.out.println("是否已付款===="+bHasPayed);
                                if(date.after(startDate) && date.before(endDate) && (bHasPayed) ){
                                    String classCodes=job.getString("sClassCode");
                                    classCode+=classCodes+",";                                    
                                }                                
                            }
                        }
                    }
                }
                if(StringUtils.isNotEmpty(classCode)){
                    classCode=classCode.substring(0,classCode.length()-1);              
                } 
            }             
        }catch (Exception e) {
            e.printStackTrace();
        }
        return classCode;
    }
}
