import java.util.ArrayList;
import java.util.List;

class Sieve {

    private final int limit;

    Sieve(int limit) {
        this.limit = limit;
    }

    List<Integer> getPrimes() {
        List<Integer> primes = new ArrayList<>();

        if (limit < 2) {
            return primes;
        }

        boolean[] isComposite = new boolean[limit + 1];

        for (int i = 2; i * i <= limit; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}