package com.human.questionnaire.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.questionnaire.entity.DisableIP;
import com.human.questionnaire.entity.FormParam;
import com.human.questionnaire.entity.ParamBean;
import com.human.questionnaire.entity.Qform;
import com.human.questionnaire.service.QuestionCollectService;
import com.human.questionnaire.service.QuestionParamService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/questionnaire/collect/")
public class QuestionCollectController {

    @Resource
    private QuestionCollectService qcService;
    
    @Resource
    private QuestionParamService qpService;
    
    /**
     * 
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/questionnaire/collect/list");
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
    public PageView query(Qform bean,PageView pageView) {
        return  qcService.query(pageView, bean);
    }
    
    /**
     * 跳转新增界面
     * 
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/questionnaire/collect/add");
        ParamBean pb=new ParamBean();
        pb.setStatus(0);
        List<ParamBean> pbList= qpService.queryParam(pb);
        mav.addObject("pbList",pbList);
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
    public Map<String, Object> add(Qform bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            qcService.saveForm(bean);
            map.put("flag", true);
            map.put("message", "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
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
    public ModelAndView toEdit(Qform bean) {
        ModelAndView mav=new ModelAndView();
        List<Qform> list=qcService.queryNoPage(bean);
        if(list.size()==0){
            return null;
        }
        mav.addObject("qform",list.get(0));
        ParamBean pb=new ParamBean();
        pb.setStatus(0);
        List<ParamBean> pbList= qpService.queryParam(pb);
        mav.addObject("pbList",pbList);
        mav.setViewName("/questionnaire/collect/edit");
        return mav;
    }
    
    /**
     * 修改
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(Qform bean) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            qcService.updateParam(bean);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch(Exception e){
            e.printStackTrace();;
            map.put("flag", false);
            map.put("message", "修改失败");
        }
        return map;
    }

    /**
     * 进入代码查看
     * 
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("showCode")
    public ModelAndView showCode(Qform bean,HttpServletRequest request) {
        ModelAndView mav=new ModelAndView();
        List<Qform> list=qcService.queryNoPage(bean);
        if(list.size()==0){
            return null;
        }
        String strBackUrl = "http://" + request.getServerName()+ ":" + request.getServerPort()+ request.getContextPath()+"/free/question/collect.html?id='+"+"$('#sm_id').val()+'&";    //项目名称  
        mav.addObject("qform",list.get(0));
        List<ParamBean> pbList= qpService.queryFormParam(bean.getId());
        for(ParamBean pb:pbList){
            strBackUrl+=pb.getKey()+"='+"+"$('#sm_"+pb.getKey()+"').val()+"+"'&";
        }
        mav.addObject("strBackUrl",strBackUrl.substring(0,strBackUrl.length()-3));
        mav.addObject("pbList",pbList);
        mav.setViewName("/questionnaire/collect/showcode");
        return mav;
    }
    
    /**
     * 进入代码查看
     * 
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toQueryResult")
    public ModelAndView showResult(Long id) {
        ModelAndView mav=new ModelAndView();
        mav.addObject("formId",id);
        mav.setViewName("/questionnaire/collect/showresult");
        return mav;
    }
    
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("queryTitle")
    @ResponseBody
    public List<FormParam> queryTitle(Long id) {
        Qform bean=new Qform();
        bean.setId(id);
        List<Qform> list=qcService.queryNoPage(bean);
        if(list.size()>=0){
           return list.get(0).getParamList();
        }
        return  null;
    }
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("queryResult")
    @ResponseBody
    public PageView queryResult(Long id,PageView pageView) {
        return  qcService.queryResult(pageView, id);
    }
    
    @RequestMapping("delCollect")
    @ResponseBody
    public Map<String,Object> delCollect(String uid) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            qcService.delCollect(uid);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch(Exception e){
            e.printStackTrace();;
            map.put("flag", false);
            map.put("message", "操作失败");
        }
        return map;
    }
    
    
    @RequestMapping("showIp")
    public ModelAndView showIp(Long formId) {
        ModelAndView mav=new ModelAndView();
        mav.addObject("formId",formId);
        mav.setViewName("/questionnaire/collect/iplist");
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping("queryDisableIpList")
    @ResponseBody
    public PageView queryDisableIpList(DisableIP bean,PageView pageView) {
        return  qcService.queryDisableIpList(pageView, bean);
    }
    
    @RequestMapping("delIp")
    @ResponseBody
    public Map<String,Object> delIp(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            qcService.delIp(deleteIds);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch(Exception e){
            e.printStackTrace();;
            map.put("flag", false);
            map.put("message", "操作失败");
        }
        return map;
    }
    
    @RequestMapping("addDisableIp")
    @ResponseBody
    public Map<String,Object> addDisableIp(DisableIP bean) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            List<DisableIP> l=qcService.queryDisableIP(bean);
            if(l.size()>0){
                map.put("flag", false);
                map.put("message", "对不起，该IP已经在黑名单中，请勿重复添加!");
                return map;
            }
            qcService.addDisableIp(bean);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "操作失败");
        }
        return map;
    }
    
    
    @RequestMapping(value="exportResult")
    public void exportResult(HttpServletRequest request,
            HttpServletResponse response)  {
            qcService.exportResult(request,response);
    }
    
    
    @RequestMapping("deleteForm")
    @ResponseBody
    public Map<String,Object> deleteForm(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            qcService.deleteForm(deleteIds);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch(Exception e){
            e.printStackTrace();;
            map.put("flag", false);
            map.put("message", "操作失败");
        }
        return map;
    }
    
}
