package com.ls.spt.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.ls.spt.security.MyUser;


public class Common {
    
    /**
     * 移动端agent常量
     */
    private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" };
    
	/**
	 * 判断变量是否为空
	 * 
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
	 * 获取map的keys
	 * @param map
	 * @return
	 */
	public static List<String> getMapKeys(Map<String,Object> map){
		List<String> list=new ArrayList<String>();
		Set<String> set=map.keySet();
		for(String key:set){
			list.add(key);
		}
		return list;
	}
	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
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

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	       if(ip != null && ip.length() > 15){    
	           if(ip.indexOf(",")>0){     
	               ip = ip.substring(0,ip.indexOf(","));     
	           }     
	       }     
		return ip;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * 获取当前认证通过的用户名
	 * @return
	 */
	public static String getAuthenticatedUsername() { 
	    String username = null; 
	    try{
	    Object principal = SecurityContextHolder.getContext() 
	        .getAuthentication().getPrincipal(); 
	    if (principal instanceof UserDetails) { 
	        username = ((UserDetails) principal).getUsername(); 
	    } else { 
	        username = principal.toString(); 
	    } 
	    }catch(Exception e){
	    	
	    }
	    return username; 
	 }

	/**
     * 获取当前认证通过的用户名
     * @return
     */
    public static MyUser getMyUser() { 
        MyUser myUser = null;
        try{
        Object principal = SecurityContextHolder.getContext() 
            .getAuthentication().getPrincipal(); 
        if (principal instanceof UserDetails) { 
            myUser = ((MyUser) principal); 
        }
        }catch(Exception e){
            
        }
        return myUser; 
     }
    
    /** 
     * 手机号验证 
     *  
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
     * 根据request判断是否移动端
     */
    public static boolean isMobileRequest(HttpServletRequest request){
        String ua = request.getHeader("User-Agent");
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
