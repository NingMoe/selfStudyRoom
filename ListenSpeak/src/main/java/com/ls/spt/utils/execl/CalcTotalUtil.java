package com.ls.spt.utils.execl;




/**
 * @Descritpion 计算String数值和的工具类
 * @author bgfang
 * @create 2015年6月7日 10:30:48
 */
public class CalcTotalUtil {
    /**
     * 计算两个数的和
     * 
     * @param arg0
     *            第一个数
     * @param arg1
     *            第二个数
     * @return 和
     */
    public static String calcTotal(String arg0, String arg1) {
        double total = 0;
        double a = 0;
        double b = 0;
        try {
            if (StringUtil.isEmpty(arg0)) {
                a = 0.00;
            } else {
                a = Double.parseDouble(arg0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (StringUtil.isEmpty(arg1)) {
                b = 0.00;
            } else {
                b = Double.parseDouble(arg1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        total = a + b;

        return String.format("%.2f", total);
    }

    /**
     * 计算四个数的和
     * 
     * @param arg0
     *            第一个数
     * @param arg1
     *            第二个数
     * @param arg2
     *            第三个数
     * @param arg3
     *            第四个数
     * @return 和
     */
    public static String calcTotal(String arg0, String arg1, String arg2,
            String arg3) {
        return calcTotal(calcTotal(arg0, arg1), calcTotal(arg2, arg3));
    }

    public static boolean isCanParseDouble(Object obj) {
        String str = obj.toString();
        if(str.endsWith("d") || str.endsWith("D") || str.endsWith("f") || str.endsWith("F")){
            return false;
        }
        double tempDouble = 0d;
        try {
            if(str.length() >= 2){
                String f1 = str.substring(0,1);
                String f2 = str.substring(1,2);
                if(f1.equals("0")){
                    if(!f2.equals(".")){
                        return false;
                    }
                }
            }
            tempDouble = Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
