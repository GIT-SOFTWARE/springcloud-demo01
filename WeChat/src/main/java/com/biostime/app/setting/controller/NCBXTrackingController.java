package com.biostime.app.setting.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biostime.app.setting.bean.Invoice;
import com.biostime.app.setting.bean.InvoiceElec;
import com.biostime.app.setting.bean.ResultInfo;
import com.biostime.app.setting.service.InvoiceService;
import com.biostime.common.controller.BaseController;
import com.biostime.common.controller.ControllerDispacher;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class NCBXTrackingController extends BaseController{

	private static final Logger logger = Logger
			.getLogger(NCBXTrackingController.class);

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value="/bxtracking/save",method = {RequestMethod.POST })
	public void saveList(HttpServletRequest request,HttpServletResponse response,@RequestBody String jsonStr) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
        response.setHeader("P3P", "CP=CAO PSA OUR");
		try {
			LogUtil.info(logger, LogFormat.PLAIN, "saveList:"+jsonStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray array = json.getJSONArray("billList");
			List<Invoice> invoices = new ArrayList<Invoice>();
			String expressno = "";
			if(array!=null&&array.size()>0){
				for(int i=0;i<array.size();i++){
					Invoice invoice = new Invoice();
					String billno = array.getString(i);
					if(i==0){
						expressno = billno;
					}else{
						invoice.setExpressno(expressno);
						invoice.setBillno(billno);
						invoice.setSentday(date);
						invoices.add(invoice);
					}
				}
			}
			int ret = 0;
			List<String[]> failnos = new ArrayList<String[]>();
			if(invoices.size()>0){
				Map<String,Object> map = invoiceService.saveInvoiceList(invoices);
				ret = (Integer)map.get("success");
				failnos = (List<String[]>)map.get("failnos");
			}
			String msg = "成功签收"+ret+"条记录<br>";
			if(failnos.size()>0){
				for(String[] ss:failnos){
					msg+=ss[0]+"此单据已寄送于"+ss[1]+"快递<br>";
				}
			}

			ControllerDispacher.ajaxWriter(response, new ResultInfo(0, msg));
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, e.getMessage()));
		}
		
	}
	
	@RequestMapping(value="/bxtracking/updateInvoice",method = {RequestMethod.POST })
	public void updateInvoice(HttpServletRequest request,HttpServletResponse response,@RequestBody String jsonStr) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        try {
			LogUtil.info(logger, LogFormat.PLAIN, "updateInvoice:"+jsonStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray array = json.getJSONArray("billList");
			String code = json.getString("userId");
			
			List<Invoice> invoices = new ArrayList<Invoice>();
			if(array!=null&&array.size()>0){
				for(int i=0;i<array.size();i++){
					Invoice invoice = new Invoice();
					String billno = array.getString(i);

					invoice.setBillno(billno);
					invoice.setReceiveday(date);
					invoice.setReceiver(code);
					invoices.add(invoice);
					
				}
			}
			int ret = 0;
			List<String> failnos = new ArrayList<String>();
			if(invoices.size()>0){
				Map<String, Object> map = invoiceService.updateInvoiceList(invoices);
				ret = (Integer)map.get("success");
				failnos = (List<String>)map.get("failnos");
			}
			String msg = "成功签收"+ret+"条记录<br>";
			if(failnos.size()>0){
				for(String s:failnos){
					msg += s+"单据此前已签收过<br>";
				}
			}
			ControllerDispacher.ajaxWriter(response, new ResultInfo(0, msg));
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, e.getMessage()));
		}
		
	}
	
	@RequestMapping(value="/bxtracking/updateInvoiceElec",method = {RequestMethod.POST })
	public void updateInvoiceElec(HttpServletRequest request,HttpServletResponse response,@RequestBody String jsonStr) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
        response.setHeader("P3P", "CP=CAO PSA OUR");
        try {
			LogUtil.info(logger, LogFormat.PLAIN, "updateInvoiceElec:"+jsonStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray array = json.getJSONArray("billList");
			String code = json.getString("userId");
			List<InvoiceElec> invoices = new ArrayList<InvoiceElec>();
			String billno = "";
			if(array!=null&&array.size()>0){
				for(int i=0;i<array.size();i++){
					InvoiceElec invoice = new InvoiceElec();
					String in = array.getString(i);
					if(i==0){
						billno = in;
					}else{
						invoice.setInvoice(in);
						invoice.setBillno(billno);
						invoice.setDay(date);
						invoice.setReceiver(code);
						invoices.add(invoice);
					}
				}
			}
			int ret = 0;
			List<String[]> failnos = new ArrayList<String[]>();
			if(invoices.size()>0){
				Map<String,Object> map = invoiceService.updateInvoiceElecList(invoices);
				ret = (Integer)map.get("success");
				failnos = (List<String[]>)map.get("failnos");
			}
			String msg = "成功签收"+ret+"条记录<br>";
			if(failnos.size()>0){
				for(String[] ss:failnos){
					msg+=ss[0]+"此电子发票已用于"+ss[1]+"单据<br>";
				}
			}
			ControllerDispacher.ajaxWriter(response, new ResultInfo(0, msg));
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, e.getMessage()));
		}
		
	}
	
	
	@RequestMapping(value="/bxtracking/list")
	public void list(HttpServletRequest request,HttpServletResponse response,@RequestBody String jsonStr) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
        response.setHeader("P3P", "CP=CAO PSA OUR");
		try {
			JSONObject json = JSONObject.fromObject(jsonStr);
			String code = json.getString("userId");
			LogUtil.info(logger, LogFormat.PLAIN, "list:"+jsonStr);
			List<Map<String, String>> mybills = invoiceService.queryMyBill(code);
			
			List<Invoice> list = new ArrayList<Invoice>();
			if(mybills!=null&&mybills.size()>0){
				for(Map<String,String> map:mybills){
					Invoice invoice = new Invoice();
					invoice.setBillno(map.get("BILLNO"));
					invoice.setSentday(map.get("SENTDAY"));
					invoice.setReceiveday(map.get("RECEIVEDAY"));
					invoice.setSpday(map.get("APPROVEDAY"));
					list.add(invoice);
				}
			}
			LogUtil.info(logger, LogFormat.PLAIN, "list"+list);
			ControllerDispacher.ajaxWriter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, "系统异常"));
		}
		
	}
	
	
	@RequestMapping(value="/bxtracking/auth")
	public void auth(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
        response.setHeader("P3P", "CP=CAO PSA OUR");
		try {

			List<String> list = invoiceService.getInvoiceAuth();
			
			LogUtil.info(logger, LogFormat.PLAIN, "list"+list);
			ControllerDispacher.ajaxWriter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, "系统异常"));
		}
		
	}
}