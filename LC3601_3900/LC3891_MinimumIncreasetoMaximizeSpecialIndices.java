package LC3601_3900;

public class LC3891_MinimumIncreasetoMaximizeSpecialIndices {
    /**
     * You are given an integer array nums of length n.
     *
     * An index i (0 < i < n - 1) is special if nums[i] > nums[i - 1] and nums[i] > nums[i + 1].
     *
     * You may perform operations where you choose any index i and increase nums[i] by 1.
     *
     * Your goal is to:
     *
     * Maximize the number of special indices.
     * Minimize the total number of operations required to achieve that maximum.
     * Return an integer denoting the minimum total number of operations required.
     *
     * Input: nums = [1,2,2]
     * Output: 1
     *
     * Input: nums = [2,1,1,3]
     * Output: 2
     *
     * Input: nums = [5,2,1,4,3]
     * Output: 4
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long minIncrease(int[] nums) {
        int n = nums.length;
        long[] w = new long[n];
        for (int i = 1; i < n - 1; i++) {
            long v = Math.max(nums[i - 1], nums[i + 1]) + 1L - nums[i];
            w[i] = Math.max(0L, v);
        }

        if (n % 2 == 1) {
            long res = 0;
            for (int i = 1; i < n - 1; i += 2) res += w[i];
            return res;
        }

        long[] pre = new long[n];
        long[] suf = new long[n];
        for (int i = 1; i + 2 < n; i += 2) pre[i] = (i >= 2 ? pre[i - 2] : 0) + w[i];
        for (int i = n - 2; i >= 2; i -= 2) suf[i] = (i + 2 < n ? suf[i + 2] : 0) + w[i];

        long res = suf[2];
        for (int i = 1; i + 2 < n; i += 2) {
            long v = pre[i] + (i + 3 < n ? suf[i + 3] : 0);
            res = Math.min(res, v);
        }
        return res;
    }
}