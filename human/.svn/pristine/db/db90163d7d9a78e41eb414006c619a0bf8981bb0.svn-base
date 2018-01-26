package com.human.recruitment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.Users;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.UserService;
import com.human.recruitment.entity.HrPositiveRecord;
import com.human.recruitment.service.HrPositiveRecordService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/positive/")
public class PositiveController {
    private final  Logger logger = LogManager.getLogger(PositiveController.class);
    
    @Autowired
    private HrCompanyService hrCompanyService;
    
    @Autowired
    private HrPositiveRecordService positiveRecordService;
    
    @Autowired
    private UserService userService;
    
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        List<HrCompany> companys = hrCompanyService.findAll();
        ModelAndView mav=new ModelAndView("/recruitment/positive/list");
        mav.addObject("companys",companys);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,HrPositiveRecord positiveRecord){
        if(positiveRecord!=null){
            positiveRecord.setLoginCompany(Common.getMyUser().getCompanyId());
        }
        return  positiveRecordService.getPositiveRecordPage(pageView, positiveRecord);
    }
    
    
    /**
     * 进入面试安排页面
     * @return
     */
    @RequestMapping(value="toArrangement")
    public ModelAndView toArrangement(String ids){
        ModelAndView mav = null;
        String[] idArr = ids.split(",");
        HrPositiveRecord record = positiveRecordService.selectByPrimaryKey(Integer.valueOf(idArr[0]));
        if(record.getInterviewTime()!=null){
            mav = new ModelAndView("/recruitment/positive/arrangement_edit");
            mav.addObject("record", record);
            return mav;
        }else{
            return new ModelAndView("/recruitment/positive/arrangement");
        }
    }
    
    /**
     * 转正面谈安排
     * @return
     */
    @RequestMapping(value="arrangement")
    @ResponseBody
    public Map<String,Object> arrangement(String ids,String isSendMsg,HrPositiveRecord positiveRecord){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<HrPositiveRecord> records = new ArrayList<HrPositiveRecord>();
            if(StringUtils.isNotEmpty(ids)){
                String[] idArr = ids.split(",");
                for(String id:idArr){
                    HrPositiveRecord h = positiveRecord;
                    h.setId(Integer.valueOf(id));
                    h.setMsStatus(1);
                    records.add(h);
                }
                positiveRecordService.updateRecordList(records);
                result.put("flag", true);
                result.put("message", "面试安排成功");
            }else{
                result.put("flag", false);
                result.put("message", "请选择单据进行设置");
            }
            
        }catch(Exception e){
            logger.error("面试安排出错"+e.getMessage());
            result.put("flag", false);
            result.put("message", "面试安排出错");
        }
        return result;
    }
    
    /**
     * 根据邮箱获取人员
     * @return
     */
    @RequestMapping(value="checkEmail")
    @ResponseBody
    public Map<String,Object> checkEmail(String email){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            Users u = new Users();
            u.setLoginName(email);
            List<Users> list = userService.queryUser(u);
            if(list==null||list.size()==0){
                result.put("flag", false);
                result.put("message", "不存在该用户");
                return result;
            }else if(list.size()>1){
                result.put("flag", false);
                result.put("message", "输入不完整");
                return result;
            }else{
                result.put("flag", true);
                result.put("user", list.get(0));
                return result;
            }
        }catch(Exception e){
            logger.error("校验邮箱:"+e.getMessage());
            result.put("flag", false);
            result.put("message", "校验邮箱出错");
        }
        return result;
    }
}
