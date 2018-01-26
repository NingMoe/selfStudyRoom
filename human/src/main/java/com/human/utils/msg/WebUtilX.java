package com.human.utils.msg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class WebUtilX {
 
	public static HttpWebRequest Create(String urlstr) throws MalformedURLException {
		URL	url = new URL(urlstr);
		return new HttpWebRequest(url);
	}
	
    public static String DoPost(String url, Map<String, String> parameters) throws IOException
    {
        HttpWebRequest req = Create(url);
        req.Method = "POST";
        req.KeepAlive = true;
        req.UserAgent = "Top4Net";
        req.ContentType = "application/x-www-form-urlencoded;charset=utf-8";
        req.setPostInfo(parameters);
        HttpWebResponse response = req.GetResponse();            
        HttpWebResponse rsp = (HttpWebResponse)response;
        return rsp.getHtml();
    }
    public static String DoGet(String url) throws IOException
    {
        HttpWebRequest req = Create(url);
        req.Method = "GET";
        req.KeepAlive = true;
        req.UserAgent = "Top4Net";
      
       
        HttpWebResponse response = req.GetResponse();            
        HttpWebResponse rsp = response;
        return rsp.getHtml();
    }

    public static String UrlEncode(String url) {
		try {
			return java.net.URLEncoder.encode(url,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

}
