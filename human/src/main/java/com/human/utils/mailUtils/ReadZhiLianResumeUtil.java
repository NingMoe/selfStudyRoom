package com.human.utils.mailUtils;

import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
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
import com.human.utils.TimeUtil;

@Component("readZhiLianResumeUtil")
public class ReadZhiLianResumeUtil {


    private  static  Logger logger = LogManager.getLogger(ReadZhiLianResumeUtil.class);
    
	/**
	 * 解析智联招聘网的简历
	 * @param docPath  html格式的简历文件
	 */
	public  static List<Object>  dealHtmlResumeByResource(File input){
	    List<Object>list=ReadXinAnResumeUtil2.getResumeObject();
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
			Element tables=doc.select("table").eq(2).first();//
			Element lastTrs =tables.children().select("tbody").eq(0).first().children().last();
			Element rs=lastTrs.select("td:eq(0)>table:eq(0)").first();
			if(rs==null){//有求职信
			    tables=doc.select("table").eq(4).first();//
	            lastTrs =tables.children().select("tbody").eq(0).first().children().last();
	            rs=lastTrs.select("td:eq(0)>table:eq(0)").first(); 
	            analysisBaseInformation(doc,list,1);
			}else{
			    analysisBaseInformation(doc,list,0);  
			}			
			int tagsNums= rs.children().select("tbody").eq(0).first().children().size();
			System.out.println("简历中除基本信息外共有"+tagsNums+"个子标签模块！");
			Elements trs=rs.children().select("tbody").eq(0).first().children();
			for(Element tr:trs){
				String tabName =tr.select("td:eq(0)>table").eq(0).select("tr:eq(0)>td:eq(0)").text().trim();
				Element elmt =tr.select("td:eq(0)>table").eq(1).first();
				if("自我评价".equals(tabName)){
					analysisEvaluation(elmt,list);
				}else if("求职意向".equals(tabName)){
					analysisExpectWork(elmt,list);
				}else if("工作经历".equals(tabName)){
					analysisWorkHistory(elmt,list);
				}else if("项目经验".equals(tabName)){
					analysisProjectExperience(elmt,list);
				}else if("教育经历".equals(tabName)){
					analysisEducationHistory(elmt,list);
				}else if("培训经历".equals(tabName)){
					analysisTrainingHistory(elmt,list);
				}else if("证书".equals(tabName)){
					analysisCertificate(elmt,list);
				}else if("语言能力".equals(tabName)){
					analysisLanguageAbility(elmt,list);
				}else if("获得荣誉".equals(tabName)){
					analysisHonour(elmt,list);
				}else if("专业技能".equals(tabName)){
                    analysisMajorSkill(elmt,list);
                }else if("专业组织".equals(tabName)){
					//analysisProfessionalOrganization(elmt);
				}	
			}
		}catch (Exception e) {
		    logger.error("简析智联招聘网简历出错！", e.getMessage());
            flag=false;
		}
		list.add(14,flag);
	    return list;
	}
	
	
	/**
	 * 简析简历中的基本信息模块
	 * @throws Exception 
	 */
	public  static void analysisBaseInformation(Document doc,List<Object>list,int type) throws Exception{
        ResumeSeeker resumeSeeker = (ResumeSeeker) list.get(0);
        ResumeBase resumeBase = (ResumeBase) list.get(1);
        try {
            Element tables=null;
            if(type==1){//有求职信
                tables = doc.select("table").eq(6).first();//   
            }else{//无求职信
                tables = doc.select("table").eq(4).first();// 
            }
            Element nameElement = tables.select("tr:eq(0)>td:eq(0)").first();
            String name = nameElement.text().trim();
            resumeBase.setName(name);
            resumeSeeker.setName(name);
            Elements fonts = tables.select("tr:eq(1)>td:eq(0)").first().children().select("font");
            for (Element font : fonts) {
                String value = font.text().trim();
                if ("女".equals(value)) {
                    resumeSeeker.setSex("F");
                    resumeBase.setSex("F");
                }
                else if ("男".equals(value)) {
                    resumeSeeker.setSex("M");
                    resumeBase.setSex("M");
                }
                else if (value.indexOf("工作经验") != -1) {
                    String working_time = value.substring(0, 2);
                    resumeSeeker.setWorkTime(working_time);
                    resumeBase.setWorkingTime(working_time);
                }
                else if (value.indexOf("年") != -1) {
                    String patten = "yyyy年MM月";
                    Date birthDate = TimeUtil.getDateByTime(value, patten);
                    resumeSeeker.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));
                    resumeBase.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd")); 
                }
                else if(value.indexOf("未婚")!=-1){
                    resumeBase.setMarriage("1");
                    resumeSeeker.setMarriage("1");
                }
                else if(value.indexOf("已婚")!=-1){
                    resumeBase.setMarriage("2");
                    resumeSeeker.setMarriage("2");
                }
            }
            String text1 = tables.select("tr:eq(1)>td:eq(0)").text().trim().replaceAll("　", "").replaceAll(" ", "");
            int index = text1.lastIndexOf("|");
            String householdRegister = text1.substring(index + 1, text1.length() - 2);// 户口
            resumeBase.setHouseholdRegister(householdRegister);
            String phone = tables.select("tr:eq(2)>td:eq(0)").select("td:eq(1)>p").text().trim();
            if(Common.isMobile(phone)){
                resumeSeeker.setPhone(phone);
                resumeBase.setTelephone(phone); 
            }
            String email = tables.select("tr:eq(2)>td:eq(0)").select("td:eq(1)>span").text().trim();
            resumeSeeker.setEmail(email);
            resumeBase.setEmail(email);
            Element photoTables = doc.select("table").eq(3).first();//
            String headUrl = photoTables.select("tr:eq(1)>td:eq(1)").select("img").attr("src").trim();
            if (!headUrl.endsWith("lookResumes.jpg")) {
                resumeSeeker.setHeadUrl(headUrl);
                resumeBase.setHeadUrl(headUrl);
            }
            System.out.println("姓名：" + name);
            System.out.println("基本信息：" + text1);
            System.out.println("手机号：" + phone);
            System.out.println("邮箱：" + email);
            System.out.println("图像：" + headUrl + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }
	
	}

	/**
	 * 简析简历中的自我评价模块
	 * @throws Exception 
	 */
	public  static void analysisEvaluation(Element element,List<Object>list) throws Exception{
        ResumeBase resumeBase = (ResumeBase) list.get(1);
        try {
            String evaluation = element.select("tr:eq(1)>td:eq(0)").text().trim();
            resumeBase.setEvaluation(evaluation);
            System.out.println("自我评价：" + evaluation + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的自我评价信息出错！", e.getMessage());
            throw  new Exception(e);
        }   		
	}
	
	/**
	 * 简析简历中的求职意向模块
	 * @throws Exception 
	 */
	public  static void analysisExpectWork(Element element,List<Object>list) throws Exception{
        ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
        try {
            Element tables = element;//
            String expectWorkPlace = tables.select("tr:eq(0)>td:eq(1)").text().trim();
            resumeIntention.setExpectWorkPlace(expectWorkPlace);
            String expectWorkProperty = tables.select("tr:eq(1)>td:eq(1)").text().trim();
            resumeIntention.setExpectWorkProperty(expectWorkProperty);
            String expectWorkJob = tables.select("tr:eq(2)>td:eq(1)").text().trim();
            resumeIntention.setExpectWorkJob(expectWorkJob);
            String expectWorkSalary = tables.select("tr:eq(3)>td:eq(1)").text().trim();
            resumeIntention.setExpectWorkSalary(expectWorkSalary);
            String currentStatus = tables.select("tr:eq(4)>td:eq(1)").text().trim();
            resumeIntention.setCurrentStatus(currentStatus);
            String expectWorkIndustry = tables.select("tr:eq(5)>td:eq(1)").text().trim();
            resumeIntention.setExpectWorkIndustry(expectWorkIndustry);
            System.out.println("期望工作地区：" + expectWorkPlace);
            System.out.println("期望工作性质：" + expectWorkProperty);
            System.out.println("期望从事职业：" + expectWorkJob);
            System.out.println("期望月薪：" + expectWorkSalary);
            System.out.println("目前状况：" + currentStatus);
            System.out.println("期望从事行业：" + expectWorkIndustry + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的求职意向信息出错！", e.getMessage());
            throw  new Exception(e);
        }
	}
	
	/**
	 * 简析简历中的工作经历模块
	 * @throws Exception 
	 */
	public  static void analysisWorkHistory(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeWorkHistory> resumeWorkHistoryList=(List<ResumeWorkHistory>) list.get(12);
	    try {
	        Elements trs=element.select("tbody").eq(0).first().children();   
	        int workHistoryNums= trs.size();
	        System.out.println("工作经历总数："+workHistoryNums);          
	        for(Element tr:trs){
	            ResumeWorkHistory resumeWorkHistory=new ResumeWorkHistory(); 
	            String workTime="";//工作时间
	            String workCompany="";//公司名称
	            String workCountTime="";//工作时长
	            String workPosition="";//工作职位
	            String workPay="";//薪资
	            String workProperty="";//工作性质
	            String workDescribe="";//工作描述
	            Elements trs2 =tr.select("td:eq(0)>table");
	            workTime=trs2.select("tr:eq(0)").select("td:eq(0)>p").text().trim();
	            int index=workTime.indexOf("-");
	            String start_time=workTime.substring(0, index).trim();
	            String end_time=workTime.substring(index+1).trim();
	            String patten="yyyy/MM";
	            resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("至今")==-1){
	                resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }           
	            workCompany=trs2.select("tr:eq(0)").select("td:eq(1)>span:eq(0)").text().trim();
	            resumeWorkHistory.setCompanyName(workCompany);
	            workCountTime=trs2.select("tr:eq(0)").select("td:eq(1)>span:eq(1)").text().trim();
	            int index1=workCountTime.indexOf("（");
	            int index2=workCountTime.indexOf("）");
	            workCountTime=workCountTime.substring(index1+1,index2);
	            resumeWorkHistory.setWorkTime(workCountTime);
	            workPosition=trs2.select("tr:eq(1)").select("td:eq(1)>span:eq(0)").text().trim();
	            resumeWorkHistory.setPosition(workPosition);
	            workPay=trs2.select("tr:eq(1)").select("td:eq(1)>span").eq(1).text().trim();
	            resumeWorkHistory.setSalary(workPay);           
	            workProperty=trs2.select("tr:eq(2)").select("td:eq(1)>span:eq(0)").text().trim();
	            resumeWorkHistory.setWorkProperty(workProperty);
	            Elements spans=trs2.select("tr:eq(2)").select("td:eq(1)").first().children().select("span");
	            for(Element span :spans){
	                String value=span.text().trim();
	                if(value.indexOf("规模")!=-1){
	                    resumeWorkHistory.setCompanyScale(value.substring(3));
	                }
	            }
	            workDescribe=trs2.select("tr:eq(3)").select("td:eq(1)>p").text().trim();
	            resumeWorkHistory.setDescribes(workDescribe);
	            resumeWorkHistoryList.add(resumeWorkHistory);
	            System.out.println("工作时间："+workTime+"\n"+"公司名称："+workCompany+"\n"+"工作时长："+workCountTime+"\n"+
	            "工作职位："+workPosition+"\n"+"薪资："+workPay+"\n"+"工作性质："+workProperty+"\n"+"工作描述："+workDescribe+"\n");
	        } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的工作经历信息出错！", e.getMessage());
            throw  new Exception(e);
        }
	}
	
	
	/**
	 * 简析简历中的项目经验模块
	 * @throws Exception 
	 */
	public  static void analysisProjectExperience(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeProjectExperience> resumeProjectExperienceList=(List<ResumeProjectExperience>) list.get(9);
	    try {
	        Elements trs=element.select("tbody").eq(0).first().children();  
	        int projectExperienceNums=trs.size();
	        System.out.println("项目经验总数："+projectExperienceNums);                
	        for(Element tr:trs){
	            ResumeProjectExperience rpe=new ResumeProjectExperience();
	            String projectTime="";//项目时间
	            String projectName="";//项目名称
	            String responsibilityDescribe="";//责任描述
	            String projectDescribe="";//项目简介
	            Elements trs2 =tr.select("td:eq(0)>table");
	            projectTime=trs2.select("tr:eq(0)").select("td:eq(0)>p").text().trim();
	            int index=projectTime.indexOf("-");
                String start_time=projectTime.substring(0, index).trim();
                String end_time=projectTime.substring(index+1).trim();
                String patten="yyyy/MM";
                rpe.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
	            projectName=trs2.select("tr:eq(0)").select("td:eq(1)>span:eq(0)").text().trim();
	            rpe.setProjectName(projectName);
	            responsibilityDescribe=trs2.select("tr:eq(1)").select("td:eq(1)>p").text().trim();
	            rpe.setResponsibilityDescribe(responsibilityDescribe);	            
	            projectDescribe=trs2.select("tr:eq(2)").select("td:eq(1)>p").text().trim();
	            rpe.setProjectDescribe(projectDescribe);
	            resumeProjectExperienceList.add(rpe);
	            System.out.println("项目时间："+projectTime+"\n"+"项目名称："+projectName+"\n"+"责任描述："+responsibilityDescribe+"\n"+
	                    "项目简介："+projectDescribe+"\n");
	        } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的项目经验信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	}
	
	/**
	 * 简析简历中的教育经历模块
	 * @throws Exception 
	 */
	public  static void analysisEducationHistory(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeEducationHistory> resumeEducationHistoryList=(List<ResumeEducationHistory>) list.get(3);
	    try {
	        Element table= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").select("table:eq(0)").first();
	        Elements trs=table.select("tbody").eq(0).first().children();
	        int educationHistoryNums= trs.size();
	        System.out.println("教育经历总数："+educationHistoryNums);                 
	        for(Element tr:trs){ 
	            ResumeEducationHistory resumeEducationHistory=new ResumeEducationHistory();
	            String educationTime="";//教育时间
	            String schoolName="";//学校名称
	            String majorName="";//专业名称
	            String entranceType="";//统招类型
	            String education="";//学历
	            educationTime=tr.select("td:eq(0)>p").text().trim();
	            int index=educationTime.indexOf("-");
                String start_time=educationTime.substring(0, index).trim();
                String end_time=educationTime.substring(index+1).trim();
                String patten="yyyy/MM";
                resumeEducationHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    resumeEducationHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
	            schoolName=tr.select("td:eq(1)>span:eq(0)").text().trim();
	            resumeEducationHistory.setSchoolName(schoolName);	            
	            majorName=tr.select("td:eq(1)>span:eq(1)").text().trim();
	            resumeEducationHistory.setMajor(majorName);
	            int size=tr.select("td:eq(1)>span").size();
	            if(size==4){
	                entranceType=tr.select("td:eq(1)>span:eq(2)").text().trim();
	                education=tr.select("td:eq(1)>span:eq(3)").text().trim();
	                resumeEducationHistory.setEducation(education);
	                resumeEducationHistory.setEntranceType(entranceType);
	            }else if(size==3){
	                education=tr.select("td:eq(1)>span:eq(2)").text().trim();
	                resumeEducationHistory.setEducation(education);
	            }
	            resumeEducationHistoryList.add(resumeEducationHistory);
	            System.out.println("教育时间："+educationTime+"\n"+"学校名称："+schoolName+"\n"+"专业名称："+majorName+"\n"+
	                    "统招类型："+entranceType+"\n"+"学历："+education+"\n");
	        } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的教育经历信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	    
	}
	
	/**
	 * 简析简历中的培训经历模块
	 * @throws Exception 
	 */
	public  static void analysisTrainingHistory(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeTrainHistory> resumeTrainHistoryList=(List<ResumeTrainHistory>) list.get(11);
	    try {
	        Elements tables= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
	        int trainingHistoryNums= tables.size();
	        System.out.println("培训经历总数："+trainingHistoryNums);  
	        for(Element table:tables){
	            ResumeTrainHistory resumeTrainHistory=new ResumeTrainHistory();
	            String trainingTime="";//培训时间
	            String trainingName="";//培训名称
	            String trainingCompany="";//培训公司
	            String trainingPlace="";//培训地点       
	            String describe="";//培训描述     
	            trainingTime=table.select("tr:eq(0)").select("td:eq(0)").text().trim();
	            int index=trainingTime.indexOf("-");
                int index1=trainingTime.lastIndexOf("-");
                String start_time=trainingTime.substring(0, index).trim();
                String end_time=trainingTime.substring(index1+1).trim();
                String patten="yyyy/MM";
                resumeTrainHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
	            trainingCompany=table.select("tr:eq(0)").select("td:eq(1)").text().trim();
	            resumeTrainHistory.setTrainCompany(trainingCompany);	            
	            trainingName=table.select("tr:eq(1)").select("td:eq(1)").text().trim();
	            resumeTrainHistory.setTrainName(trainingName);
	            String tagName=table.select("tr:eq(2)").select("td:eq(1)").select("p:eq(0)>span:eq(0)").text().trim();
	            String tagValue=table.select("tr:eq(2)").select("td:eq(1)").text().trim();
	            if(tagName.indexOf("培训描述")!=-1){
	                describe=tagValue;
	                resumeTrainHistory.setDescribes(describe);
	            }else if(tagName.indexOf("培训地点")!=-1){
	                trainingPlace=tagValue;
	                resumeTrainHistory.setPlace(trainingPlace); 
	            }
	            resumeTrainHistoryList.add(resumeTrainHistory);
	            System.out.println("培训时间："+trainingTime+"\n"+"培训名称："+trainingName+"\n"+"培训公司："+trainingCompany+"\n"+"培训地点："+trainingPlace+"\n");                
	        } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的培训经历信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	}
	
	
	/**
	 * 简析简历中的证书模块
	 * @throws Exception 
	 */
	public  static void analysisCertificate(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeCertificate>resumeCertificateList=(List<ResumeCertificate>) list.get(13);
	    try {
	        Elements trs=element.select("tbody").eq(0).first().children();  
	        int certificateNums= trs.size();
	        System.out.println("获得证书总数："+certificateNums);      
	        for(Element tr:trs){
	            ResumeCertificate resumeCertificate=new ResumeCertificate();
	            String certificateTime="";//证书时间
	            String certificateName="";//证书名称
	            Elements trs2 =tr.select("td:eq(0)>table");
	            certificateTime=trs2.select("tr:eq(0)").select("td:eq(0)").text().trim();
	            String patten="yyyy/MM";
                resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
	            certificateName=trs2.select("tr:eq(0)").select("td:eq(1)>span:eq(0)").text().trim();
	            resumeCertificate.setCertificateName(certificateName);
                resumeCertificateList.add(resumeCertificate);
	            System.out.println("证书时间："+certificateTime+"\n"+"证书名称："+certificateName+"\n");      
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
	public  static void analysisLanguageAbility(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeLanguage>resumeLanguageList=(List<ResumeLanguage>) list.get(5);
	    try {
	        Element table= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)>table:eq(0)").first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        int languageNums= trs.size();
	        System.out.println("所会语言数："+languageNums);          
	        for(Element tr:trs){
	            ResumeLanguage resumeLanguage=new ResumeLanguage();
	            String languageName="";//语言能力名称
	            String languageDescribe="";//语言能力描述
	            languageName=tr.select("td:eq(0)>span:eq(0)").text().trim().replaceAll("：", "");
	            resumeLanguage.setName(languageName);
	            Elements spans=tr.select("td:eq(0)>span:eq(0)").first().siblingElements();
	            for(Element span: spans){
	                languageDescribe+=span.text().trim().replaceAll(" ", "");
	            }
	            languageDescribe=languageDescribe.replaceAll(" ", "");
	            resumeLanguage.setDescribes(languageDescribe);
                resumeLanguageList.add(resumeLanguage);
                System.out.println("语言能力名称："+languageName+"\n"+"语言能力描述："+languageDescribe+"\n");    
	        } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的语言能力信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
	}
	
	/**
	 * 简析简历中的专业组织模块
	 */
	public  static void analysisProfessionalOrganization(Element element){		
		Elements trs=element.select("tbody").eq(0).first().children();	
		int professionalOrganizationNums= trs.size();
		System.out.println("专业组织数："+professionalOrganizationNums);
		for(Element tr:trs){
			String organizationName="";//
			organizationName=tr.select("td:eq(0)").text().trim();
			System.out.println("专业组织："+organizationName+"\n");		
		}
	}

	/**
	 * 简析简历中的获得荣誉模块
	 * @throws Exception 
	 */
	public  static void analysisHonour(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    try {
	        Elements trs=element.select("tbody").eq(0).first().children();  
	        int honourNums= trs.size();
	        System.out.println("获得荣誉数："+honourNums);                	        
	        for(Element tr:trs){
	            ResumePrize resumePrize=new ResumePrize();
	            String honourDescribe="";//荣誉描述
	            honourDescribe=tr.select("td:eq(0)").text().trim().replaceAll(" ", "");
	            resumePrize.setDescribes(honourDescribe);
	            resumePrizeList.add(resumePrize);
	            System.out.println("荣誉描述："+honourDescribe+"\n");        
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
    public  static void analysisMajorSkill(Element element,List<Object>list) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeMajorSkill> resumeMajorSkillList=(List<ResumeMajorSkill>) list.get(6);
        try {
            Element table= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)>table:eq(0)").first();
            String skillName="";//专业技能名称
            String masterdegree="";//专业技能描述
            if(table!=null){
                Elements trs= table.select("tbody").eq(0).first().children();
                int languageNums= trs.size();
                System.out.println("专业技能数："+languageNums);          
                for(Element tr:trs){
                    ResumeMajorSkill resumeMajorSkill=new ResumeMajorSkill();
                    skillName=tr.select("td:eq(0)>span:eq(0)").text().trim();
                    resumeMajorSkill.setSkillName(skillName);
                    Elements spans=tr.select("td:eq(0)>span:eq(0)").first().siblingElements();
                    for(Element span: spans){
                        masterdegree+=span.text().trim().replaceAll(" ", "");
                    }
                    masterdegree=masterdegree.replaceAll(" ", "");
                    resumeMajorSkill.setMasterDegree(masterdegree);
                    resumeMajorSkillList.add(resumeMajorSkill);                     
                }  
            }else{
                table= element.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first(); 
                if(table==null){
                    table= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first(); 
                }
                for(Node node:table.childNodes()){
                    if("#text".equals(node.nodeName())){
                        ResumeMajorSkill resumeMajorSkill=new ResumeMajorSkill();
                        skillName=node.outerHtml();//专业技能名称                       
                        resumeMajorSkill.setSkillName(skillName);  
                        resumeMajorSkillList.add(resumeMajorSkill);
                    }                    
                }
            }
            System.out.println("专业技能名称："+skillName+"\n"+"专业技能描述："+masterdegree+"\n");   
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析智联招聘简历中的专业技能信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
    }
    
	public static void main(String[] args) {
	    String docPath = "E:/temp/attachment/345.html";//
	    File input = new File(docPath);
	    dealHtmlResumeByResource(input);
    }
}
