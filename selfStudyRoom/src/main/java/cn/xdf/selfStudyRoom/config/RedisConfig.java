package cn.xdf.selfStudyRoom.config;

import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.JedisPoolConfig;





@Configuration
@PropertySource("classpath:redis.properties")
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	private final Logger logger = LogManager.getLogger(RedisConfig.class);
	
	@Value("${redis.hostName}")
	private String hostName;
	 
	@Value("${redis.password}")
	private String password;
		
	@Value("${redis.port}")
	private int port;
	
	@Value("${redis.maxActive}")
	private int maxTotal ;

	@Value("${redis.maxIdle}")
    private int maxIdle ;

	@Value("${redis.minIdle}")
    private int minIdle ;
		
	@Value("${redis.timeout}")
	private int timeout;
	
	@Value("${redis.maxWait}")
	private long maxWaitMillis;
	
	@Value("${redis.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Value("${redis.testOnReturn}")
	private boolean testOnReturn;
	
	
	@Bean	
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setMaxWaitMillis(maxWaitMillis);
		config.setMinIdle(minIdle);	
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		return config;
	}

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config);  
        factory.setHostName(hostName);
        factory.setPassword(password);
        factory.setPort(port); 
        factory.setTimeout(timeout);
        logger.info("JedisConnectionFactory bean init success.");  
        return factory; 
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    
    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }
    
    @Bean(name="jedisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public RedisTemplate<String,String> redisTemplate(){
         RedisTemplate<String,String> redisTemplate = new StringRedisTemplate();
         redisTemplate.setConnectionFactory(jedisConnectionFactory());
         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);
         redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
         redisTemplate.afterPropertiesSet();
         return redisTemplate;
     }

    
    /**
     * 自定义CacheErrorHandler来替代默认的SimpleCacheErrorHandler
     */
    @Bean
	@Override
	public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler=new CacheErrorHandler(){

			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				logger.error(exception.getMessage());				
			}

			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				logger.error(exception.getMessage());				
			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				logger.error(exception.getMessage());				
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				logger.error(exception.getMessage());				
			}};
			
		return cacheErrorHandler;
	}
    
    
    
    
}
