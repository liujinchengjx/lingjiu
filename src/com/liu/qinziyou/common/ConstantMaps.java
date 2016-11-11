package com.liu.qinziyou.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.util.DictType;

public class ConstantMaps {
	//字典类型
	private static Map<String,String> dictTypes  = null;
	
	private static List<DictType> dictTypesList  = null; //这个对象采用list保存，便于html端绑定combo
	
	private static Map<String, String> packageTypes = new HashMap<String, String>();
	
	private static Map<String, String> currencys = new HashMap<String, String>();
	
	public static Map<String, String> getcurrencys() {
		currencys.put("110", "HKD");//港币
		currencys.put("116", "JPY");//日元
		currencys.put("121", "MOP");//澳门元	
		currencys.put("122", "MYR");//马来西亚林吉特
		currencys.put("132", "SGD");//新加坡元
		currencys.put("133", "KRW");//韩国圆
		currencys.put("142", "CNY");//人民币
		currencys.put("143", "TWD");//台币
		currencys.put("300", "EUR");//欧元
		currencys.put("303", "GBP");//英镑
		currencys.put("304", "DEM");//德国马克
		currencys.put("305", "FRF");//法国法郎
		currencys.put("307", "ITL");//意大利里拉
		currencys.put("312", "ESP");//西班牙比赛塔
		currencys.put("330", "SEK");//瑞典克朗
		currencys.put("331", "CHF");//瑞士法郎
		currencys.put("332", "SUR");//苏联卢布
		currencys.put("501", "CAD");//加拿大元
		currencys.put("502", "USD");//美元
		currencys.put("601", "AUD");//澳大利亚元
		return currencys;
	}
	public static Map<String, String> getPackageTypes() {
		packageTypes.put("1", "CT");
		packageTypes.put("2", "CT");
		packageTypes.put("3", "CT");
		packageTypes.put("4", "CT");
		packageTypes.put("6", "CT");
		packageTypes.put("7", "CT");
		packageTypes.put("5", "BD");
		return packageTypes;
	}
	
