class Atbash {

    String encode(String input) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (char ch : input.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                if (count == 5) {
                    result.append(' ');
                    count = 0;
                }
                result.append((char) ('z' - (ch - 'a')));
                count++;
            } else if (Character.isDigit(ch)) {
                if (count == 5) {
                    result.append(' ');
                    count = 0;
                }
                result.append(ch);
                count++;
            }
        }

        return result.toString();
    }

    String decode(String input) {
        StringBuilder result = new StringBuilder();

        for (char ch : input.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                result.append((char) ('z' - (ch - 'a')));
            } else if (Character.isDigit(ch)) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}