package com.human.utils.mailUtils;

import java.util.ArrayList;
import java.util.List;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeCertificate;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;

public class EncapsulationResumeUtil {
    
    /**
     * 封装简历所有实体
     * @return
     */
    public static List<Object> getResumeObject(){
        List<Object>list=new ArrayList<Object>(16);
        ResumeSeeker resumeSeeker= new ResumeSeeker();
        ResumeBase resumeBase=new ResumeBase();
        ResumeIntention resumeIntention=new ResumeIntention();
        List<ResumeAttachment> resumeAttachmentList=new ArrayList<ResumeAttachment>();
        List<ResumeEducationHistory>resumeEducationHistoryList=new ArrayList<ResumeEducationHistory>();        
        List<ResumeLanguage> resumeLanguageList=new ArrayList<ResumeLanguage>();
        List<ResumeMajorSkill> resumeMajorSkillList=new ArrayList<ResumeMajorSkill>();
        List<ResumePracticeExperience> resumePracticeExperienceList=new ArrayList<ResumePracticeExperience>();
        List<ResumePrize> resumePrizeList=new ArrayList<ResumePrize>();
        List<ResumeProjectExperience> resumeProjectExperienceList=new ArrayList<ResumeProjectExperience>();
        List<ResumeSchoolPost> resumeSchoolPostList=new ArrayList<ResumeSchoolPost>();
        List<ResumeTrainHistory> resumeTrainHistoryList=new ArrayList<ResumeTrainHistory>();
        List<ResumeWorkHistory>resumeWorkHistoryList=new ArrayList<ResumeWorkHistory>();
        List<ResumeCertificate>resumeCertificateList=new ArrayList<ResumeCertificate>();
        list.add(0, resumeSeeker);
        list.add(1, resumeBase);
        list.add(2, resumeAttachmentList);
        list.add(3, resumeEducationHistoryList);
        list.add(4, resumeIntention);
        list.add(5, resumeLanguageList);
        list.add(6, resumeMajorSkillList);
        list.add(7, resumePracticeExperienceList);
        list.add(8, resumePrizeList);
        list.add(9, resumeProjectExperienceList);
        list.add(10, resumeSchoolPostList);
        list.add(11, resumeTrainHistoryList);
        list.add(12, resumeWorkHistoryList);
        list.add(13, resumeCertificateList);
        return list;
    }
}
