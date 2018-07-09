package com.biostime.common.controller;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;

import com.biostime.annotation.DispacherMapping;
import com.biostime.annotation.enums.MappingScope;
import com.biostime.common.pager.Page;
import com.biostime.util.ParamUtil;

/**
 * 
 * 类功能描述：controller基类，所有controller必需继承此类
 * 
 * 本类规定了全局的mapping地址变量。页面转发非模板配置的全局页面转发地址均在此配置
 * 
 * <p>
 * 版权所有：biostime.com
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhixiong</a>
 * @version DEALER1.0
 * @since 2013-6-22
 * 
 */
@DispacherMapping(scope = MappingScope.GLOBAL, mapping = { "template.relogin=/standard/relogon", "template.error=/standard/error",
		"template.msg=/standard/msg", "relogin=/jsp/relogon", "error=/jsp/error",
		"login=/jsp/index", "dealerLogin=/jsp/dealerlogin", "supplierLogin=/jsp/supplierLogin", 
		"terminalLogin=/jsp/terminalLogin", "result=/jsp/result" })
public abstract class BaseController {

	private static Logger log = Logger.getLogger(BaseController.class.getName());

	protected String getControllerName(ModelMap modelMap) {
		if (modelMap.containsKey("clazz"))
			return (String) modelMap.get("clazz");
		return "com.biostime.common.controller.BaseController";
	}

	protected Page getPage(HttpServletRequest request) {

		Page page = new Page();
		String url = request.getRequestURI();

		String userPageRow = request.getParameter("userPageRow");
		String pageNum = request.getParameter("pageNum");
		String pageRow = request.getParameter("pageRow");
		if (StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		try{
			pageRow = Integer.parseInt(pageRow) + "";
		}catch(Exception e){
			pageRow = 10 + "";
		}
		page.setRequestUrl(url.toString());
		if (StringUtils.isNotEmpty(userPageRow) && !userPageRow.equals(pageRow)) {
			page.setRows(ParamUtil.toInt(userPageRow));
			pageNum = "1";
		} else {
			page.setRows(ParamUtil.toInt(pageRow));
		}
		page.setPageNum(ParamUtil.toInt(pageNum));

		return page;

	}

	/**
	 * 分页处理
	 * 
	 * @param request
	 * @return
	 */
	protected <T> Page<T> fillPage(HttpServletRequest request) {
		int pageNum = 1;
		int rows = 10;
		String sortField = "";
		String orderType = "";
		String pageRow = request.getParameter("pageRow");

		if (!StringUtils.isEmpty(pageRow) && !pageRow.equals("undefined")) {
			rows = Integer.parseInt(request.getParameter("pageRow"));
		}
		if (!StringUtils.isEmpty(request.getParameter("pageNum"))) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (!StringUtils.isEmpty(request.getParameter("sortField"))) {
			sortField = request.getParameter("sortField");
		}
		if (!StringUtils.isEmpty(request.getParameter("orderByType"))) {
			orderType = request.getParameter("orderByType");
		}
		Page<T> page = new Page<T>(pageNum, rows, sortField, orderType);
		return page;
	}

	


	protected void copyProperties(Object dest, Object src) throws IllegalAccessException, InvocationTargetException {

		// ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		// ConvertUtils.register(new BigDecimalConverter(null),
		// java.math.BigDecimal.class);
		BeanUtils.copyProperties(dest, src);

	}

	protected HttpHeaders setExportHeader(byte[] documentBody, String fileName) throws UnsupportedEncodingException {

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("text", "plain"));
		header.set("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
		header.setContentLength(documentBody.length);
		return header;
	}


	
	/**
	 * request获取参数处理(去前后空格,保证返回不为NULL,不抛出异常,异常返回"")
	 *
	 * @param request
	 * @param kw
	 * @return
	 */
	public String get(HttpServletRequest request, String kw) {
		try {
			return ParamUtil.toStr("undefined".equalsIgnoreCase(request.getParameter(kw))?"":request.getParameter(kw));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessange:" + e.getMessage() + "-errorDesc:转换参数出错！");
			return "";
		}
	}
	
	/**
	 * request获取参数处理(获取为int类型,去前后空格,保证返回不为NULL,不抛出异常,异常返回0)
	 *
	 * @param request
	 * @param kw
	 * @return
	 */
	public int getInt(HttpServletRequest request, String kw) {
		try {
			return ParamUtil.toInt("undefined".equalsIgnoreCase(request.getParameter(kw))?"":request.getParameter(kw));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessange:" + e.getMessage() + "-errorDesc:转换参数出错！");
			return 0;
		}
	}
	
	/**
	 * request获取参数处理(获取为Long类型,去前后空格,保证返回不为NULL,不抛出异常,异常返回0)
	 *
	 * @param request
	 * @param kw
	 * @return
	 */
	public Long getLong(HttpServletRequest request, String kw) {
		try {
			return ParamUtil.toLong("undefined".equalsIgnoreCase(request.getParameter(kw))?"":request.getParameter(kw));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "errorMessange:" + e.getMessage() + "-errorDesc:转换参数出错！");
			return 0L;
		}
	}
	
	public void reqConsole(HttpServletRequest request){
		try {
			//请求url 如："/order/dealerOrderSettingRecList."
			String requestAddr = request.getRequestURI();
			//请求参数 如：
			Map map = request.getParameterMap();
			LogUtil.info(log, LogFormat.PLAIN, requestAddr+"提交参数信息:"+JSONObject.fromObject(map));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "reqConsole errorMessange:" + e.getMessage());
		}
	}
	
	
}
