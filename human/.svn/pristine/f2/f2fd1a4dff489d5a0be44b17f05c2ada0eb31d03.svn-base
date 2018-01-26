package com.human.continuedClass.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.RecommendClass;
import com.human.continuedClass.entity.RuleBackPhoto;
import com.human.continuedClass.service.ClassMatchService;
import com.human.utils.FileUtil;
import com.human.utils.PageView;
import com.human.utils.QRCodeUtil;

/**
 * 续班规则匹配数据控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value="/continued_class/matchData/")
public class ClassMatchController {
    
    private final  Logger logger = LogManager.getLogger(ClassMatchController.class);
    
    @Resource
    private ClassMatchService cmService;
    
    @Value("${cc.schoolId}")
    private int schoolId;
    
    @Value("${cc.cardUrl}")
    private String cardUrl;
    
     
    /**
     * 班级匹配数据首页
     */
    @RequestMapping("index")
    public ModelAndView index(long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/matchData/list");
        mav.addObject("ruleId", ruleId);
        return mav;
    }
    
    /**
     * 分页查询班级匹配数据
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,ClassMatch cm) {
        return  cmService.query(pageView, cm);
        
    }
    
    /**
     * 跳转上传续班卡背景图界面
     * @param ruleId  规则Id
     * @return
     */
    @RequestMapping("uploadBackPhoto")
    public ModelAndView uploadBackPhoto(Long ruleId) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/matchData/uploadBackPhoto");
        mav.addObject("ruleId", ruleId);
        return mav;  
    }
    
    /**
     * @description 保存续班卡背景图
     * @param RuleBackPhoto rbp
     * @param file Excel 文件
     * @return 导入结果
     */
    @RequestMapping(value ="saveBackPhoto")
    @ResponseBody
    public Map<String, Object> saveBackPhoto(RuleBackPhoto rbp,@RequestParam("file") MultipartFile multipartFile) {
        logger.info("上传续班卡背景图");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=cmService.saveBackPhoto(rbp,multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传续班卡背景图失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "上传续班卡背景图失败,请稍后重试!");
        }
        return map; 
    }
    
    /**
     * 生成学员续班数据
     * @return
     */
    @RequestMapping(value="saveStudentsToClass")
    @ResponseBody
    public Map<String, Object> saveStudentsToClass(ClassMatch cm){
        logger.info("生成学员续班数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.saveStudentsToClass(cm);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("生成学员续班失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "生成学员续班失败,失败原因:"+e.getMessage()); 
        }
        return map;
    }

    /**
     * 生成学员推荐班级数据
     * @return
     */
    @RequestMapping(value="saveRecommendClass")
    @ResponseBody
    public Map<String, Object> saveRecommendClass(ClassMatch cm){
        logger.info("生成学员推荐班数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.saveRecommendClass(cm);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("生成学员推荐班失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "生成学员推荐班失败,失败原因:"+e.getMessage()); 
        }
        return map;
    }
            
    /**
     * 导出班级-续班班级数据
     * @return
     */
    @RequestMapping(value="exportClassToClass")
    @ResponseBody
    public Map<String, Object> exportClassToClass(HttpServletRequest request, HttpServletResponse response){
        logger.info("班级-续班班级数据导出");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.exportClassToClass(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("导出班级-续班班级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出班级续班-班级数据失败,请稍后重试!"); 
        }
        return map;
    }
    
    /**
     * 导出班级-推荐班级数据
     * @return
     */
    @RequestMapping(value="exportClassToRcClass")
    @ResponseBody
    public Map<String, Object> exportClassToRcClass(HttpServletRequest request, HttpServletResponse response){
        logger.info("班级-推荐班级数据导出");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.exportClassToRcClass(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("班级-推荐班级数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出班级-推荐班级数据失败,请稍后重试!"); 
        }
        return map;
    }
    
    /**
     * 导出班级-升班班级数据
     * @return
     */
    @RequestMapping(value="exportClassToUpClass")
    @ResponseBody
    public Map<String, Object> exportClassToUpClass(HttpServletRequest request, HttpServletResponse response){
        logger.info("班级-升班班级数据导出");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.exportClassToUpClass(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("班级-升班班级数据导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "导出班级-升班班级数据失败,请稍后重试!"); 
        }
        return map;
    }
    
    
    /**
     * 导出学员-班级数据
     * @return
     */
    @RequestMapping(value="exportStudentsToClass")
    @ResponseBody
    public Map<String, Object> exportStudentsToClass(HttpServletRequest request, HttpServletResponse response){
        logger.info("学员-班级数据导出");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=cmService.exportStudentsToClass(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("学员-班级数据导出失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "学员-班级数据导出失败,请稍后重试!"); 
        }
        return map;
    }
    
    /**
     * 学员-班级数据明细
     */
    @RequestMapping("searchClassDetails")
    public ModelAndView searchClassDetails(ClassMatch cm) {
        ModelAndView mav=new ModelAndView("/ContinuedClass/matchData/classDetails_list");
        mav.addObject("cm", cm);
        return mav;
    }
    
    /**
     * 分页查询学员-班级明细
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("queryClassDetails")
    @ResponseBody
    public PageView queryClassDetails(PageView pageView,ClassMatch cm) {
        return  cmService.queryClassDetails(pageView, cm);
        
    }
    
    /**
     * 跳转新增学员-班级配课界面
     * @param cf
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(ClassMatch cm){
        ModelAndView mav=new ModelAndView("/ContinuedClass/matchData/toAdd");
        mav.addObject("cm", cm);
        return mav;
    }
    
    /**
     * 保存学员-班级配课
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("addStudentToClass")
    @ResponseBody
    public Map<String, Object> addStudentToClass(ClassMatch cm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=cmService.addStudentToClass(cm);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存学员-班级配课失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "保存学员-班级配课失败，请稍后重试!");
        }
        return map;
    }
       
    
    /**
     * 删除学员-班级数据（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("delete")
     @ResponseBody
     public Map<String, Object> delete(String deleteIds) {
         logger.info("删除学员-班级数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=cmService.delete(deleteIds);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除学员-班级数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除学员-班级数据失败,请稍后重试!");
         }
         return map;
     }
     
     /**
      * 分页查询学员-推荐班级明细
      * @param managerUser
      * @param pageView
      * @return
      */
     @RequestMapping("queryRecommendClass")
     @ResponseBody
     public PageView queryRecommendClass(PageView pageView,RecommendClass rc) {
         return  cmService.queryRecommendClass(pageView, rc);
         
     }
     
     /**
      * 跳转新增学员-推荐班级界面
      * @param cf
      * @return
      */
     @RequestMapping("toAddRc")
     public ModelAndView toAdd(RecommendClass rc){
         ModelAndView mav=new ModelAndView("/ContinuedClass/matchData/toAddRc");
         mav.addObject("rc", rc);
         return mav;
     }
     
     /**
      * 保存学员-推荐班级
      * @param model
      * @param videoType
      * @return
      */
     @RequestMapping("addRecommendClass")
     @ResponseBody
     public Map<String, Object> addRecommendClass(RecommendClass rc) {
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=cmService.addRecommendClass(rc);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("保存学员-推荐班级失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "保存学员-推荐班级失败，请稍后重试!");
         }
         return map;
     }
     /**
      * 删除学员-推荐班级数据（物理删除）
      * @param videoTypeId
      * @return
      */
      @RequestMapping("deleteRc")
      @ResponseBody
      public Map<String, Object> deleteRc(String deleteIds) {
          logger.info("删除学员-推荐班级数据");
          Map<String, Object> map = new HashMap<String, Object>();
          try {
              map=cmService.deleteRc(deleteIds);
          } catch (Exception e) {
              e.printStackTrace();
              logger.error("删除学员-推荐班级数据失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "删除学员-推荐班级数据失败,请稍后重试!");
          }
          return map;
      }
      
      /**
       * 导出学员-配课数据
       * @return
       */
      @RequestMapping(value="exportStudentsToCourse")
      @ResponseBody
      public Map<String, Object> exportStudentsToCourse(HttpServletRequest request, HttpServletResponse response){
          logger.info("学员-配课数据导出");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.exportStudentsToCourse(request,response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("学员-配课数据导出失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "学员-配课数据导出失败,请稍后重试!"); 
          }
          return map;
      }
      
      
      /**
       * 批量导出续班卡
       * @return
       */
      @RequestMapping(value="bath_exportCardPdf")
      @ResponseBody
      public Map<String, Object> bath_exportCardPdf(HttpServletRequest request, HttpServletResponse response){
          logger.info("批量导出续班卡");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.bath_exportCardPdf(request,response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("批量导出续班卡失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "批量导出续班卡失败,请稍后重试!"); 
          }
          return map;
      }
            
      /**
       * 导出全部续班卡
       * @return
       */
      @RequestMapping(value="exportAllCard")
      @ResponseBody
      public Map<String, Object> exportAllCard(HttpServletRequest request, HttpServletResponse response){
          logger.info("导出全部续班卡");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.exportAllCard(request,response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("导出全部续班卡失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "导出全部续班卡失败,请稍后重试!"); 
          }
          return map;
      }
      
      
      /**
       * 判断是否已经上传续班卡背景图
       * @return
       */
      @RequestMapping(value="checkHasBackPhoto")
      @ResponseBody
      public Map<String, Object> checkHasBackPhoto(long ruleId){
          logger.info("判断是否已经上传续班卡背景图");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.checkHasBackPhoto(ruleId);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("判断是否已经上传续班卡背景图失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "判断是否已经上传续班卡背景图失败,请稍后重试!"); 
          }
          return map;
      }
      
      /**
       * 发送续班卡邮件
       * @return
       */
      @RequestMapping(value="sendCardMail")
      @ResponseBody
      public Map<String, Object> sendCardMail(HttpServletRequest request, HttpServletResponse response){
          logger.info("发送续班卡邮件");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.sendCardMail(request,response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("发送续班卡邮件失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "发送续班卡邮件失败,请稍后重试!"); 
          }
          return map;
      }
      
      /**
       * 补发发送（失败的续班卡）邮件
       * @return
       */
      @RequestMapping(value="sendFailCardMail")
      @ResponseBody
      public Map<String, Object> sendFailCardMail(HttpServletRequest request, HttpServletResponse response){
          logger.info("补发续班卡邮件");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.sendFailCardMail(request,response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("补发续班卡邮件失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "补发续班卡邮件失败,请稍后重试!"); 
          }
          return map;
      }
      
      
      
      
      /**
       * 导出选择的学员-推荐班级
       * @return
       */     
      @RequestMapping(value="bath_exportRc")
      @ResponseBody
      public Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response){
          logger.info("学员-推荐班级批量导出");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.exportSelect(request, response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("学员-推荐班级批量导出失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "学员-推荐班级批量导出失败,请稍后重试!"); 
          }
          return map;
      }
      
      /**
       * 导出所有的学员-推荐班级
       * @return
       */
      @RequestMapping(value="exportAllRc")
      @ResponseBody
      public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
          logger.info("学员-推荐班级全部导出");
          Map<String, Object> map = new HashMap<String, Object>();
          try{
              map=cmService.exportSelect(request, response);
          }catch(Exception e){
              e.printStackTrace();
              logger.error("学员-推荐班级全部导出失败!", e.getMessage());
              map.put("flag", false);
              map.put("message", "学员-推荐班级全部导出失败,请稍后重试!"); 
          }
          return map;
      }
      
                  
      /**
       * 导出学员二维码
       * @param request
       * @param response
       * @param codes
       */
      @RequestMapping(value = "downLoadQrcode")
      public void downLoadQrcode(HttpServletRequest request,HttpServletResponse response,String codes){
          logger.info("下载二维码");
          String zipFile = request.getSession().getServletContext().getRealPath("/static/temp") + "/学员二维码" + String.valueOf(System.currentTimeMillis())+".zip";
          String newFilePath = request.getSession().getServletContext().getRealPath("/static/temp") + "/学员二维码" + String.valueOf(System.currentTimeMillis());
          OutputStream out = null;  
          OutputStream ros = null;
          try {
              File directroy = new File(newFilePath);
              if (!directroy.exists() && !directroy.isDirectory()) {
                  directroy.mkdir();
              }
              String[] codeArr = codes.split(",");
              for(String code :codeArr){
                  String xyCode = code.split("-")[0];
                  out = new FileOutputStream(new File(newFilePath+"/"+code+".png"));
                  String code_url=cardUrl+"?student_code="+xyCode+"&school_id=25&wx_ext=xbk";//二维码链接;
                  byte[]buffer=new byte[1024];
                  BufferedImage bufferedImage=QRCodeUtil.createImage(code_url);
                  InputStream fis = FileUtil.getImageStream(bufferedImage);
                  int len; //读入需要下载的文件的内容，打包到zip文件
                  while((len=fis.read(buffer))>0){
                      out.write(buffer,0,len); 
                  } 
                  out.flush();
                  out.close();
                  fis.close();
              }
              FileUtil.fileToZip(newFilePath, zipFile);
              response.setContentType("multipart/form-data");
              response.setHeader("Content-Disposition",
                      "attachment;fileName=" + new String(("学员二维码.zip").getBytes("gbk"), "iso-8859-1"));
              InputStream inputStream = new FileInputStream(zipFile);
              ros = response.getOutputStream();
              int size = (int) zipFile.length();
              byte[] b = new byte[size];
              int length;
              while ((length = inputStream.read(b)) > 0) {
                  ros.write(b, 0, length);
              }
              ros.close();
              inputStream.close();
          }catch (Exception e) {
              e.printStackTrace();
          }finally {
              FileUtil.deletefile(newFilePath);
              FileUtil.deletefile(zipFile);
          }
      }
}
