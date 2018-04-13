package cn.xdf.pay.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.StringUtils;


/**
 * 日期工具类
 * @author liuwei63
 *
 */
public class TimeUtil {
    	
	/**
	 * 获取当前时间
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */
	public static String getCurrentTime(){
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
		
	/**
	 * 获取日期 
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if(null == date ) {
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
	public static Timestamp getTimestamp2(String time) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp result = null;
		try {
			Date date = outFormat.parse(time);
			result = new Timestamp(date.getTime());
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
    
    
    /**
     * 获取当前时间前一个月的时间
     * @return
     */
    public static String getIntervalMonthDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.MONTH,-1);
        return sdf.format(c.getTime());
    }
    
    /** 
     * 计算两个日期之间相差的天数   
     * @param smdate 较小的时间  
     * @param bdate  较大的时间  
     * @return 相差天数  
     * @throws ParseException   
     * calendar 对日期进行时间操作 
     * getTimeInMillis() 获取日期的毫秒显示形式 
     */  
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{      
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis();  
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis();        
        long between_days=(time2-time1)/(1000*3600*24); 
        if(between_days==0){
            between_days=1;
        }
        return Integer.parseInt(String.valueOf(between_days));             
    } 
    
    /**
     * 时间戳，自1970年以来的秒数
     * @return
     */
	public static String getSecond() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
