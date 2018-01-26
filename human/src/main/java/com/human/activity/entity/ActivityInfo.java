package com.human.activity.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;



public class ActivityInfo {
    private Long id;

    private String activityName;

    private String deptName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String text1;

    private String text1Isneed;

    private String text2;

    private String text2Isneed;

    private String text3;

    private String text3Isneed;

    private String text4;

    private String text4Isneed;

    private String tollLimit;

    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String isValid;
    
    private String codeUrl;
    
    private String accountValid;
    
    private List<Product> productList;
    
    public class Product {
        private Long id;

        private String name;

        private BigDecimal price;

        private Long total;

        private Long activtiyId;

        private String isValid;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public Long getActivtiyId() {
            return activtiyId;
        }

        public void setActivtiyId(Long activtiyId) {
            this.activtiyId = activtiyId;
        }

        public String getIsValid() {
            return isValid;
        }

        public void setIsValid(String isValid) {
            this.isValid = isValid == null ? null : isValid.trim();
        }
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1 == null ? null : text1.trim();
    }

    public String getText1Isneed() {
        return text1Isneed;
    }

    public void setText1Isneed(String text1Isneed) {
        this.text1Isneed = text1Isneed == null ? null : text1Isneed.trim();
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }

    public String getText2Isneed() {
        return text2Isneed;
    }

    public void setText2Isneed(String text2Isneed) {
        this.text2Isneed = text2Isneed == null ? null : text2Isneed.trim();
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3 == null ? null : text3.trim();
    }

    public String getText3Isneed() {
        return text3Isneed;
    }

    public void setText3Isneed(String text3Isneed) {
        this.text3Isneed = text3Isneed == null ? null : text3Isneed.trim();
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4 == null ? null : text4.trim();
    }

    public String getText4Isneed() {
        return text4Isneed;
    }

    public void setText4Isneed(String text4Isneed) {
        this.text4Isneed = text4Isneed == null ? null : text4Isneed.trim();
    }

    public String getTollLimit() {
        return tollLimit;
    }

    public void setTollLimit(String tollLimit) {
        this.tollLimit = tollLimit == null ? null : tollLimit.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getAccountValid() {
        return accountValid;
    }

    public void setAccountValid(String accountValid) {
        this.accountValid = accountValid;
    }
    
    
    
    
}