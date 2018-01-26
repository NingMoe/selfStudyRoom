package com.human.utils;

import java.io.File;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 过滤HTML标签工具类
 * @author liuwei63
 *
 */
public class ReduceHtmlTextUtil {
    
   /**
    * 删除Html标签
    * @param inputString
    * @return
    */
    public static String removeHtmlTag(String inputString) {
        if (inputString == null)
            return null;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        java.util.regex.Pattern p_special;
        java.util.regex.Matcher m_special;
        try {
            // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML标签的正则表达式
            String regEx_html = "<[^>]+>";
            // 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            String regEx_special = "\\&[a-zA-Z]{1,10};";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("*"); // 过滤html标签
            p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
            m_special = p_special.matcher(htmlStr);
            htmlStr = m_special.replaceAll(""); // 过滤特殊标签
            textStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }
    
   /**
    * 测试用的main函数
    * @param args
    */
    public static void main(String[] args) {
        try {
            String docPath = "E:/temp/attachment/345.html";//
            File file=new File(docPath);
            Document doc = Jsoup.parse(file, "utf-8");
            /*
             * 获取HTML的编码格式
             */
            String content=doc.select("head>meta").eq(0).first().attr("content");
            String charset=content.split(";")[1].trim().split("=")[1];
            if(!"utf-8".equalsIgnoreCase(charset)){
                doc = Jsoup.parse(file, charset); 
            }           
            String ssss = removeHtmlTag(doc.toString());
            System.out.println(ssss);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
