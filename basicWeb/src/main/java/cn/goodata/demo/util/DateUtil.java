package cn.goodata.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	/**
	 * 取系统时间
	 */
	public static String getDateTime() {

		String time = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = f.format(new Date());
		return time;
	}

	/**
	 * 把字符串转换为时间类型 格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param s
	 * @return
	 */
	public static Date stringToDate(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date t;
		try {
			t = formatter.parse(s);
			return t;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 把字符串转换为时间类型 格式为yyyy-MM-dd
	 * 
	 * @param s
	 * @return
	 */
	public static Date stringToDate2(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date t;
		try {
			t = formatter.parse(s);
			return t;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * 把字符串转换为时间类型 格式为 指定时间格式
	 * 
	 * @param date
	 * @param dataFormat
	 * @return
	 */
	public static String dateToString(Date date, String dataFormat) {
		String time = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			SimpleDateFormat df = new SimpleDateFormat(dataFormat);
			time = df.format(cal.getTime());
		} catch (Exception e) {
		}
		return time;
	}

	/**
	 * 在指定的日期上加上指定的天数
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String addDay(String s, int n) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);// 增加n天

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	
	public static String addDay2(String s, int n) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);// 增加n天

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	public static Date addMinutes(Date date, int n) {
		try {

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.MINUTE, n);// 增加n秒
			return cd.getTime();

		} catch (Exception e) {
			return null;
		}
	}



	/**
	 * 计算任意两个日期之间的间隔天数
	 * 
	 * @param sDate
	 *            之前
	 * @param eDate
	 *            之后
	 * @return
	 */
	public static long daysOfTwoDates(Date sDate, Date eDate) {
		int day = 24 * 60 * 60 * 1000;
		long dayTotal = Math.abs(sDate.getTime() - eDate.getTime());
		return Math.abs(dayTotal) / day;
	}

	/**
	 * 获取两个日期间的天数
	 * 
	 * @param stime
	 *            之前
	 * @param etime
	 *            之后
	 * @return
	 */
	public static long daysOfTwoDates(String stime, String etime) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(stime);
			Date date2 = ft.parse(etime);
			quot = daysOfTwoDates(date1, date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 计算任意两个日期之间的间隔时间
	 * 
	 * @param sDate
	 *            开始时间
	 * @param eDate
	 *            结束时间
	 * @param type
	 *            0:间隔天数 1：间隔小时 2：间隔分钟 3：间隔秒数
	 * @return
	 */
	public static long getTimes(Date sDate, Date eDate, int type) {
		int time = 0;
		if (type == 0) {
			time = 24 * 60 * 60 * 1000;
		} else if (type == 1) {
			time = 60 * 60 * 1000;
		} else if (type == 2) {
			time = 60 * 1000;
		} else if (type == 3) {
			time = 1000;
		}
		long timeTotal = Math.abs(sDate.getTime() - eDate.getTime());
		return Math.abs(timeTotal) / time;
	}

	/**
	 * 取得系统当前时间前n天,格式为yyyy-mm-dd
	 * 
	 * @param n
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getNDayBeforeCurrentDate(int n) {
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, -n);
		return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-"
				+ c.get(c.DATE);
	}

	/**
	 * 得到当前年
	 * 
	 * @return 当前年
	 */
	public static int year() {
		return Calendar.YEAR;
	}

	/**
	 * 得到当前月
	 * 
	 * @return 当前月
	 */
	public static int month() {
		// 老外的月份是从0到 11
		return Calendar.MONTH + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @return 当前日
	 */
	public static int day() {
		return Calendar.DAY_OF_MONTH;
	}

	/**
	 * 得到当前小时
	 * 
	 * @return 当前小时
	 */
	public static int hour() {
		return Calendar.HOUR_OF_DAY;
	}

	/**
	 * 得到当前分钟
	 * 
	 * @return 当前分钟
	 */
	public static int minute() {
		return Calendar.MINUTE;
	}

	/**
	 * 得到当前秒
	 * 
	 * @return 当前秒
	 */
	public static int second() {
		return Calendar.SECOND;
	}

	/**
	 * 得到当前星期几
	 * 
	 * @return 当前星期几,一周中的第几天
	 */
	public static int dayOfWeek() {
		int dayofweek = Calendar.DAY_OF_WEEK;
		return dayofweek == 1 ? 7 : dayofweek - 1;
	}

	/**
	 * 得到当前是当年中的第几天
	 * 
	 * @return 当年中的第几天
	 */
	public int dayOfYear() {
		return Calendar.DAY_OF_YEAR;
	}

	/**
	 * 获取格式化日期
	 * 
	 * @param date
	 * @param format
	 * @return 返回String日期
	 */
	public static String getFormatDateString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取格式化日期
	 * 
	 * @param date
	 * @param format
	 * @return 返回date日期
	 */
	public static Date getFormatDate(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}


	/**
	 * 判断是否合法日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDateString(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			simpleDateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareDate(Date startDate, Date endDate) {
		if (endDate.after(startDate)) {
			return true;
		} else {
			return false;
		}
	}


}
