package com.human.front.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.SmsTempService;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.RecruitAcceptanceService;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumePhoto;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.service.ResumeEducationHistoryService;
import com.human.resume.service.ResumeIntentionService;
import com.human.resume.service.ResumeLanguageService;
import com.human.resume.service.ResumePhotoService;
import com.human.resume.service.ResumeProjectExperienceService;
import com.human.resume.service.ResumeService;
import com.human.resume.service.ResumeWorkHistoryService;
import com.human.utils.Constants;

/**
 * 前台简历
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value = "/front/resume/")
public class FrontResumeController {
    
    private final  Logger logger = LogManager.getLogger(FrontResumeController.class);
    
    @Resource
    private ResumeService resumeService;
    
    @Resource
    private RecruitAcceptanceService recruitAcceptanceService;
    
    @Resource
    private SmsTempService smsService;
    
    @Resource
    private ResumeIntentionService resumeIntentionService;
    
    @Resource
    private ResumeEducationHistoryService  rehService;
    
    @Resource
    private  DictionaryService dictionaryService;
    
    @Resource
    private  ResumeLanguageService rlService;
    
    @Resource
    private ResumeWorkHistoryService  rwhService;
    
    @Resource
    private ResumeProjectExperienceService rpeService;
    
    @Resource
    private ResumePhotoService rpService; 
    
    @Value("${oss.fileurl}")
    private String filePath;
              
    /**
     * 进入我的简历首页
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toMainPage(HttpServletRequest request,HttpServletResponse response){
        logger.info("进入简历首页");
        ModelAndView mav = new ModelAndView("/front/resume/index");
        String positionId="";
        long resumeId;
        ResumeBase rb=new ResumeBase();
        try{
            // 通过微信openId去查询是否已经绑定求职者
            String openId = (String) request.getSession().getAttribute(Constants.OPENID);
            ResumeSeeker rs = recruitAcceptanceService.selectByOpenId(openId);
            resumeId=ServletRequestUtils.getLongParameter(request, "resumeId", 0L);
            if(resumeId!=0){
                rb=resumeService.selectByPrimaryKey(resumeId);
            }else{
                positionId = ServletRequestUtils.getStringParameter(request, "positionId","");                
                if (rs != null){                   
                    rb=resumeService.selectOriginalResumeByRsId(rs.getId());                    
                } 
            }           
            mav.addObject("resumeSeeker", rs);
            mav.addObject("resumeId", resumeId);
            mav.addObject("rb", rb);
            mav.addObject("positionId",positionId);
            mav.addObject("filepath",filePath);
        }catch(Exception e){
           e.printStackTrace();
           logger.error("进入简历首页失败!", e.getMessage()); 
        }        
        return mav ;
    }
        
    
    /**
     * 进入我的简历个人信息页
     * @return
     */
    @RequestMapping(value="personal")
    public ModelAndView toPersonalPage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/personal");
        ResumeBase rb;
        ResumeSeeker rs = recruitAcceptanceService.selectByPrimaryKey(resumeSeekerId);
        if(resumeId!=0){
            rb=resumeService.selectByPrimaryKey(resumeId);
        }else{            
            resumeService.copyResume(resumeSeekerId);
            rb = resumeService.selectOriginalResumeByRsId(resumeSeekerId);
        }
        mav.addObject("resumeId", resumeId);
        mav.addObject("rb", rb);
        mav.addObject("rs", rs);
        mav.addObject("filepath", filePath);
        mav.addObject("positionId", positionId);               
        return mav ;
    }
    
    /**
     * 编辑我的简历个人信息
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updateMyResumeBase")
    @ResponseBody
    public Map<String, Object> updateMyResumeBase(HttpServletRequest request, ResumeBase rb) {
        logger.info("修改我的简历个人信息");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = recruitAcceptanceService.updateMyResumeBase(request, rb);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("修改我的简历个人信息失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "修改我的简历个人信息失败！");
        }
        return map;
    }
    
    
    /**
     * 进入我的简历联系手机页
     * @return
     */
    @RequestMapping(value="getTelephone")
    public ModelAndView toTelephonePage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/telephone");
        ResumeBase rb;
        ResumeSeeker rs = recruitAcceptanceService.selectByPrimaryKey(resumeSeekerId);
        if(resumeId!=0){
            rb=resumeService.selectByPrimaryKey(resumeId);
        }else{            
            rb = resumeService.selectOriginalResumeByRsId(resumeSeekerId);
        }
        mav.addObject("resumeId", resumeId);
        mav.addObject("rs", rs);      
        mav.addObject("rb", rb);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    
    /**
     * 前台发送修改手机号短信验证码
     * @param telNumber 手机号
     * @return 发送注册验证码的结果
     */
    @ResponseBody
    @RequestMapping(value="sendCode")
    public Map<String,Object>sendCode(String telNumber,Long resumeSeekerId){
        Map<String,Object> map = new HashMap<String, Object>();    
        try{
            map=smsService.sendCode(telNumber,resumeSeekerId); 
        }catch(Exception e){
           e.printStackTrace(); 
           logger.error("发送修改手机号短信验证码失败!", e.getMessage());
           map.put("flag", false);
           map.put("msg", "修改手机号短信验证码发送失败!");
        }       
        return map;
    }
    
    /**
     * 编辑我的简历联系手机号
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updatePhone")
    @ResponseBody
    public Map<String, Object>updatePhone(String msg, ResumeBase rb) {
        logger.info("修改我的简历联系手机号");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = recruitAcceptanceService.updatePhone(msg, rb);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("修改我的简历联系手机号失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "修改我的简历联系手机号失败！");
        }
        return map;
    }
    
    
    /**
     * 进入我的简历求职意向页
     * @return
     */
    @RequestMapping(value="getInterntion")
    public ModelAndView toInterntionPage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/interntion");
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有求职意向
        ResumeIntention ri=null;
        if(resumeId!=0){
             ri=resumeIntentionService.selectByResumeId(resumeId);            
        }else{            
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历        
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                ri=resumeIntentionService.selectByResumeId(orb.getId());    
            }else{
                originalFlag=false; 
                flag=false;
            }
        }
        if(ri!=null){
            mav.addObject("ri", ri); 
        }else{
            flag=false; 
        }
        mav.addObject("resumeId", resumeId);
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 编辑我的简历求职意向
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updateInterntion")
    @ResponseBody
    public Map<String, Object>updateInterntion(HttpServletRequest request, ResumeIntention ri) {
        logger.info("编辑我的简历求职意向");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = resumeIntentionService.updateInterntion(request, ri);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存求职意向失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存求职意向失败！");
        }
        return map;
    }
    
   
    /**
     * 进入我的简历教育经历页
     * @return
     */
    @RequestMapping(value="getEducationHistory")
    public ModelAndView toEducationHistoryPage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/educationHistory");    
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有教育经历
        List<ResumeEducationHistory> rehList=null;
        if(resumeId!=0){
             rehList=rehService.selectByResumeId(resumeId);
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历       
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                 rehList=rehService.selectByResumeId(orb.getId());                
            }else{
                originalFlag=false; 
                flag=false;
            }
        }
        if(rehList!=null && rehList.size()>0){
            mav.addObject("rehList", rehList); 
        }else{
            flag=false; 
        }    
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        return mav ;
    }
    
    /**
     * 新增我的简历教育经历页
     * @return
     */
    @RequestMapping(value="addEducationHistory")
    public ModelAndView toAddEducationHistoryPage(Long resumeSeekerId,boolean originalFlag,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/addEducationHistory");   
        //获取学历类型
        List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("eduList", eduList);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存我的简历新增教育经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="insertEdu")
    @ResponseBody
    public Map<String, Object>insertEdu(HttpServletRequest request, ResumeEducationHistory reh) {
        logger.info("保存新增教育经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rehService.insertEdu(request, reh);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存新增教育经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存新增教育经历失败！");
        }
        return map;
    }
    
    /**
     * 进入我的简历教育经历查看页
     * @return
     */
    @RequestMapping(value="editEducationHistory")
    public ModelAndView editEducationHistory(Long ehId,Long resumeSeekerId,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/editEducationHistory");   
        //获取学历类型
        List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
        ResumeEducationHistory reh=rehService.selectByPrimaryKey(ehId); 
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String startTime=sdf.format(reh.getStartTime());
        Date date=reh.getEndTime();
        if(date!=null){
            String endTime=sdf.format(date);  
            mav.addObject("endTime", endTime);
        }
        mav.addObject("reh", reh);
        mav.addObject("eduList", eduList);
        mav.addObject("startTime", startTime);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存编辑后的我的简历教育经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="editEdu")
    @ResponseBody
    public Map<String, Object>editEdu(ResumeEducationHistory reh) {
        logger.info("保存修改后的教育经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rehService.editEdu(reh);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存修改后的教育经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存修改后的教育经历失败！");
        }
        return map;
    }
    
    /**
     * 删除我的简历教育经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="deleteEdu")
    @ResponseBody
    public Map<String, Object>deleteEdu(Long id) {
        logger.info("删除教育经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rehService.deleteEdu(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除教育经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "删除教育经历失败！");
        }
        return map;
    }
    
    
    /**
     * 进入我的简历语言能力页
     * @return
     */
    @RequestMapping(value="getLanguage")
    public ModelAndView toLanguagePage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/language");  
        List<ResumeLanguage> rehList=null;
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有语言能力
        if(resumeId!=0){
            rehList=rlService.selectByResumeId(resumeId);
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历            
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                rehList=rlService.selectByResumeId(orb.getId());                
            }else{
                originalFlag=false; 
                flag=false;
            }
        }
        if(rehList!=null && rehList.size()>0){
            mav.addObject("rehList", rehList); 
        }else{
            flag=false; 
        }
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    
    /**
     * 新增语言能力页
     * @return
     */
    @RequestMapping(value="addLanguage")
    public ModelAndView toAddLanguagePage(Long resumeSeekerId,boolean originalFlag,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/addLanguage"); 
        //获取语言类型
        List<DicData> languageList=dictionaryService.selectByDicCode("language");
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("languageList", languageList);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    
    /**
     * 保存新增的语言能力
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="insertLanguage")
    @ResponseBody
    public Map<String, Object>insertLanguage(HttpServletRequest request, ResumeLanguage rl) {
        logger.info("保存新增语言能力");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rlService.insertLanguage(request, rl);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存新增语言能力失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存新增语言能力失败！");
        }
        return map;
    }
    
    /**
     * 进入语言能力查看页
     * @return
     */
    @RequestMapping(value="editLanguage")
    public ModelAndView editLanguage(Long ehId,Long resumeSeekerId,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/editLanguage");   
        //获取语言类型
        List<DicData> languageList=dictionaryService.selectByDicCode("language");
        ResumeLanguage rl=rlService.selectByPrimaryKey(ehId);       
        mav.addObject("rl", rl);
        mav.addObject("filepath",filePath);
        mav.addObject("languageList", languageList);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存编辑后的语言能力
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="editLu")
    @ResponseBody
    public Map<String, Object>editLu(HttpServletRequest request, ResumeLanguage rl) {
        logger.info("保存编辑后的语言能力");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rlService.editLanguage(request, rl);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存编辑后的语言能力失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存编辑后的语言能力失败！");
        }
        return map;
    }
    
    /**
     * 删除语言能力
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="deleteLanguage")
    @ResponseBody
    public Map<String, Object>deleteLanguage(Long id) {
        logger.info("删除语言能力");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rlService.deleteLanguage(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除语言能力失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "删除语言能力失败！");
        }
        return map;
    }
    
    
    /**
     * 进入我的简历工作经历页
     * @return
     */
    @RequestMapping(value="getWorkHistory")
    public ModelAndView toWorkHistoryPage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/workHistory");    
        List<ResumeWorkHistory> rwhList=null;
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有工作经历
        if(resumeId!=0){
            rwhList=rwhService.selectByResumeId(resumeId);  
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历          
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                rwhList=rwhService.selectByResumeId(orb.getId());                 
            }else{
                originalFlag=false; 
                flag=false;
            } 
        }
        if(rwhList!=null && rwhList.size()>0){
            mav.addObject("rwhList", rwhList); 
        }else{
            flag=false; 
        } 
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 新增工作经历页
     * @return
     */
    @RequestMapping(value="addWorkHistory")
    public ModelAndView toAddWorkHistoryPage(Long resumeSeekerId,boolean originalFlag,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/addWorkHistory");    
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存工作经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="insertWorkHistory")
    @ResponseBody
    public Map<String, Object>insertWorkHistory(HttpServletRequest request, ResumeWorkHistory rwh) {
        logger.info("保存新增工作经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rwhService.insertWorkHistory(request, rwh);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存新增工作经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存新增工作经历失败！");
        }
        return map;
    }
    
    
    /**
     * 进入工作经历查看页
     * @return
     */
    @RequestMapping(value="editWorkHistory")
    public ModelAndView editWorkHistory(Long ehId,Long resumeSeekerId,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/editWorkHistory");   
        ResumeWorkHistory rwh=rwhService.selectByPrimaryKey(ehId); 
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String startTime=sdf.format(rwh.getStartTime());
        Date date=rwh.getEndTime();
        if(date!=null){
            String endTime=sdf.format(date);  
            mav.addObject("endTime", endTime);
        }
        mav.addObject("rwh", rwh);
        mav.addObject("startTime", startTime);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
        

   /**
     * 保存编辑后的工作经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updateWorkHistory")
    @ResponseBody
    public Map<String, Object>updateWorkHistory(ResumeWorkHistory rwh) {
        logger.info("保存修改后的工作经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rwhService.editWorkHistory(rwh);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存修改后的工作经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存修改后的工作经历失败！");
        }
        return map;
    }


   /**
     * 删除工作经历
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="deleteWorkHistory")
    @ResponseBody
    public Map<String, Object>deleteWorkHistory(Long id) {
        logger.info("删除工作经历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rwhService.deleteWorkHistory(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除工作经历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "删除工作经历失败！");
        }
        return map;
    } 
    

    /**
     * 进入我的简历项目经验页
     * @return
     */
    @RequestMapping(value="getProjectExperience")
    public ModelAndView toProjectExperiencePage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/projectExperience");    
        List<ResumeProjectExperience> rpeList=null;
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有项目经验
        if(resumeId!=0){
            rpeList=rpeService.selectByResumeId(resumeId);
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历           
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                rpeList=rpeService.selectByResumeId(orb.getId());                 
            }else{
                originalFlag=false; 
                flag=false;
            }
        }
        if(rpeList!=null && rpeList.size()>0){
            mav.addObject("rpeList", rpeList); 
        }else{
            flag=false; 
        } 
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 新增项目经验页
     * @return
     */
    @RequestMapping(value="addProjectExperience")
    public ModelAndView toAddProjectExperiencePage(Long resumeSeekerId,boolean originalFlag,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/addProjectExperience");   
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存新增项目经验
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="insertProjectExperience")
    @ResponseBody
    public Map<String, Object>insertProjectExperience(HttpServletRequest request, ResumeProjectExperience rpe) {
        logger.info("保存新增项目经验");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rpeService.insertProjectExperience(request, rpe);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存新增项目经验失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存新增项目经验失败！");
        }
        return map;
    }
    
    /**
     * 进入项目经验查看页
     * @return
     */
    @RequestMapping(value="editProjectExperience")
    public ModelAndView editProjectExperience(Long ehId,Long resumeSeekerId,Long resumeId,String positionId){
        ModelAndView mav = new ModelAndView("/front/resume/editProjectExperience");         
        ResumeProjectExperience rpe=rpeService.selectByPrimaryKey(ehId); 
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String startTime=sdf.format(rpe.getStartTime());
        Date date=rpe.getEndTime();
        if(date!=null){
            String endTime=sdf.format(date);  
            mav.addObject("endTime", endTime);
        }
        mav.addObject("rpe", rpe);
        mav.addObject("startTime", startTime);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("resumeId", resumeId);
        mav.addObject("positionId", positionId);
        return mav ;
    }
    
    /**
     * 保存编辑后的项目经验
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updateProjectExperience")
    @ResponseBody
    public Map<String, Object>updateProjectExperience(ResumeProjectExperience rpe) {
        logger.info("保存修改后的项目经验");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rpeService.editProjectExperience(rpe);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存修改后的项目经验失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存修改后的项目经验失败！");
        }
        return map;
    }
    

   /**
     * 删除项目经验
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="deleteProjectExperience")
    @ResponseBody
    public Map<String, Object>deleteProjectExperience(Long id) {
        logger.info("删除项目经验");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rpeService.deleteProjectExperience(id);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除项目经验失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "删除项目经验失败！");
        }
        return map;
    }
    
    /**
     * 进入内部推荐页
     * @return
     */
    @RequestMapping(value="getInsideRecommend")
    public ModelAndView toInsideRecommendPage(Long resumeSeekerId,String positionId,Long resumeId,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/front/resume/insideRecommend");  
        boolean originalFlag=true;//是否有我的简历
        ResumeBase rb;
        if(resumeId!=0){
            rb=resumeService.selectByPrimaryKey(resumeId); 
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历           
            rb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);           
        }
        if(rb!=null){
            mav.addObject("rb",rb);
        }else{
            originalFlag=false; 
        }
        //查看是否是内部推荐的链接
        String insideRecommend=(String) request.getSession().getAttribute(Constants.NEITUIREN);
        if(StringUtils.isNotEmpty(insideRecommend)){
            mav.addObject("insideRecommend",insideRecommend);;
        }           
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("positionId", positionId);
        mav.addObject("resumeId", resumeId);
        return mav ;
    }  
    
    /**
     * 保存内部推荐
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="updateInsideRecommend")
    @ResponseBody
    public Map<String, Object>updateInsideRecommend(HttpServletRequest request, ResumeBase rb) {
        logger.info("保存内部推荐");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = resumeService.updateInsideRecommend(request, rb);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存求职意向失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存求职意向失败！");
        }
        return map;
    }
        
    
    /**
     * 进入图片简历页
     * @return
     */
    @RequestMapping(value="getPhotoResume")
    public ModelAndView toPhotoResumePage(Long resumeSeekerId,String positionId,Long resumeId){
        ModelAndView mav = new ModelAndView("/front/resume/photoResume");
        List<ResumePhoto> rpList=null;
        boolean originalFlag=true;//是否有我的简历
        boolean flag=true;//是否有图片简历
        if(resumeId!=0){
            rpList=rpService.selectByResumeId(resumeId);
        }else{
            resumeService.copyResume(resumeSeekerId);
            //第一步 :通过求职者ID去查询我的简历         
            ResumeBase orb=resumeService.selectOriginalResumeByRsId(resumeSeekerId);
            if(orb!=null){
                rpList=rpService.selectByResumeId(orb.getId());                   
            }else{
                originalFlag=false; 
                flag=false;
            }
        }
        if(rpList!=null && rpList.size()>0){
            mav.addObject("rpList", rpList); 
        }else{
            flag=false; 
        } 
        mav.addObject("originalFlag", originalFlag);
        mav.addObject("flag", flag);
        mav.addObject("resumeSeekerId", resumeSeekerId);
        mav.addObject("filepath",filePath);
        mav.addObject("positionId", positionId);
        mav.addObject("resumeId", resumeId);
        return mav ;
    } 
    
    
    /**
     * 保存简历图片
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="insertReumsePhoto")
    @ResponseBody
    public Map<String, Object>insertResumePhoto(@RequestParam("file")MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) {
        logger.info("保存简历图片");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rpService.insertResumePhoto(files, request, response);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("保存简历图片失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "保存简历图片失败！");
        }
        return map;
    }
  
}
