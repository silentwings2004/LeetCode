package LC2401_2700;
import java.util.*;
public class LC2680_MaximumOR {
    /**
     * You are given a 0-indexed integer array nums of length n and an integer k. In an operation, you can choose an
     * element and multiply it by 2.
     *
     * Return the maximum possible value of nums[0] | nums[1] | ... | nums[n - 1] that can be obtained after applying
     * the operation on nums at most k times.
     *
     * Note that a | b denotes the bitwise or between two integers a and b.
     *
     * Input: nums = [12,9], k = 1
     * Output: 30
     *
     * Input: nums = [8,1,2], k = 2
     * Output: 35
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 15
     * @param nums
     * @param k
     * @return
     */
    // S1: dp
    // time = O(n * k^2), space = O(n * k)
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[][] f = new long[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int u = 0; u <= j; u++) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - u] | ((long)nums[i - 1] << u));
                }
            }
        }
        return f[n][k];
    }

    // S2
    // time = O(n * k), space = O(n)
    public long maximumOr2(int[] nums, int k) {
        int n = nums.length;
        long[] suf = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) suf[i] = suf[i + 1] | nums[i];

        long res = 0;
        for (int i = 1; i <= k; i++) {
            long pre = 0;
            for (int j = 0; j < n; j++) {
                long t = pre | ((long)nums[j] << i) | suf[j + 1];
                res = Math.max(res, t);
                pre |= nums[j];
            }
        }
        return res;
    }

    // S3
    // time = O(n), space = O(1)
    public long maximumOr3(int[] nums, int k) {
        int[] cnt = new int[32];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                if ((nums[i] >> j & 1) == 1) cnt[j]++;
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            int[] temp = cnt.clone();
            for (int j = 0; j < 32; j++) {
                if ((nums[i] >> j & 1) == 1) temp[j]--;
            }
            int t = 0;
            for (int j = 0; j < 32; j++) {
                if (temp[j] > 0) t |= 1 << j;
            }
            long x = (long)nums[i] << k;
            long ans = x | t;
            res = Math.max(res, ans);
        }
        return res;
    }
}
/**
 * 最高位的1推的最高 => 集中精力推1个 => 推最高？
 * 1100
 * 1001
 * 推这个9最好，最高位的1都在第4位上，地位是对等的
 */