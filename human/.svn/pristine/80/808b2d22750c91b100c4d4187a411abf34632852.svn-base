package com.human.jzbTest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.jzbTest.entity.JzbUpClass;
import com.human.jzbTest.entity.SchoolSbq;
import com.human.jzbTest.service.JzbUpClassService;
import com.human.jzbTest.service.XbChkqService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbConfigUpClass/")
public class JzbUpClassController {
    
    private final  Logger logger = LogManager.getLogger(JzbUpClassController.class);
                                   
    @Resource    
    private JzbUpClassService upClassService;
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource
    private XbChkqService xbChkqService;
    
    /**
     * 升班规则配置信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index(JzbUpClass jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbUpClass/list");
        mav.addObject("jzbUpClass", jgs);
        return mav;
    }
    
    
    /**
     * 分页查询某年级科目配置的升班规则
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbUpClass cf) {
        return  upClassService.query(pageView, cf);
    }
    
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(JzbUpClass jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbUpClass/add");
        //获取升班期规则列表
        String companyId=Common.getMyUser().getCompanyId();
        HrCompany hrCompany=hrCompanyService.selectByPrimaryKey(companyId);
        String schoolId="";
        if(hrCompany!=null){
            schoolId=hrCompany.getMessageId();
            if(StringUtils.isNotEmpty(schoolId)){
                List<SchoolSbq> list= xbChkqService.getSbqList(schoolId);
                mav.addObject("schoolSbqList", list);
            }            
        }
        mav.addObject("schoolId", schoolId);
        mav.addObject("jzbUpClass", jgs);
        return mav;  
    }
    
        
    /**
     * 保存某年级科目配置的升班规则
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(JzbUpClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=upClassService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存升班规则失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
            
    /**
     * 删除某年级科目配置的升班规则（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("delete")
     @ResponseBody
     public Map<String, Object> delete(String deleteIds) {
         logger.info("删除某年级科目配置的升班规则");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=upClassService.delete(deleteIds);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除升班规则失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除升班规则失败,请稍后重试!");
         }
         return map;
     }
       
    
}
