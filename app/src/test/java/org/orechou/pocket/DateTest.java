package org.orechou.pocket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

    private static final String TAG = "DateTest";

    public static void main(String[] args) {

        Date now = new Date();
        System.out.println(now.getTime() + " " + now.toString());

        Calendar calendar = Calendar.getInstance();
        now = calendar.getTime();
        System.out.println(now.getTime() + " " + now.toString());

        now = calendar.getTime();
        System.out.println(now.getTime() + " " + now.toString());


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str = "2019-08-06 00:00:00";
        try {
            now = format.parse(str);
            System.out.println(now.getTime() + " " + now.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
