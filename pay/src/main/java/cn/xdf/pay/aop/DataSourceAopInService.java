package cn.xdf.pay.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import cn.xdf.pay.config.dbconfig.DataSourceContextHolder;
import cn.xdf.pay.config.dbconfig.DataSourceType;


/**
 * 在service层决定数据源
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * 如果一旦开始切换到写库，则之后的读都会走写库
 * @author liuwei63
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceAopInService implements PriorityOrdered{

    private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);
	
	@Before("execution(* cn.xdf.pay.service..*.*(..)) "
			+ " and @annotation(cn.xdf.pay.annotation.ReadDataSource) ")
	public void setReadDataSourceType() {
		log.info("---------设置读库--------");
		//如果已经开启写事务了，那之后的所有读都从写库读
		if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
			DataSourceContextHolder.setRead();
		}
	    
	}
	
	@Before("execution(* cn.xdf.pay.service..*.*(..)) "
			+ " and @annotation(cn.xdf.pay.annotation.WriteDataSource) ")
	public void setWriteDataSourceType() {
		log.info("---------设置写库--------");
	    DataSourceContextHolder.setWrite();
	}
    
	@Override
	public int getOrder() {
		/**
		 * 值越小，越优先执行
		 * 要优于事务的执行
		 * 在启动类中加上了@EnableTransactionManagement(order = 10) 
		 */
		return 1;
	}

}
