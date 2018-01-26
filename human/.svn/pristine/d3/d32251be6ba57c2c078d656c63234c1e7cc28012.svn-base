package com.human.utils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class BindingSessionListener implements HttpSessionBindingListener{

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        String sessionName=event.getName();
        HttpSession session=event.getSession();
        System.out.println("创建session:"+session.getId()+" ,创建时间为:"+TimeUtil.getCurrTime()+",sessionName="+sessionName);
        
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        String sessionName=event.getName();
        HttpSession session=event.getSession();
        long createTime=session.getCreationTime();
        long endTime=System.currentTimeMillis();
        System.out.println("失效session:"+session.getId()+" ,有效时间为:"+((endTime-createTime)/1000/60)+"分钟,sessionName="+sessionName);
        
    }

}
