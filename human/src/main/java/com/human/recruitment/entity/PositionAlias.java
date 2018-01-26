package com.human.recruitment.entity;

public class PositionAlias {
    private String positionId;
    
    private String aliasName;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj==null){
            return false;
        }
        PositionAlias p1 = (PositionAlias)obj;    
        return p1.getPositionId().equals(this.getPositionId()) 
                && p1.getAliasName().equals(this.getAliasName());
    }
}
