package LC3301_3600;
import java.util.*;
public class LC3323_MinimizeConnectedGroupsbyInsertingInterval {
    /**
     * You are given a 2D array intervals, where intervals[i] = [starti, endi] represents the start and the end of
     * interval i. You are also given an integer k.
     *
     * You must add exactly one new interval [startnew, endnew] to the array such that:
     *
     * The length of the new interval, endnew - startnew, is at most k.
     * After adding, the number of connected groups in intervals is minimized.
     * A connected group of intervals is a maximal collection of intervals that, when considered together, cover a
     * continuous range from the smallest point to the largest point with no gaps between them. Here are some examples:
     *
     * A group of intervals [[1, 2], [2, 5], [3, 3]] is connected because together they cover the range from 1 to 5
     * without any gaps.
     * However, a group of intervals [[1, 2], [3, 4]] is not connected because the segment (2, 3) is not covered.
     * Return the minimum number of connected groups after adding exactly one new interval to the array.
     *
     * Input: intervals = [[1,3],[5,6],[8,10]], k = 3
     * Output: 2
     *
     * Input: intervals = [[5,10],[1,1],[3,3]], k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 10^5
     * intervals[i] == [starti, endi]
     * 1 <= starti <= endi <= 10^9
     * 1 <= k <= 10^9
     * @param intervals
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minConnectedGroups(int[][] intervals, int k) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> q = new ArrayList<>();
        int st = -1, ed = -1;
        for (int[] x : intervals) {
            if (x[0] > ed) {
                if (st != -1) q.add(new int[]{st, ed});
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) q.add(new int[]{st, ed});

        int n = q.size(), res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (q.get(i)[0] - q.get(j)[1] > k) j++;
            res = Math.max(res, i - j);
        }
        return n - res;
    }
}