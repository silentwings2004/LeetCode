package LC3601_3900;
import java.util.*;
public class LC3791_NumberofBalancedIntegersinaRange {
    /**
     * You are given two integers low and high.
     *
     * An integer is called balanced if it satisfies both of the following conditions:
     *
     * It contains at least two digits.
     * The sum of digits at even positions is equal to the sum of digits at odd positions (the leftmost digit has
     * position 1).
     * Return an integer representing the number of balanced integers in the range [low, high] (both inclusive).
     *
     * Input: low = 1, high = 100
     * Output: 9
     *
     * Input: low = 120, high = 129
     * Output: 1
     *
     * Input: low = 1234, high = 1234
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= low <= high <= 10^15
     * @param low
     * @param high
     * @return
     */
    // time = O(n^2 * D^2), space = O(n^2 * D)
    public long countBalanced(long low, long high) {
        if (high < 11) return 0;
        low = Math.max(low, 10);

        char[] lowS = String.valueOf(low).toCharArray();
        char[] highS = String.valueOf(high).toCharArray();

        int n = highS.length;
        long[][] memo = new long[n][n * 9 + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(0, n / 2 * 9, true, true, lowS, highS, memo);
    }

    private long dfs(int i, int diff, boolean limitLow, boolean limitHigh, char[] lowS, char[] highS, long[][] memo) {
        if (i == highS.length) {
            return diff == highS.length / 2 * 9 ? 1 : 0;
        }

        if (!limitLow && !limitHigh && memo[i][diff] >= 0) {
            return memo[i][diff];
        }

        int diff_lh = highS.length - lowS.length;
        int lo = limitLow && i >= diff_lh ? lowS[i - diff_lh] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;

        long res = 0;
        int d = lo;

        if (limitLow && i < diff_lh) {
            res = dfs(i + 1, diff, true, false, lowS, highS, memo);
            d = 1;
        }

        for (; d <= hi; d++) {
            res += dfs(i + 1,
                    diff + (i % 2 == 0 ? d : -d),
                    limitLow && d == lo,
                    limitHigh && d == hi,
                    lowS, highS, memo);
        }

        if (!limitLow && !limitHigh) {
            memo[i][diff] = res;
        }
        return res;
    }
}