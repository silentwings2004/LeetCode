package LC3601_3900;

public class LC3689_MaximumTotalSubarrayValueI {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * You need to choose exactly k non-empty subarrays nums[l..r] of nums. Subarrays may overlap, and the exact same
     * subarray (same l and r) can be chosen more than once.
     *
     * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
     *
     * The total value is the sum of the values of all chosen subarrays.
     *
     * Return the maximum possible total value you can achieve.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,3,2], k = 2
     *
     * Output: 4
     *
     * Input: nums = [4,2,5,1], k = 3
     *
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 5 * 10^4
     * 0 <= nums[i] <= 10^9
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public long maxTotalValue(int[] nums, int k) {
        int mx = nums[0], mn = nums[0];
        for (int x : nums) {
            mx = Math.max(mx, x);
            mn = Math.min(mn, x);
        }
        return 1L * (mx - mn) * k;
    }
}