package com.human.questionnaire.controller;

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

import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.service.QuestionParamService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/questionnaire/param/")
public class QuestionParamController {
    
    private final Logger logger = LogManager.getLogger(QuestionParamController.class);
    
    @Resource
    private QuestionParamService  qpService;
    
    /**
     * 
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/questionnaire/param/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(ParamBean bean,PageView pageView) {
        return  qpService.query(pageView, bean);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/questionnaire/param/add");
        return mav;
    }

    /**
     * 保存数据
     * 
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(ParamBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ParamBean bean1=new ParamBean();
            bean1.setKey(bean.getKey());
            List<ParamBean> paramList = qpService.queryParam(bean1);
            if (paramList.size()>0) {
                map.put("flag", false);
                map.put("message", "对不起，参数key已存在，请重新输入!");
            } else {
              /*  if(bean.getSort()==null){
                    bean.setSort(0);
                }*/
                qpService.saveParam(bean);
                map.put("flag", true);
                map.put("message", "参数添加成功!");
            }
        } catch (Exception e) {
            logger.error(e);
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * 
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(ParamBean bean) {
        if(bean.getId()==null){
            return null;
        }
        List<ParamBean> paramList = qpService.queryParam(bean);
        ModelAndView mav = new ModelAndView();
        if(paramList.size()>0){
            mav.addObject("id", paramList.get(0).getId());
            mav.addObject("keyValue",paramList.get(0).getKey());
            mav.addObject("paramName",paramList.get(0).getName());
            mav.addObject("keySort",paramList.get(0).getSort());
        }
        mav.setViewName("/questionnaire/param/edit");
        return mav;
    }
    
    /**
     * 修改
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(ParamBean bean) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            qpService.updateParam(bean);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("message", "修改失败");
        }
        return map;
    }
    
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds,Integer status) {
        return   qpService.updateStatus(deleteIds,status);
    }
}
