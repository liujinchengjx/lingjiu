package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IMenuService extends IBaseService{
	
	 /**
     * 加载菜单列表
     * @return
     * @throws Exception
     */
    public  List loadMenuList(Integer userId) throws ServiceException;
    
}
