package com.human.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.bpm.service.HrWorkflowService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.HrOrganizationService;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.entity.EliminationResult;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.ResumeHrRemark;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.EliminationService;
import com.human.recruitment.service.HrPositionService;
import com.human.recruitment.service.PositionProcessService;
import com.human.recruitment.service.TalentService;
import com.human.resume.entity.ResumeBase;
import com.human.resume.service.ResumeService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/resume/")
public class ResumeController {
    
    private final  Logger logger = LogManager.getLogger(ResumeController.class);
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource 
    private HrOrganizationService hrOrgService;
    
    @Resource
    private HrWorkflowService hrWorkflowService;
    
    @Resource
    private HrPositionService positionService;
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private ResumeService resumeService;
    
    @Resource
    private  DictionaryService dictionaryService;
    
    @Resource
    private  PositionProcessService positionProcessService;
    
    @Resource
    private EliminationService eliminationService;
    
    @Resource
    private TalentService talentService;
    
    /**
     * 简历管理列表页面
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/recruitment/resume/list");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        //获取简历来源
        List<DicData> resumeSourceList=dictionaryService.selectByDicCode("resume_source");
        //获取学历类型
        List<DicData> eduList=dictionaryService.selectByDicCode("edu_degree");
        mav.addObject("companyList", companyList);
        mav.addObject("resumeSourceList", resumeSourceList);
        mav.addObject("eduList", eduList);
        return mav;
    }
    
    /**
     * 简历管理列表页面
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/recruitment/resume/add");
        return mav;
    }
    
    /**
     * 添加简历
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(ResumeSeeker rs){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            resumeService.addResume(rs);
            map.put("flag", true);
            map.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message","保存失败");
        }
        return map;
    }
    
    /**
     * 进入沟通记录列表页面
     * @return
     */
    @RequestMapping("toComment")
    public ModelAndView toComment(Integer resumeId) {
        ModelAndView mav=new ModelAndView("/recruitment/resume/comment");
        List<ResumeHrRemark> remarks = resumeService.getCommentByResumeId(resumeId);
        mav.addObject("remarks", remarks);
        mav.addObject("resumeId", resumeId);
        mav.addObject("userName", Common.getMyUser().getName());
        return mav;
    }
    
