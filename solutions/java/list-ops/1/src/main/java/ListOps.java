import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOps {

    private ListOps() {
        // Private constructor to prevent instantiation of utility class
    }

    static <T> List<T> append(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<>();
        for (T item : list1) {
            result.add(item);
        }
        for (T item : list2) {
            result.add(item);
        }
        return result;
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : listOfLists) {
            for (T item : list) {
                result.add(item);
            }
        }
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    static <T> int size(List<T> list) {
        int count = 0;
        for (T item : list) {
            count++;
        }
        return count;
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        List<U> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        U accumulator = initial;
        for (T item : list) {
            accumulator = f.apply(accumulator, item);
        }
        return accumulator;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        U accumulator = initial;
        for (int i = size(list) - 1; i >= 0; i--) {
            accumulator = f.apply(list.get(i), accumulator);
        }
        return accumulator;
    }

    static <T> List<T> reverse(List<T> list) {
        List<T> result = new ArrayList<>();
        for (int i = size(list) - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }
}