public class Transpose {

    public String transpose(String toTranspose) {
        if (toTranspose == null || toTranspose.isEmpty()) {
            return "";
        }

        // Split with a negative limit to preserve empty trailing rows if they exist
        String[] rows = toTranspose.split("\n", -1);

        int maxLength = 0;
        for (String row : rows) {
            maxLength = Math.max(maxLength, row.length());
        }

        StringBuilder result = new StringBuilder();

        for (int col = 0; col < maxLength; col++) {
            StringBuilder line = new StringBuilder();

            // Find the last row index that actually has a character at this column index
            int lastRowWithChar = -1;
            for (int r = rows.length - 1; r >= 0; r--) {
                if (col < rows[r].length()) {
                    lastRowWithChar = r;
                    break;
                }
            }

            // Only build the column string up to the last row that needs it
            for (int r = 0; r <= lastRowWithChar; r++) {
                String row = rows[r];
                if (col < row.length()) {
                    line.append(row.charAt(col));
                } else {
                    line.append(' ');
                }
            }

            result.append(line);

            if (col < maxLength - 1) {
                result.append('\n');
            }
        }

        return result.toString();
    }
}