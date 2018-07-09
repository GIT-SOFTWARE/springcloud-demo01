package com.biostime.common.controller;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;

import com.biostime.app.setting.bean.ResultInfo;
import com.biostime.exception.base.ServiceException;

/**
 * 
 * 类功能描述：controller分发类，对模版转发的地址进行分发处理
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@BIOSTIME.com">wuzhixiong</a>
 * @version DEALER1.0
 * @since 2013-6-29 
 *
 */
public class ControllerDispacher {

	private static Logger log = Logger.getLogger(ControllerDispacher.class.getName());
	
	

	
	public static void ajaxWriter(HttpServletResponse response,String message){
		ajaxWriter(response, message, null);
	}
	
	public static void ajaxWriterGBK(HttpServletResponse response,String message){
		ajaxWriterGBK(response, message, null);
	}
	/**
	 * desc:
	 * @param response
	 * @param message
	 * @param e
	 * @修改日期 2015-6-2
	 * @作者 7911-zc
	 */
	public static void ajaxWriter(HttpServletResponse response,String message, Exception e){
		response.setCharacterEncoding("utf-8");
		
		Writer writer = null; 
		try {     
			writer = response.getWriter();
			if (e == null) {
				writer.write(message);
			} else {
				e.printStackTrace();
				LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + e.getMessage());
				writer.write(message);
			}
		} catch (Exception ex) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + ex.getMessage());
		}finally{
			try {
				writer.close();
			} catch (IOException ioe) {
				LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + ioe.getMessage());
			}
		}
		
	}
	
	public static void ajaxWriterGBK(HttpServletResponse response,String message, Exception e){
		response.setCharacterEncoding("GBK");   
		Writer writer = null; 
		try {     
			writer = response.getWriter();
			if (e == null) {
				writer.write(message);
			} else {
				e.printStackTrace();
				LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + e.getMessage());
				writer.write(message);
			}
		} catch (Exception ex) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + ex.getMessage());
		}finally{
			try {
				writer.close();
			} catch (IOException ioe) {
				LogUtil.error(log, LogFormat.PLAIN, "errorMessage:" + ioe.getMessage());
			}
		}
		
	}
	
	public static void ajaxWriter(HttpServletResponse response,Object object){
		ajaxWriter(response,object,null);
	}
	
	
	
	public static void ajaxWriter(HttpServletResponse response,Object object, Exception e){
		response.setHeader("Pragma", "No-Cache");
		response.setHeader("Cache-Control", "No-Cache");
		response.setDateHeader("Expires", 0L);
        response.setCharacterEncoding("utf-8");
        Writer writer = null;
        try {
        	writer = response.getWriter();
			if (e == null) {
				writer.write(JSONSerializer.toJSON(object).toString());
			} else {
				if(object instanceof ResultInfo && e instanceof ServiceException){//业务类型错误信息打印出前台
					ResultInfo reuslt = ((ResultInfo)object);
					reuslt.setMsg(reuslt.getMsg() + "<br/>" + e.getMessage());
					writer.write(JSONSerializer.toJSON(reuslt).toString());
				}else{
					writer.write(JSONSerializer.toJSON(object).toString());
				}
			}
		} catch (Exception ex) {
		}finally{
			try {
				writer.close();
			} catch (IOException ioe) {
			}
		}
	}
	
}
