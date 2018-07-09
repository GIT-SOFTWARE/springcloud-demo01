/*
 *  文件创建时间： 2013-10-18
 *  文件创建者: wuzhx
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.biostime.entity.base.BaseEntity;

/**
 * 
 * 类功能描述：政策动态实体类
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author <a href="mailto:wuzhixiong@biostime.com">wuzhx</a>
 * @version DEALER 4.0
 * @since 2013-10-18 
 *
 */
@Entity
@Table(name = "DL_NOTICE")
public class Notice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 341300311283660217L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="photo")
	@SequenceGenerator(name="photo",sequenceName="SEQ_NOTICE")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "TITLE", length = 300)
	private String title;
	
	@Column(name = "DEPARTMENT", length = 50)
	private String department;
	
	@Lob
	@Column(name="CONTENT", columnDefinition="CLOB") 
	private String content;
	
	@Column(name = "STATUS",precision = 1,scale = 0)
	private int status;
	
	@Column(name = "REVIEW_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewTime;
	
	@Column(name = "REVIEW_BY", length = 50)
	private String reviewBy;
	
	@Column(name = "IS_TOP",precision = 1,scale = 0)
	private int isTop;
	
	@Column(name = "IS_POP",precision = 1,scale = 0)
	private int isPop;
	
	@Column(name = "IS_DOD",precision = 1,scale = 0)
	private int isDod;
	
	@Column(name = "IS_DOE",precision = 1,scale = 0)
	private int isDoe;
	
	@Column(name = "PHOTO_NUMBER", length = 20)
	private String photoNumber;

	@Column(name = "NOTICE_TYPE",precision = 2,scale = 0)
	private int noticeType;
	
	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "CREATE_BY", length = 50)
	private String createBy;
	
	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(name = "UPDATE_BY", length = 50)
	private String updateBy;

	@Column(name = "CATALOG",precision = 20,scale = 0)
	private Long catalog;
	
	@Lob
	@Column(name="SUMMARY", columnDefinition="CLOB") 
	private String summary;
	
	@Column(name = "PUB_OBJECT",precision = 1,scale = 0)
	private int pubObject;
	
	public int getPubObject() {
		return pubObject;
	}

	public void setPubObject(int pubObject) {
		this.pubObject = pubObject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getId() {
		return id;
	}

	public String getPhotoNumber() {
		return photoNumber;
	}

	public void setPhotoNumber(String photoNumber) {
		this.photoNumber = photoNumber;
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

	public Long getCatalog() {
		return catalog;
	}

	public void setCatalog(Long catalog) {
		this.catalog = catalog;
	}
}
