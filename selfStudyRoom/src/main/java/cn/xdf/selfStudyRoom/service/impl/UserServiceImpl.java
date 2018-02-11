package cn.xdf.selfStudyRoom.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.xdf.selfStudyRoom.domain.dao.UserDao;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.service.UserService;
import cn.xdf.selfStudyRoom.utils.PageView;

@Transactional
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

}
