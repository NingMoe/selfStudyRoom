package com.human.ielts.entity;

import java.util.Date;
import java.util.List;

public class IeltsTeacherInfo {
    private Integer id;

    private String teacher_name;

    private String teacher_mail;

    private String email_addr;

    private String empl_id;
    
    private Double tkt_scoreone;
    
    private Double tkt_scoretwo;
    
    private Double tkt_scorethree;
    
    private Double tkt_scorefour;
    
    private Double ielts_source;
    
    private Integer is_teacher_certificate;
    
    private Integer is_celta;
    
    private Integer abb_num;
    
    private Integer duty_num;
    
    private Integer article_num;
    
    private Integer share_num;
    
    private Integer operate_num;
    
    private Integer complaint_num;
    
    private Integer feedback_num;
    
    private List<IeltsTeacherFeedback> feedbacklist;
    
    private List<IeltsTeacherMatchclass> matchclasslist;
    
    private List<IeltsStudentTeacher> stlist;
    
    private Date left_integral_time;
    
    private Date right_integral_time;
    
    private Integer enroll_num;
    
    private Integer not_enroll_num;
    
    private Integer achieve_num;
    
    private Integer not_achieve_num;
    
    private Integer hight_num;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name == null ? null : teacher_name.trim();
    }

    public String getTeacher_mail() {
        return teacher_mail;
    }

    public void setTeacher_mail(String teacher_mail) {
        this.teacher_mail = teacher_mail == null ? null : teacher_mail.trim();
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr == null ? null : email_addr.trim();
    }

    public String getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(String empl_id) {
        this.empl_id = empl_id == null ? null : empl_id.trim();
    }

    public Double getTkt_scoreone() {
        return tkt_scoreone;
    }

    public void setTkt_scoreone(Double tkt_scoreone) {
        this.tkt_scoreone = tkt_scoreone;
    }

    public Double getTkt_scoretwo() {
        return tkt_scoretwo;
    }

    public void setTkt_scoretwo(Double tkt_scoretwo) {
        this.tkt_scoretwo = tkt_scoretwo;
    }

    public Double getTkt_scorethree() {
        return tkt_scorethree;
    }

    public void setTkt_scorethree(Double tkt_scorethree) {
        this.tkt_scorethree = tkt_scorethree;
    }

    public Double getTkt_scorefour() {
        return tkt_scorefour;
    }

    public void setTkt_scorefour(Double tkt_scorefour) {
        this.tkt_scorefour = tkt_scorefour;
    }

    public Double getIelts_source() {
        return ielts_source;
    }

    public void setIelts_source(Double ielts_source) {
        this.ielts_source = ielts_source;
    }

    public Integer getIs_teacher_certificate() {
        return is_teacher_certificate;
    }

    public void setIs_teacher_certificate(Integer is_teacher_certificate) {
        this.is_teacher_certificate = is_teacher_certificate;
    }

    public Integer getIs_celta() {
        return is_celta;
    }

    public void setIs_celta(Integer is_celta) {
        this.is_celta = is_celta;
    }

    public Integer getAbb_num() {
        return abb_num;
    }

    public void setAbb_num(Integer abb_num) {
        this.abb_num = abb_num;
    }

    public Integer getDuty_num() {
        return duty_num;
    }

    public void setDuty_num(Integer duty_num) {
        this.duty_num = duty_num;
    }

    public Integer getArticle_num() {
        return article_num;
    }

    public void setArticle_num(Integer article_num) {
        this.article_num = article_num;
    }

    public Integer getShare_num() {
        return share_num;
    }

    public void setShare_num(Integer share_num) {
        this.share_num = share_num;
    }

    public Integer getOperate_num() {
        return operate_num;
    }

    public void setOperate_num(Integer operate_num) {
        this.operate_num = operate_num;
    }

    public Integer getComplaint_num() {
        return complaint_num;
    }

    public void setComplaint_num(Integer complaint_num) {
        this.complaint_num = complaint_num;
    }

    public Integer getFeedback_num() {
        return feedback_num;
    }

    public void setFeedback_num(Integer feedback_num) {
        this.feedback_num = feedback_num;
    }

    public List<IeltsTeacherFeedback> getFeedbacklist() {
        return feedbacklist;
    }

    public void setFeedbacklist(List<IeltsTeacherFeedback> feedbacklist) {
        this.feedbacklist = feedbacklist;
    }

    public List<IeltsTeacherMatchclass> getMatchclasslist() {
        return matchclasslist;
    }

    public void setMatchclasslist(List<IeltsTeacherMatchclass> matchclasslist) {
        this.matchclasslist = matchclasslist;
    }

    public List<IeltsStudentTeacher> getStlist() {
        return stlist;
    }

    public void setStlist(List<IeltsStudentTeacher> stlist) {
        this.stlist = stlist;
    }

    public Date getLeft_integral_time() {
        return left_integral_time;
    }

    public void setLeft_integral_time(Date left_integral_time) {
        this.left_integral_time = left_integral_time;
    }

    public Date getRight_integral_time() {
        return right_integral_time;
    }

    public void setRight_integral_time(Date right_integral_time) {
        this.right_integral_time = right_integral_time;
    }

    public Integer getEnroll_num() {
        return enroll_num;
    }

    public void setEnroll_num(Integer enroll_num) {
        this.enroll_num = enroll_num;
    }

    public Integer getNot_enroll_num() {
        return not_enroll_num;
    }

    public void setNot_enroll_num(Integer not_enroll_num) {
        this.not_enroll_num = not_enroll_num;
    }

    public Integer getAchieve_num() {
        return achieve_num;
    }

    public void setAchieve_num(Integer achieve_num) {
        this.achieve_num = achieve_num;
    }

    public Integer getNot_achieve_num() {
        return not_achieve_num;
    }

    public void setNot_achieve_num(Integer not_achieve_num) {
        this.not_achieve_num = not_achieve_num;
    }

    public Integer getHight_num() {
        return hight_num;
    }

    public void setHight_num(Integer hight_num) {
        this.hight_num = hight_num;
    }
}