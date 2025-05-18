package LC601_900;
import java.util.*;
public class LC628_MaximumProductofThreeNumbers {
    /**
     * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
     *
     * Input: nums = [1,2,3]
     * Output: 6
     *
     * Input: nums = [1,2,3,4]
     * Output: 24
     *
     * Input: nums = [-1,-2,-3]
     * Output: -6
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^4
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int b = nums[0] * nums[1] * nums[n - 1];
        return Math.max(a, b);
    }
}
/**
 * 正正正 => 最大的3个数
 * 正正负 => 数组的长度为3
 * 正负负 => 最大的整数，最小的2个负数
 * 负负负 => 数组全为负，挑最大的3个数
 */