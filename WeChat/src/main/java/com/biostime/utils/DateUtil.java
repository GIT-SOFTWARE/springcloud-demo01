package com.biostime.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biostime.common.bean.BaseQuery;
import com.biostime.common.bean.Rule;
import com.biostime.exception.base.ServiceException;

public final class DateUtil {

	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String RANGE_DAY = "day";

	public static final String RANGE_WEEK = "week";
	public static final String RANGE_MONTH = "month";

	/**
	 * 默认的日期格式
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	public static String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_FORMAT_ORACLE = "YYYY-MM-DD HH24:MI:SS";
	
	public static String DATE_FORMAT_ORACLE_HH24 = "YYYY-MM-DD HH24:mm:SS";

	public static SimpleDateFormat SIMPLE_DB_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	public static SimpleDateFormat TIMESTAMP_FORMAT_SDF = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
	
	public static String DATE_FORMAT_ORACLE_YYYY_MM = "yyyy-MM";
	
	public static String DATE_FORMAT_ORACLE_YYYYMM = "yyyyMM";
	
	public static String DATE_FORMAT_ORACLE_YYYY = "yyyy";
	
	public static SimpleDateFormat YYYYMM_FORMAT_SDF = new SimpleDateFormat(DATE_FORMAT_ORACLE_YYYYMM);
	
	public static String DATE_FORMAT_YYYYMMDDHHMMSS ="yyyyMMddHHmmss";
	
	public static String DATE_FORMAT_MM_DD_YYYY ="MM/dd/yyyy";
	public static String DATE_FORMAT_HH_MM_SS ="HH:mm:ss";

	/*
	 * 通过指定日期字符串, 获得日期对象
	 */
	public static Calendar getCalendarByDefineTimeStr(String dateStr, String format) {
		Calendar curr_date = Calendar.getInstance();
		
		SimpleDateFormat s = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = s.parse(dateStr);
			curr_date.setTime(d);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return curr_date;
	}

	/*
	 * 参数:0代表当日; 1, 代表当日加上前一天 2012-05-01 23:59:59
	 */

