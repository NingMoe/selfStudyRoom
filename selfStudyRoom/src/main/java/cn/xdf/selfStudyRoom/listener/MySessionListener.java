package cn.xdf.selfStudyRoom.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import cn.xdf.selfStudyRoom.utils.GlobalSessionUtil;
import cn.xdf.selfStudyRoom.utils.TimeUtil;


@WebListener
public class MySessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession ses = se.getSession(); 
        String id=ses.getId();   
        System.out.println("创建session:"+id+" ,创建时间为:"+TimeUtil.getCurrTime());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se){
		 HttpSession ses = se.getSession();      
	     String id=ses.getId(); 
	     long createTime=ses.getCreationTime();
	     long endTime=System.currentTimeMillis();
	     System.out.println("失效session:"+id+" ,失效时间为:"+TimeUtil.getCurrTime());
	     System.out.println("sessionId:"+id+",有效时间为:"+((endTime-createTime)/1000/60));	
	     String username=(String) ses.getAttribute("SPRING_SECURITY_CONTEXT_USERNAME");
	     GlobalSessionUtil.usernameAndSessionIdListMap.remove(username);
	     GlobalSessionUtil.SessionIdAndSessionMap.remove(id);
	     ses.removeAttribute("SPRING_SECURITY_CONTEXT");
	     ses.removeAttribute("SPRING_SECURITY_CONTEXT_USERNAME");
	}

}
