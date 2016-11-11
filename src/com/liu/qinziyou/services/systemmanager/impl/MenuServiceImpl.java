package com.liu.qinziyou.services.systemmanager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.tree.MenuTree;
import com.liu.qinziyou.dao.systemmanager.IFrameworkModulesDao;
import com.liu.qinziyou.dao.systemmanager.IFrameworkSubsystemsDao;
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IMenuService;


public class MenuServiceImpl extends AbstractBaseService implements IMenuService{
	protected static final Log log = LogFactory.getLog(MenuServiceImpl.class);
	private IFrameworkSubsystemsDao frameworkSubsystemsDao;
	private IFrameworkModulesDao frameworkModulesDao;
	/**
     * 加载菜单列表
     * @return 
     * @throws Exception
     */
    public  List loadMenuList(Integer userId) throws ServiceException{
    	try{
           // List<FrameworkSubsystems> fsubList=frameworkSubsystemsDao.selectSubsystems(1); //
            List<FrameworkSubsystems> fsubList= frameworkSubsystemsDao.selectSubsystems(userId,1);
            List<FrameworkModules> fModulesList;
            List<MenuTree> menuList=new ArrayList<MenuTree>();
            for(FrameworkSubsystems fs:fsubList){
            	MenuTree pm=new MenuTree();
            	pm.setId(fs.getName());
            	pm.setText(fs.getDescription());
            	menuList.add(pm);
            	//fModulesList=frameworkModulesDao.selectModulesList(1,fs.getName(),1);//selectModulesList(userId,1,fs.getName(),1);.
            	fModulesList=frameworkModulesDao.selectModulesList(userId,1,fs.getName(),1);
            	for(FrameworkModules fm:fModulesList){
            		MenuTree sm=new MenuTree();
            		sm.setId(fm.getName());
            		sm.setPid(fs.getName());
            		sm.setText(fm.getDescription());
            		sm.setUrl(fm.getUrl());
            		menuList.add(sm);
            	}
            }
    		return menuList;    		
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("加载菜单列表时发生异常===="+e.getMessage());
			throw new ServiceException("加载菜单列表异常");
		}
    }
	public IFrameworkSubsystemsDao getFrameworkSubsystemsDao() {
		return frameworkSubsystemsDao;
	}
	public void setFrameworkSubsystemsDao(
			IFrameworkSubsystemsDao frameworkSubsystemsDao) {
		this.frameworkSubsystemsDao = frameworkSubsystemsDao;
	}
	public IFrameworkModulesDao getFrameworkModulesDao() {
		return frameworkModulesDao;
	}
	public void setFrameworkModulesDao(IFrameworkModulesDao frameworkModulesDao) {
		this.frameworkModulesDao = frameworkModulesDao;
	}	
    
}
