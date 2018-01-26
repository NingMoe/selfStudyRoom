package com.human.utils.mailUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 查找学校学历工具类
 * @author liuwei63
 *
 */
public class SerachEducationUtil {

    /*
     * 学历类型
     */
    public static final String[] EDUCATION=new String[]{"高中","中专","大专","本科","硕士","博士"};
    
    /*
     * 学校结尾的名词
     */
    public static final String[] ENDOFSCHOOL=new String[]{"学院","大学","中学","学校"};
    
    /*
     * 统招类型
     */
    public static final String[] ENTRANCETYPE=new String[]{"非统招","统招"};
    
    /**
     * 查找学历名称
     * @param inputString
     * @return
     */
    public static String serachEdu(String inputString){
        String returnValue="";
        if(StringUtils.isEmpty(inputString)){
            return returnValue;
        }
        for(String education :EDUCATION){
            int index=inputString.indexOf(education);
            if(index!=-1){
                returnValue=education;
                break;
            }
        }
        //查看是否有统招        
        if(inputString.indexOf("非统招")!=-1){
            returnValue+="(非统招)";
        }else if(inputString.indexOf("统招")!=-1){
            returnValue+="(统招)";
        } 
       return  returnValue;
    }
    
    /**
     * 查询学历的位置
     * @param inputString
     * @return
     */
    public static int serachIndexOfEduEnd(String inputString){
        int index=-1;
        if(StringUtils.isEmpty(inputString)){
            return index;
        }
        for(String endSuffix:EDUCATION){
            index=inputString.indexOf(endSuffix);
            if(index!=-1){
                break;
            }
        }
        return index;
    }
    
    
    /**
     * 查找统招类型名称
     * @param inputString
     * @return
     */
    public static String serachEduType(String inputString){
        String returnValue="";
        if(StringUtils.isEmpty(inputString)){
            return returnValue;
        }
        for(String education :ENTRANCETYPE){
            int index=inputString.indexOf(education);
            if(index!=-1){
                returnValue=education;
                break;
            }
        }
       return  returnValue;
    }
    
    /**
     * 查询统招类型的位置
     * @param inputString
     * @return
     */
    public static int serachIndexOfEduTypeEnd(String inputString){
        int index=-1;
        if(StringUtils.isEmpty(inputString)){
            return index;
        }
        for(String endSuffix:ENTRANCETYPE){
            index=inputString.indexOf(endSuffix);
            if(index!=-1){
                break;
            }
        }
        return index;
    }
       
    
    /**
     * 查询学校结尾名称的位置
     * @param inputString
     * @return
     */
    public static int serachIndexOfSchoolEnd(String inputString){
        int index=-1;
        if(StringUtils.isEmpty(inputString)){
            return index;
        }
        for(String endSuffix:ENDOFSCHOOL){
            index=inputString.indexOf(endSuffix);
            if(index!=-1){
                index+=2;
                break;
            }
        }
        return index;
    }
    
    
    
}
