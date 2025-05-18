package LC001_300;
import java.util.*;
public class LC152_MaximumProductSubarray {
    /**
     * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
     * and return the product.
     *
     * It is guaranteed that the answer will fit in a 32-bit integer.
     *
     * A subarray is a contiguous subsequence of the array.
     *
     * Input: nums = [2,3,-2,4]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * -10 <= nums[i] <= 10
     * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int f = nums[0], g = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int a = nums[i], fa = f * a, ga = g * a;
            f = Math.max(a, Math.max(fa, ga));
            g = Math.min(a, Math.min(fa, ga));
            res = Math.max(res, f);
        }
        return res;
    }
}
/**
 * f(i-1):最大  -> f(i)  ai > 0 => ai * f(i-1); ai < 0 => ai * g(i-1); ai == 0 => 0
 * g(i-1):最小 -> g(i)  ai > 0 => ai * g(i-1); ai < 0 => ai * f(i-1)
 *
 * 固定右边界，探索左边界
 * dp[i]: maximum subarray ending at i
 * x [x x x i] x x
 * 找一个sum最大的左边界
 * 突破口就是i
 * 加和变成陈乘积
 */
