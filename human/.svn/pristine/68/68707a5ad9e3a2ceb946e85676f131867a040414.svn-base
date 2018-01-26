package com.human.front.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
 * 简历投递控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value = "/front/resumeDelivery/")
public class ResumeDeliveryController {
    
    private final  Logger logger = LogManager.getLogger(ResumeDeliveryController.class);
    
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
        logger.info("进入简历投递页");
        ModelAndView mav = new ModelAndView("/front/resumeDelivery/index");
        ResumeBase rb=null;
        ResumeIntention ri=null;
        List<ResumeEducationHistory>rehList=null;
        List<ResumeLanguage> rlList=null;
        List<ResumeWorkHistory> rwhList=null;
        List<ResumeProjectExperience> rpeList=null;
        try{                        
            int positionId = ServletRequestUtils.getIntParameter(request, "positionId");
            // 通过微信openId去查询是否已经绑定求职者
            String openId = (String) request.getSession().getAttribute(Constants.OPENID);
            ResumeSeeker rs = recruitAcceptanceService.selectByOpenId(openId);
            resumeService.copyResume(rs.getId());
            //查询个人信息
            rb=resumeService.selectOriginalResumeByRsId(rs.getId());
            boolean hasHeadPhotoFlag=false;//是否有个人图像
            if(rb!=null && StringUtils.isNotEmpty(rb.getHeadUrl())){
                hasHeadPhotoFlag=true;
            }
            if(rb!=null){
                long resumeId=rb.getId();
                //查询出求职意向
                ri=resumeIntentionService.selectByResumeId(resumeId); 
                //查询出教育经历
                rehList=rehService.selectByResumeId(resumeId);
                //查询出语言能力
                rlList=rlService.selectByResumeId(resumeId);
                //查询出工作经历
                rwhList=rwhService.selectByResumeId(resumeId); 
                //查询出项目经历
                rpeList=rpeService.selectByResumeId(resumeId);
            }
            //获取学历类型
            List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
            mav.addObject("eduList", eduList);
            //获取语言类型
            List<DicData> resumeLanguageList=dictionaryService.selectByDicCode("language");
            mav.addObject("resumeLanguageList", resumeLanguageList); 
            //查看是否是通过内部推荐链接进行的投递
            String insideRecommend=(String) request.getSession().getAttribute(Constants.NEITUIREN);
            if(StringUtils.isNotEmpty(insideRecommend)){
                mav.addObject("insideRecommend",insideRecommend);;
            }           
            mav.addObject("rs", rs);
            mav.addObject("rb", rb);
            mav.addObject("ri", ri);
            mav.addObject("rehList", rehList);
            mav.addObject("rlList", rlList);
            mav.addObject("rpeList", rpeList);
            mav.addObject("rwhList", rwhList);
            mav.addObject("positionId",positionId);
            mav.addObject("filepath",filePath);
            mav.addObject("hasHeadPhotoFlag", hasHeadPhotoFlag);
        }catch(Exception e){
           e.printStackTrace();
           logger.error("进入简历投递页失败!", e.getMessage()); 
        }        
        return mav ;
    }
                    
    /**
     * 简历投递完成
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="finishDelivery")
    @ResponseBody
    public Map<String, Object>finishDelivery(String jsonStrings,HttpServletRequest request) {
        logger.info("简历投递");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=resumeService.finishDelivery(jsonStrings,request);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简历投递失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "简历投递失败！");
        }
        return map;
    } 
    
    
    
    
}
