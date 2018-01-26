package com.human.jw.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.human.jw.dao.JwDao;
import com.human.jw.dao.JwJyzUserDao;
import com.human.jw.entity.JwClass;
import com.human.jw.entity.JwCourse;
import com.human.jw.entity.JwJyzUser;
import com.human.jw.entity.JwTeacherGrade;
import com.human.jw.entity.JwTeacherInfo;
import com.human.jw.entity.JwTeacherSite;
import com.human.jw.entity.OrgEmployeeTree;
import com.human.jw.service.JwService;
import com.human.manager.dao.RoleDao;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Role;
import com.human.manager.entity.UserRole;
import com.human.manager.entity.Users;
import com.human.order.utils.MD5Util;
import com.human.utils.Common;
import com.human.utils.HttpClientUtil;
import com.human.utils.Md5Tool;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;

@Service
public class JwServiceImpl implements JwService{
    
    @Autowired
    private JwDao jwDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JwJyzUserDao jyzUserDao;
    
    @Value("${jw.teacherUrl}")
    private  String teacherUrl;
    
    @Value("${jw.kebiaoUrl}")
    private  String kebiaoUrl;
    
    @Value("${jw.studentsUrl}")
    private  String studentsUrl;
    
    @Value("${jw.classUrl}")
    private  String classUrl;
    
    @Value("${jw.teacherMethod}")
    private  String teacherMethod;
    
    @Value("${jw.kebiaoMethod}")
    private  String kebiaoMethod;
    
    @Value("${jw.studentsMethod}")
    private  String studentsMethod;
    
    @Value("${jw.appid}")
    private  String appid;
    
    @Value("${jw.appkey}")
    private  String appkey;
    
    @Override
    public List<OrgEmployeeTree> queryTreeById(String id) {
        return jwDao.queryTreeById(id);
    }
    
    @Override
    public PageView queryPageView(PageView pageView, JwTeacherInfo jwTeacherInfo) throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        System.out.println(Common.getAuthenticatedUsername());
        String email = Common.getAuthenticatedUsername();
        List<JwJyzUser> jus = jyzUserDao.selectByEmail(email);
        String jyzs = "";
        for(JwJyzUser jju:jus){
            jyzs += StringUtils.isEmpty(jyzs)?jju.getJyz():","+jju.getJyz();
        }
        if(StringUtils.isNotEmpty(jyzs)){
            jwTeacherInfo.setJyz(jyzs);
        }
        Integer isGz = jwDao.isGaozhongManager(email);
        if(isGz!=null && isGz>0){
            jwTeacherInfo.setRoleName("高中数学");
        }
        map.put("paging", pageView);
        map.put("t", jwTeacherInfo);
        List<JwTeacherInfo> list = jwDao.selectTeacherPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public JwTeacherInfo getTotalCnSj(JwTeacherInfo jwTeacherInfo) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        String email = Common.getAuthenticatedUsername();
        List<JwJyzUser> jus = jyzUserDao.selectByEmail(email);
        String jyzs = "";
        for(JwJyzUser jju:jus){
            jyzs += StringUtils.isEmpty(jyzs)?jju.getJyz():","+jju.getJyz();
        }
        if(StringUtils.isNotEmpty(jyzs)){
            jwTeacherInfo.setJyz(jyzs);
        }
        Integer isGz = jwDao.isGaozhongManager(email);
        if(isGz!=null && isGz>0){
            jwTeacherInfo.setRoleName("高中数学");
        }
        
