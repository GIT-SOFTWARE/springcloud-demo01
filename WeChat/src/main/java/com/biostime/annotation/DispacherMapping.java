package com.biostime.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.biostime.annotation.enums.MappingScope;

/**
 * 
 * 类功能描述：转发的mapping标注
 * 
 * 本标注只能使用范围为controller中的类与方法
 * 对类与方法进行转发地址的限定
 *
 * <p> 版权所有：biostime.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhixiong</a>
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DispacherMapping {
	
	/**
	 * 
	 * 
	 * 功能描述：保存转发地址。
	 * value的格式为： 转发名称=转发页面地址
	 * 例如：pathNormal=/jsp/test
	 * 
	 * @return 
	 * @author wuzhixiong
	 *
	 */
	String[] mapping();
	
	/**
	 * 
	 * 
	 * 功能描述：替换地址的通配符
	 * 默认值为 standard
	 * 
	 * @return 
	 * @author wuzhixiong
	 *
	 */
	String mark() default "/standard";
	
	/**
	 * 
	 * 
	 * 功能描述：mapping地址的使用范围
	 * 
	 * @return 
	 * @author wuzhixiong
	 *
	 */
	MappingScope scope() default MappingScope.CONTROLLER;
}
