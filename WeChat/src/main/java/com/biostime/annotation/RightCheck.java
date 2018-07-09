/*
 *  文件创建时间： 2013-9-1
 *  文件创建者: Administrator
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类功能描述：权限校验标识，只有配置了该标签的方法会校验权限
 * 
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:Administrator@biostime.com">Administrator</a>
 * @version 1.0
 * @since 2013-9-1 
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RightCheck {
	String value() default "";
	String module() default "";
}
