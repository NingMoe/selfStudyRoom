package com.human.sign.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.sign.dao.SignActivityDao;
import com.human.sign.dao.SignInfoDao;
import com.human.sign.entity.SignActivity;
import com.human.sign.entity.SignInfo;
import com.human.sign.service.SignInfoService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;
import com.human.utils.PlaceholderUtils;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;

@Service
public class SignInfoServiceImpl implements SignInfoService {
    
    private final Logger logger = LogManager.getLogger(SignInfoServiceImpl.class);
    
    @Resource
    private SignInfoDao siDao;
    
    @Resource
    private SignActivityDao saDao;
    
    @Resource
    private SmsTempService smsService;

    @Override
    public PageView querySignInfoByPage(PageView pageView, SignInfo info) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", info);
        List<SignInfo> list = siDao.query(map);
        if(CollectionUtils.isNotEmpty(list)){
            //调用报班接口获取学员号
            for(SignInfo signInfo:list){
                System.out.println("姓名:"+signInfo.getName()+",手机号:"+signInfo.getTelephone());
                String studentCode=getStudentCodeByPhone(signInfo.getName(),signInfo.getTelephone());
                signInfo.setStudentCode(studentCode);
            }
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> addSignInfo(SignInfo info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            //判断手机号码填写是否正确
            if(!Common.isMobile(info.getTelephone())) {
                map.put("flag", false);
                map.put("message", "手机号码格式不正确!");  
                return map;
            }
            info.setCreateUser(Common.getMyUser().getUsername());
            info.setCreateTime(new Date());
            siDao.insertSelective(info);            
            map.put("flag", true);
            map.put("message", "新增成功!"); 
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该手机号码已经存在，请输入其它手机号码!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public SignInfo selectByPrimaryKey(Long id) {
        return siDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> editSignInfo(SignInfo info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            //判断手机号码填写是否正确
            if(!Common.isMobile(info.getTelephone())) {
                map.put("flag", false);
                map.put("message", "手机号码格式不正确!");  
                return map;
            }
            info.setUpdateUser(Common.getMyUser().getUsername());
            info.setUpdateTime(new Date());
            if("2".equals(info.getIsSign())){
                siDao.updateIsSign(info.getId());
            }
            siDao.updateByPrimaryKeySelective(info);            
            map.put("flag", true);
            map.put("message", "编辑成功!"); 
            //已签到的话异步发送一条短信
            if("1".equals(info.getIsSign())){
                sendSignSuccessMessage(info);
            }
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该手机号码已经存在，请输入其它手机号码!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
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
                System.out.println("签到明细列表通过姓名手机号获取学员号result=="+result);
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

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> updateStatus(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] infoIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", infoIds);
            paraMap.put("updateUser", Common.getAuthenticatedUsername());
            siDao.updateStatusByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public void downLoadSignInfoExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileTmp = request.getSession().getServletContext().getRealPath("/static/temp/importSignInfo.xlsx");
        String fileName = "导入签到人员数据模板.xlsx";
        try {
            File file = new File(fileTmp);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
            InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            int size = (int) file.length();
            byte[] b = new byte[size];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> uploadLoadSignInfoExcel(Long activityId, MultipartFile file) {
        Map<String,Object> map = new HashMap<String, Object>();
        String createUser=Common.getMyUser().getUsername();
        Date  createTime=new Date();
        try {
            ExcelUtil<SignInfo> ex=new ExcelUtil<SignInfo>(1,0);
            Map<String,Object> result=ex.checkAccount(file,SignInfo.class);
            if(null!=result&&result.get("flag").toString().equals("true")){
                @SuppressWarnings("unchecked")
                List<SignInfo> list=(List<SignInfo>)result.get("list");
                if(list == null || list.size()==0){
                    map.put("flag", false);
                    map.put("message", "表格数据不能为空！");
                    return map;
                }
                //第一步：先检验execl表格内容是否符合模板要求，不符合直接导入不成功
                int z = 1;
                for(SignInfo info:list){
                    z++;  
                    //判断姓名是否为空
                    if(StringUtils.isEmpty(info.getName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行姓名为空!");
                        return map;
                    }
                    //判断手机号码是否为空
                    if(StringUtils.isEmpty(info.getTelephone())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行手机号码为空!");
                        return map;
                    }
                    //判断性别是否正确
                    if(StringUtils.isNotEmpty(info.getSex())){
                        if(!"男".equals(info.getSex()) && !"女".equals(info.getSex())){
                            map.put("flag", false);
                            map.put("message", "表格第"+z+"行性别填写不正确!");
                            return map;
                        }
                    }  
                }
                //第二步:保存签到人员数据相关字段
                String sex="";
                for(SignInfo info:list){                   
                    if("男".equals(info.getSex())) {
                        sex="M";
                    }else if ("女".equals(info.getSex())) {
                        sex="F";
                    } 
                    info.setSex(sex);
                    info.setActivityId(activityId);
                    info.setCreateUser(createUser);
                    info.setCreateTime(createTime);
                }
                //第三步:批量保存签到人员数据
                siDao.insertByBatch(list);                
                map.put("flag", true);
                map.put("message", "上传签到人员数据成功！");  
            }else{
                map.put("flag", false);
                map.put("message",result.get("errorMessage"));    
            } 
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "表格数据存在相同的手机号码!请删除后重新上传!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "上传签到人员数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }

    @Override
    public Map<String, Object> exportData(Long activityId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        SignActivity  activityInfo =saDao.selectByPrimaryKey(activityId);
        String activityName="";
        if(activityInfo!=null){
            activityName=activityInfo.getActivityName();
        }       
        List<Map<String,Object>> maplist =siDao.exportSelectInfo(activityId);
        for(Map<String,Object> map1:maplist){
            String name=(String) map1.get("name");
            String telephone=(String) map1.get("telephone");
            System.out.println("姓名:"+name+",手机号:"+telephone);
            String studentCode=getStudentCodeByPhone(name,telephone);
            map1.put("studentCode", studentCode);
        }
        ExcelUtil<SignInfo> ex=new ExcelUtil<SignInfo>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp")+"/";
        System.out.println("导出路径===="+path);
        try {
            ex.writeExcel(path+"exportSignInfo.xlsx", SignInfo.class, maplist, response, activityName+"签到明细", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();        
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> fastSign(SignInfo info) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //第一步：判断该手机号是否存在
            SignInfo signInfo=siDao.checkSignInfo(info);
            if(signInfo==null) {
                map.put("flag", false);
                map.put("message", "手机号对应的人员不存在!"); 
                return map;
            }
            //第二步：判断该手机号对应的人员是否已经签到
            String isSign=signInfo.getIsSign();
            if("1".equals(isSign)) {
                map.put("flag", false);
                map.put("message", "手机号对应的人员已签到!"); 
                return map;
            } 
            //第三步：签到
            signInfo.setIsSign("1");
            Date signTime=new Date();
            signInfo.setSignTime(signTime);
            signInfo.setUpdateUser(Common.getMyUser().getUsername());
            signInfo.setUpdateTime(signTime);
            siDao.updateByPrimaryKeySelective(signInfo);
            map.put("flag", true);
            map.put("message", "签到成功!");
            //签到成功后异步发送短信
            sendSignSuccessMessage(signInfo);
        }catch(Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "快速签到失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
        }
        return  map;
    }
    
    /**
     * 签到成功发送短信
     * @param orderInfoId
     */
    @Async
    public void sendSignSuccessMessage(SignInfo info){        
        try{
            Long activityId=info.getActivityId();
            SignActivity signActivity =saDao.selectByPrimaryKey(activityId);
            if(signActivity!=null){
                //判断是否启用了发送短信
                if("1".equals(signActivity.getIsSend())){
                    String temDesc=signActivity.getTemDesc();
                    if(StringUtils.isNotEmpty(temDesc)){
                        SignInfo  signInfo =siDao.selectByPrimaryKey(info.getId());
                        String message=getSendMessage(signInfo,signActivity,temDesc);
                        SmsRecord smsRecord = new SmsRecord();
                        smsRecord.setSendTel(signInfo.getTelephone());
                        smsRecord.setCompany("25");
                        smsRecord.setSmsType("3"); 
                        smsRecord.setSendComment(message);
                        smsService.sendMessage(smsRecord);   
                    }
                }             
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("签到成功后发送短信失败!", e.getMessage());
        }
    }
    
    /**
     * 拼接发送短信内容
     * @return
     */
    public String getSendMessage(SignInfo info,SignActivity signActivity,String temDesc){
        String message="";
        try{
            Map<String,String> templateMap=new HashMap<String,String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
            if(temDesc.indexOf("sign_name")!=-1){
                templateMap.put("sign_name",info.getName());
            }
            if(temDesc.indexOf("sign_time")!=-1){
                templateMap.put("sign_time",sdf.format(info.getSignTime()));                
            }
            if(temDesc.indexOf("sign_mobile")!=-1){
                templateMap.put("sign_mobile",info.getTelephone());                
            }
            if(temDesc.indexOf("sign_sex")!=-1){
                String sex="";
                if("M".equals(info.getSex())){
                    sex="男";
                }else if("F".equals(info.getSex())){
                    sex="女";
                }
                templateMap.put("sign_sex",sex);                
            }
            if(temDesc.indexOf("sign_email")!=-1){
                templateMap.put("sign_email",info.getEmail());            
            }
            if(temDesc.indexOf("sign_deptOrSchool")!=-1){
                templateMap.put("sign_deptOrSchool",info.getDeptorschool());                
            }
            if(temDesc.indexOf("sign_exportDate")!=-1){
                templateMap.put("sign_exportDate",info.getExportDate());                
            }
            if(temDesc.indexOf("sign_text1")!=-1){
                templateMap.put("sign_text1",info.getText1());                
            }
            if(temDesc.indexOf("sign_text2")!=-1){
                templateMap.put("sign_text2",info.getText2());                
            }
            if(temDesc.indexOf("sign_text3")!=-1){
                templateMap.put("sign_text3",info.getText3());                
            }
            if(temDesc.indexOf("sign_activityName")!=-1){
                templateMap.put("sign_activityName",signActivity.getActivityName());                
            }            
            if(temDesc.indexOf("sign_startTime")!=-1){
                templateMap.put("sign_startTime",sdf.format(signActivity.getStartTime()));                
            }
            if(temDesc.indexOf("sign_endTime")!=-1){
                templateMap.put("sign_endTime",sdf.format(signActivity.getEndTime()));                
            }            
            message = PlaceholderUtils.resolvePlaceholders(temDesc, templateMap);            
        }catch(Exception e){
           e.printStackTrace(); 
           logger.error("拼接发送短信内容失败!", e.getMessage());
        }
        return message;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> checkSignTime(Long activityId, String telOrCardNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SignActivity signActivity=saDao.selectByPrimaryKey(activityId);
            //判断签到时间是否超期
            Date  startTime=signActivity.getStartTime();
            Date  endTime=signActivity.getEndTime();
            //当前时间
            Date currDate=new Date();            
            if(currDate.before(startTime)){
                map.put("flag", false);
                map.put("message", "签到时间还未开始!");
                return map;
            }
            if(endTime.before(currDate)){
                map.put("flag", false);
                map.put("message", "签到时间已经结束!");
                return map;
            }
            //判断号码是否存在
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("activityId", activityId);
            paraMap.put("telOrCardNo",telOrCardNo);
            List<SignInfo> list=siDao.selectByPamas(paraMap);
            if(CollectionUtils.isEmpty(list)){
                map.put("flag", false);
                map.put("message", "该手机/身份证后4位对应的人员不存在!");
                return map;
            }
            //判断号码是否已签到
            boolean allFlag=true;
            for(SignInfo signInfo:list){
                String isSign=signInfo.getIsSign();
                if("2".equals(isSign)){
                    allFlag=false;
                    break;
                }
            }
            if(allFlag){
                map.put("flag", false);
                map.put("message", "您已签到,请勿重复签到!");
                return map; 
            }
            //如果号码唯一,并且未签到，那么设置为签到            
            if(list.size()==1){
                SignInfo signInfo=list.get(0);
                String isSign=signInfo.getIsSign();
                if("2".equals(isSign)){
                    signInfo.setIsSign("1");
                    signInfo.setSignTime(new Date());
                    int i=siDao.updateByPrimaryKeySelective(signInfo);  
                    if(i==1){
                        //签到成功后异步发送短信
                        sendSignSuccessMessage(signInfo);
                    }
                }
               map.put("onlyFlag", true);
               map.put("id",signInfo.getId());
            }else{
               String ids="";
               for(SignInfo signInfo:list){ 
                   ids+=signInfo.getId()+",";  
               }
               if(StringUtils.isNotEmpty(ids)){
                   ids=ids.substring(0,ids.length()-1);
                   map.put("id",ids);
               }
               map.put("onlyFlag", false);
            }
            map.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "校验签到时间失败,请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }  
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> revoke(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            siDao.updateIsSign(id);
            map.put("flag", true);
            map.put("message", "撤销签到成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "撤销签到失败,请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }

    @Override
    public List<SignInfo> selectByIds(String ids) {
        List<SignInfo> list=new ArrayList<SignInfo>(5);
        try{
            String[]signInfoIds=ids.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", signInfoIds);
            list=siDao.selectByIds(paraMap);
        }catch(Exception e){
            e.printStackTrace();
        }        
        return list;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> confirmMySign(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SignInfo signInfo=siDao.selectByPrimaryKey(id);
            //签到
            signInfo.setIsSign("1");
            signInfo.setSignTime(new Date());
            int i=siDao.updateByPrimaryKeySelective(signInfo);  
            if(i==1){
                //签到成功后异步发送短信
                sendSignSuccessMessage(signInfo);
            }
            map.put("flag", true);
            map.put("message", "签到成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "签到失败,请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }

    @Override
    public Map<String, Object> selectDeptSignDetails(SignInfo info) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deptorschool", info.getDeptorschool());
        SignActivity signActivity=saDao.selectByPrimaryKey(info.getActivityId());
        if("空".equals(info.getDeptorschool())){
            info.setDeptorschool(null);
        }
        List<SignInfo> list=siDao.selectDeptSignDetails(info);
        List<SignInfo>hasSignList=new ArrayList<SignInfo>(5);//已签到的
        List<SignInfo>noSignList=new ArrayList<SignInfo>(5);//未签到的
        for(SignInfo signInfo:list){
            if("1".equals(signInfo.getIsSign())){
                hasSignList.add(signInfo);
            }else{
                noSignList.add(signInfo);
            }
        }        
        map.put("signActivity", signActivity);
        map.put("hasSignList", hasSignList);
        map.put("noSignList", noSignList);
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> updateAndChange(Long activityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            SignActivity signActivity=saDao.selectByPrimaryKey(activityId);
            //第一步：判断时间
            Date endTime=signActivity.getEndTime();            
            //当前时间
            Date currDate=new Date();            
            if(currDate.before(endTime)){
                map.put("flag", false);
                map.put("message", "签到时间还未结束!");
                return map;
            }
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.add(Calendar.DATE, 7); 
            Date prevDate=calendar.getTime();   
            if(currDate.after(prevDate)){
                map.put("flag", false);
                map.put("message", "签到时间结束已过一个礼拜!");
                return map;
            }
            List<SignInfo> list = siDao.selectByActivityId(activityId);
            if(CollectionUtils.isNotEmpty(list)){
                //调用报班接口获取学员号
                for(SignInfo signInfo:list){
                    System.out.println("======姓名:"+signInfo.getName()+",手机号:"+signInfo.getTelephone());
                    String classCodes=getClassCodesByStudentInfo(signInfo.getName(),signInfo.getTelephone(),endTime,prevDate);
                    if(StringUtils.isNotEmpty(classCodes)){
                        signInfo.setClassCode(classCodes);
                        siDao.updateByPrimaryKeySelective(signInfo);
                    }                      
                }
            }
            map.put("flag", true);
            map.put("message", "更新转化成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新转化失败,请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }
    
    
    /**
     * 通过学员信息获取学员报班记录
     * @param studentCode
     * @return
     */
    public String getClassCodesByStudentInfo(String name,String telephone,Date startDate,Date endDate){        
        String classCode="";
        String method = "GetOrderList";//请求方法
        String sendUrl = "http://bm.xdf.cn/Apiext/QueryOrder/GetOrderList";//请求Url
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d hh:mm:ss");       
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("method", method);
        map.put("requestTime", sdf.format(new Date()));
        map.put("schoolId", "25");
        map.put("name",name);
        map.put("classCode", "");
        map.put("phone", telephone);
        map.put("appId", "wechatApp");
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
                if(jo != null && (jo.has("State") && jo.getInt("State") == 1) && (jo.has("Data") && !jo.isNull("Data"))){
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
