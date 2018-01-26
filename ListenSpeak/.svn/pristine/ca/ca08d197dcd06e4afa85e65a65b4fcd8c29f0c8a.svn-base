package com.ls.spt.manager.controller;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.manager.entity.OplogEntity;
import com.ls.spt.manager.service.OpLogService;
import com.ls.spt.utils.PageView;


/**
 * 操作日志控制层
 * @author HF-121093-01
 *
 */
@Controller
@RequestMapping("/manager/oplog/")
public class OpLogController {
    
    private final  Logger logger = LogManager.getLogger(OpLogController.class);
    
    @Resource
    private OpLogService opLogService;
    
    /**
     * 列表页面
     * @return
     */
    @RequestMapping(value="list")
    public ModelAndView list(){
        logger.info("=======进入操作日志列表=========");
        ModelAndView mav=new ModelAndView("/manager/op_log/list");
        return mav;
    } 

    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(OplogEntity oplog,PageView pageView){
        return  opLogService.query(oplog, pageView);
    }
}
