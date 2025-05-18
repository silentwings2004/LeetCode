package LC1501_1800;
import java.util.*;
public class LC1751_MaximumNumberofEventsThatCanBeAttendedII {
    /**
     * You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at
     * startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also
     * given an integer k which represents the maximum number of events you can attend.
     *
     * You can only attend one event at a time. If you choose to attend an event, you must attend the entire event.
     * Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other
     * ends on the same day.
     *
     * Return the maximum sum of values that you can receive by attending events.
     *
     * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= k <= events.length
     * 1 <= k * events.length <= 10^6
     * 1 <= startDayi <= endDayi <= 10^9
     * 1 <= valuei <= 10^6
     * @param events
     * @param k
     * @return
     */
    // time = O(n * k * logn), space = O(n * k)
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j] = f[i - 1][j];
                int idx = lowerBound(events, events[i - 1][0]);
                f[i][j] = Math.max(f[i][j], f[idx + 1][j - 1] + events[i - 1][2]);
            }
        }
        return f[n][k];
    }

    private int lowerBound(int[][] q, int t) {
        int l = 0, r = q.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q[mid][1] < t) l = mid;
            else r = mid - 1;
        }
        return q[r][1] < t ? r : r - 1;
    }
}
/**
 * ref: LC1235
 * 贪心，动态规划
 * LC435: the maximum number of non-overlapping intervals -> greedy 按照ending point排序
 * LC1235: the maximum weight of non-overlapping intervals -> 没有greedy，但还是需要排序
 * maximum number of non-overlapping intervals -> sort by ending point
 * minimum number of intervals that cover the whole range -> sort by starting point
 * dp[i]: the maximum weight you can gain within the first i intervals
 * 1. if not pick the events[i] =>  dp[i] = dp[i - 1]
 * 2. if pick the events[i] => dp[i] = dp[j] + events[i][2]
 * j的确定方法，ending point要比它早，ending point递增排序过的 => 二分搜索 lowerBound(events[i][0])
 * 这道题多了一个维度，
 * dp[i][t]: the maximum weight you can gain by picking t intervals from the first i intervals
 * 1. if not pick the events[i] => dp[i-1][t]
 * 2. if pick the events[i] => dp[j][t-1] + events[i][2]
 * 在LC1235基础上稍微改一下就行
 */
