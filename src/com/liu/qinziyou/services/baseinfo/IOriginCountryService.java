package com.liu.qinziyou.services.baseinfo;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IOriginCountryService extends IBaseService{
    
    public  void updateDiscountOrEpidemicFlag(Integer originCountryId,Integer discountFlag,Integer epidemicFlag) 
    		throws ServiceException ;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchOriginCountry(String searchValue,int discountFlag,int epidemicFlag,
    		int currentPage, int pageSize) throws ServiceException;      
}
