package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.human.jzbTest.entity.JzbSchool;
import com.human.utils.PageView;

public interface JzbSchoolService {
    /*
     * 分页查询尖子班公立学校数据
     * @param pageView
     * @param cf
     * @return
     */
    PageView query(PageView pageView,JzbSchool cf);
    
    /*
     * 新增尖子班公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> add(JzbSchool cf);
    
    /*
     * 通过主键查询尖子班公立学校数据
     * @param cf
     * @return
     */
    JzbSchool selectById(long id);
    
    /*
     * 通过区域及年级获取学校
     * @param cf
     * @return
     */
    List<JzbSchool> selectByAreaAndGrade(Integer areaId,Integer gradeId);
    
    /*
     * 编辑尖子班公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> edit(JzbSchool cf);
    
    /*
     * 删除尖子班公立学校数据
     * @param cf
     * @return
     */
    Map<String, Object> delete (String deleteIds);
    
    /*
     * 下载公立学校数据导入模板
     * @param request
     * @param response
     */
    void downLoadJzbSchoolExcel(HttpServletRequest request,HttpServletResponse response);
    
    /*
     * 上传公立学校数据
     * @param cf
     * @param multipartFile
     * @return
     */
    Map<String, Object> uploadLoadJzbSchoolExcel(MultipartFile multipartFile);
    
}
