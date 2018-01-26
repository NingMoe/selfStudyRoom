package com.human.utils.mailUtils;

import java.io.File;
import java.util.ArrayList;
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
import com.human.resume.entity.ResumePracticeExperience;
import com.human.resume.entity.ResumePrize;
import com.human.resume.entity.ResumeProjectExperience;
import com.human.resume.entity.ResumeSchoolPost;
import com.human.resume.entity.ResumeTrainHistory;
import com.human.resume.entity.ResumeWorkHistory;
import com.human.utils.Common;
import com.human.utils.ReduceHtmlTextUtil;
import com.human.utils.TimeUtil;

@Component("newReadXinAnResumeUtil")
public class NewReadXinAnResumeUtil extends CommonReadResumeUtil{
    
    private static Logger logger = LogManager.getLogger(NewReadXinAnResumeUtil.class);
    
    
    public  NewReadXinAnResumeUtil(){}
    
    
	public NewReadXinAnResumeUtil(ResumeModularDao rmDao, ResumeKeywordDao rkDao) {
	    super(rmDao,rkDao);
    }


    /**
	 * 解析新安人才招聘网的简历
	 * @param docPath  html格式的简历文件
	 */
	public List<Object> dealHtmlResumeByResource(File input){
	    List<Object>list=EncapsulationResumeUtil.getResumeObject();
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
			String contentText = ReduceHtmlTextUtil.removeHtmlTag(doc.toString()).replace("*", "");
            List<String>resumeModularKeyWords=super.getResumeModularKeyWords("1");
            /*<!-- 基本信息 -->*/
			analysisBaseInformation(contentText,list,resumeModularKeyWords);
			/*<!-- 联系方式 -->*/
			analysisContactInformation(contentText,list,resumeModularKeyWords);
			/*<!-- 求职意向 -->*/
			analysisExpectWork(contentText,list,resumeModularKeyWords);
			/*<!-- 工作经历 -->*/
			analysisWorkHistory(contentText,list,resumeModularKeyWords);
			/*<!-- 项目经验 -->*/
			analysisProjectExperience(contentText,list,resumeModularKeyWords);
			/*<!-- 教育经历 -->*/
			analysisEducationHistory(contentText,list,resumeModularKeyWords);
			/*<!-- 个人技能之培训经历 -->*/
			analysisTrainingHistory(contentText,list,resumeModularKeyWords);
			/*<!-- 个人技能之证书 -->*/
			analysisCertificate(contentText,list,resumeModularKeyWords);
			/*<!-- 个人技能之语言能力 -->*/
			analysisLanguageAbility(contentText,list,resumeModularKeyWords);
			/*<!-- 个人技能之IT技能 -->*/
			analysisMajorSkill(contentText,list,resumeModularKeyWords);
			/*<!-- 在校情况之荣誉奖励-->*/
			analysisHonour(contentText,list,resumeModularKeyWords);
			/*<!-- 在校情况之校内职务-->*/
			analysisSchoolPost(contentText,list,resumeModularKeyWords);
			/*<!-- 在校情况之社会实践-->*/
			analysisSchoolPractice(contentText,list,resumeModularKeyWords);
			/*<!-- 自我评价-->*/
			analysisEvaluation(contentText,list,resumeModularKeyWords);
		}catch (Exception e) {
		    e.printStackTrace();
			logger.error("简析新安人才网简历出错！", e.getMessage());
			System.out.println("新安人才网简历路径-------"+input.getAbsolutePath()+"\n"+"新安人才网简历路径-------"+input.getName());
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
	public void analysisBaseInformation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
	    ResumeBase resumeBase=(ResumeBase) list.get(1);
	    try{
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人简历",resumeModularKeyWords);
	        //截取姓名
	        int index1=contentText.indexOf("[");
	        if(index1!=-1){
	            String name=contentText.substring(0,index1).trim();
	            System.out.println("姓名："+name);
	            resumeSeeker.setName(name);     
	            resumeBase.setName(name); 
	        }	         
            //截取性别
	        index1=contentText.indexOf("]");
	        int index2=contentText.indexOf("/");
	        if(index1!=-1 && index2!=-1){
	            String value=contentText.substring(index1+1,index2).trim(); 
	            if(value.indexOf("男")!=-1){
                    System.out.println("性别："+value);
                    resumeSeeker.setSex("M");
                    resumeBase.setSex("M");
                }
                if(value.indexOf("女")!=-1){
                    System.out.println("性别："+value);
                    resumeSeeker.setSex("F");
                    resumeBase.setSex("F");
                }
	        }   
            //基本信息模块的所有关键词(后期通过数据库查询)
	        List<String>keyWords=super.getResumeKeyWords("1","基本信息");
	        //截取工作经验
	        String baseText=SearchFiledByKeyWordsUtil.searchTextByKeyWords(contentText,keyWords);
	        String[] sub=baseText.split("/");
            for(int i=1;i<sub.length;i++){
                 String textValue=sub[i];
                 if(textValue.indexOf("岁")!=-1 && textValue.indexOf("(")!=-1 && textValue.indexOf(")")!=-1){
                     int start=textValue.indexOf("(");
                     int end =textValue.indexOf(")");
                     String birthDate=textValue.substring(start+1,end);
                     System.out.println("生日："+birthDate);
                     resumeSeeker.setBirthDate(birthDate);
                     resumeBase.setBirthDate(birthDate);
                     continue;
                 }
                 if(textValue.indexOf("未婚")!=-1){
                     resumeBase.setMarriage("1");
                     resumeSeeker.setMarriage("1");
                     continue;
                 }
                 if(textValue.indexOf("已婚")!=-1){
                     resumeBase.setMarriage("2");
                     resumeSeeker.setMarriage("2");
                     continue;
                 }
                 if(textValue.indexOf("工作经验")!=-1){
                     int endIndex=textValue.indexOf("工作经验");
                     String workTime=textValue.substring(0,endIndex);
                     System.out.println("工作经验年限："+workTime);
                     resumeSeeker.setWorkTime(workTime);
                     resumeBase.setWorkingTime(workTime);
                     continue;
                 }                
            }
            //查找最高学历
            String text=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"最高学历",keyWords);
            if(StringUtils.isNotEmpty(text)){
                String[]eduStrings=text.split("/");
                String education="";
                String schoolName="";
                String majorName="";
                if(eduStrings.length==3){
                    education=eduStrings[0];
                    schoolName=eduStrings[1];
                    majorName=eduStrings[2];
                }else if(eduStrings.length==2){
                    education=eduStrings[0];
                    schoolName=eduStrings[1];
                }
                resumeBase.setMajor(majorName);
                resumeBase.setHighEdu(education);
                resumeBase.setGraSchool(schoolName);
                resumeSeeker.setMajor(majorName);
                resumeSeeker.setHighEdu(education);
                resumeSeeker.setGraSchool(schoolName);
            }
	        //查找现居住地户口
            String locationCity=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"现居住地",keyWords);
            System.out.println("现居住地："+locationCity);
            resumeSeeker.setLocationCity(locationCity);
            resumeBase.setLocationCity(locationCity);
            //查找户口
            String household_register=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"户口",keyWords);
            System.out.println("户口："+household_register);
            resumeBase.setHouseholdRegister(household_register);    
	    }catch(Exception e){
	       e.printStackTrace();
	       logger.error("简析新安人才网简历中的基本信息模块出错！", e.getMessage());	 
	       throw  new Exception(e);
	    }	
	}

	
	/**
	 * 简析简历中的联系方式信息
	 * @throws Exception 
	 */
	public void analysisContactInformation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try{
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"联系方式",resumeModularKeyWords);
            contentText=contentText.replaceAll("[　*| *| *|\\s*]*", "");
            //联系方式模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","联系方式");
            //查找手机
            String telephone=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"手机",keyWords);
            System.out.println("手机号："+telephone); 
            if(Common.isMobile(telephone)){
                resumeSeeker.setPhone(telephone);
                resumeBase.setTelephone(telephone); 
            }
            //查找邮箱
            String email=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"邮箱",keyWords);
            System.out.println("邮箱："+email);
            resumeSeeker.setEmail(email);
            resumeBase.setEmail(email);
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
	public  void analysisWorkHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeWorkHistory> resumeWorkHistoryList=(List<ResumeWorkHistory>) list.get(12);
	    try{
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作经历",resumeModularKeyWords);
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
            List<String>keyWords=super.getResumeKeyWords("1","工作经历");
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
                int index2=contextValue.indexOf("(");
                int index3=contextValue.indexOf(")");                  
                if(index1!=-1 && index2!=-1 && index3!=-1){
                    String end_time=contextValue.substring(index1+1,index2).trim();
                    System.out.println("结束时间："+end_time);
                    String work_time=contextValue.substring(index2+1,index3).trim();                  
                    if(end_time.indexOf("至今")==-1){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    }
                    resumeWorkHistory.setWorkTime(work_time);  
                }else{
                    String end_time=contextValue.substring(8,15).trim();
                    System.out.println("结束时间："+end_time);
                    if(end_time.indexOf("至今")==-1){
                        resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    }
                }
                //截取公司名称和职位名称
                end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
                if(index3!=-1 && end!=-1){
                   String text=contextValue.substring(index3+1,end).trim();
                   if(text.endsWith("|")){
                       text=text.substring(0, text.length()-1);
                   }
                   index1=text.indexOf("|");
                   index2=text.lastIndexOf("|");
                   String company_name="";
                   String position="";
                   if(index1==index2 && index1!=-1){
                       company_name=text.substring(0,index1).trim();
                       position=text.substring(index1+1,text.length()).trim();
                   }else if(index1!=index2 && index2!=-1){
                       company_name=text.substring(index1+1,index2).trim();
                       position=text.substring(index2+1,text.length()).trim();    
                   }
                   if(position.indexOf("找TA的同事")!=-1){
                       index1=position.indexOf("找TA的同事");
                       position=position.substring(0, index1);
                   }
                   System.out.println("公司名称："+company_name);
                   System.out.println("职位名称："+position);
                   resumeWorkHistory.setCompanyName(company_name);
                   resumeWorkHistory.setPosition(position);
              }
              //查找平均月薪  
              String salary=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"平均月薪",keyWords); 
              System.out.println("平均月薪："+salary);
              resumeWorkHistory.setSalary(salary);
              //查找所属行业  
              String work_property=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"所属行业",keyWords);   
              System.out.println("所属行业："+work_property);
              resumeWorkHistory.setWorkProperty(work_property);  
              //查找公司性质  
              String company_scale=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"公司性质",keyWords);  
              System.out.println("公司性质："+company_scale);
              resumeWorkHistory.setCompanyScale(company_scale); 
              //查找所在部门
              String department=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"所在部门",keyWords);  
              System.out.println("所在部门："+department);
              resumeWorkHistory.setDepartment(department);
              //查找工作描述
              String describe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"工作描述",keyWords);
              System.out.println("工作描述："+describe);
              resumeWorkHistory.setDescribes(describe); 
              resumeWorkHistoryList.add(resumeWorkHistory);
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
    public  void analysisProjectExperience(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeProjectExperience> resumeProjectExperienceList=(List<ResumeProjectExperience>) list.get(9);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"项目经验",resumeModularKeyWords);
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
            List<String>keyWords=super.getResumeKeyWords("1","项目经验");
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
                //截取项目名称和公司名称
                end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
                if(end!=-1){
                    String text1=contextValue.substring(index, end);
                    index=text1.indexOf("/");
                    if(index!=-1){
                        String projectName=text1.substring(0,index);
                        String companyName=text1.substring(index+1);
                        rpe.setProjectName(projectName);
                        rpe.setCompanyName(companyName);
                    }                
                }
                //查找项目描述
                String projectDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"项目描述",keyWords); 
                System.out.println("项目描述："+projectDescribe);
                rpe.setProjectDescribe(projectDescribe);
                //查找责任描述
                String responsibilityDescribe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"责任描述",keyWords);
                System.out.println("责任描述："+responsibilityDescribe);
                rpe.setResponsibilityDescribe(responsibilityDescribe);
                resumeProjectExperienceList.add(rpe);  
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
	public  void analysisExpectWork(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
        try{
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"求职意向",resumeModularKeyWords);
            contentText=contentText.replaceAll("[　*| *| *|\\s*]*", "");
            //求职意向模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","求职意向");
            //查找求职性质
            String expectWorkProperty=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"求职性质",keyWords);
            System.out.println("求职性质："+expectWorkProperty); 
            resumeIntention.setExpectWorkProperty(expectWorkProperty);
            //查找月薪要求
            String expectWorkSalary=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"月薪要求",keyWords);
            System.out.println("月薪要求："+expectWorkSalary);
            resumeIntention.setExpectWorkSalary(expectWorkSalary);
            //查找行业要求
            String expectWorkIndustry=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"行业要求",keyWords);
            System.out.println("行业要求："+expectWorkIndustry);
            resumeIntention.setExpectWorkIndustry(expectWorkIndustry);
            //查找职能意向
            String expectWorkJob=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"职能意向",keyWords);
            System.out.println("职能意向："+expectWorkJob);
            resumeIntention.setExpectWorkJob(expectWorkJob);
            //查找工作地点
            String expectWorkPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作地点",keyWords);
            System.out.println("工作地点："+expectWorkPlace);
            resumeIntention.setExpectWorkPlace(expectWorkPlace);
            //查找求职状态
            String currentStatus=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"求职状态",keyWords);
            System.out.println("求职状态："+currentStatus);
            resumeIntention.setCurrentStatus(currentStatus); 
            //查找到岗时间
            String expect_arrival_time=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"到岗时间",keyWords);
            System.out.println("到岗时间："+expect_arrival_time);
            resumeIntention.setExpectArrivalTime(expect_arrival_time);            
        }catch(Exception e){
            e.printStackTrace();
            logger.error("简析新安人才网简历中的求职意向信息出错！", e.getMessage()); 
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
	    try{
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"教育经历",resumeModularKeyWords);
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
                //查询专业
                index=SerachEducationUtil.serachIndexOfEduEnd(contextValue);
                String majorName="";
                if(index!=-1 && index1!=-1){
                    majorName=contextValue.substring(index1,index);   
                }
                System.out.println("专业名称："+majorName); 
                reh.setMajor(majorName);
                resumeEducationHistoryList.add(reh); 
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
	public  void analysisTrainingHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeTrainHistory> resumeTrainHistoryList=(List<ResumeTrainHistory>) list.get(11);
	    try{
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人技能",resumeModularKeyWords); 
	        //个人技能模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","个人技能");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"培训经历",keyWords);
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
                    //查找培训描述
                    index1=contextValue.indexOf("培训描述");
                    String trainingName="";//培训名称    
                    String trainingDescribes="";//培训描述  
                    if(index1!= -1){
                        trainingName=contextValue.substring(index,index1);
                        trainingDescribes=contextValue.substring(index1+4);
                    }else{
                        trainingName=contextValue.substring(index);
                    }
                    System.out.println("培训名称："+trainingName+"\n"+"培训描述："+trainingDescribes+"\n");                
                    resumeTrainHistory.setTrainName(trainingName);
                    resumeTrainHistory.setDescribes(trainingDescribes);                
                    resumeTrainHistoryList.add(resumeTrainHistory);
                 } 
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
	public  void analysisCertificate(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumeCertificate>resumeCertificateList=(List<ResumeCertificate>) list.get(13);
	    try {
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人技能",resumeModularKeyWords); 
            //个人技能模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","个人技能");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"证书",keyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}年[0-9]{2}月";
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
                    String certificateTime=contextValue.substring(0, 8);
                    String patten="yyyy年MM月";
                    resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
                    String certificateName=contextValue.substring(8);
                    resumeCertificate.setCertificateName(certificateName);
                    resumeCertificateList.add(resumeCertificate);
                    System.out.println("证书时间："+certificateTime+"\n"+"证书名称："+certificateName+"\n");    
                }  
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
	public  void analysisLanguageAbility(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{		
		@SuppressWarnings("unchecked")
        List<ResumeLanguage>resumeLanguageList=(List<ResumeLanguage>) list.get(5);
		try{
		    contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人技能",resumeModularKeyWords); 
            //个人技能模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","个人技能");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"语言能力",keyWords);
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
    public  void analysisMajorSkill(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeMajorSkill> resumeMajorSkillList=(List<ResumeMajorSkill>) list.get(6);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人技能",resumeModularKeyWords); 
            //个人技能模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","个人技能");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"IT技能",keyWords);
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
                    if(StringUtils.isNotEmpty(skillName)){
                        regEx_style="[0-9]{1,2}个月";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(skillName);
                        while(m.find()){
                            String useTime=skillName.substring(m.start(),m.end());
                            resumeMajorSkill.setUseTime(useTime);
                            System.out.println("专业技能时间："+useTime+"\n"); 
                            skillName=skillName.substring(0,m.start());                            
                        }                        
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
            logger.error("简析新安人才网简历中的专业技能信息出错！", e.getMessage());  
            throw  new Exception(e);
        }
    }
    
	/**
	 * 简析简历中的荣誉奖励模块
	 * @throws Exception 
	 */
	public  void analysisHonour(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
	    List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
	    try {
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"在校情况",resumeModularKeyWords); 
            //在校情况模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","在校情况");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"荣誉奖励",keyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}年[0-9]{2}月";
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
                    ResumePrize resumePrize=new ResumePrize();
                    start=map.get("start"+i);//某一段荣誉奖励的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段荣誉奖励的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段荣誉奖励的文本
                    //奖励时间
                    String honourTime=contextValue.substring(0, 8);
                    String patten="yyyy年MM月";   
                    resumePrize.setGainTime(TimeUtil.getDateByTime(honourTime,patten));
                    String honourName=contextValue.substring(8);
                    resumePrize.setPrizeName(honourName);
                    resumePrizeList.add(resumePrize);
                    System.out.println("奖励时间："+honourTime+"\n"+"奖励名称："+honourName+"\n"); 
                } 
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
    public  void analysisSchoolPost(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeSchoolPost> resumeSchoolPostList=(List<ResumeSchoolPost>) list.get(10);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"在校情况",resumeModularKeyWords); 
            //在校情况模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","在校情况");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"校内职务",keyWords);
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
                    ResumeSchoolPost resumeSchoolPost=new ResumeSchoolPost();
                    start=map.get("start"+i);//某一段校内职务的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段校内职务的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段校内职务的文本
                    //截取开始时间和结束时间
                    int index1=contextValue.indexOf("-");
                    String start_time=contextValue.substring(0,index1);
                    System.out.println("开始时间："+start_time);
                    String patten="yyyy/MM";
                    resumeSchoolPost.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                    String end_time="";
                    int index=0;
                    if(contextValue.length()>=(index1+8)){
                        end_time=contextValue.substring(index1+1,index1+8).trim();
                        regEx_style="[0-9]{4}/[0-9]{2}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            resumeSchoolPost.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+8;
                        }else{
                            end_time=contextValue.substring(index1+1,index1+7).trim();
                            regEx_style="[0-9]{4}/[0-9]{1}";
                            r = Pattern.compile(regEx_style);
                            m = r.matcher(end_time);
                            if(m.find()){
                                resumeSchoolPost.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                                index=index1+7;
                            }else if(end_time.indexOf("至今")!=-1){
                                index=index1+3;
                            }
                        }
                    }else if(contextValue.length()>=(index1+7)){
                        end_time=contextValue.substring(index1+1,index1+7).trim();
                        regEx_style="[0-9]{4}/[0-9]{1}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            resumeSchoolPost.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                    String schoolaJobName="";//校内职务名称   
                    String schoolaJobDescribe="";//职务描述
                    index1=contextValue.indexOf("职务描述");
                    if(index1!=-1){
                        schoolaJobName=contextValue.substring(index, index1);
                        schoolaJobDescribe=contextValue.substring(index1+4);
                    }else{
                        schoolaJobName=contextValue.substring(index);
                    }
                    resumeSchoolPost.setPostName(schoolaJobName);
                    resumeSchoolPost.setDescribes(schoolaJobDescribe);
                    System.out.println("职务名称："+schoolaJobName+"\n"+"职务描述："+schoolaJobDescribe+"\n"); 
                    resumeSchoolPostList.add(resumeSchoolPost);                     
                } 
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
	public  void analysisSchoolPractice(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
	    @SuppressWarnings("unchecked")
        List<ResumePracticeExperience> resumePracticeExperienceList=(List<ResumePracticeExperience>) list.get(7);
	    try {
	        contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"在校情况",resumeModularKeyWords); 
            //在校情况模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=super.getResumeKeyWords("1","在校情况");
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"社会实践",keyWords);
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
                    ResumePracticeExperience resumePracticeExperience=new ResumePracticeExperience();
                    start=map.get("start"+i);//某一段校内职务的开始位置
                    if((i+1)==j){
                        end=contentText.length();
                    }else{
                        end=map.get("start"+(i+1));//某一段校内职务的结束位置 
                    }                    
                    String contextValue=contentText.substring(start, end);//某段校内职务的文本
                    //截取开始时间和结束时间
                    int index1=contextValue.indexOf("-");
                    String start_time=contextValue.substring(0,index1);
                    System.out.println("开始时间："+start_time);
                    String patten="yyyy/MM";
                    resumePracticeExperience.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                    String end_time=contextValue.substring(8,15).trim();
                    System.out.println("结束时间："+end_time); 
                    int index=15;
                    if(end_time.indexOf("至今")==-1){
                        resumePracticeExperience.setEndTime(TimeUtil.getDateByTime(end_time,patten));                       
                    }else{
                        index=10;
                    }
                    String practiceName="";//实践名称
                    String practiceDescribe="";//实践简述 
                    index1=contextValue.indexOf("实践描述");
                    if(index1!=-1){
                        practiceName=contextValue.substring(index, index1);
                        practiceDescribe=contextValue.substring(index1+4);
                    }else{
                        practiceName=contextValue.substring(index);
                    }
                    resumePracticeExperience.setPracticeName(practiceName);
                    resumePracticeExperience.setPracticeDescribe(practiceDescribe);  
                    resumePracticeExperienceList.add(resumePracticeExperience);
                    System.out.println("实践名称："+practiceName+"\n"+"实践简述："+practiceDescribe+"\n");                     
                } 
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
	public  void analysisEvaluation(String contentText,List<Object>list,List<String>resumeModularKeyWords){
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"自我评价",resumeModularKeyWords);
            String evaluation=contentText.trim();
            resumeBase.setEvaluation(evaluation);
            System.out.println("自我评价："+evaluation+"\n");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析新安人才网简历中的自我评价信息出错！", e.getMessage()); 
        }				
	}
	

}
