package LC3601_3900;

public class LC3855_SumofKDigitNumbersinaRange {
    /**
     * You are given three integers l, r, and k.
     *
     * Consider all possible integers consisting of exactly k digits, where each digit is chosen independently from the
     * integer range [l, r] (inclusive). If 0 is included in the range, leading zeros are allowed.
     *
     * Return an integer representing the sum of all such numbers. Since the answer may be very large, return it modulo
     * 10^9 + 7.
     *
     * Input: l = 1, r = 2, k = 2
     * Output: 66
     *
     * Input: l = 0, r = 1, k = 3
     * Output: 444
     *
     * Input: l = 5, r = 5, k = 10
     * Output: 555555520
     *
     * Constraints:
     *
     * 0 <= l <= r <= 9
     * 1 <= k <= 10^9
     * @param l
     * @param r
     * @param k
     * @return
     */
    // time = O(logk), space = O(1)
    long mod = (long)(1e9 + 7);
    public int sumOfNumbers(int l, int r, int k) {
        long m = r - l + 1;
        long s = (l + r) * m * qmi(2, mod - 2) % mod;
        long cnt = qmi(m, k - 1);
        long v = s * cnt % mod;
        long ps = (qmi(10, k) - 1 + mod) % mod * qmi(9, mod - 2) % mod; // (10^k - 1) / 9
        return (int)((v * ps) % mod);
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