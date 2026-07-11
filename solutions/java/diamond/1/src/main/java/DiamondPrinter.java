import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char letter) {
        List<String> diamond = new ArrayList<>();

        int n = letter - 'A';
        int size = 2 * n + 1;

        // Top half (including middle)
        for (int i = 0; i <= n; i++) {
            diamond.add(buildRow(i, n, size));
        }

        // Bottom half
        for (int i = n - 1; i >= 0; i--) {
            diamond.add(buildRow(i, n, size));
        }

        return diamond;
    }

    private String buildRow(int index, int max, int size) {
        char[] row = new char[size];

        // Fill with spaces
        for (int i = 0; i < size; i++) {
            row[i] = ' ';
        }

        char ch = (char) ('A' + index);

        int left = max - index;
        int right = max + index;

        row[left] = ch;

        if (left != right) {
            row[right] = ch;
        }

        return new String(row);
    }
}