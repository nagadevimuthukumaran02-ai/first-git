import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class BafflingBirthdays {

    private static final Random RANDOM = new Random();
    private static final int YEAR = 2021; // Non-leap year

    boolean sharedBirthday(List<LocalDate> birthdates) {
        Set<String> seen = new HashSet<>();
        for (LocalDate date : birthdates) {
            String birthday = date.getMonthValue() + "-" + date.getDayOfMonth();
            if (!seen.add(birthday)) {
                return true;
            }
        }
        return false;
    }

    List<LocalDate> randomBirthdates(int groupSize) {
        List<LocalDate> birthdates = new ArrayList<>();
        for (int i = 0; i < groupSize; i++) {
            int day = RANDOM.nextInt(365) + 1;
            birthdates.add(LocalDate.ofYearDay(YEAR, day));
        }
        return birthdates;
    }

    double estimatedProbabilityOfSharedBirthday(int groupSize) {
        if (groupSize <= 1) {
            return 0.0;
        }
        if (groupSize > 365) {
            return 100.0;
        }

        double probabilityNoMatch = 1.0;
        for (int i = 0; i < groupSize; i++) {
            probabilityNoMatch *= (365.0 - i) / 365.0;
        }

        return (1.0 - probabilityNoMatch) * 100.0;
    }
}