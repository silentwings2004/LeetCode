package LC3001_3300;

public class LC3251_FindtheCountofMonotonicPairsII {
    /**
     * You are given an array of positive integers nums of length n.
     *
     * We call a pair of non-negative integer arrays (arr1, arr2) monotonic if:
     *
     * The lengths of both arrays are n.
     * arr1 is monotonically non-decreasing, in other words, arr1[0] <= arr1[1] <= ... <= arr1[n - 1].
     * arr2 is monotonically non-increasing, in other words, arr2[0] >= arr2[1] >= ... >= arr2[n - 1].
     * arr1[i] + arr2[i] == nums[i] for all 0 <= i <= n - 1.
     * Return the count of monotonic pairs.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: nums = [2,3,2]
     * Output: 4
     *
     * Input: nums = [5,5,5,5]
     * Output: 126
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 2000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n * 1000), space = O(n * 1000)
    public int countOfPairs(int[] nums) {
        long mod = (long)(1e9 + 7);
        int n = nums.length;
        long[][] f = new long[n][1010];
        for (int i = 0; i <= nums[0]; i++) f[0][i] = 1;

        for (int i = 1; i < n; i++) {
            long[] s = new long[nums[i - 1] + 2];
            for (int j = 0; j <= nums[i - 1]; j++) {
                s[j + 1] = s[j] + f[i - 1][j];
            }
            int d = nums[i] - nums[i - 1];
            for (int j = 0; j <= nums[i]; j++) {
                int x = Math.max(0, d);
                f[i][j] = (f[i][j] + s[Math.max(j - x + 1, 0)]) % mod;
            }
        }

        long res = 0;
        for (int i = 0; i <= nums[n - 1]; i++) res = (res + f[n - 1][i]) % mod;
        return (int)res;
    }
}
/**
 * ref: LC2463
 */