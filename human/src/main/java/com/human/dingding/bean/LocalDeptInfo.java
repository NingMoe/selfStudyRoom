package com.human.dingding.bean;

/**
 * 本地库部门
 * @author Administrator
 *
 */
public class LocalDeptInfo {
    
  private String orgId;
  
  private String orgName;
  
  private String parentId;
  
  private String dingdingId;
  
  private String parentDingDingId;
  
  private Boolean isParent;

public String getOrgId() {
    return orgId;
}

public void setOrgId(String orgId) {
    this.orgId = orgId;
}

public String getOrgName() {
    return orgName;
}

public void setOrgName(String orgName) {
    this.orgName = orgName;
}

public String getParentId() {
    return parentId;
}

public void setParentId(String parentId) {
    this.parentId = parentId;
}

public String getDingdingId() {
    return dingdingId;
}

public void setDingdingId(String dingdingId) {
    this.dingdingId = dingdingId;
}

public Boolean getIsParent() {
    return isParent;
}

public void setIsParent(Boolean isParent) {
    this.isParent = isParent;
}

public String getParentDingDingId() {
    return parentDingDingId;
}

public void setParentDingDingId(String parentDingDingId) {
    this.parentDingDingId = parentDingDingId;
}
  
}
