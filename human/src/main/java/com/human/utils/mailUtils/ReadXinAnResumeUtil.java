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
@Component("readXinAnResumeUtil")
public class ReadXinAnResumeUtil {
    
    private static Logger logger = LogManager.getLogger(ReadXinAnResumeUtil.class);
            
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
			Document doc = Jsoup.parse(input, "GB2312");
			/*
			 * 获取HTML的编码格式
			 */
			String content=doc.select("head>meta").eq(0).first().attr("content");
			String charset=content.split(";")[1].trim().split("=")[1];
			if(!"gb2312".equalsIgnoreCase(charset)){
                doc = Jsoup.parse(input, charset); 
            }			
			analysisBaseInformation(doc,list);			
			Elements tagDivs=doc.select("div:eq(1)").first().children();
			int tagDivNums=tagDivs.size();
			for(int i=3;i<tagDivNums;i++){
				Element tagDiv = tagDivs.get(i);//
				String htmlText=tagDiv.text().trim();
				if("".equals(htmlText)){
					continue;					
				}
				String tagName=tagDiv.select("div").eq(0).first().select("strong:eq(0)").first().text().trim();
				if("个人概况".equals(tagName)){
					//analysisPersonalInformation(tagDiv);
				}else if("联系方式".equals(tagName)){
					analysisContactInformation(tagDiv,list);
				}else if("工作经历".equals(tagName)){
					analysisWorkHistory(tagDiv,list);
				}else if("求职意向".equals(tagName)){
					analysisExpectWork(tagDiv,list);
				}else if("语言/教育/培训".equals(tagName)){
					analysisAbility(tagDiv,list);					
				}else if("所获证书".equals(tagName)){
					analysisCertificate(tagDiv,list);
				}else if("IT相关".equals(tagName)){
					
				}else if("学生相关".equals(tagName)){
					analysisStudentInformation(tagDiv,list);
				}else if("个人信息".equals(tagName)){
					
				}else if("自我评价".equals(tagName)){
					analysisEvaluation(tagDiv,list);
				}
			}
			
		}catch (Exception e) {
		    e.printStackTrace();
			logger.error("简析新安人才网简历出错！", e.getMessage());
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
	    try{
	        Elements div=doc.select("div:eq(1)").first().children().select("div").eq(1);//   	        
	        Element et=div.select("span:eq(1)>strong:eq(0)").first();
	        if(et==null){
	            div=doc.select("div:eq(1)").first().children().select("div").eq(0); 
	        }
	        String name=div.select("span:eq(1)>strong:eq(0)").first().text().trim();	       
	        resumeSeeker.setName(name);     
	        resumeBase.setName(name);	        
	        Element elmt=div.first().nextElementSibling().select("table:eq(0)>tbody:eq(0)>tr:eq(0)").first();       
	        Element div1=elmt.select("td:eq(0)>div:eq(0)").first();     
	        String text1=div1.select("span:eq(0)").first().text().trim().replaceAll(" ", "");	       
	        if(text1.indexOf("男")!=-1){
	            resumeSeeker.setSex("M");
	            resumeBase.setSex("M");
	        }
	        if(text1.indexOf("女")!=-1){
	            resumeSeeker.setSex("F");
	            resumeBase.setSex("F");
	        }
	        Element div2=div1.children().select("div").eq(0).first();
	        String text2= div2.text().trim().replaceAll(" ", "|").replaceAll("　", "");	        
	        if(text2.indexOf("工作年限")!=-1){
	            int start=text2.indexOf("工作年限")+5;
	            int end=text2.indexOf("|");
	            String workTime=text2.substring(start,end);
	            resumeSeeker.setWorkTime(workTime);
	            resumeBase.setWorkingTime(workTime);
	        }
	        if(text2.indexOf("现居住地")!=-1){
	            String locationCity="";
	            int start=text2.indexOf("现居住地")+5;
	            int end=text2.lastIndexOf("|");         
	            if(text2.indexOf("|")!=end){
	                locationCity=text2.substring(start,end);
	            }else{
	                locationCity=text2.substring(start,text2.length());
	            }
	            resumeSeeker.setLocationCity(locationCity);
	            resumeBase.setLocationCity(locationCity);
	        }
	        if(text2.indexOf("身份证")!=-1){
	            int start=text2.indexOf("身份证")+4;
	            String idCardNo=text2.substring(start,text2.length());
	            resumeSeeker.setIdCardNo(idCardNo);
	            resumeBase.setCertificatesNumber(idCardNo);
	        }
	        Element div3=div1.children().select("div").eq(1).first();
	        String text3= div3.text().trim().replaceAll(" ", "|").replaceAll("　", "");
	        if(text3.indexOf("户口")!=-1){
	            String household_register="";
	            int start=text3.indexOf("户口")+3;
	            int end=text3.lastIndexOf("|");         
	            if(text3.indexOf("|")!=end){
	                household_register=text3.substring(start,end);
	            }else{
	                household_register=text3.substring(start,text3.length());
	            }
	            resumeBase.setHouseholdRegister(household_register);
	        }
	        String headUrl=elmt.select("td:eq(1)>div:eq(0)").first().select("div:eq(0)>a:eq(0)>img:eq(0)").attr("src"); 	       
	        if(!headUrl.endsWith("nopic.gif")){
	            resumeSeeker.setHeadUrl(headUrl);
	            resumeBase.setHeadUrl(headUrl);
	        }
	                
//	        Element div4=div1.children().select("div").eq(2).first();
//	        if(div4!=null){
//	            String text4= div4.text().trim().replaceAll(" ", "|");
//	            System.out.println("标签："+text4);
//	        }
	        System.out.println("姓名："+name);
	        System.out.println("基本信息1："+text1);
	        System.out.println("基本信息2："+text2);
	        System.out.println("基本信息3："+text3);
	        System.out.println("图像："+headUrl);	        
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
            Elements divs = element.select("table").eq(0).first().select("tr:eq(0)>td:eq(0)").first().children();
            for(Element div:divs){ 
                if(div.select("span:eq(0)").first()!=null){
                    String spanName=div.select("span:eq(0)").first().text().trim().replaceAll("　", "").replaceAll(" ", "");
                    String text=div.text().trim().replaceAll("　", "");
                    if(spanName.contains("手机")){
                        String phone=text.substring(text.length()-11, text.length()).trim();
                        if(Common.isMobile(phone)){
                            resumeSeeker.setPhone(phone);
                            resumeBase.setTelephone(phone); 
                        }else{
                            int start=text.indexOf("手机")+3;
                            phone=text.substring(start, start+11).trim();
                            if(Common.isMobile(phone)){
                                resumeSeeker.setPhone(phone);
                                resumeBase.setTelephone(phone); 
                            }
                        }
                        System.out.println("手机号："+phone);                        
                    }else if(spanName.contains("E—MAIL")){
                        String email=div.childNodes().get(2).outerHtml().trim();
                        System.out.println("邮箱："+email);
                        resumeSeeker.setEmail(email);
                        resumeBase.setEmail(email);
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
	        Elements trs=element.children().select("div");
	        for(int i=1,j=trs.size();i<j;i++){
	            ResumeWorkHistory resumeWorkHistory=new ResumeWorkHistory();    
	            Element div=trs.get(i);
	            String htmlText=div.text().trim();
	            if("".equals(htmlText)){
	                continue;                   
	            }           
	            String text1=div.select("span:eq(0)").first().text().trim().replaceAll("　", "").replaceAll(" ", "");
	            int index=text1.indexOf("至");
	            int index1=text1.indexOf("(");
	            int index2=text1.indexOf(")");
	            String start_time=text1.substring(0,index).trim();
	            String patten="yyyy年MM月";
                resumeWorkHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(index1!=-1 && index2!=-1 ){
	                String end_time=text1.substring(index+1,index1).trim();
	                String work_time=text1.substring(index1+1,index2).trim();	               
	                if(end_time.indexOf("年")!=-1){
	                    resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
	                }
	                resumeWorkHistory.setWorkTime(work_time);  
                }else{
                    String end_time=text1.substring(index+1).trim();
                    if(end_time.indexOf("年")!=-1){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    }
                }	            
	            text1=div.select("strong").eq(0).first().text().trim().replaceAll("　", "").replaceAll(" ", "");
	            index=text1.indexOf("|");
	            String company_name=text1.substring(0,index);
	            String position=text1.substring(index+1,text1.length());
	            if(position.indexOf("找TA的同事")!=-1){
	                index=position.indexOf("找TA的同事");
	                position=position.substring(0, index);
	            }
	            resumeWorkHistory.setCompanyName(company_name);
	            resumeWorkHistory.setPosition(position);
	            text1=div.text().trim().replaceAll("　", "").replaceAll(" ", "");
	            if(text1.indexOf("所属行业")!=-1){
	                index=text1.indexOf("所属行业");
	            }
	            if(text1.indexOf("公司性质")!=-1){
	                index1=text1.indexOf("公司性质");
	            }
	            if(text1.indexOf("所在部门")!=-1){
	                index2=text1.indexOf("所在部门");           
	             }
	            int index3=0;
	            if(text1.indexOf("工作描述")!=-1){
	               index3=text1.indexOf("工作描述");              
	            }
	            String work_property=text1.substring(index+5,index1-1);
	            resumeWorkHistory.setWorkProperty(work_property);
	            String company_scale=text1.substring(index1+5,index2-1);
	            resumeWorkHistory.setCompanyScale(company_scale); 
	            if(index3!=0){
	                String describe=text1.substring(index3+5,text1.length());
	                resumeWorkHistory.setDescribes(describe); 
	                String department=text1.substring(index2+5,index3);
	                resumeWorkHistory.setDepartment(department);
	            }else{
	                String department=text1.substring(index2+5,text1.length());
                    resumeWorkHistory.setDepartment(department);
	            }
	            resumeWorkHistoryList.add(resumeWorkHistory);
	        } 
	      }catch(Exception e){
	        e.printStackTrace();
	        logger.error("简析新安人才网简历中的工作经历信息出错！", e.getMessage());
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
            Elements trs = element.select("table").eq(0).select("tr:eq(0)>td:eq(0)>table:eq(0)>tbody:eq(0)").first().children();
            for (Element tr : trs) {
                String key = "";
                String value = "";
                key = tr.select("td").eq(0).text().trim().replaceAll("　", "").replaceAll(" ", "");
                value = tr.select("td").eq(1).text().trim().replaceAll("　", "").replaceAll(" ", "");
                if(key.indexOf("求职性质")!=-1){
                    resumeIntention.setExpectWorkProperty(value);
                }else if(key.indexOf("薪金要求")!=-1){
                    resumeIntention.setExpectWorkSalary(value); 
                }else if(key.indexOf("行业要求")!=-1){
                    resumeIntention.setExpectWorkIndustry(value);
                }else if(key.indexOf("职能职位")!=-1){
                    resumeIntention.setExpectWorkJob(value);
                }else if(key.indexOf("工作地点")!=-1){
                    resumeIntention.setExpectWorkPlace(value);
                }else if(key.indexOf("求职状态")!=-1){
                    if(value.indexOf("(")!=-1){
                        int index=value.indexOf("(");                  
                        String currentStatus=value.substring(0, index);
                        resumeIntention.setCurrentStatus(currentStatus);                    
                    }
                    if(value.indexOf("到岗时间")!=-1 && value.indexOf(")")!=-1){
                        int start=value.indexOf("到岗时间")+5;
                        int end=value.indexOf(")");                  
                        String expect_arrival_time=value.substring(start,end);
                        resumeIntention.setExpectArrivalTime(expect_arrival_time);           
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
	 * 简析简历中的语言/教育/培训模块
	 * @param element
	 * @throws Exception 
	 */
	
	public  static void analysisAbility(Element element,List<Object>list) throws Exception{
	    try {
	        Elements divs=element.children();
	        int size=divs.size();
	        for(int i=2;i<size;i+=3){
	            Element div=divs.get(i);
	            String tagName=div.select("span:eq(0)").first().text().trim();
	            if("外语能力".equals(tagName)){
	                analysisLanguageAbility(div,list);
	            }else if("教育经历".equals(tagName)){
	                analysisEducationHistory(div,list);
	            }else if("培训经历".equals(tagName)){
	                analysisTrainingHistory(div,list);
	            }
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的语言/教育/培训模块出错！", e.getMessage());
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
	        Element table=element.nextElementSibling().select("table").eq(0).first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        int educationHistoryNums= trs.size();
	        System.out.println("教育经历总数："+(educationHistoryNums-1));                 
	        for(int i=1;i<educationHistoryNums;i++){
	            ResumeEducationHistory resumeEducationHistory=new ResumeEducationHistory();
	            Element tr=trs.get(i);
	            String educationTime="";//教育时间
	            String schoolName="";//学校名称
	            String education="";//学历
	            String majorName="";//专业名称          
	            educationTime=tr.select("td:eq(0)").text().trim();
	            int index=educationTime.indexOf("至");
	            String start_time=educationTime.substring(0, index);
	            String end_time=educationTime.substring(index+1,educationTime.length());
	            String patten="yyyy年MM月";
	            resumeEducationHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("年")!=-1){
	                resumeEducationHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }
	            schoolName=tr.select("td:eq(1)").text().trim();
	            resumeEducationHistory.setSchoolName(schoolName);
	            education=tr.select("td:eq(2)").text().trim();
	            resumeEducationHistory.setEducation(education);
	            majorName=tr.select("td:eq(3)").text().trim();
	            resumeEducationHistory.setMajor(majorName);
	            resumeEducationHistoryList.add(resumeEducationHistory);
	            System.out.println("教育时间："+educationTime+"\n"+"学校名称："+schoolName+"\n"+"专业名称："+majorName+"\n"+"学历："+education+"\n");
	        } 
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
	        Element table=element.nextElementSibling().select("table").eq(0).first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        int trainingHistoryNums= trs.size();
	        System.out.println("培训经历总数："+(trainingHistoryNums-1));      
	        for(int i=1;i<trainingHistoryNums;i++){
	            ResumeTrainHistory resumeTrainHistory=new ResumeTrainHistory();
	            Element tr=trs.get(i);
	            String trainingTime="";//培训时间
	            String trainingCompany="";//培训公司
	            String trainingName="";//培训名称           
	            String trainingCertificate="";//培训所获证书              
	            trainingTime=tr.select("td:eq(0)").text().trim();
	            int index=trainingTime.indexOf("至");
	            String start_time=trainingTime.substring(0, index);
	            String end_time=trainingTime.substring(index+1,trainingTime.length());
	            String patten="yyyy年MM月";
	            resumeTrainHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("年")!=-1){
	                resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }
	            trainingCompany=tr.select("td:eq(1)").text().trim();
	            resumeTrainHistory.setTrainCompany(trainingCompany);
	            trainingName=tr.select("td:eq(2)").text().trim();
	            resumeTrainHistory.setTrainName(trainingName);
	            trainingCertificate=tr.select("td:eq(3)").text().trim();
	            resumeTrainHistory.setCertificate(trainingCertificate);
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
	        Elements spans=element.select("table").eq(0).first().select("tr:eq(0)>td:eq(0)>div:eq(0)").first().children();
	        int certificateNums= spans.size();
	        System.out.println("获得证书总数："+certificateNums);      
	        for(Element span:spans){
	            ResumeCertificate resumeCertificate=new ResumeCertificate();
	            String certificateTime="";//证书时间
	            String certificateName="";//证书名称
	            String text =StringUtils.deleteWhitespace(span.text()).replace("|", ";");
	            String[] text1=text.split(";");
	            certificateTime=text1[0].trim();
	            String patten="yyyy年MM月";
	            resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
	            certificateName=text1[1].trim();
	            resumeCertificate.setCertificateName(certificateName);
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
		    Element table=element.nextElementSibling().select("table").eq(0).first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        int languageNums= trs.size();
	        System.out.println("所会语言数："+(languageNums-1));          
	        for(int i=1;i<languageNums;i++){
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
	 * 简析简历中的获得奖励模块
	 * @throws Exception 
	 */
	public  static void analysisHonour(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
	    List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    try {
	        Element table=element.nextElementSibling().select("table").eq(0).first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        int honourNums= trs.size();
	        System.out.println("获得奖励数："+honourNums);            
	        for(Element tr: trs){
	            ResumePrize resumePrize=new ResumePrize();
	            String honourName="";//奖励名称
	            String honourTime="";//奖励时间
	            honourTime=tr.select("td:eq(0)").text().trim();
	            honourName=tr.select("td:eq(1)").text().trim();
	            resumePrize.setPrizeName(honourName);
	            String patten="yyyy-MM-dd";
	            resumePrize.setGainTime(TimeUtil.getDateByTime(honourTime,patten));
	            resumePrizeList.add(resumePrize);
	            System.out.println("奖励时间："+honourTime+"\n"+"奖励名称："+honourName+"\n");        
	        }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的获得奖励信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }		
	}
	
	/**
	 * 简析简历中的在校实践模块
	 * @throws Exception 
	 */
	public  static void analysisSchoolPractice(Element element,List<Object>list) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumePracticeExperience> resumePracticeExperienceList=(List<ResumePracticeExperience>) list.get(7);
	    try {
	        Element table=element.nextElementSibling().select("table").eq(0).first();
	        Elements trs= table.select("tbody").eq(0).first().children();
	        Elements trs1= trs.select("tr.bch");
	        int schoolPracticeNums= trs1.size();
	        System.out.println("在校实践数："+schoolPracticeNums);        
	        for(int i=0;i<schoolPracticeNums;i++){
	            ResumePracticeExperience resumePracticeExperience=new ResumePracticeExperience();
	            String practiceName="";//实践名称
	            String practiceTime="";//实践时间
	            String practiceType="";//实践类型
	            String practiceDescribe="";//实践简述           
	            practiceTime=trs.get(4*i).select("td:eq(1)").text().trim();
	            int index=practiceTime.indexOf("至");
	            String start_time=practiceTime.substring(0, index);
	            String end_time=practiceTime.substring(index+1,practiceTime.length());
	            String patten="yyyy年MM月";
	            resumePracticeExperience.setStartTime(TimeUtil.getDateByTime(start_time,patten));
	            if(end_time.indexOf("年")!=-1){
	                resumePracticeExperience.setEndTime(TimeUtil.getDateByTime(end_time,patten)); 
	            }
	            practiceType=trs.get(4*i+1).select("td:eq(1)").text().trim();
	            practiceName=trs.get(4*i+2).select("td:eq(1)").text().trim();
	            resumePracticeExperience.setPracticeName(practiceName);
	            practiceDescribe=trs.get(4*i+3).select("td:eq(1)").text().trim();
	            resumePracticeExperience.setPracticeDescribe(practiceDescribe);
	            resumePracticeExperienceList.add(resumePracticeExperience);
	            System.out.println("实践时间："+practiceTime+"\n"+"实践类型："+practiceType+"\n"+
	                               "实践名称："+practiceName+"\n"+"实践简述："+practiceDescribe+"\n");        
	        } 
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的在校实践信息出错！", e.getMessage()); 
            throw  new Exception(e);
        }		
	}
	
		
	
	/**
	 * 简析简历中的自我评价模块
	 */
	public  static void analysisEvaluation(Element element,List<Object>list){
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            String evaluation= element.children().select("div").eq(1).select("p").eq(0).text();
            resumeBase.setEvaluation(evaluation);
            System.out.println("自我评价："+evaluation+"\n");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的自我评价信息出错！", e.getMessage()); 
        }				
	}
	


	public static void main(String[] args) {
	     String docPath = "E:/temp/attachment/123.html";//
	     File file=new File(docPath);
	     dealHtmlResumeByResource(file);
	}
}
