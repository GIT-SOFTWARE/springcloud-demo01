package com.biostime.app.setting.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biostime.app.setting.bean.Invoice;
import com.biostime.app.setting.bean.InvoiceElec;
import com.biostime.app.setting.repository.InvoiceRepository;
import com.biostime.app.setting.service.InvoiceService;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	private static final Logger log = Logger.getLogger(InvoiceServiceImpl.class);
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public Map<String,Object> saveInvoiceList(List<Invoice> invoices) {
		return invoiceRepository.saveInvoiceList(invoices);
	}

	@Override
	public Map<String,Object> updateInvoiceList(List<Invoice> invoices) {
		return invoiceRepository.updateInvoiceList(invoices);
	}

	@Override
	public Map<String,Object> updateInvoiceElecList(List<InvoiceElec> invoices) {
		return invoiceRepository.updateInvoiceElecList(invoices);
	}

	public List<Map<String, String>> queryMyBill(String code){
		return invoiceRepository.queryMyBill(code);
	}
	
	public List<String> getInvoiceAuth(){
		List<Map<String, String>> listmap = invoiceRepository.getInvoiceAuth();
		List<String> list = new ArrayList<String>();
		if(listmap!=null&&listmap.size()>0){
			for(Map<String,String> map:listmap){
				String auth = map.get("CODE");
				list.add(auth);
			}
		}
		LogUtil.info(log, LogFormat.PLAIN, "service getInvoiceAuth:"+list);
		return list;
	}
}
