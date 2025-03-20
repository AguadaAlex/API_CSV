package com.test.API_CSV.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;

@Component
public class DateUtil {

    public static String getCorrectOffsetDateTime(String offsetDateTime) {
        OffsetDateTime odt = OffsetDateTime.parse(offsetDateTime);
        OffsetDateTime odtWithOffsetTwoHours = odt.withOffsetSameInstant(ZoneOffset.of("-03:00"));
        LocalDateTime ldt = odtWithOffsetTwoHours.toLocalDateTime();
        String localDateTime = ldt.toString();
        return localDateTime;
    }

    public static String getCorrectOffsetDateTimeFilePrefix(String offsetDateTime) {
        OffsetDateTime odt = OffsetDateTime.parse(offsetDateTime);
        OffsetDateTime odtWithOffsetTwoHours = odt.withOffsetSameInstant(ZoneOffset.of("-03:00"));
        LocalDateTime ldt = odtWithOffsetTwoHours.toLocalDateTime();
        String localDateTime = ldt.toString();
        return localDateTime;
    }

    public static String getCorrectOffsetDateTimeMore3(String offsetDateTime) {
        OffsetDateTime odt = OffsetDateTime.parse(offsetDateTime);
        OffsetDateTime odtWithOffsetTwoHours = odt.withOffsetSameInstant(ZoneOffset.of("+03:00"));
        LocalDateTime ldt = odtWithOffsetTwoHours.toLocalDateTime();
        String localDateTime = ldt.toString();
        return localDateTime;
    }
    public static LocalDate findNextDay(LocalDate localdate) {
        return localdate.plusDays(1);
    }

    public static LocalDate findPrevDay(LocalDate localdate) {
        return localdate.minusDays(1);
    }

    public static String getCurrentYear() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public static String getCurrentMonth() {
        return String.valueOf(Calendar.getInstance().get(Calendar.MONTH) +1);
    }

    public static String getCurrentDay() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    public static String getCurrentDate() {
        return LocalDate.now().toString();
    }
}