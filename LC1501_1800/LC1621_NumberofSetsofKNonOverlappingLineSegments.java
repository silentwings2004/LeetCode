package LC1501_1800;

public class LC1621_NumberofSetsofKNonOverlappingLineSegments {
    /**
     * Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i, find the number of ways we can
     * draw exactly k non-overlapping line segments such that each segment covers two or more points. The endpoints of
     * each segment must have integral coordinates. The k line segments do not have to cover all n points, and they are
     * allowed to share endpoints.
     *
     * Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it
     * modulo 10^9 + 7.
     *
     * Input: n = 4, k = 2
     * Output: 5
     *
     * Input: n = 3, k = 1
     * Output: 3
     *
     * Input: n = 30, k = 7
     * Output: 796297179
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * 1 <= k <= n-1
     * @param n
     * @param k
     * @return
     */
    // S1: dp
    // time = O(n^2), space = O(n^2)
    public int numberOfSets(int n, int k) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[n][k + 1];
        long[][] s = new long[n + 1][k + 1];


        for (int i = 0; i < n; i++) {
            f[i][0] = 1;
            s[i + 1][0] = s[i][0] + 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                f[i][j] = (f[i][j] + s[i][j - 1]) % mod;
                f[i][j] = (f[i][j] + f[i - 1][j]) % mod;
                s[i + 1][j] = (s[i][j] + f[i][j]) % mod;
            }
        }
        return (int)f[n - 1][k];
    }

    // S2: Math
    // time = O(n^2), space = O(n^2)
    public int numberOfSets2(int n, int k) {
        long[][] c = new long[2010][2010];
        int mod = (int)1e9 + 7;

        for (int i = 0; i < 2010; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        return (int)c[n + k - 1][k * 2];
    }
}
/**
 * dp[i][k]: for the first i points, the number of ways to construct k line segments
 * x x x x x x x x i
 *                __ dp[i-1][k-1]
 *               ___ dp[i-2][k-1]
 *               ...
 *               ....dp[0][k-1]
 * dp[i][k] = sum{d[j][k-1]} j = 0,1,2,...i-1
 * dp[i][k] = dp[i-1][k]
 * => dp[i][k] = dp[i-1][k] + sum{d[j][k-1]} j = 0,1,2,...i-1
 */