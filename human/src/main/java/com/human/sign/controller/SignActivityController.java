package com.human.sign.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.google.zxing.WriterException;
import com.human.basic.entity.MailParam;
import com.human.basic.service.MailTempService;
import com.human.sign.entity.SignActivity;
import com.human.sign.entity.SignInfo;
import com.human.sign.service.SignActivityService;
import com.human.sign.service.SignInfoService;
import com.human.utils.MatrixToImageWriter;
import com.human.utils.PageView;
import com.human.utils.QRCodeUtil;

@Controller
@RequestMapping("/sign/activity/")
public class SignActivityController {
    
    private final Logger logger = LogManager.getLogger(SignActivityController.class);
    
    @Resource
    private SignActivityService  saService;
    
    @Resource
    private MailTempService mailTempService;
    
    @Resource
    private SignInfoService signInfoService;
    
    /**
     * 签到列表页
     * @return
     */
    @RequestMapping("index")
    public ModelAndView toIndex() {
        ModelAndView mav=new ModelAndView("/sign/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @param info
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView queryActivityByPage(PageView pageView,SignActivity info) {
        logger.info("分页查询---");
        return  saService.queryActivityByPage(pageView,info);
    }
    
    /**
     * 跳转新增界面
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/sign/add");       
        return mav;
    }
    
    /**
     * 保存新增签名活动
     * @param info
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String, Object> addActivity(SignActivity info) {
        logger.info("保存新增签名活动");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=saService.addActivity(info);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("新增签名活动失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "新增签名活动失败！");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param id
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav = new ModelAndView("/sign/edit");
        SignActivity info=saService.selectByPrimaryKey(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime=sdf.format(info.getStartTime());
        String endTime=sdf.format(info.getEndTime());
        mav.addObject("info", info);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);       
        return mav;
    }
    
    /**
     * 编辑签名活动
     * @param info
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String, Object> editActivity(SignActivity info) {
        logger.info("编辑签名活动");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=saService.editActivity(info);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑签名活动失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "编辑签名活动失败！");
        }
        return map;
    }
    
    /**
     * 生成二维码
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="getQrcode")
    public void signByQRCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
     * 下载签到活动二维码
     * @param request
     * @param response
     * @param qrcodeUrl
     */
    @RequestMapping(value = "downLoadQrcode")
    public void downLoadQrcode(HttpServletRequest request,HttpServletResponse response,String qrcodeUrl){
        logger.info("下载签到活动二维码");
        InputStream is = null;
        OutputStream ros = null;
        try {
            BufferedImage image = MatrixToImageWriter.getBufferedImage(qrcodeUrl, 180, 180);
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String("签到链接.jpg".getBytes("gbk"), "iso-8859-1"));
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
     * 编辑短信模板
     * @param id
     * @return
     */
    @RequestMapping("toEditSendMessage")
    public ModelAndView toEditSendMessage(Long id) {
        ModelAndView mav=new ModelAndView("/sign/editSendMessage");
        SignActivity info=saService.selectByPrimaryKey(id);
        MailParam mp=new MailParam();
        mp.setState(0);
        mp.setType(2);
        List<MailParam> smsParam=mailTempService.queryParam(mp);
        mav.addObject("smsParam", smsParam);
        mav.addObject("info", info);
        return mav;
    }
    
    /**
     * 签到明细列表页
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("toSignInfoList")
    public ModelAndView toSignInfoList(Long id) {
        ModelAndView mav=new ModelAndView("/sign/signInfoDetails");
        mav.addObject("activityId",id);
        return mav;
    }
    
    /**
     * 分页查询签到明细
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("querySignInfoDetails")
    @ResponseBody
    public PageView querySignInfoDetailsByPage(PageView pageView,SignInfo info) {
        logger.info("分页查询签到明细---");
        return signInfoService.querySignInfoByPage(pageView,info);
    }
    
    /**
     * 跳转新增人员界面
     * @return
     */
    @RequestMapping("toAddInfo")
    public ModelAndView toAddInfo(Long activityId) {
        ModelAndView mav=new ModelAndView("/sign/addInfo");  
        mav.addObject("activityId", activityId);
        return mav;
    }
    
    /**
     * 保存新增签名人员
     * @param info
     * @return
     */
    @RequestMapping(value="addInfo")
    @ResponseBody
    public Map<String, Object> addSignInfo(SignInfo info) {
        logger.info("保存新增签名人员");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=signInfoService.addSignInfo(info);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("新增签名人员失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "新增签名人员失败！");
        }
        return map;
    }
    
    /**
     * 进入编辑人员界面
     * @param id
     * @return
     */
    @RequestMapping("toEditInfo")
    public ModelAndView toEditInfo(Long id) {
        ModelAndView mav = new ModelAndView("/sign/editInfo");
        SignInfo info=signInfoService.selectByPrimaryKey(id);
        String signTime="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(info.getSignTime()!=null) {
            signTime=sdf.format(info.getSignTime()); 
        }
        mav.addObject("info", info);
        mav.addObject("signTime", signTime);       
        return mav;
    }
    
    /**
     * 编辑签名人员
     * @param info
     * @return
     */
    @RequestMapping(value="editInfo")
    @ResponseBody
    public Map<String, Object>editInfo(SignInfo info) {
        logger.info("编辑签名人员");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>();
        try {            
            map=signInfoService.editSignInfo(info);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑签名人员失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "编辑签名人员失败！");
        }
        return map;
    }
    
   /**
    * 删除签到人员（逻辑删除）
    * @param videoTypeId
    * @return
    */
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(String deleteIds) {
        logger.info("删除签到人员");
        return signInfoService.updateStatus(deleteIds);
    }
    
    /**
     * 跳转批量导入人员数据界面
     * @param cf
     * @return
     */
    @RequestMapping("importData")
    public ModelAndView importData(Long activityId) {
        ModelAndView mav=new ModelAndView("/sign/batchAdd");
        mav.addObject("activityId", activityId);
        return mav;  
    }
    
    /**
     * @description 签到人员数据导入模板下载
     * @param request
     * @param response
     */
    @RequestMapping(value = "downLoadSignInfoExcel")
    public void downLoadSignInfoExcel(HttpServletRequest request,HttpServletResponse response) {
        logger.info("签到人员数据导入模板下载");
        try{
            signInfoService.downLoadSignInfoExcel(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("签到人员数据导入模板下载失败!", e.getMessage());
        }         
    }
    
    /**
     * @description 签到人员数据导入
     * @param Long activityId
     * @param file Excel 文件
     * @return 导入结果
     */
    @RequestMapping(value ="uploadLoadSignInfoExcel")
    @ResponseBody
    public Map<String, Object> uploadLoadSignInfoExcel(Long activityId,@RequestParam("file") MultipartFile multipartFile) {
        logger.info("上传签到人员数据");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map=signInfoService.uploadLoadSignInfoExcel(activityId,multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传签到人员数据失败!", e.getMessage());
            map.put("flag", false);
            map.put("message", "上传签到人员数据失败,请稍后重试!");
        }
        return map; 
    }
    

    /**
     * 导出签到人员数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportData")
    public Map<String, Object> exportData(Long activityId, HttpServletRequest request, HttpServletResponse response){
        logger.info("导出签到人员数据-----");
        return  signInfoService.exportData(activityId, request, response);
    }
    
    /**
     * 跳转快速签到页面
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("toFastSign")
    public ModelAndView toFastSign(Long activityId) {
        ModelAndView mav=new ModelAndView("/sign/toFastSign");
        mav.addObject("activityId",activityId);
        return mav;
    }
    
    /**
     * 快速签到并处理相应逻辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="fastSign",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fastSign(SignInfo info){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("快速签到并处理相应逻辑-----");
        try {
            map=signInfoService.fastSign(info);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("后台快速签到异常:" + e.getMessage());
            map.put("flag", false);
            map.put("message", "后台快速签到异常!");
        }
       return map; 
    }
    
    
    /**
     * 更新转化并处理相应逻辑
     * @param request
     * @return
     */
    @RequestMapping(value="updateAndChange",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAndChange(Long activityId){
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("更新转化并处理相应逻辑-----");
        try {
            map=signInfoService.updateAndChange(activityId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("更新转化异常:" + e.getMessage());
            map.put("flag", false);
            map.put("message", "更新转化异常!");
        }
       return map; 
    } 
    
}
