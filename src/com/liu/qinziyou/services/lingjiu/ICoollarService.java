package com.liu.qinziyou.services.lingjiu;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.lingju.CoollarRecord;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface ICoollarService extends IBaseService{
	 public  PageHelper searchCoollarGoods(String searchValue ,Integer state,int currentPage, int pageSize) throws ServiceException;
	 
	 public  PageHelper searchCoollarMember(String searchValue,Integer state,int currentPage, int pageSize) throws ServiceException;
	 
	 public  PageHelper searchCoollarRecord(String searchValue,String memberCode,Integer state,Integer fleightState,int currentPage, int pageSize) throws ServiceException;
	 
	 public void addCoollarRecord(CoollarRecord coollarRecord) throws ServiceException;
	 
	 
	 public List<CoollarRecord> getByReceiverMobile(String mobile) throws ServiceException;
	 
	 public void changeState(Integer state,Integer id) throws ServiceException;
	 
	 public void fleight(String fleightCompany,String fleightNumber,Integer id) throws ServiceException;
	 
	 
	 
}
