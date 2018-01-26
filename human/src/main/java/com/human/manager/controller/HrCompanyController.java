package com.human.manager.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSClient;
import com.human.basic.entity.AreaInfo;
import com.human.basic.service.AreaInfoService;
import com.human.manager.entity.HrCompany;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.HrOrganizationService;
import com.human.manager.service.HrUserService;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping("/manager/company")
public class HrCompanyController {
    private final  Logger logger = LogManager.getLogger(HrCompanyController.class);
    
    @Autowired 
    private HrCompanyService hrCompanyService;
    
    @Autowired 
    private HrOrganizationService hrOrgService;
    
    @Autowired 
    private HrUserService userService;
    
    @Autowired 
    private AreaInfoService areaInfoService;
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.schoolPath}")
    private  String schoolPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/manager/company/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView){
        return  hrCompanyService.findAllCompany(pageView, null);
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/manager/company/add");
        return mav;
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(HrCompany hrCompany){
        logger.info("添加公司");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrCompanyService.insertSelective(hrCompany);
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch(Exception e){
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 进入编辑机构ID页面
     * @return
     */
    @RequestMapping(value="toEditBasic")
    public ModelAndView toEditBasic(String companyId){
        HrCompany company = hrCompanyService.selectByPrimaryKey(companyId);
        ModelAndView mav=new ModelAndView("/manager/company/editBasic");
        mav.addObject("company", company);
        return mav;
    }
    
    /**
     * 修改学校信息
     */
    @RequestMapping(value="toEditDetail")
    public ModelAndView toEditDetail(String companyId){
        HrCompany company = hrCompanyService.selectByPrimaryKey(companyId);
        AreaInfo areaInfo = new AreaInfo();
        areaInfo.setAreaLevel(1);
        List<AreaInfo> provinces = areaInfoService.getArea(areaInfo);
        ModelAndView mav=new ModelAndView("/manager/company/editDetail");
        mav.addObject("company", company);
        mav.addObject("fileurl", fileurl);
        mav.addObject("provinces", provinces);
        return mav;
    }
    
    /**
     * 编辑机构ID
     * @return
     */
    @RequestMapping(value="editBasic")
    @ResponseBody
    public Map<String,Object> editBasic(HrCompany hrCompany){
        logger.info("编辑机构ID");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrCompanyService.updateByPrimaryKeySelective(hrCompany);
            result.put("flag", true);
            result.put("message", "编辑成功");
        }catch(Exception e){
            result.put("flag", false);
            result.put("message", "编辑失败");
        }
        return result;
    }
    
    /**
     * 进入编辑机构ID页面
     * @return
     */
    @RequestMapping(value="editDetail")
    @ResponseBody
    public Map<String, Object> editDetail(HttpServletRequest request,HrCompany hrCompany) {
        logger.info("修改学校信息");
        Map<String, Object> map = new HashMap<String, Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        continue;
                    }
                    String newFileName = schoolPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        if("file1".equals(fileName)){
                            hrCompany.setTemperamentPcImage(newFileName);
                        }
                        if("file2".equals(fileName)){
                            hrCompany.setTemperamentMobileImage(newFileName);
                        }
                        if("file3".equals(fileName)){
                            hrCompany.setWechatImage(newFileName);
                        }
                    }
                }
            }
            hrCompanyService.updateByPrimaryKeySelective(hrCompany);
            map.put("flag", true);
            map.put("message", "修改学校信息成功!");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("修改学校信息失败");
            map.put("flag", false);
            map.put("message", "修改学校信息失败!");
        }
        return map;
    }
    
    
    @RequestMapping(value="delImg")
    @ResponseBody
    public Map<String, Object> delImg(String companyId,String buttonId) {
        logger.info("修改学校信息");
        Map<String, Object> map = new HashMap<String, Object>();
        OSSClient ossClient = ossUtil.getClient();
        boolean flag = false;
        try {
            HrCompany company = hrCompanyService.selectByPrimaryKey(companyId);
            if("file1Button".endsWith(buttonId) && StringUtils.isNoneEmpty(company.getTemperamentPcImage())){
                ossUtil.deleteObject(ossClient, bucketName, company.getTemperamentPcImage());
                company.setTemperamentPcImage("");
                flag = true;
            }
            if("file2Button".endsWith(buttonId) && StringUtils.isNoneEmpty(company.getTemperamentMobileImage())){
                ossUtil.deleteObject(ossClient, bucketName, company.getTemperamentMobileImage());
                company.setTemperamentMobileImage("");
                flag = true;
            }
            if("file3Button".endsWith(buttonId) && StringUtils.isNoneEmpty(company.getWechatImage())){
                ossUtil.deleteObject(ossClient, bucketName, company.getWechatImage());
                company.setWechatImage("");
                flag = true;
            }
            if(flag){
                hrCompanyService.updateByPrimaryKey(company);
            }
            map.put("flag", true);
            map.put("message", "删除图片成功!");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("删除图片失败");
            map.put("flag", false);
            map.put("message", "删除图片失败!");
        }
        return map;
    }
    
    /**
     * 刷新组织数据
     * @return
     */
    @RequestMapping(value="refreshOrg")
    @ResponseBody
    public Map<String,Object> refreshOrg(String companyId){
        logger.info("刷新组织");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            hrOrgService.refreshOrgsByCompanyId(companyId);
            result.put("flag", true);
            result.put("message", "刷新组织成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "刷新组织失败");
        }
        return result;
    }
    
    
    /**
     * 刷新员工数据
     * @return
     */
    @RequestMapping(value="refreshUser")
    @ResponseBody
    public Map<String,Object> refreshUser(String companyId){
        logger.info("刷新员工");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            userService.refreshUsersByCompanyId(companyId);
            result.put("flag", true);
            result.put("message", "刷新员工信息成功");
        }catch(Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("message", "刷新员工信息失败");
        }
        return result;
    }
}
