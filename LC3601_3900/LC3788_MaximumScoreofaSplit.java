package LC3601_3900;

public class LC3788_MaximumScoreofaSplit {
    /**
     * You are given an integer array nums of length n.
     *
     * Choose an index i such that 0 <= i < n - 1.
     *
     * For a chosen split index i:
     *
     * Let prefixSum(i) be the sum of nums[0] + nums[1] + ... + nums[i].
     * Let suffixMin(i) be the minimum value among nums[i + 1], nums[i + 2], ..., nums[n - 1].
     * The score of a split at index i is defined as:
     *
     * score(i) = prefixSum(i) - suffixMin(i)
     *
     * Return an integer denoting the maximum score over all valid split indices.
     *
     * Input: nums = [10,-1,3,-4,-5]
     * Output: 17
     *
     * Input: nums = [-7,-5,3]
     * Output: -2
     *
     * Input: nums = [1,1]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long maximumScore(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) suf[i] = Math.min(suf[i + 1], nums[i]);
        long res = Long.MIN_VALUE, s = 0;
        for (int i = 0; i < n - 1; i++) {
            s += nums[i];
            res = Math.max(res, s - suf[i + 1]);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long maximumScore2(int[] nums) {
        int n = nums.length;
        long s = 0, res = Long.MIN_VALUE;
        for (int x : nums) s += x;
        for (int i = n - 2, suf = nums[n - 1]; i >= 0; i--) {
            s -= nums[i + 1];
            res = Math.max(res, s - suf);
            suf = Math.min(suf, nums[i]);
        }
        return res;
    }
}