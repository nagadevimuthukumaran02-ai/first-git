class RotationalCipher {

    private final int key;

    RotationalCipher(int key) {
        this.key = key;
    }

    String rotate(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                result.append((char) ('a' + (ch - 'a' + key) % 26));
            } else if (ch >= 'A' && ch <= 'Z') {
                result.append((char) ('A' + (ch - 'A' + key) % 26));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}