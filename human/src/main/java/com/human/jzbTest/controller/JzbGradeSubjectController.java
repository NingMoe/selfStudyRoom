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
import com.human.jzbTest.entity.JzbGradeSubject;
import com.human.jzbTest.service.JzbGradeSubjectService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbGradeSubject/")
public class JzbGradeSubjectController {
    
 private final  Logger logger = LogManager.getLogger(JzbGradeSubjectController.class);
                       
    @Resource
    private  DictionaryService dictionaryService;
    
    @Resource
    private JzbGradeSubjectService subjectService;
    
    /**
     * 年级科目配置信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index(JzbGradeSubject jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubject/list");
        //获取班级类型
        List<DicData> classTypeList=dictionaryService.selectByDicCode("class_type");
        mav.addObject("classTypeList", classTypeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        mav.addObject("jzbGradeSubject", jgs);
        return mav;
    }
    
    /**
     * 分页查询年级科目配置信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbGradeSubject cf) {
        return  subjectService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(JzbGradeSubject jgs) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubject/add");
        //获取班级类型
        List<DicData> classTypeList=dictionaryService.selectByDicCode("class_type");
        mav.addObject("classTypeList", classTypeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        mav.addObject("jzbGradeSubject", jgs);
        return mav;  
    }
    
    /**
     * 保存年级科目配置信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(JzbGradeSubject cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=subjectService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存年级科目数据失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/jzbTest/jzbGradeSubject/edit"); 
        //获取班级类型
        List<DicData> classTypeList=dictionaryService.selectByDicCode("class_type");
        mav.addObject("classTypeList", classTypeList);
        //获取科目列表
        List<DicData> subjectList=dictionaryService.selectByDicCode("subject");
        mav.addObject("subjectList", subjectList);
        JzbGradeSubject jzbGradeSubject=subjectService.selectById(id);
        mav.addObject("jzbGradeSubject", jzbGradeSubject);
        return mav; 
    }
    
    /**
     * 编辑年级科目配置信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(JzbGradeSubject cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=subjectService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑年级科目数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除年级科目配置信息（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除年级科目数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=subjectService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除年级科目数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除年级科目数据失败,请稍后重试!");
        }
        return map;
    }

    /**
     * 查询某年级下的科目
     * @param gradeId
     * @return
     */
    @RequestMapping("selectSubjectByGradeId")
    @ResponseBody
    public List<JzbGradeSubject> selectByGradeId(Long gradeId) {       
        return subjectService.selectByGradeId(gradeId);
    }
}
