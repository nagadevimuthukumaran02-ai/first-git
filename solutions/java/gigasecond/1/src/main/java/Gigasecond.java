import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

class Gigasecond {

    private static final long GIGASECOND = 1_000_000_000L;
    private final LocalDateTime dateTime;

    Gigasecond(Temporal moment) {
        if (moment instanceof LocalDate) {
            this.dateTime = ((LocalDate) moment)
                    .atStartOfDay()
                    .plusSeconds(GIGASECOND);
        } else {
            this.dateTime = ((LocalDateTime) moment)
                    .plusSeconds(GIGASECOND);
        }
    }

    LocalDateTime getDateTime() {
        return dateTime;
    }
}