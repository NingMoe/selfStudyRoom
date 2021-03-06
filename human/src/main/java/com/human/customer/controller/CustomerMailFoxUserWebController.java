package com.human.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerMailFoxRecord;
import com.human.customer.entity.CutomerCenterStudent;
import com.human.customer.service.CustomerMailFoxUserWebService;
import com.human.utils.BindingConstants;
import com.human.utils.PageView;

@Controller
@RequestMapping("/wxCustomer/mailFox/")
public class CustomerMailFoxUserWebController {

    private final  Logger logger = LogManager.getLogger(CustomerMailFoxUserWebController.class);
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private CustomerMailFoxUserWebService userWebService;
    
    @RequestMapping("webIndex")
    public ModelAndView webIndex(HttpServletRequest request) {
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> aspect =dictionaryService.getDataByKey("customer_aspect");
        CutomerCenterStudent stu =(CutomerCenterStudent) request.getSession().getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
        ModelAndView mav=new ModelAndView("/wxCustomer/mailFox/webIndex");
        if(StringUtils.isBlank(stu.getStudentmoblie())){
            mav.addObject("isPhone", false);
        }else {
            mav.addObject("isPhone", true);
        }
        mav.addObject("aspectList", aspect);
        mav.addObject("gradeList", gradeList);
        return mav;
    }
    
    @RequestMapping("addMail")
    @ResponseBody
    public Map<String, Object> addMail(CustomerMailFox cmf,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile ) {
        Map<String, Object> map =new HashMap<String,Object>();
        try{
            map=userWebService.addMail(cmf,imagefile);
         }catch(Exception e){
             logger.error(e);
             map.put("flag", false);
             map.put("msg", "操作异常，请稍后重试!");
         }
         return map;
    }
    
    @RequestMapping("getMailList")
    @ResponseBody
    public PageView getMailList(PageView pageView,CustomerMailFox cmf,HttpServletRequest request) {
        CutomerCenterStudent stu =(CutomerCenterStudent) request.getSession().getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
        cmf.setOpenId(stu.getOpenid());
        return  userWebService.getMailList(pageView, cmf);
    }
    
    
    @RequestMapping("getMailById")
    @ResponseBody
    public ModelAndView getMailById(CustomerMailFox cmf,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/wxCustomer/mailFox/webEdit");
        CutomerCenterStudent stu =(CutomerCenterStudent) request.getSession().getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
        cmf.setOpenId(stu.getOpenid());
        CustomerMailFox cm=userWebService.getMailById(cmf,1);
        mav.addObject("cm",cm);
        mav.addObject("filePath",filePath);
        return mav;
    }
    
    @RequestMapping("addMailRecord")
    @ResponseBody
    public Map<String, Object> addMailRecord(HttpServletRequest request,CustomerMailFoxRecord cmr,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile) {
        Map<String, Object> map =new HashMap<String,Object>();
        try{
            cmr.setType(0);
            userWebService.addMailRecord(cmr,imagefile);
            map.put("flag", true);
            map.put("msg", "操作成功");
         }catch(Exception e){
             logger.error(e);
             map.put("flag", false);
             map.put("msg", "操作异常，请稍后重试!");
         }
         return map;
    }
    
    @RequestMapping(value = "subScoreView")
    public ModelAndView subScoreView(Long id) {
        ModelAndView mav = new ModelAndView("/wxCustomer/mailFox/subScore");
        mav.addObject("id", id);
        return mav;
    }
    
    @RequestMapping("subScore")
    @ResponseBody
    public Map<String, Object> subScore(CustomerMailFox cmf) {
        Map<String, Object> map =new HashMap<String,Object>();
        try{
            userWebService.subScore(cmf);
            map.put("flag", true);
            map.put("msg", "操作成功");
         }catch(Exception e){
             logger.error(e);
             map.put("flag", false);
             map.put("msg", "操作异常，请稍后重试!");
         }
         return map;
    }
    
}
