package com.ls.spt.manager.service.impl;

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

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.manager.dao.TeacherUserDao;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.entity.UserRole;
import com.ls.spt.manager.service.TeacherUserService;
import com.ls.spt.security.MyUser;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.Md5Tool;

@Service
public class TeacherUserServiceImpl implements TeacherUserService {

    @Resource
    private TeacherUserDao tuDao;
    
    @Override
    public TeacherUser queryUser(TeacherUser tu) {               
        return tuDao.queryUser(tu);
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(TeacherUser record) {       
        return  tuDao.updateByPrimaryKeySelective(record);
    }


    @Override
    public PageView queryUser(TeacherUser tu, PageView pageView) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", tu);
        List<TeacherUser> list = tuDao.query(map);
        pageView.setData(list);
        return pageView;
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(TeacherUser tu) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 验证用户账号手机格式
            if(!Common.isMobile(tu.getPhone())){
                map.put("flag", false);
                map.put("message", "手机号格式不正确！");
                return map;
            }
            MyUser myUser = Common.getMyUser();
            tu.setCreateUser(String.valueOf(myUser.getUserid()));           
            tu.setPassword(Md5Tool.getMd5(tu.getPassword()));
            tu.setCreateTime(new Date());
            tuDao.insertSelective(tu);
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
            tuDao.updateByIds(paraMap);
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
    public TeacherUser selectByPrimaryKey(Long id) {
        return tuDao.selectByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> update(TeacherUser tu) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            MyUser myUser = Common.getMyUser();
            tu.setUpdateUser(String.valueOf(myUser.getUserid()));           
            tu.setUpdateTime(new Date());
            tuDao.updateByPrimaryKeySelective(tu);
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
        return tuDao.getUserRole(ur);
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveUserRole(UserRole userRole, String roleIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            tuDao.delUserRole(userRole);
            List<String> list = new ArrayList<String>();
            if(roleIds.length()>0){
                 list = Common.removeSameItem(Arrays.asList(roleIds.split(",")));
            }
            for (String roleId : list) {
                userRole.setRoleId(Long.valueOf(roleId));
                tuDao.saveUserRole(userRole);
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
