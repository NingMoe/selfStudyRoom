package com.human.manager.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.HrOrganizationService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/manager/org")
public class HrOrgController {
    
    private final  Logger logger = LogManager.getLogger(HrOrgController.class);
    
    @Autowired 
    private HrOrganizationService hrOrgService;
    
    @Autowired 
    private HrCompanyService hrCompanyService;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        List<HrCompany> companys = hrCompanyService.findAll();
        ModelAndView mav=new ModelAndView("/manager/org/list");
        mav.addObject("companys", companys);
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,HrOrganization hrOrganization){
        return  hrOrgService.findAllOrg(pageView, hrOrganization);
    }
    
    /**
     * 查询组织列表
     * @param hrOrganization
     * @return
     */
    @RequestMapping("getOrgByCondition")
    @ResponseBody
    public List<HrOrganization> getUserOrg(HrOrganization hrOrganization) {
        return hrOrgService.findOrgByCondition(hrOrganization);
    }
}
