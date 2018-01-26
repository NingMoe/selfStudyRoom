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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.human.continuation.dao.TeacherContinuationTeachernameDao;
import com.human.continuation.entity.TeacherContinuationClass;
import com.human.continuation.entity.TeacherContinuationConfig;
import com.human.continuation.entity.TeacherContinuationStudentInfo;
import com.human.continuation.service.TeacherContinuationUserService;
import com.human.jzbTest.entity.SchoolXbgx;
import com.human.manager.dao.HrCompanyDao;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.Users;
import com.human.order.utils.MD5Util;
import com.human.utils.BindingConstants;
import com.human.utils.HttpClientUtil;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.XdfRequestUtil.XdfGeneralRequestSignature;
import com.human.utils.XdfRequestUtil.XdfPostDataOperate;

@Service
public class TeacherContinuationUserServiceImpl implements TeacherContinuationUserService {
    private final Logger log = LogManager.getLogger(TeacherContinuationUserServiceImpl.class);

    @Resource
    private HrCompanyDao hrCompanyDao;

    @Resource
    private XdfClassInfoDao xdfClassInfoDao;

    @Resource
    private TeacherContinuationClassDao teacherContinuationClassDao;

    @Resource
    private TeacherContinuationConfigDao teacherContinuationConfigDao;

    @Resource
    private TeacherContinuationClassDao teacherContinuationTeacherClassDao;

    @Resource
    private TeacherContinuationTeachernameDao teacherContinuationTeachernameDao;

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
    
    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    public Map<String, Object> addclasspeople(HttpServletRequest request, TeacherContinuationClass teacherContinuationClass) {
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
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            Users users = (Users) session.getAttribute(BindingConstants.BINDING_USERS);
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(users.getCompanyId());
            if (company_info == null || StringUtils.isEmpty(company_info.getMessageId())) {
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }
            String company_id = users.getCompanyId();
            
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
                                    teacherContinuationClass.setSchool_id(company_id);
                                    teacherContinuationClass.setStudent_code(teacherContinuationClass.getStudent_code());
                                    teacherContinuationClass.setClass_code(classcode);
                                    teacherContinuationClass.setEmail_addr(email_addr);
                                    teacherContinuationClass.setUpdate_user(email_addr);
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
                                teacherContinuationClass.setSchool_id(company_id);
                                teacherContinuationClass.setStudent_code(teacherContinuationClass.getStudent_code());
                                teacherContinuationClass.setClass_code(classcode);
                                teacherContinuationClass.setEmail_addr(email_addr);
                                teacherContinuationClass.setUpdate_user(email_addr);
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
     * 获取教师代课班级信息
     * 
     * @return
     */
    public Map<String, Object> getTeacherClasses(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        List<XdfClassInfo> list_a = new ArrayList<XdfClassInfo>();
        List<XdfClassInfo> list_b = new ArrayList<XdfClassInfo>();
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("email_addr", email_addr);
            mapparam.put("begin_date_left", sdf.format(new Date()));
            mapparam.put("end_date", sdf.format(new Date()));
            List<XdfClassInfo> list_a_1 = xdfClassInfoDao.selectTeacherStartClassesOnByEmrilAddr(mapparam);
            if (list_a_1 != null && list_a_1.size() > 0) {
                list_a.addAll(list_a_1);
            }

            List<XdfClassInfo> list_a_2 = teacherContinuationTeachernameDao.selectTeacherStartClassesOnByEmrilAddr(mapparam);
            if (list_a_2 != null && list_a_2.size() > 0) {
                list_a.addAll(list_a_2);
                Set<XdfClassInfo> set = new HashSet<XdfClassInfo>();
                set.addAll(list_a);
                list_a.clear();
                list_a.addAll(set);
            }

            if (list_a != null && list_a.size() >= 0) {
                for (XdfClassInfo x : list_a) {
                    List<TeacherContinuationStudentInfo> studentinfolist = getStudentsByclassCodes(x.getsClassCode(), "25");
                    for (TeacherContinuationStudentInfo studentinfo : studentinfolist) {
                        Map<String, Object> mapparamx = new HashMap<String, Object>();
                        mapparamx.put("email_addr", email_addr);
                        mapparamx.put("student_code", studentinfo.getStudent_code());
                        Integer count_num = teacherContinuationClassDao.selectClassConfigNumInfoByClassCode(mapparamx);
                        if (count_num != null && count_num > 0) {
                            if (x.getCon_num() == null) {
                                x.setCon_num(1);
                            }
                            else {
                                x.setCon_num(x.getCon_num() + 1);
                            }
                        }
                    }
                }
            }

            map.put("list_a", list_a);

            Map<String, Object> mapparam1 = new HashMap<String, Object>();
            mapparam1.put("email_addr", email_addr);
            mapparam1.put("begin_date_right", sdf.format(new Date()));
            mapparam1.put("end_date", sdf.format(new Date()));
            List<XdfClassInfo> list_b_1 = xdfClassInfoDao.selectTeacherStartClassesOnByEmrilAddr(mapparam1);
            if (list_b_1 != null && list_b_1.size() > 0) {
                list_b.addAll(list_b_1);
            }

            List<XdfClassInfo> list_b_2 = teacherContinuationTeachernameDao.selectTeacherStartClassesOnByEmrilAddr(mapparam1);
            if (list_b_2 != null && list_b_2.size() > 0) {
                list_b.addAll(list_b_2);
                Set<XdfClassInfo> set = new HashSet<XdfClassInfo>();
                set.addAll(list_b);
                list_b.clear();
                list_b.addAll(set);
            }
            map.put("list_b", list_b);

            map.put("flag", true);
            map.put("message", "获取教师班级列表成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取教师班级列表异常");
        }

        return map;
    }

