import java.util.HashSet;
import java.util.Set;

class SumOfMultiples {

    private final int number;
    private final int[] set;

    SumOfMultiples(int number, int[] set) {
        this.number = number;
        this.set = set;
    }

    int getSum() {
        Set<Integer> multiples = new HashSet<>();

        for (int value : set) {
            if (value == 0) {
                continue;
            }

            for (int i = value; i < number; i += value) {
                multiples.add(i);
            }
        }

        int sum = 0;
        for (int multiple : multiples) {
            sum += multiple;
        }

        return sum;
    }
}