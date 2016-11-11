package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;


public interface IChargesVariablesDao  extends IBaseDAO{  
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchChargesVariables(String searchValue,Integer [] businessTypes,Integer opType,String preCode,
    		int currentPage, int pageSize) throws Exception;
    /**
     * 获取最大的序列号
     * @param feeType
     * @param businessType
     * @return
     * @throws Exception
     */
    public Integer getMaxScode(Integer feeType,Integer businessType,Integer opType) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
