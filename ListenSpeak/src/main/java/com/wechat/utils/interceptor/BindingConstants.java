package com.wechat.utils.interceptor;

public class BindingConstants {
	
    /**
     * session中微信用户信息
     */
    public final static String BINDING_USERINFO = "binding_userinfo";
    
    /**
     * session中用户的学生信息
     */
    public final static String BINDING_STUDENT_USER = "binding_student_user";
    
    /**
     * session中跳转来的原链接
     */
    public final static String BINDING_REURL = "binding_reurl";
    
	 /**
	  * 设置不拦截的对象
	  */
    public final static String[] NO_FILTERS = new String[] {"wechat/binding/student", "/view/", "/img/", "/js/", "/css/", ".js",  ".css", "/easyUi/", "/zTree/", "/jquery/"};
}
