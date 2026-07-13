class RunLengthEncoding {

    String encode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        int count = 1;
        char current = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == current) {
                count++;
            } else {
                if (count > 1) {
                    encoded.append(count);
                }
                encoded.append(current);
                current = input.charAt(i);
                count = 1;
            }
        }

        if (count > 1) {
            encoded.append(count);
        }
        encoded.append(current);

        return encoded.toString();
    }

    String decode(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            } else {
                int repeat = (count == 0) ? 1 : count;
                for (int j = 0; j < repeat; j++) {
                    decoded.append(ch);
                }
                count = 0;
            }
        }

        return decoded.toString();
    }
}