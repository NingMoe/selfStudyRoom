package com.human.utils.msg;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpWebResponse {
	HttpURLConnection conn;
	public HttpWebResponse(HttpURLConnection httpConn) {
		conn=httpConn;
	}

	public String getHtml() throws IOException {
		java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream();
		BufferedInputStream bis = null;
		byte[] buf = new byte[4096];
		int size = 0;
		String type=conn.getContentType();
		String code="UTF-8";
		if(type!=null&&type.indexOf("=GBK")>0)code="GBK";
		if(type!=null&&type.indexOf("=UTF-8")>0)code="UTF-8";		
		bis = new BufferedInputStream(conn.getInputStream());
		while ((size = bis.read(buf)) != -1){
			bout.write(buf, 0, size);
 		}
		bout.flush();
		bout.close();
		String str = new String(bout.toByteArray(), code);
		bis.close();
		return str;
	}

}
