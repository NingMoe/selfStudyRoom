package cn.xdf.selfStudyRoom;

import java.io.IOException;
import javax.annotation.PostConstruct;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import cn.xdf.selfStudyRoom.security.Appctx;


@SpringBootApplication
@MapperScan("cn.xdf.selfStudyRoom.domain.dao")
@EnableCaching
@EnableScheduling
public class Application {

	private final Logger logger = LogManager.getLogger(Application.class);
	
	@PostConstruct  
    public void initApplication() throws IOException {  
		logger.info("Running with Spring profile(s) : {}");   
    }  
	
	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(Application.class);
		Appctx.ctx=app.run(args);
	}

	
	@Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                //不限制上传文件大小
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }
}
