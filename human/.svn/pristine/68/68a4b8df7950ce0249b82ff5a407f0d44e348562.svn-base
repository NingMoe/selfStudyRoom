package com.human.teacherservice.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Users;
import com.human.security.MyUser;
import com.human.teacherservice.dao.LibBookTypeDao;
import com.human.teacherservice.entity.LibBookType;
import com.human.teacherservice.service.LibraryTypeService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class LibraryTypeServiceImpl implements LibraryTypeService {
    
    private final  Logger logger = LogManager.getLogger(LibraryTypeServiceImpl.class);
    
    @Resource
    private UserDao userDao;
    
    @Resource
    private LibBookTypeDao libBookTypeDao;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.teacherPath}")
    private  String schoolPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    /**
     * 分页获取分类
     */
    public Map<String, Object> queryAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        String school_id = Common.getMyUser().getCompanyId();       
        List<LibBookType> list = libBookTypeDao.queryByTypeName(school_id);
        if(list != null && list.size() >= 0){
            map.put("flag", true);
            map.put("message", "获取分类成功");
            map.put("list", list);
        }else{
            map.put("flag", false);
            map.put("message", "获取分类失败");
        }
        
        return map;
    }
    
    /**
     * 分页获取分类
     */
    public PageView query(LibBookType libBookType, PageView pageView) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        libBookType.setSchool_id(Common.getMyUser().getCompanyId());        
        map.put("paging", pageView); 
        map.put("t", libBookType);
        List<LibBookType> list = libBookTypeDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    /**
     * 新增
     */
    public Map<String, Object> insert(LibBookType libBookType, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(libBookType == null){
            map.put("flag", false);
            map.put("message", "请填写信息");
            return map;
        }
        
        if(StringUtils.isEmpty(libBookType.getType_name())){
            map.put("flag", false);
            map.put("message", "请填写分类名称");
            return map;
        }
        
        if(libBookType.getWeight() == null){
            map.put("flag", false);
            map.put("message", "请填写权重");
            return map;
        }        
        libBookType.setSchool_id(Common.getMyUser().getCompanyId());        
        //上传图片到文件服务器
        logger.info("教师服务->新建活动发布:开始上传图片");
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
                String newFileName = schoolPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    if("file".equals(fileName)){
                      //封装参数
                        libBookType.setType_cover_url(newFileName);
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    logger.error("教师服务->新建活动发布:图片上传失败");
                    return map;
                }
            }
        }
        logger.info("教师服务->新建活动发布:上传图片结束");
        //封装参数       
        try {
            libBookTypeDao.insertSelective(libBookType);
            map.put("flag", true);
            map.put("message", "新增分类成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败："+e);
        }
        
        return map;
    }

    /**
     * 删除
     */
    public Map<String, Object> delete(LibBookType libBookType) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(libBookType == null){
            map.put("flag", false);
            map.put("message", "请选择要删除分类");
            return map;
        }
        
        if(libBookType.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的分类");
            return map;
        }
        
        try{
            OSSClient ossClient = ossUtil.getClient();
            LibBookType bookType = libBookTypeDao.selectByPrimaryKey(libBookType.getId());
            if(bookType != null && bookType.getType_cover_url()!= null && !bookType.getType_cover_url().equals("")){
                ossUtil.deleteObject(ossClient, bucketName, bookType.getType_cover_url());
            }
            libBookTypeDao.deleteByPrimaryKey(libBookType.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        return map;
    }

    /**
     * 更新
     */
    public Map<String, Object> update(LibBookType libBookType, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(libBookType == null){
            map.put("flag", false);
            map.put("message", "请填写信息");
            return map;
        }
        
        if(StringUtils.isEmpty(libBookType.getType_name())){
            map.put("flag", false);
            map.put("message", "请填写分类名称");
            return map;
        }
        
        if(libBookType.getWeight() == null){
            map.put("flag", false);
            map.put("message", "请填写权重");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        Users users = new Users();
        users.setId(myUser.getUserid());
        List<Users> user = userDao.queryUser(users);
        String school_id = user.get(0).getCompanyId();
        libBookType.setSchool_id(school_id);
        
      //上传图片到文件服务器
        logger.info("教师服务->更新图书分类:开始上传图片");
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
                String newFileName = schoolPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult =ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    if("file".equals(fileName)){
                        //封装参数
                        libBookType.setType_cover_url(newFileName);
                        LibBookType bookType = libBookTypeDao.selectByPrimaryKey(libBookType.getId());
                        if(bookType != null && bookType.getType_cover_url()!= null && !bookType.getType_cover_url().equals("")){
                            ossUtil.deleteObject(ossClient, bucketName, bookType.getType_cover_url());
                        }
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    logger.error("教师服务->更新图书分类:图片上传失败");
                    return map;
                }
            }
        }
        logger.info("教师服务->更新图书分类:上传图片结束");
        
        //封装参数
        try {
            libBookTypeDao.updateByPrimaryKeySelective(libBookType);
            map.put("flag", true);
            map.put("message", "更新分类成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败："+e);
        }
        return map;
    }
    
    /**
     * 通过id获取分类
     */
    public Map<String, Object> queryById(LibBookType libBookType) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(libBookType == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        if(libBookType.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        try {
            LibBookType BookType = libBookTypeDao.selectByPrimaryKey(libBookType.getId());
            if(BookType == null){
                map.put("flag", false);
                map.put("message", "获取失败");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("libBookType", BookType);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.info("教师服务->获取图书分类失败："+e);
        }
        return map;
    }

    /**
     * 批量禁用/启用
     * @param ids
     * @return
     */
    public Map<String, Object> updateselect(String ids, Integer is_valid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请先选择要禁用的分类");
            return map;
        }
        
        try {
            String[] idarray = ids.split(",");
            for(String idstring : idarray){
                Integer id = Integer.valueOf(idstring);
                
                LibBookType libBookType = new LibBookType();
                libBookType.setId(id);
                libBookType.setValid(is_valid);
                libBookTypeDao.updateByPrimaryKeySelective(libBookType);
            }
            
            map.put("flag", true);
            if(is_valid == 1){
                map.put("message", "启用成功");
            }else{
                map.put("message", "禁用成功");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "禁用异常："+e);
        }
        
        return map;
    }
}
