package com.human.utils.mailUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.human.basic.entity.College;

public class ReadCollegeUtil{

	public static void main(String[] args) {
		String docPath = "E:/temp/attachment/iszwaksi.html";//
		dealHtmlResumeByResource(docPath);

	}

	/**
	 * 解析大学
	 * @param docPath  html格式的简历文件
	 */
	public static List<College>dealHtmlResumeByResource(String docPath){	
	    docPath = "E:/temp/attachment/iszwaksi.html";//
		File input = new File(docPath);
		List<College>list=new ArrayList<College>();
		try {
			Document doc = Jsoup.parse(input, "utf-8");
			Elements tables=doc.select("body").eq(0).first().children().select("table");//
			int tagTableNums=tables.size();			
			for(int i=0;i<tagTableNums;i++){
				Element tagTable = tables.get(i);//
				List<College>list1=analysisCollegeInformation(tagTable);
				list.addAll(list1);
			}				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	/**
	 * 简析简历中大学模块
	 */
	public  static List<College> analysisCollegeInformation(Element tagTable){
		Elements trs = tagTable.select("tbody").eq(0).first().children();//	
		int size=trs.size();
		College college=null;
		List<College>list=new ArrayList<College>();
		for(int i=1;i<size;i++){
		    college=new College();
		    String name="";
	        Integer is211=0;
	        Integer is985=0;
	        String  provinceName="";
	        Integer isEdudirect=0;
	        Integer type=null;
	        Integer educationalLevel=0;
	        Integer schoolProperty=null;
            Element tr = trs.get(i);//
            try{
                Elements elts=tr.select("td:eq(0)").first().select("a");
                if(elts!=null&& elts.size()>0){
                    name=elts.get(0).text().trim();
                }else{
                    name=tr.select("td:eq(0)").first().text().trim();
                }
                Elements spans=tr.select("td:eq(0)").first().select("span");
                if(spans!=null && spans.size()>0 ){
                    for(Element span:spans){
                        String key=span.text().trim();
                        if("985".equals(key)){
                            is985=1;   
                        }else if("211".equals(key)){
                            is211=1;
                        }   
                    }
                }            
                provinceName=tr.select("td:eq(1)").first().text().trim();
                String text1=tr.select("td:eq(2)").first().text().trim();
                if("教育部".equals(text1)){
                    isEdudirect=1;
                }
                text1=tr.select("td:eq(3)").first().text().trim();
                if(text1.indexOf("本科")!=-1){
                    educationalLevel=0;
                }else if("高职(专科)".equals(text1)){
                    educationalLevel=1;
                }
                text1=tr.select("td:eq(4)").first().text().trim();            
                type=getType(text1);
                text1=tr.select("td:eq(5)").first().text().trim();
                schoolProperty=getschoolProperty(text1);
                college.setEducationalLevel(educationalLevel);
                college.setIs211(is211);
                college.setIs985(is985);
                college.setName(name);
                college.setProvinceName(provinceName);
                college.setIsEdudirect(isEdudirect);
                college.setType(type);
                college.setSchoolProperty(schoolProperty);
                college.setSourceType(0);
                list.add(college); 
            }catch(Exception e){
                System.out.println("出错行数===="+i);
                System.out.println("出错表===="+tagTable);
            }  
        }
		return list;
	}
		
   
	/**
	 * 获取办学类型
	 * @param typeName
	 * @return
	 */
	public static Integer getType(String typeName){
	    Map<String,Integer>map=new HashMap<String,Integer>(16);
	    map.put("大学",1);
	    map.put("学院",2);
	    map.put("高等专科学校",3);
	    map.put("高等职业技术学校",4);
	    map.put("高等学校分校",5);
	    map.put("独立学院",6);
	    map.put("短期职业大学",7);
	    map.put("成人高等学校",8);
	    map.put("管理干部学院",9);
	    map.put("教育学院",10);
	    map.put("其它",11);	    
	    return map.get(typeName); 
	}
	
	/**
     * 获取院校类型
     * @param typeName
     * @return
     */
    public static Integer getschoolProperty(String typeName){
        Map<String,Integer>map=new HashMap<String,Integer>(16);
        map.put("综合",1);
        map.put("工科",2);
        map.put("农业",3);
        map.put("林业",3);
        map.put("医药",4);
        map.put("师范",5);
        map.put("语言",6);
        map.put("财经",7);
        map.put("政法",8);
        map.put("体育",9);
        map.put("艺术",10);
        map.put("民族",11); 
        map.put("军事",12);
        map.put("其它",13);
        return map.get(typeName); 
    }
	

}
