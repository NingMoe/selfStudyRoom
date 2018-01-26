package com.ls.spt.utils;

import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;


/**
 * AOP注解方法实现日志管理 利用spring AOP 切面技术记录日志 定义切面类（这个是切面类和切入点整天合在一起的),这种情况是共享切入点情况;
 * 
 * @author lanyuan 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Aspect
// 该注解标示该类为切面类
@Component
public class LogAopAction {

	private final  Logger logger = LogManager.getLogger(LogAopAction.class);
	
	
	/**
     * 声明切入点表达式，一般在该方法中不再添加其他代码。
     * 使用@Pointcut来声明切入点表达式。
     * 后面的通知直接使用方法名来引用当前的切入点表达式。
     */
    @Pointcut("execution(* com.spt.*.controller..*.*(..))")
    public void declareJoinPointExpression() {}
    
    /**
    *前置通知，在目标方法开始之前执行。
    *@Before("execution(public int com.spring.aop.impl.ArithmeticCalculator.add(int, int))")这样写可以指定特定的方法。
     * @param joinpoint
     */
 /*   @Before("declareJoinPointExpression()")
    //这里使用切入点表达式即可。后面的可以都改成切入点表达式。如果这个切入点表达式在别的包中，在前面加上包名和类名即可。
    public void beforeMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        List<Object>args = Arrays.asList(joinpoint.getArgs());
        logger.info("前置通知："+joinpoint.getSignature().getDeclaringTypeName()+"."+methodName +" begins with " + args);
    }*/
 
    /**
    *后置通知，在目标方法执行之后开始执行，无论目标方法是否抛出异常。
    *在后置通知中不能访问目标方法执行的结果。
     * @param joinpoint
     */
   /* @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinpoint) {
        String methodName = joinpoint.getSignature().getName();
        //List<Object>args = Arrays.asList(joinpoint.getArgs());  后置通知方法中可以获取到参数
        logger.info("后置通知："+joinpoint.getSignature().getDeclaringTypeName()+"."+methodName +" ends ");
    }*/
     
    /**
    *返回通知，在方法正常结束之后执行。
    *可以访问到方法的返回值。
     * @param joinpoint
     * @param result 目标方法的返回值
     */
    /*@AfterReturning(value="declareJoinPointExpression()", returning="result")
    public void afterReturnning(JoinPoint joinpoint, Object result) {
        String methodName = joinpoint.getSignature().getName();
        logger.info("返回通知："+joinpoint.getSignature().getDeclaringTypeName()+"."+ methodName +" ends with " + result);
    }*/
     
    /**
    *异常通知。目标方法出现异常的时候执行，可以访问到异常对象，可以指定在出现特定异常时才执行。
    *假如把参数写成NullPointerException则只在出现空指针异常的时候执行。
     * @param joinpoint
     * @param e
     */
   /* @AfterThrowing(value="declareJoinPointExpression()", throwing="e")
    public void afterThrowing(JoinPoint joinpoint, Exception e) {
        String methodName = joinpoint.getSignature().getName();
        logger.info("异常通知："+joinpoint.getSignature().getDeclaringTypeName()+"."+ methodName +" occurs exception " + e);
    }*/
    
    
    
	@Around(value="declareJoinPointExpression()")
	public Object around(ProceedingJoinPoint point) {
		Object result = null;
		// 执行方法名
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		String user = null;
		Long  start = System.currentTimeMillis();;
		Long end = 0L;
		String ip = null;
		HttpServletRequest request=null;
		Map<?,?> inputParamMap=null;
		try {
		Object[] args=point.getArgs();
		result = point.proceed();
		
		 for (int i = 0; i < args.length; i++) {  
             if (args[i] instanceof HttpServletRequest) {  
                 request = (HttpServletRequest) args[i];  
                 break;
             }
         }  
		 if(null!=request){
		  inputParamMap = request.getParameterMap();
		  ip = Common.toIpAddr(request);
		 } 
			// 执行方法所消耗的时间
			end = System.currentTimeMillis();
			// 登录名
			user = Common.getAuthenticatedUsername();
			logger.info("\n"+"user："+user
	    +"\n"+"op_ip："+ip+"\n"+"className："+className+"\n"+"methodName："+methodName+"; \n"+"op_time：" +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start) + "\n"+"pro_time：" + (end - start) + "ms ;"
	    +" \n"+"param："+JSON.toJSONString(inputParamMap)+";"+"\n"+"result："+JSON.toJSONString(result));
		
	}catch (Throwable e) {
		logger.error("Log Aop is error !",e);
	}
		return result;
	}
}
