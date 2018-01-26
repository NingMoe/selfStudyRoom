package com.human.examineelist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.basic.entity.DicData;
import com.human.datamanger.entity.DataManger;
import com.human.examineelist.dao.ExamineeListDao;
import com.human.examineelist.dao.AddExcleDao;
import com.human.examineelist.entity.ExamineeList;
import com.human.examineelist.entity.ExamineeList2;
import com.human.examineelist.service.AddExcleService;
import com.human.examineelist.service.ExamineeListService;
import com.human.stuexam.dao.StuExamDao;
import com.human.stuexam.entity.StuExam;
import com.human.stuexam.service.StuExamService;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class AddExcleServiceImpl implements AddExcleService {
    private final  Logger logger = LogManager.getLogger(AddExcleServiceImpl.class);
    
    @Resource
    private AddExcleDao exDao2;
    
    /**
     * 导入excel
     */
    @SuppressWarnings("all")
    @Transactional
    @Override
    public Map<String, Object> upexcel(HttpServletRequest request,String classCode, String code){
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取上传的excel
        logger.info("数据管理->上传数据:获取excel开始");
        
        
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<ExamineeList2> ex=new ExcelUtil<ExamineeList2>(1,0);
            //获取数据管理数据
            Map<String,Object> empTeachMap=ex.checkAccount(file,ExamineeList2.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                return empTeachMap;
            }
            List<ExamineeList2> list=(List<ExamineeList2>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            for(ExamineeList2 exam : list){
                exam.setClassCode(classCode);
                exam.setCode(code);
                exam.setDeptName("北美项目部");
                String time=exDao2.queryTime(exam);
                if(time!=null&&time!=""){
                    exam.setTime(time);
                }
                exDao2.insertInto(exam);
            }
            flag=true;
            msg="导入成功,成功导入"+list.size()+"条数据";
        }catch(Exception e){
            e.printStackTrace();
            logger.error("导入异常行："+index);
           throw new RuntimeException(e);
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            map.put("flag", flag);
            map.put("message", msg);
            logger.info("数据管理->上传数据:获取excel结束");
            return map;
        }
    }
}
