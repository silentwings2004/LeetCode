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
/**
 * 贡献法
 * 每一位是互相独立的
 * 先算出每一位的贡献，再把这些贡献累加起来
 * _   _  x _ _
 * k-1    i
 * x * 10^i
 * 出去第 i 位，一共有 k-1 位，每一位都有 r-l+1 种填法
 * => (x * 10^i) * (r - l + 1)^(k - 1)
 * x: l, l+1, ..., r
 * (l+r) * (r-l+1) / 2 * 10^i * (r-l+1)^(k-1)   i=0,1,2,...,
 * => (l+r) * (r-l+1) / 2 * (r-l+1)^(k-1) * sum{10^i} (i=0,1,2,...) => 等比数列求和
 * => (l+r) * (r-l+1) / 2 * (r-l+1)^(k-1) * (10^k - 1) / 9
 */