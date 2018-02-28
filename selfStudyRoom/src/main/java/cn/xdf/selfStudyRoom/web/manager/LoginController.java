package cn.xdf.selfStudyRoom.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

import cn.xdf.selfStudyRoom.domain.entity.MenuFirstList;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.security.SecurityUser;
import cn.xdf.selfStudyRoom.service.ResourcesService;
import cn.xdf.selfStudyRoom.service.UserService;
import cn.xdf.selfStudyRoom.utils.CommonUtil;
import cn.xdf.selfStudyRoom.utils.GlobalSessionUtil;
import cn.xdf.selfStudyRoom.utils.Md5Tool;


/**
 * 登录控制
 * @author liuwei63
 *
 */
@Controller
public class LoginController {

	private final  Logger logger = LogManager.getLogger(LoginController.class);
			
	@Resource
	private UserService userService;
	
	@Resource
	private ResourcesService resourcesService;
	
	@Resource
	private AuthenticationManager myAuthenticationManager;
		
	
	@GetMapping("/login")
    public String login(HttpServletRequest request){
		//重新登录时销毁该用户的Session
		String username = CommonUtil.getAuthenticatedUsername();
		if((!"anonymousUser".equals(username)) && (!StringUtils.isEmpty(username))){
			List<String> list=GlobalSessionUtil.usernameAndSessionIdListMap.get(username);
	        if(!CollectionUtils.isEmpty(list)){
	        	for(String str:list){
	        		HttpSession session=GlobalSessionUtil.SessionIdAndSessionMap.get(str);
	        		if(session!=null){
	        			session.invalidate();
	        		}
	        	}
	        }
	        
		}
        return "/framework/login";
    }
	
	@PostMapping("/loginCheck")
	@ResponseBody
	public Map<String,Object> loginCheck(String userName, String userPassword,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!request.getMethod().equals("POST")) {
				logger.info("loginCheck is error", "只支持POST方法提交！");
				map.put("flag", false);
				map.put("message", "只支持POST方法提交！");
				return map;
			}
			if (CommonUtil.isEmpty(userName) || CommonUtil.isEmpty(userPassword)) {
				logger.info("loginCheck is error", "用户名或密码不能为空！");
				map.put("flag", false);
				map.put("message", "用户名或密码不能为空！");
				return map;
			}
			User u = new User();
			u.setLoginName(userName);
			u.setStatus("1");
			u.setPassword(Md5Tool.getMd5(userPassword));
			// 验证用户账号与密码是否正确
			User user = userService.findByLoginName(u);
			if (user == null) {
				map.put("flag", false);
				map.put("message", "用户名或密码不正确！");
				return map;
			}
			Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, Md5Tool.getMd5(userPassword)));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			List<String>list=new ArrayList<String>();
			list.add(session.getId());
			GlobalSessionUtil.usernameAndSessionIdListMap.put(userName,list);
			GlobalSessionUtil.SessionIdAndSessionMap.put(session.getId(), session);			
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);	
			session.setAttribute("SPRING_SECURITY_CONTEXT_USERNAME", userName);
		} catch (AuthenticationException e) {
			logger.error("login error !", e);
			map.put("flag", false);
			map.put("message", "登陆验证异常，请联系管理员!");
			return map;
		}
		map.put("flag", true);
		map.put("message", "登陆成功");
		return map;
	}
	
	@GetMapping("/index")
	public String index(ModelMap map) {
		SecurityUser user=CommonUtil.getMyUser();	 
	    String username = CommonUtil.getAuthenticatedUsername();
	    List<MenuFirstList> menus =resourcesService.selectMenuList(username);
	    map.addAttribute("user", user);
	    map.addAttribute("menus",menus);
		return "/framework/main";
	}
	
	/**
	 * 修改密码页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toModifyPwd")
	public ModelAndView toModifyPwd(HttpServletRequest request) {
	   return new ModelAndView("/framework/modifyPassword");
	}
	
	/**
	 * 修改密码时校验MD5密码
	 * @param request
	 * @return
	 */
	@PostMapping("/modifyPwd")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> modifyPwd(String passWord,String oldPassword) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    try{	        
	        User tu=new User();
            tu.setLoginName(CommonUtil.getMyUser().getUsername()); 
            tu.setId(CommonUtil.getMyUser().getUserId());
            tu.setStatus("1");
            tu.setPassword(Md5Tool.getMd5(oldPassword));
            // 验证用户原密码是否正确
            User user= userService.findByLoginName(tu);
            if (user==null) {
                map.put("flag", false);
                map.put("message", "原密码不正确，请重新输入!");
            }else{
                tu.setPassword(Md5Tool.getMd5(passWord));
                tu.setUpdateTime(new Date());
                tu.setUpdateUser(String.valueOf(CommonUtil.getMyUser().getUserId()));
                userService.updateByPrimaryKeySelective(tu);
	            map.put("flag", true);
	            map.put("message", "密码修改成功！下次登陆时生效！");
	        }
	    }catch(Exception e){
	        logger.error("modifyPwd error !",e);
	        map.put("flag", false);
            map.put("message", "修改失败，请稍后重试!");
	    }
		return map;
	}
	
}