    /**
     * 获取教师要续班级
     * 
     * @return
     */
    public Map<String, Object> getTeacherStudentClasses(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        List<XdfClassInfo> list_a = new ArrayList<XdfClassInfo>();
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("email_addr", email_addr);
            mapparam.put("begin_date_left", sdf.format(new Date()));
            mapparam.put("end_date", sdf.format(new Date()));
            List<XdfClassInfo> list_a_1 = xdfClassInfoDao.selectTeacherStartClassesOnByEmrilAddr(mapparam);
            if (list_a_1 != null && list_a_1.size() > 0) {
                list_a.addAll(list_a_1);
            }

            List<XdfClassInfo> list_a_2 = teacherContinuationTeachernameDao.selectTeacherStartClassesOnByEmrilAddr(mapparam);
            if (list_a_2 != null && list_a_2.size() > 0) {
                list_a.addAll(list_a_2);
                Set<XdfClassInfo> set = new HashSet<XdfClassInfo>();
                set.addAll(list_a);
                list_a.clear();
                list_a.addAll(set);
            }

            if (list_a != null && list_a.size() > 0) {
                for (XdfClassInfo x : list_a) {
                    List<TeacherContinuationStudentInfo> studentinfolist = getStudentsByclassCodes(x.getsClassCode(), "25");
                    for (TeacherContinuationStudentInfo studentinfo : studentinfolist) {
                        Map<String, Object> mapparamx = new HashMap<String, Object>();
                        mapparamx.put("email_addr", email_addr);
                        mapparamx.put("student_code", studentinfo.getStudent_code());
                        Integer count_num = teacherContinuationClassDao.selectClassConfigNumInfoByClassCode(mapparamx);
                        if (count_num != null && count_num > 0) {
                            if (x.getCon_num() == null) {
                                x.setCon_num(1);
                            }
                            else {
                                x.setCon_num(x.getCon_num() + 1);
                            }
                        }
                    }
                }
                map.put("list_a", list_a);
                map.put("flag", true);
                map.put("message", "获取教师班级列表成功");
            }
            else {
                map.put("flag", false);
                map.put("message", "获取教师班级列表失败");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取教师班级列表异常");
        }

        return map;
    }

