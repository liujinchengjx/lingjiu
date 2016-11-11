package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.exception.ServiceException;

public interface IPositionDao  extends IBaseDAO{
    
    
    public List selectPosition(String positionCode,Integer positionLevel)  throws Exception;
    /**
     * 冻结职位
     * @return
     * @throws Exception
     */
    public boolean froze(int roleId,int flag) throws Exception;
    
    /**
     * 删除职位信息
     * @param positionCode
     * @return
     * @throws Exception
     */
    public Integer deletePosition(String positionCode) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPosition(String positionName,int currentPage, int pageSize) throws Exception;
    
    public String getMaxPositionCode(String parentPositionCode,Integer positionLevel) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    
	
    
}
