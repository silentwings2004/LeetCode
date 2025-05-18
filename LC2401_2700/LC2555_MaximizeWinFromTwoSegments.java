package LC2401_2700;
import java.util.*;
public class LC2555_MaximizeWinFromTwoSegments {
    /**
     * There are some prizes on the X-axis. You are given an integer array prizePositions that is sorted in
     * non-decreasing order, where prizePositions[i] is the position of the ith prize. There could be different prizes
     * at the same position on the line. You are also given an integer k.
     *
     * You are allowed to select two segments with integer endpoints. The length of each segment must be k. You will
     * collect all prizes whose position falls within at least one of the two selected segments (including the endpoints
     * of the segments). The two selected segments may intersect.
     *
     * For example if k = 2, you can choose segments [1, 3] and [2, 4], and you will win any prize i that satisfies
     * 1 <= prizePositions[i] <= 3 or 2 <= prizePositions[i] <= 4.
     * Return the maximum number of prizes you can win if you choose the two segments optimally.
     *
     * Input: prizePositions = [1,1,2,2,3,3,5], k = 2
     * Output: 7
     *
     * Input: prizePositions = [1,2,3,4], k = 0
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= prizePositions.length <= 10^5
     * 1 <= prizePositions[i] <= 10^9
     * 0 <= k <= 10^9
     * prizePositions is sorted in non-decreasing order.
     * @param prizePositions
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maximizeWin(int[] prizePositions, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int x : prizePositions) map.put(x, map.getOrDefault(x, 0) + 1);
        TreeSet<Integer> set = new TreeSet<>();
        int l = map.firstKey(), res = 0, sum = 0;
        HashMap<Integer, Integer> mv = new HashMap<>();
        for (int r : map.keySet()) {
            sum += map.get(r);
            while (r - l > k) {
                set.add(mv.get(l));
                sum -= map.get(l);
                l = map.higherKey(l);
            }
            res = Math.max(res, sum + (!set.isEmpty() ? set.last() : 0));
            if (sum > mv.getOrDefault(r, 0)) mv.put(r, sum);
        }

        int n = prizePositions.length;
        while (l < prizePositions[n - 1]) {
            set.add(mv.get(l));
            sum -= map.get(l);
            res = Math.max(res, sum + set.last());
            l = map.higherKey(l);
        }
        return res;
    }

    // S2: 3 pass
    // time = O(n), space = O(n)
    public int maximizeWin2(int[] prizePositions, int k) {
        int n = prizePositions.length;
        if (prizePositions[n - 1] - prizePositions[0] <= k * 2) return n;
        int[] a = new int[n], b = new int[n];
        for (int i = 0, j = 0, t = 0; i < n; i++) {
            if (prizePositions[i] - prizePositions[j] > k) j++;
            t = Math.max(t, i - j + 1);
            a[i] = t;
        }
        for (int i = n - 1, j = n - 1, t = 0; i >= 0; i--) {
            if (prizePositions[j] - prizePositions[i] > k) j--;
            t = Math.max(t, j - i + 1);
            b[i] = t;
        }
        int res = 0;
        for (int i = 0; i + 1 < n; i++) res = Math.max(res, a[i] + b[i + 1]);
        return res;
    }
}
/**
 * 为了尽量大地增加收益，我们肯定不会让这两段区间重叠，即会让两段区间各自占满k的长度；
 * 除非总长度小于2k，那样的话，我们就取全部的点即可。
 * pre[k] + post[k] => 3 pass
 * 双指针
 */