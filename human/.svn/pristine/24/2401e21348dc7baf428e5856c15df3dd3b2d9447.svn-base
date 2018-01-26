package com.human.recruitment.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.bpm.dao.ActCustomCommentDao;
import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.service.HrWorkflowService;
import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.ResumeTalentDao;
import com.human.recruitment.entity.EliminationResult;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.ResumeTalent;
import com.human.recruitment.service.EliminationService;
import com.human.resume.dao.ResumeBaseDao;
import com.human.resume.dao.ResumeOperRecordDao;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeOperRecord;
import com.human.utils.Common;

@Service
public class EliminationServiceImpl implements EliminationService {
    
    @Autowired
    private HrResumeFlowDao flowDao;
    
    @Autowired
    private ResumeBaseDao resumeBaseDao;
    
    @Autowired
    private ResumeTalentDao talentDao;
    
    @Autowired
    private ResumeOperRecordDao operRecordDao;
    
    @Autowired
    private ActCustomCommentDao commentDao;
    
    @Autowired
    private HrWorkflowService hrWorkflowService;
    
    
    

    @Override
    @Transactional
    public void dealElimination(EliminationResult eliminationResult) {
        String isRencai = eliminationResult.getIsRencai();
        String flowCode = eliminationResult.getFlowCode();
        Integer currentType = null;
        Integer operType = null;
        String flowStatus = "";
        if("0".equals(isRencai)){
            currentType = 2;
            operType = 1;
            flowStatus = "2";
        }else{
            currentType = 3;
            operType = 2;
            flowStatus = "3";
            ResumeTalent talent = new ResumeTalent();
            talent.setResumeId(Integer.valueOf(eliminationResult.getResumeId()));
            talent.setDept(eliminationResult.getDept());
            talent.setPosition(eliminationResult.getPosition());
            talent.setCreateUser(Common.getAuthenticatedUsername());
            talent.setCreateTime(new Date());
            talentDao.insertOrUpdate(talent);
        }
        //更新流转单状态
        ActCustomComment c = commentDao.getLoseComment(flowCode);
        HrResumeFlow flow = new HrResumeFlow();
        flow.setId(Integer.valueOf(eliminationResult.getFlowId()));
        flow.setCurrentType(currentType);
        flow.setApprovalResult(0);
        //flow.setLoseNode(c.getNodeId());
        flow.setLoseNodeName(c.getNodeName());
        flow.setLoseCause(c.getBackReason());
        //flow.setLoseTime(c.getApproveTime());
        flow.setCurrentNode(HrPosition.ENDNODE);
        flow.setProcessEndTime(new Timestamp(System.currentTimeMillis()));
        flowDao.updateByPrimaryKeySelective(flow);
        
        //更新简历表状态
        ResumeBase resumeBase = new ResumeBase();
        resumeBase.setId(Long.valueOf(eliminationResult.getResumeId()));
        resumeBase.setFlowStatus(flowStatus);
        resumeBaseDao.updateByPrimaryKeySelective(resumeBase);
        
        //记录简历操作记录
        ResumeOperRecord bor = new ResumeOperRecord();
        bor.setOper(Common.getAuthenticatedUsername());
        bor.setOperTime(new Date());
        bor.setOperType(operType);
        bor.setResumeId(Integer.valueOf(eliminationResult.getResumeId()));
        bor.setRemark(eliminationResult.getRemark());
        operRecordDao.insert(bor);
        
        //记录审批意见
        ActCustomComment comment = new ActCustomComment();
        comment.setApprover(Common.getAuthenticatedUsername());
        comment.setApproveTime(new Timestamp(System.currentTimeMillis()));
        String content = StringUtils.isEmpty(eliminationResult.getRemark())?(isRencai.equals("0")?"淘汰":"加入人才库"):eliminationResult.getRemark();
        comment.setComment(content);
        comment.setFlowCode(eliminationResult.getFlowCode());
        comment.setNodeId("rlqrTask");
        comment.setResult(1);
        commentDao.insert(comment);
        
        //流程提交
        hrWorkflowService.tjResumeFlow(eliminationResult.getFlowCode());
    }
    
    
    @Override
    @Transactional
    public void dealChangePosition(InterviewSchedule interviewSchedule) {
        String flowCode = interviewSchedule.getFlowCode();
        //更新流转单状态
        HrResumeFlow flow = new HrResumeFlow();
        ActCustomComment c = commentDao.getLoseComment(flowCode);
        flow.setId(Integer.valueOf(interviewSchedule.getFlowId()));
        flow.setCurrentType(4);
        flow.setApprovalResult(0);
        //flow.setLoseNode(c.getNodeId());
        flow.setLoseNodeName(c.getNodeName());
        flow.setLoseCause(c.getBackReason());
        flow.setCurrentNode(HrPosition.ENDNODE);
        //flow.setLoseTime(c.getApproveTime());
        flow.setProcessEndTime(new Timestamp(System.currentTimeMillis()));
        flowDao.updateByPrimaryKeySelective(flow);
        
        //更新简历表状态
        ResumeBase resumeBase = new ResumeBase();
        resumeBase.setId(Long.valueOf(interviewSchedule.getResumeId()));
        resumeBase.setMatchingPosition(interviewSchedule.getPositionId());
        resumeBaseDao.updateByPrimaryKeySelective(resumeBase);
        
        //记录简历操作记录
        ResumeOperRecord bor = new ResumeOperRecord();
        bor.setOper(Common.getAuthenticatedUsername());
        bor.setOperTime(new Date());
        bor.setOperType(3);
        bor.setResumeId(Integer.valueOf(interviewSchedule.getResumeId()));
        bor.setRemark("更换流程提交");
        operRecordDao.insert(bor);
        
       /* ActCustomComment comment = new ActCustomComment();
        comment.setApprover(Common.getAuthenticatedUsername());
        comment.setApproveTime(new Date());
        comment.setComment("更换流程提交");
        comment.setFlowCode(interviewSchedule.getFlowCode());
        commentDao.insert(comment);*/
        //提交流程
        hrWorkflowService.tjResumeFlow(interviewSchedule.getFlowCode());
        
    }
    
