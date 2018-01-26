package com.human.continuedClass.controller;


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
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.ClassInformationService;
import com.human.continuedClass.service.SchoolAreaService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

/**
 * 续班规则导入数据控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value="/continued_class/importData/")
public class ClassInformationController {
    
    private final  Logger logger = LogManager.getLogger(ClassInformationController.class);
        
    @Resource
    private ClassInformationService cService;
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private SchoolAreaService saService;
      
    /**
     * 导入班级数据首页
     */
    @RequestMapping("index")
    public ModelAndView index(ClassInformation cf) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importData/list");
        mav.addObject("cf", cf);
        return mav;
    }
    
    /**
     * 分页查询班级数据
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,ClassInformation cf) {
        return  cService.query(pageView, cf);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(ClassInformation cf) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importData/add");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        mav.addObject("cf", cf);
        return mav;  
    }
    
    /**
     * 保存班级数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(ClassInformation cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=cService.add(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存班级数据失败!", e.getMessage());
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
    public ModelAndView toEdit(ClassInformation cf) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importData/edit");
        int type=cf.getType();
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        cf=cService.selectByPrimaryKey(cf);
        cf.setType(type);
        mav.addObject("cf", cf);
        SchoolArea sa=saService.selectByName(cf.getSchoolAreaName());
        mav.addObject("sa", sa);
        return mav; 
    }
    
    /**
     * 编辑班级数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(ClassInformation cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=cService.edit(cf);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑班级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除班级数据（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds,int type) {
        logger.info("删除班级数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=cService.delete(deleteIds,type);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除班级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除班级数据失败,请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除全部班级数据（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("deleteAll")
     @ResponseBody
     public Map<String, Object> deleteAll(ClassInformation cf) {
         logger.info("删除全部班级数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=cService.deleteAll(cf);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除全部班级数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除全部班级数据失败,请稍后重试!");
         }
         return map;
     }
    
     /**
      * 跳转批量导入班级数据界面
      * @param cf
      * @return
      */
     @RequestMapping("batch_add")
     public ModelAndView bathAdd(ClassInformation cf) {
         ModelAndView mav=new ModelAndView("/ContinuedClass/importData/batchAdd");
         mav.addObject("cf", cf);
         return mav;  
     }
     
     /**
      * @description 班级数据导入模板下载
      * @param request
      * @param response
      */
     @SuppressWarnings("deprecation")
     @RequestMapping(value = "downLoadClassExcel")
     public void downLoadClassExcel(HttpServletRequest request,HttpServletResponse response) {
         logger.info("班级数据导入模板下载");
         try{
             cService.downLoadClassExcel(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("班级数据导入模板下载失败!", e.getMessage());
         }         
     }
     
     /**
      * @description 班级数据导入
      * @param ClassInformation cf
      * @param file Excel 文件
      * @return 导入结果
      */
     @RequestMapping(value ="uploadLoadClassExcel")
     @ResponseBody
     public Map<String, Object> uploadLoadClassExcel(ClassInformation cf,@RequestParam("file") MultipartFile multipartFile) {
         logger.info("上传班级数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=cService.uploadLoadClassExcel(cf,multipartFile);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("上传班级数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "上传班级数据失败,请稍后重试!");
         }
         return map; 
     }
          
     /**
      * 导出选择的班级数据
      * @return
      */     
     @RequestMapping(value="exportSelect")
     @ResponseBody
     public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response){
         logger.info("班级数据批量导出");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=cService.exportSelect(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("班级数据批量导出失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "班级数据批量导出失败,请稍后重试!"); 
         }
         return map;
     }
     
     /**
      * 导出所有的班级数据
      * @return
      */
     @RequestMapping(value="exportAll")
     @ResponseBody
     public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
         logger.info("班级数据全部导出");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=cService.exportSelect(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("班级数据全部导出失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "班级数据全部导出失败,请稍后重试!"); 
         }
         return map;
     }
     
     /**
      * 刷新班级数据
      * @param classCodes
      * @return
      */
     @RequestMapping("refresh")
     @ResponseBody
     public Map<String, Object> refresh(String classCodes) {
         logger.info("刷新班级数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=cService.refresh(classCodes);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("刷新班级数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "刷新班级数据失败,请稍后重试!");
         }
         return map;
     }
     
     /**
      * 刷新全部班级数据
      * @param videoTypeId
      * @return
      */
      @RequestMapping("refreshAll")
      @ResponseBody
      public Map<String, Object> refreshAll(ClassInformation cf) {
          logger.info("刷新全部班级数据");
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              map=cService.refreshAll(cf);
          } catch (Exception e) {
              e.printStackTrace();
              logger.error("刷新全部班级数据失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "刷新全部班级数据失败,请稍后重试!");
          }
          return map;
      }
     
      /**
       * 通过班号获取班级学员
       * @param classCodes
       * @return
       */
      @RequestMapping("getStudentsByClassCode")
      @ResponseBody
      public Map<String, Object> getStudentsByClassCode(String classCodes,Long ruleId) {
          logger.info("通过班号获取班级学员");
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              map=cService.getStudentsByClassCode(classCodes,ruleId);
          } catch (Exception e) {
              e.printStackTrace();
              logger.error("通过班号获取班级学员失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "通过班号获取班级学员失败,请稍后重试!");
          }
          return map;
      }
      
      /**
       * 获取全部班级学员
       * @param classCodes
       * @return
       */
      @RequestMapping("getAllStudents")
      @ResponseBody
      public Map<String, Object> getAllStudents(ClassInformation cf) {
          logger.info("获取全部班级学员");
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              map=cService.getAllStudents(cf);
          } catch (Exception e) {
              e.printStackTrace();
              logger.error("获取全部班级学员失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "获取全部班级学员失败,请稍后重试!");
          }
          return map;
      }
      
      /**
       * 删除全部学员（物理删除）
       * @param videoTypeId
       * @return
       */
       @RequestMapping("delAllStudents")
       @ResponseBody
       public Map<String, Object> delAllStudents(ClassInformation cf) {
           logger.info("删除全部学员数据");
           Map<String, Object> map = new HashMap<String, Object>();
           try {
               map=cService.delAllStudents(cf);
           } catch (Exception e) {
               e.printStackTrace();
               logger.error("删除全部学员数据失败!", e.getMessage());
               map.put("flag", false);
               map.put("message", "删除全部学员数据失败,请稍后重试!");
           }
           return map;
       }
       
       /**
        * 跳转按开课日期过滤学员界面
        * @param cf
        * @return
        */
       @RequestMapping("filterStudentsByDate")
       public ModelAndView filterStudentsByDate(ClassInformation cf) {
           ModelAndView mav=new ModelAndView("/ContinuedClass/importData/filterStudentsByDate");
           mav.addObject("cf", cf);
           return mav;  
       }
       
       /**
        * 按开课日期获取全部学员
        * @param classCodes
        * @return
        */
       @RequestMapping("getAllStudentsByDate")
       @ResponseBody
       public Map<String, Object> getAllStudentsByDate(ClassInformation cf) {
           logger.info("按开课日期获取全部学员");
           Map<String, Object> map = new HashMap<String, Object>();
           try {
               map=cService.getAllStudentsByDate(cf);
           } catch (Exception e) {
               e.printStackTrace();
               logger.error("按开课日期获取全部学员失败!", e.getMessage());
               map.put("flag", false);
               map.put("message", "按开课日期获取全部学员失败,请稍后重试!");
           }
           return map;
       }
}
