package LC3001_3300;

public class LC3033_ModifytheMatrix {
    /**
     * Given a 0-indexed m x n integer matrix matrix, create a new 0-indexed matrix called answer. Make answer equal to
     * matrix, then replace each element with the value -1 with the maximum element in its respective column.
     *
     * Return the matrix answer.
     *
     * Input: matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
     * Output: [[1,2,9],[4,8,6],[7,8,9]]
     *
     * Input: matrix = [[3,-1],[5,2]]
     * Output: [[3,2],[5,2]]
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 2 <= m, n <= 50
     * -1 <= matrix[i][j] <= 100
     * The input is generated such that each column contains at least one non-negative integer.
     * @param matrix
     * @return
     */
    // time = O(m * n), space = O(n)
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] c = new int[n];
        for (int j = 0; j < n; j++) {
            int cm = matrix[0][j];
            for (int i = 0; i < m; i++) {
                cm = Math.max(cm, matrix[i][j]);
            }
            c[j] = cm;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) matrix[i][j] = c[j];
            }
        }
        return matrix;
    }
}