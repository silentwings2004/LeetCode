package LC3301_3600;

public class LC3339_FindtheNumberofKEvenArrays {
    /**
     * You are given three integers n, m, and k.
     *
     * An array arr is called k-even if there are exactly k indices such that, for each of these indices i
     * (0 <= i < n - 1):
     *
     * (arr[i] * arr[i + 1]) - arr[i] - arr[i + 1] is even.
     * Return the number of possible k-even arrays of size n where all elements are in the range [1, m].
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, m = 4, k = 2
     * Output: 8
     *
     * Input: n = 5, m = 1, k = 0
     * Output: 1
     *
     * Input: n = 7, m = 7, k = 5
     * Output: 5832
     *
     * Constraints:
     *
     * 1 <= n <= 750
     * 0 <= k <= n - 1
     * 1 <= m <= 1000
     * @param n
     * @param m
     * @param k
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int countOfArrays(int n, int m, int k) {
        long mod = (long)(1e9 + 7);
        long[][][] f = new long[n][k + 1][2];
        int odd = (m + 1) / 2, even = m / 2;
        f[0][0][0] = even;
        f[0][0][1] = odd;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                f[i][j][0] = f[i - 1][j][1] * even % mod;
                f[i][j][1] = (f[i - 1][j][0] + f[i - 1][j][1]) * odd % mod;
                if (j > 0) f[i][j][0] = (f[i][j][0] + f[i - 1][j - 1][0] * even % mod) % mod;
            }
        }
        return (int)((f[n - 1][k][0] + f[n - 1][k][1]) % mod);
    }
}
