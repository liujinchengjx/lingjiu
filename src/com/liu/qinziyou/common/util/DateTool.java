package com.liu.qinziyou.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {

	/**
	 * ȡ��ָ Symbol Meaning Presentation Example ------ ------- ------------ ------- G era designator (Text) AD y
	 * year (Number) 1996 M month in year (Text & Number) July & 07 d day in month (Number) 10 h hour in am/pm (1~12)
	 * (Number) 12 H hour in day (0~23) (Number) 0 m minute in hour (Number) 30 s second in minute (Number) 55 S millisecond
	 * (Number) 978 E day in week (Text) Tuesday D day in year (Number) 189 F day of week in month (Number) 2 (2nd Wed in
	 * July) w week in year (Number) 27 W week in month (Number) 2 a am/pm marker (Text) PM k hour in day (1~24) (Number) 24
	 * K hour in am/pm (0~11) (Number) 0 z time zone (Text) Pacific Standard Time ' escape for text (Delimiter) '' single
	 * quote (Literal) ' ��:yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param format
	 *            String
	 * @return String
	 */
	public static String getCurDateTime(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date dateCurrentTime = new Date();
		String sCurrentTime = formatter.format(dateCurrentTime);
		return sCurrentTime;
	}

	/**
	 * ����תΪָ���ĸ�ʽ�ַ�
	 * 
	 * @param inputDateTime
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String dateToString(Date inputDateTime, String format) {
		String outDateTime = "0000-00-00 00:00:00";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			outDateTime = formatter.format(inputDateTime);
		} catch (Exception ex) {
		}
		return outDateTime;
	}

	/**
	 * @param s
	 *            String
	 * @return Date
	 */
	public static Date StringToDate(String s) {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition parseposition = new ParsePosition(0);
			date = simpledateformat.parse(s, parseposition);
		} catch (Exception ex) {
		}
		return date;
	}

	public static Date DateStringToDate(String s) {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition parseposition = new ParsePosition(0);
			date = simpledateformat.parse(s, parseposition);
		} catch (Exception ex) {
		}
		return date;
	}

	public static Date TimeStringToDate(String s) {
		Date date = new Date();
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat("HH:mm:ss");
			ParsePosition parseposition = new ParsePosition(0);
			date = simpledateformat.parse(s, parseposition);
		} catch (Exception ex) {
		}
		return date;
	}

	/**
	 */
	public static int getSeason() {
		int ret = 0;
		try {
			int month = Integer.parseInt(getCurDateTime("MM"));
			int day = Integer.parseInt(getCurDateTime("dd"));
			if (month > 1 && month <= 3)
				ret = 4;
			if (month == 3 && day > 19)
				ret = 1;
			if (month > 3 && month <= 6)
				ret = 1;
			if (month == 6 && day > 20)
				ret = 2;
			if (month > 6 && month <= 9)
				ret = 2;
			if (month == 9 && day > 21)
				ret = 3;
			if (month > 9 && month <= 12)
				ret = 3;
			if (month == 12 && day > 20)
				ret = 4;
		} catch (Exception ex) {
		}
		return ret;
	}

	public static String getWeek(int i) {
		String ret = "";
		if (i <= 0 || i > 7) {
			return ret;
		}
		String[] s = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		ret = s[i - 1];
		return ret;
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param date
	 *            输入的日期
	 * @param day
	 *            向后回溯的天数
	 * @param isStartFromZero
	 *            时间是否从凌晨0:00:00算起
	 * @return
	 */
	public static Date getDateDaysBefore(Date date, int day, boolean isStartFromZero) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
		if (isStartFromZero) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		return calendar.getTime();
	}

	/**
	 * 得到几个月前的时间
	 * 
	 * @param date
	 *            输入的日期
	 * @param month
	 *            向后回溯的月数
	 * @param isStartFromZero
	 *            时间是否从凌晨0:00:00算起
	 * @return
	 */
	public static Date getDateMonthsBefore(Date date, int month, boolean isStartFromZero) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
		if (isStartFromZero) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		return calendar.getTime();
	}

	/**
	 * 得到给定时间戳对应日期的最后一秒，如给定2013-03-29 12:09:15 返回2013-03-29 23:59:59
	 * 
	 * @param timestamp
	 *            给定的时间戳
	 * @return
	 */
	public static Timestamp getLastSecond(Timestamp timestamp) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		String date = dateformat.format(timestamp);

		String dateTime = date + " 23:59:59";
		try {
			d = dateTimeFormat.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Timestamp(d.getTime());
	}

	public static void main(String[] args) {
		System.out.println(getCurDateTime("yyMMdd"));
		System.out.println(getDateDaysBefore(new Date(), 3, true));
		System.out.println(getDateMonthsBefore(new Date(), 1, true));
		System.out.println(getLastSecond(new Timestamp(System.currentTimeMillis())));
	}

}
