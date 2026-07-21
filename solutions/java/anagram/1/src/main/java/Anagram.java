import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Anagram {

    private final String word;
    private final String sortedWord;

    public Anagram(String word) {
        this.word = word.toLowerCase();
        this.sortedWord = sort(word.toLowerCase());
    }

    public List<String> match(List<String> candidates) {
        List<String> result = new ArrayList<>();

        for (String candidate : candidates) {
            String lowerCandidate = candidate.toLowerCase();

            // A word is not its own anagram
            if (lowerCandidate.equals(word)) {
                continue;
            }

            if (sortedWord.equals(sort(lowerCandidate))) {
                result.add(candidate);
            }
        }

        return result;
    }

    private String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}