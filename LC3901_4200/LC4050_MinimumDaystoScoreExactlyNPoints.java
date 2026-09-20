package LC3901_4200;
import java.util.*;
public class LC4050_MinimumDaystoScoreExactlyNPoints {
    /**
     * You are given an integer n representing a target score.
     *
     * Your score starts at 0, and each day you either earn points or skip.
     *
     * Points are earned during a streak. On the first day of a streak you earn 1 point, on the second day 2 points, on
     * the third day 3 points, and so on. Skipping a day earns nothing and resets the streak, so the next time you earn
     * points, you start from 1 again.
     *
     * Return the minimum number of days, including any skipped days, needed to reach a score of exactly n.
     *
     * Input: n = 2
     * Output: 3
     *
     * Input: n = 9
     * Output: 6
     *
     * Input: n = 12
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n * sqrt(n)), space = O(n)
    public int minDays(int n) {
        List<Integer> p = new ArrayList<>();
        List<Integer> q = new ArrayList<>();
        for (int i = 1;; i++) {
            int t = (1 + i) * i / 2;
            if (t > n) break;
            p.add(i);
            q.add(t);
        }

        final int inf = 0x3f3f3f3f;
        int m = q.size();
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int k = p.get(j), t = q.get(j);
                if (t > i) break;
                f[i] = Math.min(f[i], f[i - t] + k + 1);
            }
        }
        return f[n] - 1;
    }
}