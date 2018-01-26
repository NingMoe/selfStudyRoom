package com.human.resume.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.human.resume.entity.ResumePhoto;


public interface ResumePhotoService {
    
    List<ResumePhoto> selectByResumeId(Long resumeId);
    
    /**
     * 我的简历中图片简历的保存
     * @param files
     * @param request
     * @param response
     * @return
     */
    Map<String,Object> insertResumePhoto(MultipartFile[] files, HttpServletRequest request,HttpServletResponse response);
    
    ResumePhoto selectByPrimaryKey(Long id);
    
    int insertSelective(ResumePhoto record);
    
    
    /**
     * 查询快速投递设置的图片简历
     * @param resumeId
     * @return
     */
    List<ResumePhoto> selectFastRp(Long resumeId);
    
    /**
     * 快速投递简历中图片简历的保存
     * @param files
     * @param request
     * @param response
     * @return
     */
    Map<String,Object> uploadReumsePhoto(MultipartFile[] files, HttpServletRequest request,HttpServletResponse response);
        
}
