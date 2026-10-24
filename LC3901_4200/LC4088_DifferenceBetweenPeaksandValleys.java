package LC3901_4200;

public class LC4088_DifferenceBetweenPeaksandValleys {
    /**
     * You are given an integer array nums of length n.
     *
     * An index i is called a peak if all of the following conditions hold:
     *
     * 0 < i < n - 1
     * nums[i] > nums[i - 1]
     * nums[i] > nums[i + 1]
     * An index i is called a valley if all of the following conditions hold:
     *
     * 0 < i < n - 1
     * nums[i] < nums[i - 1]
     * nums[i] < nums[i + 1]
     * Return an integer denoting the difference between the sum of all peak values and the sum of all valley values.
     *
     * Input: nums = [1,5,2,4,1,3,0]
     * Output: 9
     *
     * Input: nums = [-5,-6,-1,-2,5,3,2]
     * Output: 12
     *
     * Input: nums = [6,5,2,5,5,3,6]
     * Output: -5
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int peakValleyDifference(int[] nums) {
        int n = nums.length, p = 0, v = 0;
        for (int i = 1; i + 1 < n; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) p += nums[i];
            if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) v += nums[i];
        }
        return p - v;
    }
}