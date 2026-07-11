class Say {

    private static final String[] ONES = {
        "zero", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen",
        "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
        "", "", "twenty", "thirty", "forty",
        "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    String say(long number) {

        if (number < 0 || number > 999999999999L) {
            throw new IllegalArgumentException();
        }

        if (number == 0) {
            return "zero";
        }

        return convert(number).trim();
    }

    private String convert(long number) {

        if (number < 20) {
            return ONES[(int) number];
        }

        if (number < 100) {
            return TENS[(int) (number / 10)]
                    + (number % 10 == 0 ? "" : "-" + convert(number % 10));
        }

        if (number < 1000) {
            return convert(number / 100) + " hundred"
                    + (number % 100 == 0 ? "" : " " + convert(number % 100));
        }

        if (number < 1_000_000) {
            return convert(number / 1000) + " thousand"
                    + (number % 1000 == 0 ? "" : " " + convert(number % 1000));
        }

        if (number < 1_000_000_000) {
            return convert(number / 1_000_000) + " million"
                    + (number % 1_000_000 == 0 ? "" : " " + convert(number % 1_000_000));
        }

        return convert(number / 1_000_000_000) + " billion"
                + (number % 1_000_000_000 == 0 ? "" : " " + convert(number % 1_000_000_000));
    }
}