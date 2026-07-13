import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Robot {

    private static final Random RANDOM = new Random();
    private static final Set<String> USED_NAMES = new HashSet<>();

    private String name;

    String getName() {
        if (name == null) {
            name = generateUniqueName();
        }
        return name;
    }

    void reset() {
        if (name != null) {
            USED_NAMES.remove(name);
            name = null;
        }
    }

    private String generateUniqueName() {
        String candidate;

        do {
            candidate = ""
                    + (char) ('A' + RANDOM.nextInt(26))
                    + (char) ('A' + RANDOM.nextInt(26))
                    + RANDOM.nextInt(10)
                    + RANDOM.nextInt(10)
                    + RANDOM.nextInt(10);
        } while (USED_NAMES.contains(candidate));

        USED_NAMES.add(candidate);
        return candidate;
    }
}