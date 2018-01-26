package com.human.ielts.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.ielts.dao.IeltsBookInfoDao;
import com.human.ielts.dao.IeltsClassEnrollDao;
import com.human.ielts.dao.IeltsClassTypeDao;
import com.human.ielts.dao.IeltsEnrollInfoDao;
import com.human.ielts.dao.IeltsStudentBookDao;
import com.human.ielts.dao.IeltsStudentInfoDao;
import com.human.ielts.dao.IeltsStudentTeacherDao;
import com.human.ielts.dao.IeltsTeacherInfoDao;
import com.human.ielts.entity.IeltsBookInfo;
import com.human.ielts.entity.IeltsClassEnroll;
import com.human.ielts.entity.IeltsClassType;
import com.human.ielts.entity.IeltsEnrollInfo;
import com.human.ielts.entity.IeltsStudentAndTeacher;
import com.human.ielts.entity.IeltsStudentBook;
import com.human.ielts.entity.IeltsStudentInfo;
import com.human.ielts.entity.IeltsStudentTeacher;
import com.human.ielts.entity.IeltsTeacherInfo;
import com.human.ielts.service.IeltsStudentInfoService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class IeltsStudentInfoServiceImpl implements IeltsStudentInfoService {
    
    @Resource
    private IeltsStudentInfoDao ieltsStudentInfoDao;
    
    @Resource
    private IeltsEnrollInfoDao ieltsEnrollInfoDao;
    
    @Resource
    private IeltsStudentTeacherDao ieltsStudentTeacherDao;
    
    @Resource
    private IeltsClassEnrollDao ieltsClassEnrollDao;
    
    @Resource
    private IeltsStudentBookDao ieltsStudentBookDao;
    
    @Resource
    private IeltsBookInfoDao ieltsBookInfoDao;
    
    @Resource
    private IeltsClassTypeDao ieltsClassTypeDao;
    
    @Resource
    private IeltsTeacherInfoDao ieltsTeacherInfoDao;
    

    /**
     * 分页获取学生信息
     */
    public PageView getstudentinfo(PageView pageView, IeltsStudentInfo ieltsStudentInfo) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsStudentInfo);
        
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsStudentInfo> list = ieltsStudentInfoDao.query(map);
            
            if(list != null && list.size() > 0){
                for(IeltsStudentInfo is : list){
                    List<IeltsBookInfo> book_list = ieltsBookInfoDao.selectByStudentId(is.getId());
                    List<IeltsClassType> class_list = ieltsClassTypeDao.selectByStudentId(is.getId());
                    List<IeltsTeacherInfo> teacher_list = ieltsTeacherInfoDao.selectByStudentId(is.getId());
                    if(book_list != null && book_list.size() > 0){
                        is.setIelts_book_info_list(book_list);
                    }
                    
                    if(class_list != null && class_list.size() > 0){
                        is.setIelts_class_type_list(class_list);
                    }
                    
                    if(teacher_list != null && teacher_list.size() > 0){
                        is.setIelts_teacher_info_list(teacher_list);
                    }
                }
            }
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }
    
    /**
     * 新增学员基础信息
     */
    public Map<String, Object> insertstudentinfo(IeltsStudentInfo ieltsStudentInfo, String classids, String bookids, String teachermails) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(ieltsStudentInfo == null){
            map.put("flag", false);
            map.put("message", "信息不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsStudentInfo.getStudent_name())){
            map.put("flag", false);
            map.put("message", "请填写学员姓名");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsStudentInfo.getStudent_phone())){
            map.put("flag", false);
            map.put("message", "请填写学员手机号码");
            return map;
        }
        
        try {
            
            //新增基础信息
            Map<String, Object> mapparam  = new HashMap<String, Object>();
            mapparam.put("student_name", ieltsStudentInfo.getStudent_name());
            mapparam.put("student_phone", ieltsStudentInfo.getStudent_phone());
            IeltsStudentInfo ieltsStudentInfo2 = ieltsStudentInfoDao.selectByStudentNameAndStudentPhone(mapparam);
            if(ieltsStudentInfo2 != null){
                map.put("flag", false);
                map.put("message", "已有该学员");
                return map;
            }
            
            ieltsStudentInfoDao.insertSelective(ieltsStudentInfo);
            //新增关联班级类型信息
            if(StringUtils.isNotEmpty(classids)){
                for(String classid : classids.split(",")){
                    IeltsClassEnroll ieltsClassEnroll = new IeltsClassEnroll();
                    Integer class_type_id = Integer.parseInt(classid);
                    ieltsClassEnroll.setClass_type_id(class_type_id);
                    ieltsClassEnroll.setStudent_id(ieltsStudentInfo.getId());
                    ieltsClassEnrollDao.insertSelective(ieltsClassEnroll);
                }
            }
            
            //新增教材关联信息
            if(StringUtils.isNotEmpty(bookids)){
                for(String bookid : bookids.split(",")){
                    IeltsStudentBook ieltsStudentBook = new IeltsStudentBook();
                    Integer book_id = Integer.parseInt(bookid);
                    ieltsStudentBook.setBook_id(book_id);
                    ieltsStudentBook.setStudent_id(ieltsStudentInfo.getId());
                    ieltsStudentBookDao.insertSelective(ieltsStudentBook);
                }
            }
            
            String message = "";
            //新增代课老师关联信息
            if(StringUtils.isNotEmpty(teachermails)){
                for(String teachermail : teachermails.split(";")){
                    
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(teachermail);
                    if(ieltsTeacherInfo == null){
                        message += "，邮箱前缀为：" + teachermail + "没有发现老师。";
                    }else{
                        IeltsStudentTeacher ieltsStudentTeacher = new IeltsStudentTeacher();
                        ieltsStudentTeacher.setStudent_id(ieltsStudentInfo.getId());
                        ieltsStudentTeacher.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsStudentTeacher.setEmail_addr(teachermail);
                        ieltsStudentTeacherDao.insertSelective(ieltsStudentTeacher); 
                    }
                }
            }
            
            map.put("flag", true);
            map.put("message", "新增结束"+message);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败："+e);
        }
        
        return map;
    }
    
    /**
     * 修改学员基础信息
     * @param ieltsStudentInfo
     * @param classids
     * @param bookids
     * @param teachermails
     * @return
     */
    @Transactional
    public Map<String, Object> updatestudentinfo(IeltsStudentInfo ieltsStudentInfo, String classids, String bookids, String teachermails) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(ieltsStudentInfo == null){
            map.put("flag", false);
            map.put("message", "信息不能为空");
            return map;
        }
        
        if(ieltsStudentInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "未知学员修改");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsStudentInfo.getStudent_name())){
            map.put("flag", false);
            map.put("message", "请填写学员姓名");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsStudentInfo.getStudent_phone())){
            map.put("flag", false);
            map.put("message", "请填写学员手机号码");
            return map;
        }
        
        try {
            
            //修改基础信息
            ieltsStudentInfoDao.updateByPrimaryKeySelective(ieltsStudentInfo);
            
            ieltsClassEnrollDao.deleteByStudentId(ieltsStudentInfo.getId());
            //修改关联班级类型信息
            if(StringUtils.isNotEmpty(classids)){
                for(String classid : classids.split(",")){
                    IeltsClassEnroll ieltsClassEnroll = new IeltsClassEnroll();
                    Integer class_type_id = Integer.parseInt(classid);
                    ieltsClassEnroll.setClass_type_id(class_type_id);
                    ieltsClassEnroll.setStudent_id(ieltsStudentInfo.getId());
                    ieltsClassEnrollDao.insertSelective(ieltsClassEnroll);
                }
            }
            
            ieltsStudentBookDao.deleteByStudentId(ieltsStudentInfo.getId());
            //修改教材关联信息
            if(StringUtils.isNotEmpty(bookids)){
                for(String bookid : bookids.split(",")){
                    IeltsStudentBook ieltsStudentBook = new IeltsStudentBook();
                    Integer book_id = Integer.parseInt(bookid);
                    ieltsStudentBook.setBook_id(book_id);
                    ieltsStudentBook.setStudent_id(ieltsStudentInfo.getId());
                    ieltsStudentBookDao.insertSelective(ieltsStudentBook);
                }
            }
            
            ieltsStudentTeacherDao.deleteByStudentId(ieltsStudentInfo.getId());
            //修改代课老师关联信息
            String message = "";
            if(StringUtils.isNotEmpty(teachermails)){
                for(String teachermail : teachermails.split(";")){
                    
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(teachermail);
                    
                    if(ieltsTeacherInfo == null){
                        message += "，邮箱前缀为：" + teachermail + "没有发现老师。";
                    }else{
                        IeltsStudentTeacher ieltsStudentTeacher = new IeltsStudentTeacher();
                        ieltsStudentTeacher.setStudent_id(ieltsStudentInfo.getId());
                        ieltsStudentTeacher.setEmail_addr(teachermail);
                        ieltsStudentTeacherDao.insertSelective(ieltsStudentTeacher);
                    }
                }
            }
            map.put("flag", true);
            map.put("message", "修改成功"+message);
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改失败："+e);
        }
        
        return map;
    }

    /**
     * 查询学员基础信息
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> selectstudentinfo(IeltsStudentInfo ieltsStudentInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(ieltsStudentInfo == null){
            map.put("flag", false);
            map.put("message", "请选择学员");
            return map;
        }
        
        if(ieltsStudentInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择学员");
            return map;
        }
        
        try {
            //新增基础信息
            IeltsStudentInfo ieltsStudentInfo1 = ieltsStudentInfoDao.selectByPrimaryKey(ieltsStudentInfo.getId());
            if(ieltsStudentInfo1 != null){
                List<IeltsBookInfo> book_list = ieltsBookInfoDao.selectByStudentId(ieltsStudentInfo1.getId());
                List<IeltsClassType> class_list = ieltsClassTypeDao.selectByStudentId(ieltsStudentInfo1.getId());
                List<IeltsTeacherInfo> teacher_list = ieltsTeacherInfoDao.selectByStudentId(ieltsStudentInfo1.getId());
                if(book_list != null && book_list.size() > 0){
                    ieltsStudentInfo1.setIelts_book_info_list(book_list);
                }
                   
                if(class_list != null && class_list.size() > 0){
                    ieltsStudentInfo1.setIelts_class_type_list(class_list);
                }
                    
                if(teacher_list != null && teacher_list.size() > 0){
                    ieltsStudentInfo1.setIelts_teacher_info_list(teacher_list);
                }
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("ieltsStudentInfo", ieltsStudentInfo1);
            }else{
                map.put("flag", false);
                map.put("message", "没有该学员");
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询失败："+e);
        }
        
        return map;
    }

    /**
     * 批量删除学生信息和分数
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    public Map<String, Object> deletestudentinfo(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请至少选择一个要删除的学员");
            return map;
        }
        
        //批量删除
        try {
            String[] idarray = ids.split(",");
            for(String idstring : idarray){
                Integer id = Integer.parseInt(idstring);
                //删除id关联的班级
                ieltsClassEnrollDao.deleteByStudentId(id);
                
                //删除id关联的教师
                ieltsStudentTeacherDao.deleteByStudentId(id);
                
                //删除id关联的教材
                ieltsStudentBookDao.deleteByStudentId(id);
                
                //删除id关联的考试信息
                ieltsEnrollInfoDao.deleteByStudentId(id);
                
                //删除id本身的基础信息
                ieltsStudentInfoDao.deleteByStudentId(id);
            }
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//全部回滚
            map.put("flag", false);
            map.put("message", "批量删除学员信息失败："+e);
        }
        return map;
    }

    /**
     * 批量上传学生信息和分数
     * @param request
     * @return
     */
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> upstudentinfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsStudentAndTeacher> ex=new ExcelUtil<IeltsStudentAndTeacher>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsStudentAndTeacher.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errormessage"));
                return map;
            }
            List<IeltsStudentAndTeacher> list=(List<IeltsStudentAndTeacher>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsStudentAndTeacher st : list){
                if(st== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                }else if(StringUtils.isEmpty(st.getStudent_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行姓名为空");
                }else if(StringUtils.isEmpty(st.getStudent_phone())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行手机号为空");
                }else{
                    Map<String, Object> mapparam  = new HashMap<String, Object>();
                    mapparam.put("student_name", st.getStudent_name());
                    mapparam.put("student_phone", st.getStudent_phone());
                    IeltsStudentInfo ieltsStudentInfo = ieltsStudentInfoDao.selectByStudentNameAndStudentPhone(mapparam);
                    IeltsStudentInfo ieltsStudentInfo1 = new IeltsStudentInfo();
                    if(ieltsStudentInfo == null){
                        ieltsStudentInfo1.setStudent_name(st.getStudent_name());
                        ieltsStudentInfo1.setStudent_phone(st.getStudent_phone());
                        ieltsStudentInfo1.setAdvisor(st.getAdvisor());
                        ieltsStudentInfo1.setStudent_manager(st.getStudent_manager());
                        ieltsStudentInfo1.setSchool(st.getSchool_name());
                        ieltsStudentInfo1.setGrade_name(st.getGrade_name());
                        if(st.getIs_planning_string().equals("是")){
                            ieltsStudentInfo1.setIs_planning(1);
                        }else{
                            ieltsStudentInfo1.setIs_planning(0);
                        }
                        ieltsStudentInfoDao.insertSelective(ieltsStudentInfo1);
                    }else{
                        ieltsStudentInfo1.setId(ieltsStudentInfo.getId());
                    }
                    
                    if(StringUtils.isNotEmpty(st.getEnroll_time_string())){
                        IeltsEnrollInfo ieltsEnrollInfo = new IeltsEnrollInfo();
                        ieltsEnrollInfo.setStudent_id(ieltsStudentInfo1.getId());
                        if(StringUtils.isNotEmpty(st.getTotal())){
                            ieltsEnrollInfo.setTotal(Double.valueOf(st.getTotal()));
                        }
                        if(StringUtils.isNotEmpty(st.getListening())){
                            ieltsEnrollInfo.setListening(Double.valueOf(st.getListening()));
                        }
                        if(StringUtils.isNotEmpty(st.getReading())){
                            ieltsEnrollInfo.setReading(Double.valueOf(st.getReading()));
                        }
                        if(StringUtils.isNotEmpty(st.getWriting())){
                            ieltsEnrollInfo.setWriting(Double.valueOf(st.getWriting()));
                        }
                        if(StringUtils.isNotEmpty(st.getOral())){
                            ieltsEnrollInfo.setOral(Double.valueOf(st.getOral()));
                        }
                        if(st.getIs_target().equals("是")){
                            ieltsEnrollInfo.setIs_target(1);
                        }else if(st.getIs_target().equals("否")){
                            ieltsEnrollInfo.setIs_target(0);
                        }
                        ieltsEnrollInfo.setTest_time(sdf.parse(st.getEnroll_time_string()));
                        ieltsEnrollInfoDao.insertSelective(ieltsEnrollInfo);
                    }
                    
                    
                    //新增关联班级类型信息
                    if(StringUtils.isNotEmpty(st.getClass_type_names())){
                        for(String class_type_name : st.getClass_type_names().split("/")){
                            Map<String, Object> mapparams = new HashMap<String, Object>();
                            mapparams.put("class_type_name", class_type_name);
                            IeltsClassType ieltsClassType = ieltsClassTypeDao.selectByClassName(mapparams);
                            if(ieltsClassType != null && ieltsClassType.getId() != null){
                                IeltsClassEnroll ieltsClassEnroll = new IeltsClassEnroll();
                                ieltsClassEnroll.setClass_type_id(ieltsClassType.getId());
                                ieltsClassEnroll.setStudent_id(ieltsStudentInfo1.getId());
                                ieltsClassEnrollDao.insertSelective(ieltsClassEnroll);
                            }
                        }
                    }
                    
                    //新增教材关联信息
                    if(StringUtils.isNotEmpty(st.getBook_type_name())){
                        for(String book_name : st.getBook_type_name().split("/")){
                            Map<String, Object> mapparams = new HashMap<String, Object>();
                            mapparams.put("book_name", book_name);
                            IeltsBookInfo ieltsBookInfo = ieltsBookInfoDao.selectByBookName(mapparams);
                            if(ieltsBookInfo != null && ieltsBookInfo.getId() != null){
                                IeltsStudentBook ieltsStudentBook = new IeltsStudentBook();
                                ieltsStudentBook.setBook_id(ieltsBookInfo.getId());
                                ieltsStudentBook.setStudent_id(ieltsStudentInfo1.getId());
                                ieltsStudentBookDao.insertSelective(ieltsStudentBook);
                            }
                        }
                    }
                    
                    //新增代课老师关联信息
                    if(StringUtils.isNotEmpty(st.getTeacher_names())){
                        for(String teachermail : st.getTeacher_names().split(";")){
                            IeltsStudentTeacher ieltsStudentTeacher = new IeltsStudentTeacher();
                            ieltsStudentTeacher.setStudent_id(ieltsStudentInfo1.getId());
                            ieltsStudentTeacher.setEmail_addr(teachermail);
                            ieltsStudentTeacherDao.insertSelective(ieltsStudentTeacher);
                        }
                    }
                    
                    n++;
                }
            }
            
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
        }catch(Exception e){
           throw new RuntimeException(e);
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            map.put("flag", flag);
            map.put("message", msg);
            return map;
        }
    }

    /**
     * 查询学员报考率
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> selectstudentapply(IeltsStudentInfo ieltsStudentInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsStudentInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsStudentInfo.getLeft_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsStudentInfo.getRight_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsStudentInfo.getLeft_integral_time());
            mapparam.put("right_test_time", ieltsStudentInfo.getRight_integral_time());
            //获取学员总数
            Integer studentcount = ieltsStudentInfoDao.selectStudentCount();
            
            //获取有报名日期的人数
            Integer enrollcount = ieltsEnrollInfoDao.selectEnrollCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取报考率成功");
            map.put("studentcount", studentcount);
            map.put("enrollcount", enrollcount);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取报考率出错："+e);
        }
        
        return map;
    }

    /**
     * 获取教师的学生信息
     * @param pageView
     * @param ieltsStudentInfo
     * @return
     */
    public PageView getteacherstudentinfo(PageView pageView, IeltsStudentInfo ieltsStudentInfo) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsStudentInfo);
        
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        map.put("mailaddr", myUser.getEmailAddr());
        //查询学生信息列表
        try {
            //数据库查询
            List<IeltsStudentInfo> list = ieltsStudentInfoDao.queryTeacherStudent(map);
            
            if(list != null && list.size() > 0){
                for(IeltsStudentInfo is : list){
                    List<IeltsBookInfo> book_list = ieltsBookInfoDao.selectByStudentId(is.getId());
                    List<IeltsClassType> class_list = ieltsClassTypeDao.selectByStudentId(is.getId());
                    List<IeltsTeacherInfo> teacher_list = ieltsTeacherInfoDao.selectByStudentId(is.getId());
                    if(book_list != null && book_list.size() > 0){
                        is.setIelts_book_info_list(book_list);
                    }
                    
                    if(class_list != null && class_list.size() > 0){
                        is.setIelts_class_type_list(class_list);
                    }
                    
                    if(teacher_list != null && teacher_list.size() > 0){
                        is.setIelts_teacher_info_list(teacher_list);
                    }
                }
            }
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }

    /**
     * 查询学员报考率
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> selectteacherstudentapply(IeltsStudentInfo ieltsStudentInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsStudentInfo == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsStudentInfo.getLeft_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        if(ieltsStudentInfo.getRight_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选取时间范围");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("left_test_time", ieltsStudentInfo.getLeft_integral_time());
            mapparam.put("right_test_time", ieltsStudentInfo.getRight_integral_time());
            mapparam.put("email_addr", myUser.getEmailAddr());
            //获取学员总数
            Integer studentcount = ieltsStudentInfoDao.selectTeacherStudentCount(mapparam);
            
            //获取有报名日期的人数
            Integer enrollcount = ieltsEnrollInfoDao.selectTeacherEnrollCount(mapparam);
            
            map.put("flag", true);
            map.put("message", "获取报考率成功");
            map.put("studentcount", studentcount);
            map.put("enrollcount", enrollcount);
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取报考率出错："+e);
        }
        
        return map;
    }
}
