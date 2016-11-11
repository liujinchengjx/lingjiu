package com.liu.qinziyou.common.util;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:字符串处理工具</p>
 * <p>创建日期:2013-06-25</p>
 * @version 1.0 
 * <p>顶丰盛业/p>
 */
public class StringUtil {
    private static final String _BR = "<br/>";

    /**
     * 检查字符串是否为数字字符串
     * 返回值：true为是数字，false为不是数字
     * @param String
     * @return boolean
     */
    public static boolean isNumber(String str) {
        int i;
        int j;
        String strTemp = "0123456789";

        if ((str == null) || (str.length() == 0)) {
            return false;
        }

        for (i = 0; i < str.length(); i++) {
            j = strTemp.indexOf(str.charAt(i));

            if (j == -1) {
                return false;
            }
        }
        return true;
    }
    public static String getHsCode(String hsCode,String orgCode) {
    	String str="";
    	if (hsCode!=null){
    		str=hsCode.substring(0, 1);
    		if (isNumber(str)){
    		   return hsCode;
    		}
    	}
    	if (orgCode!=null){
    		str=orgCode.substring(0, 7);
    		if (isNumber(str)){
    		   return orgCode;
    		}
    	}
    	return null;
    }
    /**
     * 检查电子邮件合法性
     * true表示合法 false表示非法
     * @param String email
     * @return boolean
     */
    public static boolean checkEmailIsValid(String email) {
        boolean returnResult = false;

        if ((email == null) || email.equals("")) {
            returnResult = false;
        }

        for (int i = 1; i < email.length(); i++) {
            char s = email.charAt(i);

            if (s == '@') {
                returnResult = true;

                break;
            }
        }

        return returnResult;
    }

    /**
     * 是否为手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);

        return m.matches();
    }

    /**
     * 判断是否为大于1的1-6位数字字符串
     * @param temp
     * @return
     */
    public static boolean isNumbers(String temp) {
        String regex = "^[1-9]\\d*$";

        try {
            if ("0".equals(temp)) {
                return false;
            }

            if ((temp == null) || "".equals(temp)) {
                return false;
            }

            if ((temp.length() > 7) || (!Pattern.matches(regex, temp))) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 判断字符串是否包含特殊字符
     * @param temp
     * @return
     */
    public static boolean compareToStr(String temp) {
        try {
            //剔除特殊字符的regex表达式
            String regexSpc = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regexSpc);
            Matcher m = p.matcher(temp);
            String tempNew = m.replaceAll("").trim();

            if (temp.equals(tempNew)) {
                return true;
            } else {
                return false;
            }
        } catch (PatternSyntaxException pe) {
            return false;
        }
    }
    /**
     * 判断字符串是否包含特殊字符
     * @param temp
     * @return
     */
    public static String delSpecialInStr(String temp) {
    	if ((temp==null) || ("".equals(temp))) return "";
    	String tempNew ="";
    	try {
            //剔除特殊字符的regex表达式
    		temp= temp.replace("（","("); //中文括号改成英文括号
    		temp= temp.replace("）",")");
            String regexSpc = "[`~!@#$%^&*+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regexSpc);
            Matcher m = p.matcher(temp);
            tempNew = m.replaceAll("").trim();            
        } catch (PatternSyntaxException pe) {
          
        }
        return tempNew;
    }
    public static String removeLRtrim(String str){
    	String temp="";
    	if (str!=null){  
    		str=str.replaceAll(" ", " "); //先替换网页空格 即ascii值为160的空格
    		temp=str.trim();
    	}
    	return temp;
    }
    /**
     * 获取包含中英文等混合字符的字符串的字节数
     * @param temp
     * @return
     */
    public static int getStrLength(String temp) {
        String regex = "[^\\x00-\\xff]";
        int length = 0;

        for (int i = 0; i < temp.length(); i++) {
            String c = temp.substring(i, i + 1);

            //System.out.print(c + ":");
            if (Pattern.matches(regex, c)) {
                length += 2;
            } else {
                length += 1;
            }
        }

        return length;
    }

    /**
     * 判断字符串是否在指定字符长度内
     * @param min
     * @param max
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean isLimitStrLength(int min, int max, String str)
        throws UnsupportedEncodingException {
        int length = str.length();

        return (length >= min) && (length <= max);
    }

    /**
     * 判断字符串是否为英文数字以及下划线组成
     * @return
     */
    public static boolean isEngNumUnderline(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) ||
                    ((c >= '0') && (c <= '9')) || (c == '_')) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
    /**
     * 判断字符串是否为纯英文组成
     * @return
     */
    public static boolean isEng(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
    /**
     * 字符是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (null == str) || (0 == str.trim().length());
    }

    /**
     * 字符串是否为空，不包括多个空格的情况
     * @return
     */
    public static boolean isNull(String str) {
        return (null == str) || (0 == str.length());
    }

    /**
     * 字符串初始化函数，把 null 转换为 ""
     *
     * @param str
     *            待初始化的字符串
     * @return String
     */
    public String toString(String str) {
        if (str == null) {
            str = "";
        }

        if (str.equals(null)) {
            str = "";
        }

        return str;
    }

    /**
     * UTF转换函数
     * @param str 待转换的字符串
     * @return String
     */
    public static String toUTF8Encode(String str) {
        try {
			return new String(str.getBytes("GBK"), "UTF-8");
		} catch (UnsupportedEncodingException e) {}
		return "";
    }

    /**
     * 将以逗号（英文逗号）分隔的字符串的每个元素加上单引号
     * 如： 1000,1001,1002 转换后 '1000','1001','1002'
     * @param sSource 用逗号分隔的源字符串
     * @return String
     */
    public String addSingleQuotes(String sSource) {
        String sReturn = "";
        StringTokenizer st = null;

        st = new StringTokenizer(sSource, ",");

        if (st.hasMoreTokens()) {
            sReturn += ("'" + st.nextToken() + "'");
        }

        while (st.hasMoreTokens()) {
            sReturn += ("," + "'" + st.nextToken() + "'");
        }

        return sReturn;
    }

    /**
     * 以给定的分隔符分隔字符串,并存入字符串数组中
     * @param sSource 源字符串
     * @param sChar 分隔符
     * @return String[]
     */
    public String[] stringToArray(String sSource, String sChar) {
        String[] aReturn = null;

        StringTokenizer st = null;
        st = new StringTokenizer(sSource, sChar);

        int i = 0;
        aReturn = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            aReturn[i] = st.nextToken();
            i++;
        }

        return aReturn;
    }

