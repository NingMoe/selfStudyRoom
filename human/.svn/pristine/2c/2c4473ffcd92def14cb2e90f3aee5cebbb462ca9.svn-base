package com.human.utils.mailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeAttachment;
import com.human.resume.entity.ResumeBase;
import com.human.resume.entity.ResumeCertificate;
import com.human.resume.entity.ResumeEducationHistory;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;
import com.human.resume.entity.ResumeMajorSkill;
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.utils.Common;
import com.human.utils.TimeUtil;
@Component("readXinAnResumeUtil2")
public class ReadXinAnResumeUtil2 {
    
    private static Logger logger = LogManager.getLogger(ReadXinAnResumeUtil2.class);
            
    /**
     * 封装简历所有实体
     * @return
     */
    public static List<Object> getResumeObject(){
        List<Object>list=new ArrayList<Object>(16);
        ResumeSeeker resumeSeeker= new ResumeSeeker();
        ResumeBase resumeBase=new ResumeBase();
        ResumeIntention resumeIntention=new ResumeIntention();
        List<ResumeAttachment> resumeAttachmentList=new ArrayList<ResumeAttachment>();
        List<ResumeEducationHistory>resumeEducationHistoryList=new ArrayList<ResumeEducationHistory>();        
        List<ResumeLanguage> resumeLanguageList=new ArrayList<ResumeLanguage>();
        List<ResumeMajorSkill> resumeMajorSkillList=new ArrayList<ResumeMajorSkill>();
        List<ResumePracticeExperience> resumePracticeExperienceList=new ArrayList<ResumePracticeExperience>();
        List<ResumePrize> resumePrizeList=new ArrayList<ResumePrize>();
        List<ResumeProjectExperience> resumeProjectExperienceList=new ArrayList<ResumeProjectExperience>();
        List<ResumeSchoolPost> resumeSchoolPostList=new ArrayList<ResumeSchoolPost>();
        List<ResumeTrainHistory> resumeTrainHistoryList=new ArrayList<ResumeTrainHistory>();
        List<ResumeWorkHistory>resumeWorkHistoryList=new ArrayList<ResumeWorkHistory>();
        List<ResumeCertificate>resumeCertificateList=new ArrayList<ResumeCertificate>();
        list.add(0, resumeSeeker);
        list.add(1, resumeBase);
        list.add(2, resumeAttachmentList);
        list.add(3, resumeEducationHistoryList);
        list.add(4, resumeIntention);
        list.add(5, resumeLanguageList);
        list.add(6, resumeMajorSkillList);
        list.add(7, resumePracticeExperienceList);
        list.add(8, resumePrizeList);
        list.add(9, resumeProjectExperienceList);
        list.add(10, resumeSchoolPostList);
        list.add(11, resumeTrainHistoryList);
        list.add(12, resumeWorkHistoryList);
        list.add(13, resumeCertificateList);
        return list;
    }
    
    
    
    
    
