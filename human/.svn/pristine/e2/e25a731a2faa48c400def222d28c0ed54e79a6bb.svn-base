package com.human.analysisResumeTest;

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
import com.human.utils.ReduceHtmlTextUtil;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.EncapsulationResumeUtil;
import com.human.utils.mailUtils.SearchFiledByKeyWordsUtil;
import com.human.utils.mailUtils.SerachEducationUtil;

public class NewRead51JobResumeUtilTest {
    
    private  static Logger logger = LogManager.getLogger(NewRead51JobResumeUtilTest.class);

    /**
     * 获取简历模块关键词组
     * @return
     */
    public static List<String> getResumeModularKeyWords(){
        List<String> list = new ArrayList<String>(100);
        list.add("基本信息");
        list.add("最近工作");
        list.add("最高学历/学位");
        list.add("个人信息");
        list.add("目前年收入");
        list.add("求职意向");
        list.add("工作经验");
        list.add("项目经验");
        list.add("教育经历");
        list.add("在校情况");
        list.add("技能特长");
        list.add("附加信息");
        return list;
    }
    
    /**
     * 解析前程无忧招聘网的简历
     * @param docPath  html格式的简历文件
     */
    public  static List<Object> dealHtmlResumeByResource(File input){
        List<Object>list=EncapsulationResumeUtil.getResumeObject();
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
            String contentText1 = ReduceHtmlTextUtil.removeHtmlTag(doc.toString()).replaceAll("-->", "");
            System.out.println(contentText1);
            String contentText=contentText1.replace("*", "");
            System.out.println(contentText);
            List<String>resumeModularKeyWords=getResumeModularKeyWords();
            /*<!-- 基本信息 -->*/
            analysisBaseInformation(contentText,list,resumeModularKeyWords);
            /*<!-- 求职意向 -->*/
            analysisExpectWork(contentText,list,resumeModularKeyWords);
            /*<!-- 工作经历 -->*/
            analysisWorkHistory(contentText1,list,resumeModularKeyWords);
            /*<!-- 项目经验 -->*/
            analysisProjectExperience(contentText,list,resumeModularKeyWords);
            /*<!-- 教育经历 -->*/
            analysisEducationHistory(contentText,list,resumeModularKeyWords);
            /*<!-- 在校情况 -->*/
            analysisSchoolSituation(contentText,list,resumeModularKeyWords);
            /*<!--技能特长  -->*/
            analysisSkillAndSpecialty(contentText,list,resumeModularKeyWords);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历出错！", e.getMessage());
            flag=false;
        }
        list.add(14,flag);
        return list;
    }
        
    
    /**
     * 简析简历中的基本信息模块
     * @throws Exception 
     */
    public  static void analysisBaseInformation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeSeeker resumeSeeker=(ResumeSeeker) list.get(0);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        ResumeIntention resumeIntention=(ResumeIntention) list.get(4);
        try {
            String contentTextsub=contentText;
            int position=contentTextsub.indexOf("年工作经验");
            if(position!=-1){
                contentTextsub=contentTextsub.replaceAll("工作经验","工作经历");
            }
            contentTextsub=SearchFiledByKeyWordsUtil.searchTextByKeyWords(contentTextsub,resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentTextsub)){
                //基本信息模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("应聘职位");
                keyWords.add("投递时间");
                keyWords.add("应聘公司");
                keyWords.add("简历匹配");
                keyWords.add("手机");
                keyWords.add("邮箱");
                keyWords.add("手机");
                keyWords.add("居住地");
                keyWords.add("求职状态");
                //截取姓名及性别等基本信息
                String text=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"简历匹配",keyWords);
                if(StringUtils.isNotEmpty(text)){
                    position=text.indexOf("%");
                    if(position!=-1){
                        text=text.substring(position+1);
                        String[] sub=text.split("\\|");                        
                        if(sub.length>2){
                            String name=sub[0];
                            //判断是否包含求职信
                            String regEx_style="[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}";
                            Pattern r = Pattern.compile(regEx_style);
                            Matcher m = r.matcher(name);                           
                            while(m.find()){
                                name=name.substring(m.end());                               
                            }
                            resumeBase.setName(name);
                            resumeSeeker.setName(name);
                            String sex=sub[1];
                            if(sex.indexOf("男")!=-1){
                                resumeSeeker.setSex("M");
                                resumeBase.setSex("M");
                            }else if(sex.indexOf("女")!=-1){
                                resumeSeeker.setSex("F");
                                resumeBase.setSex("F");
                            }
                            System.out.println("姓名："+name+"\n"+"性别："+sex+"\n");
                            for(int i=2;i<sub.length;i++){
                                String textValue=sub[i];
                                if(textValue.indexOf("岁")!=-1 && textValue.indexOf("(")!=-1 && textValue.indexOf(")")!=-1){
                                    int start=textValue.indexOf("(");
                                    int end =textValue.indexOf(")");
                                    String birth=textValue.substring(start+1,end).replaceAll("/", "-");
                                    System.out.println("生日："+birth);
                                    Date birthDate = TimeUtil.getDateByTime(birth, "yyyy-MM-dd");
                                    resumeBase.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));
                                    resumeSeeker.setBirthDate(TimeUtil.date2String(birthDate, "yyyy-MM-dd"));                                    
                                    continue;
                                }
                                if(textValue.indexOf("工作经历")!=-1){
                                    int endIndex=textValue.indexOf("工作经历");
                                    String workTime=textValue.substring(0,endIndex);
                                    System.out.println("工作经验年限："+workTime);
                                    resumeSeeker.setWorkTime(workTime);
                                    resumeBase.setWorkingTime(workTime);
                                    continue;
                                }  
                            }                            
                        }  
                    }
                }
                String applyPosition=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"应聘职位",keyWords);
                resumeBase.setApplyPosition(applyPosition);
                String applyTime= SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"投递时间",keyWords);
                resumeBase.setDeliveryDate(applyTime);
                String telephone=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"手机",keyWords);
                if(Common.isMobile(telephone)){
                    resumeSeeker.setPhone(telephone);
                    resumeBase.setTelephone(telephone); 
                }
                String email=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"邮箱",keyWords);
                resumeSeeker.setEmail(email);
                resumeBase.setEmail(email);  
                String locationCity=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"居住地",keyWords);
                resumeSeeker.setLocationCity(locationCity);
                resumeBase.setLocationCity(locationCity); 
                String currentStatus=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"求职状态",keyWords);
                resumeIntention.setCurrentStatus(currentStatus);  
                System.out.println("应聘职位："+applyPosition+"\n"+"投递时间："+applyTime+"\n"+"手机："+telephone+"\n"+"邮箱："+email+"\n"+"居住地："+locationCity+"\n"+"求职状态："+currentStatus+"\n");
            }
            //查找最高学历
            contentTextsub=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"最高学历/学位",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentTextsub)){
                //最高学历模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("专业");
                keyWords.add("学校");
                keyWords.add("学历/学位");
                String majorName=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"专业",keyWords);
                String schoolName=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"学校",keyWords);
                String education=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"学历/学位",keyWords);
                resumeBase.setMajor(majorName);
                resumeBase.setHighEdu(education);
                resumeBase.setGraSchool(schoolName);
                resumeSeeker.setMajor(majorName);
                resumeSeeker.setHighEdu(education);
                resumeSeeker.setGraSchool(schoolName);   
                System.out.println("专业："+majorName+"\n"+"学校："+schoolName+"\n"+"学历/学位："+education+"\n");
            }
            //查找个人信息
            contentTextsub=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"个人信息",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentTextsub)){
                //个人信息模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("QQ号");
                keyWords.add("户口/国籍");
                keyWords.add("身高");
                keyWords.add("婚姻状况");
                keyWords.add("家庭地址");
                keyWords.add("政治面貌");
                String householdRegister=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"户口/国籍",keyWords);
                resumeBase.setHouseholdRegister(householdRegister);
                String marriage=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"婚姻状况",keyWords);
                if("未婚".equals(marriage)){
                    resumeBase.setMarriage("1");
                    resumeSeeker.setMarriage("1");
                }else if("已婚".equals(marriage)){
                    resumeBase.setMarriage("2");
                    resumeSeeker.setMarriage("2");
                }
                String politicalStatus=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentTextsub,"政治面貌",keyWords);
                resumeBase.setPoliticalStatus(politicalStatus);
                System.out.println("户口："+householdRegister+"\n"+"婚姻状况："+marriage+"\n"+"政治面貌："+politicalStatus+"\n");
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的基本信息出错！", e.getMessage());
            throw  new Exception(e);
        }       
    }

        
    /**
     * 简析简历中的求职意向模块
     * @throws Exception 
     */
    public static void analysisExpectWork(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        ResumeIntention resumeIntention = (ResumeIntention) list.get(4);
        ResumeBase resumeBase=(ResumeBase) list.get(1);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"求职意向",resumeModularKeyWords);
            //求职意向模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=new ArrayList<String>();
            keyWords.add("期望薪资");
            keyWords.add("地点");
            keyWords.add("职能");
            keyWords.add("行业");
            keyWords.add("个人标签");
            keyWords.add("自我评价");
            keyWords.add("到岗时间");
            keyWords.add("工作类型");           
            //查找求职性质
            String expectWorkProperty=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作类型",keyWords);
            resumeIntention.setExpectWorkProperty(expectWorkProperty);
            //查找月薪要求
            String expectWorkSalary=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"期望薪资",keyWords);
            resumeIntention.setExpectWorkSalary(expectWorkSalary);
            //查找行业要求
            String expectWorkIndustry=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"行业",keyWords);
            resumeIntention.setExpectWorkIndustry(expectWorkIndustry);
            //查找职能意向
            String expectWorkJob=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"职能",keyWords);
            resumeIntention.setExpectWorkJob(expectWorkJob);
            //查找工作地点
            String expectWorkPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"地点",keyWords);
            resumeIntention.setExpectWorkPlace(expectWorkPlace);
            //查找到岗时间
            String expect_arrival_time=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"到岗时间",keyWords);
            resumeIntention.setExpectArrivalTime(expect_arrival_time); 
            System.out.println("求职性质："+expectWorkProperty+"\n"+"月薪要求："+expectWorkSalary+"\n"+"行业要求："+expectWorkIndustry
                              +"\n"+"职能意向："+expectWorkJob+"\n"+"工作地点："+expectWorkPlace+"\n"+"到岗时间："+expect_arrival_time+"\n" ); 
            //查找自我评价
            String evaluation=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"自我评价",keyWords);
            System.out.println("自我评价："+evaluation+"\n");
            resumeBase.setEvaluation(evaluation);
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
    public  static void analysisWorkHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeWorkHistory> resumeWorkHistoryList=(List<ResumeWorkHistory>) list.get(12);
        try {
            int position=contentText.indexOf("年工作经验");
            if(position!=-1){
                contentText=contentText.replaceAll("年工作经验","年工作经历");
            }
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"工作经验",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}/[0-9]{1,2}-";
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
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("工作描述");
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
                    String end_time="";
                    int index=0;
                    if(contextValue.length()>=(index1+8)){
                        end_time=contextValue.substring(index1+1,index1+8).trim();
                        regEx_style="[0-9]{4}/[0-9]{2}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+8;
                        }else{
                            end_time=contextValue.substring(index1+1,index1+7).trim();
                            regEx_style="[0-9]{4}/[0-9]{1}";
                            r = Pattern.compile(regEx_style);
                            m = r.matcher(end_time);
                            if(m.find()){
                                resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                            resumeWorkHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                    //截取工作职位、部门、公司名称等信息
                    end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue, keyWords);
                    if(end!=-1){
                        String text=contextValue.substring(index, end);
                        String[]sub=text.split("\\|");
                        if(sub.length>=2){
                            String workPosition=sub[0];
                            if(workPosition.indexOf("[")!=-1 && workPosition.indexOf("]")!=-1){
                                int k=workPosition.indexOf("[");
                                int t=workPosition.indexOf("]");
                                String workCountTime=workPosition.substring(k+1,t);
                                System.out.println("工作时长："+workCountTime);
                                resumeWorkHistory.setWorkTime(workCountTime);
                                String workCompanyAndPosition= workPosition.substring(0,k);
                                workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workCompanyAndPosition);
                                String[] ttt= workCompanyAndPosition.split("\\*");
                                String positionName=ttt[0];
                                System.out.println("工作职位："+positionName);
                                resumeWorkHistory.setPosition(workPosition);
                                String workCompany=ttt[ttt.length-1];    
                                System.out.println("公司名称："+workCompany);
                                resumeWorkHistory.setCompanyName(workCompany);
                                String work_property=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workPosition.substring(t+1));
                                System.out.println("工作性质："+work_property);
                                resumeWorkHistory.setWorkProperty(work_property);     
                            }else{
                                workPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workPosition).replace("*", "");
                                System.out.println("工作职位："+workPosition);
                                resumeWorkHistory.setPosition(workPosition);
                                String manyString=sub[1];
                                int k=manyString.indexOf("[");
                                int t=manyString.indexOf("]");
                                if(k!=-1 && t!=-1){
                                    String workCountTime=manyString.substring(k+1,t);
                                    System.out.println("工作时长："+workCountTime);
                                    resumeWorkHistory.setWorkTime(workCountTime);
                                    String workCompanyAndPosition= manyString.substring(0,k);
                                    workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workCompanyAndPosition);
                                    String[] ttt= workCompanyAndPosition.split("\\*");
                                    String department=ttt[0];
                                    System.out.println("工作部门："+department);
                                    resumeWorkHistory.setDepartment(department);
                                    String workCompany=ttt[ttt.length-1];    
                                    System.out.println("公司名称："+workCompany);
                                    resumeWorkHistory.setCompanyName(workCompany);
                                    String work_property=SearchFiledByKeyWordsUtil.removeSpecialSymbol(manyString.substring(t+1));
                                    System.out.println("工作性质："+work_property);
                                    resumeWorkHistory.setWorkProperty(work_property);
                                }
                            }
                            if(sub.length>2){
                               String company_scale =SearchFiledByKeyWordsUtil.removeSpecialSymbol(sub[sub.length-1]);
                               System.out.println("公司规模："+company_scale);
                               resumeWorkHistory.setCompanyScale(company_scale);
                            }
                        }else{
                            int k=text.indexOf("[");
                            int t=text.indexOf("]");
                            if(k!=-1 && t!=-1){
                                String workCountTime=text.substring(k+1,t);
                                System.out.println("工作时长："+workCountTime);
                                resumeWorkHistory.setWorkTime(workCountTime);
                                String workCompanyAndPosition=text.substring(0,k);
                                workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workCompanyAndPosition);
                                String[] ttt= workCompanyAndPosition.split("\\*");
                                String positionName=ttt[0];
                                System.out.println("工作职位："+positionName);
                                resumeWorkHistory.setPosition(positionName);
                                String workCompany=ttt[ttt.length-1];    
                                System.out.println("公司名称："+workCompany);
                                resumeWorkHistory.setCompanyName(workCompany);
                            }
                        }   
                    }else{
                        String text=contextValue.substring(index);
                        int k=text.indexOf("[");
                        int t=text.indexOf("]");
                        if(k!=-1 && t!=-1){
                            String workCountTime=text.substring(k+1,t);
                            System.out.println("工作时长："+workCountTime);
                            resumeWorkHistory.setWorkTime(workCountTime);
                            String workCompanyAndPosition=text.substring(0,k);
                            workCompanyAndPosition=SearchFiledByKeyWordsUtil.removeSpecialSymbol(workCompanyAndPosition);
                            String[] ttt= workCompanyAndPosition.split("\\*");
                            String positionName=ttt[0];
                            System.out.println("工作职位："+positionName);
                            resumeWorkHistory.setPosition(positionName);
                            String workCompany=ttt[ttt.length-1];    
                            System.out.println("公司名称："+workCompany);
                            resumeWorkHistory.setCompanyName(workCompany);
                        }
                    }
                    //查找工作描述
                    String describe=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"工作描述",keyWords);
                    describe=SearchFiledByKeyWordsUtil.removeSpecialSymbol(describe).replace("*", "\n");
                    System.out.println("工作描述："+describe);
                    resumeWorkHistory.setDescribes(describe);    
                    resumeWorkHistoryList.add(resumeWorkHistory);
                }
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
    public  static void analysisProjectExperience(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeProjectExperience> resumeProjectExperienceList=(List<ResumeProjectExperience>) list.get(9);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"项目经验",resumeModularKeyWords);
            if(StringUtils.isNotEmpty(contentText)){
                String regEx_style="[0-9]{4}/[0-9]{1,2}-";
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
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("所属公司");
                keyWords.add("项目描述");
                keyWords.add("责任描述");
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
                    String end_time="";
                    int index=0;
                    if(contextValue.length()>=(index1+8)){
                        end_time=contextValue.substring(index1+1,index1+8).trim();
                        regEx_style="[0-9]{4}/[0-9]{2}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                            index=index1+8;
                        }else{
                            end_time=contextValue.substring(index1+1,index1+7).trim();
                            regEx_style="[0-9]{4}/[0-9]{1}";
                            r = Pattern.compile(regEx_style);
                            m = r.matcher(end_time);
                            if(m.find()){
                                rpe.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                    //查找所属公司
                    String companyName=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"所属公司",keyWords); 
                    System.out.println("所属公司："+companyName);
                    rpe.setCompanyName(companyName);
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
    public  static void analysisEducationHistory(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeEducationHistory> resumeEducationHistoryList=(List<ResumeEducationHistory>) list.get(3);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"教育经历",resumeModularKeyWords);
            String regEx_style="[0-9]{4}/[0-9]{1,2}-";
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
                String end_time="";
                int index=0;
                end_time=contextValue.substring(index1+1,index1+8).trim();
                regEx_style="[0-9]{4}/[0-9]{2}";
                r = Pattern.compile(regEx_style);
                m = r.matcher(end_time);
                if(m.find()){
                    reh.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                    index=index1+8;
                }else{
                    end_time=contextValue.substring(index1+1,index1+7).trim();
                    regEx_style="[0-9]{4}/[0-9]{1}";
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
                //查询学历
                String education=SerachEducationUtil.serachEdu(contextValue);
                System.out.println("学历："+education); 
                reh.setEducation(education);
                //查询大学名称
                index1=SerachEducationUtil.serachIndexOfSchoolEnd(contextValue);
                String schoolName="";
                if(index1!=-1){
                    schoolName=contextValue.substring(index,index1);    
                }else{
                    int postiton=SerachEducationUtil.serachIndexOfEduEnd(contextValue);
                    if(postiton!=-1){
                        schoolName=contextValue.substring(index,postiton).replaceAll("海外经历",""); 
                    }
                }
                System.out.println("学校名称："+schoolName); 
                reh.setSchoolName(schoolName);
                //查询专业
                index=SerachEducationUtil.serachIndexOfEduEnd(contextValue);
                String major="";
                if(index!=-1){
                    if(education.indexOf("非统招")!=-1){
                        education=education.substring(0, education.length()-5);
                    }
                    if(education.indexOf("统招")!=-1){
                        education=education.substring(0, education.length()-4);
                    }                    
                    String majorName=contextValue.substring(index+education.length());
                    if(StringUtils.isNotEmpty(majorName)){
                           index=majorName.indexOf("专业描述");                           
                           if(index!=-1){
                               major=majorName.substring(1, index);
                               String majorDescribe=majorName.substring(index+4);
                               System.out.println("专业描述："+majorDescribe); 
                               reh.setDescribes(majorDescribe);
                           }else{
                               major=majorName.substring(1);
                           }      
                    }    
                }
                System.out.println("专业名称："+major);
                reh.setMajor(major);
                resumeEducationHistoryList.add(reh); 
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
    public  static void analysisSchoolSituation(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        List<ResumePrize> resumePrizeList=(List<ResumePrize>) list.get(8);
        List<ResumeSchoolPost> resumeSchoolPostList=(List<ResumeSchoolPost>) list.get(10);
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"在校情况",resumeModularKeyWords); 
            //在校情况模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=new ArrayList<String>();
            keyWords.add("校内荣誉");
            keyWords.add("校内职务");
            if(StringUtils.isNotEmpty(contentText)){
                String contentSubText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"校内荣誉",keyWords);
                if(StringUtils.isNotEmpty(contentSubText)){
                    String regEx_style="[0-9]{4}/[0-9]{1,2}";
                    Pattern r = Pattern.compile(regEx_style);
                    Matcher m = r.matcher(contentSubText);
                    Map<String,Integer>map=new HashMap<String,Integer>();
                    int j=1;
                    while(m.find()){
                        map.put("start"+j, m.start());
                        map.put("end"+j, m.end());
                        j++;
                    }
                    int start,end=0;
                    for(int i=1;i<j;i++){
                        ResumePrize resumePrize=new ResumePrize();
                        start=map.get("start"+i);//某一段荣誉奖励的开始位置
                        if((i+1)==j){
                            end=contentSubText.length();
                        }else{
                            end=map.get("start"+(i+1));//某一段荣誉奖励的结束位置 
                        }                    
                        String contextValue=contentSubText.substring(start, end);//某段荣誉奖励的文本
                        int index=map.get("end"+i)-start;
                        //奖励时间
                        String honourTime=contextValue.substring(0,index);
                        String patten="yyyy/MM";   
                        resumePrize.setGainTime(TimeUtil.getDateByTime(honourTime,patten));
                        String honourName=contextValue.substring(index);
                        resumePrize.setPrizeName(honourName);
                        resumePrizeList.add(resumePrize);
                        System.out.println("奖励时间："+honourTime+"\n"+"奖励名称："+honourName+"\n"); 
                    }
                }
                //校内职务
                contentSubText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"校内职务",keyWords);
                if(StringUtils.isNotEmpty(contentSubText)){
                    String regEx_style="[0-9]{4}/[0-9]{1,2}-";
                    Pattern r = Pattern.compile(regEx_style);
                    Matcher m = r.matcher(contentSubText);
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
                            end=contentSubText.length();
                        }else{
                            end=map.get("start"+(i+1));//某一段校内职务的结束位置 
                        }                    
                        String contextValue=contentSubText.substring(start, end);//某段校内职务的文本
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
    public  static void analysisSkillAndSpecialty(String contentText,List<Object>list,List<String>resumeModularKeyWords) throws Exception{
        try {
            contentText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"技能特长",resumeModularKeyWords); 
            //技能特长模块的所有关键词(后期通过数据库查询)
            List<String>keyWords=new ArrayList<String>();
            keyWords.add("技能/语言");
            keyWords.add("证书");
            keyWords.add("成绩");
            keyWords.add("培训经历");
            if(StringUtils.isNotEmpty(contentText)){
                int start=contentText.indexOf("（");
                int end=contentText.indexOf("）");
                if(start!=-1 && end!=-1){
                    contentText=contentText.substring(end+1);
                }
                String contentSubText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"技能/语言",keyWords);
                if(StringUtils.isNotEmpty(contentSubText)){
                    analysisLanguageAbility(contentSubText,list);
                }
                contentSubText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"证书",keyWords);
                if(StringUtils.isNotEmpty(contentSubText)){
                    analysisCertificate(contentSubText,list);
                }
                contentSubText=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contentText,"培训经历",keyWords);
                if(StringUtils.isNotEmpty(contentSubText)){
                    analysisTrainingHistory(contentSubText,list);
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
    public  static void analysisCertificate(String contentText,List<Object>list) throws Exception{  
        @SuppressWarnings("unchecked")
        List<ResumeCertificate>resumeCertificateList=(List<ResumeCertificate>) list.get(13);
        try {
            String regEx_style="[0-9]{4}/[0-9]{1,2}";
            Pattern r = Pattern.compile(regEx_style);
            Matcher m = r.matcher(contentText);
            Map<String,Integer>map=new HashMap<String,Integer>();
            int j=1;
            while(m.find()){
                map.put("start"+j, m.start());
                map.put("end"+j, m.end());
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
                int index=map.get("end"+i)-start;
                //证书时间
                String certificateTime=contextValue.substring(0,index);
                String patten="yyyy/MM";   
                resumeCertificate.setGainTime(TimeUtil.getDateByTime(certificateTime,patten));
                String certificateName=contextValue.substring(index);
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
    public  static void analysisLanguageAbility(String contentText,List<Object>list) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeLanguage>resumeLanguageList=(List<ResumeLanguage>) list.get(5);
        try {
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
                ResumeLanguage resumeLanguage=new ResumeLanguage();
                if(i!=1){
                   start=map.get("end"+(i-1));//某一段技能语言的开始位置 
                }
                end=map.get("end"+i);//某一段技能语言的结束位置 
                String contextValue=contentText.substring(start, end);//某段技能语言文本                
                String LanguageAbilityName=contextValue.substring(0, contextValue.length()-2);
                String LanguageAbilityDescribe=contextValue.substring(contextValue.length()-2);
                resumeLanguage.setName(LanguageAbilityName);
                resumeLanguage.setDescribes(LanguageAbilityDescribe);
                resumeLanguageList.add(resumeLanguage);
                System.out.println("技能语言名称："+LanguageAbilityName+"\n"+"技能语言描述："+LanguageAbilityDescribe+"\n");   
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
    public  static void analysisTrainingHistory(String contentText,List<Object>list) throws Exception{
        @SuppressWarnings("unchecked")
        List<ResumeTrainHistory> resumeTrainHistoryList=(List<ResumeTrainHistory>) list.get(11);
        try {
            String regEx_style="[0-9]{4}/[0-9]{1,2}-";
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
                String patten="yyyy/MM";
                resumeTrainHistory.setStartTime(TimeUtil.getDateByTime(start_time,patten));
                String end_time="";
                index1=contextValue.lastIndexOf("-");
                int index=0;
                if(contextValue.length()>=(index1+8)){
                    end_time=contextValue.substring(index1+1,index1+8).trim();
                    regEx_style="[0-9]{4}/[0-9]{2}";
                    r = Pattern.compile(regEx_style);
                    m = r.matcher(end_time);
                    if(m.find()){
                        resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
                        index=index1+8;
                    }else{
                        end_time=contextValue.substring(index1+1,index1+7).trim();
                        regEx_style="[0-9]{4}/[0-9]{1}";
                        r = Pattern.compile(regEx_style);
                        m = r.matcher(end_time);
                        if(m.find()){
                            resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                            resumeTrainHistory.setEndTime(TimeUtil.getDateByTime(end_time,patten));
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
                //培训经历模块的所有关键词(后期通过数据库查询)
                List<String>keyWords=new ArrayList<String>();
                keyWords.add("培训机构");
                keyWords.add("培训地点");
                keyWords.add("培训描述");
                end=SearchFiledByKeyWordsUtil.getIndexOfFirstKey(contextValue,keyWords);
                String trainingName="";
                if(end!=-1){
                    trainingName=contextValue.substring(index, end);  
                    resumeTrainHistory.setTrainName(trainingName);
                }
                String trainingCompany=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训机构",keyWords); 
                resumeTrainHistory.setTrainCompany(trainingCompany);
                String trainingPlace=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训地点",keyWords); 
                resumeTrainHistory.setPlace(trainingPlace);
                String describes=SearchFiledByKeyWordsUtil.searchFiledByKeyWords(contextValue,"培训描述",keyWords); 
                resumeTrainHistory.setDescribes(describes);
                resumeTrainHistoryList.add(resumeTrainHistory);
                System.out.println("培训开始时间："+start_time+"\n"+"培训结束时间："+end_time+"\n"+"培训名称："+trainingName+"\n"+"培训公司："+trainingCompany+"\n"+"培训地点："+trainingPlace+"\n"+"培训描述："+describes+"\n");                 
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("简析前程无忧简历中的培训经历信息出错！", e.getMessage());
            throw  new Exception(e);
        }
    }
    
        
    
    public static void main(String[] args) {
        String docPath = "E:/temp/attachment/123.html";//
        File input = new File(docPath);    
        long start=System.currentTimeMillis();
        dealHtmlResumeByResource(input);
        long end=System.currentTimeMillis();
        System.out.println("解析简历耗时:"+(end-start)/1000+"秒");
    }
}
