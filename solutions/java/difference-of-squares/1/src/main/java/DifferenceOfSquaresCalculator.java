class DifferenceOfSquaresCalculator {

    DifferenceOfSquaresCalculator() {
    }

    int computeSquareOfSumTo(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        return sum * sum;
    }

    int computeSumOfSquaresTo(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i * i;
        }
        return sum;
    }

    int computeDifferenceOfSquares(int number) {
        return computeSquareOfSumTo(number) - computeSumOfSquaresTo(number);
    }
}