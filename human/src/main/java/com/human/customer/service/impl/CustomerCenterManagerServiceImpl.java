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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.customer.dao.CustomerCenterDao;
import com.human.customer.entity.CenterMenuDept;
import com.human.customer.entity.CenterModel;
import com.human.customer.entity.CustomerCenterMenu;
import com.human.customer.service.CustomerCenterConfigDeptService;
import com.human.customer.service.CustomerCenterConfigModelService;
import com.human.customer.service.CustomerCenterManagerService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class CustomerCenterManagerServiceImpl implements CustomerCenterManagerService{

    @Resource
    private CustomerCenterConfigModelService  centerModelService;
    
    @Resource
    private CustomerCenterConfigDeptService deptService;
    
    @Resource
    private CustomerCenterDao   centerDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.wxCustomerPath}")
    private  String wxCustomerPath;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Override
    public List<CenterModel> getModels() {
        List<CenterModel> modelList = centerModelService.getModels();
        return modelList;
    }
    
    @Override
    public PageView query(PageView pageView, CustomerCenterMenu menu) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", menu);
        List<CustomerCenterMenu> list = centerDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> addMenu(CustomerCenterMenu cm, HttpServletRequest request) {
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
                if ((boolean) uploadResult.get("flag")) {
                    cm.setIcon(newFileName);
                }
                else {
                    map.put("flag", false);
                    map.put("msg", "图片上传失败");
                    return map;
                }
            }
            cm.setCompany(Common.getMyUser().getCompanyId());
            centerDao.add(cm);
            map.put("flag", true);
            map.put("msg", "处理成功！");
            deptService.updateAllDeptMenu();
        }
        return map;
    }
    
    @Override
    public void delMenu(CustomerCenterMenu cm) {
         centerDao.updateState(cm);
         deptService.updateAllDeptMenu();
    }
    
    @Override
    public CustomerCenterMenu QueryById(Long id) {
        return centerDao.selectById(id);
    }
    
    @Override
    public Map<String, Object> editMenu(CustomerCenterMenu cm, HttpServletRequest request) {
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

                if (StringUtils.isNotEmpty(cm.getIcon()) && ossUtil.isObjectExist(ossClient, bucketName, cm.getIcon())) {
                    ossUtil.deleteObject(ossClient, bucketName, cm.getIcon());
                }
                if ((boolean) uploadResult.get("flag")) {
                    cm.setIcon(newFileName);
                }
                else {
                    map.put("flag", false);
                    map.put("msg", "图片上传失败");
                    return map;
                }
            }
        }
        centerDao.editMenu(cm);
        deptService.updateAllDeptMenu();
        map.put("flag", true);
        map.put("msg", "编辑成功");
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void addMenuDepts(List<CenterMenuDept> depts) {
        Long menuId = depts.get(0).getMenuId();
        centerDao.delDeptByMenuId(menuId);
        centerDao.insertMenuDept(depts);
        deptService.updateAllDeptMenu();
        
    }

    @Override
    public List<CenterMenuDept> getDeptsByMenuId(Long menuId) {
        return centerDao.getDeptsByMenuId(menuId);
    }

}
