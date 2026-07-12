class GameOfLife {
    public int[][] tick(int[][] matrix) {
        int rows = matrix.length;

        if (rows == 0) {
            return new int[0][0];
        }

        int cols = matrix[0].length;
        int[][] next = new int[rows][cols];
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        liveNeighbors += matrix[nr][nc];
                    }
                }
                if (matrix[r][c] == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) {
                        next[r][c] = 1;
                    } else {
                        next[r][c] = 0;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        next[r][c] = 1;
                    } else {
                        next[r][c] = 0;
                    }
                }
            }
        }
        return next;
    }
}