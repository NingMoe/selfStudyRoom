package com.human.teacherservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.human.binding.dao.WechatTeacherBindingDao;
import com.human.manager.dao.HrUserDao;
import com.human.manager.entity.HrUser;
import com.human.teacherservice.dao.LibBookDao;
import com.human.teacherservice.dao.LibBookErrorDao;
import com.human.teacherservice.dao.LibBookTypeDao;
import com.human.teacherservice.dao.LibBorrowListDao;
import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.entity.LibBookError;
import com.human.teacherservice.entity.LibBookType;
import com.human.teacherservice.entity.LibBorrowList;
import com.human.teacherservice.service.LibraryWxService;

@Service
public class LibraryWxServiceImpl implements LibraryWxService {
    
    private final static  Logger logger = LogManager.getLogger(LibraryWxServiceImpl.class);
    
    @Resource
    private HrUserDao hrUserDao;
    
    @Resource
    private LibBookTypeDao libBookTypeDao;
    
    @Resource
    private LibBorrowListDao libBorrowListDao;
    
    @Resource
    private LibBookErrorDao libBookErrorDao;
    
    @Resource
    private LibBookDao libBookDao;
    
    @Resource
    private AuthenticationManager myAuthenticationManager;
    
    @Resource
    private WechatTeacherBindingDao wechatTeacherBindingDao;
    
    /**
     * 获取所有分类信息
     */
    public Map<String, Object> getType(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取用户权限范围
        HrUser hrUser = null;
        
        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute("binding_email_addr");
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取用户信息异常：" + e);
            e.printStackTrace();
        }
        
