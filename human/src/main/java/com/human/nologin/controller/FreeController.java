package com.human.nologin.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.basic.entity.HrEthnic;
import com.human.basic.entity.HrNationality;
import com.human.basic.service.AreaInfoService;
import com.human.basic.service.DictionaryService;
import com.human.basic.service.HrNationalityService;
import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrResumeEntryhandler;
import com.human.recruitment.service.SeekerEntryService;
import com.human.utils.OSSUtil;
import com.human.utils.SecurityHelper;

@Controller
@RequestMapping("/free/entry/")
public class FreeController {
	
	private final  Logger logger = LogManager.getLogger(FreeController.class);
	
	@Autowired
    private SeekerEntryService entryService;
    
    @Autowired
    private HrNationalityService nationalityService;
    
    @Autowired
    private AreaInfoService areaInfoService;
    
    @Autowired
    private DictionaryService dictionaryService;
    
	@Autowired 
	private OSSUtil ossUtil;
	
	@Value("${urlPreKey}")
    private String urlPreKey;
	    
	@Value("${oss.bucket}")
	private  String bucketName;
	    
	@Value("${oss.offerPath}")
	private  String offerPath;
	    
	@Value("${oss.fileurl}")
	private  String fileurl;
	
	@RequestMapping(value="toSeekerDetail")
	public ModelAndView toSeekerDetail(String id,String seekerName){
        ModelAndView mav = null;
        String actid = "";
        String actName = "";
        try {
            actid = SecurityHelper.decrypt(urlPreKey,id);
            actName = SecurityHelper.decrypt(urlPreKey,seekerName);
            HrEntryBase entry = entryService.getBaseSeeker(Integer.valueOf(actid));
            HrResumeEntryhandler entryhandler = entryService.selectByPrimaryKey(Integer.valueOf(actid));
            if(entryhandler.getStatus()!=1){
                return toViewDetail(Integer.valueOf(actid));
            }
            if(entry==null){
                mav=new ModelAndView("/recruitment/seekerEntry/seekerAddDetail");
                mav.addObject("entryHandlerId",actid);
                mav.addObject("seekerName",actName);
            }else{
                entry = entryService.getComplexSeeker(entry.getId());
                mav=new ModelAndView("/recruitment/seekerEntry/seekerEditDetail");
                mav.addObject("entryBase",entry);
                mav.addObject("entryhandler",entryhandler);
                mav.addObject("fileurl",fileurl);
            }
            
            List<HrEthnic> ethnics = nationalityService.getAllEthnic();
            List<HrNationality> nationalitys = nationalityService.getAllNationality();
            AreaInfo a = new AreaInfo();
            a.setAreaLevel(1);
            List<AreaInfo> provinces = areaInfoService.getArea(a);
            mav.addObject("ethnics",ethnics);
            mav.addObject("nationalitys",nationalitys);
            mav.addObject("provinces",provinces);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("员工个人信息反馈错误");
        }
        return mav;
    }
	
	@RequestMapping(value="uploadHeadPic")
    @ResponseBody
    public Map<String,Object> uploadHeadPic(HttpServletRequest request,HrResumeEntryhandler entryhandler){
        logger.info("上传入职头像");
        Map<String,Object> result = new HashMap<String,Object>();
        OSSClient ossClient = ossUtil.getClient();
        try {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession(false).getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                if (iter.hasNext()) {
                    String fileName = iter.next();
                    // 取得上传文件
                    MultipartFile multiFile = multiRequest.getFile(fileName);
                    String originalName = multiFile.getOriginalFilename();
                    if("".equals(originalName)||originalName==null){
                        result.put("flag", false);
                        result.put("message", "请选择头像");
                        return result;
                    }
                    if(StringUtils.isNotEmpty(entryhandler.getHeadPic())
                            &&ossUtil.isObjectExist(ossClient, bucketName,entryhandler.getHeadPic())){
                        ossUtil.deleteObject(ossClient, bucketName, entryhandler.getHeadPic());
                    }
                    String newFileName = offerPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    if((boolean) uploadResult.get("flag")){
                        entryhandler.setHeadPic(newFileName);
                        entryService.updateByPrimaryKeySelective(entryhandler);
                    }
                }
            }
            result.put("flag", true);
            result.put("message", "OFFER上传成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "上传OFFER失败");
        }
        return result;
    }
	
