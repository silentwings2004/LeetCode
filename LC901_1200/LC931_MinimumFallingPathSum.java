package LC901_1200;
import java.util.*;
public class  LC931_MinimumFallingPathSum {
    /**
     * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
     *
     * A falling path starts at any element in the first row and chooses the element in the next row that is either
     * directly below or diagonally left/right. Specifically, the next element from position (row, col) will be
     * (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
     *
     * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
     * Output: 13
     *
     * Input: matrix = [[-19,57],[-40,-5]]
     * Output: -59
     *
     * Constraints:
     *
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 100
     * -100 <= matrix[i][j] <= 100
     * @param matrix
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) f[0][i] = matrix[0][i];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = Math.max(0, j - 1); k <= Math.min(n - 1, j + 1); k++) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][k] + matrix[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) res = Math.min(res, f[n - 1][i]);
        return res;
    }
}