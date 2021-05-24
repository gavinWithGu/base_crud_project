package com.gavin.foo.crud.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

/**
 * 通用的工具类
 */
public final class GeneralUtils {

	private static final DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private GeneralUtils() {

	}

	/**
	 * 获得当前的日期毫秒
	 *
	 * @return
	 */
	public static long nowTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 获得当前的时间戳
	 *
	 * @return
	 */
	public static Timestamp nowTimeStamp() {
		return new Timestamp(nowTimeMillis());
	}

	/**
	 * 获取当前时间格式串
	 *
	 * @param
	 * @return String
	 */
	public static String currentTime() {
		return formatDate(new Date(), "yyyyMMddhhmmssSSS");
	}

	/**
	 * 取得指定日期格式的字符串
	 *
	 * @param Date
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 判断对象是否为null , 为null返回true,否则返回false
	 * 
	 * @param obj
	 *            被判断的对象
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		return (null == obj) ? true : false;
	}

	/**
	 * 判断对象是否为null , 为null返回false,否则返回true
	 *
	 * @param obj
	 *            被判断的对象
	 * @return boolean
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 判断字符串是否为null或者0长度，字符串在判断长度时，先去除前后的空格,空或者0长度返回true,否则返回false
	 *
	 * @param str
	 *            被判断的字符串
	 * 
	 * @return boolean
	 */
	public static boolean isNullOrZeroLength(String str) {
		return GenericValidator.isBlankOrNull(str);
	}

	/**
	 * 判断字符串是否为null或者0长度，字符串在判断长度时，先去除前后的空格,空或者0长度返回false,否则返回true
	 *
	 * @param str
	 *            被判断的字符串
	 * 
	 * @return boolean
	 */
	public static boolean isNotNullOrZeroLength(String str) {
		return !isNullOrZeroLength(str);
	}

	/**
	 * 判断str数组是否为null或者0长度，只要有一个空或者0长度返回false, 否则返回true
	 *
	 * @param str
	 *            String 字符数组
	 * @return boolean
	 * @author huanghui
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNotNullOrZeroLength(String... str) {
		for (String s : str) {
			if (isNullOrZeroLength(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断str数组是否为null或者0长度，只要有一个空或者0长度返回true, 否则返回false
	 *
	 * @param str
	 *            String 字符数组
	 * @return boolean
	 * @author huanghui
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNullOrZeroLength(String... str) {
		return !isNotNullOrZeroLength(str);
	}

	/**
	 * 判断集合对象是否为null或者0大小 , 为空或0大小返回true ,否则返回false
	 *
	 * @param c
	 *            collection 集合接口
	 * @return boolean 布尔值
	 * 
	 * @author huanghui
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNullOrZeroSize(Collection<? extends Object> c) {
		return isNull(c) || c.isEmpty();
	}

	/**
	 * 判断集合对象是否为null或者0大小 , 为空或0大小返回false, 否则返回true
	 *
	 * @param c
	 *            collection 集合接口
	 * @return boolean 布尔值
	 * 
	 * @author huanghui
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isNotNullOrZeroSize(Collection<? extends Object> c) {
		return !isNullOrZeroSize(c);
	}

	/**
	 * 判断数字类型是否为null或者0，如果是返回true，否则返回false
	 *
	 * @param number
	 *            被判断的数字
	 * @return boolean
	 */
	public static boolean isNullOrZero(Number number) {
		if (GeneralUtils.isNotNull(number)) {
			return (number.doubleValue() != 0) ? false : true;
		}
		return true;
	}

	/**
	 * 判断数字类型是否不为null或者0，如果是返回true，否则返回false
	 *
	 * @param number
	 *            被判断的数字
	 * @return boolean
	 */
	public static boolean isNotNullOrZero(Number number) {
		return !isNullOrZero(number);
	}

	/**
	 * 获取时间差，单位分钟
	 * 
	 * 时间格式为：yyyyMMddHHmmss
	 * 
	 * @throws ParseException
	 */
	public static long getTimeDifference(String dateStr1, String dateStr2) {
		long min = 60;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date now = null;
		java.util.Date date = null;
		try {
			now = df.parse(dateStr1);
			date = df.parse(dateStr2);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			min = day * 24 * 60 + hour * 60 + min;
			// System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("min:" + min);
		return min;
	}

	/**
	 * 获取时间差，单位秒
	 * 
	 * 时间格式为：yyyyMMddHHmmss
	 * 
	 * @throws ParseException
	 */
	public static long getSTimeDifference(String dateStr1, String dateStr2) {
		long s = 60;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date now = null;
		java.util.Date date = null;
		try {
			now = df.parse(dateStr1);
			date = df.parse(dateStr2);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			// min = day * 24 * 60 + hour * 60 + min;
			s = day * 24 * 60 * 60 + hour * 60 * 60 + min * 60 + s;
			// System.out.println("" + day + "天" + hour + "小时" + min + "分" + s +
			// "秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("min:" + min);
		return s;
	}

	/**
	 * 获取时间差，单位天
	 * 
	 * 时间格式为：yyyyMMddHHmmss
	 * 
	 * @throws ParseException
	 */
	public static long getDayDifference(String dateStr1, String dateStr2) {
		long min = 60;
		long day = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date now = null;
		java.util.Date date = null;
		try {
			now = df.parse(dateStr1);
			date = df.parse(dateStr2);
			long l = now.getTime() - date.getTime();
			day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			min = day * 24 * 60 + hour * 60 + min;
			// System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("min:" + min);
		return day;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	public static void main(String[] args) {
		System.out.println(nowTimeStamp());
		System.out.println(currentTime());
	}

}