package com.liu.qinziyou.services.baseinfo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IPayeeDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.business.Payee;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.baseinfo.IPayeeService;

public class PayeeServiceImpl extends AbstractBaseService implements IPayeeService{
	protected static final Log log = LogFactory.getLog(PayeeServiceImpl.class);
	private IPayeeDao payeeDao;	
	/**
	 * 根据ID获取
	 * @param id
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public IBean getBeanById(Class cls,Integer id)  throws ServiceException{
		try{
			Payee p=(Payee)this.getBaseDAO().findById(cls, id);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取收款人时发生异常====" + e.getMessage());
			throw new ServiceException("获取收款人时时失败");
		}
	}

	public Integer add(IBean baseBean) throws ServiceException{
		Integer id=0;
		try{
			Payee p = (Payee)baseBean;
			id=payeeDao.save(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存收款人时发生异常===="+e.getMessage());
			throw new ServiceException("保存收款人时发生异常");
		}
		return id;
	}
	
	public void update(IBean baseBean) throws ServiceException{
		try{
			Payee t = (Payee)baseBean;
			payeeDao.update(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新货车时发生异常===="+e.getMessage());
			throw new ServiceException("更新货车时发生异常");
		}
	}
  
    public  PageHelper searchPayee(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	PageHelper ph=this.payeeDao.searchPayee(searchValue,currentPage,pageSize);
	    	return ph;
	    }catch (Exception e){
			e.printStackTrace();
			log.error("搜索收款人列表分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索收款人时表分页发生异常");
		}
    }

	public IPayeeDao getPayeeDao() {
		return payeeDao;
	}

	public void setPayeeDao(IPayeeDao payeeDao) {
		this.payeeDao = payeeDao;
	}    

	
}