	public static String getEndDateByDay(int day) {
		String date_str = "";

		Calendar c = Calendar.getInstance();

		// c.add(Calendar.DATE, -day);

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	/*
	 * 参数:0代表当日; 1, 代表当日加上前一天 2012-05-01 00:00:00
	 */

	public static String getStartDateByDay(int day) {
		String date_str = "";

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, -day);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	public static String getStartDate(Calendar c, String format) {
		String date_str = "";

		// Calendar c = Calendar.getInstance();
		if (format.equals("yyyyMM")) {

			c.set(Calendar.DATE, 1);

		}
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	public static String getEndDate(Calendar c, String format) {
		String date_str = "";

		// Calendar c = Calendar.getInstance();
		// c.add(Calendar.MONTH, 1 - month);
		if (format.equals("yyyyMM")) {
			c.add(Calendar.MONTH, 1);

			c.set(Calendar.DATE, 1);

			c.add(Calendar.DATE, -1);

		}

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());
		return date_str;
	}
	
	public static String getYearMonthDate(Calendar c) {
		String dateStr = YYYYMM_FORMAT_SDF.format(c.getTime());
		return dateStr;
	}

	/**
	 * 取得当前日期
	 * 
	 * @return Date 当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回当前日期对应的默认格式的字符串
	 * 
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 返回当前日期对应的(yyyy-MM-dd HH:mm:ss)格式的字符串
	 * 
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentFormatStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_TIMESTAMP_FORMAT);
	}

	/**
	 * 返回当前日期对应的指定格式的字符串
	 * 
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate(String dateFormat) {
		return convertDate2String(getCurrentDate(), dateFormat);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date) {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @param dateFormat
	 *            - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate, String dateFormat) {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			sdf = new SimpleDateFormat(DateUtil.DEFAULT_TIMESTAMP_FORMAT); 
			try {
				return sdf.parse(stringDate);
			} catch (ParseException e) {
				sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT); 
				try {
					return sdf.parse(stringDate);
				} catch (ParseException e1) {
					return new Date(System.currentTimeMillis());
				}				
			}
		}
	}
	public static Date convertString2DateNew(String stringDate, String dateFormat) {
		if(StringUtils.isEmpty(stringDate))return null;
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			sdf = new SimpleDateFormat(DateUtil.DEFAULT_TIMESTAMP_FORMAT); 
			try {
				return sdf.parse(stringDate);
			} catch (ParseException e) {
				sdf = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT); 
				try {
					return sdf.parse(stringDate);
				} catch (ParseException e1) {
					return new Date(System.currentTimeMillis());
				}				
			}
		}
	}

	/**
	 * 将一种格式的日期字符串转换成默认格式的日期字符串
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat), DEFAULT_DATE_FORMAT);
	}

	/**
	 * 将一种格式的日期字符串转换成另一种格式的日期字符串
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @param newFormat
	 *            - 格式化后的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat, String newFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat), newFormat);
	}
	
	/**
	 * 比较两个字符串时间的大小。根据指定格式
	 * @param stringDateOne
	 * @param stringDateTwo
	 * @param format
	 * @return
	 */
    public static int compareTwoDateEqualByFormat(String stringDateOne, String stringDateTwo, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(stringDateOne);
            Date dt2 = df.parse(stringDateTwo);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	

	public static String genSerialNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		return format.format(new Date());
	}

	/**
	 * 对指定的日期增加或减少指定的天数
	 * 
	 * @param date
	 *            需要修改的日期对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一天，amount=1,如果减少一天，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addDay(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 对指定的日期增加或减少指定的月数
	 * 
	 * @param date
	 *            需要修改的日期对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一个月，amount=1,如果减少一个月，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addMonth(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 对指定的日期增加或减少指定的天数
	 * 
	 * @param date
	 *            需要修改的日期时间对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一分钟，amount=1,如果减少一分，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addMinute(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 对指定的日期增加或减少指定的天数
	 * 
	 * @param date
	 *            需要修改的日期对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一天，amount=1,如果减少一天，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addSecond(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, amount);
		return cal.getTime();
	}

	public static String getStartDate(BaseQuery query) {
		String date_str = "";

		if (query.getTimeRange().equals(BaseQuery.RANGE_betweenStartTimeStrNEndTimeStr)) {
			date_str = query.getStartTimeStr();
		}

		if (query.getTimeRange().equals(RANGE_DAY)) {
			date_str = getStartDateByDay(query.getRecentDay());
		}
		if (query.getTimeRange().equals(RANGE_WEEK)) {
			date_str = getStartDateByWeek(query.getRecentWeek());
		}
		if (query.getTimeRange().equals(RANGE_MONTH)) {
			date_str = getStartDateByMonth(query.getRecentMonth());
		}

		return date_str;
	}

	public static String getEndDate(BaseQuery query) {
		String date_str = "";

		if (query.getTimeRange().equals(query.RANGE_betweenStartTimeStrNEndTimeStr)) {
			date_str = query.getEndTimeStr();
		}

		if (query.getTimeRange().equals(RANGE_DAY)) {
			date_str = getEndDateByDay(query.getRecentDay());
		}
		if (query.getTimeRange().equals(RANGE_WEEK)) {
			date_str = getEndDateByWeek(query.getRecentWeek());
		}
		if (query.getTimeRange().equals(RANGE_MONTH)) {
			date_str = getEndDateByMonth(query.getRecentMonth());
		}

		return date_str;
	}

	/*
	 * 参数:0代表本周 ;1, 代表当周加上前一周 2012-7-07 23:59:59
	 */

	public static String getEndDateByWeek(int week) {
		String date_str = "";

		Calendar c = Calendar.getInstance();
		// c.add(Calendar.DATE, -7 * week);

		// int day_of_week_num = c.get(Calendar.DAY_OF_WEEK);

		// c.add(Calendar.DATE, (day_of_week_num - 1));

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	/*
	 * 参数:0代表本周; 1, 代表当周加上前一周 2012-07-01 00:00:00
	 */

	public static String getStartDateByWeek(int week) {
		String date_str = "";

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, -7 * week);

		int day_of_week_num = c.get(Calendar.DAY_OF_WEEK);

		c.add(Calendar.DATE, -(day_of_week_num - 1));

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	/*
	 * 参数:0代表本月; 1, 代表当月加上前一月 2012-07-31 23:59:59
	 */

	public static String getEndDateByMonth(int month) {
		String date_str = "";

		Calendar c = Calendar.getInstance();
		// c.add(Calendar.MONTH, 1 - month);
		c.add(Calendar.MONTH, 1);

		c.set(Calendar.DATE, 1);

		c.add(Calendar.DATE, -1);

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	/*
	 * 参数:0代表本月; 1, 代表当月加上前一月 2012-05-01 00:00:00
	 */

	public static String getStartDateByMonth(int month) {
		String date_str = "";

		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, -month);

		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DATE, 1);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		date_str = TIMESTAMP_FORMAT_SDF.format(c.getTime());

		return date_str;
	}

	public static boolean isDateInSpecFormat(String dateStr, String format) {
		boolean isDateInSpecFormat = true;
		// Calendar curr_date = null;

		if (StringUtils.isEmpty(dateStr)) {
			isDateInSpecFormat = false;
			return isDateInSpecFormat;
		}
		if(dateStr.length()!=format.length()){
			isDateInSpecFormat = false;
			return isDateInSpecFormat;
		}
		SimpleDateFormat s = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = s.parse(dateStr);
			// curr_date.setTime(d);
			String str = s.format(d);
			if(!str.equals(dateStr)){
				isDateInSpecFormat = false;
			}
		} catch (ParseException e) {
			isDateInSpecFormat = false;
			// e.printStackTrace();
		}

		return isDateInSpecFormat;
	}

