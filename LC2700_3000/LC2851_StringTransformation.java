package LC2700_3000;

public class LC2851_StringTransformation {
    /**
     * You are given two strings s and t of equal length n. You can perform the following operation on the string s:
     *
     * Remove a suffix of s of length l where 0 < l < n and append it at the start of s.
     * For example, let s = 'abcd' then in one operation you can remove the suffix 'cd' and append it in front of s
     * making s = 'cdab'.
     * You are also given an integer k. Return the number of ways in which s can be transformed into t in exactly k
     * operations.
     *
     * Since the answer can be large, return it modulo 10^9 + 7.
     *
     * Input: s = "abcd", t = "cdab", k = 2
     * Output: 2
     *
     * Input: s = "ababab", t = "ababab", k = 1
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= s.length <= 5 * 10^5
     * 1 <= k <= 10^15
     * s.length == t.length
     * s and t consist of only lowercase English alphabets.
     * @param s
     * @param t
     * @param k
     * @return
     */
    // time = O(n + logk), space = O(n)
    final int mod = (int)1e9 + 7;
    long[][] g;
    public int numberOfWays(String s, String t, long k) {
        int n = s.length();
        int v = kmp(s + s, t);
        g = new long[][]{{v - 1, v}, {n - v, n - 1 - v}};
        long[][] f = qmi(k);
        return s.equals(t) ? (int)f[0][0] : (int)f[0][1];
    }

    private int kmp(String s, String p) {
        int n = p.length(), m = s.length();
        s = "#" + s;
        p = "#" + p;
        int[] ne = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (p.charAt(i) == p.charAt(j + 1)) j++;
            ne[i] = j;
        }

        int cnt = 0;
        for (int i = 1, j = 0; i <= m; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (s.charAt(i) == p.charAt(j + 1)) j++;
            if (j == n) {
                if (i - n + 1 <= m / 2) cnt++;
                j = ne[j];
            }
        }
        return cnt;
    }

    private void mul(long[][] c, long[][] a, long[][] b) {
        long[][] t = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    t[i][j] = (t[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = t[i][j];
            }
        }
    }

    private long[][] qmi(long k) {
        long[][] f = new long[2][2];
        f[0][0] = 1;
        while (k > 0) {
            if ((k & 1) == 1) mul(f, f, g);
            mul(g, g, g);
            k >>= 1;
        }
        return f;
    }
}
/**
 * (n-1)^k
 * 本质相当于扑克切牌
 * abcd => dabc
 * ab | cd => cdab
 * a | bcd => bcda
 * 1: +1,+2,+3,...,+n-1
 * 2: +1,+2,+3,...,+n-1
 * 3: +1,+2,+3,...,+n-1
 * ...
 * k: +1,+2,+3,...,+n-1
 * s' = s(1),s(2),s(3),...s(n-1)
 *            t
 * a b [c d a b] c d
 * p: how many strings of shift of s that equals to t
 * f[j]: the # of bad strings after j rounds
 * g[j]: the # of good strings after j rounds
 *
 * f[j-1], g[j-1]
 * f[j] = f[j-1] * (n - p - 1) + g[j - 1] * (n - p)
 * g[j] = f[j-1] * p + g[j-1] * (p-1)
 *
 * f[0], g[0] = (0, 1) if (s == t)
 *            = (1, 0) if (s != t)
 * f[k], g[k]
 * 矩阵相乘，有结合律，没有交换律
 * => T^k
 */
