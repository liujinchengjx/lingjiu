package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.exception.ServiceException;

public interface IFrameworkButtonsDao  extends IBaseDAO{

	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
	 public  PageHelper selectButtons(int systemId,String modules,int currentPage, int pageSize)  throws Exception;
    
	 /**
		 * 获取子系统集合
		 * @param systemId 系统id 默认是1
		 * @return
		 * @throws Exception
		 */
	    public  PageHelper selectButtons(String modules,int currentPage, int pageSize) throws Exception;	  
	    /**
	     *  加载页面工具栏
	     * @param modules
	     * @param toolBarSeq
	     * @return
	     * @throws ServiceException
	     */
	    public List loadButtonsList(String modules,Integer toolBarSeq) throws Exception;
	    /**
	     *  加载页面工具栏
	     * @param modules
	     * @param toolBarSeq
	     * @return
	     * @throws ServiceException
	     */
	    public List loadButtonsList(Integer userId,String modules,Integer toolBarSeq) throws Exception;
	 /**
	     * 搜索
	     * @return
	     * @throws Exception
	     */
	  public  PageHelper searchButtons(String searchValue,int currentPage, int pageSize) throws Exception;
	    /**
	     * 检查新增加的数据是否和库里面有重复的
	     * @return
	     * @throws Exception
	     */
	  public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
