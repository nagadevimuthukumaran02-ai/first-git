import java.util.ArrayList;
import java.util.List;

class FlowerFieldBoard {

    private final List<String> boardRows;

    FlowerFieldBoard(List<String> boardRows) {
        if (boardRows == null) {
            throw new IllegalArgumentException("Board rows cannot be null");
        }

        // Validate board consistency (rectangular layout and character correctness)
        if (!boardRows.isEmpty()) {
            int expectedLength = boardRows.get(0).length();
            for (String row : boardRows) {
                if (row.length() != expectedLength) {
                    throw new IllegalArgumentException("Input grid is invalid");
                }
                for (char c : row.toCharArray()) {
                    if (c != ' ' && c != '*') {
                        throw new IllegalArgumentException("Invalid character in input");
                    }
                }
            }
        }
        this.boardRows = boardRows;
    }

    List<String> withNumbers() {
        if (boardRows.isEmpty()) {
            return new ArrayList<>();
        }

        int rows = boardRows.size();
        int cols = boardRows.get(0).length();
        List<String> annotatedBoard = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                char current = boardRows.get(r).charAt(c);
                if (current == '*') {
                    rowBuilder.append('*');
                } else {
                    int flowersCount = countAdjacentFlowers(r, c, rows, cols);
                    if (flowersCount > 0) {
                        rowBuilder.append(flowersCount);
                    } else {
                        rowBuilder.append(' ');
                    }
                }
            }
            annotatedBoard.add(rowBuilder.toString());
        }

        return annotatedBoard;
    }

    private int countAdjacentFlowers(int r, int c, int maxRows, int maxCols) {
        int count = 0;
        // Search the 3x3 grid around cell (r, c)
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                // Skip checking the target cell itself
                if (dr == 0 && dc == 0) {
                    continue;
                }
                int nr = r + dr;
                int nc = c + dc;
                // Boundary check
                if (nr >= 0 && nr < maxRows && nc >= 0 && nc < maxCols) {
                    if (boardRows.get(nr).charAt(nc) == '*') {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}