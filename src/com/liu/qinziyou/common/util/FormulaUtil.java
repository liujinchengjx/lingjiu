package com.liu.qinziyou.common.util;

import java.util.LinkedList;

/**
 * <p>
 * Title: 字符串公式工具类
 * </p>
 * <p>
 * Description:字符串公式公式，包括，计算，公式检测，等等，所有包括费用计算的方法
 * </p>
 * <p>
 * 创建日期:2013-06-25
 * </p>
 * 
 * @version 1.0
 *          <p>
 *          顶丰盛业/p>
 */
public class FormulaUtil {

	/**
	 * 检查字符串公式是否合法 返回值：true合法，false有问题
	 * 
	 * @param String
	 * @return boolean
	 */
	public static boolean doAnalysisFormula(String formulaStr) {
		boolean isRightFormat = true;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		String beforePart  =   "" ;
        String afterPart  =   "" ;
        String calculator  =   "" ;
		int curPos = 0;
		if (formulaStr==null) return true;
		formulaStr=formulaStr.trim();//去掉两边的空格、
		if (formulaStr.indexOf(")(")>=0){
			return false;
		}
		while (isRightFormat
				&& (formulaStr.indexOf("(") >= 0 || formulaStr.indexOf(")") >= 0)) {
			curPos = 0;
			for (char s : formulaStr.toCharArray()) {
				if (s == '(') {
					stack.add(curPos);
				} else if (s == ')') {
					if (stack.size() > 0) {
						beforePart  =  formulaStr.substring(0,stack.getLast());
                        afterPart  =  formulaStr.substring(curPos  +   1 );
                        calculator  =  formulaStr.substring(stack.getLast()  +   1 , curPos);
                        formulaStr  = beforePart  +  afterPart;  // beforePart  +  doCalculation(calculator)  +  afterPart;
						stack.clear();
						break;
					} else {
						System.out.println(" 有未关闭的右括号！ ");
						isRightFormat = false;						
					}
				}
				curPos++;
			}
			if (stack.size() > 0) {
				System.out.println(" 有未关闭的左括号！ ");
				isRightFormat = false;
				break;
			}
		}
		return isRightFormat;

	}
}
