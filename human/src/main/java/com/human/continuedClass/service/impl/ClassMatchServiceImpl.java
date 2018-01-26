package com.human.continuedClass.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.human.basic.dao.EmployeeDao;
import com.human.basic.entity.AttachMent;
import com.human.basic.entity.MailMessage;
import com.human.continuedClass.dao.ClassDetailsDao;
import com.human.continuedClass.dao.ClassInformationDao;
import com.human.continuedClass.dao.ClassMatchDao;
import com.human.continuedClass.dao.ClassStudentsDao;
import com.human.continuedClass.dao.CombinationClassDao;
import com.human.continuedClass.dao.ContinuedClassRuleDao;
import com.human.continuedClass.dao.RecommendClassDao;
import com.human.continuedClass.dao.RuleBackPhotoDao;
import com.human.continuedClass.dao.SendCardMailDao;
import com.human.continuedClass.dao.SendcardMailRecordDao;
import com.human.continuedClass.entity.ClassDetails;
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.ClassToRcClassDto;
import com.human.continuedClass.entity.ContinuedClassRule;
import com.human.continuedClass.entity.RecommendClass;
import com.human.continuedClass.entity.RuleBackPhoto;
import com.human.continuedClass.entity.SendCardMail;
import com.human.continuedClass.entity.SendcardMailRecord;
import com.human.continuedClass.entity.StudentsToCourse;
import com.human.continuedClass.service.ClassMatchService;
import com.human.continuedClass.threadJob.InsertBatchRecommendClassThread;
import com.human.jobs.TaskExecutorUtil;
import com.human.manager.entity.Users;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.FileUtil;
import com.human.utils.HttpClientUtil;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;
import com.human.utils.QRCodeUtil;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.ExchangeMailReceiverInfo;
import com.human.utils.mailUtils.ExchangeMailUtil;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ClassMatchServiceImpl implements ClassMatchService {
    
    @Resource
    private RuleBackPhotoDao rbpDao;
    
    @Resource
    private ClassInformationDao  cDao;
    
    @Resource
    private ClassStudentsDao  cSDao;
    
    @Resource
    private  ClassMatchDao  cMDao;
    
    @Resource
    private ClassDetailsDao cDDao;
    
    @Resource
    private RecommendClassDao  rCDao;
    
    @Resource
    private ContinuedClassRuleDao ccrDao;
    
    @Resource
    private SendCardMailDao scmDao;
    
    @Resource
    private EmployeeDao employeeDao;
    
    @Resource
    private SendcardMailRecordDao smrDao; 
    
    @Resource
    private CombinationClassDao ccDao;
    
    
    @Resource
    private TaskExecutorUtil taskExecutorUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.continuedClass.backPhotoPath}")
    private String ruleBackPhotoPath;
    
    @Value("${oss.fileurl}")
    private String filePath;

    @Resource 
    private OSSUtil ossUtil;
    
    @Value("${cc.getClassDetailsUrl}")
    private String getClassDetailsUrl;
    
    @Value("${cc.schoolId}")
    private int schoolId;
    
    @Value("${cc.cardUrl}")
    private String cardUrl;
    
    public  static  final  String[]IMGTYPES={"gif", "jpeg", "jpg", "bmp", "png"} ;              
    

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveBackPhoto(RuleBackPhoto rbp, MultipartFile file) {
        Map<String,Object> map = new HashMap<String, Object>();
        String createUser=Common.getMyUser().getUsername();
        Date   createTime=new Date();
        String originalName="";
        try{
            //第一步：判断文件是否有效
            if(file==null || file.getSize()<=0){
                map.put("flag", false);
                map.put("message", "上传续班卡背景图为空!"); 
                return map;
            }else{
                originalName = file.getOriginalFilename();
                if(!"".equals(originalName) && originalName != null){
                    String suffix=originalName.substring(originalName.lastIndexOf(".")+1).toLowerCase();
                    List<String> list=Arrays.asList(IMGTYPES);
                    if(!list.contains(suffix)){
                        map.put("flag", false);
                        map.put("message", "上传续班卡背景图格式错误!"); 
                        return map;
                    }    
                }   
            }
            //第二步：判断之前是否已经有续班卡背景图
            RuleBackPhoto r=rbpDao.selectByRuleId(rbp.getRuleId());
            if(r!=null){//如果有设置为无效
               r.setIsValid(true); 
               rbpDao.updateByPrimaryKeySelective(r);
            }
            //第三步：上传
            String newFileName = ruleBackPhotoPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
            Map<String, Object> uploadResult = ossUtil.uploadFile(ossUtil.getClient(), bucketName, newFileName, file);
            if ((boolean) uploadResult.get("flag")) {
                rbp.setPath(newFileName);
                rbp.setName(originalName);
                rbp.setCreateTime(createTime);
                rbp.setCreateUser(createUser);
            }else {
                map.put("flag", false);
                map.put("message", "续班卡背景图上传失败,请稍后重试!");
                return map;
            }
            rbpDao.insertSelective(rbp);
            map.put("flag", true);
            map.put("message", "上传续班卡背景图成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "上传续班卡背景图失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveStudentsToClass(ClassMatch cm) {
       Map<String,Object> map = new HashMap<String, Object>();
       try{
           Long ruleId=cm.getRuleId();
           //第一步：删除之前生成的学员-班级数据
           cMDao.deleteByRuleId(ruleId);
           //第二步：批量保存学员-班级数据
           List<ClassMatch> list=cMDao.selectCMBySchoolArea(ruleId);
           if(list!=null && list.size()>0){
               int totalcount=list.size();    
               int pagecount=0,pagesize=5000;    
               int m=totalcount%pagesize;    
               if(m>0){    
                 pagecount=totalcount/pagesize+1;    
               }else{    
                 pagecount=totalcount/pagesize;    
               }    
               for(int i=1;i<=pagecount;i++){
                   List<ClassMatch> subList=new ArrayList<ClassMatch>();
                   if(i==pagecount){
                        subList= list.subList((i-1)*pagesize,totalcount);    
                   }else{
                        subList= list.subList((i-1)*pagesize,pagesize*(i));
                   }
                   cMDao.insertByBatch(subList);
               } 
           }
           //第三步：删除之前生成的学员-推荐班级数据
           rCDao.deleteByRuleId(ruleId);
           //第四步：批量保存学员-推荐班级数据
           Map<Object, Object> queryMap = new HashMap<Object, Object>();
           queryMap.put("t", cm);
           List<ClassMatch> list1 = cSDao.query(queryMap);
           List<RecommendClass> totalInsertList=new ArrayList<RecommendClass>(5000);
           for(ClassMatch classMatch:list1){
               classMatch.setRuleId(ruleId);
               String oSchoolAreaName=classMatch.getoSchoolAreaName();
               String oGrade=classMatch.getoGrade();
               String[] oSchoolAreaNames=oSchoolAreaName.split(",");
               String[] oGrades=oGrade.split(",");
               Map<String, Object> paraMap = new HashMap<String, Object>();
               paraMap.put("oSchoolAreaNames", oSchoolAreaNames);
               paraMap.put("oGrades", oGrades);
               paraMap.put("t", classMatch);
               List<RecommendClass> list3=rCDao.selectRecommendClass(paraMap);
               if(list3!=null && list3.size()>0){
                   for(RecommendClass rc:list3){
                       rc.setName(classMatch.getName());
                       rc.setCode(classMatch.getCode());
                   }
                   totalInsertList.addAll(list3);
               } 
           }
           if(totalInsertList!=null && totalInsertList.size()>0){
               int totalcount=totalInsertList.size();    
               int pagecount=0,pagesize=5000;    
               int m=totalcount%pagesize;    
               if(m>0){    
                 pagecount=totalcount/pagesize+1;    
               }else{    
                 pagecount=totalcount/pagesize;    
               }    
               for(int i=1;i<=pagecount;i++){
                   List<RecommendClass> subList=new ArrayList<RecommendClass>(5000);
                   if(i==pagecount){
                        subList= totalInsertList.subList((i-1)*pagesize,totalcount);    
                   }else{
                        subList= totalInsertList.subList((i-1)*pagesize,pagesize*(i));
                   }
                   rCDao.insertByBatch(subList);
               } 
           }           
           map.put("flag", true);
           map.put("message", "生成学员续班数据成功!");
       }catch(Exception e){
           e.printStackTrace();
           map.put("flag", false);
           map.put("message", "生成学员续班数据失败!");
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
       } 
       return map;
    }
    
    @Override
    public Map<String, Object> saveRecommendClass(ClassMatch cm) {
        Map<String,Object> map = new HashMap<String, Object>();
        FutureTask<Boolean> dbtask =null;
        try{
            Long ruleId=cm.getRuleId();
            //第一步：删除之前生成的学员-推荐班级数据
            rCDao.deleteByRuleId(ruleId);
            /*批量保存学员-推荐班级数据*/ 
            //第一步：查询出每个学员
            List<ClassMatch> list=cMDao.queryStudentsByRuleId(cm);
            if(list!=null && list.size()>0){
                //开启多个线程
                List<FutureTask<Boolean>> taskResults = new ArrayList<FutureTask<Boolean>>(10);
                int totalcount=list.size();    
                int pagecount=0,pagesize=2000;    
                int m=totalcount%pagesize;    
                if(m>0){    
                  pagecount=totalcount/pagesize+1;    
                }else{    
                  pagecount=totalcount/pagesize;    
                }    
                for(int i=1;i<=pagecount;i++){
                    List<ClassMatch> subList=new ArrayList<ClassMatch>(2000);
                    if(i==pagecount){
                         subList= list.subList((i-1)*pagesize,totalcount);    
                    }else{
                         subList= list.subList((i-1)*pagesize,pagesize*(i));
                    }
                    InsertBatchRecommendClassThread at=new InsertBatchRecommendClassThread(cMDao,rCDao,ccDao,subList);
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
                        map.put("message", "生成学员推荐班级数据成功!");  
                        break;
                    }
                }
            }else{
                map.put("flag", false);
                map.put("message", "生成学员推荐班级数据失败!"); 
            }
        }catch(Exception e){
            dbtask.cancel(true);
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "生成学员推荐班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
        } 
        return map;
    }
    
   
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> exportClassToClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            int matchType=ServletRequestUtils.getIntParameter(request, "matchType"); 
            if(matchType==1){//匹配同教师
                List<Map<String,Object>> maplist =cDao.exportSelectByTeacher(ruleId);
                ExcelUtil<ClassMatch> ex=new ExcelUtil<ClassMatch>();
                String path = request.getSession().getServletContext().getRealPath("/static/temp/");
                ex.writeExcel(path+"exportClassToClass.xlsx", ClassMatch.class, maplist, response, TimeUtil.getCurrTime()+"匹配同教师班级-班级数据信息", 0, 1);   
            }else if(matchType==2){//匹配同科目
                List<Map<String,Object>> maplist =cDao.exportSelectBySubject(ruleId);
                ExcelUtil<ClassMatch> ex=new ExcelUtil<ClassMatch>();
                String path = request.getSession().getServletContext().getRealPath("/static/temp/");
                ex.writeExcel(path+"exportClassToClass.xlsx", ClassMatch.class, maplist, response, TimeUtil.getCurrTime()+"匹配同科目班级-班级数据信息", 0, 1);      
            }else{//匹配临近校区
                List<Map<String,Object>> maplist =cDao.exportSelectBySchoolArea(ruleId);
                ExcelUtil<ClassMatch> ex=new ExcelUtil<ClassMatch>();
                String path = request.getSession().getServletContext().getRealPath("/static/temp/");
                ex.writeExcel(path+"exportClassToClass.xlsx", ClassMatch.class, maplist, response, TimeUtil.getCurrTime()+"匹配临近校区班级-班级数据信息", 0, 1);      
            }
            map.put("flag", true);
            map.put("message", "导出班级-班级数据成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出班级-班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public Map<String, Object> exportClassToRcClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            List<Map<String,Object>> maplist =rCDao.exportSelectByRuleId(ruleId);
            ExcelUtil<ClassToRcClassDto> ex=new ExcelUtil<ClassToRcClassDto>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportClassToRcClass.xlsx", ClassToRcClassDto.class, maplist, response, TimeUtil.getCurrTime()+"班级-推荐班级数据信息", 0, 1);                   
            map.put("flag", true);
            map.put("message", "导出班级-推荐班级数据成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出班级-推荐班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
    @Override
    public Map<String, Object> exportClassToUpClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            List<Map<String,Object>> maplist =cDao.exportUpClassByRuleId(ruleId);
            ExcelUtil<ClassMatch> ex=new ExcelUtil<ClassMatch>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportClassToClass.xlsx", ClassMatch.class, maplist, response, TimeUtil.getCurrTime()+"班级-升班班级数据信息", 0, 1);                   
            map.put("flag", true);
            map.put("message", "导出班级-升班班级数据成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出班级-升班班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> exportStudentsToClass(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            List<Map<String,Object>> maplist =cMDao.exportAllStudentsToClass(ruleId);
            ExcelUtil<ClassMatch> ex=new ExcelUtil<ClassMatch>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportStudentsToClass.xlsx", ClassMatch.class, maplist, response, TimeUtil.getCurrTime()+"学员-班级数据信息", 0, 1);                  
            map.put("flag", true);
            map.put("message", "导出学员-班级数据成功!");    
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出学员-班级数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
        }
        return map;
    }

    @Override
    public PageView query(PageView pageView, ClassMatch cm) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView); 
        map.put("t", cm);
        long ruleId=cm.getRuleId();
        List<ClassMatch> list = cSDao.query(map);
        //先去判断是否已经生成数据
        long count=cMDao.selectByRuleId(ruleId);
        if(count!=0){
          //处理逻辑
            if(list!=null && list.size()>0){
                for(ClassMatch classMatch:list){
                   classMatch.setRuleId(ruleId);
                   //第一步：查询学员报的原班总数
                   long  countOfoClass=cMDao.selectCountOfoClass(classMatch);             
                   //第二步：查询学员续班总数
                   long  countOfcClass=cMDao.selectCountOfcClass(classMatch);               
                   //第三步：查询学员推荐班级总数                   
                   long countOfRecommendClass=rCDao.selectCountOfRecommendClass(classMatch);
                   String courseAllocation=countOfoClass+"/"+countOfcClass+"/"+countOfRecommendClass;
                   classMatch.setCourseAllocation(courseAllocation);
                   //第四步:判断配课成功与否
                   if(countOfoClass>countOfcClass){//失败
                       classMatch.setDistributionFlag("2");
                       classMatch.setDistributionReason("续班总数少于原班总数");
                   }else{
                       classMatch.setDistributionFlag("1");
                       String oGrade=classMatch.getoGrade();
                       if(oGrade.contains(",")){
                           classMatch.setDistributionReason("该学生有多个年级"); 
                       }
                   }               
                }
            }
        }
        pageView.setRecords(list); 
        return pageView;
    }



    @Override
    public PageView queryClassDetails(PageView pageView, ClassMatch cm) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", cm);
        List<ClassMatch> list = cMDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }



    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            cMDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除学员-班级数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除学员-班级数据失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }



    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> addStudentToClass(ClassMatch cm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cm.setCreateUser(Common.getMyUser().getUsername());
            cm.setCreateTime(new Date());
            //第一步：先查询班级详情表是否存在，不存在再去通过原班号调用接口获取原班详情信息
            String oClassCode=cm.getoClassCode();
            ClassDetails classDetails=cDDao.selectByClassCode(oClassCode);
            if(classDetails==null){
                classDetails=getCdetails(oClassCode);
                if(classDetails==null){
                    map.put("flag", false);
                    map.put("message", "通过原班号查询不到班级详情信息,请检查原班号是否输入正确!");  
                    return map;
                }
            }
            cm.setoClassName(classDetails.getClassName());
            cm.setoGrade(classDetails.getGradeName());
            cm.setoSchoolAreaName(classDetails.getClassAddress());
            cm.setoSubject(classDetails.getSubjectName());
            cm.setoTeacherName(classDetails.getTeacherName());
            //第二步：先查询班级详情表是否存在，不存在再去通过续班号调用接口获取原班详情信息
            String cClassCode=cm.getcClassCode();
            classDetails=cDDao.selectByClassCode(cClassCode);
            if(classDetails==null){
                classDetails=getCdetails(cClassCode);
                if(classDetails==null){
                    map.put("flag", false);
                    map.put("message", "通过续班号查询不到班级详情信息,请检查续班号是否输入正确!");  
                    return map;
                }
            }
            cm.setcClassName(classDetails.getClassName());
            cm.setcGrade(classDetails.getGradeName());
            cm.setcSchoolAreaName(classDetails.getClassAddress());
            cm.setcSubject(classDetails.getSubjectName());
            cm.setcTeacherName(classDetails.getTeacherName());
            cMDao.insertSelective(cm); 
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


    /**
     * 通过班号获取班级详情公用方法
     * @param classCodes
     */
    public  ClassDetails  getCdetails(String classCodes){
        //第一步:通过报名接口（我学）获取班级详细信息
        ClassDetails classDetails=new ClassDetails();
        String url = getClassDetailsUrl+"?schoolId="+schoolId+"&classCodes=" +classCodes;
        String result = HttpClientUtil.httpGetRequest(url, null);
        JSONArray js = JSONArray.parseArray(result);     
        if(js!=null && js.size()>0){  
            Map<String, Object> classResult =new HashMap<String, Object>();           
            for(int i=0,size=js.size();i<size;i++){
                classResult = (Map<String,Object>)js.get(i);
                String classCode=(String) classResult.get("classCode");
                String className=(String) classResult.get("className");
                String classAddress=(String) classResult.get("classAddress");
                String subjectName=(String) classResult.get("subjectName");
                String gradeName=(String) classResult.get("gradeName");
                List<Map<String, String>> teachList = (List<Map<String, String>>)classResult.get("teachers");
                String teacherName="";
                for (Map<String, String> m : teachList) {
                    teacherName = teacherName + m.get("teacherName") + ",";            
                }
                if(teacherName!=""){
                    teacherName = teacherName.substring(0, teacherName.length() - 1); 
                }
                //保存或更新
                classDetails.setClassCode(classCode);
                classDetails.setClassAddress(classAddress);
                classDetails.setClassName(className);
                classDetails.setGradeName(gradeName);;
                classDetails.setSubjectName(subjectName);
                classDetails.setTeacherName(teacherName);
            }    
        }else{
            classDetails=null;
        }
        return classDetails;
    }

    @Override
    public PageView queryRecommendClass(PageView pageView, RecommendClass rc) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", rc);
        List<RecommendClass> list = rCDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }



    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> deleteRc(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            rCDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除学员-班级数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除学员-班级数据失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }



    @Override
    public Map<String, Object> addRecommendClass(RecommendClass rc) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rc.setCreateUser(Common.getMyUser().getUsername());
            rc.setCreateTime(new Date());
            //第一步：先查询班级详情表是否存在，不存在再去通过推荐班号调用接口获取原班详情信息
            String classCode=rc.getClassCode();
            ClassDetails classDetails=cDDao.selectByClassCode(classCode);
            if(classDetails==null){
                classDetails=getCdetails(classCode);
                if(classDetails==null){
                    map.put("flag", false);
                    map.put("message", "通过推荐班号查询不到班级详情信息,请检查推荐班号是否输入正确!");  
                    return map;
                }
            }
            rc.setClassName(classDetails.getClassName());
            rc.setGrade(classDetails.getGradeName());
            rc.setSchoolAreaName(classDetails.getClassAddress());
            rc.setSubject(classDetails.getSubjectName());
            rc.setTeacherName(classDetails.getTeacherName());
            rCDao.insertSelective(rc); 
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
    public Map<String, Object> exportStudentsToCourse(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            List<Map<String,Object>> maplist = cSDao.queryList(ruleId);           
            for(Map<String,Object> map1:maplist){
                ClassMatch classMatch=new ClassMatch();
                String code=(String) map1.get("code");
                String name=(String) map1.get("name");
                classMatch.setCode(code);
                classMatch.setName(name);
                classMatch.setRuleId(ruleId);
                //第一步：查询学员报的原班总数
                long  countOfoClass=cMDao.selectCountOfoClass(classMatch);             
                //第二步：查询学员续班总数
                long  countOfcClass=cMDao.selectCountOfcClass(classMatch);               
                //第三步：查询学员推荐班级总数                   
                long countOfRecommendClass=rCDao.selectCountOfRecommendClass(classMatch);
                String courseAllocation=countOfoClass+"/"+countOfcClass+"/"+countOfRecommendClass;
                map1.put("courseAllocation", courseAllocation);
                //第四步:判断配课成功与否
                if(countOfoClass>countOfcClass){//失败
                    map1.put("distributionFlag", "失败");
                    map1.put("distributionReason", "续班总数少于原班总数");
                }else{
                    map1.put("distributionFlag", "成功");
                    String oGrade=(String) map1.get("oGrade");
                    if(oGrade.contains(",")){
                        map1.put("distributionReason", "该学生有多个年级");
                    }else{
                        map1.put("distributionReason", "");
                    }
                }                 
            }
            ExcelUtil<StudentsToCourse> ex=new ExcelUtil<StudentsToCourse>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportStudentsToCourse.xlsx", StudentsToCourse.class, maplist, response, TimeUtil.getCurrTime()+"学员-配课数据信息", 0, 1);                  
            map.put("flag", true);
            map.put("message", "导出学员-配课数据成功!");    
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出学员-配课数据失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
        }
        return map;
    }

    @Override
    public Map<String, Object> bath_exportCardPdf(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        long ruleId=Long.valueOf(request.getParameter("ruleId"));       
        ContinuedClassRule ccr=ccrDao.selectByPrimaryKey(ruleId);
        String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis());
        String zipFile = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis())+".zip";
        try{
            //查询并生成续班卡背景图            
            RuleBackPhoto rbp=rbpDao.selectByRuleId(ruleId);
            String photoPath=rbp.getPath();
            String photoUrl=filePath+photoPath;
            Image backImage= Image.getInstance(new URL(photoUrl));
            String code=ServletRequestUtils.getStringParameter(request,"code");
            String[]codes=code.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("codes", codes);
            paraMap.put("ruleId", ruleId);
            //第一步：创建文件夹维度            
            List<ClassMatch>schoolAreaList=cMDao.querySchoolAreaByStudents(paraMap);
            // 遍历校区           
            File directroy = new File(newFilePath);
            if (!directroy.exists() && !directroy.isDirectory()) {
                directroy.mkdir();
            }
            for(ClassMatch cm:schoolAreaList){
                String schoolArea=cm.getoSchoolAreaName();
                String schoolAreaFilePath = newFilePath + "/" +schoolArea;
                //创建校区总目录
                File schoolAreaDirectroy = new File(schoolAreaFilePath);
                if (!schoolAreaDirectroy.exists() && !schoolAreaDirectroy.isDirectory()) {
                    schoolAreaDirectroy.mkdir();
                }
                //创建年级目录
                ClassMatch classMatch=new ClassMatch();
                classMatch.setRuleId(ruleId);
                classMatch.setoSchoolAreaName(schoolArea);
                paraMap.put("t", classMatch);
                List<ClassMatch>gradeList=cMDao.queryGradeByStudents(paraMap);
                for(ClassMatch cm1:gradeList){
                    String grade=cm1.getoGrade();
                    String gradeFilePath = schoolAreaFilePath + "/" +grade;
                    File gradeDirectroy = new File(gradeFilePath);
                    if (!gradeDirectroy.exists() && !gradeDirectroy.isDirectory()) {
                        gradeDirectroy.mkdir();
                    }
                    //创建科目目录
                    classMatch.setoGrade(grade); 
                    paraMap.put("t", classMatch);
                    List<ClassMatch>subjectList=cMDao.querySubjectByStudents(paraMap);
                    for(ClassMatch cm2:subjectList){
                        String subject=cm2.getoSubject();
                        String subjectFilePath = gradeFilePath + "/" +subject;
                        File subjectDirectroy = new File(subjectFilePath);
                        if (!subjectDirectroy.exists() && !subjectDirectroy.isDirectory()) {
                            subjectDirectroy.mkdir();
                        }
                        //创建班号目录
                        classMatch.setoSubject(subject);
                        paraMap.put("t", classMatch);
                        List<ClassMatch>ClassCodeList=cMDao.queryClassCodeByStudents(paraMap);
                        if(ClassCodeList!=null && ClassCodeList.size()>0){
                            for(ClassMatch cm3:ClassCodeList){
                                String classCode=cm3.getoClassCode();
                                String classCodeFilePath = subjectFilePath + "/" +classCode;
                                File classCodeDirectroy = new File(classCodeFilePath);
                                if (!classCodeDirectroy.exists() && !classCodeDirectroy.isDirectory()) {
                                    classCodeDirectroy.mkdir();
                                }
                                classMatch.setoClassCode(classCode);
                                //查询教师
                                ClassDetails classDetails=cDDao.selectByClassCode(classCode);
                                if(classDetails!=null){
                                    classMatch.setoTeacherName(classDetails.getTeacherName());
                                }
                                //生成续班卡PDF
                                classMatch.setCode(code);
                                createCardPdf(request,response,classMatch,classCodeFilePath,backImage,1,null);
                            }
                        }                        
                    }                    
                }   
            }
            //生成ZIP压缩文件
            FileUtil.fileToZip(newFilePath, zipFile);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String((ccr.getRuleName()+"_续班卡.zip").getBytes("gbk"), "iso-8859-1"));
            InputStream inputStream = new FileInputStream(zipFile);
            OutputStream os = response.getOutputStream();
            int size = (int) zipFile.length();
            byte[] b = new byte[size];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
          }catch(Exception e){
              e.printStackTrace();
              map.put("flag", false);
              map.put("message", "批量导出续班卡失败!");
          }finally {
              FileUtil.deletefile(newFilePath);
              FileUtil.deletefile(zipFile);
          }
        return map;        
    }

    @Override
    public Map<String, Object> exportAllCard(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        long ruleId=Long.valueOf(request.getParameter("ruleId"));       
        ContinuedClassRule ccr=ccrDao.selectByPrimaryKey(ruleId);
        String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis());
        String zipFile = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis())+".zip";
        try{  
            //查询并生成续班卡背景图            
            RuleBackPhoto rbp=rbpDao.selectByRuleId(ruleId);
            String photoPath=rbp.getPath();
            String photoUrl=filePath+photoPath;
            Image backImage= Image.getInstance(new URL(photoUrl));
            //第一步：创建文件夹维度            
            List<ClassMatch>schoolAreaList=cMDao.querySchoolArea(ruleId);
            // 遍历校区           
            File directroy = new File(newFilePath);
            if (!directroy.exists() && !directroy.isDirectory()) {
                directroy.mkdir();
            }
            for(ClassMatch cm:schoolAreaList){
                String schoolArea=cm.getoSchoolAreaName();
                String schoolAreaFilePath = newFilePath + "/" +schoolArea;
                //创建校区总目录
                File schoolAreaDirectroy = new File(schoolAreaFilePath);
                if (!schoolAreaDirectroy.exists() && !schoolAreaDirectroy.isDirectory()) {
                    schoolAreaDirectroy.mkdir();
                }
                //创建年级目录
                ClassMatch classMatch=new ClassMatch();
                classMatch.setRuleId(ruleId);
                classMatch.setoSchoolAreaName(schoolArea);
                List<ClassMatch>gradeList=cMDao.queryGrade(classMatch);
                for(ClassMatch cm1:gradeList){
                    String grade=cm1.getoGrade();
                    String gradeFilePath = schoolAreaFilePath + "/" +grade;
                    File gradeDirectroy = new File(gradeFilePath);
                    if (!gradeDirectroy.exists() && !gradeDirectroy.isDirectory()) {
                        gradeDirectroy.mkdir();
                    }
                    //创建科目目录
                    classMatch.setoGrade(grade); 
                    List<ClassMatch>subjectList=cMDao.querySubject(classMatch);
                    for(ClassMatch cm2:subjectList){
                        String subject=cm2.getoSubject();
                        String subjectFilePath = gradeFilePath + "/" +subject;
                        File subjectDirectroy = new File(subjectFilePath);
                        if (!subjectDirectroy.exists() && !subjectDirectroy.isDirectory()) {
                            subjectDirectroy.mkdir();
                        }
                        //创建班号目录
                        classMatch.setoSubject(subject);
                        List<ClassMatch>ClassCodeList=cMDao.queryClassCode(classMatch);
                        if(ClassCodeList!=null && ClassCodeList.size()>0){
                            for(ClassMatch cm3:ClassCodeList){
                                String classCode=cm3.getoClassCode();
                                String classCodeFilePath = subjectFilePath + "/" +classCode;
                                File classCodeDirectroy = new File(classCodeFilePath);
                                if (!classCodeDirectroy.exists() && !classCodeDirectroy.isDirectory()) {
                                    classCodeDirectroy.mkdir();
                                }
                                classMatch.setoClassCode(classCode);
                                //查询教师
                                ClassDetails classDetails=cDDao.selectByClassCode(classCode);
                                if(classDetails!=null){
                                    classMatch.setoTeacherName(classDetails.getTeacherName());
                                }
                                //生成续班卡PDF                               
                                createCardPdf(request,response,classMatch,classCodeFilePath,backImage,2,null);
                            }
                        }                        
                    }                    
                }   
            }
            //生成ZIP压缩文件
            FileUtil.fileToZip(newFilePath, zipFile);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String((ccr.getRuleName()+"_续班卡.zip").getBytes("gbk"), "iso-8859-1"));
            InputStream inputStream = new FileInputStream(zipFile);
            OutputStream os = response.getOutputStream();
            int size = (int) zipFile.length();
            byte[] b = new byte[size];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出导出续班卡失败!"); 
        }finally {
            FileUtil.deletefile(newFilePath);
            FileUtil.deletefile(zipFile);
        }
        return map;
    }

    /**
     * 生成续班卡PDF
     * @param request
     * @param response
     * @param classMatch
     * @param type 1：批量  2:全部
     */
    public void createCardPdf(HttpServletRequest request,HttpServletResponse response,ClassMatch classMatch,String classCodeFilePath,Image backImage,int type,ExchangeMailReceiverInfo receiverInfo){
        try{
            //第一步：查询出续班卡上的数据
            List<ClassMatch>studentList=new ArrayList<ClassMatch>(10);
            if(type==2||type==3||type==4){
                studentList=cMDao.queryStudents(classMatch); 
            }else{
                String code=classMatch.getCode();
                String[]codes=code.split(",");
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("codes", codes);
                paraMap.put("t", classMatch);
                studentList=cMDao.queryStudentsByStudents(paraMap);                
            }        
            int count = studentList.size();// 总记录数
            if(count==0){
               return; 
            }
            int pageCount = 2;// 每页记录数
            int page = 0;// 总共页数
            if (count >= pageCount && count % pageCount == 0) {
                page = count / pageCount;
            } else {
                page = count / pageCount + 1;
            }
            ByteArrayOutputStream bos[] = new ByteArrayOutputStream[page];
            for (int item = 0; item < page; item++) {
                 bos[item] = new ByteArrayOutputStream();
                 OutputStream os=bos[item];
                 //设置要输出到磁盘上的文件名称
                 Document doc = new Document(PageSize.A4,10,10,10,10);
                 PdfWriter writer=PdfWriter.getInstance(doc,os);
                 fillData(item,doc,studentList,writer,backImage);          
                 writer.close();
            }
            String path = classCodeFilePath+"/"+classMatch.getoSchoolAreaName()+"_"+classMatch.getoGrade()+"_"+classMatch.getoSubject()+"_";
            if(StringUtils.isNotEmpty(classMatch.getoTeacherName())){
                path+=classMatch.getoTeacherName()+"教师_"+classMatch.getoClassCode()+ "续班卡.pdf";
            }else{
                path+=classMatch.getoClassCode()+ "续班卡.pdf";
            }           
            File file = new File(path);
            OutputStream fos = new FileOutputStream(file);
            Document doc = new Document();
            PdfCopy pdfCopy = new PdfCopy(doc, fos);
            doc.open();
            PdfImportedPage imPage = null;
            for (int i = 0; i < page; i++) {
                imPage = pdfCopy.getImportedPage(new PdfReader(bos[i].toByteArray()), 1);
                pdfCopy.addPage(imPage);
            }
            doc.close();
            //发送邮件
            if(type==3||type==4){
                String tempPath = request.getSession().getServletContext().getRealPath("/static/temp");
                sendMail(classMatch,studentList,receiverInfo,file,tempPath,type);
            }
        }catch(Exception e){
           e.printStackTrace(); 
        }
        
    }

    /**
     * 设置分页
     * @param count
     * @param doc
     * @param studentList
     */
    public void fillData(int count,Document doc,List<ClassMatch>studentList,PdfWriter writer,Image backImage) {
        int size = studentList.size();// 学员总记录数
        int startIndex = count * 2;// 第一条数据位置
        int endIndex = count * 2 + 1;// 第二条数据位置
        int pageLast = size / 2;
        doc.open();
        if(size%2!=0 && count==pageLast ){
            fillContent(startIndex,0,doc,studentList,writer, backImage);
        }else{
            fillContent(startIndex,endIndex,doc,studentList,writer, backImage);
        }
        doc.close();     
      }
    
    /**
     * 往PDF文件中写入内容
     * @param startIndex
     * @param endIndex
     * @param studentList
     */
    public void fillContent(int startIndex,int endIndex,Document document,List<ClassMatch>studentList,PdfWriter writer,Image backImage){
        try{
            ClassMatch cm1=studentList.get(startIndex);
            List<ClassMatch>courseDetailsList=cMDao.queryCourseDetails(cm1);
            /* 使用中文字体 */
            BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //标题字体
            Font titleFont = new Font(bf, 7, Font.NORMAL);
            //正文字体
            Font contextFont = new Font(bf,10, Font.NORMAL);
            Font contextFont1 = new Font(bf,10, Font.BOLD);
            PdfPTable table =null;
            ClassMatch cmt=new ClassMatch();
            String oClassDetails="";
            if(courseDetailsList!=null && courseDetailsList.size()>0){
                cmt=courseDetailsList.get(0);
                //拼接原班的明细
                oClassDetails=addOClassDetails(cmt);            
                Image image=backImage;
                image.scaleAbsolute(575,410);
                /* 设置图片的位置 */
                if(endIndex!=0){
                    image.setAbsolutePosition(10, 10); 
                }else{
                    image.setAbsolutePosition(10, 410); 
                }
                image.setAlignment(Image.UNDERLYING);
                document.open();
                document.add(image);             
                //原班（标题）
                table = new PdfPTable(1);
                //设置表格具体宽度
                table.setTotalWidth(380);
                table.getDefaultCell().setBorder(Cell.NO_BORDER);          
                addCommonCell(table,oClassDetails,titleFont);           
                table.writeSelectedRows(0, -1,15,820, writer.getDirectContent());
                //加入续班表格上面的文字
                table = new PdfPTable(1);
                //设置表格具体宽度
                table.setTotalWidth(380);
                table.getDefaultCell().setBorder(Cell.NO_BORDER);
                addCommonCell(table,cmt.getName()+"您好!根据您的学习情况我们为您推荐了以下课程",contextFont);
                table.writeSelectedRows(0, -1,180,600, writer.getDirectContent());
                //加入续班表格
                table = new PdfPTable(4);
                addCTable(table,contextFont1,contextFont,courseDetailsList);
                table.writeSelectedRows(0, -1,180,580, writer.getDirectContent());
                //加入二维码图片
                table = new PdfPTable(1);
                addCodeImg(table, cmt.getCode());
                table.writeSelectedRows(0, -1,25,620, writer.getDirectContent()); 
            }            
            if(endIndex!=0){
                //加入分割线
                Paragraph splitLine = new Paragraph();
                for(int i=0; i<150; i++){
                    Chunk chunk = new Chunk("-",contextFont);  
                    splitLine.add(chunk);  
                  }  
                table = new PdfPTable(1);
                //设置表格具体宽度
                table.setTotalWidth(570);
                table.getDefaultCell().setBorder(Cell.NO_BORDER);
                table.addCell(splitLine);
                table.writeSelectedRows(0, -1,10,440, writer.getDirectContent()); 
               /*  * * * * * * * * * * * *A4下半边 * * * * * * * * * * * * * * * * * * * * * */
                ClassMatch cm2=studentList.get(endIndex);
                courseDetailsList=cMDao.queryCourseDetails(cm2);
                if(courseDetailsList!=null && courseDetailsList.size()>0){
                    cmt=courseDetailsList.get(0);
                    //拼接原班的明细
                    oClassDetails=addOClassDetails(cmt);
                    Image image1 =backImage;
                    image1.scaleAbsolute(575,410);
                    /* 设置图片的位置 */
                    image1.setAbsolutePosition(10, 412);
                    image1.setAlignment(Image.UNDERLYING);
                    document.add(image1);
                    // 第一行（标题）
                    table = new PdfPTable(1);
                    //设置表格具体宽度
                    table.setTotalWidth(380);
                    table.getDefaultCell().setBorder(Cell.NO_BORDER);
                    //设置每一列所占的长度            
                    addCommonCell(table,oClassDetails,titleFont);   
                    table.writeSelectedRows(0, -1,15,410, writer.getDirectContent());
                    //加入续班表格上面的文字
                    table = new PdfPTable(1);
                    //设置表格具体宽度
                    table.setTotalWidth(380);
                    table.getDefaultCell().setBorder(Cell.NO_BORDER);
                    addCommonCell(table,cmt.getName()+"您好!根据您的学习情况我们为您推荐了以下课程",contextFont);
                    table.writeSelectedRows(0, -1,180,195, writer.getDirectContent());
                    //加入续班表格
                    table = new PdfPTable(4);
                    addCTable(table,contextFont1,contextFont,courseDetailsList);
                    table.writeSelectedRows(0, -1,180,175, writer.getDirectContent());
                    //加入二维码图片
                    table = new PdfPTable(1);
                    addCodeImg(table, cmt.getCode());
                    table.writeSelectedRows(0, -1,25,218, writer.getDirectContent());
                }
                 
            }            
        }catch(Exception e){
           e.printStackTrace(); 
        }
    }
    
    /**
     * 添加单元格公用方法
     * @param table
     * @param name
     * @param contextFont
     */
    public void addCommonCell(PdfPTable table,String name,Font contextFont){
        PdfPCell cell = new PdfPCell();
        Paragraph para = new Paragraph(name,contextFont);
        //设置该段落为居中显示
        para.setAlignment(Cell.ALIGN_CENTER);
        cell.setPhrase(para);
        cell.setBorderWidth(0);
        table.addCell(cell);
    }
    
    /**
     * 添加单元格公用方法
     * @param table
     * @param name
     * @param contextFont
     */
    public void addCommonCell2(PdfPTable table,String name,Font contextFont){
        PdfPCell cell = new PdfPCell();
        Paragraph para = new Paragraph(name,contextFont);
        //设置该段落为居中显示
        para.setAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(para);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(20f);
        table.addCell(cell);
    }
    
    /**
     * 拼接原班的明细公用方法
     * @param cmt
     */
    public String addOClassDetails(ClassMatch cmt){
        String oClassDetails=cmt.getoSchoolAreaName()+" "+cmt.getoTeacherName()+"老师 "+cmt.getoClassCode()+" "+cmt.getName()+" "+cmt.getCode()+" ";
        String oClassTime=getoClassTime(cmt);
        oClassDetails+=oClassTime;
        return oClassDetails;
    }
    
    
    /**
     * 创建续班表格公用方法
     * @param table
     * @param contextFont1
     * @param contextFont
     * @param courseDetailsList
     */
    public void addCTable(PdfPTable table,Font contextFont1,Font contextFont,List<ClassMatch>courseDetailsList){
        try{
            //设置每一列所占的长度
            table.setWidths(new int[]{18,35,12,35});
            //设置表格具体宽度
            table.setTotalWidth(400);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            addCommonCell2(table,"班号",contextFont1);
            addCommonCell2(table,"课程名称",contextFont1);
            addCommonCell2(table,"教师名称",contextFont1);
            addCommonCell2(table,"上课时间",contextFont1); 
            //查询出该学员报的其他班的续班明细
            List<ClassMatch>courseDetailsList2=cMDao.queryCourseDetails2(courseDetailsList.get(0));
            //查询出该学员推荐班级明细
            List<ClassMatch> courseDetailsList3=cMDao.queryClassMatchByRecommend(courseDetailsList.get(0));
            List<ClassMatch>totalCourseDetailsList=new ArrayList<ClassMatch>(20);
            totalCourseDetailsList.addAll(courseDetailsList);
            if(courseDetailsList2!=null && courseDetailsList2.size()>0 ){
                totalCourseDetailsList.addAll(courseDetailsList2); 
            }
            if(courseDetailsList3!=null && courseDetailsList3.size()>0 ){
                totalCourseDetailsList.addAll(courseDetailsList3); 
            }       
            //判断行数是否大于5
            int rows=totalCourseDetailsList.size();
            List<ClassMatch>courseDetailSubList=new ArrayList<ClassMatch>(5);
            if(rows>=5){
                courseDetailSubList=totalCourseDetailsList.subList(0, 5);
                for(ClassMatch cm:courseDetailSubList){
                    String cClassTime=getcClassTime(cm);
                    addCommonCell2(table,cm.getcClassCode(),contextFont);
                    addCommonCell2(table,cm.getcClassName(),contextFont);
                    addCommonCell2(table,cm.getcTeacherName(),contextFont);
                    addCommonCell2(table,cClassTime,contextFont);               
                }
            }else if(rows<5){
                for(ClassMatch cm:totalCourseDetailsList){
                    String cClassTime=getcClassTime(cm);
                    addCommonCell2(table,cm.getcClassCode(),contextFont);
                    addCommonCell2(table,cm.getcClassName(),contextFont);
                    addCommonCell2(table,cm.getcTeacherName(),contextFont);
                    addCommonCell2(table,cClassTime,contextFont);                    
                }                
            }
        }catch(Exception e){
           e.printStackTrace(); 
        }  
    }
    
    /**
     * 创建二维码图片公用方法
     * @param code
     */
    public void addCodeImg(PdfPTable table,String code){
        try{
            String code_url=cardUrl+"?student_code="+code+"&school_id=25&wx_ext=xbk";//二维码链接;
            BufferedImage bufferedImage=QRCodeUtil.createImage(code_url);
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            ImageIO.write(bufferedImage, "jpg", out);  
            byte[] b = out.toByteArray();  
            Image codeImg=Image.getInstance(b);
            codeImg.scaleAbsolute(100,100);
            //设置表格具体宽度
            table.setTotalWidth(120);
            table.getDefaultCell().setBorder(Cell.NO_BORDER);
            table.addCell(codeImg);
        }catch(Exception e){
            e.printStackTrace(); 
        }
        
    }

    /**
     * 拼接原班上课时间公用方法
     * @param cm
     * @return
     */
    public String getoClassTime(ClassMatch cm){
        String classTime="";
        String oClassStartDate=cm.getoClassStartDate();
        if(StringUtils.isNotEmpty(oClassStartDate)){
            classTime+=oClassStartDate;
        }
        String oClassEndDate=cm.getoClassEndDate();
        if(StringUtils.isNotEmpty(oClassEndDate)){
            classTime+="-"+oClassEndDate;
        }
        String oClassSprintTime=cm.getoClassSprintTime();
        if(StringUtils.isNotEmpty(oClassSprintTime)&& oClassSprintTime.indexOf("（")!=-1){
            oClassSprintTime=oClassSprintTime.substring(0, oClassSprintTime.indexOf("（")).trim();
        }else if(StringUtils.isNotEmpty(oClassSprintTime)&& oClassSprintTime.indexOf("(")!=-1){
            oClassSprintTime=oClassSprintTime.substring(0, oClassSprintTime.indexOf("(")).trim();
        }
        if(StringUtils.isNotEmpty(oClassSprintTime)){
            classTime+=" "+oClassSprintTime;
        }
        return classTime;
    }
    
    /**
     * 拼接续班上课时间公用方法
     * @param cm
     * @return
     */
    public String getcClassTime(ClassMatch cm){
        String classTime="";
        String cClassStartDate=cm.getcClassStartDate();
        if(StringUtils.isNotEmpty(cClassStartDate)){
            classTime+=cClassStartDate;
        }
        String cClassEndDate=cm.getcClassEndDate();
        if(StringUtils.isNotEmpty(cClassEndDate)){
            classTime+="-"+cClassEndDate;
        }
        String cClassSprintTime=cm.getcClassSprintTime();
        if(StringUtils.isNotEmpty(cClassSprintTime)&&cClassSprintTime.indexOf("（")!=-1){
            cClassSprintTime=cClassSprintTime.substring(0, cClassSprintTime.indexOf("（")).trim();
        }else if(StringUtils.isNotEmpty(cClassSprintTime)&& cClassSprintTime.indexOf("(")!=-1){
            cClassSprintTime=cClassSprintTime.substring(0, cClassSprintTime.indexOf("(")).trim();
        }
        if(StringUtils.isNotEmpty(cClassSprintTime)){
            classTime+=" "+cClassSprintTime;
        }
        return classTime;
    }

    @Override
    public Map<String, Object> checkHasBackPhoto(long ruleId) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            RuleBackPhoto rbp=rbpDao.selectByRuleId(ruleId);
            if(rbp==null){
                map.put("flag", false);
                map.put("message", "请先上传续班卡背景图!");
                return map;
            }
            map.put("flag", true);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "判断是否已经上传续班卡背景图失败!");
        }
        return map;
    }



    @Override
    public Map<String, Object> sendCardMail(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<String,Object>();
        long ruleId=Long.valueOf(request.getParameter("ruleId"));       
        ContinuedClassRule ccr=ccrDao.selectByPrimaryKey(ruleId);
        String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis());
        map.put("flag", true);
        map.put("message", "发送续班卡邮件成功!");
        try{
            //查询并生成续班卡背景图            
            RuleBackPhoto rbp=rbpDao.selectByRuleId(ruleId);
            String photoPath=rbp.getPath();
            String photoUrl=filePath+photoPath;
            Image backImage= Image.getInstance(new URL(photoUrl));
            //查询续班卡发件邮件
            SendCardMail scm=scmDao.selectByRuleId(ruleId);
            String sendServerHost = scm.getSendServerHost();
            String userName = scm.getMailUser();
            String password = scm.getMailPassword();
            String domain=scm.getMailDomain();
            String mailServerHost="https://"+sendServerHost+"/EWS/exchange.asmx";
            ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,userName,password,domain);
            //第一步：创建文件夹维度            
            List<ClassMatch>schoolAreaList=cMDao.querySchoolArea(ruleId);
            // 遍历校区           
            File directroy = new File(newFilePath);
            if (!directroy.exists() && !directroy.isDirectory()) {
                directroy.mkdir();
            }
            for(ClassMatch cm:schoolAreaList){
                String schoolArea=cm.getoSchoolAreaName();
                String schoolAreaFilePath = newFilePath + "/" +schoolArea;
                //创建校区总目录
                File schoolAreaDirectroy = new File(schoolAreaFilePath);
                if (!schoolAreaDirectroy.exists() && !schoolAreaDirectroy.isDirectory()) {
                    schoolAreaDirectroy.mkdir();
                }
                //创建年级目录
                ClassMatch classMatch=new ClassMatch();
                classMatch.setRuleId(ruleId);
                classMatch.setoSchoolAreaName(schoolArea);
                List<ClassMatch>gradeList=cMDao.queryGrade(classMatch);
                for(ClassMatch cm1:gradeList){
                    String grade=cm1.getoGrade();
                    String gradeFilePath = schoolAreaFilePath + "/" +grade;
                    File gradeDirectroy = new File(gradeFilePath);
                    if (!gradeDirectroy.exists() && !gradeDirectroy.isDirectory()) {
                        gradeDirectroy.mkdir();
                    }
                    //创建科目目录
                    classMatch.setoGrade(grade); 
                    List<ClassMatch>subjectList=cMDao.querySubject(classMatch);
                    for(ClassMatch cm2:subjectList){
                        String subject=cm2.getoSubject();
                        String subjectFilePath = gradeFilePath + "/" +subject;
                        File subjectDirectroy = new File(subjectFilePath);
                        if (!subjectDirectroy.exists() && !subjectDirectroy.isDirectory()) {
                            subjectDirectroy.mkdir();
                        }
                        //创建班号目录
                        classMatch.setoSubject(subject);
                        List<ClassMatch>ClassCodeList=cMDao.queryClassCode(classMatch);
                        if(ClassCodeList!=null && ClassCodeList.size()>0){
                            for(ClassMatch cm3:ClassCodeList){
                                String classCode=cm3.getoClassCode();
                                String classCodeFilePath = subjectFilePath + "/" +classCode;
                                File classCodeDirectroy = new File(classCodeFilePath);
                                if (!classCodeDirectroy.exists() && !classCodeDirectroy.isDirectory()) {
                                    classCodeDirectroy.mkdir();
                                }
                                classMatch.setoClassCode(classCode);
                                //查询教师
                                ClassInformation  classDetails=cDao.selectByClassCode(classMatch);
                                if(classDetails!=null){
                                    classMatch.setoTeacherName(classDetails.getTeacherName());
                                }
                                //生成续班卡PDF并发送邮件                              
                                createCardPdf(request,response,classMatch,classCodeFilePath,backImage,3,receiverInfo);
                            }
                        }                        
                    }                    
                }   
            } 
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "发送续班卡邮件失败!失败原因:"+e.getMessage());
        }finally {
            FileUtil.deletefile(newFilePath);
        }
        return map;
    }

    
    /**
     * 发送邮件公用方法
     * @param classMatch
     * @param studentList
     * @param file
     */
    @Async
    public void sendMail(ClassMatch classMatch,List<ClassMatch>studentList,ExchangeMailReceiverInfo receiverInfo,File file,String tempPath,int type){                
        boolean flag=false;
        MailMessage mailMessage=new MailMessage();
        String oClassCode=classMatch.getoClassCode();
        String failReason="";
        try{
            //设置发件人
            mailMessage.setFrom(receiverInfo.getUser()+"@xdf.cn");
            //设置收件人
            String oTeacherName=classMatch.getoTeacherName();
            if(StringUtils.isEmpty(oTeacherName)||"无".equals(oTeacherName)){
                return; 
            }
            mailMessage.setTo(getRecipients(oTeacherName));
            //设置主题
            String createTime=TimeUtil.date2String(new Date(),"MM月dd日");
            mailMessage.setSubject(createTime+"生成"+file.getName());
            //设置续班卡附件
            List<AttachMent> attachments = new ArrayList<AttachMent>();
            AttachMent a = new AttachMent();
            a.setAttachmentPath(file.getPath());
            a.setAttachmentPathType("2");
            a.setName(file.getName());
            attachments.add(a);
            mailMessage.setAttachments(attachments);
            //设置邮件内容
            String message="<p>"+oTeacherName+"老师：<span></span></p>"
                           +"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好！续班窗口期即将开放，为了方便老师和家长提高续班的效率，更方便的网报，信管为每个班的学员特别定制了“专属续班卡”。<span></span></p>"
                           +"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;续班卡：<b>查看附件</b>您可以随时扫描<b>电子版续班卡</b>以对学生的续班课程进行修改或添加，您同样可以把学生的续班卡截图发给家长，家长通过扫描二维码也可以进行修改或添加。<span></span></p>"
                           +"<p style='text-indent:24.0pt;'> 您在使用中如果有任何的问题可以联系信管部老师！<span></span></p>"
                           +"<p style='text-indent:24.0pt;'><b>附件中的<span>pdf</span>文件</b>是您<span>"+oClassCode+"</span>班级的续班卡，请查收！</p>"
                           +"<p>&nbsp;</p><p  style='text-indent:21.0pt;'><b>【如何找到我的续班卡】</b><b>：<span></span></b></p>"
                           +"<p style='text-indent:18.0pt;'>1.关注“新东方合肥学校微服务”（<span>hfxdfkf</span>）公众号。<span></span></p>"
                           +"<p style='text-indent:18.0pt;'>2.点击菜单【报名】选择【我的续班卡】即可进入续班卡的班级配置页面修改学生的续班课程。<span></span></p>"
                           +"<p style='margin-left:39.0pt;text-indent:0cm;'>&nbsp;</p>"
                           +"<p style='text-indent:21.0pt;'><b>【教师如何使用续班卡】</b><b>：（</b>续班卡上的<b>二维码</b>是每个学生<b>唯一不重复</b>的。<b>）</b></p>"
                           +"<p style='margin-left:21.0pt;'>1.扫描二维码，进入该学生抢报页面。您可以点击页面右上角【添加班级】输入班号为该生添加续班的班级。点击班级信息右上角“×”可以删除班级<span></span></p><p  style='margin-left:21.0pt;'>2.老师为学生筛选好续班班级之后，学生自己扫二维码再进入就可以看到筛选后的班级了。<span></span></p>"
                           +"<p style='margin-left:21.0pt;'>3.点击页面左下角【切换】可以在班级其他学员间任意切换，同样可以添加删除学员。<span></span></p><p  style='margin-left:21.0pt;'>4.窗口期开始后学生扫二维码进入点击【立即抢报】即可直接报名（无需再输入姓名手机号登录）。<span></span></p><p  style='text-indent:21.0pt;'><b>&nbsp;</b>"
                           +"</p><p  style='text-indent:21.0pt;'><b>【家长如何通过续班卡报名】</b><b>：<span></span></b></p><p  style='margin-left:39.0pt;text-indent:-18.0pt;'>１.&nbsp; 用微信扫描续班卡正面的二维码，进入学生的续班页面。<span></span></p>"
                           +"<p  style='margin-left:39.0pt;text-indent:-18.0pt;'>２.&nbsp; 界面里显示的班级是新东方老师根据您的学习情况为您推荐的下一季的续班课程，您同样也可以在界面的右上角点击【添加课程】输入班号选择您心仪的课程，如果有不满意或者时间不合适的课程，您可以点击每个课程右上角的“×”进行删除课程。<span></span></p>"
                           +"<p  style='margin-left:39.0pt;text-indent:-18.0pt;'>３.&nbsp; 左边的“√”代表着默认选中该班级，如果这笔订单暂时不报这个班级可以取消勾选此班级，<b>橘色代表选中灰色代表未选中。</b><span></span></p>"
                           +"<p  style='margin-left:39.0pt;text-indent:-18.0pt;'>４.&nbsp; 在<b>续班窗口期开始</b>之后您可以点击右下角的【立即抢报】进行报名<span>(</span>无需再输入姓名手机号登录<span>)</span>。<span></span></p><p><span>&nbsp;</span></p>";
            mailMessage.setMessage(message);
            flag=ExchangeMailUtil.send(receiverInfo,mailMessage);            
        }catch(Exception e){
            e.printStackTrace();
            failReason=e.getMessage();
        }
        saveMailSendRecord(mailMessage,flag,classMatch,type,failReason);
    }
           
    /**
     * 查找收件人公用方法
     * @param oTeacherName
     * @return
     */
    public String[] getRecipients(String oTeacherName){  
        String[]to=oTeacherName.split(",");
        List<String>list=new ArrayList<String>();
        for(String teacherName:to ){
            Users user=employeeDao.queryUserByName(teacherName);
            if(user!=null){
                list.add(user.getEmailAddr());
            }            
        }
        String[] recipients= list.toArray(new String[list.size()]);        
        return recipients;
    }
    
    /**
     * 保存续班卡邮件发送记录
     */
    public void saveMailSendRecord(MailMessage mailMessage,boolean flag,ClassMatch classMatch,int type,String failReason){
        try{
            String oClassCode=classMatch.getoClassCode();
            String teacherName=classMatch.getoTeacherName();
            String schoolAreaName=classMatch.getoSchoolAreaName();
            String grade=classMatch.getoGrade();
            String subject=classMatch.getoSubject(); 
            long ruleId=classMatch.getRuleId();
            SendcardMailRecord scm=new SendcardMailRecord();
            scm.setCompanyId("128");
            scm.setSender(mailMessage.getFrom());
            scm.setRuleId(ruleId);
            scm.setClassCode(oClassCode);
            scm.setTeacherName(teacherName);
            scm.setSchoolAreaName(schoolAreaName);
            scm.setGrade(grade);
            scm.setSubject(subject);
            //收件人
            String[] to=mailMessage.getTo();
            String toUser="";
            if(to!=null && to.length>0){        
                for(String accept:to){
                    toUser+=accept+",";
                }
                toUser=toUser.substring(0,toUser.length()-1);
            }
            scm.setRecipientsTo(toUser);
            scm.setSendTime(new Date());
            //附件
            List<AttachMent>att=mailMessage.getAttachments();
            if(att!=null && att.size()>0){
                scm.setAttchment(att.get(0).getName());
            }
            scm.setSendComment(oClassCode+"续班卡邮件");
            if(flag){
                scm.setState("0");   
                scm.setResultDesc("邮件发送成功");
            }else{
                scm.setState("1"); 
                scm.setResultDesc("邮件发送失败,失败原因:"+failReason);
            }
            if(type==3){//第一次发
                smrDao.insertSelective(scm);  
            }else if(type==4){//补发
                if(flag){
                    smrDao.updateFailClassCode(scm);
                }else{
                    smrDao.insertSelective(scm);
                }                
            }      
        }catch(Exception e){
           e.printStackTrace(); 
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        
    }

    @Override
    public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String code=ServletRequestUtils.getStringParameter(request,"code");
            if(code!=null && code.length()>0 ){
                List<String> list = new ArrayList<String>();
                String[] idarray = code.split(",");
                list=Arrays.asList(idarray);
                map.put("s", list);
            }
            long ruleId=ServletRequestUtils.getLongParameter(request, "ruleId");
            map.put("ruleId", ruleId);
            List<Map<String,Object>> maplist =rCDao.exportSelect(map);
            ExcelUtil<RecommendClass> ex=new ExcelUtil<RecommendClass>();
            String path = request.getSession().getServletContext().getRealPath("/static/temp/");
            ex.writeExcel(path+"exportRecommendClass.xlsx", RecommendClass.class, maplist, response, TimeUtil.getCurrTime()+"学员-推荐班级信息", 0, 1);
            map.put("flag", true);
            map.put("message", "学员-推荐班级数据导出成功!");  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "学员-推荐班级数据导出失败!");
        }
        return map;
    }



    @Override
    public Map<String, Object> sendFailCardMail(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<String,Object>();        
        long ruleId=Long.valueOf(request.getParameter("ruleId")); 
        List<SendcardMailRecord> list=smrDao.queryFailClassCode(ruleId);
        if(list!=null && list.size()>0){
            ContinuedClassRule ccr=ccrDao.selectByPrimaryKey(ruleId);
            String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/" + ccr.getRuleName() + String.valueOf(System.currentTimeMillis());
            try{
                //查询并生成续班卡背景图            
                RuleBackPhoto rbp=rbpDao.selectByRuleId(ruleId);
                String photoPath=rbp.getPath();
                String photoUrl=filePath+photoPath;
                Image backImage= Image.getInstance(new URL(photoUrl));
                //查询续班卡发件邮件
                SendCardMail scm=scmDao.selectByRuleId(ruleId);
                String sendServerHost = scm.getSendServerHost();
                String userName = scm.getMailUser();
                String password = scm.getMailPassword();
                String domain=scm.getMailDomain();
                String mailServerHost="https://"+sendServerHost+"/EWS/exchange.asmx";
                ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,userName,password,domain);              
                File directroy = new File(newFilePath);
                if (!directroy.exists() && !directroy.isDirectory()) {
                    directroy.mkdir();
                }
                for(SendcardMailRecord scmr:list){                    
                    String oSchoolAreaName=scmr.getSchoolAreaName();
                    String schoolAreaFilePath = newFilePath + "/" +oSchoolAreaName;
                    //创建校区目录
                    File schoolAreaDirectroy = new File(schoolAreaFilePath);
                    if (!schoolAreaDirectroy.exists() && !schoolAreaDirectroy.isDirectory()) {
                        schoolAreaDirectroy.mkdir();
                    }
                    //创建年级目录
                    String oGrade=scmr.getGrade();
                    String gradeFilePath = schoolAreaFilePath + "/" +oGrade;
                    File gradeDirectroy = new File(gradeFilePath);
                    if (!gradeDirectroy.exists() && !gradeDirectroy.isDirectory()) {
                        gradeDirectroy.mkdir();
                    }
                    //创建科目目录
                    String oSubject=scmr.getSubject();
                    String subjectFilePath = gradeFilePath + "/" +oSubject;
                    File subjectDirectroy = new File(subjectFilePath);
                    if (!subjectDirectroy.exists() && !subjectDirectroy.isDirectory()) {
                        subjectDirectroy.mkdir();
                    }
                    //创建科目目录
                    String classCode=scmr.getClassCode();
                    String classCodeFilePath = subjectFilePath+ "/" +classCode;
                    File classCodeDirectroy = new File(classCodeFilePath);
                    if (!classCodeDirectroy.exists() && !classCodeDirectroy.isDirectory()) {
                        classCodeDirectroy.mkdir();
                    }
                    ClassMatch classMatch=new ClassMatch();
                    classMatch.setRuleId(ruleId);
                    classMatch.setoClassCode(classCode);
                    classMatch.setoSchoolAreaName(oSchoolAreaName);
                    classMatch.setoGrade(oGrade);
                    classMatch.setoSubject(oSubject);
                    //查询教师
                    ClassInformation  classDetails=cDao.selectByClassCode(classMatch);
                    if(classDetails!=null){
                        classMatch.setoTeacherName(classDetails.getTeacherName());
                        
                    }
                    //生成续班卡PDF并补发送邮件                              
                    createCardPdf(request,response,classMatch,classCodeFilePath,backImage,4,receiverInfo);  
                }
                map.put("flag", true);
                map.put("message", "补发续班卡邮件成功!");
            }catch (Exception e) {
                e.printStackTrace();
                map.put("flag", false);
                map.put("message", "补发续班卡邮件失败!失败原因:"+e.getMessage());
            }finally {
                FileUtil.deletefile(newFilePath);
            }
        }else{
            map.put("flag", false);
            map.put("message", "续班卡邮件已全部发送成功,无需再补发!");
        }
        return map;
    }


}
