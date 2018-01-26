package com.human.teacherservice.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.aliyun.oss.OSSClient;
import com.human.manager.dao.UserDao;
import com.human.manager.entity.Users;
import com.human.security.MyUser;
import com.human.teacherservice.dao.TeacherActManagerDao;
import com.human.teacherservice.entity.TeacherActManager;
import com.human.teacherservice.service.TeacherActManagerService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Service
public class TeacherActManagerServiceImpl implements TeacherActManagerService {
    
    private final  Logger logger = LogManager.getLogger(TeacherActManagerServiceImpl.class);
    
    @Resource
    private TeacherActManagerDao teacherActManagerDao;
    
    @Resource
    private UserDao userDao;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.teacherPath}")
    private  String schoolPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;

    /**
     * 按条件获取活动
     * @param managerUser
     * @return
     */
    public List<TeacherActManager> query(TeacherActManager teacherActManager) {
        
        return null;
    }

    /**
     * 分页获取活动申请列表
     * @param pageView
     * @param managerUser
     * @return
     */
    public PageView query(PageView pageView, TeacherActManager teacherActManager) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        //验证参数
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        Boolean bool = false;
        for(GrantedAuthority s : myUser.getAuthorities()){
            if(s.toString().equals("ROLE_act_isagree")){
                bool = true;
            }
        }
        
        if(bool){
            Users users = new Users();
            users.setId(myUser.getUserid());
            List<Users> user = userDao.queryUser(users);
            String school_id = user.get(0).getCompanyId();
            teacherActManager.setAct_school_id(school_id);
        }else{
            teacherActManager.setAct_user(myUser.getUserid());
        }
        
        map.put("paging", pageView);
        map.put("t", teacherActManager);
        
        List<TeacherActManager> list = teacherActManagerDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    /**
     * 删除申请活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> deleteTeacherAct(TeacherActManager teacherActManager) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(teacherActManager == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的活动");
            return map;
        }
        
        if(teacherActManager.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择要删除的活动");
            return map;
        }
        
        try{
            OSSClient ossClient = ossUtil.getClient();
            TeacherActManager teacher = teacherActManagerDao.selectByPrimaryKey(teacherActManager.getId());
            if(teacher != null && teacher.getAct_img() != null && !teacher.getAct_img().equals("")){
                ossUtil.deleteObject(ossClient, bucketName, teacher.getAct_img());
            }
            teacherActManagerDao.deleteByPrimaryKey(teacherActManager.getId());
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
     * 发布新的活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> insertTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        logger.info("教师服务->新建活动发布:开始");
        //验证参数
        MyUser myUser = Common.getMyUser();
        if(teacherActManager == null){
            map.put("flag", false);
            map.put("message", "请完整填写");
            logger.error("教师服务->新建活动发布:参数为空");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherActManager.getAct_name())){
            map.put("flag", false);
            map.put("message", "活动名称不能为空");
            logger.error("教师服务->新建活动发布:活动名称不能为空");
            return map;
        }
        
        if(StringUtils.isEmpty(teacherActManager.getAct_info())){
            map.put("flag", false);
            map.put("message", "活动详情不能为空");
            logger.error("教师服务->新建活动发布:活动详情不能为空");
            return map;
        }
        
        if(teacherActManager.getAct_start_time() == null){
            map.put("flag", false);
            map.put("message", "活动开始时间不能为空");
            logger.error("教师服务->新建活动发布:活动开始时间不能为空");
            return map;
        }
        
        if(teacherActManager.getAct_end_time() == null){
            map.put("flag", false);
            map.put("message", "活动结束时间不能为空");
            logger.error("教师服务->新建活动发布:活动结束时间不能为空");
            return map;
        }
        
        if(teacherActManager.getClass_start_time() == null){
            map.put("flag", false);
            map.put("message", "报班开始时间不能为空");
            logger.error("教师服务->新建活动发布:报班开始时间不能为空");
            return map;
        }
        
        
        if(teacherActManager.getClass_end_time() == null){
            map.put("flag", false);
            map.put("message", "报班结束时间不能为空");
            logger.error("教师服务->新建活动发布:报班结束时间不能为空");
            return map;
        }
        
        try{
            //获取登录用户基础信息
            Users users = new Users();
            users.setId(myUser.getUserid());
            List<Users> user = userDao.queryUser(users);
            String school_id = user.get(0).getCompanyId();
            String school_name = user.get(0).getCompanyName();
            String department_id = user.get(0).getDeptId();
            String department_name = user.get(0).getDeptName();
            
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
                            teacherActManager.setAct_img(newFileName);
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
            teacherActManager.setAct_school_id(school_id);
            teacherActManager.setAct_school(school_name);
            teacherActManager.setAct_department_id(department_id);
            teacherActManager.setAct_department(department_name);
            teacherActManager.setAct_user(myUser.getUserid());
            teacherActManager.setAct_status(1);
            teacherActManagerDao.insertSelective(teacherActManager);
            map.put("flag", true);
            map.put("message", "发布成功");
            logger.info("教师服务->新建活动发布:结束");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "发布失败："+e);
            logger.error("教师服务->新建活动发布:新建活动失败："+e);
        }
        return map;
    }

    /**
     * 更新发布的活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> updateTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("教师服务->新建活动发布:活动更新开始");
        //验证参数
        if(teacherActManager == null){
            map.put("flag", false);
            map.put("message", "参数为空");
            logger.error("教师服务->新建活动发布:参数为空");
            return map;
        }
        
        if(teacherActManager.getId() == null){
            map.put("flag", false);
            map.put("message", "id参数为空");
            logger.error("教师服务->新建活动发布:id参数为空");
            return map;
        }
        
        //业务
        try{
            logger.info("教师服务->新建活动发布:上传图片开始");
          //上传图片到文件服务器
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
                            teacherActManager.setAct_img(newFileName);
                            TeacherActManager teacher = teacherActManagerDao.selectByPrimaryKey(teacherActManager.getId());
                            if(teacher != null && teacher.getAct_img() != null && !teacher.getAct_img().equals("")){
                                ossUtil.deleteObject(ossClient, bucketName, teacher.getAct_img());
                            }
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
            
            teacherActManagerDao.updateByPrimaryKeySelective(teacherActManager);
            map.put("flag", true);
            map.put("message", "更新成功");
            logger.info("教师服务->新建活动发布:活动更新成功");;
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
            logger.error("教师服务->新建活动发布:活动更新失败"+e);
        }
        return map;
    }
    
    /**
     * 审核活动是否生效
     */
    public Map<String, Object> isagreeTeacherAct(TeacherActManager teacherActManager) {
        Map<String, Object> map = new HashMap<String, Object>();
        //验证参数
        if(teacherActManager == null){
            map.put("flag", false);
            map.put("message", "参数为空");
            return map;
        }
        
        if(teacherActManager.getId() == null){
            map.put("flag", false);
            map.put("message", "id参数为空");
            return map;
        }
        
        if(teacherActManager.getAct_status() == null){
            map.put("flag", false);
            map.put("message", "状态参数为空");
            return map;
        }
        
        //业务
          try{
              teacherActManagerDao.updateByPrimaryKeySelective(teacherActManager);
              map.put("flag", true);
              map.put("message", "更新成功");
          }catch(Exception e){
              e.printStackTrace();
              map.put("flag", false);
              map.put("message", "更新失败："+e);
          }
          return map;
    }

    /**
     * 通过id获取单个
     */
    public Map<String, Object> queryTeacherActById(TeacherActManager teacherActManager) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //验证参数
        if(teacherActManager == null || teacherActManager.getId() == null){
            map.put("flag", false);
            map.put("message", "id不能为空");
            return map;
        }
       
        //获取信息
        try {
            TeacherActManager teacher = teacherActManagerDao.selectByPrimaryKey(teacherActManager.getId());
            if(teacher == null){
                map.put("flag", false);
                map.put("message", "查询结果为空");
            }else{
                map.put("flag", true);
                map.put("message", "查询成功");
                map.put("teacherActManager", teacher);
            }
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "查询失败"+e);
            e.printStackTrace();
        }
        
        return map;
    }
}
