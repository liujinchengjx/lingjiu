package com.liu.qinziyou.common.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.ApplicationCache;
import com.liu.qinziyou.common.MsgBean;

/**
 * 判断价格比对数据缓存是否加载完成
 * @author liujc
 *2013-3-1
 */
public class CheckPriceCacheLoadedInterceptor extends AbstractInterceptor{
	
	private final static Log logger = LogFactory.getLog(CheckPriceCacheLoadedInterceptor.class);
	@Override
	protected Object after(ActionInvoke invInfo) throws Exception{
		//logger.info("LoginInterceptor after");
		return null;
		
	}

	@Override
	protected Object before(ActionInvoke invInfo) throws Exception{
		
		if(!ApplicationCache.getInstance().isCheckPriceCacheReady()) {
			MsgBean msgBean = new MsgBean();
			msgBean.setMsg("价格比对数据缓存数据正在加载中，为了避免比对误差，请稍后(约5分钟)使用此功能").setMsgCode(MsgBean.MsgCode.FAILURE);
			logger.info("价格比对数据缓存数据正在加载中，为了避免比对误差，请稍后(约5分钟)使用此功能");
			return msgBean;
		}
		
		
		return null;
	}
}
