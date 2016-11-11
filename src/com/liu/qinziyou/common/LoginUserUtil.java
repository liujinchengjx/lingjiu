package com.liu.qinziyou.common;

import java.sql.Connection;

import com.liu.qinziyou.entity.systemmanager.User;

/**
 * 获取数据库连接工具类
 * @author liujc
 * 2012-12-13
 *
 */
public class LoginUserUtil {
	public static final ThreadLocal userLocal = new ThreadLocal();
	public static User getLoginUser() {
		//获取本地线程变量并强制转换为Connection类型
		User user = (User) userLocal.get();
        //线程首次执行此方法的时候，connectionLocal.get()肯定为null
       /* if (user == null) {
            //从连接池中取一个Connection对象，并保存到本地线程变量connectionLocal中
        	//connection = ConnectionPoolManager.getConnection();
        	throw new Exception("没有登录");
        }*/
        return user;
	 
	}
	public static void setLoginUser(User user){
		userLocal.set(user);
	}
	public static void destoryLoginUser(){
		userLocal.remove();
	}
}
