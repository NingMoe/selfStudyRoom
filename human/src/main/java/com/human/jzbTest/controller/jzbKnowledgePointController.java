package com.human.jzbTest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/jzbTest/jkPoint/")
public class jzbKnowledgePointController {
    private final  Logger logger = LogManager.getLogger(jzbKnowledgePointController.class);
    
    @Resource jzbKnowledgePointService jzbService;
    
    @Autowired
    private JzbDeptService deptService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private JzbGradeService gradeService;
    
    /**
     * 知识点首页
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/jzbTest/TestConfig/list");
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("dep", deptId.intValue());
        mav.addObject("dept", deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        return mav;
    }
    /**
     * 分页查询
     * @param pageView
     * @param jzb
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,jzbKnowledgePoint jzb){
        return jzbService.query(pageView,jzb);
    }
    
    /**
     * 删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String id){
        Map<String, Object> map=new HashMap<>();
        try {
            jzbService.delete(Integer.valueOf(id));
            map.put("flag", true);
            map.put("message", "删除成功");
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败");
            logger.error("删除失败："+e.getMessage());
        }
        return map;
    }
    /**
     * 跳转新增页面
     */
    @RequestMapping("toAdd")
    @ResponseBody
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/jzbTest/TestConfig/add");
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("dep", deptId.intValue());
        mav.addObject("dept", deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        return mav;
    }
    /**
     * 新增
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(jzbKnowledgePoint jzb){
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        String subject=jzb.getSubject();
        String classType=jzb.getClassType();
        String grade=jzb.getGrade();
        for (DicData dicData : subjects) {
            if(subject.equals(dicData.getName())){
                jzb.setSubject(dicData.getDataValue());
            }
        }
        for (DicData dicData : classTypes) {
            if(classType.equals(dicData.getName())){
                jzb.setClassType(dicData.getDataValue());
            }
        }
        for (JzbGrade jzbGrade : grades) {
            if(grade.equals(jzbGrade.getGradeName())){
                jzb.setGrade(String.valueOf(jzbGrade.getId()));
            }
        }
        Map<String, Object> map=new HashMap<>();
        try {
            jzbService.insert(jzb);
            map.put("flag", true);
            map.put("message", "新增成功");
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "新增失败");
            logger.error("新增失败："+e.getMessage());
            // TODO: handle exception
        }
        return map;
    }
    /**
     * 跳转新增页面
     */
    @RequestMapping("toEdit")
    @ResponseBody
    public ModelAndView toedit(String id){
        ModelAndView mav=new ModelAndView("/jzbTest/TestConfig/edit");
        jzbKnowledgePoint jzb=jzbService.selectByPrimaryKey(Integer.valueOf(id));
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        String dept=deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName();
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        mav.addObject("dep", deptId.intValue());
        mav.addObject("dept", dept);
        mav.addObject("classTypes", classTypes);
        mav.addObject("subjects", subjects);
        mav.addObject("grades", grades);
        mav.addObject("jzb", jzb);
        return mav;
    }
    /**
     * 更新数据
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(jzbKnowledgePoint jzb){
        Map<String, Object> map =new HashMap<>();
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        String subject=jzb.getSubject();
        String classType=jzb.getClassType();
        String grade=jzb.getGrade();
        for (DicData dicData : subjects) {
            if(subject.equals(dicData.getName())){
                jzb.setSubject(dicData.getDataValue());
            }
        }
        for (DicData dicData : classTypes) {
            if(classType.equals(dicData.getName())){
                jzb.setClassType(dicData.getDataValue());
            }
        }
        for (JzbGrade jzbGrade : grades) {
            if(grade.equals(jzbGrade.getGradeName())){
                jzb.setGrade(String.valueOf(jzbGrade.getId()));
            }
        }
        try {
            jzbService.update(jzb);
            map.put("flag", true);
            map.put("message", "编辑成功");
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "编辑失败");
            logger.error("编辑失败:"+e.getMessage());
            // TODO: handle exception
        }
        return map;
    }
    
    @RequestMapping("dele")
    @ResponseBody
    public Map<String, Object> dele(String deleteIds) {
        logger.info("数据管理删除");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbService.dele(deleteIds);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除数据管理失败,请稍后重试!");
        }
        return map;
    }
}
