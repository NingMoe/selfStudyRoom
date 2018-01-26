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
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.SchoolAreaService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping(value="/basic/school_area/")
public class SchoolAreaController {
    
    private final  Logger logger = LogManager.getLogger(SchoolAreaController.class);
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private SchoolAreaService saService;
      
    /**
     * 校区基础数据首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolArea/list");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    /**
     * 分页查询校区基础数据
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,SchoolArea ccr) {
        return  saService.query(pageView, ccr);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolArea/add");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;  
    }
    
    /**
     * 保存校区基础数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(SchoolArea ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=saService.add(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存校区基础数据失败!", e.getMessage());
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
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/SchoolArea/edit");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        SchoolArea ccr=saService.selectByPrimaryKey(id);
        mav.addObject("ccr", ccr);
        return mav; 
    }
    
    /**
     * 编辑校区基础数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(SchoolArea ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=saService.edit(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑校区基础数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除校区基础数据（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除校区基础数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=saService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除校区基础数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除校区基础数据失败,请稍后重试!");
        }
        return map;
    }
    
    @RequestMapping("getSchoolArea")
    @ResponseBody
    public List<SchoolArea> getSchoolArea(SchoolArea sa) {
        return saService.getSchoolArea(sa);
    }
    
    
    
}
