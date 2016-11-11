package com.liu.qinziyou.services.systemmanager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.tree.PopedomTree;
import com.liu.qinziyou.dao.systemmanager.IFrameworkModulesDao;
import com.liu.qinziyou.dao.systemmanager.IFrameworkSubsystemsDao;
import com.liu.qinziyou.dao.systemmanager.IPopedomDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;
import com.liu.qinziyou.entity.systemmanager.Popedom;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IPopedomService;

public class PopedomServiceImpl extends AbstractBaseService implements IPopedomService{
	protected static final Log log = LogFactory.getLog(PopedomServiceImpl.class);
	private IPopedomDao popedomDao;
	private IFrameworkSubsystemsDao frameworkSubsystemsDao;
	private IFrameworkModulesDao frameworkModulesDao;
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			Popedom p=(Popedom)baseBean;
			boolean isDuplicate=this.popedomDao.checkForDuplicate(p);
			if(isDuplicate){
				throw new ServiceException("已有重复的权限");
			}
			id=this.popedomDao.save(p);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加权限时发生异常===="+e.getMessage());
			throw new ServiceException("添加权限时失败");
		}
		return id;
	}
	/**
	 * 修改
	 * @param baseBean
	 * @throws ServiceException
	 */
	public void update(IBean baseBean) throws ServiceException{
		try{
			Popedom p=(Popedom)baseBean;
			boolean isDuplicate=this.popedomDao.checkForDuplicate(p);
			if(isDuplicate){
				throw new ServiceException("已有重复的权限");
			}
			this.popedomDao.update(p);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新权限时发生异常===="+e.getMessage());
			throw new ServiceException("更新权限时失败");
		}
	}
	/**
     * 获取权限集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomList(int currentPage, int pageSize) throws ServiceException{
	    try{
	    	PageHelper ph=this.popedomDao.selectPopedomList(currentPage, pageSize);
	    	for(BaseBean bean:ph.getRows()){
	    		Popedom	p=(Popedom)bean;
	    		p.setMenuOne(frameworkSubsystemsDao.getDescription(p.getPopedomGroup()));
	    		p.setMenuTwo(frameworkModulesDao.getDescription(p.getModuleGroup()));
	    	}
	    	return ph;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("查询权限分页时发生异常===="+e.getMessage());
			throw new ServiceException("查询权限分页异常");
		}
    }
    /**
     * 读取权限树
     * @return
     * @throws ServiceException
     */
    public  List loadPopedomTree() throws ServiceException{
    	try{
    		List<FrameworkSubsystems> fssList=frameworkSubsystemsDao.selectSubsystems(1);
    		List<FrameworkModules> fmList=null;
    		List<Popedom> pdList=null;
    		List<PopedomTree> ptLst=new ArrayList<PopedomTree>();
    		for(BaseBean fbean:fssList){
    			FrameworkSubsystems	fss=(FrameworkSubsystems)fbean;
    			PopedomTree pt=new PopedomTree();
    			pt.setId(fss.getPopedom().toString());
        		pt.setText(fss.getDescription());
        		ptLst.add(pt);   			
    			fmList=frameworkModulesDao.selectModulesList(1,fss.getName(),null);
    			for(BaseBean mbean:fmList){
    				FrameworkModules fm=(FrameworkModules)mbean;
    				PopedomTree ptm=new PopedomTree();
    				ptm.setId(fm.getPopedom().toString());
    				ptm.setPid(pt.getId());
    				ptm.setText(fm.getDescription());
            		ptLst.add(ptm);
    				pdList=popedomDao.selectPopedomList(fss.getPopedom(),fm.getPopedom());
    				for(BaseBean pbean:pdList){
    					Popedom	p=(Popedom)pbean;	
    					PopedomTree ptp=new PopedomTree();
    					ptp.setId(p.getPopedom().toString());
    					ptp.setPid(ptm.getId());
    					ptp.setText(p.getDescription());
                		ptLst.add(ptp);
    				}
    			}
    		}	    
	    	return ptLst;
    	}catch(Exception e){
	    	e.printStackTrace();
	    	log.error("获取权限树时发生异常===="+e.getMessage());
			throw new ServiceException("获取权限树时发生异常");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedom(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	PageHelper ph=this.popedomDao.searchPopedom(searchValue,currentPage, pageSize);
	    	for(BaseBean bean:ph.getRows()){
	    		Popedom	p=(Popedom)bean;
	    		p.setMenuOne(frameworkSubsystemsDao.getDescription(p.getPopedomGroup()));
	    		p.setMenuTwo(frameworkModulesDao.getDescription(p.getPopedomGroup()));
	    	}
	    	return ph;
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("搜索权限分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索权限分页异常");
		}
    }
	public IPopedomDao getPopedomDao() {
		return popedomDao;
	}

	public IFrameworkSubsystemsDao getFrameworkSubsystemsDao() {
		return frameworkSubsystemsDao;
	}
	public void setFrameworkSubsystemsDao(
			IFrameworkSubsystemsDao frameworkSubsystemsDao) {
		this.frameworkSubsystemsDao = frameworkSubsystemsDao;
	}
	public void setPopedomDao(IPopedomDao popedomDao) {
		this.popedomDao = popedomDao;
	}
	public IFrameworkModulesDao getFrameworkModulesDao() {
		return frameworkModulesDao;
	}
	public void setFrameworkModulesDao(IFrameworkModulesDao frameworkModulesDao) {
		this.frameworkModulesDao = frameworkModulesDao;
	}
	
	
}
