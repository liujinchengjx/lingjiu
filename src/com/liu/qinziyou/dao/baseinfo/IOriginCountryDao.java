package com.liu.qinziyou.dao.baseinfo;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IOriginCountryDao extends IBaseDAO{
	
    public  String getOriginCountryCode(String originName,boolean isCname) throws Exception;
    
    /**
     * 获取是否最惠国 true 是最惠国 false 非最惠国
     * @param origin 原产国
     * @return
     * @throws Exception
     */
    public boolean getDiscountFlag(String  origin) throws Exception;
    /**
     * 获取是否最惠国 true 是最惠国 false 非最惠国
     * @param code
     * @param cnName
     * @return
     * @throws Exception
     */
    @Deprecated
    public  boolean getDiscountFlag(Integer productId) throws Exception;
    /**
     * 获取是否疫区 true 是疫区 false 非疫区
     * @param code
     * @param cnName
     * @return
     * @throws Exception
     */
    @Deprecated
    public boolean getEpidemicFlag(Integer productId) throws Exception;
    /**
     * 根据原产地查看是否来自疫区
     * @param origin
     * @return
     * @throws Exception
     */
    public boolean getEpidemicFlag(String origin) throws Exception;
    
    /**
     * 根据产品ids查看是否有来自疫区
     * @param productIds
     * @return
     * @throws Exception
     */
    public boolean getEpidemicFlag(Integer [] productIds) throws Exception;
    /**
     * 获取原产国的中英文名称
     * @return
     * @throws Exception
     */
    public  String getOriginCountryName(String code,boolean isCnName) throws Exception;
    
    public  Integer updateDiscountOrEpidemicFlag(Integer originCountryId,Integer discountFlag,Integer epidemicFlag) 
      throws Exception;
    
    /**
     * 检测某个原产国是否存在
     * @param origin
     * @return
     * @throws Exception
     */
    public boolean isExist(String origin) throws Exception;
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchOriginCountry(String searchValue,int discountFlag,int epidemicFlag,
    		int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
