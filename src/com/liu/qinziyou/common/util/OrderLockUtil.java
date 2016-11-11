package com.liu.qinziyou.common.util;

import java.util.HashMap;
import java.util.Map;

public class OrderLockUtil {
	OrderLockUtil orderLockUtil = new OrderLockUtil();
	static Map<String,String>  customOrderMap = new HashMap<String,String>();
	public static String lockCustomerOrder(String orderNo,String userName){
		String flag = "1";
		if(customOrderMap.containsKey(orderNo)){
			String um= customOrderMap.get(orderNo);
			if(!um.equals(userName)){
				flag = um;
			}
		}else{
			customOrderMap.put(orderNo, userName);
		}
		return flag;
	}
	
    public static void unLockCustomerOrder(String orderNo,String userName){
    	if(customOrderMap.containsKey(orderNo)){
			String um= customOrderMap.get(orderNo);
			if(um.equals(userName)){
				customOrderMap.remove(orderNo);
			}
		}
	}
}
