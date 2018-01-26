package com.human.recruitment.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.zxing.WriterException;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.UserDeptService;
import com.human.recruitment.entity.SchoolRecruitment;
import com.human.recruitment.service.SchoolRecruitmentService;
import com.human.utils.Common;
import com.human.utils.MatrixToImageWriter;
import com.human.utils.PageView;
import com.human.utils.QRCodeUtil;

/**
 * 校招/活动管理
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/recruit/schoolRecruitmentManager/")
public class SchoolRecruitmentController {
    
    private final  Logger logger = LogManager.getLogger(SchoolRecruitmentController.class);
    
    @Resource
    private UserDeptService userDeptService;
       
    @Resource
    private SchoolRecruitmentService srService;
    
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/recruitment/schoolRecruitmentManager/list");
        //获取当前登录人能看到的机构
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;
    }
    
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,SchoolRecruitment sr){        
        return srService.query(pageView, sr);  
    }
    
    /**
     * 跳转新增界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/recruitment/schoolRecruitmentManager/add");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        return mav;  
    }
    
    /**
     * 保存校园招聘活动
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> add(SchoolRecruitment sr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=srService.add(sr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存校园招聘活动失败!", e.getMessage());
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
        ModelAndView mav=new ModelAndView("/recruitment/schoolRecruitmentManager/edit");
        List<HrCompany> companyList=userDeptService.getUserCompany(Common.getMyUser().getUserid());
        mav.addObject("companyList", companyList);
        SchoolRecruitment sr=srService.selectByPrimaryKey(id);
        mav.addObject("sr", sr);
        return mav; 
    }
    
    /**
     * 编辑校招活动
     * @param model
     * @param videoType
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(SchoolRecruitment sr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=srService.edit(sr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑校招活动失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
        }
        return map;
    }
    
    /**
     * 删除校招活动（物理删除）
     * @param videoTypeId
     * @return
     */
     @RequestMapping("delete")
     @ResponseBody
     public Map<String, Object> delete(String deleteIds) {
         logger.info("删除校招活动数据");
         Map<String, Object> map = new HashMap<String, Object>();
         try {
             map=srService.delete(deleteIds);
         } catch (Exception e) {
             e.printStackTrace();
             logger.error("删除校招活动数据失败!", e.getMessage());
             map.put("flag", false);
             map.put("message", "删除校招活动数据失败,请稍后重试!");
         }
         return map;
     }
              
     @ResponseBody
     @RequestMapping(value="getQrcode")
     public void payByQRCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
         logger.info("生成二维码");
         try{
             String code_url=request.getParameter("qrcodeUrl");//二维码链接;
             BufferedImage bufferedImage=QRCodeUtil.createImage(code_url);
             //生成二维码QRCode图片  
             ImageIO.write(bufferedImage, "jpg", response.getOutputStream());    
         }catch(Exception e){
             e.printStackTrace();
         } 
     }
     
     /**
      * 下载校招活动二维码
      * @param request
      * @param response
      * @param qrcodeUrl
      */
     @RequestMapping(value = "downLoadQrcode")
     public void downLoadQrcode(HttpServletRequest request,HttpServletResponse response,String qrcodeUrl){
         logger.info("下载二维码");
         InputStream is = null;
         OutputStream ros = null;
         try {
             BufferedImage image = MatrixToImageWriter.getBufferedImage(qrcodeUrl, 180, 180);
             response.setContentType("multipart/form-data");
             response.setHeader("Content-Disposition", "attachment;fileName=" + new String("校招活动招聘链接.jpg".getBytes("gbk"), "iso-8859-1"));
             ByteArrayOutputStream os = new ByteArrayOutputStream();  
             ImageIO.write(image, "jpg", os);  
             is = new ByteArrayInputStream(os.toByteArray()); 
             ros = response.getOutputStream();
             byte[] bytes = new byte[1024];
             int len = 0;
             while ((len = is.read(bytes)) > 0) {
                 ros.write(bytes, 0, len);
             }
         } catch (WriterException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }finally {
             try {
                 is.close();
                 ros.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             
         }
     }
     
     /**
      * 导出选择的校招活动
      * @return
      */
     @ResponseBody
     @RequestMapping(value="exportSelect")
     public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
         logger.info("校招活动导出(选择项)");
         return  srService.exportSelect(ids, request, response);
     }
     
     /**
      * 校招活动本页导出数据处理
      * @param pageView
      * @param interviewEntity
      * @param request
      * @param response
      * @return 
      */
     @RequestMapping(value ="exportThisPage")
     @ResponseBody
     public Map<String, Object> exportThisPage(PageView pageView,SchoolRecruitment sr,HttpServletRequest request, HttpServletResponse response){
         logger.info("校招活动导出(本页)");
         return srService.exportThisPage(pageView, sr, request, response);
     }
     
     /**
      * 导出所有的校招活动
      * @return
      */
     @ResponseBody
     @RequestMapping(value="exportAll")
     public Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response){
         logger.info("校招活动导出(全部)");
         return  srService.exportAll(request, response);
     }  
}
