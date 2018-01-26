package com.ls.spt.question.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.question.entity.LstQuestionType;
import com.ls.spt.question.service.LstQuestionTypeService;

@Controller
@RequestMapping(value = "/questionType/")
public class QuestionTypeController {

    private final  Logger logger = LogManager.getLogger(QuestionTypeController.class);
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private LstQuestionTypeService questionTypeService;

    /**
     * 进入题库类型列表页面
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/question_type/list");
        /*
         * List<DicData> subjects = dictionaryService.getDataByKey("subject"); mav.addObject("subjects", subjects);
         */
        return mav;
    }

    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView, LstQuestionType lqt) {
        return questionTypeService.query(pageView, lqt);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView("/question_type/add");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        mav.addObject("subjects", subjects);
        return mav;
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(LstQuestionType lqt) {
        logger.info("添加题型----");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            lqt.setStatus(1);
            questionTypeService.insert(lqt);
            map.put("flag", true);
            map.put("message", "新增题型成功");
        }
        catch (Exception e) {
            logger.error("添加题型失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增题型异常");
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Integer id) {
        ModelAndView mav = new ModelAndView("/question_type/edit");
        List<DicData> subjects = dictionaryService.getDataByKey("subject");

        LstQuestionType questionType = questionTypeService.selectById(id);
        mav.addObject("subjects", subjects);
        mav.addObject("questionType", questionType);
        return mav;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(LstQuestionType lqt) {
        logger.info("修改题型----");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            questionTypeService.updateByPrimaryKeySelective(lqt);
            map.put("flag", true);
            map.put("message", "编辑题型成功");
        }
        catch (Exception e) {
            logger.error("编辑题型失败");
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑题型异常");
        }
        return map;
    }
}
