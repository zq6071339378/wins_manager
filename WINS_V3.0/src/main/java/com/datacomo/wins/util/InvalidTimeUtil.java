package com.datacomo.wins.util;
/**
 * Copyright 2009 DataComo Communications Technology INC.
 * 
 * This source file is a part of M6_Wap_V1 project. 
 * date: May 4, 2009
 *
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
/**
 * @author Liguo
 * @version v1.0.0
 * @date Nov 11, 2008 10:14:51 AM
 */
public class InvalidTimeUtil {

	private static String defaultDatePattern = "yyyy-MM-dd hh:mm:ss";
	private static String defaultDisplayDatePattern = "yyyy-MM-dd hh:mm:ss";
	private static String defaultDisplayTimeZone = "Asia/Shanghai";
	private static String defaultDatePatternYmd = "yyyy-MM-dd";

	public static String getDatePattern() {
		return defaultDatePatternYmd;
	}

	public static String displayFormat(Date date) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(defaultDatePatternYmd);
			df.setTimeZone(TimeZone.getTimeZone(defaultDisplayTimeZone));
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	public static String displayFormatYmd(Date date) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(defaultDatePatternYmd);
			df.setTimeZone(TimeZone.getTimeZone(defaultDisplayTimeZone));
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	public static String format(Date date, String pattern) {
		String returnValue = "";
		try {
			if (date != null) {
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				returnValue = df.format(date);
			}
		} catch (Exception e) {
			return returnValue;
		}

		return (returnValue);
	}

	public static Date parse(String strDate) throws ParseException {
		return parse(strDate, getDatePattern());

	}

	public static Date parse(String strDate, String pattern)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	public static boolean validateDate(String strDate) {
		boolean flag = false;
		try {
			SimpleDateFormat df = new SimpleDateFormat(defaultDatePatternYmd);
			df.parse(strDate);
			flag = true;
		} catch (Exception e) {
			return false;
		}

		return flag;
	}

	public static String getDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	public static Date simpleDate(Date date) {
		String result = "";
		Date sdate = new Date(System.currentTimeMillis());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			result = sdf.format(date);
			sdate = DateUtils.parseDate(result, new String[] { "yyyy/MM/dd",
					"yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd HH:mm" });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdate;
	}

	public static Date currentDate() {
		TimeZone t = TimeZone.getTimeZone(defaultDisplayTimeZone);
		Locale l = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(t, l);
		return new Date(c.getTimeInMillis());
	}

	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
		
