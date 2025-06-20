package LC1801_2100;

public class LC2016_MaximumDifferenceBetweenIncreasingElements {
    /**
     * Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j]
     * (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
     *
     * Return the maximum difference. If no such i and j exists, return -1.
     *
     * Input: nums = [7,1,5,4]
     * Output: 4
     *
     * Constraints:
     *
     * n == nums.length
     * 2 <= n <= 1000
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maximumDifference(int[] nums) {
        int minv = nums[0], res = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > minv) res = Math.max(res, nums[i] - minv);
            minv = Math.min(minv, nums[i]);
        }
        return res;
    }
}