package com.liu.qinziyou.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.liu.qinziyou.common.Configuration;

public class NumberFmt {
	public static final int PRICE_LENGTH = 6;
	public static final int WEIGHT_LENGTH = 3;
	public static final int AMT_LENGTH = 2;
	public static final int QTY_LENGTH = 0;
	public static final int VOLUME_LENGTH = 3;
	public static final int RATE_LENGTH = 2;
	public static final int DEFAULT_DIV_SCALE=10;

	public NumberFmt() {
	}
	public static String fmtGrowString(String param, int num, int len) {
		num++;
		String str = "";
		for (int i = String.valueOf(num).length(); i < len; i++)
			str = (new StringBuilder(String.valueOf(str))).append("0").toString();
		
		return (new StringBuilder(String.valueOf(param))).append(str).append(
				String.valueOf(num)).toString();
	}

	public static String fmt(double num, int len, boolean pre) {
		String str = String.valueOf(num);
		str = fmt(str, len, pre);
		return str;
	}

	public static String fmt(String num, int len, boolean pre) {
		if (num == null || num.trim().length() == 0)
			return "0";
		num = num.trim().replaceAll(",", "");
		String re = "0";
		try {
			String sf = "###0";
			if (pre)
				sf = "#,##0";
			if (len > 0) {
				sf = (new StringBuilder(String.valueOf(sf))).append(".")
						.toString();
				for (int i = 0; i < len; i++)
					sf = (new StringBuilder(String.valueOf(sf))).append("0")
							.toString();

			}
			num = fix2point(Double.parseDouble(num), len);
			DecimalFormat df = new DecimalFormat(sf);
			re = df.format(Double.parseDouble(num));
			if (Double.parseDouble(num.replaceAll(",", "").trim()) == 0.0D)
				re = re.replaceAll("-", "");
		} catch (NumberFormatException numberformatexception) {
		}
		if (sum(re, 0.0D) == 0.0D)
			re = "0";
		return re;
	}

	private static String fix2point(double doubleValue, int scale) {
		Double flag = null;
		StringBuffer sb = new StringBuffer("0.");
		for (int i = 0; i < scale; i++)
			sb.append("0");

		sb.append("5");
		Double temp = new Double(sb.toString());
		flag = Double.valueOf(doubleValue + temp.doubleValue());
		double pow = Math.pow(10D, scale);
		flag = Double.valueOf((new BigDecimal(flag.doubleValue() * pow))
				.setScale(2, 4).doubleValue());
		flag = Double.valueOf(Math.floor(flag.doubleValue()));
		flag = Double.valueOf(flag.doubleValue() / pow);
		return flag.toString();
	}

	public static double sum(double a, double b) {
		return sum(String.valueOf(a), String.valueOf(b));
	}

	public static double sum(String a, double b) {
		return sum(String.valueOf(b), a);
	}

	public static double sum(double a, String b) {
		return sum(String.valueOf(a), b);
	}
	
