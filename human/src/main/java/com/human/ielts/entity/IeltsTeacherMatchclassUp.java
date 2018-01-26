package com.human.ielts.entity;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class IeltsTeacherMatchclassUp {
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="教师姓名")
    private String teacher_name;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="email前缀")
    private String email_addr;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="赛课范围")
    private String matchclass_type;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="赛课名称")
    private String matchclass_name;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="获奖等级")
    private Integer matchclass_grade;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getMatchclass_type() {
        return matchclass_type;
    }

    public void setMatchclass_type(String matchclass_type) {
        this.matchclass_type = matchclass_type;
    }

    public String getMatchclass_name() {
        return matchclass_name;
    }

    public void setMatchclass_name(String matchclass_name) {
        this.matchclass_name = matchclass_name;
    }

    public Integer getMatchclass_grade() {
        return matchclass_grade;
    }

    public void setMatchclass_grade(Integer matchclass_grade) {
        this.matchclass_grade = matchclass_grade;
    }

    
}
