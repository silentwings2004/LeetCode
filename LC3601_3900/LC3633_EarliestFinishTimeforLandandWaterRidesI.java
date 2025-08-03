package LC3601_3900;

public class LC3633_EarliestFinishTimeforLandandWaterRidesI {
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
     * 1 <= n, m <= 100
     * landStartTime.length == landDuration.length == n
     * waterStartTime.length == waterDuration.length == m
     * 1 <= landStartTime[i], landDuration[i], waterStartTime[j], waterDuration[j] <= 1000
     * @param landStartTime
     * @param landDuration
     * @param waterStartTime
     * @param waterDuration
     * @return
     */
    // time = O(n * m), space = O(1)
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int a = helper(landStartTime, landDuration, waterStartTime, waterDuration);
        int b = helper(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(a, b);
    }

    private int helper(int[] a1, int[] a2, int[] b1, int[] b2) {
        int n = a1.length, m = b1.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int t = a1[i] + a2[i];
            for (int j = 0; j < m; j++) {
                int x = b1[j], d = b2[j];
                res = Math.min(res, Math.max(x, t) + d);
            }
        }
        return res;
    }
}