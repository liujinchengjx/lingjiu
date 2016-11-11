package com.liu.qinziyou.dao.business;


import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
public interface ICustomerChargesVariablesDao  extends IBaseDAO{
  
    /**
     * 根据客户获取 根据代码值
     * @param scode
     * @return
     * @throws Exception
     */
    public Double getVariableValueByCode(Integer customerId,String scode)throws Exception;
    public String getVariableNameByCode(Integer customerId,String scode)throws Exception;
    /**
     * 根据客户获取 根据代码值地图
     * @param scode
     * @return
     * @throws Exception
     */
    public Map<String, Double> getVariableValueMap(Integer customerId,String preCode)throws Exception;
    
    /**
     * 判断该编码是否有效
     * @param customerId
     * @param scode
     * @return
     * @throws Exception
     */
    public boolean isValidCode(Integer customerId,String scode)throws Exception;
    
    public boolean isEnable(Integer customerId,String scode)throws Exception;
    /**
     * 
     * @param id
     * @param chargesValue
     * @return
     * @throws Exception
     */
    public int updateCustomerChargesVariables(Integer id,Double chargesValue,String description) throws Exception;
    /**
     * 删除cusId 对应的费用关联记录
     * @param cusId
     * @return
     * @throws Exception
     */
    public  boolean deleteCustomerChargesVariablesByCusId(int customerId) throws Exception;
    
    public  PageHelper searchCusInCharges(String searchValue,Integer customerId,
    		int currentPage, int pageSize)throws Exception;
}
