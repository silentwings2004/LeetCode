package LC3301_3600;
import java.util.*;
public class LC3351_SumofGoodSubsequences {
    /**
     * You are given an integer array nums. A good subsequence is defined as a subsequence of nums where the absolute
     * difference between any two consecutive elements in the subsequence is exactly 1.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Return the sum of all possible good subsequences of nums.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Note that a subsequence of size 1 is considered good by definition.
     *
     * Input: nums = [1,2,1]
     * Output: 14
     *
     * Input: nums = [3,4,5]
     * Output: 40
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int sumOfGoodSubsequences(int[] nums) {
        long mod = (long)(1e9 + 7);
        HashMap<Integer, Integer> pos = new HashMap<>();
        HashMap<Integer, Long> cnt = new HashMap<>();
        int n = nums.length;
        long[] f = new long[n];
        long res = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            f[i] = x;
            long c = 1;
            if (pos.containsKey(x - 1)) {
                int y = pos.get(x - 1);
                f[i] = (f[i] + (f[y] + 1L * x * cnt.get(x - 1)) % mod) % mod;
                c = (c + cnt.get(x - 1)) % mod;
            }
            if (pos.containsKey(x + 1)) {
                int y = pos.get(x + 1);
                f[i] = (f[i] + (f[y] + 1L * x * cnt.get(x + 1)) % mod) % mod;
                c = (c + cnt.get(x + 1)) % mod;
            }
            res = (res + f[i]) % mod;
            if (pos.containsKey(x)) {
                int y = pos.get(x);
                f[i] = (f[i] + f[y]) % mod;
            }
            pos.put(x, i);
            cnt.put(x, cnt.getOrDefault(x, 0L) + c);
        }
        return (int)res;
    }

    // S2
    // time = O(n), space = O(n)
    public int sumOfGoodSubsequences2(int[] nums) {
        HashMap<Integer, Long> f = new HashMap<>();
        HashMap<Integer, Long> cnt = new HashMap<>();
        long mod = (long)(1e9 + 7);
        for (int x : nums) {
            long c = cnt.getOrDefault(x - 1, 0L) + cnt.getOrDefault(x + 1, 0L) + 1;
            f.put(x, (f.getOrDefault(x, 0L) + f.getOrDefault(x - 1, 0L) + f.getOrDefault(x + 1, 0L) + 1L * x * c) % mod);
            cnt.put(x, (cnt.getOrDefault(x, 0L) + c) % mod);
        }
        long res = 0;
        for (long x : f.values()) res = (res + x) % mod;
        return (int)res;
    }
}
/**
 * 特殊子序列 dp
 * 简单版本：怎么求个数？
 * 遍历数组的同时，维护以 x 结尾的子序列的元素和、个数
 * 从子序列的倒数第二个数转移过来
 * cnt[x] = cnt[x] + cnt[x - 1] + cnt[x + 1] + 1
 * f[x] = f[x] + (f[x - 1] + cnt[x - 1] * x) + (f[x + 1] + cnt[x + 1] * x) + x
 * sum(f.values())
 *
 */