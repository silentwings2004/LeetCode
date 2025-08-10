package LC3601_3900;
import java.util.*;
public class LC3635_EarliestFinishTimeforLandandWaterRidesII {
    /**
     * You are given two categories of theme park attractions: land rides and water rides.
     *
     * Land rides
     * landStartTime[i] – the earliest time the ith land ride can be boarded.
     * landDuration[i] – how long the ith land ride lasts.
     * Water rides
     * waterStartTime[j] – the earliest time the jth water ride can be boarded.
     * waterDuration[j] – how long the jth water ride lasts.
     * A tourist must experience exactly one ride from each category, in either order.
     *
     * A ride may be started at its opening time or any later moment.
     * If a ride is started at time t, it finishes at time t + duration.
     * Immediately after finishing one ride the tourist may board the other (if it is already open) or wait until it opens.
     * Return the earliest possible time at which the tourist can finish both rides.
     *
     * Input: landStartTime = [2,8], landDuration = [4,1], waterStartTime = [6], waterDuration = [3]
     * Output: 9
     *
     * Input: landStartTime = [5], landDuration = [3], waterStartTime = [1], waterDuration = [10]
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= n, m <= 5 * 10^4
     * landStartTime.length == landDuration.length == n
     * waterStartTime.length == waterDuration.length == m
     * 1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 10^5
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    // S1
    // time = O(n + m), space = O(1)
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int a = work(landStartTime, landDuration, waterStartTime, waterDuration);
        int b = work(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(a, b);
    }

    private int work(int[] a, int[] b, int[] c, int[] d) {
        int mf = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) mf = Math.min(mf, a[i] + b[i]);
        for (int i = 0; i < c.length; i++) res = Math.min(res, Math.max(c[i], mf) + d[i]);
        return res;
    }

    // S2
    // time = O(nlogn + mlogm), space = O(n + m)
    public int earliestFinishTime2(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int a = helper(landStartTime, landDuration, waterStartTime, waterDuration);
        int b = helper(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(a, b);
    }

    private int helper(int[] a1, int[] a2, int[] b1, int[] b2) {
        int n = a1.length, m = b1.length, res = Integer.MAX_VALUE;
        Integer[] p1 = new Integer[n], p2 = new Integer[m];
        TreeSet<Integer> set = new TreeSet<>((o1, o2) -> {
            int t1 = b1[o1] + b2[o1], t2 = b1[o2] + b2[o2];
            if (t1 != t2) return t1 - t2;
            return o1 - o2;
        });
        for (int i = 0; i < n; i++) p1[i] = i;
        for (int i = 0; i < m; i++) {
            p2[i] = i;
            set.add(i);
        }

        Arrays.sort(p1, (o1, o2) -> (a1[o1] + a2[o1]) - (a1[o2] + a2[o2]));
        Arrays.sort(p2, (o1, o2) -> b1[o1] - b1[o2]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0, j = 0; i < n; i++) {
            int t = a1[p1[i]] + a2[p1[i]];
            while (j < m && b1[p2[j]] <= t) {
                pq.offer(b2[p2[j]]);
                set.remove(p2[j]);
                j++;
            }
            if (!pq.isEmpty()) res = Math.min(res, t + pq.peek());
            if (!set.isEmpty()) res = Math.min(res, b1[set.first()] + b2[set.first()]);
        }
        return res;
    }
}