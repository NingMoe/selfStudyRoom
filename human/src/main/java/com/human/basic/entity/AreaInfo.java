package com.human.basic.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AreaInfo implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
	
	private String areaCode;
	
	private String areaName;
	
	private Integer areaLevel;
	
	private Integer parentAreaCode;
	
	private Integer relation;
	
	private Boolean isValid;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	private String createTime;
	
	private String createUser;
	
	private String provinceCodeByArea;
	
	private String cityCodeByArea;
	
	/**
	 * 
	 * @return 返回id
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return 返回地区编码
	 */
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 
	 * @return 返回地区名称
	 */
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * 
	 * @return 返回地区级别
	 */
	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	/**
	 * 
	 * @return 返回上级地区Id
	 */
    public Integer getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(Integer parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }

	/***
	 * 
	 * @return 返回关联下级地区数量
	 */
	public Integer getRelation() {
		return relation;
	}

    public void setRelation(Integer relation) {
		this.relation = relation;
	}

	/**
	 * 
	 * @return 是否禁用，false启用，true禁用
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * 
	 * @return 返回创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return 返回创建人
	 */
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getProvinceCodeByArea() {
		return provinceCodeByArea;
	}

	public void setProvinceCodeByArea(String provinceCodeByArea) {
		this.provinceCodeByArea = provinceCodeByArea;
	}

	public String getCityCodeByArea() {
		return cityCodeByArea;
	}

	public void setCityCodeByArea(String cityCodeByArea) {
		this.cityCodeByArea = cityCodeByArea;
	}

}
