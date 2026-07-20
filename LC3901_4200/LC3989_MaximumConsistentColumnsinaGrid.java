package LC3901_4200;
import java.util.*;
public class LC3989_MaximumConsistentColumnsinaGrid {
    /**
     * You are given a 2D integer array grid of size m x n, and an integer limit.
     *
     * You may remove zero or more columns from the grid, but at least one column must remain. The relative order of the
     * remaining columns must be preserved.
     *
     * A grid is called consistent if for every row i, and for every pair of adjacent remaining columns a and b with
     * a < b, the following holds: |grid[i][b] - grid[i][a]| <= limit.
     *
     * Return the maximum number of columns that can remain such that the resulting grid is consistent.
     *
     * Input: grid = [[-2,0,3]], limit = 2
     * Output: 2
     *
     * Input: grid = [[1,-1,1],[2,2,2]], limit = 1
     * Output: 2
     *
     * Input: grid = [[-5,5]], limit = 9
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 250
     * 1 <= n == grid[i].length <= 250
     * -10^5 <= grid[i][j] <= 10^5
     * 0 <= limit <= 10^5
     * @param grid
     * @param limit
     * @return
     */
    // time = O(m * n^2), space = O(n)
    public int maxConsistentColumns(int[][] grid, int limit) {
        int m = grid.length, n = grid[0].length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int res = 1;

        for (int i = 0; i < n; i++) {
            int mx = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (f[j] <= mx) continue;
                boolean ok = true;
                for (int u = 0; u < m; u++) {
                    if (Math.abs(grid[u][i] - grid[u][j]) > limit) {
                        ok = false;
                        break;
                    }
                }
                if (ok) mx = f[j];
            }
            f[i] = mx + 1;
            res = Math.max(res, f[i]);
        }
        return res;
    }
}
/**
 * f[i]: [0,i] 列中能保留的最大列数
 * 枚举选哪个：
 * 枚举上一个保留的列号 j (0 <= j >= i-1), 问题变成在 [0,j] 列中能保留的最大列数，即 f[j]
 * 如果列 i 和列 j 是一致的，那么用 f[j] + 1 更新 f[i] 的最大值
 * 初始值：f[i] = 1 可以只保留一列
 *
 * 子序列 DP 模型
 * 1.相邻无关型 (例如 0-1 背包) 用选或不选思考
 * 2.相邻相关型 (例如 LIS 问题) 用枚举选哪个思考
 * 最优性剪枝
 */