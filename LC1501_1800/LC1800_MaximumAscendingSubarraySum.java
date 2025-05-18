package LC1501_1800;

public class LC1800_MaximumAscendingSubarraySum {
    /**
     * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
     *
     * A subarray is defined as a contiguous sequence of numbers in an array.
     *
     * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi < numsi+1.
     * Note that a subarray of size 1 is ascending.
     *
     * Input: nums = [10,20,30,5,10,50]
     * Output: 65
     *
     * Input: nums = [10,20,30,40,50]
     * Output: 150
     *
     *
     Constraints:

     1 <= nums.length <= 100
     1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxAscendingSum(int[] nums) {
        int n = nums.length, sum = nums[0], res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) sum += nums[i];
            else {
                res = Math.max(res, sum);
                sum = nums[i];
            }
        }
        res = Math.max(res, sum);
        return res;
    }
}
