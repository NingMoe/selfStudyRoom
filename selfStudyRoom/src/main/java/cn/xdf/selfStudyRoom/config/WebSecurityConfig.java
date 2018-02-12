package cn.xdf.selfStudyRoom.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import cn.xdf.selfStudyRoom.security.LoginSuccessHandler;
import cn.xdf.selfStudyRoom.security.MySecurityFilterInterceptor;
import cn.xdf.selfStudyRoom.security.MySecurityUserDetailServiceImpl;

/**
 * spring security 配置类
 * @author liuwei63
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private MySecurityFilterInterceptor mySecurityFilter; 
	
	@Autowired   
    private MySecurityUserDetailServiceImpl customUserDetailsService;  
	
	@Bean
	@Override  	
    public AuthenticationManager authenticationManagerBean() throws Exception {         
       return super.authenticationManagerBean();        
    } 
	
    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 登录地址为 "/login"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(mySecurityFilter,  FilterSecurityInterceptor.class)
            .authorizeRequests()
                .antMatchers("/loginCheck","/s/*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login") //成功退出后跳转到登录页
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表  
                .tokenValiditySeconds(1209600);  
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserDetailsService);
       //不删除凭据，以便记住用户  
       auth.eraseCredentials(false); 
    }
    
    @Bean  
    public Md5PasswordEncoder passwordEncoder() {  
        return new Md5PasswordEncoder();  
    }  
    
    @Bean  
    public LoginSuccessHandler loginSuccessHandler(){  
        return new LoginSuccessHandler();  
    }  
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/common/**");
        web.ignoring().antMatchers("/loginCheck**");
    }
    
    
    

}