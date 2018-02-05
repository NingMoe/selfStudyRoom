package cn.xdf.selfStudyRoom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author liuwei63
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override  
    public void addViewControllers(ViewControllerRegistry registry) {   
        registry.addViewController("/").setViewName("login");   
        registry.addViewController("/login").setViewName("login");  
    }
}
