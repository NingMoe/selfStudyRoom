package com.ls.spt.basic.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ls.spt.basic.dao.AreaInfoDao;
import com.ls.spt.basic.entity.AreaInfo;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.utils.ChineseCharToEn;
import com.ls.spt.utils.RedisTemplateUtil;


@Service
public class AreaInfoServiceImpl implements AreaInfoService {

	@Autowired
	private AreaInfoDao areaInfoDao;
	
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	
	@Value("${redis.areakey}") 
    private  String areakey;
	
	@Override
	public PageView getAreaPage(PageView page,AreaInfo areaInfo) {
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("paging", page);
		map.put("t", areaInfo);
		List<AreaInfo> list = areaInfoDao.getAreaPage(map);
//		page.setRecords(list);
		page.setData(list);
		return page;
	}
	
	@Override
	public AreaInfo getAreaByPrimaryKey(AreaInfo AreaInfo) {
		return areaInfoDao.getAreaByPrimaryKey(AreaInfo);
	}

	@Override
	public List<AreaInfo> getArea(AreaInfo areaInfo) {
		List<AreaInfo> list = getAllArea();
		return getAreaByCondition(list,areaInfo);
	}

	@Override
	public Integer addAreaInfo(AreaInfo areaInfo) {
		if (areaInfo == null) {
			return 1;
		} else if (areaInfo.getAreaLevel() == null || areaInfo.getAreaName() == null) {
			return 2;
		} else if (areaInfo.getAreaLevel() != 1) {
			if (areaInfo.getParentAreaCode() == null) {
				return 5;
		  }
		}
		int i = areaInfoDao.insertPro(areaInfo);		
		if (i > 0) {
			return 0;
		}
		return 6;
	}

	@Override
	public Integer delAreaInfo(Integer id) {
		Integer i = areaInfoDao.deleteArea(id);
		return i;
	}

	@Override
	public Integer updateAreaInfo(AreaInfo areaInfo) {
		Integer i = areaInfoDao.updateArea(areaInfo);
		return i;
	}

	@Override
	public List<AreaInfo> getAreaByAccount(String activityId) {
		List<AreaInfo> list = areaInfoDao.getAreaByAccount(activityId);
		return list;
	}
	
	@Override
	public List<AreaInfo> getParentArea(AreaInfo areaInfo) {
		return areaInfoDao.getParentArea(areaInfo);
	}


    @Override
    public List<String> getAreaCodeByParentCode(String parentCode) {
        return areaInfoDao.getAreaCodeByParentCode(parentCode);
    }

    @Override
    public Integer deleteCity(Integer id) {
        return areaInfoDao.deleteCity(id);
    }

    @Override
    public AreaInfo getAreaByAreaName(String areaName) {
      
        return areaInfoDao.getAreaByAreaName(areaName);
    }

    @Override
    public List<AreaInfo> getSiblingArea(AreaInfo areaInfo) {
        return areaInfoDao.getSiblingArea(areaInfo);
    }
    
    @Override
    public Map<String,List<AreaInfo>> getAreaMap(){
        List<AreaInfo> list = areaInfoDao.getAllSchoolArea();
        list.sort(new Comparator<AreaInfo>() {
            public int compare(AreaInfo o1, AreaInfo o2) {
                return o1.getId()-o2.getId();
            };
        });
        Map<String,List<AreaInfo>> map = new LinkedHashMap<String,List<AreaInfo>>();
        for(AreaInfo a:list){
            List<AreaInfo> temp = null;
            String CapitalLiter = ChineseCharToEn.getFirstLetter(a.getAreaName());
            if(map.get(CapitalLiter)==null){
                temp = new ArrayList<AreaInfo>();   
            }else{
                temp  = map.get(CapitalLiter);
            }
            temp.add(a);
            map.put(CapitalLiter,temp);
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    private List<AreaInfo> getAllArea(){
        List<AreaInfo> result = null;
        //如果存在缓存
        if(redisTemplateUtil.isExist(areakey)){
            result = (List<AreaInfo>) redisTemplateUtil.getObject(areakey, List.class);
        }else{
            result = areaInfoDao.getAllArea();
            redisTemplateUtil.setList(areakey, result, null);
        }
        return result;
    }
    
    private List<AreaInfo> getAreaByCondition(List<AreaInfo> areaInfos,AreaInfo areaInfo){
        List<AreaInfo> result = new ArrayList<AreaInfo>();
        for(AreaInfo a:areaInfos){
            if(areaInfo.getId()!=null&&a.getId()!=areaInfo.getId()){
                continue;
            }
            String areaName = areaInfo.getAreaName();
            if(StringUtils.isNotEmpty(areaName)){
                if(areaName.indexOf("市")<0){
                    areaName = areaName+"市";
                }
                if(StringUtils.isNotEmpty(a.getAreaName())&&!a.getAreaName().equals(areaName)){
                    continue;
                }
            }
            
            if(areaInfo.getParentAreaCode()!=null){
                if(a.getParentAreaCode()==null){
                    continue;
                }
                if((a.getParentAreaCode()!=null)&& (a.getParentAreaCode().intValue()!=areaInfo.getParentAreaCode().intValue())){
                    continue;
                }
            }
            
            if(areaInfo.getAreaLevel()!=null){
                if(a.getAreaLevel()!=null&&a.getAreaLevel().intValue()!=areaInfo.getAreaLevel().intValue()){
                    continue;
                }
            }
            result.add(a);
        }
        return result;
    }
    
}
