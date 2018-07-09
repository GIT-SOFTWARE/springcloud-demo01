package com.biostime.utils.weixin.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.protocol.HTTP;

import com.biostime.exception.base.ServiceException;

/**
 * <p>功能：http接口处理类 （包括get和post请求）
 * @author zwx
 *
 */
public class HttpUtils {
	
	
	public static String httpPostWithJSON(String url, String json) throws Exception {
		String responseMsg = "";
    	try{
    		PostMethod post = null;
    		HttpClient httpclient = new HttpClient();
    		RequestEntity requestEntity = new StringRequestEntity(json,"text/json","UTF-8");
    		post = new PostMethod(url);
    		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  //传的时候需要转成utf-8,不然会乱码
    		post.addRequestHeader(HTTP.CONTENT_TYPE, "application/json");
    		post.setRequestEntity(requestEntity);
    		int result = httpclient.executeMethod(post);
    		if (result == HttpStatus.SC_OK) {
    			responseMsg = new String(post.getResponseBodyAsString());
    		}else{
    			new ServiceException("ERROR:code="+HttpStatus.SC_OK+";msg="+responseMsg);
    		}
    	}catch(Exception e){
    		throw new ServiceException("HTTP请求出错！"+e.getMessage());
    	}
    	return responseMsg;
	}
	
	public static String httpPostWithXML(String url, String xml) throws Exception {
		String responseMsg = "";
    	try{
    		PostMethod post = null;
    		HttpClient httpclient = new HttpClient();
    		RequestEntity requestEntity = new StringRequestEntity(xml,"text/xml","UTF-8");
    		post = new PostMethod(url);
    		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  //传的时候需要转成utf-8,不然会乱码
    		post.addRequestHeader(HTTP.CONTENT_TYPE, "application/xml");
    		post.setRequestEntity(requestEntity);
    		int result = httpclient.executeMethod(post);
    		if (result == HttpStatus.SC_OK) {
    			responseMsg = new String(post.getResponseBodyAsString());
    		}else{
    			new ServiceException("ERROR:code="+HttpStatus.SC_OK+";msg="+responseMsg);
    		}
    	}catch(Exception e){
    		throw new ServiceException("HTTP请求出错！"+e.getMessage());
    	}
    	return responseMsg;
	}
	
	/**
	 * <p>功能：post请求
	 * @param paramsName 参数名称
	 * @param paramValue 参数值
	 * @return
	 * @throws Exception 
	 */
    public static String postHttp(String url,String [] paramName,String [] paramValue){
    	String responseMsg = "";
    	try{
    		PostMethod post = null;
    		HttpClient httpclient = new HttpClient();
    		post = new PostMethod(url);
    		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  //传的时候需要转成utf-8,不然会乱码
	    	// 设置参数
    		if(paramName != null && paramName.length > 0
			    	   && paramValue != null && paramValue.length > 0
			    	   && paramName.length == paramValue.length){
    		   for(int i=0;i<paramName.length;++i){ // 添加参数
    				post.setParameter(paramName[i], paramValue[i]);
    			}
    		   int result = httpclient.executeMethod(post);
    		   if (result == HttpStatus.SC_OK) {
    			   responseMsg = new String(post.getResponseBodyAsString());
    		   }
	    	}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
       return responseMsg;
    }
    
    public static String getHttp(String url,String [] paramName,String [] paramValue){
    	String responseMsg = "";
    	try{
    		String params = "";
	    	// 设置参数
    		if(paramName != null && paramName.length > 0
			    	   && paramValue != null && paramValue.length > 0
			    	   && paramName.length == paramValue.length){
    		   for(int i=0;i<paramName.length;++i){ // 添加参数
    				if(i==0){
    					params += paramName[i]+"="+paramValue[i];
    				}else{
    					params += "&"+paramName[i]+"="+paramValue[i];
    				}
    			}
    		   GetMethod get = null;
    		   HttpClient httpclient = new HttpClient();
    		   get = new GetMethod(url+"?"+params);
    		   get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  //传的时候需要转成utf-8,不然会乱码
    		   int result = httpclient.executeMethod(get);
    		   if (result == HttpStatus.SC_OK) {
    			   responseMsg = new String(get.getResponseBodyAsString());
    		   }
	    	}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
       return responseMsg;
    }
    
    
    
    public static String postHttp(String url,String param,String paramType,String HeadType) throws Exception{
    	String responseMsg = "";
    	try{
    		PostMethod post = null;
	   		HttpClient httpclient = new HttpClient();
	   		RequestEntity requestEntity = new StringRequestEntity(param,paramType,"UTF-8");
	   		post = new PostMethod(url);
	   		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  //传的时候需要转成utf-8,不然会乱码
	   		post.addRequestHeader(HTTP.CONTENT_TYPE, HeadType);
	   		post.setRequestEntity(requestEntity);
	   		int result = httpclient.executeMethod(post);
	   		if (result == HttpStatus.SC_OK) {
	   			responseMsg = new String(post.getResponseBodyAsString());
	   		}else{
	   			new Exception("code="+HttpStatus.SC_OK+";msg="+responseMsg);
	   		}
	   	}catch(Exception e){
	   		throw new Exception("HTTP请求出错！入参:"+param+"异常："+e.getMessage());
	   	}
    	return responseMsg;
    }
    
    
}
