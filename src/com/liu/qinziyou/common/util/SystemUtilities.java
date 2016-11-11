package com.liu.qinziyou.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import com.liu.qinziyou.exception.ServiceException;


// Referenced classes of package com.scm.util:
//			Cryptogram

public class SystemUtilities {

	public SystemUtilities() {
	}

	public static final String isNull(String source) {
		return source != null ? source : "";
	}

	public static final boolean isBlankStr(String str) {
		return str == null || "".equals(str.trim());
	}

	public static final boolean isBlankListItem(String selectedItem) {
		return "---".equals(selectedItem);
	}

	public static final boolean isValidFieldLength(String field, int maxLength,
			boolean allowBlank) {
		if (maxLength < 1)
			throw new IllegalArgumentException("lllegal input max length");
		if (isBlankStr(field)) {
			if (!allowBlank)
				return false;
		} else if (field.getBytes().length > maxLength)
			return false;
		return true;
	}

	public static final boolean isValidFieldLength(String field, int maxLength,
			int minLength, boolean allowBlank) {
		if (maxLength < minLength || minLength < 1)
			throw new IllegalArgumentException("lllegal input max/min length");
		if (field == null || "".equals(field.trim())) {
			if (!allowBlank)
				return false;
		} else if (field.getBytes().length > maxLength
				|| field.getBytes().length < minLength)
			return false;
		return true;
	}

	public static boolean isRepeat(String source, String campare) {
		boolean isRepeat = false;
		int index = -1;
		index = source.indexOf(campare, 0);
		if (index < 0) {
			return isRepeat;
		} else {
			index = source.indexOf(campare, index + campare.length());
			isRepeat = index >= 0;
			return isRepeat;
		}
	}

