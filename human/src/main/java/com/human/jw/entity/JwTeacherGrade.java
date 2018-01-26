package com.human.jw.entity;

public class JwTeacherGrade {
    private String teacherCode;
    
    private String grade;
    
    public JwTeacherGrade(){
        
    }

    public JwTeacherGrade(String teacherCode, String grade) {
        super();
        this.teacherCode = teacherCode;
        this.grade = grade;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
}
