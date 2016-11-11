package com.liu.qinziyou.common;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.exception.PropetiesMappingException;
import com.liu.qinziyou.web.action.Action;

public class ActionPropetiesMapper {

	private final static Log logger = LogFactory.getLog(ActionPropetiesMapper.class);

	private static boolean isBaseData(Class clz) throws Exception {
		return (AtomicInteger.class.equals(clz) || AtomicLong.class.equals(clz) || BigDecimal.class.equals(clz)
				|| BigInteger.class.equals(clz) || Double.class.equals(clz) || Float.class.equals(clz)
				|| Integer.class.equals(clz) || Long.class.equals(clz) || Short.class.equals(clz) || java.util.Date.class.equals(clz)
				|| java.sql.Date.class.equals(clz) || Timestamp.class.equals(clz) || String.class.equals(clz) || clz.isPrimitive());

	}
	/**
	 * 把请求参数值根ACTION里属性对应起来
	 * @param reqMap
	 * @param action
	 * @throws PropetiesMappingException
	 */
	public static void mappingPropeties(Map<String, String> reqMap, Action action) throws PropetiesMappingException {
		try {

			for (String key : reqMap.keySet()) {
				String[] properties = key.split("\\.");
				String propertyFirstName = properties[0];
				// 如果请求的参数在Action中没有定义则不处理他
				Object parentObject = action;

				for (int i = 0; i < properties.length; i++) {
					PropertyDescriptor pd = null;
					try{
						pd = new PropertyDescriptor(properties[i], parentObject.getClass());// propertyDescriptor直接得到属性描述符
					}catch(IntrospectionException  e){
						if(i==0) continue;
						else throw e;
					}
					
					boolean isBaseData = isBaseData(pd.getReadMethod().getReturnType());// 是否基础类型
					boolean isDeepest = (i == properties.length - 1);// 是否最深

					// 最深的一层必须是基本数据类型或其包装类
					if ((isBaseData && !isDeepest) || !isBaseData && isDeepest) {
						throw new PropetiesMappingException("属性 " + key + "映射错误");
					}

					
					if (!isDeepest  ) {//如果不是最深层
						Object obj = pd.getReadMethod().invoke(parentObject);
						if(obj == null){//如果对象为空则生成一个对象
							obj = pd.getReadMethod().getReturnType().newInstance();
							pd.getWriteMethod().invoke(parentObject, obj);
						}
						parentObject = obj;
						
					} else {// 如果是最后一层，就对基本数据类型赋值
						String value = reqMap.get(key);
						if (null == value || "".equals(value) || "null".equals(value)) {
							continue;
						}
						Class returnType = pd.getReadMethod().getReturnType();
						if (returnType.equals(AtomicInteger.class)) {
							AtomicInteger ai = new AtomicInteger();
							ai.set(new Integer(value));
							pd.getWriteMethod().invoke(parentObject, ai);
						} else if (returnType.equals(AtomicLong.class)) {
							AtomicLong al = new AtomicLong();
							al.set(new Long(value).longValue());
							pd.getWriteMethod().invoke(parentObject, al);
						} else if (returnType.equals(BigDecimal.class)) {
							BigDecimal bd = new BigDecimal(value);
							pd.getWriteMethod().invoke(parentObject, bd);
						} else if (returnType.equals(BigInteger.class)) {
							BigInteger bi = new BigInteger(value);
							pd.getWriteMethod().invoke(parentObject, bi);
						} else if (returnType.equals(Double.class)) {
							pd.getWriteMethod().invoke(parentObject, Double.valueOf(value));
						} else if (returnType.equals(Float.class)) {
							pd.getWriteMethod().invoke(parentObject, Float.valueOf(value));
						} else if (returnType.equals(Integer.class)) {
							pd.getWriteMethod().invoke(parentObject, Integer.valueOf(value));
						} else if (returnType.equals(Long.class)) {
							pd.getWriteMethod().invoke(parentObject, Long.valueOf(value));
						} else if (returnType.equals(Short.class)) {
							pd.getWriteMethod().invoke(parentObject, Short.valueOf(value));
						}else if (returnType.equals(java.sql.Date.class)) {
							java.text.SimpleDateFormat  bartDateFormat =   new java.text.SimpleDateFormat("yyyy-MM-dd"); 
							java.util.Date date=bartDateFormat.parse(value);
							java.sql.Date sqlDate =new java.sql.Date(date.getTime());
							pd.getWriteMethod().invoke(parentObject, sqlDate);
						} else if (returnType.equals(Date.class)) {
							java.util.Date d =new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value); //new Date(value);							
							pd.getWriteMethod().invoke(parentObject, d);
						} else if (returnType.equals(Timestamp.class)) {
							if (null != value && !"".equals(value)) {
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Timestamp d = new Timestamp(dateFormat.parse(value).getTime());
								pd.getWriteMethod().invoke(parentObject, d);
							}
						} else if (returnType.equals(String.class)) {
							pd.getWriteMethod().invoke(parentObject, value);
						} else if (returnType.equals(boolean.class)) {
							pd.getWriteMethod().invoke(parentObject, Boolean.parseBoolean(value));
						} else if (returnType.equals(char.class)) {
							pd.getWriteMethod().invoke(parentObject, value.charAt(0));
						} else if (returnType.equals(short.class)) {
							pd.getWriteMethod().invoke(parentObject, Short.parseShort(value));
						} else if (returnType.equals(int.class)) {
							pd.getWriteMethod().invoke(parentObject, Integer.parseInt(value));
						} else if (returnType.equals(long.class)) {
							pd.getWriteMethod().invoke(parentObject, Long.parseLong(value));
						} else if (returnType.equals(float.class)) {
							pd.getWriteMethod().invoke(parentObject, Float.parseFloat(value));
						} else if (returnType.equals(double.class)) {
							pd.getWriteMethod().invoke(parentObject, Double.parseDouble(value));
						}
					}
					

				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new PropetiesMappingException(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		/*System.out.println("1111");
		Action action = new ProductAction();
		Map<String, String> reqMap = new HashMap<String,String>();
		reqMap.put("searchProductVO.currentPage", "1");
		reqMap.put("searchProductVO.keyWord", "");
		mappingPropeties(reqMap,action);
		System.out.println("222");*/
		
	}
}
