package com.human.jzbTest.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.DicData;
import com.human.basic.entity.XdfClassInfo;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.XdfClassInfoService;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.entity.JzbQuestionPaper;
import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.JzbUpClass;
import com.human.jzbTest.entity.SchoolXbgx;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbGradeSubjectClassService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.jzbTest.service.JzbSchoolService;
import com.human.jzbTest.service.JzbUpClassService;
import com.human.jzbTest.service.XbChkqService;

@Controller
@RequestMapping("/jzbTest/weixin/")
public class JzbWxBmController {
	
	private final  Logger logger = LogManager.getLogger(JzbWxBmController.class);
	
	@Value("${humanServer}")
	private  String humanServer;
	
	@Value("${oss.fileurl}")
    private  String fileurl;
		
	@Resource
	private JzbGradeService gradeService;
	
	@Resource
    private DictionaryService dictionaryService;
		
	@Resource
	private JzbSchoolService schoolService;
	
	@Resource
	private JzbGradeSubjectClassService classService;
		   
    @Resource
    private XdfClassInfoService classInfoService;
    
    @Resource
    private JzbUpClassService  upClassService;
    
    @Resource
    private  XbChkqService	xbChkqService;
    
    @Resource
    private JzbQuestionService questionService;
    
    
    @RequestMapping(value="toSelectClass")
    public ModelAndView toSelectClass(HttpServletRequest request,HttpServletResponse response,Integer paperId){
        ModelAndView mav = new ModelAndView("/jzbTest/front_test/class_list");
        List<XdfClassInfo> classList = getClass(paperId);
        List<DicData> areas = dictionaryService.getDataByKey("CLASS_AREA");
        List<DicData> result = new ArrayList<DicData>();
        for(DicData d:areas){
            for(XdfClassInfo xci:classList){
                if(StringUtils.isNotEmpty(xci.getsAreaCode())){
                    if(xci.getsAreaCode().equals(d.getDataValue())){
                        result.add(d);
                        break;
                    }
                }
            }
        }
        mav.addObject("classList", classList);
        mav.addObject("areas", result);
        return mav;
    }
  
