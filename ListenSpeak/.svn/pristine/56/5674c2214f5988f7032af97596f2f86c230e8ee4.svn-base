package com.ls.spt.basic.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.basic.entity.AreaInfo;


public interface AreaInfoDao {
	
	List<AreaInfo> getAreaPage(Map<Object,Object> map);
	
	List<AreaInfo> getAreaNoPage(AreaInfo areaInfo);
	
	AreaInfo getAreaByPrimaryKey(AreaInfo areaInfo);
	
	List<AreaInfo> getParentArea(AreaInfo areaInfo);
	
	Integer insertPro(AreaInfo areaInfo);
	
	Integer insertArea(AreaInfo areaInfo);
	
	Integer updateArea(AreaInfo areaInfo);
	
	Integer deleteArea(Integer id);
	
	Integer deleteCity(Integer id);
			
	Integer equLvlAndCode(AreaInfo areaInfo);
	
	List<AreaInfo> getAreaByAccount(String activityId);

	AreaInfo getAreabyAreaCode(String siteArea);

	/**
	 * 根据用户名获取有权限的省份
	 * @param useName
	 * @return
	 */
    List<AreaInfo> getProByUser(String useName);

    /**
     * 根据用户名获取有权限的地区
     * @param useName
     * @return
     */
    List<AreaInfo> getCityByUser(Map<String,Object> userName);

    /**
     * 根据区域编码获取子集code
     * @param parentCode
     * @return
     */
    List<String> getAreaCodeByParentCode(String parentCode);
    
    /**
     * 根据名称获取省市区
     * @param areaInfo
     * @return
     */
    AreaInfo getAreaByAreaName(String areaName);

    /**
     * 获取同级别地区
     * @param areaInfo
     * @return
     */
    List<AreaInfo> getSiblingArea(AreaInfo areaInfo);
    
    /**
     * 获取全部地区
     * @param areaInfo
     * @return
     */
    List<AreaInfo> getAllArea();
    
    /**
     * 获取全部学校所在城市
     */
    public List<AreaInfo> getAllSchoolArea();

    /**
     * 获取登录人所属新东方学校的省份
     */
    List<AreaInfo> getAreaInfoByHrCompanyId(Map<Object, Object> map);
}
