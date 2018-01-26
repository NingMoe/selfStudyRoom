package com.ls.spt.basic.controller;

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
import com.alibaba.fastjson.JSON;
import com.ls.spt.basic.entity.BaseDictionary;
import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;


@Controller
@RequestMapping("/basic/dic")
public class DictionaryController {
    
    private final  Logger logger = LogManager.getLogger(DictionaryController.class);
    
    @Autowired
    private DictionaryService dictionaryService;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/basic/dic/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,BaseDictionary baseDictionary){
        return  dictionaryService.getDicItemPage(pageView, baseDictionary);
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/basic/dic/add");
        return mav;
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(BaseDictionary baseDictionary){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            baseDictionary.setStatus(1);
            dictionaryService.addBaseDic(baseDictionary);
            map.put("flag", true);
            map.put("message", "新增字典成功");
        }catch(Exception e){
            logger.error("新增字典出错");
            map.put("flag", false);
            map.put("flag", "新增字典失败");
        }
        
        return map;
    }
    
    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Integer id){
        ModelAndView mav=new ModelAndView("/basic/dic/edit");
        BaseDictionary baseDictionary = dictionaryService.selectByPrimaryKey(id);
        mav.addObject("dic", baseDictionary);
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="queryData")
    @ResponseBody
    public Map<String,Object> queryData(String key){
        Map<String,Object> map = new HashMap<String,Object>();
        List<DicData> datas = dictionaryService.getDataByKey(key);
        map.putIfAbsent("disDatas", datas);
        return map;
    }
    
    /**
     * 编辑数据
     * @return
     */
    @RequestMapping(value="editData")
    @ResponseBody
    public Map<String,Object> editData(Integer id,String name,String jsonStr){
        Map<String,Object> map = new HashMap<String,Object>();
        List<DicData> datas = new ArrayList<DicData>();
        try{
            if(StringUtils.isNoneEmpty(jsonStr)){
                datas = JSON.parseArray(jsonStr, DicData.class);
            }
            dictionaryService.editDicData(id, name, datas);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            map.put("flag", false);
            map.put("message", "修改失败");
        }
        
        return map;
    }
}
