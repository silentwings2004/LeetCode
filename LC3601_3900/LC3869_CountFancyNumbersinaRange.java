package LC3601_3900;
import java.util.*;
public class LC3869_CountFancyNumbersinaRange {
    /**
     * You are given two integers l and r.
     *
     * An integer is called good if its digits form a strictly monotone sequence, meaning the digits are strictly
     * increasing or strictly decreasing. All single-digit integers are considered good.
     *
     * An integer is called fancy if it is good, or if the sum of its digits is good.
     *
     * Return an integer representing the number of fancy integers in the range [l, r] (inclusive).
     *
     * A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
     *
     * A sequence is said to be strictly decreasing if each element is strictly less than its previous one (if exists).
     *
     * Input: l = 8, r = 10
     * Output: 3
     *
     * Input: l = 12340, r = 12341
     * Output: 1
     *
     * Input: l = 123456788, r = 123456788
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= l <= r <= 10^15
     * @param l
     * @param r
     * @return
     */
    // time = O(logk), space = O(1)
    public long countFancy(long l, long r) {
        return count(r) - count(l - 1);
    }

    private long count(long n) {
        if (n <= 0) return 0;
        HashSet<Long> set = new HashSet<>();
        generateGood(0, -1, 1, set);
        generateGood(0, -1, -1, set);

        long cnt1 = 0;
        for (long x : set) {
            if (x >= 1 && x <= n) cnt1++;
        }
        HashSet<Integer> gs = new HashSet<>();
        for (long x : set) {
            if (x >= 1 && x <= 135) gs.add((int)x);
        }
        long cnt2 = dp(String.valueOf(n), gs);
        long overlap = 0;
        for (long x : set) {
            if (x >= 1 && x <= n && gs.contains((int)get(x))) overlap++;
        }
        return cnt1 + cnt2 - overlap;
    }

    private void generateGood(long cur, int last, int dir, HashSet<Long> set) {
        if (cur > (long)1E15) return;
        if (cur > 0) set.add(cur);
        for (int d = 0; d <= 9; d++) {
            if (cur == 0 && d == 0) continue;
            if (cur == 0) generateGood(d, d, dir, set);
            else {
                if (dir == 1 && d > last) generateGood(cur * 10 + d, d, dir, set);
                if (dir == -1 && d < last) generateGood(cur * 10 + d, d, dir, set);
            }
        }
    }

    private long dp(String s, HashSet<Integer> gs) {
        int n = s.length();
        long[][][] f = new long[n][136][2];
        for (long[][] row : f) {
            for (long[] x : row) Arrays.fill(x, -1);
        }
        return dfs(0, 0, true, s, gs, f);
    }

    private long dfs(int u, int sum, boolean isLimit, String s, HashSet<Integer> gs, long[][][] f) {
        if (u == s.length()) return gs.contains(sum) ? 1 : 0;
        int t = isLimit ? 1 : 0;
        if (f[u][sum][t] != -1) return f[u][sum][t];

        long res = 0;
        int up = isLimit ? s.charAt(u) - '0' : 9;
        for (int d = 0; d <= up; d++) {
            res += dfs(u + 1, sum + d, isLimit && d == up, s, gs, f);
        }
        return f[u][sum][t] = res;
    }

    private int get(long x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}