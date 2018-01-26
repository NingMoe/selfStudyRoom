package com.human.nologin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.ClassBizBatch;
import com.human.basic.entity.ClassMessage;
import com.human.basic.entity.ClassNo;
import com.human.basic.entity.ClassNotice;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.XdfClassInfoService;
import com.human.utils.HttpClientUtil;

@Controller
@RequestMapping("/free/entry/")
public class FreeInterfaceController {
	
	private final  Logger logger = LogManager.getLogger(FreeInterfaceController.class);
	    
	@Value("${interfaceKey}")
	private  String interfaceKey;
	
	@Value("${jw.tclassurl}")
    private  String tclassurl;
	
	@Resource
	private XdfClassInfoService xdfClassInfoService;
	
	    
    @ResponseBody
    @RequestMapping(value = "classNotify")
    public Map<String,Object> getKfUrlInfo(@RequestBody ClassNotice notice,String appKey,String noticeType){
        logger.info("进入报班提醒");
        Map<String,Object> result = new HashMap<String,Object>();
        String getBodyJson=notice.getBodyJson();
        List<ClassNo> GetJson =(List<ClassNo>)JSON.parseArray(getBodyJson, ClassNo.class);
        
        try{
            for(ClassNo c : GetJson ){
                System.out.println(c.getnSchoolId());
                if(!c.getnSchoolId().equals("25")){
                    continue;
                }
                dealClassInfo(notice,c);
            }
            result.put("flag",true);
            result.put("message","成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag",false);
            result.put("message","消息传输失败");
        }
        logger.info("班级处理结束：共接收班级"+GetJson.size()+"条");
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "registClassNotify")
    public Map<String,Object> registClassNotify(@RequestBody ClassNotice notice,String appKey,String noticeType){
        logger.info("进入报班提醒");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            String getBodyJson=notice.getBodyJson();
            System.out.println(getBodyJson);
            ClassMessage classMessage = JSON.parseObject(getBodyJson, ClassMessage.class);
            if(classMessage.getnSchoolId().equals("25")){
                ClassBizBatch currentBizBatch = classMessage.getCurrentBizBatch();
                currentBizBatch.setnSchoolId(classMessage.getnSchoolId());
                currentBizBatch.setNoticeType(noticeType);
                xdfClassInfoService.insertXdfClassOrderInfo(currentBizBatch);
            }
            result.put("flag",true);
            result.put("message","成功");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("消息处理失败");
            result.put("flag",false);
            result.put("message","消息处理失败");
        }
        return result;
    }
    
    @Async
    private void dealClassInfo(ClassNotice notice,ClassNo c){
        new Timer().schedule(new TimerTask(){
            public void run() {
                String jstr =HttpClientUtil.httpGetRequest(tclassurl+"?nSchoolID=25&sClassCodes="+c.getsCode(), null);
                System.out.println(jstr);
                String state = JSON.parseObject(jstr).get("State").toString();
                if("1".equals(state)){
                    List<XdfClassInfo> list=(List<XdfClassInfo>)JSON.parseArray(JSON.parseObject(jstr).getString("Data"), XdfClassInfo.class);
                    for (XdfClassInfo xdfClassInfo : list) {
                        xdfClassInfo.setRouteKey(notice.getRouteKey());
                        xdfClassInfo.setnAudit(c.getnAudit());
                        xdfClassInfoService.syncClassInfo(xdfClassInfo);
                    }
                }
        }},10000);
    }
    
    public static void main(String[] args) {
        //http://apibm.staff.xdf.cn/api/NisClassApi/GetClassInfo
        String jstr =HttpClientUtil.httpGetRequest("http://apibm.staff.xdf.cn/api/NisClassApi/GetClassInfo"+"?nSchoolID=25&sClassCodes=Z6GM1813052", null);
        System.out.println(jstr);
    }
}