package com.human.basic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassMessage {
    private String nSchoolId;
    
    @JsonProperty(value = "CurrentBizBatch")  
    private ClassBizBatch currentBizBatch;

    public String getnSchoolId() {
        return nSchoolId;
    }

    public void setnSchoolId(String nSchoolId) {
        this.nSchoolId = nSchoolId;
    }

    public ClassBizBatch getCurrentBizBatch() {
        return currentBizBatch;
    }

    public void setCurrentBizBatch(ClassBizBatch currentBizBatch) {
        this.currentBizBatch = currentBizBatch;
    }
    
    
}
