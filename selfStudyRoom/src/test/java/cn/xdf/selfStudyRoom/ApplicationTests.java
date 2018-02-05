package cn.xdf.selfStudyRoom;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import cn.xdf.selfStudyRoom.domain.dao.UserDao;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.utils.RedisTemplateUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;

	@Test
	public void testInsert() throws Exception {
		User user =new User();
		user.setName("刘威");
		user.setLoginName("liuwei");
		user.setPassword("123456");
		user.setPhone("15178558263");
		userDao.insertSelective(user);
		System.out.println("id======"+user.getId());	
	}
	
	@Test
	public void testUpdate() throws Exception {
		User user =new User();
		user.setId(6L);
		user.setName("刘威");
		user.setLoginName("liuwei63");
		user.setPassword("123456");
		user.setPhone("15178558263");
		user.setUpdateTime(new Date());
		userDao.updateByPrimaryKeySelective(user);
		System.out.println("===更新成功===");	
	}
	
	@Test
	public void testQuery() throws Exception {
		User user =new User();
		user.setName("刘威");
		user.setPhone("151");
		List<User>list=userDao.query(user);
		if(!CollectionUtils.isEmpty(list)){
			for(User user1:list){
				System.out.println("name===="+user1.getName());
			}
		}
			
	}
	
	@Test
	public void testDelete() throws Exception {		
		userDao.deleteByPrimaryKey(6L);
		System.out.println("===删除成功===");	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRedis(){		
		if(redisTemplateUtil.isExist("city")){
			String city=(String) redisTemplateUtil.get("city");
			System.out.println("city=="+city);
		}else{
			redisTemplateUtil.set("city", "hefei", 120L);
		}
		User user =new User();
		user.setName("刘威");
		user.setLoginName("liuwei");
		user.setPassword("123456");
		user.setPhone("15178558263");
		List<User>list=new ArrayList<User>();
		list.add(user);
		if(redisTemplateUtil.isExist("userList")){
			List<User>list1=(List<User>) redisTemplateUtil.get("userList");
			System.out.println("name=="+list1.get(0).getName());
		}else{
			redisTemplateUtil.setList("userList", list, 120L);
		}
		
		
		
	}

}