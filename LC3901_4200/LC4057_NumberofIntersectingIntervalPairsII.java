package LC3901_4200;
import java.util.*;
public class LC4057_NumberofIntersectingIntervalPairsII {
    /**
     * You are given a 2D integer array intervals of n elements, where intervals[i] = [starti, endi] represents the
     * closed interval from starti to endi.
     *
     * Return the number of pairs of indices (i, j) such that 0 <= i < j < n and intervals[i] and intervals[j] intersect.
     *
     * Two intervals intersect if they have at least one point in common, including when they only share an endpoint.
     *
     * Input: intervals = [[1,2],[2,3],[3,4]]
     * Output: 2
     *
     * Input: intervals = [[1,5],[2,4],[3,6]]
     * Output: 3
     *
     * Input: intervals = [[1,2],[3,4],[5,6]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n == intervals.length <= 10^5
     * intervals[i] = [starti, endi]
     * 0 <= starti <= endi <= 10^9
     * @param intervals
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        int[] st = new int[n];
        for (int i = 0; i < n; i++) st[i] = intervals[i][0];
        Arrays.sort(st);

        long tot = n * (n - 1L) / 2, cnt = 0;
        for (int i = 0; i < n; i++) {
            int idx = find(st, intervals[i][1]);
            cnt += n - idx;
        }
        return tot - cnt;
    }

    private int find(int[] a, int t) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (a[mid] > t) r = mid;
            else l = mid + 1;
        }
        return a[r] > t ? r : r + 1;
    }
}
/**
 * 先排序，不影响区间相交
 * 按照左端点还是右端点进行排序？
 * 转化成不相交更好求 => 右端点都在左端点的左边
 * 按照右端点排序，与当前区间不相交的区间
 */