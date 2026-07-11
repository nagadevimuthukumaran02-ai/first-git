class CollatzCalculator {

    int computeStepCount(int start) {

        if (start <= 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }

        int steps = 0;
        long number = start;

        while (number != 1) {
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = number * 3 + 1;
            }
            steps++;
        }

        return steps;
    }
}