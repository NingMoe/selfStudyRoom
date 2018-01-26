package com.human.bpm.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.entity.MailMessage;
import com.human.bpm.dao.ActCustomCommentDao;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.ActCustomScorce;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.bpm.entity.HrActNode;
import com.human.bpm.service.BpmConfigService;
import com.human.bpm.service.BpmTaskService;
import com.human.bpm.service.HrWorkflowService;
import com.human.manager.service.MailSendRecordService;
import com.human.recruitment.dao.HrPositionDao;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.PositionProcessDao;
import com.human.recruitment.dao.PositionProcessUserDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionProcessExtend;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeOperRecordDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeOperRecord;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.SecurityHelper;

@Service
public class HrWorkflowServiceImpl implements HrWorkflowService{
    
    @Autowired
    private HrPositionDao hrPositionDao;
    
    @Autowired
    private PositionProcessDao positionProcessDao;
    
    @Autowired
    private PositionProcessUserDao processUserDao;
    
    @Autowired
    private BpmConfigService bpmConfigService;
    
    @Autowired
    protected IdentityService identityService;
    
    @Autowired
    private BpmTaskService bpmTaskService;
    
    @Autowired
    private HrResumeFlowDao resumeFlowDao;
    
    @Autowired
    private ResumeOperRecordDao operRecordDao;
    
    @Autowired
    private ActCustomCommentDao commentDao;
    
    @Autowired
    private ResumeBaseDao resumeBaseDao;
    
    @Autowired
    private RecruitAcceptanceDao seekerDao;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected RuntimeService runtimeService;
    
    @Autowired
    protected MailSendRecordService mailSendRecordService;
    
