package com.human.dingding.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ttest {

    public static void main(String[] args) {
        String s="13A";
        String regEx="[^0-9]";   
        Pattern   p   =   Pattern.compile(regEx);      
        Matcher   m   =   p.matcher(s);      
        System.out.println(   m.replaceAll("").trim()); 

    }

}
