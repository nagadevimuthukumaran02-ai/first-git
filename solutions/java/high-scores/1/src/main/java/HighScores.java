import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {

    private final List<Integer> scores;

    public HighScores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> scores() {
        return scores;
    }

    public int latest() {
        return scores.get(scores.size() - 1);
    }

    public int personalBest() {
        return Collections.max(scores);
    }

    public List<Integer> personalTopThree() {
        List<Integer> topScores = new ArrayList<>(scores);
        topScores.sort(Collections.reverseOrder());

        if (topScores.size() > 3) {
            return topScores.subList(0, 3);
        }

        return topScores;
    }
}