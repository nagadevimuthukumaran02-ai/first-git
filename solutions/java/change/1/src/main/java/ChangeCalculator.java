import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ChangeCalculator {

    private final List<Integer> coins;

    ChangeCalculator(List<Integer> currencyCoins) {
        this.coins = new ArrayList<>(currencyCoins);
        Collections.sort(this.coins);
    }

    List<Integer> computeMostEfficientChange(int grandTotal) {

        if (grandTotal < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }

        List<Integer>[] dp = new ArrayList[grandTotal + 1];
        dp[0] = new ArrayList<>();

        for (int amount = 1; amount <= grandTotal; amount++) {
            for (int coin : coins) {
                if (coin <= amount && dp[amount - coin] != null) {
                    List<Integer> candidate = new ArrayList<>(dp[amount - coin]);
                    candidate.add(coin);

                    if (dp[amount] == null || candidate.size() < dp[amount].size()) {
                        dp[amount] = candidate;
                    }
                }
            }
        }

        if (dp[grandTotal] == null) {
            throw new IllegalArgumentException("The total " + grandTotal + " cannot be represented in the given currency.");
        }

        Collections.sort(dp[grandTotal]);
        return dp[grandTotal];
    }
}