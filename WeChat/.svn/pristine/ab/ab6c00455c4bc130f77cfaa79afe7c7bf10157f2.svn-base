
package com.biostime.app.setting.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biostime.app.setting.service.ForeignOaService;
import com.biostime.common.controller.BaseController;
import com.biostime.common.controller.ControllerDispacher;
import com.biostime.util.MD5;
import com.biostime.utils.DesUtil;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;


@Controller
public class ForeignOAController extends BaseController{

	private final static Logger logger = LogUtil.getLogger(ForeignOAController.class);
	
	@Autowired
	ForeignOaService foreignOaService;

	@RequestMapping("/setting/GetOAPersonnelInfo")
	public  void getOAPersonnelInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		Long start = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 12);
		String pre = sdf.format(cal.getTime());
		try {
			String xml = request.getParameter("xml");
			xml = DesUtil.decode(pre, xml);
			
			LogUtil.info(logger, LogFormat.PLAIN, "GetOAPersonnelInfo传入的xml:"+xml);
			String workcode = xml.substring(xml.indexOf("<workcode>")+10,xml.indexOf("</workcode>"));
			String lastmoddate = xml.substring(xml.indexOf("<lastmoddate>")+13,xml.indexOf("</lastmoddate>"));
			String loginid = xml.substring(xml.indexOf("<loginid>")+9,xml.indexOf("</loginid>"));
			String password = xml.substring(xml.indexOf("<password>")+10,xml.indexOf("</password>"));
			password = MD5.md5(password);
			Map<String,String> rightMap = foreignOaService.getInterfacepurview(loginid, password);
			if(rightMap==null||rightMap.size()==0){
				throw new Exception("用户验证失败");
			}
			String right = rightMap.get("METHODS");
			checkRight(right,"getOAPersonnelInfo");

			
			List<Map<String, Object>> list = foreignOaService.GetOAPersonnelInfo(workcode, lastmoddate);
			
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			if(list!=null&&list.size()>0){
				retXml+="<state>success</state>";
				for(Map<String,Object> map:list){
					Iterator<String> it = map.keySet().iterator();
					retXml += "<show>";
					while(it.hasNext()){
						String key = it.next();
						String value = map.get(key)==null?"":map.get(key).toString();
						retXml += "<"+key+">"+value+"</"+key+">";
					}
					retXml += "</show>";
				}
			}else{
				retXml+="<state>error</state>";
				retXml+="<message>没有查询到数据</message>";
			}
			retXml+="</result>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOAPersonnelInfo返回的xml:"+retXml);
			ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			LogUtil.info(logger, LogFormat.PLAIN, "GetOAPersonnelInfo------------------------" + (System.currentTimeMillis()-start)/1000 +"s");
			
		} catch (Exception e) {
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			retXml+="<state>error</state>";
			retXml+="<message>"+e.getMessage()+"</message>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOAPersonnelInfo返回的xml:"+retXml);
			try {
				ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	private void checkRight(String right, String method) throws Exception{
		String[] ss = right.split(",");
		boolean flag = false;
		for(String s:ss){
			if(method.equals(s)){
				flag = true;
			}
		}
		if(!flag){
			throw new Exception("该用户没有查询权限");
		}
	}



	@RequestMapping("/setting/GetOASegmentInfo")
	public  void getOASegmentInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		Long start = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 12);
		String pre = sdf.format(cal.getTime());
		try {
			String xml = request.getParameter("xml");
			xml = DesUtil.decode(pre, xml);
			LogUtil.info(logger, LogFormat.PLAIN, "GetOASegmentInfo传入的xml:"+xml);
			String loginid = xml.substring(xml.indexOf("<loginid>")+9,xml.indexOf("</loginid>"));
			String password = xml.substring(xml.indexOf("<password>")+10,xml.indexOf("</password>"));
			password = MD5.md5(password);
			Map<String,String> rightMap = foreignOaService.getInterfacepurview(loginid, password);
			if(rightMap==null||rightMap.size()==0){
				throw new Exception("用户验证失败");
			}
			String right = rightMap.get("METHODS");
			checkRight(right,"getOASegmentInfo");
			
			List<Map<String, Object>> list = foreignOaService.GetOASegmentInfo();
			
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			if(list!=null&&list.size()>0){
				retXml+="<state>success</state>";
				for(Map<String,Object> map:list){
					Iterator<String> it = map.keySet().iterator();
					retXml += "<show>";
					while(it.hasNext()){
						String key = it.next();
						String value = map.get(key)==null?"":map.get(key).toString();
						retXml += "<"+key+">"+value+"</"+key+">";
					}
					retXml += "</show>";
				}
			}else{
				retXml+="<state>error</state>";
				retXml+="<message>没有查询到数据</message>";
			}
			retXml+="</result>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOASegmentInfo返回的xml:"+retXml);
			ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			LogUtil.info(logger, LogFormat.PLAIN, "GetOASegmentInfo------------------------" + (System.currentTimeMillis()-start)/1000 +"s");
			
		} catch (Exception e) {
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			retXml+="<state>error</state>";
			retXml+="<message>"+e.getMessage()+"</message>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOASegmentInfo返回的xml:"+retXml);
			try {
				ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping("/setting/GetOADepartmentInfo")
	public  void getOADepartmentInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		Long start = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 12);
		String pre = sdf.format(cal.getTime());
		try {
			String xml = request.getParameter("xml");
			xml = DesUtil.decode(pre, xml);
			LogUtil.info(logger, LogFormat.PLAIN, "GetOADepartmentInfo传入的xml:"+xml);
			String loginid = xml.substring(xml.indexOf("<loginid>")+9,xml.indexOf("</loginid>"));
			String password = xml.substring(xml.indexOf("<password>")+10,xml.indexOf("</password>"));
			password = MD5.md5(password);
			Map<String,String> rightMap = foreignOaService.getInterfacepurview(loginid, password);
			if(rightMap==null||rightMap.size()==0){
				throw new Exception("用户验证失败");
			}
			String right = rightMap.get("METHODS");
			checkRight(right,"getOADepartmentInfo");
			List<Map<String, Object>> list = foreignOaService.GetOADepartmentInfo();
			
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			if(list!=null&&list.size()>0){
				retXml+="<state>success</state>";
				for(Map<String,Object> map:list){
					Iterator<String> it = map.keySet().iterator();
					retXml += "<show>";
					while(it.hasNext()){
						String key = it.next();
						String value = map.get(key)==null?"":map.get(key).toString();
						retXml += "<"+key+">"+value+"</"+key+">";
					}
					retXml += "</show>";
				}
			}else{
				retXml+="<state>error</state>";
				retXml+="<message>没有查询到数据</message>";
			}
			retXml+="</result>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOADepartmentInfo返回的xml:"+retXml);
			ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			
			LogUtil.info(logger, LogFormat.PLAIN, "GetOADepartmentInfo------------------------" + (System.currentTimeMillis()-start)/1000 +"s");
			
		} catch (Exception e) {
			String retXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
			retXml+="<result>";
			retXml+="<state>error</state>";
			retXml+="<message>"+e.getMessage()+"</message>";
			LogUtil.info(logger, LogFormat.PLAIN, "GetOADepartmentInfo返回的xml:"+retXml);
			try {
				ControllerDispacher.ajaxWriter(response,DesUtil.encrypt(pre, retXml));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
