package com.human.utils;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.human.coupon.entity.StudentRes;
import com.human.customer.entity.CutomerCenterStudent;

public class CustomerStuInterceptor  extends HandlerInterceptorAdapter {
    private final Logger logger = LogManager.getLogger(CustomerStuInterceptor.class);
    
    @Resource
    private CoupUtil cUtil;

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        logger.info("================接入学员中心拦截器==================");
        HttpSession session = request.getSession();
        boolean beFilter = true; 
        String[] noFilters=BindingConstants.NO_STUDENT_CENTER_FILTERS;
        String requestUri = request.getRequestURI();
        logger.info("请求地址："+requestUri);
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        for (String s : noFilters) {  
            if (url.indexOf(s) != -1) {  
                beFilter = false;  
                break;  
            }
        }
        if(beFilter){
            logger.info("================需要拦截==================");
            CutomerCenterStudent stu = (CutomerCenterStudent) session.getAttribute(BindingConstants.CUTOMER_CENTER_STUDENT);
            if (stu == null || StringUtils.isBlank(stu.getOpenid()) || StringUtils.isBlank(stu.getSchoolid())) {
                String schoolid =request.getParameter("schoolid");
                String openid =request.getParameter("openid");
                logger.info("微信登陆首选返回的【schoolid="+schoolid+",openid="+openid+"】");
                if (StringUtils.isNotBlank(openid) && StringUtils.isNoneBlank(schoolid)) {
                    logger.info("Interceptor：获得授权信息如下！");
                    Map<String, Object> map = cUtil.getStudentCode(openid, schoolid);
                    logger.info(JSON.toJSONString(map));
                    StudentRes sr = (StudentRes) map.get("Data");
                    stu = new CutomerCenterStudent();
                    if ((boolean) map.get("flag")&&sr != null) {
                            stu.setSchoolid(sr.getSchoolid());
                            stu.setStudentname(sr.getStudentname());
                            stu.setStudentmoblie(sr.getStudentmoblie());
                            stu.setStudentcode(sr.getStudentcode());
                            stu.setOpenid(sr.getOpenid());
                            session.setAttribute(BindingConstants.CUTOMER_CENTER_STUDENT, stu);
                    }else {
                        stu.setSchoolid(schoolid);
                        stu.setOpenid(openid);
                        session.setAttribute(BindingConstants.CUTOMER_CENTER_STUDENT, stu);
                    }
                }
                else {
                    logger.info("Interceptor：跳转到绑定页面！");
                    response.sendRedirect("http://wxpay.xdf.cn/silenceauthorize/view.do?schoolid=25&callid=18&parm=12");
                    return false;
                }
            }
            logger.info("登陆用户的信息：" + JSON.toJSONString(stu));
        }
        logger.info("通过拦截器");
        return beFilter;
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