    @Resource
    private SecurityHelper sh;
    
    
    
    
    @Override
    @Transactional(rollbackOn=Exception.class)
    public HrResumeFlow startProcess(InterviewSchedule interviewSchedule) {
        // TODO Auto-generated method stub
        Integer positionProcessId = interviewSchedule.getPositionProcessId();
        PositionProcessExtend process = positionProcessDao.getPositionProcessExtend(positionProcessId);
        Map<String, Object> variables = new HashMap<String,Object>();
        String userName = Common.getAuthenticatedUsername();
        String flowCode = getFlowCode(process.getComid());
        List<BpmNodeConfig> configs = bpmConfigService.getAllConfig(process.getProcessDef());
        List<PositionProcessUser> firstAssignees = null; 
        //设置流程变量
        for(BpmNodeConfig c:configs){
            if(BpmNodeConfig.NODETYPE_P.equals(c.getNodeType()) || BpmNodeConfig.NODETYPE_G.equals(c.getNodeType()) ){
                continue;
            }
            List<String> userIds = new ArrayList<String>(); 
            PositionProcessUser su = new PositionProcessUser();
            su.setPositionId(process.getPositionId()+"");
            su.setType(process.getType()+"");
            su.setRole(c.getAssigneeExp());
            List<PositionProcessUser> users = processUserDao.getProcessUserByCondition(su);
            for(PositionProcessUser p:users){
                userIds.add(p.getEmail());
            }
            variables.put(c.getAssigneeExp(), userIds);
            variables.put(HrPosition.POSITIONFLAG, process.getPositionId()+"");
            variables.put(HrPosition.PROCESSDRAFT,userName);
            if(c.getNodeId().equals(HrPosition.FIRSTNODE)){
                firstAssignees = users;
            }
        }
        //提交流程
        String processDef = process.getProcessDef();
        String processKey = processDef.substring(0,processDef.indexOf(":"));
        ProcessInstance processInstance = bpmTaskService.start(processKey, flowCode, variables);
        
        //更新简历表状态
        ResumeBase rb = new ResumeBase();
        rb.setId(Long.valueOf(interviewSchedule.getResumeId()));
        rb.setFlowStatus("1");
        resumeBaseDao.updateByPrimaryKeySelective(rb);
        //记录简历流转
        ResumeOperRecord ror = new ResumeOperRecord();
        ror.setOper(Common.getAuthenticatedUsername());
        ror.setOperTime(new Date());
        ror.setOperType(0);
        ror.setResumeId(Integer.valueOf(interviewSchedule.getResumeId()));
        operRecordDao.insert(ror);
        
        //记录流转单
        HrResumeFlow flow = new HrResumeFlow();
        flow.setCreateTime(new Timestamp(System.currentTimeMillis()));
        flow.setPositionProcessId(positionProcessId);
        flow.setFlowCode(flowCode);
        flow.setResumeId(Long.valueOf(interviewSchedule.getResumeId()));
        flow.setComid(process.getComid());
        flow.setDept(process.getDept());
        flow.setProcessInstanceId(processInstance.getId());
        flow.setStatus(0);
        flow.setCurrentNode(HrPosition.FIRSTNODE);
        if(interviewSchedule.getInterviewTime()!=null){
            flow.setInterviewTime(Timestamp.valueOf(interviewSchedule.getInterviewTime()));
        }
        if(StringUtils.isNotEmpty(interviewSchedule.getInterviewLocation())){
            flow.setInterviewLocation(interviewSchedule.getInterviewLocation());
        }
        resumeFlowDao.insert(flow);
        resumeBaseDao.lockResumeBaseToFlow(Integer.valueOf(interviewSchedule.getResumeId()));

       /* ResumeSeeker rs = seekerDao.selectByResumeBaseId(Integer.valueOf(interviewSchedule.getResumeId()));
        for(PositionProcessUser u :firstAssignees){
            String approveUrl = sh.CreateResumeUrl(flowCode, u.getEmail());
            StringBuffer emailHtml = new StringBuffer("");
            StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
            sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+u.getName()+"老师：您好！smartwork系统有简历需要您审阅，求职者信息如下<span style=\"font-size:14px;color:red;\">（查看简历后请填写面试反馈）</span>:</font></div>");
            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "<table width=\"100%\" style=\"border-collapse:collapse;width:100%;text-align:justify;border:1px solid #B4D2F0;\">"
                    + "<tr style=\"height:50px;\">"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">姓名</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">手机号</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">性别</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">出生年月</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">毕业院校</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">专业</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">学历</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">毕业时间</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">当前环节</span></font></td>"
                    + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">简历详情</span></font></td>"
                    + "</tr>");
            
            String major = rs.getMajor();
            if(StringUtils.isNotEmpty(major)){
                major = major.replaceAll("[^\u4e00-\u9fa5]", "");
            }else{
                major = "";
            }
            
            String highEdu = rs.getHighEdu();
            if(StringUtils.isNotEmpty(highEdu)){
                highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
            }else{
                highEdu = "";
            }
            String tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
            String sex = "M".equals(rs.getSex())?"男":"女";
            String birthDate = "";
            if(StringUtils.isNotEmpty(rs.getBirthDate())){
                birthDate = rs.getBirthDate();
            }
            sb.append(tr
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+rs.getName()+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+rs.getPhone()+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+sex+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+birthDate+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+rs.getGraSchool()+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+rs.getGraduationDate()+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+process.getFirstNodeName()+"</span></font></td>"
                    + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\"><a href=\""+approveUrl+"\" target=\"_blank\"><font color=\"#0088CC\">查看</font></a></span></font></td>"
                    + "</tr>");
            
            sb.append("</table></div>");
            MailMessage mailMessage = new MailMessage();
            mailMessage.setSubject("简历-"+rs.getName()+"-"+process.getPositionName()+"-"+process.getFirstNodeName());
            mailMessage.setTo(new String[]{u.getEmail()+"@xdf.cn"});
            //mailMessage.setTo(new String[]{"chenyunsong@xdf.cn"});
            mailMessage.setType("2");
            mailMessage.setMessage(sb.toString());
            mailSendRecordService.sendMail(process.getComid(), mailMessage,null);
        }*/
        //发送邮件
        return flow;
    }
    
