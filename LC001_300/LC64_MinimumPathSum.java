package LC001_300;
import java.util.*;
public class LC64_MinimumPathSum {
    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes
     * the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(f[i], Integer.MAX_VALUE / 2);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) f[i][j] = grid[i][j];
                else {
                    if (i > 0) f[i][j] = Math.min(f[i][j], f[i - 1][j] + grid[i][j]);
                    if (j > 0) f[i][j] = Math.min(f[i][j], f[i][j - 1] + grid[i][j]);
                }
            }
        }
        return f[m - 1][n - 1];
    }
}