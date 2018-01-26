package com.human.recruitment.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewEntity;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.service.InterviewService;
import com.human.utils.Common;
import com.human.utils.PageView;


@Controller
@RequestMapping("/recruit/interview/")
public class InterviewController {
    
    private final  Logger logger = LogManager.getLogger(InterviewController.class);
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource
    private InterviewService interviewService;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/recruitment/interview/list");
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());       
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,InterviewEntity interviewEntity){        
        return interviewService.query(pageView, interviewEntity);  
    }
    
     
    /**
     * 导出选择的面试安排
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelect")
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
        logger.info("面试安排导出(选择项)");
        return  interviewService.exportSelect(ids, request, response);
    }
    
    /**
     * 面试安排本页导出数据处理
     * @param pageView
     * @param interviewEntity
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value ="exportThisPage")
    @ResponseBody
    public Map<String, Object> exportThisPage(PageView pageView,InterviewEntity interviewEntity,HttpServletRequest request, HttpServletResponse response){
        logger.info("面试安排导出(本页)");
        return interviewService.exportThisPage(pageView, interviewEntity, request, response);
    }
    
    /**
     * 导出所有的面试安排
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportAll")
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
        logger.info("面试安排导出(全部)");
        return  interviewService.exportAll(request, response);
    }
    
    /**
     * 进入编辑安排面试信息页面
     * @return
     */
    @RequestMapping(value="editInterview")
    public ModelAndView editInterview(Integer id){
        ModelAndView mav=new ModelAndView("/recruitment/interview/edit");  
        HrResumeFlow hrResumeFlow=interviewService.selectByPrimaryKey(id);
        mav.addObject("hrResumeFlow",hrResumeFlow);
        return mav;
    }
    
    /**
     * 编辑安排面试
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object>edit(InterviewSchedule interviewSchedule){
       return interviewService.edit(interviewSchedule);
    }
    
    /**
     * 进入批量安排面试信息页面
     * @return
     */
    @RequestMapping(value="batch_editInterview")
    public ModelAndView batch_editInterview(String ids){
        ModelAndView mav=new ModelAndView("/recruitment/interview/batch_edit");
        mav.addObject("ids",ids);
        return mav;
    }
    
    /**
     * 批量编辑安排面试
     * @return
     */
    @RequestMapping(value="batch_edit")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object>edit(String flowIds,InterviewSchedule interviewSchedule){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            String[]flowId=flowIds.split(",");
            for(String id:flowId){
                interviewSchedule.setFlowId(id);
                result=interviewService.edit(interviewSchedule);
            } 
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "批量安排面试时间失败!");
        }        
       return result;
    }
}
