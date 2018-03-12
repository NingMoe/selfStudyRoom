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
import cn.xdf.selfStudyRoom.constant.MQConstant;
import cn.xdf.selfStudyRoom.domain.dao.CustomerDao;
import cn.xdf.selfStudyRoom.domain.dao.UserDao;
import cn.xdf.selfStudyRoom.domain.entity.Customer;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.exception.MyException;
import cn.xdf.selfStudyRoom.rabbitMq.CallBackSender;
import cn.xdf.selfStudyRoom.rabbitMq.Sender;
import cn.xdf.selfStudyRoom.service.MessageQueueService;
import cn.xdf.selfStudyRoom.utils.RedisTemplateUtil;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisTemplateUtil redisTemplateUtil;
	
	@Autowired
    private Sender sender;
	
	@Autowired
	private MessageQueueService messageQueueService;
	
	@Autowired
    private CallBackSender callBackSender;
	
	@Autowired
	private CustomerDao customerDao;
	

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
	
	/**
	 * 测试RabbitMQ的普通队列
	 * @throws Exception
	 */
	@Test
    public void testRabbitMq() throws Exception {
		
        sender.send("hello,rabbitMq!");
        System.out.println("发送字符串成功!");
                
        User user =new User();
		user.setName("刘威");
		user.setLoginName("liuwei");
		user.setPhone("15178558263");
		sender.send(user);
		System.out.println("发送对象成功!");
	   		
	    sender.sendQueue1("hello,liuwei");
	    sender.sendQueue2("hello,xiamei");
    }
	
	/**
	 * 测试RabbitMQ的带回调的队列
	 */
	@Test
	public void testCallBackRabbitMq(){
		callBackSender.send("hello,world!");
	}
	
	/**
	 * 测试RabbitMQ的延迟队列
	 * @throws MyException 
	 */
	@Test
	public void testDLXRabbitMq() throws MyException{
		messageQueueService.send(MQConstant.DEFAULT_QUEUE_NAME,"测试延迟发送消息",6000);
	}
	
	@Test
	public void testElasticSerach(){
		String queryString="LIU";//搜索关键字
		Customer cu= customerDao.findByFirstName(queryString);
		System.out.println(cu);
	}
	

}