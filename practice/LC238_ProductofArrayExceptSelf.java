package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ProductofArrayExceptSelf
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 238. Product of Array Except Self
 */
public class LC238_ProductofArrayExceptSelf {
    /**
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the
     * whole array) fits in a 32 bit integer.
     *
     * Note: Please solve it without division and in O(n).
     *
     * Follow up:
     * Could you solve it with constant space complexity? (The output array does not count as extra space for the
     * purpose of space complexity analysis.)
     *
     * @param nums
     * @return
     */
    // S1: two arrays
    // time = O(n), space = O(n)
    public int[] productExceptSelf(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return null;

        int len = nums.length;
        int[] res = new int[len];
        int[] left = new int[len];
        int[] right = new int[len];
        left[0]= 1;
        right[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        for (int i = 0; i < len; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    // S2: one array
    // time = O(n), space = O(1)
    public int[] productExceptSelf2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return null;

        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;

        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