    public List<XdfClassInfo> getClass(Integer paperId){
        JzbGradeSubjectDto Jgs = new JzbGradeSubjectDto();
        JzbQuestionPaper paper = questionService.selectPaperByPaperId(paperId);
        Jgs.setClassType(Integer.valueOf(paper.getClasstype()));
        Jgs.setGradeId(Long.valueOf(paper.getGrade()));
        Integer isPass = paper.getIsPass();
        Jgs.setIsPass(isPass);
        Jgs.setsMobile(paper.getPhone());
        Jgs.setsStudentName(paper.getName());
        Jgs.setSubjectCode(paper.getSubject());
        
        List<XdfClassInfo> classInfoList=new ArrayList<XdfClassInfo>(100);
        //第一步：查询该年级科目班型下的推荐课程规则
        List<JzbGradeSubjectClass> jgscList=classService.selectClassRuleByParams(Jgs);
        if(CollectionUtils.isNotEmpty(jgscList)){
            //第二步：通过推荐课程规则查询班级
            for(JzbGradeSubjectClass jgsc:jgscList){
                String containClassName = jgsc.getContainClassName();
                jgsc.setContainClassNameList(getListFromString(containClassName));
                String noContainClassName = jgsc.getNoContainClassName();
                jgsc.setNoContainClassNameList(getListFromString(noContainClassName));
                String containClassNumber = jgsc.getContainClassNumber();
                jgsc.setContainClassNumberList(getListFromString(containClassNumber));
                String noContainClassNumber = jgsc.getNoContainClassNumber();
                jgsc.setNoContainClassNumberList(getListFromString(noContainClassNumber));
                List<XdfClassInfo> classList=classInfoService.queryClassByRule(jgsc);
                classInfoList.addAll(classList);               
            }
            //第三步：对班级去重
            if(CollectionUtils.isNotEmpty(classInfoList)){
                HashSet<XdfClassInfo> ts = new HashSet<XdfClassInfo>(classInfoList);
                classInfoList= new ArrayList<XdfClassInfo>(ts);    
            }
            
            List<String> areaCodes = new ArrayList<String>();
            if(classInfoList.size()>0){
                for(XdfClassInfo xci:classInfoList){
                    if(!areaCodes.contains(xci.getsAreaCode())){
                        areaCodes.add(xci.getsAreaCode());
                    }
                }
            }
        }
        return classInfoList;
    }  
	/**
	 * 考试报名统一入口
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="toPzSbq")
	public ModelAndView toPzSbq(HttpServletRequest request,HttpServletResponse response,String classCodes,Integer paperId){  
        JzbGradeSubjectDto Jgs = new JzbGradeSubjectDto();
        JzbQuestionPaper paper = questionService.selectPaperByPaperId(paperId);
        Jgs.setClassType(Integer.valueOf(paper.getClasstype()));
        Jgs.setGradeId(Long.valueOf(paper.getGrade()));
        Integer isPass = paper.getIsPass();
        Jgs.setIsPass(isPass);
        Jgs.setsMobile(paper.getPhone());
        Jgs.setsStudentName(paper.getName());
        Jgs.setSubjectCode(paper.getSubject());
        
        ModelAndView mav = null;      
        List<XdfClassInfo> classInfoList= JSON.parseArray(classCodes, XdfClassInfo.class);
        List<SchoolXbgx> xbgxsList=new ArrayList<SchoolXbgx>(100);
        Map<String,Object> result = new HashMap<String,Object>();
        boolean flag=true;
        String studentName = "";
        try{
            studentName = URLEncoder.encode(Jgs.getsStudentName(),"utf-8");
            if(!"0".equals(Jgs.getIsPass())){
                List<JzbUpClass> upClassList=upClassService.selectUpClassRuleByParams(Jgs);
                if(CollectionUtils.isNotEmpty(classInfoList) && CollectionUtils.isNotEmpty(upClassList)){
                    for(XdfClassInfo xdfClassInfo:classInfoList){
                        for(JzbUpClass jzbUpClass:upClassList){
                            SchoolXbgx xbgxs = new SchoolXbgx();
                            xbgxs.setnSchoolId(String.valueOf(jzbUpClass.getNschoolid()));
                            xbgxs.setsWindowPeriodId(jzbUpClass.getSwindowperiodid());
                            xbgxs.setsStageId(jzbUpClass.getSstageid());
                            xbgxs.setsContinuedClassCode(xdfClassInfo.getsClassCode());
                            xbgxs.setsContinuedClassName(xdfClassInfo.getsClassName());
                            xbgxs.setsStudentName(Jgs.getsStudentName());
                            xbgxs.setsMobile(Jgs.getsMobile());
                            xbgxs.setsStageName(jzbUpClass.getSstagename());
                            xbgxsList.add(xbgxs);
                        }
                    }
                }else{
                    flag=false;
                    result.put("message", "该年级科目班型下没有配置升班级课程规则!");
                }
                //第五步：调用接口插入升班期班级
                if(CollectionUtils.isNotEmpty(xbgxsList)){
                    result =xbChkqService.addXbgx(xbgxsList);
                    if(!(boolean) result.get("flag")){
                        flag=false;
                        result.put("message", "插入升班级失败!"); 
                    }
                }
            }                
        }catch(Exception e){
            flag=false;
            result.put("message", "进入报名页面出错!出错原因:"+e.getMessage());
            e.printStackTrace();
            logger.error("进入报名页面出错!", e.getMessage());             
        }
        if(flag){
            String classcodes="";
            for(XdfClassInfo xdfClassInfo:classInfoList){
                JzbStudentBmRecord bmRecord = new JzbStudentBmRecord();
                bmRecord.setClassCode(xdfClassInfo.getsClassCode());
                bmRecord.setPaperId(paperId);
                bmRecord.setClassName(xdfClassInfo.getsClassName());
                bmRecord.setCreateTime(new Date());
                questionService.insertBmRecord(bmRecord);
                classcodes+=xdfClassInfo.getsClassCode()+",";
            }            
            
            classcodes=classcodes.substring(0,classcodes.length()-1); 
            String resultUrl="redirect:http://wxpay.xdf.cn/silenceauthorize/view.do?schoolid=25&callid=2&urlname="+studentName
                    +"&urlmobile="+Jgs.getsMobile()+"&classcodes="
                    +classcodes+"&qrcode_id=2BEFFFF6-A712-41AA-831B-2508ED28423C&marketingSources=wechat_tjbj&marketingSourcesExt=jzbtest";
            mav =new ModelAndView(resultUrl);
        }else{
            mav =new ModelAndView("/jzbTest/front_test/bmError");
            mav.addObject("message", result.get("message"));
        }
        return mav;            
     }
    
    private List<String> getListFromString(String s){
        if(StringUtils.isEmpty(s)){
            return null;
        }
        List<String> result = new ArrayList<String>();
        if(s.indexOf(",")>0||s.indexOf("，")>0){
            String tmp = s.replaceAll("，", ",");
            String[] tmpArr = tmp.split(",");
            for(String tmpStr:tmpArr){
                if(StringUtils.isNotEmpty(tmpStr)){
                    result.add(tmpStr);
                }
            }
        }else{
            result.add(s);
        }
        return result;
    }

}
