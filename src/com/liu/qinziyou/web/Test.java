package com.liu.qinziyou.web;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;

import com.liu.qinziyou.common.util.DateUtil;

public class Test {
	
	public static void exchang(Point arg1,Point arg2){
		arg1.x=100;
		arg1.y=100;
		Point temp = arg1;
		arg1=arg2;
		arg2=temp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		System.out.println(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		System.out.println(calendar.getTime());
		String sDate = DateUtil.format(calendar.getTime(), "yyyy-MM-dd");
		System.out.println(sDate);
	}

}
