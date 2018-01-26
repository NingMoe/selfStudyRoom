package com.human.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.manager.dao.ResourceDao;
import com.human.manager.entity.MenuFirstList;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.security.MyUser;
import com.human.utils.Common;
import com.human.utils.Md5Tool;
import com.human.utils.verificationCode.VerificationCodeTool;

/**
 * 进行管理后台框架界面的类
 * 
 * @author xiazhenyou 2016.07-12
 * @Email:xiazhenyou@xdf.cn
 */
@Controller
@RequestMapping("/manager/")
public class LoginController {

	private final  Logger logger = LogManager.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;

	
	@Resource
	private ResourceDao resourcesDao;
	
	@Resource
	private AuthenticationManager myAuthenticationManager;
	
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {
		// 重新登录时销毁该用户的Session
		Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (null != o) {
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		 // request.getSession().removeAttribute("userSession");
		return new ModelAndView("/framework/login");
	}
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("verCode")
    public void verCode(HttpServletRequest request,HttpServletResponse response) {
	    try{
	    response.setHeader("Pragma", "no-cache");        
	    response.setHeader("Cache-Control", "no-cache");        
	    response.setDateHeader("Expires", 0);        
	    response.setContentType("image/jpeg");        
        // 将图像输出到Servlet输出流中。        
        ServletOutputStream sos = response.getOutputStream();   
        VerificationCodeTool vt=new VerificationCodeTool();
        ImageIO.write(vt.drawVerificationCodeImage(request), "png", sos);        
        sos.close();         
	    }catch(Exception e){
	        logger.error(e);
	    }
    }

	@RequestMapping("loginCheck")
	@ResponseBody
	public Map<String,Object> loginCheck(String userName, String userPassword,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			if (!request.getMethod().equals("POST")) {
				logger.info("loginCheck is error","只支持POST方法提交！");
				map.put("flag", false);
				map.put("message", "只支持POST方法提交！");
				return map;
			}
			if (Common.isEmpty(userName) || Common.isEmpty(userPassword)) {
				logger.info("loginCheck is error","用户名或密码不能为空！");
				map.put("flag", false);
				map.put("message", "用户名或密码不能为空！");
				return map;
			}
			/*String vcode= (String) request.getSession().getAttribute("validResult");
			if(vcode==null||!vcode.equals(vCode)){
			    map.put("flag", false);
                map.put("message", "验证码不正确！");
                return map;
			}*/
			 Users u=new Users();
		      u.setLoginName(userName);
		      u.setStatus(0);
		      u.setHrStatus("A");
		      u.setUserPassword(Md5Tool.getMd5(userPassword));
			// 验证用户账号与密码是否正确
		    List<Users> users = userService.queryUser(u);
			/*System.out.println(Md5Tool.getMd5(userPassword));
			System.out.println(users.getManagerPassword());*/
			if (users.size() ==0) {
				map.put("flag", false);
				map.put("message", "用户名或密码不正确！");
				return map;
			}
			Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,Md5Tool.getMd5(userPassword)));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		} catch (AuthenticationException e) {
			logger.error("login error !",e);
			map.put("flag", false);
			map.put("message", "登陆验证异常，请联系管理员!");
			return map;
		}
		map.put("flag", true);
		map.put("message", "登陆成功");
		return map;
	}

	@RequestMapping("index")
	public ModelAndView index() {
	    MyUser user=Common.getMyUser();
	    ModelAndView mav= new ModelAndView("/framework/main");
	    String username = Common.getAuthenticatedUsername();
	    List<MenuFirstList> menus = resourcesDao.selectMenuList(username);
	    mav.addObject("user", user);
	    mav.addObject("menus",menus);
		return mav;
	}
	
	@RequestMapping("getMenu")
    @ResponseBody
    public List<MenuFirstList> getMenu(HttpServletRequest request) {
        List<MenuFirstList> resources = new ArrayList<MenuFirstList>();
        String username = Common.getAuthenticatedUsername();
        resources = resourcesDao.selectMenuList(username);
        return resources;
    }
	
	/**
	 * 修改密码页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toModifyPwd")
	public ModelAndView toModifyPwd(HttpServletRequest request) {
			 return new ModelAndView("/framework/modifyPassword");
	}	
	
	/**
	 * 修改密码时校验MD5密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modifyPwd")
	@ResponseBody
	public Map<String,Object> modifyPwd(String passWord,String oldPassword) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    try{
	        Users nUser=new Users();
	        nUser.setId(Common.getMyUser().getUserid());
	        nUser.setUserPassword(Md5Tool.getMd5(oldPassword));
	        List<Users> users = userService.queryUser(nUser);
	        if(users.size()==0){
	            map.put("flag", false);
	            map.put("message", "原密码不正确，请重新输入!");
	        }else{
	            nUser.setUserPassword(Md5Tool.getMd5(passWord));
	            userService.editLogInUserById(nUser);
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