    /**
     * 以给定的分隔符分隔字符串,并存入整型数组中
     * @param sSource 源字符串
     * @param sChar 分隔符
     * @return int[]
     */
    public int[] intToArray(String sSource, char sChar) {
        int[] aReturn = null;

        StringTokenizer st = null;
        st = new StringTokenizer(sSource, String.valueOf(sChar));

        int i = 0;
        aReturn = new int[st.countTokens()];

        while (st.hasMoreTokens()) {
            aReturn[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        return aReturn;
    }

    /**
     * 拆分以逗号分隔的字符串,并存入字符串数组中
     * @param sSource 源字符串
     * @return String[]
     */
    public String[] stringToArrayForComma(String sSource) {
        String[] aReturn = null;

        StringTokenizer st = null;
        st = new StringTokenizer(sSource, ",");
        aReturn = new String[st.countTokens()];

        int i = 0;

        while (st.hasMoreTokens()) {
            aReturn[i] = st.nextToken();
            i++;
        }

        return aReturn;
    }

    /**
     * 将两个对象数组中的所有元素连结为一个对象数组
     * @param array1 源字符串数组1
     * @param array2 源字符串数组2
     * @return Object[]
     */
    public Object[] toArrayAppend(Object[] array1, Object[] array2) {
        int iLen = 0;
        Object[] aReturn = null;

        if (array1 == null) {
            array1 = new Object[0];
        }

        if (array2 == null) {
            array2 = new Object[0];
        }

        iLen = array1.length;
        aReturn = new Object[iLen + array2.length];

        /**
         * 将第一个对象数组的元素加到结果数组中
         */
        for (int i = 0; i < iLen; i++) {
            aReturn[i] = array1[i];
        }

        /**
         * 将第二个对象数组的元素加到结果数组中
         */
        for (int i = 0; i < array2.length; i++) {
            aReturn[iLen + i] = array2[i];
        }

        return aReturn;
    }

    /**
     * 将数组中的元素连成一个以给定字符分隔的字符串
     * @param aSource 源数组
     * @param sChar 分隔符
     * @return 字符串
     */
    public static String arrayToString(String[] aSource, String sChar) {
        String sReturn = "";

        if (aSource == null) {
            return sReturn;
        }

        for (int i = 0; i < aSource.length; i++) {
            if (i > 0) {
                sReturn += sChar;
            }

            sReturn += aSource[i];
        }

        return sReturn;
    }

    /**
     * 将数组中的每个元素两端加上给定的符号
     * @param aSource 源数组
     * @param sChar 符号
     * @return 处理后的字符串数组
     */
    public static String[] arrayAddMark(String[] aSource, String sChar) {
        String[] aReturn = new String[aSource.length];

        for (int i = 0; i < aSource.length; i++) {
            aReturn[i] = sChar + aSource[i] + sChar;
        }

        return aReturn;
    }

    /**
     * 将给定的整数转化成字符串，结果字符串的长度为给定长度,不足位数的左端补"0" 例如val=10，len=5，那么生成的字符串为"00010"
     * @param val 将要被转化成字符串的整数
     * @param len 要转化的字符串长度
     * @return String 返回值
     */
    public static String intToString(int val, int len) {
    	String sNumber = String.valueOf(val);
		int size =  sNumber.length();
		for (int i = 0; i < len -size; i++) {
			sNumber = "0" + sNumber;
		}
		return sNumber;
    }
    public static String nextChar(String s){
		char last=s.charAt(s.length() -1);
		String ostr=String.valueOf(last);    	
		if (ostr==null) return "";
		if ("Z".equals(ostr)) return s.replace("Z","A");
		if (!isEng(ostr)) return s+"A";		
		if (ostr.length()==1){
			char c = ostr.charAt(0);
			char x = (char)(c+1);
			//return s.replace(ostr,String.valueOf(x));
			return s.substring(0, s.length()-1)+String.valueOf(x);
		}else{
			return "";
		}		
	}
    /**
     * 将给定的源字符串加1 例如："0001" 经本函数转换后返回为"0002"
     * @param sSource :源字符串
     * @return 返回字符串
     */
    public static String increaseOne(String sSource) {
        String sReturn = null;
        int iSize = 0;

        iSize = sSource.length();

        long l = (new Long(sSource)).longValue();
        l++;
        sReturn = String.valueOf(l);

        for (int i = sReturn.length(); i < iSize; i++) {
            sReturn = "0" + sReturn;
        }

        return sReturn;
    }
    /**
     * 将给定的源字符串加1 例如："xxx0001" 经本函数转换后返回为"xxx0002"
     * @param sSource :源字符串
     * @return 返回字符串
     */
    public static String increaseOne(String sSource,int rLength) {
        String sReturn = null,temp=null;
        int iSize = 0;
        temp = sSource.substring(sSource.length()-rLength,sSource.length());
        iSize = temp.length();
        long l = (new Long(temp)).longValue();
        l++;
        sReturn = String.valueOf(l);

        for (int i = sReturn.length(); i < iSize; i++) {
            sReturn = "0" + sReturn;
        }
        sReturn=sSource.replace(temp, sReturn);
        return sReturn;
    }
    /**
     * 将给定的源字符串加1 例如："xxx0001" 经本函数转换后返回为"xxx0002"
     * @param sSource :源字符串
     * @return 返回字符串
     */
    public static String increaseOne(String sSource,String pre, int rLength) {
        String sReturn = null,yyMM="",curryyMM="",inrnum="";
        for (int i =sSource.length()-rLength ; i <sSource.length() ; i++){
        	inrnum=inrnum+""+sSource.charAt(i);
        	yyMM=yyMM+""+sSource.charAt(i-rLength);
        }
        curryyMM=DateUtil.getYyMMddStrForDate(new Date());
        if (curryyMM.equals(yyMM)){
        	sReturn=increaseOne(inrnum);
        }else{
        	sReturn="0001";	
        }
        sReturn=pre+curryyMM+sReturn;
        return sReturn;
    }
    /**
     * 将给定的源字符串加1 例如："0001" 经本函数转换后返回为"0002"
     * @param sSource :源字符串
     * @return 返回字符串
     */
    public static String increaseOneByBType(String sSource,String businessType) {
        String sReturn = null;
        int iSize = 0;
        String sSourceTemp="";        
        if ("IMPORT".equals(businessType)){//一般贸易进口
        	if ("".equals(sSource))return "IMPORT001";
        	sReturn=sSource.replace("IMPORT", "");
        	sSourceTemp="IMPORT";
        }else if ("EXPORT".equals(businessType)){//一般贸易出口
        	if ("".equals(sSource))return "EXPORT001";
        	sReturn=sSource.replace("EXPORT", "");
        	sSourceTemp="EXPORT";
        }else if ("WH".equals(businessType)){//仓储
        	if ("".equals(sSource))return "WH001";
        	sReturn=sSource.replace("WH", "");
        	sSourceTemp="WH";
        }else if ("TRANSPORT".equals(businessType)){//运输
        	if ("".equals(sSource))return "TRANSPORT001";
        	sReturn=sSource.replace("TRANSPORT", "");
        	sSourceTemp="TRANSPORT";
        }
        iSize = sReturn.length();
        long l = (new Long(sReturn)).longValue();
        l++;
        sReturn = String.valueOf(l);
        for (int i = sReturn.length(); i < iSize; i++) {
            sReturn = "0" + sReturn;
        }
        return sSourceTemp+sReturn;
    }

    /**
     * 此函数有三个输入参数，源字符串(将被操作的字符串),原字符串中将要被替换的字符串(旧字符串)
     * 替换的字符串(新字符串)，函数接收源字符串、旧字符串、新字符串三个值后， 用新字符串代替源字符串中的旧字符串并返回结果
     * @param source 源字符串
     * @param oldString 旧字符串
     * @param newString 新字符串
     * @return 替换后的字符串
     */
    public String toReplace(String source, String oldString, String newString) {
        StringBuffer output = new StringBuffer();

        int lengthOfSource = source.length(); // 源字符串长度
        int lengthOfOld = oldString.length(); // 老字符串长度

        int posStart = 0; // 开始搜索位置
        int pos; // 搜索到老字符串的位置

        while ((pos = source.indexOf(oldString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));

            output.append(newString);
            posStart = pos + lengthOfOld;
        }

        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }

    /**
     * 将指定字符串从源字符串中删除掉，并返回替换后的结果字符串
     * @param source 源字符串
     * @param subString 要删除的字符
     * @return 替换后的字符串
     */
    public String toDeleteString(String source, String subString) {
        StringBuffer output = new StringBuffer();

        // 源字符串长度
        int lengthOfSource = source.length();

        // 开始搜索位置
        int posStart = 0;

        // 搜索到老字符串的位置
        int pos;

        while ((pos = source.indexOf(subString, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));
            posStart = pos + 1;
        }

        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }

    /**
     * 查找源字符串数组中是否包含给定字符串
     * @param aSource:源字符串数组
     * @param sItem:子串
     * @return 是否包含
     */
    public static boolean isContain(String[] aSource, String sItem) {
        boolean isReturn = false;

        for (int i = 0; i < aSource.length; i++) {
            if (sItem.equals(aSource[i])) {
                isReturn = true;

                break;
            }
        }

        return isReturn;
    }

    /**
     * 查找以逗号分隔的源字符串是否包含给定字符串
     * @param sSource:源字符串
     * @param sItem:子串
     * @return 是否包含
     */
    public boolean isContain(String sSource, String sItem) {
        boolean isReturn = false;
        StringTokenizer st = null;
        st = new StringTokenizer(sSource, ",");

        while (st.hasMoreTokens()) {
            if (sItem.equals(st.nextToken())) {
                isReturn = true;

                break;
            }
        }

        return isReturn;
    }

    /**
     * 功能描述：分割字符串
     * @param str String 原始字符串
     * @param splitsign String 分隔符
     * @return 分割后的字符串数组
     */
    @SuppressWarnings("unchecked")
    public static String[] split(String str, String splitsign) {
        int index;
        if ((str == null) || ("".equals(str)) || (splitsign == null) ) {
            return null;
        }
        ArrayList al = new ArrayList();
        while ((index = str.indexOf(splitsign)) != -1) {
            al.add(str.substring(0, index));
            str = str.substring(index + splitsign.length());
        }
        al.add(str);
        return (String[]) al.toArray(new String[0]);
    }
    @SuppressWarnings("unchecked")
    public static Integer [] toIntArrFrStrArr(String [] strArr) {
        if ((strArr == null) ) {
            return null;
        }
        if (strArr.length<=0){
        	return null;
        }
        Integer [] intArray=new Integer[strArr.length];
		for(int i=0;i<strArr.length;i++) {
			intArray[i] =(int)Double.parseDouble(strArr[i]);
		}    
        return intArray;
    }

    /**
     * 功能描述：替换字符串
     * @param from String 原始字符串
     * @param to String 目标字符串
     * @param source String 母字符串
     * @return String 替换后的字符串
     */
    public static String replace(String from, String to, String source) {
        if ((source == null) || (from == null) || (to == null)) {
            return null;
        }

        StringBuffer str = new StringBuffer("");
        int index = -1;

        while ((index = source.indexOf(from)) != -1) {
            str.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
            index = source.indexOf(from);
        }

        str.append(source);

        return str.toString();
    }

    /**
     * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlencode(String str) {
        if (str == null) {
            return null;
        }

        return replace("\"", "&quot;", replace("<", "&lt;", str));
    }

    /**
     * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
     * @param str String
     * @return String
     */
    public static String htmldecode(String str) {
        if (str == null) {
            return null;
        }

        return replace("&quot;", "\"", replace("&lt;", "<", str));
    }

    /**
     * 功能描述：在页面上直接显示文本内容，替换小于号，空格，回车，TAB
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String htmlshow(String str) {
        if (str == null) {
            return null;
        }

        str = replace("<", "&lt;", str);
        str = replace(" ", "&nbsp;", str);
        str = replace("\r\n", _BR, str);
        str = replace("\n", _BR, str);
        str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);

        return str;
    }

    /**
     * 功能描述：删除小于号&lt;，空格&nbsp;，回车，TAB
     * @param str String 原始字符串
     * @return String 替换后的字符串
     */
    public static String delHtmlText(String str) {
        if (str == null) {
            return null;
        }

        str = replace("&lt;", "", str);
        str = replace("&nbsp;", "", str);
        str = replace(_BR, "", str);
        str = replace("&nbsp;&nbsp;&nbsp;&nbsp;", "", str);
        str = replace("&gt;", "", str);
        str = replace(_BR, "", str);
        str = replace("&quot;", "", str);

        return str;
    }

    /**
     * 去掉所有的HTML标签
     * @param html
     * @return
     */
    public static String delHTML(String html) {
        String str = html.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "")
                         .replaceAll("</[a-zA-Z]+[1-9]?>", "");

        return str;
    }

    /**
     * 功能描述：返回指定字节长度的字符串
     * @param str String 字符串
     * @param length int 指定长度
     * @return String 返回的字符串
     */
    public static String toLength(String str, int length) {
        if (str == null) {
            return null;
        }

        if (length <= 0) {
            return "";
        }

        try {
            if (str.getBytes("GBK").length <= length) {
                return str;
            }
        } catch (Exception e) {
        }

        StringBuffer buff = new StringBuffer();

        int index = 0;
        char c;
        length -= 3;

        while (length > 0) {
            c = str.charAt(index);

            if (c < 128) {
                length--;
            } else {
                length--;
                length--;
            }

            buff.append(c);
            index++;
        }

        buff.append("...");

        return buff.toString();
    }

    /**
     * 功能描述：判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");

        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     * @param str 传入的字符串
     * @return 是浮点数返回true,否则返回false
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");

        return pattern.matcher(str).matches();
    }

    /**
     * 判断是不是合法字符 c 要判断的字符
     */
    public static boolean isLetter(String str) {
        if ((str == null) || (str.length() < 0)) {
            return false;
        }

        Pattern pattern = Pattern.compile("[\\w\\.-_]*");

        return pattern.matcher(str).matches();
    }

    /**
     * 从指定的字符串中提取Email content 指定的字符串
     * @param content
     * @return
     */
    public static String parse(String content) {
        String email = null;

        if ((content == null) || (content.length() < 1)) {
            return email;
        }

        // 找出含有@
        int beginPos;
        int i;
        String token = "@";
        String preHalf = "";
        String sufHalf = "";

        beginPos = content.indexOf(token);

        if (beginPos > -1) {
            // 前项扫描
            String s = null;
            i = beginPos;

            while (i > 0) {
                s = content.substring(i - 1, i);

                if (isLetter(s)) {
                    preHalf = s + preHalf;
                } else {
                    break;
                }

                i--;
            }

            // 后项扫描
            i = beginPos + 1;

            while (i < content.length()) {
                s = content.substring(i, i + 1);

                if (isLetter(s)) {
                    sufHalf = sufHalf + s;
                } else {
                    break;
                }

                i++;
            }

            // 判断合法性
            email = preHalf + "@" + sufHalf;

            if (isEmail(email)) {
                return email;
            }
        }

        return null;
    }

    /**
     * 功能描述：判断输入的字符串是否符合Email样式.
     * @param str 传入的字符串
     * @return 是Email样式返回true,否则返回false
     */
    public static boolean isEmail(String email) {
        if ((email == null) || (email.length() < 1) || (email.length() > 256)) {
            return false;
        }

        Pattern pattern = Pattern.compile(
                "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

        return pattern.matcher(email).matches();
    }

    /**
     * 功能描述：判断输入的字符串是否为纯汉字
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true,否则返回false
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");

        return pattern.matcher(str).matches();
    }

    /**
     * 功能描述：判断是否为质数
     * @param x
     * @return
     */
    public static boolean isPrime(int x) {
        if (x <= 7) {
            if ((x == 2) || (x == 3) || (x == 5) || (x == 7)) {
                return true;
            }
        }

        int c = 7;

        if ((x % 2) == 0) {
            return false;
        }

        if ((x % 3) == 0) {
            return false;
        }

        if ((x % 5) == 0) {
            return false;
        }

        int end = (int) Math.sqrt(x);

        while (c <= end) {
            if ((x % c) == 0) {
                return false;
            }

            c += 4;

            if ((x % c) == 0) {
                return false;
            }

            c += 2;

            if ((x % c) == 0) {
                return false;
            }

            c += 4;

            if ((x % c) == 0) {
                return false;
            }

            c += 2;

            if ((x % c) == 0) {
                return false;
            }

            c += 4;

            if ((x % c) == 0) {
                return false;
            }

            c += 6;

            if ((x % c) == 0) {
                return false;
            }

            c += 2;

            if ((x % c) == 0) {
                return false;
            }

            c += 6;
        }

        return true;
    }

    /**
     * 功能描述：人民币转成大写
     * @param str 数字字符串
     * @return String 人民币转换成大写后的字符串
     */
    public static String hangeToBig(String str) {
        double value;
        try {
            value = Double.parseDouble(str.trim().replaceAll(",",""));
        } catch (Exception e) {
            return null;
        }
        char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
        char[] vunit = { '万', '亿' }; // 段名表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
        long midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串
        if (("0".equals(valStr)) || ("0.0".equals(valStr))){
        	return "零";
        }
        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分
        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
                            // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角" +
                digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数

        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字

            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置

            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增

                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if ((idx == 0) && (vidx > 0) && (zeroSerNum < 4)) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }

                continue;
            }

            zeroSerNum = 0; // 连续0次数清零

            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }

