package com.human.customer.entity;

public class CustomerMailfoxDemand {
    
    private Long id;
    
    private Long  baseId;
    
    private String demandDesc;
    
    private Boolean isMeet;
    
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getDemandDesc() {
        return demandDesc;
    }

    public void setDemandDesc(String demandDesc) {
        this.demandDesc = demandDesc;
    }

    public Boolean getIsMeet() {
        return isMeet;
    }

    public void setIsMeet(Boolean isMeet) {
        this.isMeet = isMeet;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    
}
