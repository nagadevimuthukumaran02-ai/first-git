class SpiralMatrixBuilder {

    int[][] buildMatrixOfSize(int size) {
        if (size == 0) {
            return new int[0][0];
        }

        int[][] matrix = new int[size][size];

        int top = 0;
        int bottom = size - 1;
        int left = 0;
        int right = size - 1;
        int value = 1;

        while (top <= bottom && left <= right) {

            // Left to Right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = value++;
            }
            top++;

            // Top to Bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = value++;
            }
            right--;

            // Right to Left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = value++;
                }
                bottom--;
            }

            // Bottom to Top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = value++;
                }
                left++;
            }
        }

        return matrix;
    }
}