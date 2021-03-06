package com.human.customer.entity;

import java.io.Serializable;
import java.util.List;

public class CenterDept implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String deptName;
    
    private String banerUrl;
    
    private Boolean isDel;
    
    private Long parentId;
    
    private Boolean isChecked;
    
    private List<CenterModel> cmList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBanerUrl() {
        return banerUrl;
    }

    public void setBanerUrl(String banerUrl) {
        this.banerUrl = banerUrl;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = (long) 0;
    }

    public List<CenterModel> getCmList() {
        return cmList;
    }

    public void setCmList(List<CenterModel> cmList) {
        this.cmList = cmList;
    }

}
