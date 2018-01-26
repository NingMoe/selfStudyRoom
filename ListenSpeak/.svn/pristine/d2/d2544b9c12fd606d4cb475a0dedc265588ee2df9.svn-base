package com.ls.spt.zuoye.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.dao.LstStudentZuoyeDao;
import com.ls.spt.zuoye.dao.LstZuoyeReportDao;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;
import com.ls.spt.zuoye.service.LstZuoyeReportService;

@Service
public class LstZuoyeReportServiceImpl implements LstZuoyeReportService{
    @Resource LstZuoyeReportDao lrDao;
    
    @Override
    public int getTjNum(LstStudentZuoye lz, String zs) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("t", lz);
        map.put("zs", zs);
        return lrDao.getTjNum(map);
    }

    @Override
    public  List<Map<String, Object>> getZuoyeDfl(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getZuoyeDfl(lz);
    }

    @Override
    public List<Map<String, Object>> getZuoyeSitu(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getZuoyeSitu(lz);
    }

    @Override
    public List<Map<String, Object>> getQuestionCodes(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getQuestionCodes(lz);
    }

    @Override
    public List<Map<String, Object>> getQuestions(String code, Integer zuoye_id) {
        Map<String, Object> map=new HashMap<>();
        map.put("code", code);
        map.put("zuoye_id", zuoye_id);
        // TODO Auto-generated method stub
        return lrDao.getQuestions(map);
    }

    @Override
    public Map<String, Object> getScore(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getScore(lz);
    }

    @Override
    public List<Map<String, Object>> getZuotiSitu(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getZuotiSitu(lz);
    }

    @Override
    public  Map<String, Object> getzuoyeInfo(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getzuoyeInfo(lz);
    }

    @Override
    public   Map<String, Object> getStudentZuoyeByCode(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getStudentZuoyeByCode(lz);
    }

    @Override
    public List<Map<String, Object>> getStudentQuestion(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getStudentQuestion(lz);
    }

    @Override
    public Map<String, Object> totalScoreList(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.totalScoreList(lz);
    }

    @Override
    public List<Map<String, Object>> getGrowthData(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getGrowthData(lz);
    }

    @Override
    public Map<String, Object> getScoreForStudent(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getScoreForStudent(lz);
    }

    @Override
    public int beat(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.beat(lz);
    }

    @Override
    public int totalStudent(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.totalStudent(lz);
    }

    @Override
    public List<Map<String, Object>> pgNum(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.pgNum(lz);
    }

    @Override
    public Map<String, Object> getavgScore(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getavgScore(lz);
    }

    @Override
    public List<Map<String, Object>> getStudentLevel(LstStudentZuoye lz) {
        // TODO Auto-generated method stub
        return lrDao.getStudentLevel(lz);
    }

    
    
}
