package LC901_1200;

public class LC1085_SumofDigitsintheMinimumNumber {
    /**
     * Given an integer array nums, return 0 if the sum of the digits of the minimum integer in nums is odd, or 1
     * otherwise.
     *
     * Input: nums = [34,23,1,24,75,33,54,8]
     * Output: 0
     *
     * Input: nums = [99,77,33,66,55]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int sumOfDigits(int[] nums) {
        int minv = nums[0];
        for (int x : nums) minv = Math.min(minv, x);
        int sum = 0;
        while (minv > 0) {
            sum += minv % 10;
            minv /= 10;
        }
        return sum % 2 == 1 ? 0 : 1;
    }
}