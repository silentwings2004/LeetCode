package LC2401_2700;

public class LC2529_MaximumCountofPositiveIntegerandNegativeInteger {
    /**
     * Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers
     * and the number of negative integers.
     *
     * In other words, if the number of positive integers in nums is pos and the number of negative integers is neg,
     * then return the maximum of pos and neg.
     * Note that 0 is neither positive nor negative.
     *
     * Input: nums = [-2,-1,-1,1,2,3]
     * Output: 3
     *
     * Input: nums = [-3,-2,-1,0,0,1,2]
     * Output: 3
     *
     * Input: nums = [5,20,66,1314]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2000
     * -2000 <= nums[i] <= 2000
     * nums is sorted in a non-decreasing order.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maximumCount(int[] nums) {
        int pos = 0, neg = 0;
        for (int x : nums) {
            if (x > 0) pos++;
            else if (x < 0) neg++;
        }
        return Math.max(pos, neg);
    }
}