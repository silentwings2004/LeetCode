package LC3001_3300;
import java.util.*;
public class LC3299_SumofConsecutiveSubsequences {
    /**
     * We call an array arr of length n consecutive if one of the following holds:
     *
     * arr[i] - arr[i - 1] == 1 for all 1 <= i < n.
     * arr[i] - arr[i - 1] == -1 for all 1 <= i < n.
     * The value of an array is the sum of its elements.
     *
     * For example, [3, 4, 5] is a consecutive array of value 12 and [9, 8] is another of value 17. While [3, 4, 3] and
     * [8, 6] are not consecutive.
     *
     * Given an array of integers nums, return the sum of the values of all consecutive non-empty subsequences.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Note that an array of length 1 is also considered consecutive.
     *
     * Input: nums = [1,2]
     * Output: 6
     *
     * Input: nums = [1,4,2,3]
     * Output: 31
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    public int getSum(int[] nums) {
        long mod = (long)(1e9 + 7), sum = 0;
        for (int x : nums) sum = (sum + x) % mod;
        long res = ((helper(nums, 1) + helper(nums, -1)) % mod - sum + mod) % mod;
        return (int)res;
    }

    private long helper(int[] nums, int t) {
        int n = nums.length;
        long[] f = new long[N], g = new long[N];
        long[] suf = new long[n];
        long mod = (long)(1e9 + 7), res = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            long v = g[x + t];
            suf[i] = v + 1;
            g[x] = (g[x] + v + 1) % mod;
        }
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            long v = f[x - t];
            res = (res + x * suf[i] % mod * (v + 1) % mod) % mod;
            f[x] = (f[x] + v + 1) % mod;
        }
        return res;
    }
}