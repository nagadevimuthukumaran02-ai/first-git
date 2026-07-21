import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class WordSearcher {

    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        Map<String, Optional<WordLocation>> result = new HashMap<>();

        for (String word : words) {
            result.put(word, findWord(word, grid));
        }

        return result;
    }

    private Optional<WordLocation> findWord(String word, char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (grid[row][col] != word.charAt(0)) {
                    continue;
                }

                for (int[] dir : DIRECTIONS) {
                    int dr = dir[0];
                    int dc = dir[1];

                    int r = row;
                    int c = col;
                    int i;

                    for (i = 0; i < word.length(); i++) {
                        if (r < 0 || r >= rows || c < 0 || c >= cols) {
                            break;
                        }

                        if (grid[r][c] != word.charAt(i)) {
                            break;
                        }

                        r += dr;
                        c += dc;
                    }

                    if (i == word.length()) {
                       Pair start = new Pair(col + 1, row + 1);

Pair end = new Pair(
    col + dc * (word.length() - 1) + 1,
    row + dr * (word.length() - 1) + 1
);

                        return Optional.of(new WordLocation(start, end));
                    }
                }
            }
        }

        return Optional.empty();
    }
}