    @Override
    public void updateResumeflow(HrResumeFlow resumeFlow) {
        flowDao.updateByFlowCodeSelective(resumeFlow);
    }


    @Override
    public void eliminationResume(EliminationResult eliminationResult) {
        String isRencai = eliminationResult.getIsRencai();
        Integer operType = null;
        String flowStatus = "";        
        if("0".equals(isRencai)){
            flowStatus = "2";
            operType = 1;
        }else{
            flowStatus = "3";
            operType = 2;
            ResumeTalent talent = new ResumeTalent();
            talent.setResumeId(Integer.valueOf(eliminationResult.getResumeId()));
            talent.setDept(eliminationResult.getDept());
            talent.setPosition(eliminationResult.getPosition());
            talent.setCreateUser(Common.getAuthenticatedUsername());
            talent.setCreateTime(new Date());
            talentDao.insertOrUpdate(talent);
        }        
        //更新简历表状态
        ResumeBase resumeBase = new ResumeBase();
        resumeBase.setId(Long.valueOf(eliminationResult.getResumeId()));
        resumeBase.setFlowStatus(flowStatus);
        resumeBaseDao.updateByPrimaryKeySelective(resumeBase);        
        //记录简历操作记录
        ResumeOperRecord bor = new ResumeOperRecord();
        bor.setOper(Common.getAuthenticatedUsername());
        bor.setOperTime(new Date());
        bor.setOperType(operType);
        bor.setResumeId(Integer.valueOf(eliminationResult.getResumeId()));
        bor.setRemark(eliminationResult.getRemark());
        operRecordDao.insert(bor);        
    }


    @Override
    public void eliminationResume(EliminationResult eliminationResult, int type) {
        String  resumeId=eliminationResult.getResumeId();
        Integer operType = null;
        String flowStatus = "";
        if(type==1){//流程中的淘汰
            operType = 1;
            flowStatus = "2";
            HrResumeFlow hrf=flowDao.selectHrfByResumeId(Integer.valueOf(resumeId));
            String processInstanceId =hrf.getProcessInstanceId();
            String delReason=eliminationResult.getRemark();
            String flowCode = hrf.getFlowCode();
            //删除当前流程
            hrWorkflowService.delProcess(flowCode,processInstanceId, delReason);
        }
        if(type==2){//加入人才库中
            operType = 2;
            flowStatus = "3";
            talentDao.deleteByResumeId(Integer.valueOf(resumeId));
            ResumeTalent talent = new ResumeTalent();
            talent.setResumeId(Integer.valueOf(eliminationResult.getResumeId()));
            talent.setDept(eliminationResult.getDept());
            talent.setPosition(eliminationResult.getPosition());
            talent.setCreateUser(Common.getAuthenticatedUsername());
            talent.setCreateTime(new Date());
            talentDao.insertOrUpdate(talent);
        }
        if(type==3){//人才库中的淘汰
            operType = 1;
            flowStatus = "2";
            talentDao.deleteByResumeId(Integer.valueOf(resumeId));
        }
        //更新简历表状态
        ResumeBase resumeBase = new ResumeBase();
        resumeBase.setId(Long.valueOf(resumeId));
        resumeBase.setFlowStatus(flowStatus);
        resumeBaseDao.updateByPrimaryKeySelective(resumeBase);
        
        //记录简历操作记录
        ResumeOperRecord bor = new ResumeOperRecord();
        bor.setOper(Common.getAuthenticatedUsername());
        bor.setOperTime(new Date());
        bor.setOperType(operType);
        bor.setResumeId(Integer.valueOf(resumeId));
        bor.setRemark(eliminationResult.getRemark());
        operRecordDao.insert(bor);
        
        
        
    }

}