	public static final String[] split(String str, String delimitor) {
		Vector v = new Vector();
		String ret[] = (String[]) null;
		String delim = null;
		int iFirstPos = 0;
		int iLastPos = 0;
		delim = delimitor != null ? (new StringBuilder()).append(delimitor)
				.toString() : "";
		if (str == null)
			return null;
		if (str.equals("")) {
			ret = new String[1];
			ret[0] = "";
			return ret;
		}
		if (delim.equals("")) {
			ret = new String[1];
			ret[0] = (new StringBuilder()).append(str).toString();
			return ret;
		}
		for (iLastPos = str.indexOf(delim, iFirstPos); iLastPos >= 0; iLastPos = str
				.substring(iFirstPos).indexOf(delim)) {
			iLastPos += iFirstPos;
			v.addElement(str.substring(iFirstPos, iLastPos));
			iFirstPos = iLastPos + delim.length();
		}

		v.addElement(str.substring(iFirstPos, str.length()));
		ret = new String[v.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = (String) v.elementAt(i);

		return ret;
	}

	public static final boolean isAllDigit(String numberString) {
		for (int i = 0; i < numberString.length(); i++)
			if (!Character.isDigit(numberString.charAt(i)))
				return false;

		return true;
	}

	public static final boolean isValidIntegerNumber(String numberString) {
		try {
			double l = Double.parseDouble(numberString);

			return (l >= 0.0D);
		} catch (NumberFormatException nfe) {
		}
		return false;
	}

	public static final boolean isValidNumber(String numberString) {
		try {
			Double.parseDouble(numberString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static final boolean isValidNumber(String numberString,boolean isSeparated) {
		try {
			if (isSeparated){//如果有分隔符，则把,去掉
				numberString = numberString.trim().replaceAll(",", "");
			}
			Double.parseDouble(numberString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static final boolean isValidLengthIntegerNumber(String numberString,
			int length) {
		if (isValidIntegerNumber(numberString)) {
			int len = numberString.length();
			return len <= length;
		} else {
			return false;
		}
	}

	public static final boolean isValidBigDecimal(String decimalString,
			int length, int scale) {
		try {
			BigDecimal big = new BigDecimal(decimalString);

			return ((decimalString.indexOf(".") <= length - scale) && (big
					.scale() <= scale));
		} catch (Exception ex) {
		}
		return false;
	}

	public static final boolean isValidIntegerNumber(String numberString,
			long maxIntegerNumber, long minIntegerNumber, boolean allowZero) {
		if (isValidIntegerNumber(numberString)) {
			long l = Long.parseLong(numberString);
			if (l == 0L)
				return allowZero;
			return l >= minIntegerNumber && l <= maxIntegerNumber;
		} else {
			return false;
		}
	}

	public static final boolean isValidFloatingPointNumber(String numberString) {
		try {
			Double.parseDouble(numberString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static final boolean isValidMultiPhone(String phone) {
		if (phone != null) {
			for (int i = 0; i < phone.length(); i++)
				if (!Character.isDigit(phone.charAt(i))
						&& phone.charAt(i) != '/')
					return false;

		}
		return true;
	}

	public static final boolean isValidFloatingPointNumber(String numberString,
			double maxFloatingPointNumber, double minFloatingPointNumber,
			boolean allowZero) {
		if (isValidFloatingPointNumber(numberString)) {
			double d = Double.parseDouble(numberString);
			if (d == 0.0D)
				return allowZero;
			return d >= minFloatingPointNumber && d <= maxFloatingPointNumber;
		} else {
			return false;
		}
	}

	public static final Timestamp toTimestamp(GregorianCalendar date) {
		return new Timestamp(date.getTime().getTime());
	}

	public static final Timestamp toTimestamp(String dateString,
			String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.US);
		GregorianCalendar gc = new GregorianCalendar();
		try {
			gc.setTime(sdf.parse(dateString));
		} catch (ParseException ex) {
			return null;
		}
		return new Timestamp(gc.getTime().getTime());
	}

	public static final String toDateString(Timestamp timestamp,
			String formatter) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatter, Locale.US);
			return sdf.format(timestamp);
		} catch (Exception ex) {
		}
		return "";
	}

	public static final boolean isValidDate(String dateString, String formatter) {
		SimpleDateFormat myFormat = new SimpleDateFormat(formatter, Locale.US);
		String strDate = dateString;
		Date myDate = null;
		try {
			myDate = myFormat.parse(strDate);
		} catch (ParseException pe) {
			return false;
		}
		String newStrDate = myFormat.format(myDate);
		return strDate.equals(newStrDate);
	}

	public static final boolean isValidASCII(String input) {
		if (input == null)
			return true;
		for (int i = 0; i < input.length(); i++)
			if ((input.charAt(i) > '~' || input.charAt(i) < ' ')
					&& input.charAt(i) != '\n' && input.charAt(i) != '\r')
				return false;

		return true;
	}

	public static final boolean isValidID(String input) {
		if (input == null)
			return true;
		for (int i = 0; i < input.length(); i++)
			if ((input.charAt(i) <= '/' || input.charAt(i) >= ':')
					&& (input.charAt(i) <= '@' || input.charAt(i) >= '[')
					&& (input.charAt(i) <= '`' || input.charAt(i) >= '{')
					&& input.charAt(i) != '_')
				return false;

		return true;
	}

	public static final boolean isValidName(String input) {
		if (input == null)
			return true;
		for (int i = 0; i < input.length(); i++)
			if ((input.charAt(i) <= '/' || input.charAt(i) >= ':')
					&& (input.charAt(i) <= '@' || input.charAt(i) >= '[')
					&& (input.charAt(i) <= '`' || input.charAt(i) >= '{')
					&& input.charAt(i) != '_' && input.charAt(i) <= '\377')
				return false;

		return true;
	}

	public static final boolean isValidPassword(String input) {
		if (input == null)
			return true;
		for (int i = 0; i < input.length(); i++)
			if ((input.charAt(i) <= '/' || input.charAt(i) >= ':')
					&& (input.charAt(i) <= '@' || input.charAt(i) >= '[')
					&& (input.charAt(i) <= '`' || input.charAt(i) >= '{'))
				return false;

		return true;
	}

	public static final boolean isAllChinese(String input) {
		if (input == null)
			return true;
		return input.length() * 2 == input.getBytes().length;
	}

	public static final boolean isAllEnglish(String input) {
		if (input == null)
			return true;
		for (int i = 0; i < input.length(); i++)
			if ((input.charAt(i) <= '@' || input.charAt(i) >= '[')
					&& (input.charAt(i) <= '`' || input.charAt(i) >= '{')
					&& input.charAt(i) != ' ')
				return false;

		return true;
	}

	public static final boolean isValidEmail(String email) {
		String reg3 = "\\w+@\\w+.\\w+";
		String reg4 = "\\w+@\\w+.\\w+.\\w+";
		if (isBlankStr(email))
			return true;
		boolean result3 = Pattern.matches(reg3, email);
		boolean result4 = Pattern.matches(reg4, email);
		return result3 || result4;
	}

	public static final boolean isFirstLetterIsUpCase(String str) {
		String reg3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if (isBlankStr(str))
			return false;
		String up = str.substring(0, 1);
		int len = reg3.indexOf(up);
		return len >= 0;
	}

	public static final boolean isLetterAndNum(String str) {
		String reg3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		if (isBlankStr(str))
			return false;
		for (int i = 0; i < str.length(); i++) {
			String up = str.substring(i, i + 1);
			int len = reg3.indexOf(up);
			if (len == -1)
				return false;
		}

		return true;
	}

	public static final boolean isStringInteger(String numberString, int leng) {
		boolean b = true;
		try {
			Long.parseLong(numberString);
		} catch (NumberFormatException nfe) {
			return false;
		}
		if (b)
			return numberString.length() == leng;
		else
			return false;
	}

	public static final Vector getStringVector(String boxid, String contentid) {
		Vector v = new Vector();
		if (boxid.indexOf("-") == -1) {
			v.addElement((new StringBuilder(String.valueOf(contentid))).append(
					"-").append(boxid).toString());
			return v;
		}
		String strArray[] = new String[2];
		if (boxid != null) {
			int j = 0;
			for (StringTokenizer tokenizer = new StringTokenizer(boxid, "-"); tokenizer
					.hasMoreElements();) {
				strArray[j] = tokenizer.nextToken();
				j++;
			}

		}
		int i = Integer.parseInt(strArray[0]);
		int k = Integer.parseInt(strArray[1]);
		for (int m = i; m <= k; m++) {
			String str = (new StringBuilder(String.valueOf(contentid))).append(
					"-").append(m).toString();
			v.addElement(str);
		}

		return v;
	}

	public static boolean isCellId(String str[]) {
		if (str == null)
			return false;
		int i = 0;
		return i >= str.length || str[i] != null && !"".equals(str[i]);
	}

	public static boolean isContentId(String id) {
		if (id == null || id.length() < 9)
			return false;
		if (id.indexOf("FXQ") == 0)
			return true;
		id = id.trim();
		if (id.indexOf(" ") != -1)
			return false;
		if (id.length() > 11)
			return false;
		if (id.indexOf("XQ") != 0 && id.indexOf("KC") != 0
				&& id.indexOf("TS") != 0)
			return false;
		String three = id.substring(4);
		try {
			Integer.parseInt(three);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	public static boolean isFinallyContentId(String id) {
		if (id == null)
			return false;
		id = id.trim();
		if (id.indexOf(" ") != -1)
			return false;
		if (id.length() < 10)
			return false;
		String str = id.substring(0, 3);
		return "FXQ".equals(str);
	}

	public static String[] getSep(String str, String sep) {
		StringTokenizer stBetweenTable = new StringTokenizer(str, sep);
		ArrayList list = new ArrayList();
		String strT;
		for (; stBetweenTable.hasMoreElements(); list.add(strT))
			strT = (String) stBetweenTable.nextElement();

		String strName[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String strTableName = (String) list.get(i);
			strName[i] = strTableName;
		}

		return strName;
	}

	public static int[] getCartons(String carton) {
		if (carton.indexOf("-") == -1) {
			int oneArray[] = new int[1];
			oneArray[0] = Integer.parseInt(carton);
			return oneArray;
		}
		int iArray[] = new int[2];
		StringTokenizer tokenizer = new StringTokenizer(carton, "-");
		for (int j = 0; tokenizer.hasMoreElements(); j++)
			iArray[j] = Integer.parseInt(tokenizer.nextToken());

		return iArray;
	}

	public static boolean isSerialStorageUnits(String nums) {
		boolean flag = true;
		String suNums[] = getSep(nums, ",");
		if (suNums.length > 1) {
			for (int i = 0; i < suNums.length - 1; i++) {
				String stemp1[] = getSep(suNums[i], "-");
				String stemp2[] = getSep(suNums[i + 1], "-");
				int num1 = Integer.parseInt(stemp1[stemp1.length - 1]);
				int num2 = Integer.parseInt(stemp2[stemp2.length - 1]);
				if (num2 - num1 == 1)
					continue;
				flag = false;
				break;
			}

		}
		return flag;
	}

	public static String getBeforeNdate(String date, int n) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(date));

			int d = c.get(5);

			c.set(5, d + n);

			return df.format(c.getTime());
		} catch (ParseException ex) {
		}
		return date;
	}

	public static String trim(String str) {
		if (isBlankStr(str))
			str = "";
		else
			str = str.trim();
		return str;
	}

	public static String compareDate(String sdate, String edate) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar sc = Calendar.getInstance();
			sc.setTime(df.parse(sdate));
			Calendar ec = Calendar.getInstance();
			ec.setTime(df.parse(edate));
			if (sc.before(ec))
				return "Less";
			if (sc.after(ec))
				return "Great";

			return "Equal";
		} catch (ParseException ex) {
		}
		return "Error";
	}

	private static boolean isContainChinese(String str) {
		int yleng = str.length();
		int hleng = strlen(str);
		return yleng != hleng;
	}

	public static String formatDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("US"));
		return sdf.format(new Date(date));
	}

	private static int strlen(String str) {
		if (str == null || str.length() <= 0)
			return 0;
		int len = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9' || c >= 'a' && c <= 'z' || c >= 'A'
					&& c <= 'Z')
				len++;
			else if (Character.isLetter(c))
				len += 2;
			else
				len++;
		}

