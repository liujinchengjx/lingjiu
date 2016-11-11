package com.liu.qinziyou.common;

//import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;

import com.liu.qinziyou.common.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import net.sf.json.JsonConfig;

//


/**
 * <p>Title: 系统框架</p>
 * <p>Description:JSON工具类</p>
 * <p>创建日期:2011-10-25</p>
 * @author li
 * @version 1.0 
 */
public class JsonUtil {
    static String string2Json(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 20);
        sb.append('\"');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
            case '\"':
                sb.append("\\\"");
                break;

            case '\\':
                sb.append("\\\\");
                break;

            case '/':
                sb.append("\\/");
                break;

            case '\b':
                sb.append("\\b");

                break;

            case '\f':
                sb.append("\\f");
                break;

            case '\n':
                sb.append("\\n");
                break;

            case '\r':
                sb.append("\\r");
                break;

            case '\t':
                sb.append("\\t");
                break;

            default:
                sb.append(c);
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    static String number2Json(Number number) {
        return number.toString();
    }

    static String boolean2Json(Boolean bool) {
        return bool.toString();
    }

    static String array2Json(Object[] array) {
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');

        for (Object o : array) {
            sb.append(toJson(o));
            sb.append(',');
        }

        // 将最后添加的 ',' 变为 ']': 
        sb.setCharAt(sb.length() - 1, ']');

        return sb.toString();
    }

    static String map2Json(Map<String, Object> map) {
        if (map.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder(map.size() << 4);
        sb.append('{');

        Set<String> keys = map.keySet();

        for (String key : keys) {
            Object value = map.get(key);
            sb.append('\"');
            sb.append(key);
            sb.append('\"');
            sb.append(':');
            sb.append(toJson(value));
            sb.append(',');
        }

        // 将最后的 ',' 变为 '}': 
        sb.setCharAt(sb.length() - 1, '}');

        return sb.toString();
    }

    public static String toJson(Object o) {
    	return toJson(o, "yyyy-MM-dd");
    }   
    public static String toJson(Object o, String dateFormat) {    	
        if (o == null) {
            return "null";
        }
        if (o instanceof PageHelper || o instanceof Map || o instanceof MsgBean) {
            JsonConfig jsonConfig = new JsonConfig();
        	jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
        	jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(dateFormat));
        	jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));
        	return JSONObject.fromObject(o, jsonConfig).toString();        	 
        }
        if (o instanceof List || o instanceof Collection || o instanceof ArrayList || o instanceof Object[]) {
            JsonConfig jsonConfig = new JsonConfig();    
            jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
        	jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor(dateFormat));
        	jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(dateFormat));
        	return JSONArray.fromObject(o, jsonConfig).toString();
        }

        if (o instanceof String) {
            return string2Json((String) o);
        	//return (String)o;
        }

        if (o instanceof Boolean) {
            return boolean2Json((Boolean) o);
        }

        if (o instanceof Number) {
            return number2Json((Number) o);
        }

        //if (o instanceof Map) {
        //    return map2Json((Map<String, Object>) o);
        //}

        if (o instanceof Object[]) {
            return array2Json((Object[]) o);
        }

        throw new RuntimeException("Unsupported type: " +
            o.getClass().getName());
    }

    @SuppressWarnings("deprecation")
	public static List toList(Object o, Class clazz) {
    	JSONArray jsonArray = JSONArray.fromObject(o);
    	return JSONArray.toList(jsonArray, clazz);
    }

    @SuppressWarnings("deprecation")
	public static List toList(Object o) {
    	JSONArray jsonArray = JSONArray.fromObject(o);
    	return JSONArray.toList(jsonArray);
    }

    public static Object toBean(Object o, Class clazz) {
    	JSONObject jsonObject = JSONObject.fromObject(o);
    	return JSONObject.toBean(jsonObject, clazz);
    }

    public static Object toBean(Object o) {
    	JSONObject jsonObject = JSONObject.fromObject(o);
    	return JSONObject.toBean(jsonObject);
    }

    //public static void main(String[] args) throws Exception {
    //    Map map = new HashMap();
    //    map.put("user", "thh");
    //    System.out.println(JsonUtil.toJson(map));
    //}
    
    /**
     * 反序列化数组
     * @param object：json字符串
     * @throws Exception
     */
    public static List deserializerArray(Object json) throws Exception {
    	return com.liu.qinziyou.common.JsonUtil.toList(json);
    }

    /**
     * 反序列化数组
     * @param object：json字符串
     * @param clazz: 指定要转换的类
     * @throws Exception
     */
    public static List deserializerArray(Object json, Class clazz) throws Exception {
    	return com.liu.qinziyou.common.JsonUtil.toList(json, clazz);
    }

    /**
     * 反序列化对象
     * @param object：json字符串
     * @throws Exception
     */
    public static Object deserializerObject(Object json) throws Exception {
    	return com.liu.qinziyou.common.JsonUtil.toBean(json);
    }

    /**
     * 反序列化对象
     * @param object：json字符串
     * @param clazz: 指定要转换的类
     * @throws Exception
     */
    public static Object deserializerObject(Object json, Class clazz) throws Exception {
    	return com.liu.qinziyou.common.JsonUtil.toBean(json, clazz);
    }

    /**
     * 由字符串反序列化成实体类  针对的是一个实体，此实体中的属性包括自定义的类型，如Teacher类型，或者List<Teacher>类型
     * @param jsonArrStr
     * @param clazz
     * @param classMap
     * @return
     */
    public static Object getObjFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) throws Exception { 
    
        JSONObject jsonObj = JSONObject.fromObject(jsonArrStr);  
                return JSONObject.toBean(jsonObj, clazz, classMap);            
    }
    
    /**
     * 将string转换成listBean 属性中包含实体类等 如List<Student> 而Student中含有属性List<Teacher>
     * @param jsonArrStr 需要反序列化的字符串
     * @param clazz 反序列化后的类
     * @param classMap 将属性中包含的如Teacher加入到一个Map中，格式如map.put("teacher",Teacher.class)
     * @return 反序列化后的字符串
     * 使用示例：
        Map classMap = new HashMap();
        //必须要对Parent进行初始化 否则不识别
        Teacher p = new Teacher();
        classMap.put("teacher", p.getClass());
        List mlist = JSONTransfer.getListFromJsonArrStr(resultStr, Student.class, classMap);
     */
    @SuppressWarnings("unchecked")
    public static List getListFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap)throws Exception {  
       JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);  
       List list = new ArrayList();  
       for (int i = 0; i < jsonArr.size(); i++) 
       {           
           list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));  
       }  
       return list;  
    }
    
    @SuppressWarnings("unchecked")
    public static List getListFromJsonArrStr(String jsonArrStr, Class clazz) throws Exception {  
   
       JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);  
       List list = new ArrayList();  
       for (int i = 0; i < jsonArr.size(); i++) 
       {           
           list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz));  
       }  
       return list;  
    }
}
