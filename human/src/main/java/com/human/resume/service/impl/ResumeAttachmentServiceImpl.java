package com.human.resume.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.resume.dao.ResumeAttachmentDao;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.service.ResumeAttachmentService;

@Service
public class ResumeAttachmentServiceImpl implements ResumeAttachmentService {
    
    @Resource
    private ResumeAttachmentDao raDao;
    
    public List<ResumeAttachment> queryRa(ResumeAttachment ra){
       return  raDao.queryRa(ra);
    }

}
