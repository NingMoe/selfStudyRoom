package com.human.ielts.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.human.ielts.dao.IeltsStudentTeacherDao;
import com.human.ielts.dao.IeltsTeacherArticleDao;
import com.human.ielts.dao.IeltsTeacherAttendanceDao;
import com.human.ielts.dao.IeltsTeacherCertificateDao;
import com.human.ielts.dao.IeltsTeacherComplaintDao;
import com.human.ielts.dao.IeltsTeacherFeedbackDao;
import com.human.ielts.dao.IeltsTeacherInfoDao;
import com.human.ielts.dao.IeltsTeacherMatchclassDao;
import com.human.ielts.dao.IeltsTeacherOperateDao;
import com.human.ielts.dao.IeltsTeacherShareDao;
import com.human.ielts.dao.IeltsTeacherSourceDao;
import com.human.ielts.dao.IeltsTeacherTktDao;
import com.human.ielts.entity.IeltsEnrollInfo;
import com.human.ielts.entity.IeltsStudentTeacher;
import com.human.ielts.entity.IeltsTeacherArticle;
import com.human.ielts.entity.IeltsTeacherAttendance;
import com.human.ielts.entity.IeltsTeacherCertificate;
import com.human.ielts.entity.IeltsTeacherComplaint;
import com.human.ielts.entity.IeltsTeacherFeedback;
import com.human.ielts.entity.IeltsTeacherInfo;
import com.human.ielts.entity.IeltsTeacherInfoUp;
import com.human.ielts.entity.IeltsTeacherIntegralUp;
import com.human.ielts.entity.IeltsTeacherIntegraldateUp;
import com.human.ielts.entity.IeltsTeacherMatchclass;
import com.human.ielts.entity.IeltsTeacherMatchclassUp;
import com.human.ielts.entity.IeltsTeacherMatchclassdateUp;
import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.ielts.entity.IeltsTeacherShare;
import com.human.ielts.entity.IeltsTeacherSource;
import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.ielts.service.IeltsTeacherInfoService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.ExcelUtil;
import com.human.utils.PageView;

@Service
public class IeltsTeacherInfoServiceImpl implements IeltsTeacherInfoService {
    
    @Resource
    private IeltsTeacherInfoDao ieltsTeacherInfoDao;
    
    @Resource
    private IeltsStudentTeacherDao ieltsStudentTeacherDao;
    
    @Resource
    private IeltsTeacherTktDao ieltsTeacherTktDao;
    
    @Resource
    private IeltsTeacherSourceDao ieltsTeacherSourceDao;
    
    @Resource
    private IeltsTeacherCertificateDao ieltsTeacherCertificateDao;
    
    @Resource
    private IeltsTeacherAttendanceDao ieltsTeacherAttendanceDao;
    
    @Resource
    private IeltsTeacherArticleDao ieltsTeacherArticleDao;
    
    @Resource
    private IeltsTeacherShareDao ieltsTeacherShareDao;
    
    @Resource
    private IeltsTeacherOperateDao ieltsTeacherOperateDao;
    
    @Resource
    private IeltsTeacherComplaintDao ieltsTeacherComplaintDao;
    
    @Resource
    private IeltsTeacherFeedbackDao ieltsTeacherFeedbackDao;
    
    @Resource
    private IeltsTeacherMatchclassDao ieltsTeacherMatchclassDao;
    
    /**
     * 分页获取教师信息
     * @param pageView
     * @param ieltsTeacherInfo
     * @return
     */
    public PageView queryteacher(PageView pageView, IeltsTeacherInfo ieltsTeacherInfo) {
      //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherInfo);
        
        //查询教师信息列表
        try {
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.queryteacher(map);
            
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }

    /**
     * 新增教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> insertteacher(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请填写教师信息");
            return map;
        }
        
        if(ieltsTeacherInfo.getTeacher_name() == null){
            map.put("flag", false);
            map.put("message", "请填写教师姓名");
            return map;
        }
        
        if(ieltsTeacherInfo.getEmail_addr() == null){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀");
            return map;
        }
        
        if(ieltsTeacherInfo.getEmail_addr().indexOf("@") != -1){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀，@之前的拼音和数字");
            return map;
        }
        
        try {
            
            IeltsTeacherInfo iti = ieltsTeacherInfoDao.seleByEmailAddr(ieltsTeacherInfo.getEmail_addr());
            if(iti != null){
                map.put("flag", false);
                map.put("message", "已经创建该教师");
                return map;
            }
            
            ieltsTeacherInfo.setTeacher_mail(ieltsTeacherInfo.getEmail_addr()+"@xdf.cn");
            ieltsTeacherInfoDao.insertSelective(ieltsTeacherInfo);
            
            IeltsTeacherCertificate ieltsTeacherCertificate  = new IeltsTeacherCertificate();
            ieltsTeacherCertificate.setTeacher_id(ieltsTeacherInfo.getId());
            ieltsTeacherCertificate.setIs_celta(0);
            ieltsTeacherCertificate.setIs_teacher_certificate(0);
            ieltsTeacherCertificateDao.insertSelective(ieltsTeacherCertificate);
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增失败："+e);
        }
        return map;
    }

    /**
     * 删除教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> deleteteacher(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            //删除关联学生信息
            ieltsStudentTeacherDao.deleteByTeacherEmailaddr(ieltsTeacherInfo.getEmail_addr());
            
            //删除教研文章
            ieltsTeacherArticleDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除出勤信息
            ieltsTeacherAttendanceDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除证书信息
            ieltsTeacherCertificateDao.deleteByPrimaryKey(ieltsTeacherInfo.getId());
            
            //删除投诉
            ieltsTeacherComplaintDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除反馈信息
            ieltsTeacherFeedbackDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除赛课信息
            ieltsTeacherMatchclassDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除运维支持
            ieltsTeacherOperateDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除教学分享
            ieltsTeacherShareDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除雅思分数
            ieltsTeacherSourceDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除tkt成绩
            ieltsTeacherTktDao.deleteByTeacherId(ieltsTeacherInfo.getId());
            
            //删除基础信息
            ieltsTeacherInfoDao.deleteByPrimaryKey(ieltsTeacherInfo.getId());
            
            
            
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        
        return map;
    }

    /**
     * 修改教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> updateteacher(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getTeacher_name() == null){
            map.put("flag", false);
            map.put("message", "请填写教师姓名");
            return map;
        }
        
        if(ieltsTeacherInfo.getEmail_addr() == null){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀");
            return map;
        }
        
        if(ieltsTeacherInfo.getEmail_addr().indexOf("@") != -1){
            map.put("flag", false);
            map.put("message", "请填写教师邮箱前缀，@之前的拼音和数字");
            return map;
        }
        
        try {
            ieltsTeacherInfo.setTeacher_mail(ieltsTeacherInfo.getEmail_addr()+"@xdf.cn");
            ieltsTeacherInfoDao.updateByPrimaryKeySelective(ieltsTeacherInfo);
            map.put("flag", true);
            map.put("message", "修改成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "修改失败："+e);
        }
        
        return map;
    }

    /**
     * 查询教师信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacher(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByPrimaryKey(ieltsTeacherInfo.getId());
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ieltsTeacherInfo", ieltsTeacherInfo1);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败："+e);
        }
        
        return map;
    }

    /**
     * 批量删除教师信息
     * @param ids
     * @return
     */
    public Map<String, Object> deleteteachers(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                IeltsTeacherInfo ieltsTeacherInfo = new IeltsTeacherInfo();
                ieltsTeacherInfo.setId(id);
                Map<String, Object> mapparam1 = new HashMap<String, Object>();
                mapparam1.put("t", ieltsTeacherInfo);
                //数据库查询
                IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByTeacherId(mapparam1);
                //删除关联学生信息
                ieltsStudentTeacherDao.deleteByTeacherEmailaddr(ieltsTeacherInfo1.getEmail_addr());
                
                //删除教研文章
                ieltsTeacherArticleDao.deleteByTeacherId(id);
                
                //删除出勤信息
                ieltsTeacherAttendanceDao.deleteByTeacherId(id);
                
                //删除证书信息
                ieltsTeacherCertificateDao.deleteByPrimaryKey(id);
                
                //删除投诉
                ieltsTeacherComplaintDao.deleteByTeacherId(id);
                
                //删除反馈信息
                ieltsTeacherFeedbackDao.deleteByTeacherId(id);
                
                //删除赛课信息
                ieltsTeacherMatchclassDao.deleteByTeacherId(id);
                
                //删除运维支持
                ieltsTeacherOperateDao.deleteByTeacherId(id);
                
                //删除教学分享
                ieltsTeacherShareDao.deleteByTeacherId(id);
                
                //删除雅思分数
                ieltsTeacherSourceDao.deleteByTeacherId(id);
                
                //删除tkt成绩
                ieltsTeacherTktDao.deleteByTeacherId(id);
                
                //删除基础信息
                ieltsTeacherInfoDao.deleteByPrimaryKey(id);
            }
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        
        return map;
    }

