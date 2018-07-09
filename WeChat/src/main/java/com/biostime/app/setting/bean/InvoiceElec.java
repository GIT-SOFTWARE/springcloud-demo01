package com.biostime.app.setting.bean;

public class InvoiceElec {
	
	private String invoice;
	private String billno;
	private String day;
	
	private String receiver;

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	

	
}

//grant insert on bd_invoice to NCOA
//drop table bd_invoice;
//create table bd_invoice(
//expressno	varchar2(50),
//billno	varchar2(20) UNIQUE,
//sentday	varchar2(19),
//sentmessage	varchar2(200),
//receiveday	varchar2(19),
//receivemessage	varchar2(200),
//receiver varchar2(20)
//)
//drop table bd_invoice_elec;
//create table bd_invoice_elec(
//billno	varchar2(20) UNIQUE,
//invoice	varchar2(100) ,
//day varchar2(19)
//)