	/**
	 * 
	 * @param createTime
	 * @param type
	 * @param min
	 * @param curr_date
	 *            备注:curr_date时间-min , 小于createTime时间就返回true
	 *            .意思是,从createTime时间算起,有效时间是min
	 * @return
	 */

	public static boolean isBeforeCurrentTime(Date createTime, String type, Integer min, Calendar curr_date) {

		boolean isValidate = false;

		if (createTime == null) {
			isValidate = true;
			return isValidate;
		}
		if (min == null || min.intValue() == 0) {
			isValidate = true;
			return isValidate;
		}
		if (type == null) {
			isValidate = true;
			return isValidate;
		}
		if (type.equals(Rule.type_min)) {

			curr_date.add(Calendar.MINUTE, -min);

			Calendar cal = Calendar.getInstance();
			cal.setTime(createTime);
			// cal.add(Calendar.MINUTE, min);

			logger.info("cal = " + convertDate2String(cal.getTime(), DEFAULT_TIMESTAMP_FORMAT));
			logger.info("curr - min = " + convertDate2String(curr_date.getTime(), DEFAULT_TIMESTAMP_FORMAT));
			if (curr_date.before(cal)) {
				isValidate = true;
			}
		}

		return isValidate;
	}

	/**
	 * 中文显示日期
	 * 
	 * @param date
	 *            格式为yyyy-MM-dd
	 * @return
	 */
	public static String change2DisplayName(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String todayStr = sdf.format(today);
		if (todayStr.equals(date)) {
			return "今天";
		}
		String yesterdayStr = sdf.format(addDay(today, -1));
		if (yesterdayStr.equals(date)) {
			return "昨天";
		}
		String preYesterdayStr = sdf.format(addDay(today, -2));
		if (preYesterdayStr.equals(date)) {
			return "前天";
		}
		return date;
	}

	public static Date addYear(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}

