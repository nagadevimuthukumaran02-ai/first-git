import java.util.List;

class Knapsack {

    int maximumValue(int maximumWeight, List<Item> items) {
        int[] dp = new int[maximumWeight + 1];

        for (Item item : items) {
            int weight = item.weight;
            int value = item.value;

            for (int capacity = maximumWeight; capacity >= weight; capacity--) {
                dp[capacity] = Math.max(dp[capacity], dp[capacity - weight] + value);
            }
        }

        return dp[maximumWeight];
    }
}