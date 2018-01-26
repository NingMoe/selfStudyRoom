package com.human.xdfStudent.threadJob;

import java.util.List;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.human.continuedClass.threadJob.InsertBatchRecommendClassThread;
import com.human.xdfStudent.dao.XdfStudentInfoDao;
import com.human.xdfStudent.entity.XdfStudentInfo;

/**
 * 插入新东方学员线程类
 * @author liuwei
 */
@Component("batchInsertXdfStudentThread")
public class BatchInsertXdfStudentThread implements Callable<Boolean>{

    private final  Logger logger = LogManager.getLogger(InsertBatchRecommendClassThread.class);
    
    private XdfStudentInfoDao  xstDao;
    
    private List<XdfStudentInfo> subList;
        
           
    public BatchInsertXdfStudentThread() {}
    
    public BatchInsertXdfStudentThread(XdfStudentInfoDao  xstDao,List<XdfStudentInfo> subList){
        this.xstDao=xstDao;
        this.subList = subList;
    }
    
    
    @Override
    public Boolean call() throws Exception {
        logger.info("-------插入新东方学员数据开始---------");
        Boolean flag = Boolean.FALSE;
        try {
            insertBatchXdfStudent(subList);
            flag = Boolean.TRUE;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("插入新东方学员数据数据出错！", e.getMessage());
        } 
        logger.info("-------插入新东方学员数据结束---------");
        return flag;
    }

    
    /**
     * 每个线程批量插入新东方学员公用方法
     */
     public void insertBatchXdfStudent(List<XdfStudentInfo>list){
         xstDao.insertByBatch(list);
     }
}
