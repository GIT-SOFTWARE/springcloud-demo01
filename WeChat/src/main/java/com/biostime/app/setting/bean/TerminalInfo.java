/*
 *  文件创建时间： 2013-9-12
 *  文件创建者: Administrator
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.setting.bean;

import java.util.Date;


public class TerminalInfo {

	private long id;
	private String crmid;
	private String terminalDesc;
	private String underOffice;
	private String provice;
	private String city;
	private String address;
	private String channel;
	private String channelDeatail;
	private String channelType;
	private String fromSystem;
	private int status;
	private String remark;
	private Date createTime;
	private Date updateTime;
	
	
	private String dealerIds;
	private String dName;
	private String name;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCrmid() {
		return crmid;
	}
	public void setCrmid(String crmid) {
		this.crmid = crmid;
	}
	public String getTerminalDesc() {
		return terminalDesc;
	}
	public void setTerminalDesc(String terminalDesc) {
		this.terminalDesc = terminalDesc;
	}
	public String getUnderOffice() {
		return underOffice;
	}
	public void setUnderOffice(String underOffice) {
		this.underOffice = underOffice;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getChannelDeatail() {
		return channelDeatail;
	}
	public void setChannelDeatail(String channelDeatail) {
		this.channelDeatail = channelDeatail;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getFromSystem() {
		return fromSystem;
	}
	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	
	public String getName() {
		if(this.getTerminalDesc()!=null){
			String n = this.getTerminalDesc()+"("+this.id;
			if(!"/".equals(this.crmid)){
				n += "/" + this.crmid;
			}
			n += ")";
			return n;
			
		}else{
			return name;
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDealerIds() {
		return dealerIds;
	}
	public void setDealerIds(String dealerIds) {
		this.dealerIds = dealerIds;
	}
	@Override
	public String toString() {
		return "TerminalInfo [id=" + id + ", crmid=" + crmid
				+ ", terminalDesc=" + terminalDesc + ", underOffice="
				+ underOffice + ", provice=" + provice + ", city=" + city
				+ ", address=" + address + ", channel=" + channel
				+ ", channelDeatail=" + channelDeatail + ", channelType="
				+ channelType + ", fromSystem=" + fromSystem + ", status="
				+ status + ", remark=" + remark + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", dealerIds=" + dealerIds
				+ ", dName=" + dName + ", name=" + name + "]";
	}
	
	
	

}
