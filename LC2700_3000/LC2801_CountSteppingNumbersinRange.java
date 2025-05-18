package LC2700_3000;
import java.util.*;
public class LC2801_CountSteppingNumbersinRange {
    /**
     * Given two positive integers low and high represented as strings, find the count of stepping numbers in the
     * inclusive range [low, high].
     *
     * A stepping number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
     *
     * Return an integer denoting the count of stepping numbers in the inclusive range [low, high].
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Note: A stepping number should not have a leading zero.
     *
     * Input: low = "1", high = "11"
     * Output: 10
     *
     * Input: low = "90", high = "101"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= int(low) <= int(high) < 10^100
     * 1 <= low.length, high.length <= 100
     * low and high consist of only digits.
     * low and high don't have any leading zeros.
     * @param low
     * @param high
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 110, M = 10, mod = (int)1e9 + 7;
    int[][] f;
    char[] s;
    public int countSteppingNumbers(String low, String high) {
        f = new int[N][M];
        int res = (work(high) - work(low) + mod) % mod;
        boolean isValid = true;
        for (int i = 1; i < low.length(); i++) {
            int a = low.charAt(i) - '0', b = low.charAt(i - 1) - '0';
            if (Math.abs(a - b) != 1) isValid = false;
        }
        if (isValid) res = (res + 1) % mod;
        return res;
    }

    private int work(String str) {
        s = str.toCharArray();
        int n = s.length;
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return dfs(0, 0, true, false);
    }

    private int dfs(int u, int last, boolean isLimit, boolean isNum) {
        if (u == s.length) return isNum ? 1 : 0;
        if (!isLimit && isNum && f[u][last] != -1) return f[u][last];

        int res = 0;
        if (!isNum) res = dfs(u + 1, last, false, false);
        int r = isLimit ? s[u] - '0' : 9;
        for (int i = isNum ? 0 : 1; i <= r; i++) {
            if (!isNum || Math.abs(i - last) == 1) {
                res = (res + dfs(u + 1, i, isLimit && i == r, true)) % mod;
            }
        }
        if (!isLimit && isNum) f[u][last] = res;
        return res;
    }
}