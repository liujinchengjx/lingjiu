package com.liu.qinziyou.common.util;

public class KeyFactory {
	private final static String KEY_FOR_USER = "!$%^@abcd#$#@user";
	private final static String KEY_FOR_VALIDATIONCODE = "!$%^@abcd#$#@validationcode";

	public static String createKeyForUser(Integer userId,Integer companyId) {
		return createKeyForUser(userId,companyId, System.currentTimeMillis());
	}

	public static String createKeyForUser(Integer userId, Integer companyId,Long time) {
		//String keyDate = Constants.simpleDateFormat1.format(new Timestamp(time));
		String keyDate = "";
		int key = (companyId+""+userId + "" + keyDate + "" + KEY_FOR_USER).hashCode();
		return MD5.getInstance().getMD5(key + "");
	}

	public static String createValidationcode(String code) {
		int key = (code + KEY_FOR_VALIDATIONCODE).hashCode();
		return MD5.getInstance().getMD5(key + "");
	}
}
