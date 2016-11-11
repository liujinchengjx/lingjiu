package com.liu.qinziyou.dao.business;


import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.business.Truck;


public interface ITruckDao  extends IBaseDAO{	    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
	 public  PageHelper searchTruck(String carno,Integer tranId,String tonnage,Integer truckStatus,
	    		Integer truckType,int currentPage, int pageSize) throws Exception;
    /**
     * 根据货运公司和货车id获取车牌号，
     * @param tranId
     * @param type 0 表示获取香港车牌号，1 表示获取大陆车牌号，2 表示，大陆车牌号和香港车牌号拼接在一起返回
     * @return
     * @throws Exception
     */
    public  String getTruckNo(Integer tranId,Integer truckId,Integer type) throws Exception;
    
    
    public  Map<Integer, String> getTruckNos(String truckIds) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    
    public Truck getTruckWithTranCompany() throws Exception;    
}
