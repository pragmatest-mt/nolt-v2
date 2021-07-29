package com.pragmatest.nolt.restaurants.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperMethods {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");

    public static Date generateEstimatedDeliveryTime(int hours) throws ParseException {
        Calendar calendar = Calendar.getInstance();

        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return dateFormat.parse(dateFormat.format(calendar.getTime()));
    }
}
