package com.human.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession ses = se.getSession(); 
        String id=ses.getId();   
        System.out.println("创建session:"+id+" ,创建时间为:"+TimeUtil.getCurrTime());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession ses = se.getSession();      
        String id=ses.getId(); 
        long createTime=ses.getCreationTime();
        long endTime=System.currentTimeMillis();
        System.out.println("失效session:"+id+" ,失效时间为:"+TimeUtil.getCurrTime());
        System.out.println("sessionId:"+id+",有效时间为:"+((endTime-createTime)/1000/60));
    }

}
