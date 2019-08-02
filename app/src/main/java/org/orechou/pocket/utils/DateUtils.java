package org.orechou.pocket.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy年MM月dd日");

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

    public static String formatDate(Date date) {
        return simpleFormat.format(date);
    }

}
