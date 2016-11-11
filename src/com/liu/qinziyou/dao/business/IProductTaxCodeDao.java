package com.liu.qinziyou.dao.business;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.ProductTaxCode;

public interface IProductTaxCodeDao  extends IBaseDAO{
	
    public  List selectProductTaxCodeForUpdateOnLineList(int currentPage, int pageSize) throws Exception;
    
    /**
     * 获得申报要素的顺序
     * @param codeTs
     * @return
     * @throws Exception
     */
    public String getCustomsCriterionTips(String codeTs) throws Exception;
    /**
     * 根据商品编码获取对象
     * @param codeTs
     * @return
     * @throws Exception
     */
    public ProductTaxCode getProductTaxCode(String codeTs) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchProductTaxCode(String searchValue,String unit1,String unit2,
    		int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
