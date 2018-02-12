package cn.xdf.selfStudyRoom.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import cn.xdf.selfStudyRoom.security.SecurityUser;


public class CommonUtil {
	
	
	/**
	 * 返回用户的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request){      
        String ip = request.getHeader("x-forwarded-for");      
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("Proxy-Client-IP");      
        }      
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("WL-Proxy-Client-IP");      
        }      
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("HTTP_CLIENT_IP");      
        }      
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");      
        }      
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getRemoteAddr();      
        }      
        return ip;      
    }    
	
	/** 
     * 手机号验证 
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }
    
    /**
	 * 获取当前认证通过的用户名
	 * @return
	 */
	public static String getAuthenticatedUsername() { 
		String username = null;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		return username;
	 }

	/**
     * 获取当前认证通过的用户名
     * @return
     */
    public static SecurityUser getMyUser() { 
		SecurityUser myUser = null;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserDetails) {
				myUser = ((SecurityUser) principal);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		return myUser;
     }
    
    /**
	 * 判断变量是否为空
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 用来去掉List中空值和相同项的。
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> nullArr = new ArrayList<String>();  
	    nullArr.add(null);  
	    nullArr.add("");
	    //去除空和空字符串
	    list.removeAll(nullArr);
		Set<String> difList = new HashSet<String>();
		//放进SET去重
		difList.addAll(list);
		List<String> newList=new ArrayList<String>();
		newList.addAll(difList);
		return newList;
	}
}
