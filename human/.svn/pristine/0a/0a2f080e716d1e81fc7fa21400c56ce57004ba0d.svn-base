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
import com.human.continuedClass.entity.CombinationClass;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.ClassInformationService;
import com.human.continuedClass.service.CombinationClassService;
import com.human.continuedClass.service.SchoolAreaService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.utils.Common;
import com.human.utils.PageView;

/**
 * 套餐组合导入数据控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value="/continued_class/importCombinationData/")
public class CombinationClassController {
    
    private final  Logger logger = LogManager.getLogger(CombinationClassController.class);
        
    @Resource
    private ClassInformationService cService;
    
    @Resource
    private UserDeptService userDeptService;
    
    @Resource
    private SchoolAreaService saService;
    
    @Resource
    private CombinationClassService ccService;
    
      
    /**
     * 导入套餐数据首页
     */
    @RequestMapping("index")
    public ModelAndView index(CombinationClass cc) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importCombinationData/list");
        mav.addObject("cc", cc);
        return mav;
    }
    
    /**
     * 分页查询套餐数据
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,CombinationClass cc) {
        return  ccService.query(pageView, cc);
    }
    
    /**
     * 跳转新增界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(CombinationClass cc) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importCombinationData/add");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        mav.addObject("cc", cc);
        return mav;  
    }
    
    /**
     * 保存套餐数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(CombinationClass cc) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccService.add(cc);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存套餐数据失败!", e.getMessage());
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
    public ModelAndView toEdit(CombinationClass cc) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/importCombinationData/edit");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        cc=ccService.selectByPrimaryKey(cc);
        mav.addObject("cc", cc);
        SchoolArea sa=saService.selectByName(cc.getSchoolAreaName());
        mav.addObject("sa", sa);
        return mav; 
    }
    
    /**
     * 编辑套餐数据
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(CombinationClass cc) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccService.edit(cc);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑套餐数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
   /**
    * 删除套餐数据（物理删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String deleteIds) {
        logger.info("删除套餐数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=ccService.delete(deleteIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除套餐数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "删除套餐数据失败,请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除全部套餐数据（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("deleteAll")
     @ResponseBody
     public Map<String, Object> deleteAll(CombinationClass cc) {
         logger.info("删除全部套餐数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=ccService.deleteAll(cc);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除全部套餐数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除全部套餐数据失败,请稍后重试!");
         }
         return map;
     }
    
     /**
      * 跳转批量导入套餐数据界面
      * @param cf
      * @return
      */
     @RequestMapping("batch_add")
     public ModelAndView bathAdd(CombinationClass cc) {
         ModelAndView mav=new ModelAndView("/ContinuedClass/importCombinationData/batchAdd");
         mav.addObject("cc", cc);
         return mav;  
     }
     
     /**
      * @description 套餐数据导入模板下载
      * @param request
      * @param response
      */
     @SuppressWarnings("deprecation")
     @RequestMapping(value = "downLoadClassExcel")
     public void downLoadClassExcel(HttpServletRequest request,HttpServletResponse response) {
         logger.info("套餐数据导入模板下载");
         try{
             ccService.downLoadClassExcel(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("套餐数据导入模板下载失败!", e.getMessage());
         }         
     }
     
     /**
      * @description 套餐数据导入
      * @param ClassInformation cf
      * @param file Excel 文件
      * @return 导入结果
      */
     @RequestMapping(value ="uploadLoadClassExcel")
     @ResponseBody
     public Map<String, Object> uploadLoadClassExcel(CombinationClass cc,@RequestParam("file") MultipartFile multipartFile) {
         logger.info("上传套餐数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=ccService.uploadLoadClassExcel(cc,multipartFile);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("上传套餐数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "上传套餐数据失败,请稍后重试!");
         }
         return map; 
     }
          
     /**
      * 导出选择的套餐数据
      * @return
      */     
     @RequestMapping(value="exportSelect")
     @ResponseBody
     public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response){
         logger.info("套餐数据批量导出");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=ccService.exportSelect(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("套餐数据批量导出失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "套餐数据批量导出失败,请稍后重试!"); 
         }
         return map;
     }
     
     /**
      * 导出所有的套餐数据
      * @return
      */
     @RequestMapping(value="exportAll")
     @ResponseBody
     public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
         logger.info("套餐数据全部导出");
         Map<String, Object> map = new HashMap<String, Object>();
         try{
             map=ccService.exportSelect(request, response);
         }catch(Exception e){
             e.printStackTrace();
             logger.error("套餐数据全部导出失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "套餐数据全部导出失败,请稍后重试!"); 
         }
         return map;
     }
 
}
