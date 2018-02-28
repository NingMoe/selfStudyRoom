package cn.xdf.selfStudyRoom.utils;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

public class GlobalSessionUtil {
	
	public static HashMap<String, HttpSession> SessionIdAndSessionMap = new HashMap<String, HttpSession>();

	public static HashMap<String, List<String>> usernameAndSessionIdListMap = new HashMap<String, List<String>>();
    
    
}
