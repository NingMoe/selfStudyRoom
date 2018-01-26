package com.ls.spt.student.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ls.spt.manager.entity.UserRole;
import com.ls.spt.student.dao.StudentUserDao;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.student.service.StudentUserService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.Md5Tool;
import com.ls.spt.utils.PageView;

@Service
public class StudentUserServiceImpl implements StudentUserService {

    @Resource
    private StudentUserDao suDao;
    
    @Override
    public StudentUser queryUserByUnionId(String unionId) {
        return suDao.selectByUnionid(unionId);
    }
    
    @Override
    public StudentUser queryUser(StudentUser tu) {               
        return suDao.queryUser(tu);
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(StudentUser record) {       
        return  suDao.updateByPrimaryKeySelective(record);
    }


    @Override
    public PageView queryUser(StudentUser tu, PageView pageView) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", tu);
        List<StudentUser> list = suDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(StudentUser tu) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 验证用户账号手机格式
            if(!Common.isMobile(tu.getPhone())){
                map.put("flag", false);
                map.put("message", "手机号格式不正确！");
                return map;
            }
//            MyUser myUser = Common.getMyUser();
//            tu.setCreateUser(String.valueOf(myUser.getUserid()));           
            tu.setPassword(Md5Tool.getMd5(tu.getPassword()));
            tu.setCreateTime(new Date());
            suDao.insertSelective(tu);
            map.put("flag", true);
            map.put("message", "添加用户成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该手机号已经存在,请输入其它手机号!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加用户失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> updateStatus(String deleteIds, Integer status) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] userIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", userIds);
            paraMap.put("status", status);
            paraMap.put("updateUser", Common.getMyUser().getUserid());
            suDao.updateByIds(paraMap);
            map.put("flag", true);
            map.put("message", "操作成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "操作失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public StudentUser selectByPrimaryKey(Integer id) {
        return suDao.selectByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> update(StudentUser tu) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
//            MyUser myUser = Common.getMyUser();
//            tu.setUpdateUser(String.valueOf(myUser.getUserid()));           
//            tu.setUpdateTime(new Date());
            suDao.updateByPrimaryKeySelective(tu);
            map.put("flag", true);
            map.put("message", "编辑用户成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑用户失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public List<UserRole> getUserRole(Long userId) {
        UserRole ur=new UserRole();
        ur.setUserId(userId);
        return suDao.getUserRole(ur);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveUserRole(UserRole userRole, String roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            suDao.delUserRole(userRole);
            List<String> list = new ArrayList<String>();
            if(roleIds.length()>0){
                 list = Common.removeSameItem(Arrays.asList(roleIds.split(",")));
            }
            for (String roleId : list) {
                userRole.setRoleId(Long.valueOf(roleId));
                suDao.saveUserRole(userRole);
            }
            map.put("flag", true);
            map.put("message", "配置用户角色成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "配置用户角色失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

}
