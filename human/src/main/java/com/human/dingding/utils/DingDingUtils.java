package com.human.dingding.utils;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.human.dingding.bean.AttendanceReq;
import com.human.dingding.bean.AttendanceResp;
import com.human.dingding.bean.DeptInfo;
import com.human.dingding.bean.DeptListResponse;
import com.human.dingding.bean.Emp;
import com.human.dingding.bean.EmpListRepsponse;
import com.human.dingding.bean.EmpRepsonse;
import com.human.utils.RedisTemplateUtil;

@Component
public class DingDingUtils {

    private final Logger logger = LogManager.getLogger(DingDingUtils.class);
    
    @Resource
    private RedisTemplateUtil redis;
    
    @Value("${DINGDING_ACCTOKEN}")
    private  String ACCTOKEN_KEY;
    
    @Value("${DINGDING_CORPID}")
    private String Corpid;
  
    @Value("${DINGDING_CORPSECRET}")
    private String CorpSecret;
    
    public  String getAccToken() throws OApiException{
        String accToken="";
           /* if(!redis.isExist(ACCTOKEN_KEY)){*/
                JSONObject s1=HttpHelper.httpGet("https://oapi.dingtalk.com/gettoken?corpid="+Corpid+"&corpsecret="+CorpSecret);
                accToken= (String) s1.get("access_token");
            /*    redis.set(ACCTOKEN_KEY, accToken, (long) 7200);
            }else{
                accToken=(String) redis.get(ACCTOKEN_KEY);
            }*/
            return accToken;
    }
    
    /**
     * 更新钉钉部门
     * @param accToken
     * @param info
     * @return
     */
    public boolean updateDept(String accToken, DeptInfo info) {
        try {
             Thread.sleep(500);
             HttpHelper.httpPost("https://oapi.dingtalk.com/department/update?access_token="+accToken, info);
             return true;
        }
        catch (Exception e) {
            logger.error("更新部门失败", e);
        }
        return false;
    }

    public String addDept(String accToken, DeptInfo di) {
        try {
            Thread.sleep(500);
            JSONObject s = HttpHelper.httpPost("https://oapi.dingtalk.com/department/create?access_token="+accToken, di);
            if (0 ==s.getIntValue("errcode")) {
                return s.getString("id");
            }
        }
        catch (Exception e) {
            logger.error("新增部门失败", e);
        }
        return "";
    }
    
    public DeptListResponse getDeptList(String accToken,String id){
        try {
            Thread.sleep(500);
            JSONObject js=HttpHelper.httpGet("https://oapi.dingtalk.com/department/list?access_token="+accToken);
            DeptListResponse rp=JSONObject.toJavaObject(js, DeptListResponse.class);
            return rp;
        }
        catch (Exception e) {
            logger.error("获取部门列表失败", e);
        }
        return null;
    }

    public boolean deleteDept(String accToken, String id) {
        try {
            Thread.sleep(500);
            JSONObject s=HttpHelper.httpGet("https://oapi.dingtalk.com/department/delete?access_token="+accToken+"&id="+id);
            if (0 ==s.getIntValue("errcode")) {
                return true;
            }
       }
       catch (Exception e) {
           logger.error("更新部门失败", e);
       }
       return false;
    }

    public EmpListRepsponse getEmpList(String accToken, String deptId) {
        try {
            Thread.sleep(500);
            JSONObject js=HttpHelper.httpGet("https://oapi.dingtalk.com/user/simplelist?access_token="+accToken+"&department_id="+deptId);
            EmpListRepsponse rp=JSONObject.toJavaObject(js, EmpListRepsponse.class);
            return rp;
        }
        catch (Exception e) {
            logger.error("获取人员列表失败", e);
        }
        return null;
    }
    
    public EmpRepsonse getEmpInfo(String accToken, String userid) {
        try {
            Thread.sleep(500);
            JSONObject js=HttpHelper.httpGet("https://oapi.dingtalk.com/user/get?access_token="+accToken+"&userid="+userid);
            EmpRepsonse rp=JSONObject.toJavaObject(js, EmpRepsonse.class);
            if(null!=rp&&rp.getErrcode()==0){
                return rp;
            }
            logger.error("===============获取结果==============",JSONObject.toJSONString(rp));
            return null;
        }
        catch (Exception e) {
            logger.error("获取人员列表失败", e);
        }
        return null;
    }

    public boolean updateEmp(String accToken, Emp emp) {
        try {
            Thread.sleep(500);
            JSONObject s = HttpHelper.httpPost("https://oapi.dingtalk.com/user/update?access_token="+accToken, emp);
            if (0 ==s.getIntValue("errcode")) {
                return true;
            }
        }
        catch (Exception e) {
            logger.error("更新员工失败", e);
        }
        return false;
        
    }
    
    public boolean addEmp(String accToken, Emp emp) {
        try {
            Thread.sleep(500);
            JSONObject s = HttpHelper.httpPost("https://oapi.dingtalk.com/user/create?access_token="+accToken, emp);
            if (0 ==s.getIntValue("errcode")) {
                return true;
            }
        }
        catch (Exception e) {
            logger.error("新增员工失败", e);
        }
        return false;
        
    }

    public boolean delEmp(String accToken, String userid) {
        try {
            Thread.sleep(500);
            JSONObject js=HttpHelper.httpGet("https://oapi.dingtalk.com/user/delete?access_token="+accToken+"&userid="+userid);
            EmpRepsonse rp=JSONObject.toJavaObject(js, EmpRepsonse.class);
            if(rp.getErrcode()==0){
                return true;
            }
        }
        catch (Exception e) {
            logger.error("删除人员失败", e);
        }
        return false;
    }

    public AttendanceResp getAttendanceList(String accToken,AttendanceReq ar) {
        try {
            Thread.sleep(500);
            JSONObject s = HttpHelper.httpPost("https://oapi.dingtalk.com/attendance/list?access_token="+accToken, ar);
            AttendanceResp rp=JSONObject.toJavaObject(s, AttendanceResp.class);
            if (rp!=null) {
                return rp;
            }
        }
        catch (Exception e) {
            logger.error("获取员工"+ar.getUserId()+"考勤失败", e);
        }
        return null;
    }
}
