package com.human.continuation.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.human.basic.dao.XdfClassInfoDao;
import com.human.basic.entity.XdfClassInfo;
import com.human.continuation.dao.TeacherContinuationClassDao;
import com.human.continuation.dao.TeacherContinuationConfigDao;
import com.human.continuation.entity.TeacherContinuationClass;
import com.human.continuation.entity.TeacherContinuationConfig;
import com.human.continuation.service.TeacherContinuationClassService;
import com.human.jzbTest.entity.SchoolXbgx;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.entity.HrCompany;
import com.human.order.utils.MD5Util;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.HttpClientUtil;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;

@Service
public class TeacherContinuationClassServiceImpl implements TeacherContinuationClassService {
    
    @Resource
    private TeacherContinuationClassDao teacherContinuationClassDao;
    
    @Resource
    private TeacherContinuationConfigDao teacherContinuationConfigDao;
    
    @Resource
    private XdfClassInfoDao xdfClassInfoDao;
    
    @Resource
    private HrCompanyDao hrCompanyDao;
    
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    
    @Value("${rb.client_id}") 
    private String client_id;
    
    @Value("${rb.client_secret}") 
    private String client_secret;
    
    @Value("${rb.username}") 
    private String username;
    
    @Value("${rb.password}") 
    private String password;
    
    @Value("${rb.tokenUrl}") 
    private String tokenUrl;
    
    @Value("${rb.queryUrl}") 
    private String queryUrl;
    
    @Value("${rb.addUrl}") 
    private String addUrl;
    
    @Value("${rb.delUrl}") 
    private String delUrl;
    
    
    @Value("${cc.getStudentsOfClassUrl}")
    private String getStudentsOfClassUrl;
    
    @Value("${cc.schoolId}")
    private int schoolId;
    
    @Value("${cc.method}")
    private String method;
    
    @Value("${cc.appKey}")
    private String appKey;
    
    @Value("${cc.students.appid}")
    private int appIdOfStudents;
    
