package com.human.utils;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     * 
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        // HttpHost proxy = new HttpHost("120.76.203.31", 80, "http");
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * 通过连接池获取HttpClient 添加cookie的版本
     * 
     * @return
     */
    private static CloseableHttpClient getHttpClient(CookieStore cookieStore) {
        init();
        // HttpHost proxy = new HttpHost("120.76.203.31", 80, "http");
        return HttpClients.custom().setDefaultCookieStore(cookieStore).setConnectionManager(cm).build();
    }

    /**
     * 
     * @param url
     * @return
     */
    public static String httpGetRequest(String url, CookieStore cookieStore) {
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet, cookieStore);
    }

    public static String httpGetRequest(String url, CookieStore cookieStore, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet, cookieStore);
    }

    public static String httpGetRequest(String url, CookieStore cookieStore, Map<String, Object> headers, Map<String, Object> params) throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet, cookieStore);
    }

    public static String httpPostRequest(String url, CookieStore cookieStore) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost, cookieStore);
    }

    /**
     * 发送 http put 请求
     * 
     * @param url
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String httpPutRequest(String url, String jsonObj) throws UnsupportedEncodingException {
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-type", "application/json");
        StringEntity stringEntity = new StringEntity(jsonObj, UTF_8);
        httpPut.setEntity(stringEntity);
        return getResult(httpPut, null);
    }

    public static String httpPostRequest1(String url, CookieStore cookieStore, Map<String, Object> params, String token) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        httpPost.setHeader("Authorization", "bearer " + token);
        httpPost.setHeader("User-Agent", "MicroServiceApp");
        return getResult(httpPost, cookieStore);
    }

    public static String httpPostJson(String url, String json, String token) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "bearer " + token);
        httpPost.setHeader("User-Agent", "MicroServiceApp");
        return getResult(httpPost, null);
    }
    
    public static String httpSimplePostJson(String url, String json) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return getResult(httpPost, null);
    }

    public static String httpPostRequest(String url, CookieStore cookieStore, Map<String, Object> params) throws UnsupportedEncodingException {
        System.out.println(url);
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getResult(httpPost, cookieStore);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    /**
     * 处理Http请求
     * 
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request, CookieStore cookieStore) {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = null;
        if (cookieStore == null) {
            httpClient = getHttpClient();
        }
        else {
            httpClient = getHttpClient(cookieStore);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity, "utf-8");
                    response.close();
                    // httpClient.close();
                    return result;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            
        }
        return EMPTY_STR;
    }

    public static String getStatus(String phone) {
        String url = "http://pywx.speiyou.com/register/ajaxcheckphone?phone=" + phone;
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("cookie_city", "%7B%22city%22%3A%22%5Cu5317%5Cu4eac%22%2C%22city_number%22%3A%22010%22%7D");
        cookie.setDomain("pywx.speiyou.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        return HttpClientUtil.httpGetRequest(url, cookieStore);
    }

    /**
     * 返回新东方快速报班系统-约定的签名
     * 
     * @param map
     * @return
     */
    public static String generalRequestSignature(Map<String, Object> map) {
        String signature = "";
        if (map == null) {
            return signature;
        }
        try {
            List<String> keys = new ArrayList<String>(map.keySet());
            Collections.sort(keys);
            String prestr = "";
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i).trim();
                String value = (String) map.get(key);
                // if (value != null){//值为空，不拼接
                value = value.trim();
                if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                    prestr = prestr + key + "=" + value;
                }
                else {
                    prestr = prestr + key + "=" + value + "&";
                }
                // }
            } // 循环结束
            signature = Md5Tool.getMd5(prestr + Md5Tool.getMd5("jfienfodxplxsutjhvdh298kinvbxop0"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
       /*String s1 = httpPostJson("http://xubanbm.staff.xdf.cn/Api/LimitWP/GetWindowPeriod"
                ,"25","fWkWZYv2iR2r6mWwJCquzEKDtlTkJNDrF3OFyi5K5yxYNvKrAnW8iUGDChg1cYYbImMPsdosW-Z5K-geieQwWe742h-C4znxHDnjHBn5hTVK0sUW2BIOyJy5i-9JjXqiouXzYLRULOkCmQaMvvBJh_2ccoOyoZ4VDKNYUfz-OeUjyBLr2Vn-T_7utLUELuJn5ezq5sMehyBP67H4B5cD19UFwePfe_UQqB3b3jYaYgaXl9W1dMssXYhvK9_HhmDKNr3olP6TAvjvp2_1aV8TrQGktHVzoLoIt9sse1AGOcRu497WHfblLrV0vUI49oWIsZ8ToKeAKk8");
        System.out.println(s1);*/
        
       /* String s1 = httpPostJson("http://xubanbm.staff.xdf.cn/Api/WindowPeriod_StudentPackageApi/Save","[{\"Id\": null,\"nSchoolId\":25,\"sWindowPeriodId\": \"ff841eda-76d7-4f57-a17d-b51fdbb06dff\",\"sStageId\":\"6645d030-9857-40bc-b29f-507fdffd7706\",\"sStageName\": \"尖子班限制\",\"sStudentCode\": \"HF100156\",\"sStudentName\": \"\",\"sMobile\": \"\",\"sContinuedClassCode\": \"CE1823055\",\"sContinuedClassName\": \"中考尖子英语秋季班（超尖班）\",\"dtCreateDate\": \"\",\"sCreateUserId\":\"\",\"dtModifyDate\":null,\"sModifyUserId\":null,\"bIsDelete\":false,\"dtDeleteDate\":null,\"sDeleteUserId\":null,\"ObjectState\":0}]","wAAt3eOan0WLCfdeIb4mIw_vlSyTpqt_lVzA6kFNgn3jvDyi1YVCsJXPDLKCl4IcTpnN92admCvn1RLamSgKqIsh8LlnNN01iiMOPyYxf-SwXXC4czbauyT6TkRF_okjt6HD4ta9X4ySCDkTEzVfiNh9gW062xd7xxAn9r7OvWk7p8in382pRISsAxQwvKIhlhRBXAt6cTdXZ_CrwkLRER37DIYmUduraSbrOmam7OeOmnTAtXguDmjJWh-Ow7-UzEs3iogtd6tP2xVxJY6KUj_6uy9OaqJDMe3PXUlqg-nSiaA9jdWfuWZiD6IIM_feKBz8SDPn8gE");
        System.out.println(s1);*/
       /*     
        String s1 = httpPostJson("http://xubanbm.staff.xdf.cn/api/DeleteStudentPackageForApi/ApiBatchDelete","[{\"Id\": null,\"nSchoolId\":25,\"sWindowPeriodId\": \"ff841eda-76d7-4f57-a17d-b51fdbb06dff\",\"sStageId\":\"6645d030-9857-40bc-b29f-507fdffd7706\",\"sStageName\": \"尖子班限制\",\"sStudentCode\": \"HF100156\",\"sStudentName\": \"\",\"sMobile\": \"\",\"sContinuedClassCode\": \"CE1823055\",\"sContinuedClassName\": \"\",\"dtCreateDate\": \"\",\"sCreateUserId\":\"\",\"dtModifyDate\":null,\"sModifyUserId\":null,\"bIsDelete\":false,\"dtDeleteDate\":null,\"sDeleteUserId\":null,\"ObjectState\":0}]","wAAt3eOan0WLCfdeIb4mIw_vlSyTpqt_lVzA6kFNgn3jvDyi1YVCsJXPDLKCl4IcTpnN92admCvn1RLamSgKqIsh8LlnNN01iiMOPyYxf-SwXXC4czbauyT6TkRF_okjt6HD4ta9X4ySCDkTEzVfiNh9gW062xd7xxAn9r7OvWk7p8in382pRISsAxQwvKIhlhRBXAt6cTdXZ_CrwkLRER37DIYmUduraSbrOmam7OeOmnTAtXguDmjJWh-Ow7-UzEs3iogtd6tP2xVxJY6KUj_6uy9OaqJDMe3PXUlqg-nSiaA9jdWfuWZiD6IIM_feKBz8SDPn8gE");
        System.out.println(s1);*/
    }
}