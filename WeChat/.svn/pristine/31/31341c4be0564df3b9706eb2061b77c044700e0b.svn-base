package com.biostime.util;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 类功能描述: 常用工具类
 * 
 * 转换类方法 
 * 1.转换字符串(去前后空格) toStr(Object ov) 
 * 需要不抛出异常用toStr(Object ov,Boolean flag) 
 * 2.转换整型: toInt(Object ov) 
 * 需要不抛出异常用toInt(Object ov,Boolean flag)
 * 3.转换为double(注意:不能转小数,有小数用toBigDecimal) toDouble(Object ov) 
 * 需要不抛出异常用toDouble(Object ov,Boolean flag)
 * 4.转换为Long(注意:不能转小数,有小数用toBigDecimal) toLong(Object ov)
 * 需要不抛出异常用toLong(Object ov,Boolean flag) 
 * 5.转换为BigDecimal toBigDecimal(Object ov) 
 * 需要不抛出异常用toBigDecimal(Object ov,Boolean flag)
 * 
 * 判断类方法 
 * 1.判断是空List isNullList(List listOb) 
 * 2.判断是空Map isNullMap(Map mapOb)
 * 3.是否等于0 isZero(Object ov) 转换为BigDecimal类型比较 
 * 4.是否符合BigDecimal类型转换 isBigDecimal(Object str) 
 * 5.整数校验 isInteger(Object number) 
 * 6.判断是邮件地址 judgeEmail(Object email)
 * 
 * 功能类方法 
 * 1.去除List的重复值 listValueUniq(List list) 
 * 2.map根据key值排序 sortMapByKey(Map<String, String> map
 * @version 1.0
 * @author W1028 YJD
 * @createDate 2016-7-20 下午1:17:55
 */	
public class ParamUtil {
	
	private static Logger log = Logger.getLogger(ParamUtil.class.getName());

	
	public static String toStr(Object ov) {
		return null == ov ? "" : ov.toString().trim();
	}
	
	public static String toStr(Object ov, Boolean flag) {
		try {
			return ov == null ? "" : ov.toString().trim();
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "toInt errorMessange:" + e.getMessage());
			return "";
		}
	}

	public static int toInt(Object ov) {
		return isInteger(ov) ? Integer.parseInt(toStr(ov)) : 0;
	}

	public static int toInt(Object ov, Boolean flag) {
		try {
			return ov == null ? 0 : Integer.parseInt(toStr(ov), 10);
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "toInt errorMessange:" + e.getMessage());
			return 0;
		}
	}

	public static long toLong(Object ov) {
		return ov == null ? 0l : Long.parseLong("".equals(toStr(ov)) ? "0"
				: toStr(ov), 10);
	}

	public static long toLong(Object ov, Boolean flag) {
		try {
			return ov == null ? 0l : Long.parseLong(toStr(ov), 10);
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "toLong errorMessange:" + e.getMessage());
			return 0;
		}
	}

	public static double toDouble(Object ov) {
		return ov == null ? 0.0 : Double.parseDouble(toStr(ov));
	}

	public static double toDouble(Object ov, Boolean flag) {
		try {
			return ov == null ? 0.0 : Double.parseDouble(toStr(ov));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "toDouble errorMessange:" + e.getMessage());
			return 0.0;
		}
	}

	public static BigDecimal toBigDecimal(Object ov) {
		return isBigDecimal(ov) ? new BigDecimal(toStr(ov)) : BigDecimal.ZERO;
	}

	public static BigDecimal toBigDecimal(Object ov, Boolean flag) {
		try {
			return (ov == null ? BigDecimal.ZERO : new BigDecimal(toStr(ov)));
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "toBigDecimal errorMessange:" + e.getMessage());
			return BigDecimal.ZERO;
		}
	}

	public static Boolean isZero(Object ov) {
		try {
			return BigDecimal.ZERO.compareTo(toBigDecimal(ov)) == 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean noNullList(List listOb) {
		if (null == listOb || listOb.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isNullList(List listOb) {
		if (null == listOb || listOb.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isList(List listOb) {
		if (null == listOb || listOb.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isNullMap(Map mapOb) {
		if (null == mapOb || mapOb.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isInteger(Object number) {
		try {
			Pattern pattern = Pattern.compile("^[1-9]\\d*$");
			Matcher matcher = pattern.matcher(toStr(number));
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "isInteger errorMessange:" + e.getMessage());
			return false;
		}
	}

	public static boolean isMoney(Object str) {
		try {
			if (StringUtils.isBlank(toStr(str)))return false;
			java.util.regex.Pattern pattern = java.util.regex.Pattern
					.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
			java.util.regex.Matcher match = pattern.matcher(toStr(str));
			if (match.matches()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "isMoney errorMessange:" + e.getMessage());
			return false;
		}
	}

	public static boolean isBigDecimal(Object str) {
		try {
			if (StringUtils.isBlank(toStr(str)))return false;
			Matcher match = null;
			if (isInteger(str)) {
				Pattern pattern = Pattern.compile("[0-9]*");
				match = pattern.matcher(toStr(str));
			} else {
				if (toStr(str).indexOf(".") == -1) {
					Pattern pattern = Pattern.compile("^[+-]?[0-9]*");
					match = pattern.matcher(toStr(str));
				} else {
					Pattern pattern = Pattern
							.compile("^[+-]?[0-9]+(\\.\\d{1,100})");
					match = pattern.matcher(toStr(str));
				}
			}
			return match.matches();
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "isBigDecimal errorMessange:" + e.getMessage());
			return false;
		}
	}

	public static boolean judgeEmail(Object email) throws Exception {
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(toStr(email));
			boolean isMatched = matcher.matches();
			if (isMatched) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "judgeEmail errorMessange:" + e.getMessage());
			return false;
		}
	}

	public static List listValueUniq(List list) {
		/*注意，如果是asList转换的List实际为抽象List没有clear方法会报错
		所以利用ArrayList转换一次LIST保险*/
		try {
			list = new ArrayList<String>(list);
			HashSet h = new HashSet(list);
			list.clear();
			list.addAll(h);
			return list;
		} catch (Exception e) {
			LogUtil.error(log, LogFormat.PLAIN, "listValueUniq errorMessange:" + e.getMessage());
			return list;
		}
	}

	

}
