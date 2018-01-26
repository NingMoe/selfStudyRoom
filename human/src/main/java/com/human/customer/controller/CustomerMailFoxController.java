package com.human.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerSelect;
import com.human.customer.service.CustomerMailFoxService;
import com.human.customer.service.CustomerSelectService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/customer/mailFox/")
public class CustomerMailFoxController {
    
    private final  Logger logger = LogManager.getLogger(CustomerMailFoxController.class);
    
    @Value("${oss.fileurl}")
    private String filePath;
    
    @Resource
    private CustomerSelectService selectService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    @Resource
    private  CustomerMailFoxService mailFoxService;
    
    
    @RequestMapping("beforeIndex")
    public ModelAndView beforeIndex() {
        ModelAndView mav=new ModelAndView("/customer/mailFox/manager/mailfox_list");
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype =dictionaryService.getDataByKey("customer_acctype");
        mav.addObject("deptList", deptList);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        return mav;
    }
    
    @RequestMapping("beforEdit")
    public ModelAndView beforEdit(Integer id) {
        ModelAndView mav=new ModelAndView("/customer/mailFox/manager/mailfox_edit");
        CustomerMailFox cm=mailFoxService.queryById(id);
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype =  dictionaryService.getDataByKey("customer_acctype");
        List<DicData> aspect =dictionaryService.getDataByKey("customer_aspect");
        CustomerSelect cs=new CustomerSelect();
        cs.setLevel(1);
        List<CustomerSelect> type1List=  selectService.queryByParam(cs);
        List<CustomerSelect> type2List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type3List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type4List=new ArrayList<CustomerSelect>();
        if(StringUtils.isNotBlank(cm.getType2())) {
            cs.setLevel(2);
            cs.setName(cm.getType2());
            type2List=  selectService.queryByParam(cs);
            if(StringUtils.isNotBlank(cm.getType3())) {
                cs.setLevel(3);
                cs.setName(cm.getType3());
                type3List=  selectService.queryByParam(cs);
                if(StringUtils.isNotBlank(cm.getType4())) {
                    cs.setLevel(4);
                    cs.setName(cm.getType4());
                    type4List=  selectService.queryByParam(cs);
                }
            }
        }
        mav.addObject("aspectList", aspect);
        mav.addObject("deptList", deptList);
        mav.addObject("cm", cm);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        mav.addObject("type1List", type1List);
        mav.addObject("type2List", type2List);
        mav.addObject("type3List", type3List);
        mav.addObject("type4List", type4List);
        mav.addObject("filePath",filePath);
        return mav;
    }
    
