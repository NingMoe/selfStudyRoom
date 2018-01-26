package com.ls.spt.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


public class TimeUtil {
    
	/**
	 * 把对象转换成字符串
	 * @param obj
	 * @return String 转换成字符串,若对象为null,则返回空字符串.
	 */
	public static String toString(Object obj) {
		if(obj == null)
			return "";
		
		return obj.toString();
	}
	
	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
     * 获取当前时间 yyyyMMddHHmmss
     * @return String
     */ 
    public static String getStandCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = outFormat.format(now);
        return s;
    }
	
	/**
	 * 获取当前日期 yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * 获取当前日期 前一天yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String getPrevDate() {
		Date date = new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1); 
		Date prevDate=calendar.getTime();	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(prevDate);
		return strDate;
	}
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * 获取编码字符集
	 * @param request
	 * @param response
	 * @return String
	 */
	public static String getCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) {
		
		if(null == request || null == response) {
			return "UTF-8";
		}
		
		String enc = request.getCharacterEncoding();
		if(null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}
		
		if(null == enc || "".equals(enc)) {
			enc = "UTF-8";
		}		
		return enc;
	}
	
	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		
		return date.getTime()/1000;
	}
		
	/**
	 * 时间转换成字符串
	 * @param date 时间
	 * @param formatType 格式化类型
	 * @return String
	 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}
	
	/**
	 * 根据订单生成时间生成订单失效时间，默认30分钟
	 */
	public static String getExpireTime(String startTime) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String expireTime="";
		try {
			Date date = outFormat.parse(startTime);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, 30); 
			Date expireDate=calendar.getTime();	
			expireTime = outFormat.format(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return expireTime;
	}
	
	/**
	 * 将字符串转为Timestamp
	 */
	public static Timestamp getTimestamp(String time) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp result=null;
		try {
			Date date = outFormat.parse(time);
			result=new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	
	/**
	 * 将字符串转为Timestamp
	 */
	public static Timestamp getTimestamp1(String time) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp result=null;
		try {
			Date date = outFormat.parse(time);
			result=new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	
	/**
     * 将字符串转为Timestamp
     */
    public static Timestamp getCurrentTimestamp() {        
        Timestamp result=null;
        try {
            Date date = new Date();
            result=new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return result;
    }
	
	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	public static String getOrderNo() {
		// ---------------生成订单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		String currTime = getCurrTime();
		String strTime = currTime;
		// 四位随机数
		String strRandom = buildRandom(4) + "";
		// 14位序列号,可以自行调整
		String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = strReq;
		// ---------------生成订单号 结束------------------------
		return out_trade_no;

	}
    
    /**
     * 将字符串转为Date
     * @param time
     * @param patten
     * @return
     */
    public static Date getDateByTime(String time,String patten) {
        SimpleDateFormat outFormat = new SimpleDateFormat(patten);
        Date date=null;
        try {
            date = outFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }       
        return date;
    }
	
    /**
     * 将Timestamp转为字符串
     * @param time
     * @param patten
     * @return
     */
    public static String getStringByTimestamp(Timestamp tt,String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(tt);
    }
	
    /**
     * 获取当前时间+monthIn个月的第一天
     * @return
     */
    public static String getIntervalMonthFirstDay(int monthIn,Date date){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if(date!=null){
            c.setTime(date);
        }
        c.add(Calendar.MONTH,monthIn);
        c.set(Calendar.DAY_OF_MONTH,1);
        return smf.format(c.getTime());
    }
    
    /**
     * 获取当前时间+monthIn个月的最后一天
     * @return
     */
    public static String getIntervalMonthLastDay(int monthIn,Date date){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if(date!=null){
            c.setTime(date);
        }
        c.add(Calendar.MONTH,monthIn);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH)); 
        return smf.format(c.getTime());
    }
    
    /**
     * 获取当前时间+weekIn周的第一天
     * @return
     */
    public static String getIntervalWeekFirstDay(int weekIn){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, weekIn*7);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return smf.format(cal.getTime());
    }
    
    /**
     * 获取当前时间+weekIn周的最后一天
     * @return
     */
    public static String getIntervalWeekLastDay(int weekIn){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        cal.add(Calendar.DATE, weekIn*7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
        return smf.format(cal.getTime());
    }
    
    /**
     * 获取当前时间+天的日期
     * @return
     */
    public static String getIntervalDay(int day,Date date,String pattern){
        if(StringUtils.isEmpty(pattern)){
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat smf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        if(date!=null){
            c.setTime(date);
        }
        c.add(Calendar.DATE,day);
        return smf.format(c.getTime());
    }
    
    /**
     * 获取当前时间 yyyyMMddHHmmss
     * @return String
     */ 
    public static String getNowTimeStr(String pattern) {
        Date now = new Date();
        if(StringUtils.isEmpty(pattern)){
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat outFormat = new SimpleDateFormat(pattern);
        return outFormat.format(now);
    }
    
    
    /**
     * 获取当前年份的第一天
     * @return
     */
    public static String getCurrYearFirstDay(){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        currCal.clear(); 
        currCal.set(Calendar.YEAR, currentYear);
        Date currYearFirst = currCal.getTime();
        return smf.format(currYearFirst);
    }
    
    /**
     * 获取当前年份的最后一天
     * @return
     */
    public static String getCurrYearLastDay(){
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        currCal.clear(); 
        currCal.set(Calendar.YEAR, currentYear);  
        currCal.roll(Calendar.DAY_OF_YEAR, -1); 
        Date currYearFirst = currCal.getTime();
        return smf.format(currYearFirst);
    }
    
    public static String getCurrentMonth(){
        Calendar now = Calendar.getInstance();  
        return  (now.get(Calendar.MONTH) + 1)+"" ;
    }
    
    public static Date getDateAddMinutes(int minutes){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, minutes);
       return nowTime.getTime();
    }
}