            prefix += digit[chDig[i] - '0']; // 转化该数字表示

            if (idx > 0) {
                prefix += hunit[idx - 1];
            }

            if ((idx == 0) && (vidx > 0)) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0) {
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        }

        return prefix + suffix; // 返回正确表示
    }

    /**
     * 功能描述：去掉字符串中重复的子字符串
     * @param str 原字符串，如果有子字符串则用空格隔开以表示子字符串
     * @return String 返回去掉重复子字符串后的字符串
     */
    @SuppressWarnings("unused")
    public static String removeSameString(String str, String regx) {
        Set<String> mLinkedSet = new LinkedHashSet<String>(); // set集合的特征：其子集不可以重复
        String[] strArray = str.split(regx); // 根据空格(正则表达式)分割字符串
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strArray.length; i++) {
            if (!mLinkedSet.contains(strArray[i])) {
                mLinkedSet.add(strArray[i]);
                sb.append(strArray[i] + regx);
            }
        }

        System.out.println(mLinkedSet);

        return sb.toString();
    }

    /**
     * 功能描述：过滤特殊字符
     * @param src
     * @return
     */
    public static String encoding(String src) {
        if (src == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        if (src != null) {
            src = src.trim();

            for (int pos = 0; pos < src.length(); pos++) {
                switch (src.charAt(pos)) {
                case '\"':
                    result.append("&quot;");

                    break;

                case '<':
                    result.append("&lt;");

                    break;

                case '>':
                    result.append("&gt;");

                    break;

                case '\'':
                    result.append("&apos;");

                    break;

                case '&':
                    result.append("&amp;");

                    break;

                case '%':
                    result.append("&pc;");

                    break;

                case '_':
                    result.append("&ul;");

                    break;

                case '#':
                    result.append("&shap;");

                    break;

                case '?':
                    result.append("&ques;");

                    break;

                default:
                    result.append(src.charAt(pos));

                    break;
                }
            }
        }

        return result.toString();
    }

    /**
     * 功能描述：判断是不是合法的手机号码
     * @param handset
     * @return boolean
     */
    public static boolean isHandset(String handset) {
        try {
            String regex = "^1[\\d]{10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(handset);

            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * 功能描述：反过滤特殊字符
     * @param src
     * @return
     */
    public static String decoding(String src) {
        if (src == null) {
            return "";
        }

        String result = src;
        result = result.replace("&quot;", "\"").replace("&apos;", "\'");
        result = result.replace("&lt;", "<").replace("&gt;", ">");
        result = result.replace("&amp;", "&");
        result = result.replace("&pc;", "%").replace("&ul", "_");
        result = result.replace("&shap;", "#").replace("&ques", "?");

        return result;
    }
    /**
     * 判断一个字符串在另一个数组里面是否存在
     * @param source
     * @param d
     * @return
     */
    public static boolean contains(Collection<String> source,String d) {
		for (String s : source) {
				if (d.equals(s)) {
					return true;
				}
		}
		return false;
	}
    
    public static boolean contains(String[] source,String d) {
		for (String s : source) {
				if (d.equals(s)) {
					return true;
				}
		}
		return false;
	}
   
    //右边不足位补c
    public static String rPad(String strs,Integer len,String c){
    	    if (strs==null) return null;
			int strLen = strs.trim().length();
			if (strLen < len) {
				while (strLen < len) {
					StringBuffer sb = new StringBuffer();
					sb.append(strs).append(c);//右补0
					strs = sb.toString();
					strLen = strs.length();
				}
			}			
			return strs;
    }
    //左边不足位补c
    public static String lPad(String strs,Integer len,String c){
    	if (strs==null) return null;	
    	int strLen = strs.trim().length();
		if (strLen < len) {
			while (strLen < len) {
				StringBuffer sb = new StringBuffer();
				sb.append(c).append(strs);//左补0
				strs = sb.toString();
				strLen = strs.length();
			}
		}			
		return strs;
    }
    //给出一个字符串，取出数字部分
    public static String getNumbers(String content) {  
	       Pattern pattern = Pattern.compile("\\d+");  
	       Matcher matcher = pattern.matcher(content);  
	       while (matcher.find()) {  
	           return matcher.group(0);  
	       }  
	       return "";  
	}
    public static String analyzeDealType(String dealType){
    	if (dealType!=null && (!"".equals(dealType))){
    		int index=dealType.indexOf("_");
    		return dealType.substring(0, index);
    	}else{
    		return "1";	
    	}
    	
    }
    /**
     * 累加字符串的数字部分,如果没有数字部分，则直接返回改字符串
     * @return
     */
    public static String incStrForNum(String str) {
    	String tempStr=str;
    	String tempNum=getNumbers(tempStr);
        if (!("".equals(tempNum)) && (tempNum!=null)){ //不存在数字部分 则直接返回
    	   Integer num=Integer.parseInt(tempNum);
    	   num=num+1;
    	   tempStr=tempStr.replace(tempNum, num.toString());
        }
        return tempStr;        
    }
    //将 Integer[] 数组中重复的值过滤掉
   public static Integer[] getDistinctIntArray(Integer num[]) {
        List<Integer> list = new java.util.ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            if (!list.contains(num[i])) {//如果list数组不包括num[i]中的值的话，就返回true。
                list.add(num[i]); //在list数组中加入num[i]的值。已经过滤过。
            }
        }
        return list.toArray(new Integer[0]);  //toArray（数组）方法返回数组。并要指定Integer类型。new integer[o]的空间大小不用考虑。因为如果list中的长度大于0（你integer的长度），toArray方法会分配一个具有指定数组的运行时类型和此列表大小的新数组。
    }
   //将 Integer[] 数组中重复的值过滤掉
   public static String IntArrayToStr(Integer num[],String splitsign) {
        String val="";
        for(int i = 0; i < num.length; i++) {
            if ("".equals(val)){
            	val=num[i].toString();
            }else{
            	val=val+splitsign+num[i].toString();
            }
        }
        return val; 
    }


   //拆单时查询最大的编码、如果中间中断 则取中断的编码为最大
   public static Object getChar(List list){
	   	Object co = null;
	   	if(list != null && !list.isEmpty()){
	   		String str = "";
	   		char last;
	   		String ostr;
	   		int len=0;
	   		char c;
	   		char x = 0;
	   		for (int i = 0; i < list.size(); i++) {
	   			if(i != 0){
	   				String s1 = list.get(i) + "";
	   				char last1=s1.charAt(s1.length() -1);
	   				String ostr1=String.valueOf(last1);    
	   				char c1 = ostr1.charAt(0);
	   				if(c1 != x){
	   					co = str;
	   					break;
	   				}else{
	   					co = s1;
	   				}
	   			}
	   			str = list.get(i) + "";
	   			len=str.length() -1;
	   			last=str.charAt(len);
	   			ostr=String.valueOf(last);
	   			if (i==0 && !ostr.equals("A")){ //第一个不是A
	   				return null;
	   			}
	   			c = ostr.charAt(0);
	   			x = (char)(c+1);
	   			co = str;
				}
	   	}
	   	return co;
    }
   
   public static String getWH(String whcode){
		if(whcode.toUpperCase().equals("WH0001")){
			return "HK";
		}else if(whcode.toUpperCase().equals("WH0002")){
			return "SZ";
		}else if(whcode.toUpperCase().equals("WH0003")){
			return "SH";
		}else{
			return "HK";
		}
	}
   
   public static String getPhone(String phoneNo){
       if(null==phoneNo||"".equals(phoneNo)||(3>=phoneNo.length())){
           return phoneNo;
       }else if(phoneNo.length()>3&&phoneNo.length()<11){
           return phoneNo.substring(0,3)+"****";
       }else{
           return phoneNo.substring(0,3)+"****"+phoneNo.substring(7,phoneNo.length()); 
       }
   }

	public static void main(String[] args){
		String str="";
		str=rPad(str,4*2,"10");
		
		String temp="10";
		temp=temp.substring(temp.length()-2);
		if (Integer.parseInt(temp)>=99){
			System.out.println("大于99");
		}else{
			System.out.println("小于99");
		}
//		String contractNo="DF1407012";
//		List<String> list=new ArrayList<String>();
//		list.add("DF1407012A");
//		list.add("DF1407012B");
//		list.add("DF1407012C");
//		list.add("DF1407012D");
//		Object cfno =StringUtil.getChar(list);
//		String nextNumNo=StringUtil.nextChar(cfno==null?contractNo+"":cfno.toString());
//		System.out.println(nextNumNo);
//		boolean t="1_10".substring(0, 1).equals("1");
//		System.out.println(t);
//		boolean b=compareToStr("*123-5656");
//		String str="76497442-Z";
//		System.out.println(str.substring(0, 7));
//		String s = "FSZGS14050003F";
//    	char last=s.charAt(s.length() -1);
//		String ostr=String.valueOf(last);    	
//		if (ostr==null) System.out.println("");
//		if ("Z".equals(ostr)) System.out.println(s.replace("Z","A")) ;
//		if (!isEng(ostr)) System.out.println(s+"A") ;		
//		if (ostr.length()==1){
//			char c = ostr.charAt(0);
//			char x = (char)(c+1);
//			System.out.println(ostr+"==="+String.valueOf(x));
//			String str = s.substring(0, s.length()-1)+String.valueOf(x);
//			System.out.println(str);
//			System.out.println(s.replace(ostr,String.valueOf(x)));
//		}		

	}
}
