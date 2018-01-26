package com.ls.spt.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.AreaInfo;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.entity.School;
import com.ls.spt.basic.service.AreaInfoService;
import com.ls.spt.basic.service.SchoolService;

@Controller
@RequestMapping(value="/basic/school/")

public class SchoolController {
    
 private final  Logger logger = LogManager.getLogger(SchoolController.class);
        
    @Resource
    private SchoolService  schoolService;
        
    @Resource
    private AreaInfoService areaInfoService;
    
                  
    /**
     * 公立学校信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/basic/school/list");
        //查询省份
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
    
    /**
     * 分页查询公立学校信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,School cf) {
        return  schoolService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {        
        ModelAndView mav=new ModelAndView("/basic/school/add");
        //查询省份
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav;  
    }
    
    /**
     * 保存公立学校信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(School cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=schoolService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存公立学校数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(long id) {
        ModelAndView mav=new ModelAndView("/basic/school/edit");               
        School school=schoolService.selectById(id);
        mav.addObject("school", school);
        //查询省份
        AreaInfo area = new AreaInfo();
        area.setAreaLevel(1);
        List<AreaInfo> areaInfo = areaInfoService.getArea(area);
        mav.addObject("areaInfo", areaInfo);
        return mav; 
    }
    
    /**
     * 编辑公立学校信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(School cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=schoolService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑公立学校数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 禁用/启用公立学校信息
    * @param videoTypeId
    * @param status
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds,Integer status) {
        logger.info("操作公立学校数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=schoolService.delete(deleteIds,status);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("操作公立学校数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "操作公立学校数据失败,请稍后重试!");
        }
        return map;
    }

    
     /**
      * 跳转批量导入公立学校数据界面
      * @param cf
      * @return
      */
     @RequestMapping("batch_add")
     public ModelAndView bathAdd() {
         ModelAndView mav=new ModelAndView("/basic/school/batchAdd");
         //查询省份
         AreaInfo area = new AreaInfo();
         area.setAreaLevel(1);
         List<AreaInfo> areaInfo = areaInfoService.getArea(area);
         mav.addObject("areaInfo", areaInfo);
         return mav;  
     }
     
     
     /**
      * @description 公立学校数据导入模板下载
      * @param request
      * @param response
      */
     @RequestMapping(value = "downLoadSchoolExcel")
     public void downLoadSchoolExcel(HttpServletRequest request,HttpServletResponse response) {
         logger.info("公立学校数据导入模板下载");
         try{
             schoolService.downLoadSchoolExcel(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("公立学校数据导入模板下载失败!", e.getMessage());
         }         
     }
     
     /**
      * @description 公立学校数据导入
      * @param file Excel 文件
      * @return 导入结果
      */
     @RequestMapping(value ="uploadLoadSchoolExcel")
     @ResponseBody
     public Map<String, Object> uploadLoadSchoolExcel(@RequestParam("file") MultipartFile multipartFile,School cf) {
         logger.info("上传公立学校数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=schoolService.uploadLoadSchoolExcel(multipartFile,cf);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("上传公立学校数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "上传公立学校数据失败,请稍后重试!");
         }
         return map; 
     }
     

     /**
      * 根据条件查询公立学校
      * @param cf
      * @return
      */
     @RequestMapping(value ="getSchoolByParam")
     @ResponseBody
     public List<School> getSchoolByParam(School cf) {
         List<School> list =schoolService.getSchoolByParam(cf);
         return list; 
     }
     
}
