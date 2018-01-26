package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 校区实体
 * @author liuwei63
 *
 */
public class SchoolArea {
    private Long id;

    private Integer hrCompanyId;
    
    private String companyName;
    
    private String name;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    /**
     * 用于查询的时间
     * @return
     */
    private String deliveryDateStart;
    
    private String deliveryDateEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    
    public Integer getHrCompanyId() {
        return hrCompanyId;
    }

    public void setHrCompanyId(Integer hrCompanyId) {
        this.hrCompanyId = hrCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(String deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public String getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(String deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }
    
    
}