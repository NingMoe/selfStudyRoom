package com.human.resume.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.ActCustomPhoto;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.resume.entity.ActFlow;
import com.human.resume.entity.EditBase;
import com.human.resume.entity.ResumeBase;

public interface ResumeManagerService {

    Map<String, Object> updateResumeBasic(MultipartFile file1, ResumeBase rb);

    /**
     * 下载简历附件
     * @param id
     * @return
     */
   void dowEnclosure(Long id);

   /**
    * 更新头像
    * @param file1
    * @param rb
    * @return
    */
    Map<String, Object> editResumeHead(MultipartFile file1, ResumeBase rb);

    /**
     * 更新简历，包括所有简历详情
     * @param eb
     * @return
     */
    Map<String, Object> updateResumeBasicDetail(EditBase eb);

    /**
     * 根据flowCode获取打分项
     * @param flowCode
     * @return
     */
    List<PositionProcessScoreItem> getItemScoreByFlowCode(String flowCode);

    /**
     * 查询用户当前节点评论内容
     * @param localUser
     * @param nowNode
     * @param flowCode
     * @return
     */
    List<ActCustomComment> selectComment(ActCustomComment acc);

    /**
     * 根据简历ID获取沟通记录
     * @param resumeId
     * @return
     */
    List<ActFlow> getGtRecord(String resumeId);

    /**
     * 上传图片简历
     * @param file2
     * @param resumeId
     * @return
     */
    Map<String, Object> uploadResumePhoto(MultipartFile file2, Long resumeId);

    /**
     * 删除简历图片
     * @param id
     * @return
     */
    int delResumePhoto(Long id,String path);

    /**
     * 快船更新简历头像
     * @param id
     * @param fileType
     * @param base64String
     * @return
     */
    Map<String, Object> saveQuickPhoto(Long id,String fileType, String base64String);

    /**
     * 快速上传简历图片
     * @param resumeId
     * @param base64str
     * @return
     */
    Map<String, Object> saveBasePhoto(HttpServletRequest req ,Long resumeId);
    
    /**
     * 快船更新简历头像
     * @param id
     * @param fileType
     * @param base64String
     * @return
     */
    Map<String, Object> addPhotoFeedBack(Long id,String fileType, String base64String);

    
    Map<String, Object> saveActPhoto(HttpServletRequest req, ActCustomPhoto acp);

    int delActPhoto(Long id, String photoUrl);

}
