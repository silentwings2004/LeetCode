package LC3001_3300;
import java.util.*;
public class LC3122_MinimumNumberofOperationstoSatisfyConditions {
    /**
     * You are given a 2D matrix grid of size m x n. In one operation, you can change the value of any cell to any
     * non-negative number. You need to perform some operations such that each cell grid[i][j] is:
     *
     * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists).
     * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if it exists).
     * Return the minimum number of operations needed.
     *
     * Input: grid = [[1,0,2],[1,0,2]]
     * Output: 0
     *
     * Input: grid = [[1,1,1],[0,0,0]]
     * Output: 3
     *
     * Input: grid = [[1],[2],[3]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n, m <= 1000
     * 0 <= grid[i][j] <= 9
     * @param grid
     * @return
     */
    // S1: dp
    // time = O(m * n * 10), space = O(n * 10)
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] c = new int[n][10];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < 10; k++) {
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] != k) c[j][k]++;
                }
            }
        }

        int inf = 0x3f3f3f3f;
        int[][] f = new int[n][10];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], inf);
        for (int k = 0; k < 10; k++) f[0][k] = c[0][k];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (j != k) f[i][j] = Math.min(f[i][j], f[i - 1][k] + c[i][j]);
                }
            }
        }
        int res = inf;
        for (int k = 0; k < 10; k++) res = Math.min(res, f[n - 1][k]);
        return res;
    }

    // S2
    // time = O(m * n + n * U^2), space = O(n * U)
    public int minimumOperations2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] cnt = new int[n][10];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[j][grid[i][j]]++;
            }
        }
        int[][] f = new int[n][11];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return m * n - dfs(n - 1, 10, cnt, f);
    }

    private int dfs(int i, int j, int[][] cnt, int[][] f) {
        if (i < 0) return 0;
        if (f[i][j] != -1) return f[i][j];

        int res = 0;
        for (int k = 0; k < 10; k++) {
            if (k != j) res = Math.max(res, dfs(i - 1, k, cnt, f) + cnt[i][k]);
        }
        f[i][j] = res;
        return res;
    }
}