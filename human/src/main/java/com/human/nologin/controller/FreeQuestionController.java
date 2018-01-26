package com.human.nologin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.human.questionnaire.entity.DisableIP;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.entity.Qform;
import com.human.questionnaire.service.QuestionCollectService;
import com.human.questionnaire.service.QuestionParamService;
import com.human.utils.Common;
import com.human.utils.RedisTemplateUtil;
import com.human.utils.RegExpValidatorUtils;

@Controller
@RequestMapping("/free/question/")
public class FreeQuestionController {

    
    @Resource
    private QuestionParamService qpService;
    
    @Resource
    private QuestionCollectService qcService;
    
    @Resource
    private RedisTemplateUtil redis;
    
    @RequestMapping(value = "collect")
    public ModelAndView collect(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/questionnaire/collect/tip");
        String id = request.getParameter("id");
        if (id == null || id.trim().length() == 0) {
            mav.addObject("msg", "缺少ID参数，请检查链接地址是否正确！");
            return mav;
        }
        
        String token = request.getParameter("token");
        if (token == null || token.trim().length() == 0) {
            mav.addObject("msg", "缺少token参数，请检查链接地址是否正确！");
            return mav;
        }
        boolean flag = redis.isExist("key_"+token);
        if(flag){
            mav.addObject("msg", "请勿重复提交!");
            return mav;
        }
        
        Qform q = new Qform();
        q.setId(Long.valueOf(id));
        q.setIsCollect(true);
        q.setState(1);
        List<Qform> list = qcService.queryNoPage(q);
        if (list.size() == 0) {
            mav.addObject("msg", "无法找到对应的问卷！");
            return mav;
        }
        Qform qfrom = list.get(0);
        mav.addObject("url", qfrom.getFailUrl());
        DisableIP ip = new DisableIP();
        ip.setFormId(qfrom.getId());
        List<DisableIP> ipList = qcService.queryDisableIP(ip);
        String ipAddr = Common.toIpAddr(request);
        // 获取IP黑名单
        for (DisableIP di : ipList) {
            if (di.getIpAddr().equals(ipAddr)) {
                mav.addObject("msg", "对不起，您的IP已被禁止提交！");
                return mav;
            }
        }

        String email = request.getParameter("email");
        if (null != email) {
            if (!RegExpValidatorUtils.isEmail(email)) {
                mav.addObject("msg", "邮箱格式不正确");
                return mav;
            }
        }
        String phonetel = request.getParameter("phonetel");
        if (null != phonetel) {
            if (!RegExpValidatorUtils.IsHandset(phonetel)) {
                mav.addObject("msg", "手机号格式不正确");
                return mav;
            }
        }
        
        try {
            List<ParamBean> pbList = qpService.queryFormParam(Long.valueOf(id));
            qcService.saveCollect(qfrom, pbList, request);
            redis.set("key_"+token, token, (long) 60*120);
            mav.addObject("msg", "提交成功!");
            mav.addObject("url", qfrom.getSuccessUrl());
            return mav;
        }catch (Exception e) {
            mav.addObject("msg", "提交失败，请检查必填项是否填写完全!");
            return mav;
        }
    }
       
}
