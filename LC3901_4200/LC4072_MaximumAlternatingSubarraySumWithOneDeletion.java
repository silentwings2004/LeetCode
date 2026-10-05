package LC3901_4200;
import java.util.*;
public class LC4072_MaximumAlternatingSubarraySumWithOneDeletion {
    /**
     * You are given an integer array nums.
     *
     * You may delete at most one element from nums, then choose a subarray of the resulting array.
     *
     * Return the maximum possible alternating sum of the chosen subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The alternating sum of an array is the sum of its elements at even indices minus the sum of its elements at odd
     * indices. The chosen subarray is reindexed starting from 0 before calculating its alternating sum.
     *
     * Input: nums = [5,-5,1]
     *
     * Output: 11
     *
     * Input: nums = [10,-5,-100]
     *
     * Output: 110
     *
     * Input: nums = [4,7]
     *
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long maxAlternatingSum(int[] nums) {
        final long inf = (long)1E18;
        // 0: 未删除，当前长度为奇数，最后一个为 +
        // 1: 未删除，当前长度为偶数，最后一个为 -
        // 2: 已删除，当前长度为奇数，最后一个为 +
        // 3: 已删除，当前长度为偶数，最后一个为 -
        // 4: 已删除，但还没开始子数组
        long[] f = new long[5];
        Arrays.fill(f, -inf);

        long res = -inf;
        for (int x : nums) {
            long[] g = f.clone();
            // case 1: 不删除 x
            f[0] = Math.max(x, g[1] + x);
            f[1] = g[0] - x;

            // case 2 : 已删除过一个元素，把 x 加进来
            f[2] = g[3] + x;
            f[3] = g[2] - x;

            // case 3: 删除当前的 x
            f[2] = Math.max(f[2], g[0]);
            f[3] = Math.max(f[3], g[1]);

            // 如果之前已经删了元素但子数组还没开始，x 为子数组第一个元素
            f[2] = Math.max(f[2], g[4] + x);

            // 删除当前的 x, 而且删除后还没开始子数组
            f[4] = 0;

            res = Math.max(res, Math.max(Math.max(f[0], f[1]), Math.max(f[2], f[3])));
        }
        return res;
    }

    // S2: Memoization
    // time = O(n), space = O(n)
    final long inf = (long)1E18;
    public long maxAlternatingSum2(int[] nums) {
        int n = nums.length;
        long[][][] memo = new long[n][2][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                memo[i][j][0] = memo[i][j][1] = -inf;
            }
        }
        long res = -inf;
        for (int i = 0; i < n; i++) res = Math.max(res, Math.max(dfs(i, 0, 0, nums, memo), dfs(i, 1, 0, nums, memo)));
        return res;
    }

    private long dfs(int i, int j, int rev, int[] nums, long[][][] memo) {
        if (i == nums.length) return -inf;
        if (memo[i][j][rev] != -inf) return memo[i][j][rev];

        int x = rev == 0 ? nums[i] : -nums[i];
        if (j == 0) return memo[i][j][rev] = Math.max(dfs(i + 1, 0, rev ^ 1, nums, memo), 0) + x;
        return memo[i][j][rev] = Math.max(dfs(i + 1, 1, rev ^ 1, nums, memo) + x, dfs(i + 1, 0, rev, nums, memo));
    }
}
/**
 * ref: LC53 -> LC1186
 * 枚举子数组的第一个和最后一个数
 * dfs(i,j)  j = 0 / 1
 * j = 0 => LC53   max(ai, ai + dfs(i+1,0))
 * j = 1 => max(ai + dfs(i+1,1), dfs(i+1,0))
 * dfs(n,j) = -inf
 * max{dfs(i,j)}
 *
 */