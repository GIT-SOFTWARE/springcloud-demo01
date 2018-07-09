/*
 *  文件创建时间： 2013-6-28
 *  文件创建者: wuzhixiong
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.exception;

import com.biostime.exception.base.ServiceException;

/**
 * 
 * 类功能描述：TODO
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhixiong</a>
 * @version 1.0
 * @since 2013-6-28 
 *
 */
public class TransferErrorException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7519103358390668549L;
	
	public TransferErrorException(){
		super();
	}
	
	public TransferErrorException(int key){
		super(key);
	}
	
	public TransferErrorException(String msg){
		super(msg);
	}
}
