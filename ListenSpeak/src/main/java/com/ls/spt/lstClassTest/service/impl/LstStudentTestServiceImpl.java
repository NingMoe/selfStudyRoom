package com.ls.spt.lstClassTest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ls.spt.lstClassTest.dao.LstStuTestDao;
import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.lstClassTest.service.LstStudentTestService;


@Service
public class LstStudentTestServiceImpl implements LstStudentTestService {
    @Resource
    LstStuTestDao lstDao;
    @Override
    public List<LstStudentTest> getStudentTestInfo(Integer id) {
        // TODO Auto-generated method stub
        return lstDao.getStudentTestInfo(id);
    }
    @Override
    public int insert(LstStudentTest sTest) {
        // TODO Auto-generated method stub
        return lstDao.insertSelective(sTest);
    }
    @Override
    public LstStudentTest selectPrimarykey(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return lstDao.selectByPrimaryKey(lsta.getId());
    }
    @Override
    public LstStudentTest getAvgScoreInFlAc(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return lstDao.getAvgScoreInFlAc(lsta);
    }
    @Override
    public int getTotal(LstTestStudentAnswer lsta) {
        // TODO Auto-generated method stub
        return lstDao.getTotal(lsta);
    }
    @Override
    public int overNum(LstTestStudentAnswer lsta) {
        Map<String, Object> map=new HashMap<>();
        map.put("id", lsta.getId());
        map.put("testId", lsta.getTestId());
        return lstDao.overNum(map);
    }
    
    
}
