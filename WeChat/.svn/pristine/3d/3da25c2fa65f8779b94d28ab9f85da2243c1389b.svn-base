package com.biostime.common.pager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Page<T> {
	// 每页显示记录
	private Integer rows;
	// 开始查询的页，默认从第一页开始查询
	private Integer pageNum = 1;
	// 总记录数
	private Integer recordCount = 0;
	// 总页数
	private Integer pageCount = 0;
	// 排序的字段
	private String sortField;
	// asc,desc
	private String orderType;
	// 实例list
	private List<T> list;

	private String searchStr;

	private String requestUrl;

	private String beginPageLink;

	private String prevPageLink;

	private String nextPageLink;

	private String lastPageLink;

	public Page() {

	}

	public Page(Integer pageNum, Integer rows) {
		if (pageNum != null && rows != null) {
			this.rows = Integer.valueOf(rows);
			this.pageNum = Integer.valueOf(pageNum);

		}
	}

	public Page(Integer pageNum, Integer rows, String sortField, String orderType) {
		if (pageNum != null && rows != null) {
			this.rows = Integer.valueOf(rows);
			this.pageNum = Integer.valueOf(pageNum);
			this.sortField = sortField;
			if (StringUtils.isBlank(sortField) || "null".equals(sortField)) {
				this.sortField = null;
			}
			/*if (this.sortField != null) {
				this.sortField = this.sortField.replace("_", ".");
			}*/
			this.orderType = orderType;
		}
	}

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public Integer getPageCount() {
		return recordCount % rows == 0 ? recordCount / rows : recordCount / rows + 1;
	}

	/**
	 * 数据库查询时，从第几条记录开始查询
	 * 
	 * @return
	 */
	public int getFirstResult() {
		int firstResult = (pageNum - 1) * rows;
		return firstResult <= 0 ? 0 : firstResult;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public String getSortField() {

		if (sortField == null || StringUtils.isEmpty(sortField) || sortField.equals("undefined")) {
			sortField = "";
		}
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrderType() {
		if (orderType == null || StringUtils.isEmpty(orderType) || orderType.equals("undefined")) {
			orderType = "";
		}
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getOrderBy() {
		if (this.sortField == null) {
			return "";
		}
		if(this.orderType == null){
			this.orderType = "asc";
		}
		return this.sortField + " " + this.orderType;
	}
	
	public void addOrderBy(String orderBy) {
		String preOrderBy = getOrderBy();
		if(StringUtils.isBlank(preOrderBy)){
			this.sortField = orderBy;
		}else{
			this.sortField = preOrderBy + "," + orderBy;
		}
		this.orderType = "";
	}
	
	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getRequestUrl() {
		if (requestUrl != null && requestUrl.indexOf("?") == -1) {
			requestUrl = requestUrl + "?s=";
		}

		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getBeginPageLink() {

		if (getPageNum() == 1) {
			this.beginPageLink = "javascript:void(0)";
		} else {
			this.beginPageLink = getRequestUrl() + "&pageNum=1" + "&pageRow=" + getRows() + getSearchStr();
		}

		return beginPageLink;
	}

	public void setBeginPageLink(String beginPageLink) {
		this.beginPageLink = beginPageLink;
	}

	public String getPrevPageLink() {
		if (getPageNum().intValue() - 1 > 0) {
			this.prevPageLink = getRequestUrl() + "&pageNum=" + (getPageNum() - 1) + "&pageRow=" + getRows() + getSearchStr();
		} else {
			this.prevPageLink = "javascript:void(0)";
		}

		return prevPageLink;
	}

	public void setPrevPageLink(String prevPageLink) {
		this.prevPageLink = prevPageLink;
	}

	public String getNextPageLink() {

		if (getPageNum().intValue() + 1 <= getPageCount().intValue()) {
			this.nextPageLink = getRequestUrl() + "&pageNum=" + (getPageNum() + 1) + "&pageRow=" + getRows() + getSearchStr();
		} else {
			this.nextPageLink = "javascript:void(0)";
		}

		return nextPageLink;
	}

	public void setNextPageLink(String nextPageLink) {
		this.nextPageLink = nextPageLink;
	}

	public String getLastPageLink() {

		// if (getPageCount().intValue() > 0 && getPageNum().intValue() !=
		// getPageCount().intValue()) {
		if (getPageCount().intValue() > getPageNum()) {
			this.lastPageLink = getRequestUrl() + "&pageNum=" + getPageCount() + "&pageRow=" + getRows() + getSearchStr();
		} else {
			this.lastPageLink = "javascript:void(0)";
		}

		return lastPageLink;
	}

	public void setLastPageLink(String lastPageLink) {
		this.lastPageLink = lastPageLink;
	}

	@Override
	public String toString() {
		return "Page [rows=" + rows + ", pageNum=" + pageNum + ", recordCount="
				+ recordCount + ", pageCount=" + pageCount + ", sortField="
				+ sortField + ", orderType=" + orderType + ", list=" + list
				+ ", searchStr=" + searchStr + ", requestUrl=" + requestUrl
				+ ", beginPageLink=" + beginPageLink + ", prevPageLink="
				+ prevPageLink + ", nextPageLink=" + nextPageLink
				+ ", lastPageLink=" + lastPageLink + "]";
	}

}
