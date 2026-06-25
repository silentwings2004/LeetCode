package LC3901_4200;
import java.util.*;
public class LC3966_CountGoodIntegersinaRange {
    /**
     * You are given three integers l, r and k.
     *
     * A number is considered good if the absolute difference between every pair of adjacent digits is at most k.
     *
     * Return the number of good integers in the range [l, r] (inclusive).
     *
     * The absolute difference between values x and y is defined as abs(x - y).
     *
     * Input: l = 10, r = 15, k = 1
     * Output: 3
     *
     * Input: l = 201, r = 204, k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 10 <= l <= r <= 10^15
     * 0 <= k <= 9
     * @param l
     * @param r
     * @param k
     * @return
     */
    // time = O(logr), space = O(logr)
    public long goodIntegers(long l, long r, int k) {
        char[] lowS = String.valueOf(l).toCharArray();
        char[] highS = String.valueOf(r).toCharArray();
        int n = highS.length;
        long[][] memo = new long[n][10];
        for (long[] row : memo) Arrays.fill(row, -1);
        return dfs(0, -1, true, true, lowS, highS, k, memo);
    }

    private long dfs(int i, int last, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, int k, long[][] memo) {
        if (i == highS.length) return 1;
        if (!limitLow && !limitHigh && last != -1 && memo[i][last] >= 0) return memo[i][last];

        int diff = highS.length - lowS.length;
        int lo = limitLow && i >= diff ? lowS[i - diff] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        long res = 0;
        int d = lo;

        if (limitLow && i < diff) {
            res = dfs(i + 1, last, true, false, lowS, highS, k, memo);
            d = 1;
        }

        for (; d <= hi; d++) {
            if (last == -1 || Math.abs(d - last) <= k) {
                res += dfs(i + 1, d, limitLow && d == lo, limitHigh && d == hi, lowS, highS, k, memo);
            }
        }
        if (!limitLow && !limitHigh && last != -1) memo[i][last] = res;
        return res;
    }
}
/**
 * is_num = not limit_low or i > diff_lh
 */