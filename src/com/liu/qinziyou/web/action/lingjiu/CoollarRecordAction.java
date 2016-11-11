package com.liu.qinziyou.web.action.lingjiu;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.report.excel.DataSet;
import com.liu.qinziyou.common.report.excel.ExcelUtil;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.common.util.LoginInfoUtil;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.lingju.CoollarRecord;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.lingjiu.ICoollarService;
import com.liu.qinziyou.services.systemmanager.IUserService;
import com.liu.qinziyou.web.action.BaseAction;

public class CoollarRecordAction extends BaseAction {
	protected static final Log log = LogFactory.getLog(CoollarRecordAction.class);
	private String searchValue;
	private String memberCode;
	private Integer state;
	private Integer fleightState;
	
	private CoollarRecord coollarRecord;
	// 搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean search() {
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			PageHelper ph = new PageHelper();
			ph = coollarService.searchCoollarRecord(searchValue,memberCode,state, fleightState, start, limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索列表失败===" + e.getMessage());
		}
		return msg;
	}
	
	// 搜索
		@InterceptorNames({ @InterceptorName("loginInterceptor") })
		public void searchforExport() {
			MsgBean msg = new MsgBean();
			try {
				ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
				PageHelper ph = new PageHelper();
				ph = coollarService.searchCoollarRecord(searchValue,memberCode,state, fleightState, start, 10000);
				super.getResponse().setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("领酒订单.xls", "UTF-8")); 
				writeExcel(ph.getRows(),super.getResponse().getOutputStream());
			} catch(IOException e){
				e.printStackTrace(); 
			}
			catch (ServiceException e) {
				msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
				log.error("搜索列表失败===" + e.getMessage());
			}
		}
		
		private void writeExcel(List<BaseBean> listBean,OutputStream out){
			
			String[] headers={"订单编号","领用时间","会员编号","商品名称","商品描述","数量","领用单价","物流费用","总费用","收货人","收货人电话","省","市","区县","地址(街道)","详细地址","订单状态","物流状态","物流公司","物流单号"};
			
			List<String[]> list = new ArrayList<String[]>();
			for(BaseBean baseBean:listBean){
				CoollarRecord cr = (CoollarRecord)baseBean;
				List<String> values = new ArrayList<String>();
				values.add(cr.getOrderCode());
				values.add(cr.getCoollarTime().toString());
				values.add(cr.getMemberCode());
				values.add(cr.getCoollarGoods().getGoodsName());
				values.add(cr.getCoollarGoods().getGoodsDesc());
				values.add(cr.getCoollarQty().toString());
				values.add(new Double((cr.getCoollarPrice()/100)).toString());
				values.add(new Double((cr.getLogisticsFee()/100)).toString());
				values.add(new Double((cr.getTotalFee()/100)).toString());
				values.add(cr.getReceiverName());
				values.add(cr.getReceiverMobile());
				values.add(cr.getProvinceName());
				values.add(cr.getCityName());
				values.add(cr.getAreaName());
				values.add(cr.getAddress());
				values.add(cr.getFullAddress());
				values.add(cr.getStateName());
				values.add(cr.getFleightStateName());
				values.add(cr.getFleightCompany());
				values.add(cr.getFleightNumber());
				String[] sValues = new String[values.size()];
				sValues = (String[])values.toArray(sValues);
				list.add(sValues);
				
			}
			DataSet ds = new DataSet(headers,null,list,null);
			try{
				ExcelUtil.writeExcelPOI(out, ds);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
	    
			
	// 搜索
		@InterceptorNames({ @InterceptorName("loginInterceptor") })
		public MsgBean searchMemberOrder() {
			MsgBean msg = new MsgBean();
			try {
				Integer loginUserId = LoginInfoUtil.getInstance().getUserId(super.getRequest());
				IUserService userService = (IUserService) BeanLoader.getWebBean("userService");
				User user = userService.getUserInfo(loginUserId);
				ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
				PageHelper ph = new PageHelper();
				ph = coollarService.searchCoollarRecord(searchValue,user.getUserName(),state, fleightState, start, 100);
				msg.setOutRows(outRows);
				msg.setRows(ph.getRows());
				msg.setTotal(ph.getTotal());
				msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
			} catch (ServiceException e) {
				msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
				log.error("搜索列表失败===" + e.getMessage());
			}
			return msg;
		}
	
	public MsgBean searchTop() {
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			PageHelper ph = new PageHelper();
			ph = coollarService.searchCoollarRecord(searchValue,null,20, 20, start, 5);
			for(BaseBean baseBean : ph.getRows()){
				CoollarRecord cr = (CoollarRecord)baseBean;
				cr.setReceiverName(cr.getReceiverName().substring(0,1)+"先生");
				cr.setReceiverMobile(StringUtil.getPhone(cr.getReceiverMobile()));
			}
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索列表失败===" + e.getMessage());
		}
		return msg;
	}
	
	public MsgBean getByReceiverMobile() {
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			List<CoollarRecord> coollarRecords = coollarService.getByReceiverMobile(searchValue);
			msg.setRows(coollarRecords);
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("获取领用信息失败===" + e.getMessage());
		}
		return msg;
	}
	
	public MsgBean getById() {
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			CoollarRecord coollarRecord = (CoollarRecord)coollarService.getBeanById(CoollarRecord.class, new Integer(searchValue));
			msg.setEntity(coollarRecord);
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("获取领用信息失败===" + e.getMessage());
		}
		return msg;
	}
	
	public MsgBean add() {
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			coollarService.addCoollarRecord(coollarRecord);
			msg.setOutRows(outRows);
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("领用失败===" + e.getMessage());
		}
		return msg;
	}
	
	public MsgBean changeState(){
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			coollarService.changeState(coollarRecord.getState(), coollarRecord.getId());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("更新状态失败===" + e.getMessage());
		}
		return msg;
	}
	
	public MsgBean fleight(){
		MsgBean msg = new MsgBean();
		try {
			ICoollarService coollarService = (ICoollarService) BeanLoader.getWebBean("coollarService");
			coollarService.fleight(coollarRecord.getFleightCompany(),coollarRecord.getFleightNumber(), coollarRecord.getId());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (ServiceException e) {
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("发货失败===" + e.getMessage());
		}
		return msg;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFleightState() {
		return fleightState;
	}
	public void setFleightState(Integer fleightState) {
		this.fleightState = fleightState;
	}
	public CoollarRecord getCoollarRecord() {
		return coollarRecord;
	}
	public void setCoollarRecord(CoollarRecord coollarRecord) {
		this.coollarRecord = coollarRecord;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	

}
