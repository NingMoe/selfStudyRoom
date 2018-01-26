package com.human.nologin.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.jzbTest.entity.CacheQuestion;
import com.human.jzbTest.entity.JzbCacheAnswer;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.entity.JzbMainConfig;
import com.human.jzbTest.entity.JzbPaperConfigDetail;
import com.human.jzbTest.entity.JzbPaperErrorDto;
import com.human.jzbTest.entity.JzbPaperMainMessage;
import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbPaperMonthLevel;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionPaper;
import com.human.jzbTest.entity.JzbSchool;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.jzbTest.entity.jzbPaperMainConfig;
import com.human.jzbTest.service.JzbGradeService;
import com.human.jzbTest.service.JzbGradeSubjectClassService;
import com.human.jzbTest.service.JzbMainConfigService;
import com.human.jzbTest.service.JzbPaperMonthConfigService;
import com.human.jzbTest.service.JzbPaperQuestionService;
import com.human.jzbTest.service.JzbQuestionService;
import com.human.jzbTest.service.JzbSchoolService;
import com.human.jzbTest.service.JzbStudentService;
import com.human.jzbTest.service.jzbKnowledgePointService;
import com.human.jzbTest.service.jzbPaperConfigDetailService;
import com.human.jzbTest.service.jzbPaperMainConfigService;
import com.human.manager.service.MailSendRecordService;
import com.human.utils.BindingConstants;
import com.human.utils.JsonUtil;
import com.human.utils.TimeUtil;

@Controller
@RequestMapping("/jzbTest/weixin/")
public class WxTestController {
    
    private final  Logger logger = LogManager.getLogger(WxTestController.class);
    
    @Value("${humanServer}")
    private  String humanServer;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Autowired
    private JzbMainConfigService indexConfigService;
    
    @Autowired
    private jzbPaperMainConfigService paperMainConfigService;
    
    @Autowired
    private JzbPaperMonthConfigService paperMonthConfigService;
    
    @Autowired
    private jzbPaperConfigDetailService detailConfigService;
    
    @Autowired
    private JzbGradeService gradeService;
    
    @Autowired
    private JzbQuestionService questionService;
    
    @Autowired 
    jzbKnowledgePointService pointService;
    
    @Autowired 
    JzbSchoolService schoolService;
    
    @Autowired 
    JzbPaperQuestionService jzbPaperQuestionService;
    
    @Autowired
    private SmsTempService smsTempService;
    
    @Autowired
    private JzbGradeSubjectClassService classService;
    
    @Autowired
    private MailSendRecordService mailSendRecordService;
    
    @Autowired
    private JzbStudentService jzbStudentService;
    
    
    
    
    
    /**
     * 考试前台统一入口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
        /**
         * 进入首页
         */
        String companyId = request.getParameter("companyId");
        String error500_uri = "/binding/500";
        HttpSession session = request.getSession();
        session.setAttribute(BindingConstants.TEST_COMPANY, companyId);
        String openId = (String)session.getAttribute(BindingConstants.TEST_OPENID);
        if(StringUtils.isEmpty(openId)){
            logger.info("微信跳转");
            String redirect_uri = "";
            redirect_uri = humanServer + "/jzbTest/weixin/callback.html";
            try {
                redirect_uri = java.net.URLEncoder.encode(redirect_uri,"UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return new ModelAndView(error500_uri);
            }
            String result="redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx762fd407faa3be61" + 
                    "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=1";
            return new ModelAndView(result);
        }else{
            JzbMainConfig indexConfig = indexConfigService.selectByCompanyId(companyId);
            List<JzbGrade> grades = gradeService.selectKyByCompanyId(companyId);
            ModelAndView mav = new ModelAndView("/jzbTest/front_test/index");
            mav.addObject("grades", grades);
            mav.addObject("fileurl", fileurl);
            mav.addObject("indexConfig", indexConfig);
            return mav;
        }
    }
    
