package cn.xdf.selfStudyRoom.web.manager;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.service.UserService;
import cn.xdf.selfStudyRoom.utils.PageView;

/**
 * 员工管理类
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/manager/user/")
public class UserController {
	
    private final Logger logger = LogManager.getLogger(UserController.class);
    
    @Resource
    private UserService  userService;
    
    /**
     * 员工管理列表
     * @param map
     * @return
     */
    @GetMapping("/index")
    public String index() {
		return "/view/manager/user/index";
	}
    
    /**
	 * 分页查询
	 * @param managerUser
	 * @param pageView
	 * @return
	 */
	@RequestMapping("queryUser")
	@ResponseBody
	public PageView queryUser(User user,PageView pageView){
		logger.info("分页查询员工数据");			
		return userService.queryUser(user, pageView);		
	}
    
    
}
