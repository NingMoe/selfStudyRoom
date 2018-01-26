package com.human.basic.controller;

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
import com.human.basic.entity.DicData;
import com.human.basic.entity.ResumeModular;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.ResumeModularService;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/resumeModular/")
public class ResumeModularController {
    
    private final Logger logger = LogManager.getLogger(ResumeModularController.class);
    
    @Resource
    private  DictionaryService dictionaryService;
    
    @Resource
    private ResumeModularService  rmService;
    
    
    /**
     * 简历模块管理
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/resumeModular/list");
        //获取招聘网站
        List<DicData> websiteList=dictionaryService.selectByDicCode("recruitment_website");
        mav.addObject("websiteList", websiteList);
        return mav;
    }
        
    /**
     * 分页查询简历模块
     * @param st
     * @param deptIds
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,ResumeModular rm){        
        return rmService.query(pageView, rm);  
    }
 
    /**
     * 跳转新增界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/basic/resumeModular/add");
        //获取招聘网站
        List<DicData> websiteList=dictionaryService.selectByDicCode("recruitment_website");
        mav.addObject("websiteList", websiteList);
        return mav;  
    }
    
    /**
     * 保存简历模块关键词
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> add(ResumeModular rm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=rmService.add(rm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存简历模块关键词失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/basic/resumeModular/edit");
        //获取招聘网站
        List<DicData> websiteList=dictionaryService.selectByDicCode("recruitment_website");
        mav.addObject("websiteList", websiteList);
        ResumeModular rm=rmService.selectByPrimaryKey(id);
        mav.addObject("rm", rm);
        return mav; 
    }
    
    /**
     * 编辑简历模块
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(ResumeModular rm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=rmService.edit(rm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑简历模块关键词失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除简历模块（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("delete")
     @ResponseBody
     public Map<String, Object> delete(String deleteIds) {
         logger.info("删除简历模块关键词");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=rmService.delete(deleteIds);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除简历模块关键词失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除简历模块关键词失败,请稍后重试!");
         }
         return map;
     }
     
     /**
      * 查询简历模块
      * @param ResumeModular rm
      * @return
      */
     @RequestMapping("getModularByCondition")
     @ResponseBody
     public List<ResumeModular> getResumeModular(String website) {
         return rmService.findResumeModularByCondition(website);
     }
}
