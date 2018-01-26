package com.human.nologin.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/free/htmlUtils/")
public class HtmlUtilController {

    @RequestMapping("/getTime")
    @ResponseBody
    public void test(HttpServletRequest request, HttpServletResponse response)throws Exception{
        response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Access-Control-Allow-Origin", "*");//添加跨域访问
        Map<String, Object> result = new HashMap<String, Object>();
        String jsonpCallback = request.getParameter("jsonpCallback");//客户端请求参数
        try {
            result.put("code", "0");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(new Date());
            result.put("time",dateString);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "1");
            result.put("time","");
        }finally {
            PrintWriter out = response.getWriter();
            out.println(jsonpCallback+"("+  JSONObject.toJSON(result).toString()+")");//返回jsonp格式数据  
            out.flush();
            out.close();
        }
    }
}
