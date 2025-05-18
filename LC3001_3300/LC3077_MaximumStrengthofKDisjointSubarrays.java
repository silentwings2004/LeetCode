package LC3001_3300;

public class LC3077_MaximumStrengthofKDisjointSubarrays {
    /**
     * You are given a 0-indexed array of integers nums of length n, and a positive odd integer k.
     *
     * The strength of x subarrays is defined as strength = sum[1] * x - sum[2] * (x - 1) + sum[3] * (x - 2) - sum[4] *
     * (x - 3) + ... + sum[x] * 1 where sum[i] is the sum of the elements in the ith subarray. Formally, strength is
     * sum of (-1)i+1 * sum[i] * (x - i + 1) over all i's such that 1 <= i <= x.
     *
     * You need to select k disjoint subarrays from nums, such that their strength is maximum.
     *
     * Return the maximum possible strength that can be obtained.
     *
     * Note that the selected subarrays don't need to cover the entire array.
     *
     * Input: nums = [1,2,3,-1,2], k = 3
     * Output: 22
     *
     * Input: nums = [12,-2,-2,-2,-2], k = 5
     * Output: 64
     *
     * Input: nums = [-1,-2,-3], k = 1
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * -109 <= nums[i] <= 10^9
     * 1 <= k <= n
     * 1 <= n * k <= 10^6
     * k is odd.
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n * k), space = O(n * k)
    public long maximumStrength(int[] nums, int k) {
        int n = nums.length;
        final long inf = (long)1e18;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long[][] f = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            f[i][i - 1] = -inf;
            long maxv = -inf;
            long t = (k - i + 1) * ((i + 1) % 2 == 0 ? 1 : -1);
            for (int j = i; j <= n - k + i; j++) {
                maxv = Math.max(maxv, f[i - 1][j - 1] - s[j - 1] * t);
                f[i][j] = Math.max(f[i][j - 1], s[j] * t + maxv);
            }
        }
        return f[k][n];
    }

    // S2
    // time = O(n * k), space = O(n * k)
    public long maximumStrength2(int[] nums, int k) {
        int n = nums.length;
        long inf = (long)1e18;
        long[][][] f = new long[n + 1][k + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                f[i][j][0] = f[i][j][1] = -inf;
            }
        }
        for (int i = 0; i <= n; i++) f[i][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j][0] = Math.max(f[i - 1][j][0], f[i - 1][j][1]); // not pick i
                long t = (j % 2 == 0 ? -1L : 1L) * nums[i - 1] * (k - j + 1);
                f[i][j][1] = Math.max(Math.max(f[i - 1][j - 1][0], f[i - 1][j - 1][1]), f[i - 1][j][1]) + t;
            }
        }
        return Math.max(f[n][k][0], f[n][k][1]);
    }
}
/**
 * 划分型dp 不相交
 * 1. 通常来说，f[i][j] 表示前 j 个数分成 i 段，每段选一个子数组，对应的最大能量值 nums[0] ~ nums[j-1]
 * 2. 不选 nums[j-1]: 问题变成前 j-1 个数分成 i 段
 * f[i][j] = max {f[i-1][L] + (s[j] - s[L]) * w }  L 最大是 j-1, L 最小是 i-1
 * f[i][j] = max(f[i][j-1], max {f[i-1][L] + (s[j] - s[L]) * w })
 * w_i = (-1)^(i+1)*(k-i+1)
 * 答案 = f[k][n]
 * 初始值 f[0][j] = 0
 *       f[i][i-1] = -inf
 *       f[i][<i] = -inf
 * f[i][i] = 枚举 L = i-1
 * f[i][i+1] = 枚举 L = (i-1,i)
 * f[i][i+1] = 枚举 L = (i-1,i,i+1)
 * f[i][j-1] = 枚举 L = (i-1,i,...,j-2,j-2) => max 已经算出来了
 * f[i][j] = 枚举 L = (i-1,i,...,j-2,j-1)  i-1,i,...j-2
 * 用一个变量 mx 维护 max
 * 状态包含2个信息：前多少个数，划分成多少段
 *
 * dp[i][j][0]: the maximum strength by finding j subarrays from nums[1:i] and nums[i] is not part of sum[j]
 * dp[i][j][1]: the maximum strength by finding j subarrays from nums[1:i] and nums[i] is part of sum[j]
 * dp[i][j] = sum[1] * k - sum[2] * (k -1) + sum[3] * (k - 2)
 * [x x x x x x] i
 * 1. we do not pick nums[i] => dp[i][j][0] = max(dp[i-1][j][0], dp[i][j][1])
 * suppose k % 2 == 0
 * 2. we do pick nums[i] => dp[i][j][1] = max(dp[i-1][j-1][0], dp[i-1][j-1][1]) - nums[i] * (k + 1 - j)
 *                          dp[i][j][1] = dp[i-1][j][1] - nums[i] * (k + 1 - j)
 * return max(dp[n][k][0], dp[n][k][1]);
 */