package cn.xdf.selfStudyRoom;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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

}
