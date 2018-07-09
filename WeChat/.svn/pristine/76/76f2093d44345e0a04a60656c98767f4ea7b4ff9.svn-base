/*
 *  文件创建时间： 2013年9月22日
 *  文件创建者: user
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.setting.bean;
import java.io.Serializable;
import java.util.Date;

import com.biostime.exception.base.ServiceException;
import com.biostime.utils.JsonUtils;

/**
 * 
 * 类功能描述：TODO
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:user@biostime.com">zengli</a>
 * @version DEALER 4.0
 * @since 2013年9月22日 
 *
 *
 */
public class ResultInfo implements Serializable{

	private static final long serialVersionUID =-4542204465034052025L;
	
	public ResultInfo() {
		super();
	}
	
	public ResultInfo(String msg) {
		super();
		this.msg = msg;
	}
	
	public ResultInfo(int result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	public ResultInfo(int result, String msg,String retMsg) {
		super();
		this.result = result;
		this.msg = msg;
		this.retMsg = retMsg;
	}
	
	/**
	 * 错误时候的异常结果
	 * SerivceException为系统自定义的异常，提示时候需加上异常信息 
	 */
	public ResultInfo(int result, String msg,Exception e) {
		super();
		this.result = result;
		if(e instanceof ServiceException){
			msg += e.getMessage();
		}
		this.msg = msg;
	}

	private int result;
	private String msg;
	private Date startTime;
	private String retMsg;
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String toJson() {
		return JsonUtils.toJson(this);
	}

	@Override
	public String toString() {
		return this.toJson();
	}
	
}
