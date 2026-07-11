class NaturalNumber {

    private final int number;

    NaturalNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = number;
    }

    Classification getClassification() {
        int sum = 0;

        // 1 is a factor of every number greater than 1
        if (number > 1) {
            sum = 1;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;

                int pair = number / i;
                if (pair != i) {
                    sum += pair;
                }
            }
        }

        if (sum == number) {
            return Classification.PERFECT;
        } else if (sum > number) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }
}