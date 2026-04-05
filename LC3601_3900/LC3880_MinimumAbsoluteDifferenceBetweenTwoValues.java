package LC3601_3900;

public class LC3880_MinimumAbsoluteDifferenceBetweenTwoValues {
    /**
     * You are given an integer array nums consisting only of 0, 1, and 2.
     *
     * A pair of indices (i, j) is called valid if nums[i] == 1 and nums[j] == 2.
     *
     * Return the minimum absolute difference between i and j among all valid pairs. If no valid pair exists, return -1.
     *
     * The absolute difference between indices i and j is defined as abs(i - j).
     *
     * Input: nums = [1,0,0,2,0,1]
     * Output: 2
     *
     * Input: nums = [1,0,1,0]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 2
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minAbsoluteDifference(int[] nums) {
        int n = nums.length, a = -1, b = -1, res = n + 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) a = i;
            else if (nums[i] == 2) b = i;
            if (a != -1 && b != -1) res = Math.min(res, Math.abs(a - b));
        }
        return res == n + 1 ? -1 : res;
    }
}