package LC3901_4200;

import java.util.Arrays;

public class LC4041_MinimumOperationstoFormSubsetSumII {
    /**
     * You are given an integer array nums and an integer sum.
     *
     * In one operation, choose an element with current value x and replace it with either 2 * x or floor(x / 2).
     *
     * For each element, multiplication and division operations may be performed in any order.
     *
     * Return the minimum number of operations needed so that some subset of the resulting array has a sum exactly equal
     * to sum. If it is impossible, return -1.
     *
     * A subset of an array is a selection of elements (possibly none) from the array.
     *
     * The floor() function returns the integer part of the division.
     *
     * Input: nums = [10,2], sum = 13
     * Output: 3
     *
     * Input: nums = [6,3], sum = 8
     * Output: 2
     *
     * Input: nums = [2,2], sum = 7
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 500
     * 1 <= sum <= 5000
     * @param nums
     * @param sum
     * @return
     */
    // time = O(n * sum * logU * log(sum)), space = O(sum)
    public int minOperations(int[] nums, int sum) {
        final int inf = 0x3f3f3f3f;
        int[] f = new int[sum + 1]; // min op to make sum s
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int i = sum; i > 0; i--) {
                for (int a = 0; (x >> a) > 0; a++) {
                    for (int b = 0; (x >> a << b) <= i; b++) {
                        f[i] = Math.min(f[i], f[i - (x >> a << b)] + a + b);
                    }
                }
            }
            if (f[sum] == 0) return 0;
        }
        return f[sum] == inf ? -1 : f[sum];
    }
}
/**
 * 先乘再除 => 没有意义 => 先除后乘
 * O(logx * logS)
 * 两个枚举叠加在一起
 */