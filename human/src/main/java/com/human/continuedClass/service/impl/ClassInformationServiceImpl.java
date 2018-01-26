package com.human.continuedClass.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.human.continuedClass.dao.ClassDetailsDao;
import com.human.continuedClass.dao.ClassInformationDao;
import com.human.continuedClass.dao.ClassStudentsDao;
import com.human.continuedClass.dao.SchoolAreaDao;
import com.human.continuedClass.entity.ClassDetails;
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.ClassStudents;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.ClassInformationService;
import com.human.continuedClass.threadJob.BatchQueryClassThread;
import com.human.jobs.TaskExecutorUtil;
import com.human.order.utils.MD5Util;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;

@Service
public class ClassInformationServiceImpl implements ClassInformationService {
    
    @Resource
    private ClassInformationDao  cDao;
    
    @Resource
    private SchoolAreaDao saDao;
    
    @Resource
    private ClassDetailsDao cdDao;
    
    @Resource
    private ClassStudentsDao csDao;
    
    @Resource
    private TaskExecutorUtil taskExecutorUtil;
    
    @Value("${cc.getClassDetailsUrl}")
    private String getClassDetailsUrl;
    
    @Value("${cc.schoolId}")
    private int schoolId;
    
    @Value("${cc.getClassListUrl}")
    private String getClassListUrl;
    
    @Value("${cc.appId}")
    private String appId;
    
    @Value("${cc.appValue}")
    private String appValue;
    
    
    @Value("${cc.getStudentsOfClassUrl}")
    private String getStudentsOfClassUrl;
    
    @Value("${cc.method}")
    private String method;
    
    @Value("${cc.appKey}")
    private String appKey;
    
    @Value("${cc.students.appid}")
    private int appIdOfStudents;
    

