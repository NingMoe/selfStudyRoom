package com.ls.spt.utils;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ls.spt.student.entity.StudentUser;

public class StudentPcInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger = LogManager.getLogger(StudentPcInterceptor.class);

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession();
        boolean beFilter = true; 
        String[] noFilters=StudentPcConstants.NO_FILTERS;
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		for (String s : noFilters) {  
            if (url.indexOf(s) != -1) {  
                beFilter = false;  
                break;  
            }  
        }
		if(beFilter){
			StudentUser studentUser = (StudentUser) session.getAttribute(StudentPcConstants.STUDENT_USER);
			/**
			 * 判断是否绑定了用户,没有绑定跳转到绑定页面
			 */
			if (studentUser == null) {
                logger.info("Interceptor：pc学生端，跳转到登录页面！");
                System.out.println(requestUri+"?"+request.getQueryString());
                String s = URLEncoder.encode(requestUri+"?"+request.getQueryString(),"utf-8");
                request.getRequestDispatcher("/manager/login.html").forward(request, response);
                return false;
            } 
		}
		return true;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*log.info("==============执行顺序: 2、postHandle================");
		if (modelAndView != null) { // 加入当前时间
			modelAndView.addObject("var", "测试postHandle");
		}*/
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/*log.info("==============执行顺序: 3、afterCompletion================");*/
	}
}
