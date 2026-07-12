import java.util.ArrayList;
import java.util.List;

class Flattener {

    List<Object> flatten(List<?> list) {
        List<Object> result = new ArrayList<>();
        flattenHelper(list, result);
        return result;
    }

    private void flattenHelper(List<?> list, List<Object> result) {
        for (Object item : list) {
            if (item == null) {
                continue;
            }

            if (item instanceof List<?>) {
                flattenHelper((List<?>) item, result);
            } else {
                result.add(item);
            }
        }
    }
}