        try {
            List<LibBookType> list = libBookTypeDao.queryByTypeName(hrUser.getCompany());
            map.put("flag", true);
            map.put("message", "获取分类成功");
            map.put("list", list);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取分类失败："+e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取图书相关信息
     */
    public Map<String, Object> getBookInfo(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户权限范围
        HrUser hrUser = null;
        
        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute("binding_email_addr");
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取用户信息异常：" + e);
            e.printStackTrace();
        }
        
        try {
            List<LibBorrowList> booklist = libBorrowListDao.getBookCountBySchoolId(hrUser.getCompany());
            List<LibBorrowList> peoplelist = libBorrowListDao.getPeopleCountBySchoolId(hrUser.getCompany());
            map.put("flag", true);
            map.put("message", "获取图书相关信息成功");
            map.put("booklist", booklist);
            map.put("peoplelist", peoplelist);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取图书相关信息失败："+e);
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 通过分类id获取分类信息
     */
    public Map<String, Object> getBookBype(HttpServletRequest request,  HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String idstring = request.getParameter("id");
        String book_name = request.getParameter("book_name");
        Integer book_type = null;
        HrUser hrUser = null;
        try {
            
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute("binding_email_addr");
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
            
            
            Map<String, Object> mapparam = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(idstring)){
                book_type = Integer.valueOf(idstring);
                mapparam.put("book_type", book_type);
            }
            mapparam.put("book_name", book_name);
            mapparam.put("school_id",hrUser.getCompany());
            List<LibBook> lib  = libBookDao.selectBookInfo(mapparam);
            if(lib != null){
                map.put("flag", true);
                map.put("message", "获取图书成功。");
                map.put("booklist", lib);
            }else{
                map.put("flag", false);
                map.put("message", "没有找到相关分类书籍。");
            } 
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取图书相关信息失败："+e);
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 获取用户借书记录
     */
    public Map<String, Object> getborrowbook(HttpServletRequest request,  HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取用户权限范围
        HrUser hrUser = null;
        HttpSession session = request.getSession();
        String email_addr = (String) session.getAttribute("binding_email_addr");
        
        try {
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取用户信息异常：" + e);
            e.printStackTrace();
        }
        
        try {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("school_id", hrUser.getCompany());
            map1.put("email_addr", email_addr);
            List<LibBorrowList> list = libBorrowListDao.selectByUserId(map1);
            if(list != null && list.size() > 0){
                map.put("flag", true);
                map.put("message", "借阅成功");
                map.put("list", list);
            }else{
                map.put("flag", false);
                map.put("message", "没有相关记录");
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "出错了，联系管理员："+e);
            e.printStackTrace();
        }
        
        return map;
    }

    /**
     * 借书
     */
    public Map<String, Object> borrowbook(HttpServletRequest request,  HttpServletResponse response,  LibBook libBook) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取用户权限范围
        HrUser hrUser = null;
        HttpSession session = request.getSession();
        String email_addr = (String) session.getAttribute("binding_email_addr");
        try {
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取用户信息异常：" + e);
            e.printStackTrace();
            return map;
        }
        
        try {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("school_id", hrUser.getCompany());
            map1.put("email_addr", email_addr);
            List<LibBorrowList> list = libBorrowListDao.selectByOnTime(map1);
            if(list != null && list.size() >= 2){
                map.put("flag", false);
                map.put("message", "同时只能借阅两本哦，请先归还已看完的书籍吧。");
                return map;
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询已经订阅数量异常："+e);
            return map;
        }
        
        LibBorrowList libBorrowList = new LibBorrowList();
        libBorrowList.setBorrow_schoolid(hrUser.getCompany());
        libBorrowList.setBook_id(libBook.getId());
        libBorrowList.setEmail_addr(email_addr);
        libBorrowList.setBook_name(libBook.getBook_name());
        libBorrowList.setType_name(libBook.getBook_type_name());
        
        try {
            libBorrowListDao.insertSelective(libBorrowList);
            map.put("flag", true);
            map.put("message", "借阅成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "借阅异常："+e);
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * 还书
     */
    public Map<String, Object> returnbook(HttpServletRequest request,  HttpServletResponse response,  LibBorrowList libBorrowList) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(libBorrowList == null){
            map.put("flag", false); 
            map.put("message", "请选择要还的书籍");
            return map;
        }
        
        if(libBorrowList.getBorrow_id() == null){
            map.put("flag", false); 
            map.put("message", "请选择要还的书籍");
            return map;
        }
        
        libBorrowList.setReturn_time(new Date());
        try {
            libBorrowListDao.updateByPrimaryKeySelective(libBorrowList);
            map.put("flag", true); 
            map.put("message", "还书成功");
        }catch (Exception e) {
            map.put("flag", false); 
            map.put("message", "出错了，请联系管理员："+e);
        }
        return map;
    }

    /**
     * 图书报错
     * @param request
     * @param response
     * @param libBook
     * @return
     */
    public Map<String, Object> errorbook(HttpServletRequest request, HttpServletResponse response, LibBook libBook) {
        Map<String, Object> map = new HashMap<String, Object>();
      //获取用户权限范围
        HrUser hrUser = null;
        
        try {
            HttpSession session = request.getSession();
            String email_addr = (String) session.getAttribute("binding_email_addr");
            HrUser hrUser1 = new HrUser();
            hrUser1.setEmailAddr(email_addr);
            hrUser = hrUserDao.selectByPrimaryKey(hrUser1);
            if(hrUser == null){
                map.put("flag", false);
                map.put("message", "没有该用户信息");
                return map;
            }
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取用户信息异常：" + e);
            e.printStackTrace();
        }
        
        LibBookError libBookError = new LibBookError();
        
        libBookError.setSchool_id(hrUser.getCompany());
        libBookError.setBook_id(libBook.getId());
        libBookError.setBook_name(libBook.getBook_name());
        libBookError.setReport_empl_id(Integer.valueOf(hrUser.getEmplId()));
        libBookError.setReport_name(hrUser.getName());
        
        try {
            libBookErrorDao.insertSelective(libBookError);
            map.put("flag", true); 
            map.put("message", "已成功提交书籍异常。");
        }catch (Exception e) {
            map.put("flag", false); 
            map.put("message", "失败，请联系管理员："+e);
        }
        
        return map;
    }
    
    //获取jsonobject中jsonname的返回值
    public static String getJSONObjectString(JSONObject jo, String jsonname){
        String s = "";
        if(jo == null){
            logger.error("返回json为空");
            return s;
        }
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getString(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
    
    //获取jsonarray中jsonname的返回值
    public static Integer getJSONObjectInteger(JSONObject jo, String jsonname){
        Integer s = null;
        if(jo.has(jsonname) && !jo.isNull(jsonname)){
            try {
                s = jo.getInt(jsonname);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

}
