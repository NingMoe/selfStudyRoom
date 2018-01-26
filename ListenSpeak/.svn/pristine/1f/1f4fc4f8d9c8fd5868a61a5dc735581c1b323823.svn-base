package com.ls.spt.utils;

import java.util.Date;
import java.util.concurrent.DelayQueue;

class FoodChecker implements Runnable{  
    private DelayQueue<Food> queue;  
    public FoodChecker(DelayQueue<Food> queue){  
        this.queue=queue;  
    }  
    @Override  
    public void run() {  
        try{  
            System.out.println("开始!");  
            while(true){  
                Food food = queue.take();//此处会阻塞,没有时过期食品时不会取出  
                System.out.println(food.getFoodName()+"取出时间:"+new Date());  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}  