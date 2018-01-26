package com.human.recruitment.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.human.manager.dao.UserDeptDao;
import com.human.recruitment.dao.InsideRecommendManagerDao;
import com.human.recruitment.entity.InsideRecommendManagerEntity;
import com.human.recruitment.service.InsideRecommendManagerService;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;

@Service
public class InsideRecommendServiceManagerImpl implements InsideRecommendManagerService {
    
    private final  Logger logger = LogManager.getLogger(InsideRecommendServiceManagerImpl.class);
    
    
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private InsideRecommendManagerDao ioDao;
     
    @Override
    public PageView query(PageView pageView, InsideRecommendManagerEntity  irm) {
        irm.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", irm);
        List<InsideRecommendManagerEntity> list=ioDao.query(map);
        //统计简历不同状态的数量
        if(list!=null && list.size()>0){
            for(InsideRecommendManagerEntity imr:list){   
              String insideRecommend=imr.getInsideRecommend();              
              long noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
              List<Map<String, Object>>numberMapList=ioDao.getNumberHashMap(insideRecommend);
              if(numberMapList!=null && numberMapList.size()>0){
                  for(Map<String, Object> numberMap:numberMapList){
                      if("0".equals(numberMap.get("flow_status"))){//待处理
                          noDealCount=(long) numberMap.get("totalNumber");
                      }else if("1".equals(numberMap.get("flow_status"))){//面试中
                          interviewCount=(long) numberMap.get("totalNumber");
                      }else if("2".equals(numberMap.get("flow_status"))||"3".equals(numberMap.get("flow_status"))){//淘汰
                          eliminateCount+=(long) numberMap.get("totalNumber");
                      }   
                  } 
              }
              imr.setNoDealCount(noDealCount);
              imr.setInterviewCount(interviewCount);
              imr.setEliminateCount(eliminateCount);
              //获取入职数目  
              entryCount=ioDao.getEntryNumberMap(insideRecommend); 
              imr.setEntryCount(entryCount);                  
            }          
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportThisPage(PageView pageView, InsideRecommendManagerEntity  irm, HttpServletRequest request, HttpServletResponse response) {
        irm.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", irm);
        List<Map<String,Object>> dataList =ioDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无信息(内推管理)!");
            return map; 
        }
        for(Map<String,Object>numberMap:dataList){
            String insideRecommend= (String)numberMap.get("insideRecommend");
            long noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
            List<Map<String, Object>>numberMapList=ioDao.getNumberHashMap(insideRecommend);
            if(numberMapList!=null && numberMapList.size()>0){
                for(Map<String, Object> numberMap1:numberMapList){
                    if("0".equals(numberMap1.get("flow_status"))){//待处理
                        noDealCount=(long) numberMap1.get("totalNumber");
                    }else if("1".equals(numberMap1.get("flow_status"))){//面试中
                        interviewCount=(long) numberMap1.get("totalNumber");
                    }else if("2".equals(numberMap1.get("flow_status"))||"3".equals(numberMap1.get("flow_status"))){//淘汰
                        eliminateCount+=(long) numberMap1.get("totalNumber");
                    }   
                } 
            }
            numberMap.put("noDealCount",noDealCount);
            numberMap.put("interviewCount",interviewCount);
            numberMap.put("eliminateCount",eliminateCount);
            //获取入职数目  
            entryCount=ioDao.getEntryNumberMap(insideRecommend); 
            numberMap.put("entryCount",entryCount);
            numberMap.put("propagandaLink","");
         }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportInsideRecommendManager = maps;
        map.put("flag", true);
        map.put("message", "导出信息(内推管理)成功!");
        return map;
    }

    @Override
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        String[] idarray = ids.split(",");
        list=Arrays.asList(idarray);
        map.put("s", list);
        map.put("userId", Common.getMyUser().getUserid());
        List<Map<String,Object>> maplist =ioDao.exportSelect(map);
        if(maplist!=null){
            for(Map<String,Object>numberMap:maplist){
               String insideRecommend= (String)numberMap.get("insideRecommend");
               long noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
               List<Map<String, Object>>numberMapList=ioDao.getNumberHashMap(insideRecommend);
               if(numberMapList!=null && numberMapList.size()>0){
                   for(Map<String, Object> numberMap1:numberMapList){
                       if("0".equals(numberMap1.get("flow_status"))){//待处理
                           noDealCount=(long) numberMap1.get("totalNumber");
                       }else if("1".equals(numberMap1.get("flow_status"))){//面试中
                           interviewCount=(long) numberMap1.get("totalNumber");
                       }else if("2".equals(numberMap1.get("flow_status"))||"3".equals(numberMap1.get("flow_status"))){//淘汰
                           eliminateCount+=(long) numberMap1.get("totalNumber");
                       }   
                   } 
               }
               numberMap.put("noDealCount",noDealCount);
               numberMap.put("interviewCount",interviewCount);
               numberMap.put("eliminateCount",eliminateCount);
               //获取入职数目  
               entryCount=ioDao.getEntryNumberMap(insideRecommend); 
               numberMap.put("entryCount",entryCount);
               numberMap.put("propagandaLink","");
            }
        }
        ExcelUtil<InsideRecommendManagerEntity> ex=new ExcelUtil<InsideRecommendManagerEntity>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportInsideRecommendManager.xlsx", InsideRecommendManagerEntity.class, maplist, response, "内推管理信息(选择项)", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("内推管理(选择项)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", Common.getMyUser().getUserid());
        List<Map<String,Object>> maplist =ioDao.exportSelect(map);
        if(maplist!=null){
            for(Map<String,Object>numberMap:maplist){
               String insideRecommend= (String)numberMap.get("insideRecommend");
               long noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
               List<Map<String, Object>>numberMapList=ioDao.getNumberHashMap(insideRecommend);
               if(numberMapList!=null && numberMapList.size()>0){
                   for(Map<String, Object> numberMap1:numberMapList){
                       if("0".equals(numberMap1.get("flow_status"))){//待处理
                           noDealCount=(long) numberMap1.get("totalNumber");
                       }else if("1".equals(numberMap1.get("flow_status"))){//面试中
                           interviewCount=(long) numberMap1.get("totalNumber");
                       }else if("2".equals(numberMap1.get("flow_status"))||"3".equals(numberMap1.get("flow_status"))){//淘汰
                           eliminateCount+=(long) numberMap1.get("totalNumber");
                       }   
                   } 
               }
               numberMap.put("noDealCount",noDealCount);
               numberMap.put("interviewCount",interviewCount);
               numberMap.put("eliminateCount",eliminateCount);
               //获取入职数目  
               entryCount=ioDao.getEntryNumberMap(insideRecommend); 
               numberMap.put("entryCount",entryCount);
               numberMap.put("propagandaLink","");
            }
        }
        ExcelUtil<InsideRecommendManagerEntity> ex=new ExcelUtil<InsideRecommendManagerEntity>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportInsideRecommendManager.xlsx", InsideRecommendManagerEntity.class, maplist, response, "我的内推信息(全部)", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("我的内推(全部)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }



}