        map.put("t", jwTeacherInfo);
        return jwDao.getCnSj(map);
    }
    
    @Override
    public JwTeacherInfo getTeacherKsTj(String teacherCode) throws Exception {
        JwTeacherInfo jti = new JwTeacherInfo();
        //上周时间
        long szSj = 0;
        //上月时间
        long sySj = 0;
        //下月时间
        long xySj = 0;
        //财年时间
        long cnSj = 0;
        //菜鸟开始到上月结束
        long cnMonth = 0;
        //未上的课时
        long wsSj = 0;
        List<JwCourse> courses = getTeacherJwCourses(teacherCode, TimeUtil.getIntervalWeekFirstDay(-1), TimeUtil.getIntervalWeekLastDay(-1));;
        for(JwCourse jc:courses){
            szSj += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        
        List<JwCourse> courses1 = getTeacherJwCourses(teacherCode, TimeUtil.getIntervalMonthFirstDay(-1, null),TimeUtil.getIntervalMonthLastDay(-1, null));
        for(JwCourse jc:courses1){
            sySj += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        
        List<JwCourse> courses2 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalMonthFirstDay(0, null),TimeUtil.getIntervalMonthLastDay(0, null));
        for(JwCourse jc:courses2){
            xySj += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        
        List<JwCourse> courses3 = getTeacherJwCourses(teacherCode, getCainianStart(), TimeUtil.getIntervalWeekLastDay(-1));;
        for(JwCourse jc:courses3){
            cnSj += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        
        List<JwCourse> courses4 = getTeacherJwCourses(teacherCode, getCainianStart(), TimeUtil.getIntervalMonthLastDay(1, null));;
        for(JwCourse jc:courses4){
            cnMonth += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        double yjSj = cnMonth/getMonthSpace(getCainianStart(),TimeUtil.getIntervalMonthLastDay(1, null));
        
        
        String sixMonth = TimeUtil.getIntervalMonthLastDay(6, null);
        String cnEnd = getCainianEnd();
        String earlyEnd = (sixMonth.compareTo(cnEnd)>0)?cnEnd:sixMonth;
        List<JwCourse> courses5 = getTeacherJwCourses(teacherCode, TimeUtil.getIntervalWeekFirstDay(1),earlyEnd);;
        for(JwCourse jc:courses5){
            wsSj += getIntervalHour(jc.getSectBegin(),jc.getSectEnd());
        }
        jti.setSzSj(szSj+"");
        jti.setSySj(sySj+"");
        jti.setXySj(xySj+"");
        jti.setCnSj(cnSj+"");
        jti.setYjSj(yjSj+"");
        jti.setWsSj(wsSj+"");
        return jti;
    }
    
    @Override
    public PageView queryJyzPageView(PageView pageView, JwJyzUser jwJyzUser) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", jwJyzUser);
        List<JwJyzUser> list = jyzUserDao.selectJyzPage(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Transactional
    @Override
    public void addJyzUser(JwJyzUser jwJyzUser) {
        String jyzs = jwJyzUser.getJyz();
        Role role = new Role();
        role.setRoleName("教研组长");
        List<Role> roles = roleDao.queryRole(role);
        Long roleId = roles.get(0).getId();
        Users user = new Users();
        user.setLoginName(jwJyzUser.getEmail());
        List<Users> us = userDao.queryUser(user);
        Long userId = us.get(0).getId();
        UserRole ur = new UserRole();
        ur.setRoleId(roleId);
        ur.setUserId(userId);
        userDao.saveUserRole(ur);
        if(jyzs.indexOf(",")>0){
            String[] jyzArr = jyzs.split(",");
            for(String jyz:jyzArr){
                if(StringUtils.isNotEmpty(jyz)){
                    jwJyzUser.setJyz(jyz);
                    jyzUserDao.insert(jwJyzUser);
                }
            }
        }else{
            jyzUserDao.insert(jwJyzUser);
        }
    }
    
    @Override
    public JwTeacherInfo getTeacherInfoById(Integer id) {
        return jwDao.getTeacherInfoById(id);
    }
    
    @Transactional
    @Override
    public void edit(JwTeacherInfo jwTeacherInfo) {
        jwDao.updateByPrimaryKeySelective(jwTeacherInfo);
        
        String teacherCode = jwTeacherInfo.getTeacherCode();
        jwDao.delGradeByTeacherCode(teacherCode);
        String grades = jwTeacherInfo.getGrades();
        if(StringUtils.isNotEmpty(grades)){
            String[] gradeArr = grades.split(",");
            List<JwTeacherGrade> gradeList = new ArrayList<JwTeacherGrade>();
            for(String grade:gradeArr){
                if(StringUtils.isNotEmpty(grade)){
                    JwTeacherGrade g = new JwTeacherGrade(teacherCode,grade);
                    gradeList.add(g);
                }
            }
            jwDao.insertGrades(gradeList);
        }
        
        jwDao.delSitesByTeacherCode(jwTeacherInfo.getTeacherCode());
        String sites = jwTeacherInfo.getSites();
        if(StringUtils.isNotEmpty(sites)){
            String[] siteArr = sites.split(",");
            List<JwTeacherSite> siteList = new ArrayList<JwTeacherSite>();
            for(String site:siteArr){
                if(StringUtils.isNotEmpty(site)){
                    JwTeacherSite s = new JwTeacherSite(teacherCode,site);
                    siteList.add(s);
                }
            }
            jwDao.insertSites(siteList);
        }
    }
    
    @Override
    public void editSelective(JwTeacherInfo jwTeacherInfo) {
        jwDao.updateByPrimaryKeySelective(jwTeacherInfo);
    }
    
    @Override
    public Map<String,Object> initTeachData(String ids) throws Exception {
        Map<String,Object> result = new HashMap<String,Object>();
        String[] emails = ids.split(",");
        String errorEmails = "";
        for(String email :emails){
            JwTeacherInfo t = jwDao.getBaseMsgByEmail(email);
            String teacherCode = getTeacherCode(email);
            if(teacherCode==null){
                errorEmails += StringUtils.isEmpty(errorEmails)?email : ","+email;
                continue;
            }
            t.setTeacherCode(teacherCode);
            System.out.println("1-----------");
            List<String> classCodeList1 = new ArrayList<String>();
            List<String> classCodeList2 = new ArrayList<String>();
            List<JwCourse> courses1 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalMonthFirstDay(0,null),TimeUtil.getIntervalMonthLastDay(1,null));
            List<JwCourse> courses2 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalWeekFirstDay(-2),TimeUtil.getIntervalWeekLastDay(1));
            System.out.println("2-----------");
            for(JwCourse c:courses1){
                if(!classCodeList1.contains(c.getsClassCode())){
                    classCodeList1.add(c.getsClassCode()); 
                }
            }
            
            for(JwCourse c:courses2){
                if(!classCodeList2.contains(c.getsClassCode())){
                    classCodeList2.add(c.getsClassCode()); 
                }
            }
            
            String classCodes1 = "";
            for(String classCode : classCodeList1){
                if(classCodes1.equals("")){
                    classCodes1 += classCode;
                }else{
                    classCodes1 += ","+classCode;
                }
            }
            
            String classCodes2 = "";
            for(String classCode : classCodeList2){
                if(classCodes2.equals("")){
                    classCodes2 += classCode;
                }else{
                    classCodes2 += ","+classCode;
                }
            }
            int one = 0;
            int sex = 0;
            System.out.println("3-----------");
            if(StringUtils.isNotEmpty(classCodes1)){
                List<JwClass> jsClasses1 = getJwClassByCodes(classCodes1);
                if(jsClasses1!=null){
                    for(JwClass c:jsClasses1){
                        if(c.getClassStatus().equals("2")){
                            continue;
                        }
                        if("6".equals(c.getStudentMaxCount())){
                            sex++;
                        }
                    }
                }
            }
            
            if(StringUtils.isNotEmpty(classCodes2)){
                List<JwClass> jsClasses2 = getJwClassByCodes(classCodes2);
                if(jsClasses2!=null){
                    List<String> studentList = new ArrayList<String>();
                    for(JwClass c:jsClasses2){
                        /**
                         * 根据班号获取学员号
                         */
                        if("1".equals(c.getStudentMaxCount())){
                            List<String> results = getStudentCodeByClass(c.getClassCode());
                            if(results!=null && results.size()>0){
                                String studentCode = results.get(0);
                                if(!studentList.contains(studentCode)){
                                    studentList.add(studentCode);
                                    one++;
                                }
                            }
                        }
                    }
                }
            }
            
            t.setOneCurr(one);
            t.setSexCurr(sex);
            jwDao.insert(t);
        }
        result.put("flag", true);
        if(StringUtils.isNotEmpty(errorEmails)){
            result.put("errorEmails", errorEmails);
        }
        return result;
    }

    @Override
    public void refreshTechData(String ids) throws Exception {
        String[] idArr = ids.split(",");
        if(idArr.length>0){
            for(String id :idArr){
                JwTeacherInfo t = jwDao.getTeacherInfoById(Integer.valueOf(id));
                String teacherCode = getTeacherCode(t.getEmail());
                if(StringUtils.isEmpty(teacherCode)){
                    continue;
                }
                t.setTeacherCode(teacherCode);
                List<String> classCodeList1 = new ArrayList<String>();
                List<String> classCodeList2 = new ArrayList<String>();
                List<JwCourse> courses1 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalMonthFirstDay(0,null),TimeUtil.getIntervalMonthLastDay(1,null));
                List<JwCourse> courses2 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalWeekFirstDay(-2),TimeUtil.getIntervalWeekLastDay(1));
                for(JwCourse c:courses1){
                    if(!classCodeList1.contains(c.getsClassCode())){
                        classCodeList1.add(c.getsClassCode()); 
                    }
                }
                
                for(JwCourse c:courses2){
                    if(!classCodeList2.contains(c.getsClassCode())){
                        classCodeList2.add(c.getsClassCode()); 
                    }
                }
                
                String classCodes1 = "";
                for(String classCode : classCodeList1){
                    if(classCodes1.equals("")){
                        classCodes1 += classCode;
                    }else{
                        classCodes1 += ","+classCode;
                    }
                }
                
                String classCodes2 = "";
                for(String classCode : classCodeList2){
                    if(classCodes2.equals("")){
                        classCodes2 += classCode;
                    }else{
                        classCodes2 += ","+classCode;
                    }
                }
                
                int one = 0;
                int sex = 0;
                if(StringUtils.isNotEmpty(classCodes1)){
                    List<JwClass> jsClasses1 = getJwClassByCodes(classCodes1);
                    if(jsClasses1!=null){
                        for(JwClass c:jsClasses1){
                            if(c.getClassStatus().equals("2")){
                                continue;
                            }
                            if("6".equals(c.getStudentMaxCount())){
                                sex++;
                            }
                        }
                    }
                }
                
                if(StringUtils.isNotEmpty(classCodes2)){
                    List<JwClass> jsClasses2 = getJwClassByCodes(classCodes2);
                    if(jsClasses2!=null){
                        List<String> studentList = new ArrayList<String>();
                        for(JwClass c:jsClasses2){
                            /**
                             * 根据班号获取学员号
                             */
                            if("1".equals(c.getStudentMaxCount())){
                                List<String> results = getStudentCodeByClass(c.getClassCode());
                                if(results!=null && results.size()>0){
                                    String studentCode = results.get(0);
                                    if(!studentList.contains(studentCode)){
                                        studentList.add(studentCode);
                                        one++;
                                    }
                                }
                            }
                        }
                    }
                }
                t.setOneCurr(one);
                t.setSexCurr(sex);
                jwDao.updateByPrimaryKeySelective(t);
            }
        }
    }
    
    @Override
    public void refreshAllTechData() throws Exception {
        JwTeacherInfo jwTeacherInfo = new JwTeacherInfo();
        String email = Common.getAuthenticatedUsername();
        List<JwJyzUser> jus = jyzUserDao.selectByEmail(email);
        String jyzs = "";
        for(JwJyzUser jju:jus){
            jyzs += StringUtils.isEmpty(jyzs)?jju.getJyz():","+jju.getJyz();
        }
        if(StringUtils.isNotEmpty(jyzs)){
            jwTeacherInfo.setJyz(jyzs);
        }
        Integer isGz = jwDao.isGaozhongManager(email);
        if(isGz!=null && isGz>0){
            jwTeacherInfo.setRoleName("高中数学");
        }
        String ids = jwDao.getTeacherIdsByCondition(jwTeacherInfo);
        if(StringUtils.isNotEmpty(ids)){
            refreshTechData(ids);
        }
    }
    
    @Override
    public void jobRefresh() throws Exception {
        String ids = jwDao.getAllTeacherIds();
        if(StringUtils.isNotEmpty(ids)){
            refreshTechData(ids);
        }
    }
    
    @Override
    public void kstjRefresh() throws Exception {
        List<JwTeacherInfo> teachers = jwDao.getAllTeacher();
        for(JwTeacherInfo j:teachers){
            if(StringUtils.isNotEmpty(j.getTeacherCode())){
                JwTeacherInfo jt = getTeacherKsTj(j.getTeacherCode());
                j.setSzSj(jt.getSzSj());
                j.setSySj(jt.getSySj());
                j.setXySj(jt.getXySj());
                j.setCnSj(jt.getCnSj());
                j.setYjSj(jt.getYjSj());
                j.setWsSj(jt.getWsSj());
                jwDao.updateByPrimaryKeySelective(j);
            }
        }
        
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public String getTeacherCode(String email) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("method",teacherMethod);
        params.put("appid",appid);
        params.put("schoolId",25);
        params.put("email",email+"@xdf.cn");
        
        String signText = ("Method="+teacherMethod+"&Appid="+appid+"&schoolId=25&email="+email+"@xdf.cn&appkey="+appkey).toLowerCase();
        String sign = Md5Tool.get32md5(signText).toUpperCase();
        params.put("sign",sign);
        String result = HttpClientUtil.httpPostRequest(teacherUrl, null, params);
        
        Map map = (Map) JSON.parse(result);
        if(map.get("State").toString().equals("1") && map.get("Data")!=null){
            String data =  map.get("Data").toString();
            Map map1 = (Map) JSON.parse(data);
            return map1.get("sCode").toString();
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List<JwCourse> getTeacherJwCourses(String sCode,String start,String end) throws Exception {
        long t1 = System.currentTimeMillis();
        List<JwCourse> re = null;
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("method",kebiaoMethod);
        params.put("appid",appid);
        params.put("schoolId",25);
        params.put("teachercode",sCode);
        params.put("fromDay",start);
        params.put("toDay", end);
        String signText = ("fromDay="+start+"&Method="+kebiaoMethod
                + "&Appid="+appid
                + "&schoolId=25"
                + "&toDay="+end
                + "&teachercode="+sCode
                + "&appkey="+appkey).toLowerCase();
        String sign = Md5Tool.get32md5(signText).toUpperCase();
        params.put("sign",sign);
        String result = HttpClientUtil.httpPostRequest(kebiaoUrl, null, params);
        Map map = (Map) JSON.parse(result);
        if(map.get("State").toString().equals("1")){
            String data = map.get("Data").toString();
            re = JSON.parseArray(data, JwCourse.class);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        return re;
    }
    
    @Override
    public List<JwClass> getJwClassByCodes(String classCodes) throws Exception {
        String url = classUrl + "?schoolId=25&classCodes="+classCodes;
        String result = HttpClientUtil.httpGetRequest(url, null);
        List<JwClass> classes = JSON.parseArray(result, JwClass.class);
        return classes;
    }
    
    @Override
    public List<String> getStudentCodeByClass(String classCode) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        params.put("method", studentsMethod);
        params.put("appid", appid);
        params.put("schoolId", 25);
        params.put("classcode", classCode);
        String signText = ("Method=" + studentsMethod + "&Appid=" + appid + "&schoolId=25&classcode=" + classCode + "&appkey=" + appkey).toLowerCase();
        String sign = MD5Util.MD5Encode(signText, null).toUpperCase();
        params.put("sign", sign);
        String result = HttpClientUtil.httpPostRequest(studentsUrl, null, params);
        Map map = (Map) JSON.parse(result);
        
        if (map != null && map.size() > 0) {
            String data = map.get("Data").toString();
            JSONArray js = JSONArray.parseArray(data);
            if (js != null && js.size() > 0) {
                Map<String, Object> classResult = new HashMap<String, Object>();
                for (int i = 0, size = js.size(); i < size; i++) {
                    classResult = (Map<String, Object>) js.get(i);
                    String code = (String) classResult.get("Code");
                    if(StringUtils.isNotEmpty(code)){
                        list.add(code);
                        
                    }
                }
            }
        }
        return list;
    }
    
    @Override
    public List<Map<String, Object>> getStudentInfoClass(String classCode) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        params.put("method", studentsMethod);
        params.put("appid", appid);
        params.put("schoolId", 25);
        params.put("classcode", classCode);
        String signText = ("Method=" + studentsMethod + "&Appid=" + appid + "&schoolId=25&classcode=" + classCode + "&appkey=" + appkey).toLowerCase();
        String sign = MD5Util.MD5Encode(signText, null).toUpperCase();
        params.put("sign", sign);
        String result = HttpClientUtil.httpPostRequest(studentsUrl, null, params);
        Map map = (Map) JSON.parse(result);
        
        if (map != null && map.size() > 0) {
            String data = map.get("Data").toString();
            JSONArray js = JSONArray.parseArray(data);
            if (js != null && js.size() > 0) {
                Map<String, Object> classResult = new HashMap<String, Object>();
                for (int i = 0, size = js.size(); i < size; i++) {
                    classResult = (Map<String, Object>) js.get(i);
                        list.add(classResult);
                }
            }
        }
        return list;
    }
    
    @Transactional
    @Override
    public void del(String teacherCode) {
        jwDao.delTeacherByCode(teacherCode);
        jwDao.delGradeByTeacherCode(teacherCode);
        jwDao.delSitesByTeacherCode(teacherCode);
    }
    
    @Transactional
    @Override
    public int delJyzUser(JwJyzUser jwJyzUser) {
        Role role = new Role();
        role.setRoleName("教研组长");
        List<Role> roles = roleDao.queryRole(role);
        Long roleId = roles.get(0).getId();
        
        Users user = new Users();
        user.setLoginName(jwJyzUser.getEmail());
        List<Users> us = userDao.queryUser(user);
        Long userId = us.get(0).getId();
        UserRole ur = new UserRole();
        ur.setRoleId(roleId);
        ur.setUserId(userId);
        userDao.delUserRole(ur);
        
        return jyzUserDao.deleteByPrimaryKey(jwJyzUser.getId());
    }
    
    @Override
    public List<JwJyzUser> getJwJyzUserByEmail(String email) {
        return jyzUserDao.selectByEmail(email);
    }
    
    @Override
    public boolean isHasEditAu(String email) {
        Integer cnt = jwDao.isHasEditAu(email);
        if(cnt!=null && cnt>0){
            return true;
        }
        return false;
    }
    
    
    private long getIntervalHour(String dateStr1,String dateStr2){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            Date date1 = smf.parse(dateStr1);
            Date date2 = smf.parse(dateStr2);
            long beginTime = date1.getTime(); 
            long endTime = date2.getTime(); 
            return (endTime-beginTime)/1000/60/60;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static int getMonthSpace(String date1, String date2)
            throws ParseException {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        if(year2>year1){
            result = 12*(year2-year1)+ c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        }else{
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        }
        return result+1;

    }
    
    private String getCainianStart(){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date();
            String dateStr = smf.format(date);
            String cn = dateStr.substring(0, 4)+"-06-01";
            Date cnDate = smf.parse(cn);
            if(date.after(cnDate)){
                return cn;
            }else{
                return (Integer.valueOf(dateStr.substring(0, 4))-1)+"-06-01";
            }
            
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private String getCainianEnd(){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date();
            String dateStr = smf.format(date);
            String cn = dateStr.substring(0, 4)+"-05-31";
            Date cnDate = smf.parse(cn);
            if(!date.after(cnDate)){
                return cn;
            }else{
                return (Integer.valueOf(dateStr.substring(0, 4))+1)+"-05-31";
            }
            
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) throws ParseException {
        //List<JwCourse> courses2 = getTeacherJwCourses(teacherCode,TimeUtil.getIntervalWeekFirstDay(-2),TimeUtil.getIntervalWeekLastDay(1));
        //System.out.println(TimeUtil.getIntervalWeekFirstDay(-2)+"-----------"+TimeUtil.getIntervalWeekLastDay(1));
        System.out.println("2017-03-02".compareTo("2017-01-04"));
    }
}
