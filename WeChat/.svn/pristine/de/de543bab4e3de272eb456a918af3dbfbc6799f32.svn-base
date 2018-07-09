package com.biostime.common.bean;

import org.apache.commons.lang.StringUtils;

public class BaseQuery {

	public static final String RANGE_MONTH = "month";

	public static final String RANGE_betweenStartTimeStrNEndTimeStr = "betweenStartTimeStrNEndTimeStr";

	private String startTimeSearchStr;

	private String endTimeSearchStr;

	private Integer categoryType;

	// 排序的字段
	private String sortField;
	// asc,desc
	private String orderType;

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

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * 查询时间范围
	 */
	private String timeRange;

	/*
	 * 指定某个时间的开始时间 , 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	private String startTimeStr;

	/*
	 * 指定某个时间的结束时间 , 时间格式 yyyy-MM-dd HH:mm:ss
	 */
	private String endTimeStr;

	/**
	 * 是否在第一页跳过计算总数
	 */
	private boolean isSkipCountExceptPageOne;

	/**
	 * 在isSkipCountExceptPageOne=true时,设置记录总数
	 */
	private int totalCountWhenSkipCountExceptPageOne;

	/*
	 * 默认0,是当天 , 最近几天 , 结合参数timeRange
	 */

	private Integer recentDay;

	/*
	 * 默认0,是本周 , 最近几周 , 结合参数timeRange
	 */

	private Integer recentWeek;

	/*
	 * 默认0 , 本月 , 最近几月 , 结合参数timeRange
	 */

	private Integer recentMonth;

	public Integer getRecentDay() {
		return recentDay;
	}

	public void setRecentDay(Integer recentDay) {
		this.recentDay = recentDay;
	}

	public Integer getRecentWeek() {
		return recentWeek;
	}

	public void setRecentWeek(Integer recentWeek) {
		this.recentWeek = recentWeek;
	}

	public Integer getRecentMonth() {
		return recentMonth;
	}

	public void setRecentMonth(Integer recentMonth) {
		this.recentMonth = recentMonth;
	}

	public boolean isSkipCountExceptPageOne() {
		return isSkipCountExceptPageOne;
	}

	public void setSkipCountExceptPageOne(boolean isSkipCountExceptPageOne) {
		this.isSkipCountExceptPageOne = isSkipCountExceptPageOne;
	}

	public int getTotalCountWhenSkipCountExceptPageOne() {
		return totalCountWhenSkipCountExceptPageOne;
	}

	public void setTotalCountWhenSkipCountExceptPageOne(int totalCountWhenSkipCountExceptPageOne) {
		this.totalCountWhenSkipCountExceptPageOne = totalCountWhenSkipCountExceptPageOne;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getStartTimeSearchStr() {
		return startTimeSearchStr;
	}

	public void setStartTimeSearchStr(String startTimeSearchStr) {
		this.startTimeSearchStr = startTimeSearchStr;
	}

	public String getEndTimeSearchStr() {
		return endTimeSearchStr;
	}

	public void setEndTimeSearchStr(String endTimeSearchStr) {
		this.endTimeSearchStr = endTimeSearchStr;
	}

}
