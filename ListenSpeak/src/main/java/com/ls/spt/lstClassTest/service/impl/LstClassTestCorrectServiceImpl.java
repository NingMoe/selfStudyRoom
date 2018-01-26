package com.ls.spt.lstClassTest.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClassTest.dao.LstClassTestCorrectDao;
import com.ls.spt.lstClassTest.entity.LstClassTestCorrect;
import com.ls.spt.lstClassTest.service.LstClassTestCorrectService;
import com.ls.spt.utils.Common;


@Service
public class LstClassTestCorrectServiceImpl implements LstClassTestCorrectService {
   
    @Resource
    LstClassTestCorrectDao lctcDao;
    @Override
    public PageView query(PageView pageView, LstClassTestCorrect lctc) {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("t",lctc);
        map.put("paging", pageView);
        int teacherId=Common.getMyUser().getUserid();
        map.put("teacherId", teacherId);
        List<LstClassTestCorrect> list =lctcDao.query(map);
        List<LstClassTestCorrect> result=new ArrayList<LstClassTestCorrect>();
        for (LstClassTestCorrect lst : list) {
            map.put("classCode", lst.getClassCode());
            map.put("testId", lst.getTestId());
            String status=String.valueOf(lst.getPgNum())+"/"+String.valueOf(lst.getAllStudent());
            lst.setStatus(status);
            if(lst.getAllStudent()-lst.getPgNum()==lst.getNotjNum()){
                if(lst.getOverallTeacher()>0){
                    lst.setOverallDfl(decmailFormat(lst.getOverallTeacher()*10)+"%");
                }else{
                    lst.setOverallDfl("0%");
                }
                result.add(lst);
            }
        }
        pageView.setData(result);
        return pageView;
    }
    @Override
    public List<LstClassTestCorrect> queryCorrect() {
        Map<String, Object> map=new HashMap<>();
        int teacherId=Common.getMyUser().getUserid();
        map.put("teacherId", teacherId);
        List<LstClassTestCorrect> list =lctcDao.queryCorrect(map);
        List<LstClassTestCorrect> result=new ArrayList<LstClassTestCorrect>();
        for (LstClassTestCorrect lst : list) {
            map.put("classCode", lst.getClassCode());
            map.put("testId", lst.getTestId());
//            LstClassTestCorrect score=lctcDao.getScore(map);
            int tatalNum=lctcDao.getTatalNum(map);
            int tiNum=lctcDao.getTiNum(map);
            String status=String.valueOf(tatalNum)+"/"+String.valueOf(tiNum);
            lst.setStatus(status);
//            if(score!=null){
//                lst.setAccuracyTeacher(score.getAccuracyTeacher());
//                lst.setFluencyTeacher(score.getFluencyTeacher());
//                lst.setOverallTeacher(score.getOverallTeacher());
//                lst.setIntegrityTeacher(score.getIntegrityTeacher());
//            }
            if(lst.getAllStudent()-lst.getPgNum()!=lst.getNotjNum()){
                result.add(lst);
            }
        }
        return result;
    }
    
    public static String decmailFormat(double num ){
        DecimalFormat df=new DecimalFormat(".##");
        double d=num;
        String st=df.format(d);
        return st;
    }
}
