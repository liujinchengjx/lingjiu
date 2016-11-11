package com.liu.qinziyou.services.baseinfo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IOriginCountryDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.baseinfo.IOriginCountryService;

public class OriginCountryServiceImpl extends AbstractBaseService implements IOriginCountryService{
	protected static final Log log = LogFactory.getLog(OriginCountryServiceImpl.class);
	private IOriginCountryDao originCountryDao;
	
	
	public Integer add(IBean baseBean) throws ServiceException{
		try{
			BaseBean oc = (BaseBean)baseBean;
			if(originCountryDao.checkForDuplicate(oc)){
				throw new ServiceException("原产国重复");
			}
			Integer id = originCountryDao.save(oc);
			return id;
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存原产国失败==="+e.getMessage());
			throw new ServiceException("保存原产国失败");
		}
	}
	
	public void update(IBean baseBean) throws ServiceException {
		try{
			BaseBean oc = (BaseBean)baseBean;
			if(originCountryDao.checkForDuplicate(oc)){
				throw new ServiceException("原产国重复");
			}
			originCountryDao.update(oc);
		}catch (ServiceException e){
			throw e;
		}catch(Exception e){
			log.error("更新原产国失败==="+e.getMessage());
			throw new ServiceException("更新原产国失败");
		}
	}
    public  void updateDiscountOrEpidemicFlag(Integer originCountryId,Integer discountFlag,Integer epidemicFlag) 
               throws ServiceException {
	    try{
	    	originCountryDao.updateDiscountOrEpidemicFlag(originCountryId, discountFlag, epidemicFlag);
	    }catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新原产国（最惠国/疫区）失败==="+e.getMessage());
			throw new ServiceException("更新原产国（最惠国/疫区）失败"+e.getMessage());
		}
    }
    
    /**
     * 分页查询原产国
     * @return
     * @throws Exception
     */
    public  PageHelper searchOriginCountry(String searchValue,int discountFlag,int epidemicFlag,
    		int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.originCountryDao.searchOriginCountry(searchValue,discountFlag,epidemicFlag,currentPage, pageSize);
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("分页查询原产国失败==="+e.getMessage());
    		throw new ServiceException("分页查询原产国失败");
    	}
    	
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
	public IOriginCountryDao getOriginCountryDao() {
		return originCountryDao;
	}

	public void setOriginCountryDao(IOriginCountryDao originCountryDao) {
		this.originCountryDao = originCountryDao;
	}    
}
