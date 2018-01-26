package com.human.stuadmin.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.stuadmin.entity.StuAdmin;
import com.human.utils.PageView;
public interface StuAdminService {

    PageView query(PageView pageView, StuAdmin sa);

    List<StuAdmin> queryClassCode(String sClassCode);

    int insert(StuAdmin sa);

    StuAdmin selectByprimary(String code);

    int update(StuAdmin sa);

    Map<String, Object> upload(HttpServletRequest request,String sClassCode,String stuName) ;

    void download(HttpServletRequest request,HttpServletResponse response, StuAdmin sa) throws FileNotFoundException;

    int deleteAcce(StuAdmin sa);

    Map<String, Object> getStuPhone(String code, int schoolId);

    int updateStatusBySclassCode(Map<String, Object> map);

    List<StuAdmin> selectByCodeAndSclassCode(String sClassCode, String code);

}
