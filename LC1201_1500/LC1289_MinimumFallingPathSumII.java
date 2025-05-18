package LC1201_1500;
import java.util.*;
public class LC1289_MinimumFallingPathSumII {
    /**
     * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
     *
     * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two
     * elements chosen in adjacent rows are in the same column.
     *
     * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: 13
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 1 <= n <= 200
     * -99 <= grid[i][j] <= 99
     * @param grid
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length, INF = (int)1e8;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[0][i] = grid[0][i];
        for (int i = 1; i < n; i++) {
            int d1 = INF, d2 = INF;
            for (int j = 0; j < n; j++) {
                int x = f[i - 1][j];
                if (x <= d1) {
                    d2 = d1;
                    d1 = x;
                } else if (x < d2) d2 = x;
            }
            for (int j = 0; j < n; j++) {
                if (f[i - 1][j] == d1) f[i][j] = d2 + grid[i][j];
                else f[i][j] = d1 + grid[i][j];
            }
        }

        int res = INF;
        for (int i = 0; i < n; i++) res = Math.min(res, f[n - 1][i]);
        return res;
    }
}
/**
 * same as LC265
 */