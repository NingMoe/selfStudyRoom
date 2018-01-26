package com.human.xdfStudent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.jw.entity.JwCourse;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.utils.BindingConstants;
import com.human.utils.Md5Tool;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.TimeUtil;
import com.human.xdfStudent.entity.XdfStudentInfo;
import com.human.xdfStudent.service.XdfStudentInfoService;

/**
 * 新东方学员查询微信端控制层
 * @author liuwei63
 */
@Controller
@RequestMapping(value="/wechat/binding/")
public class XdfStudentInfoWxController {
    
    private final  Logger logger = LogManager.getLogger(XdfStudentInfoWxController.class);
    
    @Resource
    private XdfStudentInfoService xstService;
    
    @Resource
    private HrCompanyService hrCompanyService;
    
    @Resource
    private RedisTemplateUtil redisTemplateUtil;
    
    @Value("${rc.appId}")
    private  String appid;
    
    @Value("${rc.appkey}")
    private  String appkey;
        
    @Value("${rc.teacherUrl}")
    private  String teacherUrl;
    
    @Value("${rc.teacherMethod}")
    private  String teacherMethod;
            
    /**
     * 进入我的授课班级页面(未结课的)
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/xdfStudent/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mav=new ModelAndView("/xdfStudent/wechat/index");  
        List<JwCourse> list=null;
        String email = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        logger.info("教师登录账号:"+email);
        String key="xdfStudentList"+email;
        long time1,time2=0;
        try{
            //如果存在缓存            
            if(redisTemplateUtil.isExist(key)){
                time1=System.currentTimeMillis();
                list = (List<JwCourse>) redisTemplateUtil.getObject(key, List.class);
                time2=System.currentTimeMillis();
                logger.info("从缓存中获取教师未结课的班号总共花了时间"+((time2-time1)/1000)+"秒");
            }else{
                //第一步:通过教师登录账号查询教师编码号
                if(StringUtils.isNotEmpty(email)){
                    time1=System.currentTimeMillis();
                    String teacherCode=xstService.getTeacherCode(email);
                    logger.info("教师编码号:"+teacherCode);
                    //第二步:通过教师编码号查询教师的授课班级
                    if(StringUtils.isNotEmpty(teacherCode)){                   
                        Date date=new Date();
                        //获取三个月前的第一天
                        String start=TimeUtil.getIntervalMonthFirstDay(-3, date);
                        //获取三个月后的第一天
                        String end=TimeUtil.getIntervalMonthFirstDay(3, date);
                        List<JwCourse> jwCourceList=xstService.getTeacherJwCourses(email,teacherCode,start,end);
                        //过滤出未结课的班号
                        list=getJwCourseListByTime(jwCourceList);
                        if(CollectionUtils.isNotEmpty(list)){
                            //根据班号去重复
                            HashSet<JwCourse> ts = new HashSet<JwCourse>(list);
                            list= new ArrayList<JwCourse>(ts);
                            time2=System.currentTimeMillis();
                            logger.info("调用接口获取教师未结课的班号总共花了时间"+((time2-time1)/1000)+"秒");
                            redisTemplateUtil.setList(key, list,Long.valueOf(30 * 60));
                            time1=System.currentTimeMillis();
                            logger.info("往缓存中存入教师未结课的班号总共花了时间"+((time1-time2)/1000)+"秒");
                        }                    
                    }                
                }                
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("进入我的授课班级页面失败!", e.getMessage());
        }
        mav.addObject("jwCourceList",list);
        return mav;
    }

    /**
     * 通过班号查询学员
     * @param request
     * @param classCodes
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/xdfStudent/serach")
    public ModelAndView serachXdfStudentByclassCodes(HttpServletRequest request,String classCodes){
        ModelAndView mav=new ModelAndView("/xdfStudent/wechat/serachResult");
        List<XdfStudentInfo> list=new ArrayList<XdfStudentInfo>();
        String email = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
        logger.info("通过班号查询学员教师登录账号:"+email);
        String key="xdfStudentInfo"+classCodes;
        long time1,time2=0;
        //如果存在缓存
        if(redisTemplateUtil.isExist(key)){
            time1=System.currentTimeMillis();
            list = (List<XdfStudentInfo>)redisTemplateUtil.getObject(key, List.class);
            time2=System.currentTimeMillis();
            logger.info("从缓存中获取我的学员手机号总共花了时间"+((time2-time1)/1000)+"秒");
        }else{            
            if(StringUtils.isNotEmpty(email)){
                time1=System.currentTimeMillis();
                list=xstService.serachXdfStudentByclassCodes(email,classCodes);
                time2=System.currentTimeMillis();
                logger.info("调用接口获取我的学员手机号总共花了时间"+((time2-time1)/1000)+"秒");
                redisTemplateUtil.setList(key,list,Long.valueOf(30 * 60));
                time1=System.currentTimeMillis();
                logger.info("往缓存中存入我的学员手机号总共花了时间"+((time1-time2)/1000)+"秒");
            } 
        }        
        mav.addObject("xsfList",list);
        return mav;
    }
    
    /**
     * 根据日期过滤出未结课的班
     * @param jwCourceList
     * @return
     */
    public List<JwCourse> getJwCourseListByTime(List<JwCourse> jwCourceList){
        List<JwCourse> list=new ArrayList<JwCourse>();
        if(CollectionUtils.isNotEmpty(jwCourceList)){
            String  dtEndDate="";
            String  patten="yyyy-MM-dd";
            Date date2=null;
            Date date3=new Date();
            for(JwCourse jc:jwCourceList){               
                dtEndDate=jc.getDtEndDate();                
                date2=TimeUtil.getDateByTime(dtEndDate, patten);
                //开课日期不大于今天并且结课日期不小于今天的就是未结课的课程
                if(date2.after(date3)){
                    list.add(jc);
                }               
            }
        }
        return list;
    }
    
    
    /**
     * 教师H5点名
     * @param request
     * @return
     */
    @RequestMapping(value="/xdfStudent/rollCall")
    public ModelAndView rollCallByH5(HttpServletRequest request){
        ModelAndView mav=null; 
        try{
            String email = (String) request.getSession().getAttribute(BindingConstants.BINDING_EMAIL_ADDR);
            logger.info("教师登录账号:"+email);
            //第一步:通过教师登录账号查询教师编码号
            if(StringUtils.isNotEmpty(email)){                
                String teacherCode=xstService.getTeacherCode(email);
                logger.info("教师编码号:"+teacherCode);
                //第二步:通过教师编码号调用接口
                if(StringUtils.isNotEmpty(teacherCode)){
                    //通过教师邮箱查询所在学校的schoolId 
                    String schoolId="25";
                    HrCompany hrCompany=hrCompanyService.selectByEmailAddr(email);
                    if(hrCompany!=null){
                        schoolId=hrCompany.getMessageId();
                    }                   
                    Map<String,Object> params = new HashMap<String,Object>();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                    String now=sdf.format(new Date()).replace(" ", "T");
                    params.put("method",teacherMethod);
                    params.put("schoolId",schoolId);
                    params.put("appId",appid);                    
                    params.put("now",now);
                    params.put("teacherCode",teacherCode);
                    String loginUserEmail=email+"@xdf.cn";
                    params.put("loginUserEmail",loginUserEmail);
                    String signText = (teacherMethod+now+schoolId+teacherCode+appid+"$"+appkey).toLowerCase();
                    String sign = Md5Tool.get32md5(signText).toUpperCase();
                    params.put("sign",sign);
                    params.put("pageSize","50");
                    String resultUrl="redirect:"+teacherUrl+"?method="+teacherMethod+"&schoolId="+schoolId+"&appId="+appid+"&now="+now+"&teacherCode="+teacherCode+"&loginUserEmail="+loginUserEmail+"&sign="+sign+"&pageSize=50";  
                    mav =new ModelAndView(resultUrl);
                }else{
                    mav =new ModelAndView("/xdfStudent/wechat/error"); 
                }      
            }            
        }catch(Exception e){
            e.printStackTrace();
            logger.error("进入教师H5点名页面异常", e.getMessage());
            mav =new ModelAndView("/xdfStudent/wechat/error");
        }
        return mav;
    }
    
    
    /**
     * 学员电话查询页面
     * @param request
     * @return
     */
    @RequestMapping(value="/xdfStudent/toSearchPhone")
    public ModelAndView ToSearchPhonePage(HttpServletRequest request){
        ModelAndView mav=new ModelAndView("/xdfStudent/wechat/searchPhone");  
        return mav;
    }
    
    /**
     * 根据学员号查询学员手机号信息
     * @param id
     * @return
     */
    @RequestMapping(value="/xdfStudent/searchPhone")
    @ResponseBody
    public Map<String, Object>searchPhone(HttpServletRequest request,String studentCode){ 
        logger.info("----根据学员号查询学员手机号信息----");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=xstService.searchPhone(request,studentCode);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("根据学员号查询学员手机号信息异常", e.getMessage());
            map.put("flag", false);
            map.put("message", "根据学员号查询学员手机号信息失败,请稍后重试!");
        }  
        return map;
    }
    
    
    
    
    
    
    
    
}
