package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.business.Customer;
import com.liu.qinziyou.exception.ServiceException;


public interface ICustomerDao  extends IBaseDAO{
	
	 public PageHelper searchCustomer(String cname,String ename,String respPerson,String ccode,Integer opType,Integer status,
			  String businessType, boolean isFilter, int currentPage, int pageSize) throws Exception;
	 
	 public Customer getCustomer(Integer companyId) throws Exception;
    /**
     * 审核客户
     * @param id 客户id
     * @param state 状态值
     * @return
     * @throws ServiceException
     */
    public  boolean updateCustomerStatus(Integer id,Integer status) throws Exception;
    
    public  boolean updateDiscountRateStatus(Integer companyId,Integer discountRateStatus) throws Exception;
    /**
     * 
     * @param companyId
     * @param rentDiscountRate  仓库租赁及库存管理费用折扣率
     * @param dnDiscountRate  提货/送货费用折扣率
     * @param mixDiscountRate  杂费（隧道、停车、转单、入场）费用折扣率
     * @param addDiscountRate 增值服务费用折扣率
     * @return
     * @throws Exception
     */
    public  boolean updateDiscountRate(Integer companyId,Double rentDiscountRate,
    		Double dnDiscountRate,Double mixDiscountRate,Double addDiscountRate) throws Exception;
    public  boolean updatePosition(Integer customerId,String positionCode) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(IBean baseBean) throws Exception;
    
    /**
     * 根据权限查询用户
     * @param positionCode
     * @return
     * @throws ServiceException
     */
    public PageHelper searchCustomer(String positionCode,int currentPage, int pageSize) throws Exception;
}
