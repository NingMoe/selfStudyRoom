package com.ls.spt.manager.controller;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.manager.dao.ResourceDao;
import com.ls.spt.manager.entity.MenuFirstList;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.entity.VAllUser;
import com.ls.spt.manager.service.TeacherUserService;
import com.ls.spt.manager.service.VAllUserService;
import com.ls.spt.security.MyUser;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.student.service.StudentUserService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.Md5Tool;
import com.ls.spt.utils.StudentPcConstants;
import com.ls.spt.utils.verificationCode.VerificationCodeTool;

/**
 * 进行管理后台框架界面的类
 * @author liuwei 2016.07-12
 * @Email: liuwei63@xdf.cn
 */
@Controller
@RequestMapping("/manager/")
public class LoginController {

	private final  Logger logger = LogManager.getLogger(LoginController.class);
	
	@Resource
    private TeacherUserService teacherUserService;
	
	@Resource
	private ResourceDao resourcesDao;
	
	@Resource
	private AuthenticationManager myAuthenticationManager;
	
	@Resource
	private VAllUserService vAllUserService;
	
	@Resource
	private StudentUserService studentUserService;
	
	
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {
		// 重新登录时销毁该用户的Session
		Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (null != o) {
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
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
				logger.info("loginCheck is error","手机号或密码不能为空！");
				map.put("flag", false);
				map.put("message", "手机号或密码不能为空！");
				return map;
			}
			// 验证用户账号手机格式
			if(!Common.isMobile(userName)){
			    map.put("flag", false);
                map.put("message", "手机号格式不正确！");
                return map;
			}
			
			VAllUser vau = new VAllUser();
			vau.setPhone(userName);
			// 验证用户账号与密码是否正确
			VAllUser v_user= vAllUserService.queryUser(vau);
			if (v_user==null || !Md5Tool.getMd5(userPassword).equals(v_user.getPassword())) {
				map.put("flag", false);
				map.put("message", "手机号或密码不正确！");
				return map;
			}
			
			if("1".equals(v_user.getUser_type())){
			    Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,Md5Tool.getMd5(userPassword)));
	            SecurityContext securityContext = SecurityContextHolder.getContext();
	            securityContext.setAuthentication(authentication);
	            HttpSession session = request.getSession(true);
	            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	            map.put("user_type", "1");
			}else if("2".equals(v_user.getUser_type())){
			    StudentUser studentUser = new StudentUser();
			    studentUser.setPhone(userName);
			    studentUser.setStatus("1");
			    StudentUser studentUser1 = studentUserService.queryUser(studentUser);
			    if(studentUser1 != null){
			        studentUser1.setPassword("");
			        HttpSession session = request.getSession(true);
	                session.setAttribute(StudentPcConstants.STUDENT_USER, studentUser1);
	                map.put("user_type", "2");
			    }
			    
			}else{
			    logger.error("登录异常：用户既不是教师也不是学生");
			    map.put("flag", false);
	            map.put("message", "登录异常：用户既不是教师也不是学生");
	            return map;
			}
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
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> modifyPwd(String passWord,String oldPassword) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    try{	        
	        TeacherUser tu=new TeacherUser();
            tu.setPhone(Common.getMyUser().getUsername()); 
            tu.setId(Common.getMyUser().getUserid());
            tu.setStatus("1");
            tu.setPassword(Md5Tool.getMd5(oldPassword));
            // 验证用户原密码是否正确
            TeacherUser teacherUser= teacherUserService.queryUser(tu);
            if (teacherUser==null) {
                map.put("flag", false);
                map.put("message", "原密码不正确，请重新输入!");
            }else{
                tu.setPassword(Md5Tool.getMd5(passWord));
                teacherUserService.updateByPrimaryKeySelective(tu);
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