    /**
     * 添加简历
     * @return
     */
    @RequestMapping(value="addComment")
    @ResponseBody
    public Map<String,Object> addComment(ResumeHrRemark rhr){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            resumeService.addHrContent(rhr);
            map.put("flag", true);
            map.put("message","保存成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message","保存失败");
        }
        return map;
    }
    
    
    /**
     * 分页查询简历
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,ResumeBase resumeBase){
        return  resumeService.query(pageView, resumeBase);
    }
    
    /**
     * 安排面试页面
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="arrangeInterview")
    public ModelAndView toArrangeInterview(Integer id,String hrCompanyId){
        ModelAndView mav=new ModelAndView("/recruitment/resume/arrangeInterview");
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",id);
        return mav;
    }
    
    /**
     * 批量安排面试
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="batchArrangeInterview")
    public ModelAndView batchArrangeInterview(String ids){
        ModelAndView mav=new ModelAndView("/recruitment/resume/arrangeInterview");
        String[]resumeIds=ids.split(",");
        //刷选出未处理的简历
        String listIds="";
        for(String resumeId: resumeIds){
            ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeId));
            if("0".equals(rb.getFlowStatus())){
                listIds+=resumeId+",";
            }
        }
        ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeIds[0]));
        String hrCompanyId=rb.getHrCompanyId();       
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",listIds);
        return mav;
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
     * 安排面试 提交流程
     * @return
     */
    @RequestMapping(value="addNewProcess")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object>addNewProcess(InterviewSchedule interviewSchedule){
       Map<String,Object> result = new HashMap<String,Object>();
       try{         
           String resumeId =interviewSchedule.getResumeId();
           if(StringUtils.isNotEmpty(resumeId)){
               String[]resumeIds=resumeId.split(",");
               for(String rId:resumeIds){               
                   ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(rId));
                   if("2".equals(rb.getFlowStatus())){
                       continue;
                   }
                   String flowStatus=rb.getFlowStatus();
                   if("1".equals(flowStatus)){//当前有流程
                       HrResumeFlow hrf=resumeService.selectHrFlowByResumeId(Integer.valueOf(rId));
                       String processInstanceId =hrf.getProcessInstanceId();
                       String flowCode = hrf.getFlowCode();
                       String delReason="";
                       //删除当前流程
                       hrWorkflowService.delProcess(flowCode,processInstanceId, delReason);              
                   }
                   //提交新的流程
                   interviewSchedule.setResumeId(rId);
                   hrWorkflowService.startProcess(interviewSchedule);
                   if("3".equals(flowStatus)){//简历在人才库
                       talentService.deleteByResumeId(Integer.valueOf(rId));
                   }
                   //第二步 ：反写简历表中的匹配职位
                   PositionProcess pp=positionProcessService.getPositionProcessById(interviewSchedule.getPositionProcessId());
                   ResumeBase rbase=new ResumeBase();
                   rbase.setId(Long.valueOf(rId));
                   rbase.setMatchingPosition(pp.getPositionId().toString());
                   rbase.setFlowStatus("1");
                   resumeService.updateByPrimaryKeySelective(rbase);                    
               } 
               result.put("flag", true);
               result.put("message", "安排面试提交流程提交成功!");
           }else{
               result.put("flag", false);
               result.put("message", "你选择的简历都已经在走流程中!"); 
           }
       }catch(Exception e){
           e.printStackTrace();
           logger.error(e.getMessage());
           result.put("flag", false);
           result.put("message", "安排面试提交流程提交失败!");
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       }
       return result;
    }
       
    /**
     * 直接淘汰简历页面
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="eliminate")
    public ModelAndView eliminateResume(Integer id,String hrCompanyId){
        ModelAndView mav=new ModelAndView("/recruitment/resume/eliminate");
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",id);
        return mav;
    }
        
    /**
     * 批量淘汰简历页面
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="batchEliminate")
    public ModelAndView batchEliminate(String ids){
        ModelAndView mav=new ModelAndView("/recruitment/resume/eliminate");
        String[]resumeIds=ids.split(",");
        //刷选出未处理的简历
        String listIds="";
        for(String resumeId: resumeIds){
            ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeId));
            if("0".equals(rb.getFlowStatus())){
                listIds+=resumeId+",";
            }
        }
        ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeIds[0]));
        String hrCompanyId=rb.getHrCompanyId();  
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",listIds);
        return mav;
    }
    /**
     * 直接淘汰
     * @return
     */
    @RequestMapping(value="taotai")
    @ResponseBody
    public Map<String,Object> taotai(EliminationResult eliminationResult){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String resumeId = eliminationResult.getResumeId();
            String[] resumeIds = resumeId.split(",");
            for (String rId : resumeIds) {
                // 第一步 ：先查询简历当前状态
                ResumeBase rb = resumeService.selectByPrimaryKey(Long.valueOf(rId));
                String flowStatus = rb.getFlowStatus();
                eliminationResult.setResumeId(rId);
                if ("0".equals(flowStatus)) {// 未处理的简历
                    eliminationService.eliminationResume(eliminationResult);
                }
                else if ("1".equals(flowStatus)) {// 流程中的简历
                    eliminationService.eliminationResume(eliminationResult, 1);
                }
                else if ("3".equals(flowStatus)) {// 已放入人才库的简历
                    eliminationService.eliminationResume(eliminationResult, 3);
                }
            }
            result.put("flag", true);
            result.put("message", "简历直接淘汰处理成功!");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "简历直接淘汰处理失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }
    
    /**
     * 简历回收页面
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="recovery")
    public ModelAndView recoveryResume(Integer id,String hrCompanyId){
        ModelAndView mav=new ModelAndView("/recruitment/resume/recovery");
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",id);
        return mav;
    }
    
    /**
     * 批量简历回收页面
     * @param id    简历ID
     * @param hrCompanyId   机构ID
     * @return
     */
    @RequestMapping(value="batchRecovery")
    public ModelAndView batchRecoveryResume(String ids){
        ModelAndView mav=new ModelAndView("/recruitment/resume/recovery");
        String[]resumeIds=ids.split(",");
        //刷选出简历
        String listIds="";
        for(String resumeId: resumeIds){
            ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeId));
            if("1".equals(rb.getFlowStatus())||"2".equals(rb.getFlowStatus())||"3".equals(rb.getFlowStatus())){
                listIds+=resumeId+",";
            }
        }
        ResumeBase rb=resumeService.selectByPrimaryKey(Long.valueOf(resumeIds[0]));
        String hrCompanyId=rb.getHrCompanyId(); 
        HrOrganization hrOrganization=new HrOrganization();
        hrOrganization.setCompany(hrCompanyId);
        List<HrOrganization> orgList=hrOrgService.findOrgByCondition(hrOrganization);
        mav.addObject("orgList",orgList);
        mav.addObject("resumeId",listIds);
        return mav;
    }

    /**
     * 简历放入人才库
     * @return
     */
    @RequestMapping(value="personnel")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object> personnel(EliminationResult eliminationResult){
       Map<String,Object> result = new HashMap<String,Object>();
       try{
            String resumeId = eliminationResult.getResumeId();
            String[] resumeIds = resumeId.split(",");
            for (String rId : resumeIds) {
                // 第一步 ：先查询简历当前状态
                ResumeBase rb = resumeService.selectByPrimaryKey(Long.valueOf(rId));
                String flowStatus = rb.getFlowStatus();
                eliminationResult.setResumeId(rId);
                if ("1".equals(flowStatus)) {// 当前有流程
                    HrResumeFlow hrf = resumeService.selectHrFlowByResumeId(Integer.valueOf(rId));
                    String processInstanceId = hrf.getProcessInstanceId();
                    String delReason = "";
                    String flowCode = hrf.getFlowCode();
                    // 删除当前流程
                    hrWorkflowService.delProcess(flowCode,processInstanceId, delReason);
                }
                eliminationService.eliminationResume(eliminationResult, 2);
            }
            result.put("flag", true);
            result.put("message", "简历放入人才库处理成功!");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "简历放入人才库处理失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    /**
     * 延迟面试
     * @return
     */
    @RequestMapping(value="delay")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object> delayInterview(String ids){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            result =resumeService.delayInterview(ids);  
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "简历设置延迟面试失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }        
        return result;
    }
    
    /**
     * 恢复面试
     * @return
     */
    @RequestMapping(value="recover")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public Map<String,Object> recoverInterview(String ids){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            result =resumeService.recoverInterview(ids);  
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "简历设置恢复面试失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }        
        return result;
    }
}