	public static List getYearList(Date specDate, int amount, String nextOrBefore) {
		String format = "yyyy";
		List l = new ArrayList();
		String year = "";

		//
		if (StringUtils.isNotEmpty(nextOrBefore) && nextOrBefore.equals("next")) {
			for (int i = 0; i < amount; i++) {
				year = convertDate2String(addYear(specDate, i), format);
				l.add(year);
			}
		}
		if (StringUtils.isNotEmpty(nextOrBefore) && nextOrBefore.equals("before")) {
			for (int i = 0; i < amount; i++) {
				year = convertDate2String(addYear(specDate, -i), format);
				l.add(year);
			}
		}

		return l;
	}

	public static List<String> getMonthList() {
		List<String> l = new ArrayList<String>();
		int amount = 12;
		String month = "";
		for (int i = 1; i <= amount; i++) {
			if(i<10){
				month = "0"+i;
			}else{
				month = i + "";
			}
			l.add(month);
		}
		return l;
	}
	
	public static java.util.Date convert2Util(java.sql.Date date){
		java.util.Date d=new java.util.Date (date.getTime());
		return d;
	}
	
	public static java.sql.Date convert2Sql(java.util.Date date){
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	
	public static String getDateQuarter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Integer year = c.get(c.YEAR);
		Integer month = c.get(c.MONTH);
		Integer d = c.get(c.DATE);
		String quarter = "01";
		if(d>=26){
			month = month+1;
		}
		month = month%12;
		if(month == 10||month == 11||month == 12){
			quarter = "01";
		}else if(month == 1||month == 2||month == 3){
			quarter = "02";
		}else if(month == 4||month == 5||month == 6){
			quarter = "03";
		}else if(month == 7||month == 8||month == 9){
			quarter = "04";
		}
		return year + quarter;
	}

	private static String dealMonth(String month) {
		String r = "";
		if (month.length() == 1) {
			r = "0" + month;
		} else {
			r = month;
		}
		return r;
	}

	public static String getDateFormatString(Date date,String format) {
		SimpleDateFormat s = new SimpleDateFormat(format);
		return s.format(date);
	}
	