    /**
     * 获取教师可续班级
     * 
     * @return
     */
    public Map<String, Object> getTeacherNotBeginClasses(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        List<XdfClassInfo> list_b = new ArrayList<XdfClassInfo>();
        try {
            Map<String, Object> mapparam1 = new HashMap<String, Object>();
            mapparam1.put("email_addr", email_addr);
            mapparam1.put("begin_date_right", sdf.format(new Date()));
            mapparam1.put("end_date", sdf.format(new Date()));
            List<XdfClassInfo> list_b_1 = xdfClassInfoDao.selectTeacherStartClassesOnByEmrilAddr(mapparam1);
            if (list_b_1 != null && list_b_1.size() > 0) {
                list_b.addAll(list_b_1);
            }

            List<XdfClassInfo> list_b_2 = teacherContinuationTeachernameDao.selectTeacherStartClassesOnByEmrilAddr(mapparam1);
            if (list_b_2 != null && list_b_2.size() > 0) {
                list_b.addAll(list_b_2);
                Set<XdfClassInfo> set = new HashSet<XdfClassInfo>();
                set.addAll(list_b);
                list_b.clear();
                list_b.addAll(set);
            }
            map.put("list_b", list_b);

            map.put("flag", true);
            map.put("message", "获取教师班级列表成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取教师班级列表异常");
        }
        return map;
    }