    @Override
    public PageView query(PageView pageView, ClassInformation cf) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", cf);
        List<ClassInformation> list = cDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(ClassInformation cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            cDao.insertSelective(cf); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public ClassInformation selectByPrimaryKey(ClassInformation cf) {
       
        return cDao.selectByPrimaryKey(cf);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(ClassInformation cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(Common.getMyUser().getUsername());
            cf.setUpdateTime(new Date());
            cDao.updateByPrimaryKeySelective(cf); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds, int type) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            paraMap.put("type", type);
            cDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> deleteAll(ClassInformation cf) {      
        Map<String,Object> map=new HashMap<String,Object>();
        try{            
            cDao.deleteAll(cf);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public void downLoadClassExcel(HttpServletRequest request, HttpServletResponse response) {
        String fileTmp = request.getSession().getServletContext().getRealPath("/static/temp/importClass.xlsx");
        String fileName = "导入班级数据模板.xlsx";
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
    public Map<String, Object> uploadLoadClassExcel(ClassInformation cf, MultipartFile file) {
        Map<String,Object> map = new HashMap<String, Object>();
        String createUser=Common.getMyUser().getUsername();
        Date  createTime=new Date();
        long ruleId=cf.getRuleId();
        int type=cf.getType();
        try{
            ExcelUtil<ClassInformation> ex=new ExcelUtil<ClassInformation>(1,0);
            Map<String,Object> result=ex.checkAccount(file,ClassInformation.class);
            if(null!=result&&result.get("flag").toString().equals("true")){
                @SuppressWarnings("unchecked")
                List<ClassInformation> list=(List<ClassInformation>)result.get("list");
                if(list == null || list.size()==0){
                    map.put("flag", false);
                    map.put("message", "表格数据不能为空！");
                    return map;
                }
                //第一步：先检验execl表格内容是否符合模板要求，不符合直接导入不成功
                int z = 1;
                SchoolArea sa=new SchoolArea();
                for(ClassInformation cft:list){
                    z++;  
                    //判断班号是否为空
                    if(StringUtils.isEmpty(cft.getClassCode())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行班号为空!");
                        return map;
                    }
                    //判断班级名称是否为空
                    if(StringUtils.isEmpty(cft.getClassName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行班级名称为空!");
                        return map;
                    }
                    //判断校区是否为空
                    if(StringUtils.isEmpty(cft.getSchoolAreaName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行校区为空!");
                        return map;
                    }
                    //判断校区是否存在
                    sa=saDao.selectByName(cft.getSchoolAreaName());
                    if(sa==null){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行校区不存在!请在校区基础数据模块添加!");
                        return map;
                    }
                    //判断年级是否为空
                    if(StringUtils.isEmpty(cft.getGrade())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行年级为空!");
                        return map;
                    }
                    //判断科目是否为空
                    if(StringUtils.isEmpty(cft.getSubject())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行科目为空!");
                        return map;
                    }
                    //判断教师是否为空
                    if(StringUtils.isEmpty(cft.getTeacherName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行教师为空!");
                        return map;
                    }
                    //判断部门是否为空
                    if(StringUtils.isEmpty(cft.getDeptName())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行部门为空!");
                        return map;
                    }
                    //判断是否扩科是否为空
                    if(StringUtils.isEmpty(cft.getKuokeFlag())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行是否扩科为空!");
                        return map;
                    }
                    //判断是否扩科填写正确
                    if(!"是".equals(cft.getKuokeFlag()) && !"否".equals(cft.getKuokeFlag())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行是否扩科填写错误!");
                        return map; 
                    }
                    //判断是否尖子是否为空
                    if(StringUtils.isEmpty(cft.getTopFlag())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行是否尖子为空!");
                        return map;
                     } 
                    //判断是否尖子填写正确
                    if(!"是".equals(cft.getTopFlag()) && !"否".equals(cft.getTopFlag())&& !"超".equals(cft.getTopFlag())){
                        map.put("flag", false);
                        map.put("message", "表格第"+z+"行是否尖子填写错误!");
                        return map; 
                    }
                }
                //第二步:保存班级数据
                for(ClassInformation cft:list){
                    String kuokeFlag="是".equals(cft.getKuokeFlag())?"0":"1";
                    cft.setKuokeFlag(kuokeFlag);
                    String topFlag="";
                    if("是".equals(cft.getTopFlag())){
                        topFlag="0";
                    }else if("否".equals(cft.getTopFlag())){
                        topFlag="1";
                    }else{
                        topFlag="2";
                    }
                    cft.setTopFlag(topFlag);
                    cft.setRuleId(ruleId);
                    cft.setType(type);
                    cft.setCreateUser(createUser);
                    cft.setCreateTime(createTime);
                    //cDao.insertSelective(cft); 
                }
                //第三步:批量保存班级数据
                if(type==1){
                    cDao.insertOrginalClassByBatch(list);
                }else{
                    cDao.insertContinuedClassByBatch(list);
                }
                map.put("flag", true);
                map.put("message", "上传班级数据成功！");  
            }else{
                map.put("flag", false);
                map.put("message",result.get("errorMessage"));    
            } 
        }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("message", "上传班级数据失败!");
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String ids=ServletRequestUtils.getStringParameter(request, "ids");
            if(ids!=null && ids.length()>0 ){
                List<String> list = new ArrayList<String>();
                String[] idarray = ids.split(",");
                list=Arrays.asList(idarray);
                map.put("s", list);
            }
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            int type=ServletRequestUtils.getIntParameter(request, "type");
            map.put("type", type);
            map.put("ruleId", ruleId);
            List<Map<String,Object>> maplist =cDao.exportSelect(map);
            ExcelUtil<ClassInformation> ex=new ExcelUtil<ClassInformation>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportClass.xlsx", ClassInformation.class, maplist, response, TimeUtil.getCurrTime()+"班级数据信息", 0, 1);
            map.put("flag", true);
            map.put("message", "班级数据导出成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "班级数据导出失败!");
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> refresh(String classCodes) {
        Map<String,Object> map = new HashMap<String, Object>();       
        try{
            List<String> list = new ArrayList<String>();
            String[] codes = classCodes.split(",");
            list=Arrays.asList(codes);
            if(list!=null && list.size()>100){//批量更新的条数大于100条
                map.put("flag", false);
                map.put("message", "批量更新的条数不要超过100条!"); 
                return map;
            }else{
                HashSet<String> hs = new HashSet<String>(list);//去掉重复的班号
                Iterator<String> it=hs.iterator();
                String newClassCodes="";
                while(it.hasNext()){
                    newClassCodes+=it.next()+",";
                }
                classCodes=newClassCodes.substring(0,newClassCodes.lastIndexOf(",") );
                addOrUpdateCdetails(classCodes); 
            }
            map.put("flag", true);
            map.put("message", "刷新班级数据成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "刷新班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> refreshAll(ClassInformation cf) {
        Map<String,Object> map = new HashMap<String, Object>(); 
        try{
            List<Map<String,Object>> maplist =cDao.selectAllClassCodes(cf);
            String classCodes="";
            if(maplist!=null && maplist.size()>0){
                for(int i=1,size=maplist.size();i<=size;i++){
                    classCodes+=maplist.get(i-1).get("classCode")+",";
                    if(i % 100 == 0){
                        classCodes=classCodes.substring(0,classCodes.lastIndexOf(","));
                        addOrUpdateCdetails(classCodes); 
                        classCodes="";
                    }
                }
              if(classCodes!=""){
                 classCodes=classCodes.substring(0,classCodes.lastIndexOf(","));
                 addOrUpdateCdetails(classCodes); 
              }           
            }
            map.put("flag", true);
            map.put("message", "刷新班级数据成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "刷新班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
        }
        return map;
    }
    
    
    /**
     * 通过班号获取班级详情公用方法
     * @param classCodes
     */
    public  void  addOrUpdateCdetails(String classCodes){
        String user=Common.getMyUser().getUsername();
        Date  time=new Date();
        List<ClassDetails> addClassList=new ArrayList<ClassDetails>();//批量插入的班级详细信息
        //第一步:通过报名接口（我学）获取班级详细信息
        String url = getClassDetailsUrl+"?schoolId="+schoolId+"&classCodes=" +classCodes;
        String result = HttpClientUtil.httpGetRequest(url, null);
        JSONArray js = JSONArray.parseArray(result);     
        if(js!=null && js.size()>0){  
            Map<String, Object> classResult =new HashMap<String, Object>();           
            for(int i=0,size=js.size();i<size;i++){
                classResult = (Map<String,Object>)js.get(i);
                ClassDetails classDetails=new ClassDetails();
                String classCode=(String) classResult.get("classCode");
                String className=(String) classResult.get("className");
                Integer studentMaxCount=(Integer) classResult.get("studentMaxCount");
                Integer studentCurrentCount=(Integer) classResult.get("studentCurrentCount");
                String classSprintTime=(String) classResult.get("classSprintTime");
                String classAddress=(String) classResult.get("classAddress");
                String classStartDate=(String) classResult.get("classStartDate");
                String classEndDate=(String) classResult.get("classEndDate");
                String roomCode=(String) classResult.get("roomCode");
                String fee=(String) classResult.get("fee");
                String quarterCode=(String) classResult.get("quarterCode");
                String subjectName=(String) classResult.get("subjectName");
                Integer souke=(Integer) classResult.get("souke");
                Integer classStatus=(Integer) classResult.get("classStatus");
                String gradeName=(String) classResult.get("gradeName");
                String openTime=(String) classResult.get("openTime");
                List<Map<String, String>> teachList = (List<Map<String, String>>)classResult.get("teachers");
                String teacherCode="";
                String teacherName="";
                for (Map<String, String> m : teachList) {
                    teacherName = teacherName + m.get("teacherName") + ",";
                    teacherCode = teacherCode + m.get("teacherCode") + ",";
                }
                if(teacherName!=""){
                    teacherName = teacherName.substring(0, teacherName.length() - 1); 
                }
                if(teacherCode!=""){
                    teacherCode = teacherCode.substring(0, teacherCode.length() - 1); 
                }
                //保存或更新
                classDetails.setClassCode(classCode);
                classDetails.setClassAddress(classAddress);
                classDetails.setClassStartDate(classStartDate);
                classDetails.setClassEndDate(classEndDate);
                classDetails.setClassName(className);
                classDetails.setClassSprintTime(classSprintTime);
                classDetails.setClassStatus(classStatus);
                classDetails.setFee(fee);
                classDetails.setGradeName(gradeName);
                classDetails.setOpenTime(openTime);
                classDetails.setQuarterCode(quarterCode);
                classDetails.setRoomCode(roomCode);
                classDetails.setSchoolId(schoolId);
                classDetails.setSouke(souke);
                classDetails.setStudentCurrentCount(studentCurrentCount);
                classDetails.setStudentMaxCount(studentMaxCount);
                classDetails.setSubjectName(subjectName);
                classDetails.setTeacherCode(teacherCode);
                classDetails.setTeacherName(teacherName);
                ClassDetails  cDetails=cdDao.selectByClassCode(classCode);
                if(cDetails==null){
                    classDetails.setCreateUser(user);
                    classDetails.setCreateTime(time);
                    addClassList.add(classDetails);
                }else{
                    classDetails.setUpdateUser(user); 
                    classDetails.setUpdateTime(time);
                    classDetails.setId(cDetails.getId());
                    cdDao.updateByPrimaryKeySelective(classDetails);
                }
            }    
        }
        //批量插入
        if(addClassList!=null && addClassList.size()>0){  
          cdDao.insertByBatch(addClassList);
        }       
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> getStudentsByClassCode(String classCodes,Long ruleId) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            List<String> list = new ArrayList<String>();
            String[] codes = classCodes.split(",");
            list=Arrays.asList(codes);
            HashSet<String> hs = new HashSet<String>(list);//去掉重复的班号
            List<String> list1 = new ArrayList<String>(hs);  
            addOrUpdateStudents(list1,ruleId);
            map.put("flag", true);
            map.put("message", "通过班号获取班级学员成功!"); 
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "通过班号获取班级学员失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> getAllStudents(ClassInformation cf) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            List<Map<String,Object>> maplist =cDao.selectAllClassCodes(cf);
            List<String> list = new ArrayList<String>();
            if(maplist!=null && maplist.size()>0){
                for(int i=0,size=maplist.size();i<size;i++){
                    list.add((String) maplist.get(i).get("classCode"));
                }
                addOrUpdateStudents(list,cf.getRuleId());
            }
            map.put("flag", true);
            map.put("message", "获取全部班级学员成功!"); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取全部班级学员失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }
    
    /**
     * 通过班号获取学员公用方法
     * @param list
     */
    @SuppressWarnings("unchecked")
    public void addOrUpdateStudents(List<String> list,Long ruleId) {
        try {
            String user = Common.getMyUser().getUsername();
            Date time = new Date();
            String url = getStudentsOfClassUrl;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("method", method);
            params.put("appid", appIdOfStudents);
            params.put("schoolId", schoolId);
            String result = "";
            List<ClassStudents> addStuList = new ArrayList<ClassStudents>();// 批量插入的学员
            List<ClassStudents> updateStuList = new ArrayList<ClassStudents>();// 批量更新的学员
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
                                ClassStudents cs = new ClassStudents();
                                classResult = (Map<String, Object>) js.get(i);
                                String name = (String) classResult.get("Name");
                                String code = (String) classResult.get("Code");
                                String sCardCode = (String) classResult.get("sCardCode");
                                cs.setClassCode(classCode);
                                cs.setCode(code);
                                cs.setName(name);
                                cs.setsCardCode(sCardCode);
                                cs.setSchoolId(schoolId);
                                cs.setRuleId(ruleId);
                                ClassStudents cStudents = csDao.select(cs);
                                if (cStudents == null) {
                                    cs.setCreateUser(user);
                                    cs.setCreateTime(time);
                                    addStuList.add(cs);
                                }
                                else {
                                    cs.setUpdateUser(user);
                                    cs.setUpdateTime(time);
                                    cs.setId(cStudents.getId());
                                    updateStuList.add(cs);
                                }
                            }
                        }
                    }
                }
            }
            // 批量插入学员
            if (addStuList != null && addStuList.size() > 0) {
                HashSet<ClassStudents> ts = new HashSet<ClassStudents>(addStuList); //去重
                addStuList= new ArrayList<ClassStudents>(ts);
                csDao.insertByBatch(addStuList);
            }
            // 批量更新学员
            if (updateStuList != null && updateStuList.size() > 0) {
                HashSet<ClassStudents> ts = new HashSet<ClassStudents>(updateStuList);//去重
                updateStuList= new ArrayList<ClassStudents>(ts);
                csDao.updateBatch(updateStuList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Map<String, Object> delAllStudents(ClassInformation cf) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{            
            csDao.deleteByRuleId(cf.getRuleId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> getAllStudentsByDate(ClassInformation cf) {
        Map<String,Object> map = new HashMap<String, Object>();      
        FutureTask<Boolean> dbtask =null;
        try{
            long ruleId=cf.getRuleId();
            List<String>codes=csDao.getStudentCode(ruleId);
            if(codes==null ||codes.size()<=0){
                map.put("flag", false);
                map.put("message", "没有学员,请先点击获取全部学员按钮获取学员!"); 
                return  map;
            }else{
                //开启多个线程
                List<FutureTask<Boolean>> taskResults = new ArrayList<FutureTask<Boolean>>(10);
                int totalcount=codes.size();    
                int pagecount=0,pagesize=1000; 
                if(totalcount>=5000){
                    pagesize=2000;
                }
                int m=totalcount%pagesize;    
                if(m>0){    
                  pagecount=totalcount/pagesize+1;    
                }else{    
                  pagecount=totalcount/pagesize;    
                }    
                for(int i=1;i<=pagecount;i++){
                    List<String> subList=new ArrayList<String>(pagesize);
                    if(i==pagecount){
                         subList= codes.subList((i-1)*pagesize,totalcount);    
                    }else{
                         subList= codes.subList((i-1)*pagesize,pagesize*(i));
                    }
                    BatchQueryClassThread at=new BatchQueryClassThread(csDao,subList,cf);
                    dbtask = new FutureTask<Boolean>(at);  
                    taskResults.add(dbtask);
                    taskExecutorUtil.getTaskExecutor().submit(dbtask);
                }
                while(true){
                    boolean isAllDone = true;
                    for (FutureTask<Boolean> taskResult : taskResults) {
                        isAllDone &= ( taskResult.isDone()||taskResult.isCancelled());
                    }
                    if(isAllDone){
                        map.put("flag", true);
                        map.put("message", "按开课日期获取全部学员成功!");  
                        break;
                    }
                }    
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "按开课日期获取全部学员失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
        }
        return map;
    }
    
  
}
