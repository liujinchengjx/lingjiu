package com.liu.qinziyou.services.systemmanager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.MD5;
import com.liu.qinziyou.common.util.SystemUtilities;
import com.liu.qinziyou.dao.business.ICompanyDao;
import com.liu.qinziyou.dao.systemmanager.IDepartmentDao;
import com.liu.qinziyou.dao.systemmanager.IUserDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.business.Company;
import com.liu.qinziyou.entity.systemmanager.Department;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IUserService;

public class UserServiceImpl extends AbstractBaseService implements IUserService{
	protected static final Log log = LogFactory.getLog(UserServiceImpl.class);
	private IUserDao userDao;
	private IDepartmentDao departmentDao;
	private ICompanyDao companyDao;

	/**
	 * 根据ID获取
	 * @param id
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public IBean getBeanById(Class cls,Integer id)  throws ServiceException{
		try{
			User u=(User)this.getBaseDAO().findById(cls, id);
    		u.setEnterpriseName(companyDao.getCompanyName(u.getEnterpriseId()));
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取报关单时发生异常====" + e.getMessage());
			throw new ServiceException("获取报关单时时失败");
		}
	}
	 public void saveUser(IBean baseBean) throws ServiceException{
			try{
				User u=(User)baseBean;
				u.setEnterpriseId(1);
				if(SystemUtilities.isBlankStr(u.getUserName())){
					String mcode = this.userDao.getMaxMemberCode();
					mcode = SystemUtilities.genMemberCode(mcode);
					u.setUserName(mcode);
				}
				
				boolean isDuplicate=this.userDao.checkForDuplicate(u);
				if(isDuplicate){
					throw new ServiceException("已有重复的用户");
				}
				if(u.getId()==null ){//新增
					String pwd=Configuration.getConfigValue("USER_INITIAL_PASSWORD");//初始密码
					if(SystemUtilities.isBlankStr(u.getPassWord())){
				
					}else{
						pwd = u.getPassWord();
					}
					
					MD5 md5 = MD5.getInstance();
			    	pwd=md5.getMD5(pwd);
			    	u.setPassWord(pwd);//获取初始密码
			    	u.setFlag(1);//设置为有效
				}
				if (SystemUtilities.isBlankStr(u.getPassWord())){ //设置密码md5
					
			    	
			    	
				}
				u.setLowerdName(u.getUserName().toLowerCase());
				this.getBaseDAO().saveOrUpdateObject(u);
			}catch (ServiceException e){
				throw e;
			}catch (Exception e){
				e.printStackTrace();
				log.error("保存用户时发生异常===="+e.getMessage());
				throw new ServiceException("保存用户时失败"+e.getMessage());
			}
	}	
	public void updatePosition(Integer userId,String positionCode) throws ServiceException{
		 try{
			 userDao.updatePosition(userId, positionCode);
		 }catch (ServiceException e){
			throw e;
		 }catch (Exception e){
			e.printStackTrace();
			log.error("更新用户职位时发生异常===="+e.getMessage());
			throw new ServiceException("更新用户职位时失败"+e.getMessage());
		 }
	 }
    /**
     * 激活用户
     * @return
     * @throws Exception
     */
    public boolean activeUser(int userId,int flag) throws ServiceException{
    	try{
    		return this.userDao.activeUser(userId,flag);
    	}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("复核用户时发生异常===="+e.getMessage());
			throw new ServiceException("审核用户时失败");
		}
    }
    /**
     * 删除用户
     * @return
     * @throws Exception
     */
    public boolean delete(int userId,int flag) throws ServiceException{
    	try{
    		return this.userDao.delete(userId,flag);
    	}catch (ServiceException e){
			throw e;
    	}catch (Exception e){
			e.printStackTrace();
			log.error("删除用户时发生异常===="+e.getMessage());
			throw new ServiceException("删除用户时失败");
		}
    }
    public boolean changePassword(Integer userId,String orgPasswod,String newPassword) throws ServiceException{
    	try{
    		if (userId==null || userId.intValue()<=0){
        		throw new ServiceException("未获取到用户！");
        	}
    		MD5 md5 = MD5.getInstance();
        	User u=(User)this.getBaseDAO().findById(User.class, userId);
        	String pwd=md5.getMD5(orgPasswod);
        	if (!pwd.equals(u.getPassWord())){
        		throw new ServiceException("输入的原密码有误，请重新输入！");
        	}
        	if (newPassword==null || "".equals(newPassword)){
        		throw new ServiceException("新密码输入不能为空！");
        	}
        	if (orgPasswod.equals(newPassword)){
        		throw new ServiceException("新密码跟旧密码不能相同！");
        	}
        	pwd=md5.getMD5(newPassword);
      
        	this.getUserDao().updatePwd(pwd, userId);
        	return true;
    	}catch (ServiceException e){
			throw e;
    	}catch (Exception e){
			e.printStackTrace();
			log.error("更新用户密码时发生异常===="+e.getMessage());
			throw new ServiceException("更新用户密码时"+e.getMessage());
		}
    }
    public boolean resetPwd(Integer userId) throws ServiceException{
    	try{
    		if (userId==null || userId.intValue()<=0){
        		throw new ServiceException("未获取到用户！");
        	}
    		User u=(User)this.getBaseDAO().findById(User.class, userId);
    		String pwd=Configuration.getConfigValue("USER_INITIAL_PASSWORD");//初始密码
	    	MD5 md5 = MD5.getInstance();
	    	pwd=md5.getMD5(pwd);
	    	this.getUserDao().updatePwd(pwd, userId);
        	return true;
    	}catch (ServiceException e){
			throw e;
    	}catch (Exception e){
			e.printStackTrace();
			log.error("重置用户密码时发生异常===="+e.getMessage());
			throw new ServiceException("重置用户密码时"+e.getMessage());
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String searchValue,Integer userType,Integer flag,int currentPage, int pageSize) throws ServiceException{
    	try{
    		PageHelper ph=this.userDao.searchUser(searchValue,userType,flag,currentPage, pageSize);
    		List<User> uList=(List)ph.getRows();
    		for(User u:uList){
	    		u.setEnterpriseName(companyDao.getCompanyName(u.getEnterpriseId()));
        	}
    		return ph;
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("查询用户时发生异常===="+e.getMessage());
			throw new ServiceException("查询用户异常");
		}
    }
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String positionCode,int currentPage, int pageSize) throws ServiceException{
    	try{
    		PageHelper ph=this.userDao.searchUser(positionCode,currentPage, pageSize);
    		List<User> uList=(List)ph.getRows();
    		for(User u:uList){
	    		u.setEnterpriseName(companyDao.getCompanyName(u.getEnterpriseId()));
        	}
    		return ph;
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("查询用户时发生异常====",e);
			throw new ServiceException("查询用户异常");
		}
    }
    
    public User userLogin(String userName,String password,Integer userType) throws ServiceException{
    	try{
    		password = MD5.getInstance().getMD5(password);
    		User user =  userDao.getUserByUserNamePassword(userName, password,userType);
    		if(user == null ){
    			throw new ServiceException("登录失败，用户名或密码错误");
    		}
    		Company company = (Company)companyDao.findById(Company.class, user.getEnterpriseId());
    		user.setCompany(company);
    		/*if(user!=null && user.getFlag().intValue()== 1){
    			throw new ServiceException("用户无效");
    		}*/
    		//记录登录日志
    		
    		return user;
    	}catch(ServiceException e){
    		throw e;
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("用户登录异常===="+e.getMessage());
			throw new ServiceException("用户登录异常，请联系系统管理员或稍后再试");
		}
    }
    
    public User getUserInfo(Integer userId) throws ServiceException{
    	try{
    		User user =  (User)this.getBeanById(User.class, userId);
    		if(user!=null){
    			Company company = (Company)this.getBaseDAO().findById(Company.class, user.getEnterpriseId());
    			user.setCompany(company);
    		}    		
    		return user;
    	}catch(ServiceException e){
    		throw e;
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("用户登录异常===="+e.getMessage());
			throw new ServiceException("用户登录异常，请联系系统管理员或稍后再试");
		}
    }
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IDepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public ICompanyDao getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	
}
