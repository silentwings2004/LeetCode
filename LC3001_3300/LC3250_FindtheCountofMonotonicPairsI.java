package LC3001_3300;

public class LC3250_FindtheCountofMonotonicPairsI {
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
     * nput: nums = [2,3,2]
     *
     * Output: 4
     *
     * Input: nums = [5,5,5,5]
     *
     * Output: 126
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 2000
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n * 50^2), space = O(n * 50)
    public int countOfPairs(int[] nums) {
        long mod = (long)(1e9 + 7);
        int n = nums.length;
        long[][] f = new long[n][55];
        for (int i = 0; i <= nums[0]; i++) f[0][i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= nums[i]; j++) {
                for (int k = 0; k <= nums[i - 1]; k++) {
                    if (j >= k && nums[i] - j <= nums[i - 1] - k) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i <= nums[n - 1]; i++) res = (res + f[n - 1][i]) % mod;
        return (int)res;
    }
}
/**
 * 数据范围的提示
 * 填数字的题目
 * 思考最后一个数怎么填
 * 最后一个数填了 j
 * 那么接下来需要思考倒数第二个数怎么填 (枚举填 k)
 */