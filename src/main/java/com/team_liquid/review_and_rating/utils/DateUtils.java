package com.team_liquid.review_and_rating.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class DateUtils {
    public static Date getExpirationDate(Integer expiration){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expiration);
        return new Date(calendar.getTime().getTime());
    }

    public static String saveDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy", new Locale("en"));
                //DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }
}
