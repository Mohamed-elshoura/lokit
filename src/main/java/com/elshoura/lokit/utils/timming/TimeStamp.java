package com.elshoura.lokit.utils.timming;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeStamp {


    public TimeStamp() {
        throw new IllegalStateException("TimeStamp class cannot be instantiated ");
    }

    public static java.sql.Timestamp currentTimeStampOfSql() {

        return new java.sql.Timestamp(System.currentTimeMillis());

    }
    public static String currentTimestampOfUtc() {
        //LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Africa/Cairo"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
