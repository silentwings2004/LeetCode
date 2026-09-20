package LC3901_4200;
import java.util.*;
public class LC4056_NumberofIntersectingIntervalPairsI {
    /**
     * You are given a 2D integer array intervals of n elements, where intervals[i] = [starti, endi] represents the
     * closed interval from starti to endi.
     *
     * Return the number of pairs of indices (i, j) such that 0 <= i < j < n and intervals[i] and intervals[j] intersect.
     *
     * Two intervals intersect if they have at least one point in common, including when they only share an endpoint.
     *
     * Input: intervals = [[1,2],[2,3],[3,4]]
     *
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
     * 2 <= n == intervals.length <= 100
     * intervals[i] = [starti, endi]
     * 0 <= starti <= endi <= 100
     * @param intervals
     * @return
     */
    // time = O(n^2), space = O(1)
    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length, res = 0;
        for (int i = 0; i < n; i++) {
            int l1 = intervals[i][0], r1 = intervals[i][1];
            for (int j = i + 1; j < n; j++) {
                int l2 = intervals[j][0], r2 = intervals[j][1];
                if (l1 > r2 || r1 < l2 || l2 > r1 || r2 < l1) continue;
                res++;
            }
        }
        return res;
    }
}