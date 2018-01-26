package com.human.recruitment.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.recruitment.dao.SchoolRecruitmentDao;
import com.human.recruitment.entity.SchoolRecruitment;
import com.human.recruitment.service.SchoolRecruitmentService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;
@Service
public class SchoolRecruitmentServiceImpl implements SchoolRecruitmentService {
            
    @Resource
    private SchoolRecruitmentDao srDao;
    
    @Value("${humanServer}")
    private String humanServer;
    
    @Value("${recruitmentUrl}")
    private String recruitmentUrl;
    
    
    @Override
    public PageView query(PageView pageView, SchoolRecruitment sr) {
        sr.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", sr);
        List<SchoolRecruitment> list=srDao.query(map);
        //统计简历不同状态的数量
        if(list!=null && list.size()>0){
            for(SchoolRecruitment srt:list){   
                long totalCount=0,noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
                //查询收到的简历总数
                totalCount=srDao.getTotalCount(srt.getId());
                srt.setTotalCount(totalCount);
                List<Map<String, Object>>numberMapList=srDao.getNumberHashMap(srt.getId());
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
                sr.setNoDealCount(noDealCount);
                sr.setInterviewCount(interviewCount);
                sr.setEliminateCount(eliminateCount);
                //获取入职数目  
                entryCount=srDao.getEntryNumberMap(srt.getId()); 
                sr.setEntryCount(entryCount);   
            }          
        }
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public Map<String, Object> add(SchoolRecruitment sr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            sr.setCreateUser(Common.getMyUser().getUsername());
            sr.setCreateTime(new Date());
            srDao.insertSelective(sr); 
            SchoolRecruitment srt=new SchoolRecruitment();
            srt.setId(sr.getId());
            srt.setCodeUrl(humanServer+recruitmentUrl+"?schoolRecruitmentId="+sr.getId());
            srDao.updateByPrimaryKeySelective(srt);
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public SchoolRecruitment selectByPrimaryKey(long id) {
        return srDao.selectByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(SchoolRecruitment sr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            sr.setUpdateUser(Common.getMyUser().getUsername());
            sr.setUpdateTime(new Date());
            srDao.updateByPrimaryKeySelective(sr);
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            srDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
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
        List<Map<String,Object>> maplist =srDao.exportSelect(map);
        if(maplist!=null){
            for(Map<String,Object>numberMap:maplist){
               long id= (long) numberMap.get("id");
               long totalCount=0,noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
               //查询收到的简历总数
               totalCount=srDao.getTotalCount(id);
               numberMap.put("totalCount",totalCount);
               List<Map<String, Object>>numberMapList=srDao.getNumberHashMap(id);
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
               entryCount=srDao.getEntryNumberMap(id);  
               numberMap.put("entryCount",entryCount);
            }
        }
        ExcelUtil<SchoolRecruitment> ex=new ExcelUtil<SchoolRecruitment>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportSchoolRecruitmentManager.xlsx", SchoolRecruitment.class, maplist, response, "校招管理信息(选择项)", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }


    @Override
    public Map<String, Object> exportThisPage(PageView pageView, SchoolRecruitment sr, HttpServletRequest request, HttpServletResponse response) {
        sr.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", sr);
        List<Map<String,Object>> dataList =srDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无信息(校招管理)!");
            return map; 
        }
        for(Map<String,Object>numberMap:dataList){
            long id= (long) numberMap.get("id");
            long totalCount=0,noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
            //查询收到的简历总数
            totalCount=srDao.getTotalCount(id);
            numberMap.put("totalCount",totalCount);
            List<Map<String, Object>>numberMapList=srDao.getNumberHashMap(id);
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
            entryCount=srDao.getEntryNumberMap(id); 
            numberMap.put("entryCount",entryCount);
         }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportSchoolRecruitmentManager = maps;
        map.put("flag", true);
        map.put("message", "导出信息(校招管理)成功!");
        return map;
    }


    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", Common.getMyUser().getUserid());
        List<Map<String,Object>> maplist =srDao.exportSelect(map);
        if(maplist!=null){
            for(Map<String,Object>numberMap:maplist){
               long id= (long) numberMap.get("id");
               long totalCount=0,noDealCount=0,interviewCount=0,eliminateCount=0,entryCount=0;
               //查询收到的简历总数
               totalCount=srDao.getTotalCount(id);
               numberMap.put("totalCount",totalCount);
               List<Map<String, Object>>numberMapList=srDao.getNumberHashMap(id);
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
               entryCount=srDao.getEntryNumberMap(id);  
               numberMap.put("entryCount",entryCount);
            }
        }
        ExcelUtil<SchoolRecruitment> ex=new ExcelUtil<SchoolRecruitment>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportSchoolRecruitmentManager.xlsx", SchoolRecruitment.class, maplist, response, "校招管理信息(全部)", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

}
