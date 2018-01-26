package com.ls.spt.basic.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.AreaInfo;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.utils.Common;
/**
 * 省市区控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value = "/basic/areaInfo/")
public class AreaInfoController {

    @Autowired
    private AreaInfoService areaInfoService;

    private final Logger logger = LogManager.getLogger(AreaInfoController.class);
    
    /**
     * 省份列表页
     * @return
     */
    @RequestMapping("areaprovince")
    public ModelAndView province() {
        return new ModelAndView("/basic/area_info/provinceList");
    }
    /**
     * 城市列表页
     * @return
     */
    @RequestMapping("areacity")
    public ModelAndView city() {
        ModelAndView mav = new ModelAndView("/basic/area_info/cityList");
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
    /**
     * 行政区列表页
     * @return
     */
    @RequestMapping("arearegion")
    public ModelAndView region() {
        ModelAndView mav = new ModelAndView("/basic/area_info/areaList");
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        //获取省份
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
    /**
     * 分页获取省市区份数据
     * @param areaInfo
     * @param pageView
     * @return
     */
//    @RequestMapping(value = "getAreaPage", method = RequestMethod.POST)
    @RequestMapping("getAreaPage")
    @ResponseBody
    public PageView getAreaInfoPage(AreaInfo areaInfo, PageView pageView) {
        return areaInfoService.getAreaPage(pageView, areaInfo);
    }
    
    /**
     * 条件查询省市区数据(不分页)
     * @param areaInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getArea", method = RequestMethod.POST)
    public List<AreaInfo> getAreaInfo(AreaInfo areaInfo) {
        List<AreaInfo> list = new ArrayList<AreaInfo>();
        list = areaInfoService.getArea(areaInfo);
        return list;
    }

    /**
     * 新增省市区数据页面
     * @param areaLevel
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(String areaLevel) {
        ModelAndView mav = new ModelAndView();
        if ("1".equals(areaLevel)) {
            return new ModelAndView("/basic/area_info/provinceAdd");
        }
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        if ("2".equals(areaLevel)) {
            mav.setViewName("/basic/area_info/cityAdd");          
            return mav;
        }
        if ("3".equals(areaLevel)) {
            mav.setViewName("/basic/area_info/areaAdd");
            return mav;
        }
        return null;
    }
    /**
     * 保存省市区数据
     * @param areaInfo
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addArea", method = RequestMethod.POST)
    public Map<String, Object> addAreaInfo(AreaInfo areaInfo, HttpSession httpSession) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer userId = Common.getMyUser().getUserid();
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != null) {
            areaInfo.setCreateUser(String.valueOf(userId));
            areaInfo.setCreateTime(df.format(new Date()));
            Integer i = areaInfoService.addAreaInfo(areaInfo);
            switch (i) {
                case 0:
                    map.put("flag", true);
                    map.put("message", "添加成功！");
                    logger.error("add area success");
                    break;
                case 1:
                    map.put("flag", false);
                    map.put("message", "请填写信息！");
                    logger.error("add area error, area is null");
                    break;
                case 2:
                    map.put("flag", false);
                    map.put("message", "信息填写不完整！");
                    logger.error("add area error, Incomplete area");
                    break;
                case 3:
                    map.put("flag", false);
                    map.put("message", "该信息已有，请不要重复添加！");
                    logger.error("add area error, area is repeat");
                    break;
                case 4:
                    map.put("flag", false);
                    map.put("message", "信息格式有错误，请确认！");
                    logger.error("add area error,The format of tarea is error");
                    break;
                case 5:
                    map.put("flag", false);
                    map.put("message", "非省级区域，必需要有上级Id！");
                    logger.error("add area error,areaLevel is not a province as not have parentAreaCode");
                    break;
                default:
                    map.put("flag", false);
                    map.put("message", "服务器出错，联系管理员！");
                    logger.error("add area error, Serer is error");
                    break;
            }
        }
        else {
            map.put("flag", false);
            map.put("message", "未登录！");
            logger.error("add area error, Not logged in");
        }
        return map;
    }

    /**
     * 编辑省市区页面
     * @param areaLevel
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(String areaLevel, Integer id) {
        AreaInfo sInfo = new AreaInfo();
        sInfo.setId(id);
        AreaInfo areaInfo = areaInfoService.getAreaByPrimaryKey(sInfo);
        String viewName = "";
        if ("1".equals(areaLevel)) {
            viewName = "/basic/area_info/provinceUpdate";
        }
        if ("2".equals(areaLevel)) {
            viewName = "/basic/area_info/cityUpdate";
        }
        if ("3".equals(areaLevel)) {
            viewName = "/basic/area_info/areaUpdate";
        }
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("area", areaInfo);
        return mav;
    }
    
    /**
     * 编辑省市区
     * @param areaInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateArea", method = RequestMethod.POST)
    public Map<String, Object> updateAreaInfo(AreaInfo areaInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer i = areaInfoService.updateAreaInfo(areaInfo);
        if (i == 1) {
            map.put("flag", true);
            map.put("message", "修改成功！");
        }else {
            map.put("flag", false);
            map.put("message", "修改失败！");
        }
        return map;
    }
    
    
    /**
     * 删除省市区数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delArea", method = RequestMethod.POST)
    public Map<String, Object> delAreaInfo(Integer id,Integer areaLevel ) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer i=0;
        if(areaLevel==3){//行政区直接删
             i = areaInfoService.delAreaInfo(id);
        }else if(areaLevel==2){//城市 ,先删行政区，再删除自己            
             i = areaInfoService.deleteCity(id);  
        }else if(areaLevel==1){//省份 ,先删行政区，再删除城市 ,再删除自己 
            AreaInfo sInfo = new AreaInfo();
            sInfo.setId(id);
            //获取该省份的城市
            List<AreaInfo> list=areaInfoService.getParentArea(sInfo);
            if(list!=null && list.size()>0){
                for(AreaInfo areaInfo:list){
                  areaInfoService.deleteCity(areaInfo.getId());
                }
            }
            i = areaInfoService.delAreaInfo(id);    
        }
        if (i == 1) {
            map.put("flag", true);
            map.put("message", "删除成功！");
        }else {
            map.put("flag", false);
            map.put("message", "删除失败！");
        }
        return map;
    }





}
