class LuhnValidator {

    boolean isValid(String candidate) {

        // Remove spaces
        candidate = candidate.replace(" ", "");

        // Must contain at least two digits
        if (candidate.length() <= 1) {
            return false;
        }

        // Only digits are allowed
        for (char c : candidate.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        int sum = 0;
        boolean doubleDigit = false;

        // Process from right to left
        for (int i = candidate.length() - 1; i >= 0; i--) {
            int digit = candidate.charAt(i) - '0';

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }
}