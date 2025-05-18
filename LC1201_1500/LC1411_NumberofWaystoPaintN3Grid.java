package LC1201_1500;

public class LC1411_NumberofWaystoPaintN3Grid {
    /**
     * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors:
     * Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that
     * share vertical or horizontal sides have the same color).
     *
     * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow
     * large, the answer must be computed modulo 109 + 7.
     *
     * Input: n = 1
     * Output: 12
     *
     * Input: n = 5000
     * Output: 30228214
     *
     * Constraints:
     *
     * n == grid.length
     * 1 <= n <= 5000
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int numOfWays(int n) {
        int mod = (int)1e9 + 7;
        long[][] f = new long[n][2];
        f[0][0] = f[0][1] = 6;
        for (int i = 1; i < n; i++) {
            f[i][0] = (f[i - 1][0] * 3 + f[i - 1][1] * 2) % mod;
            f[i][1] = (f[i - 1][0] * 2 + f[i - 1][1] * 2) % mod;
        }
        return (int)((f[n - 1][0] + f[n - 1][1]) % mod);
    }
}