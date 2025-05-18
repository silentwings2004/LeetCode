package LC601_900;

public class LC643_MaximumAverageSubarrayI {
    /**
     * You are given an integer array nums consisting of n elements, and an integer k.
     *
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10^-5 will be accepted.
     *
     * Input: nums = [1,12,-5,-6,50,3], k = 4
     * Output: 12.75000
     *
     * Input: nums = [5], k = 1
     * Output: 5.00000
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= k <= n <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double res = -1e5;
        for (int i = 0, j = 0, s = 0; i < n; i++) {
            s += nums[i];
            if (i - j + 1 > k) s -= nums[j++];
            if (i >= k - 1) res = Math.max(res, s * 1.0 / k);
        }
        return res;
    }
}