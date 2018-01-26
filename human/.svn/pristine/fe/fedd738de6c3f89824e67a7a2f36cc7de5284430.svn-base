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
import com.human.basic.entity.MailNoticeType;
import com.human.basic.service.MailNoticeTypeService;
import com.human.basic.service.MailTempService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/mailNoticeType/")
public class MailNoticeTypeController {
    
    private final Logger logger = LogManager.getLogger(MailNoticeTypeController.class);
    
    @Resource
    private UserDeptService udService;
        
    @Resource
    private MailTempService mailTempService;
    
    @Resource
    private MailNoticeTypeService  mailNoticeTypeService;
    
    /**
     * 邮件通知类型管理
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/mailNoticeType/list");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    /**
     * 分页查询邮件通知类型
     * @param st
     * @param deptIds
     * @param pageView
     * @return
     */
    @RequestMapping("queryTem")
    @ResponseBody
    public PageView queryTem(MailNoticeType mnt,String deptIds,PageView pageView) {
        mnt.setUserId(Common.getMyUser().getUserid());
        return  mailNoticeTypeService.queryTem(pageView, mnt,deptIds);
    }
    
    /**
     * 新增邮件通知类型
     * @param userId
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/basic/mailNoticeType/add");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String,Object> add(MailNoticeType mnt) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            mnt.setCreateUser(Common.getAuthenticatedUsername());
            mailNoticeTypeService.add(mnt);
            map.put("flag", true);
            map.put("msg", "新增邮件通知类型成功!");
        }catch(Exception e){
            logger.error("新增邮件通知类型错误",e);
            map.put("flag", false);
            map.put("msg", "新增邮件通知类型失败!");
        }
        return map;
    }
    
    /**
     * 编辑邮件通知类型
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav=new ModelAndView("/basic/mailNoticeType/edit");
        MailNoticeType mnt = mailNoticeTypeService.queryById(id);
        if(null!=mnt){
            mav.addObject("st", mnt);
            Long userId=Common.getMyUser().getUserid();
            List<HrCompany> companyList=udService.getUserCompany(userId);
            mav.addObject("companyList", companyList);
            UserDept ud =new UserDept();
            ud.setCompanyId(mnt.getCompanyId());
            ud.setUserId(userId);
            List<HrOrganization> hrOrg=udService.getUserOrg(ud);
            mav.addObject("hrOrgList", hrOrg);
        }
        return mav;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(MailNoticeType mnt) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            mnt.setUpdateUser(Common.getAuthenticatedUsername());
            mailNoticeTypeService.edit(mnt);
            map.put("flag", true);
            map.put("msg", "编辑邮件通知类型成功!");
        }catch(Exception e){
            logger.error("编辑邮件通知类型错误!",e);
            map.put("flag", false);
            map.put("msg", "编辑邮件通知类型错误!");
        }
        return map;
    }
    
    @RequestMapping("delTemp")
    @ResponseBody
    public Map<String,Object> delTemp(String deleteIds) {
        return mailNoticeTypeService.delTemp(deleteIds);
    }
    
    
    /**
     * 
     * @param mnt
     * @return
     */
    @RequestMapping("getMailNoticeTypeList")
    @ResponseBody
    public List<MailNoticeType> getMailNoticeTypeList(MailNoticeType mnt){
        return  mailNoticeTypeService.queryByParams(mnt);
    }
    
}
