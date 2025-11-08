package LC3601_3900;
import java.util.*;
public class LC3732_MaximumProductofThreeElementsAfterOneReplacement {
    /**
     * You are given an integer array nums.
     *
     * You must replace exactly one element in the array with any integer value in the range [-10^5, 10^5] (inclusive).
     *
     * After performing this single replacement, determine the maximum possible product of any three elements at
     * distinct indices from the modified array.
     *
     * Return an integer denoting the maximum product achievable.
     *
     * Input: nums = [-5,7,0]
     * Output: 3500000
     *
     * Input: nums = [-4,-2,-1,-3]
     * Output: 1200000
     *
     * Input: nums = [0,10,0]
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(logn)
    public long maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, a = 100000, b = -a;
        long res = 1L * nums[0] * nums[1] * a;
        res = Math.max(res, 1L * nums[0] * nums[1] * b);
        res = Math.max(res, 1L * nums[n - 1] * nums[n - 2] * a);
        res = Math.max(res, 1L * nums[n - 1] * nums[n - 2] * b);
        res = Math.max(res, 1L * nums[0] * nums[n - 1] * a);
        res = Math.max(res, 1L * nums[0] * nums[n - 1] * b);
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long maxProduct2(int[] nums) {
        int a1 = Integer.MIN_VALUE, a2 = a1;
        int b1 = Integer.MAX_VALUE, b2 = b1;
        for (int x : nums) {
            if (x > a1) {
                a2 = a1;
                a1 = x;
            } else if (x > a2) a2 = x;
            if (x < b1) {
                b2 = b1;
                b1 = x;
            } else if (x < b2) b2 = x;
        }
        long res = 0;
        res = Math.max(res, Math.abs(1L * a1 * a2));
        res = Math.max(res, Math.abs(1L * b1 * b2));
        res = Math.max(res, Math.abs(1L * a1 * b1));
        return res * 100000;
    }
}