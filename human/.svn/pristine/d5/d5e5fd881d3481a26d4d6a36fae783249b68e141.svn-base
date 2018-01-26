package com.human.continuedClass.utils;

import org.apache.commons.lang3.StringUtils;

public class ValidateUtil {
    
    /**
     * 判断某个字符串是否是整数字符串，若是数字字符串返回true，若不是则返回false
     * @param testString
     * @return
     */
    public static boolean isNumberString(String testString) {
        String numAllString = "0123456789";
        if (StringUtils.isEmpty(testString)){
            return false;
        }else{
            for(int i = 0; i < testString.length(); i++) {
                String charInString = testString.substring(i, i + 1);
                if (!numAllString.contains(charInString)){
                    return false;
                }                    
            } 
        }
        return true;
    }

    /**
     * 判断某个字符串是否是float字符串，若是返回true，若不是则返回false
     * @param testString
     * @return
     */
    public static boolean isFloathString(String testString){
       if(!testString.contains(".")){
           return isNumberString(testString);
       }else{
        String[] floatStringPartArray=testString.split("\\.");
        if(floatStringPartArray.length==2){
         if(isNumberString(floatStringPartArray[0])&&isNumberString(floatStringPartArray[1])){
             return true;
         }else{
             return false;
         }  
        }else{
            return false; 
        }              
       }      
    }       
}
