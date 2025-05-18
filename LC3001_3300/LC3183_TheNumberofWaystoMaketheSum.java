package LC3001_3300;

public class LC3183_TheNumberofWaystoMaketheSum {
    /**
     * You have an infinite number of coins with values 1, 2, and 6, and only 2 coins with value 4.
     *
     * Given an integer n, return the number of ways to make the sum of n with the coins you have.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Note that the order of the coins doesn't matter and [2, 2, 3] is the same as [2, 3, 2].
     *
     * Input: n = 4
     * Output: 4
     *
     * Input: n = 12
     * Output: 22
     *
     * Input: n = 5
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int numberOfWays(int n) {
        long mod = (long)(1e9 + 7);
        int[] coins = new int[]{1, 2, 6};
        long[] f = new long[n + 1];
        f[0] = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = coins[i]; j <= n; j++) {
                f[j] = (f[j] + f[j - coins[i]]) % mod;
            }
        }
        long res = f[n];
        if (n - 4 >= 0) res = (res + f[n - 4]) % mod;
        if (n - 8 >= 0) res = (res + f[n - 8]) % mod;
        return (int)res;
    }
}