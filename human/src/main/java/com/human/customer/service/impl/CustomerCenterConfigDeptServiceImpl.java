package com.human.customer.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.customer.dao.CustomerCenterConfigDeptDao;
import com.human.customer.entity.CenterDept;
import com.human.customer.service.CustomerCenterConfigDeptService;
import com.human.manager.service.UserService;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public  class CustomerCenterConfigDeptServiceImpl implements CustomerCenterConfigDeptService {
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.wxCustomerPath}")
    private  String wxCustomerPath;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Resource
    private CustomerCenterConfigDeptDao cfgDeptDao;

    @Resource
    private UserService userService;
    
    @Override
    public PageView query(PageView pageView,CenterDept cd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", cd);        
        List<CenterDept> list = cfgDeptDao.queryDept(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public void delByIds(String  ids) {
        cfgDeptDao.delByIds(ids.split(","));
    }

    @Override
    public Map<String, Object> add(CenterDept cd,HttpServletRequest request) {
        Map<String, Object> map=new HashMap<String,Object>();
      // 验证用户账号与密码是否正确
      List<CenterDept> deptList = cfgDeptDao.queryDeptByName(cd.getDeptName());
      if(deptList.size()>0){
          map.put("flag", false);
          map.put("msg", "对不起，该部门已经存在！");
      }else{
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
                  String newFileName = wxCustomerPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                  Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                  if((boolean) uploadResult.get("flag")){
                      cd.setBanerUrl(newFileName);
                  }else{
                      map.put("flag", false);
                      map.put("msg", "图片上传失败");
                      return map;
                  }
              }
          }
          cfgDeptDao.add(cd);
          map.put("flag", true);
          map.put("msg", "处理成功！");
      }
        return map;
    }


    @Override
    public CenterDept queryById(Long id) {
        return cfgDeptDao.queryById(id);
    }


    @Override
    public Map<String, Object> edit(CenterDept cd, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
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
                if ("".equals(originalName) || originalName == null) {
                    continue;
                }
                String newFileName = wxCustomerPath + System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String, Object> uploadResult = ossUtil.uploadFile(ossClient, bucketName, newFileName, multiFile);

                if (StringUtils.isNotEmpty(cd.getBanerUrl()) && ossUtil.isObjectExist(ossClient, bucketName, cd.getBanerUrl())) {
                    ossUtil.deleteObject(ossClient, bucketName, cd.getBanerUrl());
                }
                if ((boolean) uploadResult.get("flag")) {
                    cd.setBanerUrl(newFileName);
                }
                else {
                    map.put("flag", false);
                    map.put("msg", "图片上传失败");
                    return map;
                }
            }
        }
        cfgDeptDao.edit(cd);
        map.put("flag", true);
        map.put("msg", "操作成功");
        return map;
    }
    
    @Override
    public List<CenterDept> getDepts(){
        return cfgDeptDao.getDepts();
    }

}
