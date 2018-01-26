package com.human.manager.entity;


public class FeedbackParamBean {
    
    private Long id;
    
    private String examStartDate;
    
    private String examEndDate;
    
    private String deptId;
    
    private String name;
    
    private String ids;
    
    private Integer state;
    
    private Integer score;
    
    private String closeDesc;
    
    private Integer closeType;
    
    private String userName;

    public String getExamStartDate() {
        return examStartDate;
    }

    public void setExamStartDate(String examStartDate) {
        this.examStartDate = examStartDate;
    }

    public String getExamEndDate() {
        return examEndDate;
    }

    public void setExamEndDate(String examEndDate) {
        this.examEndDate = examEndDate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCloseDesc() {
        return closeDesc;
    }

    public void setCloseDesc(String closeDesc) {
        this.closeDesc = closeDesc;
    }

    public Integer getCloseType() {
        return closeType;
    }

    public void setCloseType(Integer closeType) {
        this.closeType = closeType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    

}
