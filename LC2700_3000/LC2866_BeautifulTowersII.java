package LC2700_3000;
import java.util.*;
public class LC2866_BeautifulTowersII {
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
     * 1 <= n == maxHeights <= 10^5
     * 1 <= maxHeights[i] <= 10^9
     * @param maxHeights
     * @return
     */
    // time = O(n), space = O(n)
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size(), tt = 0;
        long[][] stk = new long[n + 1][2];
        long[] l = new long[n], r = new long[n];

        long s = 0;
        for (int i = 0; i < n; i++) {
            int x = maxHeights.get(i);
            long cnt = 0;
            while (tt > 0 && stk[tt][0] > x) {
                s -= stk[tt][0] * stk[tt][1];
                cnt += stk[tt--][1];
            }
            if (cnt > 0) {
                stk[++tt] = new long[]{x, cnt};
                s += x * cnt;
            }
            l[i] = s;
            stk[++tt] = new long[]{x, 1};
            s += x;
        }

        stk = new long[n + 1][2];
        tt = 0;
        s = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = maxHeights.get(i);
            long cnt = 0;
            while (tt > 0 && stk[tt][0] > x) {
                s -= stk[tt][0] * stk[tt][1];
                cnt += stk[tt--][1];
            }
            if (cnt > 0) {
                stk[++tt] = new long[]{x, cnt};
                s += x * cnt;
            }
            r[i] = s;
            stk[++tt] = new long[]{x, 1};
            s += x;
        }

        long res = 0;
        for (int i = 0; i < n; i++) res = Math.max(res, l[i] + r[i] + maxHeights.get(i));
        return res;
    }
}