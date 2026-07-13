import java.util.HashMap;
import java.util.Map;

class WordCount {

    Map<String, Integer> phrase(String input) {
        Map<String, Integer> counts = new HashMap<>();

        String[] words = input
                .toLowerCase()
                .split("[^a-z0-9']+");

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Remove apostrophes at the beginning or end of a word,
            // but keep apostrophes inside contractions.
            word = word.replaceAll("^'+|'+$", "");

            if (word.isEmpty()) {
                continue;
            }

            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        return counts;
    }
}