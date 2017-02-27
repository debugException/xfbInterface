/*
 * Copyright (c) 2005 Qing.Mu. All Rights Reserved.
 */
package cn.emagsoftware.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    /**
     * Get the current date string representation.
     *
     * @param dateFormat the input dateFormat.
     *                   See the <code>java.text.SimpleDateFormat</code> API for date format
     *                   string examples
     */
    public static String getCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(cal.getTime());
    }

    /**
     * Get the string representation of the input Date object
     *
     * @param date       the input Date object
     * @param dateFormat a date format string like "dd/MM/yyyy"
     * @return the string representation of the input Date object
     */
    public static String getDateString(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(date);
        } else {
            return null;
        }
    }

    /**
     * Get a java Date object from an input date string representation.
     * <br>
     * See the <code>java.text.SimpleDateFormat</code> API for date format string
     * examples.
     *
     * @param sDate      the date string representation
     * @param dateFormat a date format string like "dd/MM/yyyy"
     * @return the Date object corresponding to the input date string,
     *         or null if the conversion fails
     */
    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }

    /**
     * Add the input number of days to the startDate string representation.
     *
     * @param startDate  the start date string representation
     * @param dateFormat the start date format
     * @param days       the number of days to add to the startDate
     * @return the Date object representing the resulting date
     */
    public static Date addDays(String startDate, String dateFormat, int days) {
        return addDays(getDate(startDate, dateFormat), days);
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate the start Date object
     * @param days      the number of days to add to the startDate object
     * @return the Date object representing the resulting date
     */
    public static Date addDays(Date startDate, int days) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.DATE, days);

        return gCal.getTime();
    }

    public static Date addWeeks(Date startDate, int weeks) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.WEEK_OF_YEAR, weeks);

        return gCal.getTime();
    }

    public static Date addMonths(Date startDate, int months) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.MONTH, months);

        return gCal.getTime();
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate the start Date object
     * @param hours     the number of days to add to the startDate object
     * @return the Date object representing the resulting date
     */
    public static Date addHours(Date startDate, int hours) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.HOUR, hours);

        return gCal.getTime();
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate  the start Date object
     * @param hours      the number of hours to add to the startDate object
     * @param dateFormat the start date format
     * @return the Date object representing the resulting date
     */
    public static Date addHours(String startDate, String dateFormat, int hours) {
        return addHours(getDate(startDate, dateFormat), hours);
    }

    /**
     * Add the input number of days to the start Date object.
     *
     * @param startDate   the start Date object
     * @param hours       the number of hours to add to the startDate object
     * @param dateFormat1 the start date format
     * @param dateFormat2 the end date format
     * @return the Date object representing the resulting date
     */
    public static String addHours(String startDate, String dateFormat1, int hours, String dateFormat2) {
//         return  getDateString(addDays(getDate(startDate, dateFormat1), hours),dateFormat2);

        return getDateString(addHours(getDate(startDate, dateFormat1), hours), dateFormat2);
    }

    /**
     * Check if the <code>d</code> input date is between <code>d1</code> and
     * <code>d2</code>.
     *
     * @param d  the date to check
     * @param d1 the lower boundary date
     * @param d2 the upper boundary date
     * @return true if d1 <= d <= d2, false otherwise
     */
    public static boolean isDateBetween(Date d, Date d1, Date d2) {
        return ((d1.before(d) || d1.equals(d)) &&
                (d.before(d2) || d.equals(d2)));
    }

    public static String getCurrentTime() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    public static String getCurrentDate() {
        return DateFormat.getDateInstance().format(new Date());
    }

    @SuppressWarnings({"finally"})
    public static String formatDate(Date theDate) {
        Locale locale = Locale.CHINA;
        String dateString = "";
        try {
            Calendar cal = Calendar.getInstance(locale);
            cal.setFirstDayOfWeek(Calendar.TUESDAY);
            cal.setTime((Date) theDate);

            //DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM,locale);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            dateString = dateFormatter.format(cal.getTime());
            //System.out.println(dateString);
            //System.out.println(cal.get(Calendar.YEAR));
            //System.out.println(cal.get(cal.DAY_OF_WEEK));
        } catch (Exception e) {
            System.out.println("test result:" + e.getMessage());
        } finally {
            return dateString;
        }
    }

    public static int getDateDiff(Date date1, Date date2, int sign) {

        long base = 1;
        switch (sign) {
            case Calendar.YEAR:
                base *= 1000 * 60 * 60 * 24*365;
                break;
            case Calendar.DATE:
                base *= 1000 * 60 * 60 * 24;
                break;
            case Calendar.HOUR:
                base *= 1000 * 60 * 60;
                break;
            case Calendar.MINUTE:
                base *= 1000 * 60;
                break;
            case Calendar.SECOND:
                base *= 1000;
                break;
            default:
                break;
        }
        return (int) ((date1.getTime() - date2.getTime()) / base);
    }


    public static String getYesterday(String style, int day) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);

        Date curDate = new Date(System.currentTimeMillis());
        String curDateStr = formatter.format(curDate);
        Date nowDayByStyle;
        Calendar c = Calendar.getInstance();
        try {
            nowDayByStyle = formatter.parse(curDateStr);
            c.setTime(nowDayByStyle);
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter.format(c.getTime());
    }

    /**
     * 转换当前时间
     *
     * @param date
     * @return
     */
    public static String getCurrentTime(long date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(new Date(date));
        return str;
    }


    public static int getAge(Date birthDate) {

        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH)+1;
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDate);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH)+1;
        int dayBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayNow < dayBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }

        return age;
    }
    /**
     * 根据两个时间戳返回他们之间的天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getDay(long date1, long date2) {
    	 date1 = getYYYYMMDD(date1);
    	 date2 = getYYYYMMDD(date2);
    	
    	long time1 = date1-date2;
    	long time2 = 1000 * 60 * 60 * 24;
		long days = time1/time2;
		if (time1 > 0) {
			if (time1%time2 != 0) {
				days = days+1;
			}
		}else {
			if (time1%time2 != 0) {
				days = days-1;
			}
		}
    	return days;
    }
    
    
    public static long getYYYYMMDD(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		String date1 = sdf.format(calendar.getTime());
		Date date11 = null ;
		long longDate = 0L;
		try {
			date11 = sdf.parse(date1);
			longDate = date11.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return longDate;
	}
}