	/**
	 * 解析新安人才招聘网的简历
	 * @param docPath  html格式的简历文件
	 */
	public  static List<Object>  dealHtmlResumeByResource(File input){
	    List<Object>list=getResumeObject();
	    boolean flag=true;
		try {
			Document doc = Jsoup.parse(input, "utf-8");
			/*
			 * 获取HTML的编码格式
			 */
			String content=doc.select("head>meta").eq(0).first().attr("content");
			String charset=content.split(";")[1].trim().split("=")[1];
			if(!"utf-8".equalsIgnoreCase(charset)){
                doc = Jsoup.parse(input, charset); 
            }			
			analysisBaseInformation(doc,list);	
			Elements div=doc.select("div:eq(0)").first().children().select("table").eq(2);//
			Elements tagTables=div.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children().select("table").eq(0).first().siblingElements();
			int tagTableNums=tagTables.size();
			for(int i=1;i<tagTableNums;){
			    Element tagTable = tagTables.get(i);//
			    Element table = tagTables.get(i+1);//
			    String tagName=tagTable.select("tbody:eq(0)>tr:eq(0)>td:eq(0)>h1:eq(0)").first().text().trim();
                if("联系方式".equals(tagName)){
                    analysisContactInformation(table,list);
                    i+=3;
                }else if("工作经历".equals(tagName)){
                    analysisWorkHistory(table,list);
                    i+=3;
                }else if("求职意向".equals(tagName)){
                    analysisExpectWork(table,list);
                    i+=3;
                }else if("项目经验".equals(tagName)){
                    analysisProjectExperience(table,list);
                    i+=3;
                }else if("教育经历".equals(tagName)){
                    int j=i+1;
                    for(;j<tagTableNums;j++){
                      Element eduTable = tagTables.get(j);
                      String tdName=eduTable.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                      if(tdName.indexOf("-")!=-1){
                          analysisEducationHistory(eduTable,list);
                      }else{
                         break; 
                      }
                    }
                    i=j+1;                   
                }else if("个人技能".equals(tagName)){
                    int j=i+1;
                    for(;j<tagTableNums;){
                      Element eduTable = tagTables.get(j);
                      String tdName=eduTable.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                      if(!StringUtils.isWhitespace(tdName)){
                         if("培训经历".equals(tdName)){
                             analysisTrainingHistory(tagTables.get(j+1),list); 
                             j+=2;
                         }else if("证书".equals(tdName)){
                             analysisCertificate(tagTables.get(j+1),list); 
                             j+=2;
                         }else if("语言能力".equals(tdName)){
                             analysisLanguageAbility(tagTables.get(j+1),list); 
                             j+=2;
                         }else if("IT技能".equals(tdName)){
                             analysisMajorSkill(tagTables.get(j+1),list); 
                             j+=2;
                         }else{
                             break;
                         }
                      }else{
                          j++;
                         continue; 
                      }
                    }
                    i=j;                
                }else if("在校情况".equals(tagName)){
                    int j=i+1;
                    for(;j<tagTableNums;){
                      Element eduTable = tagTables.get(j);
                      String tdName=eduTable.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                      if(!StringUtils.isWhitespace(tdName)){
                         if("荣誉奖励".equals(tdName)){
                             analysisHonour(tagTables.get(j+1),list); 
                             j+=2;
                         }else if("校内职务".equals(tdName)){
                             analysisSchoolPost(tagTables.get(j+1),list); 
                             j+=2;
                         }else if("社会实践".equals(tdName)){
                             analysisSchoolPractice(tagTables.get(j+1),list); 
                             j+=2;
                         }else{
                             break;
                         }
                      }else{
                          j++;
                         continue; 
                      }
                    }
                    i=j;                
                }else if("自我评价".equals(tagName)){
                    analysisEvaluation(table,list);
                    i+=2;
                }
			}			
		}catch (Exception e) {
		    e.printStackTrace();
			logger.error("简析新安人才网简历出错！", e.getMessage());
			System.out.println("新安人才网简历路径-------"+input.getAbsolutePath()+"\n"+"新安人才网简历路径-------"+input.getName());
			flag=false;
		}
		list.add(14,flag);
		return list;
	}
	
	
	/**
	 * 简析简历中的基本信息模块
	 * @throws Exception 
	 */
	public static void analysisBaseInformation(Document doc,List<Object>list) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
	    ResumeBase resumeBase=(ResumeBase) list.get(1);
	    String name="";
	    try{
	        Elements div=doc.select("div:eq(0)").first().children().select("table").eq(2);//   	        
	        Elements et=div.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children().select("table").eq(0);
	        Element td=et.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first();
	        Element image= td.select("a").first();
	        if(image!=null){
	            String headUrl=image.attr("href");                 
	            System.out.println("图像："+headUrl); 	           
	            resumeSeeker.setHeadUrl(headUrl);
	            resumeBase.setHeadUrl(headUrl);	            
	        }
            Elements trs=et.select("tbody:eq(0)>tr:eq(0)>td:eq(1)").first().select("table:eq(0)>tbody:eq(0)").first().children();
            for(Element tr:trs){
                Element ele=tr.select("td").first().select("h1").first();
                if(ele!=null){  
                    String value=ele.text().trim();  
                    int index= value.indexOf("[");
                    name=value.substring(0, index).trim();
                    System.out.println("姓名："+name);
                    resumeSeeker.setName(name);     
                    resumeBase.setName(name);   
                }else{
                    ele=tr.select("td").first(); 
                    String value=ele.text().trim();  
                    int index= value.indexOf("/");
                    if(index!=-1){
                        String[] values=value.split("/");
                        for(String s:values){
                            String textValue=s.trim();                            
                            if(textValue.indexOf("男")!=-1){
                                resumeSeeker.setSex("M");
                                resumeBase.setSex("M");
                            }
                            if(textValue.indexOf("女")!=-1){
                                resumeSeeker.setSex("F");
                                resumeBase.setSex("F");
                            }
                            if(textValue.indexOf("工作经验")!=-1){
                                int endIndex=textValue.indexOf("工作经验");
                                String workTime=textValue.substring(0,endIndex);
                                resumeSeeker.setWorkTime(workTime);
                                resumeBase.setWorkingTime(workTime);
                            } 
                            if(textValue.indexOf("岁")!=-1 && textValue.indexOf("(")!=-1 && textValue.indexOf(")")!=-1){
                                int start=textValue.indexOf("(");
                                int end =textValue.indexOf(")");
                                String birthDate=textValue.substring(start+1,end);
                                resumeSeeker.setBirthDate(birthDate);
                                resumeBase.setBirthDate(birthDate);                                 
                            }
                            if(textValue.indexOf("未婚")!=-1){
                                resumeBase.setMarriage("1");
                                resumeSeeker.setMarriage("1");
                            }
                            if(textValue.indexOf("已婚")!=-1){
                                resumeBase.setMarriage("2");
                                resumeSeeker.setMarriage("2");
                            }
                        }
                    }else{
                        if(value.indexOf("现居住地")!=-1){
                            String locationCity="";    
                            String household_register="";
                            int start=value.indexOf("现居住地")+5;
                            int end=value.indexOf("户口");         
                            if(end!=-1){
                                locationCity=value.substring(start,end).trim();
                                household_register=value.substring(end+3).trim();
                                resumeBase.setHouseholdRegister(household_register);
                            }else{
                                locationCity=value.substring(start);
                            }
                            resumeSeeker.setLocationCity(locationCity);
                            resumeBase.setLocationCity(locationCity);
                        } 
                    }
                }   
            }                     	        
	    }catch(Exception e){
	       e.printStackTrace();
	       logger.error("简析新安人才网简历中的基本信息模块出错！", e.getMessage());	 
	       throw  new Exception(e);
	    }	
	}

	/**
	 * 简析简历中的个人概况信息
	 */
	public  static void analysisPersonalInformation(Element element){
		Elements divs = element.select("table").eq(0).first().select("tr:eq(0)>td:eq(0)").first().children();			
		for(Element div:divs){
			String text=div.text().trim();
			System.out.println("个人概况："+text);
		}		
	}
	
	/**
	 * 简析简历中的联系方式信息
	 * @throws Exception 
	 */
	public  static void analysisContactInformation(Element element,List<Object>list) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try{
            Elements trs = element.select("tbody:eq(0)").first().children();
            for(Element tr:trs){
                Elements tds = tr.children();
                for(Element td:tds){
                    Element span = td.select("span:eq(0)").first(); 
                    String  value=span.text().trim().replaceAll("[　*| *| *|\\s*]*", "");                   
                    Element span2 = td.select("span:eq(1)").first(); 
                    String value2=span2.text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                    if(value.indexOf("手机")!=-1){
                        System.out.println("手机号："+value2); 
                        if(Common.isMobile(value2)){
                            resumeSeeker.setPhone(value2);
                            resumeBase.setTelephone(value2); 
                        }
                    }
                    if(value.indexOf("邮箱")!=-1){
                        System.out.println("邮箱："+value2);
                        resumeSeeker.setEmail(value2);
                        resumeBase.setEmail(value2);
                    }                          
                }
            }       
        }catch(Exception e){
            e.printStackTrace();
            logger.error("简析新安人才网简历中的联系方式信息出错！", e.getMessage());
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
	    try{
	        Elements tbales = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
	        for(Element table:tbales){
	            ResumeWorkHistory resumeWorkHistory=new ResumeWorkHistory();
	            String company_name="";
	            Element tr=table.select("tbody:eq(0)>tr:eq(0)").first();
	            String text1=tr.select("td:eq(0)").text().trim().replaceAll("[　*| *| *|\\s*]*", "");
	            int index=text1.indexOf("-");
	            int index1=text1.indexOf("(");
                int index2=text1.indexOf(")");
	            String start_time=text1.substring(0,index).trim();
                String patten="yyyy/MM";
                resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(index1!=-1 && index2!=-1 ){
                    String end_time=text1.substring(index+1,index1).trim();
                    String work_time=text1.substring(index1+1,index2).trim();                  
                    if(end_time.indexOf("至今")==-1){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    }
                    resumeWorkHistory.setWorkTime(work_time);  
                }else{
                    String end_time=text1.substring(index+1).trim();
                    if(end_time.indexOf("至今")==-1){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    }
                }
                text1=tr.select("td:eq(1)").text().trim();
                index=text1.indexOf("|");
                if(index!=-1 && index!=0){
                    company_name=text1.substring(0,index).trim();
                    String position=text1.substring(index+1,text1.length()).trim();
                    if(position.indexOf("找TA的同事")!=-1){
                        index=position.indexOf("找TA的同事");
                        position=position.substring(0, index);
                    }
                    resumeWorkHistory.setCompanyName(company_name);
                    resumeWorkHistory.setPosition(position);
                }               
                Element td=table.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first();
                if(td!=null){
                    Elements spans=td.select("span");
                    for(Element span:spans){
                        text1=span.text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                        if(text1.indexOf("平均月薪")!=-1){
                            index=text1.indexOf("平均月薪");
                            String salary=text1.substring(index+5);
                            resumeWorkHistory.setSalary(salary);
                        }
                        if(text1.indexOf("所属行业")!=-1){
                            index=text1.indexOf("所属行业");
                            String work_property=text1.substring(index+5);
                            resumeWorkHistory.setWorkProperty(work_property);
                        }
                        if(text1.indexOf("公司性质")!=-1){
                            index1=text1.indexOf("公司性质");
                            String company_scale=text1.substring(index1+5);
                            resumeWorkHistory.setCompanyScale(company_scale); 
                        }
                        if(text1.indexOf("所在部门")!=-1){
                            index1=text1.indexOf("所在部门");  
                            String department=text1.substring(index1+5);
                            resumeWorkHistory.setDepartment(department);
                        }
                    }
                }
                Element table1=table.select("tbody:eq(0)>tr:eq(2)>td:eq(0)").first();
                if(table1!=null){
                    td=table1.select("table:eq(0)>tbody:eq(0)>tr:eq(1)>td:eq(0)").first();
                    if(td!=null){
                        text1=td.text().trim();
                        String describe=text1;
                        resumeWorkHistory.setDescribes(describe); 
                    }                    
                }   
                resumeWorkHistoryList.add(resumeWorkHistory);
                System.out.println("公司名称："+company_name+"\n"); 
	        }
	      }catch(Exception e){
	        e.printStackTrace();
	        logger.error("简析新安人才网简历中的工作经历信息出错！", e.getMessage());
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
            Elements tbales = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
            for(Element table:tbales){
                ResumeProjectExperience rpe=new ResumeProjectExperience();
                String projectTime="";//项目时间
                String projectName="";//项目名称
                String companyName="";//公司名称
                String projectDescribe="";//项目描述
                String responsibilityDescribe="";//责任描述
                Element tr=table.select("tbody:eq(0)>tr:eq(0)").first();
                projectTime=tr.select("td:eq(0)").text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                int index=projectTime.indexOf("-");
                String start_time=projectTime.substring(0,index).trim();
                String end_time=projectTime.substring(index+1);
                String patten="yyyy/MM";
                rpe.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
                String text1=tr.select("td:eq(1)").text().trim();
                index=text1.indexOf("/");
                if(index!=-1){
                    projectName=text1.substring(0,index);
                    companyName=text1.substring(index+1);
                    rpe.setProjectName(projectName);
                    rpe.setCompanyName(companyName);
                }                
                Elements td=table.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().children();
                if(td!=null && !td.isEmpty()){
                    projectDescribe=td.select("table:eq(0)>tbody:eq(0)>tr:eq(1)>td:eq(0)").first().text().trim();
                    rpe.setProjectDescribe(projectDescribe);
                }
                td=table.select("tbody:eq(0)>tr:eq(2)>td:eq(0)").first().children();
                if(td!=null && !td.isEmpty()){
                    responsibilityDescribe=td.select("table:eq(0)>tbody:eq(0)>tr:eq(1)>td:eq(0)").first().text().trim();
                    rpe.setResponsibilityDescribe(responsibilityDescribe);
                }
                resumeProjectExperienceList.add(rpe);
                System.out.println("项目时间："+projectTime+"\n"+"项目名称："+projectName+"\n"+"公司名称："+companyName+"\n"+"责任描述："+responsibilityDescribe+"\n"+
                        "项目简介："+projectDescribe+"\n"); 
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("简析新安人才网简历中的项目经验信息出错！", e.getMessage());
            throw  new Exception(e);
        }
        
    }
	
	/**
	 * 简析简历中的求职意向模块
	 * @throws Exception 
	 */
	public  static void analysisExpectWork(Element element,List<Object>list) throws Exception{
        ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
        try{
            Elements trs = element.select("tbody:eq(0)").first().children();
            for(Element tr:trs){
                Elements tds = tr.children();
                for(Element td:tds){
                    Element span = td.select("span:eq(0)").first(); 
                    String  value=span.text().trim().replaceAll("[　*| *| *|\\s*]*", "");                   
                    Element span2 = td.select("span:eq(1)").first(); 
                    String value2=span2.text().trim().replaceAll("[　*| *| *|\\s*]*", "");
                    if(value.indexOf("求职性质")!=-1){
                        System.out.println("求职性质："+value2); 
                        resumeIntention.setExpectWorkProperty(value2);
                    }
                    if(value.indexOf("月薪要求")!=-1){
                        System.out.println("月薪要求："+value2);
                        resumeIntention.setExpectWorkSalary(value2);
                    }
                    if(value.indexOf("行业要求")!=-1){
                        System.out.println("行业要求："+value2);
                        resumeIntention.setExpectWorkIndustry(value2);
                    }
                    if(value.indexOf("职能意向")!=-1){
                        System.out.println("职能意向："+value2);
                        resumeIntention.setExpectWorkJob(value2);
                    }
                    if(value.indexOf("工作地点")!=-1){
                        System.out.println("工作地点："+value2);
                        resumeIntention.setExpectWorkPlace(value2);
                    }
                    if(value.indexOf("求职状态")!=-1){
                        if(value2.indexOf("(")!=-1){
                            int index=value2.indexOf("(");                  
                            String currentStatus=value2.substring(0, index);
                            resumeIntention.setCurrentStatus(currentStatus);                    
                        }
                        if(value2.indexOf("到岗时间")!=-1 && value2.indexOf(")")!=-1){
                            int start=value2.indexOf("到岗时间")+5;
                            int end=value2.indexOf(")");                  
                            String expect_arrival_time=value2.substring(start,end);
                            resumeIntention.setExpectArrivalTime(expect_arrival_time);           
                        }
                        System.out.println("求职状态："+value2);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("简析新安人才网简历中的求职意向信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }
	
	}
	
	
	/**
	 * 简析简历中的学生相关模块
	 * @param element
	 * @throws Exception 
	 */
	
	public  static void analysisStudentInformation(Element element,List<Object>list) throws Exception{
	    try {
	        Elements divs=element.children();
	        int size=divs.size();
	        for(int i=2;i<size;i+=3){
	            Element div=divs.get(i);
	            if(div.select("span:eq(0)").first()!=null){
	                String tagName=div.select("span:eq(0)").first().text().trim();
	                if("所获奖励".equals(tagName)){
	                    analysisHonour(div,list);
	                }else if("在校实践".equals(tagName)){
	                    analysisSchoolPractice(div,list);
	                } 
	            }	            
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的学生相关模块出错！", e.getMessage()); 
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
	    try{
	        ResumeEducationHistory resumeEducationHistory=new ResumeEducationHistory();
	        Element td = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first(); 
	        String educationTime=td.text().trim();
	        int index=educationTime.indexOf("-");
            String start_time=educationTime.substring(0,index).trim();
            String end_time=educationTime.substring(index+1);
            String patten="yyyy/MM";
            resumeEducationHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
            if(end_time.indexOf("至今")==-1){
                resumeEducationHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
            }
            String schoolName=element.select("tbody:eq(0)>tr:eq(0)>td:eq(1)").first().text().trim();//学校名称           
            String majorName=element.select("tbody:eq(0)>tr:eq(0)>td:eq(2)").first().text().trim();//专业名称         
            String education=element.select("tbody:eq(0)>tr:eq(0)>td:eq(3)").first().text().trim();//学历
            resumeEducationHistory.setSchoolName(schoolName);
            resumeEducationHistory.setMajor(majorName);
            resumeEducationHistory.setEducation(education);            
	        resumeEducationHistoryList.add(resumeEducationHistory);
	        System.out.println("教育时间："+educationTime+"\n"+"学校名称："+schoolName+"\n"+"专业名称："+majorName+"\n"+"学历："+education+"\n");
	     }catch(Exception e){
	         e.printStackTrace();
	         logger.error("简析新安人才网简历中的教育经历信息出错！", e.getMessage());  
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
	    try{
	        Elements tbales = element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
	        int trainingHistoryNums= tbales.size();	        
	        System.out.println("培训经历总数："+(trainingHistoryNums));
	        for(Element tbale:tbales){
	            ResumeTrainHistory resumeTrainHistory=new ResumeTrainHistory();
                String trainingTime="";//培训时间
                String trainingCompany="";//培训公司
                String trainingName="";//培训名称           
                String trainingCertificate="";//培训所获证书         
                String trainingDescribes="";//培训描述    
                trainingTime=tbale.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim();
                int index=trainingTime.indexOf("-");
                String start_time=trainingTime.substring(0, index);
                String end_time=trainingTime.substring(index+1);
                String patten="yyyy/MM";
                resumeTrainHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
                trainingName=tbale.select("tbody:eq(0)>tr:eq(0)>td:eq(1)").first().text().trim();
                resumeTrainHistory.setTrainName(trainingName);
                trainingCompany=tbale.select("tbody:eq(0)>tr:eq(0)>td:eq(2)").first().text().trim();
                resumeTrainHistory.setTrainCompany(trainingCompany);
                Elements tbale1s=tbale.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().children();
                if(tbale1s!=null && !tbale1s.isEmpty()){
                    trainingDescribes=tbale1s.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().text().trim();
                    if(trainingDescribes.indexOf("证书")!=-1){
                        resumeTrainHistory.setCertificate(trainingDescribes);
                    }else{
                        resumeTrainHistory.setDescribes(trainingDescribes); 
                    } 
                }               
                resumeTrainHistoryList.add(resumeTrainHistory);
                System.out.println("培训时间："+trainingTime+"\n"+"培训名称："+trainingName+"\n"+"培训公司："+trainingCompany+"\n"+"培训所获证书："+trainingCertificate+"\n");                
	        }
	      }catch(Exception e){
	        e.printStackTrace();
	        logger.error("简析新安人才网简历中的培训经历信息出错！", e.getMessage()); 
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
	        Elements trs = element.select("tbody:eq(0)").first().children();
	        int certificateNums= trs.size();
	        System.out.println("获得证书总数："+certificateNums);  
	        for(Element tr:trs){
	            ResumeCertificate resumeCertificate=new ResumeCertificate();
                String certificateTime="";//证书时间
                String certificateName="";//证书名称
                String describes="";//证书描述
                certificateTime=tr.select("td:eq(0)").first().text().trim();
                String patten="yyyy年MM月";
                resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
                certificateName=tr.select("td:eq(1)").first().text().trim();
                resumeCertificate.setCertificateName(certificateName);
                describes=tr.select("td:eq(2)").first().text().trim();
                resumeCertificate.setDescribes(describes);
                resumeCertificateList.add(resumeCertificate);
                System.out.println("证书时间："+certificateTime+"\n"+"证书名称："+certificateName+"\n");	            
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的证书信息出错！", e.getMessage()); 
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
		try{
		    
		    Elements trs = element.select("tbody:eq(0)").first().children();
	        int languageNums= trs.size();
	        System.out.println("所会语言数："+(languageNums));          
	        for(int i=0;i<languageNums;i++){
	            ResumeLanguage resumeLanguage=new ResumeLanguage();
	            Element tr=trs.get(i);
	            String languageName="";//语言能力名称
	            String languageDescribe="";//语言能力描述
	            languageName=tr.select("td:eq(0)").text().trim();
	            languageDescribe=tr.select("td:eq(1)").text().trim();
	            resumeLanguage.setName(languageName);
	            resumeLanguage.setDescribes(languageDescribe);
	            resumeLanguageList.add(resumeLanguage);
	            System.out.println("语言能力名称："+languageName+"\n"+"语言能力描述："+languageDescribe+"\n");        
	        }
		}catch(Exception e){
		    e.printStackTrace();
            logger.error("简析新安人才网简历中的语言能力信息出错！", e.getMessage()); 
            throw  new Exception(e);
		}
		
	}
	
	 /**
     * 简析简历中的专业技能模块
     * @throws Exception 
     */
    public  static void analysisMajorSkill(Element element,List<Object>list) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeMajorSkill> resumeMajorSkillList=(List<ResumeMajorSkill>) list.get(6);
        try {
            Elements trs = element.select("tbody:eq(0)").first().children();
            int languageNums= trs.size();
            System.out.println("专业技能数："+languageNums);          
            for(Element tr:trs){
                ResumeMajorSkill resumeMajorSkill=new ResumeMajorSkill();
                String skillName="";//专业技能名称
                String masterdegree="";//专业技能描述
                String useTime="";//专业技能使用时间
                skillName=tr.select("td:eq(0)").text().trim();
                resumeMajorSkill.setSkillName(skillName);
                useTime=tr.select("td:eq(1)").text().trim();
                resumeMajorSkill.setUseTime(useTime);
                masterdegree=tr.select("td:eq(2)").text().trim();;
                resumeMajorSkill.setMasterDegree(masterdegree);
                resumeMajorSkillList.add(resumeMajorSkill);
                System.out.println("专业技能名称："+skillName+"\n"+"专业技能描述："+masterdegree+"\n");    
            } 
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的专业技能信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
    }
	/**
	 * 简析简历中的荣誉奖励模块
	 * @throws Exception 
	 */
	public  static void analysisHonour(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
	    List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    try {
	        Elements trs= element.select("tbody").eq(0).first().children();
	        int honourNums= trs.size();
	        System.out.println("获得奖励数："+honourNums);            
	        for(Element tr: trs){
	            ResumePrize resumePrize=new ResumePrize();
	            String honourName="";//奖励名称
	            String honourTime="";//奖励时间
	            honourTime=tr.select("td:eq(0)").text().trim();
	            honourName=tr.select("td:eq(1)").text().trim();
	            resumePrize.setPrizeName(honourName);
	            String patten="yyyy年MM月";
	            resumePrize.setGainTime(TimeUtil.getDateByTime(honourTime,patten));
	            resumePrizeList.add(resumePrize);
	            System.out.println("奖励时间："+honourTime+"\n"+"奖励名称："+honourName+"\n");        
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的荣誉奖励信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }		
	}
	/**
     * 简析简历中的校内职务模块
     * @throws Exception 
     */
    public  static void analysisSchoolPost(Element element,List<Object>list) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeSchoolPost> resumeSchoolPostList=(List<ResumeSchoolPost>) list.get(10);
        try {
            Elements tables= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
            int schoolPracticeNums= tables.size();
            System.out.println("校内职务数："+schoolPracticeNums);        
            for(Element table:tables){
                ResumeSchoolPost resumeSchoolPost=new ResumeSchoolPost();
                String schoolaJobTime="";//校内职务时间
                String schoolaJobName="";//校内职务名称   
                String schoolaJobDescribe="";//职务描述
                schoolaJobTime=table.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim();
                int index=schoolaJobTime.indexOf("-");
                String start_time=schoolaJobTime.substring(0, index);
                String end_time=schoolaJobTime.substring(index+1,schoolaJobTime.length());
                String patten="yyyy/MM";
                resumeSchoolPost.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    resumeSchoolPost.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
                schoolaJobName=table.select("tbody:eq(0)>tr:eq(0)>td:eq(1)").first().text().trim();
                resumeSchoolPost.setPostName(schoolaJobName);
                Elements tbale1s=table.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().children();
                if(tbale1s!=null && !tbale1s.isEmpty()){
                    schoolaJobDescribe=tbale1s.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().text().trim();
                    resumeSchoolPost.setDescribes(schoolaJobDescribe);
                }
                resumeSchoolPostList.add(resumeSchoolPost);        
            } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的校内职务信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }       
    }
	/**
	 * 简析简历中的社会实践模块
	 * @throws Exception 
	 */
	public  static void analysisSchoolPractice(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumePracticeExperience> resumePracticeExperienceList=(List<ResumePracticeExperience>) list.get(7);
	    try {
	        Elements tables= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().children();
	        int schoolPracticeNums= tables.size();
	        System.out.println("在校实践数："+schoolPracticeNums);        
	        for(Element table:tables){
	            ResumePracticeExperience resumePracticeExperience=new ResumePracticeExperience();
	            String practiceName="";//实践名称
	            String practiceTime="";//实践时间
	            String practiceType="";//实践类型
	            String practiceDescribe="";//实践简述        
	            practiceTime=table.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim();
	            int index=practiceTime.indexOf("-");
                String start_time=practiceTime.substring(0, index);
                String end_time=practiceTime.substring(index+1,practiceTime.length());
                String patten="yyyy/MM";
                resumePracticeExperience.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                if(end_time.indexOf("至今")==-1){
                    resumePracticeExperience.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
                }
                practiceName=table.select("tbody:eq(0)>tr:eq(0)>td:eq(1)").first().text().trim();
                resumePracticeExperience.setPracticeName(practiceName);
                Elements tbale1s=table.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().children();
                if(tbale1s!=null && !tbale1s.isEmpty()){
                    practiceDescribe=tbale1s.select("tbody:eq(0)>tr:eq(1)>td:eq(0)").first().text().trim();
                    resumePracticeExperience.setPracticeDescribe(practiceDescribe);  
                }                
	            resumePracticeExperienceList.add(resumePracticeExperience);
	            System.out.println("实践时间："+practiceTime+"\n"+"实践类型："+practiceType+"\n"+
	                               "实践名称："+practiceName+"\n"+"实践简述："+practiceDescribe+"\n");        
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的社会实践信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }		
	}
	
		
	
	/**
	 * 简析简历中的自我评价模块
	 */
	public  static void analysisEvaluation(Element element,List<Object>list){
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            String evaluation= element.select("tbody:eq(0)>tr:eq(0)>td:eq(0)").first().text().trim();
            resumeBase.setEvaluation(evaluation);
            System.out.println("自我评价："+evaluation+"\n");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的自我评价信息出错！", e.getMessage()); 
        }				
	}
	


	public static void main(String[] args) {
	     String docPath = "E:/temp/attachment/456.html";//
	     File file=new File(docPath);
	     dealHtmlResumeByResource(file);
	}
}