    @Override
    public void tjResumeFlow(String flowCode) {
        Task task = getCurrentTask(flowCode);
        bpmTaskService.claim(task.getId(), Common.getAuthenticatedUsername());
        taskService.complete(task.getId());
    }
    
    @Override
    public void tjResumeMultiFlow(ActCustomComment comment) {
        Task task = getCurrentTask(comment.getFlowCode(),comment.getApprover());
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put(HrPosition.PROCESSVAR,comment.getResult()+"");
        taskService.complete(task.getId(), variables);
        addComment(comment);
    }
    
    @Override
    public Task getCurrentTask(String businessKey) {
        return taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list().get(0);
    }
    
    @Override
    public Task getCurrentTask(String businessKey, String assignee) {
        return taskService.createTaskQuery().processInstanceBusinessKey(businessKey).taskAssignee(assignee).list().get(0);
    }
    
    @Override
    public List<ActCustomComment> getAssignee(String businessKey){
        List<ActCustomComment> result = new ArrayList<ActCustomComment>();
        List<Task> tasks= taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
        for(Task t : tasks){
            ActCustomComment ac=new ActCustomComment();
            ac.setApprover(t.getAssignee());
            ac.setFlowCode(businessKey);
            ac.setNodeId(t.getTaskDefinitionKey());
            result.add(ac);
        }
        return result;
    }
    
    private String getFlowCode(String companyId){
        Date d = new Date();
        SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = smf.format(d);
        String currentCode = resumeFlowDao.getMaxCodeByPrefix("JL"+companyId+"-"+dateStr);
        if(StringUtils.isEmpty(currentCode)){
            return "JL"+companyId+"-"+dateStr+"00001";
        }
        int code = Integer.valueOf(1+currentCode.substring(currentCode.length()-5))+1;
        return  "JL"+companyId+"-"+dateStr+(code+"").substring(1);
    }
    
    @Override
    public PageView getHrResumeRlqrPage(PageView pageView, HrResumeFlow flow) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", flow);
        List<HrResumeFlow> list = resumeFlowDao.selectResumeFlowPage(map);
        for(HrResumeFlow f:list){
            ActCustomComment c = commentDao.getLoseComment(f.getFlowCode());
            f.setLoseCause(c.getBackReason());
        }
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    @Transactional(rollbackOn=Exception.class)
    public void addComment(ActCustomComment comment) {
        commentDao.insert(comment);
        if(null!=comment.getScorceList()){
            for(ActCustomScorce acs: comment.getScorceList()){
                acs.setCommentId(comment.getId());
                resumeFlowDao.insertScore(acs);
            }
        }
    }
    
    @Override
    @Transactional
    public void delProcess(String flowCode,String processInstanceId, String delReason) {
        runtimeService.deleteProcessInstance(processInstanceId, delReason);
        //将流转单状态改成无效
        HrResumeFlow flow = new HrResumeFlow();
        flow.setProcessInstanceId(processInstanceId);
        flow.setStatus(1);
        resumeFlowDao.updateByProcessInstanceIdSelective(flow);
        resumeBaseDao.unlockResumeBaseToFlow(flowCode);
    }
    
    @Override
    public ProcessInstance getProcessInstanceByFlowCode(String flowCode) {
        return runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(flowCode).list().get(0);
    }
    
    @Override
    public List<ActCustomComment> getNodeComment(ActCustomComment comment) {
        return commentDao.getLoseNodeComments(comment.getFlowCode(), comment.getNodeId());
    }
    
    @Override
    public HrActNode getCurrNode(String flowCode) {
        // TODO Auto-generated method stub
        return resumeFlowDao.getCurrNode(flowCode);
    }
    
    @Override
    public List<PositionHrUser> getPositionHrUsersByPositionId(Integer positionId) {
        return hrPositionDao.getPositionHrUsers(positionId);
    }
}
