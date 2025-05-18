package LC2700_3000;
import java.util.*;
public class LC2902_CountofSubMultisetsWithBoundedSum {
    /**
     * You are given a 0-indexed array nums of non-negative integers, and two integers l and r.
     *
     * Return the count of sub-multisets within nums where the sum of elements in each subset falls within the inclusive
     * range of [l, r].
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * A sub-multiset is an unordered collection of elements of the array in which a given value x can occur 0, 1, ...,
     * occ[x] times, where occ[x] is the number of occurrences of x in the array.
     *
     * Note that:
     *
     * Two sub-multisets are the same if sorting both sub-multisets results in identical multisets.
     * The sum of an empty multiset is 0.
     *
     * Input: nums = [1,2,2,3], l = 6, r = 6
     * Output: 1
     *
     * Input: nums = [2,1,4,2,7], l = 1, r = 5
     * Output: 7
     *
     * Input: nums = [1,2,1,3,5,2], l = 3, r = 5
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * 0 <= nums[i] <= 2 * 10^4
     * Sum of nums does not exceed 2 * 10^4.
     * 0 <= l <= r <= 2 * 10^4
     * @param nums
     * @param l
     * @param r
     * @return
     */
    // time = O(200 * n), space = O(n)
    final int N = 20010, mod = (int)1e9 + 7;
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        int[] cnt = new int[N];
        long[] f = new long[r + 1], g = new long[r + 1];
        for (int x : nums) cnt[x]++;
        f[0] = 1;

        for (int i = 1; i < N; i++) {
            if (cnt[i] == 0) continue;
            Arrays.fill(g, 0);
            for (int j = 0; j <= r; j++) {
                g[j] = f[j];
                if (j >= i) g[j] = (g[j] + g[j - i]) % mod;
            }
            for (int j = 0; j <= r; j++) {
                f[j] = g[j];
                if (j >= (cnt[i] + 1) * i) f[j] = (f[j] - g[j - (cnt[i] + 1) * i] + mod) % mod;
            }
        }

        long res = 0;
        for (int i = l; i <= r; i++) res = (res + f[i]) % mod;
        res = res * (cnt[0] + 1L) % mod;
        return (int)res;
    }
}
/**
 * 背包问题
 * 每个数看成是一种物品，出现的数量就是物品的数量，总和就是背包的容量 => 多重背包问题
 * for 物品
 *  for 容量
 *   for 选择
 * 1+2+...+x <= 20000 => x * (x + 1) / 2 <= 20000 => x <= 200
 * 不同物品的种类最多不超过200，容量20000
 * => 优化
 * 1. 二进制枚举：最值
 * 2. 单调队列优化：最值
 * 求数量：滑动窗口的总和 => 前缀和，二进制优化会有重复
 * f(i,j) = f(i-1,j) + f(i-1,j-i) + ... + f(i-1,j-k*i)
 * f(i,j-i)
 * f(i,j-2i)
 * f(i,j-3i)
 * 发现第i个数是最后k+1个数的和 => 求区间和，用前缀和
 * g(i,j) = f(i-1,j) + f(i-1,j-i) + f(i-1,j-2i) + ... => 完全背包问题
 * f(i,j) = g(i,j) - g(i,j-(k+1)*i)
 */