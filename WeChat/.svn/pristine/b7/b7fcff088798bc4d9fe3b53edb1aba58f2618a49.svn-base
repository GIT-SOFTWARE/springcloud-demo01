package com.biostime.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.biostime.exception.TransferErrorException;
import com.biostime.exception.base.ServiceException;
import com.biostime.utils.DateUtil;

public class InfoUtils{
	
	/**
	 * 
	 * 
	 * 功能描述：bean --> bean
	 * 
	 * @param src
	 * @param clazz
	 * @return
	 * @throws TransferErrorException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年9月26日
	 *
	 */
	public static Object copy(Object src,Class clazz) throws TransferErrorException{
		try{
			Object o = clazz.newInstance();
			if(src == null) {
				return o;
			}
			Method[] methods = src.getClass().getDeclaredMethods();
			for(Method m : methods){
				String mehtodName = m.getName();
				if(mehtodName.startsWith("get")){
					try {
						Method setMethod = null;
						try{
							setMethod = clazz.getMethod("set"+mehtodName.substring(3),m.getReturnType());
						}catch(NoSuchMethodException e){
							try{
								if("int".equals(m.getReturnType().toString())){
									setMethod = clazz.getMethod("set"+mehtodName.substring(3),Integer.class.getSuperclass());
								}else if("long".equals(m.getReturnType().toString())){
									setMethod = clazz.getMethod("set"+mehtodName.substring(3),Long.class);
								}else{
									continue;
								}
							}catch(NoSuchMethodException ex){
								continue;
							}
						}
						Object retVal = m.invoke(src);
						if(retVal == null){
							continue;
						}
						if(retVal instanceof BigDecimal){
							retVal = ((BigDecimal)retVal).setScale(2,BigDecimal.ROUND_HALF_UP);
						}else if(retVal instanceof Integer){
							retVal = ((Integer) retVal).intValue();
						}else if(retVal instanceof Long){
							retVal = ((Long) retVal).longValue();
						}else if(retVal instanceof Float){
							retVal = ((Float) retVal).floatValue();
						}
						setMethod.invoke(o, retVal);
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServiceException(e.getMessage());
					}
				}
			}
			return o;
		}catch(Exception e){
			e.printStackTrace();
			throw new TransferErrorException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * 
	 * 功能描述：List<bean> --> List<bean>
	 * 
	 * @param srcList
	 * @param clazz
	 * @return
	 * @throws TransferErrorException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年9月26日
	 *
	 */
	public static List copyList(List srcList,Class clazz) throws TransferErrorException{
		try{
			if(srcList==null||srcList.size()==0){
				return null;
			}
			List result = new ArrayList();
			for (Object o: srcList){
				Object t = copy(o,clazz);
				result.add(t);
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			throw new TransferErrorException(e.getMessage());
		}
	}
		
	/**
	 * 
	 * 
	 * 功能描述：map --> bean
	 * 
	 * @param src
	 * @param o
	 * @return
	 * @throws TransferErrorException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年9月26日
	 *
	 */
	public static Object copyFromMap(Map src,Object o) throws TransferErrorException{
		return copyFromMap(src, o.getClass(), o);
	}
	
	public static Object copyFromMap(Map src,Class clazz) throws TransferErrorException{
		return copyFromMap(src, clazz, null);
	}
	
	private static Object copyFromMap(Map src,Class clazz,Object o) throws TransferErrorException{
		try{
			if(src==null){
				return null;
			}
			if(o==null){
				o = clazz.newInstance();
			}
			Iterator<String> keys = src.keySet().iterator();
			while(keys.hasNext()){
				String k = keys.next();
				Object valueObj = src.get(k);
				if(valueObj==null){
					continue;
				}
				try {
					Field[] fields = clazz.getDeclaredFields();
					for(Field f:fields){
						if(f.getName().toUpperCase().equals(k.toUpperCase())){
							f.setAccessible(true);
							//类型转换
							if(valueObj instanceof BigDecimal){
								if(f.getType() == long.class||f.getType() == Long.class){
									valueObj = ((BigDecimal)valueObj).longValue();
								}if(f.getType() == int.class||f.getType() == Integer.class){
									valueObj = ((BigDecimal)valueObj).intValue();
								}
							}
							if(f.getType() == Date.class){
								if(valueObj instanceof String){
									String time = (String) valueObj;
									try{
										if(time!=null&&time.length()==DateUtil.DEFAULT_TIMESTAMP_FORMAT.length()){
											valueObj = DateUtil.convertString2Date(time,DateUtil.DEFAULT_TIMESTAMP_FORMAT);
										}else{
											valueObj = DateUtil.convertString2Date(time,DateUtil.DEFAULT_DATE_FORMAT);
										}
									}catch(Exception e){
										valueObj = DateUtil.convertString2Date(time,DateUtil.DEFAULT_DATE_FORMAT);
									}
								}
							}
							
							if(f.getType() == String.class&&!(valueObj instanceof String)){
								valueObj = valueObj.toString();
							}
							
							f.set(o, valueObj);
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			return o;
		}catch(Exception e){
			e.printStackTrace();
			throw new TransferErrorException(e.getMessage());
		}
	}
	
	public static Map BeanToMap(Object o) throws IllegalArgumentException, IllegalAccessException {
		if(o==null){
			return null;
		}
		Field[] fields = o.getClass().getFields();
		Map map = new HashMap();
		for(Field f:fields){
			map.put(f.getName(),f.get(o));
		}
		return map;
	}
	
}
