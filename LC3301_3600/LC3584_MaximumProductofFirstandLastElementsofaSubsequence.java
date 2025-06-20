package LC3301_3600;

public class LC3584_MaximumProductofFirstandLastElementsofaSubsequence {
    /**
     * You are given an integer array nums and an integer m.
     *
     * Return the maximum product of the first and last elements of any subsequence of nums of size m.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [-1,-9,2,3,-2,-3,1], m = 1
     * Output: 81
     *
     * Input: nums = [1,3,-5,5,6,-4], m = 3
     * Output: 20
     *
     * Input: nums = [2,-1,2,-6,5,2,-5,7], m = 2
     * Output: 35
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= m <= nums.length
     * @param nums
     * @param m
     * @return
     */
    // time = O(n), space = O(1)
    public long maximumProduct(int[] nums, int m) {
        long res = Long.MIN_VALUE, maxv = Long.MIN_VALUE, minv = Long.MAX_VALUE;
        int n = nums.length;
        for (int i = m - 1; i < n; i++) {
            int x = nums[i], y = nums[i - m + 1];
            minv = Math.min(minv, y);
            maxv = Math.max(maxv, y);
            res = Math.max(res, Math.max(1L * x * minv, 1L * x * maxv));
        }
        return res;
    }
}
/**
 * 题目意思是首尾元素下标相差至少是 m−1，这样子序列长度才能是 m（否则长度就小于 m 了）。
 * 我们只需关注首尾两个数，于是问题转化成：
 * nums 的任意下标相差至少为 m−1 的两数之积的最大值
 * 枚举 nums[i]，为了让乘积最大，贪心地，维护 [0,i−m+1] 中的最小值和最大值。维护最小值是因为负负得正，答案可以来自两个负数相乘。
 * 注意最终答案可能是负数，要把答案初始化成 −∞。
 * 注意无需特判 m=1 的情况，此时答案来自 nums 的最大值与自己相乘，或者最小值与自己相乘，这一定可以被我们枚举到。
 */