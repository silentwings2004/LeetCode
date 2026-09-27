package LC3901_4200;
import java.util.*;
public class LC4068_MaximizeMeetingEarningswithIdleGaps {
    /**
     * You are given a 2D integer array meetings, where meetings[i] = [starti, endi, revenuei] represents a meeting
     * starting at time starti, ending at time endi, with revenue revenuei.
     *
     * All meetings use half-open intervals [start, end), so meetings that only touch at endpoints do not overlap.
     *
     * You may select any non-empty subset of meetings such that no two selected meetings overlap. You earn the revenue
     * of each selected meeting.
     *
     * Arrange the selected meetings in increasing order of their start times. For each pair of adjacent meetings in
     * this order, you also earn 1 unit of revenue per unit of idle time between them. This idle time equals the later
     * meeting's start time minus the earlier meeting's end time.
     *
     * No idle revenue is earned before the earliest selected meeting starts or after the latest selected meeting ends.
     * If only one meeting is selected, no idle revenue is earned.
     *
     * Return the maximum total earnings achievable.
     *
     * A subset of an array is a selection of elements of the array.
     *
     * Input: meetings = [[2,5,4],[6,8,3]]
     * Output: 8
     *
     * Input: meetings = [[3,5,4],[4,7,8],[8,10,3]]
     * Output: 12
     *
     * Input: meetings = [[1,2,2],[4,5,2],[7,9,3]]
     * Output: 11
     *
     * Constraints
     * 1 <= meetings.length <= 10^5
     * meetings[i] = [starti, endi, revenuei]
     * 0 <= starti < endi <= 10^9
     * 1 <= revenuei <= 10^9
     * @param meetings
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxEarnings(int[][] meetings) {
        Arrays.sort(meetings, (o1, o2) -> o1[1] - o2[1]);
        int ed0 = meetings[0][1];

        int n = meetings.length;
        long[] preMax = new long[n + 1];
        preMax[0] = Long.MIN_VALUE;
        long res = 0;

        for (int i = 0; i < n; i++) {
            int[] m = meetings[i];
            int st = m[0], ed = m[1], revenue = m[2];
            long f = revenue;
            if (st >= ed0) {
                int j = find(meetings, i, st);
                f += preMax[j + 1] + st;
            }
            res = Math.max(res, f);
            preMax[i + 1] = Math.max(preMax[i], f - ed);
        }
        return res;
    }

    private int find(int[][] meetings, int r, int t) {
        int l = 0;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (meetings[mid][1] <= t) l = mid;
            else r = mid - 1;
        }
        return meetings[r][1] <= t ? r : r - 1;
    }
}
/**
 * ref: LC1235
 * f[i] 表示以 meetings[i] 为最后一个所选区间时，能得到的最大收益
 * 枚举倒数第二个区间为 meetings[j], 问题变成以 meetings[j] 为最后一个所选区间时，能得到的最大收益 f[j]
 * f[i] = max_j{f[j] + start[i] - end[j] + revenue[i]} 满足 end[j] <= start[i]
 *     = start[i] + revenue[i] = max_j{f[j] - end[j]}
 * meetings 按照右端点排序，max_j{f[j] - end[j]} 是 f[j] - end[j] 的前缀最大值  => 二分
 * 初始值：f[i] = revenue[i] 如果 start[i] 左边没有区间
 */