package cn.xdf.selfStudyRoom.service.impl;

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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.xdf.selfStudyRoom.domain.dao.UserDao;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.domain.entity.UserRole;
import cn.xdf.selfStudyRoom.security.SecurityUser;
import cn.xdf.selfStudyRoom.service.UserService;
import cn.xdf.selfStudyRoom.utils.CommonUtil;
import cn.xdf.selfStudyRoom.utils.Md5Tool;
import cn.xdf.selfStudyRoom.utils.PageView;

@Transactional(rollbackFor=Exception.class)
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	

	@Override
	public User findByLoginName(User user) {		
		return userDao.findByLoginName(user);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public PageView queryUser(User user, PageView pageView) {
		try{
			PageHelper.startPage(pageView.getPage(), pageView.getLimit());
			List<User> userList=userDao.queryPageUser(user);
			pageView.setData(userList);
			pageView.setCount(((Page)userList).getTotal());
			pageView.setCode("0");
		}catch(Exception e){
			e.printStackTrace();
			pageView.setCode("1");
		}
		pageView.setMsg("");
		return pageView;
	}


	@Override
	public Map<String, Object> add(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 验证用户手机格式
            if((!CommonUtil.isEmpty(user.getPhone())) && (!CommonUtil.isMobile(user.getPhone()))){
                map.put("flag", false);
                map.put("message", "手机号格式不正确！");
                return map;
            }
            SecurityUser myUser = CommonUtil.getMyUser();
            user.setCreateUser(String.valueOf(myUser.getUserId()));           
            user.setPassword(Md5Tool.getMd5(user.getPassword()));
            user.setCreateTime(new Date());
            userDao.insertSelective(user);
            map.put("flag", true);
            map.put("message", "添加用户成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该账号已经存在,请输入其它账号!");
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
	public User selectByPrimaryKey(Long id) {		
		return userDao.selectByPrimaryKey(id);
	}


	@Override
	public Map<String, Object> update(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
        try{
            // 验证用户手机格式
            if((!CommonUtil.isEmpty(user.getPhone())) && (!CommonUtil.isMobile(user.getPhone()))){
                map.put("flag", false);
                map.put("message", "手机号格式不正确！");
                return map;
            }
            SecurityUser myUser = CommonUtil.getMyUser();
            user.setUpdateUser(String.valueOf(myUser.getUserId()));           
            user.setUpdateTime(new Date());
            userDao.updateByPrimaryKeySelective(user);
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
	public Map<String, Object> updateStatus(String deleteIds, Integer status) {
		 Map<String, Object> map = new HashMap<String, Object>();
	        try {
	            String[] userIds = deleteIds.split(",");
	            Map<String, Object> paraMap = new HashMap<String, Object>();
	            paraMap.put("ids", userIds);
	            paraMap.put("status", status);
	            paraMap.put("updateUser", CommonUtil.getMyUser().getUserId());
	            userDao.updateByIds(paraMap);
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
	public List<UserRole> getUserRole(Long userId) {
		 UserRole ur=new UserRole();
	     ur.setUserId(userId);
	     return userDao.getUserRole(ur);
	}


	@Override
	public Map<String, Object> saveUserRole(UserRole userRole, String roleIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userDao.delUserRole(userRole);
			List<String> list = new ArrayList<String>();
			if (roleIds.length() > 0) {
				list = CommonUtil.removeSameItem(Arrays.asList(roleIds.split(",")));
			}
			for (String roleId : list) {
				userRole.setRoleId(Long.valueOf(roleId));
				userDao.saveUserRole(userRole);
			}
			map.put("flag", true);
			map.put("message", "配置用户角色成功!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", false);
			map.put("message", "配置用户角色失败，请稍后重试!");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return map;
	}
	
	

}
