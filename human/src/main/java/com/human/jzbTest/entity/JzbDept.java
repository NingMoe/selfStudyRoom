package com.human.jzbTest.entity;

import java.util.List;

public class JzbDept {
    private Integer id;

    private String name;

    private String company;
    
    private String companyName;
    
    private String isValid;
    
    private List<JzbDeptManager> managers;
     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public List<JzbDeptManager> getManagers() {
        return managers;
    }

    public void setManagers(List<JzbDeptManager> managers) {
        this.managers = managers;
    }
    
    
}