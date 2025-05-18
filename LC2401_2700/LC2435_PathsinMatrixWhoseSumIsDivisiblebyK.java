package LC2401_2700;

public class LC2435_PathsinMatrixWhoseSumIsDivisiblebyK {
    /**
     * You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and
     * you want to reach position (m - 1, n - 1) moving only down or right.
     *
     * Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be
     * very large, return it modulo 10^9 + 7.
     *
     * Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
     * Output: 2
     *
     * Input: grid = [[0,0]], k = 5
     * Output: 1
     *
     * Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
     * Output: 10
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 5 * 10^4
     * 1 <= m * n <= 5 * 10^4
     * 0 <= grid[i][j] <= 100
     * 1 <= k <= 50
     * @param grid
     * @param k
     * @return
     */
    // S1: DP
    // time = O(m * n * k), space = O(m * n * k)
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m][n][k];
        long mod = (long)(1e9 + 7);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int t = 0; t < k; t++) {
                    if (i == 0 && j == 0) {
                        if (t == grid[0][0] % k) f[i][j][t] = 1;
                        else f[i][j][t] = 0;
                    }
                    int s = (t + grid[i][j]) % k;
                    if (i > 0) f[i][j][s] = (f[i][j][s] + f[i - 1][j][t]) % mod;
                    if (j > 0) f[i][j][s] = (f[i][j][s] + f[i][j - 1][t]) % mod;
                }
            }
        }
        return (int) f[m - 1][n - 1][0];
    }

    // S2: DP
    // time = O(m * n * k), space = O(m * n * k)
    public int numberOfPaths2(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m + 1][n + 1][k];
        f[1][1][grid[0][0] % k] = 1;

        long mod = (long)(1e9 + 7);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                for (int r = 0; r < k; r++) {
                    f[i][j][(r + grid[i - 1][j - 1]) % k] = (f[i - 1][j][r] + f[i][j - 1][r]) % mod;
                }
            }
        }
        return (int) f[m][n][0];
    }
}
/**
 * dp[i][j][r]: the number of distinct paths whose sum % k == r
 * dp[i][j][r] = dp[i-1][j][t] + dp[i][j-1][t]
 * (t + grid[i][j]) % k = r
 */