import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

class ParallelLetterFrequency {

    private final String[] texts;

    ParallelLetterFrequency(String[] texts) {
        this.texts = texts;
    }

    Map<Character, Integer> countLetters() {
        Map<Character, Integer> frequency = new ConcurrentHashMap<>();

        Stream.of(texts)
                .parallel()
                .forEach(text -> {
                    for (char c : text.toLowerCase().toCharArray()) {
                        if (Character.isLetter(c)) {
                            frequency.merge(c, 1, Integer::sum);
                        }
                    }
                });

        return frequency;
    }
}