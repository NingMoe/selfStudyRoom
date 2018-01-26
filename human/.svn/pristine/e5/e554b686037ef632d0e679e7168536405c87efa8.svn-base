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
import com.human.recruitment.dao.InterviewObserverDao;
import com.human.recruitment.service.InterviewObserverService;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;

@Service
public class InterviewObserverServiceImpl implements InterviewObserverService {
    
    private final  Logger logger = LogManager.getLogger(InterviewObserverServiceImpl.class);
    
    
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private InterviewObserverDao ioDao;
     
    @Override
    public PageView query(PageView pageView, ResumeBase resumeBase) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptDao.getUserCompany(Common.getMyUser().getUserid());
        List<String>companyIds=new ArrayList<String>();
        if(null!=companyList&&companyList.size()>0){
            for(HrCompany hc:companyList){
                companyIds.add(hc.getCompanyId()) ;
            }
        }
        map.put("paging", pageView);
        map.put("t", resumeBase);
        if(companyIds!=null && companyIds.size()>0){
            map.put("s", companyIds);  
        }
        map.put("userName", Common.getAuthenticatedUsername());
        List<ResumeBase> list=ioDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportThisPage(PageView pageView, ResumeBase resumeBase, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
      //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptDao.getUserCompany(Common.getMyUser().getUserid());
        List<String>companyIds=new ArrayList<String>();
        if(null!=companyList&&companyList.size()>0){
            for(HrCompany hc:companyList){
                companyIds.add(hc.getCompanyId()) ;
            }
        }
        map.put("paging", pageView);
        map.put("t", resumeBase);
        if(companyIds!=null && companyIds.size()>0){
            map.put("s", companyIds);  
        }
        map.put("userName", Common.getAuthenticatedUsername());
        List<Map<String,Object>> dataList =ioDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无简历信息(面试观察员)!");
            return map; 
        }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportWatcher = maps;
        map.put("flag", true);
        map.put("message", "导出简历信息(面试观察员)成功!");
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
            ex.writeExcel(path+"exportWatcher.xlsx", ResumeBase.class, maplist, response, "面试观察员信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("面试观察员(选择项)导出失败!", e.getMessage());
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
            ex.writeExcel(path+"exportWatcher.xlsx", ResumeBase.class, maplist, response, "面试观察员信息(全部)", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("安排面试(全部)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }



}
