package com.liu.qinziyou.dao.business;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.ValueAddedServices;
import com.liu.qinziyou.entity.business.ValueAddedServicesParam;

public interface IValueAddedServicesDao  extends IBaseDAO{
   
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchValueAddedServices(String searchValue,Integer ifShow,
    		int currentPage, int pageSize) throws Exception;
    public  ValueAddedServices getValueAddedServicesByCode(String scode) throws Exception;
    public  Double getVariableValue(String scode) throws Exception;
    
    public  List<String> advNameList() throws Exception; //没有选中的增值服务Items name
    
    /**
     * 根据代码前缀 如 AV0001-1 的AV0001 获取费用代码
     * @param preScode
     * @return
     * @throws Exception
     */
    public  List getVariableValueWithPreCode(String preScode) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    
    /**
     * 获取货值表信息
     * 
     * */
    public List searchCoinValue(Integer id);
    
    /**
     * 删除货值信息
     * 
     * */
    public int deleteCoinValue(Integer id,Integer serviceID);
}
