package com.kail.human_readable_duration_format;

import java.text.MessageFormat;

public class YearMonthDayFormat {
    
    public static String formatDuration(long seconds) {
        if(seconds == 0) {
            return "now";
        }
        long year = seconds / (365 * 24 * 60 * 60);
        long d = seconds % (365 * 24 * 60 * 60);
        long day =  d / (24 * 60 * 60);
        d = d % (24 * 60 * 60);
        long hours = d / (60 * 60);
        d = d % (60 * 60);
        long minutes = d / 60;
        long sec = d % 60;
        
        int isAnd = sec > 0 ? 4 : 5;
        isAnd = isAnd == 5 && minutes > 0 ? 3 : isAnd;
        isAnd = isAnd == 5 && hours > 0 ? 2 : isAnd;
        isAnd = isAnd == 5 && day > 0 ? 1 : isAnd;
        
        String fn = MessageFormat.format(
                "{0,choice,0#|1#{0} year|1<{0} years}" +
                        "{1,choice,0#|0<'{5,choice,1# and|1<,}' {1} '{1,choice,1#day|1<days}'}" +
                        "{2,choice,0#|0<'{5,choice,2# and|2<,}' {2} '{2,choice,1#hour|1<hours}'}" +
                        "{3,choice,0#|0<'{5,choice,3# and|3<,}' {3} '{3,choice,1#minute|1<minutes}'}" +
                        "{4,choice,0#|0<'{5,choice,4# and|4<,}' {4} '{4,choice,1#second|1<seconds}'}",
                new Object[]{year, day, hours, minutes, sec, isAnd});
        if (fn.trim().startsWith(",")) {
            return fn.replaceFirst(",", "").trim();
        }
        if (fn.trim().startsWith("and")) {
            return fn.replaceFirst("and", "").trim();
        }
        return fn.trim();
    }
    
    public static void main(String[] args) {
        System.out.println(YearMonthDayFormat.formatDuration(1));
    }
    
}
