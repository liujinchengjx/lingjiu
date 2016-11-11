package com.liu.qinziyou.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;

/**
 * 公共帮助类
 * 
 * @author minge
 * 
 */
public final class CommonUtil {

	
	/**
	 * 浮点型判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 整型判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证是否数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean verifyNumber(String number) {
		if (!verifyNull(number)) {
			final String numberRegexp = "^([0-9]+)$";
			try {
				final Pattern pattern = Pattern.compile(numberRegexp);
				if (pattern.matcher(number.trim()).find()) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 验证是否为空
	 * 
	 * @param info
	 * @return
	 */
	public static boolean verifyNull(String info) {
		boolean result = false;
		if (info == null) {
			result = true;
		} else if (info.trim().length() <= 0) {
			result = true;
		}
		return result;
	}

	/**
	 * 验证EMAIL
	 * 
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email) {
		if (email == null || email.equals("")) {
			return false;
		}
		email = email.toLowerCase().trim();
		String emailRegexp = "^[a-zA-Z0-9._%+-]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		try {
			Pattern patternemail = Pattern.compile(emailRegexp);
			if (patternemail.matcher(email).find()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验证登录名称
	 * 
	 * @param loginname
	 * @return
	 */
	public static boolean verifyLoginName(String loginname) {
		if (!verifyNull(loginname)) {
			final String loginnameRegexp = "^([[^\\x00-\\xff]*a-zA-Z0-9\\s_]+)$";
			try {
				final Pattern patternname = Pattern.compile(loginnameRegexp);
				if (patternname.matcher(loginname).find())
					return true;
			} catch (Exception e) {
				;
			}
		}
		return false;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean verifyMobile(String mobile) {
		if (!verifyNull(mobile)) {
			final String mobileRegexp = "^13[0-9]{9}|15[012356789]\\d{8}|18\\d{9}$";
			try {
				final Pattern pattern = Pattern.compile(mobileRegexp);
				if (pattern.matcher(mobile).find())
					return true;
			} catch (Exception e) {
				;
			}
		}
		return false;
	}

	public static String preparedForValueSet(Object value) {
		return value == null ? "" : value + "";
	}

	/**
	 * 获取文件后缀明，包含"."号。 如果没有后缀名，则返回空字符串
	 * 
	 * @param filename
	 * @return liujc 2012-3-21
	 */
	public static String getExtensionName(String filename) {
		String ext = "";
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot >= 0) && (dot < (filename.length() - 1))) {
				ext = filename.substring(dot);
			}
		}
		return ext;
	}


	public static String getEscapeForSql(String str) {
		if (str.contains("%")) {
			str = str.replace("%", "\\%");
		}
		if (str.contains("_")) {
			str = str.replace("_", "\\_");
		}
		return str;
	}


	

	private static int baseNo = 1000;
	public static String getInviteNo() {
		Lock lock = new ReentrantLock();
		String chat = "";
		try {
			lock.lock();
			Random random = new Random();
			char[] charArr = { 'A', 'B', 'C', 'E', 'F', 'G', 'H', 'J', 'K',
					'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'2', '3', '4', '5', '6', '7', '8', '9' };
			String rand = String.valueOf(charArr[random.nextInt(29)])
					+ String.valueOf(charArr[random.nextInt(29)])
					+ String.valueOf(charArr[random.nextInt(29)])
					+ String.valueOf(charArr[random.nextInt(29)]);
			chat += rand;
			baseNo = baseNo + 1;
			if (baseNo == 9999) {
				baseNo = 1000;
			}
			chat = chat + baseNo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return chat;
	}

	private static int batchNo = 0;

	/**
	 * 生成优惠券号
	 */
	public static List<String> getCouponsNoList(Integer num) {
		List<String> listNo = new ArrayList<String>();
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			Random random = new Random();
			char[] charArr = { 'A', 'B', 'C', 'E', 'F', 'G', 'H', 'J', 'K',
					'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'2', '3', '4', '5', '6', '7', '8', '9' };
			for (int i = 0; i < num; i++) {
				String chat = "";
				String rand = String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)])
						+ String.valueOf(charArr[random.nextInt(29)]);
				chat += rand;
				batchNo = batchNo + 1;
				if (batchNo == 10) {
					batchNo = 1;
				}
				chat = chat + batchNo;
				listNo.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return listNo;
	}

	

	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getInviteNo());
		}
	}

}
