package LC2700_3000;
import java.util.*;
public class LC2999_CounttheNumberofPowerfulIntegers {
    /**
     * You are given three integers start, finish, and limit. You are also given a 0-indexed string s representing a
     * positive integer.
     *
     * A positive integer x is called powerful if it ends with s (in other words, s is a suffix of x) and each digit in
     * x is at most limit.
     *
     * Return the total number of powerful integers in the range [start..finish].
     *
     * A string x is a suffix of a string y if and only if x is a substring of y that starts from some index
     * (including 0) in y and extends to the index y.length - 1. For example, 25 is a suffix of 5125 whereas 512 is not.
     *
     * Input: start = 1, finish = 6000, limit = 4, s = "124"
     * Output: 5
     *
     * Input: start = 15, finish = 215, limit = 6, s = "10"
     * Output: 2
     *
     * Input: start = 1000, finish = 2000, limit = 4, s = "3000"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= start <= finish <= 10^15
     * 1 <= limit <= 9
     * 1 <= s.length <= floor(log10(finish)) + 1
     * s only consists of numeric digits which are at most limit.
     * s does not have leading zeros.
     * @param start
     * @param finish
     * @param limit
     * @param s
     * @return
     */
    // time = O(logk * 10), space = O(logk * 10)
    final int N = 55;
    long[][] f;
    int limit;
    String s;
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.limit = limit;
        this.s = s;
        f = new long[N][10];
        return helper(finish) - helper(start - 1);
    }

    private long helper(long x) {
        char[] cs = String.valueOf(x).toCharArray();
        for (int i = 0; i < cs.length; i++) Arrays.fill(f[i], -1);
        return dfs(cs, 0, -1, 0, x, true);
    }

    private long dfs(char[] cs, int u, int last, long sum, long up, boolean isLimit) {
        if (u + s.length() > cs.length) return 0;
        if (u + s.length() == cs.length) {
            String t = String.valueOf(sum) + s;
            long v = Long.parseLong(t);
            if (v <= up) return 1;
            return 0;
        }
        if (!isLimit && last != -1 && f[u][last] != -1) return f[u][last];

        int r = isLimit ? cs[u] - '0' : '9';
        long res = 0;
        for (int i = 0; i <= Math.min(r, limit); i++) {
            res += dfs(cs, u + 1, i, sum * 10 + i, up, isLimit && i == r);
        }
        if (!isLimit && last != -1) f[u][last] = res;
        return res;
    }
}
/**
 * 数位 dp 通用模版 v2.0
 */