package LC3001_3300;
import java.util.*;
public class LC3179_FindtheNthValueAfterKSeconds {
    /**
     * You are given two integers n and k.
     *
     * Initially, you start with an array a of n integers where a[i] = 1 for all 0 <= i <= n - 1. After each second,
     * you simultaneously update each element to be the sum of all its preceding elements plus the element itself. For
     * example, after one second, a[0] remains the same, a[1] becomes a[0] + a[1], a[2] becomes a[0] + a[1] + a[2], and
     * so on.
     *
     * Return the value of a[n - 1] after k seconds.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: n = 4, k = 5
     * Output: 56
     *
     * Input: n = 5, k = 3
     * Output: 35
     *
     * Constraints:
     *
     * 1 <= n, k <= 1000
     * @param n
     * @param k
     * @return
     */
    // S1
    // time = O(n * k), space = O(n)
    public int valueAfterKSeconds(int n, int k) {
        long mod = (long)(1e9 + 7);
        long[] s = new long[n];
        Arrays.fill(s, 1);
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                s[j] = (s[j] + s[j - 1]) % mod;
            }
        }
        return (int)s[n - 1];
    }

    // S2: Math
    // time = O(N), space = O(N)
    public int valueAfterKSeconds2(int n, int k) {
        final int N = 2010;
        long mod = (long)(1e9 + 7);
        long[] f = new long[N], g = new long[N];
        f[0] = g[0] = 1;
        for (int i = 1; i < N; i++) {
            f[i] = f[i - 1] * i % mod;
            g[i] = g[i - 1] * qmi(i, mod - 2, mod) % mod;
        }
        return (int)(f[k + n - 1] * g[k] % mod * g[n - 1] % mod);
    }

    private long qmi(long a, long k, long mod) {
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
 * 从左下往右上看，每条斜线上是数字是
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4
 * 1 5 10 10
 * 杨辉三角 组合数
 * 位于第几排第几个数呢?
 * 我们相当于计算的是杨辉三角第 n + k 排的第 n 个数
 * => C(n + k - 1, n - k) = C(n + k - 1, k)
 */