package com.human.utils.mailUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import com.human.basic.dao.ResumeKeywordDao;
import com.human.basic.dao.ResumeModularDao;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.utils.Common;
import com.human.utils.ReduceHtmlTextUtil;
import com.human.utils.TimeUtil;

@Component("newReadLieTouResumeUtil")
public class NewReadLieTouResumeUtil extends CommonReadResumeUtil {

    private  static Logger logger = LogManager.getLogger(NewReadLieTouResumeUtil.class);
    
    public NewReadLieTouResumeUtil(){}
    
    public NewReadLieTouResumeUtil(ResumeModularDao rmDao, ResumeKeywordDao rkDao) {
        super(rmDao,rkDao);
    }
    
    @Override
    public List<Object> dealHtmlResumeByResource(File input) {
        List<Object>list=EncapsulationResumeUtil.getResumeObject();
        boolean flag=true;
        try {
            Document doc = Jsoup.parse(input, "utf-8");
            /*
             * 获取HTML的编码格式
             */
            String content=doc.select("meta").eq(0).first().attr("content");
            String charset=content.split(";")[1].trim().split("=")[1];
            if(!"utf-8".equalsIgnoreCase(charset)){
                doc = Jsoup.parse(input, charset); 
            }           
            String contentText1 = ReduceHtmlTextUtil.removeHtmlTag(doc.toString());
            System.out.println(contentText1);
            String contentText=contentText1.replace("*", "");
            System.out.println(contentText);
            List<String>resumeModularKeyWords=super.getResumeModularKeyWords("4");
            /*<!-- 基本信息 -->*/
            analysisBaseInformation(contentText,list,resumeModularKeyWords);
            /*<!-- 求职意向 -->*/
            analysisExpectWork(contentText,list,resumeModularKeyWords);
            /*<!-- 工作经历 -->*/
            analysisWorkHistory(contentText1,list,resumeModularKeyWords);
            /*<!-- 项目经历 -->*/
            analysisProjectExperience(contentText,list,resumeModularKeyWords);
            /*<!-- 教育经历 -->*/
            analysisEducationHistory(contentText,list,resumeModularKeyWords);
            /*<!-- 自我评价 -->*/
            analysisEvaluation(contentText,list,resumeModularKeyWords);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历出错！", e.getMessage());
            flag=false;
        }
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        //姓名或手机任何一个没有解析出来，则为解析失败
        if(StringUtils.isEmpty(resumeBase.getName()) || StringUtils.isEmpty(resumeBase.getTelephone())){
            flag=false;
        }
        list.add(14,flag);
        return list;
    }

