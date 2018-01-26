package com.human.continuedClass.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.continuedClass.entity.ContinuedClassRule;
import com.human.continuedClass.service.ContinuedClassRuleService;
import com.human.utils.PageView;


@Controller
@RequestMapping(value="/continued_class/rule/")
public class ContinuedClassRuleController {
    
    private final  Logger logger = LogManager.getLogger(ContinuedClassRuleController.class);
    
    @Resource
    private ContinuedClassRuleService ccrService;
      
    /**
     * 续班规则首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/ContinuedClass/ContinuedClassRule/list");
        return mav;
    }
    
    /**
     * 分页查询续班规则
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,ContinuedClassRule ccr) {
        return  ccrService.query(pageView, ccr);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/ContinuedClass/ContinuedClassRule/add");
        return mav;  
    }
    
    /**
     * 保存续班规则
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(ContinuedClassRule ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccrService.add(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存续班规则失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/ContinuedClass/ContinuedClassRule/edit");
        ContinuedClassRule ccr=ccrService.selectByPrimaryKey(id);
        mav.addObject("ccr", ccr);
        return mav; 
    }
    
    /**
     * 编辑续班规则
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(ContinuedClassRule ccr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccrService.edit(ccr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑续班规则失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除续班规则（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除续班规则");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccrService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除续班规则失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除续班规则失败,请稍后重试!");
        }
        return map;
    }
}
