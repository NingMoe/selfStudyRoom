package com.human.basic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.manager.controller.HrCompanyController;
import com.human.utils.OSSUtil;

@Controller
@RequestMapping("kindeditor")
public class KindEditorController {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private final  Logger logger = LogManager.getLogger(HrCompanyController.class);
    
    @Autowired 
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.kinerEditorPath}")
    private  String kinerEditorPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileUpload(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException,
            FileUploadException {
        logger.info("开始KINDEREDITOR上传");
        Map<String, Object> result = new HashMap<String,Object>();
        try {
            OSSClient ossClient = ossUtil.getClient();
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
                    String newFileName = kinerEditorPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                    ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                    result.put("error", 0);  
                    result.put("url", fileurl + newFileName);  
                }
            }
            logger.info("KINDEREDITOR上传结束");
        }catch(Exception e){
            e.printStackTrace();
            logger.info("KINDEREDITOR上传文件失败");
            return getError("上传失败");
        }
        return result;
    }

    private Map<String, Object> getError(String message) {
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put("error", 1);
        msg.put("message", message);
        return msg;
    }

    
}