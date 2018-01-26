package com.human.customer.entity;

import java.io.Serializable;
import java.util.List;

public class CenterModel implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String modelName;
    
    private Integer sort;
    
    private Boolean isDel;
    
    private List<CustomerCenterMenu> ccmList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public List<CustomerCenterMenu> getCcmList() {
        return ccmList;
    }

    public void setCcmList(List<CustomerCenterMenu> ccmList) {
        this.ccmList = ccmList;
    }
    
}
