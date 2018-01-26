package com.human.datamanger.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.datamanger.entity.DataManger;
import com.human.datamanger.service.DataMangerService;
import com.human.datamanger.service.impl.DataMangerServiceImpl;
import com.human.utils.PageView;


@Controller
@RequestMapping(value="/data/manger/")
public class DataMangerController {
    
    private final  Logger logger = LogManager.getLogger(DataMangerController.class);
    
    @Resource
    private DataMangerService datamangerService;
    
    /**
     * 数据管理首页
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/datamanger/list");
        return mav;
    }
    
    /**
     * 分页查询数据管理
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,DataManger dm) {
        return  datamangerService.query(pageView, dm);
    }
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("数据管理删除");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=datamangerService.delete(deleteIds);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除数据管理失败,请稍后重试!");
        }
        return map;
    }
    /**
     * 跳转编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/datamanger/edit");
        DataManger dm=datamangerService.selectByPrimaryKey(id);
        mav.addObject("dm", dm);
        return mav; 
    }
    /**
     * 编辑数据管理
     * @param dm
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(DataManger dm) {
        Map<String, Object> map = new HashMap<String, Object>();
        String send=dm.getSendReason();
        int size=send.length();
        send.substring(0, 2);
        if("其他".equals(send.substring(0, 2))){
            dm.setSendReason(send.substring(3));
        }else{
            dm.setSendReason(send.substring(0, size-1));
        }
        try {
            map=datamangerService.edit(dm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/datamanger/add");
        return mav; 
    }
    /**
     * 添加操作
     * @param dm
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(DataManger dm) {
        Map<String, Object> map = new HashMap<String, Object>();
        String send=dm.getSendReason();
        int size=send.length();
        send.substring(0, 2);
        if("其他".equals(send.substring(0, 2))){
            dm.setSendReason(send.substring(3));
        }else{
            dm.setSendReason(send.substring(0, size-1));
        }
        try {
            datamangerService.addData(dm);
            map.put("message", "新增成功");
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增数据管理失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "新增失败，请稍后重试!");
        }
        return map;
    }
    /**
     * 管理上传excel
     * @return
     */
    @RequestMapping(value="updateexcel")
    public ModelAndView updateexcelview(){
        ModelAndView mov = new ModelAndView("/datamanger/updateexcel");
        return mov;
    }
    /**
     * 导入excel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="upexcel")
    public Map<String, Object> upexcel(HttpServletRequest request){
        return  datamangerService.upexcel(request);
    }
}
