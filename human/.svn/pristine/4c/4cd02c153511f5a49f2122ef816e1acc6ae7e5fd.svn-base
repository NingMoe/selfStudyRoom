package com.human.continuedClass.threadJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.human.continuedClass.dao.ClassMatchDao;
import com.human.continuedClass.dao.CombinationClassDao;
import com.human.continuedClass.dao.RecommendClassDao;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.CombinationClass;
import com.human.continuedClass.entity.RecommendClass;


/**
 * 插入学员-推荐班级线程类
 * @author liuwei
 *
 */
@Component("insertBatchRecommendClassThread")
public class InsertBatchRecommendClassThread implements Callable<Boolean> {

    private final  Logger logger = LogManager.getLogger(InsertBatchRecommendClassThread.class);
    
    private ClassMatchDao  cMDao;
    
    private RecommendClassDao  rCDao;
    
    private CombinationClassDao ccDao;
    
    private  List<ClassMatch> subList;
        
           
    public InsertBatchRecommendClassThread() {}
    
    public InsertBatchRecommendClassThread(ClassMatchDao  cMDao,RecommendClassDao  rCDao,CombinationClassDao ccDao,List<ClassMatch> subList){
        this.cMDao=cMDao;
        this.rCDao=rCDao;
        this.ccDao=ccDao;
        this.subList = subList;
    }
    
    

    @Override
    public Boolean call() {
        logger.info("-------插入学员-推荐班级数据开始---------");
        Boolean flag = Boolean.FALSE;
        try {
            insertBatchRecommendClass(subList);
            flag = Boolean.TRUE;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("插入学员-推荐班级数据出错！", e.getMessage());
        } 
        logger.info("-------插入学员-推荐班级数据结束---------");
        return flag;
    }
       
    /**
     * 每个线程批量插入学员-推荐班级公用方法
     */
     public void insertBatchRecommendClass(List<ClassMatch>list){
         List<RecommendClass> totalInsertList = new ArrayList<RecommendClass>(5000);
         for (ClassMatch classMatch : list) {
             // 第二步：查询出每个学员的续班数据
             List<ClassMatch> list1 = cMDao.queryClassMatch(classMatch);
             if (list1 != null && list1.size() > 0) {
                 for (ClassMatch classMatch1 : list1) {
                     // 第三步：拿学员的续班数据去套餐组合里查询
                     CombinationClass cc = ccDao.queryCombinationClass(classMatch1);
                     if (cc != null) {
                         String combinationData = cc.getCombinationData();
                         String[] classCodes = combinationData.split(",");
                         // 第四步：查询学员的推荐班级
                         Map<String, Object> queryMap = new HashMap<String, Object>();
                         queryMap.put("t", classMatch1);
                         queryMap.put("classCodes", classCodes);
                         List<RecommendClass> list3 = rCDao.selectRecommendClass2(queryMap);
                         if (list3 != null && list3.size() > 0) {
                             for (RecommendClass rc : list3) {
                                 rc.setName(classMatch1.getName());
                                 rc.setCode(classMatch1.getCode());
                             }
                             totalInsertList.addAll(list3);
                         }
                         break;
                     }
                 }
             }
         }
         // 批量插入
         if (totalInsertList != null && totalInsertList.size() > 0) {
             int totalcount = totalInsertList.size();
             int pagecount = 0, pagesize = 5000;
             int m = totalcount % pagesize;
             if (m > 0) {
                 pagecount = totalcount / pagesize + 1;
             }
             else {
                 pagecount = totalcount / pagesize;
             }
             for (int i = 1; i <= pagecount; i++) {
                 List<RecommendClass> subList = new ArrayList<RecommendClass>(5000);
                 if (i == pagecount) {
                     subList = totalInsertList.subList((i - 1) * pagesize, totalcount);
                 }
                 else {
                     subList = totalInsertList.subList((i - 1) * pagesize, pagesize * (i));
                 }
                 rCDao.insertByBatch(subList);
             }
         }
     }
}
