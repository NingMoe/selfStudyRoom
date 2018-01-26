package com.human.utils.mailUtils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeCertificate;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.utils.Common;
import com.human.utils.TimeUtil;



@Component("read51JobResumeUtil")
public class Read51JobResumeUtil {

    private  static Logger logger = LogManager.getLogger(Read51JobResumeUtil.class);

    
	/**
	 * 解析前程无忧招聘网的简历
	 * @param docPath  html格式的简历文件
	 */
	public  static List<Object> dealHtmlResumeByResource(File input){
	    List<Object>list=ReadXinAnResumeUtil2.getResumeObject();
	    boolean flag=true;
		try {
			Document doc = Jsoup.parse(input, "gb2312");
			/*
			 * 获取HTML的编码格式
			 */
			String content=doc.select("meta").eq(0).first().attr("content");
			String charset=content.split(";")[1].trim().split("=")[1];
			if(!"gb2312".equalsIgnoreCase(charset)){
			    doc = Jsoup.parse(input, charset); 
			}			
			analysisPositionInformation(doc,list);
			Element tables=doc.select("table").eq(0).first().siblingElements().select("table").first();//
			//判断是否有自我推荐的table
			Element recommendTables = tables.select("table").eq(1).first();//
			if(recommendTables==null){//有自我推荐
			    tables=doc.select("table").eq(0).first().siblingElements().select("table").eq(1).first();
			}
			analysisBaseInformation(tables,list);
			analysisHighEducation(tables,list);			
			Elements tagTables=tables.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
			int tagTableNums=tagTables.size();			
			for(int i=2;i<tagTableNums;i++){
				Element tagTable = tagTables.get(i);//
				String tagsName=tagTable.select("tbody:eq(0)").select("tr:eq(0)>td:eq(0)").first().text().trim();
				if("个人信息".equals(tagsName)){			
					analysisPersonalInformation(tagTable,list);								
				}else if("求职意向".equals(tagsName)){
					analysisExpectWork(tagTable,list);
				}else if("工作经验".equals(tagsName)){
					analysisWorkHistory(tagTable,list);
				}else if("项目经验".equals(tagsName)){
					analysisProjectExperience(tagTable,list);
				}else if("教育经历".equals(tagsName)){
					analysisEducationHistory(tagTable,list);
				}else if("在校情况".equals(tagsName)){
					analysisSchoolSituation(tagTable,list);
				}else if(tagsName!=""&&(tagsName.contains("技能特长"))){
					analysisSkillAndSpecialty(tagTable,list);
				}		
			}				
		}catch (Exception e) {
			logger.error("简析前程无忧简历出错！", e.getMessage());
			flag=false;
		}
	    list.add(14,flag);
	    return list;
	}
		
