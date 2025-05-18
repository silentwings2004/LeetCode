package LC2700_3000;
import java.util.*;
public class LC2865_BeautifulTowersI {
    /**
     * You are given a 0-indexed array maxHeights of n integers.
     *
     * You are tasked with building n towers in the coordinate line. The ith tower is built at coordinate i and has a
     * height of heights[i].
     *
     * A configuration of towers is beautiful if the following conditions hold:
     *
     * 1 <= heights[i] <= maxHeights[i]
     * heights is a mountain array.
     * Array heights is a mountain if there exists an index i such that:
     *
     * For all 0 < j <= i, heights[j - 1] <= heights[j]
     * For all i <= k < n - 1, heights[k + 1] <= heights[k]
     * Return the maximum possible sum of heights of a beautiful configuration of towers.
     *
     * Input: maxHeights = [5,3,4,1,1]
     * Output: 13
     *
     * Input: maxHeights = [6,5,3,9,2,7]
     * Output: 22
     *
     * Input: maxHeights = [3,2,5,5,2,3]
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= n == maxHeights <= 10^3
     * 1 <= maxHeights[i] <= 10^9
     * @param maxHeights
     * @return
     */
    // time = O(n^2), space = O(1)
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        for (int i = 0; i < n; i++) res = Math.max(res, cal(maxHeights, i));
        return res;
    }

    private long cal(List<Integer> h, int pos) {
        long t = h.get(pos);
        int n = h.size();
        for (int i = pos - 1, last = h.get(pos); i >= 0; i--) {
            t += Math.min(h.get(i), last);
            last = Math.min(h.get(i), last);
        }
        for (int i = pos + 1, last = h.get(pos); i < n; i++) {
            t += Math.min(h.get(i), last);
            last = Math.min(h.get(i), last);
        }
        return t;
    }
}