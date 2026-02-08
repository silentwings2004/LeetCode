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
        int[][][] f = new int[n][2][2]; // f[i][j][k] j: canDel, k: inc
        for (int i = 0; i < n; i++) {
            f[i][0][0] = f[i][0][1] = f[i][1][0] = f[i][1][1] = 1;
        }
        int res = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] != nums[i]) {
                int t = nums[i - 1] < nums[i] ? 1 : 0;
                f[i][0][t] = f[i - 1][0][t ^ 1] + 1;
                f[i][1][t] = f[i - 1][1][t ^ 1] + 1;
            }
            if (i > 1 && nums[i - 2] != nums[i]) {
                int t = nums[i - 2] < nums[i] ? 1 : 0;
                f[i][1][t] = Math.max(f[i][1][t], f[i - 2][0][t ^ 1] + 1);
            }
            res = Math.max(res, Math.max(f[i][1][0], f[i][1][1]));
        }
        return res;
    }
}