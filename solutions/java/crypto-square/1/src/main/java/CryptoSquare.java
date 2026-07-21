class CryptoSquare {

    private final String normalizedText;

    CryptoSquare(String plaintext) {
        StringBuilder sb = new StringBuilder();

        for (char c : plaintext.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }

        normalizedText = sb.toString();
    }

    String getCiphertext() {
        if (normalizedText.isEmpty()) {
            return "";
        }

        int length = normalizedText.length();
        int columns = (int) Math.ceil(Math.sqrt(length));
        int rows = (int) Math.ceil((double) length / columns);

        StringBuilder result = new StringBuilder();

        for (int col = 0; col < columns; col++) {
            if (col > 0) {
                result.append(' ');
            }

            for (int row = 0; row < rows; row++) {
                int index = row * columns + col;

                if (index < length) {
                    result.append(normalizedText.charAt(index));
                } else {
                    result.append(' ');
                }
            }
        }

        return result.toString();
    }
}