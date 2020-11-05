package com.project.csr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间操作工具类
 *
 * @author: bin.tong
 * @date: 2020/7/27 17:24
 **/
public class DateUtils {

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * long 转 天/时/分/秒
     *
     * @param time
     * @return java.lang.String
     * @author LiuYongTao
     * @date 2019/8/15 17:15
     */
    public static String covertLongToString(long time) {
        long days = time / (1000 * 60 * 60 * 24);
        long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (time % (1000 * 60)) / 1000;
        return days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
    }

    public static String getSimpleDateFormat() {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(new Date());
    }

    public static String getSimpleDateFormat(Date date) {
        if (null == date) {
            return "";
        }
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return fmt.format(date);

    }

    public static String getSimpleDateFormat(Date date, String format) {
        if (null == date) {
            return "";
        }
        DateFormat fmt = new SimpleDateFormat(format);
        fmt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return fmt.format(date);
    }

    public static Date getDate(String strDate, String format) throws ParseException {
        DateFormat fmt = new SimpleDateFormat(format);
        return fmt.parse(strDate);
    }

    public static String getMonth(String month, int amount, String format) throws ParseException {
        Date date = getDate(month, format);
        return getSimpleDateFormat(addMonths(date, amount), format);
    }
}
