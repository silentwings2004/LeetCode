package LC1201_1500;
import java.util.*;
public class LC1235_MaximumProfitinJobScheduling {
    /**
     * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of
     * profit[i].
     *
     * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are
     * no two jobs in the subset with overlapping time range.
     *
     * If you choose a job that ends at time X you will be able to start another job that starts at time X.
     *
     * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
     * Output: 120
     *
     * Constraints:
     *
     * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
     * 1 <= startTime[i] < endTime[i] <= 10^9
     * 1 <= profit[i] <= 10^4
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{endTime[i], i};
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) f[i] = f[i - 1];
            int pos = find(w, i - 1, startTime[w[i][1]]);
            if (pos == -1) f[i] = Math.max(f[i], profit[w[i][1]]);
            else f[i] = Math.max(f[i - 1], f[pos] + profit[w[i][1]]);
        }
        return f[n - 1];
    }

    private int find(int[][] w, int r, int t) {
        int l = 0;
        if (r < l) return -1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (w[mid][0] <= t) l = mid;
            else r = mid - 1;
        }
        return w[r][0] <= t ? r : r - 1;
    }
}
/**
 * ref: 435
 * 贪心策略：
 * sort by ending points: maximum number of non-overlapping intervals
 * LC435选第一个，因为它的ending point 比较早，有更多的机会不会干扰后面区间，它会减少取更多区间的可能 (greedy)
 * 而这里是取Profit最大的 -> 挑profit最大的，但是可能duration比较大
 * => 对重合区间没有一个贪心策略来决定取谁
 * 用O(n^2)的dp解来粗暴解决
 * dp[i] = max{dp[j] + val[i]} for j = ...
 * dp[i] = max{dp[j] + val[i], dp[i - 1]}  (截止到i这个时刻，你能得到的最大任务，这里j是指最后一个比i start point早的ending point，
 * 但这并不意味着i一定会取,不取的话直接取dp[i - 1]）
 * dp一定是单调递增的，这里i, j都是表示时刻
 * dp[t]: by the time of t, the maximum profit
 * t 是离散来看的，都是每个ending point
 * 如何找到这个t呢？=> 恰好比start point小的t => 二分 (因为是递增的)
 *
 * 按照时间来dp
 * f[i]: 枚举最后一个区间选谁
 * f[i] = f[i - 1]
 * 所有以i为右端点的区间：profit + f[L]
 * 离散化？
 * 用到的i一定是区间的结束，先排序
 * 从前i个区间里选，最多可以选多少价值
 * 不选：f[i] = f[i-1]
 * 选：f[i] = f[j] = w[i]
 * 取max => 二分找j
 */
