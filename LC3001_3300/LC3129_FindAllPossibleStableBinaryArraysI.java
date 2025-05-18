package LC3001_3300;

public class LC3129_FindAllPossibleStableBinaryArraysI {
    /**
     * You are given 3 positive integers zero, one, and limit.
     *
     * A binary array arr is called stable if:
     *
     * The number of occurrences of 0 in arr is exactly zero.
     * The number of occurrences of 1 in arr is exactly one.
     * Each subarray of arr with a size greater than limit must contain both 0 and 1.
     * Return the total number of stable binary arrays.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: zero = 1, one = 1, limit = 2
     * Output: 2
     *
     * Input: zero = 1, one = 2, limit = 1
     * Output: 1
     *
     * Input: zero = 3, one = 3, limit = 2
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= zero, one, limit <= 200
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    // time = O(m * n * limit), space = O(m * n)
    public int numberOfStableArrays(int zero, int one, int limit) {
        int n = zero, m = one;
        long mod = (long)(1e9 + 7);
        long[][] f = new long[n + 1][m + 1];
        long[][] g = new long[n + 1][m + 1];
        f[0][0] = g[0][0] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 1; k <= limit; k++) {
                    if (i >= k) f[i][j] = (f[i][j] + g[i - k][j]) % mod;
                    if (j >= k) g[i][j] = (g[i][j] + f[i][j - k]) % mod;
                }
            }
        }
        return (int)((f[n][m] + g[n][m]) % mod);
    }
}
/**
 * x x x x ?
 * dp0[i][j]: the total number of stable binary arrays with i 0s and j 1s, and ending with 0
 * dp1[i][j]: the total number of stable binary arrays with i 0s and j 1s, and ending with 1
 * 不能出现超过limit个0，之前的1最早出现在哪里？
 * x x x 1 x x x x 0
 * dp0[i][j] = dp1[i-limit][j] + dp1[i-limit+1][j] + ...+ dp[i-1][j]
 * dp1[i][j] = dp0[i][j-limit] + dp0[i][j-limit+1] + ...+ dp[i][j-1]
 */