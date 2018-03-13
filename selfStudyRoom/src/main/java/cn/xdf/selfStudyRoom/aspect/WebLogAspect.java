package cn.xdf.selfStudyRoom.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * web请求日志的统一处理AOP
 * @author liuwei63
 *
 */
@Aspect
@Component
public class WebLogAspect {

	private final Logger log = LogManager.getLogger(WebLogAspect.class);
	

    @Pointcut("execution(public * cn.xdf.selfStudyRoom.web..*.*(..))")
    public void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			// 记录请求内容
			log.info("URL : " + request.getRequestURL().toString());
			log.info("HTTP_METHOD : " + request.getMethod());
			log.info("IP : " + request.getRemoteAddr());
			log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName());
			log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		}

	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		log.info("RESPONSE : " + ret);
	}

	//后置异常通知  
    @AfterThrowing("webLog()")  
    public void throwss(JoinPoint jp){  
        log.error("CLASS_METHOD : "+jp.getSignature().getDeclaringTypeName() + "."+ jp.getSignature().getName()+" ,ARGS : " + Arrays.toString(jp.getArgs())+"调用过程中有异常!");
    } 
}
