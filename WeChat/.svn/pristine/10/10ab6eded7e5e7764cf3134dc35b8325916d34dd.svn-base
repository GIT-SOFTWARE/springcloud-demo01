package com.biostime.utils.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biostime.utils.WechatConfig;
import com.biostime.utils.weixin.pojo.AccessToken;
import com.biostime.utils.weixin.pojo.Ticket;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
 * 类功能描述：微信企业号请求工具包
 * 
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:12624HL@biostime.com">12624HL</a>
 * @version DEALER 5.0
 * @since 2016年4月29日
 */
public class WeixinUtilForEnterprise {
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtilForEnterprise.class);

	/**
	 * API文档地址
	 * http://qydev.weixin.qq.com/wiki/index.php?title=%E9%A6%96%E9%A1%B5
	 */
	
	//获取access_token url
	public final static String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=:corpid&corpsecret=:secrect";
	
	//菜单创建 url
	public final static String MENU_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=:token&agentid=:agentid";
	
	//授权oauth2_url
	public final static String OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=:corpid&redirect_uri=:redirect_uri&response_type=code&scope=SCOPE&state=:state#wechat_redirect";
	
	//通过userid 获取企业号成员信息
	public final static String GET_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=:token&userid=:userid";
	
	//获取userid
	public final static String GET_USER_ID = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=:token&code=:code";
	
	//获取ticket
	public final static String GET_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=:token";
	
	public static AccessToken  token = new AccessToken();
	
	public static Ticket ticket = new Ticket();
	
	/**
	 * 功能描述： 通过CODE获取USERID
	 * @param code
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年5月3日
	 */
	public static JSONObject getUserId(String code) {
		AccessToken token = getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = GET_USER_ID.replace(":token", token.getToken()).replace(":code", code);
		log.info("getUserId request url=" + url);
		JSONObject json = httpRequest(url,"GET",null);
		log.info("getUserId response json=" + json);
		return json;
	}
	
	/**
	 * 功能描述： 通过USERID获取企业用户信息
	 * @param code
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年5月3日
	 */
	public static JSONObject getUserInfoById(String userId) {
		AccessToken token = getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = GET_USER_URL.replace(":token", token.getToken()).replace(":userid", userId);
		log.info("getUserInfoById request url=" + url);
		JSONObject json = httpRequest(url,"GET",null);
		log.info("getUserInfoById response json=" + json);
		return json;
	}
	
	
	
	/**
	 * 功能描述：获取AccessToken
	 * @param corpid
	 * @param corpsecret
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2016年4月29日
	 */
	public static AccessToken getAccessToken(String corpid, String corpsecret) {
		//校验token,每半个小时只获取一次token
		if(token!=null&&StringUtils.isNotBlank(token.getToken())&&(System.currentTimeMillis() - token.getCreateTime())/(60*1000) <= 30){
			return token;
		}else{
			String requestUrl = ACCESS_TOKEN_URL.replace(":corpid", corpid).replace(":secrect", corpsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
			if (null != jsonObject) {
				try {
				// 如果请求成功   
		            token = new AccessToken();  
		            token.setToken(jsonObject.getString("access_token"));  
		            token.setExpiresIn(jsonObject.getInt("expires_in"));
		            token.setCreateTime(System.currentTimeMillis());
		        } catch (JSONException e) {  
		        	token = null;  
		            // 获取token失败   
		            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
		        }  
		    } 
		}
	    return token;  
	} 

	public static Ticket getTicket(String token) {
		//校验ticket,每半个小时只获取一次ticket
		if(ticket!=null&&StringUtils.isNotBlank(ticket.getTicket())&&(System.currentTimeMillis() - ticket.getCreateTime())/(60*1000) <= 30){
			return ticket;
		}else{
			String requestUrl = GET_TICKET.replace(":token", token);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
			if (null != jsonObject) {
				try {
				// 如果请求成功   
					ticket = new Ticket();  
					ticket.setTicket(jsonObject.getString("ticket"));  
					ticket.setExpiresIn(jsonObject.getInt("expires_in"));
					ticket.setCreateTime(System.currentTimeMillis());
		        } catch (JSONException e) {  
		        	token = null;  
		            // 获取token失败   
		            log.error("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
		        }  
		    } 
		}
	    return ticket;  
	} 
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (StringUtils.isNotBlank(outputStr)) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	
}
    
