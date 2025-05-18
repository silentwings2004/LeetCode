package LC1501_1800;
import java.util.*;
public class LC1594_MaximumNonNegativeProductinaMatrix {
    /**
     * You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step,
     * you can only move right or down in the matrix.
     *
     * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner
     * (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of
     * all integers in the grid cells visited along the path.
     *
     * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
     *
     * Notice that the modulo is performed after getting the maximum product.
     *
     * Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
     * Output: -1
     *
     * Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
     * Output: 8
     *
     * Input: grid = [[1,3],[0,-4]]
     * Output: 0
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 15
     * -4 <= grid[i][j] <= 4
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    final long INF = (long)1e18, mod = (long)1e9 + 7;
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] f = new long[m][n];
        long[][] g = new long[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(f[i], -INF);
            Arrays.fill(g[i], INF);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    f[i][j] = g[i][j] = grid[i][j];
                } else {
                    if (i > 0) {
                        f[i][j] = Math.max(f[i][j], Math.max(f[i - 1][j] * grid[i][j], g[i - 1][j] * grid[i][j]));
                        g[i][j] = Math.min(g[i][j], Math.min(f[i - 1][j] * grid[i][j], g[i - 1][j] * grid[i][j]));
                    }
                    if (j > 0) {
                        f[i][j] = Math.max(f[i][j], Math.max(f[i][j - 1] * grid[i][j], g[i][j - 1] * grid[i][j]));
                        g[i][j] = Math.min(g[i][j], Math.min(f[i][j - 1] * grid[i][j], g[i][j - 1] * grid[i][j]));
                    }
                }
            }
        }
        return f[m - 1][n - 1] < 0 ? -1 : (int)(f[m - 1][n - 1] % mod);
    }
}