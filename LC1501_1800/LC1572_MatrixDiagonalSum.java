package LC1501_1800;

public class LC1572_MatrixDiagonalSum {
    /**
     * Given a square matrix mat, return the sum of the matrix diagonals.
     *
     * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal
     * that are not part of the primary diagonal.
     *
     * Input: mat = [[1,2,3],
     *               [4,5,6],
     *               [7,8,9]]
     * Output: 25
     *
     * Input: mat = [[1,1,1,1],
     *               [1,1,1,1],
     *               [1,1,1,1],
     *               [1,1,1,1]]
     * Output: 8
     *
     * Input: mat = [[5]]
     * Output: 5
     *
     * Constraints:
     *
     * n == mat.length == mat[i].length
     * 1 <= n <= 100
     * 1 <= mat[i][j] <= 100
     * @param mat
     * @return
     */
    // time = O(m * n), space = O(1)
    public int diagonalSum(int[][] mat) {
        int n = mat.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) res += mat[i][j];
                else if (i == n - 1 - j) res += mat[i][j];
            }
        }
        return res;
    }
}