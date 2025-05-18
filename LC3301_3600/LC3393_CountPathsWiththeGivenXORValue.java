package LC3301_3600;

public class LC3393_CountPathsWiththeGivenXORValue {
    /**
     * You are given a 2D integer array grid with size m x n. You are also given an integer k.
     *
     * Your task is to calculate the number of paths you can take from the top-left cell (0, 0) to the bottom-right
     * cell (m - 1, n - 1) satisfying the following constraints:
     *
     * You can either move to the right or down. Formally, from the cell (i, j) you may move to the cell (i, j + 1) or
     * to the cell (i + 1, j) if the target cell exists.
     * The XOR of all the numbers on the path must be equal to k.
     * Return the total number of such paths.
     *
     * Since the answer can be very large, return the result modulo 10^9 + 7.
     *
     * Input: grid = [[2, 1, 5], [7, 10, 0], [12, 6, 4]], k = 11
     * Output: 3
     *
     * Input: grid = [[1, 3, 3, 3], [0, 3, 3, 2], [3, 0, 1, 1]], k = 2
     * Output: 5
     *
     * Input: grid = [[1, 1, 1, 2], [3, 0, 3, 2], [3, 0, 2, 2]], k = 10
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 300
     * 1 <= n == grid[r].length <= 300
     * 0 <= grid[r][c] < 16
     * 0 <= k < 16
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n * 16), space = O(m * n * 16)
    public int countPathsWithXorValue(int[][] grid, int k) {
        long mod = (long)(1e9 + 7);
        int m = grid.length, n = grid[0].length;
        long[][][] f = new long[m][n][16];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) f[i][j][grid[i][j]] = 1;
                else {
                    for (int u = 0; u < 16; u++) {
                        int v = u ^ grid[i][j];
                        if (i > 0) f[i][j][v] = (f[i][j][v] + f[i - 1][j][u]) % mod;
                        if (j > 0) f[i][j][v] = (f[i][j][v] + f[i][j - 1][u]) % mod;
                    }
                }
            }
        }
        return (int)f[m - 1][n - 1][k];
    }
}