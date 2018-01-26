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
import com.human.basic.entity.MailParam;
import com.human.basic.entity.MailTem;
import com.human.basic.service.MailNoticeTypeService;
import com.human.basic.service.MailTempService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/basic/mailTemp/")
public class MailTempController {
    
    private final Logger logger = LogManager.getLogger(MailTempController.class);
    
    @Resource
    private UserDeptService udService;
    
    
    @Resource
    private MailTempService mailTempService;
    
    @Resource
    private MailNoticeTypeService  mailNoticeTypeService;
    
    /**
     * 邮件模版管理
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/mailTemp/list");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    /**
     * 分页查询邮件模板
     * @param st
     * @param deptIds
     * @param pageView
     * @return
     */
    @RequestMapping("queryTem")
    @ResponseBody
    public PageView queryTem(MailTem mt,String deptIds,PageView pageView) {
        mt.setUserId(Common.getMyUser().getUserid());
        return  mailTempService.queryTem(pageView, mt,deptIds);
    }
    
    /**
     * 新增邮件模板
     * @param userId
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(Long userId) {
        ModelAndView mav=new ModelAndView("/basic/mailTemp/add");
        List<HrCompany> companyList=udService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);       
        MailParam mp=new MailParam();
        mp.setState(0);
        mp.setType(1);
        List<MailParam> mailParam=mailTempService.queryParam(mp);
        mav.addObject("mailParam", mailParam);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String,Object> add(MailTem mt) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            mt.setCreateUser(Common.getAuthenticatedUsername());
            mailTempService.add(mt);
            map.put("flag", true);
            map.put("msg", "新增邮件模版成功!");
        }catch(Exception e){
            logger.error("保存邮件模版错误",e);
            map.put("flag", false);
            map.put("msg", "操作失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 编辑邮件模板
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav=new ModelAndView("/basic/mailTemp/edit");
        MailTem st = mailTempService.queryById(id);
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
            MailNoticeType mnt =mailNoticeTypeService.queryById(Long.valueOf(st.getTemType()));
            mav.addObject("mnt", mnt);
        }
        MailParam mp=new MailParam();
        mp.setState(0);
        mp.setType(1);
        List<MailParam> mailParam=mailTempService.queryParam(mp);
        mav.addObject("mailParam", mailParam);
        return mav;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(MailTem mt) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            mt.setUpdateUser(Common.getAuthenticatedUsername());
            mailTempService.edit(mt);
            map.put("flag", true);
            map.put("msg", "编辑邮件模版成功!");
        }catch(Exception e){
            logger.error("编辑邮件模版错误",e);
            map.put("flag", false);
            map.put("msg", "操作失败，请稍后重试!");
        }
        return map;
    }
    
    @RequestMapping("delTemp")
    @ResponseBody
    public Map<String,Object> delTemp(String deleteIds) {
        return mailTempService.delTemp(deleteIds);
    }
    
    
}
