package com.eva.sessionManagement.utilty;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TimeConverter implements ITimeConverter{

    public LocalTime convertToLocalTime(long timeLong) {
        Date date1 = new Date(timeLong);
        Instant current = date1.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
        LocalTime localTime = ldt.toLocalTime();
        return localTime;
    }

}
