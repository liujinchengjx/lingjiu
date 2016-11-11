var Dictionary={  //静态端字典 主要是combox用
    getDictName:function(dictArr,code){
		if (code=="0"){
		} else if (code==null || code=="") return "";
		for(var i=0;i<dictArr.length;i++){
			if (dictArr[i].dictCode==code){
				return dictArr[i].dictName;
			}
		}
	},
	getDicCode:function(dictArr,curency){
		if(curency==""||curency==null){
			return "";
		}else{
			for(var i=0;i<dictArr.length;i++){
				if(dictArr[i].dictName==curency){
					return dictArr[i].dictCode;
				}
			}
		}
	},
	getMulDictName:function(dictArr,codes){
		if (codes==null || codes=="") return "";
		var arrcode=codes.split(",");
		if ((arrcode==null) || (arrcode.length<=0)) return false;
		var dictName=[];
		for(var i=0;i<arrcode.length;i++){
			for(var j=0;j<dictArr.length;j++){
				if (dictArr[j].dictCode== trim(arrcode[i])){
				   dictName.push(dictArr[j].dictName);
				   break;
				}
			}
		}
		return dictName.join(",");
	},
	EntrustOrderStatus:[{dictCode:1, dictName:"异常返回"},
						{dictCode:10, dictName:"待定"},
						{dictCode:20, dictName:"确定"},
						{dictCode:30, dictName:"已转单"}
						],
	EntrustGoodsStatus:[{dictCode:10, dictName:"待入库"},
						{dictCode:20, dictName:"已入库"},
						{dictCode:30, dictName:"无货物"}
						],
	CustomOrderStatus:[ {dictCode:10, dictName:"待定"},
						{dictCode:20, dictName:"确定"},
						{dictCode:25, dictName:"复核确定"},
						{dictCode:30, dictName:"生成报关订单"},
						{dictCode:40, dictName:"部分通关"},
						{dictCode:50, dictName:"全部通关"},
						{dictCode:60, dictName:"已结算"}
						],
	CusomFormStatus: [{dictCode:10, dictName:"待审核"},
						{dictCode:20, dictName:"审核通过待报关"},
						{dictCode:25, dictName:"发送"},
						{dictCode:30, dictName:"出报关单"},
						{dictCode:40, dictName:"通关"},
						{dictCode:50, dictName:"暂缓上货"},
						{dictCode:60, dictName:"撤单"}
						],
	CusomFormEntruckingStatus:[{dictCode:10, dictName:"待定"},
						{dictCode:20, dictName:"确定装货"},
						{dictCode:30, dictName:"备货完毕"},
						{dictCode:40, dictName:"装车发运"},
						{dictCode:50, dictName:"通关"},
						{dictCode:60, dictName:"取消"}
						],
	TruckBindStatus:[{dictCode:10, dictName:"待装车"},
						{dictCode:20, dictName:"装车中"},
						{dictCode:30, dictName:"已装车"}
						],
	CompanyStatus: [{dictCode:10, dictName:"待业务审核"},
	                {dictCode:15, dictName:"待财务审核"},
	                {dictCode:16, dictName:"审核不通过"},
					{dictCode:20, dictName:"已审核"},
					{dictCode:30, dictName:"返回"},
					{dictCode:40, dictName:"冻结"},
					{dictCode:50, dictName:"暂停业务"}
					],
	DriverStatus: [{dictCode:10, dictName:"待审核"}, //司机状态
					{dictCode:20, dictName:"已审核"}
					],
	ContractStatus:[{dictCode:10, dictName:"待审核"},
					{dictCode:20, dictName:"已审核"}
					],
	StockOutStatus:[{dictCode:10, dictName:"待定"},
					{dictCode:20, dictName:"确定"},
					{dictCode:30, dictName:"转贸易"}
					], 
	WayBillStatus:[{dictCode:10, dictName:"待定"},
					{dictCode:20, dictName:"确定"},
					{dictCode:30, dictName:"取消"}
					], 
	BackupStatus:[{dictCode:10, dictName:"待备货"},
				  {dictCode:20, dictName:"备货中"},
				  {dictCode:30, dictName:"备货完毕"}
				], 
	ForeignExchangePayStatus:[{dictCode:10, dictName:"待审核"},
				              {dictCode:20, dictName:"已审核"}
				             ],
	ForeignExchangePurchasingStatus:[{dictCode:10, dictName:"待审核"},
				                     {dictCode:20, dictName:"已审核"},
								     {dictCode:30, dictName:"已购汇"}
				                     ],
	InvoiceNoticeStatus:[{dictCode:10, dictName:"待定"},
						 {dictCode:20, dictName:"确定"},
						 {dictCode:30, dictName:"已开票"},
						 {dictCode:40, dictName:"作废"},
						 {dictCode:50, dictName:"返回"}
						 ],
	AccountReceivableStatus:[{dictCode:10, dictName:"待结算"},
							 {dictCode:20, dictName:"已结算"},
							 {dictCode:30, dictName:"取消"}
							 ],
	AccountReceivableOrderType:[{dictCode:1, dictName:"一般贸易订单"},
							 {dictCode:2, dictName:"委托单"},
							 {dictCode:3, dictName:"发货单"}
							 ],
	AccountPayableStatus:[{dictCode:10, dictName:"未支付"},
							 {dictCode:20, dictName:"已支付"},
							 {dictCode:30, dictName:"取消"}
							 ],
	CashInRecordStatus:[{dictCode:10, dictName:"待入账"},
						{dictCode:20, dictName:"已入账"}
						],
	StockInStatus:[{dictCode:10, dictName:"待收货 "},
				   {dictCode:20, dictName:"已收货"},
				   {dictCode:25, dictName:"已验核"},
				   {dictCode:30, dictName:"确定"},
				   {dictCode:40, dictName:"异常"},
				   {dictCode:50, dictName:"已结算"}
				 ],
	StockInShelfStatus:[{dictCode:10, dictName:"未上架"},
				   {dictCode:20, dictName:"部分上架"},
				   {dictCode:30, dictName:"全部上架"}
				 ],
	SettlementStatus:[{dictCode:10, dictName:"待提交"},
					{dictCode:15, dictName:"待审核"},  
				   {dictCode:20, dictName:"已审核"},
				   {dictCode:30, dictName:"已收款"}
				 ],
	AccountStatus: [{dictCode:0, dictName:"进"},
				   {dictCode:1, dictName:"出"}
				 ],
	WorkOrderStatus:[{dictCode:10, dictName:"待定"},
				     {dictCode:20, dictName:"已接单"},
					 {dictCode:30, dictName:"已完成"},
					 {dictCode:40, dictName:"已结算"},
					 {dictCode:50, dictName:"作废"},
					 {dictCode:60, dictName:"应付完成"},
					 {dictCode:65, dictName:"应收完成"}
				 ],
	DeliverStatus:[{dictCode:10, dictName:"待定"},
				   {dictCode:20, dictName:"确定"},
				   {dictCode:30, dictName:"接单"},
				   {dictCode:35, dictName:"备货完毕"},
				   {dictCode:40, dictName:"已出库"},
				   {dictCode:45, dictName:"转贸易"},
				   {dictCode:50, dictName:"取消"},
				   {dictCode:60, dictName:"作废"}
				 ],
    BusinessType:[{dictCode:'1', dictName:'一般贸易进口'},
               {dictCode:'2', dictName:'一般贸易出口'},
               {dictCode:'3', dictName:'仓储'},
               {dictCode:'4', dictName:'运输'}
			  ],
	DeliveryWay:[{dictCode:'1', dictName:'送货上门'},
               {dictCode:'2', dictName:'鼎丰提货'},
               {dictCode:'3', dictName:'国际快递'},
			   {dictCode:'4', dictName:'广通提货'}
			  ],
	PackingType:[{dictCode:'1', dictName:'木箱'},//包装方式
               {dictCode:'2', dictName:'纸箱'},
               {dictCode:'3', dictName:'桶装'},
			   {dictCode:'4', dictName:'散装'},
			   {dictCode:'5', dictName:'托盘'},
			   {dictCode:'6', dictName:'包'},
			   {dictCode:'7', dictName:'其它'}
			  ],
	OpType: [{dictCode:'1', dictName:'单抬头'}, //经营抬头
             {dictCode:'2', dictName:'双抬头'},
			 {dictCode:'5', dictName:'无'}
			 ],
	TransportMode:[ {dictCode:'0', dictName:'非保税区'}, //运输模式
                    {dictCode:'1', dictName:'监管仓库'},
					{dictCode:'2', dictName:'水路运输'},
					{dictCode:'3', dictName:'铁路运输'},
					{dictCode:'4', dictName:'公路运输'},
					{dictCode:'5', dictName:'航空运输'},
					{dictCode:'6', dictName:'邮件运输'}
			 ],
	RegulatoryType:[ {dictCode:'0110', dictName:'一般贸易'}//监管方式
			 ],
	LevyNature:[ {dictCode:'101', dictName:'一般征税'}, //免征性质
				{dictCode:'118', dictName:'整车征税'},
				{dictCode:'119', dictName:'零部件征税'},
				{dictCode:'201', dictName:'无偿援助'},
				{dictCode:'299', dictName:'其他法定'},
				{dictCode:'301', dictName:'特定区域'},
				{dictCode:'307', dictName:'保税区'},
				{dictCode:'399', dictName:'其他地区'}
			 ],
	Territory:[{dictCode:'44031', dictName:'深圳特区'} //境内目的地
			  ],
	CustomsType:[{dictCode:'O', dictName:'有纸报关'}, //报关单类型
				 {dictCode:'W', dictName:'无纸报关'},
				 {dictCode:'L', dictName:'有纸带清单报关'},
				 {dictCode:'D', dictName:'无纸带清单报关'},
				 {dictCode:'M', dictName:'通关无纸化'}
			  ],
	AttachfileType:[{dictCode:'1', dictName:'普通模版WT'}, //上传附件模版
               {dictCode:'2', dictName:'FWA'},
               {dictCode:'3', dictName:'MT'},
			   {dictCode:'4', dictName:'富昌模版'},
			   {dictCode:'5', dictName:'按批次模板'}
			  ],
	Warehouse:[{dictCode:'WH0001', dictName:'香港仓'}, //仓库
               {dictCode:'WH0002', dictName:'南山仓'},
               {dictCode:'WH0003', dictName:'上海仓'},
			   {dictCode:'', dictName:''}
			  ],
	Currency:[ {dictCode:'502', dictName:'美元'}, //币种
               {dictCode:'142', dictName:'人民币'},
               {dictCode:'300', dictName:'欧元'},
               {dictCode:'303', dictName:'英镑'},
               {dictCode:'110', dictName:'港币'},
			   {dictCode:'116', dictName:'日本元'},
			   {dictCode:'331', dictName:'瑞士法郎'},
			   {dictCode:'143', dictName:'台币'},
			   {dictCode:'501', dictName:'加拿大元'},
			   {dictCode:'121', dictName:'澳门元'}
			  ],
	PurType:[{dictCode:'1', dictName:'一般购汇'}, 
            {dictCode:'2', dictName:'预购汇'}],
	DealType:[{dictCode:"3_1",dictName:"FOB HONGKONG"},
			{dictCode:"1_1",dictName:"CIF SHENZHEN"},
			{dictCode:"1_6",dictName:"DDU SHENZHEN"},
			{dictCode:"1_7",dictName:"DDU SHANGHAI"},
			{dictCode:"3_14",dictName:"FOB TAIPEI(TAIWAN)"},
			{dictCode:"3_15",dictName:"FOB TAOYUAN(TW)"},
			{dictCode:"3_16",dictName:"FOB TAICHUNG(TW)"},
			{dictCode:"3_17",dictName:"CIF SHENZHEN AIRPORT"},
			{dictCode:"3_18",dictName:"FCA Arnstadt Germany"},
			{dictCode:"1_2",dictName:"CIF SHANGHAI"},
			{dictCode:"3_2",dictName:"FOB SHENZHEN"},
			{dictCode:"3_3",dictName:"FOB SHANGHAI"},
			{dictCode:"3_4",dictName:"FOB TAIWAN"},
			{dictCode:"3_5",dictName:"FOB SANTA CLARA"},
			{dictCode:"3_6",dictName:"FOB CHU NAN TSEN"},
			{dictCode:"3_7",dictName:"FOB SINGAPORE"},
			{dictCode:"3_8",dictName:"FOB PETAH TIKVA,ISRAEL"},
			{dictCode:"3_9",dictName:"FOB INCHEON ,KOREA"},
			{dictCode:"3_10",dictName:"FOB DENMARK"},
			{dictCode:"3_11",dictName:"FOB MIRIBEL(FRANCE)"},
			{dictCode:"3_12",dictName:"FOB Zurich(Switzerland)"},
			{dictCode:"3_13",dictName:"FOB Illinois,USA"},
			{dictCode:"1_3",dictName:"CIF HUANGPU"},
			{dictCode:"1_4",dictName:"EXW SUZHOU"},
			{dictCode:"1_5",dictName:"DAP SHENZHEN"},
			{dictCode:"1_8",dictName:"DDP SHENZHEN"},
			{dictCode:"1_9",dictName:"DDP SHANGHAI"},
			{dictCode:"1",dictName:"FOB BUSAN,KOREA"},
			{dictCode:"3-19",dictName:"FOB ITALY"},
			{dictCode:"3-20",dictName:"FOB CANADA"},
			{dictCode:"3-21",dictName:"FOB USA"},
			{dictCode:"2_1",dictName:"C&F"},
			{dictCode:"4_1",dictName:"C&I"},
			{dictCode:"5_1",dictName:"市场价"},
			{dictCode:"6_1",dictName:"垫仓"}],
	YesNot:[{dictCode:'0', dictName:'是'}, //是否
            {dictCode:'1', dictName:'否'}            
			],
    CustomerType:[{dictCode:'1', dictName:'代理商'}, //客户类型
             {dictCode:'2', dictName:'分销商'},
			 {dictCode:'3', dictName:'国内终端客户'},
			 {dictCode:'4', dictName:'国际终端客户'},
			 {dictCode:'5', dictName:'贸易商'},
			 {dictCode:'6', dictName:'方案商'}
			],
	 TruckBindType:[{dictCode:'0', dictName:'早班'},
             {dictCode:'1', dictName:'晚班'}
			],
	 SettlementType:[{dictCode:'1', dictName:'一般贸易进口单抬头'},//结算类型
			 {dictCode:'2', dictName:'一般贸易进口双抬头'},
			 {dictCode:'3', dictName:'仓储（增值服务）'}
			 //{dictCode:'4', dictName:'运输'},
			],
	 SettlementTypes:[{dictCode:'1', dictName:'一般贸易进口'},//结算类型
			 {dictCode:'3', dictName:'仓储（增值服务）'}
			 //{dictCode:'4', dictName:'运输'},
	        ],
	 Payee:[{dictCode:'1', dictName:'深圳市后台管理有限公司'},//收款方
			 {dictCode:'2', dictName:'广通物流有限公司'},
			 {dictCode:'3', dictName:'叶隆'}
			],
	BookType:[{dictCode:'10', dictName:'自动入账'},
             {dictCode:'20', dictName:'人工入账'}
			],
	InvoicenoticeType:[{dictCode:'1', dictName:'服务费发票'}, //开票类型
             {dictCode:'2', dictName:'VAT发票'},
			 {dictCode:'3', dictName:'运输费发票'},
			 {dictCode:'4', dictName:'仓储费发票'}
			],
	StoragelocationType:[{dictCode:'1', dictName:'货架'}, //储位类型
             {dictCode:'2', dictName:'板位'}
			],
	StoragelocationUserType:[{dictCode:'1', dictName:'临时储位'}, //储位类型
             {dictCode:'2', dictName:'私用'}
			],
	StoragelocationStatus:[{dictCode:'10', dictName:'未使用'}, //储位状态
             {dictCode:'20', dictName:'使用中'}
			],
	CustomsGrade:[{dictCode:'-1', dictName:''},
				  {dictCode:'1', dictName:'预申报'}, //申报级别
                  {dictCode:'2', dictName:'后申报'}
			],
	CreditGrade:[{dictCode:'1', dictName:'A类'}, //信用等级
             {dictCode:'2', dictName:'B类'}
			],
	Tonnage:[{dictCode:'1', dictName:'3T'}, //汽车吨位
             {dictCode:'2', dictName:'5T'},
			 {dictCode:'3', dictName:'8T'},
 			 {dictCode:'4', dictName:'10T'},
 			 {dictCode:'5', dictName:'12T'},
 			 {dictCode:'6', dictName:'20尺'},
 			 {dictCode:'7', dictName:'40尺'},
 			 {dictCode:'8', dictName:'45尺'},
 			 {dictCode:'9', dictName:'22T'}
			],
	TruckType:[{dictCode:'1', dictName:'中港车'}, //车辆类型
             {dictCode:'2', dictName:'内地车'},
			 {dictCode:'3', dictName:'港牌车'}
			],
	TruckStatus:[{dictCode:'1', dictName:'停运状态'}, //车辆状态
             {dictCode:'2', dictName:'待命状态'},
			 {dictCode:'3', dictName:'出车状态'}
			],	
	Sex:[{dictCode:'0', dictName:'男'}, //性别
             {dictCode:'1', dictName:'女'}
			],
	ShipWay:[{dictCode:'1', dictName:'快递'}, //发货方式
             {dictCode:'2', dictName:'鼎丰送货'},
             {dictCode:'3', dictName:'广通送货'},
			 {dictCode:'4', dictName:'空运早班'},
			 {dictCode:'5', dictName:'空运晚班'},
			 {dictCode:'6', dictName:'卡航运输'},
			 {dictCode:'7', dictName:'普通汽运'},
			 {dictCode:'8', dictName:'普货'},
			 {dictCode:'9', dictName:'客户自提'},
			 {dictCode:'10', dictName:'客户指定汽运'},
			 {dictCode:'11', dictName:'客户指定快递'},
			 {dictCode:'12', dictName:'客户指定空运'},
			 {dictCode:'13', dictName:'专车运输'},
			 {dictCode:'14', dictName:'顺丰陆运'}
			],
	SettlementWay:[{dictCode:'1', dictName:'日结'}, //结算方式
             {dictCode:'2', dictName:'月结'},
             {dictCode:'3', dictName:'年结'}
			],
	DeliveryType:[{dictCode:'1', dictName:'一般贸易'}, //派送类型
             {dictCode:'2', dictName:'仓储(按料)'}
			],
	PayWay:[{dictCode:'1', dictName:'到付'}, //付费方式
            {dictCode:'2', dictName:'垫付'},
            {dictCode:'3', dictName:'第三方付'},
			{dictCode:'4', dictName:'免费'}
			],
	UserGrade:[{dictCode:'1', dictName:'普通用户'}, //用户级别
             {dictCode:'2', dictName:'企业管理员'},
             {dictCode:'3', dictName:'系统管理员'},
			 {dictCode:'4', dictName:'非经营公司用户'},
			 {dictCode:'5', dictName:'用户'}
        	],
	OrderType:[{dictCode:'1', dictName:'贸易订单'}, //订单类型
               {dictCode:'2', dictName:'委托单'},
			   {dictCode:'3', dictName:'发货单'}
			],
	IsValid: [{dictCode:'0', dictName:'有效'}, //派送类型
             {dictCode:'1', dictName:'无效'}
			],
	units: [{dictCode:'007', dictName:'个'}, //关区代码
				  {dictCode:'035', dictName:'千克'},
				  {dictCode:'036', dictName:'克'},
				  {dictCode:'001', dictName:'台'},
				  {dictCode:'002', dictName:'座'},
				  {dictCode:'003', dictName:'辆'},
				  {dictCode:'008', dictName:'只'},
				  {dictCode:'009', dictName:'头'},
				  {dictCode:'011', dictName:'件'},
				  {dictCode:'012', dictName:'支'},
				  {dictCode:'018', dictName:'卷'},				  
				  {dictCode:'019', dictName:'副'},
				  {dictCode:'020', dictName:'片'},
				  {dictCode:'021', dictName:'组'}				  
				 ],
	CustomsPort: [{dictCode:'5300', dictName:'深圳海关'}, //关区代码
				  {dictCode:'5301', dictName:'皇岗海关'},
				  {dictCode:'5314', dictName:'深关邮办'},
				  {dictCode:'5317', dictName:'深关机场'},
				  {dictCode:'5320', dictName:'文锦渡关'},
				  {dictCode:'5321', dictName:'福保税关'},
				  {dictCode:'5326', dictName:'深数统处'},
				  {dictCode:'5345', dictName:'深圳湾关'},
				  {dictCode:'5100', dictName:'广州海关'},
				  {dictCode:'5141', dictName:'广州机场'},
				  {dictCode:'5200', dictName:'黄埔关区'},
				  {dictCode:'2200', dictName:'上海海关'},
				  {dictCode:'2202', dictName:'吴淞海关'},
				  {dictCode:'2203', dictName:'沪机场关'},
				  {dictCode:'2208', dictName:'宝山海关'},
				  {dictCode:'2210', dictName:'浦东海关'},
				  {dictCode:'2216', dictName:'浦机综保'},
				  {dictCode:'2218', dictName:'外高桥关'}
				 ],
	Port: [{dictCode:'110', dictName:'香港'}, //港口代码
		   {dictCode:'133', dictName:'韩国'},
		   {dictCode:'0', dictName:'上海'},
		   {dictCode:'132', dictName:'新加坡'},
		   {dictCode:'143', dictName:'台湾省'},
		   {dictCode:'305', dictName:'法国'},
		   {dictCode:'3416', dictName:'台中'},
		   {dictCode:'1482', dictName:'仁川'},		  
		   {dictCode:'7302', dictName:'丹麦'},
		   {dictCode:'1804', dictName:'哥本哈根'},
		   {dictCode:'3151', dictName:'长滩'},
		   {dictCode:'1', dictName:'查塔努加机场'},
		   {dictCode:'2', dictName:'GUMI KOREA'},
		   {dictCode:'3', dictName:'Columbia USA'},
		   {dictCode:'4', dictName:'AJAX CANADA'},
		   {dictCode:'5', dictName:'深圳'},
		   {dictCode:'6', dictName:'ROME ITALY'},
		   {dictCode:'7', dictName:'日本东京'}
		  ],
  FeeType: [{dictCode:'1', dictName:'服务费/增值税/关税'}, //计费种类
				  {dictCode:'4', dictName:'商检专车运输费'},
				  {dictCode:'5', dictName:'消毒费'},
				  {dictCode:'6', dictName:'运输保险费'},
				  {dictCode:'7', dictName:'付汇手续费'},
				  {dictCode:'8', dictName:'滞纳金'},
				  {dictCode:'9', dictName:'商检费'},
				  {dictCode:'10', dictName:'承兑汇票贴息费'},
				  {dictCode:'11', dictName:'清关费'},
				  {dictCode:'12', dictName:'国际快递费'},
				  {dictCode:'13', dictName:'代办证件费'},
				  {dictCode:'14', dictName:'国内快递费用'},
				  {dictCode:'17', dictName:'打单费'},
				  {dictCode:'50', dictName:'入仓费'},
				  {dictCode:'51', dictName:'出仓费'}
				 ],
  CheckStandardsType:[{dictCode:'1', dictName:'对象'}, //验核类型
             {dictCode:'2', dictName:'要素'}
			],
  TransFeeSettingStatus:[{dictCode:'10', dictName:'待审核'}, //验核类型
             {dictCode:'20', dictName:'已审核'}
			],
  RateTimePoint:[{dictCode:'1', dictName:'报关当天'}, //汇率时间点
             {dictCode:'2', dictName:'购汇当天'},
			 {dictCode:'3', dictName:'付汇当天'},
			 {dictCode:'4', dictName:'海关牌价'}
			],
  SpecialFlag:[{dictCode:'10', dictName:'无'}, //特殊标识别
             {dictCode:'20', dictName:'商检(A)'},
			 {dictCode:'30', dictName:'商检(AL)'},
			 {dictCode:'40', dictName:'商检(AM)'},
			 {dictCode:'50', dictName:'商检(AML)'},
			 {dictCode:'60', dictName:'机电证(O)'},
			 {dictCode:'70', dictName:'消毒'}
			],
  LinkManType:[{dictCode:'1', dictName:'窗口联系人'}, //特殊标识别
             {dictCode:'2', dictName:'财务联系人'},
			 {dictCode:'3', dictName:'其他联系人'}
			],
  cusBelongTo:[{dictCode:'1', dictName:'公司客户'}, //客户归属 
             {dictCode:'2', dictName:'个人客户'}
			],
  TruckBindPurpose:[{dictCode:'1', dictName:'拼车'}, //客户归属 
             {dictCode:'2', dictName:'专车'}
			],
  BackNoStatus:[{dictCode:'2', dictName:'正常'},//回单状态
			   {dictCode:'3', dictName:'异常'}
			],
  FunctionUse:[{dictCode:'1', dictName:'一仓'}, //储位的功能用途 
			   {dictCode:'2', dictName:'二仓'},
			   {dictCode:'3', dictName:'待处理仓'}
			],
  HsbcRateType:[{dictCode:'1', dictName:'买入价'}, //储位的功能用途 
				  {dictCode:'2', dictName:'卖出价'}
			],
  ValueAddedType:[{dictCode:'1', dictName:'仓库租赁及库存管理'}, //仓储费用分类
			   {dictCode:'2', dictName:'提货/送货费用'},
			   {dictCode:'3', dictName:'杂费'},
			   {dictCode:'4', dictName:'增值服务'}
			],
  UserType:[{dictCode:'1', dictName:'员工'}, //仓储费用分类
			{dictCode:'2', dictName:'会员'}
			//{dictCode:'3', dictName:'收货公司员工用户'},
			//{dictCode:'4', dictName:'运营公司供应商'},
			//{dictCode:'5', dictName:'货运公司员工用户'}
			],
  ManageModel:[{dictCode:'1', dictName:'按料管理'}, //客户来料管理模式
			   {dictCode:'2', dictName:'按批管理'}
			],
  PositionLevel:[{dictCode:'1', dictName:'1级'}, //职位级别
			   {dictCode:'2', dictName:'2级'},
			   {dictCode:'3', dictName:'3级'},
			   {dictCode:'4', dictName:'4级'}
			],
  QpPostFlag:[ {dictCode:'1', dictName:'否'}, //客户来料管理模式
			   {dictCode:'2', dictName:'是'}
			 ],
  SettlementState:[{dictCode:'1', dictName:'已结算'},
    			   {dictCode:'2', dictName:'未结算'}
	 		],
  Citys:[{dictCode:'SZ', dictName:'深圳'},
                   {dictCode:'GZ', dictName:'广州'},
                   {dictCode:'GZ', dictName:'北京'},
                   {dictCode:'GZ', dictName:'上海'}
  			],
  ServiceParamters:[{dictCode:'AV0004-4', dictName:'入仓费用(按箱收)'},
			          		{dictCode:'AV0004-7', dictName:'入仓费用(按板收)'},
			          		{dictCode:'AV0005-4', dictName:'出仓费用(按箱收)'},
			          		{dictCode:'AV0005-7', dictName:'出仓费用(按板收)'},
			          		{dictCode:'AV0032-1', dictName:'日租(按日收)'},
			          		{dictCode:'AV0034-1', dictName:'月租(按休息日计收)'},
			          		{dictCode:'AV0001-1', dictName:'送货费用(送货费用)'},
			          		{dictCode:'AV0002-1', dictName:'提货费用(提货费用)'},
			          		{dictCode:'AV0003-1', dictName:'提货后送货费用(提货后送货费用)'},
			          		{dictCode:'AV0021-2', dictName:'代付运费加操作费(实际代垫运费)'},
			          		{dictCode:'AV0022-2', dictName:'香港清关费(香港清关费)'},
			          		{dictCode:'AV0025-1', dictName:'入仓登记费(入仓登记费)'},
			          		{dictCode:'AV0026-1', dictName:'隧道费(隧道费)'},
			          		{dictCode:'AV0027-1', dictName:'停车费(停车费)'},
			          		{dictCode:'AV0029-1', dictName:'仓储保险费(按货值比例收)'},
			          		{dictCode:'AV0030-1', dictName:'机场码加收费(按机场码头收)'},
			          		{dictCode:'AV0031-1', dictName:'加急费(快速加急费)'},
			          		{dictCode:'AV0033-1', dictName:'加班费(按工作日收)'},
			          		{dictCode:'AV0033-2', dictName:'加班费(按休息日计收)'},
			          		{dictCode:'AV0035-1', dictName:'快递操作费(快递操作费)'},
			          		{dictCode:'AV0036-1', dictName:'其它费用(其它费用)'},
			          		{dictCode:'AV0006-1', dictName:'代装/缷货费(按箱收)'},
			          		{dictCode:'AV0006-2', dictName:'代装/缷货费(按板收)'},
			          		{dictCode:'AV0006-3', dictName:'代装/缷货费(按斤收)'},
			          		{dictCode:'AV0007-1', dictName:'开箱取单费(按箱收)'},
			          		{dictCode:'AV0008-2', dictName:'开箱验货费(按盘(包)收)'},
			          		{dictCode:'AV0009-2', dictName:'出仓拣料费(按盘(包)收)'},
			          		{dictCode:'AV0010-1', dictName:'纸箱费(按小箱收)'},
			          		{dictCode:'AV0010-2', dictName:'纸箱费(按中箱收)'},
			          		{dictCode:'AV0010-3', dictName:'纸箱费(按大箱收)'},
			          		{dictCode:'AV0011-2', dictName:'清点最小颗粒(按PCS数量收)'},
			          		{dictCode:'AV0012-1', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按拍照收)'},
			          		{dictCode:'AV0012-2', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按传真收)'},
			          		{dictCode:'AV0012-3', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按扫描收)'},
			          		{dictCode:'AV0012-4', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按打印收)'},
			          		{dictCode:'AV0012-5', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按磅重收)'},
			          		{dictCode:'AV0012-6', dictName:'产品拍照/传真/扫描/打印/磅重量尺寸(按尺寸收)'},
			          		{dictCode:'AV0013-1', dictName:'标签制作费(按款收)'},
			          		{dictCode:'AV0014-2', dictName:'撕标/贴标签(按张收)'},
			          		{dictCode:'AV0015-1', dictName:'单证制作费(按票数收)'},
			          		{dictCode:'AV0016-1', dictName:'打包装膜(按箱收)'},
			          		{dictCode:'AV0017-1', dictName:'打木架(打板)(按个数收)'},
			          		{dictCode:'AV0018-1', dictName:'代收支票(随货代收)'},
			          		{dictCode:'AV0018-2', dictName:'代收支票(按单取支票)'},
			          		{dictCode:'AV0019-1', dictName:'代入支票(代入支票操作费)'},
			          		{dictCode:'AV0020-2', dictName:'代收&代付货款(按代收费用收)'},
			          		{dictCode:'AV0020-3', dictName:'代收&代付货款(按代付费用收)'},
			          		{dictCode:'AV0023-1', dictName:'代办单证费(按票数收取)'},
			          		{dictCode:'AV0024-2', dictName:'真空包装费(按袋收)'}]
 
};

