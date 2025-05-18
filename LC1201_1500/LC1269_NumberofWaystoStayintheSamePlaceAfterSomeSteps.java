package LC1201_1500;

public class LC1269_NumberofWaystoStayintheSamePlaceAfterSomeSteps {
    /**
     * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1
     * position to the right in the array, or stay in the same place (The pointer should not be placed outside the array
     * at any time).
     *
     * Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after
     * exactly steps steps. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: steps = 3, arrLen = 2
     * Output: 4
     *
     * Input: steps = 2, arrLen = 4
     * Output: 2
     *
     * Input: steps = 4, arrLen = 2
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= steps <= 500
     * 1 <= arrLen <= 10^6
     * @param steps
     * @param arrLen
     * @return
     */
    // time = O(n * min(m, n)), space = O(n * min(m, n))
    public int numWays(int steps, int arrLen) {
        int n = steps, m = Math.min(steps, arrLen - 1), mod = (int)1e9 + 7;
        int[][] f = new int[n + 1][m + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j - 1 >= 0) f[i][j] = (f[i][j] + f[i - 1][j - 1]) % mod;
                if (j + 1 <= m) f[i][j] = (f[i][j] + f[i - 1][j + 1]) % mod;
            }
        }
        return f[n][0];
    }
}