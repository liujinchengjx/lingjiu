package com.liu.qinziyou.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);

	@SuppressWarnings("unused")
	private static String defaultDatePattern = null;

	private static String timePattern = "HH:mm";

	private static String datePattern = "yyyy-MM-dd HH:mm";

	private static Calendar calendar = Calendar.getInstance();

	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	/**
	 * This method generates a string representation of a date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	public static String getDateStr(Date theTime) {
		return getDateTime(datePattern, theTime);
	}

	/**
	 * This method generates a string representation of a date's date/time in the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 根据自定格式获取当前日期:pattern:YYYYMMDD
	 * 
	 * @param pattern
	 *            时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
	 * @param
	 * @return
	 * @author
	 */
	public static String getDateTime(String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, 0);
		String dt = sdf.format(rightNow.getTime());
		return dt;
	}

	/**
	 * 根据自定格式获取日期:pattern:YYYYMMDD
	 * 
	 * @param pattern
	 *            时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
	 * @param dateNum
	 *            在当前时间上向前推或向后延的时间天数
	 * @return
	 * @author
	 */
	public static String getDateTime(String pattern, int dateNum) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar rightNow = Calendar.getInstance();
		rightNow.add(Calendar.DAY_OF_MONTH, dateNum);
		String dt = sdf.format(rightNow.getTime());
		return dt;
	}

	/**
	 * add pw 2011.7.29根据给定日期算出向前向后的天数 根据自定格式获取日期:pattern:YYYYMMDD
	 * 
	 * @param pattern
	 *            时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
	 * @param dateNum
	 *            在当前时间上向前推或向后延的时间天数
	 * @return
	 * @author
	 */
	public static String getDateTime(String pattern, String beginTime, int dateNum) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		Date startTime = DateTool.DateStringToDate(beginTime);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(startTime);
		rightNow.add(Calendar.DAY_OF_MONTH, dateNum);
		String dt = sdf.format(rightNow.getTime());
		return dt;
	}

	public static final String getYesterday(String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}

	public static boolean isToday(java.sql.Date aDate) {
		final Date now = new Date();
		String nowtime = df.format(now).toString();
		String createtime = df.format(aDate).toString();
		return nowtime.equals(createtime);
	}

	public static Date addDate(Date date, int type, int intervel) {
		if (date == null) {
			date = new Date();
		}
		calendar.setTime(date);
		calendar.add(type, intervel);
		return calendar.getTime();
	}

	/**
	 * 
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getMinuteMargin(Date beginDate, Date endDate) {
		int minute = 0;
		final int mOfMinute = 1000 * 60;
		final long divtime = (endDate.getTime() - beginDate.getTime());
		final long lminute = divtime % mOfMinute > 0 ? divtime / mOfMinute + 1 : divtime / mOfMinute;
		minute = Long.valueOf(lminute).intValue();
		return minute;
	}

	public static float dateDiff(Timestamp t1, Timestamp t2, int type) {
		float i = t1.getTime() - t2.getTime();
		float f = 0.0f;// i / (1000 * 60);
		switch (type) {
			case 1 :// hour
				f = i / (1000 * 60 * 60);
				break;
			case 2 :// min
				f = i / (1000 * 60);
				break;
			case 3 :// sec
				f = i / (1000);
				break;
			case 0 : // defaut is day
				f = i / (1000 * 60 * 60 * 24);
		}
		String temp = "#,##0.0";
		try {
			return Float.valueOf(new java.text.DecimalFormat(temp).format(f));
		} catch (Exception e) {
			;// System.out.println(e);
		}
		return f;
	}

	public static Timestamp dateAdd(Timestamp t1, Integer i, int type) {
		long interval = i * 1000 * 60 * 60 * 24l;
		Timestamp t = null;
		switch (type) {
			case 1 :// hour
				interval = 1000 * 60 * 60 * i;
				t = new Timestamp(t1.getTime() + interval);
				break;
			case 2 :// min
				interval = 1000 * 60 * i;
				t = new Timestamp(t1.getTime() + interval);
				break;
			case 3 :// sec
				interval = 1000 * i;
				t = new Timestamp(t1.getTime() + interval);
				break;
			case 0 : // defaut is day
				t = new Timestamp(t1.getTime() + interval);
				break;
			default :
				t = t1;
		}
		return t;
	}

	
	/**
	 * 获取日期 年 月 日
	 * 
	 * @param date
	 * @return
	 */
	public static int[] getYearAndMonthAndDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return new int[]{year, month, day};
	}
	/**
	 * 获取日期 年 月 以yyMM格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getYyMMddStrForDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());		
		int month = calendar.get(Calendar.MONTH) + 1;
		String mmLast = String.format("%02d", month);
		return yearLast+mmLast;
	}
	/**
	 * 获取两个日期的时间差
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return 相隔天数
	 */
	public static int getTimeDifference(Date beginDate, Date endDate) {
		long DAY = 24L * 60L * 60L * 1000L;
		long day = endDate.getTime() - beginDate.getTime();
		return Long.valueOf(day / DAY).intValue();
	}

	/*
	 * 比较参数时间和当前时间： 时间格式 2005-4-21 16:16:34 如果date1<date2 返回>=0 否则<0
	 */
	public static int compareDate(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date2).compareTo(df.parse(date1));
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * Formats a Date into a date/time string which format like:'yyyy-MM-dd HH:mm:ss'
	 * 
	 * @param date
	 *            java.util.Date object, that need to be format.
	 * 
	 * @param format
	 *            the format of the data/time string, such as: "yyyy-MM-dd HH:mm:ss.SSS"
	 */
	public static String format(java.util.Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 获取当前时间 返回 Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}
   /**
    *  比较 currDate 时间是否在 beginDate 和 endDate 之间
    * @param beginDate 开始时间 格式 yyyy-MM-dd HH:mm:ss
    * @param endDate 结束时间 格式 yyyy-MM-dd HH:mm:ss
    * @param currDate  格式 yyyy-MM-dd HH:mm:ss
    * @return 
    */
	public static boolean isBetweenTwoDate(String beginDate,String endDate,String currDate){
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();
		java.util.Calendar c3=java.util.Calendar.getInstance();
		boolean retValue=false;
		try
		{
			c1.setTime(df.parse(beginDate));
			c2.setTime(df.parse(currDate));
			c3.setTime(df.parse(endDate));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
		}
		int result=c1.compareTo(c2);
		if(result<=0){
			int result1=c2.compareTo(c3);
			if (result1<=0){
				retValue=true; 
			}
		}
		return retValue;
	}

	public static Timestamp strToTimestamp(String timestampStr, String pattern)throws ParseException {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		java.util.Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(timestampStr);
		} catch (ParseException e) {
			throw e;
		}
		return date == null ? null : new Timestamp(date.getTime());
	}
	public static String timestampToStr(Timestamp timestamp, String pattern)throws ParseException {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);//定义格式，不显示毫秒 "yyyy-MM-dd HH:mm:ss"
		String str = df.format(timestamp);
		return str;
	}
	public static String getDateStrForFmt(String timestamp, String pattern)throws ParseException {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);//定义格式，不显示毫秒 "yyyy-MM-dd HH:mm:ss"
		java.util.Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(timestamp);
		} catch (ParseException e) {
			throw e;
		}
		String str = df.format(date);
		return str;
	}
	/***
	 * //获取上个月份某一周，某一天的日期
	 * @param t
	 * @param month 如：-1表示 上个月，1 表示下个月 
	 * @param week
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getDateForPoint(Timestamp t,int monthPoint,int weekPoint,int dayPoint)throws ParseException { 
		Calendar cal = Calendar.getInstance(Locale.CHINA);
        Date date =t ; //String input = "2013-10-12"; //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");这个客户端输入 df.parse(input)
        cal.setTime(date); 
        int year = 0; 
        cal.add(Calendar.MONTH, monthPoint);
        int month = cal.get(Calendar.MONTH)+1; //
        System.out.println(month);
       //设置年月  
        if (month == 0) { 
            year = cal.get(Calendar.YEAR) - 1; 
            month = 12; 
        } else { 
            year = cal.get(Calendar.YEAR); 
        }
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_MONTH,weekPoint);
        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++){
        	if (i==dayPoint){//找到
        		System.out.println(i);
        		cal.set(Calendar.DAY_OF_WEEK, i);break;    
        	}        	
        }
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String temp=year + "-" + month+"-"+day;
        return temp;
		
	}
	/***
	 * //获取上个月份某一周，某一天的日期
	 * @param t
	 * @param month 如：-1表示 上个月，1 表示下个月 
	 * @param week
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String getDateForPoint(String inpuDate,int monthPoint,int weekPoint,int dayPoint)throws ParseException { 
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(inpuDate); 
        cal.setTime(date); 
        int year = 0; 
        cal.add(Calendar.MONTH, monthPoint);
        int month = cal.get(Calendar.MONTH)+1; //
        System.out.println(month);
       //设置年月  
        if (month == 0) { 
            year = cal.get(Calendar.YEAR) - 1; 
            month = 12; 
        } else { 
            year = cal.get(Calendar.YEAR); 
        }
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_MONTH,weekPoint);
        for(int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++){
        	if (i==dayPoint){//找到
        		System.out.println(i);
        		cal.set(Calendar.DAY_OF_WEEK, i);break;    
        	}        	
        }
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String temp=year + "-" + month+"-"+day;
        return temp;
		
	}
	public static int getMonthDay(String source){//获取月份的天数
		  //String source = "2013年12月";
		  int count=30;
		  SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
		  try {     
		    Date date = format.parse(source);  
		    Calendar calendar = new GregorianCalendar();    
		    calendar.setTime(date);   
		    count=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);   
		  }catch (Exception e) {  
		     e.printStackTrace();  
		  }
		  return count;
	}
	
	public static void main(String[] args) {
		try{
		Timestamp t=new Timestamp(System.currentTimeMillis());
		System.out.println(dateAdd(t,10,0));
//		System.out.println(getDateForPoint(t,-1,3,4));
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
