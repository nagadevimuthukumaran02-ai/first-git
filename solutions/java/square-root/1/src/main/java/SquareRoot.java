class SquareRoot {

    int squareRoot(int number) {
        int left = 1;
        int right = number;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == number) {
                return mid;
            } else if (square < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // This line is never reached for valid Exercism inputs.
    }
}