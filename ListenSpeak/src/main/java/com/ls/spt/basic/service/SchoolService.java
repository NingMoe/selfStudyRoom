package com.ls.spt.basic.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.entity.School;

public interface SchoolService {
    /*
     * 分页查询公立学校数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,School cf);
    
    /*
     * 新增公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> add(School cf);
    
    /*
     * 通过主键查询公立学校数据
     * @param cf
     * @return
     */
    School selectById(long id);
    
    /*
     * 编辑公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(School cf);
    
    /*
     * 操作公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> delete (String deleteIds,Integer status);
    
    /*
     * 下载公立学校数据导入模板
     * @param request
     * @param response
     */
    void downLoadSchoolExcel(HttpServletRequest request,HttpServletResponse response);
    
    /*
     * 上传公立学校数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadSchoolExcel(MultipartFile multipartFile,School cf);
    
    /*
     * 根据条件查询
     * @param cf
     * @return
     */
    List<School> getSchoolByParam(School cf);
}
