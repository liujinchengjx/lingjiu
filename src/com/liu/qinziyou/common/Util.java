package com.liu.qinziyou.common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.Constants.SpecialFlag;
import com.liu.qinziyou.common.util.DateUtil;
import com.liu.qinziyou.common.util.NumberFmt;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.entity.business.ProductTaxCode;

public class Util {
	private static long idKey = 0L;
	 /**
	  * 获取文件后缀明，包含"."号。
	  * 如果没有后缀名，则返回空字符串
	  * @param filename
	  * @return
	  * liujc
	  * 2012-3-21
	  */
	 public static String getExtensionName(String filename) {   
		String ext = "";
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >=0) && (dot < (filename.length() - 1))) {   
            	ext =  filename.substring(dot);   
            }   
        }
        return ext;
         
    } 
		public Util()
		{
		}

		public static synchronized String getIDKey()
		{
			Calendar c = Calendar.getInstance();
			long l = c.getTimeInMillis();
			if (l > idKey)
				idKey = l;
			else
				idKey++;
			String val = (new StringBuilder(String.valueOf(idKey))).toString();
			return val;
		}
//		 public static boolean isRepeatCartons(List<Integer> cartonList,String carton)throws Exception {
//		    	Integer [] cartons=StringUtil.toIntArrFrStrArr(StringUtil.split(carton,  "-"));
//		    	if (cartons==null) return false;
//		    	int len=cartons.length;
//		    	if (len>0){
//					for(int i=cartons[0];i<=cartons[len-1];i++){
//						if (cartonList.contains(i)){
//							return true;
//						}else {
//							cartonList.add(i);
//						}
//					}
//		    	}	
//		    	return false;
//		    }	
    public static void testmoth(){
    	Integer cartons=220;
		Double js=50.0D;//基数
		Double byBoxCount=5.0D; //按箱收 多少钱一箱
		Double boxRange=100.0D; //箱子范围
		Double boxRangeMaxAmt=200.0D;//在boxRange内最高收多少
		Double byBoardCount=50.0D; //按板收 多少钱一箱
		Double boardRange=25.0D; //板范围
		Double boardRangeMaxAmt=500.0D;//在boardRange内最高收多少
		Double totalAmt=0.0D,amt=js;
		Integer count=1;
		Integer range=boxRange.intValue();
		for(Integer i=1;i<=cartons;i++){
			amt=NumberFmt.sum(amt, byBoxCount);
			if(i.equals(range*count)){ //超过范围
			   System.out.println("相等："+(range*count));	
			   if(amt>boxRangeMaxAmt){
				  System.out.println("金额超过："+(amt));
				  amt=boxRangeMaxAmt;
				  totalAmt=NumberFmt.sum(totalAmt, amt);
				  amt=0.0D;
				  count++;
    			}    				   
			}
		}
		totalAmt=NumberFmt.sum(totalAmt,amt>boxRangeMaxAmt? boxRangeMaxAmt:amt);
		System.out.println(totalAmt);
		  	    		  
    }
    
    public static void testtimeamp() throws Exception{
    	Timestamp makeDate=new Timestamp(System.currentTimeMillis());
		String timestampStr=DateUtil.timestampToStr(makeDate, "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date timeNow = df.parse(timestampStr);
		Calendar contractDate=Calendar.getInstance();
		contractDate.setTime(timeNow);
		contractDate.add(Calendar.DAY_OF_MONTH,-7); //制单时间减7天
		timestampStr = df.format(contractDate.getTime());
		System.out.println(timestampStr); //合同时间
   }
   public static void testss(){
	    String tes="0.5";
	    Double t=Double.parseDouble(NumberFmt.fmt(tes, 2, true));
	    System.out.println(t);
   }
   public static String gettest(){
		 List<ProductTaxCode> ptcList=new ArrayList<ProductTaxCode>();
		 ProductTaxCode a1=new ProductTaxCode();
		 a1.setControlMa("A");
		 a1.setQuarantineMa("M");
		 ptcList.add(a1);
		 ProductTaxCode a2=new ProductTaxCode();
		 a2.setControlMa("A");
		 a2.setQuarantineMa("LM");
		 ptcList.add(a2);
		 ProductTaxCode a3=new ProductTaxCode();
		 a3.setControlMa("A");
		 a3.setQuarantineMa("L");
		 ptcList.add(a3);
		 ProductTaxCode a4=new ProductTaxCode();
		 a4.setControlMa("A");
		 ptcList.add(a4);
		 ProductTaxCode a5=new ProductTaxCode();
		 a5.setControlMa("A");
		 ptcList.add(a5);
		 ProductTaxCode a6=new ProductTaxCode();
		 a6.setControlMa("A");
		 a6.setQuarantineMa("LM");
		 ptcList.add(a6);
		 ProductTaxCode a7=new ProductTaxCode();
		 ptcList.add(a7);
		 ProductTaxCode a8=new ProductTaxCode();
		 a8.setControlMa("O");
		 ptcList.add(a8);
		 ProductTaxCode a9=new ProductTaxCode();
		 ptcList.add(a9);
		 Map<String, Integer> sepecialMap= new HashMap<String,Integer>(); 
		 boolean epflag=true;
		 if (epflag){
			 sepecialMap.put("DISINFECT", SpecialFlag.DISINFECT_TITLE.getValue());
		 }
		 for(ProductTaxCode ptc:ptcList){
			 if ((ptc.getControlMa()!=null) && (!"".equals(ptc.getControlMa())) ){
				if (ptc.getControlMa().contains("A") && ptc.getQuarantineMa()!=null &&  
						ptc.getQuarantineMa().contains("M") &&
						ptc.getQuarantineMa().contains("L") ){
					if (!sepecialMap.containsKey("AML")){
						sepecialMap.put("AML",SpecialFlag.AML_TITLE.getValue());
					}
				}else if (ptc.getControlMa().contains("A") && ptc.getQuarantineMa()!=null &&  
						ptc.getQuarantineMa().contains("M")){
					if (!sepecialMap.containsKey("AM")){
						sepecialMap.put("AM",SpecialFlag.AM_TITLE.getValue());
					}
				}else if (ptc.getControlMa().contains("A") && ptc.getQuarantineMa()!=null && 
						ptc.getQuarantineMa().contains("L")){
					if (!sepecialMap.containsKey("AL")){
						sepecialMap.put("AL",SpecialFlag.AL_TITLE.getValue());
					}
				}else if (ptc.getControlMa().contains("A")){
					if (!sepecialMap.containsKey("A")){
						sepecialMap.put("A",SpecialFlag.A_TITLE.getValue());
					}
				}
				if (ptc.getControlMa().contains("O")){
					if (!sepecialMap.containsKey("O")){
						sepecialMap.put("O",SpecialFlag.O_TITLE.getValue());
					}
				}
			 }
		 }
		 if (sepecialMap.containsKey("AML")){
			 sepecialMap.remove("AM");
			 sepecialMap.remove("AL");
			 sepecialMap.remove("A");
		 }
		 if (sepecialMap.containsKey("AM")){
			 sepecialMap.remove("AL");
			 sepecialMap.remove("A");
		 }
		 if (sepecialMap.containsKey("AL")){
			 sepecialMap.remove("A");
		 }
		 return sepecialMap.values().toString().replace("[", "").replace("]", "");
   }
   public static String delSpace(String str) {  
       
       if(str == null) {  
           return null;  
       }  
         
       // 先将半角空格删除  
       str = str.trim();  
         
       while (str.startsWith("　")) {  
             
           // 只可惜String中没有提供replaceLast(), 否则就简单点了  
           // 所以本循环完成以后，只能将字符串前端的空格删除，却不能删除后端的空格  
           // 故而本循环完成后，又将字符串翻转后再去一次空格  
           str = str.replaceFirst("　", "");   
             
           // 一定要 trim()， 不然的话，如果前端的空格是全角和半角相间的话，就搞不定了  
           str = str.trim();  
       }  
         
       // 将字符串翻转  
       str = reverse(str);  
         
       // 再去一次空格  
       while (str.startsWith("　")) {  
             
           str = str.replaceFirst("　", "");  
             
           str = str.trim();  
       }  
         
       // 最后再将字符串翻转回去  
       return str = reverse(str).trim();  
   }  
   // 自定义的字符串翻转方法。  
   // 很多第三方的包的都有实现，但是 Java API 没有实现，这里自己实现一下 ^_^  
   public static String reverse(String str){  
       char[] charsOld = str.toCharArray();  
         
       char[] charsNew = new char[charsOld.length];  
         
       int index = charsOld.length-1;  
         
       for (int i = 0; i < charsOld.length ; i++) {  
             
           charsNew[i] = charsOld[index - i];  
       }  
         
       return String.valueOf(charsNew);  
   }
   public static boolean isRepeatCartons(List<Integer> cartonList,String carton) throws Exception {
		if (carton==null || "".equals(carton.trim())){
			return false;
		}
		Integer[] cartons = StringUtil.toIntArrFrStrArr(StringUtil.split(carton, "-"));
		if (cartons == null)
		 	return false;
		Integer len = cartons.length;
		if (len > 0) {
			for (Integer i = cartons[0]; i <= cartons[len - 1]; i++) {
				if (cartonList.contains(i)) {
					return true;
				} else {
					if (cartonList.size()>0){
						Integer lastNo=cartonList.get(cartonList.size()-1);
						if ((i-lastNo)>1){
							return false;
						}
					}
					cartonList.add(i);
				}
			}
		}
		return false;
	}

	public static void main(String[] args){
		try{
			String num="GSIN1409-HK-1010";
			System.out.print(num.toString().substring(12));
//			System.out.println(Math.round(0.005*100)/100);
//			Double vl=785670.81;
//			Double v2=83288.57;
//			BigDecimal   b1   =   new   BigDecimal(Double.toString(vl));     
//            BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));
//			System.out.println( b1.add(b2).doubleValue());
//			List<Integer> cartonList =new ArrayList<Integer> ();
//			cartonList.add(210);
//			cartonList.add(211);
//			isRepeatCartons(cartonList,"213-223");
			//String ss="0";
			//Double tt=null;
//			String ss="DF1408008 					 					";
//			String ssdf=StringUtil.removeLRtrim(ss);
//			System.out.println(ssdf);
			//new java.lang.Double($V{sum_nw}.doubleValue()+Double.valueOf($F{nw}.intValue()).doubleValue())
			//Double d=new java.lang.Double($V{sum_nw}.doubleValue()+Double.valueOf( "".equals($F{nw}.replaceAll(",",""))?"0":$F{nw} ));
			//System.out.println(d);
//			System.out.println("DN140801-0029".substring(9));
//			TSWordConvert jf = new TSWordConvert();
//			String str="場館";
//			String temp=jf.conver(str, 0);
//			System.out.println(temp);
//			List<Integer> etList=new ArrayList<Integer>();
//			etList.add(10);
//			etList.add(20);
//			etList.add(30);
//			etList.add(40);
//			etList.add(50);
//			etList.add(60);
//			etList.add(6,11);
//			System.out.println("长度"+etList.size());
//			for(Integer e:etList){
//				System.out.println(e);
//			}
//			String temp="IT8888G/DX-L ";
//			temp=StringUtil.removeLRtrim(temp);
//			temp=temp.replaceAll(" ", "");  
//			for(int i=0;i<temp.length();i++){
//				if (temp.charAt(i)==160){
//					System.out.println("等于160");
//				}
//				System.out.println(+temp.charAt(i));
//			}  
//			temp=StringUtil.delSpecialInStr(temp);
			//temp=delSpace(temp);
			//System.out.println(temp);
//			double fobFareRate=Float.parseFloat(Configuration.getConfigValue("FOB_FARE_RATE"));
//			Double fare=NumberFmt.multiply(fobFareRate,14632.0);
//			
//			System.out.println("2014-05".substring(0, 7));
			
//			List<Integer> productIds=new ArrayList<Integer>();
//			productIds.add(1);
//			productIds.add(2);
//			productIds.add(3);
//			Integer [] a = productIds.toArray(new Integer[0]);
//			System.out.println(productIds.toArray(a));
//			Double temp=9.0;
//			Double rev=temp/10;
//			System.out.println(Math.ceil(rev)); 
//			testss();
//			Integer rateType=-1;
//			Double tempRateType=1.0;
//			rateType=(Integer)tempRateType.intValue();
//			System.out.println(rateType);
//			testtimeamp();
			//testmoth();
			//String a=next("");
//			System.out.print(a);
//			String carton="1";
//			List<Integer> cartonList=new ArrayList<Integer>();
//			if (isRepeatCartons(cartonList,carton)){
//				System.out.println("箱号："+carton+"重复");
//			}
//			carton="";
//			if (isRepeatCartons(cartonList,carton)){
//				System.out.println("箱号："+carton+"重复");
//			}
//			carton="03-08";
//			if (isRepeatCartons(cartonList,carton)){
//				System.out.println("箱号："+carton+"重复");
//			}
//			carton="07-10";
//			if (isRepeatCartons(cartonList,carton)){
//				System.out.println("箱号："+carton+"重复");
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 

}
