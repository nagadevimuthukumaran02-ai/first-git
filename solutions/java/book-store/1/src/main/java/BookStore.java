import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookStore {

    private static final double[] PRICES = {
        0.0,    // 0 books
        8.0,    // 1 book
        15.2,   // 2 books (5% discount)
        21.6,   // 3 books (10% discount)
        25.6,   // 4 books (20% discount)
        30.0    // 5 books (25% discount)
    };

    private final Map<String, Double> memo = new HashMap<>();

    double calculateBasketCost(List<Integer> books) {
        int[] counts = new int[5];

        for (int book : books) {
            counts[book - 1]++;
        }

        Arrays.sort(counts);
        return minimumCost(counts);
    }

    private double minimumCost(int[] counts) {
        Arrays.sort(counts);
        String key = Arrays.toString(counts);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean empty = true;
        for (int count : counts) {
            if (count > 0) {
                empty = false;
                break;
            }
        }

        if (empty) {
            return 0.0;
        }

        double best = Double.MAX_VALUE;

        int available = 0;
        for (int count : counts) {
            if (count > 0) {
                available++;
            }
        }

        for (int size = 1; size <= available; size++) {
            int[] next = counts.clone();

            int removed = 0;
            for (int i = 4; i >= 0 && removed < size; i--) {
                if (next[i] > 0) {
                    next[i]--;
                    removed++;
                }
            }

            double cost = PRICES[size] + minimumCost(next);
            best = Math.min(best, cost);
        }

        memo.put(key, best);
        return best;
    }
}