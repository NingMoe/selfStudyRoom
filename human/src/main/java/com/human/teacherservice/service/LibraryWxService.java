package com.human.teacherservice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.entity.LibBorrowList;

public interface LibraryWxService {
    
    /**
     * 获取所有分类
     * @return
     */
    public Map<String, Object> getType(HttpServletRequest request,HttpServletResponse response);

    /**
     * 获取本人已借图书
     * @return
     */
    public Map<String, Object> getborrowbook(HttpServletRequest request,HttpServletResponse response);

    /**
     * 获取书本相关信息
     * @return
     */
    public Map<String, Object> getBookInfo(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 通过分类id获取分类下所有图书
     * @param libBookType
     * @return
     */
    public Map<String, Object> getBookBype(HttpServletRequest request,  HttpServletResponse response);

    /**
     * 借书
     * @param libBook
     * @return
     */
    public Map<String, Object> borrowbook(HttpServletRequest request,HttpServletResponse response ,LibBook libBook);
    
    /**
     * 还书
     * @param libBookError
     * @return
     */
    public Map<String, Object> returnbook(HttpServletRequest request,HttpServletResponse response ,LibBorrowList libBorrowList);

    
    /**
     * 图书报错
     * @param request
     * @param response
     * @param libBook
     * @return
     */
    public Map<String, Object> errorbook(HttpServletRequest request, HttpServletResponse response, LibBook libBook);
}