	   /**
     * 入职信息填写
     * @return
     */
    @RequestMapping(value="addSeekerDetail")
    @ResponseBody
    public Map<String,Object> addSeekerDetail(String entryBaseStr,String isTj){
        logger.info("入职资料填写");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrEntryBase entryBase = JSON.parseObject(entryBaseStr, HrEntryBase.class);
            entryService.addSeekerDetail(entryBase);
            if("1".equals(isTj)){
                HrResumeEntryhandler entryhandler = new HrResumeEntryhandler();
                entryhandler.setId(entryBase.getEntryHandlerId());
                entryhandler.setStatus(3);
                entryService.updateByPrimaryKeySelective(entryhandler);
            }
            result.put("flag", true);
            result.put("message", "添加成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "添加失败");
        }
        return result;
    }
    
    /**
     * 入职信息编辑
     * @return
     */
    @RequestMapping(value="editSeekerDetail")
    @ResponseBody
    public Map<String,Object> editSeekerDetail(String entryBaseStr,String isTj){
        logger.info("入职资料填写");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrEntryBase entryBase = JSON.parseObject(entryBaseStr, HrEntryBase.class);
            entryService.editSeekerDetail(entryBase);
            if("1".equals(isTj)){
                HrResumeEntryhandler entryhandler = new HrResumeEntryhandler();
                entryhandler.setId(entryBase.getEntryHandlerId());
                entryhandler.setStatus(3);
                entryService.updateByPrimaryKeySelective(entryhandler);
            }
            result.put("flag", true);
            result.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "修改失败");
        }
        
        return result;
    }
    
    /**
     * 求职者拒绝
     */
    @RequestMapping(value="toRefuse")
    public ModelAndView toRefuse(String id){
        ModelAndView mav=new ModelAndView("/recruitment/seekerEntry/refuse");
        String actid = "";
        try {
            actid = SecurityHelper.decrypt(urlPreKey,id);
            List<DicData> reasons = dictionaryService.getDataByKey("refuse_reason");
            mav.addObject("reasons",reasons);
            mav.addObject("actid",actid);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("员工进入拒绝OFFER页面错误");
        }
        return mav;
    }
    
    
    /**
     * 入职信息编辑
     * @return
     */
    @RequestMapping(value="refuseOffer")
    @ResponseBody
    public Map<String,Object> refuseOffer(String actId,String refuseReason){
        logger.info("拒绝OFFER");
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            HrResumeEntryhandler entryHandler = new HrResumeEntryhandler();
            entryHandler.setId(Integer.valueOf(actId));
            entryHandler.setStatus(2);
            entryHandler.setRefuseReason(refuseReason);
            entryService.updateByPrimaryKeySelective(entryHandler);
            result.put("flag", true);
            result.put("message", "修改成功");
        }catch(Exception e){
            logger.error(e.getMessage());
            result.put("flag", false);
            result.put("message", "拒绝OFFER失败");
        }
        return result;
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

    private ModelAndView toViewDetail(Integer id){
        ModelAndView mav = new ModelAndView("/recruitment/seekerEntry/seekerViewDetail");
        HrEntryBase entry = entryService.getBaseSeeker(id);
        if(entry!=null){
            entry = entryService.getComplexSeeker(entry.getId());
            mav.addObject("entryBase",entry);
            mav.addObject("fileurl",fileurl);
        }
        List<HrEthnic> ethnics = nationalityService.getAllEthnic();
        List<HrNationality> nationalitys = nationalityService.getAllNationality();
        AreaInfo a = new AreaInfo();
        a.setAreaLevel(1);
        List<AreaInfo> provinces = areaInfoService.getArea(a);
        mav.addObject("ethnics",ethnics);
        mav.addObject("nationalitys",nationalitys);
        mav.addObject("provinces",provinces);
        mav.addObject("noClose","1");
        return mav;
    }
    
    /**
     * 查询前台客服链接
     * @param areaInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getKfUrl", method = RequestMethod.GET)
    public List<DicData> getKfUrlInfo(String key) {
        return dictionaryService.getDataByKey(key);
    }
}