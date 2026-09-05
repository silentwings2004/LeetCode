package LC3901_4200;
import java.util.*;
public class LC4040_MinimumOperationstoFormSubsetSumI {
    /**
     * You are given an integer array nums and an integer sum.
     *
     * In one operation, choose an element with current value x and replace it with either 2 * x or floor(x / 2).
     *
     * For each element, all multiplication operations performed on it must occur before any division operations
     * performed on it.
     *
     * Return the minimum number of operations needed so that some subset of the resulting array has a sum exactly equal
     * to sum. If it is impossible, return -1.
     *
     * A subset of an array is a selection of elements (possibly none) from the array.
     *
     * The floor() function returns the integer part of the division.
     *
     * Input: nums = [5,6,10], sum = 4
     * Output: 3
     *
     * Input: nums = [10,2], sum = 13
     * Output: 3
     *
     * Input: nums = [6,3], sum = 8
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
    // time = O(n * sum * (log(sum) + logU), space = O(sum)  U: max(nums)
    public int minOperations(int[] nums, int sum) {
        final int inf = 0x3f3f3f3f;
        int[] f = new int[sum + 1]; // min op to make sum s
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int x : nums) {
            int w = 32 - Integer.numberOfLeadingZeros(x);
            for (int i = sum; i > 0; i--) {
                // 回想一下，0-1 背包是选或不选，状态转移方程为 f[i] = min(f[i], f[i-物品体积] + 物品价值)
                // 本题是分组背包，要枚举选哪个物品（枚举乘了 a 次或者除了 a 次）
                for (int j = 0; (x << j) <= i; j++) {
                    f[i] = Math.min(f[i], f[i - (x << j)] + j); // 物品体积为 x << a，价值为 a
                }
                // 从小到大枚举 x>>a，方便在 x >> a > i 时跳出循环
                for (int j = w - 1; j > 0 && (x >> j) <= i; j--) {
                    f[i] = Math.min(f[i], f[i - (x >> j)] + j); // 物品体积为 x >> a，价值为 a
                }
            }
        }
        return f[sum] == inf ? -1 : f[sum];
    }
}
/**
 * 分组背包 = 把 0-1 背包中的[选或不选]换成[枚举选哪个]
 * Ref: LC416
 * 最少的操作
 * 所有的乘法操作都必须发生在除法之前 => 先乘再除 x2 /2 不可能，不最优 => 要么只做乘法，要么只做除法
 * x' = x * 2^a (x << a)
 * x' = x >> 1
 * 说明什么？O(logx) 个数很少
 * nums[i] 是一个组
 * 我要从这一组当中，要么一个数都不选，要么恰好选一个数
 * 把 0-1 背包看成一组只有 1 个数的分组背包
 * 要么不选，要么枚举选哪个
 * 在 0-1 背包基础上加上枚举选哪个
 * dfs(i,j) -> min
 *  dfs(i-1,j)
 *  dfs(i-1,j-1) ...
 */