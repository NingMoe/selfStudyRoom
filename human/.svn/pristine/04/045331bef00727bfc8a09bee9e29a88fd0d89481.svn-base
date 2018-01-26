package com.human.ielts.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.ielts.dao.IeltsTeacherCertificateDao;
import com.human.ielts.entity.IeltsTeacherCertificate;
import com.human.ielts.service.IeltsTeacherCertificateService;
import com.human.security.MyUser;
import com.human.utils.Common;

@Service
public class IeltsTeacherCertificateServiceImpl implements IeltsTeacherCertificateService {
    
    @Resource
    private IeltsTeacherCertificateDao ieltsTeacherCertificateDao;
    
    /**
     * 更新证书信息
     * @param ieltsTeacherCertificate
     * @return
     */
    public Map<String, Object> updatecertificate(IeltsTeacherCertificate ieltsTeacherCertificate) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherCertificate == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherCertificate.getTeacher_id() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            ieltsTeacherCertificateDao.updateByPrimaryKeySelective(ieltsTeacherCertificate);
            map.put("flag", true);
            map.put("message", "更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "更新失败："+e);
        }
        
        return map;
    }

    /**
     * 获取教师证书信息
     * @param request
     * @return
     */
    public Map<String, Object> selecttecertificate(IeltsTeacherCertificate ieltsTeacherCertificate) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(ieltsTeacherCertificate == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        if(ieltsTeacherCertificate.getTeacher_id() == null){
            map.put("flag", false);
            map.put("message", "请选择教师");
            return map;
        }
        
        try {
            
            IeltsTeacherCertificate ieltsTeacherCertificate1 = ieltsTeacherCertificateDao.selectByPrimaryKey(ieltsTeacherCertificate.getTeacher_id());
            if(ieltsTeacherCertificate1 == null){
                map.put("flag", false);
                map.put("message", "没有证书信息");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("ieltsTeacherCertificate", ieltsTeacherCertificate1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败："+e);
        }
        
        
        return map;
    }

    /**
     * 获取登录教师教师证书信息
     * @param request
     * @return
     */
    public Map<String, Object> selectteacherselfcertificate() {
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取用户权限范围
        MyUser myUser = Common.getMyUser();
        
        try {
            
            IeltsTeacherCertificate ieltsTeacherCertificate1 = ieltsTeacherCertificateDao.selectByEmailaddr(myUser.getEmailAddr());
            if(ieltsTeacherCertificate1 == null){
                map.put("flag", false);
                map.put("message", "没有证书信息");
            }else{
                map.put("flag", true);
                map.put("message", "获取成功");
                map.put("ieltsTeacherCertificate", ieltsTeacherCertificate1);
            }
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败："+e);
        }
        
        
        return map;
    }

    /**
     * 查询Celtaz证书人数
     * @param ieltsTeacherCertificate
     * @return
     */
    public Map<String, Object> selectceltazteachercount(IeltsTeacherCertificate ieltsTeacherCertificate) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
            Integer ielts_celtaz_count = ieltsTeacherCertificateDao.selectceltazcount();
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ielts_celtaz_count", ielts_celtaz_count);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败："+e);
        }
        
        
        return map;
    }

    /**
     * 查询教师资格证人数
     * @param ieltsTeacherCertificate
     * @return
     */
    public Map<String, Object> selectcertificatecount(IeltsTeacherCertificate ieltsTeacherCertificate) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
            Integer ielts_certificate_count = ieltsTeacherCertificateDao.selectcertificatecount();
            map.put("flag", true);
            map.put("message", "获取成功");
            map.put("ielts_certificate_count", ielts_certificate_count);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "获取失败："+e);
        }
        
        
        return map;
    }

}
