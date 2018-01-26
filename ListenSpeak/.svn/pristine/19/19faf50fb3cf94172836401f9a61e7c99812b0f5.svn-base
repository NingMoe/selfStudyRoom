package com.sogou.speech;

public class SpeexInterface {
	
    static {
        System.loadLibrary("speexjni");
    }
    
    public SpeexInterface() {
        // TODO Auto-generated constructor stub
    }
    
	public native long createSpeex();
	
	public native void processIn(long instance, short[] data, int len);
	
	public native byte[] processOut(long instance, boolean force);		
	
	public static void main(String[] args) {
        SpeexInterface s = new SpeexInterface();
        System.out.println(s.createSpeex());
    }

}