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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.recruitment.dao.ResumeTalentDao;
import com.human.recruitment.entity.ResumeTalent;
import com.human.recruitment.service.TalentService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;

@Service
public class TalentServiceImpl implements TalentService {
    
    private final  Logger logger = LogManager.getLogger(TalentServiceImpl.class);
    
    @Resource
    private ResumeTalentDao resumeTalentDao;

    @Override
    public PageView query(PageView pageView, ResumeTalent  resumeTalent) {
        resumeTalent.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", resumeTalent);
        List<ResumeTalent> list= resumeTalentDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportThisPage(PageView pageView, ResumeTalent  resumeTalent, HttpServletRequest request, HttpServletResponse response) {
        resumeTalent.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", resumeTalent);
        List<Map<String,Object>> dataList =resumeTalentDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无人才库信息！");
            return map; 
        }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportTalent = maps;
        map.put("flag", true);
        map.put("message", "导出人才库成功!");
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
        List<Map<String,Object>> maplist =resumeTalentDao.exportSelect(map);
        ExcelUtil<ResumeTalent> ex=new ExcelUtil<ResumeTalent>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportTalent.xlsx", ResumeTalent.class, maplist, response, "人才库信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("人才库信息(选择项)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", Common.getMyUser().getUserid());
        List<Map<String,Object>> maplist =resumeTalentDao.exportSelect(map);
        ExcelUtil<ResumeTalent> ex=new ExcelUtil<ResumeTalent>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportTalent.xlsx", ResumeTalent.class, maplist, response, "人才库信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("导出人才库(全部)信息失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }


    @Override
    public Map<String, Object> updateStatus(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] userIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", userIds);
            resumeTalentDao.updateByIds(paraMap);
            resumeTalentDao.deleteByIds(paraMap);
            map.put("flag", true);
            map.put("message", "移出人才库成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("移出人才库失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "移出人才库失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public int deleteByResumeId(Integer resumeId) {
        
        return resumeTalentDao.deleteByResumeId(resumeId);
    }

}
