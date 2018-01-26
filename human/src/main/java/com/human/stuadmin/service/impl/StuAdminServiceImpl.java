package com.human.stuadmin.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.human.stuadmin.dao.StuAdminDao;
import com.human.stuadmin.entity.StuAdmin;
import com.human.stuadmin.service.StuAdminService;
import com.human.utils.FileUtil;
import com.human.utils.HttpClientUtil;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;

@Service
public class StuAdminServiceImpl implements StuAdminService {
    private final Logger logger = LogManager.getLogger(StuAdminServiceImpl.class);

    @Resource
    private StuAdminDao saDao;
    @Value("${jw.offerPath}")
    private String offerPath;
    
    @Value("${oss.stuGradePath}")
    private String stuGrade;
    
    @Value("${oss.bucket}")
    private  String bucketName;

    @Autowired
    private OSSUtil ossUtil;
    

    @Override
    public PageView query(PageView pageView, StuAdmin sa) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", sa);
        List<StuAdmin> list = saDao.query(map);
        try{
        for (StuAdmin stuAdmin : list) {
            String code=stuAdmin.getCode();
            String telephone1 ="";
            String schoolName ="";
                String url = "http://wxpay.xdf.cn/xdfturnback/GetStudentInfo.do";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("date",TimeUtil.getCurrTime());
                params.put("studentCode", code);
                params.put("schoolId", "25");
                String result = HttpClientUtil.httpPostRequest(url, null, params);
                if(StringUtils.isNotEmpty(result)){
                    Map map2 = (Map) JSON.parse(result);
                    if(map2.get("State").toString().equals("1") && map2.get("Data")!=null){
                        String data =  map2.get("Data").toString();
                        Map map1 = (Map) JSON.parse(data);
                        telephone1=map1.get("Mobile").toString();
                        schoolName=map1.get("Address").toString();
                    }
                }
                stuAdmin.setStuPhone(telephone1);
                stuAdmin.setSchoolName(schoolName);
                Map<String, Object> phone=new HashMap<>();
                phone.put("id", stuAdmin.getId());
                phone.put("stuPhone", telephone1);
                phone.put("schoolName", schoolName);
                saDao.updatePhone(phone);
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<StuAdmin> queryClassCode(String sClassCode) {
        // TODO Auto-generated method stub
        return saDao.queryClassCode(sClassCode);
    }

    @Override
    public int insert(StuAdmin sa) {
        // TODO Auto-generated method stub
        return saDao.insert(sa);
    }

    @Override
    public StuAdmin selectByprimary(String code) {
        // TODO Auto-generated method stub
        return saDao.selectByPrimaryKey(code);
    }

    @Override
    public int update(StuAdmin sa) {
        // TODO Auto-generated method stub
        return saDao.updateByPrimaryKey(sa);
    }

    @Override
    public Map<String, Object> upload(HttpServletRequest request,String sClassCode,String stuName) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取上传的excel
        logger.info("附件上传开始");
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if (file.isEmpty()) {
                map.put("message", "文件内容为空，请重新选择文件!");
                return map;
            }
            OSSClient ossClient = ossUtil.getClient();
            String originalName=file.getOriginalFilename();
            String newFileName = stuGrade+System.currentTimeMillis()+"/"+sClassCode+"/"+stuName+"/" + originalName;
            Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, file); 
            uploadResult.put("testAcce", newFileName);
        return uploadResult;
}

    @Override
    public void download(HttpServletRequest request,HttpServletResponse response, StuAdmin sa) throws FileNotFoundException {
        String testAcce=sa.getTestAcce();
        String[] testAcceArray=testAcce.split("</br>");    
        OSSClient ossClient = ossUtil.getClient();
        String sClassCode=sa.getsClassCode();
        String name=sa.getStuName();           
        String time=String.valueOf(System.currentTimeMillis());
        String newFilePath=request.getServletContext().getRealPath("/static/temp")+File.separator+sClassCode+"-"+name+"-"+time;  
        String zipFilePath=newFilePath+".zip"; 
        File file=new File(newFilePath);
        if(!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        try{
            InputStream is=null;
            FileOutputStream ros=null;
            for(String string : testAcceArray) {
                is= ossUtil.getObjectInputStream(ossClient,  bucketName,  string);
                String str= string.substring(string.lastIndexOf("/")+1);
                ros = new FileOutputStream(newFilePath+"/"+str);
                byte[] bytes = new byte[1024*8];
                int len = 0;               
                while ((len = is.read(bytes)) > 0) {
                    ros.write(bytes, 0, len);
                 }                  
            } 
            is.close();
            ros.close();
            response.setContentType("multipart/form-data");       
            response.setHeader("Content-Disposition","attachment;fileName=" + new String((sClassCode+"-"+name+"-"+time+".zip").getBytes("gbk"), "iso-8859-1"));  
            FileUtil.fileToZip(newFilePath, zipFilePath);
            InputStream inputStream = new FileInputStream(zipFilePath);
            OutputStream os = response.getOutputStream();
//            int size = (int) file.length();
            byte[] b = new byte[1024*8];
            int length=0;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();  
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            FileUtil.deletefile(newFilePath);
            FileUtil.deletefile(zipFilePath);            
        }
        
    }

    @Override
    public int deleteAcce(StuAdmin sa) {
           
        return saDao.updateAcce(sa);
    }

    @Override
    public Map<String, Object> getStuPhone(String code, int schoolId) {
        String telephone1 ="";
        String schoolName="";
        Map<String, Object> resu=new HashMap<>();
        try{
            String url = "http://wxpay.xdf.cn/xdfturnback/GetStudentInfo.do";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date",TimeUtil.getCurrTime());
            params.put("studentCode", code);
            params.put("schoolId", schoolId);
            String result = HttpClientUtil.httpPostRequest(url, null, params);
            if(StringUtils.isNotEmpty(result)){
                Map map = (Map) JSON.parse(result);
                if(map.get("State").toString().equals("1") && map.get("Data")!=null){
                    String data =  map.get("Data").toString();
                    Map map1 = (Map) JSON.parse(data);
                     telephone1=map1.get("Mobile").toString();
                     schoolName=map1.get("Address").toString();
                }
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        resu.put("schoolName", schoolName);
        resu.put("telephone1", telephone1);
        return resu;
    }

    @Override
    public int updateStatusBySclassCode( Map<String, Object> map) {
        // TODO Auto-generated method stub
        return saDao.updateStatusBySclassCode(map);
    }

    @Override
    public List<StuAdmin> selectByCodeAndSclassCode(String sClassCode, String code) {
        Map<String,Object> map=new HashMap<>();
        map.put("sClassCode", sClassCode);
        map.put("code", code);
        return saDao.selectByCodeAndSclassCode(map);
    }
    
}
