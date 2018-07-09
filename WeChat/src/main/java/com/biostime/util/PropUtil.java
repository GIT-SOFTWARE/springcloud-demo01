package com.biostime.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 
 * 类功能描述：TODO
 *
 * <p> 版权所有：biostime.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhixiong</a>
 * @version DEALER1.0
 * @since 2013-7-8 
 *
 */
public class PropUtil {
	private static final String propFileName = "/dealer.properties"; 
	private static Logger logger = Logger.getLogger(PropUtil.class.getName());
	private static Properties prop = null;
	
	static{
		prop = new Properties();
		try {
			prop.load(PropUtil.class.getResourceAsStream(propFileName));
		} catch (FileNotFoundException e) {
			logger.info("the property file is not found!");
		} catch (IOException e) {
			logger.info("reading property file error,please check!");
		}
	}
	
	public static String getProp(String key){
		
		String value = prop.getProperty(key);
		if(value == null){
			logger.info("the properties is not found.");
			return null;
		}else{
			return value;
		}
	}
	public static String getProp(String key, String defaultValue){
		
		String value = prop.getProperty(key);
		if(value == null){
			return defaultValue;
		}else{
			return value;
		}
	}
	public static String[] getProps(String key){
		String value = prop.getProperty(key);
		return value.split(",");
	}
	
	public static String getProp(int key){
		return getProp(String.valueOf(key));
	}
}
