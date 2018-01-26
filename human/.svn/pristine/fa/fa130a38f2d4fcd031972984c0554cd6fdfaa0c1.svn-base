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
import com.human.manager.entity.HrCompany;
import com.human.recruitment.dao.MyInsideRecommendDao;
import com.human.recruitment.service.MyInsideRecommendService;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;

@Service
public class MyInsideRecommendServiceImpl implements MyInsideRecommendService {
    
    private final  Logger logger = LogManager.getLogger(MyInsideRecommendServiceImpl.class);
    
    
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private MyInsideRecommendDao ioDao;
     
    @Override
    public PageView query(PageView pageView, ResumeBase resumeBase) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", resumeBase);
        map.put("userName", Common.getAuthenticatedUsername());
        List<ResumeBase> list=ioDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportThisPage(PageView pageView, ResumeBase resumeBase, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", resumeBase);
        map.put("userName", Common.getAuthenticatedUsername());
        List<Map<String,Object>> dataList =ioDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无简历信息(我的内推)!");
            return map; 
        }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportMyInsideRecommend = maps;
        map.put("flag", true);
        map.put("message", "导出简历信息(我的内推)成功!");
        return map;
    }

    @Override
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        String[] idarray = ids.split(",");
        list=Arrays.asList(idarray);
        map.put("s", list);
        map.put("userName", Common.getAuthenticatedUsername());
        List<Map<String,Object>> maplist =ioDao.exportSelect(map);
        ExcelUtil<ResumeBase> ex=new ExcelUtil<ResumeBase>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportMyInsideRecommend.xlsx", ResumeBase.class, maplist, response, "我的内推信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("我的内推(选择项)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", Common.getAuthenticatedUsername());
        List<Map<String,Object>> maplist =ioDao.exportSelect(map);
        ExcelUtil<ResumeBase> ex=new ExcelUtil<ResumeBase>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportMyInsideRecommend.xlsx", ResumeBase.class, maplist, response, "我的内推信息(全部)", 0, 1);
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
