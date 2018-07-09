/*
 *  文件创建时间： 2013-10-18
 *  文件创建者: hehuajun
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.bean;

/**
 * 
 * 类功能描述：经销商证照实体类
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhx</a>
 * @version DEALER 4.0
 * @since 2013-10-18 
 *
 */
public class PhotoInfoQuery {
	
	private long photoId;
	
	private String photoType;
	
	private String catalog;
	
	private String photoInformation;
	
	private String isTop;
	
	private String isPop;
	
	private String beginTime;
	
	private String endTime;
	
	private String photoversionType;
	
	private String department;
	
	private Long priceCategory;
	
	private String batch;
	
	private String uBeginTime;
	
	private String uEndTime;
   
	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getPhotoInformation() {
		return photoInformation;
	}

	public void setPhotoInformation(String photoInformation) {
		this.photoInformation = photoInformation;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsPop() {
		return isPop;
	}

	public void setIsPop(String isPop) {
		this.isPop = isPop;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPhotoversionType() {
		return photoversionType;
	}

	public void setPhotoversionType(String photoversionType) {
		this.photoversionType = photoversionType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(Long priceCategory) {
		this.priceCategory = priceCategory;
	}

	@Override
	public String toString() {
		return "PhotoInfoQuery [photoId=" + photoId + ", photoType="
				+ photoType + ", catalog=" + catalog + ", photoInformation="
				+ photoInformation + ", isTop=" + isTop + ", isPop=" + isPop
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", photoversionType=" + photoversionType + ", department="
				+ department + ", priceCategory=" + priceCategory + "]";
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getuBeginTime() {
		return uBeginTime;
	}

	public void setuBeginTime(String uBeginTime) {
		this.uBeginTime = uBeginTime;
	}

	public String getuEndTime() {
		return uEndTime;
	}

	public void setuEndTime(String uEndTime) {
		this.uEndTime = uEndTime;
	}

}
