package com.ls.spt.utils.msg;


import java.util.Locale;
import java.util.ResourceBundle;

public class MsgConfig {
	
	public static String getString(String s) {
		try {
			return getResourceBundle().getString(s);
		} catch (Exception e) {			
           e.printStackTrace();
		}
		return null;
	}
	
	static ResourceBundle bundle;  

	private static ResourceBundle getResourceBundle() {
		try {
			if (bundle == null){
				bundle = ResourceBundle.getBundle("properties/Sms",Locale.getDefault());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bundle;
	}
	
	public static String ApiRootUrl = getString("sms.apiUrl");  //
    public static String appId = getString("sms.appId"); 
    public static String appKey = getString("sms.appKey"); 

}
