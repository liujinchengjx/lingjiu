package com.liu.qinziyou.common.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.liu.qinziyou.entity.systemmanager.User;


public class LoginCache {
	
	private static final Logger logger = Logger.getLogger(LoginCache.class);
	
	private Thread checkThread;//用于定时检查清除缓存的线程
	
	public static enum CacheType{};
	
	private static LoginCache cache;
	
	private Map<Integer,List<Map<String, Object>>>  userMenusCache = new HashMap<Integer,List<Map<String, Object>>>();//用户菜单缓存

	private Map<Integer,Long>  lastAccessTimeCache =new HashMap<Integer,Long>();//用户最后访问时间缓存
	private Map<Integer,String>  userSessionCache =new HashMap<Integer,String>();//用户最后访问时间缓存
	private Map<Integer,User>  loginUserCache =new HashMap<Integer,User>();//用户最后访问时间缓存
	
	

	private volatile boolean stopRequested; //停止线程的标志
	
	public static LoginCache getInstance() {
		if(cache == null){
			cache = new LoginCache();
		}
		return cache;
	}
	
	public void startCheckThread(){
		if(checkThread==null){
			Runnable runnable = new Runnable(){
				public void run() {
					stopRequested = false;
					logger.info("Thread   "+Thread.currentThread().getName()+"   start "+new Date());
					while(!stopRequested){
						logger.info("Thread   "+Thread.currentThread().getName()+"   check "+new Date());
						try{
							Timestamp currentTime = new Timestamp(System.currentTimeMillis());
							for(Iterator<Map.Entry<Integer,Long>> it = lastAccessTimeCache.entrySet().iterator();it.hasNext();){
								Map.Entry<Integer,Long> entry = it.next();
								Timestamp lastAccessTime = new Timestamp(entry.getValue());
								float dateDiff = DateUtil.dateDiff(currentTime, lastAccessTime, 2);
								//System.out.println("before userMenusCache size="+userMenusCache.size());
								//System.out.println("before userFunctionCache size="+userMenusCache.size());
								if(dateDiff > LoginInfoUtil.TIMEOUT_TIME){
									Integer userId = entry.getKey();
									userMenusCache.remove(userId);//清除用户菜单缓存
									userSessionCache.remove(userId);
									loginUserCache.remove(userId);
									//lastAccessTimeCache.remove(userId);//清除用户最后访问时间缓存
									it.remove();//清除用户最后访问时间缓存，不可用lastAccessTimeCache.remove(userId)。
									//System.out.println("before userMenusCache size="+userMenusCache.size());
									//System.out.println("before userFunctionCache size="+userMenusCache.size());
								}
							}
							
							/**
							 * 睡眠LoginInfoUtil.TIMEOUT_TIME分钟，就是每隔这么长时间重启一次
							 */
							Thread.sleep(LoginInfoUtil.TIMEOUT_TIME * 60 * 1000);//
						}catch(InterruptedException e){
							//e.printStackTrace();
						}
					}
					logger.info("Thread   "+Thread.currentThread().getName()+"   end "+new Date());
				}
				
				
			};
			checkThread = new Thread(runnable);
			checkThread.setName("CacheCheckThread");
			checkThread.setDaemon(true);
			checkThread.start();
		}else{
			logger.info("checkThread has started,don't start again");
		}
		
	}
	
	public void endCheckThread(){
		stopRequested=true;
		if(checkThread!=null){
			checkThread.interrupt();
		}
	}
	
	public void setUserLastAccessTime(Integer userId,long lastAccessTime){
		lastAccessTimeCache.put(userId, new Long(lastAccessTime));
	}
	
	public Long getUserLastAccessTime(Integer userId){
		return lastAccessTimeCache.get(userId);
	}
	
	public List<Integer> getOnlienUserList(){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll((Collection<Integer>)lastAccessTimeCache.keySet());
		return list;
	}
	
	public void putUserMenus(Integer userId,List<Map<String, Object>> menus){
		userMenusCache.put(userId, menus);
	}
	
	public List<Map<String, Object>> getUserMenus(Integer userId){
		return userMenusCache.get(userId);
	}
	
	
	
	
	public void clearLoginCache(Integer loginUserId){
		this.lastAccessTimeCache.remove(loginUserId);//清除最后访问时间缓存
		this.userSessionCache.remove(loginUserId);
		this.loginUserCache.remove(loginUserId);
	}
	
	public void putUserSessionId(Integer loginUserId,String sessionId){
		logger.info("loginUserId:"+loginUserId+" sessionId:"+sessionId);
		userSessionCache.put(loginUserId, sessionId);
	}
	
	public String getUserSessionId(Integer loginUserId){
		return userSessionCache.get(loginUserId);
	}
	
	public void putLoginUser(Integer loginUserId,User loginUser){
		loginUserCache.put(loginUserId, loginUser);
	}
	
	public User getLoginUser(Integer loginUserId){
		return loginUserCache.get(loginUserId);
	}

	public static void main(String[] args){
		LoginCache.getInstance().lastAccessTimeCache.put(1, new Long(200));
		System.out.println(LoginCache.getInstance().lastAccessTimeCache.get(1));
		LoginCache.getInstance().lastAccessTimeCache.put(2, new Long(300));
		System.out.println(LoginCache.getInstance().lastAccessTimeCache.get(1));
		
		List<Integer> cl = LoginCache.getInstance().getOnlienUserList();
		for(Iterator<Integer> it = cl.iterator();it.hasNext();){
			System.out.println(it.next());
		}
	}
	
	
}
