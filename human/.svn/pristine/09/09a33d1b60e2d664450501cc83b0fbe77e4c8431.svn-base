package com.human.ielts.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.ielts.entity.IeltsTeacherArticle;
import com.human.ielts.entity.IeltsTeacherAttendance;
import com.human.ielts.entity.IeltsTeacherComplaint;
import com.human.ielts.entity.IeltsTeacherFeedback;
import com.human.ielts.entity.IeltsTeacherInfo;
import com.human.ielts.entity.IeltsTeacherMatchclass;
import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.ielts.entity.IeltsTeacherShare;
import com.human.ielts.entity.IeltsTeacherSource;
import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.utils.PageView;

public interface IeltsTeacherInfoService {

    /**
     * 分页获取教师信息
     * @param pageView
     * @param ieltsTeacherInfo
     * @return
     */
    public PageView queryteacher(PageView pageView, IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 新增教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> insertteacher(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 删除教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> deleteteacher(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 修改教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> updateteacher(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 查询教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacher(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 批量删除教师信息
     * @param ids
     * @return
     */
    public Map<String, Object> deleteteachers(String ids);

    
    /**
     * 分页获取教师积分信息
     * @param pageView
     * @param ieltsTeacherInfo
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherInfo ieltsTeacherInfo);
    
    /**
     * 获取单个教师积分详情信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherintegral(IeltsTeacherInfo ieltsTeacherInfo);
    
    /**
     * 获取教师积分信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherinfo(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 新增教师积分信息
     * @param ieltsTeacherInfo 教师基础信息
     * @param ieltsTeacherTkt 教师TKT分数
     * @param ieltsTeacherSource 教师雅思分数
     * @param ieltsTeacherCertificate 教师证书
     * @param ieltsTeacherAttendance 教师考勤
     * @param ieltsTeacherArticle 教师文章
     * @param ieltsTeacherShare 教师分享
     * @param ieltsTeacherOperate 教师运营
     * @param ieltsTeacherComplaint 教师投诉
     * @param ieltsTeacherFeedback 教师反馈
     * @return
     */
    public Map<String, Object> insertteacherinfo(IeltsTeacherInfo ieltsTeacherInfo, IeltsTeacherTkt ieltsTeacherTkt, IeltsTeacherSource ieltsTeacherSource,
            IeltsTeacherAttendance ieltsTeacherAttendance, IeltsTeacherArticle ieltsTeacherArticle, IeltsTeacherShare ieltsTeacherShare,
            IeltsTeacherOperate ieltsTeacherOperate, IeltsTeacherComplaint ieltsTeacherComplaint, IeltsTeacherFeedback ieltsTeacherFeedback);

    /**
     * 新增赛课信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> insertteachermatchclass(IeltsTeacherMatchclass ieltsTeacherMatchclass);
    
    /**
     * 上传excel
     * @param request
     * @return
     */
    public Map<String, Object> updatestudentinfo(HttpServletRequest request);
    
    /**
     * 上传往期数据excel
     * @param request
     * @return
     */
    public Map<String, Object> upteacherintegraldate(HttpServletRequest request);

    /**
     * 获取登录教师积分详情信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherselfintegral(IeltsTeacherInfo ieltsTeacherInfox);

    /**
     * 查询所有教师积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegral(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 查询教师教研积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralresearch(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 教学成果积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralteaching(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 查询教师教学服务积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralservice(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 查询教师总积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegraltotal(IeltsTeacherInfo ieltsTeacherInfo);

    /**
     * 上传积分excel
     * @param request
     * @return
     */
    public Map<String, Object> upteacherintegral(HttpServletRequest request);
    
    /**
     * 上传赛课excel
     * @param request
     * @return
     */
    public Map<String, Object> upteachermatchclass(HttpServletRequest request);

    /**
     * 上传往期赛课excel
     * @param request
     * @return
     */
    public Map<String, Object> upteachermatchclassdate(HttpServletRequest request);

    /**
     * 批量删除教师积分信息
     * @param ids
     * @return
     */
    public Map<String, Object> deletestudentinfo(String ids);

}
