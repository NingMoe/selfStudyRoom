package com.human.jzbTest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbPaperErrorDto;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.JzbStudentDto;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbPaperQuestionService;
import com.human.jzbTest.service.JzbStudentService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbStudent/")
public class JzbStudentController {
    
    private final  Logger logger = LogManager.getLogger(JzbStudentController.class);
                   
    @Resource
    private JzbDeptService deptService;
    
    @Resource
    private JzbStudentService jstService;
    
    @Resource
    private JzbPaperQuestionService jpqService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private HrCompanyService companyService;
    
    
    
    @Value("${oss.fileurl}")
    private  String fileurl;
                  
    /**
     * 学员信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbStudent/list");
        String company=Common.getMyUser().getCompanyId();
        List<JzbDept> jzbDepts = deptService.getDeptsByCompany(company);
        List<DicData> levels = dictionaryService.getDataByKey("jzb_score_level");
        mav.addObject("jzbDepts", jzbDepts);
        mav.addObject("levels", levels);
        return mav;
    }
    
    /**
     * 分页查询学员信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbStudentDto jsd) {
        logger.info("--分页查询学员信息--");
        return  jstService.query(pageView, jsd);
    }
    
    /**
     * 跳转错题查看页面
     * @param cf
     * @return
     */
    @RequestMapping("lookErrorQuestion")
    public ModelAndView lookErrorQuestion(Integer paperId) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbStudent/lookError");
        List<JzbPaperErrorDto> jpqList=jpqService.selectByPaperId(paperId);
        mav.addObject("jpqList", jpqList);
        mav.addObject("fileurl",fileurl);
        return mav;  
    }
    
    /**
     * 进入学生报班列表页面
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("toBmRecord")
    public ModelAndView toBmRecord(PageView pageView,Integer paperId) {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbStudent/bmRecord");
        List<JzbStudentBmRecord> records = jstService.getBmRecord(paperId);
        mav.addObject("records",records);
        return mav;  
    }
}
