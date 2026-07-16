import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FoodChain {

    private static final String[] ANIMALS = {
        "", "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"
    };

    private static final String[] REACTIONS = {
        "",
        "", // Fly has no unique secondary reaction line
        "It wriggled and jiggled and tickled inside her.\n",
        "How absurd to swallow a bird!\n",
        "Imagine that, to swallow a cat!\n",
        "What a hog, to swallow a dog!\n",
        "Just opened her throat and swallowed a goat!\n",
        "I don't know how she swallowed a cow!\n",
        "She's dead, of course!" // Horse instantly ends the song
    };

    String verse(int verseNumber) {
        StringBuilder sb = new StringBuilder();
        
        // Opening line
        sb.append("I know an old lady who swallowed a ").append(ANIMALS[verseNumber]).append(".\n");
        
        // Second line reaction
        sb.append(REACTIONS[verseNumber]);

        // Terminal case: Horse ends the song immediately
        if (verseNumber == 8) {
            return sb.toString();
        }

        // Build cumulative chase chain backwards
        for (int i = verseNumber; i > 1; i--) {
            sb.append("She swallowed the ").append(ANIMALS[i])
              .append(" to catch the ").append(ANIMALS[i - 1]);
            
            // The spider gets an extra description when being chased by the bird
            if (i == 3) {
                sb.append(" that wriggled and jiggled and tickled inside her");
            }
            sb.append(".\n");
        }

        // Standard closing line
        sb.append("I don't know why she swallowed the fly. Perhaps she'll die.");
        
        return sb.toString();
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.rangeClosed(startVerse, endVerse)
                .mapToObj(this::verse)
                .collect(Collectors.joining("\n\n"));
    }
}