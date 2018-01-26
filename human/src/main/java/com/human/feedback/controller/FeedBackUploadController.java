package com.human.feedback.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.service.FeedBackUploadService;
import com.human.manager.entity.FeedbackParamBean;
import com.human.manager.entity.HrOrganization;
import com.human.utils.BindingConstants;
import com.human.utils.PageView;
import com.human.utils.SecurityHelper;
import com.human.utils.execl.StringUtil;

@Controller
/*@RequestMapping(value = "/free/feedback/")*/
public class FeedBackUploadController {
    
    @Value("${urlPreKey}")
    private String urlPreKey;
    
    @Resource
    private FeedBackUploadService fbService;
    
    @Value("${oss.fileurl}")
    private String filePath;
    /**
     * 进入问题反馈页面
     * @return
     */
    @RequestMapping(value="/wechat/binding/feedback/index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/feedback/feedback_index");
        List<HrOrganization> orgList=fbService.queryHfXdfOrg();
        mav.addObject("orgList", orgList);
        mav.addObject("filePath", filePath);
        return mav;
    }
    
    @RequestMapping("/wechat/binding/feedback/feedback")
    @ResponseBody
    public Map<String, Object> feedback(FeedBackBase fbb,FeedBackDetail fbd,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile ) {
        Map<String, Object> map =new HashMap<String,Object>();
        try{
            map=fbService.insertFeedBack(fbb,fbd,imagefile);
         }catch(Exception e){
             map.put("flag", false);
             map.put("msg", "操作异常，请稍后重试!");
         }
         return map;
    }
    
    @RequestMapping("/wechat/binding/feedback/getMyFeedBack")
    @ResponseBody
    public PageView getMyFeedBack(PageView pageView,HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        return fbService.getMyFeedBack(pageView,userName);
    }
    
    @RequestMapping("/wechat/binding/feedback/closeFeedBack")
    @ResponseBody
    public Map<String, Object> closeFeedBack(HttpServletRequest request,FeedbackParamBean fpb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
           String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
           fpb.setUserName(userName);
           if(fpb.getCloseType()==1) {
               if(StringUtils.isBlank(fpb.getCloseDesc())) {
                   map.put("flag", false);
                   map.put("msg", "请填写关闭说明!");
                   return map;
               }
           }
           fbService.closeFeedBack(fpb);
           map.put("flag", true);
           map.put("msg", "该反馈已成功关闭!");
        }catch(Exception e){
            map.put("flag", false);
            map.put("msg", "操作异常，请稍后重试!");
        }
         return map;
    }
        
    @RequestMapping("/wechat/binding/feedback/jxfeedback")
    @ResponseBody
    public Map<String, Object> jxfeedback(HttpServletRequest request,FeedBackDetail fbd,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile ) {
        Map<String, Object> map = new HashMap<String, Object>();
       try{
           String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
           fbd.setOperUser(userName);
            if (StringUtils.isBlank(fbd.getDesc())) {
                map.put("flag", false);
                map.put("msg", "请填写回复内容!");
                return map;
            }
          fbService.jxfeedback(fbd,imagefile);
          map.put("flag", true);
          map.put("msg", "回复成功!");
       }catch(Exception e){
           map.put("flag", false);
           map.put("msg", "操作异常，请稍后重试!");
       }
        return map;
    }
    
    @RequestMapping("/wechat/binding/feedback/fbFeedBack")
    @ResponseBody
    public Map<String, Object> fbFeedBack(HttpServletRequest request,FeedBackDetail fbd,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile) {
        Map<String, Object> map = new HashMap<String, Object>();
       try{
         String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
         if(StringUtil.isEmptyOrBlank(userName)){
             map.put("flag", false);
             map.put("msg", "参数不正确，无法操作");
             return map;
         }
         fbd.setOperUser(userName);
         map= fbService.fbFeedBack(fbd,imagefile);
       }catch(Exception e){
           map.put("flag", false);
           map.put("msg", "操作异常，请稍后重试!");
       }
        return map;
    }
    
    
    @RequestMapping(value = "/wechat/binding/feedback/hfFeedback")
    public ModelAndView hfFeedback(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/feedback/feedback_back");
        try {
            String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            mav.addObject("filepath", filePath);
            mav.addObject("userName", userName);
            mav.setViewName("/feedback/feedback_back");
            return mav;
        }
        catch (Exception e) {
            e.printStackTrace();
            return mav;
        }
    }
    
    @RequestMapping("/wechat/binding/feedback/getMyOperFeedBack")
    @ResponseBody
    public PageView getMyOperFeedBack(PageView pageView,HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        return fbService.getMyOperFeedBack(pageView,userName);
    }
    
    @RequestMapping("/wechat/binding/feedback/getMyOperFeedBackEd")
    @ResponseBody
    public PageView getMyOperFeedBackEd(PageView pageView,HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
          return fbService.getMyOperFeedBackEd(pageView,userName);
    }
    
    
    @RequestMapping(value = "/wechat/binding/feedback/feedBackOperDetail")
    public ModelAndView feedBackOperDetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/feedback/feedback_backdetail");
        Long id = Long.valueOf(request.getParameter("id"));
        String userName = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        mav = new ModelAndView("/feedback/feedback_backdetail");
        FeedbackParamBean fpb = new FeedbackParamBean();
        fpb.setId(id);
        fpb.setUserName(userName);
        List<FeedBackBase> fb = fbService.queryParamFeedback(fpb);
        if (fb.size() > 0) {
            mav.addObject("fb", fb.get(0));
        }
        mav.addObject("filePath", filePath);
        return mav;
    }
    
    
    @RequestMapping(value = "/wechat/binding/feedback/closeFeedBackView")
    public ModelAndView closeFeedBackView(FeedBackBase fbb) {
        ModelAndView mav = new ModelAndView("/feedback/feedback_close");
        mav.addObject("fbb", fbb);
        return mav;
    }
    
    
    @RequestMapping(value = "/wechat/binding/feedback/feedBackDetail")
    public ModelAndView feedBackDetail(Long id) {
        ModelAndView mav = new ModelAndView("/feedback/feedback_detail");
        FeedbackParamBean fpb=new FeedbackParamBean();
        fpb.setId(id);
        List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
        if(fb.size()>0){
            mav.addObject("fb", fb.get(0));
        }
        mav.addObject("filePath",filePath);
        return mav;
    }
    
 /*==分割线==下面未邮件直接点击URL进入的方法==*/
    @RequestMapping(value = "/free/feedBack/feedBackDetail")
    public ModelAndView feedBackDetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        try{
            Long id = Long.valueOf(request.getParameter("id"));
            String userName =request.getParameter("userName");
            if(!StringUtil.isEmptyOrBlank(userName)){
                userName = SecurityHelper.decrypt(urlPreKey, request.getParameter("userName"));
            }
            if(id==null||userName==null||userName.trim().length()==0){
                return mav;
            }
            mav = new ModelAndView("/feedback/nologin/feedback_detail");
            FeedbackParamBean fpb=new FeedbackParamBean();
            fpb.setId(id);
            fpb.setUserName(userName);
            List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
            if(fb.size()>0){
                mav.addObject("fb", fb.get(0));
            }
            mav.addObject("filePath",filePath);
            mav.addObject("userName",userName);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
    
    @RequestMapping(value = "/free/feedBack/closeFeedBackView")
    public ModelAndView closeFreeFeedBackView(FeedBackBase fbb) {
        ModelAndView mav = new ModelAndView("/feedback/nologin/feedback_close");
        mav.addObject("fbb", fbb);
        return mav;
    }
    
    
    @RequestMapping("/free/feedBack/closeFeedBack")
    @ResponseBody
    public Map<String, Object> closeFreeFeedBack(FeedbackParamBean fpb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            if(fpb.getCloseType()==1) {
                if(StringUtils.isBlank(fpb.getCloseDesc())) {
                    map.put("flag", false);
                    map.put("msg", "请填写关闭说明!");
                    return map;
                }
            }
           fbService.closeFeedBack(fpb);
           map.put("flag", true);
           map.put("msg", "该反馈已成功关闭!");
        }catch(Exception e){
            map.put("flag", false);
            map.put("msg", "操作异常，请稍后重试!");
        }
         return map;
    }
    
    
    @RequestMapping("/free/feedBack/jxfeedback")
    @ResponseBody
    public Map<String, Object> jxFreefeedback(FeedBackDetail fbd,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile ) {
        Map<String, Object> map = new HashMap<String, Object>();
       try{
           if (StringUtils.isBlank(fbd.getDesc())) {
               map.put("flag", false);
               map.put("msg", "请填写回复内容!");
               return map;
           }
          fbService.jxfeedback(fbd,imagefile);
          map.put("flag", true);
          map.put("msg", "回复成功!");
       }catch(Exception e){
           map.put("flag", false);
           map.put("msg", "操作异常，请稍后重试!");
       }
        return map;
    }
    
  
    @RequestMapping(value = "/free/feedBack/feedBackOperDetail")
    public ModelAndView feedFeedBackOperDetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        try{
            Long id = Long.valueOf(request.getParameter("id"));
            String userName =request.getParameter("userName");
            if(!StringUtil.isEmptyOrBlank(userName)){
                userName = SecurityHelper.decrypt(urlPreKey, request.getParameter("userName"));
            }
            if(id==null||userName==null||userName.trim().length()==0){
                return mav;
            }
            mav = new ModelAndView("/feedback/nologin/feedback_backdetail");
            FeedbackParamBean fpb=new FeedbackParamBean();
            fpb.setId(id);
            fpb.setUserName(userName);
            List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
            if(fb.size()>0){
                mav.addObject("fb", fb.get(0));
            }
            mav.addObject("filePath",filePath);
            mav.addObject("userName",userName);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
    
    
    @RequestMapping(value = "/free/feedBack/feedFeedBackIdDetail")
    public ModelAndView feedFeedBackIdDetail(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        try{
            Long id = Long.valueOf(request.getParameter("id"));
            String userName = request.getParameter("userName");
            if(id==null||userName==null||userName.trim().length()==0){
                return mav;
            }
            mav = new ModelAndView("/feedback/nologin/feedback_backdetail");
            FeedbackParamBean fpb=new FeedbackParamBean();
            fpb.setId(id);
            fpb.setUserName(userName);
            List<FeedBackBase> fb=fbService.queryParamFeedback(fpb);
            if(fb.size()>0){
                mav.addObject("fb", fb.get(0));
            }
            mav.addObject("filePath",filePath);
            mav.addObject("userName",userName);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }
    
    @RequestMapping("/free/feedBack/fbFeedBack")
    @ResponseBody
    public Map<String, Object> fbFeedBack(FeedBackDetail fbd,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile) {
        Map<String, Object> map = new HashMap<String, Object>();
       try{
         if(StringUtil.isEmptyOrBlank(fbd.getOperUser())){
             map.put("flag", false);
             map.put("msg", "参数不正确，无法操作");
             return map;
         }
         map= fbService.fbFeedBack(fbd,imagefile);
       }catch(Exception e){
           map.put("flag", false);
           map.put("msg", "操作异常，请稍后重试!");
       }
        return map;
    }
    
    @RequestMapping(value = "/free/feedBack/hfFeedback")
    public ModelAndView hfFreeFeedback(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/nologin/denied");
        try {
            String userName = request.getParameter("userName");
            if(!StringUtil.isEmptyOrBlank(userName)){
                userName = SecurityHelper.decrypt(urlPreKey, request.getParameter("userName"));
            }
            if(userName==null||userName.trim().length()==0){
                return mav;
            }
            mav.setViewName("/feedback/nologin/feedback_back");
            mav.addObject("filepath", filePath);
            mav.addObject("userName", userName);
            return mav;
        }
        catch (Exception e) {
            e.printStackTrace();
            return mav;
        }
    }
    
    @RequestMapping("/free/feedBack/getMyOperFeedBack")
    @ResponseBody
    public PageView getMyOperFreeFeedBack(PageView pageView,String userName) {
        return fbService.getMyOperFeedBack(pageView,userName);
    }
    
    @RequestMapping("/free/feedBack/getMyOperFeedBackEd")
    @ResponseBody
    public PageView getMyOperFreeFeedBackEd(PageView pageView,String userName) {
          return fbService.getMyOperFeedBackEd(pageView,userName);
    }
}
