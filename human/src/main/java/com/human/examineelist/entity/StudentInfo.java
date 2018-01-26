package com.human.examineelist.entity;

import java.util.Date;

/**
 * 数据管理实体
 * @author yaolu
 *
 */
public class StudentInfo {
    /**
     * 主键
     */
    private long id;
    /**
     * 班号
     */
    private String classCode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 性别
     */
    private String gender;
    /**
     * 部门
     */
    private String deptName;
    
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}