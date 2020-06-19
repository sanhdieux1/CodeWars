package com.kail;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {
    
    @Test
    public void utcTest() {
        System.out.println(convertDateToDate7String(getStartDateUTC()));
        System.out.println(convertDateToDate7String(getEndDateUTC()));
        System.out.println(convertDateToDate7String(Calendar.getInstance().getTime()));
    }
    
    public static String convertDateToDate7String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(formatter.format(date));
        formatter = new SimpleDateFormat("YYYY MM dd HH:mm:ss");
//        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String result = formatter.format(date);
        return result;
    }
    
    private Date getStartDateUTC() {
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        ZonedDateTime utcTimeNow = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime beginDay = utcTimeNow.toLocalDate().atStartOfDay(utcZone.toZoneId());
        
        return Date.from(beginDay.toInstant());
    }
    
    private Date getEndDateUTC() {
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        ZonedDateTime utcTimeNow = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime beginDay = utcTimeNow.toLocalDate().atStartOfDay(utcZone.toZoneId());
        ZonedDateTime endDate = beginDay.plusDays(1);
        endDate = endDate.plusMinutes(-1);
        return Date.from(endDate.toInstant());
    }
}
