package LC3601_3900;
import java.math.BigInteger;
import java.util.*;
public class LC3685_SubsequenceSumAfterCappingElements {
    /**
     * You are given an integer array nums of size n and a positive integer k.
     *
     * An array capped by value x is obtained by replacing every element nums[i] with min(nums[i], x).
     *
     * For each integer x from 1 to n, determine whether it is possible to choose a subsequence from the array capped
     * by x such that the sum of the chosen elements is exactly k.
     *
     * Return a 0-indexed boolean array answer of size n, where answer[i] is true if it is possible when using
     * x = i + 1, and false otherwise.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [4,3,2,4], k = 5
     * Output: [false,false,true,true]
     *
     * Input: nums = [1,2,3,4,5], k = 3
     * Output: [true,true,true,true,true]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 4000
     * 1 <= nums[i] <= n
     * 1 <= k <= 4000
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn + n * k), space = O(n + k)
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] f = new boolean[k + 1];
        f[0] = true;
        boolean[] res = new boolean[n];

        for (int x = 1, i = 0; x <= n; x++) {
            while (i < n && nums[i] < x) {
                for (int v = k; v >= nums[i]; v--) {
                    f[v] = f[v] | f[v - nums[i]];
                }
                i++;
            }
            for (int j = 0; j <= n - i && k >= x * j; j++) {
                if (f[k - x * j]) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }

    // S2: bitset优化
    // time = O(nk / w + nlogn + min(n^2, klogn)), space = O(k / w)
    public boolean[] subsequenceSumAfterCapping2(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] res = new boolean[n];
        BigInteger f = BigInteger.ONE;
        BigInteger u = BigInteger.ONE.shiftLeft(k + 1).subtract(BigInteger.ONE); // (1 << (k + 1)) - 1

        int i = 0;
        for (int x = 1; x <= n; x++) {
            // 增量地考虑所有恰好等于 x 的数
            while (i < n && nums[i] == x) {
                f = f.or(f.shiftLeft(nums[i]).and(u)); // 保证 f 的二进制长度 <= k+1
                i++;
            }
            // 枚举（从大于 x 的数中）选了 j 个 x
            for (int j = 0; j <= Math.min(n - i, k / x); j++) {
                if (f.testBit(k - j * x)) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }
}
/**
 * 子集和 = k => 背包问题
 * nums, sum = k
 * knapsack = O(n * k)
 * x => limit
 * for (int i = 0; i < limit; i++) {
 *     for (int c = k; c >= 1; c--) {
 *         dp[c] = dp[c] | dp[c - nums[i]]
 *     }
 * }
 * n - i 个元素都当 x 来用
 * 额外给 n - i 个元素 = x
 * 核心：先把 < x 的元素用完，然后再看剩下 >= x 的可以用几个来凑出 k, 可以用 0 ~ n - i 个
 * 只要能满足凑出 k，就可以标记 break 了
 */