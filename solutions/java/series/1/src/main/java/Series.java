import java.util.ArrayList;
import java.util.List;

class Series {

    private final String digits;

    Series(String digits) {
        if (digits.isEmpty()) {
            throw new IllegalArgumentException("series cannot be empty");
        }
        this.digits = digits;
    }

    List<String> slices(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("slice length cannot be negative or zero");
        }
        if (n > digits.length()) {
            throw new IllegalArgumentException("slice length cannot be greater than series length");
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i <= digits.length() - n; i++) {
            result.add(digits.substring(i, i + n));
        }
        return result;
    }
}