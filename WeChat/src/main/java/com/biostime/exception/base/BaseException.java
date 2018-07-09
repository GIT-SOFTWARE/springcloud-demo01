/*
 *  文件创建时间： 2013-5-30
 *  文件创建者: wuzhixiong
 *  所属工程: settlement
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.exception.base;

import com.biostime.util.PropUtil;

/**
 * 
 * 类功能描述：TODO
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhixiong</a>
 * @version 1.0
 * @since 2013-5-30 
 *
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3567979044724280731L;
	
	public BaseException(){
		super();
		this.printStackTrace();
	}
	
	public BaseException(String msg){
		super(msg);
		this.printStackTrace();
	}
	
	public BaseException(int key){
		super(PropUtil.getProp(key));
		this.printStackTrace();
	}
}
