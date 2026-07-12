import java.util.*;

class Matrix {
    private final int[][] matrix;

    Matrix(List<List<Integer>> values) {
        if (values.isEmpty()) {
            matrix = new int[0][0];
            return;
        }
        matrix = new int[values.size()][values.get(0).size()];
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.get(i).size(); j++) {
                matrix[i][j] = values.get(i).get(j);
            }
        }
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> result = new HashSet<>();

        // Guard against empty matrix OR rows with zero columns
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Precompute row maxima
        int[] rowMax = new int[rows];
        for (int i = 0; i < rows; i++) {
            int max = matrix[i][0];
            for (int j = 1; j < cols; j++) {
                max = Math.max(max, matrix[i][j]);
            }
            rowMax[i] = max;
        }

        // Precompute column minima
        int[] colMin = new int[cols];
        for (int j = 0; j < cols; j++) {
            int min = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                min = Math.min(min, matrix[i][j]);
            }
            colMin[j] = min;
        }

        // A saddle point is max in its row AND min in its column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == rowMax[i] && matrix[i][j] == colMin[j]) {
                    result.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }

        return result;
    }
}