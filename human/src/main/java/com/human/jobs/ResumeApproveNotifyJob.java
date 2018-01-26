package com.human.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.human.basic.entity.MailMessage;
import com.human.bpm.entity.ActEmailNotify;
import com.human.manager.service.MailSendRecordService;
import com.human.recruitment.dao.HrPositionDao;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.entity.PositionWatcher;
import com.human.utils.SecurityHelper;

/**
 * 每天同步组织机构及人员信息
 * @author HF-JAVA-02
 *
 */

@Component("resumeApproveNotifyJob")
public class ResumeApproveNotifyJob {
    
    private final Logger logger = LogManager.getLogger(ResumeApproveNotifyJob.class);
    
    @Autowired
    private HrResumeFlowDao flowDao;
    
    @Autowired
    private MailSendRecordService mailSendRecordService;
    
    @Autowired
    private HrPositionDao hrPositionDao;
    
    @Resource
    private SecurityHelper sh;
    
    
    public void resumeNotify(JobExecutionContext context) { 
        dealMsgMail();
        dealWatcherMail();
        dealCsMsgMail();
        dealCsHrMail();
        dealCsWatcherMail();
    }
    
    @SuppressWarnings({"all" })
    private void dealMsgMail(){
        logger.info("简历审批提醒（email）开始");
        List<ActEmailNotify> list = flowDao.getFlowDb();
        /**
         * 对集合进行分组
         */
        if(list==null|| list.size()==0){
            return;
        }
        Map<String,List<ActEmailNotify>> map = new HashMap<String,List<ActEmailNotify>>();
        for(ActEmailNotify n:list){
            Object o = map.get(n.getEmail());
            if(o==null){
                List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                notifys.add(n);
                map.put(n.getEmail(),notifys);
            }else{
                List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                notifys.add(n);
                map.put(n.getEmail(),notifys);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String email = (String) entry.getKey();
            Map<String,List<ActEmailNotify>> postionMap = new HashMap<String,List<ActEmailNotify>>();
            List<ActEmailNotify> flows = (List<ActEmailNotify>) entry.getValue();
            for(ActEmailNotify n:flows){
                Object o = postionMap.get(n.getPositionName());
                if(o==null){
                    List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }else{
                    List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }
            }
            Iterator positionIter = postionMap.entrySet().iterator();
            while (positionIter.hasNext()) {
                Map.Entry positionEntry = (Map.Entry) positionIter.next();
                String positionName = (String) positionEntry.getKey();
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{email+"@xdf.cn"});
                mailMessage.setSubject("【明天有面试安排】"+positionName+"有简历即将面试，请提前审阅");
                List<ActEmailNotify> dbs = (List<ActEmailNotify>) positionEntry.getValue();
                int num = dbs.size();
                String msg = dbs.get(0).getAppname();
                Integer positionId = Integer.valueOf(dbs.get(0).getPositionId());
                List<PositionWatcher> watchers = hrPositionDao.getPositionWatchers(positionId);
                
                StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
                sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+msg+"老师：您好！"+positionName+"岗位有"+num+"份简历需要您处理,求职者名单如下<span style=\"font-size:14px;color:red;\">（查看简历后请填写面试反馈）</span>:</font></div>");
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
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">面试时间</span></font></td>"
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">简历详情</span></font></td>"
                        + "</tr>");
                int i=1;
                for(ActEmailNotify u:dbs){
                    String major = u.getMajor();
                    if(StringUtils.isNotEmpty(major)){
                        major = major.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        major = "";
                    }
                    
                    String highEdu = u.getHighEdu();
                    if(StringUtils.isNotEmpty(highEdu)){
                        highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        highEdu = "";
                    }
                    
                    String approveUrl = sh.CreateResumeUrl(u.getFlowCode(), u.getEmail());
                    String tr = "<tr style=\"height:50px;\">";
                    if(i%2==1){
                        tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
                    }
                    sb.append(tr
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getSeekerName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getPhone()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getSex())?"":u.getSex())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getBirthDate())?"":u.getBirthDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraSchool())?"":u.getGraSchool())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraduationDate())?"":u.getGraduationDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getNodeName())?"":u.getNodeName())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getInterviewTime())?"":u.getInterviewTime())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\"><a href=\""+approveUrl+"\" target=\"_blank\"><font color=\"#0088CC\">查看</font></a></span></font></td>"
                            + "</tr>");
                    i++;
                }
                sb.append("</table></div>");
                mailMessage.setMessage(sb.toString());
                System.out.println(sb.toString());
                mailSendRecordService.sendMail("128", mailMessage,null);
            }
        }
        logger.info("简历流转提醒（email）结束");
    }
    
    @SuppressWarnings({"all" })
    private void dealWatcherMail(){
        logger.info("简历审批提醒（email）开始");
        List<ActEmailNotify> list = flowDao.getWatcherFlowDb();
        /**
         * 对集合进行分组
         */
        if(list==null|| list.size()==0){
            return;
        }
        Map<String,List<ActEmailNotify>> map = new HashMap<String,List<ActEmailNotify>>();
        for(ActEmailNotify n:list){
            Object o = map.get(n.getWatcherId());
            if(o==null){
                List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                notifys.add(n);
                map.put(n.getWatcherId(),notifys);
            }else{
                List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                notifys.add(n);
                map.put(n.getWatcherId(),notifys);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String watcherId = (String) entry.getKey();
            Map<String,List<ActEmailNotify>> postionMap = new HashMap<String,List<ActEmailNotify>>();
            List<ActEmailNotify> flows = (List<ActEmailNotify>) entry.getValue();
            for(ActEmailNotify n:flows){
                Object o = postionMap.get(n.getPositionName());
                if(o==null){
                    List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }else{
                    List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }
            }
            Iterator positionIter = postionMap.entrySet().iterator();
            while (positionIter.hasNext()) {
                Map.Entry positionEntry = (Map.Entry) positionIter.next();
                String positionName = (String) positionEntry.getKey();
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{watcherId+"@xdf.cn"});
                mailMessage.setSubject("【未安排面试提醒】"+positionName+"有面试简历未处理");
                List<ActEmailNotify> dbs = (List<ActEmailNotify>) positionEntry.getValue();
                int num = dbs.size();
                String msg = dbs.get(0).getWatcherName();
                
                StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
                sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+msg+"老师：您好！"+positionName+"岗位有"+num+"份简历需要您处理,求职者名单如下<span style=\"font-size:14px;color:red;\">（安排好面试时间后，请通知面试官）</span>:</font></div>");
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
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">面试时间</span></font></td>"
                        + "</tr>");
                int i=1;
                for(ActEmailNotify u:dbs){
                    String major = u.getMajor();
                    if(StringUtils.isNotEmpty(major)){
                        major = major.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        major = "";
                    }
                    
                    String highEdu = u.getHighEdu();
                    if(StringUtils.isNotEmpty(highEdu)){
                        highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        highEdu = "";
                    }
                    
                    /*String approveUrl = sh.CreateResumeUrl(u.getFlowCode(), u.getEmail());*/
                    String tr = "<tr style=\"height:50px;\">";
                    if(i%2==1){
                        tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
                    }
                    sb.append(tr
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getSeekerName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getPhone()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getSex())?"":u.getSex())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getBirthDate())?"":u.getBirthDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraSchool())?"":u.getGraSchool())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraduationDate())?"":u.getGraduationDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getNodeName())?"":u.getNodeName())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getInterviewTime())?"":u.getInterviewTime())+"</span></font></td>"
                            + "</tr>");
                    i++;
                }
                sb.append("</table></div>");
                mailMessage.setMessage(sb.toString());
                System.out.println(sb.toString());
                mailSendRecordService.sendMail("128", mailMessage,null);
            }
        }
        logger.info("简历流转提醒（email）结束");
    }
    
    private void dealCsMsgMail(){
        logger.info("简历审批提醒（email）开始");
        List<ActEmailNotify> list = flowDao.getCsMsgDb();
        /**
         * 对集合进行分组
         */
        if(list==null|| list.size()==0){
            return;
        }
        Map<String,List<ActEmailNotify>> map = new HashMap<String,List<ActEmailNotify>>();
        for(ActEmailNotify n:list){
            Object o = map.get(n.getEmail());
            if(o==null){
                List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                notifys.add(n);
                map.put(n.getEmail(),notifys);
            }else{
                List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                notifys.add(n);
                map.put(n.getEmail(),notifys);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String email = (String) entry.getKey();
            Map<String,List<ActEmailNotify>> postionMap = new HashMap<String,List<ActEmailNotify>>();
            List<ActEmailNotify> flows = (List<ActEmailNotify>) entry.getValue();
            for(ActEmailNotify n:flows){
                Object o = postionMap.get(n.getPositionName());
                if(o==null){
                    List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }else{
                    List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }
            }
            Iterator positionIter = postionMap.entrySet().iterator();
            while (positionIter.hasNext()) {
                Map.Entry positionEntry = (Map.Entry) positionIter.next();
                String positionName = (String) positionEntry.getKey();
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{email+"@xdf.cn"});
                mailMessage.setSubject("【简历超时审批提醒】您负责面试的"+positionName+"有简历已超过2天未处理");
                List<ActEmailNotify> dbs = (List<ActEmailNotify>) positionEntry.getValue();
                int num = dbs.size();
                String msg = dbs.get(0).getAppname();
                Integer positionId = Integer.valueOf(dbs.get(0).getPositionId());
                StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
                sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+msg+"老师：您好！"+positionName+"岗位有"+num+"份简历已超时,求职者名单如下<span style=\"font-size:14px;color:red;\">（查看简历后请填写面试反馈）</span>:</font></div>");
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
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">面试时间</span></font></td>"
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">简历详情</span></font></td>"
                        + "</tr>");
                int i=1;
                for(ActEmailNotify u:dbs){
                    String major = u.getMajor();
                    if(StringUtils.isNotEmpty(major)){
                        major = major.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        major = "";
                    }
                    
                    String highEdu = u.getHighEdu();
                    if(StringUtils.isNotEmpty(highEdu)){
                        highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        highEdu = "";
                    }
                    
                    String approveUrl = sh.CreateResumeUrl(u.getFlowCode(), u.getEmail());
                    String tr = "<tr style=\"height:50px;\">";
                    if(i%2==1){
                        tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
                    }
                    sb.append(tr
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getSeekerName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getPhone()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getSex())?"":u.getSex())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getBirthDate())?"":u.getBirthDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraSchool())?"":u.getGraSchool())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraduationDate())?"":u.getGraduationDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getNodeName())?"":u.getNodeName())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getInterviewTime())?"":u.getInterviewTime())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\"><a href=\""+approveUrl+"\" target=\"_blank\"><font color=\"#0088CC\">查看</font></a></span></font></td>"
                            + "</tr>");
                    i++;
                }
                sb.append("</table></div>");
                mailMessage.setMessage(sb.toString());
                System.out.println(sb.toString());
                mailSendRecordService.sendMail("128", mailMessage,null);
            }
        }
        logger.info("简历流转提醒（email）结束");
    }
    
    
    private void dealCsWatcherMail(){
        logger.info("简历审批提醒（email）开始");
        List<ActEmailNotify> list = flowDao.getCsWatcherDb();
        /**
         * 对集合进行分组
         */
        if(list==null|| list.size()==0){
            return;
        }
        Map<String,List<ActEmailNotify>> map = new HashMap<String,List<ActEmailNotify>>();
        for(ActEmailNotify n:list){
            Object o = map.get(n.getWatcherId());
            if(o==null){
                List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                notifys.add(n);
                map.put(n.getWatcherId(),notifys);
            }else{
                List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                notifys.add(n);
                map.put(n.getWatcherId(),notifys);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String watcherId = (String) entry.getKey();
            Map<String,List<ActEmailNotify>> postionMap = new HashMap<String,List<ActEmailNotify>>();
            List<ActEmailNotify> flows = (List<ActEmailNotify>) entry.getValue();
            for(ActEmailNotify n:flows){
                Object o = postionMap.get(n.getPositionName());
                if(o==null){
                    List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }else{
                    List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }
            }
            Iterator positionIter = postionMap.entrySet().iterator();
            while (positionIter.hasNext()) {
                Map.Entry positionEntry = (Map.Entry) positionIter.next();
                String positionName = (String) positionEntry.getKey();
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{watcherId+"@xdf.cn"});
                mailMessage.setSubject("【简历超时审批提醒】您负责面试的"+positionName+"有简历已超过2天未处理");
                List<ActEmailNotify> dbs = (List<ActEmailNotify>) positionEntry.getValue();
                int num = dbs.size();
                String msg = dbs.get(0).getWatcherName();
                
                StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
                sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+msg+"老师：您好！"+positionName+"岗位有"+num+"份简历已超时,求职者名单如下<span style=\"font-size:14px;color:red;\">（查看简历后请通知面试官填写面试反馈）</span>:</font></div>");
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
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">面试时间</span></font></td>"
                        + "</tr>");
                int i=1;
                for(ActEmailNotify u:dbs){
                    String major = u.getMajor();
                    if(StringUtils.isNotEmpty(major)){
                        major = major.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        major = "";
                    }
                    
                    String highEdu = u.getHighEdu();
                    if(StringUtils.isNotEmpty(highEdu)){
                        highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        highEdu = "";
                    }
                    
                    String tr = "<tr style=\"height:50px;\">";
                    if(i%2==1){
                        tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
                    }
                    sb.append(tr
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getSeekerName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getPhone()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getSex())?"":u.getSex())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getBirthDate())?"":u.getBirthDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraSchool())?"":u.getGraSchool())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraduationDate())?"":u.getGraduationDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getNodeName())?"":u.getNodeName())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getInterviewTime())?"":u.getInterviewTime())+"</span></font></td>"
                            + "</tr>");
                    i++;
                }
                sb.append("</table></div>");
                mailMessage.setMessage(sb.toString());
                System.out.println(sb.toString());
                mailSendRecordService.sendMail("128", mailMessage,null);
            }
        }
        logger.info("简历流转提醒（email）结束");
    }
    
    
    private void dealCsHrMail(){
        logger.info("简历审批提醒（email）开始");
        List<ActEmailNotify> list = flowDao.getCsHrDb();
        /**
         * 对集合进行分组
         */
        if(list==null|| list.size()==0){
            return;
        }
        Map<String,List<ActEmailNotify>> map = new HashMap<String,List<ActEmailNotify>>();
        for(ActEmailNotify n:list){
            Object o = map.get(n.getHrUserId());
            if(o==null){
                List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                notifys.add(n);
                map.put(n.getHrUserId(),notifys);
            }else{
                List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                notifys.add(n);
                map.put(n.getHrUserId(),notifys);
            }
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String hrUserId = (String) entry.getKey();
            Map<String,List<ActEmailNotify>> postionMap = new HashMap<String,List<ActEmailNotify>>();
            List<ActEmailNotify> flows = (List<ActEmailNotify>) entry.getValue();
            for(ActEmailNotify n:flows){
                Object o = postionMap.get(n.getPositionName());
                if(o==null){
                    List<ActEmailNotify> notifys = new ArrayList<ActEmailNotify>();
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }else{
                    List<ActEmailNotify> notifys = (List<ActEmailNotify>)o;
                    notifys.add(n);
                    postionMap.put(n.getPositionName(),notifys);
                }
            }
            Iterator positionIter = postionMap.entrySet().iterator();
            while (positionIter.hasNext()) {
                Map.Entry positionEntry = (Map.Entry) positionIter.next();
                String positionName = (String) positionEntry.getKey();
                MailMessage mailMessage = new MailMessage();
                mailMessage.setTo(new String[]{hrUserId+"@xdf.cn"});
                mailMessage.setSubject("【简历超时审批提醒】您负责面试的"+positionName+"有简历已超过2天未处理");
                List<ActEmailNotify> dbs = (List<ActEmailNotify>) positionEntry.getValue();
                int num = dbs.size();
                String msg = dbs.get(0).getHrUserName();
                
                StringBuffer sb = new StringBuffer("<div align=\"left\" style=\"width:auto;text-align:justify;\">");
                sb.append("<div style=\"margin-top:0;margin-bottom:0;\"><font size=\"2\">"+msg+"老师：您好！"+positionName+"岗位有"+num+"份简历已超时,求职者名单如下<span style=\"font-size:14px;color:red;\">（查看简历后请通知面试官填写面试反馈）</span>:</font></div>");
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
                        + "<td style=\"padding-top:5px;padding-bottom:5px;padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">面试时间</span></font></td>"
                        + "</tr>");
                int i=1;
                for(ActEmailNotify u:dbs){
                    String major = u.getMajor();
                    if(StringUtils.isNotEmpty(major)){
                        major = major.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        major = "";
                    }
                    
                    String highEdu = u.getHighEdu();
                    if(StringUtils.isNotEmpty(highEdu)){
                        highEdu = highEdu.replaceAll("[^\u4e00-\u9fa5]", "");
                    }else{
                        highEdu = "";
                    }
                    
                    String tr = "<tr style=\"height:50px;\">";
                    if(i%2==1){
                        tr = "<tr style=\"height:50px;background-color:#EEF7FF;\">";
                    }
                    sb.append(tr
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getSeekerName()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+u.getPhone()+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getSex())?"":u.getSex())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getBirthDate())?"":u.getBirthDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraSchool())?"":u.getGraSchool())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+major+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+highEdu+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getGraduationDate())?"":u.getGraduationDate())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getNodeName())?"":u.getNodeName())+"</span></font></td>"
                            + "<td style=\"padding-left:10px;\"><font size=\"2\"><span style=\"font-size:14px;\">"+(StringUtils.isEmpty(u.getInterviewTime())?"":u.getInterviewTime())+"</span></font></td>"
                            + "</tr>");
                    i++;
                }
                sb.append("</table></div>");
                mailMessage.setMessage(sb.toString());
                System.out.println(sb.toString());
                mailSendRecordService.sendMail("128", mailMessage,null);
            }
        }
        logger.info("简历流转提醒（email）结束");
    }
}
