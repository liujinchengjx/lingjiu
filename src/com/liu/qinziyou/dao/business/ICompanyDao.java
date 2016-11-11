package com.liu.qinziyou.dao.business;

import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.Company;

public interface ICompanyDao  extends IBaseDAO{	
	
	public int updateCompanyStatus(Integer companyId,Integer status)throws Exception;
	public int deleteCompany(Integer companyId)throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    /**
     * 获取customer表最大的ccode编码
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode(Integer type) throws Exception;
    
    /**
     * 查询供方
     * @param searchValue
     * @param buyerCompanyId
     * @param companyType
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public  PageHelper searchSeller(String cname,String ename,String pinyin,
    		String ccode,Integer buyerCompanyId,Integer companyType,
    		int currentPage, int pageSize) throws Exception;
    
   /**
    * 查询需方
    * @param searchValue
    * @param sellerCompanyId
    * @param companyType
    * @param currentPage
    * @param pageSize
    * @return
    * @throws Exception
    */
    public  PageHelper searchBuyer(String searchValue,Integer sellerCompanyId,Integer companyType,
    		int currentPage, int pageSize) throws Exception;
    /**
     * 获取公司的名字
     * @param companyId
     * @return
     * @throws Exception
     */
    public String getCompanyName(Integer companyId) throws Exception;
    
    /**
     * 获取公司的名字
     * @param companyId
     * @return
     * @throws Exception
     */
    public Map<Integer, String> getCompanyNames(String companyIds) throws Exception;
    /**
     *  根据customOrder 的id获取一个供方公司，或是需方公司
     * @param customOrderId
     * @param sellerBuyer 1 供方，2 需方 
     * @return
     * @throws Exception
     */
    public Company getCompanyByCustomOrderId(Integer customOrderId,Integer sellerBuyer) throws Exception;
    /**
     * 查询公司
     * @param searchValue
     * @param companyType
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public  PageHelper searchCompany(String searchValue,Integer companyId,Integer companyType,Integer status,int currentPage, int pageSize) throws Exception;
        
    /**
     *  是否存在该公司 存在的话直接返回改公司的id,否则的话返回 -1
     * @param companyName
     * @return
     * @throws Exception
     */
    public Integer existCompany(String companyName) throws Exception;
    
    public PageHelper searchTransCompany(Integer opCompanyId,
        	String searchValue, Integer companyType,int currentPage, int pageSize) throws Exception;
    
    public PageHelper searchCustomerSupplier(Integer customerCompanyId,String searchValue,Integer status,
			int currentPage, int pageSize ) throws Exception;
    
    public PageHelper searchOpSuppler(Integer customerCompanyId,String searchValue,
    		int currentPage, int pageSize ) throws Exception;
    
    /**
     * 查询关联公司
     * @param customerCompanyId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageHelper searchAssociated(Integer customerCompanyId,
    		int currentPage, int pageSize ) throws Exception;
    /**
     * 删除关联公司
     * @param customerCompanyId
     * @param associatedId
     * @return
     * @throws Exception
     */
    public int deleteAssociated(Integer customerCompanyId,Integer associatedId)throws Exception;
    
    
    /**
     * 删除供应商银行信息
     * */
    public int deletSpplerBankInfo(Integer id);
    
    /**
     * 
     * 查询银行信息
     * */
    public List searchSupplerList(Integer supId);
    
    /**
     * 根据客户ID与银行账户信息判断是个重复
     * 
     * */
    public int checkBankInfo(Integer spuId,String bankAmount,String bankUserName);
}
