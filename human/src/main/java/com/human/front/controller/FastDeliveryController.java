package com.human.front.controller;

import java.util.ArrayList;
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
import com.human.resume.entity.ResumePhoto;
import com.human.resume.service.ResumeEducationHistoryService;
import com.human.resume.service.ResumeIntentionService;
import com.human.resume.service.ResumeLanguageService;
import com.human.resume.service.ResumePhotoService;
import com.human.resume.service.ResumeProjectExperienceService;
import com.human.resume.service.ResumeService;
import com.human.resume.service.ResumeWorkHistoryService;
import com.human.utils.Constants;


/**
 * 快速投递
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value = "/front/fastDelivery/")
public class FastDeliveryController {
    
    private final  Logger logger = LogManager.getLogger(FastDeliveryController.class);
    
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
     * 快速投递页面
     * @return
     */    
    @RequestMapping(value="index")
    public ModelAndView toMainPage(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/front/fastDelivery/main");
        try {
            // 通过微信openId去查询是否已经绑定求职者
            String openId = (String) request.getSession().getAttribute(Constants.OPENID);
            ResumeSeeker rs = recruitAcceptanceService.selectByOpenId(openId);
            int positionId = ServletRequestUtils.getIntParameter(request, "positionId");
            resumeService.copyResume(rs.getId());
            //查询出第一步的个人信息
            ResumeBase rb = resumeService.selectOriginalResumeByRsId(rs.getId());
            boolean hasHeadPhotoFlag=false;//是否有个人图像
            if(rb!=null && StringUtils.isNotEmpty(rb.getHeadUrl())){
                hasHeadPhotoFlag=true;
            }
            //查询出第二步的教育经历
            List<ResumeEducationHistory>rehList=new ArrayList<ResumeEducationHistory>();
            if(rb!=null){
                //复制教育经历
                rehList=rehService.selectByResumeId(rb.getId());
            }
            if(rehList!=null && rehList.size()>0){                
                mav.addObject("rehList", rehList); 
            }
            //查询出第三步的图片简历
            List<ResumePhoto> rpList=new ArrayList<ResumePhoto>();
            boolean hasRphotoFlag=false;//是否有图片简历
            if(rb!=null){
                //复制图片简历
                rpList=rpService.selectByResumeId(rb.getId());
            }
            if(rpList!=null && rpList.size()>0){
                mav.addObject("rpList", rpList);
                hasRphotoFlag=true;
            }  
            //获取学历类型
            List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
            mav.addObject("eduList", eduList);
            mav.addObject("rb", rb);
            mav.addObject("rs", rs);
            mav.addObject("filepath", filePath);
            mav.addObject("positionId", positionId);
            mav.addObject("hasRphotoFlag", hasRphotoFlag);
            mav.addObject("hasHeadPhotoFlag", hasHeadPhotoFlag);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
                 
    /**
     * 上传简历图片
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="uploadReumsePhoto")
    @ResponseBody
    public Map<String, Object>uploadReumsePhoto(@RequestParam("file")MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) {
        logger.info("上传简历图片");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {
            map = rpService.uploadReumsePhoto(files, request, response);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("上传简历图片失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "上传简历图片失败！");
        }
        return map;
    }
        
    /**
     * 快速投递完成
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="finishDelivery")
    @ResponseBody
    public Map<String, Object>finishDelivery(String jsonStrings ,String resumePhotoPathName,HttpServletRequest request) {
        logger.info("快速投递简历");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=resumeService.finishDelivery(jsonStrings ,resumePhotoPathName,request);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("快速投递简历失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "快速投递简历失败！");
        }
        return map;
    }
    
    
    
    
}
