package com.biostime.utils.weixin.util;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biostime.entity.base.Member;
import com.biostime.utils.WechatConfig;
import com.biostime.utils.weixin.pojo.AccessToken;

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
public class WeixinUtilForMenber {
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtilForMenber.class);

	/**
	 * API文档地址
	 * http://qydev.weixin.qq.com/wiki/index.php?title=%E9%A6%96%E9%A1%B5
	 */
	
	//创建部门
	public final static String CREATE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=:token";
	
	//更新部门
	public final static String UPDATE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=:token";
	
	//删除部门
	public final static String DELETE_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=:token&id=:id";
	
	//查询部门
	public final static String LIST_DEPARTMENT_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=:token&id=:id";
	
	//创建成员
	public final static String CREATE_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=:token";
	
	//获取成员
	public final static String GET_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=:token&userid=:userId";
		
	//更新成员
	public final static String UPDATE_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=:token";
	
	//删除成员
	public final static String DELETE_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=:token&userid=:userId";
	
	//批量删除成员
	public final static String BATCHDELETE_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=:token";
	
	//批量删除成员
	public final static String LISTDEPARTMENT_MEMBER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=:token&department_id=:deptId&fetch_child=0&status=:status";
	
	
	//获取ACCESSTOKEN
	public static AccessToken  token = new AccessToken();
	
	
	/**
	 * 
	 * 功能描述：查询部门
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月9日
	 */
	public static JSONObject listDepartment(String deptId) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = LIST_DEPARTMENT_URL.replace(":token", token.getToken()).replace(":id",deptId);
		log.info("listDepartment request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",null);
		log.info("listDepartment response json=" + json);
		return json;
	}
	
	/**
	 * 
	 * 功能描述：创建部门
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月9日
	 */
	public static JSONObject createDepartment(String parentDeptId,String deptId,String deptName) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = CREATE_DEPARTMENT_URL.replace(":token", token.getToken());
		String params = "{\"name\":\""+deptName+"\",\"parentid\": "+parentDeptId+",\"order\": "+deptId+",\"id\": "+deptId+"}";
		log.info("createDepartment request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",params);
		log.info("createDepartment response json=" + json);
		return json;
	}
	
	/**
	 * 
	 * 功能描述：更新部门
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月9日
	 */
	public static JSONObject updateDepartment(String deptId,String deptName) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = UPDATE_DEPARTMENT_URL.replace(":token", token.getToken());
		String params = "{\"name\":\""+deptName+"\",\"id\": "+deptId+"}";
		log.info("updateDepartment request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",params);
		log.info("updateDepartment response json=" + json);
		return json;
	}
	/**
	 * 
	 * 功能描述：删除部门
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月9日
	 */
	public static JSONObject deleteDepartment(String deptId) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = DELETE_DEPARTMENT_URL.replace(":token", token.getToken()).replace(":id",deptId);
		log.info("deleteDepartment request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",null);
		log.info("deleteDepartment response json=" + json);
		return json;
	}
	
	
	/**
	 * 
	 * 功能描述：创建成员
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月12日
	 */
	public static JSONObject createMember(Member m) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = CREATE_MEMBER_URL.replace(":token", token.getToken());
		log.info("createMember request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",m.toString());
		log.info("createMember response json=" + json);
		return json;
	}
	
	
	/**
	 * 
	 * 功能描述：更新成员
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月12日
	 */
	public static JSONObject updateMember(Member m) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = UPDATE_MEMBER_URL.replace(":token", token.getToken());
		log.info("updateMember request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",m.toString());
		log.info("updateMember response json=" + json);
		return json;
	}
	
	/**
	 * 获取成员
	 * @param userId
	 * @return
	 */
	public static JSONObject getMember(String userId) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = GET_MEMBER_URL.replace(":token", token.getToken()).replace(":userId", userId);
		log.info("getMember request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",null);
		log.info("getMember response json=" + json);
		return json;
	}
	/**
	 * 
	 * 功能描述：删除成员
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月12日
	 */
	public static JSONObject deleteMember(String userId) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = DELETE_MEMBER_URL.replace(":token", token.getToken()).replace(":userId", userId);
		log.info("deleteMember request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",null);
		log.info("deleteMember response json=" + json);
		return json;
	}
	
	/**
	 * 
	 * 功能描述：批量删除成员(暂不开发)
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月12日
	 */
	public static JSONObject batchDeleteMember() {
		return null;
	}
	
	/**
	 * 
	 * 功能描述：查询部门成员
	 * 
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @since 2017年1月12日
	 */
	public static JSONObject listDeptMember(String deptId,String status) {
		AccessToken token = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
		String url = LISTDEPARTMENT_MEMBER_URL.replace(":token", token.getToken()).replace(":deptId",deptId).replace(":status", status);
		log.info("listDeptMember request url=" + url);
		JSONObject json = WeixinUtilForEnterprise.httpRequest(url,"GET",null);
		log.info("listDeptMember response json=" + json);
		return json;
	}
}
    
