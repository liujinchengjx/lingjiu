package com.liu.qinziyou.services.lingjiu.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.DateTool;
import com.liu.qinziyou.common.util.SystemUtilities;
import com.liu.qinziyou.dao.lingju.ICoollarGoodsDao;
import com.liu.qinziyou.dao.lingju.ICoollarMemberDao;
import com.liu.qinziyou.dao.lingju.ICoollarRecordDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.lingju.CoollarGoods;
import com.liu.qinziyou.entity.lingju.CoollarRecord;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.lingjiu.ICoollarService;

public class CoollarServiceImpl  extends AbstractBaseService implements ICoollarService{
	protected static final Log log = LogFactory.getLog(CoollarServiceImpl.class);
	private ICoollarRecordDao coollarRecordDao;
	private ICoollarGoodsDao coollarGoodsDao;
	private ICoollarMemberDao coollarMemberDao;
	@Override
	public PageHelper searchCoollarGoods(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageHelper searchCoollarMember(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageHelper searchCoollarRecord(String searchValue,String memberCode,Integer state, Integer fleightState,int currentPage, int pageSize)
			throws ServiceException {

		try {
			PageHelper ph = this.coollarRecordDao.searchCoollarRecord(searchValue, memberCode,state, 
					fleightState, currentPage, pageSize);
			for(BaseBean bean :ph.getRows()){
				CoollarRecord coollarRecord = (CoollarRecord)bean;
				CoollarGoods coollarGoods = (CoollarGoods)coollarGoodsDao.
						findById(CoollarGoods.class, coollarRecord.getGoodsId());
				coollarRecord.setCoollarGoods(coollarGoods);
				
			}
			
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("搜索领用记录列表分页信息时发生异常====" + e.getMessage());
			throw new ServiceException("搜索领用记录分页信息时发生异常");
		}
	
	}
	
	

	
	
	public void addCoollarRecord(CoollarRecord coollarRecord) throws ServiceException {
		try{
			coollarRecord.setCoollarTime(new Timestamp(new Date().getTime()));
			if(SystemUtilities.isBlankStr(coollarRecord.getProvinceName())
					||"请选择".equals(coollarRecord.getProvinceName())){
				throw new ServiceException("请选择所在地区");
			}
			String[] areas = coollarRecord.getProvinceName().split(" ");
			String provinceName=areas[0];
			String cityName=areas[1];
			String areaName=areas[1];
			if(areas.length>=3){
				areaName=areas[2];
			}
			coollarRecord.setProvinceName(provinceName);
			coollarRecord.setCityName(cityName);
			coollarRecord.setAreaName(areaName);
			coollarRecord.setState(10);
			coollarRecord.setFleightState(10);
			coollarRecord.setFullAddress(provinceName+cityName+areaName+coollarRecord.getAddress());
			
			
			//从领用商品配置表中拿到活动信息
			CoollarGoods coollarGoods = (CoollarGoods)this.getCoollarGoodsDao().findById(CoollarGoods.class, coollarRecord.getGoodsId());
			if(null ==coollarGoods ){
				throw new ServiceException("没有此活动配置信息");
			}
			
			List users = this.getCoollarRecordDao().findByProperty(User.class.newInstance(), "userName", coollarRecord.getMemberCode());
			if(null == users || users.size()<1){
				throw new ServiceException("领取码 "+coollarRecord.getMemberCode()+" 不存在");
			}
			
			if((coollarGoods.getCoolaredCnt()+coollarRecord.getCoollarQty())>coollarGoods.getStockCnt()){
				throw new ServiceException("此商品已经领用完或库存不足，请关注下次活动");
			}
			coollarRecord.setGoodsName(coollarGoods.getGoodsName());
			coollarRecord.setCoollarPrice(coollarGoods.getCoollarPrice());
			coollarRecord.setLogisticsFee(coollarGoods.getLogisticsFee());
			if(provinceName.equals("西藏") || provinceName.equals("青海") || provinceName.equals("新疆") || provinceName.equals("内蒙古")){
				coollarRecord.setLogisticsFee(coollarRecord.getLogisticsFee() + 1000);
			}
			coollarRecord.setActivityCode(coollarGoods.getActivityCode());
			coollarRecord.setTotalFee((coollarGoods.getCoollarPrice()+coollarRecord.getLogisticsFee())*coollarRecord.getCoollarQty());
			coollarRecord.setPrize(coollarGoods.getPrize()*coollarRecord.getCoollarQty());
			String maxOrderCodeOfCurrentDay = this.getCoollarRecordDao().getMaxOrderCodeOfCurrentDay();
			coollarRecord.setOrderCode(SystemUtilities.genOrderCode(maxOrderCodeOfCurrentDay));//订单号
			
			int updateResult = this.getCoollarGoodsDao().updateCoollaredQty(coollarRecord.getCoollarQty(), coollarGoods.getCoolaredCnt(), coollarRecord.getGoodsId(),1);
			if(updateResult==0){
				throw new ServiceException("更新已领用数异常");
			}
			
			this.getCoollarRecordDao().save(coollarRecord);
		
		}catch(ServiceException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			log.error("保存领用记录时发生异常====" + e.getMessage());
			throw new ServiceException("保存领用记录时发生异常");
		}
		
	}
	
	@Override
	public List<CoollarRecord> getByReceiverMobile(String mobile)
			throws ServiceException {
		 List<CoollarRecord> coollarRecords = null;
		try{
			coollarRecords = this.getCoollarRecordDao().findByProperty(CoollarRecord.class.newInstance(), "receiverMobile", mobile);
			
		}catch(ServiceException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			log.error("根据手机号码获取领用记录时发生异常====" + e.getMessage());
			throw new ServiceException("根据手机号码获取领用记录时发生异常");
		}
		return coollarRecords;
	}
	
	

	@Override
	public void changeState(Integer state, Integer id) throws ServiceException {
		try{
			CoollarRecord coollarRecord = (CoollarRecord)this.getCoollarRecordDao().findById(CoollarRecord.class, id);
			if(null == coollarRecord){
				throw new ServiceException("没有对应的领用订单");
			}
			if(state.intValue()==1 && coollarRecord.getState().intValue()!=10){
				throw new ServiceException("订单状态有误，取消失败");
			}
			
			if(state.intValue()==20 && coollarRecord.getState().intValue()!=10){
				throw new ServiceException("订单状态有误，确认失败");
			}
			coollarRecord.setState(state);
			this.getCoollarRecordDao().changeState(state, id);
			
		}catch(ServiceException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			log.error("更新状态时发生异常====" + e.getMessage());
			throw new ServiceException("更新状态时发生异常");
		}
		
	}

	@Override
	public void fleight(String fleightCompany,String fleightNumber,Integer id) throws ServiceException {
		try{
			CoollarRecord coollarRecord = (CoollarRecord)this.getCoollarRecordDao().findById(CoollarRecord.class, id);
			if(null == coollarRecord){
				throw new ServiceException("没有对应的领用订单");
			}
			
			if(coollarRecord.getState().intValue()!=20 || coollarRecord.getFleightState().intValue()!=10){
				throw new ServiceException("订单状态有误，确认失败");
			}
			coollarRecord.setState(20);
			coollarRecord.setFleightCompany(fleightCompany);
			coollarRecord.setFleightNumber(fleightNumber);
			this.getCoollarRecordDao().fleight(fleightCompany, fleightNumber, id);
			
		}catch(ServiceException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			log.error("发运异常====" + e.getMessage());
			throw new ServiceException("发运异常");
		}
	}

	private static String genOrderCode() throws ServiceException {
		//String maxOrderCodeOfCurrentDay = this.getCoollarRecordDao().getMaxOrderCodeOfCurrentDay();
		String maxOrderCodeOfCurrentDay = "1606020001";
		String order = "";
		if(maxOrderCodeOfCurrentDay == null){//
			order = "1";
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
    
	public ICoollarRecordDao getCoollarRecordDao() {
		return coollarRecordDao;
	}

	public void setCoollarRecordDao(ICoollarRecordDao coollarRecordDao) {
		this.coollarRecordDao = coollarRecordDao;
	}

	public ICoollarGoodsDao getCoollarGoodsDao() {
		return coollarGoodsDao;
	}

	public void setCoollarGoodsDao(ICoollarGoodsDao coollarGoodsDao) {
		this.coollarGoodsDao = coollarGoodsDao;
	}

	public ICoollarMemberDao getCoollarMemberDao() {
		return coollarMemberDao;
	}

	public void setCoollarMemberDao(ICoollarMemberDao coollarMemberDao) {
		this.coollarMemberDao = coollarMemberDao;
	}
	
	
	
}
