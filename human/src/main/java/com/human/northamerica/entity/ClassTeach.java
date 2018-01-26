package com.human.northamerica.entity;

public class ClassTeach {
    private Long id;
    
    private String classCode;
    
    private String teachCode;
    
    private String teachName;
    
    private Integer teachSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTeachCode() {
        return teachCode;
    }

    public void setTeachCode(String teachCode) {
        this.teachCode = teachCode;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public Integer getTeachSub() {
        return teachSub;
    }

    public void setTeachSub(Integer teachSub) {
        this.teachSub = teachSub;
    } 
}
