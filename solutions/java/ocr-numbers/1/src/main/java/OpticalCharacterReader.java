import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OpticalCharacterReader {

    private static final Map<String, String> DIGIT_MAP = new HashMap<>();

    static {
        DIGIT_MAP.put(" _ " +
                      "| |" +
                      "|_|" +
                      "   ", "0");

        DIGIT_MAP.put("   " +
                      "  |" +
                      "  |" +
                      "   ", "1");

        DIGIT_MAP.put(" _ " +
                      " _|" +
                      "|_ " +
                      "   ", "2");

        DIGIT_MAP.put(" _ " +
                      " _|" +
                      " _|" +
                      "   ", "3");

        DIGIT_MAP.put("   " +
                      "|_|" +
                      "  |" +
                      "   ", "4");

        DIGIT_MAP.put(" _ " +
                      "|_ " +
                      " _|" +
                      "   ", "5");

        DIGIT_MAP.put(" _ " +
                      "|_ " +
                      "|_|" +
                      "   ", "6");

        DIGIT_MAP.put(" _ " +
                      "  |" +
                      "  |" +
                      "   ", "7");

        DIGIT_MAP.put(" _ " +
                      "|_|" +
                      "|_|" +
                      "   ", "8");

        DIGIT_MAP.put(" _ " +
                      "|_|" +
                      " _|" +
                      "   ", "9");
    }

    String parse(List<String> input) {
        validateInput(input);

        List<String> lineResults = new ArrayList<>();
        int totalRows = input.size();

        for (int r = 0; r < totalRows; r += 4) {
            StringBuilder lineResult = new StringBuilder();
            int totalCols = input.get(r).length();

            for (int c = 0; c < totalCols; c += 3) {
                StringBuilder digitGrid = new StringBuilder();
                for (int rowOffset = 0; rowOffset < 4; rowOffset++) {
                    digitGrid.append(input.get(r + rowOffset).substring(c, c + 3));
                }
                lineResult.append(DIGIT_MAP.getOrDefault(digitGrid.toString(), "?"));
            }
            lineResults.add(lineResult.toString());
        }

        return String.join(",", lineResults);
    }

    private void validateInput(List<String> input) {
        if (input == null || input.size() % 4 != 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }

        int columns = input.get(0).length();
        if (columns % 3 != 0) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }

        for (String line : input) {
            if (line.length() != columns) {
                throw new IllegalArgumentException("Number of input columns must be a multiple of three");
            }
        }
    }
}