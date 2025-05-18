package LC2700_3000;

public class LC2789_LargestElementinanArrayafterMergeOperations {
    /**
     * You are given a 0-indexed array nums consisting of positive integers.
     *
     * You can do the following operation on the array any number of times:
     *
     * Choose an integer i such that 0 <= i < nums.length - 1 and nums[i] <= nums[i + 1]. Replace the element
     * nums[i + 1] with nums[i] + nums[i + 1] and delete the element nums[i] from the array.
     * Return the value of the largest element that you can possibly obtain in the final array.
     *
     * Input: nums = [2,3,7,9,3]
     * Output: 21
     *
     * Input: nums = [5,3,3]
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long s = nums[n - 1], res = s;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= s) s += nums[i];
            else s = nums[i];
            res = Math.max(res, s);
        }
        return res;
    }
}