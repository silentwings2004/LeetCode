package LC3601_3900;

public class LC3743_MaximizeCyclicPartitionScore {
    /**
     * You are given a cyclic array nums and an integer k.
     *
     * Partition nums into at most k subarrays. As nums is cyclic, these subarrays may wrap around from the end of the
     * array back to the beginning.
     *
     * The range of a subarray is the difference between its maximum and minimum values. The score of a partition is the
     * sum of subarray ranges.
     *
     * Return the maximum possible score among all cyclic partitions.
     *
     * Input: nums = [1,2,3,3], k = 2
     * Output: 3
     *
     * Input: nums = [1,2,3,3], k = 1
     * Output: 2
     *
     * Input: nums = [1,2,3,3], k = 4
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k), space = O(k
    public long maximumScore(int[] nums, int k) {
        int n = nums.length;
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[idx]) idx = i;
        }
        long v1 = maxProfit(nums, idx, idx + n, k);
        long v2 = maxProfit(nums, idx + 1, idx + 1 + n, k);
        return Math.max(v1, v2);
    }

    private long maxProfit(int[] prices, int l, int r, int k) {
        int n = prices.length;
        final long inf = (long)1E18;
        long[][] f = new long[k + 2][3];
        for (int j = 1; j <= k + 1; j++) f[j][1] = -inf;
        f[0][0] = -inf;
        for (int i = l; i < r; i++) {
            int p = prices[i % n];
            for (int j = k + 1; j > 0; j--) {
                f[j][0] = Math.max(f[j][0], Math.max(f[j][1] + p, f[j][2] - p));
                f[j][1] = Math.max(f[j][1], f[j - 1][0] - p);
                f[j][2] = Math.max(f[j][2], f[j - 1][0] + p);
            }
        }
        return f[k + 1][0];
    }
}