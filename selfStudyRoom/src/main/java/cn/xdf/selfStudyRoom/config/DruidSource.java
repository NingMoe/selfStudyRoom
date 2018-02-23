package cn.xdf.selfStudyRoom.config;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DruidSource {
	
	  @Value("${jdbc.url}")
	  private String dbUrl;
	  	 
	  @Value("${jdbc.username}")
	  private String username;
	 
	  @Value("${jdbc.password}")
	  private String password;
	 
	  @Value("${jdbc.driverClassName}")
	  private String driverClassName;
	 
	  @Value("${jdbc.initialSize}")
	  private int initialSize;
	 
	  @Value("${jdbc.minIdle}")
	  private int minIdle;
	 
	  @Value("${jdbc.maxActive}")
	  private int maxActive;
	 
	  @Value("${jdbc.maxWait}")
	  private int maxWait;
	 
	  @Value("${jdbc.timeBetweenEvictionRunsMillis}")
	  private int timeBetweenEvictionRunsMillis;
	 
	  @Value("${jdbc.minEvictableIdleTimeMillis}")
	  private int minEvictableIdleTimeMillis;
	  
	  @Value("${jdbc.validationQuery}")
	  private String validationQuery;
	 
	  @Value("${jdbc.testWhileIdle}")
	  private boolean testWhileIdle;
	  
	  @Value("${jdbc.testOnBorrow}")
	  private boolean testOnBorrow;
	 
	  @Value("${jdbc.testOnReturn}")
	  private boolean testOnReturn;
	 
	  @Value("${jdbc.poolPreparedStatements}")
	  private boolean poolPreparedStatements;
	 
	  @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
	  private int maxPoolPreparedStatementPerConnectionSize;
	  
	  @Value("${jdbc.filters}")	  
	  private String filters;
	 
	  @Value("${jdbc.connectionProperties}")
	  private String connectionProperties;
	  
	  @Value("${jdbc.useGlobalDataSourceStat}")  
	  private boolean useGlobalDataSourceStat;  
	      
	  @Value("${jdbc.druidLoginName}")  
	  private String druidLoginName;  
	      
	  @Value("${jdbc.druidPassword}")  
	  private String druidPassword;  
	 
	  public String getDbUrl() {
	    return dbUrl;
	  }
	 
	  public void setDbUrl(String dbUrl) {
	    this.dbUrl = dbUrl;
	  }
	 
	  public String getUsername() {
	    return username;
	  }
	 
	  public void setUsername(String username) {
	    this.username = username;
	  }
	 
	  public String getPassword() {
	    return password;
	  }
	 
	  public void setPassword(String password) {
	    this.password = password;
	  }
	 
	  public String getDriverClassName() {
	    return driverClassName;
	  }
	 
	  public void setDriverClassName(String driverClassName) {
	    this.driverClassName = driverClassName;
	  }
	 
	  public int getInitialSize() {
	    return initialSize;
	  }
	 
	  public void setInitialSize(int initialSize) {
	    this.initialSize = initialSize;
	  }
	 
	  public int getMinIdle() {
	    return minIdle;
	  }
	 
	  public void setMinIdle(int minIdle) {
	    this.minIdle = minIdle;
	  }
	 
	  public int getMaxActive() {
	    return maxActive;
	  }
	 
	  public void setMaxActive(int maxActive) {
	    this.maxActive = maxActive;
	  }
	 
	  public int getMaxWait() {
	    return maxWait;
	  }
	 
	  public void setMaxWait(int maxWait) {
	    this.maxWait = maxWait;
	  }
	 
	  public int getTimeBetweenEvictionRunsMillis() {
	    return timeBetweenEvictionRunsMillis;
	  }
	 
	  public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
	    this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	  }
	 
	  public int getMinEvictableIdleTimeMillis() {
	    return minEvictableIdleTimeMillis;
	  }
	 
	  public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
	    this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	  }
	 
	  public String getValidationQuery() {
	    return validationQuery;
	  }
	 
	  public void setValidationQuery(String validationQuery) {
	    this.validationQuery = validationQuery;
	  }
	 
	  public boolean isTestWhileIdle() {
	    return testWhileIdle;
	  }
	 
	  public void setTestWhileIdle(boolean testWhileIdle) {
	    this.testWhileIdle = testWhileIdle;
	  }
	 
	  public boolean isTestOnBorrow() {
	    return testOnBorrow;
	  }
	 
	  public void setTestOnBorrow(boolean testOnBorrow) {
	    this.testOnBorrow = testOnBorrow;
	  }
	 
	  public boolean isTestOnReturn() {
	    return testOnReturn;
	  }
	 
	  public void setTestOnReturn(boolean testOnReturn) {
	    this.testOnReturn = testOnReturn;
	  }
	 
	  public boolean isPoolPreparedStatements() {
	    return poolPreparedStatements;
	  }
	 
	  public void setPoolPreparedStatements(boolean poolPreparedStatements) {
	    this.poolPreparedStatements = poolPreparedStatements;
	  }
	 
	  public int getMaxPoolPreparedStatementPerConnectionSize() {
	    return maxPoolPreparedStatementPerConnectionSize;
	  }
	 
	  public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
	    this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	  }
	 
	  public String getFilters() {
	    return filters;
	  }
	 
	  public void setFilters(String filters) {
	    this.filters = filters;
	  }
	 
	  public String getConnectionProperties() {
	    return connectionProperties;
	  }
	 
	  public void setConnectionProperties(String connectionProperties) {
	    this.connectionProperties = connectionProperties;
	  }
	 
	  @Bean(name="dataSource",destroyMethod = "close", initMethod="init") //声明其为Bean实例
	  @Primary //在同样的DataSource中，首先使用被标注的DataSource
	  public DataSource dataSource() throws SQLException {
	    DruidDataSource datasource = new DruidDataSource();
	 
	    datasource.setUrl(this.dbUrl);
	    datasource.setUsername(username);
	    datasource.setPassword(password);
	    datasource.setDriverClassName(driverClassName);
	 

	    datasource.setInitialSize(initialSize);
	    datasource.setMinIdle(minIdle);
	    datasource.setMaxActive(maxActive);
	    datasource.setMaxWait(maxWait);
	    datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	    datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	    datasource.setValidationQuery(validationQuery);
	    datasource.setTestWhileIdle(testWhileIdle);
	    datasource.setTestOnBorrow(testOnBorrow);
	    datasource.setTestOnReturn(testOnReturn);
	    datasource.setPoolPreparedStatements(poolPreparedStatements);
	    datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);	 
	    datasource.setFilters(filters);
	    datasource.setConnectionProperties(connectionProperties);  
	    datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
	 
	    return datasource;
	  }

   /////////////////////////////////下面是druid监控访问的设置 /////////////////////////////////////////////////////////////
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*"); //url匹配
		reg.addInitParameter("allow", "10.167.3.50,127.0.0.1"); // IP白名单(没有配置或者为空，则允许所有访问)
		reg.addInitParameter("deny", "192.168.16.111"); // IP黑名单(存在共同时，deny优先于allow)
		reg.addInitParameter("loginUsername", this.druidLoginName);// 登录名
		reg.addInitParameter("loginPassword", this.druidPassword);// 登录密码
		reg.addInitParameter("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能
		return reg;
	}

	@Bean(name = "druidWebStatFilter")
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); // 忽略资源
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}
	  
}
