package com.biostime.app.setting.bean;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class ProductInfo {

	private Long id;
	private String ufCode;
	private Integer itemCategory;
	private String productName;
	private String spec;
	private Integer box;
	private BigDecimal retailPrice;
	private Integer qualityPeriod;
	private Integer firstWarn;
	private Integer secondWarn;
	private Integer status;
	private String remarks;
	private Long stockDisplay;
	private String upc;
	private Integer isSa;
	private String isSaFront;
	private Integer stockQuota;
	private String stockQuotaFront;
	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;
	
	private BigDecimal rebate;
	private Integer isAssembles;
	private String productShortName; 
	private String unit; 
	private String ufCodeU8;
	private String bizUint;
	//
	private String itemName;
	private Integer categoryType;
	private String categoryName;

	private String statusFront;

	// ration
	private Long dealerId;
	private Date rationTime;
	private Integer rationQuantity;
	private Integer rationRemain;

	private String dealerName;
	
	private String qualityPeriodStr;
	
	private Date productTime;//生产时间
	private Date aeadTime;//失效时间

	public String getQualityPeriodStr() {
		return qualityPeriodStr;
	}

	public void setQualityPeriodStr(String qualityPeriodStr) {
		this.qualityPeriodStr = qualityPeriodStr;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public Date getRationTime() {
		return rationTime;
	}

	public void setRationTime(Date rationTime) {
		this.rationTime = rationTime;
	}

	public Integer getRationQuantity() {
		return rationQuantity;
	}

	public void setRationQuantity(Integer rationQuantity) {
		this.rationQuantity = rationQuantity;
	}

	public Integer getRationRemain() {
		return rationRemain;
	}

	public void setRationRemain(Integer rationRemain) {
		this.rationRemain = rationRemain;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUfCode() {
		return ufCode;
	}

	public void setUfCode(String ufCode) {
		this.ufCode = ufCode;
	}

	public Integer getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(Integer itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getBox() {
		return box;
	}

	public void setBox(Integer box) {
		this.box = box;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Integer getQualityPeriod() {
		return qualityPeriod;
	}

	public void setQualityPeriod(Integer qualityPeriod) {
		this.qualityPeriod = qualityPeriod;
	}

	public Integer getFirstWarn() {
		return firstWarn;
	}

	public void setFirstWarn(Integer firstWarn) {
		this.firstWarn = firstWarn;
	}

	public Integer getSecondWarn() {
		return secondWarn;
	}

	public void setSecondWarn(Integer secondWarn) {
		this.secondWarn = secondWarn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getStockDisplay() {
		if(stockDisplay==null||0L==stockDisplay){
			return 10000L;
		}else{
			return stockDisplay;
		}
	}

	public void setStockDisplay(Long stockDisplay) {
		this.stockDisplay = stockDisplay;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public Integer getIsSa() {
		return isSa;
	}

	public void setIsSa(Integer isSa) {
		this.isSa = isSa;
	}

	public Integer getStockQuota() {
		return stockQuota;
	}

	public void setStockQuota(Integer stockQuota) {
		this.stockQuota = stockQuota;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatusFront() {
		if (this.status != null && status == 1) {
			statusFront = "启用";
		}

		if (this.status != null && status == 0) {
			statusFront = "停用";
		}

		return statusFront;
	}

	public void setStatusFront(String statusFront) {
		this.statusFront = statusFront;
	}

	public String getIsSaFront() {

		if (this.isSa != null && isSa == 1) {
			isSaFront = "是";
		}

		if (this.isSa != null && isSa == 0) {
			isSaFront = "否";
		}

		return isSaFront;
	}

	public Date getProductTime() {
		return productTime;
	}

	public void setProductTime(Date productTime) {
		this.productTime = productTime;
	}

	public Date getAeadTime() {
		return aeadTime;
	}

	public void setAeadTime(Date aeadTime) {
		this.aeadTime = aeadTime;
	}

	public void setIsSaFront(String isSaFront) {
		this.isSaFront = isSaFront;
	}

	public String getStockQuotaFront() {
		if (this.stockQuota != null && stockQuota == 1) {
			stockQuotaFront = "是";
		}
		if (this.stockQuota != null && stockQuota == 2) {
			stockQuotaFront = "否";
		}
		if (this.stockQuota != null && stockQuota == 0) {
			stockQuotaFront = "无库存";
		}
		return stockQuotaFront;
	}

	public void setStockQuotaFront(String stockQuotaFront) {
		this.stockQuotaFront = stockQuotaFront;
	}

	public String getUfCodeU8() {
		return ufCodeU8;
	}

	public void setUfCodeU8(String ufCodeU8) {
		this.ufCodeU8 = ufCodeU8;
	}

	public Integer getIsAssembles() {
		return isAssembles;
	}

	public void setIsAssembles(Integer isAssembles) {
		this.isAssembles = isAssembles;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBizUint() {
		return bizUint;
	}

	public void setBizUint(String bizUint) {
		this.bizUint = bizUint;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
	
}
