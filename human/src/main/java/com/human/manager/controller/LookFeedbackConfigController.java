package com.human.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.bean.FeedBackOper;
import com.human.feedback.service.FeedBackUploadService;
import com.human.manager.entity.FeedbackParamBean;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.service.HrCompanyService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.RedisTemplateUtil;

@Controller
@RequestMapping("/manager/lookFeedbackConfig/")
public class LookFeedbackConfigController {

    @Value("${oss.fileurl}")
    private String filePath;
    
    @Resource
    private RedisTemplateUtil redisUtil;
    
    @Resource
    private FeedBackUploadService fbService;
    
    @Resource
    private HrCompanyService hrComService;
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/feedback/lookconfig/list");
        List<HrCompany> list=hrComService.findAll();
        mav.addObject("compayList", list);
        return mav;
    }
    
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,String companyId) {
       return  fbService.query(pageView,companyId);
    }
    
    @RequestMapping("editView")
    public ModelAndView editView(FeedBackOper ho) {
        ModelAndView mav=new ModelAndView("/feedback/lookconfig/edit");
        List<FeedBackOper> fboList=fbService.queryOper(ho);
        mav.addObject("dept", ho);
        mav.addObject("fboList", fboList);
        return mav;
    }
    
    @RequestMapping("removeConfig")
    @ResponseBody
    public Map<String,Object> removeConfig(FeedBackOper ho) {
        Map<String,Object> map=fbService.removeConfig(ho);
        return map;
    }
    
    
    @RequestMapping("addConfig")
    @ResponseBody
    public Map<String,Object> addConfig(FeedBackOper ho) {
        Map<String,Object> map=fbService.addConfig(ho);
        return map;
    }
    
    @RequestMapping("feedbackList")
    public ModelAndView feedbackList() {
        ModelAndView mav=new ModelAndView("/feedback/feedrecord/list");
        List<HrOrganization> orgList=fbService.queryUserOrg(Common.getAuthenticatedUsername());
        mav.addObject("deptList", orgList);
        return mav;
    }
    
    @RequestMapping("queryFeedBackListPage")
    @ResponseBody
    public PageView queryFeedBackListPage(FeedbackParamBean fpb,PageView pageView) {
        return  fbService.queryFeedBackListPage(pageView,fpb);
    }
    
    @RequestMapping("closeFeedBack")
    @ResponseBody
    public Map<String,Object> closeFeedBack(FeedbackParamBean fpb) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            fbService.closeFeedBackByIds(fpb);
            map.put("flag", true);
            map.put("msg", "关闭反馈成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("msg", "操作异常，请稍后再试");
        }
        return map;
    }
    
    
    @RequestMapping("feedBackView")
    public ModelAndView feedBackView(FeedbackParamBean fpb) {
        ModelAndView mav=new ModelAndView("/feedback/feedrecord/feedback");
        fpb.setUserName(Common.getAuthenticatedUsername());
        List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
        if(fb.size()>0){
            mav.addObject("fb", fb.get(0));
        }
        mav.addObject("filePath",filePath);
        return mav;
    }
    
    
    @RequestMapping("fbFeedBack")
    @ResponseBody
    public Map<String, Object> fbFeedBack(FeedBackDetail fbd) {
        Map<String, Object> map = new HashMap<String, Object>();
       try{
          fbd.setOperUser(Common.getAuthenticatedUsername());
          map= fbService.fbFeedBack(fbd,null);
       }catch(Exception e){
           map.put("flag", false);
           map.put("msg", "操作异常，请稍后重试!");
       }
        return map;
    }
    
    @RequestMapping("showDetailView")
    public ModelAndView showDetailView(FeedbackParamBean fpb) {
        ModelAndView mav=new ModelAndView("/feedback/feedrecord/feedback_look");
        fpb.setUserName(Common.getAuthenticatedUsername());
        List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
        if(fb.size()>0){
            mav.addObject("fb", fb.get(0));
        }
        mav.addObject("filePath",filePath);
        return mav;
    }
    
}
