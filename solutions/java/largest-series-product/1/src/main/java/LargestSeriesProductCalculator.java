class LargestSeriesProductCalculator {

    private final String input;

    // Constructor
    LargestSeriesProductCalculator(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(
                    "String to search may only contain digits."
                );
            }
        }
        this.input = input;
    }

    long calculateLargestProductForSeriesLength(int span) {

        if (span < 0) {
            throw new IllegalArgumentException(
                "Series length must be non-negative."
            );
        }

        if (span > input.length()) {
            throw new IllegalArgumentException(
                "Series length must be less than or equal to the length of the string to search."
            );
        }

        if (span == 0) {
            return 1;
        }

        long maxProduct = 0;

        for (int i = 0; i <= input.length() - span; i++) {
            long product = 1;

            for (int j = i; j < i + span; j++) {
                product *= (input.charAt(j) - '0');
            }

            if (product > maxProduct) {
                maxProduct = product;
            }
        }

        return maxProduct;
    }
}