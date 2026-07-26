package LC3901_4200;

public class LC4002_CountValidSequences {
    /**
     * You are given two positive integers n and k.
     *
     * A valid sequence is a sequence of k positive integers such that:
     *
     * The sum of all integers in the sequence is equal to n.
     * The product of all integers in the sequence is even.
     * Return the number of valid sequences. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Two sequences are considered different if they differ at any index. For example, [1, 1, 2] and [1, 2, 1] are
     * considered different sequences.
     *
     * Input: n = 5, k = 3
     * Output: 3
     *
     * Input: n = 3, k = 2
     * Output: 2
     *
     * Input: n = 5, k = 5
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^5
     * 1 <= k <= n
     * @param n
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    final long mod = (long)(1e9 + 7);
    long[] fact, infact;
    public int countValidSequences(int n, int k) {
        fact = new long[n + 1];
        infact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % mod;
        infact[n - 1] = qmi(fact[n - 1], mod - 2);
        for (int i = n - 1; i > 0; i--) infact[i - 1] = infact[i] * i % mod;

        long tot = comb(n - 1, k - 1), odd = 0;
        if ((n - k) % 2 == 0) {
            int m = (n - k) / 2;
            odd = comb(m + k - 1, k - 1);
        }
        return (int)((tot - odd + mod) % mod);
    }

    private long comb(int a, int b) {
        return fact[a] * infact[b] % mod * infact[a - b] % mod;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}