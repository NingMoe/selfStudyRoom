package com.human.recruitment.controller;


import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.service.InterviewObserverService;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/recruit/watcher/")
public class InterviewObserverController {
    
    private final  Logger logger = LogManager.getLogger(InterviewObserverController.class);
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource
    private InterviewObserverService ioService;
    
    @Resource
    private  DictionaryService dictionaryService;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/recruitment/watcher/list");
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        //获取学历类型
        List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
        //获取简历来源
        List<DicData> resumeSourceList=dictionaryService.selectByDicCode("resume_source");
        mav.addObject("companyList", companyList);
        mav.addObject("eduList", eduList);
        mav.addObject("resumeSourceList", resumeSourceList);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,ResumeBase resumeBase){        
        return ioService.query(pageView, resumeBase);  
    }
    
     
    /**
     * 导出选择的面试观察员信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelect")
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
        logger.info("面试观察员信息导出(选择项)");
        return  ioService.exportSelect(ids, request, response);
    }
    
    /**
     * 面试观察员信息本页导出数据处理
     * @param pageView
     * @param interviewEntity
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value ="exportThisPage")
    @ResponseBody
    public Map<String, Object> exportThisPage(PageView pageView,ResumeBase resumeBase,HttpServletRequest request, HttpServletResponse response){
        logger.info("面试观察员信息导出(本页)");
        return ioService.exportThisPage(pageView, resumeBase, request, response);
    }
    
    /**
     * 导出所有的
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportAll")
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
        logger.info("面试观察员信息导出(全部)");
        return  ioService.exportAll(request, response);
    }
    

}
