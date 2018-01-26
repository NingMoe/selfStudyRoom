package com.human.continuedClass.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.human.continuedClass.entity.ClassMatch;
import com.human.continuedClass.entity.RecommendClass;
import com.human.continuedClass.entity.RuleBackPhoto;
import com.human.utils.PageView;

public interface ClassMatchService {

    /**
     * 分页查询匹配数据
     */
    PageView query(PageView pageView,ClassMatch cm);
    
    /**
     * 分页查询学员-班级匹配明细数据
     */
    PageView queryClassDetails(PageView pageView,ClassMatch cm);
    
    /**
     * 分页查询学员-推荐班级明细数据
     */
    PageView queryRecommendClass(PageView pageView,RecommendClass rc);
        
    /**
     * 上传续班卡背景图
     * @param rbp
     * @param multipartFile
     * @return
     */
    Map<String, Object> saveBackPhoto(RuleBackPhoto rbp,MultipartFile multipartFile);
    
    
    /**
     * 生成学员对班级数据
     * @param cm
     * @return
     */
    Map<String, Object> saveStudentsToClass(ClassMatch cm);
    
    /**
     * 生成学员推荐班级数据
     * @param cm
     * @return
     */
    Map<String, Object> saveRecommendClass(ClassMatch cm);
    
    /**
     * 导出班级-续班班级数据
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportClassToClass(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出班级-推荐班级数据
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportClassToRcClass(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出班级-升班班级数据
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportClassToUpClass(HttpServletRequest request, HttpServletResponse response);
   
    /**
     * 导出学员对班级数据
     * @param request
     * @param response
     * @return
     */
   Map<String, Object> exportStudentsToClass(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 删除学员-班级数据
    * @param deleteIds
    * @return
    */
   Map<String, Object> delete(String deleteIds);
   
   /**
    * 保存学员-班级配课
    * @param cm
    * @return
    */
   Map<String, Object> addStudentToClass(ClassMatch cm);
   
   /**
    * 删除学员-推荐班级数据
    * @param deleteIds
    * @return
    */
   Map<String, Object> deleteRc(String deleteIds);
   
   /**
    * 保存学员-推荐班级
    * @param cm
    * @return
    */
   Map<String, Object> addRecommendClass(RecommendClass rc);
   
   /**
    * 导出学员-配课数据
    * @param cm
    * @return
    */
   Map<String, Object> exportStudentsToCourse(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 批量导出续班卡
    * @param request
    * @param response
    * @return
    */
   Map<String, Object> bath_exportCardPdf(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 导出全部续班卡
    * @param request
    * @param response
    * @return
    */
   Map<String, Object>exportAllCard(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 判断是否已经上传续班卡背景图
    * @param ruleId
    * @return
    */
   Map<String, Object> checkHasBackPhoto(long ruleId);
   
   /**
    * 发送续班卡邮件
    * @param request
    * @param response
    * @return
    */
   Map<String, Object> sendCardMail(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 导出学员-推荐班级
    * @param request
    * @param response
    * @return
    */
   Map<String, Object> exportSelect(HttpServletRequest request, HttpServletResponse response);
   
   /**
    * 补发续班卡邮件
    * @param request
    * @param response
    * @return
    */
   Map<String, Object> sendFailCardMail(HttpServletRequest request, HttpServletResponse response);

}
