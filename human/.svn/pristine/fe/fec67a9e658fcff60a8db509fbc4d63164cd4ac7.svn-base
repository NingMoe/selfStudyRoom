package com.human.utils.mailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import com.human.resume.entity.ResumeCertificate;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.utils.Common;
import com.human.utils.ReduceHtmlTextUtil;
import com.human.utils.TimeUtil;

@Component("newReadZhiLianResumeUtil")
public class NewReadZhiLianResumeUtil extends CommonReadResumeUtil{


    private static  Logger logger = LogManager.getLogger(NewReadZhiLianResumeUtil.class);
    
    public NewReadZhiLianResumeUtil(){}
    
    public NewReadZhiLianResumeUtil(ResumeModularDao rmDao, ResumeKeywordDao rkDao) {
        super(rmDao,rkDao);
    }
    
    /*
     * 常见的邮箱后缀
     */
    private  static final String[] EMAILSUFFIX =new String[]{"@163.com","@126.com","@sina.com","@sohu.com","@yeah.net","@21cn.com","@139.com","@189.cn","@qq.com","@gmail.com","@hotmail.com","@tom.com","@foxmail.com","@aliyun.com","@yahoo.cn","@yahoo.com.cn","@msn.com","@msn.cn"};
    

    
	/**
	 * 解析智联招聘网的简历
	 * @param docPath  html格式的简历文件
	 */
	public  List<Object>  dealHtmlResumeByResource(File input){
	    List<Object>list=EncapsulationResumeUtil.getResumeObject();
	    boolean flag=true;
		try {
			Document doc = Jsoup.parse(input, "gb2312");
			/*
			 * 获取HTML的编码格式
			 */
			String content=doc.select("head>meta").eq(0).first().attr("content");
			String charset=content.split(";")[1].trim().split("=")[1];
			if(!"gb2312".equalsIgnoreCase(charset)){
                doc = Jsoup.parse(input, charset); 
            }
			String contentText = ReduceHtmlTextUtil.removeHtmlTag(doc.toString()).replace("*", "");
            List<String>resumeModularKeyWords=super.getResumeModularKeyWords("3");
            /*<!-- 基本信息 -->*/
            analysisBaseInformation(contentText,list,resumeModularKeyWords);   
            /*<!-- 自我评价-->*/
            analysisEvaluation(contentText,list,resumeModularKeyWords); 
            /*<!-- 求职意向 -->*/
            analysisExpectWork(contentText,list,resumeModularKeyWords); 
            /*<!-- 工作经历 -->*/
            analysisWorkHistory(contentText,list,resumeModularKeyWords);
            /*<!-- 项目经验 -->*/
            analysisProjectExperience(contentText,list,resumeModularKeyWords);
            /*<!-- 教育经历 -->*/
            analysisEducationHistory(contentText,list,resumeModularKeyWords);
            /*<!-- 培训经历 -->*/
            analysisTrainingHistory(contentText,list,resumeModularKeyWords);
            /*<!-- 证书 -->*/
            analysisCertificate(contentText,list,resumeModularKeyWords);
            /*<!-- 语言能力 -->*/
            analysisLanguageAbility(contentText,list,resumeModularKeyWords);
            /*<!-- 获得荣誉-->*/
            analysisHonour(contentText,list,resumeModularKeyWords);
            /*<!-- 专业技能-->*/
            analysisMajorSkill(contentText,list,resumeModularKeyWords);
		}catch (Exception e) {
		    e.printStackTrace();
		    logger.error("简析智联招聘网简历出错！", e.getMessage());
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
        ResumeSeeker resumeSeeker = (ResumeSeeker) list.get(0);
        ResumeBase resumeBase = (ResumeBase) list.get(1);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchTextByKeyWords(contentText,resumeModularKeyWords);
            contentText=contentText.replaceAll("[　* * * *]*", "");
            String[] text=contentText.split("\\|");
            String value=text[0];
            if(StringUtils.isNotEmpty(value)){
                String name=value.substring(0, value.length()-1);
                String sex=value.substring(value.length()-1);
                System.out.println("姓名：" + name);
                System.out.println("性别：" + sex);
                resumeBase.setName(name);
                resumeSeeker.setName(name);
                if ("女".equals(sex)) {
                    resumeSeeker.setSex("F");
                    resumeBase.setSex("F");
                }else if ("男".equals(sex)) {
                    resumeSeeker.setSex("M");
                    resumeBase.setSex("M");
                }
            }
            for(String textValue:text){
                int index=textValue.indexOf("工作经验");
                if(index!=-1){
                    String working_time = textValue.substring(0,index);
                    System.out.println("工作经验：" + working_time);
                    resumeSeeker.setWorkTime(working_time);
                    resumeBase.setWorkingTime(working_time);
                    continue;
                }
                String regEx_style="[0-9]{4}年[0-9]{1,2}月";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(textValue);
                if(m.find()){
                    String patten = "yyyy年MM月";
                    String birthDateString=textValue.substring(m.start(), m.end());
                    Date birthDate = TimeUtil.getDateByTime(birthDateString, patten);
                    System.out.println("生日：" + birthDateString);
                    resumeSeeker.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));
                    resumeBase.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));                    
                    continue;
                }
                index=textValue.indexOf("户口");
                if(index!=-1){
                    String householdRegister  = textValue.substring(0,index);
                    System.out.println("户口：" + householdRegister);
                    resumeBase.setHouseholdRegister(householdRegister);
                    String phone =textValue.substring(index+2, index+13);
                    System.out.println("手机：" + phone);
                    if(Common.isMobile(phone)){
                        resumeSeeker.setPhone(phone);
                        resumeBase.setTelephone(phone); 
                    }
                    for(String eamilsufixx:EMAILSUFFIX){
                        if(textValue.indexOf(eamilsufixx)!=-1){
                            String email=textValue.substring(index+13,textValue.indexOf(eamilsufixx))+eamilsufixx;
                            System.out.println("邮箱：" + email);
                            resumeSeeker.setEmail(email);
                            resumeBase.setEmail(email);
                            break;
                        }
                    }
                    continue;
                } 
            }  
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }
	
	}

	/**
	 * 简析简历中的自我评价模块
	 * @throws Exception 
	 */
	public  void analysisEvaluation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeBase resumeBase = (ResumeBase) list.get(1);
        try {
            int position=contentText.indexOf("证书");
            if(position!=-1){
                contentText=contentText.replaceAll("证书","@");
            }
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"自我评价",resumeModularKeyWords);
            String evaluation = contentText.trim().replaceAll("@","证书");
            resumeBase.setEvaluation(evaluation);
            System.out.println("自我评价：" + evaluation + "\n");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的自我评价信息出错！", e.getMessage());
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
            List<String>keyWords=super.getResumeKeyWords("3","求职意向");
            //查找求职性质
            String expectWorkProperty=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望工作性质",keyWords);
            System.out.println("期望工作性质："+expectWorkProperty); 
            resumeIntention.setExpectWorkProperty(expectWorkProperty);
            //查找月薪要求
            String expectWorkSalary=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望月薪",keyWords);
            System.out.println("期望月薪："+expectWorkSalary);
            resumeIntention.setExpectWorkSalary(expectWorkSalary);
            //查找行业要求
            String expectWorkIndustry=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望从事行业",keyWords);
            System.out.println("期望从事行业："+expectWorkIndustry);
            resumeIntention.setExpectWorkIndustry(expectWorkIndustry);
            //查找职能意向
            String expectWorkJob=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望从事职业",keyWords);
            System.out.println("期望从事职业："+expectWorkJob);
            resumeIntention.setExpectWorkJob(expectWorkJob);
            //查找工作地点
            String expectWorkPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望工作地区",keyWords);
            System.out.println("期望工作地区："+expectWorkPlace);
            resumeIntention.setExpectWorkPlace(expectWorkPlace);
            //查找求职状态
            String currentStatus=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"目前状况",keyWords);
            System.out.println("目前状况："+currentStatus);
            resumeIntention.setCurrentStatus(currentStatus); 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的求职意向信息出错！", e.getMessage());
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
	            String regEx_style="[0-9]{4}/[0-9]{2}-";
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
	            List<String>keyWords=super.getResumeKeyWords("3","工作经历");
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
	                String patten="yyyy/MM";
	                resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	                String end_time=contextValue.substring(8,15).trim();
	                System.out.println("结束时间："+end_time);
	                int index=15;
	                if(end_time.indexOf("至今")==-1){
	                    resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));                       
	                }else{
	                    index=10; 
	                }
	                //查找公司名称及工作时长
	                int index2=contextValue.indexOf("（");
	                int index3=contextValue.indexOf("）"); 
	                if(index2!=-1 && index3!=-1){
	                    String workCompany=contextValue.substring(index, index2);
	                    System.out.println("公司名称："+workCompany);
	                    resumeWorkHistory.setCompanyName(workCompany);
	                    String workCountTime=contextValue.substring(index2+1, index3);
	                    System.out.println("工作时长："+workCountTime);
	                    resumeWorkHistory.setWorkTime(workCountTime);
	                }
	                //截取工作职位
	                end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
	                if(end !=-1 && index2!=-1 && index3!=-1 && end<contextValue.length()){
	                    String sub=contextValue.substring(index3+1, end);	                    
	                    if(StringUtils.isNotEmpty(sub)){
	                        String[] subs=sub.replaceAll("[　* * * *]*", "").split("\\|");
	                        if(subs.length>=2){
	                            String workPosition=subs[0];
	                            System.out.println("工作职位："+workPosition);
	                            resumeWorkHistory.setPosition(workPosition);
	                            String workPay="";//薪资
	                            String workProperty="";//工作性质
	                            index=subs[1].indexOf("保密");
	                            if(index!=-1){
	                                workPay="保密";
	                                resumeWorkHistory.setSalary(workPay);
	                                workProperty=subs[1].substring(index+2).replaceAll("|", "");
	                                resumeWorkHistory.setWorkProperty(workProperty);      
	                            }else{
	                                index=subs[1].indexOf("元/月");
	                                if(index!=-1){
	                                    workPay=subs[1].substring(0, index+3);
	                                    resumeWorkHistory.setSalary(workPay);
	                                    workProperty=subs[1].substring(index+3).replaceAll("|", "");
	                                    resumeWorkHistory.setWorkProperty(workProperty);  
	                                }
	                            }   
	                        } 
	                    }
	                }
	                //查找公司规模
                    String companyScale=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"规模",keyWords);
                    System.out.println("公司规模："+companyScale);
                    resumeWorkHistory.setCompanyScale(companyScale);
	                //查找工作描述
	                String describe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"工作描述",keyWords);
	                System.out.println("工作描述："+describe);
	                resumeWorkHistory.setDescribes(describe);
	                resumeWorkHistoryList.add(resumeWorkHistory);
	            }
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的工作经历信息出错！", e.getMessage());
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
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"项目经验",resumeModularKeyWords);
	        if(StringUtils.isNotEmpty(contentText)){
	            String regEx_style="[0-9]{4}/[0-9]{2}-";
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
	            List<String>keyWords=super.getResumeKeyWords("3","项目经验");
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
	                String patten="yyyy/MM";
	                rpe.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	                String end_time=contextValue.substring(8,15).trim();
	                System.out.println("结束时间："+end_time);
	                int index=15;
	                if(end_time.indexOf("至今")==-1){
	                    rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));                       
	                }else{
	                    index=10; 
	                }
	                //截取项目名称
	                index1=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue, keyWords);
	                if(index1!=-1){
	                    String projectName=contextValue.substring(index,index1);
	                    System.out.println("项目名称："+projectName);
	                    rpe.setProjectName(projectName);	                            
	                }
	                //查找责任描述
	                String responsibilityDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"责任描述",keyWords);
	                System.out.println("责任描述："+responsibilityDescribe);
	                rpe.setResponsibilityDescribe(responsibilityDescribe);  
	                //查找项目简介
	                String projectDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"项目简介",keyWords);
	                System.out.println("项目简介："+projectDescribe);
	                rpe.setProjectDescribe(projectDescribe);
	                resumeProjectExperienceList.add(rpe);	                
	            } 
	        }            
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的项目经验信息出错！", e.getMessage()); 
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
	        if(StringUtils.isNotEmpty(contentText)){
	            String regEx_style="[0-9]{4}/[0-9]{2}-";
	            Pattern r = Pattern.compile(regEx_style);
	            Matcher m = r.matcher(contentText);
	            Map<String,Integer>map=new HashMap<String,Integer>();
	            int j=1;
	            while(m.find()){
	                map.put("start"+j, m.start());
	                j++;
	            }
	            int start,end=0;
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
	                String patten="yyyy/MM";
	                reh.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	                String end_time=contextValue.substring(8,15).trim();
	                System.out.println("结束时间："+end_time); 
	                int index=15;
	                if(end_time.indexOf("至今")==-1){
	                    reh.setEndTime(TimeUtil.getDateByTime(end_time,patten));                       
	                }else{
	                    index=10;
	                }
	                //查询学历
	                String education=SerachEducationUtil.serachEdu(contextValue);
	                System.out.println("学历："+education); 
	                reh.setEducation(education);
	                //查询大学名称
	                index1=SerachEducationUtil.serachIndexOfSchoolEnd(contextValue);
	                String schoolName="";
	                if(index1!=-1){
	                    schoolName=contextValue.substring(index,index1);   
	                }
	                System.out.println("学校名称："+schoolName); 
                    reh.setSchoolName(schoolName);
	                //查询统招类型
	                String entranceType=SerachEducationUtil.serachEduType(contextValue);	                
                    if(StringUtils.isNotEmpty(entranceType)){
                        System.out.println("统招类型："+entranceType); 
                        reh.setEntranceType(entranceType);
                    }
	                //查询专业
                    index=SerachEducationUtil.serachIndexOfEduTypeEnd(contextValue);
                    String majorName="";
                    if(index!=-1 && index1!=-1){
                        majorName=contextValue.substring(index1,index);
                    }else{
                        index=SerachEducationUtil.serachIndexOfEduEnd(contextValue);
                        if(index!=-1 && index1!=-1){
                            majorName=contextValue.substring(index1,index);                            
                        }
                    }
                    System.out.println("专业名称："+majorName); 
                    reh.setMajor(majorName);
	                resumeEducationHistoryList.add(reh); 
	            } 
	        }            
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的教育经历信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	    
	}
	
	/**
	 * 简析简历中的培训经历模块
	 * @throws Exception 
	 */
	public  void analysisTrainingHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeTrainHistory> resumeTrainHistoryList=(List<ResumeTrainHistory>) list.get(11);
	    try {
	        int position=contentText.indexOf("所获证书");
	        if(position!=-1){
	            contentText=contentText.substring(0,position)+contentText.substring(position+5);
	        }
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"培训经历",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}/[0-9]{2}-";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(contentText);
                Map<String,Integer>map=new HashMap<String,Integer>();
                int j=1;
                while(m.find()){
                    map.put("start"+j, m.start());
                    j++;
                }
                int start,end=0;
                //培训经历模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=super.getResumeKeyWords("3", "培训经历");
                for(int i=1;i<j;i++){
                    ResumeTrainHistory resumeTrainHistory=new ResumeTrainHistory();
                    start=map.get("start"+i);//某一段培训经历的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段培训经历的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段培训经历的文本
                    //截取开始时间和结束时间
                    int index1=contextValue.indexOf("-");
                    String start_time=contextValue.substring(0,index1);
                    System.out.println("开始时间："+start_time);
                    String patten="yyyy/MM";
                    resumeTrainHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                    String end_time=contextValue.substring(8,15).trim();
                    System.out.println("结束时间："+end_time); 
                    int index=15;
                    if(end_time.indexOf("至今")==-1){
                        resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));                       
                    }else{
                        index=10;
                    }
                    //查找培训公司
                    index1=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue, keyWords);
                    if(index1!=-1){
                        String trainingCompany=contextValue.substring(index,index1);
                        System.out.println("培训公司："+trainingCompany);
                        resumeTrainHistory.setTrainCompany(trainingCompany);                              
                    }
                    //查找培训课程
                    String trainingName=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训课程",keyWords);
                    System.out.println("培训课程："+trainingName);
                    resumeTrainHistory.setTrainName(trainingName);
                    //查找培训地点 
                    String trainingPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训地点",keyWords);   
                    System.out.println("培训地点："+trainingPlace);
                    resumeTrainHistory.setPlace(trainingPlace);
                    //查找培训描述 
                    String describe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训描述",keyWords);
                    System.out.println("培训描述 ："+describe);
                    resumeTrainHistory.setDescribes(describe);
                    resumeTrainHistoryList.add(resumeTrainHistory);
                }   
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的培训经历信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	}
	
	
	/**
	 * 简析简历中的证书模块
	 * @throws Exception 
	 */
	public  void analysisCertificate(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeCertificate>resumeCertificateList=(List<ResumeCertificate>) list.get(13);
	    try {
	        int position=contentText.indexOf("所获证书");
            if(position!=-1){
                contentText=contentText.substring(0,position)+contentText.substring(position+5);
            }
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"证书",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}/[0-9]{2}";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(contentText);
                Map<String,Integer>map=new HashMap<String,Integer>();
                int j=1;
                while(m.find()){
                    map.put("start"+j, m.start());
                    j++;
                }
                int start,end=0;
                for(int i=1;i<j;i++){
                    ResumeCertificate resumeCertificate=new ResumeCertificate();
                    start=map.get("start"+i);//某一段证书的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段证书的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段证书的文本
                    //证书时间
                    String certificateTime=contextValue.substring(0,7);
                    String patten="yyyy/MM";
                    resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
                    String certificateName=contextValue.substring(7);
                    resumeCertificate.setCertificateName(certificateName);
                    resumeCertificateList.add(resumeCertificate);
                    System.out.println("证书时间："+certificateTime+"\n"+"证书名称："+certificateName+"\n"); 
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的证书模块信息出错！", e.getMessage());
            throw  new Exception(e);
        }
		
	}
	
	/**
	 * 简析简历中的语言能力模块
	 * @throws Exception 
	 */
	public  void analysisLanguageAbility(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeLanguage>resumeLanguageList=(List<ResumeLanguage>) list.get(5);
	    try {
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"语言能力",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                List<String>languageNameList=new ArrayList<String>();
                languageNameList.add("英语");
                languageNameList.add("普通话");
                languageNameList.add("德语");
                languageNameList.add("日语");
                languageNameList.add("意大利语");
                languageNameList.add("朝鲜语");
                for(String languageName:languageNameList){
                    String describes=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,languageName,languageNameList);
                    if(StringUtils.isNotEmpty(describes)){
                        ResumeLanguage resumeLanguage=new ResumeLanguage();
                        resumeLanguage.setName(languageName);
                        resumeLanguage.setDescribes(describes);
                        resumeLanguageList.add(resumeLanguage);
                        System.out.println("语言能力名称："+languageName+"\n"+"语言能力描述："+ describes+"\n");   
                    }
                } 
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的语言能力信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
	}
	
	/**
	 * 简析简历中的获得荣誉模块
	 * @throws Exception 
	 */
	public  void analysisHonour(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    try {
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"获得荣誉",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                ResumePrize resumePrize=new ResumePrize();
                String honourDescribe=contentText;//荣誉描述
                System.out.println("荣誉描述："+honourDescribe+"\n");   
                resumePrize.setDescribes(honourDescribe);
                resumePrizeList.add(resumePrize);                            
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的获得荣誉信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }	
	}
	
   /**
     * 简析简历中的专业技能模块
     * 
     */
    public  void analysisMajorSkill(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeMajorSkill> resumeMajorSkillList=(List<ResumeMajorSkill>) list.get(6);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"专业技能",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="一般|良好|熟练|精通";
                Pattern r = Pattern.compile(regEx_style);
                Matcher m = r.matcher(contentText);
                Map<String,Integer>map=new HashMap<String,Integer>();
                int j=1;
                while(m.find()){
                    map.put("end"+j, m.end());
                    j++;
                }
                int end=0;
                for(int i=1;i<j;i++){
                    int start=0;
                    ResumeMajorSkill resumeMajorSkill=new ResumeMajorSkill();
                    if(i!=1){
                       start=map.get("end"+(i-1));//某一段专业技能的开始位置 
                    }
                    end=map.get("end"+i);//某一段专业技能的结束位置 
                    String contextValue=contentText.substring(start, end);//某段专业技能文本                
                    String skillName=contextValue.substring(0, contextValue.length()-2);
                    if(i!=1){
                        skillName=skillName.substring(2);
                    }
                    String masterdegree=contextValue.substring(contextValue.length()-2);
                    resumeMajorSkill.setSkillName(skillName);  
                    resumeMajorSkill.setMasterDegree(masterdegree);
                    resumeMajorSkillList.add(resumeMajorSkill); 
                    System.out.println("专业技能名称："+skillName+"\n"+"专业技能描述："+masterdegree+"\n");                    
                }                                  
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的专业技能信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
    }
    
}
