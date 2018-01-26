package com.human.recruitment.service.impl;

import java.sql.Timestamp;
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

import com.human.recruitment.dao.HrResumeFlowDao;
import com.human.recruitment.dao.InterviewDao;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewEntity;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.service.InterviewService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.ExportConfigure;
import com.human.utils.PageView;

@Service
public class InterviewServiceImpl implements InterviewService {
    
    private final  Logger logger = LogManager.getLogger(InterviewServiceImpl.class);
    
    @Resource
    private InterviewDao interviewDao;
    
    @Resource
    private HrResumeFlowDao hrResumeFlowDao;

    @Override
    public PageView query(PageView pageView, InterviewEntity interviewEntity) {
        interviewEntity.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", interviewEntity);
        List<InterviewEntity> list= interviewDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> exportThisPage(PageView pageView, InterviewEntity interviewEntity, HttpServletRequest request, HttpServletResponse response) {
        interviewEntity.setUserId(Common.getMyUser().getUserid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", interviewEntity);
        List<Map<String,Object>> dataList =interviewDao.exporThisPage(map);
        if(dataList==null ||dataList.size()<1){
            map.put("flag", false);
            map.put("message", "暂无安排面试信息！");
            return map; 
        }
        Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
        String account=Common.getAuthenticatedUsername();
        maps.put(account, dataList);
        ExportConfigure.exportInterview = maps;
        map.put("flag", true);
        map.put("message", "导出安排面试成功!");
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
        List<Map<String,Object>> maplist =interviewDao.exportSelect(map);
        ExcelUtil<InterviewEntity> ex=new ExcelUtil<InterviewEntity>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportInterview.xlsx", InterviewEntity.class, maplist, response, "安排面试信息", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("安排面试(选择项)导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", Common.getMyUser().getUserid());
        List<Map<String,Object>> maplist =interviewDao.exportSelect(map);
        ExcelUtil<InterviewEntity> ex=new ExcelUtil<InterviewEntity>();
        String path = request.getSession().getServletContext().getRealPath("/static/temp/");
        try {
            ex.writeExcel(path+"exportInterview.xlsx", InterviewEntity.class, maplist, response, "安排面试信息", 0, 1);
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

    @Override
    public HrResumeFlow selectByPrimaryKey(Integer id) {       
        return hrResumeFlowDao.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> edit(InterviewSchedule interviewSchedule) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{         
            String flowId =interviewSchedule.getFlowId();
            HrResumeFlow flow = new HrResumeFlow();
            flow.setId(Integer.valueOf(flowId));
            flow.setInterviewTime(Timestamp.valueOf(interviewSchedule.getInterviewTime()));
            flow.setInterviewLocation(interviewSchedule.getInterviewLocation());
            hrResumeFlowDao.updateByPrimaryKeySelective(flow);
            result.put("flag", true);
            result.put("message", "安排面试编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "安排面试编辑失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }



}