	/**
	 * 简析简历中的应聘职位信息模块
	 * @throws Exception 
	 */
	public  static void analysisPositionInformation(Document doc,List<Object>list) throws Exception{
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            Element tables = doc.select("table").eq(1).first();//   
            Element table1= tables.select("tr:eq(0)>td:eq(0)>table").first();       
            String applyPosition=table1.select("tr:eq(0)>td:eq(1)").text().trim();
            resumeBase.setApplyPosition(applyPosition);                 
            Element table2= tables.select("tr:eq(0)>td:eq(1)>table").first();   
            String applyTime=table2.select("tr:eq(0)>td:eq(1)").text().trim();
            resumeBase.setDeliveryDate(applyTime);
            System.out.println("应聘职位："+applyPosition);
            System.out.println("投递时间："+applyTime); 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的应聘职位信息出错！", e.getMessage());  
            throw  new Exception(e);
        }		
	}
	
	/**
	 * 简析简历中的基本信息模块
	 * @throws Exception 
	 */
	public  static void analysisBaseInformation(Element element,List<Object>list) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        ResumeIntention resumeIntention=(ResumeIntention) list.get(4);
        try {
            Element tables = element.select("table").eq(0).first();//
            String text1= tables.select("tr:eq(0)>td:eq(1)>table:eq(0)").first().select("tr:eq(0)>td:eq(0)").text().trim().replaceAll("　", "").replaceAll(" ", "");
            int index=text1.indexOf("|");
            int index1=text1.lastIndexOf("|");
            int index2=text1.indexOf("(");
            int index3=text1.lastIndexOf(")");
            String name=text1.substring(0, index);
            resumeBase.setName(name);
            resumeSeeker.setName(name);
            String sex=text1.substring(index+1,index+2);
            if(sex.indexOf("男")!=-1){
                resumeSeeker.setSex("M");
                resumeBase.setSex("M");
            }
            if(sex.indexOf("女")!=-1){
                resumeSeeker.setSex("F");
                resumeBase.setSex("F");
            }
            if(index2!=-1&&index3!=-1){
                String birth=text1.substring(index2+1,index3).replaceAll("/", "-");
                Date birthDate = TimeUtil.getDateByTime(birth, "yyyy-MM-dd");
                resumeBase.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));
                resumeSeeker.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));                
            }
            if(index1!=-1){
                String working_time=text1.substring(index1+1,index1+3);
                resumeSeeker.setWorkTime(working_time);
                resumeBase.setWorkingTime(working_time);  
            }                      
            Element table1=tables.select("tr:eq(0)>td:eq(1)>table:eq(1)").first();            
            String phone=table1.select("tr:eq(0)>td:eq(0)>table:eq(0)").first().select("tr:eq(0)>td:eq(1)").text().trim();
            if(Common.isMobile(phone)){
                resumeSeeker.setPhone(phone);
                resumeBase.setTelephone(phone); 
            }                    
            String email=table1.select("tr:eq(0)>td:eq(1)>table:eq(0)").first().select("tr:eq(0)>td:eq(1)").text().trim();
            resumeSeeker.setEmail(email);
            resumeBase.setEmail(email);                       
            String locationCity=table1.select("tr:eq(1)>td:eq(0)>table:eq(0)").first().select("tr:eq(0)>td:eq(1)").text().trim(); 
            resumeSeeker.setLocationCity(locationCity);
            resumeBase.setLocationCity(locationCity);                     
            String currentStatus=table1.select("tr:eq(1)>td:eq(1)>table:eq(0)").first().select("tr:eq(0)>td:eq(1)").text().trim(); 
            resumeIntention.setCurrentStatus(currentStatus);                       
            String headUrl= tables.select("tr:eq(0)>td:eq(0)").select("img").attr("src");
            if(!headUrl.endsWith("man.png") && !headUrl.endsWith("woman.png")){
                resumeSeeker.setHeadUrl(headUrl);
                resumeBase.setHeadUrl(headUrl);
            }
            System.out.println("基本信息："+text1);
            System.out.println("手机："+phone);  
            System.out.println("邮箱："+email); 
            System.out.println("居住地："+locationCity); 
            System.out.println("求职状态："+currentStatus);
            System.out.println("图像："+headUrl+"\n");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }		
	}

	
	
	/**
	 * 简析简历中的离现在最近的一次工作经历
	 */
	public  static void analysisLastWorkHistory(Element element){
		Element tables = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children().get(1);
		Element table1 = tables.select("tr:eq(0)>td:eq(0)>table:eq(0)").first();
		String text1=table1.select("tr:eq(0)>td:eq(0)>table:eq(0)").first().select("tr:eq(1)>td:eq(1)").text();
		System.out.println("职位："+text1);	
		String text2=table1.select("tr:eq(0)>td:eq(0)>table:eq(0)").first().select("tr:eq(2)>td:eq(1)").text();
		System.out.println("公司："+text2);		
		String text3=table1.select("tr:eq(0)>td:eq(0)>table:eq(0)").first().select("tr:eq(3)>td:eq(1)").text();
		System.out.println("行业："+text3);				
	}
	
	
	/**
	 * 简析简历中的最高学历
	 * @throws Exception 
	 */
	public  static void analysisHighEducation(Element element,List<Object>list) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            Element tables = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children().get(1);//
            Element table1 = tables.select("tr:eq(0)>td:eq(0)>table:eq(0)").first();
            Element table2=table1.select("tr:eq(0)>td:eq(1)>table:eq(0)").first();
            if(table2!=null){
                String major=table1.select("tr:eq(0)>td:eq(1)>table:eq(0)").first().select("tr:eq(1)>td:eq(1)").text().trim();
                resumeSeeker.setMajor(major);
                resumeBase.setMajor(major);           
                String graSchool=table1.select("tr:eq(0)>td:eq(1)>table:eq(0)").first().select("tr:eq(2)>td:eq(1)").text().trim();
                resumeSeeker.setGraSchool(graSchool);
                resumeBase.setGraSchool(graSchool);                     
                String highEdu=table1.select("tr:eq(0)>td:eq(1)>table:eq(0)").first().select("tr:eq(3)>td:eq(1)").text().trim();
                resumeSeeker.setHighEdu(highEdu);
                resumeBase.setHighEdu(highEdu); 
                System.out.println("专业："+major);
                System.out.println("学校："+graSchool); 
                System.out.println("学历/学位："+highEdu+"\n"); 
            }            
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的最高学历信息出错！", e.getMessage());
            throw  new Exception(e);
        }		
	}
	
	
	/**
	 * 简析简历中的个人基本信息
	 * @throws Exception 
	 */
	public  static void analysisPersonalInformation(Element element,List<Object>list) throws Exception{
	    ResumeBase resumeBase=(ResumeBase) list.get(1);
	    try {
	        Element table1 = element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();
	        Elements trs=table1.select("tbody").eq(0).first().children();       
	        String key="";
	        String value="";
	        for(Element tr:trs){
	             key=tr.select("td:eq(0)>table").select("tr:eq(0)").select("td:eq(0)").text().trim();
	             value=tr.select("td:eq(0)>table").select("tr:eq(0)").select("td:eq(1)").text().trim();
	             if(key.indexOf("政治面貌")!=-1){
	                 resumeBase.setPoliticalStatus(value);
	             }
	             System.out.println(key+":"+value);
	             //判断第2列是否有值
	             String td2=tr.select("td:eq(1)").text().trim();
	             if(!"".equals(td2)){
	                 key=tr.select("td:eq(1)>table").select("tr:eq(0)").select("td:eq(0)").text().trim();
	                 value=tr.select("td:eq(1)>table").select("tr:eq(0)").select("td:eq(1)").text().trim();
	                 if(key.indexOf("政治面貌")!=-1){
	                    resumeBase.setPoliticalStatus(value);
	                 }
	                System.out.println(key+":"+value);
	             }           
	        }     
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的个人基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }
		
	}
	
		
	/**
	 * 简析简历中的求职意向模块
	 * @throws Exception 
	 */
	public static void analysisExpectWork(Element element,List<Object>list) throws Exception{
	    ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
	    ResumeBase resumeBase=(ResumeBase) list.get(1);
	    try {
	        Element table1=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();       
	        Elements trs=table1.select("tbody").eq(0).first().children();       
	        Map<String,String>map=new HashMap<String,String>(); 
	        String key="";
	        String value="";
	        for(Element tr:trs){
	             key=tr.select("td:eq(0)>table:eq(0)>tbody").select("tr:eq(0)").select("td:eq(0)").text().trim();
	             value=tr.select("td:eq(0)>table").select("tr:eq(0)").select("td:eq(1)").text().trim();
	             if(!Common.isEmpty(key)){
                     System.out.println(key+value);
                     map.put(key, value);  
                 }  
	             //判断第2列是否有值
	             String td2=tr.select("td:eq(1)").text().trim();
	             if(!"".equals(td2)){
	                 key=tr.select("td:eq(1)>table").select("tr:eq(0)").select("td:eq(0)").text().trim();
	                 value=tr.select("td:eq(1)>table").select("tr:eq(0)").select("td:eq(1)").text().trim();
	                 if(!Common.isEmpty(key)){
	                     System.out.println(key+value);
	                     map.put(key, value);  
	                 }	                
	             }           
	        }
	        if(map!=null&&map.size()>0){
	            for (Map.Entry<String, String> entry : map.entrySet()) {
	                  if(entry.getKey().indexOf("期望薪资")!=-1){
	                      resumeIntention.setExpectWorkSalary(entry.getValue());
	                  }else if(entry.getKey().indexOf("地点")!=-1){
	                      resumeIntention.setExpectWorkPlace(entry.getValue());
	                  }else if(entry.getKey().indexOf("职能")!=-1){
	                      resumeIntention.setExpectWorkJob(entry.getValue());
	                  }else if(entry.getKey().indexOf("行业")!=-1){
	                      resumeIntention.setExpectWorkIndustry(entry.getValue());
	                  }else if(entry.getKey().indexOf("自我评价")!=-1){
	                      resumeBase.setEvaluation(entry.getValue());
	                  }else if(entry.getKey().indexOf("到岗时间")!=-1){
	                      resumeIntention.setExpectArrivalTime(entry.getValue());
	                  }else if(entry.getKey().indexOf("工作类型")!=-1){
	                      resumeIntention.setExpectWorkProperty(entry.getValue());
	                  } 
	                }
	        }  
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的求职意向信息出错！", e.getMessage()); 
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
	        Element table1=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();
	        int workHistoryNums= table1.select("tbody").eq(0).first().children().size();
	        System.out.println("工作经历总数："+workHistoryNums);
	        Elements trs=table1.select("tbody").eq(0).first().children();           
	        for(Element tr:trs){
	            ResumeWorkHistory resumeWorkHistory=new ResumeWorkHistory(); 
	            String workTime="";//工作时间
	            String workCompany="";//公司名称
	            String workCountTime="";//工作时长
	            String workPosition="";//工作职位
	            String workCompanyIndustry="";//公司性质
	            String workDescribe="";//工作描述
	            Elements trs2 =tr.select("td:eq(0)>table:eq(0)");
	            workTime=trs2.select("tr:eq(0)").select("td:eq(0)").first().text().trim();
	            int index=workTime.indexOf("-");
	            String start_time=workTime.substring(0, index);
	            String end_time=workTime.substring(index+1);
	            String patten="yyyy/MM";
	            resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("至今")==-1){
	                resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }           
	            workPosition=trs2.select("tr:eq(0)").select("td:eq(1)").first().text().trim().replaceAll("　", "").replaceAll(" ", "");
	            index=workPosition.indexOf("|");
	            if(index!=-1){//有部门
	                String position=workPosition.substring(0,index).trim().replaceAll(" ", "");
	                String department=workPosition.substring(index+1).trim().replaceAll(" ", "");
	                resumeWorkHistory.setDepartment(department);
	                resumeWorkHistory.setPosition(position);                
	            }else{//无部门
	                resumeWorkHistory.setPosition(workPosition); 
	            }
	            workCompany=trs2.select("tr:eq(1)").select("td:eq(0)>span:eq(0)").text().trim().replaceAll("　", "").replaceAll(" ", "");
	            resumeWorkHistory.setCompanyName(workCompany);
	            workCountTime=trs2.select("tr:eq(1)").select("td:eq(0)>span:eq(1)").text().trim().replaceAll("　", "").replaceAll(" ", "");
	            int index1=workCountTime.indexOf("[");
	            int index2=workCountTime.indexOf("]");
	            workCountTime=workCountTime.substring(index1+1,index2);
	            resumeWorkHistory.setWorkTime(workCountTime);
	            int size=trs2.select("tbody").eq(0).first().children().size();
	            if(size==4){
	                workCompanyIndustry=trs2.select("tr:eq(2)").select("td:eq(0)").text().trim();
	                index=workCompanyIndustry.indexOf("|");
	                if(index==-1){//只有工作性质
	                    resumeWorkHistory.setWorkProperty(workCompanyIndustry);         
	                }else{
	                    String work_property=workCompanyIndustry.substring(0,index).trim().replaceAll(" ", "");
	                    resumeWorkHistory.setWorkProperty(work_property);
	                    String company_scale=workCompanyIndustry.substring(index+1).trim().replaceAll(" ", "");
	                    resumeWorkHistory.setCompanyScale(company_scale);       
	                }
	                workDescribe=trs2.select("tr:eq(3)").select("td:eq(0)>table").select("tr:eq(0)>td:eq(1)").text().trim();
	                resumeWorkHistory.setDescribes(workDescribe);
	            }else if(size==3){
	                workDescribe=trs2.select("tr:eq(2)").select("td:eq(0)>table").select("tr:eq(0)>td:eq(1)").text().trim();
	                resumeWorkHistory.setDescribes(workDescribe);
	            }
	            resumeWorkHistoryList.add(resumeWorkHistory);
	            System.out.println("工作时间："+workTime+"\n"+"公司名称："+workCompany+"\n"+"工作时长："+workCountTime+"\n"+
	            "工作职位："+workPosition+"\n"+"公司性质："+workCompanyIndustry+"\n"+"工作描述："+workDescribe+"\n");
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的工作经历信息出错！", e.getMessage()); 
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
	        Element table1=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();
	        int projectExperienceNums= table1.select("tbody").eq(0).first().children().size();
	        System.out.println("项目经验总数："+projectExperienceNums);
	        Elements trs=table1.select("tbody").eq(0).first().children();           
	        for(Element tr:trs){
	            ResumeProjectExperience rpe=new ResumeProjectExperience();
	            String projectTime="";//项目时间
	            String projectName="";//项目名称
	            String companyName="";//公司名称
	            String projectDescribe="";//项目描述
	            String responsibilityDescribe="";//责任描述
	            Elements trs2 =tr.select("td:eq(0)>table:eq(0)");
	            projectTime=trs2.select("tr:eq(0)").select("td:eq(0)").first().text().trim();
                int index=projectTime.indexOf("-");
                String start_time=projectTime.substring(0, index);
                String end_time=projectTime.substring(index+1);
                String patten="yyyy/MM";
                rpe.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
                projectName=trs2.select("tr:eq(0)").select("td:eq(1)>strong:eq(0)").first().text().trim();
                rpe.setProjectName(projectName);
	            Elements sibTrs=trs2.select("tr:eq(0)").first().siblingElements();
	            for(Element sibTr:sibTrs){              
	                String  name =sibTr.select("td:eq(0)>table:eq(0)").select("tr:eq(0)>td:eq(0)").text().split("：")[0];
	                String  text=sibTr.select("td:eq(0)>table:eq(0)").select("tr:eq(0)>td:eq(1)").text().trim();
	                if("所属公司".equals(name)){
	                    companyName=text;
	                    rpe.setCompanyName(companyName);   
	                }else if("项目描述".equals(name)){
	                    projectDescribe=text;
	                    rpe.setProjectDescribe(projectDescribe);
	                }else if("责任描述".equals(name)){
	                    responsibilityDescribe=text;
	                    rpe.setResponsibilityDescribe(responsibilityDescribe);
	                }               
	            }
	            resumeProjectExperienceList.add(rpe);
	            System.out.println("项目时间："+projectTime+"\n"+"项目名称："+projectName+"\n"+"公司名称："+companyName+"\n"+"责任描述："+responsibilityDescribe+"\n"+
	                    "项目简介："+projectDescribe+"\n");
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的项目经验信息出错！", e.getMessage());
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
	        Element table1=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();
	        int educationHistoryNums= table1.select("tbody").eq(0).first().children().size();
	        System.out.println("教育经历总数："+educationHistoryNums);
	        Elements trs=table1.select("tbody").eq(0).first().children();               
	        for(Element tr:trs){
	            ResumeEducationHistory resumeEducationHistory=new ResumeEducationHistory();
	            String educationTime="";//教育时间
	            String schoolName="";//学校名称
	            String majorName="";//专业名称
	            String education="";//学历
	            String majorDescribe="";//专业描述  
	            Elements trs2 =tr.select("td:eq(0)>table:eq(0)");
	            educationTime=trs2.select("tr:eq(0)").select("td:eq(0)").first().text().trim();         
	            int index=educationTime.indexOf("-");
	            String start_time=educationTime.substring(0, index).trim();
	            String end_time=educationTime.substring(index+1).trim();
	            String patten="yyyy/MM";
	            resumeEducationHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("至今")==-1){
	                resumeEducationHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }
	            schoolName=trs2.select("tr:eq(0)").select("td:eq(1)>strong:eq(0)").text().trim();
	            resumeEducationHistory.setSchoolName(schoolName);
	            String[] a=trs2.select("tr:eq(1)").select("td:eq(0)").text().trim().split(" ");   
	            if(a.length>2){
	                majorName=a[2].trim();               
	            }
	            education=a[0].trim(); 
	            resumeEducationHistory.setMajor(majorName);
	            resumeEducationHistory.setEducation(education);
	            int size=trs2.select("tbody").eq(0).first().children().size();
	            if(size==3){
	                majorDescribe=trs2.select("tr:eq(2)").select("td:eq(0)>table:eq(0)").select("tr:eq(0)>td:eq(1)").text().trim();
	                resumeEducationHistory.setDescribes(majorDescribe);;
	            }
	            resumeEducationHistoryList.add(resumeEducationHistory);
	            System.out.println("教育时间："+educationTime+"\n"+"学校名称："+schoolName+"\n"+"专业名称："+majorName+"\n"+
	                    "学历："+education+"\n"+"专业描述："+majorDescribe+"\n");
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的教育经历信息出错！", e.getMessage());
            throw  new Exception(e);
        }		
	}
					
	/**
	 * 简析简历中的在校情况模块
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public  static void analysisSchoolSituation(Element element,List<Object>list) throws Exception{
        List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    List<ResumeSchoolPost> resumeSchoolPostList=(List<ResumeSchoolPost>) list.get(10);
	    try {
	        //校内荣誉
	        Element table1=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();
	        int schoolHonourNums= table1.select("tbody").eq(0).first().children().size();
	        System.out.println("校内荣誉总数："+(schoolHonourNums-1));         
	        Elements trs=table1.select("tbody").eq(0).first().children().first().siblingElements();
	        for(Element tr:trs){
	            ResumePrize resumePrize=new ResumePrize();
	            String schoolHonourTime="";//获得校内荣誉时间
	            String schoolHonourName="";//校内荣誉名称
	            Elements trs2 =tr.select("td:eq(0)>table:eq(0)");
	            schoolHonourTime=trs2.select("tr:eq(0)").select("td:eq(0)").text();
	            String patten="yyyy/MM";
	            resumePrize.setGainTime(TimeUtil.getDateByTime(schoolHonourTime,patten));
	            schoolHonourName=trs2.select("tr:eq(0)").select("td:eq(1)").text().trim();  
	            resumePrize.setPrizeName(schoolHonourName);
	            resumePrizeList.add(resumePrize);
	            System.out.println("获得校内荣誉时间："+schoolHonourTime+"\n"+"校内荣誉名称："+schoolHonourName+"\n");
	        }
	        //校内职务
	        table1=element.select("tr:eq(1)>td:eq(0)>table:eq(1)").first();
	        if(table1!=null){
	            schoolHonourNums= table1.select("tbody").eq(0).first().children().size();
	            System.out.println("校内职务总数："+(schoolHonourNums-1));
	            trs=table1.select("tbody").eq(0).first().children().first().siblingElements();
	            for(Element tr:trs){
	                ResumeSchoolPost resumeSchoolPost=new ResumeSchoolPost();
	                String schoolaJobTime="";//校内职务时间
	                String schoolaJobName="";//校内职务名称   
	                String schoolaJobDescribe="";//职务描述
	                Elements trs2 =tr.select("td:eq(0)>table:eq(0)");
	                schoolaJobTime=trs2.select("tr:eq(0)").select("td:eq(0)").first().text();           
	                int index=schoolaJobTime.indexOf("-");
	                String start_time=schoolaJobTime.substring(0, index).trim();
	                String end_time=schoolaJobTime.substring(index+1).trim();
	                String patten="yyyy/MM";
	                resumeSchoolPost.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	                if(end_time.indexOf("至今")==-1){
	                    resumeSchoolPost.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	                }
	                schoolaJobName=trs2.select("tr:eq(0)").select("td:eq(1)").first().text().trim();
	                resumeSchoolPost.setPostName(schoolaJobName);
	                //判断是否含有职务描述
	                int size=trs2.select("tbody").eq(0).first().children().size(); 
	                if(size==2){
	                    schoolaJobDescribe=trs2.select("tr:eq(1)").select("td:eq(0)>table:eq(0)").select("tr:eq(0)>td:eq(1)").text().trim();
	                    resumeSchoolPost.setDescribes(schoolaJobDescribe);
	                }
	                resumeSchoolPostList.add(resumeSchoolPost);
	                System.out.println("校内职务时间："+schoolaJobTime+"\n"+"校内职务名称："+schoolaJobName+"\n"+"职务描述："+schoolaJobDescribe+"\n");
	            } 
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的在校情况信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	}
				
	/**
	 * 简析简历中的技能特长模块
	 * @throws Exception 
	 */
	public  static void analysisSkillAndSpecialty(Element element,List<Object>list) throws Exception{
	    try {
	        Elements tables=element.select("tr:eq(1)>td:eq(0)").first().children();
	        String tagName="";
	        for(Element table:tables){
	            tagName =table.select("tr:eq(0)>td:eq(0)").first().text().trim();
	            if("技能/语言".equals(tagName)){
	                analysisLanguageAbility(table,list);
	            }else if("证书".equals(tagName)){
	                analysisCertificate(table,list);
	            }else if("培训经历".equals(tagName)){
	                analysisTrainingHistory(table,list);
	            }           
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的技能特长信息出错！", e.getMessage()); 
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
	        Elements trs=element.select("tbody:eq(0)>tr:eq(0)").first().siblingElements();
	        for(Element tr:trs){
	            ResumeCertificate resumeCertificate=new ResumeCertificate();
	            String certificateTime="";//证书时间
	            String certificateName="";//证书名称
	            Elements table =tr.select("td:eq(0)>table:eq(0)");
	            certificateTime=table.select("tr:eq(0)").select("td:eq(0)").text().trim();
	            String patten="yyyy/MM";
	            resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
	            certificateName=table.select("tr:eq(0)").select("td:eq(1)").text().trim();
	            resumeCertificate.setCertificateName(certificateName);
	            resumeCertificateList.add(resumeCertificate);
	            System.out.println("证书时间："+certificateTime+"\n"+"证书名称："+certificateName+"\n");  
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的证书信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
		
	}
	
	/**
	 * 简析简历中的技能语言能力模块
	 * @throws Exception 
	 */
	public  static void analysisLanguageAbility(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeLanguage>resumeLanguageList=(List<ResumeLanguage>) list.get(5);
	    try {
	        Element table=element.select("tr:eq(1)>td:eq(0)>table:eq(0)").first();      
	        Elements trs=table.select("tbody").eq(0).first().children();
	        for(Element tr:trs){                
	            Elements tds =tr.children();
	            for(Element td: tds){
	                ResumeLanguage resumeLanguage=new ResumeLanguage();
	                String LanguageAbilityName="";//技能或语言名称
	                String LanguageAbilityDescribe="";//技能或语言描述
	                LanguageAbilityName=td.select("table:eq(0)").select("tr:eq(0)>td:eq(0)").text().trim();
	                LanguageAbilityDescribe=td.select("table:eq(0)").select("tr:eq(0)>td:eq(1)").text().trim();
	                resumeLanguage.setName(LanguageAbilityName);
	                resumeLanguage.setDescribes(LanguageAbilityDescribe);
	                resumeLanguageList.add(resumeLanguage);
	                System.out.println("技能语言名称："+LanguageAbilityName+"\n"+"证书名称描述："+LanguageAbilityDescribe+"\n");  
	            }                   
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的技能语言信息出错！", e.getMessage());
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
	        Elements trs=element.select("tbody:eq(0)>tr:eq(0)").first().siblingElements();
	        for(Element tr:trs){
	            ResumeTrainHistory resumeTrainHistory=new ResumeTrainHistory();
	            String trainingTime="";//培训时间
	            String trainingName="";//培训名称
	            String trainingCompany="";//培训公司
	            String trainingPlace="";//培训地点
	            Element table =tr.select("td:eq(0)>table:eq(0)").first();
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
	            trainingName=table.select("tr:eq(0)").select("td:eq(1)").text().trim(); 
	            resumeTrainHistory.setTrainName(trainingName);
	            table =tr.select("td:eq(0)>table:eq(1)").first();
	            Element tdTable=table.select("tr:eq(0)").select("td:eq(0)>table:eq(0)").first();
	            trainingCompany=tdTable.select("tr:eq(0)").select("td:eq(1)").text();
	            resumeTrainHistory.setTrainCompany(trainingCompany);	            
	            tdTable=table.select("tr:eq(0)").select("td:eq(1)>table:eq(0)").first();
	            trainingPlace=tdTable.select("tr:eq(0)").select("td:eq(1)").text();
	            resumeTrainHistory.setPlace(trainingPlace);
	            resumeTrainHistoryList.add(resumeTrainHistory);
	            System.out.println("培训时间："+trainingTime+"\n"+"培训名称："+trainingName+"\n"+"培训公司："+trainingCompany+"\n"+"培训地点："+trainingPlace+"\n");                
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的培训经历信息出错！", e.getMessage());
            throw  new Exception(e);
        }
	}
	
	
	/**
	 * 简析简历中的附加信息模块
	 */
	public  static void analysisAdditional(Element element){		
		Elements tables=element.select("tr:eq(1)>td:eq(0)").first().children();
		String tagName="";
		for(Element table:tables){
			tagName =table.select("tr:eq(0)>td:eq(0)").first().text().trim();
			if("其它".equals(tagName)){
				
			}			
		}
	}
	
	
	public static void main(String[] args) {
	    String docPath = "E:/temp/attachment/234.html";//
	    File input = new File(docPath);    
	    dealHtmlResumeByResource(input);

	}
	
}
