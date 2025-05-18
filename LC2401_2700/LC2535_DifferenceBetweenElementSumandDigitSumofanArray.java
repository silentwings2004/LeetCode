package LC2401_2700;

public class LC2535_DifferenceBetweenElementSumandDigitSumofanArray {
    /**
     * You are given a positive integer array nums.
     *
     * The element sum is the sum of all the elements in nums.
     * The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
     * Return the absolute difference between the element sum and digit sum of nums.
     *
     * Note that the absolute difference between two integers x and y is defined as |x - y|.
     *
     * Input: nums = [1,15,6,3]
     * Output: 9
     *
     * Input: nums = [1,2,3,4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2000
     * 1 <= nums[i] <= 2000
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int differenceOfSum(int[] nums) {
        int s1 = 0, s2 = 0;
        for (int x : nums) {
            s1 += x;
            while (x > 0) {
                s2 += x % 10;
                x /= 10;
            }
        }
        return Math.abs(s1 - s2);
    }
}