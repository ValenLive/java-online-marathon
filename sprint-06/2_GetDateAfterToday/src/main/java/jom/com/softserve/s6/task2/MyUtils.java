package jom.com.softserve.s6.task2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MyUtils {
    public static String getDateAfterToday(int years, int months, int days) {
        return DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .format(Period.of(years, months, days).addTo(LocalDate.now()));
    }
}
