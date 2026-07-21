import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

class Meetup {

    private final int month;
    private final int year;

    Meetup(int monthOfYear, int year) {
        this.month = monthOfYear;
        this.year = year;
    }

    LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {

        switch (schedule) {
            case FIRST:
                return nth(dayOfWeek, 1);

            case SECOND:
                return nth(dayOfWeek, 2);

            case THIRD:
                return nth(dayOfWeek, 3);

            case FOURTH:
                return nth(dayOfWeek, 4);

            case LAST:
                return last(dayOfWeek);

            case TEENTH:
                return teenth(dayOfWeek);

            default:
                throw new IllegalArgumentException();
        }
    }

    private LocalDate nth(DayOfWeek dayOfWeek, int n) {
        LocalDate date = LocalDate.of(year, month, 1);

        while (date.getDayOfWeek() != dayOfWeek) {
            date = date.plusDays(1);
        }

        return date.plusWeeks(n - 1);
    }

    private LocalDate last(DayOfWeek dayOfWeek) {
        LocalDate date = YearMonth.of(year, month).atEndOfMonth();

        while (date.getDayOfWeek() != dayOfWeek) {
            date = date.minusDays(1);
        }

        return date;
    }

    private LocalDate teenth(DayOfWeek dayOfWeek) {
        LocalDate date = LocalDate.of(year, month, 13);

        while (date.getDayOfWeek() != dayOfWeek) {
            date = date.plusDays(1);
        }

        return date;
    }
}