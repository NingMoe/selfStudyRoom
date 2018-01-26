package com.human.utils.mailUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.human.utils.ReduceHtmlTextUtil;
/**
 * 关键词查找文本工具类
 * @author liuwei63
 *
 */
public class SearchFiledByKeyWordsUtil {

    
    /*
     * 符号数组
     */
    public static final String[] SYMBOL=new String[]{"/","|","(",")","&","*","#","{","}","[","]","<",">","?",";",",",":","-","~","%","、","【","】","：","；","？","—","——"};

    
    /**
     * 获取关键词组
     * @return
     */
    public static List<String> getKeyWords(){        
        List<String> list=new ArrayList<String>(100);
        list.add("姓名");list.add("性别");list.add("生日");list.add("现居住地");list.add("籍贯");list.add("住址");
        list.add("户口");list.add("联系方式");list.add("手机");list.add("邮箱");list.add("工作经验");list.add("工作经历");
        list.add("求职意向");list.add("求职性质");list.add("月薪要求");list.add("行业要求");list.add("所属行业");list.add("职能意向");
        list.add("工作地点");list.add("求职状态");list.add("目前状况");list.add("到岗时间");list.add("开始时间");list.add("结束时间");
        list.add("职位类型");list.add("公司名称");list.add("平均月薪");list.add("薪水");list.add("公司性质");list.add("所在部门");
        list.add("所属部门");list.add("公司类型");list.add("工作描述");list.add("项目经验");list.add("项目经历");list.add("项目描述");
        list.add("责任描述");list.add("教育经历");list.add("教育背景");list.add("学校名称");list.add("专业");list.add("入学时间");
        list.add("毕业时间");list.add("学历");list.add("个人技能");list.add("证书");list.add("语言能力");list.add("在校情况");
        list.add("荣誉奖励");list.add("校内职务");list.add("职务描述");list.add("培训经历");list.add("培训机构");list.add("社会实践");
        list.add("工作地区");list.add("从事职业");list.add("期望月薪");list.add("从事行业");list.add("在校学习情况");list.add("在校实践经验");
        list.add("公司规模");list.add("获得荣誉");list.add("期望薪资");list.add("工作类型");list.add("自我评价");
        list.add("简历标签");
        return list;
    }
    
    /**
     * 过滤掉文本的结尾符号
     * @param keyValue 关键词的值
     * @return
     */
    public static String removeLastSymbol(String keyValue){
        String returnValue="";
        if(StringUtils.isEmpty(keyValue)){
            return returnValue;
        }
        for(String suffix:SYMBOL){
            if(keyValue.endsWith(suffix)){
                keyValue=keyValue.substring(0, keyValue.length()-1);
            }            
        }
        returnValue=keyValue;
        return returnValue;
    }
    
    /**
     * 查询关键词的值
     * @param inputString 输入的简历文本
     * @param keyWord  待查询的关键词
     * @param keyWords 关键词组
     * @return
     */
    public static String searchFiledByKeyWords(String inputString,String keyWord,List<String>keyWords){
        String keyWordValue="";
        if(CollectionUtils.isEmpty(keyWords)|| StringUtils.isEmpty(inputString)){
            return keyWordValue;
        }
        int start=inputString.indexOf(keyWord);
        if(start!=-1){
            inputString=inputString.substring(start+keyWord.length());
            for(String kw:keyWords){
                start=inputString.indexOf(kw);
                if(start!=-1){
                    inputString=inputString.substring(0,start);
                }
            }
            keyWordValue=inputString.replaceAll("[:：]" , "").trim();            
        }
        //过滤掉文本的结尾符号        
        return removeLastSymbol(keyWordValue);
    }
    
    /**
     * 某段文本过滤掉所有关键词
     * @param inputString 输入的简历文本
     * @param keyWords 关键词组
     * @return
     */
    public static String searchTextByKeyWords(String inputString,List<String>keyWords){
        String keyWordValue="";
        if(CollectionUtils.isEmpty(keyWords)|| StringUtils.isEmpty(inputString)){
            return keyWordValue;
        }
        for(String kw:keyWords){
            int start=inputString.indexOf(kw);
            if(start!=-1){
                inputString=inputString.substring(0,start);
            }
            if(StringUtils.isEmpty(inputString)){
                break;
            }
        }
        keyWordValue=inputString.replaceAll("[:：]" , "").trim(); 
        //过滤掉文本的结尾符号        
        return removeLastSymbol(keyWordValue);
    }
    
    /**
     * 查找某文本中第一个匹配到关键词的下标位置
     * @param inputString
     * @param keyWords
     * @return
     */
    public static int getIndexOfFirstKey(String inputString,List<String>keyWords){
        int index=-1;
        if(CollectionUtils.isEmpty(keyWords)|| StringUtils.isEmpty(inputString)){
            return index;
        }
        for(String kw:keyWords){
            int start=inputString.indexOf(kw);
            if(start!=-1){
                index=start;
                break;
            }
        }
        return index; 
    }
    
    /**
     * 查找某文本中某关键词的下标位置
     * @param inputString
     * @param keyWord
     * @return
     */
    public static int serachIndexOfKeyWord(String inputString,String keyWord){
        int index=-1;
        if(StringUtils.isEmpty(keyWord)|| StringUtils.isEmpty(inputString)){
            return index;
        }      
        index=inputString.indexOf(keyWord);           
        return index; 
    }
    
    /**
     * 过滤掉文本开头及结尾的*特殊标记符号
     * @param keyValue 关键词的值
     * @return
     */
    public static String removeSpecialSymbol(String keyValue){
        String returnValue="";
        if(StringUtils.isEmpty(keyValue)){
            return returnValue;
        }
        //去掉开头的*特殊符号
        while(true){
            if(keyValue.startsWith("*")){
                keyValue=keyValue.substring(1);
            }else{
                break;
            }
        }
        //去掉末尾的*特殊符号
        while(true){
            if(keyValue.endsWith("*")){
                keyValue=keyValue.substring(0,keyValue.length()-1);
            }else{
                break;
            }
        }
        returnValue=keyValue;
        return returnValue;
    }
    
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        try{
            String docPath = "E:/temp/attachment/123.html";//
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
            String inputString = ReduceHtmlTextUtil.removeHtmlTag(doc.toString());
            String keyWord="培训经历";
            List<String>keyWords=getKeyWords();
            String keyValue=searchFiledByKeyWords(inputString,keyWord,keyWords);
            System.out.println(keyWord+":"+keyValue);
        }catch(Exception e){
            
        }
    }

}
