package com.human.northamerica.entity;

import com.human.pulgin.excel.ExcelAnnotation;

public class SatBean {
    
    @ExcelAnnotation(exportName="学管师")
    private String managerTeach;
    
    @ExcelAnnotation(exportName="考试日期")
    private String examDate;
    
    @ExcelAnnotation(exportName="学员号")
    private String studentCode;
    
    @ExcelAnnotation(exportName="学员姓名")
    private String studentName;
    
    @ExcelAnnotation(exportName="是否大学生")
    private String isCollege;
    
    @ExcelAnnotation(exportName="学校名称")
    private String schoolName;
    
    @ExcelAnnotation(exportName="是否首考")
    private String isFirst;
    
    @ExcelAnnotation(exportName="入班托福成绩")
    private String joinToefl;
    
    @ExcelAnnotation(exportName="总分")
    private String totalScore;
    
    @ExcelAnnotation(exportName="阅读")
    private String readScore;
    
    @ExcelAnnotation(exportName="文法")
    private String grammarScore;
    
    @ExcelAnnotation(exportName="数学")
    private String matheScore;
    
    @ExcelAnnotation(exportName="写作")
    private String writeScore;

    public String getManagerTeach() {
        return managerTeach;
    }

    public void setManagerTeach(String managerTeach) {
        this.managerTeach = managerTeach;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getJoinToefl() {
        return joinToefl;
    }

    public void setJoinToefl(String joinToefl) {
        this.joinToefl = joinToefl;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getReadScore() {
        return readScore;
    }

    public void setReadScore(String readScore) {
        this.readScore = readScore;
    }

    public String getGrammarScore() {
        return grammarScore;
    }

    public void setGrammarScore(String grammarScore) {
        this.grammarScore = grammarScore;
    }

    public String getMatheScore() {
        return matheScore;
    }

    public void setMatheScore(String matheScore) {
        this.matheScore = matheScore;
    }

    public String getWriteScore() {
        return writeScore;
    }

    public void setWriteScore(String writeScore) {
        this.writeScore = writeScore;
    }

    public String getIsCollege() {
        return isCollege;
    }

    public void setIsCollege(String isCollege) {
        this.isCollege = isCollege;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }
    
}
