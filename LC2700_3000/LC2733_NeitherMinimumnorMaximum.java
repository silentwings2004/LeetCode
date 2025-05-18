package LC2700_3000;

public class LC2733_NeitherMinimumnorMaximum {
    /**
     * Given an integer array nums containing distinct positive integers, find and return any number from the array
     * that is neither the minimum nor the maximum value in the array, or -1 if there is no such number.
     *
     * Return the selected integer.
     *
     * Input: nums = [3,2,1,4]
     * Output: 2
     *
     * Input: nums = [1,2]
     * Output: -1
     *
     * Input: nums = [2,1,3]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * All values in nums are distinct
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int findNonMinOrMax(int[] nums) {
        int a = nums[0], b = nums[0];
        for (int x : nums) {
            a = Math.min(a, x);
            b = Math.max(b, x);
        }
        for (int x : nums) {
            if (x != a && x != b) return x;
        }
        return -1;
    }
}