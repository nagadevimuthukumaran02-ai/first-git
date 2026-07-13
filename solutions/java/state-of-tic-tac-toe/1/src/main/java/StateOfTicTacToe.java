class StateOfTicTacToe {

    static GameState determineState(String[] board) {
        long xCount = countChar(board, 'X');
        long oCount = countChar(board, 'O');

        if (oCount > xCount) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }
        if (xCount > oCount + 1) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }

        boolean xWins = hasWin(board, 'X');
        boolean oWins = hasWin(board, 'O');

        if (xWins && oWins) {
            throw new IllegalArgumentException("Impossible board: game should have ended after the game was won");
        }

        if (xWins) {
            if (xCount != oCount + 1) {
                throw new IllegalArgumentException("Wrong turn order: game should have ended after the game was won");
            }
            return GameState.WIN;
        }

        if (oWins) {
            if (xCount != oCount) {
                throw new IllegalArgumentException("Wrong turn order: game should have ended after the game was won");
            }
            return GameState.WIN;
        }

        if (xCount + oCount == 9) {
            return GameState.DRAW;
        }

        return GameState.ONGOING;
    }

    private static long countChar(String[] board, char c) {
        long count = 0;
        for (String row : board) {
            for (char ch : row.toCharArray()) {
                if (ch == c) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean hasWin(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == c && board[1].charAt(j) == c && board[2].charAt(j) == c) {
                return true;
            }
        }

        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }

        return false;
    }
}