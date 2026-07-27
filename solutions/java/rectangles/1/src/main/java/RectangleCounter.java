class RectangleCounter {

    int countRectangles(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length();
        int count = 0;

        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                if (grid[r1].charAt(c1) != '+') {
                    continue;
                }

                // Look for top-right corner on the same row
                for (int c2 = c1 + 1; c2 < cols; c2++) {
                    if (grid[r1].charAt(c2) != '+') {
                        // Horizontal line must remain continuous with '-' or '+'
                        if (!isHorizontalEdge(grid[r1].charAt(c2))) {
                            break;
                        }
                        continue;
                    }

                    // Top horizontal edge must be fully connected
                    if (!isConnectedHorizontally(grid, r1, c1, c2)) {
                        break;
                    }

                    // Look down for bottom-left and bottom-right corners
                    for (int r2 = r1 + 1; r2 < rows; r2++) {
                        // Check if vertical sides are still valid down to row r2
                        if (!isVerticalEdge(grid[r2].charAt(c1)) || !isVerticalEdge(grid[r2].charAt(c2))) {
                            break; // Stop going further down if side walls break
                        }

                        // Check if both bottom corners exist
                        if (grid[r2].charAt(c1) == '+' && grid[r2].charAt(c2) == '+') {
                            // Verify the bottom horizontal line is intact
                            if (isConnectedHorizontally(grid, r2, c1, c2)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    private boolean isHorizontalEdge(char ch) {
        return ch == '-' || ch == '+';
    }

    private boolean isVerticalEdge(char ch) {
        return ch == '|' || ch == '+';
    }

    private boolean isConnectedHorizontally(String[] grid, int row, int col1, int col2) {
        for (int c = col1 + 1; c < col2; c++) {
            if (!isHorizontalEdge(grid[row].charAt(c))) {
                return false;
            }
        }
        return true;
    }
}