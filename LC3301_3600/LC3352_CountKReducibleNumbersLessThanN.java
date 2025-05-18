package LC3301_3600;
import java.util.*;
public class LC3352_CountKReducibleNumbersLessThanN {
    /**
     * You are given a binary string s representing a number n in its binary form.
     *
     * You are also given an integer k.
     *
     * An integer x is called k-reducible if performing the following operation at most k times reduces it to 1:
     *
     * Replace x with the count of set bits in its binary representation.
     * For example, the binary representation of 6 is "110". Applying the operation once reduces it to 2 (since "110"
     * has two set bits). Applying the operation again to 2 (binary "10") reduces it to 1 (since "10" has one set bit).
     *
     * Return an integer denoting the number of positive integers less than n that are k-reducible.
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * A set bit refers to a bit in the binary representation of a number that has a value of 1.
     *
     * Input: s = "111", k = 1
     * Output: 3
     *
     * Input: s = "1000", k = 2
     * Output: 6
     *
     * Input: s = "1", k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 800
     * s has no leading zeros.
     * s consists only of the characters '0' and '1'.
     * 1 <= k <= 5
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int countKReducibleNumbers(String s, int k) {
        if (s.equals("1")) return 0;
        long mod = (long)(1e9 + 7);
        int n = s.length();
        long[] f = new long[n + 1];
        for (int i = 2; i <= n; i++) {
            int x = Integer.bitCount(i);
            f[i] = f[x] + 1;
        }

        long[][] C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % mod;
            }
        }

        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (f[i] <= k - 1) {
                int r = i;
                for (int j = 0; j < n; j++) {
                    if (s.charAt(j) == '1') {
                        if (r >= 0) {
                            res = (res + C[n - 1 - j][r]) % mod;
                        }
                        r--;
                    }
                }
            }
        }
        return (int)res;
    }

    // S2: 数位dp
    // time = O(n^2), space = O(n^2)
    long mod = (long)(1e9 + 7);
    long[][] f;
    String s;
    public int countKReducibleNumbers2(String s, int k) {
        this.s = s;
        int n = s.length();
        f = new long[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        long res = 0;
        long[] g = new long[n];
        for (int i = 1; i < n; i++) {
            g[i] = g[Integer.bitCount(i)] + 1;
            if (g[i] <= k) res = (res + dfs(0, i, true)) % mod;
        }
        return (int)res;
    }

    private long dfs(int u, int r, boolean isLimit) {
        if (u == s.length()) return !isLimit && r == 0 ? 1 : 0;
        if (!isLimit && f[u][r] != -1) return f[u][r];

        int up = isLimit ? s.charAt(u) - '0' : 1;
        long res = 0;
        for (int i = 0; i <= Math.min(up, r); i++) {
            res = (res + dfs(u + 1, r - i, isLimit && i == up)) % mod;
        }
        if (!isLimit) f[u][r] = res;
        return res;
    }
}
/**
 * f*(i) 表示 i 变成 1 的迭代次数
 * 直接算 f*(i) <= k, 时间复杂度 O(2^n)
 * 算 f*(i) < k，再算有多少个 < s 的二进制数恰好有 i 个 1, 时间复杂度 O(n^2)
 * 这些二进制数的 f* <= k
 *
 * 数位 dp: 有上下界的统计问题
 * 上界 s - 1
 * 下界 1
 */