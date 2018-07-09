/*
 *  文件创建时间： 2013-9-2
 *  文件创建者: Administrator
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 类功能描述：TODO
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:Administrator@biostime.com">Administrator</a>
 * @version 1.0
 * @since 2013-9-2 
 *
 */
public class NoticeInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6346230833080928826L;

	private long id;
	private String title;
	private String department;
	private String content;
	private int status;
	private Date reviewTime;
	private String reviewBy;
	private int isTop;
	private int isPop;
	private int isDod;
	private int isDoe;

	private int noticeType;
	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;
	private Long catalog;
	private String summary;
	private String photoNumber;
	private int pubObject; 
	
	private List<Map<String, Object>>	photoPathList;
	
	public int getPubObject() {
		return pubObject;
	}
	public void setPubObject(int pubObject) {
		this.pubObject = pubObject;
	}

	public String getPhotoNumber() {
		return photoNumber;
	}
	public void setPhotoNumber(String photoNumber) {
		this.photoNumber = photoNumber;
	}
	public List<Map<String, Object>> getPhotoPathList() {
		return photoPathList;
	}
	public void setPhotoPathList(List<Map<String, Object>> photoPathList) {
		this.photoPathList = photoPathList;
	}
	private List<Long> priceCategory;
	private List<String> files;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getReviewBy() {
		return reviewBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public int getIsPop() {
		return isPop;
	}
	public void setIsPop(int isPop) {
		this.isPop = isPop;
	}
	public int getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(int noticeType) {
		this.noticeType = noticeType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public List<Long> getPriceCategory() {
		return priceCategory;
	}
	public void setPriceCategory(List<Long> priceCategory) {
		this.priceCategory = priceCategory;
	}
	public Long getCatalog() {
		return catalog;
	}
	public void setCatalog(Long catalog) {
		this.catalog = catalog;
	}
	
	public int getIsDod() {
		return isDod;
	}
	public void setIsDod(int isDod) {
		this.isDod = isDod;
	}
	public int getIsDoe() {
		return isDoe;
	}
	public void setIsDoe(int isDoe) {
		this.isDoe = isDoe;
	}
	
	@Override
	public String toString() {
		return "NoticeInfo [id=" + id + ", title=" + title + ", department="
				+ department + ", content=" + content + ", status=" + status
				+ ", reviewTime=" + reviewTime + ", reviewBy=" + reviewBy
				+ ", isTop=" + isTop + ", isPop=" + isPop + ", noticeType="
				+ noticeType + ", createTime=" + createTime + ", createBy="
				+ createBy + ", updateTime=" + updateTime + ", updateBy="
				+ updateBy + ", catalog=" + catalog + ", priceCategory="
				+ priceCategory + ", summary=" + summary +",photoNumber="+photoNumber+"]";
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	
}
