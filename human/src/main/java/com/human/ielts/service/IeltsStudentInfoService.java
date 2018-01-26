package com.human.ielts.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.ielts.entity.IeltsStudentInfo;
import com.human.utils.PageView;

public interface IeltsStudentInfoService {

    /**
     * 获取学生信息
     * @param ieltsStudentInfo
     * @return
     */
    public PageView getstudentinfo(PageView pageView, IeltsStudentInfo ieltsStudentInfo);

    /**
     * 批量删除学生信息和分数
     * @param ids
     * @return
     */
    public Map<String, Object> deletestudentinfo(String ids);

    /**
     * 新增学员基础信息
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> insertstudentinfo(IeltsStudentInfo ieltsStudentInfo,String classids, String bookids, String teachermails);

    /**
     * 批量上传学生信息和分数
     * @param request
     * @return
     */
    public Map<String, Object> upstudentinfo(HttpServletRequest request);

    /**
     * 修改学员基础信息
     * @param ieltsStudentInfo
     * @param classids
     * @param bookids
     * @param teachermails
     * @return
     */
    public Map<String, Object> updatestudentinfo(IeltsStudentInfo ieltsStudentInfo, String classids, String bookids, String teachermails);

    /**
     * 查询学员基础信息
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> selectstudentinfo(IeltsStudentInfo ieltsStudentInfo);

    /**
     * 查询学员报考率
     * @param ieltsStudentInfo
     * @return
     */
    public Map<String, Object> selectstudentapply(IeltsStudentInfo ieltsStudentInfo);

    /**
     * 获取教师学生信息
     * @param pageView
     * @param ieltsStudentInfo
     * @return
     */
    public PageView getteacherstudentinfo(PageView pageView, IeltsStudentInfo ieltsStudentInfo);

    public Map<String, Object> selectteacherstudentapply(IeltsStudentInfo ieltsStudentInfo);
}
