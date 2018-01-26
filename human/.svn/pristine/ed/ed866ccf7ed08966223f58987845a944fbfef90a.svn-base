package com.human.customer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.customer.entity.CenterDept;
import com.human.customer.entity.CutomerCenterStudent;
import com.human.customer.service.CustomerCenterConfigDeptService;
import com.human.customer.service.CustomerCenterConfigModelService;
import com.human.customer.service.CustomerCenterManagerService;
import com.human.utils.BindingConstants;
import com.human.utils.CoupUtil;
import com.human.utils.interfaces.WechatLdxStudentClass;
import com.human.utils.interfaces.XdfBusinessInterfaceGeneralRequestSignature;

@Controller
@RequestMapping("/wxCustomer/CustomerCenter/")
public class CustomerCenterWXcontroller {

    private final  Logger logger = LogManager.getLogger(CustomerCenterWXcontroller.class);
    
    @Resource
    private CustomerCenterConfigModelService  centerModelService;
    
    @Resource
    private CustomerCenterManagerService managerService;
    
    @Resource
    private XdfBusinessInterfaceGeneralRequestSignature xdfIntegerface;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Resource
    private CoupUtil coupUtil;
    
    @Resource
    private CustomerCenterConfigDeptService  centerDeptService;
    
    @RequestMapping(value="toStudentCenter")
    public ModelAndView toTeacherCenter(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        CutomerCenterStudent stu = (CutomerCenterStudent)session.getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
        logger.info(stu.getSchoolid()+":"+stu.getOpenid()+"进入学员中心,进来的时间:"+System.currentTimeMillis());
        ModelAndView mav = new ModelAndView("/customer/student_center");
        List<CenterDept> allMenus =getMyMenus(stu);
        logger.info(stu.getSchoolid()+":"+stu.getOpenid()+"出来的时间:"+System.currentTimeMillis());
        mav.addObject("allMenus", allMenus);
        mav.addObject("fileurl", fileurl);
        return mav;
    }

    private List<CenterDept> getMyMenus(CutomerCenterStudent stu) {
        List<CenterDept> resultList=new ArrayList<CenterDept>();
        List<WechatLdxStudentClass> managerDeptList=new  ArrayList<WechatLdxStudentClass>();
        try {
            if(StringUtils.isNotBlank(stu.getStudentcode())) {
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                String endDate=sdf.format(new Date());
                String startDate=sdf.format(getNowOfLastYear());
                managerDeptList = xdfIntegerface.getByStuCodex(stu.getStudentcode(), stu.getSchoolid(), startDate, endDate);
                if(managerDeptList!=null) {
                    //排序
                    Collections.sort(managerDeptList, new Comparator<WechatLdxStudentClass>(){
                        public int compare(WechatLdxStudentClass o1, WechatLdxStudentClass o2) {  
                          int res=  o1.getBeginDate().compareTo(o2.getBeginDate());
                            if(res>0){  
                                return -1;  
                            }  
                            if(res==0){  
                                return 0;  
                            }  
                            return 1;  
                        }  
                    });
                    if(managerDeptList.size()>2) {
                        managerDeptList= managerDeptList.subList(0, 2);
                    }
                }
            }
            List<CenterDept> list= centerDeptService.getAllDeptMenu();
            for(CenterDept cd:list) {
                if(cd.getId()==-1||StringUtils.isEmpty(cd.getDeptName())) {
                    resultList.add(cd);
                    continue;
                }
                for(WechatLdxStudentClass ws:managerDeptList) {
                    if(cd.getDeptName().equals(ws.getDptName())) {
                        resultList.add(cd);
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error("===========获取班级管理部门接口失败===========",e);
        }
        return resultList;
    }
    public  Date getNowOfLastYear() {
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar.get(Calendar.YEAR) - 1);
        return aGregorianCalendar.getTime();
    }
    
    @RequestMapping(value="unbind")
    @ResponseBody
    public Map<String,Object> unbind(HttpServletRequest request){
        HttpSession session = request.getSession();
        CutomerCenterStudent stu = (CutomerCenterStudent)session.getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
        return coupUtil.unBindOpenId(stu.getOpenid(), stu.getSchoolid());
    }
}
