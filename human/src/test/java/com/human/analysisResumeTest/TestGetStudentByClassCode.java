package com.human.analysisResumeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.human.order.utils.MD5Util;
import com.human.utils.HttpClientUtil;
import com.human.utils.TimeUtil;
import com.human.xdfStudent.entity.XdfStudentInfo;

public class TestGetStudentByClassCode {

    public static void main(String[] args) {
        String classCodes="PM1834051";
        List<XdfStudentInfo> list=serachXdfStudentByclassCodes(classCodes);
        System.out.println("班级总人数2===="+list.size());
        for(XdfStudentInfo xdf:list){
            System.out.println("姓名:"+xdf.getStudentName()+",手机号:"+xdf.getTelephone1());
        }
    }

    public static List<XdfStudentInfo> serachXdfStudentByclassCodes(String classCodes) {
        List<XdfStudentInfo> stuList = new ArrayList<XdfStudentInfo>();
        try{
            List<String> list = new ArrayList<String>();
            String[] codes = classCodes.split(",");
            list=Arrays.asList(codes);
            int schoolId=25; 
            stuList =getStudentsByclassCodes(list,schoolId); 
            System.out.println("班级总人数1===="+stuList.size());
            if(CollectionUtils.isNotEmpty(stuList)){
                String studentCode="";
                for(XdfStudentInfo xs:stuList){
                    //通过学员号查询学员信息
                    studentCode=xs.getStudentCode();
                    selectByStudentCode(studentCode,schoolId,xs);
                }
            } 
        }catch(Exception e){
            e.printStackTrace();
        }  
        return stuList;
    }
    
    public static List<XdfStudentInfo> getStudentsByclassCodes(List<String> list,int schoolId) {
        List<XdfStudentInfo> stuList = new ArrayList<XdfStudentInfo>();//学员
        try {
            String url = "http://i.xdf.cn/api/class";
            String method="GetStudentsOfClass";
            String appIdOfStudents="5038";
            String appKey="v5k-hfsw-9e9541a6d078";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("method", method);
            params.put("appid", appIdOfStudents);
            params.put("schoolId", schoolId);
            String result = "";            
            for (String classCode : list) {
                params.put("classcode", classCode);
                String signText = ("Method=" + method + "&Appid=" + appIdOfStudents + "&schoolId=" + schoolId + "&classcode=" + classCode + "&appkey=" + appKey).toLowerCase();
                String sign = MD5Util.MD5Encode(signText, null).toUpperCase();
                params.put("sign", sign);
                result = HttpClientUtil.httpPostRequest(url, null, params);
                if (result != "") {
                    Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(result);
                    if (resultMap != null && resultMap.size() > 0) {
                        String data = resultMap.get("Data").toString();
                        JSONArray js = JSONArray.parseArray(data);
                        if (js != null && js.size() > 0) {
                            Map<String, Object> classResult = new HashMap<String, Object>();
                            for (int i = 0, size = js.size(); i < size; i++) {
                                XdfStudentInfo cs = new XdfStudentInfo();
                                classResult = (Map<String, Object>) js.get(i);
                                String name = (String) classResult.get("Name");
                                String code = (String) classResult.get("Code");                                
                                cs.setStudentCode(code);
                                cs.setStudentName(name);  
                                stuList.add(cs);
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return stuList;
    }
    
    @SuppressWarnings("rawtypes")
    public static void selectByStudentCode(String studentCode,int schoolId,XdfStudentInfo xs){
        try{
            String url = "http://wxpay.xdf.cn/xdfturnback/GetStudentInfo.do";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date",TimeUtil.getCurrTime());
            params.put("studentCode", studentCode);
            params.put("schoolId", schoolId);
            String result = HttpClientUtil.httpPostRequest(url, null, params);
            if(StringUtils.isNotEmpty(result)){
                Map map = (Map) JSON.parse(result);
                if(map.get("State").toString().equals("1") && map.get("Data")!=null){
                    String data =  map.get("Data").toString();
                    Map map1 = (Map) JSON.parse(data);
                    String schoolName=map1.get("Address").toString();
                    if(schoolName.startsWith("|")){
                        schoolName=schoolName.substring(1);
                    }
                    schoolName=schoolName.replace("安徽省","").replace("安徽","").replace("合肥市","").replace("合肥","");
                    String telephone1=map1.get("Mobile").toString();
                    String telephone2=map1.get("Phone").toString(); 
                    xs.setSchoolName(schoolName);
                    xs.setTelephone1(telephone1);
                    xs.setTelephone2(telephone2);
                }
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
