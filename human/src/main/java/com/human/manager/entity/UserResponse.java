package com.human.manager.entity;

import java.util.List;

public class UserResponse {
    
    private String status;
    
    private String message;
    
    private List<HrUser> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HrUser> getData() {
        return data;
    }

    public void setData(List<HrUser> data) {
        this.data = data;
    }

    
    
    
}
