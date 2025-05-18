package LC3301_3600;

public class LC3473_SumofKSubarraysWithLengthatLeastM {
    /**
     * You are given an integer array nums and two integers, k and m.
     *
     * Return the maximum sum of k non-overlapping subarrays of nums, where each subarray has a length of at least m.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [1,2,-1,3,3,4], k = 2, m = 2
     * Output: 13
     *
     * Input: nums = [-10,3,-1,-2], k = 4, m = 1
     * Output: -10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2000
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= floor(nums.length / m)
     * 1 <= m <= 3
     * @param nums
     * @param k
     * @param m
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= k; i++) f[0][i] = Integer.MIN_VALUE;

        for (int j = 1; j <= k; j++) {
            int maxv = Integer.MIN_VALUE;
            for (int i = j * m; i <= n; i++) {
                maxv = Math.max(maxv, f[i - m][j - 1] - s[i - m]);
                if (i == j * m) f[i][j] = s[i] + maxv;
                else f[i][j] = Math.max(f[i - 1][j], s[i] + maxv);
            }
        }
        return f[n][k];
    }
}