	public static Map<String, String> getDictTypes() {
		if (dictTypes==null){
			dictTypes= new HashMap<String,String>();
			//报关用 key 从1 到 20
			dictTypes.put("1", "报关单类型");
			dictTypes.put("2", "单位");
			dictTypes.put("3", "币种");
			dictTypes.put("4", "包装种类");
			dictTypes.put("5", "征免性质");
			dictTypes.put("6", "结汇方式");
			dictTypes.put("7", "征免方式");
			dictTypes.put("8", "成交方式");
			dictTypes.put("9", "贸易方式");
			dictTypes.put("10", "运输方式");
			dictTypes.put("11", "用途");
			dictTypes.put("12", "运保杂币制");
			dictTypes.put("13", "监管方式");
			dictTypes.put("14", "境内目的地");
			dictTypes.put("15", "关区代码");
			dictTypes.put("16", "经营抬头");
			dictTypes.put("17", "运费标识");	 //默认3
			dictTypes.put("18", "保费标识");	//默认1
			dictTypes.put("19", "杂费标识");	//默认3
			dictTypes.put("20", "港口代码");	//
			//预留
			//系统其他用 key 从21开始
			dictTypes.put("21", "业务类型");
			dictTypes.put("22", "客户类型");
			dictTypes.put("23", "特殊标示");
			dictTypes.put("24", "用户级别");
			dictTypes.put("25", "性别");
			dictTypes.put("26", "是否冻结");
			dictTypes.put("27", "是否禁用");
			dictTypes.put("28", "是否"); //0 是 1 否
			dictTypes.put("29", "审核状态");
			dictTypes.put("30", "租赁方式");
			dictTypes.put("31", "海关布控");
			dictTypes.put("32", "毛重加重");
			dictTypes.put("33", "信用等级");	
			dictTypes.put("34", "是否有效"); //0 有效  1 无效
			dictTypes.put("35", "是否一般纳税人");
			dictTypes.put("36", "申报级别");
			dictTypes.put("37", "交易状态");
			dictTypes.put("38", "付款方式");
			dictTypes.put("39", "初始密码");
			dictTypes.put("40", "发货方式");//普通汽运 卡板汽运
			dictTypes.put("41", "车辆类型"); //中港车、内地车、港牌车
			dictTypes.put("42", "汽车状态"); //停运状态、待命状态、出车状态
			dictTypes.put("43", "吨位"); // 3T  5T  8T  10T  12T  20尺  40尺 45尺
			dictTypes.put("44", "运输类型"); //自运，外协，广通物流			
			dictTypes.put("45", "到货方式"); // 指客户的货物到达香港仓的方式 送货上门，鼎丰 
			dictTypes.put("46", "仓库代码"); // WH001 香港 , WH002 深圳 南山 WH003 上海
			dictTypes.put("47", "商品编码类型"); // 指商检，敏感，无，等等这些归类
			dictTypes.put("48", "付费方式"); // 0 到付 1 垫付 2 第三方付 3 免费
			dictTypes.put("49", "储位类型"); //储位类型 1 货架 2 板位
			dictTypes.put("50", "储位使用类型"); //  1公用临时储位，表示任何一个客户都能用，2私用，表示分配给了某一个客户
			dictTypes.put("51", "入账方式"); // 10 自动入账 20 人工入账
			dictTypes.put("52", "出车类型"); // 0 早班 1 晚班
			dictTypes.put("53", "订单类型"); // 1:一般贸易进口订单 2、入库单 3、出库单 
			dictTypes.put("54", "结算类型"); ////结算类型 1:一般贸易进口结算 2、仓储结算 3 入库单 4 增值服务
			dictTypes.put("55", "计费类型"); // 设置费用参数的时候用用 一般贸易
			dictTypes.put("56", "发票类型"); //发票类型 1服务费发票 2 VAT发票 3运输费发票 4仓储费发票
			dictTypes.put("57", "是否保价"); //0 是 1 否
			dictTypes.put("58", "增值服务类型"); //1 委托单类型 2 收货增值服务 3 发货增值服务 4 仓储
			dictTypes.put("59", "派送类型"); // 一般贸易:1 仓储:2 只生成出库单:3
			dictTypes.put("60", "仓储计费类型"); //
			dictTypes.put("61", "汇率时间点"); //
			dictTypes.put("62", "管理模式"); // 客户来料管理模式  1 按料管理 ,2 按批管理
			dictTypes.put("63", "职位级别"); // 职位级别：1,2,3,4
		}
		return dictTypes;
	}

	public static List<DictType> getDictTypesList() {
		if (dictTypesList==null){
			dictTypesList= new ArrayList<DictType>();
			Map<String,String> dictTypesTemp= getDictTypes();
			for(String key : dictTypesTemp.keySet()){
				DictType dt=new DictType();
				dt.setKey(key);
				dt.setValue(dictTypesTemp.get(key));
				dictTypesList.add(dt);		   
			}
			Collections.sort(dictTypesList,new Comparator(){
				public int compare(Object o1, Object o2) {
					DictType dictType1 = (DictType)o1;
					DictType dictType2 = (DictType)o2;
					return Integer.parseInt(dictType1.getKey())-Integer.parseInt(dictType2.getKey());
				}
     		});
		}		
		return dictTypesList;
	}
	
	public static String getSpecialFlag(String value){		
		String str = "";
		if (value != null && value.length() > 0) {
			String[] arr = value.split("\\,");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != null && "10".equals(arr[i])) {
					str += "无,";
				} else if (arr[i] != null && "20".equals(arr[i])) {
					str += "商检(A),";
				} else if (arr[i] != null && "30".equals(arr[i])) {
					str += "商检(AL),";
				} else if (arr[i] != null && "40".equals(arr[i])) {
					str += "商检(AM),";
				} else if (arr[i] != null && "50".equals(arr[i])) {
					str += "商检(AML),";
				} else if (arr[i] != null && "60".equals(arr[i])) {
					str += "机电证(O),";
				} else if (arr[i] != null && "70".equals(arr[i])) {
					str += "消毒,";
				}
			}
			if (str.length() > 0) {
				str = str.substring(0,(str.length() - 1));
			}
		}		
		return str;
	}

}
