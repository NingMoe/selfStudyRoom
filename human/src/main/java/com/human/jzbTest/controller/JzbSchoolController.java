package com.human.jzbTest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.entity.AreaInfo;
import com.human.basic.service.AreaInfoService;
import com.human.jzbTest.entity.JzbSchool;
import com.human.jzbTest.service.JzbSchoolService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/basic/jzbSchool/")

public class JzbSchoolController {
    
 private final  Logger logger = LogManager.getLogger(JzbSchoolController.class);
        
    @Resource
    private JzbSchoolService jzbService;
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private AreaInfoService areaInfoService;
                  
    /**
     * 尖子班公立学校信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbSchool/list");
        //获取当前登录人所在的新东方学校
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());   
        List<String>hrCompanyIds=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(companyList)){
            for(HrCompany hc:companyList){
                hrCompanyIds.add(hc.getCompanyId());
            }
        }
        //获取当前登录人所在的新东方学校省份
        Map<Object, Object> map = new HashMap<Object, Object>();
        if(CollectionUtils.isNotEmpty(hrCompanyIds)){
            map.put("s", hrCompanyIds);
        }
        List<AreaInfo> areaInfo = areaInfoService.getAreaInfoByHrCompanyId(map);
        mav.addObject("areaInfo", areaInfo);
        return mav;
    }
    
    /**
     * 分页查询尖子班公立学校信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,JzbSchool cf) {
        return  jzbService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/jzbTest/jzbSchool/add");
        //获取当前登录人所在的新东方学校
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());   
        List<String>hrCompanyIds=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(companyList)){
            for(HrCompany hc:companyList){
                hrCompanyIds.add(hc.getCompanyId());
            }
        }
        //获取当前登录人所在的新东方学校省份
        Map<Object, Object> map = new HashMap<Object, Object>();
        if(CollectionUtils.isNotEmpty(hrCompanyIds)){
            map.put("s", hrCompanyIds);
        }
        List<AreaInfo> areaInfo = areaInfoService.getAreaInfoByHrCompanyId(map);
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
    public Map<String, Object> add(JzbSchool cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbService.add(cf);
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
        ModelAndView mav=new ModelAndView("/jzbTest/jzbSchool/edit"); 
        //获取当前登录人所在的新东方学校
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());   
        List<String>hrCompanyIds=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(companyList)){
            for(HrCompany hc:companyList){
                hrCompanyIds.add(hc.getCompanyId());
            }
        }
        //获取当前登录人所在的新东方学校省份
        Map<Object, Object> map = new HashMap<Object, Object>();
        if(CollectionUtils.isNotEmpty(hrCompanyIds)){
            map.put("s", hrCompanyIds);
        }
        List<AreaInfo> areaInfo = areaInfoService.getAreaInfoByHrCompanyId(map);
        mav.addObject("areaInfo", areaInfo);                
        JzbSchool jzbSchool=jzbService.selectById(id);
        mav.addObject("jzbSchool", jzbSchool);
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
    public Map<String, Object> edit(JzbSchool cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑公立学校数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除公立学校信息（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除公立学校数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=jzbService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除公立学校数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除公立学校数据失败,请稍后重试!");
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
         ModelAndView mav=new ModelAndView("/jzbTest/jzbSchool/batchAdd");
         return mav;  
     }
     
     
     /**
      * @description 公立学校数据导入模板下载
      * @param request
      * @param response
      */
     @SuppressWarnings("deprecation")
     @RequestMapping(value = "downLoadXdfStudentExcel")
     public void downLoadJzbSchoolExcel(HttpServletRequest request,HttpServletResponse response) {
         logger.info("公立学校数据导入模板下载");
         try{
             jzbService.downLoadJzbSchoolExcel(request, response);
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
     @RequestMapping(value ="uploadLoadJzbSchoolExcel")
     @ResponseBody
     public Map<String, Object> uploadLoadJzbSchoolExcel(@RequestParam("file") MultipartFile multipartFile) {
         logger.info("上传公立学校数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=jzbService.uploadLoadJzbSchoolExcel(multipartFile);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("上传公立学校数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "上传公立学校数据失败,请稍后重试!");
         }
         return map; 
     }
          
}
