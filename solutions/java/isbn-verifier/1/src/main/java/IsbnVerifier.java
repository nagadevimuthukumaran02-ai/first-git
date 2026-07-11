class IsbnVerifier {

    boolean isValid(String stringToVerify) {

        String isbn = stringToVerify.replace("-", "");

        if (isbn.length() != 10) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            char ch = isbn.charAt(i);
            int value;

            if (i == 9 && ch == 'X') {
                value = 10;
            } else if (Character.isDigit(ch)) {
                value = ch - '0';
            } else {
                return false;
            }

            sum += value * (10 - i);
        }

        return sum % 11 == 0;
    }
}