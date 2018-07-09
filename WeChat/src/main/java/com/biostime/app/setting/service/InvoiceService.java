package com.biostime.app.setting.service;

import java.util.List;
import java.util.Map;

import com.biostime.app.setting.bean.Invoice;
import com.biostime.app.setting.bean.InvoiceElec;

public interface InvoiceService {
	
	public Map<String,Object> saveInvoiceList(List<Invoice> invoices);
	
	public Map<String,Object> updateInvoiceList(List<Invoice> invoices);
	
	public Map<String,Object> updateInvoiceElecList(List<InvoiceElec> invoices);
	
	public List<Map<String, String>> queryMyBill(String code);
	
	public List<String> getInvoiceAuth();
}
