package LC3901_4200;
import java.util.*;
public class LC3956_MaximumSumofMNonOverlappingSubarraysI {
    /**
     * You are given an integer array nums of length n, and three integers m, l, and r.
     *
     * Your task is to select at least one and at most m non-overlapping subarrays from nums such that:
     *
     * Each selected subarray has a length between [l, r] (inclusive).
     * The total sum of all selected subarrays is maximized.
     * Return the maximum total sum you can achieve.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [4,1,-5,2], m = 2, l = 1, r = 3
     * Output: 7
     *
     * Input: nums = [1,0,3,4], m = 2, l = 1, r = 2
     * Output: 8
     *
     * Input: nums = [-1,7,-4], m = 1, l = 2, r = 3
     * Output: 6
     *
     * Input: nums = [-3,-4,-1], m = 2, l = 1, r = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 1000
     * -10^9 <= nums[i] <= 10^9
     * 1 <= m <= n
     * 1 <= l <= r <= n
     * @param nums
     * @param m
     * @param l
     * @param r
     * @return
     */
    // time = O(n * m), space = O(n * m)
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) s[i + 1] = s[i] + nums[i];

        // f[i][j] 表示在前 j 个数 (下标 0 到 j-1) 中选出恰好 i 个子数组，所选元素之和的最大值
        long inf = (long)1E18;
        long[][] f = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) Arrays.fill(f[i], -inf);

        long res = -inf * 2;
        for (int i = 1; i <= m; i++) {
            int[] dq = new int[n + 1];
            int hh = 0, tt = -1;
            // 前 i 个子数组至少占用了 i * l 个位置
            for (int j = i * l; j <= n; j++) {
                // 1. 入
                int k = j - l;
                long v = f[i - 1][k] - s[k];
                while (hh <= tt && f[i - 1][dq[tt]] - s[dq[tt]] <= v) tt--;
                dq[++tt] = k;

                // 2. 更新：不选 nums[j-1] vs 选一个以 j-1 结尾的子数组
                f[i][j] = Math.max(f[i][j - 1], f[i - 1][dq[hh]] - s[dq[hh]] + s[j]);

                // 3. 出，下一轮循环队首离开窗口
                if (dq[hh] <= j - r) hh++;
            }
            res = Math.max(res, f[i][n]);
        }
        return res;
    }
}
/**
 * 单调队列优化 DP
 * 对于 DP 问题，思考「最后一步发生了什么」，可以帮助我们找到状态定义和状态转移方程。
 * 最后一个数：
 * 1.不选：n-1 个数中选 j 个子数组，枚举 j，所有情况取个最大值
 * 2.选：包含最后一个数的子数组，确定子数组的左端点 j =>
 * 从 0~i 中选 => 0~j-1 中少选一个子数组的最大值
 *
 * f[i][j]: 从前 j 个数(下标 0 到 j-1) 中选出恰好 i 个子数组 (恰好：比至多好，可以枚举) 所选元素之和的最大值
 * 不选第 j 个数，从前 j-1 个数中选出恰好 i 个子数组，所选元素之和的最大值 f[i][j-1]
 * 选第 j 个数，枚举右端点为 j-1 的子数组，左端点为 L 从前 L 个数中选出恰好 i-1 个子数组，所选元素之和的最大值 f[i-1][L] + s[j] - s[L]
 * f[i][j] = max(f[i][j-1], max(f[i-1][L] + s[j] - s[L] for L = [j-r,j-left]))
 * => s[j] 与 L 无关，可以迁移到循环外 f[i][j] = max(f[i][j-1], s[j] + max(f[i-1][L] - s[L] for L = [j-r,j-left]))
 * 随着j 变大，定长滑动窗口 => 滑动窗口中的最大值 =>
 * 定义 d[L] = f[i-1][L] - s[L] 滑动窗口求最大值 (LC239)
 *
 * 初始值：根据定义来，f[0][j] = 0, f[i][0] = -inf (i > 0)
 * 答案为 f[i][n] 中的最大值
 *
 * 定义为“恰好”是有优势的，比如改为至少选 2 个，比“至多”能解决更复杂的情况
 * 核心想法为枚举恰好选 i 个子数组
 *
 * 为什么入：k = j - l 因为长度至少为 l => 当前 j 能用的最右起点：j-l => 当前新增子数组窗口的左端点 L = j - l
 * 滑窗单调队列里维护的值是 g(k) = f[i-1][k] - s[k] 的最大值 (k 是滑窗的左端点 L)
 * 当前新滑入一个 k = j - l，那么我们就把滑窗队列里 g 值 <= g(k) 的都可以出队了，因为最大值不可能是这个小于等于 g(k) 的值
 */