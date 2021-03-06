package com.human.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.human.customer.service.CustomerMailFoxUserWebService;
import com.human.utils.BindingConstants;
import com.human.utils.PageView;

@Controller
public class CustomerMailFoxUserWebManagerController {

    private final  Logger logger = LogManager.getLogger(CustomerMailFoxUserWebManagerController.class);
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private CustomerMailFoxUserWebService userWebService;
    
    @RequestMapping("/wechat/binding/customerMailFox/webIndex")
    public ModelAndView webIndex() {
        ModelAndView mav=new ModelAndView("/wxCustomer/managerMailFox/webIndex");
        return mav;
    }
    
    @RequestMapping("/wechat/binding/customerMailFox/getMailAllList")
    @ResponseBody
    public PageView getMailAllList(PageView pageView,Integer queryType,HttpServletRequest request) {
        Map<String,Object> parmMap=new HashMap<String,Object>();
        parmMap.put("queryType", queryType);
        return  userWebService.queryManagerList(pageView, parmMap);
    }
    
    @RequestMapping("/wechat/binding/customerMailFox/getMailMyList")
    @ResponseBody
    public PageView getMailMyList(PageView pageView,Integer queryType,HttpServletRequest request) {
        String solUser =(String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        Map<String,Object> parmMap=new HashMap<String,Object>();
        parmMap.put("queryType",queryType);
        parmMap.put("solUser",solUser);
        return  userWebService.queryManagerList(pageView, parmMap);
    }
    
    @RequestMapping("/wechat/binding/customerMailFox/getMailById")
    @ResponseBody
    public ModelAndView getMailById(CustomerMailFox cmf,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/wxCustomer/managerMailFox/webEdit");
            String solUser =(String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            //cmf.setSolUser(solUser);
            cmf.setSolUser("xiazhenyou");
            CustomerMailFox cm=userWebService.getMailById(cmf,0);
            mav.addObject("cm",cm);
            mav.addObject("filePath",filePath);
         return mav;
    }
    
    @RequestMapping("/wechat/binding/customerMailFox/addMailRecord")
    @ResponseBody
    public Map<String, Object> addMailRecord(HttpServletRequest request,CustomerMailFoxRecord cmr,@RequestParam(value = "imagefile", required = false) MultipartFile[] imagefile) {
        Map<String, Object> map =new HashMap<String,Object>();
        try{
            String solUser =(String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            cmr.setType(1);
            cmr.setOperUser(solUser);
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
}
