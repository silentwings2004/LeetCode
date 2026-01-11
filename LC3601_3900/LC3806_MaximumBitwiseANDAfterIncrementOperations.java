package LC3601_3900;
import java.util.*;
public class LC3806_MaximumBitwiseANDAfterIncrementOperations {
    /**
     * You are given an integer array nums and two integers k and m.
     *
     * You may perform at most k operations. In one operation, you may choose any index i and increase nums[i] by 1.
     *
     * Return an integer denoting the maximum possible bitwise AND of any subset of size m after performing up to k
     * operations optimally.
     *
     * Input: nums = [3,1,2], k = 8, m = 2
     * Output: 6
     *
     * Input: nums = [1,2,8,4], k = 7, m = 3
     * Output: 4
     *
     * Input: nums = [1,1], k = 3, m = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * 1 <= m <= n
     * @param nums
     * @param k
     * @param m
     * @return
     */
    // time = O(nlogn + m), space = O(n)
    public int maximumAND(int[] nums, int k, int m) {
        int n = nums.length;
        long[] cost = new long[n];
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            int t = res | (1 << i);
            for (int j = 0; j < n; j++) {
                cost[j] = cal(nums[j], t);
            }
            Arrays.sort(cost);
            long tot = 0;
            for (int j = 0; j < m; j++) {
                tot += cost[j];
                if (tot > k) break;
            }
            if (tot <= k) res = t;
        }
        return res;
    }

    private long cal(int x, int t) {
        long y = x;
        for (int i = 30; i >= 0; i--) {
            if ((t >> i & 1) == 1 && (y >> i & 1) == 0) {
                y |= 1L << i;
                y &= ~((1L << i) - 1);
            }
        }
        return y - x;
    }
}