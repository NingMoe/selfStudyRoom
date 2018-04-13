package cn.xdf.pay.controller;


import java.io.FileNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import cn.xdf.pay.domain.User;
import cn.xdf.pay.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="用户操作接口")
@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(HttpServletRequest request) throws FileNotFoundException{		
		String fileName=new DefaultResourceLoader().getResource("classpath:/static/certificate1/apiclient_cert.p12").getFilename();
        System.out.println(fileName);
		return "hello";
	}
	
	/**
	 * 测试插入
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody User user){
		this.userService.insertUser(user);
		return user.getId()+"    " + user.getUserName();
	}
	
		
	/**
	 * 测试读
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String",paramType="path")
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String findById(@PathVariable("id") String id){
		User u = this.userService.findById(id);
		return u.getId()+"    " + u.getUserName();
	}
	
	
	/**
	 * 测试写然后读
	 * @param id
	 * @param userName
	 * @return
	 */
	@RequestMapping("/addAndRead")
	@ResponseBody
	public String addAndRead(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.wirteAndRead(u);
		return u.getId()+"    " + u.getUserName();
	}
	
	/**
	 * 测试读然后写
	 * @param id
	 * @param userName
	 * @return
	 */
	@RequestMapping("/readAndAdd")
	@ResponseBody
	public String readAndWrite(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.readAndWirte(u);
		return u.getId()+"    " + u.getUserName();
	}
	
	/**
	 * 测试分页插件
	 * @return
	 */
	@ApiOperation(value="分页获取用户列表", notes="")
	@RequestMapping(value="/queryPage",method=RequestMethod.GET)
	@ResponseBody
	public String queryPage(){
		PageInfo<User> page = this.userService.queryPage("liu", 1, 2);
		StringBuilder sb = new StringBuilder();
		sb.append("<br/>总页数=" + page.getPages());
		sb.append("<br/>总记录数=" + page.getTotal()) ;
		for(User u : page.getList()){
			sb.append("<br/>" + u.getId() + "      " + u.getUserName());
		}
		System.out.println("分页查询....\n" + sb.toString());
		return sb.toString();
	}
	
}
