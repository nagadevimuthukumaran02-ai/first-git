import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class PalindromeCalculator {

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) {
            throw new IllegalArgumentException("invalid input: min must be <= max");
        }

        SortedMap<Long, List<List<Integer>>> result = new TreeMap<>();
        long minPalindrome = Long.MAX_VALUE;
        long maxPalindrome = Long.MIN_VALUE;

        List<List<Integer>> minFactors = new ArrayList<>();
        List<List<Integer>> maxFactors = new ArrayList<>();

        for (int i = minFactor; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j++) {
                long product = (long) i * j;

                if (isPalindrome(product)) {
                    // Update smallest palindrome tracking
                    if (product < minPalindrome) {
                        minPalindrome = product;
                        minFactors = new ArrayList<>();
                        minFactors.add(Arrays.asList(i, j));
                    } else if (product == minPalindrome) {
                        minFactors.add(Arrays.asList(i, j));
                    }

                    // Update largest palindrome tracking
                    if (product > maxPalindrome) {
                        maxPalindrome = product;
                        maxFactors = new ArrayList<>();
                        maxFactors.add(Arrays.asList(i, j));
                    } else if (product == maxPalindrome) {
                        maxFactors.add(Arrays.asList(i, j));
                    }
                }
            }
        }

        if (minPalindrome != Long.MAX_VALUE) {
            result.put(minPalindrome, minFactors);
            result.put(maxPalindrome, maxFactors);
        }

        return result;
    }

    private boolean isPalindrome(long number) {
        long original = number;
        long reversed = 0;

        while (number > 0) {
            reversed = reversed * 10 + (number % 10);
            number /= 10;
        }

        return original == reversed;
    }
}