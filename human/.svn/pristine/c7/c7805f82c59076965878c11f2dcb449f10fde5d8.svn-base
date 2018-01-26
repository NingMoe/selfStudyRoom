package com.human.resume.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.bpm.entity.ActCustomComment;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.resume.entity.ActFlow;
import com.human.resume.entity.EditBase;
import com.human.resume.entity.InterAppr;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;

@Repository
public interface ResumeManagerDao {
    
    /**
     * 更新简历基础表
     * */
    int updateBaseByKey(EditBase eb);
    
    /**
     * 更新求职意向
     * @param ri
     * @return
     */
    int editIntentionByKey(ResumeIntention ri);

    /**
     * 更新专业技能
     * @param ms
     * @return
     */
    int editMajorByKey(ResumeMajorSkill ms);

    /**
     * 更新语言能力
     * @param ms
     * @return
     */
    int editLanguageByKey(ResumeLanguage rl);

    /**
     * 更新工作经历
     * @param wh
     * @return
     */
    int editWorkHistoryKey(ResumeWorkHistory wh);

    /**
     * 更新教育经历
     * @param reh
     * @return
     */
    int editEducationHistoryKey(ResumeEducationHistory reh);

    /**
     * 更新校内职务记录
     * @param rsp
     * @return
     */
    int editSchoolPosKey(ResumeSchoolPost rsp);

    /**
     * 更新培训经历
     * @param rth
     * @return
     */
    int editTrainHistoryKey(ResumeTrainHistory rth);

    /**
     * 更新项目经验
     * @param projectExp
     * @return
     */
    int editProjectHistoryKey(ResumeProjectExperience projectExp);

    /**
     * 更新实践经验
     * @param practiceExp
     * @return
     */
    int editPracticeHistoryKey(ResumePracticeExperience practiceExp);
    
    /**
     * 根据flowCode获取打分项
     * @param flowCode
     * @return
     */
    List<PositionProcessScoreItem> getItemScoreByFlowCode(String flowCode);

    /**
     * 获取评论记录
     * @param acc
     * @return
     */
    List<ActCustomComment> selectComment(ActCustomComment acc);

    /**
     * 获取简历沟通记录
     * @param resumeId
     * @return
     */
    List<ActFlow> getGtRecord(String resumeId);

    /**
     * 获取待审批人
     * @param map
     * @return
     */
    List<InterAppr> queryToAppr(Map<String, Object> map);

    /**
     * 获取已审批人
     * @param map
     * @return
     */
    List<InterAppr> queryEndAppr(Map<String, Object> map);
}
