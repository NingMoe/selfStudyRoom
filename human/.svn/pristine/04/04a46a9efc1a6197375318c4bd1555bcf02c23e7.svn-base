package com.human.resume.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.resume.dao.ResumeManagerDao;
import com.human.resume.entity.InterAppr;
import com.human.resume.service.InterviewApprovalService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class InterviewApprovalServiceImpl implements InterviewApprovalService{
    
    @Resource
    private ResumeManagerDao managerDao;

    @Override
    public PageView queryToAppr(PageView pageView, InterAppr ia) {
        Map<String, Object> map = new HashMap<String, Object>();
        ia.setApprUser(Common.getMyUser().getUsername());
        map.put("paging", pageView);
        map.put("t", ia);
        List<InterAppr> list = managerDao.queryToAppr(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public PageView queryEndAppr(PageView pageView, InterAppr ia) {
        Map<String, Object> map = new HashMap<String, Object>();
        ia.setApprUser(Common.getMyUser().getUsername());
        map.put("paging", pageView);
        map.put("t", ia);
        List<InterAppr> list = managerDao.queryEndAppr(map);
        pageView.setRecords(list);
        return pageView;
    }

}