    @RequestMapping("managerUpdate")
    @ResponseBody
    public Map<String, Object> managerUpdate(String jstr) {
        Map<String, Object> map = new HashMap<String, Object>();
        CustomerMailFox mailFox=JSONObject.parseObject(jstr, CustomerMailFox.class);
        try {
            mailFoxService.managerUpdate(mailFox);
            map.put("flag", true);
            map.put("msg", "保存成功");
        }
        catch (Exception e) {
            logger.error("update error !", e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,CustomerMailFox mailFox) {
       return  mailFoxService.query(pageView,mailFox);
    }
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/customer/mailFox/operation_list");
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype =  dictionaryService.getDataByKey("customer_acctype");
        mav.addObject("deptList", deptList);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        return mav;
    }
    
    @RequestMapping("queryMyOper")
    @ResponseBody
    public PageView queryMyOper(PageView pageView,CustomerMailFox mailFox) {
        mailFox.setSolUser(Common.getAuthenticatedUsername());
       return  mailFoxService.query(pageView,mailFox);
    }
    
    @RequestMapping("tracerIndex")
    public ModelAndView tracerIndex() {
        ModelAndView mav=new ModelAndView("/customer/mailFox/tracer/list");
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype =  dictionaryService.getDataByKey("customer_acctype");
        mav.addObject("deptList", deptList);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        return mav;
    }
    
    @RequestMapping("queryTracer")
    @ResponseBody
    public PageView queryTracer(PageView pageView,CustomerMailFox mailFox) {
        mailFox.setTracer(Common.getAuthenticatedUsername());
       return  mailFoxService.query(pageView,mailFox);
    }
    
    
    @RequestMapping("add")
    public ModelAndView add() {
        ModelAndView mav=new ModelAndView("/customer/mailFox/operation_add");
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype = dictionaryService.getDataByKey("customer_acctype");
        CustomerSelect cs=new CustomerSelect();
        cs.setLevel(1);
        List<CustomerSelect> csList=  selectService.queryByParam(cs);
        mav.addObject("deptList", deptList);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        mav.addObject("csList", csList);
        return mav;
    }
    
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(CustomerMailFox mailFox) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            mailFox.setSolUser(Common.getAuthenticatedUsername());
            mailFox.setState(2);
            mailFoxService.save(mailFox);
            map.put("flag", true);
            map.put("msg", "保存成功");
        }
        catch (Exception e) {
            logger.error("save error !", e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    @RequestMapping("tracerEdit")
    public ModelAndView tracerEdit(Integer id) {
        ModelAndView mav=new ModelAndView("/customer/mailFox/tracer/edit");
        CustomerMailFox cm=mailFoxService.queryById(id);
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype = dictionaryService.getDataByKey("customer_acctype");
        CustomerSelect cs=new CustomerSelect();
        cs.setLevel(1);
        List<CustomerSelect> type1List=  selectService.queryByParam(cs);
        List<CustomerSelect> type2List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type3List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type4List=new ArrayList<CustomerSelect>();
        if(StringUtils.isNotBlank(cm.getType2())) {
            cs.setLevel(2);
            cs.setName(cm.getType2());
            type2List=  selectService.queryByParam(cs);
            if(StringUtils.isNotBlank(cm.getType3())) {
                cs.setLevel(3);
                cs.setName(cm.getType3());
                type3List=  selectService.queryByParam(cs);
                if(StringUtils.isNotBlank(cm.getType4())) {
                    cs.setLevel(4);
                    cs.setName(cm.getType4());
                    type4List=  selectService.queryByParam(cs);
                }
            }
        }
        mav.addObject("deptList", deptList);
        mav.addObject("cm", cm);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        mav.addObject("type1List", type1List);
        mav.addObject("type2List", type2List);
        mav.addObject("type3List", type3List);
        mav.addObject("type4List", type4List);
        mav.addObject("filePath",filePath);
        return mav;
    }
    
    @RequestMapping("edit")
    public ModelAndView edit(Integer id) {
        ModelAndView mav=new ModelAndView("/customer/mailFox/operation_edit");
        CustomerMailFox cm=mailFoxService.queryById(id);
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype = dictionaryService.getDataByKey("customer_acctype");
        CustomerSelect cs=new CustomerSelect();
        cs.setLevel(1);
        List<CustomerSelect> type1List=  selectService.queryByParam(cs);
        List<CustomerSelect> type2List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type3List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type4List=new ArrayList<CustomerSelect>();
        if(StringUtils.isNotBlank(cm.getType2())) {
            cs.setLevel(2);
            cs.setName(cm.getType2());
            type2List=  selectService.queryByParam(cs);
            if(StringUtils.isNotBlank(cm.getType3())) {
                cs.setLevel(3);
                cs.setName(cm.getType3());
                type3List=  selectService.queryByParam(cs);
                if(StringUtils.isNotBlank(cm.getType4())) {
                    cs.setLevel(4);
                    cs.setName(cm.getType4());
                    type4List=  selectService.queryByParam(cs);
                }
            }
        }
        mav.addObject("deptList", deptList);
        mav.addObject("cm", cm);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        mav.addObject("type1List", type1List);
        mav.addObject("type2List", type2List);
        mav.addObject("type3List", type3List);
        mav.addObject("type4List", type4List);
        mav.addObject("filePath",filePath);
        return mav;
    }
    
    
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(String jstr) {
        Map<String, Object> map = new HashMap<String, Object>();
        CustomerMailFox mailFox=JSONObject.parseObject(jstr, CustomerMailFox.class);
        try {
            mailFoxService.customerUpdate(mailFox);
            map.put("flag", true);
            map.put("msg", "保存成功");
        }
        catch (Exception e) {
            logger.error("update error !", e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    @RequestMapping("updateTracer")
    @ResponseBody
    public Map<String, Object> updateTracer(CustomerMailFox mailFox) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            mailFoxService.updateTracer(mailFox);
            map.put("flag", true);
            map.put("msg", "保存成功");
        }
        catch (Exception e) {
            logger.error("update error !", e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    
    @RequestMapping("show")
    public ModelAndView show(Integer id) {
        ModelAndView mav=new ModelAndView("/customer/mailFox/operation_show");
        CustomerMailFox cm=mailFoxService.queryById(id);
        List<DicData> deptList = dictionaryService.getDataByKey("customer_dept");
        List<DicData> campusList = dictionaryService.getDataByKey("customer_campus");
        List<DicData> gradeList = dictionaryService.getDataByKey("customer_grade");
        List<DicData> acctype = dictionaryService.getDataByKey("customer_acctype");
        CustomerSelect cs=new CustomerSelect();
        cs.setLevel(1);
        List<CustomerSelect> type1List=  selectService.queryByParam(cs);
        List<CustomerSelect> type2List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type3List=new ArrayList<CustomerSelect>();
        List<CustomerSelect> type4List=new ArrayList<CustomerSelect>();
        if(StringUtils.isNotBlank(cm.getType2())) {
            cs.setLevel(2);
            cs.setName(cm.getType2());
            type2List=  selectService.queryByParam(cs);
            if(StringUtils.isNotBlank(cm.getType3())) {
                cs.setLevel(3);
                cs.setName(cm.getType3());
                type3List=  selectService.queryByParam(cs);
                if(StringUtils.isNotBlank(cm.getType4())) {
                    cs.setLevel(4);
                    cs.setName(cm.getType4());
                    type4List=  selectService.queryByParam(cs);
                }
            }
        }
        mav.addObject("deptList", deptList);
        mav.addObject("cm", cm);
        mav.addObject("acctype", acctype);
        mav.addObject("gradeList", gradeList);
        mav.addObject("campusList", campusList);
        mav.addObject("type1List", type1List);
        mav.addObject("type2List", type2List);
        mav.addObject("type3List", type3List);
        mav.addObject("type4List", type4List);
        mav.addObject("filePath",filePath);
        return mav;
    }
}
