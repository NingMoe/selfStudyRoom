package com.human.recruitment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.HrEthnic;
import com.human.basic.entity.HrNationality;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.HrNationalityService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrInterviewRecord;
import com.human.recruitment.entity.HrPositiveRecord;
import com.human.recruitment.entity.HrResumeEntryhandler;
import com.human.recruitment.service.HrPositiveRecordService;
import com.human.recruitment.service.SeekerEntryService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/positiveInterview/")
public class PositiveInterviewController {
    private final  Logger logger = LogManager.getLogger(PositiveInterviewController.class);
    
    @Autowired
    private HrCompanyService hrCompanyService;
    
    @Autowired
    private HrPositiveRecordService positiveRecordService;
    
    @Autowired
    private SeekerEntryService entryService;
    
    @Autowired
    private HrNationalityService nationalityService;
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        List<HrCompany> companys = hrCompanyService.findAll();
        ModelAndView mav=new ModelAndView("/recruitment/positiveInterview/list");
        String currentTime = Common.fromDateY();
        mav.addObject("companys",companys);
        mav.addObject("currentTime",currentTime);
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
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toInterview")
    public ModelAndView toInterview(Integer id){
        ModelAndView mav = new ModelAndView("/recruitment/positiveInterview/interview_result");
        HrInterviewRecord record = positiveRecordService.getInterviewRecord(id);
        if(record==null){
            record = new HrInterviewRecord();
            record.setPositiveId(id);
        }
        mav.addObject("interview",record);
        return mav;
    }
    
    
    /**
     * 编辑面谈反馈
     * @return
     */
    @RequestMapping(value="addInterview")
    @ResponseBody
    public Map<String,Object> addInterview(HrInterviewRecord result){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            positiveRecordService.updateInterviewRecord(result);
            map.put("flag", true);
            map.put("message", "编辑面谈反馈成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑面谈反馈失败");
        }
        return map;
    }
    
    /**
     * 进入查看入职资料页面
     * @return
     */
    @RequestMapping(value="toViewDetail")
    public ModelAndView toViewDetail(HttpServletRequest request,Integer id){
        ModelAndView mav = new ModelAndView("/recruitment/positiveInterview/detail");
        HrPositiveRecord record = positiveRecordService.selectByPrimaryKey(id);
        HrResumeEntryhandler hreh = entryService.selectByIdCard(record.getIdCard());
        HrEntryBase entry = entryService.getBaseSeeker(hreh.getId());
        HrInterviewRecord interviewRecord = positiveRecordService.getInterviewRecord(record.getId());
        if(entry!=null){
            entry = entryService.getComplexSeeker(entry.getId());
            mav.addObject("entryBase",entry);
            mav.addObject("fileurl",fileurl);
        }
        if(interviewRecord!=null){
            mav.addObject("interviewRecord",interviewRecord);
        }
        List<HrEthnic> ethnics = nationalityService.getAllEthnic();
        List<HrNationality> nationalitys = nationalityService.getAllNationality();
        AreaInfo a = new AreaInfo();
        a.setAreaLevel(1);
        List<AreaInfo> provinces = areaInfoService.getArea(a);
        mav.addObject("ethnics",ethnics);
        mav.addObject("nationalitys",nationalitys);
        mav.addObject("provinces",provinces);
        mav.addObject("resumeId", hreh.getResumeId());
        mav.addObject("positiveId",record.getId());
        return mav;
    }
 
}
