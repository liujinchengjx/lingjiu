package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;


public interface IDriverDao  extends IBaseDAO{
	
	public  boolean updateDriverStatus(Integer driverId,Integer status) throws Exception;
    /**
     * 根据运输公司和司机ID获取司机名称
     * @return
     * @throws Exception
     */
    public  String getDriverName(Integer driverId) throws Exception;
    /**
     * 获取Contract表最大的code编码
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode() throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDriver(String searchValue,String phone,Integer status,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
