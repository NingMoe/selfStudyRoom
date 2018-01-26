package com.human.recruitment.service;

import com.human.recruitment.entity.EliminationResult;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;

public interface EliminationService {
    public void dealElimination(EliminationResult eliminationResult);
    
    public void dealChangePosition(InterviewSchedule interviewSchedule);
    
    public void updateResumeflow(HrResumeFlow resumeFlow);
    /**
     * 直接淘汰简历
     * author  liuwei
     * @param eliminationResult
     */
    void eliminationResume(EliminationResult eliminationResult);
    
    /**
     * 淘汰简历
     * author  liuwei
     * @param eliminationResult
     * @param type
     */
    void eliminationResume(EliminationResult eliminationResult,int type);
}
