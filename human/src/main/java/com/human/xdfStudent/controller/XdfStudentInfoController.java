package com.human.xdfStudent.controller;

import java.util.HashMap;
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
import com.human.manager.service.UserDeptService;
import com.human.utils.PageView;
import com.human.xdfStudent.entity.XdfStudentInfo;
import com.human.xdfStudent.service.XdfStudentInfoService;

/**
 * 新东方学员查询控制层
 * @author liuwei63
 */
@Controller
@RequestMapping(value="/xdfStudent/rule/")
public class XdfStudentInfoController {

    private final  Logger logger = LogManager.getLogger(XdfStudentInfoController.class);
    
    @Resource
    private XdfStudentInfoService xstService;
    
    @Resource
    private UserDeptService userDeptService;
          
    /**
     * 新东方学员信息查询首页
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/xdfStudent/list");
        return mav;
    }
    
    /**
     * 分页查询新东方学员信息
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,XdfStudentInfo cf) {
        return  xstService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/xdfStudent/add");
        return mav;  
    }
    
    /**
     * 保存新东方学员信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(XdfStudentInfo cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=xstService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存新东方学员数据失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/xdfStudent/edit");       
        XdfStudentInfo xs=xstService.selectById(id);
        mav.addObject("xs", xs);
        return mav; 
    }
    
    /**
     * 编辑新东方学员信息
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(XdfStudentInfo cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=xstService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑新东方学员数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除新东方学员信息（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除新东方学员数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=xstService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除新东方学员数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除新东方学员数据失败,请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除全部新东方学员信息（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("deleteAll")
     @ResponseBody
     public Map<String, Object> deleteAll() {
         logger.info("删除全部新东方学员数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=xstService.deleteAll();
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除全部新东方学员数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除全部新东方学员数据失败,请稍后重试!");
         }
         return map;
     }
    
     /**
      * 跳转批量导入新东方学员数据界面
      * @param cf
      * @return
      */
     @RequestMapping("batch_add")
     public ModelAndView bathAdd() {
         ModelAndView mav=new ModelAndView("/xdfStudent/batchAdd");
         return mav;  
     }
     
     
     /**
      * @description 新东方学员数据导入模板下载
      * @param request
      * @param response
      */
     @SuppressWarnings("deprecation")
     @RequestMapping(value = "downLoadXdfStudentExcel")
     public void downLoadXdfStudentExcel(HttpServletRequest request,HttpServletResponse response) {
         logger.info("新东方学员数据导入模板下载");
         try{
             xstService.downLoadXdfStudentExcel(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("新东方学员数据导入模板下载失败!", e.getMessage());
         }         
     }
     
     /**
      * @description 新东方学员数据导入
      * @param file Excel 文件
      * @return 导入结果
      */
     @RequestMapping(value ="uploadLoadXdfStudentExcel")
     @ResponseBody
     public Map<String, Object> uploadLoadXdfStudentExcel(@RequestParam("file") MultipartFile multipartFile) {
         logger.info("上传新东方学员数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=xstService.uploadLoadXdfStudentExcel(multipartFile);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("上传新东方学员数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "上传新东方学员数据失败,请稍后重试!");
         }
         return map; 
     }
          
     /**
      * 导出选择的新东方学员数据
      * @return
      */     
     @RequestMapping(value="exportSelect")
     @ResponseBody
     public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response){
         logger.info("新东方学员数据批量导出");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=xstService.exportSelect(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("新东方学员数据批量导出失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "新东方学员数据批量导出失败,请稍后重试!"); 
         }
         return map;
     }
           
    
}
