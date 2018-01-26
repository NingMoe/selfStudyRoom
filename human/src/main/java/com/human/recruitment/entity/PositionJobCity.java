package com.human.recruitment.entity;

public class PositionJobCity {
    private String positionId;
    
    private String jobCity;
    
    private String areaName;

    

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "{\"positionId\":\""+positionId+"\",\"jobCity\":\""+jobCity+"\",\"areaName\":\""+areaName+"\"}";
    }
    
}
