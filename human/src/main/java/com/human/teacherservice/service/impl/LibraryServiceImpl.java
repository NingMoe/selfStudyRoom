package com.human.teacherservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Users;
import com.human.security.MyUser;
import com.human.teacherservice.dao.LibBookDao;
import com.human.teacherservice.dao.LibBookTypeDao;
import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.entity.LibBookType;
import com.human.teacherservice.service.LibraryService;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class LibraryServiceImpl implements LibraryService {
    
    private final  Logger logger = LogManager.getLogger(LibraryServiceImpl.class);
    
    @Resource
    private LibBookDao libBookDao;
    
    @Resource
    private LibBookTypeDao libBookTypeDao;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Resource
    private UserDao userDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.bookPath}")
    private  String bookPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;

    /**
     * 分页查询图书列表
     * @param pageView
     * @param libBook
     * @return
     */
    public PageView query(PageView pageView, LibBook libBook) {
        Map<Object, Object> map = new HashMap<Object, Object>();        
        libBook.setBook_school(Common.getMyUser().getCompanyId());       
        map.put("paging", pageView); 
        map.put("t", libBook);        
        List<LibBook> list = libBookDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    /**
     * 通过id查询图书
     */
    public Map<String, Object> queryById(HttpServletRequest request,HttpServletResponse response,  LibBook libBook1) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(libBook1 == null || libBook1.getId() == null){
            map.put("flag", false);
            map.put("message", "id为空");
            return map;
        }
        LibBook libBook = libBookDao.selectByPrimaryKey(libBook1.getId());
        if(libBook != null){
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("libBook", libBook);
        }else{
            map.put("flag", false);
            map.put("message", "获取失败");
        }
        return map;
    }

    /**
     * 新增图书
     * @param libBook
     * @return
     */
    public Map<String, Object> insert(LibBook libBook, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(libBook == null){
            map.put("flag", false);
            map.put("message", "请填写信息");
            return map;
        }
        
        if(StringUtils.isEmpty(libBook.getBook_name())){
            map.put("flag", false);
            map.put("message", "请填写书籍名称");
            return map;
        }
        
        logger.info("教师服务->新增图书:开始");
        
      //上传图片到文件服务器
        logger.info("教师服务->新增图书:开始上传图片");
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
                String newFileName = bookPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult = ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    if("file".equals(fileName)){
                      //封装参数
                        libBook.setBook_cover(newFileName);
                      //获取用户权限范围
                        MyUser myUser = Common.getMyUser();
                        
                        Users users = new Users();
                        users.setId(myUser.getUserid());
                        List<Users> user = userDao.queryUser(users);
                        String school_id = user.get(0).getCompanyId();
                        libBook.setBook_school(school_id);
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    logger.error("教师服务->新增图书:图片上传失败");
                    return map;
                }
            }
        }
        logger.info("教师服务->新增图书:上传图片结束");
        
        try {
            libBookDao.insertSelective(libBook);
            map.put("flag", true);
            map.put("message", "新建成功");
        }catch (Exception e) {
            map.put("flag", true);
            map.put("message", "新建失败："+e);
            e.printStackTrace();
        }
        logger.info("教师服务->新增图书:结束");
        return map;
    }

    /**
     * 更新图书
     * @param libBook
     * @return
     */
    public Map<String, Object> update(LibBook libBook, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(libBook == null){
            map.put("flag", false);
            map.put("message", "请填写信息");
            return map;
        }
        
        if(StringUtils.isEmpty(libBook.getBook_name())){
            map.put("flag", false);
            map.put("message", "请填写书籍名称");
            return map;
        }
        
        logger.info("教师服务->更新图书:开始");
        
        //上传图片到文件服务器
        logger.info("教师服务->更新图书:开始上传图片");
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
                String newFileName = bookPath+System.currentTimeMillis() + originalName.substring(originalName.lastIndexOf("."));
                Map<String,Object> uploadResult = ossUtil.uploadFile(ossClient,bucketName, newFileName, multiFile);
                if((boolean) uploadResult.get("flag")){
                    if("file".equals(fileName)){
                      //封装参数
                        libBook.setBook_cover(newFileName);
                        LibBook libBook1 = libBookDao.selectByPrimaryKey(libBook.getId());
                        if(libBook1 != null && StringUtils.isNotEmpty(libBook1.getBook_cover())){
                            ossUtil.deleteObject(ossClient, bucketName, libBook1.getBook_cover());
                        }
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "图片上传失败");
                    logger.error("教师服务->更新图书:图片上传失败");
                    return map;
                }
            }
        }
        logger.info("教师服务->更新图书:上传图片结束");
        
        try {
            libBookDao.updateByPrimaryKeySelective(libBook);
            map.put("flag", true);
            map.put("message", "更新成功");
        }catch (Exception e) {
            map.put("flag", true);
            map.put("message", "更新失败："+e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除图书
     * @param libBook
     * @return
     */
    public Map<String, Object> delete(LibBook libBook) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(libBook == null || libBook.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
        
        LibBook libBook1 = libBookDao.selectByPrimaryKey(libBook.getId());
        if(libBook1 != null && StringUtils.isNotEmpty(libBook1.getBook_cover())){
            OSSClient ossClient = ossUtil.getClient();
            ossUtil.deleteObject(ossClient, bucketName, libBook1.getBook_cover());
        }
        
        try {
            libBookDao.deleteByPrimaryKey(libBook.getId());
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败:"+e);
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 导入excel
     */
    @SuppressWarnings("all")
    @Transactional
    public Map<String, Object> upexcel(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取上传的excel
        logger.info("教师服务->上传图书:获取excel开始");
        
        
        boolean flag=false;
        String msg="未知错误";
        int index = 1;
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<LibBook> ex=new ExcelUtil<LibBook>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,LibBook.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("flag", false);
                map.put("message", empTeachMap.get("errorMessage"));
                return map;
            }
            List<LibBook> list=(List<LibBook>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
          //获取用户权限范围
            MyUser myUser = Common.getMyUser();
            
            Users users = new Users();
            users.setId(myUser.getUserid());
            List<Users> user = userDao.queryUser(users);
            String school_id = user.get(0).getCompanyId();
            List<LibBookType> libBookType = libBookTypeDao.queryByTypeName(school_id);
            
            if(libBookType == null || libBookType.size() <= 0){
                flag = false;
                msg = "没有书籍分类，请先创建书籍分类";
                return map;
            }
            
            
            int i = 0;
            for(LibBook libBook : list){
                index ++;
                i++;
                libBook.setBook_school(school_id);
                
                boolean b = false;
                
                for(LibBookType lkType : libBookType){
                    if(lkType.getType_name().equals(libBook.getBook_type_name())){
                        libBook.setBook_type(lkType.getId());
                        b = true;
                    }
                }
                
                if(!b){
                    flag = false;
                    msg = "第"+i+"条书籍分类名称错误，请按照系统填写。";
                    throw new RuntimeException();
                }
                
                libBookDao.insertSelective(libBook);
                
            }
            flag=true;
            msg="导入成功,成功导入"+list.size()+"条图书信息";
        }catch(Exception e){
            logger.error("导入异常行："+index);
           throw new RuntimeException(e);
        }finally {
            if(!flag){
                //手动回滚事物
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            map.put("flag", flag);
            map.put("message", msg);
            logger.info("教师服务->上传图书:获取excel结束");
            return map;
        }
    }

    /**
     * 批量导出
     * @param ids
     * @return
     */
    public Map<String, Object> downselect(String ids, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> list = new ArrayList<Integer>();
        String[] idarray = ids.split(",");
        try {
            for(String id :idarray){
                list.add(Integer.valueOf(id));
            }
        }catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        
        try {
            List<Map<String,Object>>  maplist = libBookDao.selectByIds(list);
            ExcelUtil<LibBook> ex=new ExcelUtil<LibBook>();
            String path = request.getSession().getServletContext()
                    .getRealPath("/static/temp/");
            ex.writeExcel(path+"downloadfile1.xlsx", LibBook.class, maplist, response, "导出图书", 0, 1);
            map.put("flag", true);
            map.put("message", "导出成功");
        }
        catch (IOException e) {
            map.put("flag", false);
            map.put("message", "导出失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 批量启用/禁用
     * @param ids
     * @return
     */
    public Map<String, Object> isopen(String ids, Integer valid) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Integer> list = new ArrayList<Integer>();
        String[] idarray = ids.split(",");
        try {
            for(String id :idarray){
                list.add(Integer.valueOf(id));
            }
        }catch (Exception e) {
            e.printStackTrace();
            return map;
        }
        
        try {
            int i = 0;
            if(valid == 0){
                i = libBookDao.updateFalseByIds(list);
            }else if(valid == 1){
                i = libBookDao.updateTrueByIds(list);
            }
            map.put("flag", true);
            map.put("message", "批量操作成功"+i+"条");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "操作失败");
            e.printStackTrace();
        }
        
        return map;
    }
    
}
