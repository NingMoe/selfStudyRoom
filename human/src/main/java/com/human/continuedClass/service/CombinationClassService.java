package com.human.continuedClass.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.human.continuedClass.entity.CombinationClass;
import com.human.utils.PageView;

public interface CombinationClassService {
    
    PageView query(PageView pageView,CombinationClass cc);
    
    Map<String, Object> add(CombinationClass cc);
    
    Map<String, Object> edit(CombinationClass cc);
         
    /**
     * 根据主键查询套餐数据
     * @param cf
     * @return
     */
    CombinationClass selectByPrimaryKey(CombinationClass cc);
    /**
     * 下载套餐数据导入模板
     * @param request
     * @param response
     */
    void downLoadClassExcel(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 上传套餐数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadClassExcel(CombinationClass cc,MultipartFile multipartFile);
    
    /**
     * 删除套餐数据
     * @param deleteIds
     * @param type
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 删除全部班套餐数据
     * @param cf
     * @return
     */
    Map<String, Object> deleteAll(CombinationClass cc);
    
    /**
     * 导出套餐数据
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response);
    
    
}

