package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;


public interface IContractDao  extends IBaseDAO{
	
    
    public  PageHelper searchContract(String ctCode,Integer customerId,Integer businessType,Integer contractStatus,
    		String startTime,String endTime,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    /**
     * 获取Contract表最大的code编码
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode() throws Exception;
}
