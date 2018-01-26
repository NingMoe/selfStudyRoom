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
import com.human.recruitment.entity.ResumeTalent;
import com.human.recruitment.service.TalentService;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/recruit/talent/")
public class TalentController {
    
    private final  Logger logger = LogManager.getLogger(TalentController.class);
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource
    private TalentService talentService;
    
    @Resource
    private  DictionaryService dictionaryService;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/recruitment/talent/list");
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        //获取学历类型
        List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
        mav.addObject("companyList", companyList);
        mav.addObject("eduList", eduList);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,ResumeTalent  resumeTalent){        
        return talentService.query(pageView, resumeTalent);  
    }
    
     
    /**
     * 导出选择的人才库
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelect")
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
        logger.info("人才库导出(选择项)");
        return  talentService.exportSelect(ids, request, response);
    }
    
    /**
     * 人才库本页导出数据处理
     * @param pageView
     * @param interviewEntity
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value ="exportThisPage")
    @ResponseBody
    public Map<String, Object> exportThisPage(PageView pageView,ResumeTalent  resumeTalent,HttpServletRequest request, HttpServletResponse response){
        logger.info("人才库导出(本页)");
        return talentService.exportThisPage(pageView, resumeTalent, request, response);
    }
    
    /**
     * 导出所有的人才库
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportAll")
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
        logger.info("人才库导出(全部)");
        return  talentService.exportAll(request, response);
    }
    
   /**
    * 移出人才库
    * @param deleteIds
    * @return
    */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds) {
        logger.info("移出人才库");
        return talentService.updateStatus(deleteIds);
    }
}
