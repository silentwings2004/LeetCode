package LC3601_3900;

public class LC3830_LongestAlternatingSubarrayAfterRemovingAtMostOneElement {
    /**
     * You are given an integer array nums.
     *
     * A subarray nums[l..r] is alternating if one of the following holds:
     *
     * nums[l] < nums[l + 1] > nums[l + 2] < nums[l + 3] > ...
     * nums[l] > nums[l + 1] < nums[l + 2] > nums[l + 3] < ...
     * In other words, if we compare adjacent elements in the subarray, then the comparisons alternate between strictly
     * greater and strictly smaller.
     *
     * You can remove at most one element from nums. Then, you select an alternating subarray from nums.
     *
     * Return an integer denoting the maximum length of the alternating subarray you can select.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * A subarray of length 1 is considered alternating.
     *
     * Input: nums = [2,1,3,2]
     * Output: 4
     *
     * Input: nums = [3,2,1,2,3,2,1]
     * Output: 4
     *
     * Input: nums = [100000,100000]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n], suf = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] == nums[i - 1]) pre[i] = 1;
            else if (i == 1 || 1L * (nums[i] - nums[i - 1]) * (nums[i - 1] - nums[i - 2]) < 0) {
                pre[i] = pre[i - 1] + 1;
            } else pre[i] = 2;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || nums[i] == nums[i + 1]) suf[i] = 1;
            else if (i == n - 2 || 1L * (nums[i + 1] - nums[i]) * (nums[i + 2] - nums[i + 1]) < 0) {
                suf[i] = suf[i + 1] + 1;
            } else suf[i] = 2;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, pre[i]);
            if (i == 0) res = Math.max(res, suf[1]);
            else if (i < n - 1) {
                res = Math.max(res, Math.max(pre[i - 1], suf[i + 1]));
                if (nums[i - 1] != nums[i + 1]) {
                    boolean fl = pre[i - 1] == 1 || 1L * (nums[i - 1] - nums[i - 2]) * (nums[i + 1] - nums[i - 1]) < 0;
                    boolean fr = suf[i + 1] == 1 || 1L * (nums[i + 1] - nums[i - 1]) * (nums[i + 2] - nums[i + 1]) < 0;
                    if (fl && fr) res = Math.max(res, pre[i - 1] + suf[i + 1]);
                }
            }
        }
        return res;
    }
}