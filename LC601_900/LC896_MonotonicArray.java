package LC601_900;

public class LC896_MonotonicArray {
    /**
     * An array is monotonic if it is either monotone increasing or monotone decreasing.
     *
     * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing
     * if for all i <= j, nums[i] >= nums[j].
     *
     * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
     *
     * Input: nums = [1,2,2,3]
     * Output: true
     *
     * Input: nums = [6,5,4,4]
     * Output: true
     *
     * Input: nums = [1,3,2]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isMonotonic(int[] nums) {
        boolean x = true, y = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) x = false;
            if (nums[i - 1] < nums[i]) y = false;
        }
        return x || y;
    }
}