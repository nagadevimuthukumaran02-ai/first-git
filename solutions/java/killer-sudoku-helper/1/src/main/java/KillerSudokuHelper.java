import java.util.ArrayList;
import java.util.List;

class KillerSudokuHelper {

    public List<List<Integer>> combinationsInCage(int sum, int size) {
        return combinationsInCage(sum, size, List.of());
    }

    public List<List<Integer>> combinationsInCage(int sum, int size, List<Integer> excluded) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, sum, size, excluded, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int remainingSum, int remainingSize,
                           List<Integer> excluded,
                           List<Integer> current,
                           List<List<Integer>> result) {

        if (remainingSize == 0) {
            if (remainingSum == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        for (int digit = start; digit <= 9; digit++) {

            if (excluded.contains(digit)) {
                continue;
            }

            if (digit > remainingSum) {
                break;
            }

            current.add(digit);

            backtrack(digit + 1,
                      remainingSum - digit,
                      remainingSize - 1,
                      excluded,
                      current,
                      result);

            current.remove(current.size() - 1);
        }
    }
}