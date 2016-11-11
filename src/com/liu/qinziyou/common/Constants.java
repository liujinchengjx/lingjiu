package com.liu.qinziyou.common;

public class Constants {
	private static Integer defaultOpCompanyId;

	// 业务类型
	public static enum BusinessType {
		IMPORT_TITLE(1), // 一般贸易进口
		EXPORT_TITLE(2), // 一般贸易出口
		WH_TITLE(3), // 仓储
		TRANSPORT_TITLE(4);// 运输
		private Integer value;

		private BusinessType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 订单类型
	public static enum OrderType {
		CUSTOM_ORDER(1), // 一般贸易订单
		RECEIVER_TITLE(2), // 委托单
		DELIVER_TITLE(3);// 发货单
		private Integer value;

		private OrderType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 经营抬头
	public static enum Optype {
		DF_SINGLE_TITLE(1), // 鼎丰单抬头 供方：经营公司 需方： 经营公司
		DF_DOUBLE_TITLE(2), // 鼎丰双抬头 供方：经营公司 需方： 经营公司的客户
		CUS_SINGLE_TITLE(3), // 客户单抬头 供方：客户公司 需方： 客户公司
		CUS_DOUBLE_TITLE(4);// 客户双抬头 供方：客户公司 需方： 客户公司的收货公司

		private Integer value;

