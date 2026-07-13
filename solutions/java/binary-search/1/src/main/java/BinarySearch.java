import java.util.List;

class BinarySearch {

    private final List<Integer> items;

    BinarySearch(List<Integer> items) {
        this.items = items;
    }

    int indexOf(int item) throws ValueNotFoundException {
        int low = 0;
        int high = items.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = items.get(mid);

            if (midValue == item) {
                return mid;
            } else if (midValue < item) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        throw new ValueNotFoundException("Value not in array");
    }
}