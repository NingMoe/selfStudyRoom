package com.human.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.human.basic.entity.SmsParam;
import com.human.basic.entity.SmsRecord;
import com.human.basic.entity.SmsTem;
import com.human.basic.service.SmsTempService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/sms/")
public class SmsController {
    
    private final Logger logger = LogManager.getLogger(SmsController.class);
    
    @Resource
    private UserDeptService udService;
    
    @Resource
    private SmsTempService smsService;
    
    /**
     * 短信模版管理
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/sms/list");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    @RequestMapping("queryTem")
    @ResponseBody
    public PageView queryTem(SmsTem st,String deptIds,PageView pageView) {
        st.setUserId(Common.getMyUser().getUserid());
        return  smsService.queryTem(pageView, st,deptIds);
    }
    
    
    @RequestMapping("toAdd")
    public ModelAndView toAdd(Long userId) {
        ModelAndView mav=new ModelAndView("/basic/sms/add");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        SmsParam sp=new SmsParam();
        List<SmsParam> smsParam=smsService.queryParam(sp);
        mav.addObject("smsParam", smsParam);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String,Object> add(SmsTem st) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            st.setCreateUser(Common.getMyUser().getUserid());
            smsService.add(st);
            map.put("flag", true);
            map.put("msg", "新增短信模版成功!");
        }catch(Exception e){
            logger.error("保存短信模版错误",e);
            map.put("flag", false);
            map.put("msg", "操作失败，请稍后重试!");
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav=new ModelAndView("/basic/sms/edit");
        SmsTem st = smsService.queryById(id);
        if(null!=st){
            mav.addObject("st", st);
            Long userId=Common.getMyUser().getUserid();
            List<HrCompany> companyList=udService.getUserCompany(userId);
            mav.addObject("companyList", companyList);
            UserDept ud =new UserDept();
            ud.setCompanyId(st.getTemCompany());
            ud.setUserId(userId);
            List<HrOrganization> hrOrg=udService.getUserOrg(ud);
            mav.addObject("hrOrgList", hrOrg);
        }
        SmsParam sp=new SmsParam();
        List<SmsParam> smsParam=smsService.queryParam(sp);
        mav.addObject("smsParam", smsParam);
        return mav;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(SmsTem st) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            st.setUpdateUser(Common.getMyUser().getUserid());
            smsService.edit(st);
            map.put("flag", true);
            map.put("msg", "编辑短信模版成功!");
        }catch(Exception e){
            logger.error("编辑短信模版错误",e);
            map.put("flag", false);
            map.put("msg", "操作失败，请稍后重试!");
        }
        return map;
    }
    
    @RequestMapping("delTemp")
    @ResponseBody
    public Map<String,Object> delTemp(String deleteIds) {
        return smsService.delTemp(deleteIds);
    }
    
    
    @RequestMapping("sendMsg")
    @ResponseBody
    public Map<String,Object> sendMsg(SmsRecord sr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            MyUser mu=new Common().getMyUser();
            sr.setCompany(mu.getCompanyId());
            sr.setDeptId(mu.getDeptId());
            sr.setSendUser(mu.getUserid());
            sr.setSendName(mu.getName());
            smsService.sendMessage(sr);
            map.put("flag", true);
        }
        catch (Exception e) {
            logger.error("发送短信错误", e);
            map.put("flag", false);
        }
        return map; 
    }
    
    
    @RequestMapping("sendView")
    public ModelAndView sendView(SmsRecord sr) {
        ModelAndView mav=new ModelAndView("/basic/sms/sendView");
        SmsTem st=new SmsTem();
        st.setUserId(Common.getMyUser().getUserid());
        List<SmsTem> stList=smsService.queryTemAll(st);
        List<SmsRecord> srList=smsService.queryMsgRecordNoPage(sr);
        mav.addObject("stList", stList);
        mav.addObject("srList", srList);
        mav.addObject("sr", sr);
        return mav;
    }
    
    @RequestMapping("sendBatchView")
    public ModelAndView sendBatchView(String smsJson) {
        ModelAndView mav=new ModelAndView("/basic/sms/batchSendView");
        SmsTem st=new SmsTem();
        st.setUserId(Common.getMyUser().getUserid());
        List<SmsTem> stList=smsService.queryTemAll(st);
        mav.addObject("stList", stList);
        mav.addObject("smsJson", smsJson);
        return mav;
    }
    
    @RequestMapping("batchSendMsg")
    @ResponseBody
    public Map<String,Object> batchSendMsg(SmsRecord sr,String smsJson) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            MyUser mu=new Common().getMyUser();
            List<SmsRecord> srList = JSON.parseArray(smsJson, SmsRecord.class);
            for(SmsRecord s:srList){
                s.setSendComment(sr.getSendComment());
                s.setMemo(sr.getMemo());
                s.setCompany(mu.getCompanyId());
                s.setDeptId(mu.getDeptId());
                s.setSendUser(mu.getUserid());
                s.setSendName(mu.getName());
            }
            smsService.sendMessage(srList);
            map.put("flag", true);
        }
        catch (Exception e) {
            logger.error("发送短信错误", e);
            map.put("flag", false);
        }
        return map; 
    }
    
    /**
     * 短信历史记录
     * @return
     */
    @RequestMapping("smsRecord")
    public ModelAndView smsRecord() {
        ModelAndView mav=new ModelAndView("/basic/sms/smsRecordList");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    @RequestMapping("queryMsgRecord")
    @ResponseBody
    public PageView queryMsgRecord(SmsRecord sr,String deptIds,PageView pageView) {
        sr.setSendUser(Common.getMyUser().getUserid());
        return  smsService.queryMsgRecord(pageView, sr,deptIds);
    }
    
}
