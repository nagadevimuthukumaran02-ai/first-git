class RailFenceCipher {

    private final int rows;

    RailFenceCipher(int rows) {
        this.rows = rows;
    }

    String getEncryptedData(String message) {
        if (rows == 1 || message.isEmpty()) {
            return message;
        }

        StringBuilder[] rails = new StringBuilder[rows];
        for (int i = 0; i < rows; i++) {
            rails[i] = new StringBuilder();
        }

        int rail = 0;
        int direction = 1;

        for (char ch : message.toCharArray()) {
            rails[rail].append(ch);

            if (rail == 0) {
                direction = 1;
            } else if (rail == rows - 1) {
                direction = -1;
            }

            rail += direction;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rails) {
            result.append(sb);
        }

        return result.toString();
    }

    String getDecryptedData(String message) {
        if (rows == 1 || message.isEmpty()) {
            return message;
        }

        int length = message.length();

        // Mark zig-zag positions
        char[][] rail = new char[rows][length];

        int row = 0;
        int direction = 1;

        for (int col = 0; col < length; col++) {
            rail[row][col] = '*';

            if (row == 0) {
                direction = 1;
            } else if (row == rows - 1) {
                direction = -1;
            }

            row += direction;
        }

        // Fill marked positions with ciphertext
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < length; j++) {
                if (rail[i][j] == '*' && index < length) {
                    rail[i][j] = message.charAt(index++);
                }
            }
        }

        // Read zig-zag to reconstruct plaintext
        StringBuilder result = new StringBuilder();

        row = 0;
        direction = 1;

        for (int col = 0; col < length; col++) {
            result.append(rail[row][col]);

            if (row == 0) {
                direction = 1;
            } else if (row == rows - 1) {
                direction = -1;
            }

            row += direction;
        }

        return result.toString();
    }
}