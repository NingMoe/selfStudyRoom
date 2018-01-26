package com.human.ielts.entity;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class IeltsTeacherInfoUp {
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="教师姓名")
    private String teacher_name;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="教师邮箱前缀")
    private String email_addr;

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
}
