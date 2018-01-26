package com.human.continuedClass.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.human.continuedClass.entity.ClassInformation;
import com.human.utils.PageView;

public interface ClassInformationService {
    /**
     * 分页查询班级数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,ClassInformation cf);
    
    /**
     * 保存班级数据
     * @param cf
     * @return
     */
    Map<String, Object> add(ClassInformation cf);
    
    /**
     * 根据主键查询班级数据
     * @param cf
     * @return
     */
    ClassInformation selectByPrimaryKey(ClassInformation cf);
    
    /**
     * 编辑班级数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(ClassInformation cf);
    
    
    /**
     * 删除班级数据
     * @param deleteIds
     * @param type
     * @return
     */
    Map<String, Object> delete(String deleteIds,int type);
    
    /**
     * 删除全部班级数据
     * @param cf
     * @return
     */
    Map<String, Object> deleteAll(ClassInformation cf);
    
    /**
     * 下载班级数据导入模板
     * @param request
     * @param response
     */
    void downLoadClassExcel(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 上传班级数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadClassExcel(ClassInformation cf,MultipartFile multipartFile);
    
    /**
     * 班级数据批量导出
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response);
    
    
    /**
     * 刷新班级数据
     * @param classCodes
     * @return
     */
    Map<String, Object> refresh(String classCodes);
    
    /**
     * 全部刷新班级数据
     * @param cf
     * @return
     */
    Map<String, Object> refreshAll(ClassInformation cf);
    
    /**
     * 通过班号获取班级学员
     * @param classCodes
     * @return
     */
    Map<String, Object> getStudentsByClassCode(String classCodes,Long ruleId);
    
    
    /**
     * 获取全部班级学员
     * @param cf
     * @return
     */
    Map<String, Object> getAllStudents(ClassInformation cf);
    
    /**
     * 删除全部学员
     * @param cf
     * @return
     */
    Map<String, Object> delAllStudents(ClassInformation cf);
    
    /**
     * 按开课日期获取全部学员
     * @param cf
     * @return
     */
    Map<String, Object> getAllStudentsByDate(ClassInformation cf);
}