    /**
     * 分页获取教师积分信息
     * @param pageView
     * @param ieltsTeacherInfo
     * @return
     */
    public PageView query(PageView pageView, IeltsTeacherInfo ieltsTeacherInfo) {
        //封装参数
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ieltsTeacherInfo);
        
        //查询教师信息列表
        try {
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.query(map);
            for(IeltsTeacherInfo ieltsteacherinfo : list){
                Map<String, Object> mapparam = new HashMap<String, Object>();
                mapparam.put("teacher_id", ieltsteacherinfo.getId());
                mapparam.put("email_addr", ieltsTeacherInfo.getEmail_addr());
                mapparam.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
                mapparam.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                
                //反馈
                List<IeltsTeacherFeedback> feedbacklist = ieltsTeacherFeedbackDao.selectByTeacherId(mapparam);
                if(feedbacklist != null && feedbacklist.size() > 0){
                    ieltsteacherinfo.setFeedbacklist(feedbacklist);
                }
                
                
                
                Integer enroll_num = 0;
                Integer not_enroll_num = 0;
                List<IeltsStudentTeacher> stlist = ieltsStudentTeacherDao.selectByTeacherId(mapparam);
                
                //成绩回收率
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            enroll_num++;
                        }else{
                            not_enroll_num++;
                        }
                    }
                    ieltsteacherinfo.setStlist(stlist);
                }
                ieltsteacherinfo.setEnroll_num(enroll_num);
                ieltsteacherinfo.setNot_enroll_num(not_enroll_num);
                
                //考试学员达分率
                Integer achieve_num = 0;
                Integer not_achieve_num = 0;
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            for(IeltsEnrollInfo ieltsEnrollInfo : ist.getIelts_enroll_info_list()){
                                if(ieltsEnrollInfo.getIs_target() != null && ieltsEnrollInfo.getIs_target() == 1){
                                    achieve_num++;
                                }else{
                                    not_achieve_num++;
                                }
                                
                            }
                        }
                    }
                }
                ieltsteacherinfo.setAchieve_num(achieve_num);
                ieltsteacherinfo.setNot_achieve_num(not_achieve_num);
                
                Integer hight_num = 0;
                //高分学员数
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            int x = 0;
                            for(IeltsEnrollInfo ielts_enroll_info : ist.getIelts_enroll_info_list()){
                                if(ielts_enroll_info.getTotal() != null && ielts_enroll_info.getTotal() >= 7){
                                    x = 1;
                                }
                            }
                            if(x == 1){
                                hight_num++;
                            }
                        }
                    }
                    ieltsteacherinfo.setStlist(stlist);
                }
                ieltsteacherinfo.setHight_num(hight_num);
                
                //赛课
                List<IeltsTeacherMatchclass> matchclasslist = ieltsTeacherMatchclassDao.selectByTeacherId(mapparam);
                if(matchclasslist != null && matchclasslist.size() > 0){
                    ieltsteacherinfo.setMatchclasslist(matchclasslist);
                }
                
            }
            //查询结果存入page对象
            pageView.setRecords(list);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pageView;
    }
    
    /**
     * 获取单个教师积分详情信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherintegral(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
      //查询教师信息列表
        try {
            Map<String, Object> mapparam1 = new HashMap<String, Object>();
            mapparam1.put("t", ieltsTeacherInfo);
            //数据库查询
            IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByTeacherId(mapparam1);
            if(ieltsTeacherInfo1 == null){
                map.put("flag", false);
                map.put("message", "没有查询到教师信息");
                return map;
            }
            
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("teacher_id", ieltsTeacherInfo.getId());
            mapparam.put("email_addr", ieltsTeacherInfo.getEmail_addr());
            mapparam.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
            mapparam.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                
            //反馈
            List<IeltsTeacherFeedback> feedbacklist = ieltsTeacherFeedbackDao.selectByTeacherId(mapparam);
            if(feedbacklist != null && feedbacklist.size() > 0){
                ieltsTeacherInfo1.setFeedbacklist(feedbacklist);
            }
                
            Integer enroll_num = 0;
            Integer not_enroll_num = 0;
            List<IeltsStudentTeacher> stlist = ieltsStudentTeacherDao.selectByTeacherId(mapparam);
                
            //成绩回收率
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        enroll_num++;
                    }else{
                        not_enroll_num++;
                    }
                }
                ieltsTeacherInfo1.setStlist(stlist);
            }
            ieltsTeacherInfo1.setEnroll_num(enroll_num);
            ieltsTeacherInfo1.setNot_enroll_num(not_enroll_num);
                
            //考试学员达分率
            Integer achieve_num = 0;
            Integer not_achieve_num = 0;
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        for(IeltsEnrollInfo ieltsEnrollInfo : ist.getIelts_enroll_info_list()){
                            if(ieltsEnrollInfo.getIs_target() != null && ieltsEnrollInfo.getIs_target() == 1){
                                achieve_num++;
                            }else{
                                not_achieve_num++; 
                            }
                        }
                    }
                }
            }
            ieltsTeacherInfo1.setAchieve_num(achieve_num);
            ieltsTeacherInfo1.setNot_achieve_num(not_achieve_num);

            Integer hight_num = 0;
            //高分学员数
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        int x = 0;
                        for(IeltsEnrollInfo ielts_enroll_info : ist.getIelts_enroll_info_list()){
                            if(ielts_enroll_info.getTotal() != null && ielts_enroll_info.getTotal() >= 7){
                                x = 1;
                            }
                        }
                        if(x == 1){
                            hight_num++;
                        }
                    }
                }
                ieltsTeacherInfo1.setStlist(stlist);
            }
            ieltsTeacherInfo1.setHight_num(hight_num);
            
            //赛课
            List<IeltsTeacherMatchclass> matchclasslist = ieltsTeacherMatchclassDao.selectByTeacherId(mapparam);
            if(matchclasslist != null && matchclasslist.size() > 0){
                ieltsTeacherInfo1.setMatchclasslist(matchclasslist);
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ieltsTeacherInfo", ieltsTeacherInfo1);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败 :" + e);
        }
        
        return map;
    }
    
    /**
     * 获取教师积分信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherinfo(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByPrimaryKey(ieltsTeacherInfo.getId());
            if(ieltsTeacherInfo1 == null){
                map.put("flag", false);
                map.put("message", "没有教师信息");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("ieltsTeacherInfo", ieltsTeacherInfo1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取教师信息失败："+e);
        }
        return map;
    }
    

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
            IeltsTeacherOperate ieltsTeacherOperate, IeltsTeacherComplaint ieltsTeacherComplaint, IeltsTeacherFeedback ieltsTeacherFeedback) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfo.getId() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            
            //新增TKT分数
            if(ieltsTeacherTkt != null && (ieltsTeacherTkt.getTkt_scoreone() != null || ieltsTeacherTkt.getTkt_scoretwo() != null || ieltsTeacherTkt.getTkt_scorethree() != null || ieltsTeacherTkt.getTkt_scorefour() != null)){
                ieltsTeacherTkt.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherTktDao.insertSelective(ieltsTeacherTkt);
            }
            
            //新增雅思分数
            if(ieltsTeacherSource != null && ieltsTeacherSource.getIelts_source() != null){
                ieltsTeacherSource.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherSourceDao.insertSelective(ieltsTeacherSource);
            }
            
            //新增出勤详情
            if(ieltsTeacherAttendance != null && ieltsTeacherAttendance.getAbb_num() != null && ieltsTeacherAttendance.getDuty_num() != null){
                ieltsTeacherAttendance.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherAttendanceDao.insertSelective(ieltsTeacherAttendance);
            }
            
            //新增教研文章
            if(ieltsTeacherArticle != null && ieltsTeacherArticle.getArticle_num() != null){
                ieltsTeacherArticle.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherArticleDao.insertSelective(ieltsTeacherArticle);
            }
            
            //新增教学分享
            if(ieltsTeacherShare != null && ieltsTeacherShare.getShare_num() != null){
                ieltsTeacherShare.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherShareDao.insertSelective(ieltsTeacherShare);
            }
            
            //新增运营支持
            if(ieltsTeacherOperate != null && ieltsTeacherOperate.getOperate_num() != null){
                ieltsTeacherOperate.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherOperateDao.insertSelective(ieltsTeacherOperate);
            }
            
            //新增教学投诉
            if(ieltsTeacherComplaint != null && ieltsTeacherComplaint.getComplaint_num() != null){
                ieltsTeacherComplaint.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherComplaintDao.insertSelective(ieltsTeacherComplaint);
            }
            
            //新增教学反馈
            if(ieltsTeacherFeedback != null && ieltsTeacherFeedback.getFeedback_num() != null){
                ieltsTeacherFeedback.setTeacher_id(ieltsTeacherInfo.getId());
                ieltsTeacherFeedbackDao.insertSelective(ieltsTeacherFeedback);
            }
            
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增教师积分失败："+e);
        }
        
        
        
        return map;
    }

    /**
     * 新增赛课信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> insertteachermatchclass(IeltsTeacherMatchclass ieltsTeacherMatchclass) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ieltsTeacherMatchclass == null){
            map.put("flag", false);
            map.put("message", "请填写信息");
            return map;
        }
        
        if(ieltsTeacherMatchclass.getTeacher_id() == null){
            map.put("flag", false);
            map.put("message", "请选择教师信息");
            return map;
        }
        
        if(StringUtils.isEmpty(ieltsTeacherMatchclass.getMatchclass_name())){
            map.put("flag", false);
            map.put("message", "请填写赛课名称");
            return map;
        }
        
        
        try {
            ieltsTeacherMatchclassDao.insertSelective(ieltsTeacherMatchclass);
            map.put("flag", true);
            map.put("message", "新增成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增赛课信息失败：" + e);
        }
        
        return map;
    }

    /**
     * 上传excel
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> updatestudentinfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsTeacherInfoUp> ex=new ExcelUtil<IeltsTeacherInfoUp>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsTeacherInfoUp.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errorMessage"));
                return map;
            }
            
            List<IeltsTeacherInfoUp> list=(List<IeltsTeacherInfoUp>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsTeacherInfoUp ti : list){
                if(ti== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getTeacher_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行教师姓名为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行邮箱前缀为空");
                    return map;
                }else if(ti.getEmail_addr().indexOf("@") != -1){
                    map.put("flag", false);
                    map.put("message", "请填写教师邮箱前缀，@之前的拼音和数字");
                    return map;
                }else{
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(ti.getEmail_addr());
                    IeltsTeacherInfo iti = ieltsTeacherInfoDao.seleByEmailAddr(ieltsTeacherInfo.getEmail_addr());
                    if(iti != null){
                        map.put("flag", false);
                        msg += "已经创建教师："+ti.getEmail_addr();
                    }else{
                        ieltsTeacherInfo.setTeacher_mail(ieltsTeacherInfo.getEmail_addr()+"@xdf.cn");
                        ieltsTeacherInfoDao.insertSelective(ieltsTeacherInfo);
                        
                        IeltsTeacherCertificate ieltsTeacherCertificate  = new IeltsTeacherCertificate();
                        ieltsTeacherCertificate.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherCertificate.setIs_celta(0);
                        ieltsTeacherCertificate.setIs_teacher_certificate(0);
                        ieltsTeacherCertificateDao.insertSelective(ieltsTeacherCertificate);
                        n++; 
                    }
                    
                    
                }
            }
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
            map.put("flag", flag);
            map.put("message", msg);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", flag);
            map.put("message", msg);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    /**
     * 获取登录教师积分详情信息
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectteacherselfintegral(IeltsTeacherInfo ieltsTeacherInfox) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherInfox == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherInfox.getLeft_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择最小时间");
            return map;
        }
        if(ieltsTeacherInfox.getRight_integral_time() == null){
            map.put("flag", false);
            map.put("message", "请选择最小时间");
            return map;
        }
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(myUser.getEmailAddr());
        if(ieltsTeacherInfo == null){
            map.put("flag", false);
            map.put("message", "请先在系统初始化该教师");
            return map;
        }
        ieltsTeacherInfo.setLeft_integral_time(ieltsTeacherInfox.getLeft_integral_time());
        ieltsTeacherInfo.setRight_integral_time(ieltsTeacherInfox.getRight_integral_time());
        //查询教师信息列表
        try {
            Map<String, Object> mapparam1 = new HashMap<String, Object>();
            mapparam1.put("t", ieltsTeacherInfo);
            //数据库查询
            IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByTeacherId(mapparam1);
            if(ieltsTeacherInfo1 == null){
                map.put("flag", false);
                map.put("message", "没有查询到教师信息");
                return map;
            }
            
            Map<String, Object> mapparam = new HashMap<String, Object>();
            mapparam.put("teacher_id", ieltsTeacherInfo.getId());
            mapparam.put("email_addr", ieltsTeacherInfo.getEmail_addr());
            mapparam.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
            mapparam.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                
            //反馈
            List<IeltsTeacherFeedback> feedbacklist = ieltsTeacherFeedbackDao.selectByTeacherId(mapparam);
            if(feedbacklist != null && feedbacklist.size() > 0){
                ieltsTeacherInfo1.setFeedbacklist(feedbacklist);
            }
                
                
                
            Integer enroll_num = 0;
            Integer not_enroll_num = 0;
            List<IeltsStudentTeacher> stlist = ieltsStudentTeacherDao.selectByTeacherId(mapparam);
                
            //成绩回收率
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        enroll_num++;
                    }else{
                        not_enroll_num++;
                    }
                }
                ieltsTeacherInfo1.setStlist(stlist);
            }
            ieltsTeacherInfo1.setEnroll_num(enroll_num);
            ieltsTeacherInfo1.setNot_enroll_num(not_enroll_num);
                
            //考试学员达分率
            Integer achieve_num = 0;
            Integer not_achieve_num = 0;
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        for(IeltsEnrollInfo ieltsEnrollInfo : ist.getIelts_enroll_info_list()){
                            if(ieltsEnrollInfo.getIs_target() != null && ieltsEnrollInfo.getIs_target() == 1){
                                achieve_num++;  
                            }else{
                                not_achieve_num++;
                            }
                        }
                    }
                }
            }
            ieltsTeacherInfo1.setAchieve_num(achieve_num);
            ieltsTeacherInfo1.setNot_achieve_num(not_achieve_num);
                
            Integer hight_num = 0;
            //高分学员数
            if(stlist != null && stlist.size() > 0){
                for(IeltsStudentTeacher ist :stlist){
                    if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                        int x = 0;
                        for(IeltsEnrollInfo ielts_enroll_info : ist.getIelts_enroll_info_list()){
                            if(ielts_enroll_info.getTotal() != null && ielts_enroll_info.getTotal() >= 7){
                                x = 1;
                            }
                        }
                        if(x == 1){
                            hight_num++;
                        }
                    }
                }
                ieltsTeacherInfo1.setStlist(stlist);
            }
            ieltsTeacherInfo1.setHight_num(hight_num);
            
            //赛课
            List<IeltsTeacherMatchclass> matchclasslist = ieltsTeacherMatchclassDao.selectByTeacherId(mapparam);
            if(matchclasslist != null && matchclasslist.size() > 0){
                ieltsTeacherInfo1.setMatchclasslist(matchclasslist);
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ieltsTeacherInfo", ieltsTeacherInfo1);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败 :" + e);
        }
        
        return map;
    }

    /**
     * 查询所有教师积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegral(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //封装参数
            Map<Object, Object> mapparam = new HashMap<Object, Object>();
            mapparam.put("t", ieltsTeacherInfo);
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.selectselectallteacherintegral(mapparam);
            Integer teacher_num = 0;
            Integer sum_integer = 0;
            Integer avg_integer = 0;
            Integer max_integral = 0;
            String max_teacher_name = "";
            String max_email_addr = "";
            for(IeltsTeacherInfo ieltsteacherinfo : list){
                Integer integral = 0;
                if(ieltsteacherinfo.getTkt_scoreone() != null){
                    if(ieltsteacherinfo.getTkt_scoreone() == 3){
                        integral += 1;
                    }
                    
                    if(ieltsteacherinfo.getTkt_scoreone() >= 4){
                        integral += 3;
                    }
                }
                
                if(ieltsteacherinfo.getTkt_scoretwo() != null){
                    if(ieltsteacherinfo.getTkt_scoretwo() == 3){
                        integral += 1;
                    }
                    
                    if(ieltsteacherinfo.getTkt_scoretwo() >= 4){
                        integral += 3;
                    }
                }
                
                if(ieltsteacherinfo.getTkt_scorethree() != null){
                    if(ieltsteacherinfo.getTkt_scorethree() == 3){
                        integral += 1;
                    }
                    
                    if(ieltsteacherinfo.getTkt_scorethree() >= 4){
                        integral += 3;
                    }
                }
                
                
                if(ieltsteacherinfo.getTkt_scorefour() != null){
                    if(ieltsteacherinfo.getTkt_scorefour() == 3){
                        integral += 1;
                    }
                    
                    if(ieltsteacherinfo.getTkt_scorefour() >= 4){
                        integral += 3;
                    }
                }
                
                if(ieltsteacherinfo.getIelts_source() != null){
                    if(ieltsteacherinfo.getIelts_source() == 7){
                        integral += 5;
                    }
                    
                    if(ieltsteacherinfo.getIelts_source() == 7.5){
                        integral += 10;
                    }
                    if(ieltsteacherinfo.getIelts_source() == 8){
                        integral += 15;
                    }
                    if(ieltsteacherinfo.getIelts_source() == 8.5){
                        integral += 20;
                    }
                }
                
                if(ieltsteacherinfo.getIs_teacher_certificate() == 1){
                    integral += 5;
                }
                
                if(ieltsteacherinfo.getIs_celta() == 1){
                    integral += 10;
                }
                
                if(integral > max_integral){
                    max_integral = integral;
                    max_teacher_name = ieltsteacherinfo.getTeacher_name();
                    max_email_addr = ieltsteacherinfo.getEmail_addr();
                }
                teacher_num ++;
                sum_integer += integral;
            }
            if(teacher_num != 0){
                avg_integer = sum_integer / teacher_num;
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("avg_integer", avg_integer);
            map.put("max_integral", max_integral);
            map.put("max_teacher_name", max_teacher_name);
            map.put("max_email_addr", max_email_addr);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取失败:"+e);
        }
        return map;
    }

    /**
     * 查询教师教研积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralresearch(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //封装参数
            Map<Object, Object> mapparam = new HashMap<Object, Object>();
            
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.selectAllTeacherInfo();
            Integer teacher_num = 0;
            Integer sum_integer = 0;
            Integer avg_integer = 0;
            Integer max_integral = -100;
            String max_teacher_name = "";
            String max_email_addr = "";
            for(IeltsTeacherInfo ieltsteacherinfo : list){
                Integer integral = 0;
                ieltsTeacherInfo.setId(ieltsteacherinfo.getId());
                mapparam.put("t", ieltsTeacherInfo);
                
                //出勤率
                List<IeltsTeacherAttendance> ieltsTeacherAttendancelist = ieltsTeacherAttendanceDao.selectByTeacherId(mapparam);
                Integer abb_num = 0;
                Integer duty_num = 0;
                for(IeltsTeacherAttendance att : ieltsTeacherAttendancelist){
                    if(att != null){
                        if(att.getAbb_num() != null){
                            abb_num += att.getAbb_num();
                        }
                        
                        if(att.getDuty_num() != null){
                            duty_num += att.getDuty_num();
                        }
                    }
                }
                if(abb_num != 0 || duty_num != 0){
                    Integer a = abb_num * 100 / (abb_num + duty_num);
                    if(a >= 90){
                        integral += 7;
                    }else if(a >= 80){
                        integral += 5;
                    }else if(a < 60){
                        integral -= 10;
                    }
                }
                
                //教研文章
                List<IeltsTeacherArticle> ieltsTeacherArticlelist = ieltsTeacherArticleDao.selectByTeacherId(mapparam);
                Integer art_num = 0;
                for(IeltsTeacherArticle art : ieltsTeacherArticlelist){
                    if(art != null && art.getArticle_num() != null){
                        art_num += art.getArticle_num();
                    }
                }
                if(art_num >= 1000){
                    integral += 3;
                }else if(art_num >= 500){
                    integral += 2;
                }else if(art_num >= 300){
                    integral += 1;
                }
                
                //教研分享
                List<IeltsTeacherShare> ieltsTeacherSharelist = ieltsTeacherShareDao.selectByTeacherId(mapparam);
                Integer share_num = 0;
                for(IeltsTeacherShare share_my : ieltsTeacherSharelist){
                    if(share_my != null && share_my.getShare_num() != null){
                        share_num += share_my.getShare_num();
                    }
                }
                
                if(share_num > 0){
                    integral += 3;
                }
                
                if(integral > max_integral){
                    max_integral = integral;
                    max_teacher_name = ieltsteacherinfo.getTeacher_name();
                    max_email_addr = ieltsteacherinfo.getEmail_addr();
                }
                teacher_num ++;
                sum_integer += integral;
            }
            if(teacher_num != 0){
                avg_integer = sum_integer / teacher_num;
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("avg_integer", avg_integer);
            map.put("max_integral", max_integral);
            map.put("max_teacher_name", max_teacher_name);
            map.put("max_email_addr", max_email_addr);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取失败:"+e);
        }
        return map;
    }

    /**
     * 教学成果积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralteaching(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //封装参数
            Map<Object, Object> mapparam = new HashMap<Object, Object>();
            
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.selectAllTeacherInfo();
            Integer teacher_num = 0;
            Integer sum_integer = 0;
            Integer avg_integer = 0;
            Integer max_integral = 0;
            String max_teacher_name = "";
            String max_email_addr = "";
            for(IeltsTeacherInfo ieltsteacherinfo : list){
                Integer integral = 0;
                ieltsTeacherInfo.setId(ieltsteacherinfo.getId());
                mapparam.put("t", ieltsTeacherInfo);
                
                
                
                Map<String, Object> mapparam1 = new HashMap<String, Object>();
                mapparam1.put("teacher_id", ieltsteacherinfo.getId());
                mapparam.put("email_addr", ieltsTeacherInfo.getEmail_addr());
                mapparam1.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
                mapparam1.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                
                Integer enroll_num = 0;
                Integer not_enroll_num = 0;
                List<IeltsStudentTeacher> stlist = ieltsStudentTeacherDao.selectByTeacherId(mapparam1);
                
                //成绩回收率
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            enroll_num++;
                        }else{
                            not_enroll_num++;
                        }
                    }
                    
                    Integer i = 0;
                    if(enroll_num != 0 || not_enroll_num != 0){
                        i = enroll_num * 100 / (enroll_num + not_enroll_num);
                    }
                    
                    if(i == 100){
                        integral += 10;
                    }else if(i >= 90){
                        integral += 5;
                    }else if(i >= 80){
                        integral += 2;
                    }
                    
                }
                
                //考试学员达分率
                Integer achieve_num = 0;
                Integer not_achieve_num = 0;
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            for(IeltsEnrollInfo ieltsEnrollInfo : ist.getIelts_enroll_info_list()){
                                if(ieltsEnrollInfo.getIs_target() != null && ieltsEnrollInfo.getIs_target() == 1){
                                    achieve_num++;
                                }else{
                                    not_achieve_num++;
                                }
                            }
                        }
                    }
                }
                Integer acc = 0;
                if(achieve_num != 0 || not_achieve_num != 0){
                    acc = achieve_num * 100 / (achieve_num + not_achieve_num);
                }
                if(acc >= 80){
                    integral += 20;
                }else if(acc >= 70){
                    integral += 15;
                }else if(acc >= 60){
                    integral += 10;
                }else if(acc >= 50){
                    integral += 5;
                }
                
                
                //高分学员数
                Integer hight_num = 0;
                if(stlist != null && stlist.size() > 0){
                    for(IeltsStudentTeacher ist :stlist){
                        if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                            int x = 0;
                            for(IeltsEnrollInfo ielts_enroll_info : ist.getIelts_enroll_info_list()){
                                if(ielts_enroll_info.getTotal() != null && ielts_enroll_info.getTotal() >= 7){
                                    x = 1;
                                }
                            }
                            if(x == 1){
                                hight_num++;
                            }
                        }
                    }
                    
                    if(hight_num >= 1 && hight_num <= 5){
                        integral += 2;
                    }else if(hight_num >= 6){
                        integral += 5;
                    }
                }
                
                //赛课
                List<IeltsTeacherMatchclass> matchclasslist = ieltsTeacherMatchclassDao.selectByTeacherId(mapparam1);
                if(matchclasslist != null && matchclasslist.size() > 0){
                    for(IeltsTeacherMatchclass mc : matchclasslist){
                        if(mc.getMatchclass_type() == 3){
                            if(mc.getMatchclass_grade() == 1){
                                integral += 5;
                            }
                            
                            if(mc.getMatchclass_grade() == 2){
                                integral += 3;
                            }
                            
                            if(mc.getMatchclass_grade() == 3){
                                integral += 2;
                            }
                        }
                        
                        if(mc.getMatchclass_type() == 2){
                            if(mc.getMatchclass_grade() == 1){
                                integral += 8;
                            }
                            
                            if(mc.getMatchclass_grade() == 2){
                                integral += 5;
                            }
                            
                            if(mc.getMatchclass_grade() == 3){
                                integral += 3;
                            }
                        }
                        
                        
                        if(mc.getMatchclass_type() == 1){
                            if(mc.getMatchclass_grade() == 1){
                                integral += 15;
                            }
                            
                            if(mc.getMatchclass_grade() == 2){
                                integral += 10;
                            }
                            
                            if(mc.getMatchclass_grade() == 3){
                                integral += 8;
                            }
                        }
                    }
                }
                
                if(integral > max_integral){
                    max_integral = integral;
                    max_teacher_name = ieltsteacherinfo.getTeacher_name();
                    max_email_addr = ieltsteacherinfo.getEmail_addr();
                }
                teacher_num ++;
                sum_integer += integral;
            }
            
            
            if(teacher_num != 0){
                avg_integer = sum_integer / teacher_num;
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("avg_integer", avg_integer);
            map.put("max_integral", max_integral);
            map.put("max_teacher_name", max_teacher_name);
            map.put("max_email_addr", max_email_addr);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取失败:"+e);
        }
        return map;
    }

    /**
     * 查询教师教学服务积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegralservice(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //封装参数
            Map<Object, Object> mapparam = new HashMap<Object, Object>();
            
            //数据库查询
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.selectAllTeacherInfo();
            Integer teacher_num = 0;
            Integer sum_integer = 0;
            Integer avg_integer = 0;
            Integer max_integral = -1000;
            String max_teacher_name = "";
            String max_email_addr = "";
            for(IeltsTeacherInfo ieltsteacherinfo : list){
                Integer integral = 0;
                ieltsTeacherInfo.setId(ieltsteacherinfo.getId());
                mapparam.put("t", ieltsTeacherInfo);
                
                Map<String, Object> mapparam1 = new HashMap<String, Object>();
                mapparam1.put("teacher_id", ieltsteacherinfo.getId());
                mapparam1.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
                mapparam1.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                
                //运营支持
                List<IeltsTeacherOperate> ieltsTeacherOperatelist = ieltsTeacherOperateDao.selectByTeacherId(mapparam1);
                if(ieltsTeacherOperatelist != null && ieltsTeacherOperatelist.size() > 0){
                    Integer op_num = 0;
                    for(IeltsTeacherOperate to : ieltsTeacherOperatelist){
                       if(to.getOperate_num() > 0){
                           op_num = 1;
                       }
                    }
                    if(op_num == 1){
                        integral += 2;
                    }
                }
                
                //反馈
                List<IeltsTeacherFeedback> feedbacklist = ieltsTeacherFeedbackDao.selectByTeacherId(mapparam1);
                if(feedbacklist != null && feedbacklist.size() > 0){
                    Integer feed_back = 0;
                    for(IeltsTeacherFeedback tfeedback : feedbacklist){
                        if(tfeedback.getFeedback_num() >=2){
                            feed_back++;
                        }
                    }
                    integral -= feed_back * 5;
                }
                
                //投诉
                List<IeltsTeacherComplaint> complaintlist = ieltsTeacherComplaintDao.selectByTeacherId(mapparam1);
                if(complaintlist != null && complaintlist.size() > 0){
                    for(IeltsTeacherComplaint tc : complaintlist){
                        integral -= tc.getComplaint_num() * 5;
                    }
                }
                
                
                if(integral > max_integral){
                    max_integral = integral;
                    max_teacher_name = ieltsteacherinfo.getTeacher_name();
                    max_email_addr = ieltsteacherinfo.getEmail_addr();
                }
                teacher_num ++;
                sum_integer += integral;
            }
            
            
            if(teacher_num != 0){
                avg_integer = sum_integer / teacher_num;
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("avg_integer", avg_integer);
            map.put("max_integral", max_integral);
            map.put("max_teacher_name", max_teacher_name);
            map.put("max_email_addr", max_email_addr);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取失败:"+e);
        }
        return map;
    }

    /**
     * 查询教师总积分
     * @param ieltsTeacherInfo
     * @return
     */
    public Map<String, Object> selectallteacherintegraltotal(IeltsTeacherInfo ieltsTeacherInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //封装参数
            Map<Object, Object> mapparam = new HashMap<Object, Object>();
            
            List<IeltsTeacherInfo> list = ieltsTeacherInfoDao.selectselectallteacherintegral(mapparam);
            Integer teacher_num = 0;
            Integer sum_integer = 0;
            Integer avg_integer = 0;
            Integer min_integral = 1000;
            Integer max_integral = 0;
            String max_teacher_name = "";
            String max_email_addr = "";
            if(list != null & list.size() > 0){
                for(IeltsTeacherInfo ieltsteacherinfo : list){
                    Integer integral = 0;
                    ieltsTeacherInfo.setId(ieltsteacherinfo.getId());
                    mapparam.put("t", ieltsTeacherInfo);
                    
                    //功底
                    if(ieltsteacherinfo.getTkt_scoreone() != null){
                        if(ieltsteacherinfo.getTkt_scoreone() == 3){
                            integral += 1;
                        }
                        
                        if(ieltsteacherinfo.getTkt_scoreone() >= 4){
                            integral += 3;
                        }
                    }
                    
                    if(ieltsteacherinfo.getTkt_scoretwo() != null){
                        if(ieltsteacherinfo.getTkt_scoretwo() == 3){
                            integral += 1;
                        }
                        
                        if(ieltsteacherinfo.getTkt_scoretwo() >= 4){
                            integral += 3;
                        }
                    }
                    
                    if(ieltsteacherinfo.getTkt_scorethree() != null){
                        if(ieltsteacherinfo.getTkt_scorethree() == 3){
                            integral += 1;
                        }
                        
                        if(ieltsteacherinfo.getTkt_scorethree() >= 4){
                            integral += 3;
                        }
                    }
                    
                    if(ieltsteacherinfo.getTkt_scorefour() != null){
                        if(ieltsteacherinfo.getTkt_scorefour() == 3){
                            integral += 1;
                        }
                        
                        if(ieltsteacherinfo.getTkt_scorefour() >= 4){
                            integral += 3;
                        }
                    }
                    
                    if(ieltsteacherinfo.getIelts_source() != null){
                        if(ieltsteacherinfo.getIelts_source() == 7){
                            integral += 5;
                        }
                        
                        if(ieltsteacherinfo.getIelts_source() == 7.5){
                            integral += 10;
                        }
                        if(ieltsteacherinfo.getIelts_source() == 8){
                            integral += 15;
                        }
                        if(ieltsteacherinfo.getIelts_source() == 8.5){
                            integral += 20;
                        }
                    }
                    
                    if(ieltsteacherinfo.getIs_teacher_certificate() == 1){
                        integral += 5;
                    }
                    
                    if(ieltsteacherinfo.getIs_celta() == 1){
                        integral += 10;
                    }
                    
                    
                    //出勤率
                    List<IeltsTeacherAttendance> ieltsTeacherAttendancelist = ieltsTeacherAttendanceDao.selectByTeacherId(mapparam);
                    Integer abb_num = 0;
                    Integer duty_num = 0;
                    for(IeltsTeacherAttendance att : ieltsTeacherAttendancelist){
                        if(att != null){
                            if(att.getAbb_num() != null){
                                abb_num += att.getAbb_num();
                            }
                            
                            if(att.getDuty_num() != null){
                                duty_num += att.getDuty_num();
                            }
                        }
                    }
                    if(abb_num != 0 || duty_num != 0){
                        Integer a = abb_num * 100 / (abb_num + duty_num);
                        if(a >= 90){
                            integral += 7;
                        }else if(a >= 80){
                            integral += 5;
                        }else if(a < 60){
                            integral -= 10;
                        }
                    }
                    
                    //教研文章
                    List<IeltsTeacherArticle> ieltsTeacherArticlelist = ieltsTeacherArticleDao.selectByTeacherId(mapparam);
                    Integer art_num = 0;
                    for(IeltsTeacherArticle art : ieltsTeacherArticlelist){
                        if(art != null && art.getArticle_num() != null){
                            art_num += art.getArticle_num();
                        }
                    }
                    if(art_num >= 1000){
                        integral += 3;
                    }else if(art_num >= 500){
                        integral += 2;
                    }else if(art_num >= 300){
                        integral += 1;
                    }
                    
                    //教研分享
                    List<IeltsTeacherShare> ieltsTeacherSharelist = ieltsTeacherShareDao.selectByTeacherId(mapparam);
                    Integer share_num = 0;
                    for(IeltsTeacherShare share_my : ieltsTeacherSharelist){
                        if(share_my != null && share_my.getShare_num() != null){
                            share_num += share_my.getShare_num();
                        }
                    }
                    
                    if(share_num > 0){
                        integral += 3;
                    }
                    
                    Map<String, Object> mapparam1 = new HashMap<String, Object>();
                    mapparam1.put("teacher_id", ieltsteacherinfo.getId());
                    mapparam.put("email_addr", ieltsTeacherInfo.getEmail_addr());
                    mapparam1.put("left_integral_time", ieltsTeacherInfo.getLeft_integral_time());
                    mapparam1.put("right_integral_time", ieltsTeacherInfo.getRight_integral_time());
                    
                    Integer enroll_num = 0;
                    Integer not_enroll_num = 0;
                    List<IeltsStudentTeacher> stlist = ieltsStudentTeacherDao.selectByTeacherId(mapparam1);
                    
                    //成绩回收率
                    if(stlist != null && stlist.size() > 0){
                        for(IeltsStudentTeacher ist :stlist){
                            if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                                enroll_num++;
                            }else{
                                not_enroll_num++;
                            }
                        }
                        
                        Integer i = 0;
                        if(enroll_num != 0 || not_enroll_num != 0){
                            i = enroll_num * 100 / (enroll_num + not_enroll_num);
                        }
                        
                        if(i == 100){
                            integral += 10;
                        }else if(i >= 90){
                            integral += 5;
                        }else if(i >= 80){
                            integral += 2;
                        }
                        
                    }
                    
                    //考试学员达分率
                    Integer achieve_num = 0;
                    Integer not_achieve_num = 0;
                    if(stlist != null && stlist.size() > 0){
                        for(IeltsStudentTeacher ist :stlist){
                            if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                                for(IeltsEnrollInfo ieltsEnrollInfo : ist.getIelts_enroll_info_list()){
                                    if(ieltsEnrollInfo.getIs_target() != null && ieltsEnrollInfo.getIs_target() == 1){
                                        achieve_num++;
                                    }else{
                                        not_achieve_num++;
                                    }
                                    
                                }
                            }
                        }
                    }
                    Integer acc = 0;
                    if(achieve_num != 0 || not_achieve_num != 0){
                        acc = achieve_num * 100 / (achieve_num + not_achieve_num);
                    }
                    if(acc >= 80){
                        integral += 20;
                    }else if(acc >= 70){
                        integral += 15;
                    }else if(acc >= 60){
                        integral += 10;
                    }else if(acc >= 50){
                        integral += 5;
                    }
                    
                    
                    //高分学员数
                    Integer hight_num = 0;
                    if(stlist != null && stlist.size() > 0){
                        for(IeltsStudentTeacher ist :stlist){
                            if(ist.getIelts_enroll_info_list() != null && ist.getIelts_enroll_info_list().size() > 0){
                                int x = 0;
                                for(IeltsEnrollInfo ielts_enroll_info : ist.getIelts_enroll_info_list()){
                                    if(ielts_enroll_info.getTotal() != null && ielts_enroll_info.getTotal() >= 7){
                                        x = 1;
                                    }
                                }
                                if(x == 1){
                                    hight_num++;
                                }
                            }
                        }
                        
                        if(hight_num >= 1 && hight_num <= 5){
                            integral += 2;
                        }else if(hight_num >= 6){
                            integral += 5;
                        }
                    }
                    
                    //赛课
                    List<IeltsTeacherMatchclass> matchclasslist = ieltsTeacherMatchclassDao.selectByTeacherId(mapparam1);
                    if(matchclasslist != null && matchclasslist.size() > 0){
                        for(IeltsTeacherMatchclass mc : matchclasslist){
                            if(mc != null && mc.getMatchclass_type() != null && mc.getMatchclass_grade() != null){
                                if(mc.getMatchclass_type() == 3){
                                    if(mc.getMatchclass_grade() == 1){
                                        integral += 5;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 2){
                                        integral += 3;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 3){
                                        integral += 2;
                                    }
                                }
                                
                                if(mc.getMatchclass_type() == 2){
                                    if(mc.getMatchclass_grade() == 1){
                                        integral += 8;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 2){
                                        integral += 5;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 3){
                                        integral += 3;
                                    }
                                }
                                
                                
                                if(mc.getMatchclass_type() == 1){
                                    if(mc.getMatchclass_grade() == 1){
                                        integral += 15;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 2){
                                        integral += 10;
                                    }
                                    
                                    if(mc.getMatchclass_grade() == 3){
                                        integral += 8;
                                    }
                                }
                            }
                        }
                    }
                    
                    //运营支持
                    List<IeltsTeacherOperate> ieltsTeacherOperatelist = ieltsTeacherOperateDao.selectByTeacherId(mapparam1);
                    if(ieltsTeacherOperatelist != null && ieltsTeacherOperatelist.size() > 0){
                        Integer op_num = 0;
                        for(IeltsTeacherOperate to : ieltsTeacherOperatelist){
                           if(to != null && to.getOperate_num() != null && to.getOperate_num() > 0){
                               op_num = 1;
                           }
                        }
                        if(op_num == 1){
                            integral += 2;
                        }
                    }
                    
                    //反馈
                    List<IeltsTeacherFeedback> feedbacklist = ieltsTeacherFeedbackDao.selectByTeacherId(mapparam1);
                    if(feedbacklist != null && feedbacklist.size() > 0){
                        Integer feed_back = 0;
                        for(IeltsTeacherFeedback tfeedback : feedbacklist){
                            if(tfeedback!= null && tfeedback.getFeedback_num() != null && tfeedback.getFeedback_num() >=2){
                                feed_back++;
                            }
                        }
                        integral -= feed_back * 5;
                    }
                    
                    //投诉
                    List<IeltsTeacherComplaint> complaintlist = ieltsTeacherComplaintDao.selectByTeacherId(mapparam1);
                    if(complaintlist != null && complaintlist.size() > 0){
                        for(IeltsTeacherComplaint tc : complaintlist){
                            if(tc != null && tc.getComplaint_num() != null){
                                integral -= tc.getComplaint_num() * 5;
                            }
                        }
                    }
                    
                    if(integral < min_integral){
                        min_integral = integral;
                    }
                    
                    if(integral > max_integral){
                        max_integral = integral;
                        max_teacher_name = ieltsteacherinfo.getTeacher_name();
                        max_email_addr = ieltsteacherinfo.getEmail_addr();
                    }
                    teacher_num ++;
                    sum_integer += integral;
                } 
            }
            
            
            if(teacher_num != 0){
                avg_integer = sum_integer / teacher_num;
            }
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("avg_integer", avg_integer);
            map.put("min_integral", min_integral);
            map.put("max_integral", max_integral);
            map.put("max_teacher_name", max_teacher_name);
            map.put("max_email_addr", max_email_addr);
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "获取失败:"+e);
        }
        return map;
    }
    
    /**
     * 上传教师积分
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> upteacherintegral(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsTeacherIntegralUp> ex=new ExcelUtil<IeltsTeacherIntegralUp>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsTeacherIntegralUp.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errorMessage"));
                return map;
            }
            
            List<IeltsTeacherIntegralUp> list=(List<IeltsTeacherIntegralUp>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsTeacherIntegralUp ti : list){
                if(ti== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getTeacher_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行教师姓名为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行邮箱前缀为空");
                    return map;
                }else{
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(ti.getEmail_addr());
                    if(ieltsTeacherInfo == null){
                        map.put("flag", false);
                        map.put("message", "第"+(m+1)+"行没有查询到该教师信息，请先在系统初始化该教师 ");
                        return map;
                    }
                    
                    //新增TKT模块
                    if(ti.getTkt_source_one() != null || ti.getTkt_source_two() != null || ti.getTkt_source_three()!= null || ti.getTkt_source_four() != null){
                        IeltsTeacherTkt ieltsTeacherTkt = new IeltsTeacherTkt();
                        if(ti.getTkt_source_one() != null){
                            ieltsTeacherTkt.setTkt_scoreone(ti.getTkt_source_one());
                        }
                        
                        if(ti.getTkt_source_two() != null){
                            ieltsTeacherTkt.setTkt_scoretwo(ti.getTkt_source_two());
                        }

                        if(ti.getTkt_source_three() != null){
                            ieltsTeacherTkt.setTkt_scorethree(ti.getTkt_source_three());
                        }
                        
                        if(ti.getTkt_source_four() != null){
                            ieltsTeacherTkt.setTkt_scorefour(ti.getTkt_source_four());
                        }
                        
                        ieltsTeacherTkt.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherTktDao.insertSelective(ieltsTeacherTkt);
                    }
                    
                    //新增雅思总分
                    if(ti.getIelts_source() != null && ti.getIelts_time_vaild() != null){
                        IeltsTeacherSource ieltsTeacherSource = new IeltsTeacherSource();
                        ieltsTeacherSource.setIelts_source(ti.getIelts_source());
                        Date mydate = sdf.parse(ti.getIelts_time_vaild());
                        ieltsTeacherSource.setTime_valid(mydate);
                        ieltsTeacherSource.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherSourceDao.insertSelective(ieltsTeacherSource);
                    }
                    
                    //教师资格证
                    if(ti.getIs_teacher_certificate() != null || ti.getIs_celta() != null){
                        IeltsTeacherCertificate ieltsTeacherCertificate = new IeltsTeacherCertificate();
                        if(ti.getIs_teacher_certificate() != null){
                            if(ti.getIs_teacher_certificate().equals("是")){
                                ieltsTeacherCertificate.setIs_teacher_certificate(1);
                            }else{
                                ieltsTeacherCertificate.setIs_teacher_certificate(0);
                            }
                            
                        }
                        
                        if(ti.getIs_celta() != null){
                            if(ti.getIs_celta().equals("是")){
                                ieltsTeacherCertificate.setIs_celta(1);
                            }else{

                                ieltsTeacherCertificate.setIs_celta(0);
                            }
                            
                        }
                        
                        ieltsTeacherCertificate.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherCertificateDao.updateByTeacherId(ieltsTeacherCertificate);
                    }
                    
                    //新增出勤信息
                    if(ti.getAbb_num() != null || ti.getDuty_num() != null){
                        IeltsTeacherAttendance ieltsTeacherAttendance = new IeltsTeacherAttendance();
                        if(ti.getAbb_num() != null){
                            ieltsTeacherAttendance.setAbb_num(ti.getAbb_num());
                        }
                        
                        if(ti.getDuty_num() != null){
                            ieltsTeacherAttendance.setDuty_num(ti.getDuty_num());
                        }
                        ieltsTeacherAttendance.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherAttendanceDao.insertSelective(ieltsTeacherAttendance);
                    }
                    
                    //教研文章
                    if(ti.getArticle_num() != null){
                        IeltsTeacherArticle ieltsTeacherArticle = new IeltsTeacherArticle();
                        ieltsTeacherArticle.setArticle_num(ti.getArticle_num());
                        ieltsTeacherArticle.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherArticleDao.insertSelective(ieltsTeacherArticle);
                    }
                    
                    //教学分享次数
                    if(ti.getShare_num() != null){
                        IeltsTeacherShare ieltsTeacherShare = new IeltsTeacherShare();
                        ieltsTeacherShare.setShare_num(ti.getShare_num());
                        ieltsTeacherShare.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherShareDao.insertSelective(ieltsTeacherShare);
                    }
                    
                    //运营支持次数
                    if(ti.getOperate_num() != null){
                        IeltsTeacherOperate ieltsTeacherOperate = new IeltsTeacherOperate();
                        ieltsTeacherOperate.setOperate_num(ti.getOperate_num());
                        ieltsTeacherOperate.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherOperateDao.insertSelective(ieltsTeacherOperate);
                    }
                    
                    //反馈
                    if(ti.getFeedback_num() != null){
                        IeltsTeacherFeedback ieltsTeacherFeedback = new IeltsTeacherFeedback();
                        ieltsTeacherFeedback.setFeedback_num(ti.getFeedback_num());
                        ieltsTeacherFeedback.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherFeedbackDao.insertSelective(ieltsTeacherFeedback);
                    }
                    
                    //投诉
                    if(ti.getComplaint_num() != null){
                        IeltsTeacherComplaint ieltsTeacherComplaint = new IeltsTeacherComplaint();
                        ieltsTeacherComplaint.setComplaint_num(ti.getComplaint_num());
                        ieltsTeacherComplaint.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherComplaintDao.insertSelective(ieltsTeacherComplaint);
                    }
                    n++;
                }
            }
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
            map.put("flag", flag);
            map.put("message", msg);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", flag);
            map.put("message", msg);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
    /**
     * 上传往期数据excel
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> upteacherintegraldate(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsTeacherIntegraldateUp> ex=new ExcelUtil<IeltsTeacherIntegraldateUp>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsTeacherIntegraldateUp.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errorMessage"));
                return map;
            }
            
            List<IeltsTeacherIntegraldateUp> list=(List<IeltsTeacherIntegraldateUp>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsTeacherIntegraldateUp ti : list){
                if(ti== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getTeacher_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行教师姓名为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行邮箱前缀为空");
                    return map;
                }else{
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(ti.getEmail_addr());
                    if(ieltsTeacherInfo == null){
                        map.put("flag", false);
                        map.put("message", "第"+(m+1)+"行没有查询到:"+ti.getTeacher_name()+"-"+ti.getEmail_addr()+"该教师信息，请先在系统初始化该教师 ");
                        return map;
                    }
                    
                    //新增TKT模块
                    if(ti.getTkt_source_one() != null || ti.getTkt_source_two() != null || ti.getTkt_source_three()!= null || ti.getTkt_source_four() != null){
                        IeltsTeacherTkt ieltsTeacherTkt = new IeltsTeacherTkt();
                        if(ti.getTkt_source_one() != null){
                            ieltsTeacherTkt.setTkt_scoreone(ti.getTkt_source_one());
                        }
                        
                        if(ti.getTkt_source_one() != null){
                            ieltsTeacherTkt.setTkt_scoretwo(ti.getTkt_source_two());
                        }

                        if(ti.getTkt_source_one() != null){
                            ieltsTeacherTkt.setTkt_scorethree(ti.getTkt_source_three());
                        }
                        
                        if(ti.getTkt_source_one() != null){
                            ieltsTeacherTkt.setTkt_scorefour(ti.getTkt_source_four());
                        }
                        
                        ieltsTeacherTkt.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getTkt_source_date())){
                            ieltsTeacherTkt.setCreate_time(sdf.parse(ti.getTkt_source_date()));
                        }
                        ieltsTeacherTktDao.insertSelective(ieltsTeacherTkt);
                    }
                    
                    //新增雅思总分
                    if(ti.getIelts_source() != null && ti.getIelts_time_vaild() != null){
                        IeltsTeacherSource ieltsTeacherSource = new IeltsTeacherSource();
                        ieltsTeacherSource.setIelts_source(ti.getIelts_source());
                        Date mydate = sdf.parse(ti.getIelts_time_vaild());
                        ieltsTeacherSource.setTime_valid(mydate);
                        ieltsTeacherSource.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherSourceDao.insertSelective(ieltsTeacherSource);
                    }
                    
                    //教师资格证
                    if(ti.getIs_teacher_certificate() != null || ti.getIs_celta() != null){
                        IeltsTeacherCertificate ieltsTeacherCertificate = new IeltsTeacherCertificate();
                        if(ti.getIs_teacher_certificate() != null){
                            if(ti.getIs_teacher_certificate().equals("是")){
                                ieltsTeacherCertificate.setIs_teacher_certificate(1);
                            }else{
                                ieltsTeacherCertificate.setIs_teacher_certificate(0);
                            }
                            
                        }
                        
                        if(ti.getIs_celta() != null){
                            if(ti.getIs_celta().equals("是")){
                                ieltsTeacherCertificate.setIs_celta(1);
                            }else{

                                ieltsTeacherCertificate.setIs_celta(0);
                            }
                            
                        }
                        
                        ieltsTeacherCertificate.setTeacher_id(ieltsTeacherInfo.getId());
                        ieltsTeacherCertificateDao.updateByTeacherId(ieltsTeacherCertificate);
                    }
                    
                    //新增出勤信息
                    if(ti.getAbb_num() != null || ti.getDuty_num() != null){
                        IeltsTeacherAttendance ieltsTeacherAttendance = new IeltsTeacherAttendance();
                        if(ti.getAbb_num() != null){
                            ieltsTeacherAttendance.setAbb_num(ti.getAbb_num());
                        }
                        
                        if(ti.getDuty_num() != null){
                            ieltsTeacherAttendance.setDuty_num(ti.getDuty_num());
                        }
                        ieltsTeacherAttendance.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getDuty_date())){
                            ieltsTeacherAttendance.setCreate_time(sdf.parse(ti.getDuty_date()));
                        }
                        ieltsTeacherAttendanceDao.insertSelective(ieltsTeacherAttendance);
                    }
                    
                    //教研文章
                    if(ti.getArticle_num() != null){
                        IeltsTeacherArticle ieltsTeacherArticle = new IeltsTeacherArticle();
                        ieltsTeacherArticle.setArticle_num(ti.getArticle_num());
                        ieltsTeacherArticle.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getArticle_date())){
                            ieltsTeacherArticle.setCreate_time(sdf.parse(ti.getArticle_date()));
                        }
                        ieltsTeacherArticleDao.insertSelective(ieltsTeacherArticle);
                    }
                    
                    //教学分享次数
                    if(ti.getShare_num() != null){
                        IeltsTeacherShare ieltsTeacherShare = new IeltsTeacherShare();
                        ieltsTeacherShare.setShare_num(ti.getShare_num());
                        ieltsTeacherShare.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getShare_date())){
                            ieltsTeacherShare.setCreate_time(sdf.parse(ti.getShare_date()));
                        }
                        ieltsTeacherShareDao.insertSelective(ieltsTeacherShare);
                    }
                    
                    //运营支持次数
                    if(ti.getOperate_num() != null){
                        IeltsTeacherOperate ieltsTeacherOperate = new IeltsTeacherOperate();
                        ieltsTeacherOperate.setOperate_num(ti.getOperate_num());
                        ieltsTeacherOperate.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getOperate_date())){
                            ieltsTeacherOperate.setCreate_time(sdf.parse(ti.getOperate_date()));
                        }
                        ieltsTeacherOperateDao.insertSelective(ieltsTeacherOperate);
                    }
                    
                    //反馈
                    if(ti.getFeedback_num() != null){
                        IeltsTeacherFeedback ieltsTeacherFeedback = new IeltsTeacherFeedback();
                        ieltsTeacherFeedback.setFeedback_num(ti.getFeedback_num());
                        ieltsTeacherFeedback.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getFeedback_date())){
                            ieltsTeacherFeedback.setCreate_time(sdf.parse(ti.getFeedback_date()));
                        }
                        ieltsTeacherFeedbackDao.insertSelective(ieltsTeacherFeedback);
                    }
                    
                    //投诉
                    if(ti.getComplaint_num() != null){
                        IeltsTeacherComplaint ieltsTeacherComplaint = new IeltsTeacherComplaint();
                        ieltsTeacherComplaint.setComplaint_num(ti.getComplaint_num());
                        ieltsTeacherComplaint.setTeacher_id(ieltsTeacherInfo.getId());
                        if(StringUtils.isNotEmpty(ti.getComplaint_date())){
                            ieltsTeacherComplaint.setCreate_time(sdf.parse(ti.getComplaint_date()));
                        }
                        ieltsTeacherComplaintDao.insertSelective(ieltsTeacherComplaint);
                    }
                    n++;
                }
            }
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
            map.put("flag", flag);
            map.put("message", msg);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", flag);
            map.put("message", msg+":" + e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    /**
     * 上传赛课excel
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> upteachermatchclass(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsTeacherMatchclassUp> ex=new ExcelUtil<IeltsTeacherMatchclassUp>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsTeacherMatchclassUp.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errormessage"));
                return map;
            }
            
            List<IeltsTeacherMatchclassUp> list=(List<IeltsTeacherMatchclassUp>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsTeacherMatchclassUp ti : list){
                if(ti== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getTeacher_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行教师姓名为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行邮箱前缀为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getMatchclass_type())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行赛课范围为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getMatchclass_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行赛课名称为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行获奖等级为空");
                    return map;
                }else{
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(ti.getEmail_addr());
                    if(ieltsTeacherInfo == null){
                        map.put("flag", false);
                        map.put("message", "第"+(m+1)+"行没有查询到该教师信息，请先在系统初始化该教师 ");
                        return map;
                    }
                    
                    IeltsTeacherMatchclass ieltsTeacherMatchclass = new IeltsTeacherMatchclass();
                    if(ti.getMatchclass_type().equals("集团")){
                        ieltsTeacherMatchclass.setMatchclass_type(1); 
                    }else if(ti.getMatchclass_type().equals("区域")){
                        ieltsTeacherMatchclass.setMatchclass_type(2); 
                    }else if(ti.getMatchclass_type().equals("部门")){
                        ieltsTeacherMatchclass.setMatchclass_type(3); 
                    }
                    
                    ieltsTeacherMatchclass.setMatchclass_name(ti.getMatchclass_name());
                    ieltsTeacherMatchclass.setMatchclass_grade(ti.getMatchclass_grade());
                    ieltsTeacherMatchclass.setTeacher_id(ieltsTeacherInfo.getId());
                    ieltsTeacherMatchclassDao.insertSelective(ieltsTeacherMatchclass);
                    n++;
                }
                m++;
            }
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
            map.put("flag", flag);
            map.put("message", msg);
        }catch(Exception e){
           e.printStackTrace();
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           map.put("flag", false);
           map.put("message", "导入异常："+e);
        }
        return map;
    }

    /**
     * 上传往期赛课excel
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> upteachermatchclassdate(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        //获取上传的excel
        boolean flag=false;
        String msg="未知错误";
        try{
            map.put("flag", flag);
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multiRequest.getFile("file");
            if(file.isEmpty()){
                map.put("message","文件内容为空，请重新选择文件!");
               return map;
            }
            ExcelUtil<IeltsTeacherMatchclassdateUp> ex=new ExcelUtil<IeltsTeacherMatchclassdateUp>(1,0);
            //获取城市下地区
            Map<String,Object> empTeachMap=ex.checkAccount(file,IeltsTeacherMatchclassdateUp.class);
            if(null!=empTeachMap&&empTeachMap.get("flag").toString().equals("false")){
                map.put("message", empTeachMap.get("errormessage"));
                return map;
            }
            
            List<IeltsTeacherMatchclassdateUp> list=(List<IeltsTeacherMatchclassdateUp>) empTeachMap.get("list");
            if(list.size()==0){
                map.put("message", "导入文件为空文件!");
               return map;
            }
            
            int m = 0;
            int n = 0;
            for(IeltsTeacherMatchclassdateUp ti : list){
                if(ti== null){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getTeacher_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行教师姓名为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行邮箱前缀为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getMatchclass_type())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行赛课范围为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getMatchclass_name())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行赛课名称为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getEmail_addr())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行获奖等级为空");
                    return map;
                }else if(StringUtils.isEmpty(ti.getMatchclass_date())){
                    map.put("flag", false);
                    map.put("message", "第"+(m+1)+"行赛课时间未空");
                    return map;
                }else{
                    IeltsTeacherInfo ieltsTeacherInfo = ieltsTeacherInfoDao.seleByEmailAddr(ti.getEmail_addr());
                    if(ieltsTeacherInfo == null){
                        map.put("flag", false);
                        map.put("message", "第"+(m+1)+"行没有查询到该教师信息，请先在系统初始化该教师 ");
                        return map;
                    }
                    
                    IeltsTeacherMatchclass ieltsTeacherMatchclass = new IeltsTeacherMatchclass();
                    if(ti.getMatchclass_type().equals("集团")){
                        ieltsTeacherMatchclass.setMatchclass_type(1); 
                    }else if(ti.getMatchclass_type().equals("区域")){
                        ieltsTeacherMatchclass.setMatchclass_type(2); 
                    }else if(ti.getMatchclass_type().equals("部门")){
                        ieltsTeacherMatchclass.setMatchclass_type(3); 
                    }
                    
                    if(StringUtils.isNotEmpty(ti.getMatchclass_date())){
                        ieltsTeacherMatchclass.setCreate_time(sdf.parse(ti.getMatchclass_date()));
                    }
                    ieltsTeacherMatchclass.setMatchclass_name(ti.getMatchclass_name());
                    ieltsTeacherMatchclass.setMatchclass_grade(ti.getMatchclass_grade());
                    ieltsTeacherMatchclass.setTeacher_id(ieltsTeacherInfo.getId());
                    ieltsTeacherMatchclassDao.insertSelective(ieltsTeacherMatchclass);
                    n++;
                }
            }
            flag=true;
            msg="导入成功,成功导入"+n+"条信息";
            map.put("flag", flag);
            map.put("message", msg);
        }catch(Exception e){
           e.printStackTrace();
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           map.put("flag", false);
           map.put("message", "导入异常："+e);
        }
        return map;
    }

    /**
     * 批量删除教师积分信息
     * @param ids
     * @return
     */
    public Map<String, Object> deletestudentinfo(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(ids)){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        
        try {
            for(String idstring : ids.split(",")){
                Integer id = Integer.parseInt(idstring);
                IeltsTeacherInfo ieltsTeacherInfo = new IeltsTeacherInfo();
                ieltsTeacherInfo.setId(id);
                Map<String, Object> mapparam1 = new HashMap<String, Object>();
                mapparam1.put("t", ieltsTeacherInfo);
                //数据库查询
                IeltsTeacherInfo ieltsTeacherInfo1 = ieltsTeacherInfoDao.selectByTeacherId(mapparam1);
                //删除关联学生信息
                ieltsStudentTeacherDao.deleteByTeacherEmailaddr(ieltsTeacherInfo1.getEmail_addr());
                
                //删除教研文章
                ieltsTeacherArticleDao.deleteByTeacherId(id);
                
                //删除出勤信息
                ieltsTeacherAttendanceDao.deleteByTeacherId(id);
                
                //删除证书信息
                IeltsTeacherCertificate ieltsTeacherCertificate = new IeltsTeacherCertificate();
                ieltsTeacherCertificate.setTeacher_id(id);
                ieltsTeacherCertificate.setIs_celta(0);
                ieltsTeacherCertificate.setIs_teacher_certificate(0);
                ieltsTeacherCertificateDao.updateByPrimaryKey(ieltsTeacherCertificate);
                
                //删除投诉
                ieltsTeacherComplaintDao.deleteByTeacherId(id);
                
                //删除反馈信息
                ieltsTeacherFeedbackDao.deleteByTeacherId(id);
                
                //删除赛课信息
                ieltsTeacherMatchclassDao.deleteByTeacherId(id);
                
                //删除运维支持
                ieltsTeacherOperateDao.deleteByTeacherId(id);
                
                //删除教学分享
                ieltsTeacherShareDao.deleteByTeacherId(id);
                
                //删除雅思分数
                ieltsTeacherSourceDao.deleteByTeacherId(id);
                
                //删除tkt成绩
                ieltsTeacherTktDao.deleteByTeacherId(id);
            }
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败："+e);
        }
        
        return map;
    }
}