    /**
     * 添加人班关系
     * 
     * @param studentclasses
     * @return
     */
    public Map<String, Object> addStduentClasses(HttpServletRequest request, HttpServletResponse response, String student_code, String student_name, String studentclasses) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isEmpty(student_code)) {
            map.put("flag", false);
            map.put("message", "学员号为空");
            return map;
        }

        if (StringUtils.isEmpty(student_name)) {
            map.put("flag", false);
            map.put("message", "学员姓名为空");
            return map;
        }

        if (StringUtils.isEmpty(studentclasses)) {
            map.put("flag", false);
            map.put("message", "续班班号为空");

        }

        Integer i = 0;
        Integer j = 0;
        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            Users users = (Users) session.getAttribute(BindingConstants.BINDING_USERS);
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(users.getCompanyId());
            if (company_info == null || StringUtils.isEmpty(company_info.getMessageId())) {
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }

            String message = "";
            String schoolid = company_info.getMessageId();
            for (String classcode : studentclasses.split(",")) {
                XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(classcode);
                if (xdfClassInfo != null) {
                    Map<String, Object> mappara = new HashMap<String, Object>();
                    mappara.put("school_id", users.getCompanyId());
                    if (StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())) {
                        mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                    }
                    if (StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())) {
                        mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                    }
                    if (StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())) {
                        String year = "";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String begintime = xdfClassInfo.getDtBeginDate().substring(0, 10);
                        Date date = sdf.parse(begintime);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        Integer month = cal.get(Calendar.MONTH) + 1;
                        Integer years = cal.get(Calendar.YEAR);
                        if (month >= 6) {
                            year = years + 1 + "";
                        }
                        else {
                            year = years + "";
                        }
                        mappara.put("new_fiscal_year", year);
                    }
                    Map<String, Object> mapparams = new HashMap<String, Object>();
                    mapparams.put("student_code", student_code);
                    mapparams.put("class_code", classcode);
                    mapparams.put("email_addr", email_addr);
                    mapparams.put("school_id", users.getCompanyId());
                    List<TeacherContinuationClass> slist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparams);
                    if (slist != null && slist.size() > 0) {
                        continue;
                    }
                    List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                    if (tcclist != null && tcclist.size() > 0) {
                        String[] configids = null;
                        for (TeacherContinuationConfig t : tcclist) {
                            List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
                            // 通过学员号插入升班期接口
                            SchoolXbgx sbq = new SchoolXbgx();
                            sbq.setnSchoolId(schoolid);
                            sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                            sbq.setsStageId(t.getsStageId());
                            sbq.setsStudentCode(student_code);
                            sbq.setsContinuedClassCode(classcode);
                            xbgxs.add(sbq);
                            i++;
                            log.info("第" + i + "次添加升班期：窗口期id" + t.getsWindowPeriodId() + "，stageId：" + t.getsStageId() + "，学员号：" + student_code + "，班号：" + classcode);
                            Map<String, Object> mapp = addXbgx(xbgxs);
                            if (!(boolean) mapp.get("flag")) {
                                message += "通过学员号插入升班期失败:学员号" + student_code + ",班号" + classcode + "。<br/>";
                            }
                            // 通过学员号插入续班2接口
                            if (StringUtils.isNotEmpty(t.getContinue_id())) {
                                if(configids == null){
                                    j++;
                                    log.info("第" + j + "次添加续班2：continueid" + t.getContinue_id() + "，学员号：" + student_code + "，班号：" + classcode);
                                    Boolean b = addstudentcodememcached(t.getContinue_id(), student_code, classcode, company_info.getMessageId());
                                    if (!b) {
                                        message += "通过学员号插入续班2失败:学员号" + student_code + ",班号" + classcode + "。<br/>";
                                    }
                                }else{
                                    int z = 0;
                                    for(String cid : configids){
                                        if(t.getContinue_id().equals(cid)){
                                            z = 1;
                                        }
                                    }
                                    if(z == 0){
                                        Boolean b = addstudentcodememcached(t.getContinue_id(), student_code, classcode, company_info.getMessageId());
                                        if (!b) {
                                            message += "通过学员号插入续班2失败:学员号" + student_code + ",班号" + classcode + "。<br/>";
                                        }
                                    }
                                }
                            }
                        }
                        TeacherContinuationClass teacherContinuationClass = new TeacherContinuationClass();
                        teacherContinuationClass.setSchool_id(users.getCompanyId());
                        teacherContinuationClass.setStudent_code(student_code);
                        teacherContinuationClass.setStudent_name(student_name);
                        teacherContinuationClass.setClass_code(classcode);
                        teacherContinuationClass.setEmail_addr(email_addr);
                        teacherContinuationClass.setUpdate_user(email_addr);
                        teacherContinuationClass.setUpdate_time(new Date());
                        teacherContinuationClass.setUpdate_type(1);
                        teacherContinuationClass.setIs_add(1);
                        teacherContinuationClassDao.insertSelective(teacherContinuationClass);
                    }
                    else {
                        message += "未找到相关配置:学员号" + student_code + ",班号" + classcode + "，请截图联系信管老师<br/>";
                    }
                }
                else {
                    message += "通过学员号插入升班期失败:学员号" + student_code + ",班号" + classcode + "。<br/>";
                }
            }
            message += "添加结束";
            map.put("flag", true);
            map.put("message", message);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加人班关系异常：" + e);
        }

        return map;
    }

    /**
     * 添加班班关系
     * 
     * @param classesclasses
     * @return
     */
    public Map<String, Object> addClassesClasses(HttpServletRequest request, HttpServletResponse response, String oldclasses, String newclasses) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isEmpty(oldclasses)) {
            map.put("flag", false);
            map.put("message", "原班班号为空");
            return map;
        }

        if (StringUtils.isEmpty(newclasses)) {
            map.put("flag", false);
            map.put("message", "续班班号为空");
            return map;
        }

        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            Users users = (Users) session.getAttribute(BindingConstants.BINDING_USERS);
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(users.getCompanyId());
            if (company_info == null || StringUtils.isEmpty(company_info.getMessageId())) {
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }

            String message = "";
            Integer i = 0;
            Integer j = 0;
            for (String newclass : newclasses.split(",")) {
                XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(newclass);
                if (xdfClassInfo != null) {
                    Map<String, Object> mappara = new HashMap<String, Object>();
                    mappara.put("school_id", users.getCompanyId());
                    if (StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())) {
                        mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                    }

                    if (StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())) {
                        mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                    }

                    if (StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())) {
                        String year = "";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String begintime = xdfClassInfo.getDtBeginDate().substring(0, 10);
                        Date date = sdf.parse(begintime);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        Integer month = cal.get(Calendar.MONTH) + 1;
                        Integer years = cal.get(Calendar.YEAR);
                        if (month >= 6) {
                            year = years + 1 + "";
                        }
                        else {
                            year = years + "";
                        }
                        mappara.put("new_fiscal_year", year);
                    }

                    List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                    log.info("查询到"+tcclist.size()+"条升班期规则");
                    int zz = 1;
                    if (tcclist != null && tcclist.size() > 0) {
                        for (String oldclass : oldclasses.split(",")) {
                            List<TeacherContinuationStudentInfo> l = getStudentsByclassCodes(oldclass, company_info.getMessageId());
                            for (TeacherContinuationStudentInfo s : l) {
                                String studentcode = s.getStudent_code();
                                String studentname = s.getStudent_name();
                                Map<String, Object> mapparams = new HashMap<String, Object>();
                                mapparams.put("student_code", studentcode);
                                mapparams.put("class_code", newclass);
                                mapparams.put("email_addr", email_addr);
                                mapparams.put("school_id", users.getCompanyId());
                                List<TeacherContinuationClass> slist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparams);
                                if (slist != null && slist.size() > 0) {
                                    log.info("第"+(zz++)+"个学员号班号重复。");
                                    continue;
                                }
                                String[] configids = null;
                                for (TeacherContinuationConfig t : tcclist) {
                                    List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
                                    log.info("第"+(zz++)+"条升班期规则:");
                                    // 通过学员号插入升班期接口
                                    SchoolXbgx sbq = new SchoolXbgx();
                                    sbq.setnSchoolId(company_info.getMessageId());
                                    sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                                    sbq.setsStageId(t.getsStageId());
                                    sbq.setsStudentCode(studentcode);
                                    sbq.setsContinuedClassCode(newclass);
                                    xbgxs.add(sbq);

                                    i++;
                                    log.info("第" + i + "次添加升班期：窗口期id" + t.getsWindowPeriodId() + "，stageId：" + t.getsStageId() + "，学员号：" + studentcode + "，班号：" + newclass);
                                    Map<String, Object> mapp = addXbgx(xbgxs);
                                    if (!(boolean) mapp.get("flag")) {
                                        message += "通过学员号插入升班期失败:学员号" + studentcode + ",班号" + newclass + "。<br/>";
                                    }

                                    // 通过学员号插入续班2接口
                                    if (StringUtils.isNotEmpty(t.getContinue_id())) {
                                        if(configids == null){
                                            j++;
                                            log.info("第" + j + "次添加续班2：continueid" + t.getContinue_id() + "，学员号：" + studentcode + "，班号：" + newclass);
                                            Boolean b = addstudentcodememcached(t.getContinue_id(), studentcode, newclass, company_info.getMessageId());
                                            if (!b) {
                                                message += "通过学员号插入续班2失败:学员号" + studentcode + ",班号" + newclass + "。<br/>";
                                            }
                                        }else{
                                            int z = 0;
                                            for(String cid : configids){
                                                if(t.getContinue_id().equals(cid)){
                                                    z = 1;
                                                }
                                            }
                                            if(z == 0){
                                                Boolean b = addstudentcodememcached(t.getContinue_id(), studentcode, newclass, company_info.getMessageId());
                                                if (!b) {
                                                    message += "通过学员号插入续班2失败:学员号" + studentcode + ",班号" + newclass + "。<br/>";
                                                }
                                            }
                                        }
                                    }
                                }

                                TeacherContinuationClass teacherContinuationClass = new TeacherContinuationClass();
                                teacherContinuationClass.setSchool_id(users.getCompanyId());
                                teacherContinuationClass.setStudent_code(studentcode);
                                teacherContinuationClass.setStudent_name(studentname);
                                teacherContinuationClass.setClass_code(newclass);
                                teacherContinuationClass.setEmail_addr(email_addr);
                                teacherContinuationClass.setUpdate_user(email_addr);
                                teacherContinuationClass.setUpdate_time(new Date());
                                teacherContinuationClass.setUpdate_type(1);
                                teacherContinuationClass.setIs_add(1);
                                teacherContinuationClassDao.insertSelective(teacherContinuationClass);
                            }
                        }

                    }
                    else {
                        message += "未找到相关配置:续班班号" + newclass + "，请截图联系信管老师<br/>";
                    }
                }
                else {
                    message += "未找到班级信息:续班班号" + newclass + "。<br/>";
                }
            }

            message += "添加结束。";
            map.put("flag", true);
            map.put("message", message);
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("falg", false);
            map.put("message", "新增班班关系异常：" + e);
        }

        return map;
    }

    /**
     * 删除人班关系
     * 
     * @param request
     * @param response
     * @param student_code
     * @param studentclasses
     * @return
     */
    public Map<String, Object> removestduentclasses(HttpServletRequest request, HttpServletResponse response, String student_code, String studentclasses) {
        log.info("删除关系开始");
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(student_code)) {
            map.put("flag", false);
            map.put("message", "学员号为空");
            return map;
        }

        if (StringUtils.isEmpty(studentclasses)) {
            map.put("flag", false);
            map.put("message", "续班班号为空");

        }

        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            Users users = (Users) session.getAttribute(BindingConstants.BINDING_USERS);
            HrCompany company_info = hrCompanyDao.selectByPrimaryKey(users.getCompanyId());
            if (company_info == null || StringUtils.isEmpty(company_info.getMessageId())) {
                map.put("flag", false);
                map.put("message", "获取school_id失败");
                return map;
            }

            String message = "";
            String schoolid = company_info.getMessageId();
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("school_id", users.getCompanyId());
            mapparam.put("student_code", student_code);
            mapparam.put("class_code", studentclasses);
            mapparam.put("email_addr", email_addr);
            List<TeacherContinuationClass> teacherContinuationClasslist = teacherContinuationClassDao.selectClassesInfoByStudentcodeAndClasscode(mapparam);
            for (TeacherContinuationClass teacherContinuationClass1 : teacherContinuationClasslist) {
                XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(teacherContinuationClass1.getClass_code());
                String studentcode = teacherContinuationClass1.getStudent_code();
                String classcode = teacherContinuationClass1.getClass_code();
                List<SchoolXbgx> xbgxs = new ArrayList<SchoolXbgx>();
                if (xdfClassInfo != null) {
                    Map<String, Object> mappara = new HashMap<String, Object>();
                    mappara.put("school_id", users.getCompanyId());
                    if (StringUtils.isNotEmpty(xdfClassInfo.getsManageDeptCodes())) {
                        mappara.put("manager_dept_code", xdfClassInfo.getsManageDeptCodes());
                    }
                    if (StringUtils.isNotEmpty(xdfClassInfo.getsQuarter())) {
                        mappara.put("new_class_quarter", Integer.valueOf(xdfClassInfo.getsQuarter()));
                    }
                    if (StringUtils.isNotEmpty(xdfClassInfo.getDtBeginDate())) {
                        String year = "";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String begintime = xdfClassInfo.getDtBeginDate().substring(0, 10);
                        Date date = sdf.parse(begintime);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        Integer month = cal.get(Calendar.MONTH) + 1;
                        Integer years = cal.get(Calendar.YEAR);
                        if (month >= 6) {
                            year = years + 1 + "";
                        }
                        else {
                            year = years + "";
                        }
                        mappara.put("new_fiscal_year", year);
                    }
                    List<TeacherContinuationConfig> tcclist = teacherContinuationConfigDao.getWindowConfig(mappara);
                    if (tcclist != null && tcclist.size() > 0) {
                        for (TeacherContinuationConfig t : tcclist) {
                            // 通过学员号删除升班期接口
                            SchoolXbgx sbq = new SchoolXbgx();
                            sbq.setnSchoolId(schoolid);
                            sbq.setsWindowPeriodId(t.getsWindowPeriodId());
                            sbq.setsStageId(t.getsStageId());
                            sbq.setsStudentCode(teacherContinuationClass1.getStudent_code());
                            sbq.setsContinuedClassCode(teacherContinuationClass1.getClass_code());

                            if (StringUtils.isNotEmpty(teacherContinuationClass1.getStudent_name())) {
                                sbq.setsStudentName(teacherContinuationClass1.getStudent_name());
                            }

                            if (StringUtils.isNotEmpty(teacherContinuationClass1.getPhone())) {
                                sbq.setsMobile(teacherContinuationClass1.getPhone());
                            }

                            xbgxs.add(sbq);
                            log.info("删除" + studentcode + "升班期开始");
                            Map<String, Object> mapp = delXbgx(xbgxs);
                            if (!(boolean) mapp.get("flag")) {
                                message += "通过学员号删除升班期失败:学员号" + studentcode + ",班号" + classcode + ",stageId" + t.getsStageId() + "。<br/>";
                            }
                            log.info("删除" + studentcode + "升班期结束");
                            log.info("删除" + studentcode + "续班2开始");
                            // 通过学员号删除续班2接口
                            if (StringUtils.isNotEmpty(t.getContinue_id())) {
                                delstudentcodememcached(t.getContinue_id(), studentcode, classcode, schoolid);
                            }
                            log.info("删除" + studentcode + "续班2结束");
                        }
                    }
                }
                teacherContinuationClassDao.deleteByPrimaryKey(teacherContinuationClass1.getId());
                message += "学员号" + studentcode + ",班号" + classcode + "删除结束。<br/>";
            }
            message += "删除结束";
            map.put("flag", true);
            map.put("message", message);
            log.info("删除关系开始");
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除人班关系异常：" + e);
        }
        return map;
    }

    /**
     * 通过班号获取班级信息
     * 
     * @param class_code
     * @return
     */
    public Map<String, Object> getClassinfoByClasscode(String class_code) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(class_code)) {
            map.put("flag", false);
            map.put("message", "班号不能为空");
            return map;
        }
        try {
            XdfClassInfo xdfClassInfo = xdfClassInfoDao.selectByPrimaryKey(class_code);
            if (xdfClassInfo == null) {
                map.put("flag", false);
                map.put("message", "获取班号信息为空");
            }
            else {
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("xdfClassInfo", xdfClassInfo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取班级信息异常");
        }

        return map;
    }

    /**
     * 通过班号获取学员以及所报班级
     * 
     * @param class_code
     * @return
     */
    public Map<String, Object> getStudentinfoByClasscode(HttpServletRequest request, String class_code, String student_name) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(class_code)) {
            map.put("flag", false);
            map.put("message", "班号不能为空");
            return map;
        }
        HttpSession session = request.getSession();
        String email_addr = (String) session.getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        Users u = (Users) session.getAttribute(BindingConstants.BINDING_USERS);
        try {
            List<TeacherContinuationStudentInfo> list1 = getStudentsByclassCodes(class_code, "25");
            List<TeacherContinuationStudentInfo> list = new ArrayList<TeacherContinuationStudentInfo>();
            if (list1 != null && list1.size() > 0) {
                for (TeacherContinuationStudentInfo s : list1) {
                    if (StringUtils.isNotEmpty(student_name) && s.getStudent_name().indexOf(student_name) < 0) {
                        continue;
                    }
                    Map<String, Object> mapparam = new HashMap<String, Object>();
                    mapparam.put("school_id", u.getCompanyId());
                    mapparam.put("email_addr", email_addr);
                    mapparam.put("student_code", s.getStudent_code());
                    List<TeacherContinuationClass> classlist = teacherContinuationTeacherClassDao.selectClassesinfoByStudentCodeAndEmailaddr(mapparam);
                    if (classlist != null && classlist.size() > 0) {
                        s.setList(classlist);
                    }
                    list.add(s);
                }
                map.put("flag", true);
                map.put("message", "获取学员成功");
                map.put("list", list);
            }
            else {
                map.put("flag", false);
                map.put("message", "该班没有学员");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "班号不能为空");
        }

        return map;
    }

    // 获取升班期token
    @SuppressWarnings("unchecked")
    public String getToken() {
        if (redisTemplateUtil.isExist("SBTOKEN")) {
            return (String) redisTemplateUtil.get("SBTOKEN");
        }
        String access_token = "";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("grant_type", "password");
        params.put("client_id", client_id);
        params.put("client_secret", client_secret);
        params.put("username", username);
        params.put("password", password);
        try {
            String res = HttpClientUtil.httpPostRequest(tokenUrl, null, params);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
            access_token = map.get("access_token").toString();
            redisTemplateUtil.set("SBTOKEN", access_token, 29 * 60L);
            return access_token;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 新增升班期
    @SuppressWarnings("unchecked")
    public Map<String, Object> addXbgx(List<SchoolXbgx> xbgxs) {
        Map<String, Object> result = new HashMap<String, Object>();
        String token = getToken();
        if (StringUtils.isEmpty(token)) {
            result.put("flag", false);
            result.put("message", "获取TOKEN失败");
            return result;
        }
        try {
            String json = JSON.toJSONString(xbgxs);
            System.out.println(json);
            String res = HttpClientUtil.httpPostJson(addUrl, json, token);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(res);
            String success = map.get("success").toString();
            if ("true".equals(success)) {
                result.put("flag", true);
                result.put("message", "插入成功");
            }
            else {
                result.put("flag", false);
                result.put("message", "插入失败");
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "插入失败");
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public Map<String, Object> delXbgx(List<SchoolXbgx> xbgxs) {
        Map<String, Object> result = new HashMap<String, Object>();
        String token = getToken();
        if (StringUtils.isEmpty(token)) {
            result.put("flag", false);
            result.put("message", "获取TOKEN失败");
            return result;
        }
        try {
            String json = JSON.toJSONString(xbgxs);
            String res = HttpClientUtil.httpPostJson(delUrl, json, token);
            if (StringUtils.isNotEmpty(res)) {
                Map map = (Map) JSON.parse(res);
                String success = map.get("success").toString();
                if ("true".equals(success)) {
                    result.put("flag", true);
                    result.put("message", "删除成功");
                }
                else {
                    result.put("flag", false);
                    result.put("message", "删除失败");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "删除失败");
        }
        return result;
    }

    // 通过班号获取班号下的学员号
    @SuppressWarnings("unchecked")
    public List<TeacherContinuationStudentInfo> getStudentsByclassCodes(String classCode, String schoolId) {
        List<TeacherContinuationStudentInfo> stuList = new ArrayList<TeacherContinuationStudentInfo>();// 学员
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
                            TeacherContinuationStudentInfo cs = new TeacherContinuationStudentInfo();
                            classResult = (Map<String, Object>) js.get(i);
                            String name = (String) classResult.get("Name");
                            String code = (String) classResult.get("Code");
                            cs.setStudent_code(code);
                            cs.setStudent_name(name);
                            cs.setClass_code(classCode);
                            stuList.add(cs);
                        }
                    }
                }
            }
            Set<TeacherContinuationStudentInfo> set = new HashSet<TeacherContinuationStudentInfo>();
            set.addAll(stuList);
            stuList.clear();
            stuList.addAll(set);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stuList;
    }

    // 往续班2.0插入学员号和班号
    public Boolean addstudentcodememcached(String config_id, String studentcode, String classcode, String schoolid) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除续班2.0人班关系
    public Boolean delstudentcodememcached(String config_id, String studentcode, String classcode, String schoolid) {
        try {
            String url = "http://wxpay.xdf.cn/xdf/xdfstudentcodecontinuedclass/delstudentcodememcached/forhf.do?";
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
}
