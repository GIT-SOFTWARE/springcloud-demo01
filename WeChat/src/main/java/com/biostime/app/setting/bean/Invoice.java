package com.biostime.app.setting.bean;

public class Invoice {
	
	private String expressno;
	private String billno;
	private String sentday;
	private String sentmessage;
	private String receiveday;
	private String receivemessage;
	
	private String receiver;
	
	private String spday;
	
	private String ispay;
	
	
	
	public String getIspay() {
		return ispay;
	}



	public void setIspay(String ispay) {
		this.ispay = ispay;
	}



	public String getSpday() {
		return spday;
	}



	public void setSpday(String spday) {
		this.spday = spday;
	}



	public String getReceiver() {
		return receiver;
	}



	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	public String getExpressno() {
		return expressno;
	}



	public void setExpressno(String expressno) {
		this.expressno = expressno;
	}



	public String getBillno() {
		return billno;
	}



	public void setBillno(String billno) {
		this.billno = billno;
	}



	public String getSentday() {
		return sentday;
	}



	public void setSentday(String sentday) {
		this.sentday = sentday;
	}



	public String getSentmessage() {
		return sentmessage;
	}



	public void setSentmessage(String sentmessage) {
		this.sentmessage = sentmessage;
	}



	public String getReceiveday() {
		return receiveday;
	}



	public void setReceiveday(String receiveday) {
		this.receiveday = receiveday;
	}



	public String getReceivemessage() {
		return receivemessage;
	}



	public void setReceivemessage(String receivemessage) {
		this.receivemessage = receivemessage;
	}



	@Override
	public String toString() {
		return "Invoice [expressno=" + expressno + ", billno=" + billno + ", sentday=" + sentday + ", sentmessage="
				+ sentmessage + ", receiveday=" + receiveday + ", receivemessage=" + receivemessage + ", receiver="
				+ receiver + ", spday=" + spday + "]";
	}

	
	
}

//grant insert on bd_invoice to NC63
//drop table bd_invoice;
//create table bd_invoice(
//expressno	varchar2(50),
//billno	varchar2(20) UNIQUE,
//sentday	varchar2(19),
//sentmessage	varchar2(200),
//receiveday	varchar2(19),
//receivemessage	varchar2(200),
//receiver varchar2(20),
//ispay varchar2(1)
//)
//drop table bd_invoice_elec;
//create table bd_invoice_elec(
//billno	varchar2(20),
//invoice	varchar2(100) UNIQUE,
//day varchar2(19),
//receiver varchar2(20)
//)