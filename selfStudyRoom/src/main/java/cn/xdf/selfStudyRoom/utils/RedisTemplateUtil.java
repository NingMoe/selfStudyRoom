package cn.xdf.selfStudyRoom.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class RedisTemplateUtil {
    @Autowired @Qualifier("jedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;
    
    
    public void set(String key,Object value,Long time){
        if(value.getClass().equals(String.class)){  
            redisTemplate.opsForValue().set(key, value.toString());                 
        }else if(value.getClass().equals(Integer.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else if(value.getClass().equals(Double.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else if(value.getClass().equals(Float.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else if(value.getClass().equals(Short.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else if(value.getClass().equals(Long.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else if(value.getClass().equals(Boolean.class)){  
            redisTemplate.opsForValue().set(key, value.toString());  
        }else{  
            redisTemplate.opsForValue().set(key, value);              
        }  
        if(time !=null ){  
            redisTemplate.expire(key,time, TimeUnit.SECONDS);  
        }  
    }
    
    public void setJson(String key,Object value,Long time){  
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));  
        if(time !=null ){  
            redisTemplate.expire(key, time, TimeUnit.SECONDS);  
        }  
    } 
    
    public <T> void setMap(String key, Map<String, T> map, Long time){  
        redisTemplate.opsForHash().putAll(key, map);  
        if(time !=null ){  
            redisTemplate.expire(key, time, TimeUnit.SECONDS);  
        }  
    }  
    
    public <T> void addMap(String key, String field, T obj){  
        redisTemplate.opsForHash().put(key, field, obj);  
    }  
    
    public <T> void setList(String key, List<T> list, Long time){  
        redisTemplate.opsForValue().set(key, list);
        if(time !=null ){  
            redisTemplate.expire(key, time, TimeUnit.SECONDS);  
        }  
    } 
    
    public Object get(String key) {  
        return redisTemplate.boundValueOps(key).get();  
    }  
    
    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> clazz) {  
        return (T)redisTemplate.boundValueOps(key).get();
    } 
    
    public <T> Map<String, T> mget(String key, Class<T> clazz){  
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);   
        return boundHashOperations.entries();  
    }  
    
    public void del(String key){  
        redisTemplate.delete(key);  
    } 
    
    public boolean isExist(String key){
        return redisTemplate.hasKey(key);
    }
  
}
