import java.util.HashSet;
import java.util.Set;

class IsogramChecker {

    boolean isIsogram(String phrase) {

        Set<Character> letters = new HashSet<>();

        for (char ch : phrase.toLowerCase().toCharArray()) {

            if (ch == ' ' || ch == '-') {
                continue;
            }

            if (letters.contains(ch)) {
                return false;
            }

            letters.add(ch);
        }

        return true;
    }
}