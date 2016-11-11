package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.ProductTaxItem;
import com.liu.qinziyou.exception.ServiceException;


public interface IProductTaxItemDao  extends IBaseDAO{
    
    public  PageHelper searchProductTaxItem(String searchValue,int currentPage, int pageSize) throws Exception;
    
    /**
     * 根据型号，品牌获取 product的中文品名 和 申报要素信息， 并以数组的形式返回
     * @param pn
     * @param brand
     * @return
     * @throws Exception
     */
    public String [] getProductNameAndElements(String pn,String brand,String productName) throws Exception;
    public void updateElements(String pn,String brand,String productName,String declareElements,String codeTs) throws Exception;
    /**
     * 加载申报要素信息
     * @param codeTs
     * @return
     * @throws Exception
     */
    //public String [] getProductElements(String codeTs,String pn,String brand,String productName) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    
    public ProductTaxItem getProuctTaxItem(String pn,String brand,String productName) throws Exception;

    
    /**
     * 更新状态
     * @param id
     * @return
     * @throws ServiceException
     */
    public boolean updateStatus(Integer id,Integer flag) throws Exception;

}
