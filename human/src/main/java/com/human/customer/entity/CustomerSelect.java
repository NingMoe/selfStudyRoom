package com.human.customer.entity;

import java.io.Serializable;

public class CustomerSelect implements Serializable{

    private static final long serialVersionUID = 7685217084836724212L;
    
    private Integer id;
    
    private String name;
    
    /**
     * 0.根目录，1-4为四级下拉列表
     */
    private Integer level;
    
    private String parentName;
    
    private String userName;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
