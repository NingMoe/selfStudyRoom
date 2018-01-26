package com.human.xdfStudent.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import com.human.jw.entity.JwCourse;
import com.human.utils.PageView;
import com.human.xdfStudent.entity.XdfStudentInfo;

public interface XdfStudentInfoService {
    /*
     * 分页查询新东方学员数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,XdfStudentInfo cf);
    
    /*
     * 新增新东方学员数据
     * @param cf
     * @return
     */
    Map<String, Object> add(XdfStudentInfo cf);
    
    /*
     * 通过主键查询新东方学员数据
     * @param cf
     * @return
     */
    XdfStudentInfo selectById(long id);
    
    /*
     * 编辑新东方学员数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(XdfStudentInfo cf);
    
    /*
     * 删除新东方学员数据
     * @param cf
     * @return
     */
    Map<String, Object> delete (String deleteIds);
    
    /*
     * 删除全部新东方学员数据
     * @param cf
     * @return
     */
    Map<String, Object> deleteAll();
    
    /*
     * 下载新东方学员数据导入模板
     * @param request
     * @param response
     */
    void downLoadXdfStudentExcel(HttpServletRequest request,HttpServletResponse response);
    
    /*
     * 上传新东方学员数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadXdfStudentExcel(MultipartFile multipartFile);
    
    /*
     * 新东方学员数据批量导出
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response);
    
    /*
     * 通过班号查询新东方学员
     */
    List<XdfStudentInfo> serachXdfStudentByclassCodes(String email,String classCodes);
    
    /*
     * 通过教师登录账号获取登录教师编号
     */
    String getTeacherCode(String email);
    
    /*
     * 获取教师的课程
     */
    List<JwCourse> getTeacherJwCourses(String email,String sCode,String start,String end);
    
    /*
     * 根据学员号查询学员手机号信息
     */
    Map<String, Object>searchPhone(HttpServletRequest request,String studentCode);
}
