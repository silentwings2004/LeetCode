package LC3601_3900;
import java.util.*;
public class LC3654_MinimumSumAfterDivisibleSumDeletions {
    /**
     * You are given an integer array nums and an integer k.
     *
     * You may repeatedly choose any contiguous subarray of nums whose sum is divisible by k and delete it; after each
     * deletion, the remaining elements close the gap.
     *
     * Return the minimum possible sum of nums after performing any number of such deletions.
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 1
     *
     * Input: nums = [3,1,4,1,5], k = 3
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long minArraySum(int[] nums, int k) {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);
        long res = 0, s = 0;
        for (int x : nums) {
            s += x;
            int r = (int)(s % k);
            res += x;
            if (map.containsKey(r)) res = Math.min(res, map.get(r));
            map.put(r, res);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public long minArraySum2(int[] nums, int k) {
        final long inf = (long)1E18;
        long[] f = new long[k];
        Arrays.fill(f, inf);
        f[0] = 0;
        long g = 0;
        int s = 0;
        for (int x : nums) {
            s = (s + x) % k;
            g = Math.min(g + x, f[s]);
            f[s] = g;
        }
        return g;
    }
}