package com.human.utils.msg;

import java.security.MessageDigest;

public class SmsMd5 {
    public final static String md5(String s) {
        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            return Hex.encodeHexString(md).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    } 
}
