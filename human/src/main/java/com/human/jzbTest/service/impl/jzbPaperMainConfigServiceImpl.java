package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.basic.entity.DicData;
import com.human.basic.service.DictionaryService;
import com.human.jzbTest.dao.JzbPaperMainMessageDao;
import com.human.jzbTest.dao.JzbPaperMonthLevelDao;
import com.human.jzbTest.dao.jzbPaperMainConfigDao;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbPaperMainMessage;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.jzbTest.service.JzbDeptService;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.jzbPaperConfigDetailService;
import com.human.jzbTest.service.jzbPaperMainConfigService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
@Service
public class jzbPaperMainConfigServiceImpl implements jzbPaperMainConfigService {
    
    @Resource jzbPaperMainConfigDao jzbDao;
    
    @Resource JzbPaperMainMessageDao messDao;
    
    @Resource 
    JzbPaperMonthLevelDao moDao;
    
    @Autowired
    private JzbDeptService deptService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
    @Autowired
    private JzbGradeService gradeService;
    @Autowired
    JzbPaperMonthConfigService jzbpapermonthService;
    @Autowired
    jzbPaperConfigDetailService jzbpaperconfigdetailService;
    @Override
    public PageView query(PageView pageView, jzbPaperMainConfig jzb) {
        Map< String , Object> map =new HashMap<>();
        Integer deptId = deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getId();
        map.put("paging", pageView);
        map.put("t", jzb);
        map.put("deptId", deptId);
        List<jzbPaperMainConfig> list = jzbDao.query(map);
        List<jzbPaperMainConfig> list2=new ArrayList<jzbPaperMainConfig>();
        List<DicData> subjects = dictionaryService.getDataByKey("subject");
        List<DicData> classTypes = dictionaryService.getDataByKey("class_type");
        List<JzbGrade> grades = gradeService.selectByDeptId(Long.valueOf(deptId));
        for (jzbPaperMainConfig jzbKnowledgePoint : list) {
            String subject=jzbKnowledgePoint.getSubject();
            String classType=jzbKnowledgePoint.getClassType();
            String grade=jzbKnowledgePoint.getGrade();
            for (DicData dicData : subjects) {
                if(subject.equals(dicData.getDataValue())){
                    jzbKnowledgePoint.setSubject(dicData.getName());
                }
            }
            for (DicData dicData : classTypes) {
                if(classType.equals(dicData.getDataValue())){
                    jzbKnowledgePoint.setClassType(dicData.getName());
                }
            }
            for (JzbGrade jzbGrade : grades) {
                if(grade.equals(String.valueOf(jzbGrade.getId()))){
                    jzbKnowledgePoint.setGrade(String.valueOf(jzbGrade.getGradeName()));
                }
            }
//            jzbKnowledgePoint.setDept((int)deptService.getManageDeptByEmail(Common.getMyUser().getEmailAddr()).getName());
            list2.add(jzbKnowledgePoint);
        }
        pageView.setRecords(list2);
        return pageView;
    }

    @Override
    public int update(jzbPaperMainConfig jzb) {
        // TODO Auto-generated method stub
        return jzbDao.updateByPrimaryKey(jzb);
    }

    @Override
    public jzbPaperMainConfig selectByPrimaryKey(int id) {
        
        return jzbDao.selectByPrimaryKey(id);
    }

    @Override
    public int insert(jzbPaperMainConfig jzb) {
        // TODO Auto-generated method stub
        return jzbDao.insert(jzb);
    }

    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            jzbDao.deleteByMap(paraMap);
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
    public Map<String, Object> save(String str) {
        // TODO Auto-generated method stub
        return jzbDao.save(str);
    }
    
    @Override
    public jzbPaperMainConfig getPaperMainConfig(jzbPaperMainConfig jzb) {
        return jzbDao.getPaperMainConfig(jzb);
    }

    @Override
    public Map<String, Object> getSubjectDicData(String dicCode, String subject) {
        Map<String, Object> map=new HashMap<>();
        map.put("dicCode", dicCode);
        map.put("subject", subject);
        return jzbDao.getDicData(map);
    }

    @Override
    public jzbPaperMainConfig selectPaperInfo(jzbPaperMainConfig jzb) {
        // TODO Auto-generated method stub
        return jzbDao.selectPaperInfo(jzb);
    }
    
   @Override
    public List<DicData> getVaidClassTypeByGrade(String gradeIds) {
       String month = TimeUtil.getCurrentMonth();
       if("10".equals(month)){
           month = "A";
       }
       if("11".equals(month)){
           month = "B";
       }
       if("12".equals(month)){
           month = "C";
       }
       return jzbDao.selectValidClassType(gradeIds, month);
    }
   
    @Override
    public List<DicData> selectValidSubject(String gradeIds,String classType) {
        String month = TimeUtil.getCurrentMonth();
        if("10".equals(month)){
            month = "A";
        }
        if("11".equals(month)){
            month = "B";
        }
        if("12".equals(month)){
            month = "C";
        }
        return jzbDao.selectValidSubject(gradeIds,classType,month);
    }

    @Override
    public List<Map<String, Object>> getList(String deleteIds) {
        String[] ids = deleteIds.split(",");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("ids", ids);
        jzbDao.deleteByMap(paraMap);
        return jzbDao.getList(paraMap);
    }

    @Override
    public int deleteMonth(String deleteIds) {
        String[] ids = deleteIds.split(",");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("ids", ids);
        jzbDao.deleteByMap(paraMap);
        //删除monthlevel表中数据
        moDao.deleteMonthLevelByMainId(paraMap);
        //删除mainMessage关联表中数据
        messDao.deleteMainMessage(paraMap);
        return jzbDao.deleteMonth(paraMap);
    }

    @Override
    public List<JzbPaperMainMessage> getMessageById(Integer id) {
        // TODO Auto-generated method stub
        return messDao.getMessageById(id);
    }
    
    @Override
    public List<JzbPaperMainMessage> getMessageUseById(Integer id) {
        // TODO Auto-generated method stub
        return messDao.getMessageUseById(id);
    }
    
    @Override
    public int insertMessage(JzbPaperMainMessage me) {
        // TODO Auto-generated method stub
        return messDao.insertSelective(me);
    }

    @Override
    public int deleteMessage(int mainId) {
        // TODO Auto-generated method stub
        return messDao.deleteMessage(mainId);
    }
}
