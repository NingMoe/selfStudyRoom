package cn.xdf.selfStudyRoom.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import cn.xdf.selfStudyRoom.utils.CommonUtil;

/**
 * 
 * @author liuwei63
 *
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
		// 获得授权后可得到用户信息可使用UserService进行数据库操作
		SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
		// 输出登录提示信息
		System.out.println("用户登录IP:" + CommonUtil.getIpAddress(request)+",用户登录账号:"+userDetails.getUsername());
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}
