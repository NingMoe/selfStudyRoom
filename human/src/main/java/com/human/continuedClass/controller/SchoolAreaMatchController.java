package com.human.continuedClass.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.entity.SchoolAreaMatch;
import com.human.continuedClass.service.SchoolAreaMatchService;
import com.human.continuedClass.service.SchoolAreaService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping(value="/continued_class/school_area_match/")
public class SchoolAreaMatchController {
    
    private final  Logger logger = LogManager.getLogger(SchoolAreaMatchController.class);
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private SchoolAreaService saService;
    
    @Resource
    private SchoolAreaMatchService samService;
      
    /**
     * 临近校区匹配数据首页
     */
    @RequestMapping("index")
    public ModelAndView index(Long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolAreaMatch/list");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        mav.addObject("ruleId", ruleId);
        return mav;
    }
    
    /**
     * 分页查询临近校区匹配数据
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,SchoolAreaMatch ccr) {
        return  samService.query(pageView, ccr);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(Long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolAreaMatch/add");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        mav.addObject("ruleId", ruleId);
        return mav;  
    }
    
    /**
     * 保存临近校区匹配基础数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(SchoolAreaMatch ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=samService.add(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存临近校区失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(SchoolAreaMatch ccr) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolAreaMatch/edit");
        SchoolArea sa=saService.selectByPrimaryKey(ccr.getSchoolAreaId());
        List<SchoolArea>saList=saService.getSchoolArea(sa);
        List<SchoolAreaMatch> samList=samService.selectBySmId(ccr);
        mav.addObject("saList", saList);
        mav.addObject("samList", JSONObject.toJSONString(samList));
        mav.addObject("sa", sa);
        mav.addObject("ruleId", ccr.getRuleId());
        return mav; 
    }
    
    /**
     * 编辑临近校区基础数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(SchoolAreaMatch ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=samService.edit(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑临近校区失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除临近校区基础数据（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds,long ruleId) {
        logger.info("删除临近校区基础数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=samService.delete(deleteIds,ruleId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除临近校区失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除临近校区基础数据失败,请稍后重试!");
        }
        return map;
    }
}
