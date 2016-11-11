package com.liu.qinziyou.common;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.liu.qinziyou.common.util.NumberFmt;

public class Test {
	
	 public static String getURLSource(URL url) throws Exception    {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        conn=null;
        return htmlSource;
	}
	    
	    /**
	     * 把二进制流转化为byte字节数组
	     * @param instream
	     * @return byte[]
	     * @throws Exception
	     */
	    public static byte[] readInputStream(InputStream instream) throws Exception {
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        byte[]  buffer = new byte[1204];
	        int len = 0;
	        while ((len = instream.read(buffer)) != -1){
	            outStream.write(buffer,0,len);
	        }
	        instream.close();
	        return outStream.toByteArray();         
	    }
    public static String dealStr(String s){
    	int index = s.lastIndexOf(".");//寻找小数点的索引位置，若不是小数，则为-1
    	if(index > -1) {
    		int len = s.substring(index + 1).length();//取得小数点后的数值，不包括小数点
    		s += len == 1 ? "0" : "";
    	}
    	return s;
    }
	public static void main(String[] args) throws Exception {
		List<String> customerNoList=new ArrayList<String>();
		customerNoList.add("SZGS1235689");
		customerNoList.add("SZGS1235670");
		System.out.println(customerNoList.toString().replace("[", "").replace("]", ""));
//		for(int i=0;i<10;i++){
//		   if (i==5){
//			   continue;
//		   }
//		   System.out.println(i);
//		}
//		Double avgQty = 100.0 / 3;
//		avgQty = NumberFmt.round(avgQty, 0,5);
//		Double yus=100.0 % 2;
//		System.out.println(avgQty);
//		System.out.println(yus);
//		String gsinno="GSIN110512-06";
//		String year=gsinno.substring(4,6);
//		System.out.println(year);
//		String month=gsinno.substring(6,8);
//		System.out.println(month);
//		String day=gsinno.substring(8,10);
//		System.out.println(day);
//		String dte="20"+gsinno.substring(4,6)+"-"+gsinno.substring(6,8)+"-"+gsinno.substring(8,10);
//		System.out.println(DateUtil.strToTimestamp(dte, null));
		//Double avgQty=0.0;
//		 Map<Integer,Integer> eodIdsList =  new HashMap<Integer,Integer>();
//		 eodIdsList.put(1, 1);
//		 eodIdsList.put(2, 1);
//		 eodIdsList.put(3, 1);
//		 eodIdsList.put(4, 2);
//		 eodIdsList.put(5, 2);
//		 eodIdsList.put(6, 3);
//		 Integer [] ids =eodIdsList.values().toArray(new Integer[0]);
//		 System.out.println("");
		
//		List<String> slnos=new ArrayList<String>();
//		slnos.add("TP");
//		slnos.add("TDD");
//		slnos.add("TDD123");
//		System.out.println(slnos.toString().replace("[", "").replace("]",""));
//		String strs=dealStr("0");
//		System.out.println(strs);
//		String str=NumberFmt.fmt("23lsd", 0, false);
//		System.out.println(str);
//		 String s = StringUtil.increaseOne("REC14051405","REC",4);//REC14020002
//		 String s = StringUtil.increaseOne("REC14051405","REC",4);//REC14020002
		 
		// System.out.println(s);
//		String a="http://srh.bankofchina.com/search/whpj/search.jsp?erectDate=2014-05-19&nothing=2014-05-19&pjname=1316&page=1";
//		URL url = new URL(a); 
//	    String urlsource = getURLSource(url);
//	    System.out.println(urlsource);
//		double a1=8000;
//		double a2=3;
//		a1=a1/a2;
////		NumberFmt.divide(a1,a2,0)
////		NumberFmt.round(a1/a2, 0);
//		Double D1=new Double(a1); int i1=D1.intValue();		
//		System.out.println(i1);
//		String taxid="8507600090"; 
//		taxid=taxid.substring(0, 8);
//		System.out.println(taxid);
//		Integer a=1000;
//		Integer b=1000;
//		String c="abcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
//		String d="abcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
//		if (a==b){
//			System.out.println("a==b");
//		}else{
//			System.out.println("a!=b");
//		}
//		if (a.equals(b)){
//			System.out.println("a equals b");
//		}else{
//			System.out.println("a not equals b");
//		}
//		if (c==d){
//			System.out.println("c==d");
//		}else{
//			System.out.println("c!=d");
//		}
//		if (c.equals(d)){
//			System.out.println("c equals d");
//		}else{
//			System.out.println("c not equals d");
//		}
		
//		String s1="2013.11.08 09:12:12";
//		String s2="2013-11-08 09:12:11";
//		String currDate="2013.11.08 09:12:12";
//		currDate=currDate.replaceAll("\\.", "-");
//		System.err.println(currDate);
//		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		java.util.Calendar c1=java.util.Calendar.getInstance();
//		java.util.Calendar c2=java.util.Calendar.getInstance();
//		try
//		{
//		c1.setTime(df.parse(s1));
//		c2.setTime(df.parse(s2));
//		}catch(java.text.ParseException e){
//		System.err.println("格式不正确");
//		}
//		int result=c1.compareTo(c2);
//		if(result==0)
//		System.out.println("c1相等c2");
//		else if(result<0)
//		System.out.println("c1小于c2");
//		else
//		System.out.println("c1大于c2");
//		
//		Double f=NumberFmt.round(100.6592555, 2);
//		System.out.println(f);
//		Long amt=1000L;
//		System.out.println(amt*6.1254);
//		System.out.print(StringUtil.rPad("85369019".substring(8),2, "0"));
		
	}
}


