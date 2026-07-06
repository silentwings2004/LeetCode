package LC3901_4200;
import java.util.*;
public class LC3976_MaximumSubarraySumAfterMultiplier {
    /**
     * You are given an integer array nums and a positive integer k.
     *
     * You must choose exactly one subarray of nums and perform exactly one of the following operations:
     *
     * Multiply each number in the chosen subarray by k.
     * Divide each number in the chosen subarray by k.
     * When dividing a positive number by k, use the floor value of the division result.
     * When dividing a negative number by k, use the ceiling value of the division result.
     * Return the maximum possible sum of a non-empty subarray in the resulting array.
     *
     * Note that the subarray chosen for the operation and the subarray chosen for the sum may be different.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,-2,3,4,-5], k = 2
     * Output: 14
     *
     * Input: nums = [-5,-4,-3], k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -105 <= nums[i] <= 10^5
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public long maxSubarraySum(int[] nums, int k) {
        final long inf = (long)1E18;
        int n = nums.length;
        long[][] f = new long[2][4];
        for (int i = 0; i < 2; i++) Arrays.fill(f[i], -inf);

        long res = -inf;
        for (int i = 1; i <= n; i++) {
            f[i & 1] = f[i - 1 & 1].clone();
            int x = nums[i - 1];
            long mul = 1L * x * k, div = 1L * x / k;
            f[i & 1][0] = Math.max(f[i - 1 & 1][0], 0) + x;
            f[i & 1][1] = Math.max(Math.max(f[i - 1 & 1][0], f[i - 1 & 1][1]), 0) + mul;
            f[i & 1][2] = Math.max(Math.max(f[i - 1 & 1][0], f[i - 1 & 1][2]), 0) + div;
            f[i & 1][3] = Math.max(Math.max(f[i - 1 & 1][1], f[i - 1 & 1][2]), 0) + x;
            for (int j = 0; j < 4; j++) res = Math.max(res, f[i & 1][j]);
        }
        return res;
    }
}
/**
 * ref: LC1186
 */