	public static double sum(String a, String b) {
		double val = 0.0D;
		if (a != null) {
			a = a.trim().replaceAll(",", "");
			try {
				val += Double.parseDouble(a);
			} catch (NumberFormatException numberformatexception) {
			}
		}
		if (b != null) {
			b = b.trim().replaceAll(",", "");
			try {
				BigDecimal   b1   =   new   BigDecimal(Double.toString(val));     
	            BigDecimal   b2   =   new   BigDecimal(b);
	           return b1.add(b2).doubleValue();
			} catch (NumberFormatException numberformatexception1) {
			}
		}
		return val;
	}

	
	//-------------------------------------------------------
	/**
	* 提供精确的乘法运算。
	* @param v1
	* @param v2
	* @return 两个参数的积
	*/
	public static double multiply(Double v1, Double v2){
		if (v1==null) v1=0.0D;
		if (v2==null) v2=0.0D;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	public static double multiply(Double v1, Integer v2){
		if (v1==null) v1=0.0D;
		if (v2==null) v2=0;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	public static double multiply(Integer v1, Double v2){
		if (v1==null) v1=0;
		if (v2==null) v2=0.0D;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	//-------------------------------------------------------
	/**
	* 提供精确的乘法运算。
	* @param v1
	* @param v2
	* @return 两个参数的积
	*/
	public static double multiply(String v1, double v2){
		String v11 = v1.trim().replaceAll(",", "");
		BigDecimal b1 = new BigDecimal(v11);
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	* 提供精确的乘法运算
	* @param v1
	* @param v2
	* @return 两个参数的数学积，以字符串格式返回
	*/
	public static String multiply(String v1, String v2){
		String v11 = v1.trim().replaceAll(",", "");
		String v21 = v2.trim().replaceAll(",", "");
		BigDecimal b1 = new BigDecimal(v11);
		BigDecimal b2 = new BigDecimal(v21);
		return b1.multiply(b2).toString();
	}

	/**
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	* 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	* @param v1
	* @param v2
	* @return 两个参数的商
	*/
	public static double divide(double v1, double v2){
	    return divide(v1, v2, DEFAULT_DIV_SCALE);
	}
	//如果 hasScale 为false,则将两个数进行整除,小数位不进位，直接返回整数部分
	public static double divide(double v1, double v2,boolean hasScale){
		if (hasScale){
			return divide(v1, v2, DEFAULT_DIV_SCALE);
		}else{
			double ret=v1/v2;
			Double D1=new Double(ret); 
			int i1=D1.intValue();
			return i1;
		}
	}
	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	* @param v1
	* @param v2
	* @param scale 表示需要精确到小数点以后几位。
	* @return 两个参数的商
	*/
	public static double divide(double v1,double v2, int scale){
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	* @param v1
	* @param v2
	* @param scale 表示需要精确到小数点以后几位
	* @param round_mode 表示用户指定的舍入模式
	* @return 两个参数的商
	*/
	public static double divide(double v1,double v2,int scale, int round_mode){
		if(scale < 0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, round_mode).doubleValue();
	}

	/**
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	* 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	* @param v1
	* @param v2
	* @return 两个参数的商，以字符串格式返回
	*/
	public static String divide(String v1, String v2){
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	* @param v1
	* @param v2
	* @param scale 表示需要精确到小数点以后几位
	* @return 两个参数的商，以字符串格式返回
	*/
	public static String divide(String v1, String v2, int scale){
		return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	* @param v1
	* @param v2
	* @param scale 表示需要精确到小数点以后几位
	* @param round_mode 表示用户指定的舍入模式
	* @return 两个参数的商，以字符串格式返回
	*/
	public static String divide(String v1, String v2, int scale, int round_mode){
		if(scale < 0){	
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).toString();
	}

	/**
	* 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_UP
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @return 四舍五入后的结果
	*/
	public static double round(double v,int scale){
		return round(v, scale, BigDecimal.ROUND_HALF_UP);
	}
	/**
	* 提供精确的小数位四舍五入处理
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @param round_mode 指定的舍入模式
	* @return 四舍五入后的结果
	*/
	public static double round(double v, int scale, int round_mode){
		if(scale<0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		return b.setScale(scale, round_mode).doubleValue();
	}

	/**
	* 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @return 四舍五入后的结果，以字符串格式返回
	*/
	public static String round(String v, int scale){
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}
	/**
	* 提供精确的小数位四舍五入处理
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @param round_mode 指定的舍入模式
	* @return 四舍五入后的结果，以字符串格式返回
	*/
	public static String round(String v, int scale, int round_mode){	
		if(scale<0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, round_mode).toString();
	}
	//	******************************
	/**
     *  字符串金额转换成long类型的数值金额 并且 以分为单位
     * @param str
     * @return
	 * @throws Exception 
     */
    public static long strToLongByPoint(String str,int point){
    	if (str==null){
    		return 0;
    	}
    	str = str.trim().replaceAll(",", "");
    	if ("".equals(str)){
    	   return 0;	
    	}
    	try{
    		Double d=Double.parseDouble(str);
    		str=fmt(d*point,0,false);
	    	long l = Long.parseLong(str);
	    	return l;
    	}catch(Exception e){
    		return 0;
    	}
    }
    public static long doubleToLongByPoint(Double dt,int point){
    	if (dt<=0){
    		return 0;
    	}    	
    	try{
    		String dtStr=fmt(dt*point,0,false);
	    	long l = Long.parseLong(dtStr);
	    	return l;
    	}catch(Exception e){
    		return 0;
    	}
    }
    /**
     *  将实际金额long 型 除以100再转换实际真实的金额
     * @param str
     * @return
	 * @throws Exception 
     */
    public static String longToStrByPoint(Long lg,int point){
    	if (lg==null){
    		return "";
    	}
    	try{
	    	Double lt =divide(lg,point,2);
	    	return lt.toString();
    	}catch(Exception e){
    		return "";
    	}
    }
    /**
     *  将实际金额long 型 除以100再转换实际真实的金额
     * @param str
     * @return
	 * @throws Exception 
     */
    public static Double longToDoubleByPoint(Long lg,int point){
    	if (lg==null){
    		return 0.0D;
    	}
    	try{
	    	Double lt =divide(lg,point,2);
	    	return lt;
    	}catch(Exception e){
    		return 0.0D;
    	}
    }
    /**
     *  将实际金额integer 型 除以100再转换实际真实的金额
     * @param str
     * @return
	 * @throws Exception 
     */
    public static Double integerToDoubleByPoint(Integer ig,int point){
    	if (ig==null){
    		return 0.0D;
    	}
    	try{
	    	Double lt =divide(ig,point,2);
	    	return lt;
    	}catch(Exception e){
    		return 0.0D;
    	}
    }
    public static Double ronndGw(Double num){ //毛重的特殊取法 小数部分 小于0.5 按0.5 算 大于0.5 则进1
    	if (num==null) return 0.0;
		String strdd = num+"";
		strdd = strdd.substring(strdd.indexOf('.')+1);
		double result = Double.parseDouble("0."+strdd);
		Double intp=Math.floor(num);
		if (result>=0.1 && result<=0.5){
		   result=intp+0.5;
		}else if ((0.5<result) && (result<=0.999)){
		   result=intp+1;
		}else{
		   result=intp;	
		}
		System.out.println(result);
		return result;
    }
   //根据人民币货值得到运包杂的金额 1 获取运费 2 获取  杂费 3 获取保费 4 总金额
   //别家公司 大联大的算法：=ROUND((ROUND(D10*E10*(1+$F$4),0)+ROUND(ROUND(D10*E10*$F$2,2),0))/(1-$F$3),0)
   //别家公司 大联大的算法： SELECT ROUND((ROUND(6.15*6.1559*(1+0.001),0)+ROUND(ROUND(6.15*6.1559*0.0006,2),0))/(1-0.0002),0) ;
    public static Double getfpmFareAllAmt(Double goodsRmbAmt,String dealType, Integer flag) {
    	if ((goodsRmbAmt<=0) || (dealType==null) || (!"3".equals(dealType.substring(0, 1)))) return 0.0D;//只有成交方式为3 fob才去计算
		double fobRate,allFareAmt,fare,mixFare,premiumsFare;
		fobRate=Float.parseFloat(Configuration.getConfigValue("FOB_FARE_RATE"));
		fare=multiply(goodsRmbAmt, fobRate);
		fare=round(fare,0);
		if (flag==1) return fare; 
		fobRate=Float.parseFloat(Configuration.getConfigValue("FOB_MIX_RATE"));
		mixFare = multiply(goodsRmbAmt, fobRate);
		if (flag==2) return mixFare; 
		allFareAmt=sum(fare, mixFare);
		fobRate=Float.parseFloat(Configuration.getConfigValue("FOB_PREMIUMS_RATE"));
		premiumsFare=multiply(sum(goodsRmbAmt,allFareAmt), fobRate);
		premiumsFare=round(premiumsFare,0);
//		if (premiumsFare<1)
//		premiumsFare=NumberFmt.round(premiumsFare, 0);
		if (flag==3) return  premiumsFare;
		allFareAmt=NumberFmt.sum(allFareAmt, premiumsFare);
		return allFareAmt;
	}
	public static void main(String[] args){
		Double rmbAmt=multiply(1000,10.5);//45000.10;
		rmbAmt=round(rmbAmt,2);
		rmbAmt=multiply(rmbAmt,6.1589);
		Double resutlt=getfpmFareAllAmt(rmbAmt,"3",4);
		rmbAmt=sum(rmbAmt,resutlt);
		System.out.println("完税价格："+rmbAmt);
		System.out.println(round(rmbAmt,0));
		
		//System.out.println(round(119489.6945600000,2));
//		double t=Math.ceil(3.1D);
//		System.out.println(t);
//		double dd = 123.6552356899;
//		ronndGw(null);
		//result就是你要的结果
		
		//String str=fmt("100,000",);
//		String val1="100,001";
//		String val2="100,780";
//		String rev=multiply(val1,val2);
//		Double vl=100010.0;
//		String v2=NumberFmt.fmt(vl, 2, true);
//		System.out.println(v2);

		
	}

	
}
