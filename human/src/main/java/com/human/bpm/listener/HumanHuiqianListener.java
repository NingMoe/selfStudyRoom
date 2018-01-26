package com.human.bpm.listener;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.basic.entity.MailMessage;
import com.human.bpm.dao.BpmNodeConfigDao;
import com.human.bpm.entity.BpmAction;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.bpm.entity.BpmTransitionConfig;
import com.human.bpm.service.BpmConfigService;
import com.human.manager.service.MailSendRecordService;
import com.human.recruitment.dao.HrResumeEntryhandlerDao;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.RecruitAcceptanceDao;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessUser;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.dao.ResumeBaseDao;
import com.human.utils.SecurityHelper;

@Service
public class HumanHuiqianListener implements TaskListener{
    /**
     * 监听会签节点
     */
    private static final long serialVersionUID = 1L;
    
    public static final String RESULT_NOPASS = "0";
    public static final String RESULT_PASS = "1";
    public static final String RESULT_BACK = "2";
    public static final String RESULT_QUICKPASS = "3";
    
    @Autowired
    private BpmConfigService bpmConfigService;
    
    @Autowired
    private HrResumeFlowDao flowDao;
    
    @Autowired ResumeBaseDao resumeBaseDao;
    
    @Autowired
    private HrResumeEntryhandlerDao entryhandlerDao;
    
    @Autowired
    private RecruitAcceptanceDao seekerDao;
    
    @Autowired
    private BpmNodeConfigDao nodeConfigDao;
    
    @Autowired
    private MailSendRecordService mailSendRecordService;
    
    @Resource
    private SecurityHelper sh;
    
     
    @Override
    @Transactional
    public void notify(DelegateTask delegateTask) {
        String taskKey = delegateTask.getTaskDefinitionKey();
        String processdefId = delegateTask.getProcessDefinitionId();
        BpmTransitionConfig c = new BpmTransitionConfig();
        c.setProcessdefId(processdefId);
        c.setSourceNode(taskKey);
        String condition = bpmConfigService.selectByCondition(c).getConditionText();
        String conditionExp = condition.substring(2, condition.indexOf("="));
        ActivityExecution execution = (ActivityExecution)delegateTask.getExecution();
        String flowCode = execution.getProcessBusinessKey();
        PositionProcessNodeConfig signConfig = bpmConfigService.getSignConfigByTaskAndFlowCode(flowCode, taskKey);
        Map<String,Object> isCompltete = isComplete(execution,signConfig);
        if((boolean)isCompltete.get("flag")){
            Map<String,String> map = new HashMap<String,String>();
            String result = (String) isCompltete.get("vote");
            map.put(conditionExp, result);
            delegateTask.setVariables(map);
            c.setConditionText("${"+conditionExp+"==\""+result+"\"}");
            c = bpmConfigService.selectByPrimaryCondition(c);
            String nextNode = c.getTargetNode();
            
            HrResumeFlow flow = new HrResumeFlow();
            flow.setFlowCode(flowCode);
            flow.setCurrentNode(nextNode);
            flow.setInterviewLocation("");
            flow.setInterviewTime(null);
            
            if( (result.equals(HumanHuiqianListener.RESULT_PASS) && !nextNode.equals(HrPosition.ENDNODE)) || result.equals(HumanHuiqianListener.RESULT_BACK) ){
                ResumeSeeker rs = seekerDao.selectByFlowCode(flowCode);
                BpmAction bpmAction = new BpmAction();
                bpmAction.setAction(result.equals(HumanHuiqianListener.RESULT_PASS)?"同意":"退回");
                bpmAction.setFlowCode(flowCode);
                bpmAction.setSourceNode(taskKey);
                List<PositionProcessUser>  users = bpmConfigService.getNextAssignee(bpmAction);
                BpmNodeConfig nc = nodeConfigDao.selectByNodeIdAndFlowCode(flowCode, nextNode); 
               /* for(PositionProcessUser u:users){
                    String approveUrl = sh.CreateResumeUrl(flowCode, u.getEmail());
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
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+nc.getNodeName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\"><a href=\""+approveUrl+"\" target=\"_blank\"><font color=\"#0088CC\">查看</font></a></span></font></td>"
                            + "</tr>");
                    
                    sb.append("</table></div>");
                    
                    MailMessage mailMessage = new MailMessage();
                    mailMessage.setTo(new String[]{u.getEmail()+"@xdf.cn"});
                    //mailMessage.setTo(new String[]{"chenyunsong@xdf.cn"});
                    mailMessage.setType("2");
                    mailMessage.setSubject("简历-"+rs.getName()+"-"+nc.getPositionName()+"-"+nc.getNodeName());
                    mailMessage.setMessage(sb.toString());
                    String companyId = flowCode.substring(2, flowCode.indexOf("-"));
                    mailSendRecordService.sendMail(companyId, mailMessage,null);
                }*/
            }
            //直接通过或者会签通过
            if((result.equals(HumanHuiqianListener.RESULT_QUICKPASS))||(result.equals(HumanHuiqianListener.RESULT_PASS) && nextNode.equals(HrPosition.ENDNODE))){
                flow.setCurrentType(1);
                flow.setApprovalResult(1);
                flow.setProcessEndTime(new Timestamp(System.currentTimeMillis()));
                //初始化入职管理表
                entryhandlerDao.initResumeEntry(flowCode);
                //修改简历状态
                resumeBaseDao.updateResumeStatus(flowCode,"4");
            }
            
            //不同意的
            if(result.equals(HumanHuiqianListener.RESULT_NOPASS)){
                flow.setLoseNode(taskKey);
                flow.setLoseTime(new Timestamp(System.currentTimeMillis()));
                flow.setCurrentType(1);
            }
            flowDao.updateByFlowCode(flow);
        }
    }
    
