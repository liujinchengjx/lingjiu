package com.liu.qinziyou.common.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void main(String[] args) throws Exception{
		String s= StringUtil.getHsCode("","4403042754-1**");
		System.out.println(s);
//		String url = "http://srh.bankofchina.com/search/whpj/search.jsp";
//		int page = 1;
//		List<Rate> listRate = new ArrayList<Rate>();
//		String shtml="";
//		while(true){
//			String html  = HttpPostUtil.getRateHtmlContent(url,"2013-11-07","1314",page++);
//			if("".equals(html) || shtml.equals(html) || (html==null)){
//				break;
//			}else{
//				List<Rate> returnRateList= JsoupUtil.analyzeRateHtml(html,"502");
//				listRate.addAll(returnRateList);
//			}
//			shtml = html;
//		}
//		Rate tempRate =null;
//		Long tempDiff=100000000000000l;
//		for(Rate rate : listRate){
//			Timestamp timestamp = DateUtil.strToTimestamp("2013-11-07 09:30:00","yyyy-MM-dd hh:mm:ss");
//			Long a = timestamp.getTime();
//			Long b = rate.getOpDate().getTime();
//			long diff = Math.abs(b-a);
//			if(diff<tempDiff){
//				tempRate = rate;
//				tempDiff = diff;
//			}
//		}
//		if(tempRate!=null){
//			System.out.println(tempRate.getCovertPrice());
//			System.out.println(tempRate.getOpDate());
//		}
//		
	}
}
