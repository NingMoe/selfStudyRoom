package com.human.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 获取微信url地址的属性文件
 * @author niuchong
 * @date 2014-12-17 15:58:00
 */
public class PropertiesUrlUtil {

	/**
	 * 获取属性文件中的某个值
	 * @param type 属性文件中的键
	 * @return 值
	 */
	public static String getPropertyeString(String type){
		//获取当前类加载的根目录，如：/C:/APP/Tomcat 6.0/webapps/consultation/WEB-INF/classes/  
		String dirpath = PropertiesUrlUtil.class.getClassLoader().getResource("").getPath();
		String filePath = dirpath + "properties/wechatbinding_url.properties";
		//实例化
		Properties properties = new Properties();
		try{
			//创建一个流文件
			FileInputStream file = new FileInputStream(new File(filePath));
			properties.load(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(type);
		
	}
}
