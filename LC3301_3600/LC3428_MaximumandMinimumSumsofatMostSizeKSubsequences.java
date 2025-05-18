package LC3301_3600;
import java.util.*;
public class LC3428_MaximumandMinimumSumsofatMostSizeKSubsequences {
    /**
     * You are given an integer array nums and a positive integer k. Return the sum of the maximum and minimum elements
     * of all subsequences of nums with at most k elements.
     *
     * A non-empty subsequence is an array that can be derived from another array by deleting some or no elements
     * without changing the order of the remaining elements.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: nums = [1,2,3], k = 2
     * Output: 24
     *
     * Input: nums = [5,0,6], k = 1
     * Output: 22
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 1 <= k <= min(100, nums.length)
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn + n * k), space = O(n)
    final long mod = (long)(1e9 + 7);
    long[] f, g;
    public int minMaxSums(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;

        f = new long[n + 1];
        g = new long[n + 1];
        f[0] = g[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] * i % mod;
            g[i] = g[i - 1] * qmi(i, mod - 2) % mod;
        }

        for (int i = 0; i < n; i++) {
            long s = 0;
            for (int j = 0; j < Math.min(k, i + 1); j++) s = (s + C(i, j)) % mod;
            res = (res + s * (nums[i] + nums[n - 1 - i]) % mod) % mod;
        }
        return (int)res;
    }

    private long C(int a, int b) {
        if (a < b) return 0;
        return f[a] * g[b] % mod * g[a - b] % mod;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}