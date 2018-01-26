package com.human.basic.service;

import java.util.List;
import java.util.Map;

import com.human.basic.entity.AreaInfo;
import com.human.utils.PageView;

public interface AreaInfoService {

	PageView getAreaPage(PageView page,AreaInfo areaInfo);

	List<AreaInfo> getArea(AreaInfo areaInfo);

	Integer addAreaInfo(AreaInfo areaInfo);

	Integer delAreaInfo(Integer id);
	
	Integer deleteCity(Integer id);

	Integer updateAreaInfo(AreaInfo areaInfo);
	
	List<AreaInfo> getAreaByAccount(String activityId);
	
	AreaInfo getAreaByPrimaryKey(AreaInfo areaInfo);
	
	List<AreaInfo> getParentArea(AreaInfo areaInfo);
	
	/**
	 * 获取同省份节点
	 * @param areaInfo
	 * @return
	 */
	List<AreaInfo> getSiblingArea(AreaInfo areaInfo);
	
	/**
	 * 根据用户名获取有权限查看的省份
	 * @param useName
	 * @return
	 */
	List<AreaInfo> getProByUser(String useName);
	
	/**
     * 根据用户名获取有权限查看城市
     * @param useName
     * @return
     */
    List<AreaInfo> getCityByUser(Map<String,Object> param);
    
    /**
     * 
     */
	List<String> getAreaCodeByParentCode(String parentCode);
	
    /**
     * 根据名称获取省市区
     * @param areaInfo
     * @return
     */
    AreaInfo getAreaByAreaName(String areaName);
    
    public Map<String,List<AreaInfo>> getAreaMap();
    
    /**
     * 获取登录人所属新东方学校的省份
     */
    List<AreaInfo> getAreaInfoByHrCompanyId(Map<Object, Object> map);
    
}