    /**
     * 微信回调页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="callback")
    public ModelAndView callback(HttpServletRequest request,HttpServletResponse response){
        String error404_uri = "/binding/404";
        String code =  request.getParameter("code");
        logger.info("code:"+code);
        if(StringUtils.isEmpty(code)){
            logger.error("获取code失败。");
            return new ModelAndView(error404_uri);
        }
        String getTeacherUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";
        JsonUtil jsonUtil = new JsonUtil();
        List<NameValuePair> namelist = new ArrayList<NameValuePair>();
        namelist.add(new BasicNameValuePair("appid", "wx762fd407faa3be61"));
        namelist.add(new BasicNameValuePair("secret", "34ea4a0fa63044e49d973058c9a5e694"));
        namelist.add(new BasicNameValuePair("code", code));
        namelist.add(new BasicNameValuePair("grant_type", "authorization_code"));
        JSONObject jsonObject = jsonUtil.getJson(getTeacherUrl, namelist);
        String openid = getJSONObjectString(jsonObject, "openid");
        HttpSession session = request.getSession();
        session.setAttribute(BindingConstants.TEST_OPENID, openid);
        String companyId = (String)session.getAttribute(BindingConstants.TEST_COMPANY); 
        return new ModelAndView("forward:/jzbTest/weixin/index.html?companyId="+companyId);
    }
    
    
    /**
     * 历史考试页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="toPapers")
    public ModelAndView toPapers(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        String openId = (String)session.getAttribute(BindingConstants.TEST_OPENID);
        List<JzbQuestionPaper> papers = questionService.selectByOpenId(openId);
        for(JzbQuestionPaper jqp :papers){
            JzbGradeSubjectDto Jgs = new JzbGradeSubjectDto();
            Jgs.setClassType(Integer.valueOf(jqp.getClasstype()));
            Jgs.setGradeId(Long.valueOf(jqp.getGrade()));
            Jgs.setIsPass(jqp.getIsPass());
            Jgs.setsMobile(jqp.getPhone());
            Jgs.setsStudentName(jqp.getName());
            Jgs.setSubjectCode(jqp.getSubject());
            List<JzbGradeSubjectClass> jgscList=classService.selectClassRuleByParams(Jgs);
            if(jgscList==null||jgscList.size()==0){
                jqp.setIsExistClass("1");
            }else{
                jqp.setIsExistClass("0");
            }
        }
        ModelAndView mav = new ModelAndView("/jzbTest/front_test/papers");
        mav.addObject("papers", papers);
        return mav;
    }

    
    /**
     *错题页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="toErrorQusstion")
    public ModelAndView toErrorQusstion(Integer paperId,HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/jzbTest/front_test/error_question");
        List<JzbPaperErrorDto> errors = jzbPaperQuestionService.selectByPaperId(paperId);
        mav.addObject("jpqList",errors);
        mav.addObject("fileurl",fileurl);
        return mav;
    }

    /**
     * 进入考试配置页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="toPaperConfig")
    public ModelAndView toPaperConfig(HttpServletRequest request,HttpServletResponse response){
        String gradeIds = request.getParameter("gradeId");
        String gradeName = request.getParameter("gradeName");
        List<DicData> classTypes = paperMainConfigService.getVaidClassTypeByGrade(gradeIds);
        ModelAndView mav = new ModelAndView("/jzbTest/front_test/paper_config");
        mav.addObject("gradeName", gradeName);
        mav.addObject("gradeId", gradeIds);
        mav.addObject("classTypes", classTypes);
        return mav;
    }
    
    @RequestMapping(value="getValidSubject")
    @ResponseBody
    public Map<String,Object> getValidSubject(String gradeIds,String classType){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<DicData> subjects = paperMainConfigService.selectValidSubject(gradeIds, classType);
            result.put("flag", true);
            result.put("subjects", subjects);
        }catch(Exception e){
            logger.error("根据年级及班型查询科目出错");
            e.printStackTrace();
            result.put("flag", false);
        }
        return result;
    }
    

    /**
     * 获取考试配置
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="getPaperConfig")
    @ResponseBody
    public Map<String,Object> getPaperConfig(jzbPaperMainConfig pmc){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            jzbPaperMainConfig paperMainConfig = paperMainConfigService.getPaperMainConfig(pmc);
            if(paperMainConfig==null){
                result.put("flag", false);
                result.put("message", "试卷配置不正确，请联系老师");
            }else{
                String month = TimeUtil.getCurrentMonth();
                if("10".equals(month)){
                    month ="A";
                }
                if("11".equals(month)){
                    month ="B";
                }
                if("12".equals(month)){
                    month ="C";
                }
                
                JzbPaperMonthConfig monthConfig = new JzbPaperMonthConfig();
                monthConfig.setMainConfigId(paperMainConfig.getId());
                monthConfig.setMonth(month);
                
                monthConfig = paperMonthConfigService.getPaperMonthConfig(monthConfig);
                if(monthConfig==null){
                    result.put("flag", false);
                    result.put("message", "试卷配置不正确，请联系老师");
                }else{
                    monthConfig.setMainConfig(paperMainConfig);
                    result.put("flag", true);
                    result.put("Data", monthConfig);
                }
            }
        }catch(Exception e){
            logger.error("获取试卷配置错误");
            result.put("flag", false);
            result.put("message", "试卷配置不正确，请联系老师");
        }
        return result;
    }
    
    
    /**
     * 根据行政鞥去获取学校 
     * @param areaId
     * @param gradeId
     * @return
     */
    @RequestMapping(value="getSchoolByArea")
    @ResponseBody
    public Map<String,Object> getSchoolByArea(Integer areaId,Integer gradeId){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<JzbSchool> schools = schoolService.selectByAreaAndGrade(areaId, gradeId);
            result.put("flag", true);
            result.put("Data",schools);
        }catch(Exception e){
            logger.error("获取学校错误");
            result.put("flag", false);
            result.put("message", "获取学校错误，请联系老师");
        }
        return result;
    }
    
    /**
     * 进入学生信息完善页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="toStudentMsg")
    public ModelAndView toStudentMsg(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Object o = session.getAttribute("existpaper");
        String monthConfigId = request.getParameter("monthConfigId");
        if(o!=null){
            JzbQuestionPaper p = (JzbQuestionPaper) o;
            if(p.getMonthConfigId().intValue()==Integer.valueOf(monthConfigId).intValue()){
                return new ModelAndView("redirect:/jzbTest/weixin/toExam.html?paperId="+p.getId()+"&sort=1&totalNum="+p.getTotalNum()+"&monthConfigId="+p.getMonthConfigId());
            }
        }
        
        String openId= (String)session.getAttribute(BindingConstants.TEST_OPENID);
        /**
         * 格局OPENID获取上一次的记录,填充表单
         */
        JzbQuestionPaper paper = questionService.selectLastPaperByOpenId(openId);
        
        Integer gradeId = Integer.valueOf(request.getParameter("gradeId"));
        ModelAndView mav = new ModelAndView("/jzbTest/front_test/student_msg");
        List<AreaInfo> areas = questionService.getAreaByGrade(gradeId);
        
        mav.addObject("areas", areas);
        mav.addObject("paper", paper);
        return mav;
    }
    
    @RequestMapping(value="checkMonthConfig")
    @ResponseBody
    public Map<String,Object> checkMonthConfig(Integer mainConfigId,Integer monthConfigId){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            return paperMonthConfigService.checkMonthConfigStatus(mainConfigId,monthConfigId);
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false); 
            result.put("message", "配置异常，请重新配置");
        }
        return result;
    }
    
    /**
     * 根据行政鞥去获取学校 
     * @param areaId
     * @param gradeId
     * @return
     */
    @RequestMapping(value="checkJymAndKsTimes")
    @ResponseBody
    public Map<String,Object> checkKsTimes(HttpServletRequest request,JzbQuestionPaper paper,String jym){
        System.out.println("教研验证码和考试次数");
        Map<String,Object> result = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        try{
            String msg = mailSendRecordService.getSendMsg(paper.getPhone());
            System.out.println("教研验证码和考试次数。。。。。1");
            if(!msg.contains(jym)){
                result.put("flag", false); 
                result.put("message", "手机验证码不正确");
                return result;
            }
            String openId= (String)session.getAttribute(BindingConstants.TEST_OPENID);
            paper.setOpenId(openId); 
            System.out.println("教研验证码和考试次数。。。。2");
            Integer existDayTimes = questionService.getExistDayTimesByOpenId(paper);
            Integer existMonthTimes = questionService.getExistMonthTotalTimesByOpenId(paper);
            Integer existTotalTimes = questionService.getExistTotalTimesByOpenId(paper);
            if(existDayTimes.intValue()>=paper.getDayTimes().intValue()
                    ||existMonthTimes.intValue()>=paper.getMonthTimes().intValue()
                    ||existTotalTimes.intValue()>=paper.getTotalTimes().intValue()){
                String companyId = (String)session.getAttribute(BindingConstants.TEST_COMPANY);
                result.put("flag", false); 
                result.put("message", "您答卷已超过限定次数，请联系考试管理员");
                result.put("companyId", companyId);
            }else{
                result.put("flag", true); 
            }
            System.out.println("教研验证码和考试次数。。。。。3");
        }catch(Exception e){
            logger.error("查询是否超过次数失败");
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "系统错误，请联系考试管理员");
        }
        return result;
    }
    
    
    /**
     * 进入考试页面*（组卷逻辑）
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="toStartKs")
    public ModelAndView toStartKs(HttpServletRequest request,JzbQuestionPaper paper){
        try{
            Integer monthConfigId = paper.getMonthConfigId();
            HttpSession session = request.getSession();
            Object o = session.getAttribute("existpaper");
            if(o!=null){
                JzbQuestionPaper p = (JzbQuestionPaper) o;
                long start = p.getStartTime().getTime();
                long end = start+p.getPaperTime()*60*60*1000;
                long now = System.currentTimeMillis();
                if(p.getMonthConfigId().intValue()==paper.getMonthConfigId().intValue() && end>now  ){
                    return new ModelAndView("redirect:/jzbTest/weixin/toExam.html?paperId="+p.getId()+"&sort=1&totalNum="+p.getTotalNum()+"&monthConfigId="+p.getMonthConfigId());
                }else{
                    session.removeAttribute("existpaper");
                    session.removeAttribute("paperFinishTime");
                }
            }
            String error500_uri = "/binding/500";
            String openId= (String)session.getAttribute(BindingConstants.TEST_OPENID);
            if(StringUtils.isEmpty(openId)){
                logger.info("微信跳转");
                String redirect_uri = "";
                redirect_uri = humanServer + "/jzbTest/weixin/callback.html";
                try {
                    redirect_uri = java.net.URLEncoder.encode(redirect_uri,"UTF-8");
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return new ModelAndView(error500_uri);
                }
                String result="redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "wx762fd407faa3be61" + 
                        "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=1";
                return new ModelAndView(result);
            }
            paper.setOpenId(openId);
            List<JzbPaperConfigDetail> detailConfigs = detailConfigService.selectByMonth(monthConfigId);
            JzbPaperMonthConfig monthConfig = paperMonthConfigService.selectByPrimaryKey(monthConfigId);
            logger.info("----7");
            paper.setCreateTime(new Date());
            paper.setStartTime(new Date());
            paper.setPaperName(monthConfig.getExamName());
            questionService.insertPaper(paper);
            Integer paperId = paper.getId();
            String preCode = monthConfig.getPreCode();
            String tkMonth =monthConfig.getTkMonth();
            
            String month = TimeUtil.getCurrentMonth();
            if("10".equals(month)){
                month ="A";
            }
            if("11".equals(month)){
                month ="B";
            }
            if("12".equals(month)){
                month ="C";
            }
            logger.info("----8");
            List<JzbPaperQuestion> paperQuestions = new ArrayList<JzbPaperQuestion>();
            int index = 1;
            for(JzbPaperConfigDetail detail:detailConfigs){
                detail.setMonth(month);
                Integer zsdId = detail.getKnowledgeId();
                jzbKnowledgePoint point = pointService.selectByPrimaryKey(zsdId);
                String qType ="1";
                if(point.getTitleNum()>1){
                    qType ="2";
                }
                List<CacheQuestion> questions = questionService.getCacheQuestion(preCode,qType);
                int num = point.getTitleNum();
                List<JzbPaperQuestion> needQs = getQuestions(questions,detail,paperQuestions,num,tkMonth);
                if(needQs==null){
                    session.removeAttribute("existpaper");
                    session.removeAttribute("paperFinishTime");
                    return new ModelAndView("/binding/404");
                }
                for(JzbPaperQuestion q:needQs){
                    if(StringUtils.isEmpty(q.getIds())){
                        JzbPaperQuestion tmp = new JzbPaperQuestion();
                        tmp.setQuestionId(q.getQuestionId());
                        tmp.setSort(index);
                        tmp.setPaperId(paperId);
                        tmp.setqType("1");
                        tmp.setqCode(q.getqCode());
                        paperQuestions.add(tmp);
                    }else{
                        String ids = q.getIds();
                        String[] idArr = ids.split(",");
                        for(String id:idArr){
                            JzbPaperQuestion tmp = new JzbPaperQuestion();
                            tmp.setQuestionId(Integer.valueOf(id));
                            tmp.setSort(index);
                            tmp.setPaperId(paperId);
                            tmp.setqType("2");
                            tmp.setqCode(q.getqCode());
                            paperQuestions.add(tmp);
                        }
                    }
                    index++;
                }
            }
            logger.info("----9");
            if(paperQuestions==null||paperQuestions.size()==0){
                logger.info("----10");
                session.removeAttribute("existpaper");
                session.removeAttribute("paperFinishTime");
                return new ModelAndView("/binding/404");
            }
            logger.info("----10");
            int totalNum = index-1;
            paper.setTotalNum(totalNum);
            questionService.insertPaperQuestions(paperQuestions);
            questionService.setAnswerId(paperId);
            session.setAttribute("existpaper", paper);
            session.setAttribute("paperFinishTime", TimeUtil.getDateAddMinutes(paper.getPaperTime()));
            return new ModelAndView("redirect:/jzbTest/weixin/toExam.html?paperId="+paperId+"&sort=1&totalNum="+totalNum+"&monthConfigId="+paper.getMonthConfigId());
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("/binding/500");
        }
        
        
    }
    
    /**
     * 进入试卷
     * @param request
     * @param paper
     * @param answers
     * @return
     */
    @RequestMapping(value="toExam")
    public ModelAndView toExam(HttpServletRequest request,JzbPaperQuestion paper,String answers){
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        Date finishTime = (Date) session.getAttribute("paperFinishTime");
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = outFormat.format(finishTime);
        String currentTime = TimeUtil.getStandCurrTime();
        JzbQuestion q = questionService.getPageQuestion(paper);
        if("1".equals(q.getqType())){
            mav = new ModelAndView("/jzbTest/front_test/simple_ques");
        }else{
            mav = new ModelAndView("/jzbTest/front_test/multi_ques");
        }
        mav.addObject("question", q);
        mav.addObject("finishTime", time);
        mav.addObject("currentTime", currentTime);
        mav.addObject("paper", paper);
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    
    /**
     * 考试结果页
     * @param request
     * @param paperId
     * @param monthConfigId
     * @return
     */
    @RequestMapping(value="toExamResult")
    public ModelAndView toExamResult(HttpServletRequest request,HttpServletResponse response,Integer paperId,Integer monthConfigId){
        ModelAndView mav = null;
        HttpSession session = request.getSession();
        String openId = (String)session.getAttribute(BindingConstants.TEST_OPENID);
        JzbQuestionPaper paper = questionService.selectPaperByPaperId(paperId);
        JzbPaperMonthConfig c = paperMonthConfigService.selectByPrimaryKey(monthConfigId);
        List<JzbPaperMainMessage> msgs = paperMainConfigService.getMessageUseById(c.getMainConfigId());
        Integer isPass = 0;
        String msg = "";
        session.removeAttribute("existpaper");
        session.removeAttribute("paperFinishTime");
        if(paper.getEndTime()!=null){
            isPass = paper.getIsPass();
            msg = getPipeiMsg(msgs,isPass);
            if(isPass!=0){
                mav = new ModelAndView("/jzbTest/front_test/pass");
            }else{
                mav = new ModelAndView("/jzbTest/front_test/nopass");
            }
            mav.addObject("paper", paper);
        }else{
            setAnswersFromCookie(request,response,paperId,c.getPreCode());
            long start = paper.getStartTime().getTime();//开始8.15
            long end = start+c.getPaperTime()*60*1000;//结束8.45
            long now = System.currentTimeMillis()-10000;//现在8.45
            Integer usedSj = (int) ((now - start)/1000/60);
            /**
             * 设置了最短答题时间并小于这个时间的，设置为不通过
             */
            if(c.getMinTime()!=null && usedSj<c.getMinTime()){
                JzbQuestionPaper p = new JzbQuestionPaper();
                p.setId(paperId);
                p.setEndTime(new Date());
                p.setIsPass(0);
                p.setOpenId(openId);
                questionService.updatePaper(p);
                String companyId = (String)session.getAttribute(BindingConstants.TEST_COMPANY); 
                return new ModelAndView("redirect:/jzbTest/weixin/index.html?companyId="+companyId);  
            }
            /**
             * 超时的
             */
            if(end<now){
                JzbQuestionPaper p = new JzbQuestionPaper();
                p.setId(paperId);
                p.setEndTime(new Date());
                p.setStatus("2");
                p.setOpenId(openId);
                questionService.updatePaper(p);
                String companyId = (String)session.getAttribute(BindingConstants.TEST_COMPANY); 
                return new ModelAndView("redirect:/jzbTest/weixin/index.html?companyId="+companyId);  
            }
            JzbQuestionPaper p = new JzbQuestionPaper();
            p.setId(paperId);
            p.setEndTime(new Date());
            Integer passNum = jzbPaperQuestionService.selectPassNum(paperId);
            List<JzbPaperMonthLevel> levels = paperMonthConfigService.getMonthUseLevel(monthConfigId);
            isPass = getPipeiLevel(levels,passNum);
            msg = getPipeiMsg(msgs,isPass);
            
            p.setIsPass(isPass);
            p.setOpenId(openId);
            
            if(isPass.intValue()>0){
                mav = new ModelAndView("/jzbTest/front_test/pass");
                if(paper.getEndTime()==null){
                    SmsRecord smsRecord = new SmsRecord();
                    smsRecord.setSendTel(paper.getPhone());
                    smsRecord.setCompany("25");
                    smsRecord.setSendComment(paper.getName()+"同学您好，您已通过"+paper.getPaperName()+"测试，您可以在测试系统的查看历史成绩页面内选择课程报名，也可以去前台出示短信报名。关注[新东方合肥学校微服务]微信公众号（hfxdfkf）可在线咨询与报名相关课程。");
                    smsTempService.sendMessage(smsRecord);
                }
            }else{
                mav = new ModelAndView("/jzbTest/front_test/nopass");
            }
            String student_code = jzbStudentService.getXdfStudentCode(paper.getName(), paper.getPhone(), "25");
            if(StringUtils.isNotEmpty(student_code)){
                p.setXdfNo(student_code);
                p.setXdf(2);
            }else{
                p.setXdf(3);
            }
            questionService.updatePaper(p);
            mav.addObject("paper", p);
        }
        
        JzbGradeSubjectDto Jgs = new JzbGradeSubjectDto();
        Jgs.setClassType(Integer.valueOf(paper.getClasstype()));
        Jgs.setGradeId(Long.valueOf(paper.getGrade()));
        Jgs.setIsPass(isPass);
        Jgs.setsMobile(paper.getPhone());
        Jgs.setsStudentName(paper.getName());
        Jgs.setSubjectCode(paper.getSubject());
        List<JzbGradeSubjectClass> jgscList=classService.selectClassRuleByParams(Jgs);
        if(jgscList==null||jgscList.size()==0){
            mav.addObject("isExistClass","1");
        }else{
            mav.addObject("isExistClass", "0");
        }
        mav.addObject("msg", msg);
        return mav;
    }
    
    /**
     * 发送绑定短信验证码
     * @param telNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value="sendWxBindMsg")
    public Map<String,Object> sendWxBindMsg(String telephone){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(telephone)){
            map.put("flag", false);
            map.put("message", "手机号不能为空!");
            return map;
        }
        try{
            return questionService.sendBindMsg(telephone);
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", "系统异常，请联系管理员!");
        }
        return map;
    }
    
    private String getJSONObjectString(JSONObject jo, String jsonname){
        String s = "";
        if(jo == null){
            logger.error("返回json为空");
            return s;
        }
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getString(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    private List<JzbPaperQuestion> getQuestions(List<CacheQuestion> questions,JzbPaperConfigDetail detail,
            List<JzbPaperQuestion> paperQuestions,int num,String tkMonth){
        List<JzbPaperQuestion> result = new ArrayList<JzbPaperQuestion>();
        logger.info(detail.getMonth()+"-"+detail.getDifficulty()+"-"+detail.getKnowledgeId()+"-"+detail.getNum());
        for(CacheQuestion q:questions){
            logger.info(q.getKnowledge()+"-"+q.getMonths()+"-"+q.getDifficulty()+"-"+q.getqCode());
            if(q.getDifficulty().intValue()==detail.getDifficulty().intValue()
                    && q.getKnowledge().equals(detail.getKnowledgeId()+"")
                    && isMonthPipei(q.getMonths(),tkMonth) 
                    && !isQusetionExist(paperQuestions,q.getqCode())
                    ){
                if(result.size()>0 && isQusetionExist(result,q.getqCode())){
                    continue;
                }
                JzbPaperQuestion pq = new JzbPaperQuestion();
                pq.setqCode(q.getqCode());
                if(num==1){
                    pq.setQuestionId(q.getQuestionId());
                }else{
                    pq.setIds(q.getQuestionIds());
                }
                result.add(pq);
            }
        }
        
        Integer needNum = detail.getNum()/num;
        if(result.size()<needNum){
            return null;
        }
        
        Collections.shuffle(result);
        return result.subList(0, needNum);
    }
    
    
    private boolean isQusetionExist(List<JzbPaperQuestion> paperQuestions,String qCode){
        for(JzbPaperQuestion q:paperQuestions){
            if(q.getqCode().equals(qCode)){
                return true;
            }
        }
        return false;
    }
    
    private boolean isMonthPipei(String month,String tkMonth){
        String[] monthArr = month.split(",");
        String[] tkMonthArr = tkMonth.split(",");
        for(String a:monthArr){
            for(String b:tkMonthArr){
                if(a.equals(b)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 从cookie中获取答案
     * @param request
     * @return
     */
    private void setAnswersFromCookie(HttpServletRequest request,HttpServletResponse response,Integer paperId,String preCode) {
        Cookie[] cookies = request.getCookies();
        List<JzbCacheAnswer> answers= questionService.getCacheAnswer(preCode);
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                Integer questionId = null;
                String stuAnswer = null;
                if(cookieName.contains("JZBAS")){
                    questionId = Integer.valueOf(cookieName.substring(5));
                    stuAnswer = cookie.getValue();
                    
                    Map<String,Object> result = isAnswerCorrect(answers,questionId,stuAnswer);
                    
                    JzbPaperQuestion p = new JzbPaperQuestion();
                    p.setPaperId(paperId);
                    String isTk = (String) result.get("isTk");
                    Integer isCorrect = (Integer) result.get("isCorrect");
                    if("1".equals(isTk)){
                        p.setStuAnswer(Integer.valueOf(stuAnswer));
                        p.setQuestionId(questionId);
                        p.setIsCorrect(isCorrect);
                    }else{
                        p.setTkAnswer(stuAnswer);
                        p.setQuestionId(questionId);
                        p.setIsCorrect(isCorrect);
                    }
                    Cookie killMyCookie = new Cookie(cookieName, null);
                    killMyCookie.setMaxAge(0);
                    killMyCookie.setPath("/");
                    response.addCookie(killMyCookie);
                    
                    jzbPaperQuestionService.setStuAnswer(p);
                }
            }
        }
    }
    
    private Map<String,Object> isAnswerCorrect(List<JzbCacheAnswer> answers,Integer questionId,String answer){
        Map<String,Object> result = new HashMap<String,Object>();
        for(JzbCacheAnswer a:answers){
            if(a.getQuestionId().equals(questionId+"")){
                result.put("isTk", a.getIsTk());
                if(a.getIsTk().equals("1")){
                    result.put("isCorrect", answer.equals(a.getAnswer())?"1":"2");
                }else{
                    String tkAnswer = a.getAnswer();
                    if(tkAnswer.indexOf("@@@")>-1){
                        if(answer.indexOf("@@@")==-1){
                            result.put("isCorrect","2");
                        }else{
                            String[] tmp = tkAnswer.split("@@@");
                            String[] stuTmp = answer.split("@@@");
                            if(tmp.length!=stuTmp.length){
                                result.put("isCorrect","2");
                            }else{
                                for(int i=0;i<tmp.length;i++){
                                    String[] tmpans = tmp[i].split("\n");
                                    if(!isArrayExist(tmpans,stuTmp[i])){
                                        result.put("isCorrect","2");
                                    }
                                }
                            }
                        }
                    }else{
                        String[] tmpans = a.getAnswer().split("\n");
                        if(!isArrayExist(tmpans,answer)){
                            result.put("isCorrect","2");
                        }
                    }
                }
                result.put("isCorrect","1");
            }
        }
        return result;
    }
   
    private String getPipeiMsg(List<JzbPaperMainMessage> msgs,Integer isPass){
        for(JzbPaperMainMessage msg:msgs){
            if(isPass.intValue()==msg.getDicId().intValue()){
                return msg.getMessage();
            }
        }
        return null;
    }
    
    private Integer getPipeiLevel(List<JzbPaperMonthLevel> levels,Integer num){
        Collections.sort(levels,new Comparator<JzbPaperMonthLevel>() {
            @Override
             public int compare(JzbPaperMonthLevel o1,JzbPaperMonthLevel o2) {
                return o1.getNum().compareTo(o2.getNum());
             }
         });
        for(int i=levels.size()-1;i>=0;i--){
            if(num.compareTo(levels.get(i).getNum())>=0){
                return levels.get(i).getDicId();
            }
        }
        return 0;
    }
    
    private boolean isArrayExist(String[] src,String curr){
        if(src==null||src.length==0||StringUtils.isEmpty(curr)){
            return false;
        }
        for(String tmp:src){
            if(!tmp.equals(curr)){
                return false;
            }
        }
        return true;
    }
}
