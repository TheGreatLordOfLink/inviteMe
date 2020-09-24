package com.jzqm.inviteme.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
	
	/**
	 * 静态常量
	 */
	public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
	public static final String C_MONTH_PATTON_DEFAULT = "yyyyMM";
	public static final String C_NUMBER_PATTON_DEFAULT = "MMddHHmm";
	
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final long C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;
	
	/**
	 * 计算当前月份的最大天数
	 * @return
	 */
	public static int findMaxDayInMonth() {
		return findMaxDayInMonth(0, 0);
	}
	
	/**
	 * 计算指定日期月份的最大天数
	 * @param date
	 * @return
	 */
	public static int findMaxDayInMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return findMaxDayInMonth(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历月份的最大天数
	 * @param calendar
	 * @return
	 */
	public static int findMaxDayInMonth(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 计算当前年某月份的最大天数
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int month) {
		return findMaxDayInMonth(0, month);
	}
	
	/**
	 * 计算某年某月份的最大天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int findMaxDayInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		if (month > 0) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		
		return findMaxDayInMonth(calendar);
	}
	
	/**
	 * Calendar 转换为 Date
	 * @param calendar
	 * @return
	 */
	public static Date calendar2Date(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.getTime();
	}
	
	/**
	 * Date 转换为 Calendar
	 * @param date
	 * @return
	 */
	public static Calendar date2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * UTC Date 转换为 Calendar
	 * @param date
	 * @return
	 */
	public static Calendar UTCdate2Calendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -8);
		return calendar;
	}
	
	public static Date UTCdate2Normal(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -8);
		return calendar.getTime();
	}
	
	/**
	 * 拿到默认格式的SimpleDateFormat
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return getSimpleDateFormat(null);
	}
	
	/**
	 * 拿到指定输出格式的SimpleDateFormat
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			sdf = new SimpleDateFormat(C_TIME_PATTON_DEFAULT);
		} else{
			sdf = new SimpleDateFormat(format,Locale.CHINA);
		}
		
		return sdf;
	}
	
	/**
	 * 转换当前时间为默认格式
	 * @return
	 */
	public static String formatDate2Str() {
		return formatDate2Str(new Date());
	}
	
	/**
	 * 转换指定时间为默认格式
	 * @param date
	 * @return
	 */
	public  static String formatDate2Str(Date date) {
		return formatDate2Str(date, C_TIME_PATTON_DEFAULT);
	}
	
	
	/**
	 * 转换指定时间为指定格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate2Str(Date date, String format) {
		if (date == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 转换默认格式的时间为Date
	 * @param dateStr
	 * @return
	 */
	public static Date formatStr2Date(String dateStr) {
		return formatStr2Date(dateStr, null);
	}
	
	/**
	 * 转换指定格式的时间为Date
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		SimpleDateFormat sdf = getSimpleDateFormat(format);
		return sdf.parse(dateStr, new ParsePosition(0));
	}
	
	/**
	 * 转换默认格式的时间为指定格式时间
	 * @param dateStr
	 * @param defineFormat
	 * @return
	 */
	public static String formatDefault2Define(String dateStr, String defineFormat) {
		return formatSource2Target(dateStr, C_TIME_PATTON_DEFAULT, defineFormat);
	}
	
	/**
	 * 转换源格式的时间为目标格式时间
	 * @param dateStr
	 * @param sourceFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatSource2Target(String dateStr, String sourceFormat, String targetFormat) {
		Date date = formatStr2Date(dateStr, sourceFormat);
		return formatDate2Str(date, targetFormat);
	}
	
	/**
	 * 计算当天是该年中的第几周
	 * @return
	 */
	public static int findWeeksNoInYear() {
		return findWeeksNoInYear(new Date());
	}
	
	/**
	 * 计算指定日期是该年中的第几周
	 * @param date
	 * @return
	 */
	public static int findWeeksNoInYear(Date date) {
		if (date == null) {
			return 0;
		}
		return findWeeksNoInYear(date2Calendar(date));
	}
	
	/**
	 * 计算指定日历是该年中的第几周
	 * @param calendar
	 * @return
	 */
	public static int findWeeksNoInYear(Calendar calendar) {
		if (calendar == null) {
			return 0;
		}
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 计算一年中的第几星期是几号 
	 * @param year
	 * @param weekInYear
	 * @param dayInWeek
	 * @return
	 */
	public static Date findDateInWeekOnYear(int year, int weekInYear, int dayInWeek) {
		Calendar calendar = Calendar.getInstance();
		if (year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		calendar.set(Calendar.WEEK_OF_YEAR, weekInYear);
		calendar.set(Calendar.DAY_OF_WEEK, dayInWeek);
		
		return calendar.getTime();
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static Date dayBefore2Date(int days) { 
		Date date = new Date();
		return dayBefore2Object(days, null, date);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @return
	 */
	public static String dayBefore2Str(int days) {
		String string = formatDate2Str();
		return dayBefore2Object(days, null, string);
	}
	
	/**
	 * 相对于当前日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param days
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T dayBefore2Object(int days, String format, T instance) {
		Calendar calendar = Calendar.getInstance();
		if (days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date defineDayBefore2Date(Date date, int days) {
		Date dateInstance = new Date();
		return defineDayBefore2Object(date, days, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param date
	 * @param days
	 * @return
	 */
	public static String defineDayBefore2Str(Date date, int days) {
		String stringInstance = formatDate2Str();
		return defineDayBefore2Object(date, days, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几天(days < 0０００００)或者后几天(days > 0)
	 * @param <T>
	 * @param date
	 * @param days
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineDayBefore2Object(Date date, 
			int days, String format, T instance) {
		if (date == null || days == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.DATE, days);
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		return null;
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static Date monthBefore2Date(int months) {
		Date date = new Date();
		return monthBefore2Object(months, null, date);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param months
	 * @return
	 */
	public static String monthBefore2Str(int months) {
		String string = formatDate2Str();
		return monthBefore2Object(months, null, string);
	}
	
	/**
	 * 相对于当前日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param months
	 * @param format
	 * @param instance
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T monthBefore2Object(int months, String format, T instance) {
//		if (months == 0) {
//			return null;
//		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Object> T yearBefore2Object(int years, String format, T instance) {
//		if (years == 0) {
//			return null;
//		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, years);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date defineMonthBefore2Date(Date date, int months) {
		Date dateInstance = new Date();
		return defineMonthBefore2Object(date, months, null, dateInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param date
	 * @param months
	 * @return
	 */
	public static String defineMonthBefore2Str(Date date, int months) {
		String stringInstance = formatDate2Str();
		return defineMonthBefore2Object(date, months, null, stringInstance);
	}
	
	/**
	 * 相对于指定日期的前几月(months < 0０００００)或者后几月(months > 0)时间
	 * @param <T>
	 * @param date
	 * @param months
	 * @param format
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T defineMonthBefore2Object(Date date,
			int months, String format, T instance) {
		if (months == 0) {
			return null;
		}
		
		if (format == null || format.equals("")) {
			format = C_TIME_PATTON_DEFAULT;
		}
		
		Calendar calendar = date2Calendar(date);
		calendar.add(Calendar.MONTH, months);
		
		if (instance instanceof Date) {
			return (T)calendar.getTime();
		} else if (instance instanceof String) {
			return (T)formatDate2Str(calendar2Date(calendar), format);
		}
		
		return null;
	}
	/**
	 * 计算两个日期之间差的天数(包括跨年)
	 * miracle 2014-3-26
	 * 修改者名字 
	 * 修改日期 
	 * 修改内容
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static int caculate2Days(Date fromDate, Date toDate){
	        Calendar aCalendar = Calendar.getInstance();
	        Calendar bCalendar = Calendar.getInstance();
	        aCalendar.setTime(fromDate);
	        bCalendar.setTime(toDate);
	        int days = 0;
	        while(aCalendar.before(bCalendar)){
	            days++;
	            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
	        }
	        return days;
	}
	/**
	 * 计算两个日期之间差的天数（同一年内）
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int caculate2DaysInYear(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayNum1 = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(secondDate);
		int dayNum2 = calendar.get(Calendar.DAY_OF_YEAR);
		return Math.abs(dayNum1 - dayNum2);
	}
	
	/**
	 * 计算两个日期之间差的天数
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return
	 */
	public static int caculate2Days(Calendar firstCalendar, Calendar secondCalendar) {
		if (firstCalendar.after(secondCalendar)) {
			Calendar calendar = firstCalendar;
			firstCalendar = secondCalendar;
			secondCalendar = calendar;
		}
		
		long calendarNum1 = firstCalendar.getTimeInMillis();
		long calendarNum2 = secondCalendar.getTimeInMillis();
		return Math.abs((int)((calendarNum1 - calendarNum2)/C_ONE_DAY));
		
	}
	public Date clearDatePart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	} 

	public static long caculate2Sec(long after) {
		long millisecond = System.currentTimeMillis() - after;
		return  (millisecond / 1000);
	}
	
	public static void main(String[] args) {
//		System.out.println("当前月份的最大天数:" + findMaxDayInMonth(new Date()));
//		System.out.println("6月份的最大天数:" + findMaxDayInMonth(6));
//		System.out.println("1999-02月份的最大天数:" + findMaxDayInMonth(1999, 2));
//		System.out.println("2000-02月份的最大天数:" + findMaxDayInMonth(2000, 2));
		
//		System.out.println(formatSource2Target("2009-07-24 11:02:35", null, "yyyy/MM/dd"));
//		System.out.println(findWeeksNoInYear());
		
//		System.out.println("2003年的第30个星期一是那天:" + findDateInWeekOnYear(2003, 30, Calendar.MONDAY));
//		System.out.println("2009年的第30个星期一是那天:" + findDateInWeekOnYear(2009, 30, Calendar.FRIDAY));
		
//		System.out.println("【日期格式】当前日期的前7天是:" + dayBefore2Date(-7));
//		System.out.println("【字符格式】当前日期的前7天是:" + dayBefore2Str(-7));
//		System.out.println("【日期格式】当前日期的后7天是:" + dayBefore2Date(7));
//		System.out.println("【字符格式】当前日期的后7天是:" + dayBefore2Str(7));
		
//		System.out.println(formatStr2Date("2009-07-22", "yyyy-MM-dd"));
		
//		System.out.println("【日期格式】2009-07-22的前7天是:" + 
//				defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
//		System.out.println("【字符格式】2009-07-22的前7天是:" + 
//				defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -7));
//		System.out.println("【日期格式】2009-07-22的后7天是:" + 
//				defineDayBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
//		System.out.println("【字符格式】2009-07-22的后7天是:" + 
//				defineDayBefore2Str(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 7));
		
//		System.out.println("【日期格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
//		System.out.println("【字符格式】相对于当前时间的前2月日期是:" + monthBefore2Date(-2));
//		System.out.println("【日期格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
//		System.out.println("【字符格式】相对于当前时间的后2月日期是:" + monthBefore2Date(2));
		
//		System.out.println("【日期格式】2009-07-22的前2月日期是:" + 
//				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -2));
//		System.out.println("【字符格式】2009-07-22的前2月日期是:" +
//				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), -2));
//		System.out.println("【日期格式】2009-07-22的后2月日期是:" + 
//				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 2));
//		System.out.println("【字符格式】2009-07-22的后2月日期是:" + 
//				defineMonthBefore2Date(formatStr2Date("2009-07-22", "yyyy-MM-dd"), 2));
		
//		Date firstDate = formatStr2Date("2009-07-22", "yyyy-MM-dd");
//		Date secondDate = formatStr2Date("2009-07-18", "yyyy-MM-dd");
//		System.out.println(caculate2Days(firstDate, secondDate));
		
//		Calendar firstC = date2Calendar(formatStr2Date("2009-07-22", "yyyy-MM-dd"));
//		Calendar secondC = date2Calendar(formatStr2Date("2009-07-18", "yyyy-MM-dd"));
//		System.out.println(caculate2Days(firstC, secondC));
		
//		System.out.println(getWeekDay(123));
//		System.out.println(getSequentialMonthSQL("2013-01-01", "2014-01-01"));
		
//		String m = DateUtils.monthBefore2Object(0, "MM", "String");
//		String y = DateUtils.yearBefore2Object(0, "yyyy", "String");
//		System.out.print(m+"-"+y);
		
//		System.out.print(formatDate2Str(getLastDayOfMonth(monthBefore2Date(-1)),"yyyy-MM-dd"));
//		System.out.print(formatDate2Str(getLastDayOfMonth(formatStr2Date("2017-10-14T12:51:14Z", "yyyy-MM-dd")),"yyyy-MM-dd"));
//		String a = "【1,2,3】";  
//		String b = a.replaceAll("[\\[\\]]", "");  
//		System.out.println(b);// b=1,2,3  
		Date date2 = new Date();
		date2.setDate(24);
		
		System.out.println(judgeIsSameDay(new Date(),date2,0));
		
	}
	/**
	 * 获取星期几
	 * @param afterOrBefore 如果是今天为0，前一天-1，后一天1
	 * @return
	 */
	public static String getWeekDay(int afterOrBefore){
		Date requestDate = new Date();
		if(afterOrBefore != 0){
			requestDate = DateUtils.dayBefore2Date(afterOrBefore);
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH,1);
		// DateUtils.date2Calendar(requestDate);
//		System.out.println(requestDate);
		int dayFlag = c.get(Calendar.DAY_OF_WEEK);
		switch (dayFlag) {
		case 1:
			return "星期天";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		default:
			return null;
		}
	}
	/**
	 * 生成连续日期SQL
	 * 修改者名字 
	 * 修改日期 
	 * 修改内容
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static String getSequentialDateSQL(String fromDate,String toDate){
		StringBuffer sql = new StringBuffer(" SELECT TO_CHAR(TO_DATE('"+fromDate+"', 'YYYY-MM-DD') + LEVEL -1 ,'YYYY-MM-DD') DAY");
		sql.append(" FROM DUAL ");
		sql.append(" CONNECT BY LEVEL < ((TO_DATE('"+toDate+"', 'YYYY-MM-DD')) - ");
		sql.append(" (TO_DATE('"+fromDate+"', 'YYYY-MM-DD')) +1 ) ");
		return sql.toString();
	}
	/**
	 * 生成连续日期SQL
	 * 修改者名字 
	 * 修改日期 
	 * 修改内容
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static String getSequentialMonthSQL(String fromDate,String toDate){
		StringBuffer sql = new StringBuffer(" SELECT to_char(add_months(to_date('"+fromDate+"', 'yyyy-mm-dd'), LEVEL - 1), 'yyyymm') MONTH ");
		sql.append(" FROM DUAL ");
		sql.append(" CONNECT BY LEVEL <  months_between(to_date('"+toDate+"', 'yyyy-mm-dd'),");
		sql.append(" to_date('"+fromDate+"', 'yyyy-mm-dd')) + 1");
		return sql.toString();
	}
	
	/**
	 * 将String转换为Timestamp类型
	 * 方法功能说明
	 * 修改者  修改日期 
	 * 修改说明
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public final static Timestamp string2Time(String dateString, String format) throws Exception {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(format);// 设定格式
		Date timeDate = dateFormat.parse(dateString);
		Timestamp dateTime = new Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
		return dateTime;
	}
	
	/**
	 * 将Timestamp转换为String类型
	 * 方法功能说明
	 * @date 2014-12-18 下午3:37:47
	 * 修改者  修改日期 
	 * 修改说明
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Timestamp timestamp, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = C_TIME_PATTON_DEFAULT; // "yyyy-MM-dd HH:mm:ss"
		}
		DateFormat df = new SimpleDateFormat(pattern);
		String s = df.format(timestamp);
		return s;
	}
	
	
	private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;
	
	/**
     * 距离今天多久
     * @param date
     * @return 
     * 
     */
    public static String fromToday(Date date) {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
 
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
    	
        if(ago <= ONE_MINUTE)
        	 return "刚刚";
        else if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时前" ;
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前";
        } else if (ago <= ONE_YEAR) {
            
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.HOUR_OF_DAY);
            
			String monthStr = "";
			String dayStr = "";

			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = month + "";
			}

			if (day < 10) {
				dayStr = "0" + day;
			} else {
				dayStr = day + "";
			}
            
            return monthStr + "月" + dayStr + "日";
        } else {
            long year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            String monthStr = "";
            if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = month + "";
			}
            
            return year + "年" + monthStr + "月";
        }

    	
//        if (ago <= ONE_HOUR)
//            return ago / ONE_MINUTE + "分钟前";
//        else if (ago <= ONE_DAY)
//            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
//                    + "分钟前";
//        else if (ago <= ONE_DAY * 2)
//            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
//                    + calendar.get(Calendar.MINUTE) + "分";
//        else if (ago <= ONE_DAY * 3)
//            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
//                    + calendar.get(Calendar.MINUTE) + "分";
//        else if (ago <= ONE_MONTH) {
//            long day = ago / ONE_DAY;
//            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
//                    + calendar.get(Calendar.MINUTE) + "分";
//        } else if (ago <= ONE_YEAR) {
//            long month = ago / ONE_MONTH;
//            long day = ago % ONE_MONTH / ONE_DAY;
//            return month + "个月" + day + "天前"
//                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
//                    + calendar.get(Calendar.MINUTE) + "分";
//        } else {
//            long year = ago / ONE_YEAR;
//            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
//            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
//                    + "日";
//        }
 
    }
    
    /**
     * long转换为Date类型
     * @param currentTime 要转换的long类型的时间
     * @param formatType 要转换的时间格式yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
  	public static Date longToDate(long currentTime, String formatType)
  			throws ParseException {
  		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生成一个date类型的时间
  		String sDateTime = formatDate2Str(dateOld, formatType); // 把date类型的时间转换为string
  		Date date = formatStr2Date(sDateTime, formatType); // 把String类型转换为Date类型
  		return date;
  	}
  	
  	/**
  	 * long类型转换为String类型
  	 * @param currentTime 要转换的long类型的时间
  	 * @param formatType 要转换的string类型的时间格式
  	 * @return
  	 * @throws ParseException
  	 */
  	public static String longToString(long currentTime, String formatType)
  			throws ParseException {
  		Date date = longToDate(currentTime, formatType); // long类型转成Date类型
  		String strTime = formatDate2Str(date, formatType); // date类型转成String
  		return strTime;
  	}
  	
  //获得每个月最后一天的日期  
  	public   static   Date   getLastDayOfMonth(Date   sDate1)   {            
  	        Calendar   cDay1   =   Calendar.getInstance();            
  	        cDay1.setTime(sDate1);            
  	        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);           
  	        Date   lastDate   =   cDay1.getTime();            
  	        lastDate.setDate(lastDay);            
  	        return   lastDate;    
  	        }  
  	
  	 /**
     * 相对于指定日期的前几月(Months < 0０００００)或者后几月(Months > 0)
     * @param i 间隔的月数
     * @return yyyy-MM-dd'T'HH:mm:ss'Z' TO yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    public static String getSolrMonths(int i){
    	
    	String  toTime = DateUtils.formatDate2Str(new Date(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
    	String  fromTime = DateUtils.formatDate2Str(DateUtils.monthBefore2Date(-i), "yyyy-MM-dd'T'HH:mm:ss'Z'");
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(fromTime);
    	buffer.append(" TO ");
    	buffer.append(toTime);
    	
    	return buffer.toString();
    }
 	 /**
    * 相对于指定日期的前几日(Months < 0０００００)或者后几日月(Months > 0)
    * @param i 间隔的月数
    * @return yyyy-MM-dd'T'HH:mm:ss'Z' TO yyyy-MM-dd'T'HH:mm:ss'Z'
    */
   public static String getSolrDays(int i){
   	
   	String  toTime = DateUtils.formatDate2Str(new Date(), "yyyy-MM-dd'T'HH:mm:ss'Z'");
   	String  fromTime = DateUtils.formatDate2Str(DateUtils.dayBefore2Date(-i), "yyyy-MM-dd'T'HH:mm:ss'Z'");
   	StringBuffer buffer = new StringBuffer();
   	buffer.append(fromTime);
   	buffer.append(" TO ");
   	buffer.append(toTime);
   	
   	return buffer.toString();
   }
    /**
     * 获得指定日期  的前几天或后几天
     * @param date  指定日期
     * @param i   相加减的天数
     * @return
     */
    public static Date getDate(Date date,int i){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, i);
		Date date1 = new Date(calendar.getTimeInMillis());
		return date1;
	}
    
    public static Integer getShortNumber(){
    	String number = formatDate2Str(new Date(), C_NUMBER_PATTON_DEFAULT);
    	return Integer.valueOf(number);
    }
    
    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
   
    /**
     * 获取当月第一天
     * @return
     */
    public static String getMonthFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		return df.format(gcLast.getTime());
	}
    
    
    /**
     * 判断两个时间是否是同一天
     * @param date1
     * @param date2
     * @param date1BeforeDays  date1的指定变动天数,前几天 (days < 0)或者后几天(days > 0)
     * @return
     */
    public static boolean judgeIsSameDay(Date date1 , Date date2,int date1BeforeDays) {
    	if(date1BeforeDays!=0) {
    		date1 = defineDayBefore2Date(date1,date1BeforeDays);
    	}
    	
    	return org.apache.commons.lang3.time.DateUtils.isSameDay(date1,date2);
    	
    }
    
    /**
     * 判断当前时间的指定变动天数下，与目标时间是否是同一天
     * @param date2
     * @param distanceNow 前几天 (days < 0)或者后几天(days > 0)
     * @return
     */
    public static boolean judgeIsSameDayWithNow(Date date2,int distanceNow) {
    	return judgeIsSameDay(new Date(), date2, distanceNow);
    }
    /**
     * 判断当前时间与目标时间是否是同一天
     * @param date2
     * @return
     */
    public static boolean judgeIsSameDayWithNow(Date date2) {
    	return judgeIsSameDay(new Date(), date2, 0);
    }
    
    public static String solrUTC2NormalString(String date) {
    	try {
			Date a= new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.US).parse(date);
			return formatDate2Str(UTCdate2Normal(a));
    	}catch(Exception e){
    		return "";
    	}
    	
    }
    
    /**
     * 获取当前时间到第二天凌晨的秒数
     * @return
     */
	public static Long getSecondsNextEarlyMorning() {
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DAY_OF_YEAR, 1);
	        cal.set(Calendar.HOUR_OF_DAY, 0);      
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
	}

	public static String createSSSTime()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String nowDate = format.format(new Date());
		return nowDate;
	}

}
