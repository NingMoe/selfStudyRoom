package com.ls.spt.lstClassTest.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClassTest.entity.LstClassTest;
import com.ls.spt.lstClassTest.entity.LstClassTestCorrect;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.lstClassTest.service.LstClassTestCorrectService;
import com.ls.spt.lstClassTest.service.LstClassTestService;
import com.ls.spt.lstClassTest.service.LstTestStudentAnswerService;

@Controller
@RequestMapping(value="/lstClassTestCorrect/")
public class LstClassTestCorrectController {
    
    private final  Logger logger = LogManager.getLogger(LstClassTestCorrectController.class);
    
    @Resource
    LstClassTestCorrectService lstclasstestCorrectService;
    
    @Resource
    LstTestStudentAnswerService lsttestStudentAnswerService;
    
    @Resource
    LstClassTestService lstclasstestService;
    
    @RequestMapping("index")
    @ResponseBody
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/list");
        List<LstClassTestCorrect> list=lstclasstestCorrectService.queryCorrect();
        mav.addObject("list", list);
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView ,LstClassTestCorrect lctc){
        
        return lstclasstestCorrectService.query(pageView,lctc);
    }
    @RequestMapping("toStudentScore")
    @ResponseBody
    public ModelAndView toStudentScore(LstTestStudentAnswer ltsa,PageView pageView,String type){
        ModelAndView mav=new ModelAndView("/lstclasscorrect/stu_score_list");
        LstClassTest lct=new LstClassTest();
        lct.setId(ltsa.getTestId());
        LstClassTest l=lstclasstestService.selectPrimaryKey(lct);
        mav.addObject("classCode", ltsa.getClassCode());
        mav.addObject("testId", ltsa.getTestId());
        mav.addObject("testName", ltsa.getTestName());
        mav.addObject("className",ltsa.getClassName());
        mav.addObject("type", type);
        mav.addObject("ltsa", ltsa);
        mav.addObject("lct", l);
        return mav;
    }
}
