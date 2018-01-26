package com.human.jzbTest.controller;

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
import com.human.basic.service.DictionaryService;
import com.human.basic.service.XdfClassInfoService;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.service.JzbGradeSubjectClassService;
import com.human.jzbTest.service.JzbGradeSubjectService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbConfigClass/")
public class JzbGradeSubjectClassController {
    
    private final  Logger logger = LogManager.getLogger(JzbGradeSubjectClassController.class);
                       
    @Resource
    private  DictionaryService dictionaryService;
    
    @Resource
    private JzbGradeSubjectService subjectService;
    
    @Resource
    private JzbGradeSubjectClassService classService;
    
    @Resource
    private XdfClassInfoService xdfClassInfoService;
    
    /**
     * 课程配置信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index(JzbGradeSubjectClass jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubjectClass/list");
        //获取管理部门列表
        DicData dicData=new DicData();
        dicData.setFilter(Common.getMyUser().getCompanyId());
        dicData.setDicCode("managerDept");        
        List<DicData> deptList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("deptList", deptList);
        //获取年级列表
        dicData.setDicCode("jibGrade");
        List<DicData> gradeList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("gradeList", gradeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        //获取考试分数线列表
        List<DicData> levels = dictionaryService.getDataByKey("jzb_score_level");
        mav.addObject("subjectList", subjectList);
        mav.addObject("jzbGradeSubjectClass", jgs);
        mav.addObject("levels", levels);
        return mav;
    }
    
    /**
     * 分页查询某年级科目配置的推荐课程
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbGradeSubjectClass cf) {
        return  classService.query(pageView, cf);
    }
    
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(JzbGradeSubjectClass jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubjectClass/add");
        //获取管理部门列表
        DicData dicData=new DicData();
        dicData.setFilter(Common.getMyUser().getCompanyId());
        dicData.setDicCode("managerDept");        
        List<DicData> deptList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("deptList", deptList);
        //获取年级列表
        dicData.setDicCode("jibGrade");
        List<DicData> gradeList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("gradeList", gradeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        
        List<DicData> levels = dictionaryService.getDataByKey("jzb_score_level");
        mav.addObject("levels", levels);
        mav.addObject("jzbGradeSubjectClass", jgs);
        return mav;  
    }
    
        
    /**
     * 保存某年级科目配置的推荐课程规则
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(JzbGradeSubjectClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=classService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存推荐课程规则失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubjectClass/edit"); 
        //获取管理部门列表
        DicData dicData=new DicData();
        dicData.setFilter(Common.getMyUser().getCompanyId());
        dicData.setDicCode("managerDept");        
        List<DicData> deptList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("deptList", deptList);
        //获取年级列表
        dicData.setDicCode("jibGrade");
        List<DicData> gradeList=dictionaryService.selectByDicCodeAndCompany(dicData);
        mav.addObject("gradeList", gradeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        
        JzbGradeSubjectClass jzbGradeSubjectClass=classService.selectById(id);
        mav.addObject("jzbGradeSubjectClass", jzbGradeSubjectClass);
        
        List<DicData> levels = dictionaryService.getDataByKey("jzb_score_level");
        mav.addObject("levels", levels);
        return mav; 
    }
    
    /**
     * 编辑推荐课程规则信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(JzbGradeSubjectClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=classService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑推荐课程规则失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
    
    /**
     * 删除某年级科目配置的推荐课程规则（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("delete")
     @ResponseBody
     public Map<String, Object> delete(String deleteIds) {
         logger.info("删除某年级科目配置的推荐课程规则");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=classService.delete(deleteIds);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除推荐课程规则失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除推荐课程规则失败,请稍后重试!");
         }
         return map;
     }
    
     /**
      * 全部删除某年级科目配置的推荐课程（物理删除）
      * @param videoTypeId
      * @return
      */
      @RequestMapping("deleteAll")
      @ResponseBody
      public Map<String, Object> deleteAll(Long gradeSubjectId) {
          logger.info("全部删除某年级科目配置的推荐课程");
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              map=classService.deleteAll(gradeSubjectId);
          } catch (Exception e) {
              e.printStackTrace();
              logger.error("全部删除推荐课程失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "全部删除推荐课程失败,请稍后重试!");
          }
          return map;
      }
    
    
}
