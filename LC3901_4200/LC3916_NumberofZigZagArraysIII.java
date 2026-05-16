package LC3901_4200;

public class LC3916_NumberofZigZagArraysIII {
    /**
     * You are given three integers n, l, and r.
     *
     * A ZigZag array of length n is defined as follows:
     *
     * Each element lies in the range [l, r].
     * No two adjacent elements are equal.
     * No three consecutive elements form a strictly increasing or strictly decreasing sequence.
     * Return the total number of valid ZigZag arrays.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, l = 4, r = 5
     * Output: 2
     *
     * Input: n = 3, l = 1, r = 3
     * Output: 10
     *
     * Constraints:
     *
     * 3 <= n <= 200
     * 1 <= l < r <= 10^9
     * @param n
     * @param l
     * @param r
     * @return
     */
    // time = O(n^2), space = O(n^2)
    long mod = (long)(1e9 + 7);
    static long[][] pre = null;
    static long[] inv;
    int n;
    public int zigZagArrays(int n, int l, int r) {
        this.n = n;
        init();
        int k = r - l + 1;
        long res = 0;
        for (int i = 1; i <= n + 1; i++) {
            long y = pre[n][i];
            if (y == 0) continue;
            long t = y;
            for (int j = 1; j <= n + 1; j++) {
                if (i == j) continue;
                long x = (k - j + mod) % mod;
                t = t * x % mod;
                int d = i - j + 200;
                t = t * inv[d] % mod;
            }
            res = (res + t) % mod;
        }
        return (int)res;
    }

    private void init() {
        if (pre != null) return;
        pre= new long[210][210];
        pre[1][1] = 1;
        for (int i = 2; i < 210; i++) {
            long[] up = new long[i + 1];
            long[] down = new long[i + 1];
            for (int j = 1; j <= i; j++) {
                up[j] = j - 1;
                down[j] = i - j;
            }
            pre[1][i] = i;
            long s = 0;
            for (int j = 1; j <= i; j++) s = (s + up[j] + down[j]) % mod;
            pre[2][i] = s;

            for (int len = 3; len < 210; len++) {
                long[] nu = new long[i + 1];
                long[] nd = new long[i + 1];
                long pref = 0;
                for (int j = 1; j <= i; j++) {
                    nu[j] = pref;
                    pref = (pref + down[j]) % mod;
                }
                long suf = 0;
                for (int j = i; j >= 1; j--) {
                    nd[j] = suf;
                    suf = (suf + up[j]) % mod;
                }
                up = nu;
                down = nd;
                s = 0;
                for (int j = 1; j <= i; j++) s = (s + up[j] + down[j]) % mod;
                pre[len][i] = s;
            }

            inv = new long[410];
            for (int j = -200; j <= 200; j++) {
                if (j != 0) {
                    long v = (j + mod) % mod;
                    inv[j + 200] = qmi(v, mod - 2);
                }
            }
        }
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