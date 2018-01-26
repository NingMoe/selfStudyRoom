package com.human.examineelist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.basic.entity.DicData;
import com.human.datamanger.entity.DataManger;
import com.human.examineelist.dao.ExamineeListDao;
import com.human.examineelist.dao.AddExcleDao;
import com.human.examineelist.entity.ExamineeList;
import com.human.examineelist.entity.ExamineeList2;
import com.human.examineelist.service.ExamineeListService;
import com.human.stuexam.dao.StuExamDao;
import com.human.stuexam.entity.StuExam;
import com.human.stuexam.service.StuExamService;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class ExamineeListServiceImpl implements ExamineeListService {
    private final  Logger logger = LogManager.getLogger(ExamineeListServiceImpl.class);
    
    @Resource
    private ExamineeListDao exDao;
    
    
    /**
     * 分页查询
     */
    @Override
    public PageView query(PageView pageView, ExamineeList se) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", se);
        List<ExamineeList> list = exDao.query(map);
        for (ExamineeList e : list) {
          int num=exDao.getKsNum( e.getCode());
            if(num >0){
                e.setStatus(1); 
            }
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public int insertIntoStu(Map<String, Object> map) {
        return exDao.insertIntoStu(map);
    }

    @Override
    public int delete() {
        return exDao.deleteAll();
    }

    @Override
    public List<ExamineeList> queryData(ExamineeList em) {
        return exDao.queryData(em);
    }

    @Override
    public  void editDicData(String  classCode,String code,List<ExamineeList> datas) {
        Map<String , Object> map=new HashMap<>();
        map.put("classCode", classCode);
        map.put("code", code);
        //删除所选学生成绩
        if(datas!=null && datas.size()>0){
            for (ExamineeList examineeList : datas) {
                long id =examineeList.getId();
                if(id<100000000){
                    map.put("id", id);
                    exDao.deleteById(map);
                }
            }
            
            Map<String , Object> map2 =new HashMap<String,Object>();
            String time = null ;
            for(ExamineeList data:datas){
                if(data.getCode()==null||data.getClassCode()==null){
                    data.setCode(code);
                    data.setClassCode(classCode);
                }
               //获取查询条件 去考试表中查询,将获取的时间插入到学生成绩表中
                 String stage=data.getStage() ;
                 String type_1=data.getName();
                 String frequery=data.getFrequency();
                 map2.put("stage", stage);
                 map2.put("type_1", type_1);
                 map2.put("frequery", frequery);
                 map2.put("classCode", classCode);
                 List<ExamineeList> list2 =exDao.queryForTime(map2);
                 for (ExamineeList examineeList : list2) {
                     time=examineeList.getTime();
                }
                 data.setTime(time);
                 time="";
                exDao.insert(data);
            }
        }
    }

    @Override
    public List<ExamineeList> queryByCode(Map<String, Object> code) {
        return exDao.queryByCode(code);
    }

    @Override
    public List<ExamineeList> queryforClassCode(String code) {
        // TODO Auto-generated method stub
        return exDao.queryforclassCode(code);
    }

    @Override
    public List<ExamineeList> queryForTime(Map<String, Object> map2) {
        // TODO Auto-generated method stub
        return exDao.queryForTime(map2);
    }

    @Override
    public List<ExamineeList> queryForGrade(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return exDao.queryForGrade(map);
    }

    @Override
    public void delete(String deleteIds) {
        String[] ids = deleteIds.split(",");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", ids);
        // TODO Auto-generated method stub
         exDao.deleteByCode(map);
    }

    @Override
    public Map<String, Object> queryinfo(String code) {
        // TODO Auto-generated method stub
        return exDao.queryinfo(code);
    }

    @Override
    public List<ExamineeList> queryClassCode(String classCode) {
        // TODO Auto-generated method stub
        return exDao.queryClassCode(classCode);
    }

    @Override
    public List<ExamineeList> queryByCodeTotal(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return exDao.queryByCodeTotal(map);
    }

    @Override
    public String getStudentName(String code) {
        // TODO Auto-generated method stub
        return exDao.getStudentName(code);
    }


}
