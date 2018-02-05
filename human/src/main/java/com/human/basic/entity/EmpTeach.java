package com.human.basic.entity;

import com.human.pulgin.excel.ExcelAnnotation;

public class EmpTeach {
    
    @ExcelAnnotation(exportName="员工邮箱")
    private String empEmail;
    
    @ExcelAnnotation(exportName="员工工号")
    private String empId;
    
    private String empName;
    
    private String teachId;
    
    @ExcelAnnotation(exportName="汇报人邮箱")
    private String emailAddr;
    
    private String teachName;
    
    private String comName;
    
    private String deptName;
    
    /**
     * 导入操作类型:A 新增，D删除
     */
    @ExcelAnnotation(exportName="操作类型")
    private String type;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}