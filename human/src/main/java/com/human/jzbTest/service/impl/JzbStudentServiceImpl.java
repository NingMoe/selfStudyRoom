package com.human.jzbTest.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.human.jzbTest.dao.JzbDeptDao;
import com.human.jzbTest.dao.JzbStudentDao;
import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.JzbStudentDto;
import com.human.jzbTest.service.JzbStudentService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;

@Service
public class JzbStudentServiceImpl implements JzbStudentService {

    @Resource
    private JzbDeptDao  jzbDeptdao;
    
    @Resource
    private JzbStudentDao jzbStudentDao;
    
    @Override
    public PageView query(PageView pageView, JzbStudentDto jsd) {
        String company=Common.getMyUser().getCompanyId();
        List<JzbDept> jzbDepts = jzbDeptdao.getDeptsByCompany(company);
        List<Integer>daptIds=new ArrayList<Integer>();
        if(jzbDepts!=null){
            for(JzbDept d:jzbDepts){
                daptIds.add(d.getId());
            }
        }  
        Map<Object, Object> map = new HashMap<Object, Object>();        
        map.put("paging", pageView);
        map.put("t", jsd);
        if(CollectionUtils.isNotEmpty(daptIds)){
            map.put("s", daptIds);
        }
        List<JzbStudentDto> list = jzbStudentDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<JzbStudentBmRecord> getBmRecord(Integer paperId) {
        return jzbStudentDao.getBmRecord(paperId);
    }
    
    @Override
    public String getXdfStudentCode(String studentName, String mobile, String schoolId) {
        String student_code ="";
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
            return "";
        }
        
        map.put("signMethod", "MD5");
        map.put("signature", signature);
        try {
            result = XdfPostDataOperate.sendPostDataToWechatUser(map,sendUrl);
            JSONObject jo = new JSONObject(result);
            if(jo != null && jo.has("State") && jo.getInt("State") == 1 && jo.has("Data")){
                JSONArray ja = jo.getJSONArray("Data");
                if(ja != null && ja.length() > 0){
                    for(int i =0; i < ja.length(); i++){
                        JSONObject job = ja.getJSONObject(i);
                        String code = job.getString("StudentCode");
                        if(StringUtils.isNotEmpty(code)){
                            student_code += StringUtils.isNotEmpty(student_code)?","+code:code;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return student_code;
    }
}
