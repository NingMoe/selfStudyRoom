package com.human.recruitment.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.human.recruitment.entity.CommunicationDesc;
import com.human.recruitment.entity.CommunicationRecord;
import com.human.recruitment.entity.CommunicationSms;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.InterAppr;
import com.human.resume.entity.ResumeBase;
import com.human.utils.PageView;

public interface RecruitAcceptanceService {

    List<ResumeSeeker> queryResumeSeekerList(ResumeSeeker rs);

    /**
     * 创建应聘人员
     * @param rs
     */
    Map<String, Object> saveResumeSeeker(MultipartFile file1, ResumeSeeker rs);

    /**
     * 获取简历投递记录
     * @param rs
     */
     List<ResumeBase> jlQuery(ResumeSeeker rs);

     /**
      * 获取人员沟通记录
      * @param rs
      */
    List<CommunicationRecord> linkQuery(CommunicationRecord cr);

    /**
     * 获取人员短信记录
     * @param rs
     * @return
     */
    List<CommunicationSms> smsQuery(ResumeSeeker rs);

    /**
     * 快速新增沟通记录
     * @param cd
     * @return
     */
    Map<String, Object> quickAdd(CommunicationRecord cr, CommunicationDesc cd);

    /**
     * 更新应聘人员
     * @param rs
     */
    Map<String, Object> updateResumeSeeker(MultipartFile file1, ResumeSeeker rs);

    PageView toLinkQuery(PageView pageView, ResumeSeeker rs);

    /**
     * 通过微信openId查询应聘者
     * @param openId
     * @return
     */
    ResumeSeeker selectByOpenId(String openId);
    
    ResumeSeeker selectByPrimaryKey(Long id);
    
    /**
     * 前台更新我的简历个人信息
     * @param rs
     */
    Map<String, Object> updateMyResumeBase(HttpServletRequest request, ResumeBase rb);
    
    /**
     * 前台更新我的简历联系手机号
     * @param rs
     */
    Map<String, Object>updatePhone(String msg, ResumeBase rb);
    
    /**
     * 解除前台微信绑定
     * @param resumeSeekerId
     * @return
     */
    Map<String, Object>removeBinding(Long id); 
    
    
}