	public static void main(String[] args) {
		String beginDate = getBiostimeCheckDate(4).get("beginDate");
        String endDate = getBiostimeCheckDate(4).get("endDate");
        System.out.println(beginDate + "==" +  endDate);
//		System.out.println("------"+getDateQuarter(convertString2Date("2015-9-26")));
		//System.out.println(">>>>>>"+DateUtil.convertString2Date(getCurrentStringDate("YYYY-MM-DD")));
		// BaseQuery query = new BaseQuery();
		// query.setTimeRange(RANGE_DAY);
		// query.setRecentDay(30);
		//
		// String sql = " and s.created_time >= to_date('" +
		// DateUtil.getStartDate(query) + "' , '" + DATE_FORMAT_ORACLE + "')";
		//
		// System.out.println(sql);
		//
		// String sql1 = " and s.created_time <= to_date('" +
		// DateUtil.getEndDate(query) + "' , '" + DATE_FORMAT_ORACLE + "')";
		//
		// System.out.println(sql1);

		/*
		 * int min = 2; boolean isValidate = false; String curr_date_str =
		 * "2012-10-30 15:01:59";// "2012-10-30 12:01:59";//; String
		 * cal_date_str = "2012-10-30 12:00:00";
		 * 
		 * Calendar curr = getCalendarByDefineTimeStr(curr_date_str,
		 * DEFAULT_TIMESTAMP_FORMAT); Calendar cal =
		 * getCalendarByDefineTimeStr(cal_date_str, DEFAULT_TIMESTAMP_FORMAT);
		 * 
		 * isValidate = isBeforeCurrentTime(cal.getTime(), "min", min, curr);
		 * System.out.println(isValidate);
		 */

		// List yearList = getYearList(new Date(), 5, "next");

		// List yearList = getYearList(new Date(), 2, "before");
		/*List yearList = getYearList(new Date(), 2, "next");
		for (int i = 0; i < yearList.size(); i++) {
			String year = (String) yearList.get(i);
			System.out.println(year);
		}

		List monthList = getMonthList();
		for (int i = 0; i < monthList.size(); i++) {
			String month = (String) monthList.get(i);
			System.out.println(month);
		}
        
		System.out.println(">>>>>>"+getCurrentStringDate("HH:mm:ss"));*/
		// String rationTimeStr = "201309";
		/*
		 * String rationTimeStr = "20139";
		 * 
		 * Date rationTime = convertString2Date(rationTimeStr, "yyyyMM");
		 * System.out.println(rationTime);
		 */
	}

	
	/**
	 * 
	 * 功能描述：获取本月第一天
	 * 
	 * @param format
	 * @return 
	 * @author 12624HL
	 * @version DEALER 5.0
	 * @throws ServiceException 
	 * @since 2016年2月27日
	 */
	public static String getFirstDayOfCurruntMonth(String format) throws ServiceException {
		String date_str = "";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH,0);
			c.set(Calendar.MILLISECOND, 0);
			c.set(Calendar.DATE, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			date_str = sdf.format(c.getTime());
		}catch(Exception e){
			throw new ServiceException("getFirstDayOfCurruntMonth error:"+format);
		}
		return date_str;
	}
	
	public static Map<String, String> getBiostimeCheckDate(int delayMonth) {
		Map<String, String> checkDate = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);
		//一月从0开始
		int month = calendar.get(Calendar.MONTH) + 1;
		StringBuffer beginDate = new StringBuffer();
		StringBuffer endDate = new StringBuffer();
		endDate.append(year);
		endDate.append("-");
		if(day > 25) {
			endDate.append(getMonth(month + 1));
			calBeginDate(beginDate, calendar, year, month, delayMonth);
		} else {
			endDate.append(getMonth(month));
			calBeginDate(beginDate, calendar, year, (month - 1), delayMonth);
		}
		beginDate.append("-26");
		endDate.append("-25");
		checkDate.put("beginDate", beginDate.toString());
		checkDate.put("endDate", endDate.toString());
		return checkDate;
	}
	
	private static void calBeginDate(StringBuffer beginDate,Calendar calendar, int year, int month, int delayMonth) {
		if(delayMonth > 0) {
			calendar.add(Calendar.MONTH, -(delayMonth-1));
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
		}
		beginDate.append(year);
		beginDate.append("-");
		beginDate.append(getMonth(month));
	}
	
	/**
	 * 
	* 方法描述: 比较两个时间相差多少秒
	*
	* @param fTime
	* @param lTime
	* @return fTime-lTime相差的秒数
	* @author w1025-test10
	* @createDate 2016-8-1 下午3:18:53
	 */
	 public static long compareDiffSeconds(Object fTime, Object lTime){
		 Long seconds = null;
		 try {
			Date f = null;
			 Date l = null;
			 if(fTime instanceof Date){
				 f = (Date)fTime;
			 }else if(fTime instanceof String){
				 String sf = (String)fTime;
				 f = DateUtil.convertString2Date(sf, DateUtil.DEFAULT_TIMESTAMP_FORMAT);
			 }
			 if(lTime instanceof Date){
				 l = (Date)lTime;
			 }else if(lTime instanceof String){
				 String lf = (String)lTime;
				 l = DateUtil.convertString2Date(lf, DateUtil.DEFAULT_TIMESTAMP_FORMAT);
			 }
			seconds = 	(f.getTime()-l.getTime())/1000;
			 
			 return seconds;
		} catch (Exception e) {
			return seconds;
		}
	}
	

	public static Map<String, String> getBiostimeCheckDate() {
		return getBiostimeCheckDate(0);
	}

	private static String getMonth(int month) {
		if(month < 10) return "0" + month;
		else return String.valueOf(month);
	}
	
}
