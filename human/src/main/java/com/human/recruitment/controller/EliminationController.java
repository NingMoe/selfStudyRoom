package com.human.recruitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.bpm.service.HrWorkflowService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.entity.EliminationResult;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.EliminationService;
import com.human.recruitment.service.HrPositionService;
import com.human.recruitment.service.PositionProcessService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/elimination/")
public class EliminationController {
    private final  Logger logger = LogManager.getLogger(EliminationController.class);
    
    @Autowired
    private HrWorkflowService hrWorkflowService;
    
    @Autowired
    private HrPositionService positionService;
    
    @Autowired
    private PositionProcessService positionProcessService;
    
    @Autowired
    private UserDeptService userDeptService;
    
    @Autowired
    private EliminationService eliminationService;
    
    @Autowired 
    private DictionaryService dictionaryService;
    
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        
        HrCompany company = new HrCompany();
        company.setCompanyId(u.getCompanyId());
        company.setCompanyName(u.getCompanyName());
        
        List<DicData> degrees = dictionaryService.getDataByKey("edu_degree");
        ModelAndView mav=new ModelAndView("/recruitment/elimination/list");
        mav.addObject("company",company);
        mav.addObject("orgs",orgs);
        mav.addObject("degrees",degrees);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,HrResumeFlow flow,ResumeSeeker seeker){
        if(flow!=null){
            flow.setSeeker(seeker);
            flow.setAssignee(Common.getMyUser().getUsername());
        }
        return  hrWorkflowService.getHrResumeRlqrPage(pageView, flow);
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="getPositionList")
    @ResponseBody
    public List<HrPosition> getPositionList(HrPosition position){
        return  positionService.getValidPositionList(position);
    }
    
    /**
     * 获取流程职位
     * @return
     */
    @RequestMapping(value="getPositionProcessList")
    @ResponseBody
    public List<PositionProcess>getPositionProcessList(String deptId){
        return positionProcessService.getListByDeptId(deptId);
    }
    
    /**
     * 进入淘汰页面
     * @return
     */
    @RequestMapping(value="toTaotai")
    public ModelAndView toTaotai(Integer flowId,Long resumeId,String flowCode){
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        ModelAndView mav=new ModelAndView("/recruitment/elimination/taotai");
        mav.addObject("flowId",flowId);
        mav.addObject("resumeId",resumeId);
        mav.addObject("flowCode",flowCode);
        mav.addObject("orgs",orgs);
        return mav;
    }
    
    /**
     * 淘汰
     * @return
     */
    @RequestMapping(value="taotai")
    @ResponseBody
    public Map<String,Object> taotai(EliminationResult eliminationResult){
       logger.info("淘汰简历,简历ID："+eliminationResult.getResumeId());
       Map<String,Object> result = new HashMap<String,Object>();
       try{
           eliminationService.dealElimination(eliminationResult);
           result.put("flag", true);
           result.put("message", "淘汰处理成功!");
       }catch(Exception e){
           logger.error(e.getMessage());
           result.put("flag", false);
           result.put("message", "淘汰处理失败!");
       }
       return result;
    }
    
    
    /**
     * 更换岗位
     * @return
     */
    @RequestMapping(value="toChangePosition")
    public ModelAndView toChangePosition(HttpServletRequest request, Integer flowId,Long resumeId,String flowCode){
        ModelAndView mav=new ModelAndView("/recruitment/elimination/changePosition");
        MyUser u = Common.getMyUser();
        UserDept ud = new UserDept();
        ud.setCompanyId(u.getCompanyId());
        ud.setUserId(u.getUserid());
        List<HrOrganization> orgs = userDeptService.getUserOrg(ud);
        mav.addObject("resumeId",resumeId);
        mav.addObject("flowId",flowId);
        mav.addObject("flowCode",flowCode);
        mav.addObject("orgs",orgs);
        return mav;
    }
    
    /**
     * 更换流程
     * @return
     */
    @RequestMapping(value="changePosition")
    @ResponseBody
    public Map<String,Object> changePosition(InterviewSchedule interviewSchedule){
       logger.info("简历更换流程,简历ID："+interviewSchedule.getResumeId());
       Map<String,Object> result = new HashMap<String,Object>();
       try{
           //结束当前流程
           eliminationService.dealChangePosition(interviewSchedule);
           //提交新的流程
           hrWorkflowService.startProcess(interviewSchedule);
           result.put("flag", true);
           result.put("message", "更换流程提交成功!");
       }catch(Exception e){
           logger.error(e.getMessage());
           result.put("flag", false);
           result.put("message", "更换流程提交失败!");
       }
       return result;
    }
    
}
