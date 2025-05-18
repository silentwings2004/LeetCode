package LC3001_3300;

public class LC3176_FindtheMaximumLengthofaGoodSubsequenceI {
    /**
     * You are given an integer array nums and a non-negative integer k. A sequence of integers seq is called good if
     * there are at most k indices i in the range [0, seq.length - 2] such that seq[i] != seq[i + 1].
     *
     * Return the maximum possible length of a good subsequence of nums.
     *
     * Input: nums = [1,2,1,1,3], k = 2
     *
     * Output: 4
     *
     * Input: nums = [1,2,3,4,5,1], k = 0
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 500
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= min(nums.length, 25)
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2 * k), space = O(n * k)
    public int maximumLength(int[] nums, int k) {
        int n = nums.length, res = 1;
        int[][] f = new int[n][k + 1];
        for (int i = 0; i < n; i++) f[i][0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                f[i][j] = 1;
                for (int u = 0; u < i; u++) {
                    if (nums[i] == nums[u]) f[i][j] = Math.max(f[i][j], f[u][j] + 1);
                    else {
                        if (j > 0) f[i][j] = Math.max(f[i][j], f[u][j - 1] + 1);
                    }
                }
                res = Math.max(res, f[i][j]);
            }
        }
        return res;
    }
}
/**
 * dp[i][t]: the maximum possible length of a good subsequence from [0:i] && there are t times of such situations
 * x x x x j x x i
 * if (nums[j] == nums[i]) dp[i][t] = dp[j][t] + 1
 * else dp[i][t] = dp[j][t - 1] + 1
 * return max(dp[i][t])
 */