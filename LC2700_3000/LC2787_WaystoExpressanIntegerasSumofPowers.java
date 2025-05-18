package LC2700_3000;

public class LC2787_WaystoExpressanIntegerasSumofPowers {
    /**
     * Given two positive integers n and x.
     *
     * Return the number of ways n can be expressed as the sum of the xth power of unique positive integers,
     * in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
     *
     * Since the result can be very large, return it modulo 10^9 + 7.
     *
     * For example, if n = 160 and x = 3, one way to express n is n = 2^3 + 3^3 + 5^3.
     *
     * Input: n = 10, x = 2
     * Output: 1
     *
     * Input: n = 4, x = 1
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 300
     * 1 <= x <= 5
     * @param n
     * @param x
     * @return
     */
    // time = O(n * k), space = O(n)
    public int numberOfWays(int n, int x) {
        int mod = (int)(1e9 + 7);
        int[] g = new int[n + 1];
        for (int i = 1; i <= n; i++) g[i] = (int)Math.pow(i, x);

        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = n; j >= g[i]; j--) {
                f[j] = (f[j] + f[j - g[i]]) % mod;
            }
        }
        return f[n];
    }
}