package com.human.recruitment.service;

import java.util.List;

import com.human.basic.entity.SmsRecord;
import com.human.bpm.entity.ActNode;
import com.human.recruitment.entity.HrPosition;
import com.human.recruitment.entity.PositionHrUser;
import com.human.recruitment.entity.PositionMsUser;
import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionWatcher;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeBase;
import com.human.utils.PageView;

public interface HrPositionService {
    PageView getPositionPage(PageView pageView,HrPosition hrPosition);
    
    List<HrPosition> getPositionList(HrPosition hrPosition); 
    
    List<HrPosition> getValidPositionList(HrPosition hrPosition); 
    
    void addHrPosition(HrPosition hrPosition);
    
    void editHrPosition(HrPosition hrPosition);
    
    HrPosition selectPostionDetailById(Integer id);
    
    List<PositionProcess> getProcessByPositionId(Integer positionId);
    
    HrPosition getPositionSuperConfig(Integer positionId);
    
    boolean isPositionUserExist(PositionHrUser hrUser);
    
    boolean isPositionWatcherExist(PositionWatcher watcher);
    
    boolean isPositionMsUserExist(PositionMsUser msUser);
    
    public void insertPositionHrUser(PositionHrUser hrUser);
    
    public int insertPositionWatcher(PositionWatcher watcher);
    
    public int insertPositionMsUser(PositionMsUser msUser);
    
    int delPositionMsUser(PositionMsUser msUser);
    
    int delPositionHrUser(PositionHrUser hrUser);
    
    int delPositionWatcher(PositionWatcher watcher);
    
    void editHrPositionSuperConfig(HrPosition hrPosition);
    
    List<HrPosition> getCachePositionList(String comid);
    
    List<HrPosition> getCachePositionListByCondition(HrPosition position);
    
    HrPosition getPositionForFrontById(Integer id);
    
    List<PositionMsUser> getPositionMsUsers(Integer positionId);
    
    Integer getFrontResumeCntByOpenId(String openId);
    
    List<ResumeBase> getFrontResumeByOpenId(String openId);
    
    Integer getFrontMsgCntByOpenId(String openId);
    
    ResumeSeeker getResumeSeekerByOpenId(String openId);
    
    List<ActNode> getNodesByResumeId(Integer resumeId);
    
    List<SmsRecord> getSmsByResumeId(Integer resumeId);
    
}
