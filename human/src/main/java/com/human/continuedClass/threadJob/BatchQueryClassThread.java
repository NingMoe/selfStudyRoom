package com.human.continuedClass.threadJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.human.continuedClass.dao.ClassMatchDao;
import com.human.continuedClass.dao.ClassStudentsDao;
import com.human.continuedClass.dao.CombinationClassDao;
import com.human.continuedClass.dao.RecommendClassDao;
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.ClassStudents;
import com.human.continuedClass.entity.CombinationClass;
import com.human.continuedClass.entity.RecommendClass;
import com.human.utils.HttpClientUtil;
import com.human.utils.TimeUtil;


/**
 * 更新学员班级线程类
 * @author liuwei
 *
 */
@Component("batchQueryClassThread")
public class BatchQueryClassThread implements Callable<Boolean> {

    private final  Logger logger = LogManager.getLogger(BatchQueryClassThread.class);
    
    private ClassStudentsDao csDao;
        
    private List<String> subList;
        
    private ClassInformation cf;
    
    public BatchQueryClassThread() {}
    
    public BatchQueryClassThread(ClassStudentsDao csDao,List<String> subList,ClassInformation cf){
        this.csDao=csDao;
        this.subList = subList;
        this.cf=cf;
    }
    
    

    @Override
    public Boolean call() {
        logger.info("-------更新学员-班级数据开始---------");
        Boolean flag = Boolean.FALSE;
        try {
            batchQueryClass(subList,cf);
            flag = Boolean.TRUE;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("更新学员-班级数据出错！", e.getMessage());
        } 
        logger.info("-------更新学员-班级数据结束---------");
        return flag;
    }
       
    /**
     * 每个线程批量更新学员-班级公用方法
     */
     public void batchQueryClass(List<String>codeList,ClassInformation cf){
         String classStartDate=cf.getClassStartDate();
         long ruleId=cf.getRuleId();
         List<Long>idsList=new ArrayList<Long>(100);
         for(String code :codeList){
             //第一步：通过学员号获取学员未开课的已报班记录公用方法
             List<Map<String, String>>list=getClassByStudentCode(code,classStartDate);
             if(list==null ||list.size()<=0){
                 continue;
             }
             //第二步：通过学员号获取学员原班记录
             ClassStudents cs=new ClassStudents();
             cs.setCode(code);
             cs.setRuleId(ruleId);
             List<ClassStudents> csList=csDao.getAllClass(cs);
             if(csList==null ||csList.size()<=0){
                 continue;
             }
             //第三步：获取学员未开课的已报班的班号所代表的科目,取班号前2位字母(如果班号以Z6开头则取第3、4两位)
             List<String>subject=new ArrayList<String>();
             for(Map<String, String> resultMap:list){
                String classCode=resultMap.get("classCode").trim();
                if(classCode.indexOf("Z6")!=-1){
                    classCode=classCode.substring(2,4);
                }else{
                    classCode=classCode.substring(0,2);
                }
                subject.add(classCode);   
             }  
             //第四步：过滤
             for(ClassStudents classStudent:csList){
                 String classCode=classStudent.getClassCode().trim();
                 if(classCode.indexOf("Z6")!=-1){
                     classCode=classCode.substring(2,4);
                 }else{
                     classCode=classCode.substring(0,2);
                 }
                 if(subject.contains(classCode)){//命中
                     idsList.add(classStudent.getId());
                 }
             } 
         }
         //第五步：批量更新
         if (idsList != null && idsList.size() > 0) {
             int totalcount = idsList.size();
             int pagecount = 0, pagesize = 5000;
             int m = totalcount % pagesize;
             if (m > 0) {
                 pagecount = totalcount / pagesize + 1;
             }else {
                 pagecount = totalcount / pagesize;
             }
             for (int i = 1; i <= pagecount; i++) {
                 List<Long> subList = new ArrayList<Long>(5000);
                 if (i == pagecount) {
                     subList = idsList.subList((i - 1) * pagesize, totalcount);
                 }else {
                     subList = idsList.subList((i - 1) * pagesize, pagesize * (i));
                 }
                 Map<String, Object> paraMap = new HashMap<String, Object>();
                 paraMap.put("ids", subList);
                 csDao.updateStudentByIds(paraMap);  
             }
         }
     }
     
     /**
      * 通过学员号获取学员未开课的已报班记录公用方法
      * @return
      */
     @SuppressWarnings("unchecked")
     public List<Map<String, String>> getClassByStudentCode(String code,String classStartDate){
         List<Map<String, String>>list=new ArrayList<Map<String, String>>();
         String getStudentCodeUrl = "http://wxapidata.xdf.cn/1/wechat/student/classes?accessToken=ef4e3a2b-ae21-4cad-ada8-86ec46a8a83g&appId=702&schoolId=25&studentCode="+code;
         String result = HttpClientUtil.httpGetRequest(getStudentCodeUrl, null);
         Map<String, Object> jsonObj = JSONObject.parseObject(result);
         Map<String, Object> m =(Map<String, Object>)jsonObj.get("classesByStatus");
         if(null == m) {
            return list;
         }                    
         List<Map<String, String>> resultList = (List<Map<String, String>>)m.get("3");
         if(resultList!=null){
             for(Map<String, String> resultMap:resultList){
                 String date=resultMap.get("classStartDate");
                 //比较开课日期
                 int a=TimeUtil.getTimestamp(date).compareTo(TimeUtil.getTimestamp(classStartDate));
                 if(a>=0){
                     list.add(resultMap);
                 } 
             }          
         }
         return list;        
     }
}
