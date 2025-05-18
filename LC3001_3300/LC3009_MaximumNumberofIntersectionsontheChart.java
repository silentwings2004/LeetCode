package LC3001_3300;
import java.util.*;
public class LC3009_MaximumNumberofIntersectionsontheChart {
    /**
     * There is a line chart consisting of n points connected by line segments. You are given a 1-indexed integer array
     * y. The kth point has coordinates (k, y[k]). There are no horizontal lines; that is, no two consecutive points
     * have the same y-coordinate.
     *
     * We can draw an infinitely long horizontal line. Return the maximum number of points of intersection of the line
     * with the chart.
     *
     * Input: y = [1,2,1,2,1,3,2]
     * Output: 5
     *
     * Input: y = [2,1,3,4,5]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= y.length <= 10^5
     * 1 <= y[i] <= 10^9
     * y[i] != y[i + 1] for i in range [1, n - 1]
     * @param y
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxIntersectionCount(int[] y) {
        int n = y.length;
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            long a = y[i - 1] * 2, b = 2 * y[i] + (i == n - 1 ? 0 : (y[i] > y[i - 1] ? -1 : 1));
            long minv = Math.min(a, b), maxv = Math.max(a, b);
            map.put(minv, map.getOrDefault(minv, 0) + 1);
            map.put(maxv + 1, map.getOrDefault(maxv + 1, 0) - 1);
        }
        int cnt = 0, res = 0;
        for (int v : map.values()) {
            cnt += v;
            res = Math.max(res, cnt);
        }
        return res;
    }
}