    /**
     * 简析简历中的基本信息模块
     * @throws Exception 
     */
    public  void analysisBaseInformation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        ResumeIntention resumeIntention=(ResumeIntention) list.get(4);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"基本信息",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                //基本信息模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=super.getResumeKeyWords("4","基本信息");
                //查找姓名
                String name=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"姓名",keyWords);
                resumeBase.setName(name);
                resumeSeeker.setName(name);
                //查找工作年限
                String working_time=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作年限",keyWords);
                resumeSeeker.setWorkTime(working_time);
                resumeBase.setWorkingTime(working_time); 
                //查找联系电话
                String telephone=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"联系电话",keyWords);
                if(Common.isMobile(telephone)){
                    resumeSeeker.setPhone(telephone);
                    resumeBase.setTelephone(telephone); 
                }
                //查找性别
                String sex=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"性别",keyWords);
                if(sex.indexOf("男")!=-1){
                    resumeSeeker.setSex("M");
                    resumeBase.setSex("M");
                }else if(sex.indexOf("女")!=-1){
                    resumeSeeker.setSex("F");
                    resumeBase.setSex("F");
                }
                //查找邮箱
                String email=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"电子邮件",keyWords);
                resumeSeeker.setEmail(email);
                resumeBase.setEmail(email);  
                //查找求职状态
                String currentStatus=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"职业状态",keyWords);
                resumeIntention.setCurrentStatus(currentStatus);  
                //查找户口
                String household_register=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"户籍",keyWords);                
                resumeBase.setHouseholdRegister(household_register); 
                System.out.println("姓名："+name+"\n"+"性别："+sex+"\n"+"工作年限："+working_time+"\n"+
                                   "联系电话："+telephone+"\n"+"邮箱："+email+"\n"+"户口："+household_register+"\n");                 
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }       
    }

        
    /**
     * 简析简历中的求职意向模块
     * @throws Exception 
     */
    public  void analysisExpectWork(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"求职意向",resumeModularKeyWords);
            //求职意向模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("4","求职意向");    
            //查找月薪要求
            String expectWorkSalary=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望年薪",keyWords);
            resumeIntention.setExpectWorkSalary(expectWorkSalary);
            //查找行业要求
            String expectWorkIndustry=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望从事行业",keyWords);
            resumeIntention.setExpectWorkIndustry(expectWorkIndustry);
            //查找职能意向
            String expectWorkJob=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望从事职业",keyWords);
            resumeIntention.setExpectWorkJob(expectWorkJob);
            //查找工作地点
            String expectWorkPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望工作地点",keyWords);
            resumeIntention.setExpectWorkPlace(expectWorkPlace);
            System.out.println("月薪要求："+expectWorkSalary+"\n"+"行业要求："+expectWorkIndustry+"\n"+"职能意向："+expectWorkJob+"\n"+"工作地点："+expectWorkPlace+"\n"); 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的求职意向信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }           
    }
    
    /**
     * 简析简历中的工作经历模块
     * @throws Exception 
     */
    public  void analysisWorkHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeWorkHistory> resumeWorkHistoryList=(List<ResumeWorkHistory>) list.get(12);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作经历",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}.[0-9]{1,2}-";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(contentText);
                Map<String,Integer>map=new HashMap<String,Integer>();
                int j=1;
                while(m.find()){
                    map.put("start"+j, m.start());
                    j++;
                }
                int start,end=0;
                //工作经历模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=super.getResumeKeyWords("4","工作经历");
                for(int i=1;i<j;i++){
                    ResumeWorkHistory resumeWorkHistory=new ResumeWorkHistory();
                    start=map.get("start"+i);//某一段工作经历的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段工作经历的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段工作经历的文本
                    //截取开始时间和结束时间
                    int index1=contextValue.indexOf("-");
                    String start_time=contextValue.substring(0,index1);
                    System.out.println("开始时间："+start_time);
                    String patten="yyyy.MM";
                    resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                    String end_time="";
                    int index=0;
                    end_time=contextValue.substring(index1+1,index1+8).trim();
                    regEx_style="[0-9]{4}.[0-9]{2}";
                    r = Pattern.compile(regEx_style);
                    m = r.matcher(end_time);
                    if(m.find()){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                        index=index1+8;
                    }else{
                        end_time=contextValue.substring(index1+1,index1+7).trim();
                        regEx_style="[0-9]{4}.[0-9]{1}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+7;
                        }else if(end_time.indexOf("至今")!=-1){
                            index=index1+3;
                        }
                    }
                    System.out.println("结束时间："+end_time);
                    //截取公司名称、工作职位等信息
                    end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue, keyWords);
                    if(end!=-1){
                        String workCompanyAndPosition=contextValue.substring(index, end);
                        workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeLastSymbol(workCompanyAndPosition);
                        workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workCompanyAndPosition);
                        String[] ttt= workCompanyAndPosition.split("\\*");
                        String workCompany=ttt[0];    
                        System.out.println("公司名称："+workCompany);
                        resumeWorkHistory.setCompanyName(workCompany);
                        String positionName=ttt[ttt.length-1];
                        System.out.println("工作职位："+positionName);
                        resumeWorkHistory.setPosition(positionName);
                    }
                    //查找工作描述
                    String describe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"职责业绩",keyWords);
                    describe=SearchFiledByKeyWordsUtil.removeSpecialSymbol(describe).replace("*", "\n");
                    System.out.println("工作描述："+describe);
                    resumeWorkHistory.setDescribes(describe);    
                    resumeWorkHistoryList.add(resumeWorkHistory);
                }
            } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的工作经历信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }   
 }
    
    
    /**
     * 简析简历中的项目经验模块
     * @throws Exception 
     */
    public  void analysisProjectExperience(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeProjectExperience> resumeProjectExperienceList=(List<ResumeProjectExperience>) list.get(9);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"项目经历",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}.[0-9]{1,2}-";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(contentText);
                Map<String,Integer>map=new HashMap<String,Integer>();
                int j=1;
                while(m.find()){
                    map.put("start"+j, m.start());
                    j++;
                }
                int start,end=0;
                //项目经验模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=super.getResumeKeyWords("4","项目经历");
                for(int i=1;i<j;i++){
                    ResumeProjectExperience rpe=new ResumeProjectExperience();
                    start=map.get("start"+i);//某一段项目经验的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段项目经验的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段项目经验的文本
                    //截取开始时间和结束时间
                    int index1=contextValue.indexOf("-");
                    String start_time=contextValue.substring(0,index1);
                    System.out.println("开始时间："+start_time);
                    String patten="yyyy.MM";
                    rpe.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                    String end_time="";
                    int index=0;
                    if(contextValue.length()>(index1+8)){
                        end_time=contextValue.substring(index1+1,index1+8).trim();
                        regEx_style="[0-9]{4}.[0-9]{2}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+8;
                        }else{
                            end_time=contextValue.substring(index1+1,index1+7).trim();
                            regEx_style="[0-9]{4}.[0-9]{1}";
                            r = Pattern.compile(regEx_style);
                            m = r.matcher(end_time);
                            if(m.find()){
                                rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                                index=index1+7;
                            }else if(end_time.indexOf("至今")!=-1){
                                index=index1+3;
                            }
                        }
                    }else if(contextValue.length()>(index1+7)){
                        end_time=contextValue.substring(index1+1,index1+7).trim();
                        regEx_style="[0-9]{4}.[0-9]{1}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+7;
                        }else if(end_time.indexOf("至今")!=-1){
                            index=index1+3;
                        }
                    }else{
                        end_time=contextValue.substring(index1+1,index1+3).trim();
                        if(end_time.indexOf("至今")!=-1){
                            index=index1+3;
                        }
                    }
                    System.out.println("结束时间："+end_time);
                    //截取项目名称
                    end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
                    if(end!=-1){
                        String projectName=contextValue.substring(index, end);    
                        rpe.setProjectName(projectName);                  
                    }
                    //查找项目职位
                    String projectPosition=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"项目职务",keyWords); 
                    System.out.println("项目职位："+projectPosition);
                    rpe.setProjectPosition(projectPosition);
                    //查找所属公司
                    String companyName=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"所在公司",keyWords); 
                    System.out.println("所属公司："+companyName);
                    rpe.setCompanyName(companyName);
                    //查找项目描述
                    String projectDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"项目简介",keyWords); 
                    System.out.println("项目描述："+projectDescribe);
                    rpe.setProjectDescribe(projectDescribe);
                    //查找责任描述
                    String responsibilityDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"项目职责",keyWords);
                    System.out.println("责任描述："+responsibilityDescribe);
                    rpe.setResponsibilityDescribe(responsibilityDescribe);
                    resumeProjectExperienceList.add(rpe); 
                }   
            }            
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的项目经验信息出错！", e.getMessage());
            throw  new Exception(e);
        }

    }
    
    /**
     * 简析简历中的教育经历模块
     * @throws Exception 
     */
    public  void analysisEducationHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeEducationHistory> resumeEducationHistoryList=(List<ResumeEducationHistory>) list.get(3);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"教育经历",resumeModularKeyWords);
            String regEx_style="[0-9]{4}.[0-9]{1,2}-";
            Pattern r = Pattern.compile(regEx_style);
            Matcher m = r.matcher(contentText);
            Map<String,Integer>map=new HashMap<String,Integer>();
            int j=1;
            while(m.find()){
                map.put("start"+j, m.start());
                j++;
            }
            int start,end=0;
            //教育经历模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("4","教育经历");
            for(int i=1;i<j;i++){
                ResumeEducationHistory reh=new ResumeEducationHistory();
                start=map.get("start"+i);//某一段教育经历的开始位置
                if((i+1)==j){
                    end=contentText.length();
                }else{
                    end=map.get("start"+(i+1));//某一段教育经历的结束位置 
                }                    
                String contextValue=contentText.substring(start, end);//某段教育经历的文本
                //截取开始时间和结束时间
                int index1=contextValue.indexOf("-");
                String start_time=contextValue.substring(0,index1);
                System.out.println("开始时间："+start_time);
                String patten="yyyy.MM";
                reh.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                String end_time="";
                int index=0;
                end_time=contextValue.substring(index1+1,index1+8).trim();
                regEx_style="[0-9]{4}.[0-9]{2}";
                r = Pattern.compile(regEx_style);
                m = r.matcher(end_time);
                if(m.find()){
                    reh.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    index=index1+8;
                }else{
                    end_time=contextValue.substring(index1+1,index1+7).trim();
                    regEx_style="[0-9]{4}.[0-9]{1}";
                    r = Pattern.compile(regEx_style);
                    m = r.matcher(end_time);
                    if(m.find()){
                        reh.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                        index=index1+7;
                    }else if(end_time.indexOf("至今")!=-1){
                        index=index1+3;
                    }
                }
                System.out.println("结束时间："+end_time);
                //截取大学名称
                end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
                String  schoolName="";
                if(end!=-1){
                    schoolName=contextValue.substring(index,end).replaceAll("-","");                            
                }
                System.out.println("学校名称："+schoolName); 
                reh.setSchoolName(schoolName);   
                //查询学历
                String education=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"学历",keyWords);
                System.out.println("学历："+education); 
                reh.setEducation(education);
                //查询专业
                String major=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"专业",keyWords);
                System.out.println("专业名称："+major); 
                reh.setMajor(major);
                resumeEducationHistoryList.add(reh); 
            }                   
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的教育经历信息出错！", e.getMessage());
            throw  new Exception(e);
        }       
    }
                    
    /**
     * 简析简历中的自我评价模块
     */
    public  void analysisEvaluation(String contentText,List<Object>list,List<String>resumeModularKeyWords){
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"自我评价",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                int index=contentText.indexOf("目标人选");
                String evaluation="";
                if(index!= -1){
                    evaluation=contentText.substring(0, index).trim();                   
                }else{
                    evaluation=contentText.trim(); 
                }
                resumeBase.setEvaluation(evaluation);
                System.out.println("自我评价："+evaluation+"\n");
            }  
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析猎聘网简历中的自我评价信息出错！", e.getMessage()); 
        }               
    }        
}
