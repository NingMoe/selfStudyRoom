package com.ls.spt.utils;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Food implements Delayed{  
    private String foodName;  
    private long saveTime;//保存时间  
    private long expireTime;//过期时刻=当前时间+保存时间  
    public Food(String foodName,long saveTime){  
        this.foodName=foodName;  
        this.saveTime=saveTime;  
        this.expireTime=TimeUnit.NANOSECONDS.convert(saveTime, TimeUnit.SECONDS)+System.nanoTime();  
    }  
    @Override  
    public int compareTo(Delayed o) {  
        Food that = (Food)o;  
        if(this.expireTime > that.expireTime){//过期时刻越靠后，越排在队尾.  
            return 1;  
        }else if(this.expireTime==that.expireTime){  
            return 0;  
        }else{  
            return -1;  
        }  
    }  
    @Override  
    public long getDelay(TimeUnit unit) {  
        return unit.convert(this.expireTime-System.nanoTime(), TimeUnit.NANOSECONDS);  
    }  
    public String getFoodName(){  
        return this.foodName;  
    }  
    public long getExpireTime(){  
        return this.expireTime;  
    }  
    public long getSaveTime(){  
        return this.saveTime;  
    }  
}  