    public Map<String,Object> isComplete(ActivityExecution execution,PositionProcessNodeConfig signConfig) {
        //同属的数值
        int passNum = 1;
        //不通过的数值
        int refuseNum= 1;
        Map<String,Object> map = new HashMap<String,Object>();
        String taskId = execution.getActivity().getId();
        String perResult = (String) execution.getVariable("perResult");
        if("2".equals(perResult)){
            map.put("flag", true);
            map.put("vote", HumanHuiqianListener.RESULT_BACK);
            execution.getParent().setVariable("alreadyVote"+taskId,0);
            return map;
        }
        
        if("3".equals(perResult)){
            map.put("flag", true);
            map.put("vote", HumanHuiqianListener.RESULT_QUICKPASS);
            execution.getParent().setVariable("alreadyVote"+taskId,0);
            return map;
        }
        //获取已审批人数
        int completeCounter = ((Integer)execution.getVariable("nrOfCompletedInstances")).intValue();
        //获取总的面试官数量
        int nrOfInstances = ((Integer)execution.getVariable("nrOfInstances")).intValue();
        if(signConfig.getSignType()==0){
            passNum = signConfig.getSignQuantity();
        }else{
            passNum = (int) Math.ceil(nrOfInstances*signConfig.getSignQuantity()/100.0);
        }
        
        if(signConfig.getNopassSignType()==0){
            refuseNum = signConfig.getNopassSignQuantity();
        }else{
            refuseNum = (int) Math.ceil(nrOfInstances*signConfig.getNopassSignQuantity()/100.0);
        }
        
        int num = 0;
        if(execution.getParent().getVariable("alreadyVote"+taskId)==null){
            execution.getParent().setVariable("alreadyVote"+taskId,0);
        }else{
            num = (int) execution.getParent().getVariable("alreadyVote"+taskId);
        }
        
        
        
        if(perResult.equals(HumanHuiqianListener.RESULT_NOPASS)){
            if(completeCounter-num+1 >= refuseNum){
                execution.getParent().setVariable("alreadyVote"+taskId,0);
                map.put("flag", true);
                map.put("vote",HumanHuiqianListener.RESULT_NOPASS);
            }else{
                map.put("flag", false);
            }
        }
        if(perResult.equals(HumanHuiqianListener.RESULT_PASS)){
            if(num+1>=passNum){
                execution.getParent().setVariable("alreadyVote"+taskId,0);
                map.put("flag", true);
                map.put("vote", HumanHuiqianListener.RESULT_PASS);
            }else{
                execution.getParent().setVariable("alreadyVote"+taskId,num+1);
                map.put("flag", false);
            }
        }
        return map;
    }
}
