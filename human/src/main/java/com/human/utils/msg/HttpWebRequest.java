package com.human.utils.msg;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;


public class HttpWebRequest {
	URL url =null;
	public String Method;
	public boolean KeepAlive;
	public String UserAgent;
	public String ContentType;
	public HttpWebRequest(URL url) {
		this.url=url;
 
	}
	String info = "";
	
	
    private  String BuildPostData(java.util.Map<String, String> parameters)
    {
        StringBuilder postData = new StringBuilder();
        boolean hasParam = false;
       
        Set<Map.Entry<String, String>> demset = parameters.entrySet();
        for(Map.Entry<String, String> dem:demset)
        {
            String name = dem.getKey();
            String value = dem.getValue();
            // ���Բ����������ֵΪ�յĲ���
            if (name!=null && !(value==null||value.isEmpty()))
            {
                if (hasParam)
                {
                    postData.append("&");
                }

                postData.append(name);
                postData.append("=");
                postData.append(EnCode.rawurlencode(value));
                hasParam = true;
            }
        }

        return postData.toString();
    }
    
    public	String setPostInfo(Map<String, String> map) {
		info="";
		info= BuildPostData(map);
		return info;
	}

	@SuppressWarnings("static-access")
    public HttpWebResponse GetResponse() throws IOException {
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setFollowRedirects(true);
	 
		httpConn.setInstanceFollowRedirects(false);

		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("User-Agent",UserAgent);
		if(KeepAlive){
		httpConn.setRequestProperty("Connection", "keep-alive");
		}
		httpConn.setRequestProperty("Content-Type",ContentType);
		httpConn.setRequestProperty("method",Method);
		if(info.length()>0){
			PrintStream send = new PrintStream(httpConn.getOutputStream());
			send.print(info);
			send.close();
		}
		return new HttpWebResponse(httpConn);
	}

}
