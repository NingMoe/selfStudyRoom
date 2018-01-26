package com.human.teacherservice.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.teacherservice.entity.LibBook;
import com.human.teacherservice.entity.LibBookError;
import com.human.utils.PageView;

public interface LibraryBookErrorService {

    /**
     * 分页获取异常书籍
     * @param pageView
     * @param libBookError
     * @return
     */
    public PageView query(PageView pageView, LibBookError libBookError);

    /**
     * 图书报错
     * @param libBookType
     * @return
     */
    public Map<String, Object> errorbook(HttpServletRequest request,HttpServletResponse response ,LibBook libBook);

    /**
     * 微图书馆异常解决
     * @param ids
     * @return
     */
    public Map<String, Object> selecttrue(String ids);

}
