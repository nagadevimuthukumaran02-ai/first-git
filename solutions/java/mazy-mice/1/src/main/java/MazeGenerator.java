import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private static final char ENTRANCE_EXIT = '⇨';
    private static final char SPACE = ' ';

    // Box drawing lookup table indexed by bitmask: UP=1, RIGHT=2, DOWN=4, LEFT=8
    private static final char[] BOX_DRAWING = {
        ' ', // 0: None
        '│', // 1: U
        '─', // 2: R
        '└', // 3: U + R
        '│', // 4: D
        '│', // 5: U + D
        '┌', // 6: R + D
        '├', // 7: U + R + D
        '─', // 8: L
        '┘', // 9: U + L
        '─', // 10: R + L
        '┴', // 11: U + R + L
        '┐', // 12: D + L
        '┤', // 13: U + D + L
        '┬', // 14: R + D + L
        '┼'  // 15: U + R + D + L
    };

    public char[][] generatePerfectMaze(int rows, int columns) {
        return generatePerfectMaze(rows, columns, new Random());
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {
        return generatePerfectMaze(rows, columns, new Random(seed));
    }

    private char[][] generatePerfectMaze(int rows, int columns, Random random) {
        if (rows < 5 || rows > 100 || columns < 5 || columns > 100) {
            throw new IllegalArgumentException("Rows and columns must be between 5 and 100.");
        }

        int gridRows = 2 * rows + 1;
        int gridCols = 2 * columns + 1;

        // Initialize maze grid with wall placeholders
        boolean[][] hasWall = new boolean[gridRows][gridCols];
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                // Initialize all edges/walls
                if (r % 2 == 0 || c % 2 == 0) {
                    hasWall[r][c] = true;
                }
            }
        }

        // Generate passages using Randomized DFS (Backtracking)
        boolean[][] visited = new boolean[rows][columns];
        carvePassages(0, 0, rows, columns, visited, hasWall, random);

        // Pick random entrance and exit rows
        int entranceRow = random.nextInt(rows);
        int exitRow = random.nextInt(rows);

        // Remove outer walls at entrance and exit
        hasWall[2 * entranceRow + 1][0] = false;
        hasWall[2 * exitRow + 1][gridCols - 1] = false;

        // Render characters
        char[][] maze = new char[gridRows][gridCols];
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                if (!hasWall[r][c]) {
                    maze[r][c] = SPACE;
                } else {
                    int mask = 0;
                    if (r > 0 && hasWall[r - 1][c]) mask |= 1;           // Up
                    if (c < gridCols - 1 && hasWall[r][c + 1]) mask |= 2; // Right
                    if (r < gridRows - 1 && hasWall[r + 1][c]) mask |= 4; // Down
                    if (c > 0 && hasWall[r][c - 1]) mask |= 8;           // Left

                    maze[r][c] = BOX_DRAWING[mask];
                }
            }
        }

        // Place entrance and exit arrows
        maze[2 * entranceRow + 1][0] = ENTRANCE_EXIT;
        maze[2 * exitRow + 1][gridCols - 1] = ENTRANCE_EXIT;

        return maze;
    }

    private void carvePassages(int r, int c, int rows, int cols, boolean[][] visited, boolean[][] hasWall, Random random) {
        visited[r][c] = true;

        List<int[]> directions = new ArrayList<>(List.of(
            new int[]{-1, 0}, // Up
            new int[]{1, 0},  // Down
            new int[]{0, -1}, // Left
            new int[]{0, 1}   // Right
        ));
        Collections.shuffle(directions, random);

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                // Remove wall between current cell and neighbor cell
                int wallR = 2 * r + 1 + dir[0];
                int wallC = 2 * c + 1 + dir[1];
                hasWall[wallR][wallC] = false;

                carvePassages(nr, nc, rows, cols, visited, hasWall, random);
            }
        }
    }
}