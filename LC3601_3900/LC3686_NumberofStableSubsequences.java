package LC3601_3900;

public class LC3686_NumberofStableSubsequences {
    /**
     * You are given an integer array nums.
     *
     * A subsequence is stable if it does not contain three consecutive elements with the same parity when the
     * subsequence is read in order (i.e., consecutive inside the subsequence).
     *
     * Return the number of stable subsequences.
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [1,3,5]
     * Output: 6
     *
     * Input: nums = [2,3,4,2]
     * Output: 14
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int countStableSubsequences(int[] nums) {
        long mod = (long)(1e9 + 7);
        long[] odd = new long[2], even = new long[2];
        for (int x : nums) {
            if (x % 2 == 1) {
                long a = (even[0] + even[1] + 1) % mod;
                long b = odd[0];
                odd[0] = (odd[0] + a) % mod;
                odd[1] = (odd[1] + b) % mod;
            } else {
                long a = (odd[0] + odd[1] + 1) % mod;
                long b = even[0];
                even[0] = (even[0] + a) % mod;
                even[1] = (even[1] + b) % mod;
            }
        }
        long res = (odd[0] + odd[1] + even[0] + even[1]) % mod;
        return (int)res;
    }

    // S2
    // time = O(n), space = O(1)
    public int countStableSubsequences2(int[] nums) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[2][2];
        for (int x : nums) {
            x %= 2;
            f[x][1] = (f[x][1] + f[x][0]) % mod;
            f[x][0] = (f[x][0] + f[x ^ 1][0] + f[x ^ 1][1] + 1) % mod;
        }
        return (int)((f[0][0] + f[0][1] + f[1][0] + f[1][1]) % mod);
    }
}
/**
 * 考虑最后一个数 x = nums[n−1] 选或不选。
 * 选：
 * 1. x 单独组成一个长为 1 的子序列
 * 2. if x = even =>  x 可以添加到末尾为奇数的子序列的后面 => 在 [0,n−2] 中能选出多少个末尾为奇数的稳定子序列
 *  2.1: 后2个数 = even + odd or odd + odd
 * 3. similar for x = odd
 * 4. define f[i+1][x][j]: 下标在[0,i]，末尾元素为 x (0 / 1)，子序列末尾恰好有连续 j + 1 个奇偶性都为 x 的数
 * not pick: f[i][x][j]
 * pick:
 * (1) nums[i] 单独组成一个len = 1 的稳定子序列 => + 1
 * (2) if j == 0 只有1个 x => nums[i] 只能添加到末尾元素奇偶性为 x ^ 1 的稳定子序列的后面 => f[i][x^1][0] + f[i][x^1][1]
 * (3) if j == 1 有2个 x => nums[i] 只能添加到末尾元素奇偶性为 x，且末尾恰好有一个奇偶性为 x 的稳定子序列的后面 => f[i][x][0]
 */