import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftScheduling {

    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        switch (description) {
            case "NOW":
                return meetingStart.plusHours(2);
            case "ASAP":
                return scheduleAsap(meetingStart);
            case "EOW":
                return scheduleEow(meetingStart);
        }

        Matcher monthMatcher = Pattern.compile("^(\\d{1,2})M$").matcher(description);
        if (monthMatcher.matches()) {
            int n = Integer.parseInt(monthMatcher.group(1));
            return scheduleMonth(n, meetingStart);
        }

        Matcher quarterMatcher = Pattern.compile("^Q(\\d)$").matcher(description);
        if (quarterMatcher.matches()) {
            int n = Integer.parseInt(quarterMatcher.group(1));
            return scheduleQuarter(n, meetingStart);
        }

        throw new IllegalArgumentException("Unknown description: " + description);
    }

    private static LocalDateTime scheduleAsap(LocalDateTime meetingStart) {
        if (meetingStart.getHour() < 13) {
            return meetingStart.toLocalDate().atTime(17, 0);
        } else {
            return meetingStart.toLocalDate().plusDays(1).atTime(13, 0);
        }
    }

    private static LocalDateTime scheduleEow(LocalDateTime meetingStart) {
        DayOfWeek day = meetingStart.getDayOfWeek();
        LocalDate date = meetingStart.toLocalDate();
        if (day == DayOfWeek.MONDAY || day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY) {
            return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).atTime(17, 0);
        } else {
            return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(20, 0);
        }
    }

    private static LocalDateTime scheduleMonth(int n, LocalDateTime meetingStart) {
        int year = meetingStart.getYear();
        int currentMonth = meetingStart.getMonthValue();
        // "after or in" N-th month -> next year; "before" -> this year
        if (n <= currentMonth) {
            year += 1;
        }
        LocalDate firstOfMonth = LocalDate.of(year, n, 1);
        LocalDate firstWorkday = firstWorkdayOnOrAfter(firstOfMonth);
        return firstWorkday.atTime(8, 0);
    }

    private static LocalDateTime scheduleQuarter(int n, LocalDateTime meetingStart) {
        int year = meetingStart.getYear();
        int currentQuarter = (meetingStart.getMonthValue() - 1) / 3 + 1;
        // "after" N-th quarter -> next year; "before or in" -> this year
        if (n < currentQuarter) {
            year += 1;
        }
        int lastMonthOfQuarter = n * 3;
        YearMonth ym = YearMonth.of(year, lastMonthOfQuarter);
        LocalDate lastDayOfMonth = ym.atEndOfMonth();
        LocalDate lastWorkday = lastWorkdayOnOrBefore(lastDayOfMonth);
        return lastWorkday.atTime(8, 0);
    }

    private static LocalDate firstWorkdayOnOrAfter(LocalDate date) {
        while (isWeekend(date)) {
            date = date.plusDays(1);
        }
        return date;
    }

    private static LocalDate lastWorkdayOnOrBefore(LocalDate date) {
        while (isWeekend(date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}