		return c.getTimeInMillis();
	}

	
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
		
		return c.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
		
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
	
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
		return c.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(date);
		} catch (Exception e) {
			c.setTime(new Date());
		}
		return c.get(Calendar.SECOND);
	}

	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	public static Date subtractMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n * (-1));
		return cal.getTime();
	}

	public static Date addHour(Date date, long hour) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + hour * 3600 * 1000);
		return c.getTime();
	}

	public static Date subtractHour(Date date, long hour) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - hour * 3600 * 1000);
		return c.getTime();
	}

	public static Date addDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public static Date subtractDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public static Date addSecond(Date date, long second) {
		return new Date(getMillis(date) + second * 1000);
	}

	public static Date subtractSecond(Date date, long second) {
		return new Date(getMillis(date) - second * 1000);
	}

	public static Date addMillis(Date date, long millis) {
		return new Date(getMillis(date) + millis);
	}

	public static Date subtractMillis(Date date, long millis) {
		return new Date(getMillis(date) - millis);
	}

	public static Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day);
		return c.getTime();
	}

	public static boolean isBetween(Date date, Date date1, Date date2) {
		long m = InvalidTimeUtil.getMillis(date);
		long m1 = InvalidTimeUtil.getMillis(date1);
		long m2 = InvalidTimeUtil.getMillis(date2);
		if (m1 <= m && m <= m2) {
			return true;
		}
		return false;
	}


	/**
	 * 取得消息的时间格式
	 * 
	 * @param messageDate
	 * @return
	 */
	public static String getDayMessage(Date messageDate) {
		Date currentDate = new Date();
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
		String today = format3.format(currentDate);

		String yesterday = format3.format(InvalidTimeUtil.subtractDay(currentDate, 1));
		String qday = format3.format(InvalidTimeUtil.subtractDay(currentDate, 2));

		String messageDay = format3.format(messageDate);

		if (messageDay.equalsIgnoreCase(today)) {
			return "今天";
		}
		if (messageDay.equalsIgnoreCase(yesterday)) {
			return "昨天";
		}
		if (messageDay.equalsIgnoreCase(qday)) {
			return "前天";
		}
		return InvalidTimeUtil.format(messageDate, "yyyy-MM-dd");

	}
	/**
	 * 将字符串转换为日期
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStrToDate(String strDate) throws ParseException {
		return parse(strDate, "yyyy-MM-dd");

	}
	public static Date parseStrToDate2(String strDate) throws ParseException {
		return parse(strDate, "yyyy-MM-dd HH:mm");

	}
	/**
	 * 将字符串转换为小时
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static boolean parseStrToHourStr(String strDate) throws ParseException{
		//Pattern.compile("^[0-2]{1}[0-9]{0,1}[:][0-9]{1,2}$");
		strDate=strDate.replaceAll("：",":").replaceAll(":", ":");
		Pattern pattern = Pattern.compile("^(?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]$");
		Matcher matchName = pattern.matcher(strDate);
		return matchName.find();

    }

	/**
	 * 验证年月日是否合法
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
  public static boolean validateYear(String strDate) throws ParseException{
	    Pattern pattern = Pattern.compile("^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1]))$");
		Matcher matchName = pattern.matcher(strDate);
		return matchName.find();
  }
	
	/**
	 * 获得当前日期年月日
	 * @return
	 * @throws ParseException 
	 */
	public static long currentDateTime() throws ParseException {
		String result="";
		TimeZone t = TimeZone.getTimeZone(defaultDisplayTimeZone);
		Locale l = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(t, l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		result = sdf.format( new Date(c.getTimeInMillis()));
		return  InvalidTimeUtil.getMillis(parseStrToDate(result));
	}

	/**
	 * 获得当前日期小时
	 * @return
	 * @throws ParseException 
	 */
	public static int currentDateHour() throws ParseException {
		TimeZone t = TimeZone.getTimeZone(defaultDisplayTimeZone);
		Locale l = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(t, l);
		int hour=c.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	/**
	 * 判断字符串是否是数字
	 * @return
	 * @throws ParseException 
	 * */
	public static boolean isNumeric(String str){  
	    Pattern pattern = Pattern.compile("[0-9]*");  
	    return pattern.matcher(str).matches();     
	} 
	
	/**
	 * 获得当前日期分钟
	 * @return
	 * @throws ParseException 
	 */
	public static int currentDateMinute() throws ParseException {
		TimeZone t = TimeZone.getTimeZone(defaultDisplayTimeZone);
		Locale l = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(t, l);
		int minute=c.get(Calendar.MINUTE);
		return minute;
	}
	
    /**
	 * 获得当前日期的前一天
	 * @return
	 * @throws ParseException 
	 */
	public static String currentTime() throws ParseException {
		TimeZone t = TimeZone.getTimeZone(defaultDisplayTimeZone);
		Locale l = new Locale("zh", "CN");
		Calendar c = Calendar.getInstance(t, l);
		c.add(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return  sdf.format( new Date(c.getTimeInMillis()));
	}
	
	public static int getCurrentHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY-1);
	}

	/*
	 * 取得N个月之前/之后
	 */
	public static Date getMonth(Date date, int len) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, len);
			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}
	/*
	 * 取得本月第一天日期
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/*
	 * 取得本周第一天日期
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return cal.getTime();
	}
	/**
	 * 获取黑名单结束时间
	 * */
	public static String getEndTimeStr(int type){
		String result="";
		if(type==1){
			result=InvalidTimeUtil.getNextDay();
		}else if(type==2){
			result=InvalidTimeUtil.getNextWeekDay();
		}else if(type==3){
			result=InvalidTimeUtil.getNextMonthDate();
		}else if(type==4){
			result="0000-00-00";
		}
		return result;
	}
	
	/**
	 * 获取明天日期
	 * */
	public static String getNextDay() {
	
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date date=new Date();
	 System.out.println("********得到明天的日期*******"+df.format(date.getTime()+86400000));
	 return df.format(date.getTime()+86400000);
}
	
	/**
	 * 获取下周一
	 * */
	public static String getNextWeekDay() {
		  Calendar cal =Calendar.getInstance();
	      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		 cal.add(Calendar.WEEK_OF_YEAR, 1);
		 System.out.println("********得到本周天的日期*******"+df.format(cal.getTimeInMillis()+86400000));
		 return df.format(cal.getTimeInMillis()+86400000);
	}
	
	/**
	 * 获取下个月1号
	 * */
		public static String getNextMonthDate(){
		  // 获取Calendar 
		  Calendar calendar = Calendar.getInstance();
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		 // 设置日期为本月最大日期 
		   calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
		   System.out.print("*********得到本月的最大日期**********"+format.format(calendar.getTimeInMillis()+86400000));
		   return format.format(calendar.getTimeInMillis()+86400000);
		 }
		public static void main(String[] arg){
			InvalidTimeUtil.getNextDay();
			//InvalidTimeUtil.getNextWeekDay();
			//InvalidTimeUtil.getNextMonthDate();
		}

}
