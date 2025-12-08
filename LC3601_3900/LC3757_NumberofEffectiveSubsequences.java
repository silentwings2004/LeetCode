package LC3601_3900;

public class LC3757_NumberofEffectiveSubsequences {
    /**
     * You are given an integer array nums.
     *
     * The strength of the array is defined as the bitwise OR of all its elements.
     *
     * A subsequence is considered effective if removing that subsequence strictly decreases the strength of the
     * remaining elements.
     *
     * Return the number of effective subsequences in nums. Since the answer may be large, return it modulo 10^9 + 7.
     *
     * The bitwise OR of an empty array is 0.
     *
     * Input: nums = [1,2,3]
     * Output: 3
     *
     * Input: nums = [7,4,6]
     * Output: 4
     *
     * Input: nums = [8,8]
     * Output: 1
     *
     * Input: nums = [2,2,1]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n + k * 2^k), space = O(n + 2^k)
    public int countEffective(int[] nums) {
        long mod = (long)(1e9 + 7);
        int n = nums.length, tot = 0;
        for (int x : nums) tot |= x;
        if (tot == 0) return 0;

        int[] b = new int[20];
        int k = 0;
        for (int i = 0; i < 20; i++) {
            if ((tot >> i & 1) != 0) b[k++] = i;
        }

        int[] cnt = new int[1 << k];
        for (int x : nums) {
            int v = 0;
            for (int j = 0; j < k; j++) {
                if ((x >> b[j] & 1) != 0) v |= 1 << j;
            }
            cnt[v]++;
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 1 << k; j++) {
                if ((j & (1 << i)) != 0) cnt[j] += cnt[j ^ (1 << i)];
            }
        }

        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) p[i] = p[i - 1] * 2 % mod;
        long res = 0;
        for (int i = 1; i < 1 << k; i++) {
            int cmp = ((1 << k) - 1) ^ i, c0 = cnt[cmp];
            long w = p[c0];
            int v = Integer.bitCount(i);
            if ((v & 1) == 1) res = (res + w) % mod;
            else res = (res - w + mod) % mod;
        }
        return (int)res;
    }
}
/**
 * SOS DP (高维前缀和)
 * 问题等价于: 计算 nums 的子序列 b 的个数，满足 b 的按位或严格小于 or。
 * 所有子序列的个数 - or 等于3的子序列的个数 = < 3 的子序列个数
 * {or(b) = or(a)}
 * 计数或者组合数学问题，直接计算方案数不太好做 => 恰好型问题 转化成"至多型"或者"至少型"问题
 *
 * 1. 去掉子序列，剩下的还是子序列， 求子序列的 OR < nums 所有元素的按位或的子序列个数
 * 2. 所有子序列的个数 2^n - 子序列的 OR = nums 所有元素的按位或的子序列个数 = or_val
 * 3. 子序列的 OR 是 or_val 的[子集]的子序列个数
 * 4. 根据容斥原理，问题相当于计算一个二进制数(看成集合)的子集 S 的个数
 *    比如 nums 有 3 个数是 S 的子集,那么有2^3个按位或是 S 的子集的子序列
 * 5. 最后，剩下的问题是，如何计算 nums 中有多少个数是 S 的子集
 * 6. SOS (sum over subset) DP  变形：求 nums 中的是 S 的子集的元素和
 * 目标：计算 nums 中有多少个数是 S 的子集
 * 把 S 的所有子集，分解为互不相交的
 *
 * nums 中的第 i 位是 1 且是 S 的子集的元素个数
 * nums 中的第 i 位是 0 且是 S^(1 << i) 的子集的元素个数
 *
 * f[i][S] 表示 S 的大于 i 的位置都保留，nums 中的是 S 的子集的元素个数
 * f(2,111)
 * 讨论 i 这个位置
 * a. 如果 S 的 i 位是 0，那么只能不选，问题变成 S 的大于 i-1 的位置都保留，讨论这种约束下，nums 中的是 S 的子集的元素个数
 * b. 如果 S 的 i 位是 1
 *      选 1 (保留1), 那么问题变成 S 的大于 i-1 的位置都保留，讨论这种约束下，nums 中的是 S 的子集的元素个数
 *          f[i][S] = f[i-1][S]
*       不选 1 (不保留1), 那么问题变成 S^(1 << i) 的大于 i-1 的位置都保留，讨论这种约束下，nums 中的是 S 的子集的元素个数
 *          f[i][S] = f[i-1][S^(1 << i)]
 *      两种情况加起来：
 *          f[i][S] = f[i-1][S] + f[i-1][S^(1 << i)]
 * 初始值：f[-1][x] = nums 中的 x 的个数
 * 如果要求 nums 中有多少个数是 S 的子集，那么根据定义，就是 f[w - 1][S]
 *
 */