    /**
     * 分页查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    public PageView query(PageView page, TeacherContinuationClass teacherContinuationClass) {
        //验证参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        
        try {
            MyUser myUser = Common.getMyUser();
            teacherContinuationClass.setSchool_id(myUser.getCompanyId());
            map.put("paging", page); 
            map.put("t", teacherContinuationClass);
            
            List<TeacherContinuationClass> list = teacherContinuationClassDao.query(map);
            page.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> insert(TeacherContinuationClass teacherContinuationClass) {
        //验证参数
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "请填写学员信息");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationClass.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写续班班号");
            return map;
        }
        
        try {
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            
            
            
            if(StringUtils.isEmpty(teacherContinuationClass.getStudent_code())){
                //未填写学员号
                if(StringUtils.isEmpty(teacherContinuationClass.getStudent_name()) || StringUtils.isEmpty(teacherContinuationClass.getPhone())){
                    map.put("flag", false);
                    map.put("message", "学生姓名、手机号或者学员号二选一必填");
                    return map;
                }else{
                    //填写了姓名手机号
                    Boolean a = false;
                    String message = "";
                    List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
                    String schoolid = company_info.getMessageId();
                    for(String classcode : teacherContinuationClass.getClass_code().split(",")){
                        Map<String, Object> mapparams = new HashMap<String, Object>();
                        mapparams.put("student_name", teacherContinuationClass.getStudent_name());
                        mapparams.put("phone", teacherContinuationClass.getPhone());
                        mapparams.put("class_code", classcode);
                        mapparams.put("school_id", company_id);
                        List<TeacherContinuationClass> slist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparams);
                        if(slist != null && slist.size() > 0){
                            message += "姓名："+teacherContinuationClass.getStudent_name()+"，手机号："+teacherContinuationClass.getPhone()+"，已经添加续班"+classcode+"。<br/>";
                            continue;
                        }else{
                            XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(classcode);
                            if(xdfClassInfo != null){
                                Map<String, Object> mappara = new HashMap<String, Object>();
                                mappara.put("school_id", company_id);
                                if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                                    mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                                }
                                if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                                    mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                                }
                                if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                                    String year = "";
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                                    Date date = sdf.parse(begintime);
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    Integer month = cal.get(Calendar.MONTH)+1;
                                    Integer years = cal.get(Calendar.YEAR);
                                    if(month >= 6){
                                        year = years + 1 + "";
                                    }else{
                                        year = years + "";
                                    }
                                    mappara.put("new_fiscal_year", year);
                                }
                                List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                                if(tcclist != null && tcclist.size() > 0){
                                    for(TeacherContinuationConfig t : tcclist){
                                        //通过学员号插入升班期接口
                                        SchoolXbgx sbq = new SchoolXbgx();
                                        sbq.setnSchoolId(schoolid);
                                        sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                                        sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                                        sbq.setsStageId(t.getsStageId());
                                        sbq.setsStageName(t.getsStageName());
                                        sbq.setsStudentName(teacherContinuationClass.getStudent_name());
                                        sbq.setsMobile(teacherContinuationClass.getPhone());
                                        sbq.setsContinuedClassCode(classcode);
                                        sbq.setsStageName(t.getsStageName());
                                        xbgxs.add(sbq);
                                        Map<String, Object> mapp = addXbgx(xbgxs);
                                        if(!(boolean) mapp.get("flag")){
                                            message += "通过姓名手机号插入升班期失败，姓名："+teacherContinuationClass.getStudent_name()+",手机号："+teacherContinuationClass.getPhone()+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                                        }
                                        
                                        
                                      //通过姓名手机号获取学员号
                                        String result = getstudentcodebyphone(teacherContinuationClass.getStudent_name(),teacherContinuationClass.getPhone(),company_info.getMessageId());
                                        JSONObject jo = new JSONObject(result);
                                        if(jo != null && jo.has("State") && jo.getInt("State") == 1 && jo.has("Data")){
                                            JSONArray ja = jo.getJSONArray("Data");
                                            if(ja != null && ja.length() > 0){
                                                for(int i =0; i < ja.length(); i++){
                                                    JSONObject job = ja.getJSONObject(i);
                                                    String student_code = job.getString("StudentCode");
                                                  //通过学员号插入续班2接口
                                                    Boolean b = addstudentcodememcached(t.getContinue_id(), student_code, classcode, schoolid);
                                                    if(!b){
                                                        message += "通过学员号插入续班2失败:学员号"+student_code+",班号"+classcode+"。<br/>";
                                                    }
                                                }
                                            }else{
                                                message += "通过姓名手机号查询不到学员号，无法插入续班2系统<br/>";
                                            }
                                        }else{
                                            message += "通过姓名手机号查询不到学员号，无法插入续班2系统<br/>";
                                        }
                                        
                                    }
                                    
                                    
                                    
                                    if(StringUtils.isNotEmpty(teacherContinuationClass.getStudent_name())){
                                        teacherContinuationClass.setStudent_name(teacherContinuationClass.getStudent_name());
                                    }
                                    
                                    if(StringUtils.isNotEmpty(teacherContinuationClass.getPhone())){
                                        teacherContinuationClass.setPhone(teacherContinuationClass.getPhone());
                                    }
                                    teacherContinuationClass.setSchool_id(myUser.getCompanyId());
                                    teacherContinuationClass.setStudent_code(teacherContinuationClass.getStudent_code());
                                    teacherContinuationClass.setClass_code(classcode);
                                    teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
                                    teacherContinuationClass.setUpdate_user(myUser.getEmailAddr());
                                    teacherContinuationClass.setUpdate_time(new Date());
                                    teacherContinuationClass.setUpdate_type(1);
                                    teacherContinuationClass.setIs_add(1);
                                    teacherContinuationClassDao.insertSelective(teacherContinuationClass);
                                }else{
                                    message += "未找到相关升班期配置，姓名："+teacherContinuationClass.getStudent_name()+",手机号："+teacherContinuationClass.getPhone()+",班号"+classcode+"。<br/>";
                                }
                            }else{
                                message += "未找到班级信息,班号:"+classcode+"。<br/>";
                            }
                            a = true;
                        }
                    }
                    message+="插入完成";
                    map.put("flag", a);
                    map.put("message", message);
                    return map;
                    
                }
            }else{
                //填写了学员号
                Boolean a = false;
                String message = "";
                List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
                String schoolid = company_info.getMessageId();
                for(String classcode : teacherContinuationClass.getClass_code().split(",")){
                    Map<String, Object> mapparams = new HashMap<String, Object>();
                    mapparams.put("student_code", teacherContinuationClass.getStudent_code());
                    mapparams.put("class_code", classcode);
                    mapparams.put("school_id", company_id);
                    List<TeacherContinuationClass> slist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparams);
                    if(slist != null && slist.size() > 0){
                        message += "学员号"+teacherContinuationClass.getStudent_code()+"已经添加续班"+classcode+"。<br/>";
                        continue;
                    }else{
                        XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(classcode);
                        if(xdfClassInfo != null){
                            Map<String, Object> mappara = new HashMap<String, Object>();
                            mappara.put("school_id", company_id);
                            if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                                mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                            }
                            if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                                mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                            }
                            if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                                String year = "";
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                                Date date = sdf.parse(begintime);
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                Integer month = cal.get(Calendar.MONTH)+1;
                                Integer years = cal.get(Calendar.YEAR);
                                if(month >= 6){
                                    year = years + 1 + "";
                                }else{
                                    year = years + "";
                                }
                                mappara.put("new_fiscal_year", year);
                            }
                            List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                            if(tcclist != null && tcclist.size() > 0){
                                for(TeacherContinuationConfig t : tcclist){
                                    //通过学员号插入升班期接口
                                    SchoolXbgx sbq = new SchoolXbgx();
                                    sbq.setnSchoolId(schoolid);
                                    sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                                    sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                                    sbq.setsStageId(t.getsStageId());
                                    sbq.setsStageName(t.getsStageName());
                                    sbq.setsStudentCode(teacherContinuationClass.getStudent_code());
                                    sbq.setsContinuedClassCode(classcode);
                                    
                                    if(StringUtils.isNotEmpty(teacherContinuationClass.getStudent_name())){
                                        sbq.setsStudentName(teacherContinuationClass.getStudent_name());
                                    }
                                     
                                    if(StringUtils.isNotEmpty(teacherContinuationClass.getPhone())){
                                        sbq.setsMobile(teacherContinuationClass.getPhone());
                                    }
                                    
                                    xbgxs.add(sbq);
                                    Map<String, Object> mapp = addXbgx(xbgxs);
                                    if(!(boolean) mapp.get("flag")){
                                        message += "通过学员号插入升班期失败:学员号"+teacherContinuationClass.getStudent_code()+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                                    }
                                    
                                    //通过学员号插入续班2接口
                                    Boolean b = addstudentcodememcached(t.getContinue_id(), teacherContinuationClass.getStudent_code(), classcode, schoolid);
                                    if(!b){
                                        message += "通过学员号插入续班2失败:学员号"+teacherContinuationClass.getStudent_code()+",班号"+classcode+"。<br/>";
                                    }
                                }
                                if(StringUtils.isNotEmpty(teacherContinuationClass.getStudent_name())){
                                    teacherContinuationClass.setStudent_name(teacherContinuationClass.getStudent_name());
                                }
                                
                                if(StringUtils.isNotEmpty(teacherContinuationClass.getPhone())){
                                    teacherContinuationClass.setPhone(teacherContinuationClass.getPhone());
                                }
                                teacherContinuationClass.setSchool_id(myUser.getCompanyId());
                                teacherContinuationClass.setStudent_code(teacherContinuationClass.getStudent_code());
                                teacherContinuationClass.setClass_code(classcode);
                                teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
                                teacherContinuationClass.setUpdate_user(myUser.getEmailAddr());
                                teacherContinuationClass.setUpdate_time(new Date());
                                teacherContinuationClass.setUpdate_type(1);
                                teacherContinuationClass.setIs_add(1);
                                teacherContinuationClassDao.insertSelective(teacherContinuationClass);
                            }else{
                                message += "未找到相关配置:学员号"+teacherContinuationClass.getStudent_code()+",班号"+classcode+"。<br/>";
                            }
                        }else{
                            message += "通过学员号插入升班期失败:学员号"+teacherContinuationClass.getStudent_code()+",班号"+classcode+"。<br/>";
                        }
                        a = true;
                    }
                }
                message+="插入完成";
                map.put("flag", a);
                map.put("message", message);
                return map;
                
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增学员插入人班关系异常:"+e);
        }
        
        return map;
    }

    /**
     * 新增班级中所有学员的人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> insertclass(TeacherContinuationClass teacherContinuationClass) {
        //验证参数
        Map<String, Object> map = new HashMap<String, Object>();
        //List<String> student_code_list = new ArrayList<String>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "请填写班级信息");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationClass.getOld_class_code())){
            map.put("flag", false);
            map.put("message", "请填写原班班号");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationClass.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写续班班号");
            return map;
        }
        
        try {
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            
            List<TeacherContinuationClass> l = getStudentsByclassCodes(teacherContinuationClass.getOld_class_code(), company_info.getMessageId());
            
            List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
            String message = "";
            String schoolid = company_info.getMessageId();
            String class_codes = teacherContinuationClass.getClass_code();
            for(TeacherContinuationClass c : l){
                String studentcode = c.getStudent_code();
                String studentname = c.getStudent_name();
                for(String classcode : class_codes.split(",")){
                    
                    Map<String, Object> mapparams = new HashMap<String, Object>();
                    mapparams.put("student_code", studentcode);
                    mapparams.put("class_code", classcode);
                    mapparams.put("school_id", company_id);
                    List<TeacherContinuationClass> slist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparams);
                    if(slist != null && slist.size() > 0){
                        message += "学员号"+studentcode+"已经添加续班"+classcode+"。<br/>";
                        continue;
                    }
                    
                    XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(classcode);
                    if(xdfClassInfo != null){
                        Map<String, Object> mappara = new HashMap<String, Object>();
                        mappara.put("school_id", company_id);
                        if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                            mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                        }
                        if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                            mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                        }
                        if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                            String year = "";
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                            Date date = sdf.parse(begintime);
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            Integer month = cal.get(Calendar.MONTH)+1;
                            Integer years = cal.get(Calendar.YEAR);
                            if(month >= 6){
                                year = years + 1 + "";
                            }else{
                                year = years + "";
                            }
                            mappara.put("new_fiscal_year", year);
                        }
                        List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                        if(tcclist != null && tcclist.size() > 0){
                            for(TeacherContinuationConfig t : tcclist){
                                //通过学员号插入升班期接口
                                SchoolXbgx sbq = new SchoolXbgx();
                                sbq.setnSchoolId(schoolid);
                                sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                                sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                                sbq.setsStageId(t.getsStageId());
                                sbq.setsStageName(t.getsStageName());
                                sbq.setsStudentCode(studentcode);
                                sbq.setsContinuedClassCode(classcode);
                                
                                if(StringUtils.isNotEmpty(teacherContinuationClass.getStudent_name())){
                                    sbq.setsStudentName(teacherContinuationClass.getStudent_name());
                                }
                                
                                if(StringUtils.isNotEmpty(teacherContinuationClass.getPhone())){
                                    sbq.setsMobile(teacherContinuationClass.getPhone());
                                }
                                
                                xbgxs.add(sbq);
                                Map<String, Object> mapp = addXbgx(xbgxs);
                                if(!(boolean) mapp.get("flag")){
                                    message += "通过学员号插入升班期失败:学员号"+studentcode+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                                }
                                
                                //通过学员号插入续班2接口
                                Boolean b = addstudentcodememcached(t.getContinue_id(), studentcode, classcode, schoolid);
                                if(!b){
                                    message += "通过学员号插入续班2失败:学员号"+studentcode+",班号"+classcode+"。<br/>";
                                }
                            }
                            teacherContinuationClass.setSchool_id(myUser.getCompanyId());
                            teacherContinuationClass.setStudent_code(studentcode);
                            teacherContinuationClass.setStudent_name(studentname);
                            teacherContinuationClass.setClass_code(classcode);
                            teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
                            teacherContinuationClass.setUpdate_user(myUser.getEmailAddr());
                            teacherContinuationClass.setUpdate_time(new Date());
                            teacherContinuationClass.setUpdate_type(1);
                            teacherContinuationClass.setIs_add(1);
                            teacherContinuationClassDao.insertSelective(teacherContinuationClass);
                        }else{
                            message += "未找到相关配置:学员号"+studentcode+",班号"+classcode+"。<br/>";
                        }
                    }else{
                        message += "通过学员号插入升班期失败:学员号"+studentcode+",班号"+classcode+"。<br/>";
                    }
                    
                    //通过学员号插入抢报2接口
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                }
            }
            
            message+="插入完成";
            map.put("flag", true);
            map.put("message", message);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增学员插入人班关系异常:"+e);
        }
        
        return map;
    }

    /**
     * 修改人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> update(TeacherContinuationClass teacherContinuationClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "请填写学员信息");
            return map;
        }
        
        if(teacherContinuationClass.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要修改的人班关系");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationClass.getStudent_code())){
            map.put("flag", false);
            map.put("message", "学生学员号不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherContinuationClass.getClass_code())){
            map.put("flag", false);
            map.put("message", "请填写班号");
            return map;
        }
        try {
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            
            //通过学员号插入升班期接口
                
            
            //通过学员号插入续班2接口
                
            
            //通过学员号插入抢报2接口
                
                
            teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
            teacherContinuationClass.setUpdate_time(new Date());
            teacherContinuationClass.setIs_add(1);
            teacherContinuationClassDao.updateByPrimaryKeySelective(teacherContinuationClass);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改人班关系异常:"+e);
        }
        
        return map;
    }

    /**
     * 查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> select(TeacherContinuationClass teacherContinuationClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(teacherContinuationClass.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            TeacherContinuationClass teachercontinuationClass = teacherContinuationClassDao.selectByPrimaryKey(teacherContinuationClass.getId());
            if(teachercontinuationClass == null){
                map.put("flag", false);
                map.put("message", "查询结果为空");
            }else{
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("teachercontinuationClass", teachercontinuationClass);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询人班关系异常："+e);
        }
        
        return map;
    }

    /**
     * 删除人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> delete(TeacherContinuationClass teacherContinuationClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(teacherContinuationClass.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            String schoolid = company_info.getMessageId();
            String message = "";
            TeacherContinuationClass teacherContinuationClass1 =teacherContinuationClassDao.selectByPrimaryKey(teacherContinuationClass.getId());
            XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(teacherContinuationClass1.getClass_code());
            String studentcode = teacherContinuationClass1.getStudent_code();
            String classcode = teacherContinuationClass1.getClass_code();
            List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
            if(xdfClassInfo != null){
                Map<String, Object> mappara = new HashMap<String, Object>();
                mappara.put("school_id", company_id);
                if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                    mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                    mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                    String year = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                    Date date = sdf.parse(begintime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Integer month = cal.get(Calendar.MONTH)+1;
                    Integer years = cal.get(Calendar.YEAR);
                    if(month >= 6){
                        year = years + 1 + "";
                    }else{
                        year = years + "";
                    }
                    mappara.put("new_fiscal_year", year);
                }
                List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                if(tcclist != null && tcclist.size() > 0){
                    for(TeacherContinuationConfig t : tcclist){
                        //通过学员号删除升班期接口
                        SchoolXbgx sbq = new SchoolXbgx();
                        sbq.setnSchoolId(schoolid);
                        sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                        sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                        sbq.setsStageId(t.getsStageId());
                        sbq.setsStageName(t.getsStageName());
                        sbq.setsStudentCode(teacherContinuationClass1.getStudent_code());
                        sbq.setsContinuedClassCode(teacherContinuationClass1.getClass_code());
                        
                        if(StringUtils.isNotEmpty(teacherContinuationClass.getStudent_name())){
                            sbq.setsStudentName(teacherContinuationClass.getStudent_name());
                        }
                        
                        if(StringUtils.isNotEmpty(teacherContinuationClass.getPhone())){
                            sbq.setsMobile(teacherContinuationClass.getPhone());
                        }
                        
                        xbgxs.add(sbq);
                        Map<String, Object> mapp = delXbgx(xbgxs);
                        if(!(boolean) mapp.get("flag")){
                            message += "通过学员号删除升班期失败:学员号"+studentcode+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                        }
                        
                        //通过学员号删除续班2接口
                        Boolean b = delstudentcodememcached(t.getContinue_id(), studentcode, classcode, schoolid);
                        if(!b){
                            message += "通过学员号删除续班2失败:学员号"+studentcode+",班号"+classcode+"。<br/>";
                        }
                    }
                }else{
                    message += "未找到相关配置:学员号"+studentcode+",班号"+classcode+"。<br/>";
                }
            }
            teacherContinuationClassDao.deleteByPrimaryKey(teacherContinuationClass.getId());
            message += "删除成功";
            map.put("flag", true);
            map.put("message", message);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除人班关系异常："+e);
        }
        
        return map;
    }

    /**
     * 往接口中插入人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> updateIsadd(TeacherContinuationClass teacherContinuationClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(teacherContinuationClass.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            
            TeacherContinuationClass teacherContinuationClass1 =teacherContinuationClassDao.selectByPrimaryKey(teacherContinuationClass.getId());
            List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
            String message = "";
            String schoolid = company_info.getMessageId();
            String classcode = teacherContinuationClass1.getClass_code();
            String studentcode = teacherContinuationClass1.getStudent_code();
            XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(teacherContinuationClass1.getClass_code());
            if(xdfClassInfo != null){
                Map<String, Object> mappara = new HashMap<String, Object>();
                mappara.put("school_id", company_id);
                if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                    mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                    mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                    String year = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                    Date date = sdf.parse(begintime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Integer month = cal.get(Calendar.MONTH)+1;
                    Integer years = cal.get(Calendar.YEAR);
                    if(month >= 6){
                        year = years + 1 + "";
                    }else{
                        year = years + "";
                    }
                    mappara.put("new_fiscal_year", year);
                }
                List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                if(tcclist != null && tcclist.size() > 0){
                    for(TeacherContinuationConfig t : tcclist){
                        //通过学员号插入升班期接口
                        SchoolXbgx sbq = new SchoolXbgx();
                        sbq.setnSchoolId(company_info.getMessageId());
                        sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                        sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                        sbq.setsStageId(t.getsStageId());
                        sbq.setsStageName(t.getsStageName());
                        sbq.setsStudentCode(studentcode);
                        sbq.setsContinuedClassCode(classcode);
                        xbgxs.add(sbq);
                        Map<String, Object> mapp = addXbgx(xbgxs);
                        if(!(boolean) mapp.get("flag")){
                            message += "通过学员号插入升班期失败:学员号"+studentcode+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                        }
                        
                        //通过学员号插入续班2接口
                        Boolean b = addstudentcodememcached(t.getContinue_id(), studentcode, classcode, schoolid);
                        if(!b){
                            message += "通过学员号插入续班2失败:学员号"+studentcode+",班号"+classcode+"。<br/>";
                        }
                    }
                    teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
                    teacherContinuationClass.setUpdate_user(myUser.getEmailAddr());
                    teacherContinuationClass.setUpdate_time(new Date());
                    teacherContinuationClass.setUpdate_type(2);
                    teacherContinuationClass.setIs_add(1);
                    teacherContinuationClassDao.updateByPrimaryKeySelective(teacherContinuationClass);
                }else{
                    message += "未找到相关配置:学员号"+studentcode+",班号"+classcode+"。<br/>";
                }
            }
            
            message += "开启同步接口结束。";
            map.put("flag", true);
            map.put("message", message);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "插入接口异常");
        }
        
        return map;
    }

    /**
     * 删除接口中人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> updateIsnotadd(TeacherContinuationClass teacherContinuationClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(teacherContinuationClass == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(teacherContinuationClass.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            
            MyUser myUser = Common.getMyUser();
            
            String company_id = myUser.getCompanyId();
            if(StringUtils.isEmpty(company_id)){
                map.put("flag", false);
                map.put("message", "获取company_id失败");
                return map;
            }
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
            if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            
            TeacherContinuationClass teacherContinuationClass1 =teacherContinuationClassDao.selectByPrimaryKey(teacherContinuationClass.getId());
            //删除续班2.0
            List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
            String message = "";
            String schoolid = company_info.getMessageId();
            String classcode = teacherContinuationClass1.getClass_code();
            String studentcode = teacherContinuationClass1.getStudent_code();
            XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(teacherContinuationClass1.getClass_code());
            if(xdfClassInfo != null){
                Map<String, Object> mappara = new HashMap<String, Object>();
                mappara.put("school_id", company_id);
                if(StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())){
                    mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())){
                    mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                }
                if(StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())){
                    String year = "";
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String begintime = xdfClassInfo.getDtBeginDate().substring(0,10);
                    Date date = sdf.parse(begintime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    Integer month = cal.get(Calendar.MONTH)+1;
                    Integer years = cal.get(Calendar.YEAR);
                    if(month >= 6){
                        year = years + 1 + "";
                    }else{
                        year = years + "";
                    }
                    mappara.put("new_fiscal_year", year);
                }
                List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                if(tcclist != null && tcclist.size() > 0){
                    for(TeacherContinuationConfig t : tcclist){
                        //通过学员号插入升班期接口
                        SchoolXbgx sbq = new SchoolXbgx();
                        sbq.setnSchoolId(company_info.getMessageId());
                        sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                        sbq.setsWindowPeriodName(t.getsWindowPeriodName());
                        sbq.setsStageId(t.getsStageId());
                        sbq.setsStageName(t.getsStageName());
                        sbq.setsStudentCode(studentcode);
                        sbq.setsContinuedClassCode(classcode);
                        xbgxs.add(sbq);
                        Map<String, Object> mapp = delXbgx(xbgxs);
                        if(!(boolean) mapp.get("flag")){
                            message += "通过学员号插入升班期失败:学员号"+studentcode+",班号"+classcode+",stageId"+t.getsStageId()+"。<br/>";
                        }
                        
                        //通过学员号插入续班2接口
                        Boolean b = delstudentcodememcached(t.getContinue_id(), studentcode, classcode, schoolid);
                        if(!b){
                            message += "通过学员号插入续班2失败:学员号"+studentcode+",班号"+classcode+"。<br/>";
                        }
                    }
                    teacherContinuationClass.setEmail_addr(myUser.getEmailAddr());
                    teacherContinuationClass.setUpdate_user(myUser.getEmailAddr());
                    teacherContinuationClass.setUpdate_time(new Date());
                    teacherContinuationClass.setUpdate_type(3);
                    teacherContinuationClass.setIs_add(0);
                    teacherContinuationClassDao.updateByPrimaryKeySelective(teacherContinuationClass);
                }else{
                    message += "未找到相关配置:学员号"+studentcode+",班号"+classcode+"。<br/>";
                }
            }
            
            message+="关闭接口同步结束";
            map.put("flag", true);
            map.put("message", message);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "插入接口异常:"+e);
        }
        
        return map;
    }
    
    /**
     * 获取姓名手机号下的学员号
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> getStduentCodes(TeacherContinuationClass teacherContinuationClass){
        Map<String, Object> map = new HashMap<String, Object>();
        String student_codes = "";
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        String company_id = myUser.getCompanyId();
        if(StringUtils.isEmpty(company_id)){
            map.put("flag", false);
            map.put("message", "获取company_id失败");
            return map;
        }
        HrCompany company_info = hrCompanyDao.selectByPrimaryKey(company_id);
        if(company_info == null || StringUtils.isEmpty(company_info.getMessageId())){
            map.put("flag", false);
            map.put("message", "获取school_id失败");
            return map;
        }
        
        String result = getstudentcodebyphone(teacherContinuationClass.getStudent_name(), teacherContinuationClass.getPhone(),company_info.getMessageId());
        
        JSONObject jo = new JSONObject(result);
        if(jo != null && jo.has("State") && jo.getInt("State") == 1 && jo.has("Data")){
            JSONArray ja = jo.getJSONArray("Data");
            if(ja != null && ja.length() > 0){
                for(int i =0; i < ja.length(); i++){
                    JSONObject job = ja.getJSONObject(i);
                    if(job.has("StudentCode")){
                        String student_code = job.getString("StudentCode");
                        student_codes += "," + student_code;
                    }
                }
            }
        }
        
        if(StringUtils.isNotEmpty(student_codes)){
            student_codes = student_codes.substring(1);
            map.put("flag", true);
            map.put("message", "获取学员号成功");
            map.put("student_codes", student_codes);
        }else{
            map.put("flag", false);
            map.put("message", "姓名手机号查询学员号失败");
        }
        
        return map;
    }
    
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
    
    //获取升班期token
    @SuppressWarnings("unchecked")
    public String getToken() {
            if(redisTemplateUtil.isExist("SBTOKEN")){
                return (String) redisTemplateUtil.get("SBTOKEN");
            }
            String access_token = "";
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("grant_type","password");
            params.put("client_id",client_id);
            params.put("client_secret",client_secret);
            params.put("username",username);
            params.put("password",password);
            try {
                String res = HttpClientUtil.httpPostRequest(tokenUrl,null, params);
                Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
                access_token = map.get("access_token").toString();
                redisTemplateUtil.set("SBTOKEN", access_token,29*60L);
                return access_token;
            }catch(Exception e){
                e.printStackTrace();
                return "";
            }
    }
    
    //新增升班期
    @SuppressWarnings("unchecked")
    public Map<String, Object> addXbgx(List<SchoolXbgx> xbgxs) {
        Map<String,Object> result = new HashMap<String,Object>();
        String token = getToken();
        if(StringUtils.isEmpty(token)){
            result.put("flag", false);
            result.put("message", "获取TOKEN失败");
            return result;
        }
        try {
            String json = JSON.toJSONString(xbgxs);
            String res = HttpClientUtil.httpPostJson(addUrl,json, token);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
            String success = map.get("success").toString();
            if("true".equals(success)){
                result.put("flag", true);
                result.put("message", "插入成功");
            }else{
                result.put("flag", false);
                result.put("message", "插入失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "插入失败");
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    public Map<String, Object> delXbgx(List<SchoolXbgx> xbgxs) {
        Map<String,Object> result = new HashMap<String,Object>();
        String token = getToken();
        if(StringUtils.isEmpty(token)){
            result.put("flag", false);
            result.put("message", "获取TOKEN失败");
            return result;
        }
        try {
            String json = JSON.toJSONString(xbgxs);
            String res = HttpClientUtil.httpPostJson(delUrl,json, token);
            if(StringUtils.isNotEmpty(res)){
                Map map = (Map) JSON.parse(res);
                String success = map.get("success").toString();
                if("true".equals(success)){
                    result.put("flag", true);
                    result.put("message", "删除成功");
                }else{
                    result.put("flag", false);
                    result.put("message", "删除失败");
                }  
            }           
        }catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }
    
    //通过班号获取班号下的学员号
    @SuppressWarnings("unchecked")
    public List<TeacherContinuationClass> getStudentsByclassCodes(String classCode,String schoolId) {
        List<TeacherContinuationClass> stuList = new ArrayList<TeacherContinuationClass>();//学员
        try {
            String url = getStudentsOfClassUrl;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("method", method);
            params.put("appid", appIdOfStudents);
            params.put("schoolId", schoolId);
            String result = "";            
            params.put("classcode", classCode);
            String signText = ("Method=" + method + "&Appid=" + appIdOfStudents + "&schoolId=" + schoolId + "&classcode=" + classCode + "&appkey=" + appKey).toLowerCase();
            String sign = MD5Util.MD5Encode(signText, null).toUpperCase();
            params.put("sign", sign);
            result = HttpClientUtil.httpPostRequest(url, null, params);
            if (result != "") {
                Map<String, Object> resultMap = (Map<String, Object>) com.alibaba.fastjson.JSON.parse(result);
                if (resultMap != null && resultMap.size() > 0) {
                    String data = resultMap.get("Data").toString();
                    com.alibaba.fastjson.JSONArray js = com.alibaba.fastjson.JSONArray.parseArray(data);
                    if (js != null && js.size() > 0) {
                        Map<String, Object> classResult = new HashMap<String, Object>();
                        for (int i = 0, size = js.size(); i < size; i++) {
                            TeacherContinuationClass cs = new TeacherContinuationClass();
                            classResult = (Map<String, Object>) js.get(i);
                            String name = (String) classResult.get("Name");
                            String code = (String) classResult.get("Code");
                            cs.setStudent_code(code);
                            cs.setStudent_name(name);
                            stuList.add(cs);
                        }
                    }
                }
            }
            Set<TeacherContinuationClass> set = new HashSet<TeacherContinuationClass>();
            set.addAll(stuList);
            stuList.clear();
            stuList.addAll(set);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return stuList;
    }
    
    //往续班2.0插入学员号和班号
    public Boolean addstudentcodememcached(String config_id, String studentcode, String classcode,String schoolid) {
        try {
            String url = "http://wxpay.xdf.cn/xdf/xdfstudentcodecontinuedclass/addstudentcodememcached/forhf.do?";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("config_id", config_id);
            params.put("studentcode", studentcode);
            params.put("classcode", classcode);
            params.put("schoolid", schoolid);
            String result = HttpClientUtil.httpPostRequest(url, null, params);
            if (StringUtils.isNotEmpty(result)) {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("result") && jsonObject.getInt("result") == 1) {
                    return true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //删除续班2.0人班关系
    public Boolean delstudentcodememcached(String config_id, String studentcode, String classcode,String schoolid) {
        try {
            String url = "http://wxpay.xdf.cn/xdf/xdfstudentcodecontinuedclass/delstudentcodememcached/forhf.do?";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("config_id", config_id);
            params.put("studentcode", studentcode);
            params.put("classcode", classcode);
            params.put("schoolid", schoolid);
            String result = HttpClientUtil.httpPostRequest(url, null, params);
            System.out.println(result);
            if (StringUtils.isNotEmpty(result)) {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("result") && jsonObject.getInt("result") == 1) {
                    return true;
                }
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
