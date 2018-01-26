package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.front.entity.FastDeliveryEditBase;
import com.human.jzbTest.dao.JzbPaperConfigDetailDao;
import com.human.jzbTest.dao.jzbPaperMainConfigDao;
import com.human.jzbTest.entity.JzbConfig;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbPaperConfigDetail;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.jzbTest.service.jzbPaperConfigDetailService;
import com.human.jzbTest.service.jzbPaperMainConfigService;
import com.human.utils.Common;
import com.human.utils.PageView;
@Service
public class jzbPaperConfigDetailServiceImpl implements jzbPaperConfigDetailService {
    @Resource 
    private JzbPaperConfigDetailDao jzb;
    
    @Autowired
    private JzbDeptService deptService;
    @Resource 
    private jzbKnowledgePointService jzbknowledgeService;
    
    @Autowired 
    private JzbQuestionService jzbquestionService;
    
    @Resource 
    private jzbKnowledgePointService jzbknowledgePointService;
    
    @Override
    public int insert(JzbPaperConfigDetail jzbpaperconfigdetail) {
        // TODO Auto-generated method stub
        return jzb.insert(jzbpaperconfigdetail);
    }
    @Override
    public List<JzbPaperConfigDetail> selectByMonth(Integer id) {
        // TODO Auto-generated method stub
        return jzb.selectByMonth( id);
    }
    @Override
    public List<Map<String, Object>> searchZsdDetail(Integer id, Integer month, Integer knowledgeId) {
        Map<String, Object> map=new HashMap<>();
        map.put("id", id);
        map.put("month", month);
        map.put("knowledgeId", knowledgeId);
        return jzb.searchZsdDetail(map);
    }
    @Override
    public List<Map<String, Object>> selectKnowledge(String month, int mainConfigId,String subject,String classType,String grade) {
        Map<String, Object> map=new HashMap<>();
        map.put("month", month);
        map.put("id", mainConfigId);
        deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr());
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        JzbPaperConfigDetail jzbKey=jzb.selectforkey(map);
        List<Map<String, Object>>  jzblist=jzb.selectKnowledge(map);
        Map<String, Object> tk=jzb.queryForTkMonth(map);
        String tkMonth=(String) tk.get("tkMonth");
        String[] mon=tkMonth.split(",");
        String questionId="";
        for (String tkmonth : mon) {
           List<Integer> questionIdList=jzbquestionService.selectForQuestion(tkmonth,subject,classType,grade,String.valueOf(deptId));
           for (Integer qId : questionIdList) {
               if(questionId.indexOf(qId)==-1){
                   questionId+=String.valueOf(qId)+",";
               }
        }
        }
        List<jzbKnowledgePoint> jzbpoint=jzbknowledgePointService.selectCount(questionId);
        //查询出月份表中的tkmonth
        for (Map<String, Object> map2 : jzblist) {
            //查询出每条记录的最大可配置数量
            int knowledgeId=(int)map2.get("ID");
            int count =0;
            for (jzbKnowledgePoint jzbKnowledgePoint : jzbpoint) {
                if(knowledgeId==jzbKnowledgePoint.getId()){
                     count=jzbKnowledgePoint.getCount();
                }
                Map<String, Object> idMap=new HashMap<>();
                idMap.put("knowledgeId", knowledgeId);
                idMap.put("monthId", jzbKey.getId());
                List<Map<String, Object>> list=jzb.selectDictKnowledge(idMap);
                map2.put("list", list);
                map2.put("count",count);
            }
//            Integer count =jzbknowledgeService.selectMaxConfigNum(knowledgeId);
        }
        return jzblist;
    }
    @Override
    public List<Map<String, Object>> queryByCondition(String month, int mainConfigId, int knowledgeId) {
        Map<String, Object> map =new HashMap<>();
        map.put("month", month);
        map.put("mainConfigId", mainConfigId);
        map.put("knowledgeId", knowledgeId);
        return jzb.queryByCondition(map);
    }
    @Override
    public void delete(int mainConfigId,String month) {
        Map<String, Object> map=new HashMap<>();
        map.put("mainConfigId", mainConfigId);
        map.put("month", month);
        jzb.delete(map);
        
    }
    @Override
    public List<Map<String, Object>> searchZsd(String month, Integer id) {
        Map<String, Object> map=new HashMap<>();
        map.put("month", month);
        map.put("id", id);
        return jzb.searchZsd(map);
    }
    @Override
    public Map<String, Object> getMonthId(int mainConfigId, String paperMonth) {
        Map<String, Object> map=new HashMap<>();
        map.put("paperMonth", paperMonth);
        map.put("mainConfigId", mainConfigId);
        return jzb.getMonthId(map);
    }
    @Override
    public int deleteByMonthId(int monthId) {
        Map<String, Object> map=new HashMap<>();
        map.put("monthId", monthId);
        return jzb.deleteByMonthId(map);
    }
    

}
