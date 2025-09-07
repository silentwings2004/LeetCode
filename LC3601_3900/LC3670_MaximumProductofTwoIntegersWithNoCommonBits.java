package LC3601_3900;

public class LC3670_MaximumProductofTwoIntegersWithNoCommonBits {
    /**
     * You are given an integer array nums.
     *
     * Your task is to find two distinct indices i and j such that the product nums[i] * nums[j] is maximized, and the
     * binary representations of nums[i] and nums[j] do not share any common set bits.
     *
     * Return the maximum possible product of such a pair. If no such pair exists, return 0.
     *
     * Input: nums = [1,2,3,4,5,6,7]
     * Output: 12
     *
     * Input: nums = [5,6,4]
     * Output: 0
     *
     * Input: nums = [64,8,32]
     * Output: 2048
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1: 高维前缀和
    // time = O(n + UlogU), space = O(U)  U: max(nums)
    public long maxProduct(int[] nums) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);
        int m = 32 - Integer.numberOfLeadingZeros(mx);
        long[] f = new long[1 << m];
        for (int x : nums) f[x] = x;
        for (int i = 0; i < m; i++) {
            for (int mask = 0; mask < 1 << m; mask++) {
                if ((mask >> i & 1) != 0) {
                    int ns = mask ^ (1 << i);
                    f[mask] = Math.max(f[mask], f[ns]);
                }
            }
        }
        int full = (1 << m) - 1;
        long res = 0;
        for (int x : nums) {
            int y = full ^ x;
            res = Math.max(res, f[y] * x);
        }
        return res;
    }

    // S1.2
    // time = O(n + UlogU), space = O(U)  U: max(nums)
    public long maxProduct2(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int w = 32 - Integer.numberOfLeadingZeros(mx);
        int u = 1 << w;
        int[] f = new int[u];
        for (int x : nums) {
            f[x] = x;
        }

        for (int i = 0; i < w; i++) {
            for (int s = 3; s < u; s++) {
                s |= 1 << i; // 快速跳到第 i 位是 1 的 s
                f[s] = Math.max(f[s], f[s ^ (1 << i)]);
            }
        }

        long ans = 0;
        for (int x : nums) {
            ans = Math.max(ans, (long) x * f[(u - 1) ^ x]);
        }
        return ans;
    }
}
/**
 * 高维前缀和
 * 子集和 DP (SOS DP)
 * 求出A的补集的所有子集里的最大值
 * O(m * 2^m)    m <= 20
 * A = {0, 2, 3, 5} -> {0, 3}
 * B = {0, 1, 3, 5} -> {0, 3}
 * T belongs to A => max(T)
 * 优化掉重复的枚举
 * A = {0, 2, 3, 5}
 * 把 A 拆分成若干个子集的并集
 * {2,3,5}
 * {0,3,5}
 * {0,2,5}
 * {0,2,3}
 * => 分别求这些子集的最大值，再求一次这些最大值的最大值
 * 根本原因：这些子集的并集 = A
 */