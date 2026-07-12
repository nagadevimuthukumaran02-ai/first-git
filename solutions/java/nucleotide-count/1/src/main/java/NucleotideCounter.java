import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private final Map<Character, Integer> counts = new HashMap<>();

    NucleotideCounter(String sequence) {
        counts.put('A', 0);
        counts.put('C', 0);
        counts.put('G', 0);
        counts.put('T', 0);

        for (char ch : sequence.toCharArray()) {
            if (!counts.containsKey(ch)) {
                throw new IllegalArgumentException("Invalid nucleotide in strand");
            }
            counts.put(ch, counts.get(ch) + 1);
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        return counts;
    }
}