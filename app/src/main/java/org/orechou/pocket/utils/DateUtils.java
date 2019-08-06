package org.orechou.pocket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy年MM月dd日");
    static SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat dateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 获取当前日期与指定时间的天数差
     * @param date 指定日期
     * @return 天数差
     */
    public static int getDaysDifferenceFromNow(Date date) {
        int days = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate nowDate = LocalDate.now();

            // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
            LocalDate targetDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(nowDate, targetDate);
            days = period.getDays();
        }

        return days;
    }

    /**
     * 获取现在的时间
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取本周第一个时刻
     * @return
     */
    public static Date getFirstTimeOfCurrentWeek() {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String dateStr = dateFormatWithoutTime.format(calendar.getTime()) + " 00:00:00";
        try {
            date = dateFormatWithTime.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取本周最后一个时刻
     * @return
     */
    public static Date getLastTimeOfCurrentWeek() {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String dateStr = dateFormatWithoutTime.format(calendar.getTime()) + " 23:59:59";
        try {
            date = dateFormatWithTime.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间是星期几
     * @param date
     * @return
     */
    public static int getWeekIndex(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);

        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(weekIndex <= 0){
            weekIndex = 7;
        }
        return weekIndex;
    }

    public static String formatDate(Date date) {
        return simpleFormat.format(date);
    }

    public static String formatDateWithoutTime(Date date) {
        return dateFormatWithoutTime.format(date);
    }

    public static String formatDateWithTime(Date date) {
        return dateFormatWithTime.format(date);
    }
}