		return len;
	}

	public static String isOkPartNO(String str) throws Exception {
		if (isBlankStr(str))
			throw new Exception("PN is null.");
		if (isContainChinese(str)) {
			throw new Exception("PN has chinese word.");
		} else {
			str = str.trim().toUpperCase();
			str = str.replaceAll("\\+", "");
			str = str.replaceAll("&", "");
			return replaceMoreSpace(str);
		}
	}

	public static String replaceMoreSpace(String str) {
		int i = 0;
		while (str.length() > 1) {
			char ch_1 = str.charAt(i);
			char ch_2 = str.charAt(i + 1);
			if (ch_1 == ch_2 && ch_2 == ' ') {
				str = (new StringBuilder(String
						.valueOf(str.substring(0, i + 1)))).append(
						str.substring(i + 2)).toString();
				i--;
			}
			if (++i == str.length() - 1)
				break;
		}
		return str.trim();
	}
    //检测箱号是否合法
	public static String isOkCartonNo(String str) throws Exception {
		if (isBlankStr(str)) {
			return "";
		} else {
			str = str.trim().toUpperCase();
			if (str.contains("-") || str.contains("~")){
				String [] cartons;
				if (str.contains("-")){
					cartons = getSep(str, "-");
				}else{
					cartons = getSep(str, "~");	
				}
				if (cartons.length!=2) throw new Exception("箱号："+str+"不合法！");
				if (!isValidNumber(cartons[0])) throw new Exception("箱号："+str+"不合法！");
				if (!isValidNumber(cartons[1])) throw new Exception("箱号："+str+"不合法！");
				int bpos=Integer.parseInt(cartons[0]);
				int epos=Integer.parseInt(cartons[1]);
				if (bpos<1) throw new Exception("箱号："+str+"不合法！");
				if (bpos==epos) throw new Exception("箱号："+str+"不合法！");
				if (epos<bpos) throw new Exception("箱号："+str+"不合法！");
				return str;	
			}
			if (!isValidNumber(str)) throw new Exception("箱号："+str+"不合法！");
			return str;
		}
	}
	public static String isOkBrand(String str) throws Exception {
		if (isBlankStr(str)) {
			throw new Exception("Brand is null.");
		} else {
			str = str.trim().toUpperCase();
			str = str.replaceAll("\\+", "");
			str = str.replaceAll("&", "");
			return replaceMoreSpace(str);
		}
	}

	public static String isOkOrigin(String str) throws Exception {
		if (isBlankStr(str)) {
			throw new Exception("Origin is null.");
		} else {
			str = str.trim().toUpperCase();
			str = str.replaceAll("\\+", "");
			str = str.replaceAll("&", "");
			str = str.replaceAll(" ", "");
			return str;
		}
	}
	 /**
     *  解析附件上传的箱号，诸如 04-08 或是 04~08 之类的 type 为0 是，返回 4 type为1时返回 8
     *  如果是 04 则当tye 为0 时返回4，type为1时返回-1
     * @param carton
     * @param type
     * @return
     */
    public static Integer getCartonNoBySplit(String carton,Integer type) {
    	if (("".equals(carton)) || (carton==null)){
    		return -1;
    	}
    	carton=carton.replaceAll(" ", "");
    	String [] cartonNos=new String[]{};
    	if (carton.contains("-")){
    		cartonNos=carton.split("-");
    	}else if (carton.contains("~")){
    		cartonNos=carton.split("~");
    	}else{
    		if (type==0){
    			return Integer.valueOf(carton);
    		}else{
    			return -1;
    		}
    	}
    	Integer [] cartonNos1=new Integer[cartonNos.length];
    	for(int i=0;i<cartonNos.length;i++){
    		cartonNos1[i]=Integer.valueOf( cartonNos[i]);
    	}
    	if ((type==0) && (cartonNos1.length>=1)){
    		return cartonNos1[0];
    	}else if((type==0) && (cartonNos1.length<=0)){
    		return -1;
    	}else if ((type==1) && (cartonNos1.length<=1)){
    		return -1;
    	}else if ((type==1) && (cartonNos1.length>=2)){
    		return cartonNos1[1];
    	}
    	return -1;
    }
    /**
     *  比较两个箱号是否相同
     * @param cartonOne
     * @param cartonTwo
     * @return
     */
    public static boolean compareCarton(String cartonOne,String cartonTwo) {
    	if ((cartonOne==null) && (cartonTwo==null)) return true;
    	cartonOne=cartonOne.replace("0", "");
    	cartonOne=cartonOne.replace("-", "");
    	cartonOne=cartonOne.replace("~", "");
    	cartonTwo=cartonTwo.replace("0", "");
    	cartonTwo=cartonTwo.replace("-", "");
    	cartonTwo=cartonTwo.replace("~", "");
    	if (cartonOne.equals(cartonTwo)) return true;
    	return false;
    }
    /**
     * 判断当前currCarton是否是一个合法的箱号  根据前一个preCarton系统箱号来判断当前箱号是否合法
     * 根据系统箱号的连续性，系统箱号必须是一个种递增关系
     * @param currCarton,
     * @param preCarton
     * @return
     */
    public static boolean isLegalCarton(String currCarton,String preCarton) {
		try{
			String[] arrCurrCarton=null;
			String[] arrPreCarton=null;
			if (currCarton.contains("~")){
				 arrCurrCarton=split(currCarton,"~");
			}else{
				 arrCurrCarton=split(currCarton,"-");
			}
			if (preCarton.contains("~")){
				 arrPreCarton=split(preCarton,"~");
			}else{
				 arrPreCarton=split(preCarton,"-");
			}
			Integer maxCarton=preCarton==null? 0: "".equals(preCarton)? 0 
					:Integer.parseInt(arrPreCarton[arrPreCarton.length-1]);
	    	String tempCarton1="",tempCarton2="";
	    	if (arrCurrCarton==null) return false;
	    	if (arrCurrCarton.length>=3) return false;
	    	if (("".equals(preCarton)) || (preCarton==null)){ //说明currCarton 是属于第一箱，currCarton必须等于1
	    	   if (arrCurrCarton.length==1){
	    		   tempCarton1=arrCurrCarton[0].replace("0", "");
	    		   if (! "1".equals(tempCarton1)) return false;
	    	   }else if (arrCurrCarton.length==2){
	    		   tempCarton1=arrCurrCarton[0].replace("0", "");
	    		   if (! "1".equals(tempCarton1)) return false;
	    		   tempCarton2=arrCurrCarton[1].replace("0", "");
	    		   Integer temp=Integer.parseInt(tempCarton2);
	    		   if (temp<=1) return false; //有斜杠的箱号不能两边相等
	    	   }
	    	}
	    	if (arrCurrCarton.length==1){
	    		Integer temp=Integer.parseInt(arrCurrCarton[0]);
	    		if(! temp.equals(maxCarton+1)) return false; //箱号不连续
	    	}
	    	if (arrCurrCarton.length==2){
	    		Integer temp=Integer.parseInt(arrCurrCarton[0]);
	    		if(! temp.equals(maxCarton+1)) return false; //箱号不连续
	    		Integer temp1=Integer.parseInt(arrCurrCarton[1]);
	    		if (temp1<=temp) return false;
	    	}
		 }catch(Exception e){
			 return false;
		 }
    	 return true;
    }
    /**
     * 申报要素自动替换批次号
     * @param elementsSeq 申报要素次序
     * @param element  要替换的申报要素
     * @param batchNo  批次号
     * @return 替换批次好后的申报要素
     */
    public static String autoReplaceBatchNo(String elementsSeq,String element,String lotNum){
		String[] eleSeqs =  elementsSeq.split("\\|");
		String[] elements = element.split("\\|");
		
		//找出批号所在的位置
		int batchNoIndex=-1;
		for(int i=0;i<eleSeqs.length;i++){
			if(eleSeqs[i].indexOf("批号")!=-1){
				batchNoIndex = i;
			}
		}
		if(batchNoIndex!=-1){
			//创建临时数组，数组大小取申报要素和次序中较大的
			String[] tempElements = elements;
			if(elements.length<eleSeqs.length){
				tempElements = new String[eleSeqs.length];
			}else{
				tempElements  = new String[elements.length];
			}
			//将已有申报要素对应位置的值给临时数组
			for(int i=0;i<tempElements.length;i++){
				if(i<elements.length){
					tempElements[i] = elements[i];
				}else {//，说明已有申报要素的元素比申报要素次序的元素少，赋值“无”
					tempElements[i]="无";
				}
			}
			//在批次号位置的元素赋值 批号
			tempElements[batchNoIndex]="批号："+lotNum;
			elements = tempElements;
			
		}
		
		String reStr = "";
		for(int i=0;i<elements.length;i++){
			
			reStr=reStr+"|"+elements[i];
		}
		return reStr.substring(1);
	}
    //查找第二高
	/*public static CheckPrice findSecondBiggest(List<CheckPrice> v) {
		int len = v.size();
		CheckPrice max, second;
		if (len < 1) {
			return null;
		}
		if (len == 1) {
			return v.get(0);
		}
		if (v.get(0).getRmbAmt() > v.get(1).getRmbAmt()) {
			second = v.get(1);
			max = v.get(0);
		} else {
			second = v.get(0);
			max = v.get(1);
		}
		for (int i = 2; i < len; i++) {
			if (max.getRmbAmt() < v.get(i).getRmbAmt()) {
				second = max;
				max = v.get(i);
			} else if (second.getRmbAmt() < v.get(i).getRmbAmt()) {
				second = v.get(i);
			}
		}
		return second;
	}*/
	
	
    private static Map<String,String> dealTypeMaping=new HashMap<String,String>();
    static{
    	
    	//CIF
    	dealTypeMaping.put("1_1", "CIF");
    	dealTypeMaping.put("1_2", "CIF");
    	dealTypeMaping.put("1_3", "CIF");
    	dealTypeMaping.put("3_17", "CIF");
    	
    	//C&F
    	dealTypeMaping.put("2_1", "C&F");
		
    	//FOB
    	dealTypeMaping.put("3_1", "FOB");
    	dealTypeMaping.put("3_2", "FOB");
    	dealTypeMaping.put("3_3", "FOB");
    	dealTypeMaping.put("3_4", "FOB");
    	dealTypeMaping.put("3_5", "FOB");
    	dealTypeMaping.put("3_6", "FOB");
    	dealTypeMaping.put("3_7", "FOB");
    	dealTypeMaping.put("3_8", "FOB");
    	dealTypeMaping.put("3_9", "FOB");
    	dealTypeMaping.put("3_10", "FOB");
    	dealTypeMaping.put("3_11", "FOB");
    	dealTypeMaping.put("3_12", "FOB");
    	dealTypeMaping.put("3_13", "FOB");
    	dealTypeMaping.put("3_14", "FOB");
    	dealTypeMaping.put("3_15", "FOB");
    	dealTypeMaping.put("3_16", "FOB");
    	dealTypeMaping.put("3_19", "FOB");
    	dealTypeMaping.put("3_20", "FOB");
    	dealTypeMaping.put("3_21", "FOB");
    	dealTypeMaping.put("3_22", "FOB");
    	
    	//C&I
    	dealTypeMaping.put("4_1", "C&I");
    	
    	//市场价
    	dealTypeMaping.put("5_1", "市场价");
		
		//垫仓
    	dealTypeMaping.put("6_1", "垫仓");
    	
		//EXW
    	dealTypeMaping.put("1_4", "EXW");
    	
    	//DAP
    	dealTypeMaping.put("1_5", "EXW");
    	
    	//DDU
    	dealTypeMaping.put("1_6", "DDU");
    	dealTypeMaping.put("1_7", "DDU");
    	
		//DDP
    	dealTypeMaping.put("1_8", "DDP");
    	dealTypeMaping.put("1_9", "DDP");
		//FCA
    	dealTypeMaping.put("3-18", "FCA");
    	
    }
	public static String getDealTypeGeneraByDealType(String dealType){
		return dealTypeMaping.get(dealType);
	}
	public static String genOrderCode(String maxOrderCodeOfCurrentDay) throws ServiceException {
		String order = "";
		if(maxOrderCodeOfCurrentDay == null){//
			order = "0";
		}else{
			order = maxOrderCodeOfCurrentDay.substring(6);
			System.out.println(order);
		}
		Integer maxorder= new Integer(order).intValue()+1;
		order = maxorder.toString(); 
		if(order.length()==1){
			order = "000"+order;
		}else if(order.length()==2){
			order = "00"+order;
		}else if(order.length()==3){
			order = "0"+order;
		}
		String day = DateTool.getCurDateTime("yyMMdd");
		order = day+order;
		
		return order;
	}
	
	public static String genMemberCode(String maxMemberCode) throws ServiceException {
		String code = "";
		if(maxMemberCode == null){//
			code = "0";
		}else{
			code = maxMemberCode.substring(1);
			System.out.println(code);
		}
		Integer maxcode= new Integer(code).intValue()+1;
		code = maxcode.toString(); 
		if(code.length()==1){
			code = "00000"+code;
		}else if(code.length()==2){
			code = "0000"+code;
		}else if(code.length()==3){
			code = "000"+code;
		}else if(code.length()==4){
			code = "00"+code;
		}else if(code.length()==5){
			code = "0"+code;
		}
		code = "M"+code;
		System.out.print(code);
		return code;
	}
	public static void main(String args[]) {
		System.out.println(genMemberCode("M0005"));
	}
}