package LC3301_3600;
import java.util.*;
public class LC3381_MaximumSubarraySumWithLengthDivisiblebyK {
    /**
     * You are given an array of integers nums and an integer k.
     *
     * Return the maximum sum of a non-empty subarray of nums, such that the size of the subarray is divisible by k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2], k = 1
     * Output: 3
     *
     * Input: nums = [-1,-2,-3,-4,-5], k = 4
     * Output: -10
     *
     * Input: nums = [-5,1,2,-3,4], k = 2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 2 * 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    final long inf = (long)1e18;
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long s = 0, res = -inf;
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);
        for (int i = 0; i < n; i++) {
            s += nums[i];
            int t = (i + 1) % k;
            if (map.containsKey(t)) res = Math.max(res, s - map.get(t));
            if (!map.containsKey(t) || map.get(t) > s) map.put(t, s);
        }
        return res;
    }
}