		private Optype(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 订单
	// 委托单单据状态 属于委托单的部分
	public static enum EntrustOrderStatus {
		EXCEPTION_UNCONFIRM_TITLE(1), // 异常待定
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定
		GENERATE_TITLE(30); // 已转单---已生成一般贸易

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private EntrustOrderStatus(Integer value) {
			this.value = value;
		}
	}

	// 委托单货物状态-- 属于委托单的部分
	public static enum EntrustGoodsStatus {
		UNCONFIRM_TITLE(10), // 待入库
		CONFIRM_TITLE(20), // 已入库 做完gsin之后
		NOGOODS_TITLE(30);// 无货物

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private EntrustGoodsStatus(Integer value) {
			this.value = value;
		}
	}

	// 报关订单状态
	public static enum CustomOrderStatus {
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定
		AUDITCONFIRM_TITLE(25), // 复合确定
		GENERATE_TITLE(30), // 生成报关订单
		PASS_PART_TITLE(40), // 部分通关
		PASS_ALL_TITLE(50), // 全部通关
		SETTLED_TITLE(60); // 已结算
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private CustomOrderStatus(Integer value) {
			this.value = value;
		}
	}

	// 报关单customForm状态
	public static enum CustomFormStatus {
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20), // 审核通过待报关
		SEND_TITLE(25), // 报关单发送状态
		MAKE_TITLE(30), // 出报关单
		PASS_TITLE(40), // 通关
		DELAY_TITLE(50), // 暂缓上货
		CANCEL_TITLE(60); // 撤单

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private CustomFormStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum DealType { // 成交方式
		CIF_TITLE("1"), // CIF
		C_AND_F_TITLE("2"), // C&F
		FOB_TITLE("3"), // FOB
		C_AND_I_TITLE("4"), // C&I
		MARKET_TITLE("5"), // 市场价
		PADSTOREHOUSE_TITLE("6"); // 垫仓
		private String value;

		public String getValue() {
			return value;
		}

		private DealType(String value) {
			this.value = value;
		}
	}

	// 装车单CusomFormEntrucking的状态
	public static enum CusomFormEntruckingStatus {
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定装货
		STOCK_END_TITLE(30), // 备货完毕
		LOAD_SHIP_TITLE(40), // 装车发运
		CLEARANCE_TITLE(50), // 通关
		CANCEL_TITLE(60); // 取消

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private CusomFormEntruckingStatus(Integer value) {
			this.value = value;
		}

		// 判断是否属于其中的一个常量
		public boolean isConst(Integer tempValue) {
			if (UNCONFIRM_TITLE.value.equals(tempValue) || CONFIRM_TITLE.value.equals(tempValue)
					|| STOCK_END_TITLE.value.equals(tempValue) || LOAD_SHIP_TITLE.value.equals(tempValue)
					|| CLEARANCE_TITLE.value.equals(tempValue) || CANCEL_TITLE.value.equals(tempValue)) {
				return true;
			}
			return false;
		}
	}

	public static enum BackupStatus { // 报关单备货状态
		UNBACKUP_TITLE(10), // 未备货
		BACKUPING_TITLE(20), // 备货中
		BACKUP_OK_ALL_TITLE(30); // 备货完毕
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private BackupStatus(Integer value) {
			this.value = value;
		}
	}

	//
	public static enum TruckBindStatus { // 状态 10 待装车 20 装车中 30 已装车
		UNBIND_TITLE(10), // 待装车
		BIND_PART_TITLE(20), // 装车中
		BIND_ALL_TITLE(30); // 已装车
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private TruckBindStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum DriverStatus {
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20);// 已审核
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private DriverStatus(Integer value) {
			this.value = value;
		}
	}

	// 仓库

	// 入库单GSIN状态
	public static enum StockInStatus {
		UNCONFIRM_TITLE(10), // 待收货
		RECEIPTED_TITLE(20), // 已收货
		CHECK_OK_TITLE(25), // 已验核
		CONFIRM_TITLE(30), // 确定
		EXCEPTION_TITLE(40), // 异常 就是香港的 INQTY !=委托单的QTY时要有这个状态。
		SETTLED_TITLE(50);// 已结算
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StockInStatus(Integer value) {
			this.value = value;
		}
	}

	// 入库单GSIN 上架的状态
	public static enum StockInShelfStatus {
		SHELF_NO_TITLE(10), // 未上架
		SHELF_PART_TITLE(20), // 部分上架
		SHELF_ALL_TITLE(30);// 全部上架
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StockInShelfStatus(Integer value) {
			this.value = value;
		}
	}

	// 出库单GSOUT状态
	public static enum StockOutStatus {
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定
		TOTRADING_TITLE(30);// 转贸易
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StockOutStatus(Integer value) {
			this.value = value;
		}
	}

	// 工作单状态
	public static enum WorkOrderStatus {
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定(已接单)
		COMPLETE_TITLE(30), // 已完成
		SETTLED_TITLE(40), // 已结算
		CANCEL_TITLE(50),// 作废cancel
		EDITPAY_TITLE(60),
		EDITREVICE_TITLE(65);
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private WorkOrderStatus(Integer value) {
			this.value = value;
		}
	}

	// 工作单（增值服务单）类型
	public static enum WorkOrderType {
		RECEIVER_TITLE(1), // 委托单类型 收货
		DELIVER_TITLE(2);// 发货类型
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private WorkOrderType(Integer value) {
			this.value = value;
		}
	}

	// 编码前缀
	public static enum CodePre {
		CUSTOMER_TITLE("CUS"), // 客户编码
		SUPPLIER_TITLE("SUP"), // 供应商编码/经营公司供应商编码
		RECOMPANY_TITLE("REC"), // 收货公司编码
		CONTRACT_TITLE("CONTR"), // 合同编码
		COMPANY_TITLE("COMP"), // 经营公司编码
		TRANSPORT_TITLE("TR"), // 货运公司编码
		ENTRUSTORDER_TITLE("WT"), // 委托单编码
		ORDER_TITLE("SZGS"), // 订单编码
		CUSTOMS_TITLE("BG"), // 报关单编码

		STOCK_IN_TITLE("GSIN"), // 入库单编码
		STOCK_OUT_TITLE("GSOUT"), // 出库单编码

		WORK_ORDER_TITLE_HK("HKGS"), // 工作单
		WORK_ORDER_TITLE_SZ("SZWH"), // 工作单
		WORK_ORDER_TITLE_SH("SHWH"), // 工作单
		WAY_BILL_TITLE("WB"), // 运单

		ENTRUCKING_TITLE("PI"), // 装车单编码

		DRIVER_NO_TITLE("DR"), // 司机编码
		DELIVERY_NO_TITLE("DN"), // 送货单编码
		WAYBILL_NO_TITLE("WN"), // 运单编码
		SETTLEMENT_NO_TITLE("ST");// 结算主档编码

		private String value;

		public String getValue() {
			return value;
		}

		private CodePre(String value) {
			this.value = value;
		}
	}

	// 模版常数
	public static enum UploadTemplate {
		COMMON_TITLE("1"), // 通用模版
		FWA_TITLE("2"), // fwa模版
		MT_TITLE("3"), // MT模版
		FC_TITLE("4"), // 富昌模版
		BATCH_TITLE("5"), // 按批上传
		OLD_TITLE("6");// 旧系统库存模版
		private String value;

		public String getValue() {
			return value;
		}

		private UploadTemplate(String value) {
			this.value = value;
		}
	}

	// 拆单原则 常数
	public static enum TakeApart {
		ADDEDGW_TITLE(0.3), // 毛重额外加0.3kg
		MAXAMT_ALL_TITLE(100000), // 整份单的总金额一般不超过USD100000
		MAXAMT_ONE_TITLE(50000), // 单项金额小于USD50000;
		PRICE_LESS_THAN_TITLE(0.1);// 申报价与历史记录价对比低于10%的，一般会单独拆开申报

		private double value;

		public double getValue() {
			return value;
		}

		private TakeApart(double value) {
			this.value = value;
		}
	}

	// 报关单最大的项数
	public static enum OrderMaxItems {
		MAXCOUNT_TITLE(20);// 每份报关单不能多于20项
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private OrderMaxItems(Integer value) {
			this.value = value;
		}
	}

	public static Integer getDefaultOpCompanyId() {
		if (defaultOpCompanyId == null) {
			String s = Configuration.getConfigValue("default_opcompany_id");
			if (s != null) {
				defaultOpCompanyId = new Integer(s);
			}
		}
		return defaultOpCompanyId;
	}

	// 公司类型
	public static enum CompanyType {
		OPERATION(1), // 运营公司
		CUSTOMER(2), // 客户
		RECEIVER(3), // 客户的客户（客户的收货公司）
		SUPPLIER(4), // 供应商(客户的供应商或运营公司的供应商)
		TRANSPORT(5);// 货运公司

		private Integer value;

		private CompanyType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 公司类型
	public static enum UserType {
		OPERATION(1), // 运营公司
		CUSTOMER(2), // 客户
		RECEIVER(3), // 客户的客户（客户的收货公司）
		SUPPLIER(4), // 供应商(客户的供应商或运营公司的供应商)
		TRANSPORT(5);// 货运公司

		private Integer value;

		private UserType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 供方，需方
	public static enum SellerBuyer {
		SELLER(1), // 供方
		BUYER(2);// 需方
		private Integer value;

		private SellerBuyer(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 客户状态
	public static enum CompanyStatus {
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE_BUS(15), // 待财务审核
		CONFIRM_NOPASS(16), // 待财务审核
		CONFIRM_TITLE(20), // 已审核
		BACK_TITLE(30), // 返回
		FREEZE_TITLE(40), // 冻结
		PAUSE_TITLE(50);// 暂停业务 用户客户欠费是不走货

		private Integer value;

		private CompanyStatus(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	public static enum ContractStatus {
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20);// 已审核
		private Integer value;

		private ContractStatus(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	// 送货单Deliver的状态
	public static enum DeliverStatus {
		UNCONFIRM_TITLE(10), // 待定
		AUDIT_TITLE(20), // 确定复核
		RECEIVE_TITLE(30), // 接单
		STOCK_UP_OVER(35), // 备货完毕
		CONFIRM_TITLE(40), // 确定出库
		TOTRADE_TITLE(45), // 转贸易
		BACK_TITLE(50), // 取消
		CANCEL_TITLE(60); // 作废

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private DeliverStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum DeliveryType {
		TRADE_TITLE(1), // 一般贸易
		WH_MATERIAL_TITLE(2), // 仓储(按料)
		STOCKOUT_TITLE(3), // 仓储转一般贸易(只生成出库单)
		WH_TRAN_BATCH_TITLE(4); // 4： 仓储/运输(按批)
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private DeliveryType(Integer value) {
			this.value = value;
		}

	}

	// 运单的状态
	public static enum WayBillStatus {
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定
		CANCEL_TITLE(30); // 取消

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private WayBillStatus(Integer value) {
			this.value = value;
		}
	}

	// 储位的状态
	public static enum StorageLocationStatus {
		UNUSED_TITLE(10), // 未用中
		USE_TITLE(20), // 使用中
		CANCEL_TITLE(30); // 取消

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StorageLocationStatus(Integer value) {
			this.value = value;
		}
	}

	// 是否有效
	public static enum IsValid {
		FLAG_YES(0), // 有效
		FLAG_NO(1);// 无效
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private IsValid(Integer value) {
			this.value = value;
		}
	}

	public static enum SettlementStatus { // 结算状态
		UNCONFIRM_TITLE(10), // 10待审核
		POSTED_TITLE(15), // 15已提交
		CONFIRM_TITLE(20), // 已审核 20
		RECEIVED_TITLE(30);// 30 已收款
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private SettlementStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum SettlementType { // 结算类型
		IMPORT_TITLE(1), // 一般贸易进口结算
		EXPORT_TITLE(2), // 一般贸易出口结算
		WH_ADD_TITLE(3), // 仓储(增值服务)
		TRANSPORT_TITLE(4);// 运输
		// RECEIVER_TITLE(2),//收货 入库单结算
		// DELIVER_TITLE(3),//发货 出库单结算
		private Integer value;

		private SettlementType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	public static enum CashInStatus { // 入账状态
		UNCASHIN_TITLE(10), // 待入账
		CASHIN_TITLE(20);// 已入账
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private CashInStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum CashInType { // 入账方式 10 自动入账 20 人工入账
		AUTO_IN_TITLE(10), // 自动入账
		MANUAL_TITLE(20);// 手工入账
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private CashInType(Integer value) {
			this.value = value;
		}
	}

	public static enum ForeignExchangePurchasingStatus { // 购汇状态 10 待审核 20 已审核
															// 30已购汇
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20), // 已审核
		BUYRATE_TITLE(30);// 已购汇
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private ForeignExchangePurchasingStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum ForeignExchangePayStatus { // 付汇状态 10 待审核 20 已审核
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20);// 已审核
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private ForeignExchangePayStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum AccountReceivableStatus { // 应收状态 10 待结算 20 已结算 30 取消
		UNCONFIRM_TITLE(10), // 待结算
		CONFIRM_TITLE(20), // 已结算
		CANCEL_TITLE(30);// 取消
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private AccountReceivableStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum AccountPayableStatus { // 应付状态 10未支付 20 已支付 30 取消
		UNCONFIRM_TITLE(10), // 未支付
		CONFIRM_TITLE(20), // 已支付
		CANCEL_TITLE(30);// 取消
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private AccountPayableStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum InvoiceNoticeStatus { // 10 待定 20 确定 30已开票 40 作废 50 返回
		UNCONFIRM_TITLE(10), // 待定
		CONFIRM_TITLE(20), // 确定
		CONFIRM_BILLING_TITLE(30), // 已开票
		CANCEL_TITLE(40), // 作废
		BACK_TITLE(50);// 返回

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private InvoiceNoticeStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum TransFeeSettingStatus {
		UNCONFIRM_TITLE(10), // 待审核
		CONFIRM_TITLE(20);// 已审核
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private TransFeeSettingStatus(Integer value) {
			this.value = value;
		}
	}

	public static enum RateTimePoint { // 汇率时间点
		CUSTOMFORM_TITLE(1), // 报关当天
		BUYER_TITLE(2), // 购汇当天
		PAY_TITLE(3), // 付汇当天
		CUSTOMS_TITLE(4);// 海关保价
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private RateTimePoint(Integer value) {
			this.value = value;
		}
	}

	public static enum HsbcRateType { // 汇丰银行买卖方向
		BUY_TITLE(1), // 买入价
		SELL_TITLE(2);// 卖出价
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private HsbcRateType(Integer value) {
			this.value = value;
		}
	}

	public static enum OrgTargetStockoutFlag {
		// 这个是出库标识 主要是针对源仓库 通常这里是指香港仓库的出货状态
		ORGSTOCKOUT_TITLE1(1), // 1 表示未出库(需要生成出库单 )
		ORGSTOCKOUT_TITLE2(2), // 2则表示 已经出库
		// 这个是出库标识 主要是针对目的仓库 通常这里是指报关过来的深圳仓 的出货状态
		TARGETSTOCKOUT_TITLE1(1), // 1 表示未出库完毕可以出货
		TARGETSTOCKOUT_TITLE2(2);// 2 则表示 已经出库完毕

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private OrgTargetStockoutFlag(Integer value) {
			this.value = value;
		}
	}

	// 特殊标识( 10： 无，(20：A、30：AL、40：AM、50：AML): 商检 ，(60：O): 机电正， 70：消毒 注：括号内是单选一
	// 即只能属于一种
	public static enum SpecialFlag {
		NOT_TITLE(10), // 无
		A_TITLE(20), // 商检带A的情况 (按L标准收)
		AL_TITLE(30), // 商检带AL的情况 (按L标准收)
		AM_TITLE(40), // 商检带AM的情况 (按M标准收)
		AML_TITLE(50), // 商检带AML的情况 (按M标准收)
		O_TITLE(60), // 机电证带O的情况
		DISINFECT_TITLE(70);// 消毒的情况 (按票数收)
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private SpecialFlag(Integer value) {
			this.value = value;
		}
	}

	public static enum StoragelocationType { // 储位类型
		SHELF_TITLE(1), // 货架
		BOARD_TITLE(2);// 板位

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StoragelocationType(Integer value) {
			this.value = value;
		}
	}

	public static enum StoragelocationUserType { // 储位使用类型
		TEMP_TITLE(1), // 临时储位
		PRIVATE_TITLE(2);// 私用

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private StoragelocationUserType(Integer value) {
			this.value = value;
		}
	}

	public static enum ValueAddedType { // 仓储费用分类
		RENT_TITLE(1), // 仓库租赁及库存管理
		DN_TITLE(2), // 提货/送货费用
		MIX_TITLE(3), // 杂费
		ADD_TITLE(4);// 增值服务

		private Integer value;

		public Integer getValue() {
			return value;
		}

		private ValueAddedType(Integer value) {
			this.value = value;
		}
	}

	public static enum ManageModel { // 来货管理模式
		MATERIAL_TITLE(1), // 按料管理
		BATCH_TITLE(2);// 按批管理
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private ManageModel(Integer value) {
			this.value = value;
		}
	}

	public static enum OrderStatusType { // 订单类型
		TRADE_ORDER(1), // 贸易订单
		BG_ORDER(2), // 报关订单
		ZC_ORDER(3), // 装车订单
		INVOICE_ORDER(4), // 发票订单
		ENTRUCT_ORDER(5), // 委托订单
		STOCKIN_ORDER(6), // 入库订单
		SEND_ORDER(7), // 发货
		CARD_ORDER(8), // 车辆安排
		SETTLEMENT_ORDER(9),//结算
		WORK_ORDER(10),// 增值服务
		STOCKOUT_ORDER(11),// 出库单
		WAYBILL_ORDER(12);// 运单
		private Integer value;

		public Integer getValue() {
			return value;
		}

		private OrderStatusType(Integer value) {
			this.value = value;
		}
	}
}
