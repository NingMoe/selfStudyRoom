package com.human.bpm.entity;

import java.util.Date;
import java.util.List;

public class BpmProcessDefinition {
	private String id;  
    private String name;  
    private String key;  
    private String description;  
    private String status;
    private Date createTime;  
    private Date lastUpdateTime;  
    private int version;
    
    private List<BpmNodeConfig> nodes;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<BpmNodeConfig> getNodes() {
        return nodes;
    }
    public void setNodes(List<BpmNodeConfig> nodes) {
        this.nodes = nodes;
    } 
    
    
}
