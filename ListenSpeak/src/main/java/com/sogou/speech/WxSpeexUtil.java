package com.sogou.speech;

public class WxSpeexUtil {
	
    static {
        System.loadLibrary("speexwxjni");
    }
    
    public WxSpeexUtil() {
        // TODO Auto-generated constructor stub
    }
    
    public static native boolean decode(String in, String out);
}